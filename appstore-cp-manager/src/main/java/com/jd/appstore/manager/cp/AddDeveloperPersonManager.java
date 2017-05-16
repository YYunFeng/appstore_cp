package com.jd.appstore.manager.cp;

import com.jd.appstore.domain.CpBaseinfo;
import com.jd.appstore.domain.CpReginfo;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-10
 * Time: 下午7:09
 * To change this template use File | Settings | File Templates.
 */
public interface AddDeveloperPersonManager {
    /**
     * 注册帐号
     * @param cpBaseinfo
     * @param cpReginfo
     * @return
     */
    int AddDeveloperPerson(CpBaseinfo cpBaseinfo, CpReginfo cpReginfo);
}
