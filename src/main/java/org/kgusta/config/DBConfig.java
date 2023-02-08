package org.kgusta.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.kgusta.model.*;


public class DbConfig {
    private static SessionFactory sessionFactory;
    static  {
        init();
    }
    public static void init() {

        Configuration configuration = new Configuration()
                .addPackage("org.kgusta.model")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Currency.class)
                .addAnnotatedClass(Conversion.class)
                .addAnnotatedClass(ConversionHistory.class)
                .addAnnotatedClass(ExchangeRate.class);
        sessionFactory = configuration.buildSessionFactory();


    }
    //getter method for sessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    //method Session creation
    public static void fillDB() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user1 =  User.builder()
                .username("user1")
                .password("password1")
                .build();
        User user2 =  User.builder()
                .username("user2")
                .password("password2")
                .build();
        User user3 =  User.builder()
                .username("user3")
                .password("password3")
                .build();
        session.persist(user1);
        session.persist(user2);
        session.persist(user3);
        session.getTransaction().commit();
    }

}
