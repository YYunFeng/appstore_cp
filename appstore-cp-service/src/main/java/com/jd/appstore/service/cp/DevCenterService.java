package com.jd.appstore.service.cp;

import com.jd.appstore.domain.*;
import com.jd.appstore.domain.json.AuthApkJSON;
import com.jd.appstore.domain.paramter.CheckApk;
import com.jd.appstore.domain.paramter.GetComment;
import com.jd.common.web.result.Result;

import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-15
 * Time: 下午6:58
 * To change this template use File | Settings | File Templates.
 */
public interface DevCenterService {
    /**
     * 创建APK应用
     *
     * @param appLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    Result createApk(AppLog appLog, AppDetailLog appDetailLog, AppResource appResource) throws UnsupportedEncodingException;

    /**
     * 创建Web应用
     * @param appLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    Result createWeb(AppLog appLog,AppDetailLog appDetailLog,AppResource appResource);

    /**
     * 查看详情日志表里的应用
     *
     * @param checkApk
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Result checkApksOnVersity(CheckApk checkApk, int pageIndex, int pageSize);

    /**
     * 查看详情表中的应用
     *
     * @param checkApk
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Result checkApks(CheckApk checkApk, int pageIndex, int pageSize);

    /**
     * 获取类目
     *
     * @param id
     * @return
     */
    Result getCategory(int id);

    /**
     * 统计应用
     *
     * @param apps
     * @return
     */
    Result statApkes(Apps apps);

    /**
     * 按appId从详情日志表中获得应用详情
     *
     * @param checkApk
     * @return
     */
    Result checkApkByAppIdFromLog(CheckApk checkApk);

    /**
     * 按appId从详情表中获得应用详情
     *
     * @param checkApk
     * @return
     */
    Result checkApkByAppId(CheckApk checkApk);

    /**
     * 按accountId查看个人信息
     *
     * @param accountId
     * @return
     */
    Result personMess(int accountId);

    /**
     * 更新cp信息
     * @param cpBaseinfo
     * @return
     */
    Result updateCpMess(CpBaseinfo cpBaseinfo,CpReginfo cpReginfo);

    /**
     * 更新cp基本信息 用于修改密码
     * @param cpBaseinfo
     * @return
     */
    int updateCpBaseingo(CpBaseinfo cpBaseinfo);

    /**
     * 验证该应用是否还有详情
     *
     * @param apps
     * @return
     */
    AuthApkJSON authApk(Apps apps);

    /**
     * 修改Apk应用
     * @param appLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    Result updateApp(AppLog appLog, AppDetailLog appDetailLog, AppResource appResource);

    /**
     * 修改web应用
     * @param appLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    Result updateWeb(AppLog appLog, AppDetailLog appDetailLog, AppResource appResource);

    /**
     * 获取评论摘要
     * @param 
     * @return
     */
    Result getCommentSummary(GetComment getComment,int pageSize,int pageIndex);

    /**
     * 判断是否能修改应用
     * @param checkApk
     * @return
     */
    boolean ismodifyApk(CheckApk checkApk);
}
