package com.jd.appstore.dao.cp.impl;

import com.jd.appstore.dao.cp.DataCenterDao;
import com.jd.appstore.domain.paramter.CountAppParameter;
import com.jd.appstore.domain.paramter.StatAppDown;
import com.jd.appstore.domain.paramter.StatusDownload;
import com.jd.appstore.domain.web.CountAppResult;
import com.jd.appstore.domain.web.StatAppReuslt;
import com.jd.appstore.domain.web.DownStatus;
import com.jd.common.dao.BaseDao;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-24
 * Time: 下午6:04
 * To change this template use File | Settings | File Templates.
 */
public class DataCenterDaoImpl extends BaseDao implements DataCenterDao {

    public List<DownStatus> downStauts(StatusDownload statusDownload) {
        return queryForList("DataCenter.statApp", statusDownload);
    }

    public List<StatAppReuslt> statAppReuslt(StatAppDown statAppDown) {
        return queryForList("DataCenter.statsAppByTime", statAppDown);
    }

    public int downStautsCounts(StatusDownload statusDownload) {
        return (Integer) queryForObject("DataCenter.statAppCounts", statusDownload);
    }

    public List<CountAppResult> getCountApps(CountAppParameter countAppParameter) {
        return queryForList("DataCenter.getCountApps", countAppParameter);
    }

    public List<CountAppResult> getInstallApps(CountAppParameter countAppParameter) {
        return queryForList("DataCenter.getInstallApps", countAppParameter);
    }

    public int getCountAppsCounts(CountAppParameter countAppParameter) {
        return (Integer) queryForObject("DataCenter.getCountAppsCounts", countAppParameter);
    }

    public List<CountAppResult> countAppsPhoneImei(CountAppParameter countAppParameter) {
        return queryForList("DataCenter.countAppsPhoneImei", countAppParameter);
    }

    public int countAppsPhoneImeiCounts(CountAppParameter countAppParameter) {
        return (Integer) queryForObject("DataCenter.countAppsPhoneImeiCounts", countAppParameter);
    }

    public List<CountAppResult> getPhoneImeiFromInstall(CountAppParameter countAppParameter) {
        return queryForList("DataCenter.getPhoneImeiFromInstall", countAppParameter);
    }

    public int getPhoneImeiFromInstallCounts(CountAppParameter countAppParameter) {
        return (Integer) queryForObject("DataCenter.getPhoneImeiFromInstallCounts", countAppParameter);
    }
}
