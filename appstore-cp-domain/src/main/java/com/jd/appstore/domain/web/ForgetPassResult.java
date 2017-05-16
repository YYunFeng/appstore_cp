package com.jd.appstore.domain.web;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-25
 * Time: 下午11:33
 * To change this template use File | Settings | File Templates.
 */
public class ForgetPassResult {
    private String email;
    private Integer flag;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
