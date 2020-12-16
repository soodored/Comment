package com.example.Comment.controllers;


import com.example.Comment.entity.Comment;
import com.example.Comment.entity.dto.RestResponse;
import com.example.Comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService service;


    @PutMapping("/add")
    public RestResponse<String> addComment(@RequestParam("text") String text) {
        if(text != null){
            service.addComment(text);
            return new RestResponse<>(null, "Save successfully", 0);
        }
            return new RestResponse<>(null, "Get Comment is PROBLEM", 1);
    }


//    @Valid
    @GetMapping("/get")
    public RestResponse<Comment> getComment(@RequestParam("id") Long id){
        Comment comment = service.getComment(id);
        if(comment == null){
            return new RestResponse<>(null, "Get Comment is PROBLEM", 1);
        }
        return new RestResponse<>(comment, "ALL RIGHT", 0);
    }

    @GetMapping("/findAll")
    public List<Comment> findAll(){
        return service.findAll();
    }

}
