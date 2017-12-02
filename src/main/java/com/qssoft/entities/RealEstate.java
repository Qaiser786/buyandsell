package com.qssoft.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Realestates")
public class RealEstate implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer dealTypeId;
    private String title;
    private String description;
    private Integer ownerId;
    private BigDecimal price;
    private String address;
    private String nearbyLocations;
    private String adminNote;
    private Integer statusId;
    private Float latitude;
    private Float longitude;
    private String pictureCode;
    private Integer propertyTypeId;
    private String city;

    private PropertyType propertyType;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "ownerId")
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Column(name = "dealTypeId")
    public Integer getDealTypeId() {
        return dealTypeId;
    }

    public void setDealTypeId(Integer dealTypeId) {
        this.dealTypeId = dealTypeId;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "nearbyLocations")
    public String getNearbyLocations() {
        return nearbyLocations;
    }

    public void setNearbyLocations(String nearbyLocations) {
        this.nearbyLocations = nearbyLocations;
    }

    @Column(name = "adminNote")
    public String getAdminNote() {
        return adminNote;
    }

    public void setAdminNote(String adminNote) {
        this.adminNote = adminNote;
    }

    @Column(name = "statusId")
    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Column(name = "lat")
    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    @Column(name = "lng")
    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Column(name = "pictureCode")
    public String getPictureCode() {
        return pictureCode;
    }

    public void setPictureCode(String pictureCode) {
        this.pictureCode = pictureCode;
    }

    @Column(name = "propertyTypeId")
    public Integer getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(Integer propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "propertyTypeId", insertable = false, updatable = false)
    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public RealEstate(Integer id, Integer dealTypeId, String title, String description, Integer ownerId, BigDecimal price, String
            address, String nearbyLocations, String adminNote, Integer statusId, Float latitude, Float longitude, String pictureCode) {
        this(id, dealTypeId, title, description, ownerId, price, address, nearbyLocations, adminNote, statusId, latitude, longitude, pictureCode, null, null);
    }

    public RealEstate(Integer id, Integer dealTypeId, String title, String description, Integer ownerId, BigDecimal price, String
              address, String nearbyLocations, String adminNote, Integer statusId, Float latitude, Float longitude, String pictureCode,
              String city, Integer propertyTypeId) {
        this.id = id;
        this.dealTypeId = dealTypeId;
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
        this.price = price;
        this.address = address;
        this.nearbyLocations = nearbyLocations;
        this.adminNote = adminNote;
        this.statusId = statusId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pictureCode = pictureCode;
        this.city = city;
        this.propertyTypeId = propertyTypeId;
    }

    public RealEstate() {
    }
}
