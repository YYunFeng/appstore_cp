package com.jd.appstore.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-4
 * Time: 下午7:17
 * To change this template use File | Settings | File Templates.
 * 应用基本信息日志表
 */
public class AppLog implements Serializable {
    /**
     * 应用基本信息日志表主键
     */
    private Integer id;
    /**
     * 应用编号
     */
    private Integer appId;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 包名
     */
    private String pkg;
    /**
     * 软件类型
     */
    private Integer appType;
    /**
     * 所属二级类目
     */
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
     * 基本信息审核状态
     */
    private Integer baseCheckStatus;
    /**
     * 基本信息同步ERP标记
     */
    private Integer baseSyncFlag;
    /**
     * 上下架同步ERP标记
     */
    private Integer onlineSyncFlag;
    /**
     * 最新标记
     */
    private Integer newstFlag;
    /**
     * 上下架操作类型
     */
    private Integer onlineOptType;


    /**
     * 记录创建时间
     */
    private Date created;
    /**
     * 记录修改时间
     */
    private Date modified;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getBaseCheckStatus() {
        return baseCheckStatus;
    }

    public void setBaseCheckStatus(Integer baseCheckStatus) {
        this.baseCheckStatus = baseCheckStatus;
    }

    public Integer getBaseSyncFlag() {
        return baseSyncFlag;
    }

    public void setBaseSyncFlag(Integer baseSyncFlag) {
        this.baseSyncFlag = baseSyncFlag;
    }

    public Integer getOnlineSyncFlag() {
        return onlineSyncFlag;
    }

    public void setOnlineSyncFlag(Integer onlineSyncFlag) {
        this.onlineSyncFlag = onlineSyncFlag;
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

    public Integer getNewstFlag() {
        return newstFlag;
    }

    public void setNewstFlag(Integer newstFlag) {
        this.newstFlag = newstFlag;
    }

    public Integer getOnlineOptType() {
        return onlineOptType;
    }

    public void setOnlineOptType(Integer onlineOptType) {
        this.onlineOptType = onlineOptType;
    }
}
