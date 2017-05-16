package com.jd.appstore.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-4
 * Time: 下午8:09
 * To change this template use File | Settings | File Templates.
 * 终端操作系统类型表
 */
public class AndroidAppOsversion implements Serializable {
    /**
     * OS版本ID
     */
    private int osVersionId;
    /**
     * OS版本名
     */
    private String osVersionName;
    /**
     * OS版本码
     */
    private int osVersionCode;
    /**
     * 备注
     */
    private String intro;
    /**
     * 记录创建时间
     */
    private Date created;

    /**
     * 记录修改时间
     */
    private Date modified;

    public int getOsVersionId() {
        return osVersionId;
    }

    public void setOsVersionId(int osVersionId) {
        this.osVersionId = osVersionId;
    }

    public String getOsVersionName() {
        return osVersionName;
    }

    public void setOsVersionName(String osVersionName) {
        this.osVersionName = osVersionName;
    }

    public int getOsVersionCode() {
        return osVersionCode;
    }

    public void setOsVersionCode(int osVersionCode) {
        this.osVersionCode = osVersionCode;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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
