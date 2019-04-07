package com.distribution.wamoli.common.jdbc.support.mssql;
public class SqlServer2005SqlFunction extends AbstractMssqlSqlFunction {
	
	
	
	private static SqlServer2005SqlFunction conver;
	
	
	private SqlServer2005SqlFunction() {
	}
	
	
	public static SqlServer2005SqlFunction getInstance() {
		if(conver==null) buildConver();
		return conver;
	}
	
	
	private synchronized static void buildConver() {
		if(conver != null) return ;
		conver = new SqlServer2005SqlFunction();
	}
	
	
}
