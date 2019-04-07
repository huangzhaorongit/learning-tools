package com.distribution.wamoli.domain;

import javax.persistence.*;

import com.distribution.wamoli.common.bean.BasePojo;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "f_systemuser_roles")
public class FSystemuserRoles extends BasePojo<FSystemuserRoles> implements GrantedAuthority  {
    @Id
    private String userid;

    /**
     * 自动生成
     */
    @Id
    private String roleid;

    private String companyid;

    private String custom1;

    private String custom2;

    private String custom3;




    public FSystemuserRoles() {
		super();
		// TODO Auto-generated constructor stub
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
     * 获取自动生成
     *
     * @return roleid - 自动生成
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * 设置自动生成
     *
     * @param roleid 自动生成
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid;
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

    @Override
    public String getAuthority() {
        return roleid;
    }
}
