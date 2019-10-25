package com.thamserios.notificationsserver.controller;

import com.thamserios.notificationsserver.model.NotificationResponse;
import com.thamserios.notificationsserver.services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/slack")
public class SlackController {

    @Autowired
    private SlackService slackService;

    @PostMapping
    public @ResponseBody
    ResponseEntity<NotificationResponse> sendSlackMessage(@RequestBody String message){
        return new ResponseEntity<>(slackService.sendSlackMessage(message), HttpStatus.OK);
    }
}
