package com.jd.appstore.domain.json;

/**
 * Created with IntelliJ IDEA.
 * User: YUNFENG
 * Date: 13-4-15
 * Time: 下午3:03
 * To change this template use File | Settings | File Templates.
 */
public class ExportExcelJSON {
    private String excelDownAddress;
    private String mess;
    private String code;
    private Integer status;

    public String getExcelDownAddress() {
        return excelDownAddress;
    }

    public void setExcelDownAddress(String excelDownAddress) {
        this.excelDownAddress = excelDownAddress;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
