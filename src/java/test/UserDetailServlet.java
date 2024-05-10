package test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class UserDetailServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            Class.forName(getServletContext().getInitParameter("dbDriver"));
        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL JDBC driver not found", e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        SQLUser user = getUserDetails(username);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/user_profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String link = request.getParameter("link");
        updateUserDetails(username, firstname, lastname, email, password, role, link);
        response.sendRedirect("instructor_myaccount.jsp");
    }

    private SQLUser getUserDetails(String username) {
        SQLUser user = null;
        ServletContext context = getServletContext();
        String url = context.getInitParameter("MySQLURL");
        String dbUser = context.getInitParameter("MySQLUsername");
        String dbPassword = context.getInitParameter("MySQLPassword");
        String query = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new SQLUser(rs.getString("username"), rs.getString("firstname"), rs.getString("lastname"),
                            rs.getString("email"), rs.getString("password"), rs.getString("role"), rs.getString("link"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private void updateUserDetails(String username, String firstname, String lastname, String email, String password, String role, String link) {
        ServletContext context = getServletContext();
        String url = context.getInitParameter("dbUrl");
        String dbUser = context.getInitParameter("dbUser");
        String dbPassword = context.getInitParameter("dbPassword");
        String query = "UPDATE users SET firstname = ?, lastname = ?, email = ?, password = ?, role = ?, link = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, firstname);
            pstmt.setString(2, lastname);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setString(5, role);
            pstmt.setString(6, link);
            pstmt.setString(7, username);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
