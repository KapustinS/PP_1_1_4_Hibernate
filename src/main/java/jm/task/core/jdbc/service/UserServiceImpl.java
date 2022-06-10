package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.Collection;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDaoJDBCImpl method = new UserDaoJDBCImpl();

    public void createUsersTable() {
        method.createUsersTable();
    }

    public void dropUsersTable() {
        method.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        method.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        method.removeUserById(id);

    }

    public List<User> getAllUsers() {

        return method.getAllUsers();
    }

    public void cleanUsersTable() {
        method.cleanUsersTable();
    }
}
