package com.jd.appstore.action;

import com.jd.common.struts.action.BaseAction;
import com.jd.common.web.result.Result;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.commons.lang.StringUtils;


import java.util.Set;

/**
 * 重写BaseAction的方法，因为BaseAction中的toVm方法会出现数组越界异常
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12-3-27
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */
public class AppStoreBaseAction extends BaseAction{

    /**
     * 将result中的值写入值栈
     * 会写入result变量，同时会把reuslt里面map的内容写入。
     * 对于消息。如果result返回成功，则写入message，否则写入error
     *
     * @param result 结果
     */
    protected void toVm(Result result) {

        ValueStack context = ActionContext.getContext().getValueStack();
        context.set("textProvider", this);
		context.set("datePickerLocale", getDatePickerLocale());
        context.set("result", result);
        Set<String> set = result.keySet();
        for (String key : set) {
            context.set(key, result.get(key));
        }
        String resultCode = result.getResultCode();
        if (StringUtils.isNotBlank(resultCode)) {
            String text;
            String[] params = result.getResultCodeParams();
            if (params != null && params.length > 0) {
                text = getText(resultCode, params);
            } else {
                text = getText(resultCode);
            }
            if (result.getSuccess()) {
                addActionMessage(text);
            } else {
                addActionError(text);
            }
        }
    }

    //日历控件My97DatePicker使用
    // 覆盖BaseAction 中的 getDatePickerLocale 方法。因为方法中会出现输入越界异常
	private String getDatePickerLocale() {
        if (getLocale() != null) {
            String locale = getLocale().toString().toLowerCase();
            String[] arr = locale.split("_");
            if (arr.length == 1 || arr[0].equals("en")) {
                // 输入长度等于1，或者第一个是en，就直接返回第一个
                locale = arr[0];
            } else if (arr.length > 1) {
                // 数组大于1，就拼装返回
                locale = arr[0] + "-" + arr[1];
            }

            return locale;
        } else {
            return null;
        }
    }

}
