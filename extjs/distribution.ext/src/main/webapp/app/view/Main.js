Ext.define('app.view.Main', {
	extend : 'Ext.container.Viewport',
	alternateClassName : 'main',
	xtype : 'main',
	requires : ['app.controller.MainController',
			'app.view.platform.login.Login',
			'app.view.platform.login.LockScreen'],
	controller : 'main',
	layout: 'card',
	items: [
		 {layout:'fit'}, //主窗口
         {xtype:'lockscreen'} //锁屏窗口
	]
});