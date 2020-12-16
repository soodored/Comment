package com.example.Comment.repositoty;

import com.example.Comment.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
}
