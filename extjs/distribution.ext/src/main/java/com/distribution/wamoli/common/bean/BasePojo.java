package com.distribution.wamoli.common.bean;

import java.io.Serializable;

import javax.persistence.Transient;

public abstract class BasePojo<T> implements Serializable {
	
	
	@Transient
	private String dbName;
	
//	
//	public String getDbName(){
//		return Globals.DB_NAME;
//	}
//	
	


	 @Transient
	 private String startDate;
	 
	 @Transient
	 private String endDate;
	
	 @Transient
	 private int rowNum;

	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}




	public int getRowNum() {
		return rowNum;
	}


	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	 
	 
	 
	 

}
