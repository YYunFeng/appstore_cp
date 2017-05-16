package com.jd.appstore.service.cp.impl;

import com.jd.appstore.domain.CpBaseinfo;
import com.jd.appstore.domain.CpReginfo;
import com.jd.appstore.manager.cp.AddDeveloperPersonManager;
import com.jd.appstore.service.cp.AddDeveloperService;
import com.jd.common.web.result.Result;
import com.jd.digital.common.util.tool.MD5Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-10
 * Time: 下午7:32
 * To change this template use File | Settings | File Templates.
 */
public class AddDeveloperServiceImpl implements AddDeveloperService {
    private final static Log log = LogFactory.getLog(AddDeveloperServiceImpl.class);

    private AddDeveloperPersonManager addDeveloperPersonManager;
    

    public AddDeveloperPersonManager getAddDeveloperPersonManager() {
        return addDeveloperPersonManager;
    }

    public void setAddDeveloperPersonManager(AddDeveloperPersonManager addDeveloperPersonManager) {
        this.addDeveloperPersonManager = addDeveloperPersonManager;
    }

    public Result createDeveloperService(CpBaseinfo cpBaseinfo, CpReginfo cpReginfo) {
        Result result = new Result();
        try {
            int addDeveloperPerson = addDeveloperPersonManager.AddDeveloperPerson(cpBaseinfo, cpReginfo);
            result.addDefaultModel("addDeveloperPerson", addDeveloperPerson);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("createDeveloper error!", e);
            result.setSuccess(false);
            result.setResultCode("system.error");
        }
        return result;
    }
}
