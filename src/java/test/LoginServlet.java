package test;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final List<User> users = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            String driver = getServletConfig().getInitParameter("DB_driver");
            Class.forName(driver);

            Class.forName(getServletContext().getInitParameter("dbDriver"));
            String url = getServletConfig().getInitParameter("DB_url");
            String DBusername = getServletConfig().getInitParameter("DB_username");
            String DBpassword = getServletConfig().getInitParameter("DB_password");

            try (Connection con = DriverManager.getConnection(url, DBusername, DBpassword); PreparedStatement ps = con.prepareStatement("SELECT * FROM USER_INFO ORDER BY username"); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String username = rs.getString("USERNAME").trim();
                    String encryptedPassword = rs.getString("PASSWORD").trim();
                    String role = rs.getString("ROLE").trim();
                    String decryptedPassword = Security.decrypt(encryptedPassword, config.getServletContext());
                    users.add(new User(username, decryptedPassword, role));
                }
            } catch (Exception ex) {
                throw new ServletException("Error decrypting password", ex);
            }
        } catch (ClassNotFoundException e) {
            throw new ServletException("Initialization failed due to database issues", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the Account button is clicked
        if (request.getParameter("account") != null) {
            handleAccountButton(request, response);
            return;
        }

        // Get user input
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String enteredCaptcha = request.getParameter("captcha");

        // Get the correct CAPTCHA value from the session
        HttpSession session = request.getSession();
        String correctCaptcha = (String) session.getAttribute("captcha");

        // Validate CAPTCHA
        if (correctCaptcha != null && !correctCaptcha.equalsIgnoreCase(enteredCaptcha)) {
            // CAPTCHA mismatch, redirect with error
            response.sendRedirect("login.jsp?error=captcha");
            return;
        }

        // Authenticate the user using the Derby database
        User user = null;
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }

        // Redirect based on validation result and user role
        if (user != null) {
            // Store user details in the session
            session.setAttribute("username", user.getUsername());

            // Map the role from Derby database to MySQL database
            String role = user.getRole();
            if (role.equals("Admin")) {
                role = "Instructor";
            } else if (role.equals("Guest")) {
                role = "Student";
            }
            session.setAttribute("role", role);

            // Retrieve user details from the MySQL database
            SQLUser sqlUser = getUserDetails(username);
            if (sqlUser != null) {
                System.out.println("First Name (Servlet): " + sqlUser.getFirstName());
                System.out.println("Last Name (Servlet): " + sqlUser.getLastName());
                session.setAttribute("firstname", sqlUser.getFirstName());
                session.setAttribute("lastname", sqlUser.getLastName());
                session.setAttribute("email", sqlUser.getEmail());
                session.setAttribute("password", sqlUser.getPassword());
                session.setAttribute("link", sqlUser.getLink());
            }

            // Redirect based on user role
            if (role.equals("Instructor")) {
                response.sendRedirect("instructor_myaccount.jsp");
            } else if (role.equals("Student")) {
                response.sendRedirect("student_myaccount.jsp");
            }
        } else {
            // Authentication failed, redirect with error
            response.sendRedirect("error_3.jsp");
        }
    }

    // Method to handle Account button
    private void handleAccountButton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role != null && role.equalsIgnoreCase("admin")) {
            response.sendRedirect("instructor_myaccount.jsp");
        } else {
            response.sendRedirect("student_myaccount.jsp");
        }
    }

    private SQLUser getUserDetails(String username) {
        SQLUser user = null;
        String url = getServletContext().getInitParameter("dbUrl");
        String dbUser = getServletContext().getInitParameter("dbUser");
        String dbPassword = getServletContext().getInitParameter("dbPassword");
        String query = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    String link = rs.getString("link");

                    System.out.println("Guest User Details:");
                    System.out.println("First Name: " + firstname);
                    System.out.println("Last Name: " + lastname);
                    System.out.println("Email: " + email);
                    System.out.println("Password: " + password);
                    System.out.println("Role: " + role);
                    System.out.println("Link: " + link);

                    user = new SQLUser(firstname, lastname, username, email, password, role, link);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
