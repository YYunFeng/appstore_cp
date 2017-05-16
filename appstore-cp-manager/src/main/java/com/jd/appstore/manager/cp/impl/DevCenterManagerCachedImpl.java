package com.jd.appstore.manager.cp.impl;

import com.jd.appstore.domain.*;
import com.jd.appstore.domain.constant.PagingConstants;
import com.jd.appstore.domain.json.AuthApkJSON;
import com.jd.appstore.domain.paramter.CheckApk;
import com.jd.appstore.domain.paramter.StatApks;
import com.jd.appstore.domain.web.CheckApkResult;
import com.jd.appstore.domain.web.UserResult;
import com.jd.appstore.manager.cp.DevCenterManager;
import com.jd.common.manager.BaseManager;
import com.jd.common.util.PaginatedList;
import com.jd.digital.common.util.cache.CacheUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-30
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class DevCenterManagerCachedImpl extends BaseManager implements DevCenterManager {
    private CacheUtils cacheUtils;
    private DevCenterManager devCenterManager;
    private final Log log = LogFactory.getLog(DevCenterManagerCachedImpl.class);


    public List<Category> getCategory(int id) {
        return devCenterManager.getCategory(id);
    }

    public PaginatedList<CheckApkResult> findApks(CheckApk checkApk, int pageIndex, int pageSize) {
        PaginatedList<CheckApkResult> apks;
        if (0 == pageIndex) {
            Integer pager = (Integer) cacheUtils.get(PagingConstants.CP_APPDETAILS_PAGER_CACHED);
            if (null != pager) {
                pageIndex = pager;
            }
        }
        apks = devCenterManager.findApks(checkApk, pageIndex, pageSize);
        if (0 == apks.size()) {
            if (1 < pageIndex) {
                pageIndex = pageIndex - 1;
                cacheUtils.set(PagingConstants.CP_APPDETAILS_PAGER_CACHED, PagingConstants.CP_EXPIRE_TIME, pageIndex);
                apks=findApks(checkApk, pageIndex, pageSize);
            }
        }
        cacheUtils.set(PagingConstants.CP_APPDETAILS_PAGER_CACHED, PagingConstants.CP_EXPIRE_TIME, pageIndex);
        return apks;
    }

    public StatApks statApks(Apps apps) {
        return devCenterManager.statApks(apps);
    }

    public PaginatedList<CheckApkResult> ApkVersity(CheckApk checkApk, int pageIndex, int pageSize) {
        PaginatedList<CheckApkResult> apks;
        if (0 == pageIndex) {
            Integer pager = (Integer) cacheUtils.get(PagingConstants.CP_APPDETAIL_LOG_PAGER_CACHED);
            if (null != pager) {
                pageIndex = pager;
            }
        }
        apks = devCenterManager.ApkVersity(checkApk, pageIndex, pageSize);
        if (0 == apks.size()) {
            if (1 < pageIndex) {
                pageIndex = pageIndex - 1;
                cacheUtils.set(PagingConstants.CP_APPDETAILS_PAGER_CACHED, PagingConstants.CP_EXPIRE_TIME, pageIndex);
                apks=ApkVersity(checkApk, pageIndex, pageSize);
            }
        }
        cacheUtils.set(PagingConstants.CP_APPDETAILS_PAGER_CACHED, PagingConstants.CP_EXPIRE_TIME, pageIndex);
        return apks;
    }

    public CheckApkResult checkApkByAppIdFromLog(CheckApk checkApk) {
        return devCenterManager.checkApkByAppIdFromLog(checkApk);
    }

    public CheckApkResult checkApkByAppId(CheckApk checkApk) {
        return devCenterManager.checkApkByAppId(checkApk);
    }

    public UserResult personMess(int accountId) {
        return devCenterManager.personMess(accountId);
    }

    public int updateCpBaseinfo(CpBaseinfo cpBaseinfo) {
        return devCenterManager.updateCpBaseinfo(cpBaseinfo);
    }

    public int updateCpReginfo(CpReginfo cpReginfo) {
        return devCenterManager.updateCpReginfo(cpReginfo);
    }

    public AuthApkJSON authApk(Apps apps) {
        return devCenterManager.authApk(apps);
    }

    public int updateApp(AppLog appLog, AppDetailLog appDetailLog,AppResource appResource) {
        return devCenterManager.updateApp(appLog,appDetailLog,appResource);
    }

    public int updateWeb(AppLog appLog, AppDetailLog appDetailLog, AppResource appResource) {
        return devCenterManager.updateWeb(appLog,appDetailLog,appResource);
    }

    public boolean ismodifyApk(CheckApk checkApk) {
        return devCenterManager.ismodifyApk(checkApk);
    }

    public Integer verifyButUploading(Apps apps) {
        return devCenterManager.verifyButUploading(apps);
    }

    public CacheUtils getCacheUtils() {
        return cacheUtils;
    }

    public void setCacheUtils(CacheUtils cacheUtils) {
        this.cacheUtils = cacheUtils;
    }

    public void setDevCenterManager(DevCenterManager devCenterManager) {
        this.devCenterManager = devCenterManager;
    }

    public int createApk(AppLog appsLog, AppDetailLog appDetailLog, AppResource appResource) {
        return devCenterManager.createApk(appsLog, appDetailLog, appResource);
    }

    public int createWeb(AppLog appsLog, AppDetailLog appDetailLog, AppResource appResource) {
        return devCenterManager.createWeb(appsLog,appDetailLog,appResource);
    }
}
