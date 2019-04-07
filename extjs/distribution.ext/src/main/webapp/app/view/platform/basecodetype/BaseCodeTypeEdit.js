Ext.define('app.view.platform.basecodetype.BaseCodeTypeEdit', {
    extend: 'ux.form.Panel',
    alternateClassName: 'basecodetypeedit',
    xtype: 'basecodetypeedit',
    trackResetOnLoad: true, //开启关闭验证提醒
    layout:'border',
    
    initComponent:function(){
    	var me = this;
    	var display = me.viewtype == 'display'; //只读
    	var moduleoperate  = {
    		xtype: "grid",title: '分类信息',iconCls: "x-fa fa-th-list",reference: 'moduleoperate',
            plugins: display?null:[{ptype: 'cellediting',clicksToEdit: 1}],
    		tbar:[{iconCls: 'x-fa fa-plus-square',text: "添加操作代码",border: "0px",scope: this,hidden: display,handler: this.onGridAddhandler}],
    		store:{url: "platform/basecodetype/getfbasecodelist.do"},
    		columns:{items:[
    			{text: '序号',sortable: false,width:60,xtype: 'rownumberer',align: 'center'},
    			{text: '匹配编号',width: 80,dataIndex: 'codeid',align: 'center', editor:{}},
    			{text: '中文名称',width: 150,dataIndex: 'codename',align: 'left', editor:{}},
//    			{text: '英文名称',flex: 1,dataIndex: 'pinyincode', editor:{},align: 'center'},
//    			{text: '显示颜色',flex: 1,dataIndex: 'color', editor:{},align: 'center'},
    			{text:'是否有效',width: 80,dataIndex:'isvalid',xtype:'viewcolumn',viewname:'v_is_no',align:'center',editor:{xtype:'combobox',allowBlank:false,viewname:'v_is_no'}},
    			{text: '其他',flex: 1,dataIndex: 'custom1',align: 'left', editor:{}},
    			{xtype: 'actioncolumn',text: '操作',menuDisabled: true,sortable: false,width: 80,hidden: display,align: 'center',items: [{
                    iconCls: 'x-fa fa-times',tooltip: '删除记录',handler: this.onGridRemovehandler
                }]}
    		]}
    	};
    	var buttons = display?null:[{text: '提交',scope: this,handler: this.onFormSubmit},{text: '关闭',scope:this,handler:this.onFormCancel}];
    	Ext.apply(me, {
    	 	items:[{xtype: 'fieldset',title: '基本信息',region: 'north',collapsible:true,titleCollapse:true,
    	 				defaults: {xtype:"textfield",anchor: '100%',readOnly:display},
				        items: [{fieldLabel: '分类说明',name: 'coderemark',allowBlank:false}, 
				        		{fieldLabel: '视图名称',name: 'viewname',allowBlank:false}, 
				        		{fieldLabel: '备注',xtype:"textarea",name: 'remark',allowBlank:false}, 
			        		    {xtype:'checkbox',fieldLabel: '是否有效',checked:true,inputValue:'1',uncheckedValue:"0",name:'isvalid'}
				        ]
				  },
				  {xtype:"panel",region: 'center',layout:'fit',autoScroll:true,items:[moduleoperate]}
    	 	],
    	 	buttons:buttons
    	});
    	this.callParent();
    },
    
    beforeRender:function(){
    	var me = this;
    	me.moduleoperate = me.lookupReference('moduleoperate');
    	me.dataObject = [me.moduleoperate];
        var codetype = me.get("codetype");
        if(Ext.isEmpty(codetype))return;
        me.loadData(codetype);
    },
    
    loadData:function(codetype){
    	var me = this;
        var params =  {codetype:codetype};
        var url = "platform/basecodetype/getbasecodetypeinfo.do";
        EU.RS({url:url,scope:me,msg:false,params:params,callback:function(result){
       		 me.getForm().setValues(result);
        }});
        me.moduleoperate.getStore().load({params:params});
    },
    
    onFormCancel:function(){
    	var me = this;
    	me.closeWindowVerify();
    },
    
    onFormSubmit:function(callback){
    	var me = this;
    	var params = {};
    	if(!me.getForm().isValid())return;
    	params.bean = me.getForm().getValues()
    	params.list = me.moduleoperate.getValues();
        Ext.apply(params.bean,me.get());
    	var url = "platform/basecodetype/saveorupdate.do";
    	EU.RS({url:url,scope:this,params:params,callback:function(result){
    		  me.set("codetype",result);
    		  me.setReturnValue(result);
       		  me.loadData(result);
    		  EU.toastInfo("模块<font color='red'>『"+params.bean.coderemark+"』</font>保存成功");
    		  if(Ext.isFunction(callback))callback();
    	}});
    },
    
    onGridAddhandler:function(btn){
    	var me = this;
    	var gridObj  = btn.ownerCt.ownerCt;
    	var storeObj = gridObj.getStore();
    	var storeCount = storeObj.getCount();
    	storeObj.add({isvalid:1});
    	var rowEditing = gridObj.findPlugin("cellediting"); 
	    	rowEditing.startEdit(storeCount,1);
    },
    
    onGridRemovehandler:function(grid, rowIndex, colIndex){
    	grid.getStore().removeAt(rowIndex);
    }	
});