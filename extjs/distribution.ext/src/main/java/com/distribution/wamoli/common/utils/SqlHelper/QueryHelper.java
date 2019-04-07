package com.distribution.wamoli.common.utils.SqlHelper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.distribution.wamoli.common.bean.GridAutoParams;
import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.utils.CommonUtils;
import com.distribution.wamoli.common.utils.JsonUtil;




@SuppressWarnings("unchecked")
public class QueryHelper{
	
	public static String getQuerySql(String sql) {
		HttpServletRequest request = Local.getRequest();
		if(request == null)return sql;
		StringBuffer sb = new StringBuffer("select * from ("+sql+") ux where 1=1 ");
		
		//filter条件
		String filterParams = request.getParameter("filterParams");
		if("true".equals(filterParams)){
			String liststr = request.getParameter("sys_grid_filterparams");
			if(!CommonUtils.isEmpty(liststr)){
				List<GridAutoParams> list = (List<GridAutoParams>) JsonUtil.fromJson(liststr,GridAutoParams.class);
				setFilterParamsSQL(sb, list);
			}
		}
		
		//高级查询条件
		String autoParams = request.getParameter("autoParams");
		if("true".equals(autoParams)){
			String liststr = request.getParameter("sys_grid_autoparams");
			if(!CommonUtils.isEmpty(liststr)){
				List<GridAutoParams> list = (List<GridAutoParams>) JsonUtil.fromJson(liststr,GridAutoParams.class);
				setParamsSQL(sb, list);
			}
		}
        return sb.toString();
	}
	
    public static String getQuerySql(String sql,List<GridAutoParams> list) {
    	StringBuffer sb = new StringBuffer("select * from ("+sql+") ux where 1=1 ");
		setParamsSQL(sb, list);
        return sb.toString();
    }
    
    
    private static void setParamsSQL(StringBuffer sb,List<GridAutoParams> list){
		if(list==null)return;
		for (int i = 0; i < list.size(); i++) {
			GridAutoParams bean = list.get(i);
			String fieldcode = CommonUtils.camelhumpToUnderline(bean.getProperty());
			if(bean.getChildren()!=null && bean.getChildren().size()>0){
				sb.append(getConnectorSQL(bean.getConnector())).append(" (");
				sb.append(" ux.").append(fieldcode).append(getOperateSQL(bean));
				setParamsSQL(sb,bean.getChildren());
				sb.append(" )");
			}else{
				sb.append(getConnectorSQL(bean.getConnector()));
				sb.append(" ux.").append(fieldcode).append(getOperateSQL(bean));
			}
		}
	}
	
	private static String getConnectorSQL(String connector){
		return connector.equals("01")?" and ":" or ";
	}
	
	private static String getOperateSQL(GridAutoParams bean){
		String sql = "";
		String operate = bean.getOperator();
		String fieldtype = bean.getFieldtype();
		String fieldvalue = bean.getValue();
		if(fieldtype.equals("combobox") ){
			sql = " = '"+fieldvalue+"'";
		}else{
			int ljf = Integer.valueOf(operate);
			switch(ljf){
				case 1: {sql = " = ";break;}
				case 2: {sql = " <> ";break;}
				case 3: {sql = " like ";break;}
				case 4: {sql = " in ";break;}
				case 5: {sql = " < ";break;}
				case 6: {sql = " > ";break;}
				case 7: {sql = " <= ";break;}
				case 8: {sql = " >= ";break;}
				case 9: {sql = " is null ";break;}
				case 10: {sql = " is not null ";break;}
			}
			if(ljf==1 || ljf==2 || ljf==5 || ljf==6 || ljf==7 || ljf==8){
				if(fieldtype.equals("string")|| fieldtype.equals("datetime")){
					sql += " '"+fieldvalue+"'";
				}else if(fieldtype.equals("number")){
					sql += " "+fieldvalue+"";
				}
			}else if(ljf==3){
				if(fieldtype.equals("string")){
					sql += " '%"+fieldvalue+"%'";
				}
			}else if(ljf==4){
				if(fieldtype.equals("string")){
					sql += " ('"+fieldvalue.replace(",","','")+"') ";
				}
			}
		}
		return sql;
	}
	
	/*************************filter过滤查询*********************************/
	private static void setFilterParamsSQL(StringBuffer sb,List<GridAutoParams> list){
		if(list==null)return;
		for (int i = 0; i < list.size(); i++) {
			GridAutoParams bean = list.get(i);
			sb.append(" and ");
			String fieldcode = CommonUtils.camelhumpToUnderline(bean.getProperty());
			sb.append(" ux.").append(fieldcode).append(getFilterOperateSQL(bean));
		}
	}
	
	private static String getFilterOperateSQL(GridAutoParams bean){
		String operate = bean.getOperator();
		String sql = "";
		if(operate.equals("lt")){
			operate = "<";
		}else if(operate.equals("gt")){
			operate = ">";
		}else if(operate.equals("eq")){
			operate = "=";
		}
		if(operate.equals("like")){
			sql = " like ('%"+bean.getValue()+"%')";
		}else if(operate.equals("in")){
			sql = " in ('"+bean.getValue().replace(",","','")+"')";
		}else{
			sql = " "+ operate +" '"+bean.getValue()+"'";
		}
		return sql;
	}
}
