package com.jd.appstore.web.action.cp;

import com.jd.appstore.service.job.UploadAliyunJob;
import com.jd.common.struts.action.BaseAction;

/**
 * 该Action用于上传文件到阿里云的存储服务器
 */
public class UploadAliyunAction extends BaseAction {
    private UploadAliyunJob uploadAliyunJob;

    public String uploadAliyun() {
        Thread thread = new Thread(uploadAliyunJob);
        thread.start();
        return SUCCESS;
    }


    public void setUploadAliyunJob(UploadAliyunJob uploadAliyunJob) {
        this.uploadAliyunJob = uploadAliyunJob;
    }
}
