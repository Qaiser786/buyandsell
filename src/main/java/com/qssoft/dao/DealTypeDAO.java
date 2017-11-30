package com.qssoft.dao;

import com.qssoft.entities.DealType;
import com.qssoft.hibernate.SessionFactoryHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DealTypeDAO
{
    public DealType getDealTypeById(final int id)
    {
        Session session = SessionFactoryHelper.getSession();
        Transaction t = session.beginTransaction();
        DealType result = session.get(DealType.class, id);
        t.commit();
        return result;
    }

    public List<DealType> getAllDealTypes() {
        Session session = SessionFactoryHelper.getSession();
        List<DealType> result = null;
        try {
            session.beginTransaction();
            result = session.createQuery("from DealType").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.getTransaction().commit();
                session.close();
            }
        }
        return result;
    }
}
