package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Максим", "Максович", (byte) 23);
        userDao.saveUser("Антон", "Викторович", (byte) 32);
        userDao.saveUser("Лев", "Львович", (byte) 25);
        userDao.saveUser("Юрий", "Юрьевич", (byte) 63);
        userDao.removeUserById(2);
        userDao.getAllUsers().forEach(x-> System.out.println(x.toString()));
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
