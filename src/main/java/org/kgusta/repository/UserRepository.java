package org.kgusta.repository;

import org.hibernate.Session;
import org.kgusta.model.User;

public class UserRepository extends Repository<User>{
    public UserRepository() {
        super(User.class);
    }


    public User findByUsername(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.createQuery("from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }
}
