package test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Connection connection;

    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        this.connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public List<SQLUser> getAllUsers() throws SQLException {
        List<SQLUser> listUser = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String link = resultSet.getString("link");

                SQLUser user = new SQLUser(firstName, lastName, username, email, password, role, link);
                listUser.add(user);
            }
        }
        return listUser;
    }

    public void insertUser(SQLUser user) throws SQLException {
        String sql = "INSERT INTO users (firstName, lastName, username, email, password, role, link) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getRole());
            statement.setString(7, user.getLink());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Inserting user failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding course:" + e.getMessage());
            throw new SQLException("Error adding course", e);
        }
    }

    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No user found with the name: " + username + ", no rows affected.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error deleting user with name " + username + ": " + e.getMessage(), e);
        }
    }
}
