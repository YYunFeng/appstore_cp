package com.jd.appstore.manager.cp.impl;

import com.jd.appstore.domain.paramter.BindMobileMess;
import com.jd.appstore.manager.cp.BindMoblieManager;
import com.jd.common.manager.BaseManager;
import com.jd.digital.common.rpc.domain.bean.CommonResult;
import com.jd.digital.common.rpc.manager.sms.SMSManager;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-27
 * Time: 下午9:24
 * To change this template use File | Settings | File Templates.
 */
public class BindMobileManagerImpl extends BaseManager implements BindMoblieManager {
    private SMSManager smsManager;
    
    public CommonResult getMess(BindMobileMess bindMobileMess) {
        CommonResult commonResult = null;
        try {
            commonResult = smsManager.sendSMS(bindMobileMess.getMobileNum(),bindMobileMess.getMess(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("commonResult:" + commonResult);
        return  commonResult;
    }

    public void setSmsManager(SMSManager smsManager) {
        this.smsManager = smsManager;
    }
}
