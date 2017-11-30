package com.qssoft.dao;

import com.qssoft.entities.Status;
import com.qssoft.hibernate.SessionFactoryHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

@Service
public class PropertyStatusDAO
{
    public Integer getStatusIdByString(final String statusName)
    {
        Integer result = null;

        Session session = SessionFactoryHelper.getSession();
        try {
            Transaction trns = session.beginTransaction();

            Query query = session.createQuery("select id from Status where name = :name");

            query.setString("name", statusName);

            result = (Integer) query.uniqueResult();

            trns.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return result;
    }

    public Status getStatusById(final int id) {
        Session session = SessionFactoryHelper.getSession();
        session.beginTransaction();
        Status status = session.get(Status.class, id);
        session.getTransaction().commit();
        return status;
    }
}
