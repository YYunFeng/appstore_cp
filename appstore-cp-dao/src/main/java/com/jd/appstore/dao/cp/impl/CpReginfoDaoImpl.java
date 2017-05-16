package com.jd.appstore.dao.cp.impl;

import com.jd.appstore.dao.cp.CpReginfoDao;
import com.jd.appstore.domain.CpReginfo;
import com.jd.common.dao.BaseDao;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-9
 * Time: 上午10:46
 * To change this template use File | Settings | File Templates.
 */
public class CpReginfoDaoImpl extends BaseDao implements CpReginfoDao {

    public void createCpReginfoDao(CpReginfo cpReginfo) {
        insert("CpReginfo.createCpReginfo", cpReginfo);
    }
}
