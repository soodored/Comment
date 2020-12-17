package com.example.Comment.controllers;


import com.example.Comment.entity.Notification;
import com.example.Comment.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {


    @Autowired
    private NotificationServiceImpl service;

    @GetMapping()
    public Notification getNotificationByComment(@RequestParam("comment") Long comment) {
        return service.getNotificationByComment(comment);
    }

}
