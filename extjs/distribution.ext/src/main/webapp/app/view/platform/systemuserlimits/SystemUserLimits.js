Ext.define('app.view.platform.systemuserlimits.SystemUserLimits',{
	extend: 'Ext.panel.Panel',
	alternateClassName: 'systemUserLimits',
	xtype: 'systemUserLimits',
	requires:[
		'app.controller.platform.systemuserlimits.SystemUserLimitsController'
	],
	controller: 'systemUserLimits',
    referenceHolder: true,
    layout: 'border',
    items:[
    	{
            xtype: 'gridpanel',reference:'gridPanel',region: 'center',
            listeners: {rowclick: 'gridclick'},
            tbar: [
		    	{xtype:'searchfield',fieldLabel: '用户编号/名称',emptyText: '用户编号/名称',paramName:'usercode',hasSearch:true,width:300,store: "gridStore"}
		    ],
            store:{storeId:"gridStore",autoLoad:true,url:'platform/systemuserlimits/getsystemuserlist.do'},
            columnLines:true,
            columns: [
                {text: '序 号',sortable: false,width:60,xtype: 'rownumberer',align: 'center'},
            	{text: '用户代码',sortable:true ,flex: 1,dataIndex: 'usercode',align: 'left'},
	            {text: '用户姓名',flex: 1, sortable: true,dataIndex: 'username',align: 'center'},
	            {text: '是否有效',flex: 1,dataIndex: 'isvalid',sortable: true,align: 'center',renderer:function(value, metaData,record){
	            	var cls = value=='1'?"x-fa fa-check-square":"x-fa fa-minus-square"
	            	var color = value=='1'?"#048D70":"red"
	            	return "<spne class='"+cls+"' style='font-size:18px;color:"+color+";'/>";
	            }},
	            {text: '是否锁定',flex: 1,dataIndex: 'islocked',sortable: true,align: 'center',renderer:function(value, metaData,record){
	            	var cls = value=='1'?"x-fa fa-check-square":"x-fa fa-minus-square"
	            	var color = value=='1'?"#048D70":"red"
	            	return "<spne class='"+cls+"' style='font-size:18px;color:"+color+";'/>";
	            }}
	        ]
    	},
    	{
    		region: 'east',
    		title:"系统模块",
    		xtype:'treepanel',
    		split: true,
    		collapsible: true,
    		border: true,
    		width: 400,
    		reference:'treePanel',
		    tools:[
	    		{
					type : 'expand',
					handler : function(event, toolEl, panelHeader) {
						this.up('treepanel').collapseAll();
					}
				},{
					type : 'collapse',
					handler : function() {
						this.up('treepanel').expandAll();
					}
				},{
					type : 'refresh',
					handler : function(event, toolEl, panelHeader) {
						this.up('treepanel').getStore().reload();
					}
				},
	    		{
	    			type:'save',
	    			tooltip: '保存',
	    			hidden : true,
	    			reference:'saveBtn',
	    			handler:'saveLimit'
	    		}
		    ],
    		store:{
  	            autoLoad:true,
  	            url:'platform/systemuserlimits/getmodultree.do'
  	        },
  	        listeners:{checkchange:'checkchange'}
    	}
    ]
});