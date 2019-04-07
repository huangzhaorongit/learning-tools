package com.distribution.wamoli.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.distribution.wamoli.common.bean.BasePojo;
@Entity
@Table(name = "f_systemmenu")
public class FSystemmenu extends BasePojo<FSystemmenu>{
    @Id
    private String menuid;

    /**
     * 00=目录 01=菜单
     */
    private String menutype;

    private String menuname;

    /**
     * 顶级菜单缺省为‘00’
     */
    private String parentid;

    private String companyid;

    /**
     * 文件图标
     */
    private String icon;

    /**
     * 显示图标的id
     */
    private String iconcls;

    private String iconcolor;

    /**
     * 0- 否 1-是
     */
    private String isdisplay;

    /**
     * 平级显示顺序
     */
    private Integer orderno;

    /**
     * 关联的系统模块
     */
    private String moduleid;

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
    
    


	public FSystemmenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * @return menuid
     */
    public String getMenuid() {
        return menuid;
    }

    /**
     * @param menuid
     */
    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    /**
     * 获取00=目录 01=菜单
     *
     * @return menutype - 00=目录 01=菜单
     */
    public String getMenutype() {
        return menutype;
    }

    /**
     * 设置00=目录 01=菜单
     *
     * @param menutype 00=目录 01=菜单
     */
    public void setMenutype(String menutype) {
        this.menutype = menutype;
    }

    /**
     * @return menuname
     */
    public String getMenuname() {
        return menuname;
    }

    /**
     * @param menuname
     */
    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    /**
     * 获取顶级菜单缺省为‘00’
     *
     * @return parentid - 顶级菜单缺省为‘00’
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * 设置顶级菜单缺省为‘00’
     *
     * @param parentid 顶级菜单缺省为‘00’
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
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
     * 获取文件图标
     *
     * @return icon - 文件图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置文件图标
     *
     * @param icon 文件图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取显示图标的id
     *
     * @return iconcls - 显示图标的id
     */
    public String getIconcls() {
        return iconcls;
    }

    /**
     * 设置显示图标的id
     *
     * @param iconcls 显示图标的id
     */
    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }

    /**
     * @return iconcolor
     */
    public String getIconcolor() {
        return iconcolor;
    }

    /**
     * @param iconcolor
     */
    public void setIconcolor(String iconcolor) {
        this.iconcolor = iconcolor;
    }

    /**
     * 获取0- 否 1-是
     *
     * @return isdisplay - 0- 否 1-是
     */
    public String getIsdisplay() {
        return isdisplay;
    }

    /**
     * 设置0- 否 1-是
     *
     * @param isdisplay 0- 否 1-是
     */
    public void setIsdisplay(String isdisplay) {
        this.isdisplay = isdisplay;
    }

    /**
     * 获取平级显示顺序
     *
     * @return orderno - 平级显示顺序
     */
    public Integer getOrderno() {
        return orderno;
    }

    /**
     * 设置平级显示顺序
     *
     * @param orderno 平级显示顺序
     */
    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    /**
     * 获取关联的系统模块
     *
     * @return moduleid - 关联的系统模块
     */
    public String getModuleid() {
        return moduleid;
    }

    /**
     * 设置关联的系统模块
     *
     * @param moduleid 关联的系统模块
     */
    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
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
}