package com.jd.appstore.domain.web;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-24
 * Time: 下午12:35
 * To change this template use File | Settings | File Templates.
 */
public class DownStatus {
    
    private Integer appId;
    private String appName;
    private Integer sevenDaysAwayCount;
    private Integer totalCount;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getSevenDaysAwayCount() {
        return sevenDaysAwayCount;
    }

    public void setSevenDaysAwayCount(Integer sevenDaysAwayCount) {
        this.sevenDaysAwayCount = sevenDaysAwayCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
