package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Максим", "Максович", (byte) 23);
        userDaoJDBC.saveUser("Антон", "Внтонович", (byte) 32);
        userDaoJDBC.saveUser("Лев", "Левович", (byte) 25);
        userDaoJDBC.saveUser("Юрий", "Юрьевич", (byte) 63);
        userDaoJDBC.removeUserById(2);
        userDaoJDBC.getAllUsers().forEach(x-> System.out.println(x.toString()));
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
