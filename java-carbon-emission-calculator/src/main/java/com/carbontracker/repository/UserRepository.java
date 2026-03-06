package com.carbontracker.repository;

import com.carbontracker.config.HibernateUtil;
import com.carbontracker.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepository {

    public void saveUser(User user) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(user);

        tx.commit();
        session.close();
    }

    public User getUserById(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        User user = session.get(User.class, id);

        session.close();

        return user;
    }
}