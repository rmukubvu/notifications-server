package com.thamserios.notificationsserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "notifications")
public class NotificationResponse {
    @Id
    private String id;
    private String sender;
    private String receiver;
    private String message;
    private Date messageDate;
    private String serviceProviderResponseId;
    private String serviceProvider;

    public NotificationResponse() {
    }

    public NotificationResponse(String sender, String receiver, String message, Date messageDate, String serviceProviderResponseId, String serviceProvider) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.messageDate = messageDate;
        this.serviceProviderResponseId = serviceProviderResponseId;
        this.serviceProvider = serviceProvider;
    }
}
