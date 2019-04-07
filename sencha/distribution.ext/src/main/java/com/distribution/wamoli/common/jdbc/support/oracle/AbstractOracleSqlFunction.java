package com.distribution.wamoli.common.jdbc.support.oracle;

import com.distribution.wamoli.common.jdbc.support.AbstractSqlFunction;
import com.distribution.wamoli.common.utils.DateFormat;

public abstract class AbstractOracleSqlFunction extends AbstractSqlFunction {
	
	
	public String ceil(String value) {
		return "ceil("+value+")";
	}
	
	
	public String trunc(String value) {
		return "trunc("+value+")";
	}
	
		
	public String log(String value) {
		return "ln("+value+")";
	}
		
	public String log10(String value) {
		return "log(10,"+value+")";
	}
	
	
	public String square(String value) {
		return "power("+value+",2)";
	}
	
	
	public String random() {
		return "sys.dbms_random.value(0,1)";
	}
	
	
	public String pi() {
		return "acos(-1)";
	}
	
	
	public String degrees(String value) {
		return "(180/acos(-1))*"+value;
	}
	public String radians(String value) {
		return "(acos(-1)/180)*"+value;
	}
	
	
	public String isNull(String field, String value) {
		return "nvl("+field+", "+value+")";
	}
	
	
	public String character(String value) {
		return "chr("+value+")";
	}
	
	
	public String link(String[] vs) {
		String str = "";
		for(int i=0; i<vs.length; i++) {
			if(i > 0) str += "||";
			str += vs[i];
		}
		return str;
	}
	
	
	public String charindex(String parent, String sub, String count) {
		return "instr("+parent+","+sub+","+count+")";
	}
	
	
	public String substring(String parent, String start, String length) {
		return "substr("+parent+","+start+","+length+")";
	}
	
	
	
	public String length(String value) {
		return "length("+value+")";
	}
	
	
	public String trim(String value) {
		return "trim("+value+")";
	}
	
	
	
	public String getDate() {
		return "sysdate";
	}
	
	
	public String toChar(String datestring, DateFormat format) {
		return "to_char("+datestring+",'"+format.getOracleFormat()+"')";
	}
	
	
	
	public String toDate(String str, DateFormat format) {
		return "to_date("+str+",'"+format.getOracleFormat()+"')";
	}
	
}





