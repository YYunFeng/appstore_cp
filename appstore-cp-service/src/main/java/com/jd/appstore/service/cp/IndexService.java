package com.jd.appstore.service.cp;

import com.jd.appstore.domain.paramter.ForgetPass;
import com.jd.appstore.domain.web.ForgetPassResult;
import com.jd.common.web.result.Result;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-11
 * Time: 下午7:03
 * To change this template use File | Settings | File Templates.
 */
public interface IndexService {
    
    Result isHaveApp(int id);

    ForgetPassResult forgetPassResult(ForgetPass forgetPass);

    /**
     * 本站登录cookie名称
     *
     * @return
     */
    public String getLoginCookieKey();

    /**
     * 主站登录cookie名称
     *
     * @return
     */
    public String getDotnetAuthCookieName();


    Result getRecommend();
}
