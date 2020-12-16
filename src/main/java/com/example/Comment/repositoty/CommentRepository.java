package com.example.Comment.repositoty;

import com.example.Comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsByComment(String comment);
    boolean existsByDate(Date comment);
}
