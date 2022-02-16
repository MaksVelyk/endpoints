package com.cookwarestore.database.impl.hibernate;

import com.cookwarestore.database.ICookwareDAOI;
import com.cookwarestore.model.Cookware;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class CookwareDAOImpl implements ICookwareDAOI {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Cookware> getCookwares() {
        Session session = this.sessionFactory.openSession();
        Query<Cookware> query = session.createQuery("FROM com.cookwarestore.model.Cookware");
        List<Cookware> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Cookware> getCookwareById(int cookwareId) {
        Session session = this.sessionFactory.openSession();
        Query<Cookware> query = session.createQuery("FROM com.cookwarestore.model.Cookware WHERE id = :id");
        query.setParameter("id", cookwareId);
        try {
            Cookware cookware = query.getSingleResult();
            session.close();
            return Optional.of(cookware);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void updateCookware(Cookware cookware) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(cookware);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
