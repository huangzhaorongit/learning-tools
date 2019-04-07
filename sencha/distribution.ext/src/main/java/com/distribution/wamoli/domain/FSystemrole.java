package com.distribution.wamoli.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.distribution.wamoli.common.bean.BasePojo;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "f_systemrole")
public class FSystemrole extends BasePojo<FSystemrole> implements GrantedAuthority {
    /**
     * 自动生成
     */
    @Id
    private String roleid;

    private String rolecode;

    /**
     *

     */
    private String rolename;

    private String companyid;

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

    private String roledescription;




    public FSystemrole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FSystemrole(String roleid) {

    	this.roleid=roleid;
	}

	/**
     * 获取自动生成
     *
     * @return roleid - 自动生成
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * 设置自动生成
     *
     * @param roleid 自动生成
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    /**
     * @return rolecode
     */
    public String getRolecode() {
        return rolecode;
    }

    /**
     * @param rolecode
     */
    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    /**
     * 获取

     *
     * @return rolename -

     */
    public String getRolename() {
        return rolename;
    }

    /**
     * 设置

     *
     * @param rolename

     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * @return companyid
     */
    public String getCompanyid() {
        return companyid;
    }

    /**
     * @param companyid
     */
    public void setCompanyid(String companyid) {
        this.companyid = companyid;
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
     * @return roledescription
     */
    public String getRoledescription() {
        return roledescription;
    }

    /**
     * @param roledescription
     */
    public void setRoledescription(String roledescription) {
        this.roledescription = roledescription;
    }

    @Override
    public String getAuthority() {
        return roleid;
    }
}
