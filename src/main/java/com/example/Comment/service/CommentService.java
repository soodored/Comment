package com.example.Comment.service;


import com.example.Comment.entity.Comment;
import com.example.Comment.entity.Notifications;
import com.example.Comment.repositoty.CommentRepository;
import com.example.Comment.repositoty.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.example.Comment.logic.BusinessLogic.doSomeWorkOnCommentCreation;
import static com.example.Comment.logic.BusinessLogic.doSomeWorkOnNotification;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private NotificationsRepository notificationsRepository;

    public void addComment(String text) {
        Comment comment = new Comment();
        Notifications notifications = new Notifications();

        try {
            comment = addNewComment(text);
            notifications.setDelivered(true);
            notifications.setDate(new Date());
            notifications.setCommentId(comment);
            notificationsRepository.save(notifications);
        } catch (RuntimeException ignored) {

        }

        try {
            doSomeWorkOnCommentCreation();
        } catch (RuntimeException e) {
            repository.delete(comment);
        }


        try {
            doSomeWorkOnNotification();
            notifications.setDelivered(true);
        } catch (RuntimeException e) {
            notifications.setDelivered(false);
        }

    }

    public Comment addNewComment(String text) {
        Comment comment = new Comment();
        comment.setComment(text);
        comment.setDate(new Date());
        return repository.save(comment);
    }

    public Comment getComment(Long id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).orElse(new Comment());
        }
        return null;
    }

    public List<Comment> findAll() {
        return repository.findAll();
    }

}
