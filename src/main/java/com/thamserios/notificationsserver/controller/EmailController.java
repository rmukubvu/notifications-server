package com.thamserios.notificationsserver.controller;

import com.thamserios.notificationsserver.model.Person;
import com.thamserios.notificationsserver.model.NotificationResponse;
import com.thamserios.notificationsserver.services.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private WelcomeService welcomeService;

    @PostMapping
    public @ResponseBody
    ResponseEntity<NotificationResponse> welcomeMessage(@RequestBody Person person){
        return new ResponseEntity<>(welcomeService.sendWelcomeMessage(person), HttpStatus.OK);
    }
}
