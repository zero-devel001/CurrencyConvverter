package org.kgusta.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.kgusta.model.*;


public class DBConfig {
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

}
