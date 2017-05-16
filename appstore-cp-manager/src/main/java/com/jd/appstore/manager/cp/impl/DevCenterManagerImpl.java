package com.jd.appstore.manager.cp.impl;

import com.jd.appstore.dao.cp.DevCenterDao;
import com.jd.appstore.domain.*;
import com.jd.appstore.domain.constant.CategoryConstant;
import com.jd.appstore.domain.constant.CommonContants;
import com.jd.appstore.domain.constant.SequenceConstants;
import com.jd.appstore.domain.json.AuthApkJSON;
import com.jd.appstore.domain.paramter.CheckApk;
import com.jd.appstore.domain.paramter.StatApks;
import com.jd.appstore.domain.web.CheckApkResult;
import com.jd.appstore.domain.web.UserResult;
import com.jd.appstore.manager.cp.DevCenterManager;
import com.jd.common.manager.BaseManager;
import com.jd.common.util.PaginatedList;
import com.jd.common.util.base.PaginatedArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-15
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */
public class DevCenterManagerImpl extends BaseManager implements DevCenterManager {
    private DevCenterDao devCenterDao;
    private final static Log log = LogFactory.getLog(DevCenterManagerImpl.class);
    private int CREATEAPP_STATUS = 0;
    private int UPDATEAPP_RESULT = 0;

    /**
     * 创建应用，表：tb_app_log,tb_app_detail_log,tb_app_resource
     *
     * @param appLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    public int createApk(final AppLog appLog, final AppDetailLog appDetailLog, final AppResource appResource) {
        TransactionTemplate template = getDataSourceTransactionManager();
        CREATEAPP_STATUS = 0;
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    int appSku = (int) sequenceUtil.get(SequenceConstants.CP_APPS) + CommonContants.APPSTORE_APK_SKU_START_NUM;
                    int appLogId = (int) sequenceUtil.get(SequenceConstants.CP_APP_LOG);
                    Apps apps = new Apps();
                    int appDetailsId = (int) sequenceUtil.get(SequenceConstants.CP_APP_DETAIL_LOG);
                    if (appLog.getAppId() == null) { // 第一次插入数据  向tb_apps插入数据
                        apps.setAppId(appSku);
                        apps.setAppName(appLog.getAppName());
                        apps.setAppType(appLog.getAppType());
                        apps.setCategoryidL2(appLog.getCategoryidL2());
                        apps.setCategoryId1(appLog.getCategoryId1());
                        apps.setPkg(appLog.getPkg());
                        apps.setOnline(0);
                        apps.setZhuowangMark(0);
                        apps.setZhuowangMark(0); // 卓望接口标示
                        devCenterDao.insertApps(apps);
                        appLog.setAppId(appSku);
                        appLog.setId(appLogId);
                        appLog.setBaseSyncFlag(0);
                        appLog.setOnlineSyncFlag(0);
                        appLog.setNewstFlag(1);
                        devCenterDao.insertAppLog(appLog);
                    }
                    appDetailLog.setId(appDetailsId);
                    appDetailLog.setAppId(appLog.getAppId());
                    appDetailLog.setUpdateStatus(1);
                    appDetailLog.setValid(0);
                    appDetailLog.setAppLogo(0);
                    devCenterDao.insertAppDetailLog(appDetailLog);


                    for (int i = 0, j = appResource.getPics().length; i < j; i++) {
                        int appResourceId = (int) sequenceUtil.get(SequenceConstants.APP_RESOURCE);
                        AppResource appRes = new AppResource();
                        appRes.setResId(appResourceId);
                        appRes.setAppId(appLog.getAppId());
                        appRes.setResType(0);
                        appRes.setCopy(1);
                        appRes.setZhuowangMark(0);
                        appRes.setAccountId(appResource.getAccountId());
                        appRes.setResUrl(appResource.getPics()[i]);
                        devCenterDao.insertAppResource(appRes);
                    }
                    int appResourceId = (int) sequenceUtil.get(SequenceConstants.APP_RESOURCE);
                    AppResource appRes = new AppResource();
                    appRes.setResId(appResourceId);
                    appRes.setAppId(appLog.getAppId());
                    appRes.setResType(1);
                    appRes.setCopy(1);
                    appRes.setZhuowangMark(0);
                    appRes.setAccountId(appResource.getAccountId());
                    appRes.setResUrl(appResource.getApkName());
                    devCenterDao.insertAppResource(appRes);
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    CREATEAPP_STATUS = 1;
                    e.printStackTrace();
                }
            }
        });
        return CREATEAPP_STATUS;
    }

    /**
     * 创建web应用
     *
     * @param appLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    public int createWeb(final AppLog appLog, final AppDetailLog appDetailLog, final AppResource appResource) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        CREATEAPP_STATUS = 0;
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    int appSku = (int) sequenceUtil.get(SequenceConstants.CP_APPS) + CommonContants.APPSTORE_WEB_SKU_START_NUM;
                    int appLogId = (int) sequenceUtil.get(SequenceConstants.CP_APP_LOG);
                    Apps apps = new Apps();
                    apps.setAppId(appSku);
                    apps.setAppName(appLog.getAppName());
                    if (appResource.getResType() == 2) {
                        // web应用包
                        apps.setAppType(3);
                    }
                    if (appResource.getResType() == 3) {
                        // web外链地址
                        apps.setAppType(4);
                    }
                    apps.setCategoryidL2(appLog.getCategoryidL2());
                    apps.setCategoryId1(appLog.getCategoryId1());
                    apps.setOnline(0);
                    devCenterDao.insertApps(apps);

                    if (appResource.getResType() == 2) {
                        // web应用包
                        appLog.setAppType(3);
                    }
                    if (appResource.getResType() == 3) {
                        // web外链地址
                        appLog.setAppType(4);
                    }
                    appLog.setBaseCheckStatus(2);
                    appLog.setBaseSyncFlag(1);
                    appLog.setOnlineOptType(1);
                    appLog.setId(appLogId);
                    appLog.setAppId(appSku);
                    appLog.setOnlineSyncFlag(0);
                    appLog.setNewstFlag(1);
                    devCenterDao.insertAppLog(appLog);
                    int appDetailsId = (int) sequenceUtil.get(SequenceConstants.CP_APP_DETAIL_LOG);
                    appDetailLog.setId(appDetailsId);
                    appDetailLog.setAppId(appLog.getAppId());
                    appDetailLog.setUpdateStatus(1);
                    appDetailLog.setValid(0);
                    appDetailLog.setOnline(0);
                    appDetailLog.setPriceSyncFlag(0);
                    appDetailLog.setAppLogo(0);
                    devCenterDao.insertAppDetailLog(appDetailLog);
                    /*
                    BackendTask backendTask = new BackendTask();
                    backendTask.setId((int) sequenceUtil.get(SequenceConstants.CMS_BACKEND_TASK_SEQUENCE));
                    backendTask.setAdminId(appDetailLog.getAccountId());
                    backendTask.setTaskTypeId(Integer.parseInt(TaskTypeConstants.CMS_TASKTYPE_UPLOADCOVER));
                    backendTask.setRelatedId(appDetailsId);
                    backendTask.setStatus(TaskTypeConstants.CMS_TASKSTATUS_WAITEXECUTE);
                    backendTask.setIp(appDetailLog.getIp());
                    backendTask.setComment("上传主图");
                    backendTask.setRetryTimes(TaskTypeConstants.CMS_TASK_RETRYTIMES);
                    backendTask.setAutoRetry(TaskTypeConstants.CMS_TASK_AUTORETRY);
                    devCenterDao.insertBackendTask(backendTask);*/

                    int appResourceId = (int) sequenceUtil.get(SequenceConstants.APP_RESOURCE);
                    appResource.setResId(appResourceId);
                    appResource.setAppId(appLog.getAppId());
                    appResource.setAccountId(appDetailLog.getAccountId());
                    appResource.setCopy(1);
                    devCenterDao.insertAppResource(appResource);
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    CREATEAPP_STATUS = 1;
                    e.printStackTrace();
                }
            }
        });
        return CREATEAPP_STATUS;
    }

    /**
     * 获取类目，用于上传应用时，用户选择类目，tb_category
     *
     * @param id
     * @return
     */
    public List<Category> getCategory(int id) {
        List list = devCenterDao.findCategory(id);
        return list;
    }

    /**
     * 分页查看应用，表：tb_apps,tb_app_details,tb_app_log,tb_app_detail_log
     *
     * @param checkApk
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PaginatedList<CheckApkResult> findApks(CheckApk checkApk, int pageIndex, int pageSize) {
        if (0 == pageIndex) pageIndex = 1;
        PaginatedList<CheckApkResult> apks = new PaginatedArrayList<CheckApkResult>(pageIndex, pageSize);
        try {
            int totalItem = devCenterDao.findApkCounts(checkApk);
            apks.setTotalItem(totalItem);
            checkApk.setStartRow((pageIndex - 1) * pageSize);
            checkApk.setEndRow(pageSize);
            List<CheckApkResult> apk = devCenterDao.findApks(checkApk);
            apks.addAll(apk);
        } catch (Exception ex) {
            log.error("分页查询全部APK error!", ex);
            throw new RuntimeException("findApks error!", ex);
        }
        return apks;
    }

    /**
     * 从应用详情日志表中分页查询apk,表：tb_app_log,tb_app_detail_log
     *
     * @param checkApk
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PaginatedList<CheckApkResult> ApkVersity(CheckApk checkApk, int pageIndex, int pageSize) {
        if (0 == pageIndex) pageIndex = 1;
        PaginatedList<CheckApkResult> apks = new PaginatedArrayList<CheckApkResult>(pageIndex, pageSize);
        try {
            int totalItem = devCenterDao.findApkVersityCounts(checkApk);
            apks.setTotalItem(totalItem);
            checkApk.setStartRow((pageIndex - 1) * pageSize);
            checkApk.setEndRow(pageSize);
            List<CheckApkResult> apk = devCenterDao.ApkVersity(checkApk);
            apks.addAll(apk);
        } catch (Exception ex) {
            log.error("分页查询全部APK error!", ex);
            throw new RuntimeException("findApks error!", ex);
        }
        return apks;
    }

    /**
     * 按appId从日志表中查看应用详情，表：tb_app_log,tb_app_detail_log,tb_app_resource
     *
     * @param checkApk
     * @return
     */
    public CheckApkResult checkApkByAppIdFromLog(CheckApk checkApk) {

        CheckApkResult checkApkResult = devCenterDao.checkApkByAppIdFromLog(checkApk);
        if (checkApkResult.getCategoryId_l2() == CategoryConstant.MOBLIE_GAME_CATEGORY_ID) {
            checkApkResult.setCategoryId_l2Name(CategoryConstant.MOBLIE_GAME_CATEGORY_NAME);
        } else if (checkApkResult.getCategoryId_l2() == CategoryConstant.MOBLIE_SOFT_CATEGORY_ID) {
            checkApkResult.setCategoryId_l2Name(CategoryConstant.MOBLIE_SOFT_CATEGORY_NAME);
        } else if (checkApkResult.getCategoryId_l2() == CategoryConstant.WEB_SOFT_CATEGORY_ID) {
            checkApkResult.setCategoryId_l2Name(CategoryConstant.WEB_SOFT_CATEGORY_NAME);
        }
        checkApkResult.setCategoryId1Name(devCenterDao.getCategoryName(checkApkResult.getCategoryId1()));
        checkApkResult.setAppIntroduce(checkApkResult.getAppIntroduce().replace("\n", "<br>"));
        if (!checkApkResult.getCategoryId_l2().equals(CategoryConstant.WEB_SOFT_CATEGORY_ID)) {
            checkApk.setResType(0);
            checkApk.setCopy(1);

            List<AppResource> appResources = new ArrayList<AppResource>();
            if (devCenterDao.getAppResource(checkApk).size() != 0) {
                appResources = devCenterDao.getAppResource(checkApk);
            }
            checkApkResult.setAppResources(appResources);
            checkApk.setResType(1);
            checkApk.setCopy(1);

            if (devCenterDao.getAppResource(checkApk).size() != 0) {
                List<AppResource> apk = devCenterDao.getAppResource(checkApk);
                checkApkResult.setAppRes(apk.get(0));
            }
        } else {
            checkApk.setCopy(1);
            if (devCenterDao.getAppResource(checkApk).size() != 0) {
                List<AppResource> appResources = devCenterDao.getAppResource(checkApk);
                checkApkResult.setAppResource(appResources.get(0));
            }
        }
        return checkApkResult;
    }

    /**
     * 按appId查看应用详情
     *
     * @param checkApk
     * @return
     */
    public CheckApkResult checkApkByAppId(CheckApk checkApk) {
        CheckApkResult checkApkResult = devCenterDao.checkApkByAppId(checkApk);
        if (checkApkResult.getCategoryId_l2() == CategoryConstant.MOBLIE_GAME_CATEGORY_ID) {
            checkApkResult.setCategoryId_l2Name(CategoryConstant.MOBLIE_GAME_CATEGORY_NAME);
        } else if (checkApkResult.getCategoryId_l2() == CategoryConstant.MOBLIE_SOFT_CATEGORY_ID) {
            checkApkResult.setCategoryId_l2Name(CategoryConstant.MOBLIE_SOFT_CATEGORY_NAME);
        } else if (checkApkResult.getCategoryId_l2() == CategoryConstant.WEB_SOFT_CATEGORY_ID) {
            checkApkResult.setCategoryId_l2Name(CategoryConstant.WEB_SOFT_CATEGORY_NAME);
        }
        checkApkResult.setCategoryId1Name(devCenterDao.getCategoryName(checkApkResult.getCategoryId1()));
        checkApkResult.setAppIntroduce(checkApkResult.getAppIntroduce().replace("\n", "<br>"));
        if (!checkApkResult.getCategoryId_l2().equals(CategoryConstant.WEB_SOFT_CATEGORY_ID)) {
            checkApk.setResType(0);
            checkApk.setCopy(0);

            List<AppResource> appResources = new ArrayList<AppResource>();
            if (devCenterDao.getAppResource(checkApk).size() != 0) {
                appResources = devCenterDao.getAppResource(checkApk);
            }
            checkApkResult.setAppResources(appResources);

            checkApk.setResType(1);
            checkApk.setCopy(0);
            if (devCenterDao.getAppResource(checkApk).size() != 0) {
                List<AppResource> apk = devCenterDao.getAppResource(checkApk);
                checkApkResult.setAppRes(apk.get(0));
            }
        } else {
            checkApk.setCopy(0);
            if (devCenterDao.getAppResource(checkApk).size() != 0) {
                List<AppResource> appResources = devCenterDao.getAppResource(checkApk);
                checkApkResult.setAppResource(appResources.get(0));
            }
        }
        return checkApkResult;
    }

    public UserResult personMess(int accountId) {
        return devCenterDao.personMess(accountId);
    }

    public int updateCpBaseinfo(CpBaseinfo cpBaseinfo) {
        return devCenterDao.updateCpBaseinfo(cpBaseinfo);
    }

    public int updateCpReginfo(CpReginfo cpReginfo) {
        return devCenterDao.updateCpReginfo(cpReginfo);
    }

    public AuthApkJSON authApk(Apps apps) {
        AuthApkJSON authApkJSON = new AuthApkJSON();
        Apps appTmp = devCenterDao.authApk(apps);
        // 0：可以上传详情，1：同一个CP第二次上传同一详情，2:该详情已经有个收费应用 ，3：第一次上传该应用
        if (appTmp != null) {
            authApkJSON.setFalg(verifyButUploading(apps));
        } else {
            authApkJSON.setFalg(3); // 第一次上传该应用
            /* *//* try {
                String extName = null;
                if (apps.getAppName().lastIndexOf(".") >= 0) {
                    extName = apps.getAppName().substring(apps.getAppName().lastIndexOf("."));
                }
                String str = jssAppManager.inRights(apps.getAppName(), apps.getPkg() + System.currentTimeMillis() + extName);
                File apkFike = new File(apps.getAppName());
                //删除临时文件，直到成功
                int c = 0;
                while (!apkFike.delete() && c < 200) {
                    c++;
                    Thread.sleep(50);
                }

                if (StringUtils.isBlank(str)) {
                    authApkJSON.setStatus(0);
                    authApkJSON.setMess("上传APK错误，请重试");
                    return authApkJSON;
                }
                authApkJSON.setApkFileName(str);*//*
            } catch (Exception e) {
                e.printStackTrace();
                authApkJSON.setStatus(0);
                authApkJSON.setMess("系统错误，请重试");
            }*/

        }
        // 如果能上传把应用名称类目名称赋值到authApkJSON
        if (appTmp != null && authApkJSON.getFalg().intValue() == 0) {
            authApkJSON.setAppName(appTmp.getAppName());
            authApkJSON.setAppId(appTmp.getAppId());
            authApkJSON.setCategoryidL2(appTmp.getCategoryidL2());
            authApkJSON.setCategoryId1(appTmp.getCategoryId1());
            String setCategoryidL2Name = "";
            if (appTmp.getCategoryidL2().equals(CategoryConstant.MOBLIE_GAME_CATEGORY_ID)) {
                setCategoryidL2Name = CategoryConstant.MOBLIE_GAME_CATEGORY_NAME;
            }
            if (appTmp.getCategoryidL2().equals(CategoryConstant.MOBLIE_SOFT_CATEGORY_ID)) {
                setCategoryidL2Name = CategoryConstant.MOBLIE_SOFT_CATEGORY_NAME;
            }
            authApkJSON.setCategoryidL2Name(setCategoryidL2Name);
            authApkJSON.setCategoryId1Name(devCenterDao.getCategoryName(appTmp.getCategoryId1()));
            /* try {
                String extName = null;
                if (apps.getAppName().lastIndexOf(".") >= 0) {
                    extName = apps.getAppName().substring(apps.getAppName().lastIndexOf("."));
                }
                String str = jssAppManager.inRights(apps.getAppName(), apps.getPkg() + System.currentTimeMillis() + extName);
                File apkFike = new File(apps.getAppName());
                //删除临时文件，直到成功
                int c = 0;
                while (!apkFike.delete() && c < 200) {
                    c++;
                    Thread.sleep(50);
                }
                if (StringUtils.isBlank(str)) {
                    authApkJSON.setStatus(0);
                    authApkJSON.setMess("上传APK错误，请重试");
                    return authApkJSON;
                }
                authApkJSON.setApkFileName(str);
            } catch (Exception e) {
                e.printStackTrace();
                authApkJSON.setStatus(0);
                authApkJSON.setMess("系统错误，请重试");
            }*/
        }

        return authApkJSON;
    }

    /**
     * 修改APK文件
     *
     * @param appLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    public int updateApp(final AppLog appLog, final AppDetailLog appDetailLog, final AppResource appResource) {
        UPDATEAPP_RESULT = 0;
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    int appLogId = (int) sequenceUtil.get(SequenceConstants.CP_APP_LOG);
                    int appDetailLogId = (int) sequenceUtil.get(SequenceConstants.CP_APP_DETAIL_LOG);
                    int appResId = (int) sequenceUtil.get(SequenceConstants.APP_RESOURCE);
                    List<AppDetails> appDetails = devCenterDao.getApkDetails(appLog.getAppId());
                    // 新发审核不通过
                    if (appDetails.size() == 0 && appDetailLog.getUpdateStatus() == 2) {

                        devCenterDao.updateAppLog(appLog.getAppId());
                        AppLog appLogTmp = devCenterDao.findAppLogByAppId(appLog.getAppId());
                        appLog.setAppName(appLogTmp.getAppName());
                        appLog.setAppType(appLogTmp.getAppType());
                        appLog.setPkg(appLogTmp.getPkg());
                        appLog.setCategoryidL2(appLogTmp.getCategoryidL2());
                        appLog.setCategoryId1(appLogTmp.getCategoryId1());
                        appLog.setId(appLogId);
                        appLog.setBaseCheckStatus(2);
                        appLog.setBaseSyncFlag(1);
                        appLog.setOnlineSyncFlag(1);
                        appLog.setOnlineOptType(1);
                        appLog.setNewstFlag(1);
                        devCenterDao.insertAppLog(appLog);
                        // 更新详情日志表

                        devCenterDao.updateAppDetailLog(appDetailLog);


                        // 删除资源表里是副本的数据
                        AppResource appResTemp = new AppResource();
                        appResTemp.setResId(appResId);
                        appResTemp.setAppId(appLog.getAppId());
                        appResTemp.setAccountId(appDetailLog.getAccountId());
                        devCenterDao.deteleAppres(appResTemp);

                        // 向资源表中插入APk包
                        AppResource appResTmp = new AppResource();
                        appResTmp.setResId(appResId);
                        appResTmp.setAppId(appLog.getAppId());
                        appResTmp.setAccountId(appDetailLog.getAccountId());
                        appResTmp.setResType(1);
                        appResTmp.setCapSeq(0);
                        appResTmp.setResUrl(appResource.getResUrl());
                        appResTmp.setCopy(1);
                        appResTmp.setZhuowangMark(0);
                        devCenterDao.insertAppResource(appResTmp);


                        //插入新的图片信息
                        if (appResource.getPics() != null) {
                            appResource.setPics(appResource.getPics()[0].split(","));
                            for (int i = 0; i < appResource.getPics().length; i++) {
                                if (appResource.getPics()[i] != "" && !appResource.getPics()[i].equals("")) {
                                    int appResourceId = (int) sequenceUtil.get(SequenceConstants.APP_RESOURCE);
                                    AppResource appRes = new AppResource();
                                    appRes.setResId(appResourceId);
                                    appRes.setAppId(appLog.getAppId());
                                    appRes.setResType(0);
                                    appRes.setAccountId(appDetailLog.getAccountId());
                                    appRes.setCopy(1);
                                    appRes.setResUrl(appResource.getPics()[i]);
                                    appRes.setZhuowangMark(0);
                                    devCenterDao.insertAppResource(appRes);
                                }
                            }
                        }

                    } else {
                        appDetailLog.setAppId(appLog.getAppId());
                        AppDetailLog appDetailLogTmp = devCenterDao.findAppDetailLog(appDetailLog);
                        if (appDetailLogTmp == null) { // 没有有效详情
                            /* if (devCenterDao.authCpFromDetailLog(appDetailLog)) {
                                appDetailLog.setId(appDetailLogId);
                                appDetailLog.setValid(0);
                                appDetailLog.setOnline(0);
                                appDetailLog.setUpdateStatus(1);
                                devCenterDao.insertAppDetailLog(appDetailLog);
                            } else {
                                UPDATEAPP_RESULT = 2;  // 该应用不属于该CP

                            }*/


                            devCenterDao.updateAppDetailLog(appDetailLog); //置为待审核状态
                        } else {
                            // 如果该应用是审核通过的，向日志表中重新插入新数据
                            if (appDetailLog.getUpdateStatus() != 2) {
                                if (devCenterDao.authCpFromDetailLog(appDetailLog)) {
                                    appDetailLogTmp.setAppIntroduce(appDetailLog.getAppIntroduce());
                                    appDetailLogTmp.setOsVersionId(appDetailLog.getOsVersionId());
                                    appDetailLogTmp.setAppId(appDetailLog.getAppId());
                                    appDetailLogTmp.setId(appDetailLogId);
                                    appDetailLogTmp.setAppVersion(appDetailLog.getAppVersion());
                                    appDetailLogTmp.setAppVersionCode(appDetailLog.getAppVersionCode());
                                    appDetailLogTmp.setPkgSize(appDetailLog.getPkgSize());
                                    appDetailLogTmp.setLogoUrl(appDetailLog.getLogoUrl());
                                    appDetailLogTmp.setOnline(0);
                                    appDetailLogTmp.setValid(0);
                                    appDetailLogTmp.setAppLogo(appDetailLog.getAppLogo());
                                    appDetailLogTmp.setAppTag(appDetailLog.getAppTag());
                                    appDetailLogTmp.setUpdateStatus(1);
                                    appDetailLogTmp.setApkCode(appDetailLog.getApkCode());

                                    devCenterDao.insertAppDetailLog(appDetailLogTmp);

                                } else {
                                    UPDATEAPP_RESULT = 2;  // 该应用不属于该CP
                                }
                            } else {
                                // 如果该应用审核不通过的，更新日志表的信息

                                devCenterDao.updateAppDetailLog(appDetailLog); //置为待审核状态
                            }

                        }

                        if (UPDATEAPP_RESULT == 0) {

                            // 删除资源表里是副本的数据
                            AppResource appResTemp = new AppResource();
                            appResTemp.setResId(appResId);
                            appResTemp.setAppId(appLog.getAppId());
                            appResTemp.setAccountId(appDetailLog.getAccountId());
                            devCenterDao.deteleAppres(appResTemp);

                            // 向资源表中插入APk包
                            AppResource appResTmp = new AppResource();
                            appResTmp.setResId(appResId);
                            appResTmp.setAppId(appLog.getAppId());
                            appResTmp.setAccountId(appDetailLog.getAccountId());
                            appResTmp.setCapSeq(0);
                            appResTmp.setResType(1);
                            appResTmp.setResUrl(appResource.getResUrl());
                            appResTmp.setCopy(1);
                            appResTmp.setZhuowangMark(0);
                            devCenterDao.insertAppResource(appResTmp);

                            //插入新的图片信息
                            if (appResource.getPics() != null) {
                                appResource.setPics(appResource.getPics()[0].split(","));
                                for (int i = 0; i < appResource.getPics().length; i++) {
                                    if (appResource.getPics()[i] != "" && !appResource.getPics()[i].equals("")) {
                                        int appResourceId = (int) sequenceUtil.get(SequenceConstants.APP_RESOURCE);
                                        AppResource appRes = new AppResource();
                                        appRes.setResId(appResourceId);
                                        appRes.setAppId(appLog.getAppId());
                                        appRes.setResType(0);
                                        appRes.setAccountId(appDetailLog.getAccountId());
                                        appRes.setCopy(1);
                                        appRes.setZhuowangMark(0);
                                        appRes.setResUrl(appResource.getPics()[i]);
                                        devCenterDao.insertAppResource(appRes);
                                    }
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                    UPDATEAPP_RESULT = 1;
                    transactionStatus.setRollbackOnly();
                    e.printStackTrace();
                }
            }
        });

        return UPDATEAPP_RESULT;
    }

    /**
     * 修改WEB应用
     *
     * @param appLog
     * @param appDetailLog
     * @param appResource
     * @return
     */
    public int updateWeb(final AppLog appLog, final AppDetailLog appDetailLog, final AppResource appResource) {
        UPDATEAPP_RESULT = 0;
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    int appLogId = (int) sequenceUtil.get(SequenceConstants.CP_APP_LOG);
                    int appDetailLogId = (int) sequenceUtil.get(SequenceConstants.CP_APP_DETAIL_LOG);
                    int appResId = (int) sequenceUtil.get(SequenceConstants.APP_RESOURCE);
                    List<AppDetails> appDetails = devCenterDao.getApkDetails(appLog.getAppId());
                    // 新发审核不通过
                    if (appDetails.size() == 0 && appDetailLog.getUpdateStatus() == 2) {
                        devCenterDao.updateAppLog(appLog.getAppId());
                        AppLog appLogTmp = devCenterDao.findAppLogByAppId(appLog.getAppId());
                        appLog.setAppName(appLogTmp.getAppName());
                        appLog.setAppType(appLogTmp.getAppType());
                        appLog.setCategoryidL2(appLogTmp.getCategoryidL2());
                        appLog.setCategoryId1(appLogTmp.getCategoryId1());
                        appLog.setId(appLogId);
                        appLog.setBaseCheckStatus(2);
                        appLog.setBaseSyncFlag(1);
                        appLog.setOnlineSyncFlag(1);
                        appLog.setOnlineOptType(1);
                        appLog.setNewstFlag(1);
                        devCenterDao.insertAppLog(appLog);

                        devCenterDao.updateAppDetailLog(appDetailLog);

                        // 删除资源表里是副本的数据
                        AppResource appResTemp = new AppResource();
                        appResTemp.setResId(appResId);
                        appResTemp.setAppId(appLog.getAppId());
                        appResTemp.setAccountId(appDetailLog.getAccountId());
                        devCenterDao.deteleAppres(appResTemp);

                        AppResource appResTmp = new AppResource();
                        appResTmp.setResId(appResId);
                        appResTmp.setAppId(appLog.getAppId());
                        appResTmp.setAccountId(appDetailLog.getAccountId());
                        appResTmp.setResType(appResource.getResType());
                        appResTmp.setCapSeq(0);
                        appResTmp.setResUrl(appResource.getResUrl());
                        appResTmp.setCopy(1);
                        devCenterDao.insertAppResource(appResTmp);

                    } else {
                        appDetailLog.setAppId(appLog.getAppId());
                        AppDetailLog appDetailLogTmp = devCenterDao.findAppDetailLog(appDetailLog);
                        if (appDetailLogTmp == null) {    //没有有效详情
                            /* if (devCenterDao.authCpFromDetailLog(appDetailLog)) {
                                appDetailLog.setId(appDetailLogId);
                                appDetailLog.setValid(0);
                                appDetailLog.setOnline(0);
                                appDetailLog.setUpdateStatus(1);
                                devCenterDao.insertAppDetailLog(appDetailLog);
                            } else {
                                UPDATEAPP_RESULT = 2;
                            }*/
                            devCenterDao.updateAppDetailLog(appDetailLog); //置为待审核状态
                        } else {
                            if (appDetailLog.getUpdateStatus() != 2) {
                                if (devCenterDao.authCpFromDetailLog(appDetailLog)) {
                                    appDetailLogTmp.setAppId(appDetailLog.getAppId());
                                    appDetailLogTmp.setId(appDetailLogId);
                                    appDetailLogTmp.setAppVersion(appDetailLog.getAppVersion());
                                    appDetailLogTmp.setAppVersionCode(appDetailLog.getAppVersionCode());
                                    appDetailLogTmp.setPkgSize(appDetailLog.getPkgSize());
                                    appDetailLogTmp.setLogoUrl(appDetailLog.getLogoUrl());
                                    appDetailLogTmp.setOsVersionId(appDetailLog.getOsVersionId());
                                    appDetailLogTmp.setOnline(0);
                                    appDetailLogTmp.setValid(0);
                                    appDetailLogTmp.setAppLogo(appDetailLog.getAppLogo());
                                    appDetailLogTmp.setAppIntroduce(appDetailLog.getAppIntroduce());
                                    appDetailLogTmp.setAppTag(appDetailLog.getAppTag());
                                    appDetailLogTmp.setUpdateStatus(1);
                                    devCenterDao.insertAppDetailLog(appDetailLogTmp);

                                } else {
                                    UPDATEAPP_RESULT = 2;
                                }
                            } else {
                                devCenterDao.updateAppDetailLog(appDetailLog); //置为待审核状态
                            }
                        }

                        if (UPDATEAPP_RESULT == 0) {
                            // 删除资源表里是副本的数据
                            AppResource appResTemp = new AppResource();
                            appResTemp.setResId(appResId);
                            appResTemp.setAppId(appLog.getAppId());
                            appResTemp.setAccountId(appDetailLog.getAccountId());
                            devCenterDao.deteleAppres(appResTemp);

                            AppResource appResTmp = new AppResource();
                            appResTmp.setResId(appResId);
                            appResTmp.setAppId(appLog.getAppId());
                            appResTmp.setAccountId(appDetailLog.getAccountId());
                            appResTmp.setCapSeq(0);
                            appResTmp.setResType(appResource.getResType());
                            appResTmp.setResUrl(appResource.getResUrl());
                            appResTmp.setCopy(1);
                            devCenterDao.insertAppResource(appResTmp);
                        }
                    }
                } catch (Exception e) {
                    UPDATEAPP_RESULT = 1;
                    transactionStatus.setRollbackOnly();
                    e.printStackTrace();
                }
            }
        }

        );
        return UPDATEAPP_RESULT;
    }

    public boolean ismodifyApk(CheckApk checkApk) {
        return devCenterDao.isModifyApk(checkApk);
    }

    public Integer verifyButUploading(Apps apps) {
        List<Integer> list = devCenterDao.verifyButUploading(apps);
        Integer flag = 0;
        if (list != null && list.size() > 0) {
            for (int i = 0, j = list.size(); i < j; i++) {
                if (list.get(i).intValue() == 1) {
                    flag = 2; // 该应用已经存在一收费版本
                    break;
                } else {
                    flag = 1; // 你已经上传该应用
                    break;
                }
            }
        } else {
            flag = 0;
        }

        return flag;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<AppDetailLog> findApkOnVersity(AppDetailLog appDetailLog) {
        return devCenterDao.findApkOnVersity(appDetailLog);
    }

    /**
     * 统计应用详情,用于查看应用页面，导航条上的统计应用
     *
     * @param apps
     * @return
     */
    public StatApks statApks(Apps apps) {
        StatApks statApks = new StatApks();
        statApks.setOnApprovedCount(0);
        List<StatApks> statApksesAppDetail = devCenterDao.statApkByAppDetail(apps);
        for (int i = 0; i < statApksesAppDetail.size(); i++) {
            if (statApksesAppDetail.get(i).getOnline() != null && statApksesAppDetail.get(i).getOnline() == 0) {
                //未上线
                statApks.setNotOnline(statApksesAppDetail.get(i).getCount());
            }
            if (statApksesAppDetail.get(i).getOnline() != null && statApksesAppDetail.get(i).getOnline() == 1) {
                //在线无更新
                statApks.setOnlineNoupdate(statApksesAppDetail.get(i).getCount());
            }
            if (statApksesAppDetail.get(i).getOnline() != null && statApksesAppDetail.get(i).getOnline() == 2) {
                //已经下线
                statApks.setOutOnline(statApksesAppDetail.get(i).getCount());
            }
            if (statApksesAppDetail.get(i).getOnline() != null && statApksesAppDetail.get(i).getOnline() == 3) {
                //审核中
                statApks.setOnApprovedCount(statApksesAppDetail.get(i).getCount());
            }
        }
        List<StatApks> statApksesAppDetailsLog = devCenterDao.statApkByAppDetailsLog(apps);
        for (int i = 0; i < statApksesAppDetailsLog.size(); i++) {
            if (statApksesAppDetailsLog.get(i).getUpdateStatus() != null && statApksesAppDetailsLog.get(i).getUpdateStatus() == 1) {
                statApks.setAwaitApprovedCount(statApksesAppDetailsLog.get(i).getCount());
            } else if (statApksesAppDetailsLog.get(i).getUpdateStatus() != null && statApksesAppDetailsLog.get(i).getUpdateStatus() == 2) {
                statApks.setNotApprovedCount(statApksesAppDetailsLog.get(i).getCount());
            } else {
                statApks.setOnApprovedCount(statApks.getOnApprovedCount() + statApksesAppDetailsLog.get(i).getCount());
            }
        }
        statApks.setOnlineNoupdate(statApks.getOnlineNoupdate() == null ? 0 : statApks.getOnlineNoupdate());
        statApks.setOutOnline(statApks.getOutOnline() == null ? 0 : statApks.getOutOnline());
        statApks.setAwaitApprovedCount(statApks.getAwaitApprovedCount() == null ? 0 : statApks.getAwaitApprovedCount());
        statApks.setNotApprovedCount(statApks.getNotApprovedCount() == null ? 0 : statApks.getNotApprovedCount());
        statApks.setOnApprovedCount(statApks.getOnApprovedCount() == null ? 0 : statApks.getOnApprovedCount());
        statApks.setNotOnline(statApks.getNotOnline() == null ? 0 : statApks.getNotOnline());
        statApks.setTotalCount(statApks.getOnlineNoupdate() + statApks.getOutOnline() + statApks.getAwaitApprovedCount() + statApks.getNotApprovedCount() + statApks.getOnApprovedCount() + statApks.getNotOnline());
        return statApks;
    }


    public DevCenterDao getDevCenterDao() {
        return devCenterDao;
    }

    public void setDevCenterDao(DevCenterDao devCenterDao) {
        this.devCenterDao = devCenterDao;
    }


}
