
/**
* 辅组方法
*/
Ext.define('app.utils.ProjectUtils', {
	 alternateClassName: 'PU',
	 requires: [
        'app.view.platform.systemattach.FileBatchUpload',
        'app.view.platform.systemattach.FileBatchPreview'
     ],
	 statics: {
	 	wins:{},
	 	
	 	operateLimits:{},
        
        getHeight:function(){
        	return Ext.FramePanel.getEl().getHeight()
        },
        
        getWidth:function(){
        	return Ext.FramePanel.getEl().getWidth()
        },
        
        openTabModule:function(config,tabcfg){
        	CU.log(config)
    		if(Ext.isEmpty(config.type) || config.type =='00')return;
        	var tabcfg = tabcfg || {}
        	var contentPanel = Ext.SystemTabPanel;
        	if(Ext.isEmpty(contentPanel)){EU.toastErrorInfo("主窗口容器为空，请与管理员联系。");return;};
        	if(contentPanel instanceof Ext.tab.Panel){
	     		var id = config.id;
	        	var tabPanel = contentPanel.getComponent("TAB_"+id);
	        	if(Ext.isEmpty(tabPanel)){
        			var items = contentPanel.items.items;
        			if(cfg.openPanelNumber!=-1 && items.length>=cfg.openPanelNumber){
        				EU.showMsg({title:"警告",icon:Ext.MessageBox.WARNING,message:"<font color='red'>由于打开多个窗口会影响机器性能!<br>系统限制最多只能打开"+cfg.openPanelNumber+"个窗口!</font>"});
        				return;
        			}
		     	 	if(config.type=='02'){
			     	 	tabPanel =  Ext.create("Ext.ux.IFrame",{id:"TAB_"+id,title:config.text,closable:true,margin: '1 0 0 0',src:config.url});
		     	 	}else {
		     	 		tabPanel = Ext.create(config.url,{id:"TAB_"+id,title:config.text,closable:true,margin: '1 0 0 0'});
		     	 	}
		     	 	tabPanel.menuid = id;
		     	 	if(!Ext.isEmpty(config.glyph))tabPanel.glyph = config.glyph;
		     	 	if(!Ext.isEmpty(config.iconCls))tabPanel.iconCls = config.iconCls;
			     	tabPanel = contentPanel.add(tabPanel);
			     	tabPanel.tab.setClosable(!(tabcfg.checked === false));
			     	this.systemLimits(tabPanel);
		     	}
		     	return contentPanel.setActiveTab(tabPanel);
        	}else if(contentPanel instanceof Ext.container.Container){
        		var id = config.id, cid = "TAB_"+id,
            		mainLayout = contentPanel.getLayout(),
	        		lastView = mainLayout.getActiveItem(),
	        		tabPanel = contentPanel.child('component[id=' + cid + ']');
	        	if(!tabPanel){
        			var items = contentPanel.items.items;
        			if(cfg.openPanelNumber!=-1 && items.length>=cfg.openPanelNumber)contentPanel.remove(items[0]);
	        		if(config.type=='02'){
			     	 	tabPanel = Ext.create("Ext.ux.IFrame",{id:cid,margin: '20 20 0 20',cls:'shadow',src:config.url});
		     	 	}else {
		     	 		tabPanel = Ext.create(config.url,{id:cid,header:false,margin: '20 20 0 20',cls:'shadow'});
		     	 	}
		     	 	tabPanel.menuid = id;
		     	 	tabPanel.height = Ext.Element.getViewportHeight() - 84;
		     	 	if(!Ext.isEmpty(config.glyph))tabPanel.glyph = config.glyph;
		     	 	if(!Ext.isEmpty(config.iconCls))tabPanel.iconCls = config.iconCls;
	                mainLayout.setActiveItem(contentPanel.add(tabPanel));
	        	}else{
	        		if (tabPanel !== lastView) {
	                    mainLayout.setActiveItem(tabPanel);
	                }
	        		if (tabPanel.isFocusable(true)) {
			            tabPanel.focus();
			        }
	        	}
			    this.systemLimits(tabPanel);
        	}else{
        		EU.toastErrorInfo("错误主窗口容器，请与管理员联系。");
        	}
        },
	 	
	 	openModule:function(config){
	 		var me = this;
	 		if(Ext.isEmpty(config)){EU.toastErrorInfo("错误请求!");return;}
	 		if(Ext.isEmpty(config.url)&&Ext.isEmpty(config.xtype)){EU.toastWarn("url和xtype不能同时为null!");return;}
			config.modal = Ext.isEmpty(config.modal)?true:config.modal;
			config.layout = Ext.isEmpty(config.layout)?"fit":config.layout;
			var xtype = config.xtype; delete config.xtype;
			var url = config.url; delete config.url;
			var pcfg = config.pcfg; delete config.pcfg;
			Ext.apply(config,{maximizable: true});
			var item = null;
			var pscope = config.scope;
			config.resizable = Ext.isEmpty(config.resizable)?false:config.resizable;
			config.closable = false;
			config.height = (config.height == 'max' || config.height == -1) ? me.getHeight() : (config.height>me.getHeight()?me.getHeight():config.height);
			config.width = (config.width == 'max' || config.width == -1) ? me.getWidth() : (config.width>me.getWidth()?me.getWidth():config.width);
			var dialog = Ext.create('Ext.window.Window',config);
			if(!Ext.isEmpty(url)){
				var panelcfg = Ext.apply({src:url},pcfg);
	     	 	item = Ext.create("Ext.ux.IFrame",panelcfg);
				//item.iframeEl.dom.contentWindow = iframe对象
     	 	}else {
     	 		var panelcfg = Ext.apply({params:config.params,pscope:pscope,callback:config.callback,fn:config.fn},pcfg);
     	 		item = Ext.create(xtype,panelcfg);
     	 	}
     	 	dialog.add(item);
     	 	if(Ext.isObject(config.position)){
     	 		dialog.setPosition(config.position.x,config.position.y);
     	 	}
     	 	dialog.show();
     	 	dialog.on("close",function(panel, eOpts){delete me.wins[dialog.id];}, this);
     	 	dialog.addTool({xtype:'tool',type:'close',tooltip:'关闭窗口',scope:this,handler:function(){
				if(Ext.isFunction(item.closeWindowVerify)){
					item.closeWindowVerify();
				}else{
					dialog.close();
				}
			}});
			me.wins[dialog.id] = dialog;
			return dialog;
	 	},
	 	
	 	/**
		 * 附件上传/下载组件
		 * @param {} config
		 * @param disabeld 是否不可上传 缺省false
		 *
		 */
	 	openAttachWindow : function(config){
			config.disabeld = Ext.isEmpty(config.disabeld)?false:config.disabeld;
			config.scope = config.scope || this;
			config.title = config.title || (config.disabeld?"附件预览":"附件上传");
			config.modal = Ext.isEmpty(config.modal) ? true : config.modal;
			if(Ext.isEmpty(config.tablename)){
				EU.toastErrorInfo("参数:tablename不能为空!");return;
			}else if(Ext.isEmpty(config.fieldname)){
				EU.toastErrorInfo("参数:fieldname不能为空!");return;
			}else if(Ext.isEmpty(config.fieldvalue)){
				EU.toastErrorInfo("参数:fieldvalue不能为空!");return;
			}
			var xtype='FileBatchUpload',width=1000,height=600;
			if(config.disabeld){width = 800;xtype = 'FileBatchPreview';}
			this.openModule({xtype:xtype,title:config.title,width:width,height:height,params:config,modal:config.modal,scope:config.scope,callback:config.callback});
		},
		
		download : function(cfg,timeout){ 
			var me = this;
			var params = Ext.isEmpty(cfg.params)?{}:cfg.params;
			var url = Ext.isEmpty(cfg.url)?"platform/fileattach/downloadfile.do":cfg.url;
			for (var key in params){var data=params[key];if(Ext.isArray(data))params[key] = CU.toString(data);}//转换为spring @RequestList接受的转码格式
            params = CU.toParams(params);//转换为spring mvc参数接受方式
            url+= (url.indexOf("?")>0?"&":"?")+CU.parseParams(params);
			var width = Ext.isEmpty(cfg.width)?650:cfg.width; //350
            var height = Ext.isEmpty(cfg.height)?500:cfg.height; //300
			var bodyWidth =Ext.getBody().getWidth()
            var bodyHeight = Ext.getBody().getHeight();
            var iLeft = bodyWidth/2-(width/2);
            var iTop = bodyHeight/2-(height/2);
            window.open(url,'fullscreen=0,menubar=0,toolbar=0,location=0,scrollbars=0,resizable=0,status=1,left='+iLeft+',top='+iTop+',width='+width+',height='+height);
            if(Ext.isFunction(cfg.callback))cfg.callback();
        },
		
		/**
		 * 系统刷新
		 * @param {} xtype
		 */
        onAppUpdate:function(config){
        	config = config || {};
        	config.title = config.title || '应用更新';
        	config.msg = config.msg || '应用程序有一个更新，是否重新加载界面？';
        	config.option = 1;
        	config.callback = function(choice){
        		if (choice === 'yes') {
                    if(config.xtype){
                    	EU.redirectTo(config.xtype);
                    }else{
                   		window.location.reload();
                    }
                }
        	}
        	EU.showMsg(config);
        },
        
        /**
         * 退出系统
         * @param {} btn
         */
        onLogout:function(btn,callback){
	    	EU.showMsg({title:"退出系统",message:"您确定要退出吗？",animateTarget:btn,option:1,callback:function(result){
	    		if(result != 'yes')return;
	        	EU.RS({url:"login/logout.do",callback:function(result){
	        		if(CU.getBoolean(result)){
	        			session.remove("isLogin");
	        			Ext.callback(callback);
	        			EU.redirectTo(cfg.xtypeLogin);
	        		}
	        	}});
	    	}});
	    },
	    
	    /**
	     * 获取系统全部的url连接
	     * @param {} callback
	     * @param {} scope
	     */
	    createUrlFunction:function(systemurls){
	    	if(Ext.isEmpty(systemurls))return;
	    	for(key in systemurls){
        		var rec = systemurls[key];
        		if(EU[rec.beanname] == null)EU[rec.beanname] = {};
        		EU[rec.beanname][rec.methodname] = new Function("cfg","cfg.url='"+rec.url+"';return EU.RS(cfg);");
        	}
	    },
	    
	    /**
	     * 系统按钮权限控制
	     * @param {} panel 	  获取reference的容器
	     * @param {} modulurl 为alternateClassName对应的地址
	     */
	    systemLimits:function(panel,modulurl,callback){
	    	if(!Ext.isFunction(panel.lookupReference))return;
	    	modulurl  = modulurl || panel.$className;
	    	var operateLimits = cfg.sub.systemlimits[modulurl];
	    	if(!Ext.isArray(operateLimits))return;
     		Ext.each(operateLimits,function(rec){
     			if(rec.islimit > 0)return;
     			var object = panel.lookupReference(rec.operatecode);
     			if(Ext.isEmpty(object))return;
     			object.hide();
     		});
     		if(Ext.isFunction(callback))Ext.callback(callback)
	    }
	 }
});