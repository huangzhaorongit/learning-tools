package com.distribution.wamoli.common.bean;

public class FileUploadBean {
	private String tablename;  // 表名
	private String fieldname;  // 主键
	private String fieldvalue; // 主键值
	private String isindex;    //是否添加到索引
	private String nfield;	   //附件数量修改字段

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getFieldvalue() {
		return fieldvalue;
	}

	public void setFieldvalue(String fieldvalue) {
		this.fieldvalue = fieldvalue;
	}

	public String getIsindex() {
		return isindex;
	}

	public void setIsindex(String isindex) {
		this.isindex = isindex;
	}

	public String getNfield() {
		return nfield;
	}

	public void setNfield(String nfield) {
		this.nfield = nfield;
	}
}
