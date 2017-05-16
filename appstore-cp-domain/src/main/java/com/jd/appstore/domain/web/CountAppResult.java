package com.jd.appstore.domain.web;

/**
 * Created by YUNFENG on 14-2-11.
 */
public class CountAppResult {
    private String appid;
    private String appName;
    private Integer installCounts;
    private Integer countApps = 0;
    private String phoneImei;
    private String installTime;
    private String phoneModelName;

    public String getInstallTime() {
        return installTime;
    }

    public void setInstallTime(String installTime) {
        this.installTime = installTime;
    }

    public String getPhoneModelName() {
        return phoneModelName;
    }

    public void setPhoneModelName(String phoneModelName) {
        this.phoneModelName = phoneModelName;
    }

    public String getPhoneImei() {
        return phoneImei;
    }

    public void setPhoneImei(String phoneImei) {
        this.phoneImei = phoneImei;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getInstallCounts() {
        return installCounts;
    }

    public void setInstallCounts(Integer installCounts) {
        this.installCounts = installCounts;
    }

    public Integer getCountApps() {
        return countApps;
    }

    public void setCountApps(Integer countApps) {
        this.countApps = countApps;
    }
}
