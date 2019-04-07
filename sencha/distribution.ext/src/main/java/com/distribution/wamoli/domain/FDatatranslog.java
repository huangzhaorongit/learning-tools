package com.distribution.wamoli.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.distribution.wamoli.common.bean.BasePojo;
@Entity
@Table(name = "f_datatranslog")
public class FDatatranslog extends BasePojo<FDatatranslog>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 如船岸emp接口：shipland
     */
    private String interfacetype;

    /**
     * 比如: organizationservice 类
     */
    private String interfacename;

    /**
     * 比如: updatelandorganization 方法
     */
    private String interfacemethod;

    /**
     * 同步内部组织
     */
    private String interfacedesc;

    /**
     * 调用时间
     */
    private Date transtime;

    /**
     * 0-否 1-是
     */
    private String issuccess;

    private String custom1;

    private String custom2;

    private String custom3;
    
    
    

    public FDatatranslog() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * 如果失败，记录失败信息。
     */
    private String remark;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取如船岸emp接口：shipland
     *
     * @return interfacetype - 如船岸emp接口：shipland
     */
    public String getInterfacetype() {
        return interfacetype;
    }

    /**
     * 设置如船岸emp接口：shipland
     *
     * @param interfacetype 如船岸emp接口：shipland
     */
    public void setInterfacetype(String interfacetype) {
        this.interfacetype = interfacetype;
    }

    /**
     * 获取比如: organizationservice 类
     *
     * @return interfacename - 比如: organizationservice 类
     */
    public String getInterfacename() {
        return interfacename;
    }

    /**
     * 设置比如: organizationservice 类
     *
     * @param interfacename 比如: organizationservice 类
     */
    public void setInterfacename(String interfacename) {
        this.interfacename = interfacename;
    }

    /**
     * 获取比如: updatelandorganization 方法
     *
     * @return interfacemethod - 比如: updatelandorganization 方法
     */
    public String getInterfacemethod() {
        return interfacemethod;
    }

    /**
     * 设置比如: updatelandorganization 方法
     *
     * @param interfacemethod 比如: updatelandorganization 方法
     */
    public void setInterfacemethod(String interfacemethod) {
        this.interfacemethod = interfacemethod;
    }

    /**
     * 获取同步内部组织
     *
     * @return interfacedesc - 同步内部组织
     */
    public String getInterfacedesc() {
        return interfacedesc;
    }

    /**
     * 设置同步内部组织
     *
     * @param interfacedesc 同步内部组织
     */
    public void setInterfacedesc(String interfacedesc) {
        this.interfacedesc = interfacedesc;
    }

    /**
     * 获取调用时间
     *
     * @return transtime - 调用时间
     */
    public Date getTranstime() {
        return transtime;
    }

    /**
     * 设置调用时间
     *
     * @param transtime 调用时间
     */
    public void setTranstime(Date transtime) {
        this.transtime = transtime;
    }

    /**
     * 获取0-否 1-是
     *
     * @return issuccess - 0-否 1-是
     */
    public String getIssuccess() {
        return issuccess;
    }

    /**
     * 设置0-否 1-是
     *
     * @param issuccess 0-否 1-是
     */
    public void setIssuccess(String issuccess) {
        this.issuccess = issuccess;
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
     * 获取如果失败，记录失败信息。
     *
     * @return remark - 如果失败，记录失败信息。
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置如果失败，记录失败信息。
     *
     * @param remark 如果失败，记录失败信息。
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}