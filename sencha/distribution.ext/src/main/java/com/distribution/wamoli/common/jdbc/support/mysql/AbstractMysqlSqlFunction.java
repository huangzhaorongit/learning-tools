package com.distribution.wamoli.common.jdbc.support.mysql;



import com.distribution.wamoli.common.jdbc.support.AbstractSqlFunction;
import com.distribution.wamoli.common.utils.DateFormat;

public abstract class AbstractMysqlSqlFunction extends AbstractSqlFunction {
	
	
	public String ceil(String value) {
		return "ceiling("+value+")";
	}
	
	public String trunc(String value) {
		return "truncate("+value+",0)";
	}
	
	
	public String log(String value) {
		return "log("+value+")";
	}
	
	
	public String log10(String value) {
		return "log10("+value+")";
	}
	
	
	public String square(String value) {
		return "power("+value+",2)";
	}
	
	
	public String random() {
		return "rand()";
	}
	
	
	public String pi() {
		return "pi()";
	}
	
	
	public String degrees(String value) {
		return "degrees("+value+")";
	}
	public String radians(String value) {
		return "radians("+value+")";
	}
	
	
	public String isNull(String field, String value) {
		return "ifnull("+field+", "+value+")";
	}
	
	public String character(String value) {
		return "char("+value+")";
	}
	
	
	public String link(String[] vs) {
		String str = "concat(";
		for(int i=0; i<vs.length; i++) {
			if(i > 0) str += ",";
			str += vs[i];
		}
		str += ")";
		return str;
	}
	
	
	public String charindex(String parent, String sub, String count) {
		return "instr("+parent+","+sub+")";
	}
	
	
	public String substring(String parent, String start, String length) {
		return "substring("+parent+","+start+","+length+")";
	}
	
	
	public String length(String value) {
		return "length("+value+")";
	}
	
	
	public String trim(String value) {
		return "trim("+value+")";
	}
	
	
	public String getDate() {
		return "now()";
	}
	
	
	public String toChar(String datestring, DateFormat format) {
		return "date_format("+datestring+",'"+format.getMySQLFormat()+"')";
	}
	
	
	public String toDate(String str, DateFormat format) {
		return "str_to_date("+str+",'"+format.getMySQLFormat()+"')";
	}
	
	
	
}


