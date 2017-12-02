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
import org.hibernate.query.NativeQuery;
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
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Transactional
public class RealEstateDAO {

    public static class CityCount {
        private String city;
        private Long count;
        CityCount(String city, Long count) { this.city = city; this.count = count; }
        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }
    }

    @Deprecated
    public static class AddressCount {
        private String city;
        private String address;
        private String type;
        private Long count;
        AddressCount(String city, String address, String type, Long count) {
            this.address = address;
            this.type = type;
            this.city = city;
            this.count = count;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }
    }

    public void createUpdateProperty(Property realEstateDTO) {
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

    public List<RealEstate> searchByFilters(String city, /* city parameter is required */
                                            String address,
                                            Double minPrice,
                                            Double maxPrice,
                                            Integer propertyTypeId) {
        if ( city == null || city.isEmpty() ) {
            throw new IllegalArgumentException("city parameter is required");
        }

        List<RealEstate> result = new LinkedList<>();

        Session session = SessionFactoryHelper.getSession();
        try {
            session.beginTransaction();

            StringBuilder sql = new StringBuilder("select r from RealEstate r where ( upper(r.city) LIKE :city or upper(r.address) LIKE :city )");

            if ( address != null && !address.isEmpty() ) { sql.append(" and upper(r.address) LIKE :address"); }
            if ( minPrice != null ) { sql.append(" and r.price >= :minPrice"); }
            if ( maxPrice != null ) { sql.append(" and r.price <= :maxPrice"); }
            if ( propertyTypeId != null ) { sql.append(" and r.propertyTypeId = :propertyTypeId"); }

            Query query = session.createQuery(sql.toString());

            query.setParameter("city", "%" + city.toUpperCase() + "%");

            if ( address != null && !address.isEmpty() ) { query.setParameter("address", "%" + address.toUpperCase() + "%"); }
            if ( minPrice != null ) { query.setParameter("minPrice", new BigDecimal(minPrice)); }
            if ( maxPrice != null ) { query.setParameter("maxPrice", new BigDecimal(maxPrice)); }
            if ( propertyTypeId != null ) { query.setParameter("propertyTypeId", propertyTypeId); }

            result = query.getResultList();

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

    @SuppressWarnings("unchecked")
    public List<CityCount> getPopularCities() {
        List<CityCount> result = new LinkedList<>();
        Session session = SessionFactoryHelper.getSession();
        try {
            session.beginTransaction();

            NativeQuery query = session.createNativeQuery("SELECT count(id) AS count , city FROM realestates GROUP BY city ORDER BY count DESC");
            List<Object[]> resultSet = query.getResultList();
            for (Object[] row : resultSet) {
                result.add(new CityCount(String.valueOf(row[1]), new Long(String.valueOf(row[0]))));
            }
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
                property.getPictureCode(),
                property.getCity(),
                property.getPropertyTypeId()
        );

        return realEstate;
    }

}
