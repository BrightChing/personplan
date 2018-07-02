package io.github.brightqin.personplan.dao;

import io.github.brightqin.personplan.entity.User;
import io.github.brightqin.personplan.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author brightqin
 * @date 2018/6/16
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public void saveUser(User user) {
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String userId) {
        try (Session session = HibernateUtils.getSession()) {
            return session.get(User.class, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUser(User user) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
