package com.jd.appstore.manager.cp;

import com.jd.appstore.domain.paramter.ForgetPass;
import com.jd.appstore.domain.web.ForgetPassResult;
import com.jd.appstore.domain.web.RecommendResult;
import com.jd.appstore.domain.web.UserStatus;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-11
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public interface IndexManager {
    UserStatus isHaveApp(int id);

    ForgetPassResult forgetPassResult(ForgetPass forgetPass);

    List<RecommendResult> getRecommend();
}
