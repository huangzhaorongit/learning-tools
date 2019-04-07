package com.distribution.wamoli.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.distribution.wamoli.common.bean.BasePojo;
@Entity
@Table(name = "f_moduledatastate")
public class FModuledatastate extends BasePojo<FModuledatastate> {
    @Id
    private String moduleid;

    /**
     * 对应数据所在的状态编号
     */
    @Id
    private String statecode;

    /**
     * 数据状态的含义
     */
    private String statename;

    private Integer orderno;

    private String custom1;

    private String custom2;

    private String custom3;
    
    

    public FModuledatastate() {
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
     * 获取对应数据所在的状态编号
     *
     * @return statecode - 对应数据所在的状态编号
     */
    public String getStatecode() {
        return statecode;
    }

    /**
     * 设置对应数据所在的状态编号
     *
     * @param statecode 对应数据所在的状态编号
     */
    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    /**
     * 获取数据状态的含义
     *
     * @return statename - 数据状态的含义
     */
    public String getStatename() {
        return statename;
    }

    /**
     * 设置数据状态的含义
     *
     * @param statename 数据状态的含义
     */
    public void setStatename(String statename) {
        this.statename = statename;
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