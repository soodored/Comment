package com.example.Comment.service;


import com.example.Comment.entity.Comment;
import com.example.Comment.entity.Notifications;
import com.example.Comment.repositoty.CommentRepository;
import com.example.Comment.repositoty.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private NotificationsRepository notificationsRepository;

    public void addComment(String text) {
        Comment comment = new Comment();
        comment.setComment(text);
        comment.setDate(new Date());
        Comment save = repository.save(comment);
        if(repository.existsByComment(text) && repository.existsByDate(comment.getDate())){
            Notifications notifications = new Notifications();
            notifications.setDate(new Date());
            notifications.setDelivered(true);
            notifications.setCommentId(save);
            notificationsRepository.save(notifications);
        }
        else {
            Notifications notifications = new Notifications();
            notifications.setDate(new Date());
            notifications.setDelivered(false);
            notifications.setCommentId(save);
            notificationsRepository.save(notifications);
        }

    }

    public Comment getComment(Long id){
        if(repository.findById(id).isPresent()){
            return repository.findById(id).orElse(new Comment());
        }
        return null;
    }

    public List<Comment> findAll(){
        return repository.findAll();
    }

}
