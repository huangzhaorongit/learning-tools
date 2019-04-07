Ext.define('app.view.platform.systemmodule.SystemModule', {
    extend: 'Ext.panel.Panel',
    alternateClassName: 'systemModule',
    xtype: 'systemModule',
    requires: [
        'app.controller.platform.systemmodule.SystemModuleController',
        'app.view.platform.systemmodule.SystemModuleEdit'
    ],
    controller:'systemModule',
    referenceHolder: true,
    layout: 'border',
    items:[
    	{
            xtype: 'treepanel',reference:'treePanel',region: 'center', rootVisible:false,
            store:{autoLoad:true,defaultRootId: "00",url:'platform/systemmodule/gettree.do'},
            listeners: {selectionchange:'onSelectionchange',rowdblclick: 'onModuledblClick'},
            tbar: [
            	{text: '新增子节点',iconCls:'x-fa fa-plus',cls:'active',handler: 'onAdd',type:0,xtype : 'splitbutton',
				 	menu : {items : [{text : '新增根节点',iconCls:'x-fa fa-home',tooltip : '新增第一级目录',handler: 'onAdd',type:1}]}
		    	}, 
		    	{text: '修改',iconCls:'x-fa fa-pencil-square-o',handler: 'onUpdate'}, 
		    	{text: '删除',iconCls:'x-fa fa-trash',handler: 'onDelete'}, 
		    	"->",
		    	{cls: 'delete-focus-bg',iconCls:'x-fa fa-folder-open',handler: 'onExpandAll',tooltip : '全部展开'},
		    	{cls: 'delete-focus-bg',iconCls:'x-fa fa-folder',handler: 'onCollapseAll',tooltip : '全部收缩'},
		    	{cls: 'delete-focus-bg',iconCls:'x-fa fa-refresh',handler: 'onRefresh',tooltip : '刷新数据'}
		    ],
            columns: [
	            {xtype: 'treecolumn',text: '模块名称',flex: 3, sortable: true,dataIndex: 'modulename'},
	            {text: '模块编号',flex: 1,sortable: true,dataIndex: 'modulecode'},
	            {xtype:'viewcolumn',text: '模块类型',flex: 1,sortable: true,dataIndex: 'moduletype',viewname:'v_moduletype',align: 'center'},
	            {text: '模块地址',flex: 2,dataIndex: 'moduleurl',sortable: true},
	            {text: '是否有效',flex: 1,dataIndex: 'isdisplay',sortable: true,align: 'center',renderer:function(value, metaData,record){
	            	var cls = value=='1'?"x-fa fa-check-square":"x-fa fa-minus-square"
	            	var color = value=='1'?"#048D70":"red"
	            	return "<spne class='"+cls+"' style='font-size:18px;color:"+color+";'/>";
	            }}
	        ]
    	},
    	{
    		region: 'east',title:"数据明细",reference:'detailPanel',collapsible: true,collapsed :true,split: true,border: true,width: 400,layout: 'fit',
	        items:[
	        	{xtype:"systemModuleEdit",reference:'moduleinfo',viewtype :'display'}
	        ]
    	}
    ]
});