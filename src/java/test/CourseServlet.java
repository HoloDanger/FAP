package test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseServlet extends HttpServlet {

    private CourseDAO courseDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            String driver = getServletConfig().getInitParameter("jdbcDriver");
            Class.forName(driver);
            String jdbcURL = getServletConfig().getInitParameter("jdbcURL");
            String jdbcUsername = getServletConfig().getInitParameter("jdbcUsername");
            String jdbcPassword = getServletConfig().getInitParameter("jdbcPassword");

            courseDAO = new CourseDAO(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new ServletException("Initialization failed due to database issues", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get all courses from the database
            request.setAttribute("courses", courseDAO.getAllCourses());
        } catch (SQLException e) {
            throw new ServletException("Database error while retrieving courses", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("courses.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if ("delete".equals(action)) {
                // Delete course
                String courseName = request.getParameter("courseName");
                courseDAO.deleteCourse(courseName);
                response.sendRedirect(request.getContextPath() + "/instructor_managecourses.jsp");
            } else {
                // Add course
                String courseName = request.getParameter("coursename");
                String instructor = request.getParameter("courseinstructor");
                String startDateStr = request.getParameter("coursestartdate");
                String endDateStr = request.getParameter("courseenddate");
                String courseTime = request.getParameter("coursetime");
                String banner = request.getParameter("coursebanner");

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = format.parse(startDateStr);
                Date endDate = format.parse(endDateStr);

                Course course = new Course(courseName, instructor, startDate, endDate, courseTime, banner);
                courseDAO.addCourse(course);

                response.sendRedirect(request.getContextPath() + "/instructor_managecourses.jsp");
            }
        } catch (SQLException | ParseException e) {
            throw new ServletException("Error processing course", e);
        }
    }
}
