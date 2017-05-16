package com.jd.appstore.service.cp.impl;

import com.jd.appstore.domain.web.Province;
import com.jd.appstore.manager.cp.GetAddressManager;
import com.jd.appstore.service.cp.GetAddressService;
import com.jd.common.web.result.Result;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-29
 * Time: 下午7:17
 * To change this template use File | Settings | File Templates.
 */
public class GetAddressServiceImpl implements GetAddressService {
    private GetAddressManager getAddressManager;

    public List<Province> getProvince() {
        return getAddressManager.getProvince();
    }

    public Result getProvinceCity(int id) {
        Result result = new Result();
        try {
            result.addDefaultModel("provinceCity", getAddressManager.getProvinceCity(id));
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Result getCity(int id) {
        Result result = new Result();
        try{
            result.addDefaultModel("city", getAddressManager.getCity(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public GetAddressManager getGetAddressManager() {
        return getAddressManager;
    }

    public void setGetAddressManager(GetAddressManager getAddressManager) {
        this.getAddressManager = getAddressManager;
    }
}
