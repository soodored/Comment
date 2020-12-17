package util;

public class CreatedCommentPercentageScorer {
    private static int commentCount = 0;
    private static int createdCommentCount = 0;
    private static int score = 0;

    public void recalculateScore(CommentCreationStatusEnum commentCreationStatusEnum) {
        commentCount += 1;
        if (commentCreationStatusEnum == CommentCreationStatusEnum.CREATED) {
            createdCommentCount += 1;
        }
        score = Math.round((float) (createdCommentCount * 100) / commentCount);
    }

    public int getScore() {
        return score;
    }
}
