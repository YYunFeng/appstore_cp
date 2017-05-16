package com.jd.appstore.service.cp.impl;

import com.jd.appstore.domain.paramter.ForgetPass;
import com.jd.appstore.domain.web.ForgetPassResult;
import com.jd.appstore.domain.web.RecommendResult;
import com.jd.appstore.domain.web.UserStatus;
import com.jd.appstore.manager.cp.IndexManager;
import com.jd.appstore.service.cp.IndexService;
import com.jd.common.web.result.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-11
 * Time: 下午7:05
 * To change this template use File | Settings | File Templates.
 */
public class IndexServiceImpl implements IndexService {
    private final static Log log = LogFactory.getLog(CpBaseinfoServiceImpl.class);
    private IndexManager indexManager;

    private String loginCookieKey;
    private String dotnetAuthCookieName;

    public String getLoginCookieKey() {
        return loginCookieKey;
    }

    public void setLoginCookieKey(String loginCookieKey) {
        this.loginCookieKey = loginCookieKey;
    }

    public String getDotnetAuthCookieName() {
        return dotnetAuthCookieName;
    }

    public Result getRecommend() {
       Result result = new Result();
        try {
            result.addDefaultModel("recommend", indexManager.getRecommend());
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("recommend error", e);
        }
        return result;
    }

    public void setDotnetAuthCookieName(String dotnetAuthCookieName) {
        this.dotnetAuthCookieName = dotnetAuthCookieName;
    }

    public IndexManager getIndexManager() {
        return indexManager;
    }

    public void setIndexManager(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    public Result isHaveApp(int id) {
        Result result = new Result();
        try {
            UserStatus userStatus = indexManager.isHaveApp(id);
            result.addDefaultModel("userStatus", userStatus);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("system error!", e);
            result.setResultCode("system.error");
        }
        return result;
    }

    public ForgetPassResult forgetPassResult(ForgetPass forgetPass) {
        ForgetPassResult forgetPassResult = indexManager.forgetPassResult(forgetPass);
        if (forgetPassResult.getEmail() != null && !forgetPassResult.getEmail().equals("")) {
            forgetPassResult.setFlag(1); // 用户名和邮箱是正确的
        } else {
            forgetPassResult.setFlag(0); //用户和邮箱不匹配
        }
        return forgetPassResult;
    }
}
