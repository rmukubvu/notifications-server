package com.thamserios.notificationsserver.services;

import com.thamserios.notificationsserver.model.MessagingLog;
import com.thamserios.notificationsserver.model.MessagingTypes;
import com.thamserios.notificationsserver.model.NotificationResponse;
import com.thamserios.notificationsserver.model.Person;
import com.thamserios.notificationsserver.repository.MessagingLogsRepository;
import com.thamserios.notificationsserver.repository.NotificationResponseRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;

@Service
public class WelcomeService {

    final
    MessagingLogsRepository messagingLogsRepository;

    final
    NotificationResponseRepository notificationResponseRepository;

    final
    SendGridEmailService sendGridEmailService;

    @Autowired
    public WelcomeService(final MessagingLogsRepository messagingLogsRepository, final NotificationResponseRepository notificationResponseRepository, final SendGridEmailService sendGridEmailService) {
        this.messagingLogsRepository = messagingLogsRepository;
        this.notificationResponseRepository = notificationResponseRepository;
        this.sendGridEmailService = sendGridEmailService;
    }

    public NotificationResponse sendWelcomeMessage(Person person) {
        var model = new NotificationResponse("welcome@amakhosi.co.za", person.getEmail(), "Amakhosi Login Details", new Date(), "Queued", "SENDGRID");
        Executors.newSingleThreadExecutor().execute(() -> {
            var html = getHtmlView(person);
            sendEmailNotification(person.getCellPhone(), person.getEmail(), "Amakhosi Login Details", html);
            notificationResponseRepository.save(model);
        });
        return model;
    }

    private String getHtmlView(Person person) {
        String result = "";
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream("static/welcome.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.replace("%full_name%", String.format("%s %s", person.getFirstName(), person.getLastName()))
                .replace("%user_name%", person.getCellPhone())
                .replace("%user_password%","Sent via SMS");
    }

    private void sendEmailNotification(String userId,String destination,String subject,String body) {
        String response = sendGridEmailService.sendHTML("welcome@amakhosi.co.za", destination, subject, body);
        messagingLogsRepository.save(new MessagingLog(userId, body, MessagingTypes.EMAIL, response));
    }
}
