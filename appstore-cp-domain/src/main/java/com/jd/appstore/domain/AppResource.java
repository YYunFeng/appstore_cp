package com.jd.appstore.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-4
 * Time: 下午7:41
 * To change this template use File | Settings | File Templates.
 * 应用资源信息表
 */
public class AppResource implements Serializable {
    /**
     * 资源编号
     */
    private int resId;
    /**
     * 应用编号
     */
    private int appId;
    /**
     * CP账号id
     */
    private int accountId;
    /**
     * 资源类型
     */
    private int resType;
    /**
     * 截图序号
     */
    private int capSeq;
    /**
     * 资源URL
     */
    private String resUrl;
    /**
     * 是否资源副本
     */
    private int isCopy;
    /**
     * 记录创建时间
     */
    private Date created;
    /**
     * 记录修改时间
     */
    private Date modified;
    
    private String[] pics;

    private String apkName;

    private Integer zhuowangMark;

    public Integer getZhuowangMark() {
        return zhuowangMark;
    }

    public void setZhuowangMark(Integer zhuowangMark) {
        this.zhuowangMark = zhuowangMark;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public String[] getPics() {
        return pics;
    }

    public void setPics(String[] pics) {
        this.pics = pics;
    }


    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getResType() {
        return resType;
    }

    public void setResType(int resType) {
        this.resType = resType;
    }

    public int getCapSeq() {
        return capSeq;
    }

    public void setCapSeq(int capSeq) {
        this.capSeq = capSeq;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public int getCopy() {
        return isCopy;
    }

    public void setCopy(int copy) {
        isCopy = copy;
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
}
