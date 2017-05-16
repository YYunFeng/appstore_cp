package com.jd.appstore.web.action.cp;


import com.jd.appstore.action.AppStoreBaseAction;
import com.jd.digital.common.util.cache.CacheUtils;
import com.jd.digital.common.util.tool.ValidateCodeUtil;

/**
 * 验证码
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-3
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
public class AuthCodeAction extends AppStoreBaseAction {
    private CacheUtils cacheUtils;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setCacheUtils(CacheUtils cacheUtils) {
        this.cacheUtils = cacheUtils;
    }

    /**
     * 获取验证码
     */
    public void getAuthCode() {
        try {
            ValidateCodeUtil.validatecode(cacheUtils, response, uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
