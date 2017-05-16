package com.jd.appstore.domain.enumtype;

/**
 * 应用商店登录状态
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12-8-29
 * Time: 下午8:14
 * To change this template use File | Settings | File Templates.
 */
public enum AppStoreLoginStatus {

    NOT_LOGIN(1, "未登录"),
    LOGIN_AND_DEVELOPER(2, "已经登录");

    private int code;
    private String desc;

    private AppStoreLoginStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
