package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Agafon", "Agafonov", (byte) 22);
        userService.saveUser("Evlampiy", "Evlampiev", (byte) 22);
        userService.saveUser("Vavilen", "Tatarskiy", (byte) 22);

//        userService.removeUserById(1);

        userService.getAllUsers();

        userService.cleanUsersTable();

//        userService.dropUsersTable();
    }
}
