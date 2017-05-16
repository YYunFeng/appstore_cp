package com.jd.appstore.web.action.cp;

import com.jd.appstore.action.AppStoreBaseAction;
import com.jd.appstore.domain.*;
import com.jd.appstore.domain.json.AuthApkJSON;
import com.jd.appstore.domain.json.StatusJSON;
import com.jd.appstore.domain.paramter.BindMobileMess;
import com.jd.appstore.domain.paramter.CheckApk;
import com.jd.appstore.domain.paramter.GetComment;
import com.jd.appstore.domain.utils.Utils;
import com.jd.appstore.service.cp.BindMoblieService;
import com.jd.appstore.service.cp.CpBaseinfoService;
import com.jd.appstore.service.cp.DevCenterService;
import com.jd.appstore.service.cp.GetAddressService;
import com.jd.appstore.web.util.cp.AuthMess;
import com.jd.appstore.web.util.cp.ClientIp;
import com.jd.common.util.StringUtils;
import com.jd.common.web.result.Result;
import com.jd.digital.common.rpc.domain.bean.CommonResult;
import com.jd.digital.common.util.cache.CacheUtils;
import com.jd.digital.common.util.tool.ValidateCodeUtil;
import com.jd.digital.common.util.tool.WebHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import java.io.IOException;
import java.net.URLDecoder;


/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-12
 * Time: 上午10:19
 * To change this template use File | Settings | File Templates.
 */
public class DevCenterAction extends AppStoreBaseAction {
    private static final Log logger = LogFactory.getLog(RegistAction.class);

    private String msg;
    private AppLog appLog;
    private AppDetailLog appDetailLog;
    private AppResource appResource;
    private DevCenterService devCenterService;
    private CpBaseinfoService cpBaseinfoService;
    private String pics;
    private CheckApk checkApk;
    private int id;
    private int pageSize = 5;
    private Integer categoryId_l2;
    private Integer updateStatus;
    private Integer online;
    private CpBaseinfo cpBaseinfo;
    private CpReginfo cpReginfo;
    private CacheUtils cacheUtils;
    private String uid;
    private String authcode;
    private int flag;
    private String pkg;
    private BindMobileMess bindMobileMess;
    private BindMoblieService bindMoblieService;
    private GetComment getComment;
    private GetAddressService getAddressService;
    private String apkName;
    private String appName;

    /**
     * 应用发布中心
     *
     * @return
     */
    public String devCenter() {
        Result result = new Result();
        try {
            String pin = WebHelper.getPin();
            CpBaseinfo cpBaseinfo = new CpBaseinfo();
            cpBaseinfo.setAccount(pin);
            Result cpResult = cpBaseinfoService.getCpBaseinfo(cpBaseinfo);
            if (cpResult.isSuccess()) {
                cpBaseinfo = (CpBaseinfo) cpResult.get("cpBaseinfo");
            }
            if (StringUtils.isBlank(msg)) {
                result.addDefaultModel("cpType", cpBaseinfo.getCpProperty());
            } else {
                result.addDefaultModel("msg", msg);
                result.addDefaultModel("cpType", cpBaseinfo.getCpProperty());
            }
            toVm(result);
        } catch (Exception e) {
            logger.error(e);
        }
        return SUCCESS;
    }

    /**
     * 创建APK应用
     *
     * @return
     */
    public String createApk() {
        cpBaseinfo = new CpBaseinfo();
        String pin = WebHelper.getPin();
        cpBaseinfo.setAccount(pin);
        Result feeMode = cpBaseinfoService.getCpBaseinfo(cpBaseinfo);
        toVm(feeMode);
        return SUCCESS;
    }

    /**
     * 验证该应用是否存在
     */
    public void authApk() throws IOException, JSONException {
        AuthApkJSON authApkJSON = new AuthApkJSON();
        try {
            Apps apps = new Apps();
            apps.setPkg(pkg);
            apps.setAppName(apkName);
            String pin = WebHelper.getPin();
            apps.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
            authApkJSON = devCenterService.authApk(apps);
            if (authApkJSON.getStatus() == null)
                authApkJSON.setStatus(1);
        } catch (Exception e) {
            authApkJSON.setStatus(0);
            authApkJSON.setMess("系统错误，请重试!");
            e.printStackTrace();
        }
        String json = JSONUtil.serialize(authApkJSON);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
    }

    /**
     * 保存APK文件
     *
     * @throws JSONException
     * @throws IOException
     */
    public String saveApk() throws JSONException, IOException {
        String pin = WebHelper.getPin();
        StatusJSON statusJSON = new StatusJSON();
        try {
            // 判断是否登陆
            if (StringUtils.isNotBlank(pin)) {
                Integer accountId = cpBaseinfoService.findCpAccoutId(pin);
                appDetailLog.setAccountId(accountId);
                appResource.setAccountId(accountId);
                appDetailLog.setIp(ClientIp.getIp(request));
                // 判断所有字段是否全
                if (StringUtils.isNotBlank(appLog.getAppName()) && StringUtils.isNotBlank(appLog.getPkg()) && StringUtils.isNotBlank(appDetailLog.getAppVersion()) && StringUtils.isNotBlank(appDetailLog.getAppIntroduce()) && StringUtils.isNotBlank(appDetailLog.getLogoUrl()) && StringUtils.isNotBlank(appResource.getApkName())) {
                    appLog.setAppName(URLDecoder.decode(appLog.getAppName(), "UTF-8"));
                    appDetailLog.setAppTag(URLDecoder.decode(appDetailLog.getAppTag(), "UTF-8"));
                    AuthMessJSON authMessJSON = AuthMess.AuthMess(appLog, appDetailLog, appResource, 0);
                    // 判断所有字段是否超长
                    if (authMessJSON != null && authMessJSON.getStatus()) {
                        Result result = devCenterService.createApk(appLog, appDetailLog, appResource);
                        // 判断是否创建成功
                        if (result != null && result.getSuccess()) {
                            statusJSON.setStatus(true);
                            statusJSON.setMsg("success");
                        } else {
                            statusJSON.setStatus(false);
                            statusJSON.setMsg("系统错误，请重试");
                        }
                    } else {
                        statusJSON.setStatus(false);
                        statusJSON.setMsg(authMessJSON.getMess());
                    }

                } else {
                    statusJSON.setStatus(false);
                    statusJSON.setMsg("系统错误，请重试");
                }

            } else {
                statusJSON.setStatus(false);
                statusJSON.setMsg("你还未登陆!");
            }

            String json = JSONUtil.serialize(statusJSON);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 创建WEB应用
     *
     * @return
     */
    public String createWeb() {
        return SUCCESS;
    }

    /**
     * 保存Web应用
     */
    public String saveWeb() throws JSONException, IOException {
        StatusJSON statusJSON = new StatusJSON();
        String pin = WebHelper.getPin();
        try {
            // 判断是否登录 
            if (StringUtils.isNotBlank(pin)) {
                Integer accountId = cpBaseinfoService.findCpAccoutId(pin);
                appDetailLog.setAccountId(accountId);
                appResource.setAccountId(accountId);
                appDetailLog.setIp(ClientIp.getIp(request));
                // 判断所有字段是否全
                if (StringUtils.isNotBlank(appLog.getAppName()) && StringUtils.isNotBlank(appDetailLog.getAppVersion()) && StringUtils.isNotBlank(appDetailLog.getAppIntroduce())) {
                    appLog.setAppName(URLDecoder.decode(appLog.getAppName(), "UTF-8"));
                    appDetailLog.setAppTag(URLDecoder.decode(appDetailLog.getAppTag(), "UTF-8"));
                    AuthMessJSON authMessJSON = AuthMess.AuthMess(appLog, appDetailLog, appResource, 1);
                    // 判断字段是否超长
                    if (authMessJSON != null && authMessJSON.getStatus()) {
                        Result result = devCenterService.createWeb(appLog, appDetailLog, appResource);
                        // 判断是否更新成功
                        if (result != null && result.getSuccess()) {
                            statusJSON.setStatus(true);
                            statusJSON.setMsg("success");
                        } else {
                            statusJSON.setStatus(false);
                            statusJSON.setMsg("系统错误，请重试");
                        }
                    } else {
                        statusJSON.setStatus(false);
                        statusJSON.setMsg(authMessJSON.getMess());
                    }
                } else {
                    statusJSON.setStatus(false);
                    statusJSON.setMsg("系统错误，请重试");
                }
            } else {
                statusJSON.setStatus(false);
                statusJSON.setMsg("你还未登陆!");
            }

            String json = JSONUtil.serialize(statusJSON);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从日志表里查看应用
     *
     * @return
     */

    public String checkApkOnVerify() {
        try {
            CheckApk checkApk = new CheckApk();
            String pin = WebHelper.getPin();
            checkApk.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
            checkApk.setUpdateStatus(updateStatus);
            Result result = devCenterService.checkApksOnVersity(checkApk, page, pageSize);
            toVm(result);
            Apps apps = new Apps();
            apps.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
            apps.setCategoryidL2(checkApk.getCategoryId_l2());
            Result stat = devCenterService.statApkes(apps);
            toVm(stat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 从正式表中查看应用
     *
     * @return
     */
    public String checkApkNoVerify() {
        try {
            CheckApk checkApk = new CheckApk();
            String pin = WebHelper.getPin();
            checkApk.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
            checkApk.setOnline(online);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 查看应用
     *
     * @return
     */
    public String checkApks() {
        try {
            Apps apps = new Apps();
            String pin = WebHelper.getPin();
            apps.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
            checkApk = new CheckApk();
            if (appName != null) {
                checkApk.setAppName(appName);
                Result appNameRes =new Result();
                appNameRes.addDefaultModel("keyWord",appName);
                toVm(appNameRes);
            }
            if (categoryId_l2 != null) {
                checkApk.setCategoryId_l2(categoryId_l2);
                apps.setCategoryidL2(categoryId_l2);
            }
            if (updateStatus != null) {
                checkApk.setUpdateStatus(updateStatus);
            }
            if (online != null) {
                checkApk.setOnline(online);
            }
            checkApk.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
            Result result = devCenterService.checkApks(checkApk, page, pageSize);
            toVm(result);

            Result stat = devCenterService.statApkes(apps);
            toVm(stat);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 按appId查看应用详情
     *
     * @return
     */
    public String checkApksByAppId() {
        checkApk = new CheckApk();
        String pin = WebHelper.getPin();
        checkApk.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
        checkApk.setAppId(id);
        checkApk.setUpdateStatus(updateStatus);
        try {
            if (updateStatus != null && updateStatus != 0) {
                Result stat = devCenterService.checkApkByAppIdFromLog(checkApk);
                toVm(stat);
            } else {
                Result stat = devCenterService.checkApkByAppId(checkApk);
                toVm(stat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 获得类目
     */
    public void getCategory() throws JSONException, IOException {
        Result categorys = devCenterService.getCategory(id);
        String json = JSONUtil.serialize(categorys);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    /**
     * 获得城市
     */
    public void getProvinceCity() throws JSONException, IOException {
        Result provinceCity = getAddressService.getProvinceCity(id);
        String json = JSONUtil.serialize(provinceCity);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    /**
     * 获得地区
     */
    public void getCityDistrict() throws JSONException, IOException {
        Result cityDistricts = getAddressService.getCity(id);
        String json = JSONUtil.serialize(cityDistricts);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    /**
     * 修改apk应用
     *
     * @return
     */
    public String modifyApk() {
        try {
            checkApk = new CheckApk();
            String pin = WebHelper.getPin();
            checkApk.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
            checkApk.setAppId(id);
            checkApk.setUpdateStatus(updateStatus);
            cpBaseinfo = new CpBaseinfo();
            cpBaseinfo.setAccount(pin);
            Result feeMode = cpBaseinfoService.getCpBaseinfo(cpBaseinfo);
            toVm(feeMode);
            if (updateStatus != null && updateStatus != 0) {
                Result apk = devCenterService.checkApkByAppIdFromLog(checkApk);
                toVm(apk);
            } else {
                Result apk = devCenterService.checkApkByAppId(checkApk);
                toVm(apk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 验证是否可以修改APP
     *
     * @throws JSONException
     * @throws IOException
     */
    public void ismodify() throws JSONException, IOException {
        String json = null;
        try {
            String pin = WebHelper.getPin();
            CheckApk checkApk = new CheckApk();
            checkApk.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
            checkApk.setAppId(id);
            json = JSONUtil.serialize(devCenterService.ismodifyApk(checkApk));
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    /**
     * 保存修改的APk文件
     *
     * @throws JSONException
     * @throws IOException
     */
    public String saveModifyApk() throws JSONException, IOException {
        StatusJSON statusJSON = new StatusJSON();
        try {
            String pin = WebHelper.getPin();
            // 判断是否登陆
            if (com.jd.common.util.StringUtils.isNotBlank(pin)) {
                Integer accoundId = cpBaseinfoService.findCpAccoutId(pin);
                appDetailLog.setAccountId(accoundId);
                appResource.setAccountId(accoundId);
                appDetailLog.setIp(ClientIp.getIp(request));
                // 判断所有字段是否全
                if (StringUtils.isNotBlank(appLog.getAppName()) && StringUtils.isNotBlank(appLog.getPkg()) && StringUtils.isNotBlank(appDetailLog.getAppVersion()) && StringUtils.isNotBlank(appDetailLog.getLogoUrl())) {
                    appLog.setAppName(URLDecoder.decode(appLog.getAppName(), "UTF-8"));
                    appDetailLog.setAppTag(URLDecoder.decode(appDetailLog.getAppTag(), "UTF-8"));
                    AuthMessJSON authMessJSON = AuthMess.AuthMess(appLog, appDetailLog, appResource, 0);
                    // 判断字段是否超长
                    if (authMessJSON != null && authMessJSON.getStatus()) {
                        Result result = devCenterService.updateApp(appLog, appDetailLog, appResource);
                        // 判断是否更新成功
                        if (result != null && result.getSuccess()) {
                            statusJSON.setStatus(true);
                            statusJSON.setMsg("success");
                        } else {
                            statusJSON.setStatus(false);
                            statusJSON.setMsg("系统错误，请重试");
                        }
                    } else {
                        statusJSON.setStatus(false);
                        statusJSON.setMsg(authMessJSON.getMess());
                    }
                } else {
                    statusJSON.setStatus(false);
                    statusJSON.setMsg("系统错误，请重试");
                }
            } else {
                statusJSON.setStatus(false);
                statusJSON.setMsg("你还未登陆");
            }
            String json = JSONUtil.serialize(statusJSON);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改web应用
     *
     * @return
     */
    public String modifyWeb() {
        checkApk = new CheckApk();
        String pin = WebHelper.getPin();
        checkApk.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
        checkApk.setAppId(id);
        checkApk.setUpdateStatus(updateStatus);
        try {
            if (updateStatus != null && updateStatus != 0) {
                Result stat = devCenterService.checkApkByAppIdFromLog(checkApk);
                toVm(stat);
            } else {
                Result stat = devCenterService.checkApkByAppId(checkApk);
                toVm(stat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 保存修改Web文件
     */
    public String saveModifyWeb() {
        StatusJSON statusJSON = new StatusJSON();
        String pin = WebHelper.getPin();
        try {
            // 判断是否登陆
            if (com.jd.common.util.StringUtils.isNotBlank(pin)) {
                Integer accountId = cpBaseinfoService.findCpAccoutId(pin);
                appDetailLog.setAccountId(accountId);
                appResource.setAccountId(accountId);
                appDetailLog.setIp(ClientIp.getIp(request));
                // 判断是否字段是否全
                if (StringUtils.isNotBlank(appLog.getAppName()) && StringUtils.isNotBlank(appDetailLog.getAppVersion()) && StringUtils.isNotBlank(appDetailLog.getAppIntroduce())) {
                    appLog.setAppName(URLDecoder.decode(appLog.getAppName(), "UTF-8"));
                    appDetailLog.setAppTag(URLDecoder.decode(appDetailLog.getAppTag(), "UTF-8"));
                    AuthMessJSON authMessJSON = AuthMess.AuthMess(appLog, appDetailLog, appResource, 1);
                    // 判断字段是否超长
                    if (authMessJSON != null && authMessJSON.getStatus()) {
                        Result result = devCenterService.updateWeb(appLog, appDetailLog, appResource);
                        // 判断是否更新成功
                        if (result != null && result.getSuccess()) {
                            statusJSON.setStatus(true);
                            statusJSON.setMsg("success");
                        } else {
                            statusJSON.setStatus(false);
                            statusJSON.setMsg("系统错误，请重试");
                        }
                    } else {
                        statusJSON.setStatus(false);
                        statusJSON.setMsg(authMessJSON.getMess());
                    }
                } else {
                    statusJSON.setStatus(false);
                    statusJSON.setMsg("系统错误，请重试");
                }
            } else {
                statusJSON.setStatus(false);
                statusJSON.setMsg("你还未登陆");
            }
            String json = JSONUtil.serialize(statusJSON);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查看个人信息
     *
     * @return
     */

    public String personMess() {
        try {
            String pin = WebHelper.getPin();
            Result result = devCenterService.personMess(cpBaseinfoService.findCpAccoutId(pin));
            toVm(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 修改个人信息
     *
     * @return
     */
    public String modifyCpMess() {
        try {
            String pin = WebHelper.getPin();
            Result result = devCenterService.personMess(cpBaseinfoService.findCpAccoutId(pin));
            result.addDefaultModel("uid", Utils.getUuid() + System.currentTimeMillis());
            toVm(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 修改公司信息
     *
     * @return
     */
    public String modifyCompanyMess() {
        try {
            String pin = WebHelper.getPin();
            Result result = devCenterService.personMess(cpBaseinfoService.findCpAccoutId(pin));
            result.addDefaultModel("uid", Utils.getUuid());
            toVm(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 得到评论
     *
     * @return
     */
    public String comment() {
        Result result = devCenterService.getCommentSummary(getComment, pageSize, page);
        toVm(result);
        return SUCCESS;
    }

    /**
     * 查看公司信息
     *
     * @return
     */
    public String companyMess() {
        try {
            String pin = WebHelper.getPin();
            Result result = devCenterService.personMess(cpBaseinfoService.findCpAccoutId(pin));
            toVm(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 更新cp信息
     */
    public void updateCpMess() {
        try {
            if (!cacheUtils.get(ValidateCodeUtil.MOBILE_REGISTER_VALIDATE_CODE + uid).equals(authcode)) {
                flag = 0; // 验证码输出错误
            } else {
                String pin = WebHelper.getPin();
                cpBaseinfo.setId(cpBaseinfoService.findCpAccoutId(pin));
                cpReginfo.setAccountId(cpBaseinfoService.findCpAccoutId(pin));
                Result result = devCenterService.updateCpMess(cpBaseinfo, cpReginfo);
                flag = 1; //更新成功
            }
            String json = JSONUtil.serialize(flag);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 付费应用合同信息
     *
     * @return
     */
    public String payAppInfo() {
        try {
            String pin = WebHelper.getPin();
            cpBaseinfo = new CpBaseinfo();
            cpBaseinfo.setAccount(pin);
            Result result = cpBaseinfoService.getCpBaseinfo(cpBaseinfo);
            toVm(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 修改密码
     *
     * @return
     * @deprecated
     */
    public String changePassword() {
        return SUCCESS;
    }

    /**
     * 验证密码是否正确
     *
     * @deprecated
     */
    public void checkPass() {
        try {
            String pin = WebHelper.getPin();
            cpBaseinfo.setAccount(pin);
            int falg = cpBaseinfoService.checkCpBaseinfo(cpBaseinfo);
            String json = JSONUtil.serialize(falg);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改密码
     *
     * @deprecated
     */
    public void modifyPass() {
        try {
            String pin = WebHelper.getPin();
            cpBaseinfo.setId(cpBaseinfoService.findCpAccoutId(pin));
            int flag = devCenterService.updateCpBaseingo(cpBaseinfo);
            String json = JSONUtil.serialize(flag);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setBindMobileMess(BindMobileMess bindMobileMess) {
        this.bindMobileMess = bindMobileMess;
    }

    /**
     * 获取手机验证码
     *
     * @throws JSONException
     * @throws IOException
     * @deprecated
     */
    public void getMess() throws JSONException, IOException {
        CommonResult commonResult = bindMoblieService.getMess(bindMobileMess);
        String json = JSONUtil.serialize(commonResult);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    /**
     * @return
     * @deprecated
     */
    public BindMobileMess getBindMobileMess() {
        return bindMobileMess;
    }

    /**
     * @return
     * @deprecated
     */
    public BindMoblieService getBindMoblieService() {
        return bindMoblieService;
    }

    /**
     * @return
     * @deprecated
     */
    public void setBindMoblieService(BindMoblieService bindMoblieService) {
        this.bindMoblieService = bindMoblieService;
    }

    /**
     * @return
     * @deprecated
     */
    public String bindMobile() {
        return SUCCESS;
    }

    public DevCenterService getDevCenterService() {
        return devCenterService;
    }

    public void setDevCenterService(DevCenterService devCenterService) {
        this.devCenterService = devCenterService;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Integer getCategoryId_l2() {
        return categoryId_l2;
    }

    public void setCategoryId_l2(Integer categoryId_l2) {
        this.categoryId_l2 = categoryId_l2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public AppLog getAppLog() {
        return appLog;
    }

    public AppResource getAppResource() {
        return appResource;
    }

    public void setAppResource(AppResource appResource) {
        this.appResource = appResource;
    }

    public void setAppLog(AppLog appLog) {
        this.appLog = appLog;
    }

    public AppDetailLog getAppDetailLog() {
        return appDetailLog;
    }

    public void setAppDetailLog(AppDetailLog appDetailLog) {
        this.appDetailLog = appDetailLog;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CpBaseinfo getCpBaseinfo() {
        return cpBaseinfo;
    }

    public void setCpBaseinfo(CpBaseinfo cpBaseinfo) {
        this.cpBaseinfo = cpBaseinfo;
    }

    public CpReginfo getCpReginfo() {
        return cpReginfo;
    }

    public void setCpReginfo(CpReginfo cpReginfo) {
        this.cpReginfo = cpReginfo;
    }

    public CpBaseinfoService getCpBaseinfoService() {
        return cpBaseinfoService;
    }

    public void setCpBaseinfoService(CpBaseinfoService cpBaseinfoService) {
        this.cpBaseinfoService = cpBaseinfoService;
    }

    public void setCacheUtils(CacheUtils cacheUtils) {
        this.cacheUtils = cacheUtils;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public GetComment getGetComment() {
        return getComment;
    }

    public void setGetComment(GetComment getComment) {
        this.getComment = getComment;
    }

    public GetAddressService getGetAddressService() {
        return getAddressService;
    }

    public void setGetAddressService(GetAddressService getAddressService) {
        this.getAddressService = getAddressService;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
