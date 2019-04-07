Ext.define('app.view.platform.frame.dashboard.Main', {
    extend: 'Ext.panel.Panel',
    xtype: 'dashboardThene',
    requires: [
        'app.controller.platform.frame.dashboard.MainController',
        'app.view.platform.frame.default.SkinSelect'
    ],
    layout: {type: 'vbox', align: 'stretch'},
    controller:'dashboardThemeController',
    cls: 'sencha-dash-viewport',
    initComponent : function() {
    	var me = this;
    	var toolbar = Ext.create("Ext.toolbar.Toolbar",{height: 64,cls:'sencha-dash-dash-headerbar toolbar-btn-shadow',items: [
		        {xtype: 'component',reference: 'senchaLogo',cls: 'sencha-logo',width: 250,html: '<div class="main-logo"><img src="app/view/platform/frame/dashboard/resources/images/company-logo.png">Sencha</div>'},
                {margin: '0 0 0 8',cls: 'delete-focus-bg',iconCls:'x-fa fa-navicon',handler: 'onToggleNavigationSize'},
                {xtype:'label',cls: 'delete-focus-bg',html:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="letter-spacing:2mm;font-size:24px; color:#4D4D4D;" class="x-fa fa-th"><b>'+cfg.systemname+'</b></span>(Ver:6.0.2015.10.10) '},
                {xtype: 'tbspacer',flex:1},
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
    	);
    	me.items = [toolbar];
    	Loader.request("app/view/platform/frame/dashboard/resources/style.css",function(){
    		me.initView();//css加载完成后才初始化组件
    	});
    	me.callParent();
    },
    
    initView : function(){
    	var me = this,height = Ext.Element.getViewportHeight() - 64;
    	var navTree = Ext.create("Ext.list.Tree",{reference: 'navigationTreeList',ui: 'navigation',style:'min-height:'+ height + 'px',
            store:{autoLoad:true,proxy : {type:'ajax',url:'platform/systemframe/getmenutree.do',reader: {type: 'json'}}},
            width: 250,expanderFirst: false,expanderOnly: false,listeners: {selectionchange: 'onNavigationTreeSelectionChange'}
    	});
    	var mainCardPanel = Ext.SystemTabPanel = Ext.create("Ext.container.Container",{flex: 1, reference: 'mainCardPanel',cls: 'sencha-dash-right-main-container',
            layout: {type: 'card',anchor: '100%'}
        });
    	var maincontainerwrap = Ext.create("Ext.container.Container",{scrollable: 'y',flex: 1,minHeight:height,
		    layout: {type: 'hbox',align: 'stretchmax',animate: true,animatePolicy: {x: true,width: true}},
			reference: 'mainContainerWrap',style:'background: #f6f6f6;',items: [navTree,mainCardPanel],
		    listeners:{
		    	resize :function( thiz, width, height, oldWidth, oldHeight, eOpts ){
		    		var me = this,height = Ext.Element.getViewportHeight() - 64;
			        me.minHeight = height;
			        navTree.setStyle({'min-height': height + 'px'});
		    	}
		    }
		});
    	me.add(maincontainerwrap);
    }
});