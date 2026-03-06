package com.carbontracker.repository;

import com.carbontracker.config.HibernateUtil;
import com.carbontracker.entity.CarbonRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CarbonRepository {

    public void saveCarbonRecord(CarbonRecord record) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(record);

        tx.commit();
        session.close();
    }
}