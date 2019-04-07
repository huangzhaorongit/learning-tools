package com.distribution.wamoli.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.bean.ResultBean;
import com.distribution.wamoli.common.bean.SystemUrlBean;
import com.distribution.wamoli.common.bean.UserBean;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.common.utils.CommonUtils;
import com.distribution.wamoli.common.utils.ProjectUtils;
import com.distribution.wamoli.service.LoginService;
import com.distribution.wamoli.service.SystemFrameService;
import com.distribution.wamoli.mapper.FSystemuserMapper;
import com.distribution.wamoli.domain.FSystemuser;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("loginService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginServiceImpl extends BaseService<FSystemuser> implements LoginService {

	@Autowired
	private SystemFrameService systemFrameService;


	@Autowired
	private  FSystemuserMapper fSystemuserMapper;

    @Autowired
    private PasswordEncoder  encoder;

	@Resource
	private SystemFrameService sfservice;

	/**
	 *  case "0": login(themetype); break;
	 *	case "1": msg = "请输入正确的验证码!"; break;
	 *	case "2": msg = "您所输入的用户名不存在!"; break;
	 *	case "3": msg = "密码输入错误,请重新输入!"; break;
	 *	case "4": msg = "当前用户名已被锁定,无法登录!"; break;
	 *	case "5": msg = "当前用户名已被注销,无法登录!"; break;
	 *	case "6": msg = "当前用户所在公司已被注销,无法登录!"; break;
	 *	case "7": msg = "当前用户已经在线"; break;
	 *	default:  msg = "提交失败, 可能存在网络故障或其他未知原因!"; break;
	 */
//	@SystemLogs("用户登陆")
	public ResultBean login(String usercode,String password,Boolean invalidate){
		List<FSystemuser> list = sqlMapper.selectList("select * from f_systemuser a where a.usercode='"+usercode+"' ",FSystemuser.class);
		if(list.size()==0)return new ResultBean(false,"2");
		String userpassword = encoder.encode(password);
		boolean isOkey = encoder.matches(password, list.get(0).getPassword());
//		list = sqlMapper.selectList("select * from f_systemuser where usercode='"+usercode+"' and password='"+userpassword+"' ",FSystemuser.class);
		if(!isOkey)return new ResultBean(false,"3");
		FSystemuser userinfo = list.get(0);
		if(StringUtils.isEmpty(userinfo.getIslocked())||userinfo.getIslocked().equals("1"))return new ResultBean(false,"4");
		if(StringUtils.isEmpty(userinfo.getIsvalid())||userinfo.getIsvalid().equals("0"))return new ResultBean(false,"5");
//		String singleonline = ProjectUtils.getInitParameter("sys.singleOnline");
//		if(singleonline.equals("true")){
//			if(CommonUtils.is(invalidate)){
//				SessionUtils.invalidateOnlineUser(userinfo.getUserid());
//			}else if(SessionUtils.isOnlineUser(userinfo.getUserid())){
//				return new ResultBean(false,"7");
//			}
//		}
		return new ResultBean(true,"");
	}

	public UserBean getUserInfo(String usercode){
		String sql = "select a.userid,a.usercode,a.username, "
				+"		b.companyid,b.companycode,b.companyname,b.companylongname,b.levelid, "
				+"		c.personnelid,c.personnelcode,c.personnelname,c.position,c.photo as photoid,"
				+"		d.orgid departmentid,d.OrgCode departmentcode,d.orgname departmentname,d.orgtype departmenttype "
				+"	from f_systemuser a  "
				+"	inner join f_company b on a.companyid = b.companyid "
				+"	left join f_personnel c on a.personnelid = c.personnelid "
				+"	left join f_organization d on c.departmentid = d.orgid"
				+"  where a.usercode = '"+usercode+"' ";
		UserBean bean = sqlMapper.selectOne(sql, UserBean.class);
		bean.setSystemlimits(getSystemlimits(bean.getUserid()));
		bean.setUrllimits(getUrlLimits(bean.getSystemlimits()));
		bean.setSystemurls(getSystemUrls());
		return bean;
	}

	/**
	 * 获取系统URL对象
	 * @return
	 */
	private Map<String,SystemUrlBean> getSystemUrls(){
		return ProjectUtils.getSystemUrls(null);
	}

	/**
	 * 获取用户全部的页面权限
	 * @param userid
	 * @return
	 */
	private Map<String, List<Map<String, Object>>> getSystemlimits(String userid){
		Map<String,List<Map<String,Object>>> systemlimits =  new HashMap<String, List<Map<String,Object>>>();
		List<Map<String,Object>> list = sfservice.getSystemLimits(userid,null);
		for (Map<String,Object> map: list) {
			String key = String.valueOf(map.get("moduleurl"));
			List<Map<String,Object>> limitsMap = systemlimits.get(key);
			if(limitsMap == null){
				limitsMap = new ArrayList<Map<String,Object>>();
				systemlimits.put(key,limitsMap);
			}
			limitsMap.add(map);
		}
		return systemlimits;
	}

	private Map<String,Boolean> getUrlLimits(Map<String, List<Map<String, Object>>> systemlimits){
		Map<String,Boolean> map = new HashMap<String, Boolean>();
		for (String key : systemlimits.keySet()) {
			List<Map<String, Object>> list = systemlimits.get(key);
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> data = list.get(i);
				String url = String.valueOf(data.get("url"));
				Integer islimit = Integer.valueOf(String.valueOf(data.get("islimit")));
				if(!url.equals("null") && !CommonUtils.isEmpty(url)){
					map.put(url,islimit==1);
				}
			}
		}
		return map;
	}



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String lowercaseLogin = username.toLowerCase(Locale.ENGLISH);
        List<FSystemuser> list = sqlMapper.selectList("select * from f_systemuser a where a.usercode='"+username+"' ",FSystemuser.class);
        if(list.size() == 0){
            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " +
                "database");
        }
        FSystemuser u = list.get(0);
        List<GrantedAuthority>  roles = new ArrayList<>();
        roles.add(() -> "ROLE_ADMIN");
        u.setAuthorities(roles);
        return u;
    }
}
