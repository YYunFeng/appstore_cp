package com.jd.appstore.manager.cp;

import com.jd.appstore.domain.paramter.CountAppParameter;
import com.jd.appstore.domain.paramter.StatAppDown;
import com.jd.appstore.domain.paramter.StatusDownload;
import com.jd.appstore.domain.web.CountAppResult;
import com.jd.appstore.domain.web.StatAppReuslt;
import com.jd.appstore.domain.web.DownStatus;
import com.jd.common.util.PaginatedList;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-24
 * Time: 下午6:08
 * To change this template use File | Settings | File Templates.
 */
public interface DataCenterManager {
    PaginatedList<DownStatus> downStauts(StatusDownload statusDownload, int pageIndex, int pageSize);

    List<StatAppReuslt> statAppReuslt(StatAppDown statAppDown);


    PaginatedList<CountAppResult> getCountApps(CountAppParameter countAppParameter, int pageIndex, int pageSize);

    PaginatedList<CountAppResult> countAppsPhoneImei(CountAppParameter countAppParameter, int pageIndex, int pageSize);

    PaginatedList<CountAppResult> getPhoneImeiFromInstall(CountAppParameter countAppParameter, int pageIndex, int pageSize);

}
