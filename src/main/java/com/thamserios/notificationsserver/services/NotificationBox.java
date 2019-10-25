package com.thamserios.notificationsserver.services;

import com.thamserios.notificationsserver.model.Person;
import com.thamserios.notificationsserver.model.TMessage;
import com.thamserios.notificationsserver.model.User;
import com.thamserios.notificationsserver.processor.NotificationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
@EnableBinding(NotificationProcessor.class)
public class NotificationBox {

    @Autowired
    private TwilioSmsService twilioSmsService;

    @StreamListener(NotificationProcessor.SMS_INPUT)
    public void processMessages(TMessage message){
       Executors.newSingleThreadExecutor().execute(() -> {
           twilioSmsService.sendSms(message);
        });
    }

    @StreamListener(NotificationProcessor.EMAIL)
    public void processMessages(User user){
        Executors.newSingleThreadExecutor().execute(() -> {
            var message = String.format("Welcome to House Hold\n" +
                    "You login with your cellphone number\n" +
                    "with the following pin: %s\n" +
                    "add +44 161 850 7453 to your contacts.",user.getPassword());
            var model = new TMessage();
            model.setReceiver(user.getUserName());
            model.setSender("");
            model.setMessage(message);
            model.setCountryCode(user.getCountryCode());
            twilioSmsService.sendSms(model);
        });
    }
}
