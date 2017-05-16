package com.jd.appstore.dao.cp.impl;

import com.jd.appstore.dao.cp.CpBaseinfoDao;
import com.jd.appstore.domain.CpBaseinfo;
import com.jd.common.dao.BaseDao;

/**
 * User: Administrator
 * Date: 2010-4-15
 * Time: 18:23:46
 */
@SuppressWarnings("unchecked")
public class CpBaseinfoDaoImpl extends BaseDao implements CpBaseinfoDao {
    public void createCpBaseinfo(CpBaseinfo cpBaseinfo) {
        insert("CpBaseinfo.createCpBaseinfo", cpBaseinfo);
    }

    public CpBaseinfo findCpBaseinfo(CpBaseinfo cpBaseinfo) {
        return (CpBaseinfo) queryForObject("CpBaseinfo.findCpBaseinfos", cpBaseinfo);
    }

    public boolean findCpBaseinfoByPin(String userPin) {
        CpBaseinfo cpBaseinfo = new CpBaseinfo();
        cpBaseinfo.setAccount(userPin);
        Integer count = (Integer) queryForObject("CpBaseinfo.findCpBaseinfoByPin", cpBaseinfo);
        return count > 0;
    }

    public Integer findCpAccoutId(String account) {
        return (Integer)queryForObject("CpBaseinfo.findCpAccountId",account);
    }


}
