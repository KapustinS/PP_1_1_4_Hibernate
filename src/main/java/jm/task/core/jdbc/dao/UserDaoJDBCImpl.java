package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
       String sql = "CREATE TABLE IF NOT EXISTS `mydbtest`.`users` (" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT, " +
                "  `name` VARCHAR(45) NOT NULL, " +
                "  `lastname` VARCHAR(45) NOT NULL, " +
                "  `age` INT NOT NULL, " +
                "  PRIMARY KEY (`id`));";

        try(Connection connection = getConnect();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Create table \"users\" - error");
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users;";

        try(Connection connection = getConnect();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Create table \"users\" - error");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastname, age) VALUES(?, ?, ?)";

        try(Connection connection = getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Save user to SQL - error");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id=?";

        try(Connection connection = getConnect();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Remove user to SQL - error");
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String sql = "SELECT id, name, lastname, age FROM users";
        List<User> userList = new ArrayList<>();

        try(Connection connection = getConnect();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Get all users - error");
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try(Connection connection = getConnect();
            Statement statement = connection.createStatement()){
                statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Clean users - error");
            e.printStackTrace();
        }

    }
}
