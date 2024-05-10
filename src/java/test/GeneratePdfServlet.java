package test;

import javax.servlet.http.HttpSession;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/generatepdf")
public class GeneratePdfServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");

        try {
            HttpSession session = request.getSession(false); // Ensure existing session
            if (session != null) {
                String role = (String) session.getAttribute("role");
                if (role != null) {
                    Document document = new Document();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                    String timestamp = dateFormat.format(new Date());
                    String filename = "C:\\Users\\Darwin\\Desktop\\COURSELIST_" + timestamp + ".pdf";
                    PdfWriter.getInstance(document, new FileOutputStream(filename));

                    // Retrieve course data from the database
                    List<Course> courses = retrieveCoursesFromDatabase(); // Implement this method

                    if (courses.isEmpty()) {
                        response.sendRedirect("instructor_managecourses.jsp"); // Redirect if no courses found
                        return;
                    }

                    for (Course course : courses) {
                        addCourseDetailsToPDF(document, course);
                    }

                    document.close();
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User role not found in session");
                }
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session not found");
            }
        } catch (DocumentException e) {
            throw new IOException(e.getMessage());
        }
    }

    private List<Course> retrieveCoursesFromDatabase() {
        // Retrieve course data from the database
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";

        String url = getServletContext().getInitParameter("jdbcURL");
        String username = getServletContext().getInitParameter("jdbcUsername");
        String password = getServletContext().getInitParameter("jdbcPassword");
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String courseName = rs.getString("COURSENAME");
                String instructor = rs.getString("INSTRUCTOR");
                Date startDate = rs.getDate("STARTDATE");
                Date endDate = rs.getDate("ENDDATE");
                String courseTime = rs.getString("COURSETIME");
                String banner = rs.getString("BANNER");
                courses.add(new Course(courseName, instructor, startDate, endDate, courseTime, banner));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print the retrieved courses
        for (Course course : courses) {
            System.out.println(course);
        }

        return courses;
    }

    private Course getSelectedCourse(HttpServletRequest request, List<Course> courses) {
        String courseNameParam = request.getParameter("courseName");

        if (courseNameParam != null && !courseNameParam.isEmpty()) {
            for (Course course : courses) {
                if (course.getCourseName().equals(courseNameParam)) {
                    return course;
                }
            }
        }
        return null;
    }

    private void addCourseDetailsToPDF(Document document, Course course) throws DocumentException {
        String courseName = course.getCourseName();
        String instructor = course.getInstructor();
        Date startDate = course.getStartDate();
        Date endDate = course.getEndDate();
        String courseTime = course.getCourseTime();
        String banner = course.getBanner();

        document.add(new Paragraph("Course Name: " + courseName));
        document.add(new Paragraph("Instructor: " + instructor));
        document.add(new Paragraph("Start Date: " + startDate));
        document.add(new Paragraph("End Date: " + endDate));
        document.add(new Paragraph("Time: " + courseTime));
        document.add(new Paragraph("Banner: " + banner));
        document.add(new Paragraph("------------------------------------"));
    }
}
