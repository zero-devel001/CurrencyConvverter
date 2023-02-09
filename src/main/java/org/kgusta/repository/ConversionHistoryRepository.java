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
    public ConversionHistory findByUser(User user) {
        Session session  = sessionFactory.openSession();
        session.beginTransaction();
        ConversionHistory conversions =  session.createQuery("SELECT c FROM ConversionHistory c WHERE c.user = : user", ConversionHistory.class)
                .setParameter("user", user)
                .getSingleResult();
        session.getTransaction().commit();
        return conversions;
    }

}
