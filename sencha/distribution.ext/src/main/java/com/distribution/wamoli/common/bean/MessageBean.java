package com.distribution.wamoli.common.bean;

public class MessageBean {

	/**1=登录,2=退出,3=用户消息(指定接受人)*/
	private int type;
	
	/**type=3  消息类型      默认 0=普通消息 */
	private int ctype = 0;

	/**消息发送者id*/
	private String fid;

	/**消息发送者名称*/
	private String fname;
 
	/**消息接受者id*/
	private String jid; 

	/**消息接受者名称*/
	private String jname;
	
	private Object result = "";
	
	private String time;
	
	private String by1;
	
	private String by2;
	
	private String by3;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	/**1=登录,2=退出,3=消息*/
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCtype() {
		return ctype;
	}

	public void setCtype(int ctype) {
		this.ctype = ctype;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getJid() {
		return jid;
	}

	public void setJid(String jid) {
		this.jid = jid;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getBy1() {
		return by1;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	public String getBy2() {
		return by2;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}

	public String getBy3() {
		return by3;
	}

	public void setBy3(String by3) {
		this.by3 = by3;
	}
}
