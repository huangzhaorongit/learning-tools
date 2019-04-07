Ext.define('app.controller.platform.login.LoginController', {
    extend: 'Ext.app.ViewController',
    
    alias:'controller.login',
    
    views: ['systemFrame'],
    
    onUserNameEnterKey:function(field,e){
    	if (e.getKey()==Ext.EventObject.ENTER){
    		this.lookupReference('t_password').focus(true,true);
        }  
    }, 
    
    onPasswordEnterKey:function(field,e){
    	if (e.getKey()==Ext.EventObject.ENTER){    
            this.onLoginButton();
        }  
    }, 
    
    onLoginButton:function(button, e, eOpts){
    	this.validate(false);
    },
    
    validate:function(invalidate){
    	var me = this;
        var p_form = this.lookupReference('p_form').getForm();
    	if(!p_form.isValid())return;
    	var userinfo = p_form.getValues();
    	userinfo.invalidate = invalidate;
    	var url = "login/validate.do";
    	EU.RS({url:url,scope:this,params:userinfo,callback:function(result){
    		var msg = "";
			if(result.success){this.login(userinfo,result.data);return}
			switch (result.data) {
				case "1": msg = "请输入正确的验证码!"; break; 
				case "2": msg = "您所输入的用户名不存在!!"; break; 
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
    
    login:function(userinfo,sub){
    	if(userinfo.keepusername != '1'){
    	   delete userinfo.username;
    	   delete userinfo.password;
    	}
    	if(userinfo.keeppassword != '1'){
    	   delete userinfo.password;
    	}
    	local.set("userinfo",userinfo);
		session.set("isLogin",true);
		cfg.sub = sub;
		PU.createUrlFunction(cfg.sub.systemurls);
		EU.redirectTo('systemFrame');
    },
    
    init:function(){
    	var me = this;
    	var userinfo = local.get("userinfo");
    	if(!Ext.isEmpty(userinfo)){
    		var p_form = me.lookupReference('p_form').getForm();
    		p_form.setValues(userinfo);
    	}
    }
});