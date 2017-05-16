package com.jd.appstore.domain.web;

import com.jd.appstore.domain.AppResource;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-20
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public class CheckApkResult {
    private Integer id;
    private Integer accountId;
    private Integer appId;
    private Integer categoryId_l2;
    private String categoryId_l2Name;
    private Integer categoryId1;
    private String categoryId1Name;
    private String appName;
    private Integer appType;
    private String logoUrl;
    private String appVersion;
    private Integer online;
    private Integer updateStatus;
    private String appIntroduce;
    private Integer price;
    private String appTag;
    private Date created;
    private Integer star;
    private Integer goodRate;
    private Integer downloadCount;
    private Integer tab;
    private String pkg;
    private Integer feeMode;
    private Integer valid;
    private List<AppResource> appResources;
    private AppResource appRes;
    private Integer pkgSize;
    private Integer downloadTimes;
    private String appVersionCode;
    private Integer isAppLogo;
    private Integer osVersionId;
    private AppResource appResource;
    private String comment;
    private String apkCode;


    public String getApkCode() {
        return apkCode;
    }

    public void setApkCode(String apkCode) {
        this.apkCode = apkCode;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AppResource getAppResource() {
        return appResource;
    }

    public void setAppResource(AppResource appResource) {
        this.appResource = appResource;
    }

    public Integer getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(Integer downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }


    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public List<AppResource> getAppResources() {
        return appResources;
    }

    public void setAppResources(List<AppResource> appResources) {
        this.appResources = appResources;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAppTag() {
        return appTag;
    }

    public void setAppTag(String appTag) {
        this.appTag = appTag;
    }

    public String getAppIntroduce() {
        return appIntroduce;
    }

    public void setAppIntroduce(String appIntroduce) {
        this.appIntroduce = appIntroduce;
    }

    public Integer getCategoryId_l2() {
        return categoryId_l2;
    }

    public void setCategoryId_l2(Integer categoryId_l2) {
        this.categoryId_l2 = categoryId_l2;
    }

    public String getCategoryId_l2Name() {
        return categoryId_l2Name;
    }

    public void setCategoryId_l2Name(String categoryId_l2Name) {
        this.categoryId_l2Name = categoryId_l2Name;
    }

    public String getCategoryId1Name() {
        return categoryId1Name;
    }

    public void setCategoryId1Name(String categoryId1Name) {
        this.categoryId1Name = categoryId1Name;
    }

    public Integer getCategoryId1() {
        return categoryId1;
    }

    public void setCategoryId1(Integer categoryId1) {
        this.categoryId1 = categoryId1;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getGoodRate() {
        return goodRate;
    }

    public void setGoodRate(Integer goodRate) {
        this.goodRate = goodRate;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getTab() {
        return tab;
    }

    public void setTab(Integer tab) {
        this.tab = tab;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public Integer getFeeMode() {
        return feeMode;
    }

    public void setFeeMode(Integer feeMode) {
        this.feeMode = feeMode;
    }


    public AppResource getAppRes() {
        return appRes;
    }

    public void setAppRes(AppResource appRes) {
        this.appRes = appRes;
    }

    public Integer getPkgSize() {
        return pkgSize;
    }

    public void setPkgSize(Integer pkgSize) {
        this.pkgSize = pkgSize;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public Integer getAppLogo() {
        return isAppLogo;
    }

    public void setAppLogo(Integer appLogo) {
        isAppLogo = appLogo;
    }

    public Integer getOsVersionId() {
        return osVersionId;
    }

    public void setOsVersionId(Integer osVersionId) {
        this.osVersionId = osVersionId;
    }
}
