//package test;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/signup")
//public class SignupServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private static final List<User> users = new ArrayList<>();
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//            String driver = getServletConfig().getInitParameter("DB_driver");
//            Class.forName(driver);
//
//            String url = getServletConfig().getInitParameter("DB_url");
//            String DBusername = getServletConfig().getInitParameter("DB_username");
//            String DBpassword = getServletConfig().getInitParameter("DB_password");
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String firstname = request.getParameter("firstname");
//        String lastname = request.getParameter("lastname");
//        String username = request.getParameter("username");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String role = request.getParameter("role");
//        String link = request.getParameter("link");
//
//        // Add the new user to the users list
//        try {
//            // Decrypt password
//            String decryptedPassword = Security.decrypt(password, getServletContext());
//            users.add(new User(username, firstname, lastname, email, link, decryptedPassword, role));
//
//            // Insert the new user to the database
//            insertUser(username, firstname, lastname, email, link, decryptedPassword, role);
//
//            // Signup successful
//            PrintWriter out = response.getWriter();
//            out.println("<html><body>");
//            out.println("<h1>Signup Successful</h1>");
//            out.println("<p>Welcome, " + firstname + " " + lastname + "</p>");
//            out.println("</body></html>");
//        } catch (Exception e) {
//            // Signup failed
//            e.printStackTrace();
//            PrintWriter out = response.getWriter();
//            out.println("<html><body>");
//            out.println("<h1>Signup Failed</h1>");
//            out.println("<p>Failed to create a new user</p>");
//            out.println("</body></html>");
//        }
//    }
//
//    private void insertUser(String username, String firstname, String lastname, String email, String link, String password, String role) throws SQLException {
//        try (Connection con = DriverManager.getConnection(url, DBusername, DBpassword)) {
//            String sql = "INSERT INTO USER_INFO (USERNAME, FIRSTNAME, LASTNAME, EMAIL, LINK, PASSWORD, ROLE) VALUES (?, ?, ?, ?, ?, ?, ?)";
//            try (PreparedStatement ps = con.prepareStatement(sql)) {
//                ps.setString(1, username);
//                ps.setString(2, firstname);
//                ps.setString(3, lastname);
//                ps.setString(4, email);
//                ps.setString(5, link);
//                ps.setString(6, password);
//                ps.setString(7, role);
//                ps.executeUpdate();
//            }
//        }
//    }
//}
