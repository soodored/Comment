package com.example.Comment.controllers;


import com.example.Comment.entity.Notification;
import com.example.Comment.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private static final int DEFAULT_COUNT = 10;

    @Autowired
    private NotificationServiceImpl service;

    @GetMapping()
    public Notification getNotificationByComment(@RequestParam("comment") Long comment) {
        return service.getNotificationByComment(comment);
    }

    @GetMapping("/findAll")
    public Page<Notification> findAll(@RequestParam("page") Integer page,
                                      @RequestParam(name = "count", required = false) Integer count) {
        return service.findAll(page, count != null ? count : DEFAULT_COUNT);
    }

}
