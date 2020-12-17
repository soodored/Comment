package util;

import lombok.Getter;

@Getter
public class EntitiesStatus {
    CommentCreationStatusEnum commentCreationStatusEnum;
    NotificationDeliverStatusEnum notificationDeliverStatusEnum;

    public EntitiesStatus(CommentCreationStatusEnum commentCreationStatusEnum,
                          NotificationDeliverStatusEnum notificationDeliverStatusEnum) {
        this.commentCreationStatusEnum = commentCreationStatusEnum;
        this.notificationDeliverStatusEnum = notificationDeliverStatusEnum;
    }
}
