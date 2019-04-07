package com.distribution.wamoli.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.distribution.wamoli.common.bean.BasePojo;
@Entity
@Table(name = "f_moduleoperate")
public class FModuleoperate extends BasePojo<FModuleoperate>{
    @Id
    private String moduleid;

    /**
     * 对应操作按钮上的id,规范命名:add,delete,update,query,print,excel,download,upload，对于特殊的操作，按此标准定义。
     */
    @Id
    private String operatecode;

    /**
     * 操作按钮上显示的名称
     */
    private String operatename;

    private String controltype;

    private Integer orderno;

    private String custom1;

    private String custom2;

    private String custom3;
    
    

    public FModuleoperate() {
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
     * 获取对应操作按钮上的id,规范命名:add,delete,update,query,print,excel,download,upload，对于特殊的操作，按此标准定义。
     *
     * @return operatecode - 对应操作按钮上的id,规范命名:add,delete,update,query,print,excel,download,upload，对于特殊的操作，按此标准定义。
     */
    public String getOperatecode() {
        return operatecode;
    }

    /**
     * 设置对应操作按钮上的id,规范命名:add,delete,update,query,print,excel,download,upload，对于特殊的操作，按此标准定义。
     *
     * @param operatecode 对应操作按钮上的id,规范命名:add,delete,update,query,print,excel,download,upload，对于特殊的操作，按此标准定义。
     */
    public void setOperatecode(String operatecode) {
        this.operatecode = operatecode;
    }

    /**
     * 获取操作按钮上显示的名称
     *
     * @return operatename - 操作按钮上显示的名称
     */
    public String getOperatename() {
        return operatename;
    }

    /**
     * 设置操作按钮上显示的名称
     *
     * @param operatename 操作按钮上显示的名称
     */
    public void setOperatename(String operatename) {
        this.operatename = operatename;
    }

    /**
     * @return controltype
     */
    public String getControltype() {
        return controltype;
    }

    /**
     * @param controltype
     */
    public void setControltype(String controltype) {
        this.controltype = controltype;
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