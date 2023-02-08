package org.kgusta.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kgusta.model.User;

import java.util.List;

public class Repository<T> {
    private Class<T> tClass;

    public Repository(Class<T> tClass) {
        this.tClass = tClass;
    }

    protected SessionFactory sessionFactory = DbConfig.getSessionFactory();

    public Repository() {
        sessionFactory = DbConfig.getSessionFactory();
    }
    public T finUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        T entity = session.get(tClass, id);
        session.getTransaction().commit();
        return entity;
    }

    public void saveUser(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    public void updateUser(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }
    public void deleteUser(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<T> entitiesList =  session.createQuery("from " + tClass.getName()).list();
        session.getTransaction().commit();
        return entitiesList;
    }
}
