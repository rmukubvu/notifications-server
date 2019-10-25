package com.thamserios.notificationsserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Bank {
    @Id
    private String id;
    private String bankName;
    private String universalBranchCode;
}

