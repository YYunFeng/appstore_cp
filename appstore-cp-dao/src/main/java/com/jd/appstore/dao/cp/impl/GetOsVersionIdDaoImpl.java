package com.jd.appstore.dao.cp.impl;

import com.jd.appstore.dao.cp.GetOsVersionIdDao;
import com.jd.common.dao.BaseDao;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-29
 * Time: 下午8:06
 * To change this template use File | Settings | File Templates.
 */
public class GetOsVersionIdDaoImpl extends BaseDao implements GetOsVersionIdDao {

    public Integer getOsVersionId(int osVersionCode) {
        return (Integer)queryForObject("AndroidAppOsVersion.getAndroidAppOsId",osVersionCode);
    }
}
