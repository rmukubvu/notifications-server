package com.thamserios.notificationsserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class User {
    private String id;
    private String userName;
    private String password;
    private String personId;
    private String countryCode;
}
