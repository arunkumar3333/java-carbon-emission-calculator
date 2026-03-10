package com.carbontracker.repository;

import com.carbontracker.config.HibernateUtil;
import com.carbontracker.entity.CarbonRecord;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CarbonRepository {

    // Save carbon emission record
    public void saveCarbonRecord(CarbonRecord record) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(record);

        tx.commit();
        session.close();
    }


    // Category wise carbon emission
    public List<Object[]> getCategoryWiseEmission() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql =
                "SELECT a.vehicleType, SUM(c.co2Emission) " +
                "FROM CarbonRecord c, Activity a " +
                "WHERE c.activityId = a.id " +
                "GROUP BY a.vehicleType";

        Query<Object[]> query = session.createQuery(hql, Object[].class);

        List<Object[]> result = query.list();

        session.close();

        return result;
    }


    // Combined emission of ALL CSV uploads
    public List<Object[]> getCombinedEmission(Long userId) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();

            List<Object[]> result = session.createQuery(
                    "select a.vehicleType, sum(c.co2Emission) " +
                    "from CarbonRecord c join Activity a on c.activityId = a.id " +
                    "where c.userId = :userId " +
                    "group by a.vehicleType",
                    Object[].class
            )
            .setParameter("userId", userId)
            .list();

            return result;

        } finally {

            if (session != null) {
                session.close();
            }

        }
    }
    // 🔹 Latest uploaded CSV emission (IMPORTANT)
    public List<Object[]> getLatestBatchEmission(Long userId){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Long latestBatch = (Long) session.createQuery(
                "select max(batchId) from CarbonRecord where userId = :userId"
        )
        .setParameter("userId", userId)
        .uniqueResult();

        List<Object[]> result = session.createQuery(
                "select a.vehicleType, sum(c.co2Emission) " +
                "from CarbonRecord c join Activity a on c.activityId = a.id " +
                "where c.batchId = :batchId and c.userId = :userId " +
                "group by a.vehicleType"
        )
        .setParameter("batchId", latestBatch)
        .setParameter("userId", userId)
        .list();

        session.close();

        return result;
    }


//Total Carbon Emission (User Analytics)
public Double getTotalEmission(Long userId){

    Session session = HibernateUtil.getSessionFactory().openSession();

    Double total = (Double) session.createQuery(
        "select sum(c.co2Emission) from CarbonRecord c where c.userId = :userId"
    )
    .setParameter("userId", userId)
    .uniqueResult();

    session.close();

    return total;
}

}