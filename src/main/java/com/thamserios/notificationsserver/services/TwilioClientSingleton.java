package com.thamserios.notificationsserver.services;

import com.thamserios.notificationsserver.model.NotificationResponse;
import com.thamserios.notificationsserver.model.TMessage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.Date;

public class TwilioClientSingleton {
    private static TwilioClientSingleton instance;
    private final String ACCOUNT_SID = "AC4cfb2d24bb0aab19249af1ad33cecdea";
    private static final String AUTH_TOKEN = "a5e460406248767cd0d8c32e6ea9560b";

    private TwilioClientSingleton(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public static synchronized TwilioClientSingleton getInstance(){
        if(instance == null){
            instance = new TwilioClientSingleton();
        }
        return instance;
    }

    public NotificationResponse send(TMessage routingMessage){
        String from = "+" + routingMessage.getSender();
        String to = "+" + routingMessage.getReceiver();

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber(from),
                routingMessage.getMessage())
                .create();
        return getResponse(message.getSid(),routingMessage);
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
