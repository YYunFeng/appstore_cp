package com.jd.appstore.dao.cp;

import com.jd.appstore.domain.paramter.CountAppParameter;
import com.jd.appstore.domain.paramter.StatAppDown;
import com.jd.appstore.domain.paramter.StatusDownload;
import com.jd.appstore.domain.web.CountAppResult;
import com.jd.appstore.domain.web.StatAppReuslt;
import com.jd.appstore.domain.web.DownStatus;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-24
 * Time: 下午6:03
 * To change this template use File | Settings | File Templates.
 */
public interface DataCenterDao {
    /**
     * 统计CP的每个应用的下载数量
     *
     * @param statusDownload
     * @return
     */
    List<DownStatus> downStauts(StatusDownload statusDownload);

    List<StatAppReuslt> statAppReuslt(StatAppDown statAppDown);

    int downStautsCounts(StatusDownload statusDownload);

    /**
     * 获取到达数
     *
     * @param countAppParameter
     * @return
     */
    List<CountAppResult> getCountApps(CountAppParameter countAppParameter);

    /**
     * 获得安装数
     *
     * @param countAppParameter
     * @return
     */
    List<CountAppResult> getInstallApps(CountAppParameter countAppParameter);


    int getCountAppsCounts(CountAppParameter countAppParameter);


    List<CountAppResult> countAppsPhoneImei(CountAppParameter countAppParameter);


    int countAppsPhoneImeiCounts(CountAppParameter countAppParameter);


    List<CountAppResult> getPhoneImeiFromInstall(CountAppParameter countAppParameter);


    int getPhoneImeiFromInstallCounts(CountAppParameter countAppParameter);
}
