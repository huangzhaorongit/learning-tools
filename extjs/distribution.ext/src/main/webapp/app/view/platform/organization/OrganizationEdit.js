Ext.define('app.view.platform.organization.OrganizationEdit',{
	extend: 'ux.form.Panel',
	alternateClassName: 'organizationEdit',
	xtype: 'organizationEdit',
	
	initComponent:function(){
    	var me = this;
    	Ext.apply(me, {
    	 	items:[
		    	{xtype: 'fieldset',title: '基本信息',defaults: {xtype:"textfield",anchor: '100%'},
			        items: [{fieldLabel: '组织名称',name: 'orgname',allowBlank:false}, 
			        		{fieldLabel: '所属公司',name: 'companyname',allowBlank:false,readOnly: true},
			        		{fieldLabel: '菜单类型',xtype:'combobox',name:'orgtype',viewname:'v_orgtype',allowBlank:false},
			        		{fieldLabel: '电话',name: 'telephone',allowBlank:false},
			        		{fieldLabel: '地址',name: 'address',allowBlank:false},
			        		{fieldLabel: '负责人',name: 'manager',allowBlank:false},
			        		{fieldLabel: '备注',name: 'remarks',xtype: 'textareafield'},
			        		{xtype:'checkbox',fieldLabel: '是否有效',checked:true,inputValue:'1',uncheckedValue:"0",name:'isvalid'}
			        ]
			   }	
		    ],
    	 	buttons:[
		    	{text:'提交',scope:this,handler:me.onFormSubmit},
		    	{text:'关闭',scope:this,handler:me.onFormCancel}
		    ]
    	});
    	this.callParent();
    },
    
    beforeRender:function(){
    	var me = this;
    	this.callParent();
        var orgid = me.get("orgid");
        var companyname = me.get("companyname");
        if(Ext.isEmpty(orgid)){
        	var result = {'companyname':companyname};
        	me.getForm().setValues(result);
        }else{
        	me.loadData(orgid);
        }
    },
    
    loadData: function(orgid){
    	var me = this;
    	var params =  {orgid:orgid};
    	var url = 'platform/organization/getOrganizationInfo.do';
    	EU.RS({url:url,scope:me,msg:false,params:params,callback:function(result){
       		 me.getForm().setValues(result);
        }});
    },
    
    onFormCancel: function(){
    	var me = this;
    	me.closeWindowVerify();
    },
    
    onFormSubmit: function(callback){
    	var me = this;
    	if(!me.getForm().isValid())return;
    	var params = me.getForm().getValues();
        Ext.apply(params,me.get());
        var url = 'platform/organization/saveorupdate.do';
        EU.RS({url:url,scope:this,params:params,callback:function(result){
    		  me.set("orgid",result.orgid);
    		  me.setReturnValue(me.toTreeData(result));
       		  me.updateDirty();
    		  EU.toastInfo("菜单<font color='red'>『"+result.orgname+"』</font>保存成功");
    		  if(Ext.isFunction(callback))callback();
    	}});
    },
    
    toTreeData:function(result){
    	result.leaf = true;
	    result.id = result.orgid;
	    result.companyname = this.get("companyname");
    	if(result.orgcode=='00'){
	    	result.leaf = false;
	    	result.loaded = true;
    	}
    	return result;
    }
    
});