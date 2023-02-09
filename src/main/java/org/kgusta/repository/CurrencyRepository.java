package org.kgusta.repository;

import org.hibernate.Session;
import org.kgusta.model.Currency;

public class CurrencyRepository extends Repository<Currency>{
    public CurrencyRepository() {
        super(Currency.class);
    }

    public Currency findByCode(String usd) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Currency currency = session.createQuery("select c from Currency c where c.code = :code", Currency.class)
                .setParameter("code", usd)
                .getSingleResult();
        session.getTransaction().commit();
        return currency;
    }
}
