package com.jd.appstore.domain.json;

import java.io.Serializable;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-10
 * Time: 上午11:43
 * To change this template use File | Settings | File Templates.
 */
public class CpBaseinfoJSON implements Serializable {
    private int id;
    //    上传应用的个数
    private int count;
    //    审核通过的个数
    private int forApprovalCount;
    //   审核未通过的个数
    private int approvalFailCount;
    //    待审核的个数
    private int onApprovalCount;
    //    在线的个数
    private int onlineCount;
    private int updataStatus;
    private int online;
    private String account;
    private String password;
    private String appid;
    private String msg;

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getUpdataStatus() {
        return updataStatus;
    }

    public void setUpdataStatus(int updataStatus) {
        this.updataStatus = updataStatus;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getForApprovalCount() {
        return forApprovalCount;
    }

    public void setForApprovalCount(int forApprovalCount) {
        this.forApprovalCount = forApprovalCount;
    }

    public int getApprovalFailCount() {
        return approvalFailCount;
    }

    public void setApprovalFailCount(int approvalFailCount) {
        this.approvalFailCount = approvalFailCount;
    }

    public int getOnApprovalCount() {
        return onApprovalCount;
    }

    public void setOnApprovalCount(int onApprovalCount) {
        this.onApprovalCount = onApprovalCount;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
