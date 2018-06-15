package io.github.brightqin.personUser.dao;


import io.github.brightqin.personplan.entity.User;
import io.github.brightqin.personplan.util.HibernateUtils;
import org.hibernate.Session;


/**
 * @author brightqin
 * @date 2018/6/13
 */
public class UserDAO {
    public void saveUser(User user) {
        Session session = HibernateUtils.getSession();
        session.save(user);
    }

    public User getUser(String userId) {
        Session session = HibernateUtils.getSession();
        return session.get(User.class, userId);
    }

    public void deleteUser(User user) {
        Session session = HibernateUtils.getSession();
        session.delete(user);
    }

    public void updateUser(User user) {
        Session session = HibernateUtils.getSession();
        session.update(user);
    }
}
