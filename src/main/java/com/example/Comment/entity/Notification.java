package com.example.Comment.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Comment commentId;

    private Date date;

    private Boolean delivered;

    public Notification(Comment comment, Date date, Boolean delivered) {
        this.commentId = comment;
        this.date = date;
        this.delivered = delivered;
    }
}
