package com.jd.appstore.manager.cp;

import com.jd.appstore.domain.*;
import com.jd.appstore.domain.json.AuthApkJSON;
import com.jd.appstore.domain.paramter.CheckApk;
import com.jd.appstore.domain.paramter.StatApks;
import com.jd.appstore.domain.web.CheckApkResult;
import com.jd.appstore.domain.web.UserResult;
import com.jd.common.util.PaginatedList;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-15
 * Time: 下午4:51
 * To change this template use File | Settings | File Templates.
 */
public interface DevCenterManager {

    /**
     * 创建应用
     *
     * @param appsLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    int createApk(AppLog appsLog, AppDetailLog appDetailLog, AppResource appResource);

    /**
     * 创建web应用
     *
     * @param appsLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    int createWeb(AppLog appsLog, AppDetailLog appDetailLog, AppResource appResource);

    /**
     * 获得类目
     *
     * @param id
     * @return
     */
    List<Category> getCategory(int id);

    /**
     * 分页查看应用
     *
     * @param checkApk
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PaginatedList<CheckApkResult> findApks(CheckApk checkApk, int pageIndex, int pageSize);


    /**
     * 统计用户apk,在查看应用页面，导航的应用统计数据
     *
     * @param apps
     * @return
     */
    StatApks statApks(Apps apps);

    /**
     * 分页从应用详情日志表里查看应用
     *
     * @param checkApk
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PaginatedList<CheckApkResult> ApkVersity(CheckApk checkApk, int pageIndex, int pageSize);

    /**
     * @param checkApk
     * @return
     */
    CheckApkResult checkApkByAppIdFromLog(CheckApk checkApk);

    /**
     * @param checkApk
     * @return
     */
    CheckApkResult checkApkByAppId(CheckApk checkApk);

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
     * 验证该应用是否还有详情
     *
     * @param apps
     * @return
     */
    AuthApkJSON authApk(Apps apps);

    /**
     * 修改Apk文件
     * @param appLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    int updateApp(AppLog appLog, AppDetailLog appDetailLog, AppResource appResource);

    /**
     * 修改Web
     * @param appLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    int updateWeb(AppLog appLog, AppDetailLog appDetailLog, AppResource appResource);

    boolean ismodifyApk(CheckApk checkApk);

    Integer verifyButUploading(Apps apps);

}
