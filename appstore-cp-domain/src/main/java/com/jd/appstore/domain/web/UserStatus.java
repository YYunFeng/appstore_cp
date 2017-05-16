package com.jd.appstore.domain.web;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-11
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
public class UserStatus {
    //    判断是否有应用
    private String flag;
    //    已下传应用的个数
    private Integer outOnlineCount;
    //    已上线应用的个数
    private Integer onlineCount;
    //    未上线应用的个数
    private  Integer notOnlineCount;
    //    上下架状态
    protected Integer online;
    //   应用总数
    private Integer totalCount;
    private Integer count;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getOutOnlineCount() {
        return outOnlineCount;
    }

    public void setOutOnlineCount(Integer outOnlineCount) {
        this.outOnlineCount = outOnlineCount;
    }

    public Integer getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(Integer onlineCount) {
        this.onlineCount = onlineCount;
    }

    public Integer getNotOnlineCount() {
        return notOnlineCount;
    }

    public void setNotOnlineCount(Integer notOnlineCount) {
        this.notOnlineCount = notOnlineCount;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
