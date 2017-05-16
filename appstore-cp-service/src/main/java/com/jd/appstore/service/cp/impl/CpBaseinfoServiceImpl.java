package com.jd.appstore.service.cp.impl;

import com.jd.appstore.domain.CpBaseinfo;
import com.jd.appstore.manager.cp.CpBaseinfoManager;
import com.jd.appstore.service.cp.CpBaseinfoService;
import com.jd.common.web.result.Result;
import com.jd.digital.common.util.tool.MD5Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-5
 * Time: 下午8:23
 * To change this template use File | Settings | File Templates.
 */
public class CpBaseinfoServiceImpl implements CpBaseinfoService {

    private final static Log log = LogFactory.getLog(CpBaseinfoServiceImpl.class);

    private CpBaseinfoManager cpBaseinfoManager;

    public void setCpBaseinfoManager(CpBaseinfoManager cpBaseinfoManager) {
        this.cpBaseinfoManager = cpBaseinfoManager;
    }

    public CpBaseinfo findCpBaseinfo(CpBaseinfo cpBaseinfo) {
        cpBaseinfo.setPassword(MD5Util.md5Hex(cpBaseinfo.getPassword()));
        return cpBaseinfoManager.findCpBaseinfo(cpBaseinfo);
    }

    public boolean findCpBaseinfoByPin(String userPin) {
        return cpBaseinfoManager.findCpBaseinfoByPin(userPin);
    }

    /**
     * 查看cp付费应用合作信息
     *
     * @param cpBaseinfo
     * @return
     */
    public Result getCpBaseinfo(CpBaseinfo cpBaseinfo) {
        Result result = new Result();
        try {
            result.addDefaultModel("cpBaseinfo", cpBaseinfoManager.findCpBaseinfo(cpBaseinfo));
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int checkCpBaseinfo(CpBaseinfo cpBaseinfo) {
        int falg;
        if (cpBaseinfo.getPassword() != null) {
            cpBaseinfo.setPassword(MD5Util.md5Hex(cpBaseinfo.getPassword()));
        }
        CpBaseinfo cpBaseinfoResult = cpBaseinfoManager.findCpBaseinfo(cpBaseinfo);
        if (cpBaseinfoResult == null) {
            falg = 0; // falg = 0 时：密码不正确
        } else {
            falg = 1; // falg = 1 时：密码正确
        }
        return falg;
    }

    public Result createCpBaseinfo(CpBaseinfo cpBaseinfo) {
        Result result = new Result();
        try {
            int cpBaseinfoId = cpBaseinfoManager.createCpBaseinfo(cpBaseinfo);
            result.addDefaultModel("cpBaseinfoId", cpBaseinfoId);
            result.addDefaultModel("cpBaseinfo", cpBaseinfo);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("createExample error!", e);
            result.setResultCode("system.error");
        }
        return result;
    }

    public Integer findCpAccoutId(String account) {
        return cpBaseinfoManager.findCpAccoutId(account);
    }

}
