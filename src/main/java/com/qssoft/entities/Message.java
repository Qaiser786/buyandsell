package com.qssoft.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "Messages" )
public class Message implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int id;
    private int senderId;
    private int recipientId;
    private String message;
    private int statusId;
    private int propertyId;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "senderId")
    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @Column(name = "recipientId")
    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "statusId")
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Column(name = "propertyId")
    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public Message() {
    }

    public Message(int senderId, int recipientId, String message, int statusId, int propertyId) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.message = message;
        this.statusId = statusId;
        this.propertyId = propertyId;
    }
}
