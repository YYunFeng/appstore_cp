package com.jd.appstore.manager.cp;

import com.jd.appstore.domain.paramter.BindMobileMess;
import com.jd.digital.common.rpc.domain.bean.CommonResult;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-27
 * Time: 下午9:22
 * To change this template use File | Settings | File Templates.
 */
public interface BindMoblieManager {
    CommonResult getMess(BindMobileMess bindMobileMess);
}
