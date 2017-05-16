package com.jd.appstore.web.interceptor.cp;

import com.jd.appstore.web.action.cp.IndexAction;
import com.jd.appstore.web.action.cp.RegistAction;
import com.jd.appstore.web.action.cp.UploadAction;
import com.jd.appstore.web.util.cp.CookieOper;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-18
 * Time: 下午2:39
 * To change this template use File | Settings | File Templates.
 */
public class CheckLoginInterceptor implements Interceptor {
    private Logger logger = Logger.getLogger(this.getClass());

    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void init() {
        // TODO Auto-generated method stub
    }

    /**
     * 
     * 方法拦截器
     **/
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        try {
            Object action = actionInvocation.getAction();
            CookieOper cookieOper;
            //获取request
            ActionContext context = actionInvocation.getInvocationContext();
            HttpServletRequest httpServletRequest = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
            //获取cookie
            cookieOper = new CookieOper();
            Cookie cookie = cookieOper.getCookies(httpServletRequest, "account");
            //获取请求的路径

            if (action instanceof IndexAction) {
                return actionInvocation.invoke();
            }
            if (action instanceof RegistAction) {
                return actionInvocation.invoke();
            }
            if (action instanceof UploadAction) {
                return actionInvocation.invoke();
            }
            //判断是否登录,如果登录判断是否有访问的权限
            if (cookie == null || cookie.equals("")) {
                long currTime = System.currentTimeMillis();
                // 重定向到登录界面
                return Action.LOGIN;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actionInvocation.invoke();
    }
}


