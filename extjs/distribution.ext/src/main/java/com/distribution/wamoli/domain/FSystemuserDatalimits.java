package com.distribution.wamoli.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.distribution.wamoli.common.bean.BasePojo;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "f_systemuser_datalimits")
public class FSystemuserDatalimits extends BasePojo<FSystemuserDatalimits>  implements GrantedAuthority {
    /**
     * 自动生成
     */
    @Id
    private String userid;

    @Id
    private String menuid;

    /**
     * 拥有此数据状态的代码。
     */
    @Id
    private String statecode;

    private String companyid;

    private String custom1;

    private String custom2;

    private String custom3;




    public FSystemuserDatalimits() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * 获取自动生成
     *
     * @return userid - 自动生成
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置自动生成
     *
     * @param userid 自动生成
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return menuid
     */
    public String getMenuid() {
        return menuid;
    }

    /**
     * @param menuid
     */
    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    /**
     * 获取拥有此数据状态的代码。
     *
     * @return statecode - 拥有此数据状态的代码。
     */
    public String getStatecode() {
        return statecode;
    }

    /**
     * 设置拥有此数据状态的代码。
     *
     * @param statecode 拥有此数据状态的代码。
     */
    public void setStatecode(String statecode) {
        this.statecode = statecode;
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
        return menuid + "-" + statecode;
    }
}
