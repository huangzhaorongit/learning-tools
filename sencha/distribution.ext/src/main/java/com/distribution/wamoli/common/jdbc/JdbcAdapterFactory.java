package com.distribution.wamoli.common.jdbc;
import com.github.pagehelper.Dialect;
import com.distribution.wamoli.common.jdbc.support.AbstractSqlFunction;
import com.distribution.wamoli.common.jdbc.support.mssql.SqlServer2005SqlFunction;
import com.distribution.wamoli.common.jdbc.support.mysql.MySQL5SqlFunction;
import com.distribution.wamoli.common.jdbc.support.oracle.Oracle10GSqlFunction;


public class JdbcAdapterFactory {
	
	private static Oracle10GSqlFunction Instance_Oracle10G;
	private static SqlServer2005SqlFunction Instance_SqlServer2005;
	private static MySQL5SqlFunction Instance_MySQL5;

	
	
	public static AbstractSqlFunction getJdbcAdapter(Dialect dialect) {
		 switch (dialect) {
		 	case sqlserver :{
		 		if(Instance_MySQL5==null) buildMySQL5JdbcAdapter(JdbcType.TYPE_MSSQL_2005);
				return Instance_MySQL5;
		 	}
		 	case oracle :{
		 		if(Instance_MySQL5==null) buildMySQL5JdbcAdapter(JdbcType.TYPE_ORACLE_10G);
				return Instance_MySQL5;
		 	}
		 	case mysql :{
		 		if(Instance_MySQL5==null) buildMySQL5JdbcAdapter(JdbcType.TYPE_MYSQL_5);
				return Instance_MySQL5;
		 	}
			default : return null;
		 }
	}
	
	public static AbstractSqlFunction getJdbcAdapter(String jdbcType) {
		if(jdbcType.equals("mysql")){
			if(Instance_MySQL5==null) buildMySQL5JdbcAdapter(JdbcType.TYPE_MYSQL_5);
			return Instance_MySQL5;
		}else if(jdbcType.equals("oracle")){
			if(Instance_Oracle10G==null) buildOracle10GJdbcAdapter(JdbcType.TYPE_ORACLE_10G);
			return Instance_Oracle10G;
		}else if(jdbcType.equals("sqlserver")){
			if(Instance_SqlServer2005==null) buildSqlServer2005JdbcAdapter(JdbcType.TYPE_MSSQL_2005);
			return Instance_SqlServer2005;
		}else{
			if(Instance_MySQL5==null) buildMySQL5JdbcAdapter(JdbcType.TYPE_MYSQL_5);
			return Instance_MySQL5;
		}
	}
	
	/**
	 * 按指定数据库类型获取Jdbc适配器
	 * @param type
	 * @return
	 */
	public static AbstractSqlFunction getJdbcAdapter(JdbcType jdbcType) {
		switch(jdbcType.value()) {
			case JdbcType.VALUE_ORACLE_10G: {
				if(Instance_Oracle10G==null) buildOracle10GJdbcAdapter(jdbcType);
				return Instance_Oracle10G;
			}
			case JdbcType.VALUE_MSSQL_2005: {
				if(Instance_SqlServer2005==null) buildSqlServer2005JdbcAdapter(jdbcType);
				return Instance_SqlServer2005;
			}
			case JdbcType.VALUE_MYSQL_5: {
				if(Instance_MySQL5==null) buildMySQL5JdbcAdapter(jdbcType);
				return Instance_MySQL5;
			}
			default : return null;
		}
	}
	
	private synchronized static void buildOracle10GJdbcAdapter(JdbcType type) {
		if(Instance_Oracle10G != null) return ;
		Instance_Oracle10G = Oracle10GSqlFunction.getInstance();
	}
	
	
	private synchronized static void buildSqlServer2005JdbcAdapter(JdbcType type) {
		if(Instance_SqlServer2005 != null) return ;
		Instance_SqlServer2005 = SqlServer2005SqlFunction.getInstance();
	}
	
	private synchronized static void buildMySQL5JdbcAdapter(JdbcType type) {
		if(Instance_MySQL5 != null) return ;
		Instance_MySQL5 = MySQL5SqlFunction.getInstance();
	}
	
}
