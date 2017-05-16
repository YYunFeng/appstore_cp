package com.jd.appstore.web.util.cp;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-9-10
 * Time: 下午5:18
 * To change this template use File | Settings | File Templates.
 */
public class ClientIp {
       /**
       * 获得HTTP请求的IP地址
       */
       public static String getIp(HttpServletRequest request) {
       String ip = request.getHeader("x-forwarded-for");
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("Proxy-Client-IP");
       }
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("WL-Proxy-Client-IP");
       }
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getRemoteAddr();
       }
       if (ip != null) {
           String[] ipArray = ip.split(",");
           if (ipArray != null && ipArray.length > 1) {
           ip = ipArray[0];
           }
       }
       return ip;
       }
}
