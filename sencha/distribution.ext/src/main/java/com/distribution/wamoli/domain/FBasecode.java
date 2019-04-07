package com.distribution.wamoli.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.distribution.wamoli.common.bean.BasePojo;
@Entity
@Table(name = "f_basecode")
public class FBasecode extends BasePojo<FBasecode>{
    @Id
    private String codetype;

    @Id
    private String codeid;

    private String codename;

    private String codename1;

    /**
     * 可以设置自定码
     */
    private String pinyincode;

    private String parentcodeid;

    private String color;

    /**
     * 0- 否 1-是
     */
    private String isvalid;

    private String custom1;

    private String custom2;

    private String custom3;

    private String custom4;

    private String custom5;

    private BigDecimal custom6;

    private BigDecimal custom7;
    
    
    

    public FBasecode() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * @return codetype
     */
    public String getCodetype() {
        return codetype;
    }

    /**
     * @param codetype
     */
    public void setCodetype(String codetype) {
        this.codetype = codetype;
    }

    /**
     * @return codeid
     */
    public String getCodeid() {
        return codeid;
    }

    /**
     * @param codeid
     */
    public void setCodeid(String codeid) {
        this.codeid = codeid;
    }

    /**
     * @return codename
     */
    public String getCodename() {
        return codename;
    }

    /**
     * @param codename
     */
    public void setCodename(String codename) {
        this.codename = codename;
    }

    /**
     * @return codename1
     */
    public String getCodename1() {
        return codename1;
    }

    /**
     * @param codename1
     */
    public void setCodename1(String codename1) {
        this.codename1 = codename1;
    }

    /**
     * 获取可以设置自定码
     *
     * @return pinyincode - 可以设置自定码
     */
    public String getPinyincode() {
        return pinyincode;
    }

    /**
     * 设置可以设置自定码
     *
     * @param pinyincode 可以设置自定码
     */
    public void setPinyincode(String pinyincode) {
        this.pinyincode = pinyincode;
    }

    /**
     * @return parentcodeid
     */
    public String getParentcodeid() {
        return parentcodeid;
    }

    /**
     * @param parentcodeid
     */
    public void setParentcodeid(String parentcodeid) {
        this.parentcodeid = parentcodeid;
    }

    /**
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
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