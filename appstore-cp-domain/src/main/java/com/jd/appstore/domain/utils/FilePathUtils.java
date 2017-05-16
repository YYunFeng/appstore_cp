package com.jd.appstore.domain.utils;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-31
 * Time: 下午4:01
 * To change this template use File | Settings | File Templates.
 */
public class FilePathUtils {

    private String androidApkPath;
    private String webSwfPath;
    private String coverPicPath;
    private String imgPicPath;
    private String picPath;

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getAndroidApkPath() {
        return androidApkPath;
    }

    public void setAndroidApkPath(String androidApkPath) {
        this.androidApkPath = androidApkPath;
    }

    public String getWebSwfPath() {
        return webSwfPath;
    }

    public void setWebSwfPath(String webSwfPath) {
        this.webSwfPath = webSwfPath;
    }

    public String getCoverPicPath() {
        return coverPicPath;
    }

    public void setCoverPicPath(String coverPicPath) {
        this.coverPicPath = coverPicPath;
    }

    public String getImgPicPath() {
        return imgPicPath;
    }

    public void setImgPicPath(String imgPicPath) {
        this.imgPicPath = imgPicPath;
    }
}
