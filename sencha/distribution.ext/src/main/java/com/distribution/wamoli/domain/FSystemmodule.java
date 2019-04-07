package com.distribution.wamoli.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.distribution.wamoli.common.bean.BasePojo;
@Entity
@Table(name = "f_systemmodule")
public class FSystemmodule extends BasePojo<FSystemmodule>{
    @Id
    private String moduleid;

    private String modulecode;

    private String parentid;

    private String modulename;

    /**
     * 01 基础配置类  02业务操作类  03数据查询类 04 统计分析类
     */
    private String moduletype;

    /**
     * 模块系统入口的url地址。
     */
    private String moduleurl;

    private Integer orderno;

    /**
     * 模块操作帮助文件id
     */
    private String helpid;

    private String companyid;

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

    /**
     * 是否有效
     */
    private String isdisplay;

    private String description;
    
    
    
    

    public FSystemmodule() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * @return moduleid
     */
    public String getModuleid() {
        return moduleid;
    }

    /**
     * @param moduleid
     */
    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    /**
     * @return modulecode
     */
    public String getModulecode() {
        return modulecode;
    }

    /**
     * @param modulecode
     */
    public void setModulecode(String modulecode) {
        this.modulecode = modulecode;
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
     * @return modulename
     */
    public String getModulename() {
        return modulename;
    }

    /**
     * @param modulename
     */
    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    /**
     * 获取01 基础配置类  02业务操作类  03数据查询类 04 统计分析类
     *
     * @return moduletype - 01 基础配置类  02业务操作类  03数据查询类 04 统计分析类
     */
    public String getModuletype() {
        return moduletype;
    }

    /**
     * 设置01 基础配置类  02业务操作类  03数据查询类 04 统计分析类
     *
     * @param moduletype 01 基础配置类  02业务操作类  03数据查询类 04 统计分析类
     */
    public void setModuletype(String moduletype) {
        this.moduletype = moduletype;
    }

    /**
     * 获取模块系统入口的url地址。
     *
     * @return moduleurl - 模块系统入口的url地址。
     */
    public String getModuleurl() {
        return moduleurl;
    }

    /**
     * 设置模块系统入口的url地址。
     *
     * @param moduleurl 模块系统入口的url地址。
     */
    public void setModuleurl(String moduleurl) {
        this.moduleurl = moduleurl;
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
     * 获取模块操作帮助文件id
     *
     * @return helpid - 模块操作帮助文件id
     */
    public String getHelpid() {
        return helpid;
    }

    /**
     * 设置模块操作帮助文件id
     *
     * @param helpid 模块操作帮助文件id
     */
    public void setHelpid(String helpid) {
        this.helpid = helpid;
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
     * 获取是否有效
     *
     * @return isdisplay - 是否有效
     */
    public String getIsdisplay() {
        return isdisplay;
    }

    /**
     * 设置是否有效
     *
     * @param isdisplay 是否有效
     */
    public void setIsdisplay(String isdisplay) {
        this.isdisplay = isdisplay;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}