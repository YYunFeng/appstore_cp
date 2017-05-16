package com.jd.appstore.domain.json;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-13
 * Time: 上午11:39
 * To change this template use File | Settings | File Templates.
 */
public class UploadJSON  {
    private String savePath; //上传后存储的地址
    private String fileName; //文件名
    private String msg ;//上传状态

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
