package com.jd.appstore.domain;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-11-21
 * Time: 下午6:02
 * To change this template use File | Settings | File Templates.
 */
public class AuthMessJSON {
    private Boolean status;
    private String code;
    private String mess;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
