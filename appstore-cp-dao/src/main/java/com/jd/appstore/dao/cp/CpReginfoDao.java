package com.jd.appstore.dao.cp;

import com.jd.appstore.domain.CpReginfo;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-9
 * Time: 上午10:44
 * To change this template use File | Settings | File Templates.
 */
public interface CpReginfoDao {
    /**
     * 插入cp注册信息
     * @param cpReginfo
     */
    void createCpReginfoDao(CpReginfo cpReginfo);
}
