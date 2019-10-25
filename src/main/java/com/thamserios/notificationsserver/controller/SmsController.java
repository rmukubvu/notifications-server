package com.thamserios.notificationsserver.controller;

import com.thamserios.notificationsserver.i.SmsService;
import com.thamserios.notificationsserver.model.TMessage;
import com.thamserios.notificationsserver.model.NotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping
    public @ResponseBody
    ResponseEntity<NotificationResponse> post(@RequestBody TMessage message){
        return new ResponseEntity<>(smsService.sendSms(message), HttpStatus.OK);
    }
}
