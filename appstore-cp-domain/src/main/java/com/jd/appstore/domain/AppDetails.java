package com.jd.appstore.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-4
 * Time: 下午7:23
 * To change this template use File | Settings | File Templates.
 * 应用详情信息表
 */
public class AppDetails implements Serializable {
    /**
     * 详情ID
     */
    private Integer id;
    /**
     * 应用编号
     */
    private Integer appId;
    /**
     * CP账号id
     */
    private Integer accountId;
    /**
     * 计费模式
     */
    private Integer feeMode;
    /**
     * 售价
     */
    private Integer price;
    /**
     * CP分成比例1
     */
    private Integer percent;
    /**
     * 分成期限1开始
     */
    private Date period1Begin;
    /**
     * 分成期限1结束
     */
    private Date period1End;
    /**
     * CP分成比例2
     */
    private Integer percent2;
    /**
     * 分成期限2开始
     */
    private Date period2Begin;
    /**
     * 分成期限2结束
     */
    private Date period2End;
    /**
     * CP分成比例3
     */
    private Integer percent3;
    /**
     * 分成期限3开始
     */
    private Date period3Begin;
    /**
     * 分成期限3结束
     */
    private Date period3End;
    /**
     * 预付金额
     */
    private Integer prepay;
    /**
     * 买断金额
     */
    private Integer buyout;
    /**
     * 买断期限开始
     */
    private Date buyoutBegin;
    /**
     * 买断期限结束
     */
    private Date buyoutEnd;
    /**
     * 应用包版本号
     */
    private String appVersion;
    /**
     * 主图相对URL
     */
    private String logoUrl;
    /**
     * 适配的最低固件版本
     */
    private Integer osVersionId;
    /**
     * 安装包尺寸
     */
    private Integer pkgSize;
    /**
     * 详情上下架状态
     */
    private Integer online;
    /**
     * 详情更新状态
     */
    private Integer updateStatus;
    /**
     * 发布时间
     */
    private Date pubTime;
    /**
     * 广告语
     */
    private String adText;
    /**
     * 标签
     */
    private String appTag;
    /**
     * 新增功能介绍
     */
    private String newFeatures;
    /**
     * 应用介绍
     */
    private String appIntroduce;
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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getFeeMode() {
        return feeMode;
    }

    public void setFeeMode(Integer feeMode) {
        this.feeMode = feeMode;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Date getPeriod1Begin() {
        return period1Begin;
    }

    public void setPeriod1Begin(Date period1Begin) {
        this.period1Begin = period1Begin;
    }

    public Date getPeriod1End() {
        return period1End;
    }

    public void setPeriod1End(Date period1End) {
        this.period1End = period1End;
    }

    public Integer getPercent2() {
        return percent2;
    }

    public void setPercent2(Integer percent2) {
        this.percent2 = percent2;
    }

    public Date getPeriod2Begin() {
        return period2Begin;
    }

    public void setPeriod2Begin(Date period2Begin) {
        this.period2Begin = period2Begin;
    }

    public Date getPeriod2End() {
        return period2End;
    }

    public void setPeriod2End(Date period2End) {
        this.period2End = period2End;
    }

    public Integer getPercent3() {
        return percent3;
    }

    public void setPercent3(Integer percent3) {
        this.percent3 = percent3;
    }

    public Date getPeriod3Begin() {
        return period3Begin;
    }

    public void setPeriod3Begin(Date period3Begin) {
        this.period3Begin = period3Begin;
    }

    public Date getPeriod3End() {
        return period3End;
    }

    public void setPeriod3End(Date period3End) {
        this.period3End = period3End;
    }

    public Integer getPrepay() {
        return prepay;
    }

    public void setPrepay(Integer prepay) {
        this.prepay = prepay;
    }

    public Integer getBuyout() {
        return buyout;
    }

    public void setBuyout(Integer buyout) {
        this.buyout = buyout;
    }

    public Date getBuyoutBegin() {
        return buyoutBegin;
    }

    public void setBuyoutBegin(Date buyoutBegin) {
        this.buyoutBegin = buyoutBegin;
    }

    public Date getBuyoutEnd() {
        return buyoutEnd;
    }

    public void setBuyoutEnd(Date buyoutEnd) {
        this.buyoutEnd = buyoutEnd;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getOsVersionId() {
        return osVersionId;
    }

    public void setOsVersionId(Integer osVersionId) {
        this.osVersionId = osVersionId;
    }

    public Integer getPkgSize() {
        return pkgSize;
    }

    public void setPkgSize(Integer pkgSize) {
        this.pkgSize = pkgSize;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getAdText() {
        return adText;
    }

    public void setAdText(String adText) {
        this.adText = adText;
    }

    public String getAppTag() {
        return appTag;
    }

    public void setAppTag(String appTag) {
        this.appTag = appTag;
    }

    public String getNewFeatures() {
        return newFeatures;
    }

    public void setNewFeatures(String newFeatures) {
        this.newFeatures = newFeatures;
    }

    public String getAppIntroduce() {
        return appIntroduce;
    }

    public void setAppIntroduce(String appIntroduce) {
        this.appIntroduce = appIntroduce;
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
