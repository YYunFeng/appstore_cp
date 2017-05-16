package com.jd.appstore.manager.cp.impl;

import com.jd.appstore.dao.cp.GetAddressDao;
import com.jd.appstore.domain.web.CityDistrict;
import com.jd.appstore.domain.web.Province;
import com.jd.appstore.domain.web.ProvinceCity;
import com.jd.appstore.manager.cp.GetAddressManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-29
 * Time: 下午7:14
 * To change this template use File | Settings | File Templates.
 */
public class GetAddressManagerImpl implements GetAddressManager {
    private GetAddressDao getAddressDao;

    public List<Province> getProvince() {
        return getAddressDao.getProvince();
    }

    public List<ProvinceCity> getProvinceCity(int id) {
        return getAddressDao.getProvinceCity(id);
    }

    public List<CityDistrict> getCity(int id) {
        return getAddressDao.getCity(id);
    }

    public void setGetAddressDao(GetAddressDao getAddressDao) {
        this.getAddressDao = getAddressDao;
    }
}
