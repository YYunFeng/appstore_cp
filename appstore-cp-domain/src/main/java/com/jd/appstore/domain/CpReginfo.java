package com.jd.appstore.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-4
 * Time: 下午6:56
 * To change this template use File | Settings | File Templates.
 * CP注册信息表
 */
public class CpReginfo implements Serializable {
    /**
     * CP注册信息表的主键
     */
    private Integer id;
    /**
     * CP帐号Id
     */
    private Integer accountId;
    /**
     * 是信息副本
     */
    private Integer isCopy;
    /**
     * CP英文名
     */
    private String ename;
    /**
     * 证件类型
     */
    private Integer certificateType;
    /**
     * 证件号码
     */
    private String certificateNo;
    /**
     * 证件图片
     */
    private String certificatePhoto;
    /**
     * 网站
     */
    private String webadd;
    /**
     * 联系人姓名
     */
    private String contacter;
    /**
     * 通讯地址
     */
    private String address;
    /**
     * 固定电话
     */
    private String phone;
    /**
     * 传真
     */
    private String fax;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 手机绑定状态
     */
    private Integer mobileBinded;
    /**
     * Email
     */
    private String email;
    /**
     * Email验证状态
     */
    private Integer emailChecked;
    /**
     * CP简介
     */
    private String intro;
    /**
     * 其它联系方式
     */
    private String otherContact;
    /**
     * 记录创建时间
     */
    private Date created;
    /**
     * 记录修改时间
     */
    private Date modified;
    private Integer provinceId;
    private Integer cityId;
    private Integer districtId;
    private String province;
    private String city;
    private String district;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCopy() {
        return isCopy;
    }

    public void setCopy(Integer copy) {
        isCopy = copy;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getCertificatePhoto() {
        return certificatePhoto;
    }

    public void setCertificatePhoto(String certificatePhoto) {
        this.certificatePhoto = certificatePhoto;
    }

    public String getWebadd() {
        return webadd;
    }

    public void setWebadd(String webadd) {
        this.webadd = webadd;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getMobileBinded() {
        return mobileBinded;
    }

    public void setMobileBinded(Integer mobileBinded) {
        this.mobileBinded = mobileBinded;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmailChecked() {
        return emailChecked;
    }

    public void setEmailChecked(Integer emailChecked) {
        this.emailChecked = emailChecked;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(String otherContact) {
        this.otherContact = otherContact;
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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
