package com.jd.appstore.dao.cp.impl;

import com.jd.appstore.dao.cp.IndexDao;
import com.jd.appstore.domain.paramter.ForgetPass;
import com.jd.appstore.domain.web.ForgetPassResult;
import com.jd.appstore.domain.web.RecommendResult;
import com.jd.appstore.domain.web.UserStatus;
import com.jd.common.dao.BaseDao;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-11
 * Time: 下午3:32
 * To change this template use File | Settings | File Templates.
 */
public class IndexDaoImpl extends BaseDao implements IndexDao {
    public List<UserStatus> isHaveApp(int id) {
        return queryForList("AppDetails.isHavaApp", id);
    }

    public ForgetPassResult forgetPassResult(ForgetPass forgetPass) {
        return (ForgetPassResult)queryForObject("Index.forgetPass",forgetPass);
    }

    public List<RecommendResult> getRecommend() {
        return queryForList("Recommend.getRecommend");
    }

}
