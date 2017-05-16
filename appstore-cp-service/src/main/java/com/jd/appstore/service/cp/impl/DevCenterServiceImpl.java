package com.jd.appstore.service.cp.impl;

import com.aliyun.openservices.oss.model.CopyObjectResult;
import com.jd.appstore.domain.*;
import com.jd.appstore.domain.constant.CommonContants;
import com.jd.appstore.domain.enumtype.PicResolutionEnum;
import com.jd.appstore.domain.json.AuthApkJSON;
import com.jd.appstore.domain.paramter.CheckApk;
import com.jd.appstore.domain.paramter.GetComment;
import com.jd.appstore.domain.utils.FilePathUtils;
import com.jd.appstore.domain.utils.UploadDirUtils;
import com.jd.appstore.domain.utils.VerifyCode;
import com.jd.appstore.domain.web.CheckApkResult;
import com.jd.appstore.domain.web.CommentSummaryReuslt;
import com.jd.appstore.manager.cp.DevCenterManager;
import com.jd.appstore.manager.cp.GetAddressManager;
import com.jd.appstore.manager.cp.GetOsVesionIdManager;
import com.jd.appstore.service.aliyun.AliyunService;
import com.jd.appstore.service.cp.DevCenterService;
import com.jd.common.util.PaginatedList;
import com.jd.common.util.StringEscapeUtils;
import com.jd.common.util.StringUtils;
import com.jd.common.util.base.PaginatedArrayList;
import com.jd.common.web.result.Result;
import com.jd.digital.common.rpc.domain.bean.comment.CommentBo;
import com.jd.digital.common.rpc.domain.bean.comment.CommentSummaryBo;
import com.jd.digital.common.rpc.domain.bean.userinfo.UserInfoBean;
import com.jd.digital.common.rpc.manager.comment.CommentManager;
import com.jd.digital.common.rpc.manager.userinfo.UserInfoManager;
import com.jd.digital.common.util.tool.MD5Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-15
 * Time: 下午6:59
 * To change this template use File | Settings | File Templates.
 */
public class DevCenterServiceImpl implements DevCenterService {
    private final static Log log = LogFactory.getLog(DevCenterServiceImpl.class);
    private DevCenterManager devCenterManagerCached;
    private CommentManager commentManager;
    private GetAddressManager getAddressManager;
    private GetOsVesionIdManager getOsVersionIdManager;
    private UserInfoManager userInfoManager;
    private FilePathUtils filePathUtils;
    // 阿里云开放式存储服务
    private AliyunService aliyunService;


    /**
     * 创建应用
     *
     * @param appLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    public Result createApk(AppLog appLog, AppDetailLog appDetailLog, AppResource appResource) throws UnsupportedEncodingException {
        Result result = new Result();
        try {
            appLog.setBaseCheckStatus(2);
            appLog.setBaseSyncFlag(0);
            appLog.setOnlineSyncFlag(0);
            appLog.setOnlineOptType(1);
            appDetailLog.setOnline(0);

            // 获取APK文件的校验码
            File apkFile = new File(filePathUtils.getAndroidApkPath() + appResource.getApkName());
            VerifyCode verifyCode = new VerifyCode(apkFile);
            appDetailLog.setApkCode(verifyCode.verifyCode());
            appDetailLog.setZhuowangMark(0);

            String intro = URLDecoder.decode(appDetailLog.getAppIntroduce(), "UTF-8");
            intro = intro.replaceAll("<br>", "\n");
            intro = StringEscapeUtils.escapeHtml(intro);
            appDetailLog.setAppIntroduce(intro);
            appResource.setPics(appResource.getPics()[0].split(","));
            Integer osVersionId = null;
            if (appDetailLog.getMinSdkVersion() != null && appDetailLog.getMinSdkVersion().intValue() != 0) {
                osVersionId = getOsVersionIdManager.getOsVersionId(appDetailLog.getMinSdkVersion());
                if (osVersionId != null && osVersionId.intValue() != 0) {
                    appDetailLog.setOsVersionId(osVersionId);
                } else {
                    appDetailLog.setOsVersionId(CommonContants.OS_VERSION_ID);
                }
            } else {
                appDetailLog.setOsVersionId(CommonContants.OS_VERSION_ID);
            }
            if (appDetailLog.getFeeMode() == 0) {
                appDetailLog.setPriceSyncFlag(0);
            } else {
                appDetailLog.setPriceSyncFlag(1);
            }
            try {
                // 上传APK文件到阿里云
                aliyunService.putObject(filePathUtils.getAndroidApkPath() + appResource.getApkName(), UploadDirUtils.APK + appResource.getApkName(), 0);
                // 上传LOGO文件到阿里云
                if (StringUtils.isNotBlank(appDetailLog.getLogoUrl())) {
                    aliyunService.copyObject(UploadDirUtils.COVER + appDetailLog.getLogoUrl(), UploadDirUtils.COVER + appDetailLog.getLogoUrl());
                }
                // 上传软件截图文件到阿里云
                if (appResource.getPics() != null && appResource.getPics().length > 0) {
                    for (int i = 0; i < appResource.getPics().length; i++) {
                        aliyunService.copyObject(UploadDirUtils.IMG_PIC + appResource.getPics()[i], UploadDirUtils.IMG_PIC + appResource.getPics()[i]);
                    }
                }
                result.setSuccess(true);
            } catch (Exception e) {
                result.setSuccess(false);
                e.printStackTrace();
            }
            // 如果上传文件到阿里云服务成功的话，则往数据库中写入数据
            int exampleId = 1;
            if (result.getSuccess()) {
                exampleId = devCenterManagerCached.createApk(appLog, appDetailLog, appResource);
            }
            if (exampleId == 0) {
                result.setSuccess(true);
            } else {
                result.setSuccess(false);
            }

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
        }
        return result;
    }

    public Result createWeb(AppLog appLog, AppDetailLog appDetailLog, AppResource appResource) {
        Result result = new Result();
        try {
            String intro = URLDecoder.decode(appDetailLog.getAppIntroduce(), "UTF-8");
            intro = intro.replaceAll("<br>", "\n");
            intro = StringEscapeUtils.escapeHtml(intro);
            appDetailLog.setAppIntroduce(intro);
            int exampleId = devCenterManagerCached.createWeb(appLog, appDetailLog, appResource);
            if (exampleId == 0) {
                result.setSuccess(true);
            } else {
                result.setSuccess(false);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            log.error("createApk error!", e);
        }
        return result;
    }

    /**
     * 从应用日志表内查找应用
     *
     * @param checkApk
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Result checkApksOnVersity(CheckApk checkApk, int pageIndex, int pageSize) {
        Result result = new Result();
        try {
            result.addDefaultModel("apks", devCenterManagerCached.ApkVersity(checkApk, pageIndex, pageSize));
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("checkApksOnVersity error", e);
        }
        return result;
    }

    /**
     * 从应用详情表中查找应用
     *
     * @param checkApk
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Result checkApks(CheckApk checkApk, int pageIndex, int pageSize) {
        Result result = new Result();
        try {
            PaginatedList<CheckApkResult> list = devCenterManagerCached.findApks(checkApk, pageIndex, pageSize);
            result.addDefaultModel("apks", list);
            result.addDefaultModel("categoryId_l2", checkApk.getCategoryId_l2());
            result.addDefaultModel("PicResolutionEnum", PicResolutionEnum.N4.getPrefix());
        } catch (Exception e) {
            log.error("checkApks error", e);
        }
        return result;
    }

    /**
     * 获取类目表里面
     *
     * @param id
     * @return
     */
    public Result getCategory(int id) {
        Result result = new Result();
        try {
            result.addDefaultModel("categorys", devCenterManagerCached.getCategory(id));
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("getCategory error", e);
        }
        return result;
    }

    /**
     * 统计应用数据
     *
     * @param apps
     * @return
     */
    public Result statApkes(Apps apps) {
        Result result = new Result();
        try {
            result.addDefaultModel("statapks", devCenterManagerCached.statApks(apps));
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("statApkes error", e);
        }
        return result;
    }

    /**
     * 按appId从详情日志表里取得应用详情
     *
     * @param checkApk
     * @return
     */
    public Result checkApkByAppIdFromLog(CheckApk checkApk) {
        Result result = new Result();
        try {
            CheckApkResult checkApkResult = devCenterManagerCached.checkApkByAppIdFromLog(checkApk);
            result.addDefaultModel("apps", checkApkResult);
            result.addDefaultModel("PicResolutionEnum", PicResolutionEnum.N1.getPrefix());
            result.addDefaultModel("PicResolutionEnumLogo", PicResolutionEnum.N4.getPrefix());
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("checkApkByAppIdFromLog error", e);
        }
        return result;
    }

    /**
     * 按appId从详情表里取得应用详情
     *
     * @param checkApk
     * @return
     */
    public Result checkApkByAppId(CheckApk checkApk) {
        Result result = new Result();
        try {
            CheckApkResult checkApkResult = devCenterManagerCached.checkApkByAppId(checkApk);
            result.addDefaultModel("apps", checkApkResult);
            result.addDefaultModel("PicResolutionEnum", PicResolutionEnum.N1.getPrefix());
            result.addDefaultModel("PicResolutionEnumLogo", PicResolutionEnum.N4.getPrefix());
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("checkApkByAppId error", e);
        }
        return result;
    }


    /**
     * 查看cp个人信息
     *
     * @param accountId
     * @return
     */
    public Result personMess(int accountId) {
        Result result = new Result();
        try {
            result.addDefaultModel("personMess", devCenterManagerCached.personMess(accountId));
            result.addDefaultModel("addressMess", getAddressManager.getProvince());
            result.setSuccess(true);

        } catch (Exception e) {
            log.error("personMess error", e);
        }
        return result;
    }

    /**
     * 更新cp基本信息
     *
     * @param cpBaseinfo
     * @return
     */
    public Result updateCpMess(CpBaseinfo cpBaseinfo, CpReginfo cpReginfo) {
        Result result = new Result();
        try {
            int isSuccessBaseinfo = devCenterManagerCached.updateCpBaseinfo(cpBaseinfo);
            int isSuccessReginfo = devCenterManagerCached.updateCpReginfo(cpReginfo);
            if (0 != isSuccessBaseinfo && 0 != isSuccessReginfo) {
                result.addDefaultModel("info", "更新成功");
                result.setSuccess(true);
            } else {
                result.addDefaultModel("info", "更新失败");
                result.setSuccess(false);
            }
        } catch (Exception e) {
            log.error("updateCpMess error", e);
        }
        return result;
    }

    public int updateCpBaseingo(CpBaseinfo cpBaseinfo) {
        cpBaseinfo.setPassword(MD5Util.md5Hex(cpBaseinfo.getPassword()));
        int flag = devCenterManagerCached.updateCpBaseinfo(cpBaseinfo);
        return flag;
    }

    public AuthApkJSON authApk(Apps apps) {
        AuthApkJSON appsResult = new AuthApkJSON();
        try {
            appsResult = devCenterManagerCached.authApk(apps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appsResult;
    }

    public Result updateApp(AppLog appLog, AppDetailLog appDetailLog, AppResource appResource) {
        Result result = new Result();
        if (appDetailLog.getMinSdkVersion() != null && appDetailLog.getMinSdkVersion().intValue() != 0) {
            Integer osVersionId = getOsVersionIdManager.getOsVersionId(appDetailLog.getMinSdkVersion());
            appDetailLog.setZhuowangMark(0);
            if (osVersionId != null && osVersionId.intValue() != 0) {
                appDetailLog.setOsVersionId(osVersionId);
            } else {
                appDetailLog.setOsVersionId(CommonContants.OS_VERSION_ID);
            }
        }

        try {
            String intro = URLDecoder.decode(appDetailLog.getAppIntroduce(), "UTF-8");
            intro = intro.replaceAll("<br>", "\n");
            intro = StringEscapeUtils.escapeHtml(intro);
            appDetailLog.setAppIntroduce(intro);

            File apkFile = new File(filePathUtils.getAndroidApkPath() + appResource.getApkName());
            VerifyCode verifyCode = new VerifyCode(apkFile);
            appDetailLog.setApkCode(verifyCode.verifyCode());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            // 上传APK文件到阿里云
            aliyunService.putObject(filePathUtils.getAndroidApkPath() + appResource.getApkName(), UploadDirUtils.APK + appResource.getApkName(), 0);
            // 上传LOGO文件到阿里云
            if (StringUtils.isNotBlank(appDetailLog.getLogoUrl())) {
                aliyunService.copyObject(UploadDirUtils.COVER + appDetailLog.getLogoUrl(), UploadDirUtils.COVER + appDetailLog.getLogoUrl());
            }
            // 上传软件截图文件到阿里云
            if (appResource.getPics() != null && appResource.getPics().length > 0) {
                String[] appUrl = appResource.getPics()[0].split(",");
                for (int i = 0; i < appUrl.length; i++) {
                    aliyunService.copyObject(UploadDirUtils.IMG_PIC + appUrl[i], UploadDirUtils.IMG_PIC + appUrl[i]);
                }
            }
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
        }
        // 如果文件都上传成功了的话那么插入数据库
        if (result.getSuccess()) {
            int flag = devCenterManagerCached.updateApp(appLog, appDetailLog, appResource);
            if (flag == 0) {
                result.setSuccess(true);
            } else {
                result.setSuccess(false);
            }
        }
        return result;
    }

    public Result updateWeb(AppLog appLog, AppDetailLog appDetailLog, AppResource appResource) {
        Result result = new Result();
        try {
            String intro = URLDecoder.decode(appDetailLog.getAppIntroduce(), "UTF-8");
            intro = intro.replaceAll("<br>", "\n");
            intro = StringEscapeUtils.escapeHtml(intro);
            appDetailLog.setAppIntroduce(intro);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int flag = devCenterManagerCached.updateWeb(appLog, appDetailLog, appResource);
        if (flag == 0) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    public Result getCommentSummary(GetComment getComment, int pageSize, int pageIndex) {
        Result result = new Result();
        CommentSummaryBo commentSummaryBo = new CommentSummaryBo();
        try {
            commentSummaryBo = commentManager.queryProductSummary(getComment.getAppId());
            CommentSummaryReuslt commentSummaryReuslt = new CommentSummaryReuslt();
            DecimalFormat format = new DecimalFormat("0");
            double commentCount = commentSummaryBo.getCommentCount();
            if (!commentSummaryBo.getGoodRate().equals("")) {
                int goodRate = commentSummaryBo.getScore4Count() + commentSummaryBo.getScore5Count();
                double goodRatePer = goodRate / commentCount;
                double goodValue = goodRatePer * 100;
                commentSummaryReuslt.setGoodRate(format.format(goodValue));

                int middlRate = commentSummaryBo.getScore2Count() + commentSummaryBo.getScore3Count();
                double middleRatePer = middlRate / commentCount;
                double middleValue = middleRatePer * 100;
                commentSummaryReuslt.setMiddleRate(format.format(middleValue));

                int poorRate = commentSummaryBo.getScore1Count();
                double poorRatePer = poorRate / commentCount;
                double poorValue = poorRatePer * 100;
                commentSummaryReuslt.setPoorRate(format.format(poorValue));

            } else {
                commentSummaryReuslt.setGoodRate("0");
                commentSummaryReuslt.setMiddleRate("0");
                commentSummaryReuslt.setPoorRate("0");
            }
            commentSummaryReuslt.setScore1Count(commentSummaryBo.getScore1Count());
            commentSummaryReuslt.setScore2Count(commentSummaryBo.getScore2Count());
            commentSummaryReuslt.setScore3Count(commentSummaryBo.getScore3Count());
            commentSummaryReuslt.setScore4Count(commentSummaryBo.getScore4Count());
            commentSummaryReuslt.setScore5Count(commentSummaryBo.getScore5Count());
            commentSummaryReuslt.setCommentCount(commentSummaryBo.getCommentCount());

            result.addDefaultModel("summary", commentSummaryReuslt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (0 == pageIndex) pageIndex = 1;
        PaginatedList<CommentInfoObj> commentBos = new PaginatedArrayList<CommentInfoObj>(pageIndex, pageSize);

        try {
//            0：全部；1：差评；3：中评；5：好评
            if (getComment.getScore() == 0) {
                int totalItem = commentSummaryBo.getCommentCount();
                commentBos.setTotalItem(totalItem);
            }
//             差评下的总评论数，用于分页
            if (getComment.getScore() == 1) {
                int totalItem = commentSummaryBo.getScore1Count();
                commentBos.setTotalItem(totalItem);
            }
            //             中评下的总评论数，用于分页
            if (getComment.getScore() == 3) {
                int totalItem = commentSummaryBo.getScore2Count() + commentSummaryBo.getScore3Count();
                commentBos.setTotalItem(totalItem);
            }
            //             好评下的总评论数，用于分页
            if (getComment.getScore() == 5) {
                int totalItem = commentSummaryBo.getScore4Count() + commentSummaryBo.getScore5Count();
                commentBos.setTotalItem(totalItem);
            }
            List<CommentBo> commentBoList = commentManager.queryPageProductComments(getComment.getAppId(), pageSize, pageIndex, getComment.getScore());
            List<CommentInfoObj> commentInfoObjList = new ArrayList<CommentInfoObj>();

            for (CommentBo commentBo : commentBoList) {
                CommentInfoObj commentInfoObj = new CommentInfoObj();
                commentInfoObj.setClientType(commentBo.getClienttype());
                commentInfoObj.setSubject(commentBo.getTitle());
                commentInfoObj.setGoodPoint(commentBo.getGoodPoint());
                commentInfoObj.setWeakPoint(commentBo.getWeakPoint());
                commentInfoObj.setContent(commentBo.getContent());
                commentInfoObj.setScore(commentBo.getScore());
                commentInfoObj.setDateTime(commentBo.getDateTime());

                UserInfoObj userInfoObj = new UserInfoObj();
                userInfoObj.setPin(commentBo.getPin());
                UserInfoBean userInfoBean = userInfoManager.getUserInfo(commentBo.getPin());
                if (userInfoBean != null) {
                    userInfoObj.setLevelName(commentBo.getUserLevelName());
                    userInfoObj.setSmallImgUrl(userInfoBean.getSmallImgUrl());
                    userInfoObj.setLocation(StringUtils.defaultIfEmpty(userInfoBean.getLocation(), "暂无"));
                }
                commentInfoObj.setUserInfoObj(userInfoObj);
                commentInfoObjList.add(commentInfoObj);
            }
            commentBos.addAll(commentInfoObjList);
            result.addDefaultModel("commentlist", commentBos);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean ismodifyApk(CheckApk checkApk) {
        return devCenterManagerCached.ismodifyApk(checkApk);
    }


    public void setDevCenterManagerCached(DevCenterManager devCenterManagerCached) {
        this.devCenterManagerCached = devCenterManagerCached;
    }

    public void setCommentManager(CommentManager commentManager) {
        this.commentManager = commentManager;
    }

    public void setGetAddressManager(GetAddressManager getAddressManager) {
        this.getAddressManager = getAddressManager;
    }

    public void setGetOsVersionIdManager(GetOsVesionIdManager getOsVersionIdManager) {
        this.getOsVersionIdManager = getOsVersionIdManager;
    }

    public void setUserInfoManager(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    public FilePathUtils getFilePathUtils() {
        return filePathUtils;
    }

    public void setFilePathUtils(FilePathUtils filePathUtils) {
        this.filePathUtils = filePathUtils;
    }

    public void setAliyunService(AliyunService aliyunService) {
        this.aliyunService = aliyunService;
    }
}
