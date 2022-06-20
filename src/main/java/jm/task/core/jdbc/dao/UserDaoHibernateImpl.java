package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `users` (" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT, " +
                "  `name` VARCHAR(45) NOT NULL, " +
                "  `lastname` VARCHAR(45) NOT NULL, " +
                "  `age` INT NOT NULL, " +
                "  PRIMARY KEY (`id`));";
        Transaction transaction = null;

        try (Session session = getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(sql, User.class).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users;";
        Transaction transaction = null;

        try (Session session = getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(sql, User.class).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;

        try (Session session = getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        try (Session session = getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery("SELECT i FROM User i", User.class).getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;

        try (Session session = getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createMutationQuery("delete from User").executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
