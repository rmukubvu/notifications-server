package com.thamserios.notificationsserver.services;

import com.thamserios.notificationsserver.i.SmsService;
import com.thamserios.notificationsserver.model.TMessage;
import com.thamserios.notificationsserver.model.NotificationResponse;
import com.thamserios.notificationsserver.repository.NotificationResponseRepository;
import com.thamserios.notificationsserver.util.PhoneNumberFix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Executors;

@Service
public class TwilioSmsService implements SmsService {
    private TwilioClientSingleton routing;
    private final String twilioNumber = "441452260189";

    @Autowired
    private NotificationResponseRepository notificationResponseRepository;

    public TwilioSmsService() {
        this.routing = TwilioClientSingleton.getInstance();;
    }

    @Override
    public NotificationResponse sendSms(TMessage message) {
        var fixSenderNumber = PhoneNumberFix.getCorrectPhoneNumber(message.getCountryCode(),message.getReceiver());
        message.setSender(twilioNumber);
        message.setReceiver(fixSenderNumber);
        Executors.newSingleThreadExecutor().execute(() -> {
            var result = routing.send(message);
            notificationResponseRepository.save(result);
        });
        return getResponse("Queued",message);
    }

    private NotificationResponse getResponse(String sid, TMessage routingMessage){
        return new NotificationResponse(routingMessage.getSender(),
                routingMessage.getReceiver(),
                routingMessage.getMessage(),
                new Date(),
                sid,
                "TWILIO");
    }
}
