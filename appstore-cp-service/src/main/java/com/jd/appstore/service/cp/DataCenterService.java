package com.jd.appstore.service.cp;

import com.jd.appstore.domain.json.ExportExcelJSON;
import com.jd.appstore.domain.paramter.CountAppParameter;
import com.jd.appstore.domain.paramter.StatAppDown;
import com.jd.appstore.domain.paramter.StatusDownload;
import com.jd.common.web.result.Result;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-24
 * Time: 下午6:06
 * To change this template use File | Settings | File Templates.
 */
public interface DataCenterService {
    Result downStauts(StatusDownload statusDownload, int pageIndex, int pageSize);

    Result statAppReuslt(StatAppDown statAppDown);

    Result getCountApps(CountAppParameter countAppParameter, int pageIndex, int pageSize);


    Result countAppsPhoneImei(CountAppParameter countAppParameter, int pageIndex, int pageSize);

    Result getPhoneImeiFromInstall(CountAppParameter countAppParameter, int pageIndex, int pageSize);

    ExportExcelJSON exportExcel(CountAppParameter countAppParameter,Integer flag);
}
