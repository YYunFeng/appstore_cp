package com.jd.appstore.web.action.cp;

import com.jd.appstore.action.AppStoreBaseAction;
import com.jd.appstore.domain.CpBaseinfo;
import com.jd.appstore.domain.CpReginfo;
import com.jd.appstore.domain.enumtype.AppStoreLoginStatus;
import com.jd.appstore.domain.json.CpBaseinfoJSON;
import com.jd.appstore.domain.paramter.ForgetPass;
import com.jd.appstore.domain.utils.Utils;
import com.jd.appstore.domain.web.ForgetPassResult;
import com.jd.appstore.service.cp.AddDeveloperService;
import com.jd.appstore.service.cp.CpBaseinfoService;
import com.jd.appstore.service.cp.IndexService;
import com.jd.appstore.web.util.cp.CookieOper;
import com.jd.common.struts.context.LoginContext;
import com.jd.common.web.cookie.CookieUtils;
import com.jd.common.web.result.Result;
import com.jd.digital.common.rpc.domain.web.JdAppLoginContext;
import com.jd.digital.common.util.cache.CacheUtils;
import com.jd.digital.common.util.tool.ValidateCodeUtil;
import com.jd.digital.common.util.tool.WebHelper;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-5
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 * CP自服务系统首页Action
 */
public class IndexAction extends AppStoreBaseAction {
    private int id;
    private String account;
    private CpBaseinfo cpBaseinfo;
    private IndexService indexService;
    private CpBaseinfoJSON cpBaseinfoJSON;
    private CpBaseinfoService cpBaseinfoService;
    private CookieOper cookieOper = new CookieOper();
    private ForgetPass forgetPass;
    private CacheUtils cacheUtils;
    private String authCode;
    private String uid;
    private CookieUtils cookieUtils;
    private AddDeveloperService addDeveloperService;


    /**
     * 初始化CP自服务系统首页
     *
     * @return
     */
    public String index() {
        String pin = WebHelper.getPin();
        int loginStatus = AppStoreLoginStatus.NOT_LOGIN.getCode();
        LoginContext loginContext = JdAppLoginContext.getLoginContext();
        if (loginContext != null && loginContext.isLogin()) {
            if (cpBaseinfoService.findCpBaseinfoByPin(loginContext.getPin())) {
                loginStatus = AppStoreLoginStatus.LOGIN_AND_DEVELOPER.getCode();
            }
        }
        Result result = new Result();
        result.addDefaultModel("loginStatus", loginStatus);
        Result resultPic = indexService.getRecommend();
        toVm(resultPic);
        toVm(result);
        return SUCCESS;
    }

    /**
     * 用户登录
     *
     * @return
     * @throws Exception
     */
    public void login() throws Exception {
        cpBaseinfoJSON = new CpBaseinfoJSON();
        CpBaseinfo cpBaseinfoResult = cpBaseinfoService.findCpBaseinfo(cpBaseinfo);
        if (cpBaseinfoResult != null) {
            //  登录成功
            cpBaseinfoJSON.setMsg("success");
            cpBaseinfoJSON.setAccount(cpBaseinfoResult.getAccount());
            cpBaseinfoJSON.setId(cpBaseinfoResult.getId());

            LoginContext context = JdAppLoginContext.getLoginContext();
            if (context == null) {
                context = new LoginContext();
            }
            //设置PIN的值
            context.setPin(cpBaseinfoResult.getAccount());
            JdAppLoginContext.setLoginContext(context);
            //将用户名 保存到cookie
            cookieUtils.setCookie(response, indexService.getLoginCookieKey(), context.toCookieValue());
        } else {
            //  登录失败
            cpBaseinfoJSON.setMsg("用户名或者密码错误");
        }
        String json = JSONUtil.serialize(cpBaseinfoJSON);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
    }


    /**
     * 登录认证 记录日志
     *
     * @return
     */
    public String home() {
//       判断该用户是否上传过应用
        Result userStatuResult = indexService.isHaveApp(id);
        toVm(userStatuResult);
        return SUCCESS;
    }

    /**
     * 获得CP帐号名称
     *
     * @throws Exception
     */
    public void getCpName() throws Exception {
        String account = "";
        LoginContext loginContext = JdAppLoginContext.getLoginContext();
        if (loginContext != null && loginContext.isLogin()) {
            account = loginContext.getPin();
        }
        String json = JSONUtil.serialize(account);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    //  退出登录
    public String exit() {
        ActionContext actionContext = ActionContext.getContext();
        HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
        // 删除当前网站登录cookie
        cookieUtils.deleteCookie(response, indexService.getLoginCookieKey());

        return "index";
    }

    /**
     * 忘记密码
     *
     * @return
     */
    public String retrievePassword() {
        Result result = new Result();
        result.addDefaultModel("uid", Utils.getUuid());
        toVm(result);
        return SUCCESS;
    }

    /**
     * 忘记密码?
     */
    public void retrievePasswordSubmit() throws JSONException, IOException {
        ForgetPassResult forgetPassResult = new ForgetPassResult();
        if (!cacheUtils.get(ValidateCodeUtil.MOBILE_REGISTER_VALIDATE_CODE + uid).equals(authCode)) {
            forgetPassResult.setFlag(2); // 验证码不正确
        } else {
            forgetPassResult = indexService.forgetPassResult(forgetPass);
            if (forgetPassResult.getFlag() == 1) {
                System.out.print("已经发送邮件!");
            }
        }
        String json = JSONUtil.serialize(forgetPassResult);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public CpBaseinfo getCpBaseinfo() {
        return cpBaseinfo;
    }

    public void setCpBaseinfo(CpBaseinfo cpBaseinfo) {
        this.cpBaseinfo = cpBaseinfo;
    }

    public IndexService getIndexService() {
        return indexService;
    }

    public void setIndexService(IndexService indexService) {
        this.indexService = indexService;
    }

    public CpBaseinfoJSON getCpBaseinfoJSON() {
        return cpBaseinfoJSON;
    }

    public void setCpBaseinfoJSON(CpBaseinfoJSON cpBaseinfoJSON) {
        this.cpBaseinfoJSON = cpBaseinfoJSON;
    }

    public CpBaseinfoService getCpBaseinfoService() {
        return cpBaseinfoService;
    }

    public void setCpBaseinfoService(CpBaseinfoService cpBaseinfoService) {
        this.cpBaseinfoService = cpBaseinfoService;
    }

    public CookieOper getCookieOper() {
        return cookieOper;
    }

    public void setCookieOper(CookieOper cookieOper) {
        this.cookieOper = cookieOper;
    }

    public ForgetPass getForgetPass() {
        return forgetPass;
    }

    public void setForgetPass(ForgetPass forgetPass) {
        this.forgetPass = forgetPass;
    }

    public CookieUtils getCookieUtils() {
        return cookieUtils;
    }

    public void setCookieUtils(CookieUtils cookieUtils) {
        this.cookieUtils = cookieUtils;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setAddDeveloperService(AddDeveloperService addDeveloperService) {
        this.addDeveloperService = addDeveloperService;
    }
}
