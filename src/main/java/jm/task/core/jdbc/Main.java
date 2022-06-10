package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        Util.getConnect();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Agafon", "Agafonov", (byte) 22);
        userService.saveUser("Evlampiy", "Evlampiev", (byte) 22);
        userService.saveUser("Vavilen", "Tatarskiy", (byte) 22);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
