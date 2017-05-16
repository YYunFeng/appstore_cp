package com.jd.appstore.domain;

/**
 * Created by IntelliJ IDEA.
 * User: gaoyang
 * Date: 12-7-20
 * Time: 下午2:10
 * To change this template use File | Settings | File Templates.
 */
public class CommentInfoObj {
    /**
     *评价标题，长度在4-20个字之间
     */
    private String subject;
    /**
     *优点，长度在5-100个字之间
     */
    private String goodPoint;
    /**
     *缺点，长度在5-100个字之间
     */
    private String weakPoint;
    /**
     *使用心得，长度在5-200个字之间
     */
    private String content;
    /**
     *评分，1-5
     */
    private Integer score;
    /**
     * 客户端类型
     */
    private Integer clientType;

    private String dateTime;

    /**
     * 用户信息
     */
    private UserInfoObj userInfoObj;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public UserInfoObj getUserInfoObj() {
        return userInfoObj;
    }

    public void setUserInfoObj(UserInfoObj userInfoObj) {
        this.userInfoObj = userInfoObj;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGoodPoint() {
        return goodPoint;
    }

    public void setGoodPoint(String goodPoint) {
        this.goodPoint = goodPoint;
    }

    public String getWeakPoint() {
        return weakPoint;
    }

    public void setWeakPoint(String weakPoint) {
        this.weakPoint = weakPoint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }
}
