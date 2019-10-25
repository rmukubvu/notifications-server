package com.thamserios.notificationsserver.services;

import com.thamserios.notificationsserver.model.NotificationResponse;
import com.thamserios.notificationsserver.model.SlackMessage;
import com.thamserios.notificationsserver.repository.NotificationResponseRepository;
import com.thamserios.notificationsserver.util.SlackUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Executors;

@Service
public class SlackService {

    @Autowired
    private NotificationResponseRepository notificationResponseRepository;

    public NotificationResponse sendSlackMessage(String message){
        var model = new NotificationResponse("notification-service", "house-hold-support", message, new Date(), "Queued", "SLACK");
        Executors.newSingleThreadExecutor().execute(() -> {
            SlackMessage slackMessage = SlackMessage.builder()
                    .channel("house-hold-support")
                    .username("notifier")
                    .text(message)
                    .icon_emoji(":twice:")
                    .build();
            SlackUtils.sendMessage(slackMessage);
            notificationResponseRepository.save(model);
        });
        return model;
    }
}
