package com.jd.appstore.domain.web;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-9-27
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */
public class CommentSummaryReuslt {
    /**
     * 好评度
     */
    private String goodRate;

    private String middleRate;

    private String poorRate;

    /**
     * 星级*
     */
    private int star;
    /**
     * 评论次数*
     */
    private int commentCount;

    /**
     * 星级：1*
     */
    private int score1Count;
    /**
     * 星级：2*
     */
    private int score2Count;
    /**
     * 星级：3*
     */
    private int score3Count;
    /**
     * 星级：4*
     */
    private int score4Count;
    /**
     * 星级：5*
     */
    private int score5Count;


    public String getGoodRate() {
        return goodRate;
    }

    public void setGoodRate(String goodRate) {
        this.goodRate = goodRate;
    }

    public String getMiddleRate() {
        return middleRate;
    }

    public void setMiddleRate(String middleRate) {
        this.middleRate = middleRate;
    }

    public String getPoorRate() {
        return poorRate;
    }

    public void setPoorRate(String poorRate) {
        this.poorRate = poorRate;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getScore1Count() {
        return score1Count;
    }

    public void setScore1Count(int score1Count) {
        this.score1Count = score1Count;
    }

    public int getScore2Count() {
        return score2Count;
    }

    public void setScore2Count(int score2Count) {
        this.score2Count = score2Count;
    }

    public int getScore3Count() {
        return score3Count;
    }

    public void setScore3Count(int score3Count) {
        this.score3Count = score3Count;
    }

    public int getScore4Count() {
        return score4Count;
    }

    public void setScore4Count(int score4Count) {
        this.score4Count = score4Count;
    }

    public int getScore5Count() {
        return score5Count;
    }

    public void setScore5Count(int score5Count) {
        this.score5Count = score5Count;
    }
}
