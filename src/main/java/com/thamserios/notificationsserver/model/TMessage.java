package com.thamserios.notificationsserver.model;

import lombok.Data;

@Data
public class TMessage {
    private String sender;
    private String receiver;
    private String message;
    private String countryCode;
}
