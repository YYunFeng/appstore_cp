package com.jd.appstore.service.job;

import com.jd.appstore.service.aliyun.AliyunService;

/**
 * Created by YYF on 14-5-21.
 */
public class UploadAliyunJob implements Runnable {
    private AliyunService aliyunService;

    public void run() {
        aliyunService.uploadAliyun();
    }


    public void setAliyunService(AliyunService aliyunService) {
        this.aliyunService = aliyunService;
    }
}
