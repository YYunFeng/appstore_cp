package com.jd.appstore.manager.cp.impl;

import com.jd.appstore.dao.cp.DataCenterDao;
import com.jd.appstore.domain.paramter.CountAppParameter;
import com.jd.appstore.domain.paramter.StatAppDown;
import com.jd.appstore.domain.paramter.StatusDownload;
import com.jd.appstore.domain.web.CountAppResult;
import com.jd.appstore.domain.web.StatAppReuslt;
import com.jd.appstore.domain.web.DownStatus;
import com.jd.appstore.manager.cp.DataCenterManager;
import com.jd.common.manager.BaseManager;
import com.jd.common.util.PaginatedList;
import com.jd.common.util.base.PaginatedArrayList;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-24
 * Time: 下午6:10
 * To change this template use File | Settings | File Templates.
 */
public class DataCenterManagerImpl extends BaseManager implements DataCenterManager {
    private DataCenterDao dataCenterDao;

    public PaginatedList<DownStatus> downStauts(StatusDownload statusDownload, int pageIndex, int pageSize) {
        if (0 == pageIndex) pageIndex = 1;
        PaginatedList<DownStatus> apks = new PaginatedArrayList<DownStatus>(pageIndex, pageSize);
        try {
            int totalItem = dataCenterDao.downStautsCounts(statusDownload);
            apks.setTotalItem(totalItem);
            statusDownload.setStartRow((pageIndex - 1) * pageSize);
            statusDownload.setEndRow(pageSize);
            List<DownStatus> apk = dataCenterDao.downStauts(statusDownload);
            apks.addAll(apk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return apks;
    }

    public List<StatAppReuslt> statAppReuslt(StatAppDown statAppDown) {
        List<StatAppReuslt> statAppReuslts = dataCenterDao.statAppReuslt(statAppDown);
        for (StatAppReuslt stat : statAppReuslts) {
            stat.setCount(stat.getCount() == null ? 0 : stat.getCount());
        }
        return statAppReuslts;
    }

    public PaginatedList<CountAppResult> getCountApps(CountAppParameter countAppParameter, int pageIndex, int pageSize) {
        if (0 == pageIndex) pageIndex = 1;
        PaginatedList<CountAppResult> countAppResults = new PaginatedArrayList<CountAppResult>(pageIndex, pageSize);
        try {
            int totalItem = dataCenterDao.getCountAppsCounts(countAppParameter);
            if (totalItem > 0) {
                if (countAppParameter.getFlag() != null && countAppParameter.getFlag().intValue() == 0) {
                    countAppResults.setTotalItem(totalItem);
                    countAppParameter.setStartRow((pageIndex - 1) * pageSize);
                    countAppParameter.setEndRow(pageSize);
                }
                List<CountAppResult> installAppResultList = dataCenterDao.getInstallApps(countAppParameter);
                /*List<CountAppResult> countAppResultList = dataCenterDao.getCountApps(countAppParameter);
                for (CountAppResult countAppResult : installAppResultList) {
                    for (CountAppResult countAppResult1 : countAppResultList) {
                        if (countAppResult.getAppid() != countAppResult1.getAppid() && countAppResult.getAppid().equals(countAppResult1.getAppid())) {
                            countAppResult.setCountApps(countAppResult1.getCountApps());
                        }
                    }
                }*/
                countAppResults.addAll(installAppResultList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countAppResults;
    }

    public PaginatedList<CountAppResult> countAppsPhoneImei(CountAppParameter countAppParameter, int pageIndex, int pageSize) {
        if (0 == pageIndex) pageIndex = 1;
        PaginatedList<CountAppResult> countAppResults = new PaginatedArrayList<CountAppResult>(pageIndex, pageSize);
        try {
            int totalItem = dataCenterDao.countAppsPhoneImeiCounts(countAppParameter);
            if (totalItem > 0) {
                if (countAppParameter.getFlag() != null && countAppParameter.getFlag().intValue() == 0) {
                    countAppResults.setTotalItem(totalItem);
                    countAppParameter.setStartRow((pageIndex - 1) * pageSize);
                    countAppParameter.setEndRow(pageSize);
                }
                List<CountAppResult> countAppResultList = dataCenterDao.countAppsPhoneImei(countAppParameter);
                countAppResults.addAll(countAppResultList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countAppResults;
    }

    public PaginatedList<CountAppResult> getPhoneImeiFromInstall(CountAppParameter countAppParameter, int pageIndex, int pageSize) {
        if (0 == pageIndex) pageIndex = 1;
        PaginatedList<CountAppResult> countAppResults = new PaginatedArrayList<CountAppResult>(pageIndex, pageSize);
        try {
            int totalItem = dataCenterDao.getPhoneImeiFromInstallCounts(countAppParameter);
            if (totalItem > 0) {
                if (countAppParameter.getFlag() != null && countAppParameter.getFlag().intValue() == 0) {
                    countAppResults.setTotalItem(totalItem);
                    countAppParameter.setStartRow((pageIndex - 1) * pageSize);
                    countAppParameter.setEndRow(pageSize);
                }
                List<CountAppResult> countAppResultList = dataCenterDao.getPhoneImeiFromInstall(countAppParameter);
                countAppResults.addAll(countAppResultList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countAppResults;
    }

    public void setDataCenterDao(DataCenterDao dataCenterDao) {
        this.dataCenterDao = dataCenterDao;
    }
}
