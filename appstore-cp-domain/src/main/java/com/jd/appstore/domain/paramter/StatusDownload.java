package com.jd.appstore.domain.paramter;

import com.jd.common.util.base.BaseQuery;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-24
 * Time: 下午12:38
 * To change this template use File | Settings | File Templates.
 */
public class StatusDownload extends BaseQuery {
    private Integer accountId;
    private String beginTime;
    private String endTime;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
