package com.example.Comment.controllers;


import com.example.Comment.entity.Comment;
import com.example.Comment.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl service;


    @PostMapping("/add")
    public Comment addComment(@RequestParam("text") String text) {
        return text != null ? service.addComment(text) : null;
    }

    @GetMapping()
    public Comment getCommentByText(@RequestParam("text") String text) {
        return service.getCommentByText(text);
    }

    @GetMapping("/findAll")
    public List<Comment> findAll() {
        return service.findAll();
    }

}
