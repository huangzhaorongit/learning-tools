
/**
* 辅组方法
*/
Ext.define('app.utils.ExtUtils', {
	alternateClassName: 'EU',
	statics: {
		redirectTo :function(xtype){
			Ext.Panel.redirectTo(xtype);
		},
		
        toastInfo : function( text, config){ 
    		var param = {title : '提示信息',html : text,border : true,saveDelay : 10,align : 'tr',closable : true,minWidth : 200,useXAxis : false,slideInDuration : 800,slideBackDuration : 1500,iconCls : 'ux-notification-icon-smile',autoCloseDelay : 4000,slideInAnimation : 'elasticIn',slideBackAnimation : 'elasticIn'};
			Ext.apply(param, config);Ext.toast(param);
        },
        
        toastWarn :function(text, config){
        	var param = {title : '警告信息',html : text,border : true,saveDelay : 10,align : 'tr',closable : true,minWidth : 200,useXAxis : false,slideInDuration : 800,slideBackDuration : 1500,iconCls : 'ux-notification-icon-warn',autoCloseDelay : 4000,slideInAnimation : 'elasticIn',slideBackAnimation : 'elasticIn'};
			Ext.apply(param, config);Ext.toast(param);
        },
        
        toastErrorInfo :function(text, config){
        	var param = {title : '错误信息',html : text,border : true,saveDelay : 10,align : 'tr',closable : true,minWidth : 200,useXAxis : false,slideInDuration : 800,slideBackDuration : 1500,iconCls : 'ux-notification-icon-error',autoCloseDelay : 5000,slideInAnimation : 'elasticIn',slideBackAnimation : 'elasticIn'};
			Ext.apply(param, config);Ext.toast(param);
        },
        
        /**
         * 弹出消息框
         * prompt 缺省:false   true:可以输入 false不可以输入
         */
        showMsg:function(config){
        	config = config || {};
        	config.title = config.title || "消息";
        	config.message = config.message|| config.msg || "";
        	config.option = config.option || -1;
        	config.fn = config.callback;
			if (Ext.isEmpty(config.buttons)) {
				switch (config.option) {
					case 1 :{
						config.icon = Ext.MessageBox.QUESTION;
						config.buttons = Ext.MessageBox.YESNO;
						break;
					}
					case 2 :config.buttons = Ext.MessageBox.YESNOCANCEL;break;
					case 3 :config.buttons = Ext.MessageBox.OKCANCEL;break;
					default :config.buttons = Ext.MessageBox.OK;break;
				}
			}
        	Ext.Msg.show(config);
        },
        
        /**
         * 远程访问
         * @param {} config
         */
        RS:function(config){
        	var thiz = this;
        	if(config.service && config.method){
        		config.url = CU.getURL(config.service,config.method);
        		delete config.method;
        	}
			var params = Ext.isEmpty(config.params)?{}:config.params;
			for (var key in params){var data=params[key];if(Ext.isArray(data))params[key] = CU.toString(data);}//转换为spring @RequestList接受的转码格式
			config.params = CU.toParams(params);//转换为spring mvc参数接受方式
        	config.async = Ext.isEmpty(config.async)?true:config.async; // true=异步 , false=同步
        	config.scope = config.scope || thiz;
        	config.dataType = config.dataType || "json"; 
        	config.timeout = config.timeout || 30000;
        	config.method  = 'POST';
        	var msg = Ext.isEmpty(config.msg)? config.async :config.msg;
        	config.message = config.message || '正在访问服务器, 请稍候...';
        	config.target =  config.target || Ext.Viewport;
        	thiz.myMask = null;
			if (msg){
				thiz.myMask = new Ext.LoadMask({msg:config.message,target:config.target,removeMask:true}); //,style:'background-color: rgba(208, 208, 208, 0.5);'
				thiz.myMask.show();
			}
			var caller_callback = config.callback;
			var caller_errorcallback = config.errorcallback;
			var datas = null;
			
			var callback = function(type,scope,success,result,response, options){
				if(msg)thiz.myMask.hide();
				if(success){
					datas = result;
					if (Ext.isFunction(caller_callback)) {
					 	Ext.callback(caller_callback,config.scope,[result]);
					}
				}else{
					if(response.status=="999" || response.status=="998")return;
					if(Ext.isFunction(caller_errorcallback)){ 
					 	Ext.callback(caller_errorcallback,config.scope,[response,options]);
					}else{
						thiz.toastErrorInfo("访问远程服务器失败!");
					}
				}
			}
			if(cfg.crossdomain){
				config.url =  cfg.requestUrl + config.url;
				config.callback = function(success, result,errortype) {
					Ext.callback(callback,this,['jsonp',config.scope,success,result])
				}
				Ext.data.JsonP.request(config);
			}else{
				config.callback = function(options, success, response) {
					var text = response.responseText;
					Ext.callback(callback,this,['json',config.scope,success,CU.toObject(text),response,options])
				};
				Ext.Ajax.request(config);
			}
			return datas;
        },
        
        /**
         * 常用的view转码数据查询（带缓存机制）。
         * @param {} config 对象或viewname
         * @return {}
         */
        RSView:function(config,callback){
        	var thiz = this;
        	if(Ext.isString(config))config = {params:{viewname:config}};
        	var cache = Ext.isEmpty(config.cache)?true:config.cache;//是否缓存,缺省true
        	var key = config.params.viewname;
        	var data = cache?session.get(key):null;
        	callback = callback || config.callback;
        	var scope = config.scope || thiz;
        	if(!Ext.isEmpty(data) && Ext.isArray(data)){
        		if(Ext.isFunction(callback))Ext.callback(callback,scope,[data]);
        		return data;
        	}
        	var url = "platform/basecode/getviewlist.do";
        	var params = {viewname:config.params.viewname,ids:config.ids,idfield:config.idfield,orderbyfield:config.orderbyfield};
        	thiz.RS({url:url,params:params,callback:function(result){
        		if(cache)session.set(key,result);
        		data = result;
        		if(Ext.isFunction(callback))Ext.callback(callback,scope,[data]);
        	}});
        	return data;
        },
        
        /**
         * 查询配置文件列表信息
         * @param {} config
         * @param {} callback
         * @return {}
         */
        RScfgList:function(config,callback){
        	var params = {};
        	if(Ext.isString(config))config = {params:{type:config}};
        	config.msg = false;
        	config.url = "platform/systemcfg/getlist.do";
        	if(Ext.isFunction(callback))config.callback = callback;
        	return this.RS(config);
        },
        
        /**
         * 获取系统配置文件信息
         * @param {} config  对象或主键id
         * @param {} callback
         * @return {}
         */
        RScfgInfo:function(config,callback){
        	var params = {};
        	if(Ext.isString(config))config = {params:{id:config}};
        	config.msg = false;
        	config.url = "platform/systemcfg/getinfo.do";
        	if(Ext.isFunction(callback))config.callback = callback;
        	return this.RS(config);
        },
        
        /**
         * 删除系统配置文件信息
         * @param {} config 对象或主键id
         * @param {} callback
         * @return {}
         */
        RScfgDel:function(config,callback){
        	var params = {};
        	if(!Ext.isObject(config))config = {params:{id:config}};
        	config.msg = false;
        	config.url = "platform/systemcfg/delete.do";
        	if(Ext.isFunction(callback))config.callback = callback;
        	return this.RS(config);
        },
        
        /**
         * 新增系统配置文件信息
         * @param {} config
         * @param {} callback
         * @return {}
         */
        RScfgSave:function(config,callback){
        	var params = {};
        	config.msg = false;
        	config.url = "platform/systemcfg/saveorupdate.do";
        	if(Ext.isFunction(callback))config.callback = callback;
        	return this.RS(config);
        }
	}
});