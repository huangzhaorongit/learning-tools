/**
 * 主窗口TabPanel
 * Ext.SystemTabPanelAutoOpens  当前业务Panel面板中TabPanel对象自动打开菜单窗口Map对象{menuid:obj}
 */
Ext.define('app.view.platform.frame.default.TabPanel', {
	extend : 'Ext.tab.Panel',
    alternateClassName: 'frame.default.TabPanel',
	xtype : 'maintabpanel',
	region: 'center',
    style:'background-color:red',
	reference: 'contentPanel',
    tabPosition:'top',
    enableTabScroll:true,
    systemcfg:{type:'02'},
	initComponent : function() {
		var me = this;
		var tabmenu = me.plugins = new Ext.create('Ext.ux.TabCloseMenu',{
            extraItemsTail:['-',{
				text : '可关闭',
				itemId : 'canclose',
				iconCls:'x-fa fa-unlock-alt',
				checked : true,
				hideOnClick : false,
				handler : function(item) {
					tabmenu.tab.setClosable(item.checked);
					tabmenu.menu.close.setDisabled(!item.checked);
					item.setIconCls(item.checked?'x-fa fa-unlock-alt':'x-fa fa-lock');
				}
			},'-',{
                text: "登录时自动打开",
				itemId : 'autoopen',
                checked : false,
                iconCls:'x-fa fa-eye-slash',
				hideOnClick : false,
                handler: function(item){
					var params = {value:tabmenu.item.menuid,type:'02'};
                	if (item.checked){
                		EU.RScfgSave({params:params},function(){
							Ext.SystemTabPanelAutoOpens[tabmenu.item.menuid] = tabmenu.item;
		        			item.setIconCls("x-fa fa-eye");
						});
                	}else{
						EU.RScfgDel({params:params},function(){
							delete Ext.SystemTabPanelAutoOpens[tabmenu.item.menuid];
		        			item.setIconCls("x-fa fa-eye-slash");
						});
                	}
                }
            },'-',{
                text: "收藏菜单",
				itemId : 'favorites',
                checked : false,
                iconCls:'x-fa fa-heart',
				hideOnClick : false,
                handler: function(item){
					var params = {value:tabmenu.item.menuid,type:'01'};
					if (item.checked){
						EU.RScfgSave({params:params},function(){
							Ext.SystemFavorites[tabmenu.item.menuid] = tabmenu.item;
							item.setIconCls("x-fa fa-heart");
						});
					}else{
						EU.RScfgDel({params:params},function(){
							delete Ext.SystemFavorites[tabmenu.item.menuid];
							item.setIconCls("x-fa fa-heart-o");
						});
					}
                }
            }],
            listeners : {
				beforemenu : function(menu, tabPanel,tabmenu, tab) {
		        	menu.canclose.setChecked(tab.closable);
					menu.canclose.setIconCls(tab.closable?'x-fa fa-unlock-alt':'x-fa fa-lock');
		        	//自动打开
		        	var isAutoOpen = Ext.isObject(Ext.SystemTabPanelAutoOpens[tabmenu.item.menuid]);
		        	menu.autoopen.setChecked(isAutoOpen);
		        	menu.autoopen.setIconCls(isAutoOpen?"x-fa fa-eye":"x-fa fa-eye-slash");
		        	//收藏
		        	var isFavorites = Ext.isObject(Ext.SystemFavorites[tabmenu.item.menuid]);
		        	menu.favorites.setChecked(isFavorites);
		        	menu.favorites.setIconCls(isFavorites?"x-fa fa-heart":"x-fa fa-heart-o");
				}
            }
        });
		this.callParent();
	}
});