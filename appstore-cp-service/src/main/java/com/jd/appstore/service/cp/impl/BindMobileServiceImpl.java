package com.jd.appstore.service.cp.impl;

import com.jd.appstore.domain.constant.BindMobileConstants;
import com.jd.appstore.domain.paramter.BindMobileMess;
import com.jd.appstore.domain.utils.Utils;
import com.jd.appstore.manager.cp.BindMoblieManager;
import com.jd.appstore.service.cp.BindMoblieService;
import com.jd.digital.common.rpc.domain.bean.CommonResult;
import com.jd.digital.common.util.cache.CacheUtils;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-27
 * Time: 下午9:20
 * To change this template use File | Settings | File Templates.
 */
public class BindMobileServiceImpl implements BindMoblieService {
    private BindMoblieManager bindMoblieManager;
    private CacheUtils cacheUtils;


    public CommonResult getMess(BindMobileMess bindMobileMess) {
        StringBuffer mess = new StringBuffer();
        Random rand = new Random();
        String messAddress = Utils.getUuid()+"BINDMOBIE";
        for (int i = 0; i < 4; i++) {
            mess.append(rand.nextInt(9));
        }
        System.out.println("mess--------->:" + mess);
        cacheUtils.add(messAddress, BindMobileConstants.BIND_MOBILE_TIME, mess);
        bindMobileMess.setMess(mess.toString());
        CommonResult commonResult = bindMoblieManager.getMess(bindMobileMess);;
        commonResult.setCode(messAddress.toString());
        return commonResult;
    }
}
