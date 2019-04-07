/**
 * Ext.Panel					主窗口ViewController对象
 * Ext.Viewport   	 			主窗口Viewport
 * Ext.FramePanel   			业务Panel面板
 * Ext.lockPanel     			锁屏Panel面板
 * Ext.SystemTabPanel       	当前业务Panel面板中TabPanel对象
 * Ext.SystemTabPanelAutoOpens  当前业务Panel面板中TabPanel对象自动打开菜单窗口Map对象{menuid:obj}  //系统菜单加载时候提取数据
 * Ext.SystemFavorites  		我的收藏菜单Map对象{menuid:obj}   //系统菜单加载时候提取数据
 * Ext.SystemLimits             我的界面权限Map对象  //执行PU.openTabModule时候创建新的tab，判断当前界面modelurl在SystemLimits是否存在，如果没有且加载并且保存在SystemLimits中
 */
Ext.define('app.Application', {
    extend: 'Ext.app.Application',
    
    name: 'app',

    stores: [
       
    ],
    
    requires: [
        'app.utils.Loader', 
        'app.utils.ExtUtils', 
        'app.utils.ExtFactory', 
        'app.utils.ProjectUtils',
        'app.utils.WebSocket',
        
    	'app.expand.ux.FormPanel',
        'app.expand.ux.GridIndex',
        'app.expand.ux.SelectField',
        'app.expand.ux.IconClsField',
        'app.expand.ux.BtnGridQuery',
        'app.expand.ux.TreeFilterField',
        'app.expand.ux.UploadField',
        'app.expand.ux.DateTimeField',
        
        'app.expand.widget.ColorColumn',
        'app.expand.widget.ViewColumn',
        'app.expand.widget.RadioColumn',
        'app.expand.plugin.PageRequest',
        'app.expand.trigger.Clear'
    ],
    
    launch: function () {
		Ext.get("loading").remove();
		Ext.get("progress").remove();
    },

    onAppUpdate: function () {
        PU.onAppUpdate();
    }
});
