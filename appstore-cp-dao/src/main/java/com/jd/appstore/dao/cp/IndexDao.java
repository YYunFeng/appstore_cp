package com.jd.appstore.dao.cp;

import com.jd.appstore.domain.paramter.ForgetPass;
import com.jd.appstore.domain.web.ForgetPassResult;
import com.jd.appstore.domain.web.RecommendResult;
import com.jd.appstore.domain.web.UserStatus;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-11
 * Time: 下午3:29
 * To change this template use File | Settings | File Templates.
 */
public interface IndexDao {
    /**
     * 验证该用户下是否有应用
     *
     * @param id
     * @return
     */
    List<UserStatus> isHaveApp(int id);

    /**
     * 验证忘记密码时候,用户名和邮箱是否一致
     * @param forgetPass
     * @return
     */
    ForgetPassResult forgetPassResult(ForgetPass forgetPass);

    List<RecommendResult> getRecommend();
}
