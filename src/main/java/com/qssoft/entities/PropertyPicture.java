package com.qssoft.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Pictures")
public class PropertyPicture implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer propertyId;
    private String pictureCode;

    @ManyToOne
    @JoinColumn(name="propertyId", nullable=false)
    private RealEstate realEstate;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "propertyId")
    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    @Column(name = "pictureCode")
    public String getPictureCode() {
        return pictureCode;
    }

    public void setPictureCode(String pictureCode) {
        this.pictureCode = pictureCode;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
    }

    public PropertyPicture(Integer propertyId, String pictureCode) {
        this.propertyId = propertyId;
        this.pictureCode = pictureCode;
    }

    @Override
    public String toString() {
        return "PropertyPicture{" +
                "id=" + id +
                ", propertyId=" + propertyId +
                ", pictureCode='" + pictureCode + '\'' +
                '}';
    }
}
