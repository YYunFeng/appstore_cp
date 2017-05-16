package com.jd.appstore.manager.cp.impl;

import com.jd.appstore.dao.cp.IndexDao;
import com.jd.appstore.domain.paramter.ForgetPass;
import com.jd.appstore.domain.web.ForgetPassResult;
import com.jd.appstore.domain.web.RecommendResult;
import com.jd.appstore.domain.web.UserStatus;
import com.jd.appstore.manager.cp.IndexManager;
import com.jd.common.manager.BaseManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-11
 * Time: 下午6:31
 * To change this template use File | Settings | File Templates.
 */
public class IndexManagerImpl extends BaseManager implements IndexManager {
    private IndexDao indexDao;

    public IndexDao getIndexDao() {
        return indexDao;
    }

    public void setIndexDao(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    /**
     * 统计该用户的详情，用于登录页用户查看所上传的应用
     *
     * @param id
     * @return
     */
    public UserStatus isHaveApp(int id) {
        UserStatus userStatus = new UserStatus();
        List<UserStatus> list = indexDao.isHaveApp(id);
        if (list.equals("") || list.size() == 0) {
            userStatus.setFlag("isEmpty");
        } else {
            Integer total = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getOnline() == 0) {
                    userStatus.setNotOnlineCount(list.get(i).getCount());
                    total = total + list.get(i).getCount();
                }
                if (list.get(i).getOnline() == 1) {
                    userStatus.setOnlineCount(list.get(i).getCount());
                    total = total + list.get(i).getCount();
                }
                if (list.get(i).getOnline() == 2) {
                    userStatus.setOutOnlineCount(list.get(i).getCount());
                    total = total + list.get(i).getCount();
                }
            }
            userStatus.setTotalCount(total);
            userStatus.setFlag("noEmpty");
        }
        return userStatus;
    }

    public ForgetPassResult forgetPassResult(ForgetPass forgetPass) {
        return indexDao.forgetPassResult(forgetPass);
    }

    public List<RecommendResult> getRecommend() {
        List<RecommendResult> recommendResults = indexDao.getRecommend();
       /* for(RecommendResult recommendResult : recommendResults){
            recommendResult.setPicUrl(PicResolutionEnum.N0.getPrefix()+recommendResult.getPicUrl());
        }*/
        return recommendResults;
    }
}
