package com.qssoft.dao;


import com.qssoft.dto.Property;
import com.qssoft.entities.DealType;
import com.qssoft.entities.PropertyPicture;
import com.qssoft.entities.RealEstate;
import com.qssoft.entities.Status;
import com.qssoft.entities.User;
import com.qssoft.hibernate.SessionFactoryHelper;
import com.qssoft.security.UserAccessHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Transactional
public class RealEstateDAO
{
    public void createUpdateProperty(Property realEstateDTO)
    {
        Session session = SessionFactoryHelper.getSession();

        RealEstate entity = createEntity(realEstateDTO);

        try {
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(entity);

            transaction.commit();

        } catch (RuntimeException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

    }

    public List<RealEstate> getPropertiesListForAdmin() {
        Session session = SessionFactoryHelper.getSession();
        List<RealEstate> result = null;
        try {
            session.beginTransaction();
            result = session.createQuery( "from RealEstate" ).list();
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

    public List<RealEstate> getNonDelitedPropertiesListForAdmin() {
        Session session = SessionFactoryHelper.getSession();
        List<RealEstate> result = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery( "from RealEstate where statusId <> :statusId" );
            query.setParameter("statusId", 3);
            result= query.list();
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

    public List<RealEstate> getApprovedProperties() {
        Session session = SessionFactoryHelper.getSession();
        List<RealEstate> result = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from RealEstate where statusId = :statusId");
            query.setParameter("statusId", 2);
            result = query.list();
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

    public List<RealEstate> searchPropertiesByPrice(BigDecimal price) {
        Session session = SessionFactoryHelper.getSession();
        List<RealEstate> result = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from RealEstate where statusId = :statusId and price BETWEEN 0 AND :price");
            query.setParameter("statusId", 2);
            query.setParameter("price", price);
            result = query.list();
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

    public List<RealEstate> searchPropertiesByCity(String city) {
        Session session = SessionFactoryHelper.getSession();
        List<RealEstate> result = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from RealEstate where statusId = :statusId and address LIKE :city");
            query.setParameter("statusId", 2);
            query.setParameter("city", "%" +  city + "%");
            result = query.list();
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
    public List<RealEstate> searchPropertiesByCityName(String RWP) {
        Session session = SessionFactoryHelper.getSession();
        List<RealEstate> result = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from RealEstate where statusId = :statusId and address LIKE :Rawalpindi");
            query.setParameter("statusId", 2);
            query.setParameter("Rawalpindi", "%" +  RWP + "%");
            result = query.list();
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
    public void changePropertyStatus(Integer id, Integer statusId) {
        Session session = SessionFactoryHelper.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("update RealEstate rs set rs.statusId = :statusId where rs.id = :id");
            query.setParameter("statusId", statusId);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.getTransaction().commit();
                session.close();
            }
        }
    }

    public List<RealEstate> getPropertiesListByOwner(Integer ownerId) {
        Session session = SessionFactoryHelper.getSession();
        List<RealEstate> result = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from RealEstate where ownerId = :ownerId AND statusId <> :statusId");
            query.setParameter("ownerId", ownerId);
            query.setParameter("statusId", 3);
            result = query.list();
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

    public RealEstate getPropertyById(Integer id) {
        Session session = SessionFactoryHelper.getSession();
        session.beginTransaction();
        RealEstate result = session.get(RealEstate.class, id);
        session.getTransaction().commit();
        return result;
    }

    private RealEstate createEntity(Property property) {
        PropertyPicture pp = new PropertyPicture(property.getId(), property.getPictureCode());
        RealEstate realEstate = new RealEstate(
                property.getId(),
                property.getDealTypeId(),
                property.getTitle(),
                property.getDescription(),
                property.getOwnerId(),
                property.getPrice(),
                property.getAddress(),
                property.getNearbyLocations(),
                property.getAdminNote(),
                property.getStatusId(),
                property.getLatitude(),
                property.getLongitude(),
                property.getPictureCode()
        );

        return realEstate;
    }

}
