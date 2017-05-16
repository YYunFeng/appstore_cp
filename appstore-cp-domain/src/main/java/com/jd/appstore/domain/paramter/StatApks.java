package com.jd.appstore.domain.paramter;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-26
 * Time: 下午9:32
 * To change this template use File | Settings | File Templates.
 */
public class StatApks {
    private Integer online;
    private Integer updateStatus;
    private Integer count;
    private Integer totalCount;
    //  待审核
    private Integer awaitApprovedCount;
    //  审核中
    private Integer onApprovedCount;
    //  在线无更新
    private Integer onlineNoupdate;
    //  审核不通过
    private Integer notApprovedCount;
    //  已下线
    private Integer outOnline;
    // 未上线
    private Integer notOnline;

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getAwaitApprovedCount() {
        return awaitApprovedCount;
    }

    public void setAwaitApprovedCount(Integer awaitApprovedCount) {
        this.awaitApprovedCount = awaitApprovedCount;
    }

    public Integer getOnApprovedCount() {
        return onApprovedCount;
    }

    public void setOnApprovedCount(Integer onApprovedCount) {
        this.onApprovedCount = onApprovedCount;
    }

    public Integer getOnlineNoupdate() {
        return onlineNoupdate;
    }

    public void setOnlineNoupdate(Integer onlineNoupdate) {
        this.onlineNoupdate = onlineNoupdate;
    }

    public Integer getNotApprovedCount() {
        return notApprovedCount;
    }

    public void setNotApprovedCount(Integer notApprovedCount) {
        this.notApprovedCount = notApprovedCount;
    }

    public Integer getOutOnline() {
        return outOnline;
    }

    public void setOutOnline(Integer outOnline) {
        this.outOnline = outOnline;
    }

    public Integer getNotOnline() {
        return notOnline;
    }

    public void setNotOnline(Integer notOnline) {
        this.notOnline = notOnline;
    }
}
