package com.jd.appstore.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-4
 * Time: 下午7:10
 * To change this template use File | Settings | File Templates.
 * 应用基本信息表
 */
public class Apps implements Serializable {
    /**
     * 应用编号
     */
    private Integer appId;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * package名
     */
    private String pkg;
    /**
     * 应用类型
     */
    private Integer appType;
    
    private Integer categoryidL2;
    /**
     * 所属三级类目1
     */
    private Integer categoryId1;
    /**
     * 所属三级类目2
     */
    private Integer categoryId2;
    /**
     * 默认详情ID
     */
    private Integer defaultDetailId;
    /**
     * 应用上下架状态
     */
    private Integer online;

    private Integer feeMode;
    /**
     * 记录创建时间
     */
    private Date created;
    /**
     * 记录修改时间
     */
    private Date modified;
    
    private Integer accountId;

    private Integer zhuowangMark;


    public Integer getZhuowangMark() {
        return zhuowangMark;
    }

    public void setZhuowangMark(Integer zhuowangMark) {
        this.zhuowangMark = zhuowangMark;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
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

    public Integer getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(Integer categoryId2) {
        this.categoryId2 = categoryId2;
    }

    public Integer getDefaultDetailId() {
        return defaultDetailId;
    }

    public void setDefaultDetailId(Integer defaultDetailId) {
        this.defaultDetailId = defaultDetailId;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getFeeMode() {
        return feeMode;
    }

    public void setFeeMode(Integer feeMode) {
        this.feeMode = feeMode;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
