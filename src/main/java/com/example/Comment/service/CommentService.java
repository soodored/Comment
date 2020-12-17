package com.example.Comment.service;


import com.example.Comment.entity.Comment;
import com.example.Comment.repositoty.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.Comment.logic.BusinessLogic.doSomeWorkOnCommentCreation;


@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private NotificationService notificationService;

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

    public Page<Comment> findAll(int page, int count) {
        return repository.findAll(PageRequest.of(page, count));
    }
}
