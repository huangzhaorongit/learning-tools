Ext.define('app.view.platform.systemlog.SystemLog',{
	extend: 'Ext.tab.Panel',
	alternateClassName: 'systemLog',
	xtype: 'systemLog',
	requires:[
		'app.controller.platform.systemlog.SystemLogController',
		'app.view.platform.systemlog.SystemOperateEdit',
		'app.view.platform.systemlog.SystemExceptionEdit'
	],
	controller: 'systemLog',
	referenceHolder: true,
    layout: 'fit',
    	items:[{
    		xtype: 'grid',title: '系统操作',iconCls:"x-fa fa-gear",paging:true,reference: 'systemOperate',
	        store: {storeId:'logstore1',autoLoad:true,url:'platform/systemlog/getsystemoperate.do'},
            listeners: {rowdblclick: 'onModuledblClick'},
            tbar: [
		    	{xtype:'searchfield',fieldLabel: '操作信息/操作人',emptyText: '操作信息/操作人',width:320,paramName:'description',hasSearch:true,store: "logstore1"},
		    	{xtype: 'datefield',fieldLabel:'开始时间',labelWidth:60,width:220,name:'startDate',reference:'startDate',maxValue: new Date(),format:'Y-m-d'},
		    	{xtype: 'datefield',fieldLabel:'结束时间',labelWidth:60,width:220,name:'endDate',reference:'endDate',maxValue: new Date(),format:'Y-m-d'},
		    	{text: '查询',iconCls:'x-fa fa-search',handler:'querylog'},
		    	"->",
		    	{cls: 'delete-focus-bg',iconCls:'x-fa fa-refresh',handler: 'onRefresh',tooltip : '刷新数据'}
		    ],
            columns: [
            	{text: '序  号',sortable: false,width: 60,xtype: 'rownumberer',align: 'center'},
	            {text: '操作信息',width: 200,sortable: true,dataIndex: 'description',qcfg:{type:'string'}},
	            {text: '操作人',width: 100,sortable: true,dataIndex: 'creater',align: 'center'},
	            {text: '操作方法',minWidth:400,flex: 1,dataIndex: 'method',sortable: true},
	            {text: '请求IP',width: 150,dataIndex: 'ip',sortable: true,align: 'center'},
	            {text: '操做时间',width: 150,dataIndex: 'createdate',sortable: true,align: 'center'}
	        ]
    	},{
    		xtype: 'grid',title:'系统异常',iconCls:"x-fa fa-times-circle-o",paging:true,reference: 'systemException',
    		store: {storeId:'logstore2',autoLoad:true,pageSize:10,url:'platform/systemlog/getsystemexception.do'},
            listeners: {rowdblclick: 'onModuledblClick'},
            tbar: [
		    	{xtype:'searchfield',emptyText: '操作信息/操作人',paramName:'description',hasSearch:true,store: "logstore2"},
		    	"->",
		    	{cls: 'delete-focus-bg',iconCls:'x-fa fa-refresh',handler: 'onRefresh',tooltip : '刷新数据'}
		    ],
            columns: [
            	{text: '序  号',sortable: false,width: 60,xtype: 'rownumberer',align: 'center'},
	            {text: '操作信息',width: 200, sortable: true,dataIndex: 'description',qcfg:{type:'string'}},
	            {text: '操作人', width: 100,sortable: true,dataIndex: 'creater',align: 'center'},
	            {text: '操作方法',width: 400,dataIndex: 'method',sortable: true},
	            {text: '错误信息',width: 100,dataIndex: 'exceptiondetail',sortable: true},
	            {text: '异常代码',minWidth:300,flex:1,dataIndex: 'exceptioncode',sortable: true},
	            {text: '参数',width: 150,dataIndex: 'params',sortable: true,align: 'center',hidden:true},
	            {text: '请求IP',width: 150,dataIndex: 'ip',sortable: true,align: 'center'},
	            {text: '操作时间',width: 150,dataIndex: 'createdate',sortable: true,align: 'center'}
	        ]
    	}]
})