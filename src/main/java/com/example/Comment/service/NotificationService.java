package com.example.Comment.service;

import com.example.Comment.entity.Comment;
import com.example.Comment.entity.Notification;
import com.example.Comment.repositoty.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.Comment.logic.BusinessLogic.doSomeWorkOnNotification;

import java.util.Date;

@Service
public class NotificationService {
    @Autowired
    private NotificationsRepository notificationsRepository;

    public Notification createNotification(Comment comment, Boolean delivered) {
        Notification notification = notificationsRepository
                .save(new Notification(comment, new Date(), delivered));
        updateDelivered(doneSomeWorkOnNotification(), notification);
        return notification;
    }

    private Boolean doneSomeWorkOnNotification() {
        try {
            doSomeWorkOnNotification();
            return true;
        } catch (RuntimeException exception) {
            return false;
        }
    }

    private void updateDelivered(Boolean delivered, Notification notification) {
        notification.setDelivered(delivered);
        notificationsRepository.save(notification);
    }
}