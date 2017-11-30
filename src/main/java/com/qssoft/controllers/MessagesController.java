package com.qssoft.controllers;

import com.qssoft.dto.MessageDTO;
import com.qssoft.dto.Property;
import com.qssoft.entities.Message;
import com.qssoft.security.UserAccessHelper;
import com.qssoft.services.MessageService;
import com.qssoft.services.PropertyDetailsService;
import com.qssoft.services.RealEstateUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MessagesController
{
    @Autowired
    private MessageService messageService;

    @Autowired
    RealEstateUserDetailsService userDetailsService;

    @Autowired
    PropertyDetailsService propertyDetailsService;

    @RequestMapping(value="/showMessages")
    public String messagesList(Model model)
    {
        List<Message> allMessagesToOwner = messageService.getAllMessagesToOwner(UserAccessHelper.getUserId());
        model.addAttribute("messagesList", populateMessageDTO(allMessagesToOwner));
        model.addAttribute("numberOfUnread", getNumberOfUnreadMessages(allMessagesToOwner));
        return "messages/messagesList";
    }

    private int getNumberOfUnreadMessages(List<Message> messages)
    {
        return Math.toIntExact(messages.stream().filter(message -> message.getStatusId() == 1).count());
    }

    @RequestMapping(value="/viewMessage/{id}", method = RequestMethod.GET)
    public String openMessage(@PathVariable("id") final String id, Model model)
    {
        Message message = messageService.getMessageById(Integer.parseInt(id));
        MessageDTO result = new MessageDTO();
        result.setMessage(message.getMessage());
        result.setOwnerId(String.valueOf(message.getSenderId()));
        result.setPropertyId(String.valueOf(message.getPropertyId()));
        messageService.markMessageAsRead(Integer.parseInt(id));
        model.addAttribute("message", result);
        return "messages/messageDetails";
    }

    @ResponseBody
    @RequestMapping(value="/postMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendMessage(@RequestBody final MessageDTO MessageDTO)
    {
        messageService.createMessage(UserAccessHelper.getUserId(), Integer.parseInt(MessageDTO.getOwnerId()), MessageDTO.getMessage(), Integer.parseInt(MessageDTO.getPropertyId()));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    private List<MessageDTO> populateMessageDTO(List<Message> messages) {
        List<MessageDTO> result = new ArrayList<>(messages.size());
        for(Message message : messages) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessage(message.getMessage());
            messageDTO.setPropertyId(String.valueOf(message.getPropertyId()));
            UserDetails userDetails = userDetailsService.loadUserById(message.getSenderId());
            messageDTO.setSenderName(userDetails.getUsername());
            Property property = propertyDetailsService.getPropertyById(String.valueOf(message.getPropertyId()));
            messageDTO.setAdTopic(property.getTitle());
            messageDTO.setStatus(String.valueOf(message.getStatusId()));
            messageDTO.setMessageId(String.valueOf(message.getId()));
            result.add(messageDTO);
        }
        return result;
    }

}
