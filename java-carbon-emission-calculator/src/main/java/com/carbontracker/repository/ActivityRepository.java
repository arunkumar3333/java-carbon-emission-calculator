package com.carbontracker.repository;

import com.carbontracker.config.HibernateUtil;
import com.carbontracker.entity.Activity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ActivityRepository {

    public Long saveActivity(Activity activity) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Long id = (Long) session.save(activity);

        tx.commit();
        session.close();

        return id;
    }
}