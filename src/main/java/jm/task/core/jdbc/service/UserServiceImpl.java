package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoHibernateImpl method;

    public UserServiceImpl() {
        method = new UserDaoHibernateImpl();
    }

    @Override
    public void createUsersTable() {
        method.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        method.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        method.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        method.removeUserById(id);

    }

    @Override
    public List<User> getAllUsers() {

        return method.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        method.cleanUsersTable();
    }
}
