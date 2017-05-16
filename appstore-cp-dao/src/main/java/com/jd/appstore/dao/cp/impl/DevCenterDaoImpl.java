package com.jd.appstore.dao.cp.impl;

import com.jd.appstore.dao.cp.DevCenterDao;
import com.jd.appstore.domain.*;
import com.jd.appstore.domain.paramter.CheckApk;
import com.jd.appstore.domain.paramter.StatApks;
import com.jd.appstore.domain.web.CheckApkResult;
import com.jd.appstore.domain.web.UserResult;
import com.jd.common.dao.BaseDao;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-15
 * Time: 下午4:48
 * To change this template use File | Settings | File Templates.
 */
public class DevCenterDaoImpl extends BaseDao implements DevCenterDao {

    public void insertApps(Apps apps) {
        insert("Apps.createApps", apps);
    }

    public void insertAppDetails(AppDetails appDetails) {
        insert("AppDetails.createAppDetails", appDetails);
    }

    public void insertAppDetailLog(AppDetailLog appDetailLog) {
        insert("AppDetailLog.createAppDetailLog", appDetailLog);
    }

    public void insertAppLog(AppLog appLog) {
        insert("AppLog.createAppLog", appLog);
    }

    public void insertAppResource(AppResource appResource) {
        insert("AppResource.createAppResource", appResource);
    }

    public List<AppDetailLog> findApkOnVersity(AppDetailLog appDetailLog) {
        return queryForList("DevCenter.findApkOnVersity", appDetailLog);
    }

    public List<Category> findCategory(int id) {
        return queryForList("Category.getCategory", id);
    }

    public int findApkCounts(CheckApk checkApk) {
        return (Integer) queryForObject("DevCenter.findApkCounts", checkApk);
    }

    public List<CheckApkResult> findApks(CheckApk checkApk) {
        return queryForList("DevCenter.findApks", checkApk);
    }

    public int findApkVersityCounts(CheckApk checkApk) {
        return (Integer) queryForObject("DevCenter.findApkVersityCount", checkApk);
    }

    public List<CheckApkResult> ApkVersity(CheckApk checkApk) {
        return queryForList("DevCenter.findApkVersity", checkApk);
    }

    public List<StatApks> statApkByAppDetailsLog(Apps apps) {
        return queryForList("DevCenter.statApkByAppDetailsLog", apps);
    }

    public List<StatApks> statApkByAppDetail(Apps apps) {
        return queryForList("DevCenter.statApkByAppDetails", apps);
    }

    public CheckApkResult checkApkByAppIdFromLog(CheckApk checkApk) {
        return (CheckApkResult) queryForObject("DevCenter.CheckApkByAppIdFormLog", checkApk);
    }

    public CheckApkResult checkApkByAppId(CheckApk checkApk) {
        return (CheckApkResult) queryForObject("DevCenter.CheckApkByAppId", checkApk);
    }

    public List<AppResource> getAppResource(CheckApk checkApk) {
        return queryForList("DevCenter.getAppResource", checkApk);
    }

    public UserResult personMess(int accountId) {
        return (UserResult) queryForObject("DevCenter.personMess", accountId);
    }

    public int updateCpBaseinfo(CpBaseinfo cpBaseinfo) {
        return update("DevCenter.updateBaseinfo", cpBaseinfo);
    }

    public int updateCpReginfo(CpReginfo cpReginfo) {
        return update("DevCenter.updatereginfo", cpReginfo);
    }

    public Apps authApk(Apps apps) {
        return (Apps) queryForObject("Apps.authApk", apps);
    }

    public String getCategoryName(int categoryId) {
        return (String) queryForObject("Category.getCategoryName", categoryId);
    }

    public List<AppDetails> getApkDetails(int appid) {
        return queryForList("Apps.getApkDetails", appid);
    }

    public List<AppDetailLog> getApkDetailLog(int appid) {
        return queryForList("Apps.getApkDetailLog", appid);
    }

    public List<Integer> verifyButUploading(Apps apps) {
        return queryForList("Apps.verifyButUploading", apps);
    }

    public int updateAppDetials(AppDetails appDetails) {
        return update("AppDetails.updateAppDetails", appDetails);
    }

    public int updateAppResource(AppResource appResource) {
        return update("AppResource.updateAppResource", appResource);
    }

    public int updateAppLog(int appId) {
        return update("AppLog.updateAppLog", appId);
    }

    public AppLog findAppLogByAppId(int appId) {
        return (AppLog) queryForObject("AppLog.findAppByAppId", appId);
    }

    public Apps findAppsByAppId(int appId) {
        return (Apps) queryForObject("Apps.findAppsByAppId", appId);
    }

    public AppDetailLog findAppDetailLog(AppDetailLog appDetailLog) {
        return (AppDetailLog) queryForObject("AppDetailLog.findAppDetailsLog", appDetailLog);
    }


    public int updateAppDetailLog(AppDetailLog appDetailLog) {
        return update("AppDetailLog.updateDetailsLog", appDetailLog);
    }

    public int deteleAppres(AppResource appResource) {
        return delete("AppResource.deteteAppRes", appResource);
    }

    public void insertBackendTask(BackendTask backendTask) {
        insert("BackendTask.insertBackendTask", backendTask);
    }

    public boolean isModifyApk(CheckApk checkApk) {
        Integer count = (Integer) queryForObject("DevCenter.isModifyApk", checkApk);
        return count > 0;
    }

    // 验证cp修改应用的时候不是自己的应用 从tb_apps_detailslog表
    public boolean authCpFromDetailLog(AppDetailLog appDetailLog) {
        Integer count = (Integer) queryForObject("AppDetailLog.authCp", appDetailLog);
        return count > 0;
    }

    // 验证cp修改应用的时候不是自己的应用 从tb_apps_detailslog表
    public boolean authCpFromDetails(AppDetails appDetails) {
        Integer count = (Integer) queryForObject("AppDetails.authCp", appDetails);
        return count > 0;
    }
}
