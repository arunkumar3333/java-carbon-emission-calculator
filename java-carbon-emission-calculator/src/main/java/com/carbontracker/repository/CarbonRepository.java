//package com.carbontracker.repository;
//
//import com.carbontracker.config.HibernateUtil;
//import com.carbontracker.entity.CarbonRecord;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//public class CarbonRepository {
//
//    public void saveCarbonRecord(CarbonRecord record) {
//
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//
//        session.save(record);
//
//        tx.commit();
//        session.close();
//    }
//    
//}
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

    // NEW METHOD: Category wise carbon emission
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
}