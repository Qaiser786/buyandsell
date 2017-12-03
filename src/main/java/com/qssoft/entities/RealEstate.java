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
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;

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

    @Column(name = "img1")
    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    @Column(name = "img2")
    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    @Column(name = "img3")
    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    @Column(name = "img4")
    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    @Column(name = "img5")
    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "propertyTypeId", insertable = false, updatable = false)
    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public RealEstate(Integer id, Integer dealTypeId, String title, String description, Integer ownerId, BigDecimal price,
                      String address, String nearbyLocations, String adminNote, Integer statusId, Float latitude,
                      Float longitude, String pictureCode) {
        this(id, dealTypeId, title, description, ownerId, price, address, nearbyLocations, adminNote, statusId, latitude,
                longitude, pictureCode, null, null);
    }

    public RealEstate(Integer id, Integer dealTypeId, String title, String description, Integer ownerId, BigDecimal price,
                      String address, String nearbyLocations, String adminNote, Integer statusId, Float latitude,
                      Float longitude, String pictureCode, String city, Integer propertyTypeId) {
        this(id, dealTypeId, title, description, ownerId, price, address, nearbyLocations, adminNote, statusId,
                latitude, longitude, pictureCode, city, propertyTypeId, null, null, null, null, null);
    }

    public RealEstate(Integer id, Integer dealTypeId, String title, String description, Integer ownerId, BigDecimal price,
                      String address, String nearbyLocations, String adminNote, Integer statusId, Float latitude, Float longitude,
                      String pictureCode, String city, Integer propertyTypeId, String img1, String img2, String img3,
                      String img4, String img5) {
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
        this.propertyTypeId = propertyTypeId;
        this.city = city;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.img5 = img5;
    }

    public RealEstate() {
    }
}
