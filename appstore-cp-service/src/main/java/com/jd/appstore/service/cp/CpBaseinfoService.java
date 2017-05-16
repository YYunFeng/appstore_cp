package com.jd.appstore.service.cp;

import com.jd.appstore.domain.CpBaseinfo;
import com.jd.common.web.result.Result;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-5
 * Time: 下午8:17
 * To change this template use File | Settings | File Templates.
 */
public interface CpBaseinfoService {

    /**
     * 登录后，获得用户信息
     *
     * @return
     */
    CpBaseinfo findCpBaseinfo(CpBaseinfo cpBaseinfo);

    /**
     * 根据pin查询用户是否存在
     *
     * @return
     */
    boolean findCpBaseinfoByPin(String userPin);

    /**
     * 查看cp的信息，
     * @param cpBaseinfo
     * @return
     */
    Result getCpBaseinfo(CpBaseinfo cpBaseinfo);

    /**
     * 查看密码是否正确
     * @param cpBaseinfo
     * @return
     */
     int checkCpBaseinfo(CpBaseinfo cpBaseinfo);
    /**
     * 创建一个CP用户
     *
     * @param cpBaseinfo
     * @return
     */
    Result createCpBaseinfo(CpBaseinfo cpBaseinfo);

    /**
     * 得到accountId
     * @param account
     * @return
     */
    Integer findCpAccoutId(String account);

}
