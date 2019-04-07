Ext.define('app.view.platform.systemrolelimits.SystemroleLimits',{
	extend: 'Ext.panel.Panel',
	alternateClassName: 'systemroleLimits',
	xtype: 'systemroleLimits',
	requires:[
		'app.controller.platform.systemrolelimits.SystemroleLimitsController'
	],
	controller: 'systemroleLimits',
    referenceHolder: true,
    layout: 'border',
    items:[
    	{
            xtype: 'gridpanel',reference:'gridPanel',region: 'center',id:'rgpanel',
            listeners: {rowclick: 'gridclick'},
            tbar: [
		    	{xtype:'searchfield',fieldLabel: '角色编号/名称',emptyText: '角色编号/名称',paramName:'rolecode',hasSearch:true,width:300,store: "gridStore"}
		    ],
            store:{storeId:"gridStore",autoLoad:true,url:'platform/systemrolelimits/getsystemrole.do'},
            columnLines:true,
            columns: [
                {text: '序 号',sortable: false,width:60,xtype: 'rownumberer',align: 'center'},
            	{text: '角色编号',sortable: true,flex:1,dataIndex: 'rolecode',align: 'left'},
	            {text: '角色名称',flex:1,sortable: true,dataIndex: 'rolename',align: 'center'},
	            {text: '是否有效',flex:1,dataIndex: 'isvalid',sortable: true,align: 'center',renderer:function(value, metaData,record){
	            	var cls = value=='1'?"x-fa fa-check-square":"x-fa fa-minus-square"
	            	var color = value=='1'?"#048D70":"red"
	            	return "<spne class='"+cls+"' style='font-size:18px;color:"+color+";'/>";
	            }},
	            {text: '角色描述',flex: 3,sortable: true,dataIndex: 'roledescription',align: 'center'}
	        ]
    	},
    	{
    		region: 'east',
    		title:"系统模块",
    		xtype:'treepanel',
    		collapsible: true,
    		collapsed :false,
		    tools:[{
				type : 'expand',
				handler : function(event, toolEl, panelHeader) {
					this.up('treepanel').collapseAll();
				}
			}, {
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
    			change:false,
    			reference:'saveBtn',
    			handler:'saveLimit'
    		}],
    		split: true,
    		border: true,
    		width: 400,
    		layout: 'fit',
    		reference:'treePanel',
    		columnLines:true,
    		viewtype :'display',
    		store:{
  	        	autoLoad:true,
  	          	url:'platform/systemrolelimits/getmodultree.do'
  	        },
  	        listeners:{checkchange:'checkchange'}
    	}
    ]
});