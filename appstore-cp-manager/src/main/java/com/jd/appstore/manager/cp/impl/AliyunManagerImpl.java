package com.jd.appstore.manager.cp.impl;

import com.jd.appstore.dao.cp.AliyunDao;
import com.jd.appstore.manager.cp.AliyunManager;
import com.jd.common.manager.BaseManager;

import java.util.List;

/**
 * Created by YYF on 14-5-21.
 */
public class AliyunManagerImpl extends BaseManager implements AliyunManager {
    private AliyunDao aliyunDao;

    public List<String> getRes(Integer resType) {
        return aliyunDao.getRes(resType);
    }

    public List<String> getLogo() {
        return aliyunDao.getLogo();
    }


    public void setAliyunDao(AliyunDao aliyunDao) {
        this.aliyunDao = aliyunDao;
    }
}
