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
        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES(?, ?, ?)";

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
        String sql = "DELETE FROM USERS WHERE ID=?";

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
        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM USERS";
        List<User> userList = new ArrayList<>();

        try(Connection connection = getConnect();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Get all users - error");
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM USERS";
        try(Connection connection = getConnect();
            Statement statement = connection.createStatement()){
                statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Clean users - error");
            e.printStackTrace();
        }

    }
}
