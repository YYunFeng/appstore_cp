package com.jd.appstore.dao.cp.impl;

import com.jd.appstore.dao.cp.AliyunDao;
import com.jd.common.dao.BaseDao;

import java.util.List;

/**
 * Created by YYF on 14-5-21.
 */
public class AliyunDaoImpl extends BaseDao implements AliyunDao {
    public List<String> getRes(Integer resType) {
        return queryForList("Aliyun.getRes", resType);
    }

    public List<String> getLogo() {
        return queryForList("Aliyun.getLogo");
    }
}
