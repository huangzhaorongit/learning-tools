Ext.define('app.view.platform.login.LockScreen', {
    extend: 'ux.form.Panel',
    alternateClassName: 'lockscreen',
    xtype: 'lockscreen',
    layout: {type: 'vbox',align: 'center', pack: 'center'},
	bodyStyle:"background:url('resources/images/bgpng.jpg') no-repeat;background-size:100%;",
	initComponent:function(){
		var me = this;
		this.photoImg = Ext.create("Ext.Img",{cls: 'header-right-profile-image',height: 150,width: 150,alt:'current user image'});
        this.l_username = Ext.create("Ext.form.Label");
		this.t_usercode = Ext.create("Ext.form.field.Text",{name: 'usercode',height: 25,scope:this,hidden:true,allowBlank:true,emptyText: '用户名称',width:250,listeners: {specialkey: me.onUserNameEnterKey}});
		this.t_password = Ext.create("Ext.form.field.Text",{name: 'password',inputType:'password',height: 25,scope:this,allowBlank:true,emptyText: '用户密码',width:250,listeners: {specialkey:function(field,e){if (e.getKey()==Ext.EventObject.ENTER){ me.login()}  }}});
		var btns = {xtype: 'container',layout: 'table',defaults : {margin : 20},items: [
	                   	{xtype: 'button',text: '登陆',scope:this,width: 100,action:'login',handler:me.login},
	                	{xtype: 'button',scope:this,text: '切换用户',width: 100,handler:me.checkUser}
	               ]}
		this.p_form = new Ext.form.Panel({width:400,layout:{type:'vbox',align:'center',pack:'center'},bodyPadding: '20 20 20 20',baseCls:'background:none;',
										items:[this.photoImg,this.l_username,this.t_usercode,this.t_password,btns]});
		me.items = [this.p_form];
		this.callParent();
	},
	
    beforeShow:function(){
    	var me = this;
    	this.l_username.show();
    	this.t_usercode.hide();
    	this.photoImg.setSrc('platform/personnel/getphoto.do?_dc='+new Date().getTime()+'&companyid='+cfg.sub.companyid+'&photoid='+cfg.sub.photoid);
		this.l_username.getEl().setHtml('<h2><font  color="#ffffff"><span style="letter-spacing:2mm;text-shadow: 3px 3px 3px rgba(42, 42, 42, 0.75);">'+cfg.sub.usercode+'</span></font></h2>');
    	this.t_usercode.setValue(cfg.sub.usercode);
    	this.t_password.setValue("");
    },
    
    checkUser:function(){
    	this.l_username.hide();
    	this.t_usercode.show();
    	this.t_usercode.setValue("");
    	this.t_password.setValue("");
    },
    
    login:function(){
    	this.validate(false);
    },
    
    validate:function(invalidate){
    	var me = this;
    	var p_form = this.p_form;
    	var username = this.t_usercode.getValue();
    	var password = this.t_password.getValue();
    	if(Ext.isEmpty(username)){EU.toastErrorInfo("用户名不能为空");return;}
    	if(Ext.isEmpty(password)){EU.toastErrorInfo("密码不能为空");return;}
    	var userinfo = p_form.getValues();
    	userinfo.invalidate = invalidate;
    	var url = "login/validate.do";
    	EU.RS({url:url,scope:this,params:userinfo,callback:function(result){
			if(result.success){
				var old_userinfo = local.get("userinfo");
				if(!Ext.isEmpty(old_userinfo))userinfo.keeppassword = old_userinfo.keeppassword;
			  	local.set("userinfo",userinfo);
			  	var sameUser = username == cfg.sub.usercode;
			 	session.set("sub",cfg.sub = result.data);
			 	if(!sameUser){
			 		Ext.FramePanel.removeAll(true);
    				Ext.FramePanel.add(Ext.create(cfg.xtypeFrame));
			 	}else{
					if(Ext.msgWin)Ext.msgWin.initStart();
			 	}
				Ext.Viewport.getLayout().setActiveItem(0);
			   	return;
		    }
    		var msg = "";
			switch (result.data) {
			  	case "1": msg = "请输入正确的验证码!"; break; 
				case "2": msg = "您所输入的用户名不存在!"; break; 
				case "3": msg = "密码输入错误,请重新输入!"; break;
				case "4": msg = "当前用户名已被锁定,无法登录!"; break;
				case "5": msg = "当前用户名已被注销,无法登录!"; break; 
				case "6": msg = "当前用户所在公司已被注销,无法登录!"; break; 
				case "7": {
					EU.showMsg({title:'提示信息',message:"当前用户已经在线，确定强制登录吗？",option:1,callback:function(rt){
				    	if(rt=='yes')me.validate(true);
			    	}});
					break;
				}
				default:  msg = "提交失败, 可能存在网络故障或其他未知原因!"; break;
			}
    		if(!Ext.isEmpty(msg))EU.toastErrorInfo(msg);
    	}});
    },
    
    onUserNameEnterKey:function(field,e){
    	if (e.getKey()==Ext.EventObject.ENTER){
            this.t_password.focus(true,true);
        }  
    }
});