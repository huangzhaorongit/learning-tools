Ext.define('app.view.platform.frame.desktop.Main', {
	extend : 'Ext.ux.desktop.App',
    quickdatas : [], 	  //任务栏快捷方式
	desktopdatas : [], 	 //桌面图标
	modules : [],   	 //菜单树
	moduleMap : {},
	themecfg: {type:'01'},
    init: function() {
    	var me = this;
    	Loader.request("app/view/platform/frame/desktop/resources/style.css");
    	var result = EU.RS({url:"platform/systemframe/getmenutree.do",async:false});
    	Ext.each(result,function(rec){
    		var cfg = {title:rec.text,iconCls:rec.iconCls,launcher:{text:rec.text,iconCls:rec.iconCls}};
    		if(Ext.isArray(rec.children) && rec.children.length>0){
				cfg.handler = function(){return false;}
				cfg.launcher.menu = {};
    			me.dataToModules(rec.children,cfg.launcher.menu,function(rec){
    				if(Ext.isEmpty(rec.param1))return;
    				me.desktopdatas.push(Ext.apply(rec,{name:rec.text,appType:"WID_"+rec.id}));
    			});
    		}
			me.modules.push(cfg);
    	});
    	this.callParent();
    	this.refreshDesktopIcon();
    },
    
    
    dataToModules:function(arr,menu,callback){
    	var me = this;
    	menu.items = [];
    	Ext.each(arr,function(rec){
			var appType = "WID_"+rec.id;
			var style = Ext.style=='neptune'?'color:#FFFFFF;':'color:#4e90c1;';
    		var cfg = {text:rec.text, appType:appType, iconCls:rec.iconCls, iconColor:rec.iconColor, type:rec.type, url:rec.url,
    			config :{
	    			tools :[{
	    				iconCls: 'x-fa fa-star-o',style:style,tooltip: '添加到桌面快捷方式',menuid: rec.id,hidden: true,
					    listeners: {
					    	click:function(thiz,eOpts){
						    	var params = Ext.apply({value:thiz.menuid},me.themecfg);
					    		EU.RScfgSave({params:params},function(result){
						    		var dataview = me.desktop.shortcutsView;
    								dataview.store.add({name:rec.text,iconCls:rec.iconCls,appType:appType,iconColor:rec.iconColor,orderno:result});
        							me.refreshDesktopIcon();
						    		thiz.hide();
						    	});
						    },
						    beforerender:function(thiz,eOpts){
						    	var dataview = me.desktop.shortcutsView;
						    	var map = dataview.getStore().getData().map;
						    	if(!(Ext.isObject(map) && Ext.isObject(map[thiz.menuid]))){
						    		thiz.show();
						    	}
						    }
					    }
					}]
    			},
    			handler : function(thiz) {
					var win = me.desktop.app.createDesktopWindow(me.desktop, thiz);
					if (win)me.desktop.restoreWindow(win);
				}
    		};
    		if(Ext.isArray(rec.children) && rec.children.length>0){
				cfg.menu = {};
				me.dataToModules(rec.children,cfg.menu,callback);
			}
			me.moduleMap[appType] = cfg;
    		menu.items.push(cfg);
    		if(Ext.isFunction(callback))Ext.callback(callback,this,[rec]);
    	});
    },
    
    getModules : function(){
    	return this.modules;
    },
    
    getModule : function(name) {
        return this.moduleMap[name];
    },
    
    /**
     * 桌面
     * @return {}
     */
    getDesktopConfig: function () {
        var me = this;
        var cfg = {
        	dataviewlisteners:{
        		itemcontextmenu : function rightClick(dataView, record, item, index, e, options) {
					e.preventDefault();
					if (Ext.isEmpty(item.menu)) {
						 item.menu = new Ext.menu.Menu({
						 	items : [
								{text : '删除菜单',iconCls : 'x-fa fa-bitbucket',handler : function(btn,e){
									EU.RScfgDel(record.data.themecfgid,function(result){
							    		dataView.store.remove(record);
	    								me.refreshDesktopIcon();
							    	});
								}}
							]
						});
					}
					item.menu.showAt(e.getXY());
				}
        	},
            contextMenuItems: [
                { text: '壁纸设置', handler:function(){}, scope: me },
                { text: '刷新布局', handler:function(){me.refreshDesktopIcon(); }, scope: me },'-',
                { text: '显示全部', handler:function(){Ext.each(this.desktop.windows.items,function(rec){rec.show();});}, scope:me,minWindows:1},
                { text: '隐藏全部', handler:function(){Ext.each(this.desktop.windows.items,function(rec){rec.hide();});}, scope:me,minWindows:1}
            ],
            shortcuts: {data:me.desktopdatas},
            wallpaper: 'app/view/platform/frame/desktop/resources/images/bg/313296.jpg',
            wallpaperStretch: true
        }
        return Ext.apply(me.callParent(),cfg);
    },
    
    /**
     * 开始菜单样式
     * @return {}
     */
    getStartConfig : function() {
        var me = this, ret = me.callParent();
        return Ext.apply(ret, {
        	title: '系统菜单',iconCls: 'x-fa fa-list',height: 400,
            toolConfig: {
                width: 100,
                items: [
                    {text:'设置', iconCls:'settings',handler: me.onSettings,scope: me},'-',
                    {text:'退出',iconCls:'logout',handler: me.onLogout,scope: me}
                ]
            }
        });
    },
    
    /**
     * 快速启动
     * @return {}
     */
    getTaskbarConfig: function () {
        var me=this,ret = this.callParent();
        return Ext.apply(ret, {
            quickStart: me.quickdatas,
            trayItems: [
            	{xtype: 'button',cls: 'delete-focus-bg',iconCls: 'x-fa fa-university',text: '主题风格',initComponent:function(){
    				var me = this;
    				this.callParent();
    				EU.RSView("v_theme",function(result){
			    		Ext.each(result,function(rec){
			    			rec.code = rec.id;
			    			delete rec.id;
			    			rec.handler = function(btn){
			    				local.set("theme",btn.code);
			    				PU.onAppUpdate();
			    			}
			    		})
			       		me.setMenu(result);
			    	});
    			}},{xtype: 'button', cls: 'delete-focus-bg',iconCls: 'x-fa fa-th-large',text: '系统皮肤',initComponent:function(){
    				var me = this;
    				this.callParent();
    				EU.RSView("v_style",function(result){
			    		Ext.each(result,function(rec){
			    			delete rec.id;
			    			rec.handler = function(btn){
			    				localStorage.setItem("style",btn.text);
			    				PU.onAppUpdate();
			    			}
			    		})
			       		me.setMenu(result);
			    	});
    			}},
                { xtype: 'trayclock', flex: 1 }
            ]
        });
    },
    
    onLogout: function () {
    	PU.onLogout();
    },

    onSettings: function () {
        Ext.Msg.confirm('系统设置', '系统设置');
    },
    
    /**
     * 桌面排版
     */
    refreshDesktopIcon:function(){
    	var dataview = this.desktop.shortcutsView,
    		height = dataview.getHeight(),
    		nodes = dataview.getEl().dom.childNodes,
    		w=90,h=105,
    		n = parseInt(height/h);
    	dataview.store.sort([{property:"orderno"}]);
    	for (var i = 0; i < nodes.length; i++) {
    		var rec = nodes[i];
    		rec.style.left=parseInt(i/n)*w+"px";
	        rec.style.top=((i%n*h)+10)+"px";
    	}
    }
});