package com.distribution.wamoli.common.jdbc.support.oracle;

public class Oracle10GSqlFunction extends AbstractOracleSqlFunction {
	
	
	private static Oracle10GSqlFunction conver;
	
	
	private Oracle10GSqlFunction() {
	}
	
	
	public static Oracle10GSqlFunction getInstance() {
		if(conver==null) buildConver();
		return conver;
	}
	
	
	private synchronized static void buildConver() {
		if(conver != null) return ;
		conver = new Oracle10GSqlFunction();
	}
	
	
	
	
	
}
