package com.jd.appstore.manager.cp.impl;

import com.jd.appstore.domain.constant.PagingConstants;
import com.jd.appstore.domain.paramter.CountAppParameter;
import com.jd.appstore.domain.paramter.StatAppDown;
import com.jd.appstore.domain.paramter.StatusDownload;
import com.jd.appstore.domain.web.CountAppResult;
import com.jd.appstore.domain.web.DownStatus;
import com.jd.appstore.domain.web.StatAppReuslt;
import com.jd.appstore.manager.cp.DataCenterManager;
import com.jd.common.manager.BaseManager;
import com.jd.common.util.PaginatedList;
import com.jd.digital.common.util.cache.CacheUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-24
 * Time: 下午6:16
 * To change this template use File | Settings | File Templates.
 */
public class DataCenterManagerCachedImpl extends BaseManager implements DataCenterManager {
    private CacheUtils cacheUtils;
    private DataCenterManager dataCenterManager;

    public PaginatedList<DownStatus> downStauts(StatusDownload statusDownload, int pageIndex, int pageSize) {
        PaginatedList<DownStatus> apks;
        if (0 == pageIndex) {
            Integer pager = (Integer) cacheUtils.get(PagingConstants.CP_STATUS_APP_DOWNLOAD);
            if (null != pager) {
                pageIndex = pager;
            }
        }
        apks = dataCenterManager.downStauts(statusDownload, pageIndex, pageSize);
        if (0 == apks.size()) {
            if (1 < pageIndex) {
                pageIndex = pageIndex - 1;
                cacheUtils.set(PagingConstants.CP_STATUS_APP_DOWNLOAD, PagingConstants.CP_EXPIRE_TIME, pageIndex);
            }
        }
        cacheUtils.set(PagingConstants.CP_STATUS_APP_DOWNLOAD, PagingConstants.CP_EXPIRE_TIME, pageIndex);
        return apks;
    }

    public List<StatAppReuslt> statAppReuslt(StatAppDown statAppDown) {
        return dataCenterManager.statAppReuslt(statAppDown);
    }

    public PaginatedList<CountAppResult> getCountApps(CountAppParameter countAppParameter, int pageIndex, int pageSize) {
        return dataCenterManager.getCountApps(countAppParameter, pageIndex, pageSize);
    }

    public PaginatedList<CountAppResult> countAppsPhoneImei(CountAppParameter countAppParameter, int pageIndex, int pageSize) {
        return dataCenterManager.countAppsPhoneImei(countAppParameter, pageIndex, pageSize);
    }

    public PaginatedList<CountAppResult> getPhoneImeiFromInstall(CountAppParameter countAppParameter, int pageIndex, int pageSize) {
        return dataCenterManager.getPhoneImeiFromInstall(countAppParameter, pageIndex, pageSize);
    }

    public void setCacheUtils(CacheUtils cacheUtils) {
        this.cacheUtils = cacheUtils;
    }

    public void setDataCenterManager(DataCenterManager dataCenterManager) {
        this.dataCenterManager = dataCenterManager;
    }
}
