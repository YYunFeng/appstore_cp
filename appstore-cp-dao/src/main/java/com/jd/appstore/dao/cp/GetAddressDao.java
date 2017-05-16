package com.jd.appstore.dao.cp;

import com.jd.appstore.domain.web.CityDistrict;
import com.jd.appstore.domain.web.Province;
import com.jd.appstore.domain.web.ProvinceCity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-29
 * Time: 下午7:08
 * To change this template use File | Settings | File Templates.
 */
public interface GetAddressDao {
    /**
     * 获取省份
     * @return
     */
     List<Province> getProvince();

    /**
     * 获取城市
     * @param id
     * @return
     */
     List<ProvinceCity> getProvinceCity(int id);

    /**
     * 获取区
     * @param id
     * @return
     */
     List<CityDistrict> getCity(int id);
}
