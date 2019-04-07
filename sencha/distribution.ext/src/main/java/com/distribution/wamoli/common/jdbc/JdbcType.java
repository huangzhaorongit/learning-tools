package com.distribution.wamoli.common.jdbc;

import com.distribution.wamoli.common.exception.PreglacialJdbcTypeException;

public class JdbcType {
	
	
	public static final int VALUE_ORACLE_10G = 101;
	public static final int VALUE_MSSQL_2005 = 201;
	public static final int VALUE_MYSQL_5 = 301;
	
	
	public static final JdbcType TYPE_ORACLE_10G = new JdbcType(VALUE_ORACLE_10G);
	public static final JdbcType TYPE_MSSQL_2005 = new JdbcType(VALUE_MSSQL_2005);
	public static final JdbcType TYPE_MYSQL_5 = new JdbcType(VALUE_MYSQL_5);
	
	
	/**
	 * 101: Oracle10g
	 * 
	 * 201: SqlServer2005
	 * 
	 * 301: MySQL5
	 */
	private int type;
	
	
	
	private JdbcType(int type) {
		this.type = type;
	}
	
	
	public int value() {
		return type;
	} 
	
	public int getType() {
		return value();
	}
	
	
	
	public int getValue() {
		return value();
	}
	
	
	
	public String toString() {
		switch (type) {
			case VALUE_ORACLE_10G: return "Oracle10G";
			case VALUE_MSSQL_2005: return "SqlServer2005";
			case VALUE_MYSQL_5: return "MySQL5";
			default : return null;
		}
	}
	
	
	
	public static JdbcType valueOf(String name) {
		if("Oracle10G".equals(name)) {
			return TYPE_ORACLE_10G;
		}else if("SqlServer2005".equals(name)) {
			return TYPE_MSSQL_2005;
		}else if("MySQL5".equals(name)) {
			return TYPE_MYSQL_5;
		}else {
			throw new PreglacialJdbcTypeException(" is wrong JdbcTypeName:'"+name+"'!");
		}
	} 
	
	
	
	public static JdbcType valueOf(int typeValue) {
		switch(typeValue) {
			case VALUE_ORACLE_10G: return TYPE_ORACLE_10G;
			case VALUE_MSSQL_2005: return TYPE_MSSQL_2005;
			case VALUE_MYSQL_5: return TYPE_MYSQL_5;
			default : throw new PreglacialJdbcTypeException(" is wrong JdbcType:'"+typeValue+"'!");
		}
	}	
	
	
	
}



