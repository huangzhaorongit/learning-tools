Ext.define('app.view.platform.systemuser.SystemUser', {
    extend: 'Ext.panel.Panel',
    alternateClassName: 'systemUser',
    xtype: 'systemUser',
    requires: [
        'app.controller.platform.systemuser.SystemUserController',
        'app.view.platform.systemuser.SystemUserEdit'
    ],
    controller:'systemUser',
    layout: 'fit',
    items:[{
		xtype: 'grid',reference:'gridPanel',selType: 'checkboxmodel',paging:true,
      	listeners: {rowdblclick: 'rowdblclick'},
		store:{storeId:'gridStore',autoLoad:true,url:"platform/systemuser/getlist.do"},
		tbar: [
	    	{text: '添加',reference:'add',iconCls:'x-fa fa-plus',cls:'active',handler: 'onAdd'}, 
	    	{text: '修改',reference:'update',iconCls:'x-fa fa-pencil-square-o',handler: 'onUpdate'}, 
	    	{text: '删除',reference:'delete',iconCls:'x-fa fa-trash',handler: 'onDelete'},
   	    	{xtype:'searchfield',emptyText: '名称/编号',paramName:'usercode',hasSearch:true,width:200},
   	    	{xtype:'btngridquery',text: '高级查询',iconCls:'x-fa fa-search'}, 
	    	"->",
	    	{cls: 'delete-focus-bg',iconCls:'x-fa fa-refresh',handler: 'onRefresh',tooltip : '刷新数据'}
	    ],
        columns: [
              	{text: '序  号',sortable:false,width:60,xtype: 'rownumberer',align: 'center'},
   				{text: '用户代码',flex: 2,sortable: true,dataIndex:'usercode',align: 'left',qcfg:{type:'string'}},
   				{text: '用户姓名',flex: 2,sortable: true,dataIndex:'username',align: 'center',qcfg:{type:'string'}},
   				{text: '所属公司',width: 200,sortable: true,dataIndex:'companyname',align: 'center',qcfg:{type:'string'}},
   				{text: '创建者',flex: 2,sortable: true,dataIndex:'creater',align: 'center'},
   				{text: '创建时间',width: 150,sortable: true,dataIndex:'createdate',align: 'center'},
   				{text: '最后修改人',flex: 2,sortable: true,dataIndex:'lastmodifier',align: 'center'},
   				{text: '最后修改时间',width: 150,sortable: true,dataIndex:'lastmodifydate',align: 'center'},
   				{text: '是否有效',width: 80,dataIndex: 'isvalid',sortable: true,align: 'center',viewname:'v_is_no',qcfg:{type:'combobox'},renderer:function(value, metaData,record){
   						var cls = value=='1'?"x-fa fa-check-square":"x-fa fa-minus-square"
   						var color = value=='1'?"#048D70":"red"
   						return "<spne class='"+cls+"' style='font-size:18px;color:"+color+";'/>";
   	           	}},
   				{text: '是否锁定',width: 80,dataIndex: 'islocked',sortable: true,align: 'center',viewname:'v_is_no',qcfg:{type:'combobox'},renderer:function(value, metaData,record){
					var cls = value=='1'?"x-fa fa-check-square":"x-fa fa-minus-square"
					var color = value=='1'?"#048D70":"red"
					return "<spne class='"+cls+"' style='font-size:18px;color:"+color+";'/>";
           		}}
        ]
    }]
});