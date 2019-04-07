package com.distribution.wamoli.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.bean.ResultBean;
import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.bean.UserBean;
import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.common.utils.SessionUtils;
import com.distribution.wamoli.web.controller.WebSokcetServer;
import com.distribution.wamoli.mapper.FPersonnelMapper;
import com.distribution.wamoli.mapper.FSystemuserMapper;
import com.distribution.wamoli.domain.FBasecodetype;
import com.distribution.wamoli.domain.FPersonnel;
import com.distribution.wamoli.domain.FSystemmessage;
import com.distribution.wamoli.domain.FSystemuser;
import com.distribution.wamoli.service.SystemFrameService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("systemFrameService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemFrameServiceImpl extends BaseService<FBasecodetype> implements SystemFrameService{
    @Autowired
    private PasswordEncoder encoder;

	@Autowired
	private FSystemuserMapper umapper;

	@Autowired
	private FPersonnelMapper fmapper;

//	@SystemLogs("获取系统左侧树形菜单")
	public List<TreeNode> getMenuTree() {
		String userid = Local.getUserid();
		String sql = "select m.menuid as id,m.menuname text,m.parentid,n.moduleurl as url,m.icon,m.iconCls,"
				+ "  m.iconColor,n.moduletype as type,g.id as param1,g.orderno as param4,h.id as param2,h.orderno as param5,m.custom4 as hasChart,m.custom5  as hasGrid,"
				+ "  (case (select count(1) from f_systemmenu d "
				+ "		where d.parentid = m.menuid and d.menuid in (  "
				+ "				select a.menuid from f_systemrole_limits a  "
				+ "						inner join f_systemuser_roles b on a.roleid = b.roleid and b.userid = '" + userid + "'  "
				+ "				union  "
				+ "				select c.menuid from f_systemuser_limits c where c.userid = '" + userid + "'  "
				+ "		) "
				+ "	) when 0 then 1 else 0 end ) as leaf "
				+ " from f_systemmenu m  "
				+ " left join f_systemmodule n on m.moduleid = n.moduleid "
				+ " left join f_systemcfg g on m.menuid = g.value and g.userid = '"+userid+"' and g.type = '01' "
				+ " left join f_systemcfg h on m.menuid = h.value and h.userid = '"+userid+"' and h.type = '02' "
				+ " where m.menuid in ( "
				+ " 		select a.menuid from f_systemrole_limits a "
				+ " 				inner join f_systemuser_roles b on a.roleid = b.roleid and b.userid = '" + userid + "' "
				+ " 		union  "
				+ " 		select c.menuid from f_systemuser_limits c where c.userid = '" + userid + "' "
				+ " ) "
				+ " and m.isdisplay = '1' "
				+ " order by m.orderno";
		List<TreeNode> dataList = sqlMapper.selectList(sql, TreeNode.class);
		return dataList;
	}

	//@SystemLogs("修改用户及人员信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public ResultBean updateUser(FSystemuser user, FPersonnel personnel, String opassword, String npassword, String qpassword) {
		ResultBean bean = new ResultBean();
		String sql = "select * from f_systemuser where userid='" + user.getUserid() + "'";
		Map<String, Object> userinfo = getInfo(user.getUserid(),personnel.getPersonnelid());
		FSystemuser ouser = sqlMapper.selectOne(sql, FSystemuser.class);
		if (!StringUtils.isEmpty(opassword) && !StringUtils.isEmpty(npassword) && !StringUtils.isEmpty(qpassword)) {
			if (!encoder.matches(opassword, ouser.getPassword())) {
				bean.setSuccess(false);
				bean.setMessage("原密码验证失败,请重试！");
				return bean;
			}else{
				user.setPassword(encoder.encode(npassword));
			}
		}
		bean.setSuccess(true);
		bean.setMessage("修改成功");
		bean.setData(userinfo);
		umapper.updateByPrimaryKeySelective(user);
		fmapper.updateByPrimaryKeySelective(personnel);
		return bean;
	}

	//@SystemLogs("获取登录用户及人员信息")
	public Map<String, Object> getInfo(String userid, String personnelid) {
		String sql = "select u.*,f.* from f_systemuser u,f_personnel f where u.userid='"
				+ userid + "' and f.personnelid='" + personnelid + "'";
		Map<String, Object> umap = sqlMapper.selectOne(sql);
		return umap;
	}

	//@SystemLogs("获取当前用户指定模块的权限")
	public List<Map<String,Object>> getSystemLimits(String userid,String modulurl) {
		String sql = "select d.moduleurl, a.operatecode,a.operatename,a.custom1 as url,"
				+" case when (  "
				+" 		(select count(b.menuid) from f_systemuser_limits b where b.menuid = e.menuid and  b.operatecode = a.operatecode and b.userid='"+userid+"')+  "
				+" 		((select count(c.menuid) from f_systemrole_limits c where c.menuid =  e.menuid and c.operatecode = a.operatecode and c.roleid in (select d.roleid from f_systemuser_roles d where d.userid='"+userid+"')))  "
				+" )>0 then 1 else 0 end as islimit "
				+" from f_moduleoperate a  "
				+" inner join f_systemmodule d on a.moduleid = d.moduleid "
				+" inner join f_systemmenu e on e.moduleid = a.moduleid "
				+" where e.menuid in ( "
				+"	  select f.menuid from f_systemuser_limits f where f.operatecode = '00' and f.userid='"+userid+"' "
				+"    union all "
				+"	  select g.menuid from f_systemrole_limits g where g.operatecode = '00' and g.roleid in (select h.roleid from f_systemuser_roles h where h.userid='"+userid+"') "
				+" )";
		if(!StringUtils.isEmpty(modulurl)){
			sql+=" and d.moduleurl = '"+modulurl+"' ";
		}
		sql+=" order by a.moduleid,a.orderno";
		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("获取系统在线用户")
	public List<TreeNode> getOnlineUsers() {
		List<TreeNode> resuslt = new ArrayList<TreeNode>();
		Set<String> setuser = SessionUtils.sokcetMap.keySet();
		String userids = StringUtils.join(setuser.toArray(),",");
		Map<String,Integer> numMap = getUserChatRecordNumber(userids);
		for(String key : SessionUtils.sokcetMap.keySet()){
			WebSokcetServer ws = SessionUtils.sokcetMap.get(key);
			UserBean bean = ws.getUserbean();
			TreeNode node = new TreeNode(bean.getUserid(),"00",bean.getUsername());
			node.setLeaf(true);
			node.setLinkIcon(false);
			node.setIconCls("tree-panel-icon");
			node.setAttributes(bean);
			node.setIcon(bean.getPhotoid());
			Integer param4 = numMap.get(node.getId());
			node.setParam4(param4==null?0:param4);
			resuslt.add(node);
		}
		return resuslt;
	}

	private Map<String,Integer> getUserChatRecordNumber(String userids){
		String sql = "select * from f_systemmessage where fid in ('"+userids.replace(",","','")+"') and jid = '"+Local.getUserid()+"' and isread='0' ";
		List<FSystemmessage> list = sqlMapper.selectList(sql, FSystemmessage.class);
		Map<String,Integer> map = new HashMap<String,Integer>();
		for (int i = 0; i < list.size(); i++) {
			FSystemmessage bean = list.get(i);
			Integer num = map.get(bean.getFid());
			if(num==null){
				map.put(bean.getFid(),1);
			}else{
				map.put(bean.getFid(),num+1);
			}
		}
		return map;
	}

//	@SystemLogs("获取系统离线用户")
	public List<TreeNode> getOfflineUsers() {

		Set<String> setuser = SessionUtils.sokcetMap.keySet();
		String userids = StringUtils.join(setuser.toArray(),",");
		String sql = "SELECT a.userid as id,a.username as text,c.orgname as param1,'00' as parentId,"
				+" 1 as leaf,0 as linkIcon,'tree-panel-icon'as iconCls,"
				+ "b.photo as icon"
				+" FROM f_systemuser a "
				+" left join f_personnel b on a.personnelid = b.personnelid"
				+" left join f_organization c on b.departmentid = c.orgid"
				+" where a.userid not in ('"+userids.replace(",","','")+"')"
				+" order by a.usercode";
		List<TreeNode> resuslt = sqlMapper.selectList(sql,TreeNode.class);
		String[] ids = new String[resuslt.size()];
		for (int i = 0; i < resuslt.size(); i++) {
			TreeNode node = resuslt.get(i);
			ids[i] = node.getId();
		}
		userids = StringUtils.join(ids,",");
		Map<String,Integer> numMap = getUserChatRecordNumber(userids);
		for (int i = 0; i < resuslt.size(); i++) {
			TreeNode node = resuslt.get(i);
			Integer param4 = numMap.get(node.getId());
			node.setParam4(param4==null?0:param4);
			UserBean bean  = new UserBean();
			bean.setUserid(node.getId());
			bean.setUsername(node.getText());
			bean.setDepartmentname(node.getParam1());
			node.setAttributes(bean);
		}
		return resuslt;
	}




}
