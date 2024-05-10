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
            response.sendRedirect("index.jsp?error=captcha");
            return;
        }

        // Validate username and password
        boolean checkUsername = false;
        boolean checkPassword = false;
        String role = ""; // Variable to store user role

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                checkUsername = true;
                if (user.getPassword().equals(password)) {
                    checkPassword = true;
                    role = user.getRole(); // Store user role
                    HttpSession userSession = request.getSession();
                    userSession.setAttribute("username", user.getUsername());
                    userSession.setAttribute("role", role);
                    break;
                }
            }
        }

        // Redirect based on validation result and user role
        if (checkUsername && checkPassword) {
            if (role.equalsIgnoreCase("admin")) {
                response.sendRedirect("instructor_myaccount.jsp");
            } else {
                response.sendRedirect("student_myaccount.jsp");
            }
        } else {
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
}
