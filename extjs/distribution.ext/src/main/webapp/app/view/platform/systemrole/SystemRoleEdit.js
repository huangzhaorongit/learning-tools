Ext.define('app.view.platform.systemrole.SystemRoleEdit',{
	extend: 'ux.form.Panel',
    alternateClassName: 'sytemroleEdit',
    xtype: 'systemroleEdit',
    
    initComponent:function(){
    	var me = this;
    	Ext.apply(me, {
    	 	items:[
		    	{xtype: 'fieldset',title: '基本信息',defaults: {xtype:"textfield",anchor: '100%'},
			        items: [
		        		{fieldLabel: '角色编号',name: 'rolecode',allowBlank:false},
		        		{fieldLabel: '角色名称',name: 'rolename',allowBlank:false},
		        		{xtype: 'textareafield',fieldLabel: '角色描述',name: 'roledescription',height:100},
		        		{xtype: 'checkbox',fieldLabel: '是否有效',checked:true,inputValue:'1',uncheckedValue:"0",name:'isvalid'}
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
        var roleid = me.get("roleid");
        if(Ext.isEmpty(roleid))return;
        me.loadData(roleid);
    },
    
    loadData: function(roleid){
    	var me = this;
    	var params =  {roleid:roleid};
    	var url = 'platform/systemrole/getinfo.do';
    	EU.RS({url:url,scope:me,msg:false,params:params,callback:function(result){
       		 me.getForm().setValues(result);
        }});
    },
    
    onFormCancel: function(){
    	var me = this;
    	me.closeWindowVerify();
    },
    
    onFormSubmit:function(callback){
    	var me = this;
    	var params = {};
    	if(!me.getForm().isValid())return;
    	params = me.getForm().getValues()
        Ext.apply(params,me.get());
    	var url = "platform/systemrole/saveorupdate.do";
    	EU.RS({url:url,scope:this,params:params,callback:function(result){
    		  me.set("roleid",result.roleid);
    		  me.setReturnValue(me.toData(result));
       		  me.getForm().setValues(result);
    		  EU.toastInfo("角色<font color='red'>『"+params.rolename+"』</font>保存成功");
    		  if(Ext.isFunction(callback))callback();
    	}});
    },
    
    toData: function(result){
    	result.id = result.roleid;
    	return result;
    }
});