package test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        this.connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public List<SQLUser> getAllUsers() throws SQLException {
        List<SQLUser> listUser = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("userid");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                SQLUser user = new SQLUser(id, email, password, firstName, lastName);
                listUser.add(user);
            }
        }
        return listUser;
    }

    public boolean insertUser(SQLUser user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, first_name, last_name) VALUES (?, ?, ?, ?, ?)";
        boolean rowInserted;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            rowInserted = statement.executeUpdate() > 0;
        }
        return rowInserted;
    }
}
