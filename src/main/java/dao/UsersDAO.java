package dao;

import accounts.UserHuuzer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class UsersDAO {

    SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public UserHuuzer findById(int id) {
        return sessionFactory.openSession().get(UserHuuzer.class, id);
    }

    public void save(UserHuuzer user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
        }
    }

    public void update(UserHuuzer user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(user);
            tx1.commit();
        }
    }

    public void delete(UserHuuzer user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(user);
            tx1.commit();
        }
    }

    public UserHuuzer findByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(UserHuuzer.class, login);
        }
    }
}

