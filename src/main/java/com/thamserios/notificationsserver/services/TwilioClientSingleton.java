package com.thamserios.notificationsserver.services;

import com.thamserios.notificationsserver.model.NotificationResponse;
import com.thamserios.notificationsserver.model.TMessage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.Date;

public class TwilioClientSingleton {
    private static TwilioClientSingleton instance;
    private final String ACCOUNT_SID = "AC06dc812b689948eff7b484b7ad3a3548";
    private static final String AUTH_TOKEN = "8ade518cea360d0e41547dd8529cf2ef";

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
