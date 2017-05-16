package com.jd.appstore.web.interceptor.cp;

import com.jd.common.struts.context.LoginContext;
import com.jd.common.struts.interceptor.NewLoginContextInterceptor;
import com.jd.common.struts.interceptor.dotnet.DotnetAuthenticationUtil;
import com.jd.common.struts.interceptor.dotnet.FormsAuthenticationTicket;
import com.jd.digital.common.rpc.domain.web.JdAppLoginContext;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 * User: chenbaoan@360buy.com
 * Date: 11-12-29
 * Time: 上午11:22
 */
public class JdAppStoreLoginContextInterceptor extends NewLoginContextInterceptor {
    private Logger log = Logger.getLogger(this.getClass());

    private static final long serialVersionUID = 4139448550703417383L;

    protected String dotnetAuthCookieName = "ceshi3.com";

    protected String dotnetAuthenticationKey;

    @Override
    protected JdAppLoginContext getLoginContext(String cookieValue) {
        return JdAppLoginContext.parse(cookieValue);
    }

    protected void updateLogin(HttpServletRequest request, HttpServletResponse response) {
        //判断是否配置了cookie的cookie名称
        if (loginCookieKey != null) {
            try {
                String value = cookieUtils.getCookieValue(request, loginCookieKey);
                if (StringUtils.isNotBlank(value)) {//能取到值
                    LoginContext context = getLoginContext(value);
                    if (context != null) {//又能解出来
                        long current = System.currentTimeMillis();
                        long created = context.getCreated();
                        long expires = context.getExpires();
                        long timeout = expires == 0 ? sessionTimeout * 1000 : expires - created;//如果没有设置过期时间，则使用默认的
                        if (current - created < timeout) { //如果没有过期
                            LoginContext.setLoginContext(context);
                            if ((current - created) * rate > timeout) {//如果剩下的时间只有2/3，就需要重新派发cookie
                                log.debug("session cookie[" + loginCookieKey + "] rewrite!");
                                //写最后一次访问的cookie
                                context.setCreated(current);
                                if (expires != 0) {
                                    context.setTimeout(timeout);
                                }
                                cookieUtils.setCookie(response, loginCookieKey, context.toCookieValue());
                            }
//                        } else if (expires == 0) {//如果没有设置过期时间。应该不能做特殊处理。否则就会兼容以前的。
//                            cookieUtils.setCookie(response, loginCookieKey, context.toCookieValue());
                        } else {
                            log.debug("session cookie[" + loginCookieKey + "] is valid!");
                            //超时后，要清空
                            cookieUtils.invalidate(request, response);
                        }
                    } else {
                        log.debug("session cookie[" + loginCookieKey + "] is error!");
                    }
                } else {
                    //解密主站Cookie
                    value = cookieUtils.getCookieValue(request, dotnetAuthCookieName);
                    if (StringUtils.isNotBlank(value)) {//能取到值
                        FormsAuthenticationTicket ticket = null;
                        try {
                            ticket = DotnetAuthenticationUtil.getFormsAuthenticationTicket(value, dotnetAuthenticationKey);
                            log.info("------解析主站cookie:pin=" + ticket.getUsername() + ", ticket.isExpired()=" + ticket.isExpired() +
                                ",expiredDate=" + DateFormatUtils.format(ticket.getExpires(), "yyyy-MM-dd HH:mm:ss"));
                        } catch (Exception e) {
                            log.error("decrypt dotnet cookie error!", e);
                        }
                        if (ticket != null && !ticket.isExpired()) {
                            JdAppLoginContext context = new JdAppLoginContext();
                            context.setPin(ticket.getUsername());
                            LoginContext.setLoginContext(context);

                            // 写入当前网站cookie
                            cookieUtils.setCookie(response, loginCookieKey, context.toCookieValue());
                        }
                    } else {
                        log.debug("session cookie[" + loginCookieKey + "] is empty!");
                    }
                }
            } catch (Exception e) {
                log.error("login intercept error", e);
            }
        } else {
            log.debug("session cookie key is empty!");
        }
//        log.info("登录完毕。pin=" + (LoginContext.getLoginContext() != null ? LoginContext.getLoginContext().getPin() : "null"));
    }




    public void setDotnetAuthCookieName(String dotnetAuthCookieName) {
        this.dotnetAuthCookieName = dotnetAuthCookieName;
    }

    public void setDotnetAuthenticationKey(String dotnetAuthenticationKey) {
        this.dotnetAuthenticationKey = dotnetAuthenticationKey;
    }
}
