package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

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

        UserDao userDao1 = new UserDaoHibernateImpl();
        userDao1.createUsersTable();
        userDao1.saveUser("Евгений", "Евгеневич", (byte) 33);
        userDao1.saveUser("Anton", "Antonovich", (byte) 33);
        userDao1.saveUser("Mihail", "Mihalovich", (byte) 33);
        userDao1.saveUser("Leonid", "Leonidovich", (byte) 33);
        userDao1.removeUserById(1);
        userDao1.getAllUsers().forEach(x-> System.out.println(x.toString()));
        userDao1.cleanUsersTable();
        userDao1.dropUsersTable();
//         реализуйте алгоритм здесь
    }
}
