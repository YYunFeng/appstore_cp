package com.jd.appstore.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-4
 * Time: 下午6:42
 * CP基本信息表
 */
public class CpBaseinfo implements Serializable {
    /**
     * CP基本信息表主键
     */
    private int id;
    /**
     * CP登录帐号
     */
    private String account;
    /**
     * 登录密码
     */
    private String password;
    /**
     * CP名称
     */
    private String cpName;
    /**
     * 供应商简码
     */
    private String supplierNo;
    /**
     * 注册时间
     */
    private Date regTime;
    /**
     * CP性质
     */
    private Integer cpProperty;
    /**
     * CP类型
     */
    private Integer cpType;
    /**
     * 收费资质状态
     */
    private Integer feeStatus;
    /**
     * 帐户开开通状态
     */
    private Integer accountStatus;
    /**
     * 注册信息更改状态
     */
    private Integer updateStatus;
    /**
     * 信息变更审核备注
     */
    private String updateComment;
    /**
     * 合同号
     */
    private String contractNumber;
    /**
     * 合同生效时间
     */
    private Date validPeriodBegin;
    /**
     * 合同失效时间
     */
    private Date validPeriodEnd;
    /**
     * 默认计费模式
     */
    private Integer defaultFeeMode;
    /**
     * 默认CP分成比例
     */
    private Integer defaultPercent;
    /**
     * 收费资质审核备注
     */
    private String comment;
    /**
     * 记录创建时间
     */
    private Date created;
    /**
     * 记录修改时间
     */
    private Date modified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getSupplierNo() {
        return supplierNo;
    }

    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Integer getCpProperty() {
        return cpProperty;
    }

    public void setCpProperty(Integer cpProperty) {
        this.cpProperty = cpProperty;
    }

    public Integer getCpType() {
        return cpType;
    }

    public void setCpType(Integer cpType) {
        this.cpType = cpType;
    }

    public Integer getFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(Integer feeStatus) {
        this.feeStatus = feeStatus;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getUpdateComment() {
        return updateComment;
    }

    public void setUpdateComment(String updateComment) {
        this.updateComment = updateComment;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Date getValidPeriodBegin() {
        return validPeriodBegin;
    }

    public void setValidPeriodBegin(Date validPeriodBegin) {
        this.validPeriodBegin = validPeriodBegin;
    }

    public Date getValidPeriodEnd() {
        return validPeriodEnd;
    }

    public void setValidPeriodEnd(Date validPeriodEnd) {
        this.validPeriodEnd = validPeriodEnd;
    }

    public Integer getDefaultFeeMode() {
        return defaultFeeMode;
    }

    public void setDefaultFeeMode(Integer defaultFeeMode) {
        this.defaultFeeMode = defaultFeeMode;
    }

    public Integer getDefaultPercent() {
        return defaultPercent;
    }

    public void setDefaultPercent(Integer defaultPercent) {
        this.defaultPercent = defaultPercent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
