package org.kgusta.repository;

import org.hibernate.Session;
import org.kgusta.model.Conversion;
import org.kgusta.model.ConversionHistory;
import org.kgusta.model.User;

import java.util.List;

public class ConversionHistoryRepository extends Repository<ConversionHistory>{
    public ConversionHistoryRepository() {
        super(ConversionHistory.class);
    }
    public List<Conversion> findByUser(User user) {
        Session session  = sessionFactory.openSession();
        session.beginTransaction();
        List<Conversion>conversions =  session.createQuery("SELECT c FROM Conversion c WHERE c.user = :user", Conversion.class)
                .setParameter("user", user)
                .getResultList();
        session.getTransaction().commit();
        return conversions;
    }

}
