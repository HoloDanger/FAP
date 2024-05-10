package test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;

    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public List<SQLUser> getAllUsers() throws SQLException {
        List<SQLUser> listUser = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");

                SQLUser user = new SQLUser(id, email, password, firstName, lastName);
                listUser.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users:" + e.getMessage());
            throw new SQLException("Error fetching users", e);
        }
        return listUser;
    }

    public boolean insertUser(SQLUser user) throws SQLException {
        String sql = "INSERT INTO users (email, password, firstName, lastName) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            return statement.executeUpdate() > 0;
        }
    }
}
