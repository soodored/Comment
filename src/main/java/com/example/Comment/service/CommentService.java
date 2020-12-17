package com.example.Comment.service;


import com.example.Comment.entity.Comment;
import com.example.Comment.entity.Notification;
import com.example.Comment.repositoty.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.Comment.logic.BusinessLogic.doSomeWorkOnCommentCreation;


@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private NotificationService notificationService;

    public void addComment(String text) {
        Comment comment = createComment(text);

        if (!doneSomeWorkOnCommentCreation()) {
            deleteComment(comment);
            return;
        }

        notificationService
                .createNotification(comment, true);
    }

    private Comment createComment(String text) {
        return repository.save(new Comment(text, new Date()));
    }

    private void deleteComment(Comment comment) {
        repository.delete(comment);
    }

    private Boolean doneSomeWorkOnCommentCreation() {
        try {
            doSomeWorkOnCommentCreation();
            return true;
        } catch (RuntimeException exception) {
            return false;
        }
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
