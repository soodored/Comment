package com.example.Comment.controllers;

import com.example.Comment.entity.Comment;
import com.example.Comment.entity.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import util.*;

import java.util.Date;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final static int REPEATS = 100;

    EntitiesStatus testAddComment(String comment) {
        Comment createdComment = this.restTemplate.postForObject(
                "http://localhost:" + port + "/comment/add?text=" + comment,
                null, Comment.class);

        if (isCommentCreated(comment)) {
            return isCreatedNotificationDelivered(createdComment)
                    ? new EntitiesStatus(CommentCreationStatusEnum.CREATED, NotificationDeliverStatusEnum.DELIVERED)
                    : new EntitiesStatus(CommentCreationStatusEnum.CREATED, NotificationDeliverStatusEnum.NOT_DELIVERED);
        } else {
            return new EntitiesStatus(CommentCreationStatusEnum.NOT_CREATED, null);
        }
    }

    @Test
    void repeatedTest() {
        CreatedCommentPercentageScorer createdCommentPercentageScorer = new CreatedCommentPercentageScorer();
        DeliveredNotificationPercentageScorer deliveredNotificationPercentageScorer =
                new DeliveredNotificationPercentageScorer();

        for (int i = 0; i < REPEATS; i++) {
            String comment = generateRandomString();
            EntitiesStatus entitiesStatus = testAddComment(comment);

            createdCommentPercentageScorer.recalculateScore(entitiesStatus.getCommentCreationStatusEnum());
            deliveredNotificationPercentageScorer.recalculateScore(entitiesStatus.getNotificationDeliverStatusEnum());
        }

        printResult(createdCommentPercentageScorer, deliveredNotificationPercentageScorer);
    }

    private boolean isCommentCreated(String comment) {
        return this.restTemplate.getForObject(
                "http://localhost:" + port + "/comment?text=" + comment, Comment.class) != null;
    }

    private Boolean isCreatedNotificationDelivered(Comment comment) {
        return this.restTemplate.getForObject("http://localhost:" + port + "/notification?comment=" + comment.getId(),
                Notification.class).getDelivered();
    }

    private void printResult(CreatedCommentPercentageScorer createdCommentPercentageScorer,
                             DeliveredNotificationPercentageScorer deliveredNotificationPercentageScorer) {
        System.out.print("CreatedCommentPercentage: " + createdCommentPercentageScorer.getScore());
        System.out.println("\nDeliveredNotificationPercentage: " + deliveredNotificationPercentageScorer.getScore());
    }

    private String generateRandomString() {
        return Long.toString(new Date().getTime());
    }
}