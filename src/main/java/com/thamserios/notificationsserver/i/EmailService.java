package com.thamserios.notificationsserver.i;

public interface EmailService {
    String sendText(String from, String to, String subject, String body);
    String sendHTML(String from, String to, String subject, String body);
}
