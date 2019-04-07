package com.distribution.wamoli.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.distribution.wamoli.common.bean.BasePojo;
@Entity
@Table(name = "f_datatranscontrl")
public class FDatatranscontrl extends BasePojo<FDatatranscontrl> {
    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 上次成功传输的时间
     */
    private Date lasttranstime;

    private String custom1;

    private String custom2;

    private String custom3;
    
    

    public FDatatranscontrl() {
		super();
		// TODO Auto-generated constructor stub
	}

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
     * 获取上次成功传输的时间
     *
     * @return lasttranstime - 上次成功传输的时间
     */
    public Date getLasttranstime() {
        return lasttranstime;
    }

    /**
     * 设置上次成功传输的时间
     *
     * @param lasttranstime 上次成功传输的时间
     */
    public void setLasttranstime(Date lasttranstime) {
        this.lasttranstime = lasttranstime;
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
}