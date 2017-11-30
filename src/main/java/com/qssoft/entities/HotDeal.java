package com.qssoft.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "HotDeals" )
public class HotDeal implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int id;
    private int propertyId;
    private int hits;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "propertyId")
    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    @Column(name = "hits")
    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public HotDeal(int propertyId, int hits) {
        this.propertyId = propertyId;
        this.hits = hits;
    }

    public HotDeal() {
    }
}
