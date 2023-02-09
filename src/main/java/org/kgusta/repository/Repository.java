package org.kgusta.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kgusta.config.DBConfig;

import java.util.List;

public class Repository<T> {
    private Class<T> tClass;

    public Repository(Class<T> tClass) {
        this.tClass = tClass;
    }

    protected SessionFactory sessionFactory = DBConfig.getSessionFactory();

    public Repository() {

        sessionFactory = DBConfig.getSessionFactory();
    }

    public T findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        T entity = session.get(tClass, id);
        session.getTransaction().commit();
        return entity;
    }

    public void save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    public void update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    //create updateById method
    public void updateById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        T entity = session.get(tClass, id);
        session.update(entity);
        session.getTransaction().commit();
    }

    //create deleteById method
    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        T entity = session.get(tClass, id);
        session.remove(entity);
        session.getTransaction().commit();
    }

    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<T> entitiesList = session.createQuery("from " + tClass.getName()).list();
        session.getTransaction().commit();
        return entitiesList;
    }

}
