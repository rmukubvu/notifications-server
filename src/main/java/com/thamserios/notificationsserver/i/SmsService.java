package com.thamserios.notificationsserver.i;

import com.thamserios.notificationsserver.model.TMessage;
import com.thamserios.notificationsserver.model.NotificationResponse;

public interface SmsService {
    NotificationResponse sendSms(TMessage message);
}
