package com.jd.appstore.dao.cp;

import com.jd.appstore.domain.*;
import com.jd.appstore.domain.paramter.CheckApk;
import com.jd.appstore.domain.paramter.StatApks;
import com.jd.appstore.domain.web.CheckApkResult;
import com.jd.appstore.domain.web.UserResult;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-15
 * Time: 下午4:46
 * To change this template use File | Settings | File Templates.
 */
public interface DevCenterDao {
    /**
     * 插入应用详情日志表
     *
     * @param appDetailLog
     */
    void insertAppDetailLog(AppDetailLog appDetailLog);

    /**
     * 插入应用表
     *
     * @param apps
     */
    void insertApps(Apps apps);

    /**
     * 插入应用日志表
     *
     * @param appLog
     */
    void insertAppLog(AppLog appLog);

    /**
     * 插入应用资源表
     *
     * @param appResource
     */
    void insertAppResource(AppResource appResource);


    List<AppDetailLog> findApkOnVersity(AppDetailLog appDetailLog);

    /**
     * 查找类目信息
     *
     * @param id
     * @return
     */
    List<Category> findCategory(int id);

    /**
     * 计算apk总数，用于分页
     *
     * @param checkApk
     * @return
     */
    int findApkCounts(CheckApk checkApk);

    /**
     * 查看应用
     *
     * @param checkApk
     * @return
     */
    List<CheckApkResult> findApks(CheckApk checkApk);

    /**
     * 从应用详情日志表计算apk总数，用于分页从应用详情日志表读取apk
     *
     * @param checkApk
     * @return
     */
    int findApkVersityCounts(CheckApk checkApk);

    /**
     * 从应用详情日志变里读取apk
     *
     * @param checkApk
     * @return
     */
    List<CheckApkResult> ApkVersity(CheckApk checkApk);

    /**
     * 统计应用详情日志表里的信息
     *
     * @param apps
     * @return
     */
    List<StatApks> statApkByAppDetailsLog(Apps apps);

    /**
     * 统计应用详情表里的信息
     *
     * @param apps
     * @return
     */
    List<StatApks> statApkByAppDetail(Apps apps);

    /**
     * 按appId从应用详情日志表内读取数据
     *
     * @param checkApk
     * @return
     */
    CheckApkResult checkApkByAppIdFromLog(CheckApk checkApk);

    /**
     * 按appId从应用详情表内读取数据
     *
     * @param checkApk
     * @return
     */
    CheckApkResult checkApkByAppId(CheckApk checkApk);

    /**
     * 按appId得到应用的资源图片
     *
     * @param checkApk
     * @return
     */
    List<AppResource> getAppResource(CheckApk checkApk);

    /**
     * 按accountId查看个人信息
     *
     * @param accountId
     * @return
     */
    UserResult personMess(int accountId);

    /**
     * 更新cp基本信息
     *
     * @param cpBaseinfo
     * @return
     */
    int updateCpBaseinfo(CpBaseinfo cpBaseinfo);

    /**
     * 更新cp注册信息
     *
     * @param cpReginfo
     * @return
     */
    int updateCpReginfo(CpReginfo cpReginfo);

    /**
     * 验证改应用是否还有详情
     *
     * @param apps
     * @return
     */
    Apps authApk(Apps apps);

    /**
     * 获得类目的名称
     *
     * @param categoryId
     * @return
     */
    String getCategoryName(int categoryId);

    /**
     * 从tb_app_details
     * 获得详情的CP帐号和收费模式
     *
     * @param appid
     * @return
     */
    List<AppDetails> getApkDetails(int appid);

    /**
     * 从tb_app_detail_log
     * 获得详情的CP帐号和收费模式
     *
     * @param appid
     * @return
     */
    List<AppDetailLog> getApkDetailLog(int appid);

    /**
     * 验证是否可以上传应用
     * @param apps
     * @return
     */
    List<Integer> verifyButUploading(Apps apps);

    /**
     * 更新tb_app_detais
     *
     * @param appDetails
     * @return
     */
    int updateAppDetials(AppDetails appDetails);

    /**
     * 更新资源表
     *
     * @param appResource
     * @return
     */
    int updateAppResource(AppResource appResource);

    /**
     * 更新tb_app_log
     *
     * @param appId
     * @return
     */
    int updateAppLog(int appId);

    /**
     * 按AppId 得到tb_app_log
     *
     * @param appId
     * @return
     */

    AppLog findAppLogByAppId(int appId);

    Apps findAppsByAppId(int appId);

    AppDetailLog findAppDetailLog(AppDetailLog appDetailLog);

    int updateAppDetailLog(AppDetailLog appDetailLog);

    int deteleAppres(AppResource appResource);

    void insertBackendTask(BackendTask backendTask);

    /**
     * 判断是否还能修改应用
     *
     * @param
     * @return
     */
    boolean isModifyApk(CheckApk checkApk);


    boolean authCpFromDetailLog(AppDetailLog appDetailLog);

    boolean authCpFromDetails(AppDetails appDetails);
}
