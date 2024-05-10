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
        try (Connection connection = getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String courseName = resultSet.getString("courseName");
                String instructor = resultSet.getString("instructor");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                String courseTime = resultSet.getString("courseTime");
                String banner = resultSet.getString("banner");

                Course course = new Course(courseName, instructor, startDate, endDate, courseTime, banner);
                listCourse.add(course);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching courses:" + e.getMessage());
            throw new SQLException("Error fetching courses", e);
        }
        return listCourse;
    }

    public void addCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses (courseName, instructor, startDate, endDate, courseTime, banner) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setString(2, course.getInstructor());
            preparedStatement.setDate(3, new java.sql.Date(course.getStartDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(course.getEndDate().getTime()));
            preparedStatement.setString(5, course.getCourseTime());
            preparedStatement.setString(6, course.getBanner());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding course:" + e.getMessage());
            throw new SQLException("Error adding course", e);
        }
    }

    public void deleteCourse(String courseName) throws SQLException {
        String sql = "DELETE FROM courses WHERE courseName = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, courseName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No course found with the name: " + courseName + ", no rows affected.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error deleting course with name " + courseName + ": " + e.getMessage(), e);
        }
    }
}
