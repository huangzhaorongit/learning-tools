package com.distribution.wamoli.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.distribution.wamoli.common.bean.BasePojo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "f_systemuser")
public class FSystemuser extends BasePojo<FSystemuser> implements UserDetails {
    @Id
    private String userid;

    private String username;

    private String name;

    private String password;
    /**
     * v_passwordlevel
     */
    private String passwordlevel;

    /**
     * 密码变更时间
     */
    private Date alterdate;

    /**
     * 1-是  0-否
     */
    private String islongvalid;

    /**
     * 缺省单位为天
     */
    private Integer alertperiod;

    /**
     * 用户生效时间
     */
    private Date startvailddate;

    /**
     * 用户失效时间
     */
    private Date outvailddate;

    /**
     * v_loginauthtype
     */
    private String loginauthtype;

    private String personnelid;

    /**
     * 外部用户：为空 ；内部用户：从职员信息表中获取
     */
    private String companyid;

    /**
     * 0- 否 1-是
     */
    private String isvalid;

    /**
     * 0-否  1-是   是否锁定，表示临时锁住用户，用户不能登录系统
     */
    private String islocked;

    private String creater;

    private Date createdate;

    private String lastmodifier;

    /**
     * 主数据变动的时间戳
     */
    private Date lastmodifydate;

    /**
     * 分辨率
     */
    private String resolution;

    /**
     * 是否显示背景
     */
    private String isshowbg;

    /**
     * 背景图片地址
     */
    private String bgsrc;

    /**
     * 分辨率
     */
    private String custom1;

    /**
     * 是否显示背景
     */
    private String custom2;

    /**
     * 背景图片地址
     */
    private String custom3;

    private String custom4;

    private String custom5;

    private BigDecimal custom6;

    private BigDecimal custom7;

    @Transient
    private List<? extends GrantedAuthority> authorities;


    public FSystemuser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FSystemuser(String userid) {
		this.userid=userid;
	}

	/**
     * @return userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        Date dt = new Date();
        return  dt.before(outvailddate) && dt.after(startvailddate);
    }

    @Override
    public boolean isAccountNonLocked() {
        return "0".equals(islocked);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "1".equals(isvalid);
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /*
     * @param username
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(List<? extends GrantedAuthority> authorities){
        this.authorities = authorities;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取v_passwordlevel
     *
     * @return passwordlevel - v_passwordlevel
     */
    public String getPasswordlevel() {
        return passwordlevel;
    }

    /**
     * 设置v_passwordlevel
     *
     * @param passwordlevel v_passwordlevel
     */
    public void setPasswordlevel(String passwordlevel) {
        this.passwordlevel = passwordlevel;
    }

    /**
     * 获取密码变更时间
     *
     * @return alterdate - 密码变更时间
     */
    public Date getAlterdate() {
        return alterdate;
    }

    /**
     * 设置密码变更时间
     *
     * @param alterdate 密码变更时间
     */
    public void setAlterdate(Date alterdate) {
        this.alterdate = alterdate;
    }

    /**
     * 获取1-是  0-否
     *
     * @return islongvalid - 1-是  0-否
     */
    public String getIslongvalid() {
        return islongvalid;
    }

    /**
     * 设置1-是  0-否
     *
     * @param islongvalid 1-是  0-否
     */
    public void setIslongvalid(String islongvalid) {
        this.islongvalid = islongvalid;
    }

    /**
     * 获取缺省单位为天
     *
     * @return alertperiod - 缺省单位为天
     */
    public Integer getAlertperiod() {
        return alertperiod;
    }

    /**
     * 设置缺省单位为天
     *
     * @param alertperiod 缺省单位为天
     */
    public void setAlertperiod(Integer alertperiod) {
        this.alertperiod = alertperiod;
    }

    /**
     * 获取用户生效时间
     *
     * @return startvailddate - 用户生效时间
     */
    public Date getStartvailddate() {
        return startvailddate;
    }

    /**
     * 设置用户生效时间
     *
     * @param startvailddate 用户生效时间
     */
    public void setStartvailddate(Date startvailddate) {
        this.startvailddate = startvailddate;
    }

    /**
     * 获取用户失效时间
     *
     * @return outvailddate - 用户失效时间
     */
    public Date getOutvailddate() {
        return outvailddate;
    }

    /**
     * 设置用户失效时间
     *
     * @param outvailddate 用户失效时间
     */
    public void setOutvailddate(Date outvailddate) {
        this.outvailddate = outvailddate;
    }

    /**
     * 获取v_loginauthtype
     *
     * @return loginauthtype - v_loginauthtype
     */
    public String getLoginauthtype() {
        return loginauthtype;
    }

    /**
     * 设置v_loginauthtype
     *
     * @param loginauthtype v_loginauthtype
     */
    public void setLoginauthtype(String loginauthtype) {
        this.loginauthtype = loginauthtype;
    }

    /**
     * @return personnelid
     */
    public String getPersonnelid() {
        return personnelid;
    }

    /**
     * @param personnelid
     */
    public void setPersonnelid(String personnelid) {
        this.personnelid = personnelid;
    }

    /**
     * 获取外部用户：为空 ；内部用户：从职员信息表中获取
     *
     * @return companyid - 外部用户：为空 ；内部用户：从职员信息表中获取
     */
    public String getCompanyid() {
        return companyid;
    }

    /**
     * 设置外部用户：为空 ；内部用户：从职员信息表中获取
     *
     * @param companyid 外部用户：为空 ；内部用户：从职员信息表中获取
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
     * 获取0-否  1-是   是否锁定，表示临时锁住用户，用户不能登录系统
     *
     * @return islocked - 0-否  1-是   是否锁定，表示临时锁住用户，用户不能登录系统
     */
    public String getIslocked() {
        return islocked;
    }

    /**
     * 设置0-否  1-是   是否锁定，表示临时锁住用户，用户不能登录系统
     *
     * @param islocked 0-否  1-是   是否锁定，表示临时锁住用户，用户不能登录系统
     */
    public void setIslocked(String islocked) {
        this.islocked = islocked;
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
     * 获取分辨率
     *
     * @return resolution - 分辨率
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * 设置分辨率
     *
     * @param resolution 分辨率
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * 获取是否显示背景
     *
     * @return isshowbg - 是否显示背景
     */
    public String getIsshowbg() {
        return isshowbg;
    }

    /**
     * 设置是否显示背景
     *
     * @param isshowbg 是否显示背景
     */
    public void setIsshowbg(String isshowbg) {
        this.isshowbg = isshowbg;
    }

    /**
     * 获取背景图片地址
     *
     * @return bgsrc - 背景图片地址
     */
    public String getBgsrc() {
        return bgsrc;
    }

    /**
     * 设置背景图片地址
     *
     * @param bgsrc 背景图片地址
     */
    public void setBgsrc(String bgsrc) {
        this.bgsrc = bgsrc;
    }

    /**
     * 获取分辨率
     *
     * @return custom1 - 分辨率
     */
    public String getCustom1() {
        return custom1;
    }

    /**
     * 设置分辨率
     *
     * @param custom1 分辨率
     */
    public void setCustom1(String custom1) {
        this.custom1 = custom1;
    }

    /**
     * 获取是否显示背景
     *
     * @return custom2 - 是否显示背景
     */
    public String getCustom2() {
        return custom2;
    }

    /**
     * 设置是否显示背景
     *
     * @param custom2 是否显示背景
     */
    public void setCustom2(String custom2) {
        this.custom2 = custom2;
    }

    /**
     * 获取背景图片地址
     *
     * @return custom3 - 背景图片地址
     */
    public String getCustom3() {
        return custom3;
    }

    /**
     * 设置背景图片地址
     *
     * @param custom3 背景图片地址
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
