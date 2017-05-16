package com.jd.appstore.web.util.cp;

import com.jd.appstore.domain.*;
import com.jd.common.util.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-11-21
 * Time: 下午6:01
 * To change this template use File | Settings | File Templates.
 */
public class AuthMess {

    public static AuthMessJSON AuthMess(AppLog appLog, AppDetailLog appDetailLog, AppResource appResource, Integer appType) {
        AuthMessJSON authMessJSON = new AuthMessJSON();
        // 验证安卓应用提交信息
        if (appType != null && appType == 0) {
            if (appLog != null && appDetailLog != null && appResource != null) {
                if (appLog.getAppName() != null && appLog.getAppName().length() > 30) {
                    authMessJSON.setStatus(false);
                    authMessJSON.setCode("1");
                    authMessJSON.setMess("应用名称超长,请输入少于30字的应用名称");
                    return authMessJSON;
                }
                if (appLog.getPkg() != null && appLog.getPkg().length() > 150) {
                    authMessJSON.setStatus(false);
                    authMessJSON.setCode("2");
                    authMessJSON.setMess("上传失败,错误原因：您的apk包包名长度超过最大限制150位");
                    return authMessJSON;
                }

                if (appDetailLog.getAppVersion() != null && appDetailLog.getAppVersion().length() > 30) {
                    authMessJSON.setStatus(false);
                    authMessJSON.setCode("3");
                    authMessJSON.setMess("上传失败,错误原因：您的apk包版本号长度超过最大限制30位");
                    return authMessJSON;
                }
                if (appDetailLog.getAppTag() != null && appDetailLog.getAppTag().length() > 200) {
                    authMessJSON.setStatus(false);
                    authMessJSON.setCode("4");
                    authMessJSON.setMess("应用关键字超长,请输入少于200字的标签");
                    return authMessJSON;
                }
                if (appDetailLog.getAppVersionCode() == null) {
                    authMessJSON.setStatus(false);
                    authMessJSON.setCode("4");
                    authMessJSON.setMess("上传失败,错误原因：您的apk包VersionCode不正确");
                    return authMessJSON;
                }
            } else {
                authMessJSON.setStatus(false);
                authMessJSON.setCode("-1");
                authMessJSON.setMess("系统异常,请重试!");
                return authMessJSON;
            }


        }
        // 验证网页应用提交信息
        if (appType != null && appType == 1) {
            if (appLog != null && appDetailLog != null && appResource != null) {
                if (appLog.getAppName() != null && appLog.getAppName().length() > 30) {
                    authMessJSON.setStatus(false);
                    authMessJSON.setCode("1");
                    authMessJSON.setMess("应用名称超长,请输入少于30字的应用名称");
                    return authMessJSON;
                }
                if (appDetailLog.getAppVersion() != null && appDetailLog.getAppVersion().length() > 10) {
                    authMessJSON.setStatus(false);
                    authMessJSON.setCode("3");
                    authMessJSON.setMess("应用版本号超长,请输入少于10字的版本号");
                    return authMessJSON;
                }
                if (appDetailLog.getAppTag() != null && appDetailLog.getAppTag().length() > 200) {
                    authMessJSON.setStatus(false);
                    authMessJSON.setCode("4");
                    authMessJSON.setMess("应用关键字超长,请输入少于200字的标签");
                    return authMessJSON;
                }
                if (appResource.getResUrl() != null && appResource.getResUrl().length() > 200) {
                    authMessJSON.setStatus(false);
                    authMessJSON.setCode("5");
                    authMessJSON.setMess("应用URL地址超长,请输入少于200字的URL地址");
                    return authMessJSON;
                }
            } else {
                authMessJSON.setStatus(false);
                authMessJSON.setCode("-1");
                authMessJSON.setMess("系统异常,请重试!");
                return authMessJSON;
            }

        }
        authMessJSON.setStatus(true);
        authMessJSON.setCode("0");
        authMessJSON.setMess("成功");
        return authMessJSON;
    }
}
