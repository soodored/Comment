package com.example.Comment.service;


import com.example.Comment.entity.Comment;
import com.example.Comment.repositoty.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.example.Comment.logic.BusinessLogic.doSomeWorkOnCommentCreation;


@Service
public class CommentServiceImpl {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private NotificationServiceImpl notificationService;

    public Comment addComment(String text) {
        Comment comment = createComment(text);

        if (!doneSomeWorkOnCommentCreation()) {
            deleteComment(comment);
            return null;
        }

        notificationService
                .createNotification(comment, true);

        return comment;
    }

    public Comment createComment(String text) {
        return repository.save(new Comment(text, new Date()));
    }

    public void deleteComment(Comment comment) {
        repository.delete(comment);
    }

    public Boolean doneSomeWorkOnCommentCreation() {
        try {
            doSomeWorkOnCommentCreation();
            return true;
        } catch (RuntimeException exception) {
            return false;
        }
    }

    public Comment getCommentByText(String text) {
        return repository.findByComment(text);
    }

    public List<Comment> findAll() {
        return repository.findAll();
    }
}
