package com.jd.appstore.service.cp;

import com.jd.appstore.domain.CpBaseinfo;
import com.jd.appstore.domain.CpReginfo;
import com.jd.common.web.result.Result;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-10
 * Time: 下午7:29
 * To change this template use File | Settings | File Templates.
 */
public interface AddDeveloperService {
    Result createDeveloperService(CpBaseinfo cpBaseinfo, CpReginfo cpReginfo);
}
