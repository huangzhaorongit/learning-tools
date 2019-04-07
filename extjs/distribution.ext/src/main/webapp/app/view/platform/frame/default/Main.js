Ext.define('app.view.platform.frame.default.Main', {
    extend: 'Ext.panel.Panel',
    xtype: 'defaultThene',
    requires: [
        'app.controller.platform.frame.default.MainController',
        'app.view.platform.frame.default.TabPanel',
        'app.view.platform.frame.default.UserEdit',
        'app.view.platform.frame.default.SkinSelect'
    ],
    controller:'defaultThemeController',
    layout: 'border',
    referenceHolder: true,
    menuPatternKey : "SYSTEM_MENU_PATTERN_KEY",
    initComponent : function() {
    	var me = this;
    	var type = local.get(me.menuPatternKey) || 0; //type 0=卡片式菜单（tabPanel），1=切换式菜单（accordion布局） 缺省：卡片式菜单  
    	var northPanel = Ext.create("Ext.panel.Panel",{region: 'north',
	    	items:[{xtype:'toolbar',height: 64,items: [
			        {xtype:'label',cls: 'delete-focus-bg',html:'<span style="letter-spacing:2mm;font-size:24px; color:#4D4D4D;" class="x-fa fa-th"><b>'+cfg.systemname+'</b></span>(Ver:6.0.2015.10.10) '},
			        "->",
			        {xtype: 'button',cls: 'delete-focus-bg',iconCls: 'x-fa fa-university',text: '主题风格',reference:'b_theme'},
			        {xtype: 'button',cls: 'delete-focus-bg',iconCls: 'x-fa fa-th-large',text: '系统皮肤',reference:'b_style'},
			        {xtype: 'skinSelect'},
			        {cls: 'delete-focus-bg',iconCls:'x-fa fa-search',tooltip: '全文检索'},
			        {cls: 'delete-focus-bg',iconCls:'x-fa fa-sign-out',tooltip: '退出系统',handler: 'onLogout'},
                	{cls: 'delete-focus-bg',iconCls:'x-fa fa-bell',tooltip: '消息提醒',reference:'b_tips',handler: 'onMessage'},
	                {cls: 'delete-focus-bg',iconCls:'x-fa fa-user',tooltip: '用户信息',handler: 'onUserclick'},
	                {xtype: 'tbtext',text: cfg.sub.username,cls: 'top-user-name'},
	                {xtype: 'image',cls: 'header-right-profile-image',height: 35,width: 35,alt:'current user image',src: 'platform/personnel/getphoto.do?_dc='+new Date().getTime()+'&photoid='+cfg.sub.photoid}
                ]}
	    	]
	    });
	    var tools = [
	    	{type : 'unpin',tooltip:'切换至层叠方式显示',handler : function(event, toolEl, panelHeader){
	    		local.set(me.menuPatternKey,type = (type==0?1:0));
	    		this.setTooltip(type==0?'切换至层叠方式显示':'切换至树状方式显示');
	    		this.setType(type==0?'unpin':'pin');
	    		me.updateMenuStyle(westPanel,systemPanel,favoritesPanel,type);
	    	}}, 
			{type : 'expand',tooltip:'收缩全部树节点',handler : function(event, toolEl, panelHeader){
				var panel = me.getHandleTreePanel(westPanel,type);
				if(panel)panel.collapseAll();
			}}, 
			{type : 'collapse',tooltip:'展开全部树节点',handler : function() {
				var panel = me.getHandleTreePanel(westPanel,type);
				if(panel)panel.expandAll();
			}},
			{type : 'refresh',tooltip:'刷新菜单树',handler : function(event, toolEl, panelHeader) {
				var panel = me.getHandleTreePanel(westPanel,type);
				if(panel)panel.getStore().reload();
			}
		}];
	    var maintabpanel = Ext.SystemTabPanel = Ext.create("frame.default.TabPanel");
	    var systemPanel = {xtype:'treepanel',layout: 'fit',title:"系统菜单",useArrows: true,hideHeaders: true,
            store:{autoLoad:true,url:'platform/systemframe/getmenutree.do',
            	listeners: {
            		load: function(thiz, records, successful, eOpts){
            			var datas = [];
            			Ext.SystemTabPanelAutoOpens = {};
            			Ext.each(records,function(record){datas.push(record.data);});
        				CU.eachChild(datas,function(data){
            				if(!Ext.isEmpty(data.param2)){
            					PU.openTabModule(data,{checked:false});
            					Ext.SystemTabPanelAutoOpens[data.id] = data;
            				}
        				},this);
            		}
            	}
            },
            columns: [{xtype: 'treecolumn',flex: 1,dataIndex: 'text',scope: 'controller', renderer: 'treeNavNodeRenderer'}],
            listeners: {itemclick: 'onMenuTreeItemClick'},
            buttons:[{xtype:"treefilterfield",width:'100%',emptyText: '搜索菜单',autoFilter:true,dataIndex:'text'}]
		};
		var favoritesPanel = {xtype:'treepanel',layout: 'fit',title:"收藏菜单",useArrows: true,hideHeaders: true,
            store:{autoLoad:true,url:'platform/systemcfg/getmenulist.do',params:{type:'01'},
            	listeners: {
            		load: function(thiz, records, successful, eOpts){
            			Ext.SystemFavorites = {};
            			Ext.each(records,function(record){
            				Ext.SystemFavorites[record.data.id] = record.data;
            			});
            		}
            	}
            },
            columns: [{xtype: 'treecolumn',flex: 1,dataIndex: 'text',scope: 'controller', renderer: 'treeNavNodeRenderer'}],
            listeners:{itemcontextmenu:'onFavoritesContextmenu',itemclick: 'onMenuTreeItemClick'},
            buttons:[{xtype:"treefilterfield",width:'100%',emptyText: '搜索菜单',autoFilter:true,dataIndex:'text'}]
		};
	    var westPanel = Ext.create("Ext.panel.Panel",{region:'west',layout: 'fit',title:"导航菜单",width: 250,minWidth: 250,collapsible: true,split: true,tools:tools});
    	me.items = [northPanel,westPanel,maintabpanel];
	    me.updateMenuStyle(westPanel,systemPanel,favoritesPanel,type);
    	me.callParent();
    },
    
    /**
     * 更新菜单风格
     * @param {} type 0=卡片式菜单（tabPanel），1=切换式菜单（accordion布局）
     */
    updateMenuStyle:function(westPanel,systemPanel,favoritesPanel,type){
    	westPanel.removeAll(true);
    	switch(type){
    		case 0: {westPanel.add({xtype:'tabpanel',tabPosition:'left',items:[systemPanel,favoritesPanel]});break;}
    		case 1: {westPanel.add({xtype:'panel',layout:'accordion',items:[systemPanel,favoritesPanel]});break;}
    	}
    },
    
    /**
     * 获取当前活动panel
     * @param {} westPanel
     * @param {} type
     * @return {}
     */
    getHandleTreePanel:function(westPanel,type){
    	var item = westPanel.items.items[0];
    	var panel = null;
    	switch(type){
    		case 0:{panel = item.getActiveTab();break;}
    		case 1:{panel = item.getLayout().getExpanded()[0];break;}
    	}
    	return panel;
    }
});