Ext.define('app.view.platform.systemmenu.SystemMenu', {
    extend: 'Ext.panel.Panel',
    alternateClassName: 'systemMenu',
    xtype: 'systemMenu',
    requires: [
        'app.controller.platform.systemmenu.SystemMenuController',
        'app.view.platform.systemmenu.SystemMenuEdit'
    ],
    controller:'systemMenu',
    referenceHolder: true,
    layout: 'fit',
    items:[
    	{
            xtype: 'treepanel',reference:'treePanel',region: 'center',rootVisible:false,selFirstNode:false,
            store:{autoLoad:true,url:"platform/systemmenu/gettree.do"},
            listeners:{rowcontextmenu:'onRowContextmenu',rowdblclick: 'onMenudblClick'},
		    dockedItems: [{
                dock: (Ext.isEmpty(local.get('system_menu_dock'))?'left':local.get('system_menu_dock')),
                xtype: 'toolbar',
                weight:40,
                items: [{iconCls: "x-fa fa-folder-open",xtype: 'button',handler: 'onExpandAll',tooltip : '全部展开'}, 
                		{iconCls: "x-fa fa-folder",xtype: 'button',handler: 'onCollapseAll',tooltip : '全部收缩'}, 
                		{iconCls: "x-fa fa-refresh",xtype: 'button',handler: 'onRefresh',tooltip : '刷新数据'}, 
                		'-',
                		{iconCls: "x-fa fa-pencil-square-o",xtype: 'button',handler: 'onUpdate',tooltip : '修改数据'},
                		{iconCls: "x-fa fa-trash",xtype: 'button',handler: 'onDelete',tooltip : '删除数据'},
                		'->',
                		{iconCls: "x-fa fa-arrows",xtype: 'button',handler: 'onChangeDock',tooltip : '显示位置'}]
            }],
            viewConfig: {
            	scope:this,
                plugins: {
                    ptype: 'treeviewdragdrop'
                },
	            listeners:{
	            	beforedrop:function(node, data, overModel, dropPosition, dropHandlers){
	            		return !(dropPosition=='append' &&　overModel.data.menutype != '00');
	            	},
	            	drop:"treeDrop"
	            }
            },
            columns: [
	            {text:'菜单名称',flex: 3,xtype: 'treecolumn', sortable: true,dataIndex: 'menuname'},
	            {text: '关联模块',flex: 1,sortable: true,dataIndex: 'modulename',align: 'center'},
	            {xtype:'viewcolumn',text: '菜单类型',flex: 1,sortable: true,dataIndex: 'menutype',viewname:'v_menutype',align: 'center'},
	            {text: '显示图标',flex: 1,sortable: true,dataIndex: 'iconCls',align: 'center',renderer:function(value, metaData,record){
	            	var color = record.get("iconColor") || '5FA2DD';
	            	return "<spne class='"+value+"' style='font-size:18px;color:#"+color+";'/>";
	            }},
	            {text: '是否有效',flex: 1,dataIndex: 'isdisplay',sortable: true,align: 'center',renderer:function(value, metaData,record){
	            	var cls = value=='1'?"x-fa fa-check-square":"x-fa fa-minus-square"
	            	var color = value=='1'?"#048D70":"red"
	            	return "<spne class='"+cls+"' style='font-size:18px;color:"+color+";'/>";
	            }}
	        ]
    	}
    ]
});