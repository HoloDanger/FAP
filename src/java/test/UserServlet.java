package test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            String driver = getServletConfig().getInitParameter("jdbcDriver");
            Class.forName(driver);
            String jdbcURL = getServletConfig().getInitParameter("jdbcURL");
            String jdbcUsername = getServletConfig().getInitParameter("jdbcUsername");
            String jdbcPassword = getServletConfig().getInitParameter("jdbcPassword");

            // Instantiate UserDAO with database parameters
            userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Initialization failed due to database issues", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<SQLUser> users = userDAO.getAllUsers();
            request.setAttribute("users", users);
        } catch (SQLException e) {
            throw new ServletException("Database error while retrieving users", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("instructor_manageaccounts.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if ("delete".equals(action)) {
                // Delete course
                String username = request.getParameter("username");
                System.out.println("Attempting to delete user with username: " + username);
                userDAO.deleteUser(username);
                response.sendRedirect(request.getContextPath() + "/instructor_manageaccounts.jsp");
            } else {
                // Add course
                String firstName = request.getParameter("firstname");
                String lastName = request.getParameter("lastname");
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String role = request.getParameter("role");
                String link = request.getParameter("link");

                SQLUser user = new SQLUser(firstName, lastName, username, email, password, role, link);
                userDAO.insertUser(user);

                response.sendRedirect(request.getContextPath() + "/instructor_manageaccounts.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing user", e);
        }
    }
}
