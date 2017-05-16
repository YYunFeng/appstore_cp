package com.jd.appstore.domain;

/**
 * Created by IntelliJ IDEA.
 * User: xietieyun
 * Date: 12-8-24
 * Time: 下午8:24
 * To change this template use File | Settings | File Templates.
 */
public class UserInfoObj {
    
    private String pin;
    private String imgUrl;// 头像
    private String smallImgUrl; //小头像
    private String levelName;
    private String location;// 所在地

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
