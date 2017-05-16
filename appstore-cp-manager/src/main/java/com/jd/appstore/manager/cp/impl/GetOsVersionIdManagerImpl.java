package com.jd.appstore.manager.cp.impl;

import com.jd.appstore.dao.cp.GetOsVersionIdDao;
import com.jd.appstore.manager.cp.GetOsVesionIdManager;
import com.jd.common.manager.BaseManager;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-29
 * Time: 下午8:11
 * To change this template use File | Settings | File Templates.
 */
public class GetOsVersionIdManagerImpl extends BaseManager implements GetOsVesionIdManager {
    private GetOsVersionIdDao getOsVersionIdDao;

    public void setGetOsVersionIdDao(GetOsVersionIdDao getOsVersionIdDao) {
        this.getOsVersionIdDao = getOsVersionIdDao;
    }

    public Integer getOsVersionId(int osVersionCode) {
        return getOsVersionIdDao.getOsVersionId(osVersionCode);
    }
}
