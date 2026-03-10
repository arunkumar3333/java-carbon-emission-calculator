package com.carbontracker.repository;

import com.carbontracker.config.HibernateUtil;
import com.carbontracker.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserRepository {

    public boolean saveUser(User user) {

        Session session = null;
        Transaction tx = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            session.save(user);

            tx.commit();
            return true;

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            e.printStackTrace();
            return false;

        } finally {

            if (session != null) {
                session.close();
            }

        }
    }

    public User getUserById(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();

        return user;
    }

    public User loginUser(String email, String password) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM User WHERE email = :email AND password = :password";

        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        User user = query.uniqueResult();

        session.close();

        return user;
    }
}