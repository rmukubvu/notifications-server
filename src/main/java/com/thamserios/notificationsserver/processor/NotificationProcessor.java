package com.thamserios.notificationsserver.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface NotificationProcessor {
    String EMAIL = "local_rabbit_email";
    String SMS_INPUT = "local_rabbit_sms";

    @Input(NotificationProcessor.EMAIL)
    SubscribableChannel process_emails();

    @Input(NotificationProcessor.SMS_INPUT)
    SubscribableChannel process_sms();
}
