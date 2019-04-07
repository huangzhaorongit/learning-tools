package com.distribution.wamoli.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.bean.TreeBuilder;
import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.bean.TreeParams;
import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.common.utils.DateUtils;
import com.distribution.wamoli.common.utils.IDUtils;
import com.distribution.wamoli.service.SystemMenuService;
import com.distribution.wamoli.mapper.FSystemmenuMapper;
import com.distribution.wamoli.domain.FSystemmenu;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("systemMenuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemMenuServiceImpl extends BaseService<FSystemmenu> implements SystemMenuService {





	@Autowired
	private FSystemmenuMapper mapper;


	//@SystemLogs("查询系统菜单树形菜单")
	public List<Map<String,Object>> getMenuTree(TreeParams params){
		String sql = "select a.menuid as id,a.menuname,a.menutype,a.icon,a.iconCls,a.iconColor,a.isdisplay,a.parentId,b.modulename,a.custom1 as notdel,"
				   +" (case (select count(1) from f_systemmenu b where b.parentid = a.menuid) when 0 then 1 else 0 end ) as loaded,"
//				   +" (case a.menutype when '00' then 'classicon.gif' else 'sameheight.gif' end ) as icon,"
				   + "(case a.menutype when '00' then 0 else 1 end ) as leaf"
				   +" from f_systemmenu a "
				   +" left join f_systemmodule b on a.moduleid = b.moduleid"
				   +" where a.parentid = '"+params.getNode()+"'"
				   +" order by a.orderno";
		return TreeBuilder.updateTreeIcon(sqlMapper.selectList(sql));
	}

	//@SystemLogs("查询系统菜单信息")
	public Map<String,Object> getMenuInfo(String menuid){
		String sql = "select a.menuid,a.menuname,a.menutype,a.parentid,a.icon,a.iconcls,a.iconcolor,a.isdisplay,a.moduleid,b.modulename"
				   +" from f_systemmenu a "
				   +" left join f_systemmodule b on a.moduleid = b.moduleid"
				   +" where a.menuid = '"+menuid+"'";
		return sqlMapper.selectOne(sql);
	}

	//@SystemLogs("查询系统模块树形菜单信息")
	public List<TreeNode> getAllModule(){
		String sql = "select a.moduleid as id,a.modulename text,a.parentId,"
				   + "(case a.moduletype when '00' then 1 else 0 end ) as disabled"
				   +" from f_systemmodule a "
				   +" where a.isdisplay = '1' "
				   +" order by a.modulecode ";
		return TreeBuilder.buildListToTree(sqlMapper.selectList(sql,TreeNode.class));
	}

	//@SystemLogs("添加或修改系统菜单信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public FSystemmenu saveOrUpdate(FSystemmenu bean){
		boolean add = StringUtils.isEmpty(bean.getMenuid());
		if(add){
			bean.setMenuid(IDUtils.getUuid());
			bean.setCompanyid(Local.getCompanyid());
			bean.setCreater(Local.getUsername());
			bean.setCreatedate(DateUtils.getCurrentTime());
			bean.setLastmodifier(Local.getUsername());
			bean.setLastmodifydate(DateUtils.getCurrentTime());
			bean.setOrderno((int)selectMax("f_systemmenu","Orderno","parentid = '"+bean.getParentid()+"'")+1);
			mapper.insert(bean);
		}else{
			bean.setLastmodifier(Local.getUsername());
			bean.setLastmodifydate(DateUtils.getCurrentTime());
			mapper.updateByPrimaryKeySelective(bean);
		}
		return bean;
	}

//	@SystemLogs("删除系统菜单信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public String deleteMenu(String menuid){
		List<Map<String,Object>> list = sqlMapper.selectList("select menuid from f_systemmenu where parentid  = '"+menuid+"' ");
		int count = list.size();
		if(count>0)return "-1";
		count = sqlMapper.delete("delete from f_systemmenu where menuid = '"+menuid+"'");  //菜单
		count += sqlMapper.delete("delete from f_systemuser_limits where menuid = '"+menuid+"'");//人员权限
		count += sqlMapper.delete("delete from f_systemrole_limits where menuid = '"+menuid+"'");//角色权限
		count += sqlMapper.delete("delete from f_systemuser_datalimits where menuid = '"+menuid+"'"); //人员数据权限
		count += sqlMapper.delete("delete from f_systemcfg where value = '"+menuid+"'");//包含菜单的配置
		return String.valueOf(count);
	}

	//@SystemLogs("修改系统菜单节点的排列顺序")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public void saveMoveOrder(List<FSystemmenu> list){
		 for (int i = 0; i < list.size(); i++) {
			FSystemmenu bean = list.get(i);
			bean.setOrderno(i);
			mapper.updateByPrimaryKeySelective(bean);
		 }
	}

}
