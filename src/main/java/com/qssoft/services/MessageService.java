package com.qssoft.services;

import com.qssoft.dao.MessageDAO;
import com.qssoft.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService
{
    @Autowired
    private MessageDAO messageDAO;

    public void createMessage(int senderId, int recepintId, String mess, int propertyId) {
        Message message = new Message(senderId, recepintId, mess, 1, propertyId);
        messageDAO.createMessage(message);
    }

    public List<Message> getAllMessagesToOwner(int ownerId) {
        return messageDAO.getMessagesByOwnerId(ownerId);
    }

    public List<Message> getMessagesByPropertyId(int propertyId) {
        return messageDAO.getMessagesByPropertyId(propertyId);
    }

    public Message getMessageById(int messageId) {
        return messageDAO.getMessagesById(messageId);
    }

    public void markMessageAsRead(int messageId) {
        messageDAO.updateMessageStatus(messageId, 2);
    }

}
