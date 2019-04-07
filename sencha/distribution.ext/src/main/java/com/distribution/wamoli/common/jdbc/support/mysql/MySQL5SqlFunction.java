package com.distribution.wamoli.common.jdbc.support.mysql;
public class MySQL5SqlFunction extends AbstractMysqlSqlFunction {
	
	
	private static MySQL5SqlFunction conver;
	
	
	private MySQL5SqlFunction() {
	}
	
	
	public static MySQL5SqlFunction getInstance() {
		if(conver==null) buildConver();
		return conver;
	}
	
	
	private synchronized static void buildConver() {
		if(conver != null) return ;
		conver = new MySQL5SqlFunction();
	}
	
}
