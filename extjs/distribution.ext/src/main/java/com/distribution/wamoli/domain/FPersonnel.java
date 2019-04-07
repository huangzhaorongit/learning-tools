package com.distribution.wamoli.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.distribution.wamoli.common.bean.BasePojo;
@Entity
@Table(name = "f_personnel")
public class FPersonnel extends BasePojo<FPersonnel> {
    /**
     * 人员ID
     */
    @Id
    private String personnelid;

    /**
     * 人员编号
     */
    private String personnelcode;

    /**
     * 人员姓名
     */
    private String personnelname;

    /**
     * 对应组织orgid
     */
    private String departmentid;

    /**
     * 所在公司id
     */
    private String companyid;

    /**
     * 职位
     */
    private String position;

    /**
     * v_sex
     */
    private String sex;

    /**
     * 出身日期
     */
    private Date birthdate;

    /**
     * 参加工作日期
     */
    private Date workdate;

    /**
     * 第一专业
     */
    private String firstprofessional;

    /**
     * 第一学历
     */
    private String firsteducation;

    /**
     * 第一院校
     */
    private String firstcollage;

    /**
     * 最终学历
     */
    private String education;

    /**
     * 最终学位
     */
    private String degree;

    /**
     * 最终专业
     */
    private String professional;

    /**
     * 毕业院校
     */
    private String collage;

    /**
     * v_maritalstatus 00-未婚 01-已婚 
     */
    private String maritalstatus;

    /**
     * 身份证号
     */
    private String idcode;

    /**
     * 社会保障号
     */
    private String insurcode;

    /**
     * 家庭住址
     */
    private String homeaddress;

    /**
     * 邮政编码
     */
    private String postalcode;

    /**
     * 办公电话
     */
    private String officetel;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * v_politicalface 00-群众 01-党员
     */
    private String politicalface;

    /**
     * 加入组织时间
     */
    private Date joindate;

    /**
     * 相片
     */
    private String photo;

    /**
     * 0- 否 1-是
     */
    private String isvalid;

    /**
     * 附件个数，可以上传多个,默认为0
     */
    private Short attachsnum;

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
     * 工作简介
     */
    private String workremark;
    
    

    public FPersonnel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * 获取人员ID
     *
     * @return personnelid - 人员ID
     */
    public String getPersonnelid() {
        return personnelid;
    }

    /**
     * 设置人员ID
     *
     * @param personnelid 人员ID
     */
    public void setPersonnelid(String personnelid) {
        this.personnelid = personnelid;
    }

    /**
     * 获取人员编号
     *
     * @return personnelcode - 人员编号
     */
    public String getPersonnelcode() {
        return personnelcode;
    }

    /**
     * 设置人员编号
     *
     * @param personnelcode 人员编号
     */
    public void setPersonnelcode(String personnelcode) {
        this.personnelcode = personnelcode;
    }

    /**
     * 获取人员姓名
     *
     * @return personnelname - 人员姓名
     */
    public String getPersonnelname() {
        return personnelname;
    }

    /**
     * 设置人员姓名
     *
     * @param personnelname 人员姓名
     */
    public void setPersonnelname(String personnelname) {
        this.personnelname = personnelname;
    }

    /**
     * 获取对应组织orgid
     *
     * @return departmentid - 对应组织orgid
     */
    public String getDepartmentid() {
        return departmentid;
    }

    /**
     * 设置对应组织orgid
     *
     * @param departmentid 对应组织orgid
     */
    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
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
     * 获取职位
     *
     * @return position - 职位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置职位
     *
     * @param position 职位
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取v_sex
     *
     * @return sex - v_sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置v_sex
     *
     * @param sex v_sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取出身日期
     *
     * @return birthdate - 出身日期
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * 设置出身日期
     *
     * @param birthdate 出身日期
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * 获取参加工作日期
     *
     * @return workdate - 参加工作日期
     */
    public Date getWorkdate() {
        return workdate;
    }

    /**
     * 设置参加工作日期
     *
     * @param workdate 参加工作日期
     */
    public void setWorkdate(Date workdate) {
        this.workdate = workdate;
    }

    /**
     * 获取第一专业
     *
     * @return firstprofessional - 第一专业
     */
    public String getFirstprofessional() {
        return firstprofessional;
    }

    /**
     * 设置第一专业
     *
     * @param firstprofessional 第一专业
     */
    public void setFirstprofessional(String firstprofessional) {
        this.firstprofessional = firstprofessional;
    }

    /**
     * 获取第一学历
     *
     * @return firsteducation - 第一学历
     */
    public String getFirsteducation() {
        return firsteducation;
    }

    /**
     * 设置第一学历
     *
     * @param firsteducation 第一学历
     */
    public void setFirsteducation(String firsteducation) {
        this.firsteducation = firsteducation;
    }

    /**
     * 获取第一院校
     *
     * @return firstcollage - 第一院校
     */
    public String getFirstcollage() {
        return firstcollage;
    }

    /**
     * 设置第一院校
     *
     * @param firstcollage 第一院校
     */
    public void setFirstcollage(String firstcollage) {
        this.firstcollage = firstcollage;
    }

    /**
     * 获取最终学历
     *
     * @return education - 最终学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置最终学历
     *
     * @param education 最终学历
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取最终学位
     *
     * @return degree - 最终学位
     */
    public String getDegree() {
        return degree;
    }

    /**
     * 设置最终学位
     *
     * @param degree 最终学位
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     * 获取最终专业
     *
     * @return professional - 最终专业
     */
    public String getProfessional() {
        return professional;
    }

    /**
     * 设置最终专业
     *
     * @param professional 最终专业
     */
    public void setProfessional(String professional) {
        this.professional = professional;
    }

    /**
     * 获取毕业院校
     *
     * @return collage - 毕业院校
     */
    public String getCollage() {
        return collage;
    }

    /**
     * 设置毕业院校
     *
     * @param collage 毕业院校
     */
    public void setCollage(String collage) {
        this.collage = collage;
    }

    /**
     * 获取v_maritalstatus 00-未婚 01-已婚 
     *
     * @return maritalstatus - v_maritalstatus 00-未婚 01-已婚 
     */
    public String getMaritalstatus() {
        return maritalstatus;
    }

    /**
     * 设置v_maritalstatus 00-未婚 01-已婚 
     *
     * @param maritalstatus v_maritalstatus 00-未婚 01-已婚 
     */
    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    /**
     * 获取身份证号
     *
     * @return idcode - 身份证号
     */
    public String getIdcode() {
        return idcode;
    }

    /**
     * 设置身份证号
     *
     * @param idcode 身份证号
     */
    public void setIdcode(String idcode) {
        this.idcode = idcode;
    }

    /**
     * 获取社会保障号
     *
     * @return insurcode - 社会保障号
     */
    public String getInsurcode() {
        return insurcode;
    }

    /**
     * 设置社会保障号
     *
     * @param insurcode 社会保障号
     */
    public void setInsurcode(String insurcode) {
        this.insurcode = insurcode;
    }

    /**
     * 获取家庭住址
     *
     * @return homeaddress - 家庭住址
     */
    public String getHomeaddress() {
        return homeaddress;
    }

    /**
     * 设置家庭住址
     *
     * @param homeaddress 家庭住址
     */
    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    /**
     * 获取邮政编码
     *
     * @return postalcode - 邮政编码
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * 设置邮政编码
     *
     * @param postalcode 邮政编码
     */
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    /**
     * 获取办公电话
     *
     * @return officetel - 办公电话
     */
    public String getOfficetel() {
        return officetel;
    }

    /**
     * 设置办公电话
     *
     * @param officetel 办公电话
     */
    public void setOfficetel(String officetel) {
        this.officetel = officetel;
    }

    /**
     * 获取手机
     *
     * @return mobile - 手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机
     *
     * @param mobile 手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取电子邮件
     *
     * @return email - 电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮件
     *
     * @param email 电子邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取v_politicalface 00-群众 01-党员
     *
     * @return politicalface - v_politicalface 00-群众 01-党员
     */
    public String getPoliticalface() {
        return politicalface;
    }

    /**
     * 设置v_politicalface 00-群众 01-党员
     *
     * @param politicalface v_politicalface 00-群众 01-党员
     */
    public void setPoliticalface(String politicalface) {
        this.politicalface = politicalface;
    }

    /**
     * 获取加入组织时间
     *
     * @return joindate - 加入组织时间
     */
    public Date getJoindate() {
        return joindate;
    }

    /**
     * 设置加入组织时间
     *
     * @param joindate 加入组织时间
     */
    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    /**
     * 获取相片
     *
     * @return photo - 相片
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置相片
     *
     * @param photo 相片
     */
    public void setPhoto(String photo) {
        this.photo = photo;
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
     * 获取附件个数，可以上传多个,默认为0
     *
     * @return attachsnum - 附件个数，可以上传多个,默认为0
     */
    public Short getAttachsnum() {
        return attachsnum;
    }

    /**
     * 设置附件个数，可以上传多个,默认为0
     *
     * @param attachsnum 附件个数，可以上传多个,默认为0
     */
    public void setAttachsnum(Short attachsnum) {
        this.attachsnum = attachsnum;
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
     * 获取工作简介
     *
     * @return workremark - 工作简介
     */
    public String getWorkremark() {
        return workremark;
    }

    /**
     * 设置工作简介
     *
     * @param workremark 工作简介
     */
    public void setWorkremark(String workremark) {
        this.workremark = workremark;
    }
}