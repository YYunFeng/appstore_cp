package com.jd.appstore.service.aliyun;

import com.aliyun.openservices.oss.model.CopyObjectResult;

import java.io.FileNotFoundException;

/**
 * 该接口为阿里云的开放存储接口
 */
public interface AliyunService {


    /**
     * 新建Bucket
     */
    void createBucket(String bucketName);

    /**
     * 上传Object
     *
     * @param filePath
     * @return
     */
    String putObject(String filePath, String key, int flag) throws FileNotFoundException;


    /**
     * 拷贝Object
     *
     * @param key
     * @param destinationKey
     * @return
     */
    CopyObjectResult copyObject(String key, String destinationKey) throws Exception;


    void uploadAliyun();
}
