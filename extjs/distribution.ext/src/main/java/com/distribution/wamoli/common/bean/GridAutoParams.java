package com.distribution.wamoli.common.bean;

import java.util.List;


public class GridAutoParams {
	
	//连接符
	private String connector;
	
	//字段类型
	private String fieldtype;
	
	//字段名称
	private String property;
	
	//字段值
	private String value;
	
	//条件
	private String operator;
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	//排序desc asc
	private String fieldorder;
	
	//联合查询
	private List<GridAutoParams> children;

	public String getFieldorder() {
		return fieldorder;
	}

	public void setFieldorder(String fieldorder) {
		this.fieldorder = fieldorder;
	}

	public List<GridAutoParams> getChildren() {
		return children;
	}

	public void setChildren(List<GridAutoParams> children) {
		this.children = children;
	}

	public String getConnector() {
		return connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	public String getFieldtype() {
		return fieldtype;
	}

	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}
}
