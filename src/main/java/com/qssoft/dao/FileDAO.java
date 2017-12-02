package com.qssoft.dao;

import com.qssoft.entities.File;
import com.qssoft.hibernate.SessionFactoryHelper;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class FileDAO {

    public File findById(Long id) {
        Session session = SessionFactoryHelper.getSession();
        session.beginTransaction();
        File result = session.get(File.class, id);
        session.getTransaction().commit();
        return result;
    }

    public File create(File file) {
        Session session = SessionFactoryHelper.getSession();

        try {
            session.beginTransaction();
            session.save(file);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if ( session != null && session.isOpen() ) {
                session.close();
            }
        }

        return file;
    }

    public Boolean delete(File file) {
        Session session = SessionFactoryHelper.getSession();

        try {
            session.beginTransaction();
            session.delete(file);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        } finally {
            if ( session != null && session.isOpen() ) {
                session.close();
            }
        }

        return Boolean.TRUE;
    }

    public Boolean delete(Long id) {
        return this.delete(this.findById(id));
    }

}
