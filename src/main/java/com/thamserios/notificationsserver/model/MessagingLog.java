package com.thamserios.notificationsserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class MessagingLog {
        @Id
        private String id;
        private String account;
        private String message;
        private String messageType;
        private String messageResponse;
        private Date createdDate;

        public MessagingLog(String account, String message, String messageType, String messageResponse) {
            this.account = account;
            this.message = message;
            this.messageType = messageType;
            this.messageResponse = messageResponse;
            this.createdDate = new Date();
        }
}
