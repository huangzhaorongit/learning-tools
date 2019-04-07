Ext.define('app.view.platform.systemrole.SystemRole',{
	extend: 'Ext.panel.Panel',
	alternateClassName: 'systemRole',
	xtype: 'systemRole',
	requires: [
		'app.controller.platform.systemrole.SystemRoleController',
		'app.view.platform.systemrole.SystemRoleEdit'
	],
	controller: 'systemRole',
	layout: 'fit',
	referenceHolder: true,
	items:[
    	{
        	xtype: 'grid',reference:'gridPanel',paging:true,region: 'center',
	        store: {storeId:'rolestore',autoLoad:true,url:'platform/systemrole/getlist.do'},
            listeners: {rowdblclick: 'onModuledblClick'},
            selType: 'checkboxmodel',
            tbar: [
            	{text: '新增',iconCls:'x-fa fa-plus',cls:'active',handler: 'onAdd'}, 
		    	{text: '修改',iconCls:'x-fa fa-pencil-square-o',handler: 'onUpdate'}, 
		    	{text: '删除',iconCls:'x-fa fa-trash',handler: 'onDelete'},
		    	{xtype:'searchfield',emptyText: '角色名称/编号',paramName:'rolename',hasSearch:true,store: "rolestore"},
		    	"->",
		    	{cls: 'delete-focus-bg',iconCls:'x-fa fa-refresh',handler: 'onRefresh',tooltip : '刷新数据'}
		    ],
            columns: [
            	{text: '序  号',sortable: false,width: 60,xtype: 'rownumberer',align: 'center'},
	            {text: '角色编号',flex: 1, sortable: true,dataIndex: 'rolecode'},
	            {text: '角色名称',flex: 1,sortable: true,dataIndex: 'rolename',align: 'center'},
	            {text: '角色描述',flex: 3,dataIndex: 'roledescription',sortable: true,align: 'center'},
	            {text: '是否有效',flex: 1,dataIndex: 'isvalid',sortable: true,align: 'center',renderer:function(value, metaData,record){
	            	var cls = value=='1'?"x-fa fa-check-square":"x-fa fa-minus-square"
	            	var color = value=='1'?"#048D70":"red"
	            	return "<spne class='"+cls+"' style='font-size:18px;color:"+color+";'/>";
	            }}
	        ]
    	}
    ]                
});