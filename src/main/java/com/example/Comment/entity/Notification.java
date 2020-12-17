package com.example.Comment.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Comment comment;

    private Date date;

    private Boolean delivered;

    public Notification(Comment comment, Date date, Boolean delivered) {
        this.comment = comment;
        this.date = date;
        this.delivered = delivered;
    }
}
