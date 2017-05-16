package com.jd.appstore.domain.paramter;

import com.jd.common.util.base.BaseQuery;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-24
 * Time: 上午10:23
 * To change this template use File | Settings | File Templates.
 */
public class CheckApk extends BaseQuery {
    private Integer accountId;
    private Integer updateStatus;
    private Integer online;
    private Integer appType;
    private Integer appId;
    private Integer categoryId_l2;
    private Integer isCopy;
    private Integer resType;
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getCopy() {
        return isCopy;
    }

    public void setCopy(Integer copy) {
        isCopy = copy;
    }

    public Integer getCategoryId_l2() {
        return categoryId_l2;
    }

    public void setCategoryId_l2(Integer categoryId_l2) {
        this.categoryId_l2 = categoryId_l2;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }
}
