package com.distribution.wamoli.common.utils;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import com.distribution.wamoli.common.bean.UserBean;
import com.distribution.wamoli.common.exception.ApplicationRuntimeException;
import com.distribution.wamoli.web.controller.WebSokcetServer;

/**
 * 系统session管理
 * @author LIUM
 */
public class SessionUtils {//Session容器 (key=session)
	public static final Hashtable<String,HttpSession> SessionContext = new Hashtable<String,HttpSession>();

	//WebSokcet用户管理
	public static final Hashtable<String,WebSokcetServer> sokcetMap = new Hashtable<String,WebSokcetServer>();


	/**
	 * 用户是否在线
	 */
	public static boolean isOnlineUser(String userid) {
		boolean result = false;
		Iterator<Entry<String,HttpSession>> it = SessionContext.entrySet().iterator();
		while(it.hasNext()){
	        Entry<String,HttpSession> entry =it.next();
	        HttpSession session = entry.getValue();
	        try{
		        UserBean bean = (UserBean)session.getAttribute(Globals.SYSTEM_USER);
				if(bean==null)continue;
				if(bean.getUserid().equals(userid)){
					result = true;
					break;
				}
	        }catch(IllegalStateException e){
	        	it.remove();
	        }
		}
		return result;
	}

	/**
	 * 注销在线用户
	 * @param userid 用户ID
	 */
	public synchronized static void invalidateOnlineUser(String userid) {
		Iterator<Entry<String,HttpSession>> it = SessionContext.entrySet().iterator();
		while(it.hasNext()){
	        Entry<String,HttpSession> entry =it.next();
	        HttpSession session = entry.getValue();
	        try{
				UserBean bean = (UserBean)session.getAttribute(Globals.SYSTEM_USER);
				if(bean==null)throw new ApplicationRuntimeException();
				if(bean.getUserid().equals(userid)){
					invalidateOnlineUser(session);
					it.remove();
				}
			}catch(Exception e){ //失效用户
				try{
					session.invalidate();
				}catch(Exception ex){}
				it.remove();
			}
		}
	}

	/**
	 * 注销在线用户
	 * @param session 用户session
	 */
	public synchronized static void invalidateOnlineUser(HttpSession session){
		try{
			UserBean bean = (UserBean)session.getAttribute(Globals.SYSTEM_USER);
			if(bean==null)return;
			WebSokcetServer ws = sokcetMap.get(bean.getUserid());
			if(ws!=null){
				ws.getSession().close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.invalidate();
		}
	}

	/**
	 * 根据用户ID获取在线用户HttpSession
	 * @param userid
	 * @return
	 */
	public static HttpSession getHttpSession(String userid){
		Iterator<Entry<String,HttpSession>> it = SessionContext.entrySet().iterator();
		while(it.hasNext()){
	        Entry<String,HttpSession> entry =it.next();
	        HttpSession session = entry.getValue();
	        try{
		        UserBean bean = (UserBean)session.getAttribute(Globals.SYSTEM_USER);
				if(bean==null)continue;
				if(bean.getUserid().equals(userid)){
					return session;
				}
	        }catch(IllegalStateException e){
	        	it.remove();
	        }
		}
		return null;
	}

	/**
	 * 根据用户ID获取在线用户UserBean
	 * @param userid
	 * @return
	 */
	public static UserBean getUserBean(String userid){
		Iterator<Entry<String,HttpSession>> it = SessionContext.entrySet().iterator();
		while(it.hasNext()){
	        Entry<String,HttpSession> entry =it.next();
	        HttpSession session = entry.getValue();
	        try{
		        UserBean bean = (UserBean)session.getAttribute(Globals.SYSTEM_USER);
				if(bean==null)continue;
				if(bean.getUserid().equals(userid)){
					return bean;
				}
	        }catch(IllegalStateException e){
	        	it.remove();
	        }
		}
		return null;
	}
	}
