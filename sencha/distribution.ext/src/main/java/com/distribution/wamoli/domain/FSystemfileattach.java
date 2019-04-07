package com.distribution.wamoli.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.distribution.wamoli.common.bean.BasePojo;

@Table(name = "f_systemfileattach")
public class FSystemfileattach extends BasePojo<FSystemfileattach> {
    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String companyid;

    private String fileclass;

    private String title;

    /**
     * v_doctype
     */
    private String filetype;

    private String originalname;

    /**
     * v_docsuffix
     */
    private String filesuffix;

    /**
     * kb
     */
    private Long filesize;

    private Date uploadtime;

    private Long viewnum;

    private Long downloadnum;

    private String sourcetablename;

    private String sourcefieldname;

    private String sourcerecordkey;

    private String custom1;

    private String custom2;

    private String custom3;

    private String custom4;

    private String custom5;

    private BigDecimal custom6;

    private BigDecimal custom7;

    private String creater;

    private Date createdate;

    private String lastmodifier;

    private Date lastmodifydate;

    private String keyword;

    private String remark;
    
    

    public FSystemfileattach() {
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
     * @return fileclass
     */
    public String getFileclass() {
        return fileclass;
    }

    /**
     * @param fileclass
     */
    public void setFileclass(String fileclass) {
        this.fileclass = fileclass;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取v_doctype
     *
     * @return filetype - v_doctype
     */
    public String getFiletype() {
        return filetype;
    }

    /**
     * 设置v_doctype
     *
     * @param filetype v_doctype
     */
    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    /**
     * @return originalname
     */
    public String getOriginalname() {
        return originalname;
    }

    /**
     * @param originalname
     */
    public void setOriginalname(String originalname) {
        this.originalname = originalname;
    }

    /**
     * 获取v_docsuffix
     *
     * @return filesuffix - v_docsuffix
     */
    public String getFilesuffix() {
        return filesuffix;
    }

    /**
     * 设置v_docsuffix
     *
     * @param filesuffix v_docsuffix
     */
    public void setFilesuffix(String filesuffix) {
        this.filesuffix = filesuffix;
    }

    /**
     * 获取kb
     *
     * @return filesize - kb
     */
    public Long getFilesize() {
        return filesize;
    }

    /**
     * 设置kb
     *
     * @param filesize kb
     */
    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    /**
     * @return uploadtime
     */
    public Date getUploadtime() {
        return uploadtime;
    }

    /**
     * @param uploadtime
     */
    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    /**
     * @return viewnum
     */
    public Long getViewnum() {
        return viewnum;
    }

    /**
     * @param viewnum
     */
    public void setViewnum(Long viewnum) {
        this.viewnum = viewnum;
    }

    /**
     * @return downloadnum
     */
    public Long getDownloadnum() {
        return downloadnum;
    }

    /**
     * @param downloadnum
     */
    public void setDownloadnum(Long downloadnum) {
        this.downloadnum = downloadnum;
    }

    /**
     * @return sourcetablename
     */
    public String getSourcetablename() {
        return sourcetablename;
    }

    /**
     * @param sourcetablename
     */
    public void setSourcetablename(String sourcetablename) {
        this.sourcetablename = sourcetablename;
    }

    /**
     * @return sourcefieldname
     */
    public String getSourcefieldname() {
        return sourcefieldname;
    }

    /**
     * @param sourcefieldname
     */
    public void setSourcefieldname(String sourcefieldname) {
        this.sourcefieldname = sourcefieldname;
    }

    /**
     * @return sourcerecordkey
     */
    public String getSourcerecordkey() {
        return sourcerecordkey;
    }

    /**
     * @param sourcerecordkey
     */
    public void setSourcerecordkey(String sourcerecordkey) {
        this.sourcerecordkey = sourcerecordkey;
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
     * @return lastmodifydate
     */
    public Date getLastmodifydate() {
        return lastmodifydate;
    }

    /**
     * @param lastmodifydate
     */
    public void setLastmodifydate(Date lastmodifydate) {
        this.lastmodifydate = lastmodifydate;
    }

    /**
     * @return keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}