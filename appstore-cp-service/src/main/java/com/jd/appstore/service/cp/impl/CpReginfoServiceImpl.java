package com.jd.appstore.service.cp.impl;

import com.jd.appstore.domain.CpBaseinfo;
import com.jd.appstore.domain.CpReginfo;
import com.jd.appstore.manager.cp.CpBaseinfoManager;
import com.jd.appstore.manager.cp.CpReginfoManager;
import com.jd.appstore.service.cp.CpReginfoService;
import com.jd.common.web.result.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-9
 * Time: 上午11:05
 * To change this template use File | Settings | File Templates.
 */
public class CpReginfoServiceImpl implements CpReginfoService {

    private final static Log log = LogFactory.getLog(CpReginfoServiceImpl.class);

    private CpReginfoManager cpReginfoManager;

    public CpReginfoManager getCpReginfoManager() {
        return cpReginfoManager;
    }

    public void setCpReginfoManager(CpReginfoManager cpReginfoManager) {
        this.cpReginfoManager = cpReginfoManager;
    }

    public Result createCpReginfo(CpReginfo cpReginfo) {

        Result result = new Result();
        try {
            int cpReginfoId = cpReginfoManager.createReginfo(cpReginfo);
            result.addDefaultModel("cpReginfoId", cpReginfoId);
            result.addDefaultModel("cpBaseinfo", cpReginfo);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("createExample error!", e);
            result.setResultCode("system.error");
        }
        return result;
    }

}
