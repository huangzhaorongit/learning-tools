package com.distribution.wamoli.web.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

import com.distribution.wamoli.common.bean.MessageBean;
import com.distribution.wamoli.common.bean.UserBean;
import com.distribution.wamoli.common.exception.WebSokcetException;
import com.distribution.wamoli.common.utils.DateUtils;
import com.distribution.wamoli.common.utils.JsonUtils;
import com.distribution.wamoli.common.utils.MyBatisUtil;
import com.distribution.wamoli.common.utils.SessionUtils;
import com.distribution.wamoli.domain.FSystemmessage;
import com.distribution.wamoli.service.SystemMessageService;
import com.distribution.wamoli.service.impl.SystemMessageServiceImp;

@ServerEndpoint("/WebSocketServer")
@Controller
public class WebSokcetServer {
	private UserBean userbean = new UserBean();

	private Session session = null;

	private SqlSession sqlsession;

	private SystemMessageService service;

	@OnOpen
	public void start(Session session) {
		this.session = session;
		service = MyBatisUtil.getSystemService(SystemMessageServiceImp.class);
		sqlsession = service.getSqlMapper().getSqlSession();
	}

	@OnClose
	public void end() {
		WebSokcetServer ws = SessionUtils.sokcetMap.remove(userbean.getUserid());
		if(ws!=null){
			MessageBean bean = new MessageBean();
			bean.setType(2);
			bean.setFid(userbean.getUserid());
			bean.setFname(userbean.getUsername());
			sendMessage(bean);
		}
		sqlsession.close();
	}

	@OnMessage
	public void reMessage(String message) {
		MessageBean bean = (MessageBean) JsonUtils.fromJson(message,MessageBean.class);
		switch(bean.getType()){
			case 1 : {
				BeanUtils.copyProperties(SessionUtils.getUserBean(bean.getFid()),userbean);
				if(userbean==null)return;
				userbean.setSystemlimits(null);
				userbean.setSystemurls(null);
				userbean.setPdfformat(null);
				bean.setResult(userbean);
				sendMessage(bean);
				SessionUtils.sokcetMap.put(userbean.getUserid(),this);
				break;
			}
			case 2 : {
				end();
				break;
			}
			case 3 : {
				sendMessage(bean);
				break;
			}
		}
	}

	/**
	 * 发送消息
	 * @param bean
	 */
	public synchronized void sendMessage(MessageBean bean) {
		switch(bean.getType()){
			case 1 :
			case 2 : {
				Iterator<Entry<String,WebSokcetServer>> it = SessionUtils.sokcetMap.entrySet().iterator();
				while(it.hasNext()){
			        Entry<String,WebSokcetServer> entry =it.next();
			        WebSokcetServer ws = entry.getValue();
			        try {
						ws.session.getBasicRemote().sendText(JsonUtils.toJsonString(bean));
					} catch (Exception e) {
						it.remove();
						try {
							ws.session.close();
						} catch (IOException e1) {
						}
					}
				}
				break;
			}
			case 3 : {
				try{
					FSystemmessage sysbean = messageToSystemmessage(bean);
					sysbean.setIsread("0");
					boolean issend = false;
					Iterator<Entry<String,WebSokcetServer>> it = SessionUtils.sokcetMap.entrySet().iterator();
					while(it.hasNext()){
				        Entry<String,WebSokcetServer> entry =it.next();
				        WebSokcetServer ws = entry.getValue();
				        if(!bean.getFid().equals(bean.getJid()) && ws.userbean.getUserid().equals(bean.getJid())){
							sysbean.setIsread("1");
							service.saveOrUpdate(sysbean);
							issend = true;
							sendText(ws, bean);
						}
						if(ws.userbean.getUserid().equals(bean.getFid())){
							MessageBean fbean = new MessageBean();
							BeanUtils.copyProperties(bean,fbean);
							fbean.setFid(bean.getJid());
							sendText(ws,fbean);
						}
					}
					if(!issend)service.saveOrUpdate(sysbean);
					sqlsession.commit();
				}catch(Exception e){
					bean.setFname("系统");
					bean.setJid(bean.getFid());
					bean.setResult("消息发送失败！");
					sendText(this,bean);
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 发送消息
	 * @param ws
	 * @param bean
	 * @param sysbean
	 */
	private void sendText(WebSokcetServer ws,MessageBean bean){
		try {
			ws.session.getBasicRemote().sendText(JsonUtils.toJsonString(bean));
		} catch (Exception e) {
			try {
				SessionUtils.sokcetMap.remove(ws.userbean.getUserid());
				ws.session.close();
				ws.sqlsession.close();
			} catch (IOException e1) {
				ws.session = null;
				ws.sqlsession = null;
			}
			throw new WebSokcetException();
		}
	}

	public Session getSession() {
		return session;
	}

	public UserBean getUserbean() {
		return userbean;
	}

	public FSystemmessage messageToSystemmessage(MessageBean bean){
		FSystemmessage sysbean = new FSystemmessage();
		BeanUtils.copyProperties(bean,sysbean);
		sysbean.setTime(DateUtils.getCurrentTimestamp());
		bean.setTime(DateUtils.getFormattedString(new java.util.Date(), "YYYYMMDD"));
		sysbean.setResult(String.valueOf(bean.getResult()));
		return sysbean;
	}
}
