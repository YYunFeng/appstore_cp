package com.jd.appstore.dao.cp.impl;

import com.jd.appstore.dao.cp.GetAddressDao;
import com.jd.appstore.domain.web.CityDistrict;
import com.jd.appstore.domain.web.Province;
import com.jd.appstore.domain.web.ProvinceCity;
import com.jd.common.dao.BaseDao;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-29
 * Time: 下午7:11
 * To change this template use File | Settings | File Templates.
 */
public class GetAddressDaoImpl extends BaseDao implements GetAddressDao {
    public List<Province> getProvince() {
        return queryForList("Address.getProprovince");
    }

    public List<ProvinceCity> getProvinceCity(int id) {
        return queryForList("Address.getProvinceCity", id);
    }

    public List<CityDistrict> getCity(int id) {
        return queryForList("Address.getCityDistrict", id);
    }
}
