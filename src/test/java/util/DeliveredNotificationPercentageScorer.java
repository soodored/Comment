package util;

public class DeliveredNotificationPercentageScorer {
    private static int notificationCount = 0;
    private static int deliveredNotificationCount = 0;
    private static int score = 0;

    public void recalculateScore(NotificationDeliverStatusEnum notificationDeliverStatusEnum) {
        notificationCount += 1;
        if (notificationDeliverStatusEnum == NotificationDeliverStatusEnum.DELIVERED) {
            deliveredNotificationCount += 1;
        }
        score = Math.round((float) (deliveredNotificationCount * 100) / notificationCount);
    }

    public int getScore() {
        return score;
    }
}
