package com.qssoft.dao;

import com.qssoft.entities.PropertyType;
import com.qssoft.hibernate.SessionFactoryHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Repository
@Transactional
public class PropertyTypeDAO {

    public PropertyType getById(Integer id) {
        Session session = SessionFactoryHelper.getSession();
        Transaction t = session.beginTransaction();
        PropertyType result = session.get(PropertyType.class, id);
        t.commit();
        return result;
    }

    public List<PropertyType> getAll() {
        Session session = SessionFactoryHelper.getSession();
        List<PropertyType> result = new LinkedList<>();
        try {
            session.beginTransaction();
            result = session.createQuery("from PropertyType").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( session != null && session.isOpen() ) {
                session.getTransaction().commit();
                session.close();
            }
        }

        return result;
    }

}
