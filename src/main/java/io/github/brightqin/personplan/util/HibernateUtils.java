package io.github.brightqin.personplan.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



/**
 * @author brightqin
 */
public class HibernateUtils {
      
    private static final SessionFactory sessionFactory;
    static {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}  