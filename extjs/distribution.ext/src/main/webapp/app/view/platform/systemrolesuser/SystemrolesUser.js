Ext.define('app.view.platform.systemrolesuser.SystemrolesUser',{
	extend: 'Ext.panel.Panel',
	layout: 'fit',
	alternateClassName: 'systemRolesUser',
	xtype: 'systemRolesUser',
	controller: 'systemRolesUser',
	requires:[
		'app.controller.platform.systemrolesuser.SystemrolesUserController'
	],
	layout:{type:'hbox',pack:'start',align:'stretch'},
	bodyPadding:0,
	defaults:{flex:1,frame:false,border:true,margin: '0 1 0 0'},
	initComponent:function(){
		var me = this;
		var group1 =  this.id+'group1',
		group2 = this.id+'grouo2';
		var usercolunms = [
			{text: '序  号',sortable:false,width:60,xtype: 'rownumberer',align: 'center'},
		   	{text: '用户编号',width:120,sortable: true,dataIndex:'usercode',align: 'left',flex: 1},
		   	{text: '用户名称',width:120,sortable: true,dataIndex:'username',align: 'left',flex: 1}
		];
		var	rolecolunms = [
			{text: '序  号',sortable:false,width:60,xtype: 'rownumberer',align: 'center'},
		   	{text: '角色编号',width:120,sortable: true,dataIndex:'rolecode',align: 'left',flex: 1},
		   	{text: '角色名称',width:120,sortable: true,dataIndex:'rolename',align: 'left',flex: 1}
		];
		Ext.apply(me, {
			items:[{	
					title: '角色信息',flex: 1, xtype: 'grid',reference:'rolePanel',paging:true,region: 'center',
					store: {storeId:'rolestore',autoLoad:true,url:"platform/systemuserroles/getrolelist.do"},
					listeners: {selectionchange:'onSelectionchange'},
					tools: [
				        {xtype:'searchfield',emptyText: '角色名称/编号',paramName:'rolename',hasSearch:true,store: "rolestore"}
				    ],
		            columns: rolecolunms
		    },{		
		   			viewConfig: {
		            	scope:this,plugins: {ptype: 'gridviewdragdrop',dragGroup: group1,dropGroup: group2},
			            listeners:{beforedrop:function(node, data, overModel, dropPosition, dropHandlers){return true;},drop:"gridDropYes"}
	    			},
		    		itemId: 'grid1',title:'已关联的用户',flex: 1, xtype: 'grid',reference:'roleyespanel',paging:true,region: 'center',
					store: {storeId:'userstoreyes',autoLoad:true,url:"platform/systemuserroles/getuseryeslist.do"},
					tools: [
						{xtype:'searchfield',emptyText: '用户名称/编号',paramName:'username',hasSearch:true,store: "userstoreyes"}
				    ],
		            columns: usercolunms
		    },{ 	
		    	 	viewConfig: {scope:this,plugins: {ptype: 'gridviewdragdrop',dragGroup: group2,dropGroup: group1},
			            listeners:{beforedrop:function(node, data, overModel, dropPosition, dropHandlers){return true},drop:"gridDropNo"}
			        },
		    	 	itemId: 'grid2',title: '无关联的用户',flex: 1,xtype: 'grid',reference:'rolenopanel',paging:true,
		      		store: {storeId:'userstoreno',autoLoad:true,url:"platform/systemuserroles/getusernolist.do"},
		      		tools: [
						{xtype:'searchfield',emptyText: '用户名称/编号',paramName:'username',hasSearch:true,store: "userstoreno"}
				    ],
		            columns: usercolunms
		    }]    
		})
		this.callParent();
	}
    
});