package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS Users (id BIGINT AUTO_INCREMENT, name  VARCHAR(255),lastName  VARCHAR(255),age TINYINT UNSIGNED, PRIMARY KEY (id))";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();

        }
    }


    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            String sql = "DROP TABLE IF EXISTS users";
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("User с именем " + name + " добавлен в базу");
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }


    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> list = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "from User";
            session.createQuery(sql).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "delete from User ";
            session.createQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
