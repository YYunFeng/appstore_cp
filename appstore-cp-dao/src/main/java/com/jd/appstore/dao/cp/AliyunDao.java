package com.jd.appstore.dao.cp;

import java.util.List;

/**
 * Created by YYF on 14-5-21.
 */
public interface AliyunDao {

    List<String> getRes(Integer resType);


    List<String> getLogo();
}
