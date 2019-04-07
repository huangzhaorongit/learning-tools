package com.distribution.wamoli.common.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userid; // 用户ID
	private String username; // 用户代码
	private String name; // 用户名称
	private String usertype; // 用户类型

	private String companyid; // 公司ID (本部)
	private String companyname; // 公司名称(本部)
	private String companylongname; // 公司全称
	private String companycode; // 公司单据规则编码缩写
	private String levelid; // 公司级别

	private String departmentid; // 部门ID
	private String departmentcode; // 部门代码
	private String departmentname; // 部门名称
	private String departmenttype; // 部门类型

	private String personnelid; // 人员ID
	private String personnelcode; // 人员编号
	private String personnelname; // 人员姓名
	private String position; // 岗位
	private String photoid; // 人员头像id

	private String sessionid; // 当前登录人员sessionid
	private String pdfformat; // 可以转换pdf文件的格式

	private String basepath; //项目上下文

	private Map<String,List<Map<String,Object>>> systemlimits;  //当前用户菜单中按钮权限
	private Map<String,SystemUrlBean> systemurls;  				//当前全部菜单的url地址
	private Map<String,Boolean> urllimits;						//按钮请求URL权限

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 00-系统管理员 01-普通用户
	 *
	 * @return
	 */
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getCompanylongname() {
		return companylongname;
	}

	public void setCompanylongname(String companylongname) {
		this.companylongname = companylongname;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getDepartmentcode() {
		return departmentcode;
	}

	public void setDepartmentcode(String departmentcode) {
		this.departmentcode = departmentcode;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getDepartmenttype() {
		return departmenttype;
	}

	public void setDepartmenttype(String departmenttype) {
		this.departmenttype = departmenttype;
	}

	public String getPersonnelid() {
		return personnelid;
	}

	public void setPersonnelid(String personnelid) {
		this.personnelid = personnelid;
	}

	public String getPersonnelname() {
		return personnelname;
	}

	public void setPersonnelname(String personnelname) {
		this.personnelname = personnelname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	public String getLevelid() {
		return levelid;
	}

	public void setLevelid(String levelid) {
		this.levelid = levelid;
	}

	public String getPersonnelcode() {
		return personnelcode;
	}

	public void setPersonnelcode(String personnelcode) {
		this.personnelcode = personnelcode;
	}

	public String getPhotoid() {
		return photoid;
	}

	public void setPhotoid(String photoid) {
		this.photoid = photoid;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getPdfformat() {
		return pdfformat;
	}

	public void setPdfformat(String pdfformat) {
		this.pdfformat = pdfformat;
	}

	public Map<String, List<Map<String, Object>>> getSystemlimits() {
		return systemlimits;
	}

	public void setSystemlimits(Map<String, List<Map<String, Object>>> systemlimits) {
		this.systemlimits = systemlimits;
	}

	public Map<String, SystemUrlBean> getSystemurls() {
		return systemurls;
	}

	public void setSystemurls(Map<String, SystemUrlBean> systemurls) {
		this.systemurls = systemurls;
	}

	public String getBasepath() {
		return basepath;
	}

	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	public Map<String, Boolean> getUrllimits() {
		return urllimits;
	}

	public void setUrllimits(Map<String, Boolean> urllimits) {
		this.urllimits = urllimits;
	}
}
