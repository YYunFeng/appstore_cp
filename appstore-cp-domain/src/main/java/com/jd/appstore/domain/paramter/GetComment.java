package com.jd.appstore.domain.paramter;

import com.jd.common.util.base.BaseQuery;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-28
 * Time: 下午10:20
 * To change this template use File | Settings | File Templates.
 */
public class GetComment extends BaseQuery {
    private String appId;
    private int score;
  
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
