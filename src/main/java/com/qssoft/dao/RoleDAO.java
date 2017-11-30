package com.qssoft.dao;

import com.qssoft.entities.Role;
import com.qssoft.hibernate.SessionFactoryHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Repository
@Transactional
public class RoleDAO
{
    public Role getRoleByRoleName(final String roleName) {
        Session session = SessionFactoryHelper.getSession();

        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);

        Root<Role> role = criteriaQuery.from(Role.class);

        criteriaQuery.select(role);

        criteriaQuery.where(criteriaBuilder.equal(role.get("name"), roleName));

        criteriaQuery.from(Role.class);

        Role result = session.createQuery(criteriaQuery).uniqueResult();

        transaction.commit();

        return result;
    }

    public Role getRoleByRoleId(final Integer roleId) {
        Session session = SessionFactoryHelper.getSession();

        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);

        Root<Role> role = criteriaQuery.from(Role.class);

        criteriaQuery.where(criteriaBuilder.equal(role.get("id"), roleId));

        Role result = session.createQuery(criteriaQuery).uniqueResult();

        session.getTransaction().commit();

        return result;
    }

}
