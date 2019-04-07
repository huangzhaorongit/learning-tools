Ext.define('app.view.platform.basecodetype.BaseCodeType',{
	extend: 'Ext.panel.Panel',
	alternateClassName: 'baseCodeType',
	xtype: 'baseCodeType',
	requires:[
		'app.controller.platform.basecodetype.BaseCodeTypeController',
		'app.view.platform.basecodetype.BaseCodeTypeEdit',
		'Ext.ux.ProgressBarPager'
	],
	controller: 'basecodetype',
    referenceHolder: true,
    layout: 'border',
    items:[
    	{
        	xtype: 'gridpanel',reference:'gridPanel',paging:true,region: 'center',
	        store:{storeId:'basestore',autoLoad:true,url:'platform/basecodetype/getbasecodetypelist.do'},
            listeners: {selectionchange:'onSelectionchange',rowdblclick: 'onModuledblClick'},
            tbar: [
            	{text: '新增',iconCls:'x-fa fa-plus',cls:'active',handler: 'onAdd'}, 
		    	{text: '修改',iconCls:'x-fa fa-pencil-square-o',handler: 'onUpdate'}, 
		    	{text: '删除',iconCls:'x-fa fa-trash',handler: 'onDelete'},
		    	{xtype:'searchfield',fieldLabel: '试图名称/分类',emptyText: '视图名称/分类',width:320,paramName:'viewname',hasSearch:true,store: "basestore"},
		    	"->",
		    	{cls: 'delete-focus-bg',iconCls:'x-fa fa-refresh',handler: 'onRefresh',tooltip : '刷新数据'}
		    ],
            columns: [
            	{text: '序  号',sortable: false,width: 60,xtype: 'rownumberer',align: 'center'},
	            {text: '分类说明',flex: 1, sortable: true,dataIndex: 'coderemark'},
	            {text: '关联视图名称',flex: 1,sortable: true,dataIndex: 'viewname',align: 'left'},
	            {text: '备注',flex: 1,dataIndex: 'remark',sortable: true,align: 'center'},
	            {text: '是否有效',flex: 1,dataIndex: 'isvalid',sortable: true,align: 'center',renderer:function(value, metaData,record){
	            	var cls = value=='1'?"x-fa fa-check-square":"x-fa fa-minus-square"
	            	var color = value=='1'?"#048D70":"red"
	            	return "<spne class='"+cls+"' style='font-size:18px;color:"+color+";'/>";
	            }}
	        ]
    	},
    	{
    		region: 'east',title:"数据明细",reference:'detailPanel',collapsible: true,collapsed :true,split: true,border: true,width: 500,layout: 'fit',
    		items:[
    			{xtype:"basecodetypeedit",reference:'basecodetypeEditInto',viewtype :'display'}
	        ]
    	}
    ]                
});