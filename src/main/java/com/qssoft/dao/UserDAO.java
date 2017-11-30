package com.qssoft.dao;

import com.qssoft.entities.User;
import com.qssoft.hibernate.SessionFactoryHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDAO
{
    public User getUserByLogin(final String login)
    {
        Session session = SessionFactoryHelper.getSession();

        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteriaQuery.from(User.class);

        criteriaQuery.select(userRoot);

        criteriaQuery.where(criteriaBuilder.equal(userRoot.get("login"), login));

        criteriaQuery.from(User.class);

        User user = session.createQuery(criteriaQuery).uniqueResult();

        transaction.commit();

        session.close();

        return user;
    }

    public User getUserById(final int id)
    {
        Session session = SessionFactoryHelper.getSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        return user;
    }

    public void createUser(final User user)
    {
        Session session = SessionFactoryHelper.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }


    /*
    public UserDTO getUserByLogin(final String login)
    {
        Session session = SessionFactoryHelper.getSession();
        
        UserDTO user = null;
        try {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<UserDTO> criteriaQuery = criteriaBuilder.createQuery(UserDTO.class);

            Root<UserDTO> userRoot = criteriaQuery.from(UserDTO.class);

            criteriaQuery.select(userRoot);

            criteriaQuery.where(criteriaBuilder.equal(userRoot.get("login"), login));

            criteriaQuery.from(UserDTO.class);

            user = session.createQuery(criteriaQuery).uniqueResult();

            transaction.commit();
            
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return user;
    }
    */

//    public UserDTO getUserByLogin(final String login)
//    {
//        UserDTO user = null;
//        Session session = SessionFactoryHelper.getSession();
//        try {
//            Transaction trns = session.beginTransaction();
//            String queryString = "from UserDTO where id = :id";
//            Query query = session.createQuery(queryString);
//            query.("id", userid);
//            user = (UserDTO) query.uniqueResult();
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        } finally {
//            session.flush();
//            session.close();
//        }
//        return user;
//    }
}
