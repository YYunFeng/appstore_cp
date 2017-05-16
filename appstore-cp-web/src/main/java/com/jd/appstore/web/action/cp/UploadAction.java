package com.jd.appstore.web.action.cp;

import com.jd.appstore.action.AppStoreBaseAction;
import com.jd.appstore.domain.Apps;
import com.jd.appstore.domain.json.ApkMessJSON;
import com.jd.appstore.domain.json.AuthApkJSON;
import com.jd.appstore.domain.utils.FilePathUtils;
import com.jd.appstore.domain.utils.UploadDirUtils;
import com.jd.appstore.domain.utils.Utils;
import com.jd.appstore.service.aliyun.AliyunService;
import com.jd.appstore.web.util.cp.AnalysisApk;
import com.jd.digital.common.rpc.manager.jss.JssAppManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import java.io.File;
import java.io.IOException;
/*

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
*/


/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-12
 * Time: 下午5:17
 * To change this template use File | Settings | File Templates.
 */
public class UploadAction extends AppStoreBaseAction {

    private File uploadFile;
    private String uploadContentType;
    private String uploadFileName;
    private String fileName;
    private JssAppManager jssAppManager;
    private FilePathUtils filePathUtils;
    private Apps apps;
    private String appLogo;
    private Integer appId;
    private String appName;
    private String account;

    private AliyunService aliyunService;

    /**
     * 上传APK文件到本地
     *
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public String uploadApk() throws IOException, JSONException {
        String targetDirectory = filePathUtils.getAndroidApkPath() + account;
        String extName = "";
        String newFileName = "";
        String names[] = ((MultiPartRequestWrapper) this.request).getFileNames("uploadFile");
        if (names[0].lastIndexOf(".") >= 0) {
            extName = names[0].substring(names[0].lastIndexOf("."));
        }
        newFileName = Utils.getUuid() + extName;
        try {
            File target = new File(targetDirectory, newFileName);
            FileUtils.copyFile(uploadFile, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().write(account + '/' + newFileName);
        return null;
    }

    /**
     * 上传WEB至本地
     *
     * @return
     */
    public String uploadWeb() {
        String str = null;
        try {
            String targetDirectory = filePathUtils.getWebSwfPath() + account;
            String extName = "";
            String newFileName = "";
            String names[] = ((MultiPartRequestWrapper) this.request).getFileNames("uploadFile");
            if (names[0].lastIndexOf(".") >= 0) {
                extName = names[0].substring(names[0].lastIndexOf("."));
            }
            newFileName = Utils.getUuid() + extName;
            File target = new File(targetDirectory, newFileName);
            FileUtils.copyFile(uploadFile, target);
            response.getWriter().write(account + '/' + newFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取APK文件
     *
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public String readApk() throws IOException, JSONException {
        ApkMessJSON apkMessJSON = new ApkMessJSON();
        try {
            String apkPath = filePathUtils.getAndroidApkPath() + fileName;
            apkMessJSON = AnalysisApk.unZip(apkPath);
            apkMessJSON.setApkName(apkPath);
            apkMessJSON.setFlag(1);
        } catch (Exception e) {
            apkMessJSON.setFlag(0);
            apkMessJSON.setMess("系统错误，请重试!");
            e.printStackTrace();
        }
        String json = JSONUtil.serialize(apkMessJSON);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
        return null;

    }

    public String uploadApkJss() throws IOException, JSONException {
        String str = null;
        AuthApkJSON authApkJSON = new AuthApkJSON();
        try {
            File apkFike = new File(apps.getAppName());
            String extName = null;
            if (apps.getAppName().lastIndexOf(".") >= 0) {
                extName = apps.getAppName().substring(apps.getAppName().lastIndexOf("."));
            }
            str = jssAppManager.inRights(apps.getAppName(), apps.getPkg() + System.currentTimeMillis() + extName);
            //删除临时文件，直到成功
            int c = 0;
            while (!apkFike.delete() && c < 200) {
                c++;
                Thread.sleep(50);
            }
            if (StringUtils.isBlank(str)) {
                authApkJSON.setStatus(0);
                authApkJSON.setMess("上传APK错误，请重试");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(JSONUtil.serialize(authApkJSON));
                return null;
            }
            authApkJSON.setApkFileName(str);
            authApkJSON.setStatus(1);
        } catch (Exception e) {
            authApkJSON.setStatus(0);
            authApkJSON.setMess("系统错误，请重试");
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONUtil.serialize(authApkJSON));
        return null;
    }

    /**
     * 上传LOGO
     *
     * @throws IOException
     */
    public void uploadLogo() throws IOException {
        String targetDirectory = filePathUtils.getCoverPicPath() + account;
        String extName = "";
        String newFileName = "";
        String names[] = ((MultiPartRequestWrapper) this.request).getFileNames("uploadFile");
        String str = null;
        if (names[0].lastIndexOf(".") >= 0) {
            extName = names[0].substring(names[0].lastIndexOf("."));
        }
        newFileName = Utils.getUuid() + extName;
        File target = new File(targetDirectory, newFileName);
        FileUtils.copyFile(uploadFile, target);
        String filePath = targetDirectory + "/" + newFileName;
        String path = aliyunService.putObject(filePath, UploadDirUtils.COVER + account + "/" + newFileName, 1);
        response.getWriter().write(account + '/' + newFileName);
    }

    /**
     * 上传应用应用截图到本地
     *
     * @return
     * @throws IOException
     */
    public String uploadPic() throws IOException, JSONException {
        String targetDirectory = filePathUtils.getImgPicPath() + account;
        String extName = "";
        String newFileName = "";
        String names[] = ((MultiPartRequestWrapper) this.request).getFileNames("uploadFile");
        String str = null;
        if (names[0].lastIndexOf(".") >= 0) {
            extName = names[0].substring(names[0].lastIndexOf("."));
        }
        newFileName = Utils.getUuid() + extName;

        File target = new File(targetDirectory, newFileName);
        FileUtils.copyFile(uploadFile, target);

        // 调用阿里云的开放性存储服务器
        String filePath = targetDirectory + "/" + newFileName;
        String path = aliyunService.putObject(filePath, UploadDirUtils.IMG_PIC + account + "/" + newFileName, 1);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(account + '/' + newFileName);
        return null;
    }


    public File getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public void setJssAppManager(JssAppManager jssAppManager) {
        this.jssAppManager = jssAppManager;
    }


    public void setFilePathUtils(FilePathUtils filePathUtils) {
        this.filePathUtils = filePathUtils;
    }

    public Apps getApps() {
        return apps;
    }

    public void setApps(Apps apps) {
        this.apps = apps;
    }

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAliyunService(AliyunService aliyunService) {
        this.aliyunService = aliyunService;
    }
}
