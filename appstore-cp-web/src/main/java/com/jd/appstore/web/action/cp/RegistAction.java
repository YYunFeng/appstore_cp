package com.jd.appstore.web.action.cp;

import com.jd.appstore.action.AppStoreBaseAction;
import com.jd.appstore.domain.CpBaseinfo;
import com.jd.appstore.domain.CpReginfo;
import com.jd.appstore.domain.utils.Utils;
import com.jd.appstore.service.cp.AddDeveloperService;
import com.jd.appstore.service.cp.CpBaseinfoService;
import com.jd.common.web.result.Result;
import com.jd.digital.common.util.cache.CacheUtils;
import com.jd.digital.common.util.tool.MD5Util;
import com.jd.digital.common.util.tool.ValidateCodeUtil;
import com.jd.digital.common.util.tool.WebHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.JSON;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-6
 * Time: 上午10:21
 * To change this template use File | Settings | File Templates.
 */
public class RegistAction extends AppStoreBaseAction {
    private static final Log logger = LogFactory.getLog(RegistAction.class);
    /*
     * CP的注册信息
     */
    private CpReginfo cpReginfo;
    /*
     * CP的基本信息
      */
    private CpBaseinfo cpBaseinfo;

    private String account;

    private CpBaseinfoService cpBaseinfoService;
    private CacheUtils cacheUtils;
    private String authCode;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public CacheUtils getCacheUtils() {
        return cacheUtils;
    }

    public void setCacheUtils(CacheUtils cacheUtils) {
        this.cacheUtils = cacheUtils;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public CpBaseinfoService getCpBaseinfoService() {
        return cpBaseinfoService;
    }

    public void setCpBaseinfoService(CpBaseinfoService cpBaseinfoService) {
        this.cpBaseinfoService = cpBaseinfoService;
    }

    @JSON(serialize = false)
    public CpReginfo getCpReginfo() {
        return cpReginfo;
    }

    @JSON(serialize = false)
    public void setCpReginfo(CpReginfo cpReginfo) {
        this.cpReginfo = cpReginfo;
    }

    public CpBaseinfo getCpBaseinfo() {
        return cpBaseinfo;
    }

    public void setCpBaseinfo(CpBaseinfo cpBaseinfo) {
        this.cpBaseinfo = cpBaseinfo;
    }

    @JSON(serialize = false)
    public AddDeveloperService getAddDeveloperService() {
        return addDeveloperService;
    }

    @JSON(serialize = false)
    public void setAddDeveloperService(AddDeveloperService addDeveloperService) {
        this.addDeveloperService = addDeveloperService;
    }

    private AddDeveloperService addDeveloperService;

    /**
     * CP开发注册页面
     *
     * @return
     */
    public String regist() {
        Result result = new Result();
        result.addDefaultModel("uid", Utils.getUuid());
        toVm(result);
        return SUCCESS;
    }

    /**
     * 添加CP帐号
     *
     * @return
     */
    public String addDeveloper() throws JSONException, IOException {
        int flag = 0;
        String pin = WebHelper.getPin();
        try {
            if (cacheUtils.get(ValidateCodeUtil.MOBILE_REGISTER_VALIDATE_CODE + uid).equals(authCode)) {
                if (cpBaseinfo.getCpName() == null || cpBaseinfo.getCpName().equals("")) {
                    throw new RuntimeException("注册用户失败，未填开发者姓名");
                } else if (cpBaseinfo.getCpProperty() == null || cpBaseinfo.getCpProperty().equals("")) {
                    throw new RuntimeException("注册用户失败，未知开发者性质");
                } else if (cpReginfo.getCertificateType() == null || cpReginfo.getCertificateType().equals("")) {
                    throw new RuntimeException("注册用户失败，未选择证件类型");
                } else if (cpReginfo.getCertificateNo() == null || cpReginfo.getCertificateNo().equals("")) {
                    throw new RuntimeException("注册用户失败，未输入证件号码");
                } else {
                    cpBaseinfo.setPassword(MD5Util.md5Hex(cpBaseinfo.getPassword()));
                    Result addDeveloperServiceResult = addDeveloperService.createDeveloperService(cpBaseinfo, cpReginfo);
                    if (addDeveloperServiceResult.isSuccess()) {
                        flag = 1;
                    } else {
                        flag = 2;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("[userPin=" + pin + "]注册cp账户失败" + e);
        }
        String json = JSONUtil.serialize(flag);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        return null;
    }

    /**
     * 验证用户名是否重复
     */
    public void authAccount() {
        try {
            cpBaseinfo = new CpBaseinfo();
            cpBaseinfo.setAccount(account);
            int falg = cpBaseinfoService.checkCpBaseinfo(cpBaseinfo);
            String json = JSONUtil.serialize(falg);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册成功
     *
     * @return
     */
    public String regSuccess() {
        return SUCCESS;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
