Ext.define('app.view.platform.organization.Organization', {
    extend: 'Ext.panel.Panel',
    xtype: 'organization',
    alternateClassName: 'organization',
    requires: [
    	'app.controller.platform.organization.OrganizationController',
    	'app.view.platform.organization.OrganizationEdit'
    ],
    controller: 'organization',
    layout: 'fit',
    referenceHolder: true,
   	items:[{
   		 xtype: 'treepanel',reference:'treePanel',region: 'center',rootVisible:false,
   		 store:{autoLoad:true,url:"platform/organization/getOrganizationTree.do"},
   		 listeners:{rowcontextmenu:'onRowContextmenu',rowdblclick: 'onMenudblClick'},
   		 dockedItems: [{
                dock: (Ext.isEmpty(local.get('system_organization_dock'))?'left':local.get('system_organization_dock')),
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
	            		return true;
	            	},
	            	drop:"treeDrop"
	            }
            },
            columns: [
	            {text: '组织名称',iconCls: "fa fa-group",flex: 4,xtype: 'treecolumn', sortable: true,dataIndex: 'orgname'},
	            {text: '所属公司',flex: 2,sortable: true,dataIndex: 'companyname',align: 'center'},
	            {text: '组织类型',xtype: 'viewcolumn',flex: 1,sortable: true,dataIndex: 'orgtype',viewname: 'v_orgtype',align: 'center'},
	            {text: '电话',flex: 1,sortable: true,dataIndex: 'telephone',align: 'center'},
	            {text: '地址',flex: 2,sortable: true,dataIndex: 'address',align: 'center'},
	            {text: '负责人',flex: 1,sortable: true,dataIndex: 'manager',align: 'center'},
	            {text: '备注',flex: 2,sortable: true,dataIndex: 'remarks',align: 'center'},
	            {text: '是否有效',flex: 1,dataIndex: 'isvalid',sortable: true,align: 'center',renderer:function(value, metaData,record){
	            	var cls = value=='1'?"x-fa fa-check-square":"x-fa fa-minus-square"
	            	var color = value=='1'?"#048D70":"red"
	            	return "<spne class='"+cls+"' style='font-size:18px;color:"+color+";'/>";
	            }}
	        ]
   	}]
});