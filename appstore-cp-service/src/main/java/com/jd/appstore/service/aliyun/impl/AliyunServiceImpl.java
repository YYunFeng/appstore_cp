package com.jd.appstore.service.aliyun.impl;

import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.*;
import com.jd.appstore.domain.utils.UploadDirUtils;
import com.jd.appstore.manager.cp.AliyunManager;
import com.jd.appstore.service.aliyun.AliyunService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.tools.ant.taskdefs.Copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * 该接口为阿里云的开放存储接口
 */
public class AliyunServiceImpl implements AliyunService {
    private String bucketName;
    private String accessKeyId;
    private String accessKeySecret;
    private String tempBucketName;
    private String endpoint;

    private static final Log logger = LogFactory.getLog(AliyunServiceImpl.class);


    private AliyunManager aliyunManager;


    public void createBucket(String bucketName) {
        OSSClient ossClient = new OSSClient(accessKeyId, accessKeySecret);
        ossClient.createBucket(bucketName);
    }

    /**
     * 上传一个Object
     *
     * @param filePath
     * @return
     */
    public String putObject(String filePath, String key, int flag) throws FileNotFoundException {

        // 创建ClientConfiguration实例
        ClientConfiguration conf = new ClientConfiguration();
        conf.setSocketTimeout(2000);
        conf.setConnectionTimeout(3000);
        conf.setMaxErrorRetry(3);

        // 初始化OSSClient
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
        logger.info("endpoint:" + endpoint);
        logger.info("上传文件目录：" + filePath);
        logger.info("上传文件名：" + key);
        // 获取指定文件的输入流
        File file = new File(filePath);
        InputStream content = new FileInputStream(file);

        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();

        // 必须设置ContentLength
        meta.setContentLength(file.length());
        // 上传Object
        PutObjectResult result = null;

        if (flag == 0) {   // 上传到正式的Bucket
            result = client.putObject(bucketName, key, content, meta);
            logger.info("上传正式文件成功" +bucketName);
        } else if (flag == 1) { // 上传到临时的Bucket
            result = client.putObject(tempBucketName, key, content, meta);
            logger.info("上传正式文件成功" +bucketName);
            logger.info("上传正式文件成功");
        }
        // 打印ETag
        String eTag = result.getETag();
        logger.info(eTag);
        return eTag;
    }

    public CopyObjectResult copyObject(String key, String destinationKey) throws Exception {
        ClientConfiguration conf = new ClientConfiguration();
        conf.setSocketTimeout(2000);
        conf.setConnectionTimeout(3000);
        conf.setMaxErrorRetry(3);
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
        logger.info("拷贝的文件为：" + key);
        boolean isCopy = false;
        try {
            if(client.getObjectMetadata(tempBucketName, key).getETag()!=null){
                isCopy = true;
            }
        } catch (OSSException e) {
            if (e.getErrorCode().equals("NoSuchKey")) {
                isCopy = false;
                logger.info("不存在临时文件");
            } else {
                e.printStackTrace();
            }
        }
        if(isCopy){
            CopyObjectResult copyObjectResult = client.copyObject(tempBucketName, key, bucketName, destinationKey);
            logger.info("拷贝成功");
            logger.info("eTag:" + copyObjectResult.getETag());
            return copyObjectResult;
        }
        return null;
    }


    /**
     * 将所有文件到阿里云的服务器
     */
    public void uploadAliyun() {
        try {
            String path = "/alidata1/www/img.wifi.taotaojing.cn/";
            // 上传APK文件到阿里云
            List<String> list = aliyunManager.getRes(1);
            int a = 0;
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (StringUtils.isNotBlank(list.get(i))) {
                        try {
                            String eTag = putObject(path + UploadDirUtils.APK + list.get(i), UploadDirUtils.APK + list.get(i), 0);
                            if (eTag != null) {
                                a = a + 1;
                            }
                            logger.info("上传APK文件成功：" + a + "个");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            // 上传图片至阿里云
            List<String> stringList = aliyunManager.getRes(0);
            int b = 0;
            if (stringList != null && stringList.size() > 0) {
                for (int i = 0; i < stringList.size(); i++) {
                    if (StringUtils.isNotBlank(stringList.get(i))) {
                        try {
                            String eTag = putObject(path + UploadDirUtils.IMG_PIC + stringList.get(i), UploadDirUtils.IMG_PIC + stringList.get(i), 0);
                            if (eTag != null) {
                                b = b + 1;
                            }
                            logger.info("上传截图成功：" + b + "个");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            // 上传Logo至阿里云
            List<String> strings = aliyunManager.getLogo();
            int c = 0;
            if (strings != null && strings.size() > 0) {
                for (int i = 0; i < strings.size(); i++) {
                    if (StringUtils.isNotBlank(strings.get(i))) {
                        try {
                            String eTag = putObject(path + UploadDirUtils.COVER + strings.get(i), UploadDirUtils.COVER + strings.get(i), 0);
                            if (eTag != null) {
                                c = c + 1;
                            }
                            logger.info("上传LOGO成功：" + c + "个");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   /* public static void main(String arg[]) throws FileNotFoundException {
        String accessKeyId = "LgHZPO39U1Tl2htk";
        String accessKeySecret = "sRcCpDJRfVSxyXSSESeF8KoLkW8LM";
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";

        // 创建ClientConfiguration实例
        ClientConfiguration conf = new ClientConfiguration();
        conf.setSocketTimeout(2000);
        conf.setConnectionTimeout(3000);
        conf.setMaxErrorRetry(3);


        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);

//        ossClient.createBucket("test-beijing-test");

        String filePath = "D:\\data\\apk\\81479E6826AD48F7A8CF95697875F43E.apk";
        File file = new File(filePath);
        InputStream content = new FileInputStream(file);

        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();


        // 必须设置ContentLength
        meta.setContentLength(file.length());
        // 上传Object
        PutObjectResult result = ossClient.putObject("liuxingyuan-ppad-leyu", "apk/53415B57732B/81479E6826AD48F7A8CF95697875F43E.apk", content, meta);
        System.out.print(result.getETag());
    }*/


    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }


    public String getTempBucketName() {
        return tempBucketName;
    }

    public void setTempBucketName(String tempBucketName) {
        this.tempBucketName = tempBucketName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }


    public void setAliyunManager(AliyunManager aliyunManager) {
        this.aliyunManager = aliyunManager;
    }
}
