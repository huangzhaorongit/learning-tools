Ext.define('app.view.platform.personnel.Personnel', {
    extend: 'Ext.panel.Panel',
    alternateClassName: 'Personnel',
    xtype: 'personnel',
    requires: [
    	'app.controller.platform.personnel.PersonnelController',
    	'app.view.platform.personnel.PersonnelEdit'
    ],
    controller:'personnel',
    referenceHolder: true,
    layout: 'border',
    items:[
		{
    		xtype: 'treepanel',title:'系统部门',region:'west',layout:'fit',reference:'treePanel',
    	    split: true,collapsible: true,width:250,useArrows: true,
            store:{autoLoad:true,url:'platform/personnel/gettreelist.do'},
	        tools : [
		       	{type : 'expand',handler : function(event, toolEl, panelHeader) {
					 this.up('treepanel').collapseAll();
				}},
				{type : 'collapse',handler : function() {
					 this.up('treepanel').expandAll();
				}},
				{type : 'refresh',handler : 'refresh'}
			],
	        viewConfig: {  
	            plugins: {  
	                ptype: 'treeviewdragdrop',  
	                ddGroup:  'DragDropGroup',  
	                enableDrag : false //配置tree不允许拖动  
	            },  
	            listeners: {  
	                beforedrop:'beforedrop' 
	            }  
	        },
		    listeners: {
	            selectionchange:'treeSelectionchange'
			}
        },
        {
        	xtype:'panel',layout:'fit',region:'center',reference: 'contentPanel',
            items:[{xtype:'grid',paging:true ,reference: 'gridpanel',selType: 'checkboxmodel',
                store:{storeId:"gridStore",url:'platform/personnel/getlist.do',autoLoad:true,remoteSort:true},
                tbar: [
           	    	{xtype:'button',text: '添加',reference:'add',iconCls:'x-fa fa-plus',handler:'tbarclick',type:1},
           	    	{xtype:'button',text: '修改',reference:'update',iconCls:'x-fa fa-pencil-square-o',handler:'tbarclick',type:2},
           	    	{xtype:'button',text: '删除',reference:'delete',iconCls:'x-fa fa-trash',handler:'tbarclick',type:3},
           	    	{xtype:'searchfield',emptyText: '名称/编号',paramName:'personnelname',hasSearch:true,width:200,store: "gridStore"},
           	    	{xtype:'btngridquery',text: '高级查询',iconCls:'x-fa fa-search'}
           	    ],
       	        viewConfig: {  
	       	        plugins:{  
	       	            ptype: 'gridviewdragdrop',  
	       	            ddGroup:'DragDropGroup'//此处代表拖动的组 拖动组件与放置组件要同属一组才能实现相互拖放
	       	        }  
       	        },
        	    columns:[
    	            {text: '序  号',sortable:false,width:60,xtype: 'rownumberer',align: 'center'},
    		    	{text: '员工姓名',width: 100,dataIndex: 'personnelname',type:'key',qcfg:{type:'string'}},
    		    	{text: '员工编号',width: 100,dataIndex: 'personnelcode',align:'center',qcfg:{type:'string'}},
    		    	{text: '所属部门',width: 150,dataIndex: 'orgname',align:'left',qcfg:{type:'string'}},
    		        {text: '性  别',width: 60, dataIndex: 'sex',xtype:'viewcolumn',viewname:'v_sex',align:'center',qcfg:{type:'combobox'}},
    		    	{text: '出生年月',width: 100,dataIndex: 'birthdate',align:'center',qcfg:{type:'datetime'},xtype:'datecolumn',format:'Y-m-d'},
    		        {text: '电  话',width: 120,dataIndex: 'mobile',align:'center',qcfg:{type:'number'}},
    		    	{text: '邮  箱',width: 180,dataIndex: 'email',align:'left'},
    		    	{text: '职  位',width: 100,dataIndex: 'position',align:'center'},
    		    	{text: '是否有效',width: 80 ,dataIndex: 'isvalid',sortable: true,align: 'center',viewname:'v_is_no',qcfg:{type:'combobox'},renderer:function(value, metaData,record){
    	            	var cls = value=='1'?"x-fa fa-check-square":"x-fa fa-minus-square"
    	            	var color = value=='1'?"#048D70":"red"
    	            	return "<spne class='"+cls+"' style='font-size:18px;color:"+color+";'/>";
    	            }}
        	    ],
                listeners:{
            	  rowdblclick:'rowdblclick'
                }
            }]
   	     }
     ]
});