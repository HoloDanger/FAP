package test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;

    public CourseDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> listCourse = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String courseName = resultSet.getString("courseName");
                String instructor = resultSet.getString("instructor");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                String courseTime = resultSet.getString("courseTime");

                Course course = new Course(id, courseName, instructor, startDate, endDate, courseTime);
                listCourse.add(course);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users:" + e.getMessage());
            throw new SQLException("Error fetching users", e);
        }
        return listCourse;
    }
}
