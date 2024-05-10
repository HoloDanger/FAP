package test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            String driver = getServletConfig().getInitParameter("jdbcDriver");
            Class.forName(driver);
            String url = getServletConfig().getInitParameter("jdbcURL");
            String DBusername = getServletConfig().getInitParameter("jdbcUsername");
            String DBpassword = getServletConfig().getInitParameter("jdbcPassword");

            // Instantiate UserDAO with database parameters
            userDAO = new UserDAO(url, DBusername, DBpassword);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("courses.jsp");
        dispatcher.forward(request, response);
    }
}
