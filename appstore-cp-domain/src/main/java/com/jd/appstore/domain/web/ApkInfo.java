package com.jd.appstore.domain.web;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-15
 * Time: 下午6:36
 * To change this template use File | Settings | File Templates.
 */
public class ApkInfo {

    private String appName;
    private String appVersion;
    private int year, month, days;
    private long categoryid_l2, categoryid1;
    private double price;
    private int fee_mode;
    private String applogo;
    private String[] appPic;


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public long getCategoryid_l2() {
        return categoryid_l2;
    }

    public void setCategoryid_l2(long categoryid_l2) {
        this.categoryid_l2 = categoryid_l2;
    }

    public long getCategoryid1() {
        return categoryid1;
    }

    public void setCategoryid1(long categoryid1) {
        this.categoryid1 = categoryid1;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFee_mode() {
        return fee_mode;
    }

    public void setFee_mode(int fee_mode) {
        this.fee_mode = fee_mode;
    }

    public String getApplogo() {
        return applogo;
    }

    public void setApplogo(String applogo) {
        this.applogo = applogo;
    }

    public String[] getAppPic() {
        return appPic;
    }

    public void setAppPic(String[] appPic) {
        this.appPic = appPic;
    }
}
