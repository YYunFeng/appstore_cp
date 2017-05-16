package com.jd.appstore.domain.paramter;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-26
 * Time: 上午11:26
 * To change this template use File | Settings | File Templates.
 */
public class StatAppDown  {
    private Integer appId;
    private String startTime;
    private String endTime;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
