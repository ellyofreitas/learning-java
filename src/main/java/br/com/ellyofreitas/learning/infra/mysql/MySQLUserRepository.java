package br.com.ellyofreitas.learning.infra.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.ellyofreitas.learning.domain.entities.User;
import br.com.ellyofreitas.learning.domain.repositories.UserRepository;
import br.com.ellyofreitas.learning.infra.errors.DatabaseException;

import java.util.ArrayList;
import java.util.List;

public class MySQLUserRepository implements UserRepository {

    private User toDomain(ResultSet rs) throws SQLException {
        return new User(rs.getString("id"), rs.getString("name"), rs.getString("email"));
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(this.toDomain(resultSet));
            }
        } catch (SQLException e) {
            throw new DatabaseException("An error occurred while executing the operation on the database", e);
        }
        return users;
    }

    public void save(User user) {
        final String query = """
                    insert into
                        users (id, name, email)
                    values
                        (?, ?, ?) on duplicate key
                    update
                        name = values(name),
                        email = values(email)
                """;
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("An error occurred while executing the operation on the database", e);
        }

    }
}
