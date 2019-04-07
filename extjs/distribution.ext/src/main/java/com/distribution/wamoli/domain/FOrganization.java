package com.distribution.wamoli.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.distribution.wamoli.common.bean.BasePojo;
@Entity
@Table(name = "f_organization")
public class FOrganization extends BasePojo<FOrganization>{
    @Id
    private String orgid;

    private String orgcode;

    private String orgname;

    /**
     * 01-业务部门   02-组织单元
     */
    private String orgtype;

    /**
     * 所在公司id
     */
    private String companyid;

    private String manager;

    private String parentid;

    private String telephone;

    private String address;

    private Integer orderno;

    /**
     * 附件个数，可以上传多个,默认为0
     */
    private Integer attachsnum;

    /**
     * 0- 否 1-是
     */
    private String isvalid;

    private String creater;

    private Date createdate;

    private String lastmodifier;

    /**
     * 主数据变动的时间戳
     */
    private Date lastmodifydate;

    private String custom1;

    private String custom2;

    private String custom3;

    private String custom4;

    private String custom5;

    private BigDecimal custom6;

    private BigDecimal custom7;

    private String remarks;
    
    

    public FOrganization() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * @return orgid
     */
    public String getOrgid() {
        return orgid;
    }

    /**
     * @param orgid
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    /**
     * @return orgcode
     */
    public String getOrgcode() {
        return orgcode;
    }

    /**
     * @param orgcode
     */
    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    /**
     * @return orgname
     */
    public String getOrgname() {
        return orgname;
    }

    /**
     * @param orgname
     */
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    /**
     * 获取01-业务部门   02-组织单元
     *
     * @return orgtype - 01-业务部门   02-组织单元
     */
    public String getOrgtype() {
        return orgtype;
    }

    /**
     * 设置01-业务部门   02-组织单元
     *
     * @param orgtype 01-业务部门   02-组织单元
     */
    public void setOrgtype(String orgtype) {
        this.orgtype = orgtype;
    }

    /**
     * 获取所在公司id
     *
     * @return companyid - 所在公司id
     */
    public String getCompanyid() {
        return companyid;
    }

    /**
     * 设置所在公司id
     *
     * @param companyid 所在公司id
     */
    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    /**
     * @return manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * @param manager
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * @return parentid
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * @param parentid
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return orderno
     */
    public Integer getOrderno() {
        return orderno;
    }

    /**
     * @param orderno
     */
    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    /**
     * 获取附件个数，可以上传多个,默认为0
     *
     * @return attachsnum - 附件个数，可以上传多个,默认为0
     */
    public Integer getAttachsnum() {
        return attachsnum;
    }

    /**
     * 设置附件个数，可以上传多个,默认为0
     *
     * @param attachsnum 附件个数，可以上传多个,默认为0
     */
    public void setAttachsnum(Integer attachsnum) {
        this.attachsnum = attachsnum;
    }

    /**
     * 获取0- 否 1-是
     *
     * @return isvalid - 0- 否 1-是
     */
    public String getIsvalid() {
        return isvalid;
    }

    /**
     * 设置0- 否 1-是
     *
     * @param isvalid 0- 否 1-是
     */
    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    /**
     * @return creater
     */
    public String getCreater() {
        return creater;
    }

    /**
     * @param creater
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * @return createdate
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * @return lastmodifier
     */
    public String getLastmodifier() {
        return lastmodifier;
    }

    /**
     * @param lastmodifier
     */
    public void setLastmodifier(String lastmodifier) {
        this.lastmodifier = lastmodifier;
    }

    /**
     * 获取主数据变动的时间戳
     *
     * @return lastmodifydate - 主数据变动的时间戳
     */
    public Date getLastmodifydate() {
        return lastmodifydate;
    }

    /**
     * 设置主数据变动的时间戳
     *
     * @param lastmodifydate 主数据变动的时间戳
     */
    public void setLastmodifydate(Date lastmodifydate) {
        this.lastmodifydate = lastmodifydate;
    }

    /**
     * @return custom1
     */
    public String getCustom1() {
        return custom1;
    }

    /**
     * @param custom1
     */
    public void setCustom1(String custom1) {
        this.custom1 = custom1;
    }

    /**
     * @return custom2
     */
    public String getCustom2() {
        return custom2;
    }

    /**
     * @param custom2
     */
    public void setCustom2(String custom2) {
        this.custom2 = custom2;
    }

    /**
     * @return custom3
     */
    public String getCustom3() {
        return custom3;
    }

    /**
     * @param custom3
     */
    public void setCustom3(String custom3) {
        this.custom3 = custom3;
    }

    /**
     * @return custom4
     */
    public String getCustom4() {
        return custom4;
    }

    /**
     * @param custom4
     */
    public void setCustom4(String custom4) {
        this.custom4 = custom4;
    }

    /**
     * @return custom5
     */
    public String getCustom5() {
        return custom5;
    }

    /**
     * @param custom5
     */
    public void setCustom5(String custom5) {
        this.custom5 = custom5;
    }

    /**
     * @return custom6
     */
    public BigDecimal getCustom6() {
        return custom6;
    }

    /**
     * @param custom6
     */
    public void setCustom6(BigDecimal custom6) {
        this.custom6 = custom6;
    }

    /**
     * @return custom7
     */
    public BigDecimal getCustom7() {
        return custom7;
    }

    /**
     * @param custom7
     */
    public void setCustom7(BigDecimal custom7) {
        this.custom7 = custom7;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}