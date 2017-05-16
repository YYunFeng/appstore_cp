package com.jd.appstore.manager.cp;

import com.jd.appstore.domain.CpBaseinfo;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-5
 * Time: 下午8:28
 * To change this template use File | Settings | File Templates.
 */
public interface CpBaseinfoManager {

    /**
     * 创建对象
     *
     * @param
     */
    int createCpBaseinfo(CpBaseinfo cpBaseinfo);

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

    Integer findCpAccoutId(String account);
}


