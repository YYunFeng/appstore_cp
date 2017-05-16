package com.jd.appstore.web.interceptor.cp;

import com.jd.appstore.service.cp.CpBaseinfoService;
import com.jd.common.struts.interceptor.JdInterceptor;
import com.jd.common.util.StringUtils;
import com.jd.common.web.security.FilePathSecurity;
import com.jd.common.web.url.JdUrl;
import com.jd.common.web.url.JdUrlUtils;
import com.jd.digital.common.util.tool.WebHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * appstore登录拦截器
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12-8-29
 * Time: 下午7:02
 * To change this template use File | Settings | File Templates.
 */
public class AppStoreLoginInterceptor extends JdInterceptor {
    private Logger logger = Logger.getLogger(this.getClass());
    private CpBaseinfoService cpBaseinfoService;// 登录信息

    private JdUrlUtils jdUrlUtils;
    private static final String jdMainLoginUrl = "jdMainLoginUrl";// 京东主站url的key
    private static final String homeModule = "homeModule";// 本站url
    private FilePathSecurity jdSecurity;

    public String intercept(ActionInvocation invocation) throws Exception {
        if (jdSecurity != null) {
            ActionContext actionContext = invocation.getInvocationContext();
            HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
            logger.info(request.getRequestURI());
            if (jdSecurity.isProtected(request.getRequestURI())) {
                String pin = WebHelper.getPin();
                if (StringUtils.isBlank(pin)) {
                    // 未登录
                    return getNoLoginUrl();
                }
            }
        }
        return invocation.invoke();
    }


    protected String getNoLoginUrl() {
        return "login";
    }

    protected void setLoginUrl() {
        ActionContext actionContext = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        JdUrl loginUrl = jdUrlUtils.getJdUrl(jdMainLoginUrl);
        String currentUrl = getCurrentUrl(request);
        loginUrl.addQueryData("ReturnUrl", currentUrl);
        ValueStack valueStack = actionContext.getValueStack();
        valueStack.set("returnurl", loginUrl.encodeUrl(currentUrl));
        valueStack.set("jdMainLoginUrl", loginUrl.toString());
    }

    protected String getCurrentUrl(HttpServletRequest request) {
        JdUrl venderUrl = jdUrlUtils.getJdUrl(homeModule);
        venderUrl.getTarget(request.getRequestURI());
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = (String) parameterNames.nextElement();
            venderUrl.addQueryData(key, request.getParameterValues(key));
        }
        return venderUrl.toString();
    }

    public void setCpBaseinfoService(CpBaseinfoService cpBaseinfoService) {
        this.cpBaseinfoService = cpBaseinfoService;
    }

    public void setJdSecurity(FilePathSecurity jdSecurity) {
        this.jdSecurity = jdSecurity;
    }

    public void setJdUrlUtils(JdUrlUtils jdUrlUtils) {
        this.jdUrlUtils = jdUrlUtils;
    }
}
