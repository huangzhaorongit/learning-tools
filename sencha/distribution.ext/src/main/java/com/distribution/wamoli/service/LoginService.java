package com.distribution.wamoli.service;
import com.distribution.wamoli.common.bean.ResultBean;
import com.distribution.wamoli.common.bean.UserBean;
import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FSystemuser;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface LoginService extends IService<FSystemuser>, UserDetailsService{


	/**
	 *  case "0": login(themetype); break;
	 *	case "1": msg = "请输入正确的验证码!"; break;
	 *	case "2": msg = "您所输入的用户名不存在!"; break;
	 *	case "3": msg = "密码输入错误,请重新输入!"; break;
	 *	case "4": msg = "当前用户名已被锁定,无法登录!"; break;
	 *	case "5": msg = "当前用户名已被注销,无法登录!"; break;
	 *	case "6": msg = "当前用户所在公司已被注销,无法登录!"; break;
	 *	default:  msg = "提交失败, 可能存在网络故障或其他未知原因!"; break;
	 */
	//@SystemLogs("用户登陆")
	public ResultBean login(String usercode,String password,Boolean invalidate);

	//@SystemLogs("查询登陆成功用户信息")
	public UserBean getUserInfo(String usercode);

}
