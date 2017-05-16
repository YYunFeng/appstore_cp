package com.jd.appstore.web.action.cp;

import com.jd.appstore.action.AppStoreBaseAction;
import com.jd.appstore.domain.json.ExportExcelJSON;
import com.jd.appstore.domain.paramter.CountAppParameter;
import com.jd.appstore.domain.paramter.StatAppDown;
import com.jd.appstore.domain.paramter.StatusDownload;
import com.jd.appstore.service.cp.CpBaseinfoService;
import com.jd.appstore.service.cp.DataCenterService;
import com.jd.common.web.result.Result;
import com.jd.digital.common.util.tool.WebHelper;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import java.io.IOException;


/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-24
 * Time: 上午11:51
 * To change this template use File | Settings | File Templates.
 */
public class DataCenterAction extends AppStoreBaseAction {
    private StatusDownload statusDownload;
    private DataCenterService dataCenterService;
    private StatAppDown statAppDown;
    private CpBaseinfoService cpBaseinfoService;
    private CountAppParameter countAppParameter;
    private Integer flag;

    /**
     * 数据中心
     *
     * @return
     */
    public String dataCenter() {
        return SUCCESS;
    }

    /**
     * 计数器到达数据
     *
     * @return
     */
    public String countApp() {
        try {
            String pin = WebHelper.getPin();
            if (countAppParameter != null) {
                countAppParameter.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
            } else {
                countAppParameter = new CountAppParameter();
                countAppParameter.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
            }
            Result result = dataCenterService.getCountApps(countAppParameter, page, PAGE_SIZE);
            toVm(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }


    public String countAppsPhoneImei() {
        try {
            Result result = dataCenterService.countAppsPhoneImei(countAppParameter, page, PAGE_SIZE);
            toVm(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String getPhoneImeiFromInstall() {
        try {
            Result result = dataCenterService.getPhoneImeiFromInstall(countAppParameter, page, PAGE_SIZE);
            toVm(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public void exportExcel() throws JSONException, IOException {
        String pin = WebHelper.getPin();
        if (countAppParameter != null) {
            countAppParameter.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
        } else {
            countAppParameter = new CountAppParameter();
            countAppParameter.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
        }
        ExportExcelJSON exportExcelJSON = dataCenterService.exportExcel(countAppParameter, flag);
        String json = JSONUtil.serialize(exportExcelJSON);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
    }

    /**
     * 总的列表统计
     *
     * @return
     */
    public String stautAppCheck() {
        statusDownload = new StatusDownload();
        String pin = WebHelper.getPin();
        statusDownload.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
        Result result = dataCenterService.downStauts(statusDownload, page, PAGE_SIZE);
        toVm(result);
        return SUCCESS;
    }

    /**
     * 按时间段获取某个应用的日下载量
     *
     * @return
     */
    public String statAppByTime() {
        Result result = dataCenterService.statAppReuslt(statAppDown);
        toVm(result);
        return SUCCESS;
    }


    public void setDataCenterService(DataCenterService dataCenterService) {
        this.dataCenterService = dataCenterService;
    }

    public StatusDownload getStatusDownload() {
        return statusDownload;
    }

    public void setStatusDownload(StatusDownload statusDownload) {
        this.statusDownload = statusDownload;
    }

    public StatAppDown getStatAppDown() {
        return statAppDown;
    }

    public void setStatAppDown(StatAppDown statAppDown) {
        this.statAppDown = statAppDown;
    }

    public CpBaseinfoService getCpBaseinfoService() {
        return cpBaseinfoService;
    }

    public void setCpBaseinfoService(CpBaseinfoService cpBaseinfoService) {
        this.cpBaseinfoService = cpBaseinfoService;
    }


    public CountAppParameter getCountAppParameter() {
        return countAppParameter;
    }

    public void setCountAppParameter(CountAppParameter countAppParameter) {
        this.countAppParameter = countAppParameter;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
