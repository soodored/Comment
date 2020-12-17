package com.example.Comment.controllers;


import com.example.Comment.entity.Comment;
import com.example.Comment.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comment")
public class CommentController {

    private static final int DEFAULT_COUNT = 10;

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
    public Page<Comment> findAll(@RequestParam("page") Integer page,
                                 @RequestParam(name = "count", required = false) Integer count) {
        return service.findAll(page, count != null ? count : DEFAULT_COUNT);
    }

}
