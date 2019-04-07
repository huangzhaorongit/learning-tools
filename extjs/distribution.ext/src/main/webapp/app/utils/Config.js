
/**
*项目基础配置信息
*/
Ext.define('app.utils.Config', {
	 alternateClassName: 'cfg', //设置别名
	 statics: {
	 	 systemname:'配网能效管理云平台',

	 	 xtypeLogin : 'login',

	 	 xtypeFrame : 'systemFrame',

	 	 resourcesPath: 'resources/',

	 	 /**用户信息*/
	 	 sub : {},

	 	 /**系统默认语言*/
	 	 language:'zh_CN',

	 	 /**系统默认主题风格*/
	 	 theme:'03',

	 	 /**跨域请求*/
	 	 crossdomain : false,

	 	 /**跨域url*/
	 	 requestUrl : "http://127.0.0.1:8080"
	 }
});
