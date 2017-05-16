package com.jd.appstore.service.cp;

import com.jd.appstore.domain.web.Province;
import com.jd.appstore.domain.web.ProvinceCity;
import com.jd.common.web.result.Result;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-29
 * Time: 下午7:16
 * To change this template use File | Settings | File Templates.
 */
public interface GetAddressService {
    /**
     * 获取省份
     *
     * @return
     */
    List<Province> getProvince();

    /**
     * 获取城市
     *
     * @param id
     * @return
     */
    Result getProvinceCity(int id);

    /**
     * 获取区
     *
     * @param id
     * @return
     */
    Result getCity(int id);
}
