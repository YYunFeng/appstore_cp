package com.jd.appstore.domain.json;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-9
 * Time: 上午11:29
 * To change this template use File | Settings | File Templates.
 */
public class AuthApkJSON {
    //应用名称
    private String appName;
    // 默认二级类目名称
    private String categoryidL2Name;
    // 默认二级类目Id
    private Integer categoryidL2;
    // 默认三级类目1名称
    private String categoryId1Name;
    // 默认三级类目1Id
    private Integer categoryId1;
    // 收费模式
    private Integer feeMode;
    // cp帐号Id
    private Integer accountId;
    // 应用的上下架状态
    private Integer online;
    // 标志是否能上传应用  0：可以上传详情，1：同一个CP第二次上传同一详情，2:该详情已经有个收费应用
    private Integer falg;
    // appId
    private Integer appId;
    private String apkFileName;

    private Integer status;
    
    private String mess;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getCategoryidL2Name() {
        return categoryidL2Name;
    }

    public void setCategoryidL2Name(String categoryidL2Name) {
        this.categoryidL2Name = categoryidL2Name;
    }

    public String getCategoryId1Name() {
        return categoryId1Name;
    }

    public void setCategoryId1Name(String categoryId1Name) {
        this.categoryId1Name = categoryId1Name;
    }

    public Integer getFeeMode() {
        return feeMode;
    }

    public void setFeeMode(Integer feeMode) {
        this.feeMode = feeMode;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getFalg() {
        return falg;
    }

    public void setFalg(Integer falg) {
        this.falg = falg;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getCategoryidL2() {
        return categoryidL2;
    }

    public void setCategoryidL2(Integer categoryidL2) {
        this.categoryidL2 = categoryidL2;
    }

    public Integer getCategoryId1() {
        return categoryId1;
    }

    public void setCategoryId1(Integer categoryId1) {
        this.categoryId1 = categoryId1;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getApkFileName() {
        return apkFileName;
    }

    public void setApkFileName(String apkFileName) {
        this.apkFileName = apkFileName;
    }
}
