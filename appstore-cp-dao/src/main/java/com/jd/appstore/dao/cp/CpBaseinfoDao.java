package com.jd.appstore.dao.cp;

import com.jd.appstore.domain.CpBaseinfo;
/**
 * User: Administrator
 * Date: 2010-4-15
 * Time: 18:17:34
 */
public interface CpBaseinfoDao {
    /**
     * 插入一个CP帐号
     *
     * @param cpBaseinfo
     */
    void createCpBaseinfo(CpBaseinfo cpBaseinfo);

    /**
     * 验证登录
     *
     * @param cpBaseinfo
     * @return
     */
    CpBaseinfo findCpBaseinfo(CpBaseinfo cpBaseinfo);

    /**
     * 根据用户pin查询用户是否存在
     * @param userPin
     * @return
     */
    boolean findCpBaseinfoByPin(String userPin);

    /**
     * 获得CP帐号的accoutId
     * @param account
     * @return
     */
    Integer findCpAccoutId(String account);
}
