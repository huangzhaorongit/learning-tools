package com.distribution.wamoli.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "f_systemmessage")
public class FSystemmessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String type;

    /**
     * 发送人ID
     */
    private String fid;

    private String fname;

    /**
     * 接收人ID
     */
    private String jid;

    private String jname;

    /**
     * 内容
     */
    private String result;

    /**
     * 发送时间
     */
    private Date time;

    /**
     * 是否已读
     */
    private String isread;

    private String custom1;

    private String custom2;

    private String custom3;
    
    
    

    public FSystemmessage() {
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
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取发送人ID
     *
     * @return fid - 发送人ID
     */
    public String getFid() {
        return fid;
    }

    /**
     * 设置发送人ID
     *
     * @param fid 发送人ID
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * @return fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * 获取接收人ID
     *
     * @return jid - 接收人ID
     */
    public String getJid() {
        return jid;
    }

    /**
     * 设置接收人ID
     *
     * @param jid 接收人ID
     */
    public void setJid(String jid) {
        this.jid = jid;
    }

    /**
     * @return jname
     */
    public String getJname() {
        return jname;
    }

    /**
     * @param jname
     */
    public void setJname(String jname) {
        this.jname = jname;
    }

    /**
     * 获取内容
     *
     * @return result - 内容
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置内容
     *
     * @param result 内容
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取发送时间
     *
     * @return time - 发送时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置发送时间
     *
     * @param time 发送时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取是否已读
     *
     * @return isread - 是否已读
     */
    public String getIsread() {
        return isread;
    }

    /**
     * 设置是否已读
     *
     * @param isread 是否已读
     */
    public void setIsread(String isread) {
        this.isread = isread;
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