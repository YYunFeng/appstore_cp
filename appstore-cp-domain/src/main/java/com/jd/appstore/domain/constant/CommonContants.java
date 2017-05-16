package com.jd.appstore.domain.constant;

/**
 * Created by IntelliJ IDEA.
 * User: cuiyi
 * Date: 12-8-20
 * Time: 下午7:39
 * To change this template use File | Settings | File Templates.
 */
public class CommonContants {
    public static final String STRING_COMMA = ",";
    public static final String STRING_SEMICOLON = ";";
    public static final String STRING_COMMA_ZH = "，";  //中文逗号


    //邮件使用模板
    public static final String EMAIL_TEMPLATE_TYPE = "1055";


    // 移动应用sku开始序号
    public static final int APPSTORE_APK_SKU_START_NUM = 50000000;
    // 移动应用sku结束序号
    public static final int APPSTORE_APK_SKU_END_NUM = 55999999;
    // WEB应用sku开始
    public static final int APPSTORE_WEB_SKU_START_NUM = 56000000;
    // WEB应用sku结束
    public static final int APPSTORE_WEB_SKU_END_NUM = 58999999;
    // 网游sku开始
    public static final int APPSTORE_NETGAME_SKU_START_NUM = 59000000;
    // 网游sku开始
    public static final int APPSTORE_NETGAME_SKU_END_NUM = 59999999;

    // 关注应用商店sku范围过滤串
    public static final String FOLLOW_APPSTORE_SKU_SCOPE_FILTER = "L" + APPSTORE_APK_SKU_START_NUM + "M" + APPSTORE_NETGAME_SKU_END_NUM;

    public static final int OS_VERSION_ID = 9;

}