package com.jd.appstore.domain.json;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-19
 * Time: 下午2:38
 * To change this template use File | Settings | File Templates.
 */
public class StatusJSON {
    private Boolean status;
    private String msg;
    private Integer accountId;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
