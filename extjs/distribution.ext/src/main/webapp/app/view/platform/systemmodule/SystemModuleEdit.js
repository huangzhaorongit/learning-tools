Ext.define('app.view.platform.systemmodule.SystemModuleEdit', {
    extend: 'ux.form.Panel',
    alternateClassName: 'systemModuleEdit',
    xtype: 'systemModuleEdit',
    layout:'border',
    
    initComponent:function(){
    	var me = this;
    	var display = me.viewtype == 'display'; //只读
    	var moduleoperate  = {
    		xtype:"grid",title: '按钮配置',iconCls:"x-fa fa-th-list",reference:'moduleoperate',
            plugins: display?null:[{ptype:'cellediting',clicksToEdit:1}],
    		tbar:[{iconCls:'x-fa fa-plus-square',text:"添加操作代码",border:"0px",scope:this,hidden:display,handler:this.onGridAddhandler}],
    		store:{url:"platform/systemmodule/getoperatelist.do"},
    		columns:[
    			{text:'序号',sortable:false,width:60,xtype: 'rownumberer',align: 'center'},
    			{text:'操作代码',flex:1,dataIndex:'operatecode',align: 'left',editor:{}},
    			{text:'操作名称',flex:1,dataIndex:'operatename',editor:{}},
    			{text:'控制类型',flex:1,dataIndex:'controltype',xtype:'viewcolumn',viewname:'v_controltype',align:'center',
    				editor:{xtype:'combobox',viewname:'v_controltype'}},
    			{text:'其他参数',flex:1,dataIndex:'custom1',editor:{}},
    			{xtype:'actioncolumn',text:'操作',menuDisabled:true,sortable:false,width:80,hidden:display,align:'center',items: [{
                    iconCls: 'x-fa fa-times',tooltip: '删除记录',handler:this.onGridRemovehandler
                }]}
    		]
    	};
    	var modulestatus = {
            xtype:"grid",title:'数据状态配置',iconCls:"x-fa fa-database",reference:'moduledatastate',
            plugins: display?null:[{ptype:'cellediting',clicksToEdit:1}],
    		tbar:[{iconCls:'x-fa fa-plus-square',text:"添加状态代码",border:"0px",scope:this,hidden:display,handler:this.onGridAddhandler}],
    		store:{url:"platform/systemmodule/getdatastatelist.do"},
    		columns:[
    			{text:'序号',sortable:false,width:60,xtype: 'rownumberer',align: 'center'},
    			{text:'状态代码',flex:1,sortable:false,dataIndex:'statecode',align: 'center',editor:{}},
    			{text:'状态名称',flex:1,sortable:false,dataIndex:'statename',editor:{}},
    			{text:'其他参数1',flex:1,sortable:false,dataIndex:'custom1',editor:{}},
    			{text:'其他参数2',flex:1,sortable:false,dataIndex:'custom2',editor:{}},
    			{xtype:'actioncolumn',text:'操作',menuDisabled:true,sortable:false,width:80,hidden:display,align:'center',items: [{
                    iconCls: 'x-fa fa-times',tooltip: '删除记录',handler:this.onGridRemovehandler
                }]}
    		]
    	};
    	var buttons = display?null:[{text:'提交',scope:this,handler:this.onFormSubmit},{text:'关闭',scope:this,handler:this.onFormCancel}];
    	Ext.apply(me, {
    	 	items:[{xtype: 'fieldset',title: '基本信息',heigth:250,region: 'north',collapsible:true,titleCollapse:true,
    	 				defaults: {xtype:"textfield",anchor: '100%',readOnly:display},
				        items: [{fieldLabel: '模块名称',name: 'modulename',allowBlank:false}, 
				        		{fieldLabel: '模块编号',name: 'modulecode',allowBlank:false}, 
				        		{xtype:'combobox',fieldLabel: '模块类型',name:'moduletype',viewname:'v_moduletype',allowBlank:true}, 
				        		{fieldLabel: '访问地址',name: 'moduleurl'}, 
			        		    {xtype:'checkbox',fieldLabel: '是否有效',checked:true,inputValue:'1',uncheckedValue:"0",name:'isdisplay'}
				        ]
				  },
				  {xtype:"tabpanel",region: 'center',layout:'fit',autoScroll:true,items:[moduleoperate,modulestatus]}
    	 	],
    	 	buttons:buttons
    	});
    	this.callParent();
    },
    
    beforeRender:function(){
    	var me = this;
    	me.moduleoperate = me.lookupReference('moduleoperate');
    	me.moduledatastate = me.lookupReference('moduledatastate');
    	me.dataObject = [me.moduleoperate,me.moduledatastate];
        var moduleid = me.get("moduleid");
        if(Ext.isEmpty(moduleid))return;
        me.loadData(moduleid);
    },
    
    loadData:function(moduleid){
    	var me = this;
        var params =  {moduleid:moduleid};
        var url = "platform/systemmodule/getinfo.do";
        EU.RS({url:url,scope:me,msg:false,params:params,callback:function(result){
       		 me.getForm().setValues(result);
        }});
        me.moduleoperate.getStore().load({params:params});
        me.moduledatastate.getStore().load({params:params});
    },
    
    onFormSubmit:function(callback){
    	var me = this;
    	var params = {};
    	if(!me.getForm().isValid())return;
    	params.bean = me.getForm().getValues()
    	params.list1 = me.moduleoperate.getValues();
    	params.list2 = me.moduledatastate.getValues();
        Ext.apply(params.bean,me.get());
    	var url = "platform/systemmodule/saveorupdate.do";
    	EU.RS({url:url,scope:this,params:params,callback:function(result){
    		  me.set("moduleid",result);
    		  me.setReturnValue(result);
       		  me.loadData(result);
    		  EU.toastInfo("模块<font color='red'>『"+params.bean.modulename+"』</font>保存成功");
    		  if(Ext.isFunction(callback))callback();
    	}});
    },
    
    onFormCancel:function(){
    	var me = this;
    	me.closeWindowVerify();
    },
    
    onGridAddhandler:function(btn){
    	var me = this;
    	var gridObj  = btn.ownerCt.ownerCt;
    	var storeObj = gridObj.getStore();
    	var storeCount = storeObj.getCount();
    	storeObj.add({});
    	var rowEditing = gridObj.findPlugin("cellediting"); 
    	rowEditing.startEdit(storeCount,1);
    },
    
    onGridRemovehandler:function(grid, rowIndex, colIndex){
    	grid.getStore().removeAt(rowIndex);
    }
});