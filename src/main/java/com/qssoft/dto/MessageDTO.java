package com.qssoft.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable
{
    private static final long serialVersionUID = 1513207428686438208L;

    String message;
    String ownerId;
    String propertyId;
    String senderName;
    String adTopic;
    String status;
    String messageId;

    public MessageDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getAdTopic() {
        return adTopic;
    }

    public void setAdTopic(String adTopic) {
        this.adTopic = adTopic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
