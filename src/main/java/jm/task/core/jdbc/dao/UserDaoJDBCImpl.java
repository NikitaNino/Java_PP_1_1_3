package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    // Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            String sql = "CREATE TABLE if not exists `name_db`.`user` (\n" +
                    "  `id` INT(60) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `lastName` VARCHAR(45) NULL,\n" +
                    "  `age` INT(60) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление таблицы User(ов)
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            String sql = "DROP TABLE if exists `user`";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Добавление User в таблицу
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            String sql = "INSERT INTO `name_db`.`user`\n" +
                    "(`name`,`lastName`,`age`)\n" +
                    "VALUES ('" +  name + "','" + lastName + "'," + age + ")";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление User из таблицы (по id)
    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            String sql = "DELETE FROM `name_db`.`user`\n" +
                    "WHERE id = " + id;
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение всех User(ов) из таблицы
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            String sql = "SELECT Name, lastName, age FROM `user`";
            ResultSet resultSet = connection.prepareStatement(sql).executeQuery(sql);
            while (resultSet.next()) {
                User one = new User(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getByte(3));
                list.add(one);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    // Очистка содержания таблицы
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            String sql = "DELETE FROM `name_db`.`user`";
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
