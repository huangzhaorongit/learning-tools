Ext.define('app.view.bussiness.device.DeviceEdit', {
    extend: 'ux.form.Panel',
    alternateClassName: 'deviceEdit',
    xtype: 'deviceEdit',

    initComponent:function(){
    	var me = this;
    	var buttons = [{text:'提交',scope:this,handler:this.onFormSubmit},{text:'关闭',scope:this,handler:this.onFormCancel}];
     	Ext.apply(me, {
    	 	items:[{
	 			xtype: 'fieldset',title: '基本信息',defaults: {xtype:"textfield",width: '100%'},
	 			layout: {type: 'table',columns: 2, tdAttrs :{style : {width:'50%',textAlign:'center'}}},
		        items: [
	        		{fieldLabel: '编号',name: 'deviceNo',allowBlank:false},
	        		{fieldLabel: '名称',name: 'name',allowBlank:false},
	                {xtype:'treepicker',fieldLabel: '所属设备',name: 'parentId',allowBlank:true,minPickerHeight:300,
    			    	store:{autoLoad:true,url:'bussiness/device/gettreelist.do'}
    		        },
    		         {fieldLabel: '地址',name: 'address',allowBlank:false},
	        		{fieldLabel: '排序',name: 'sort',allowBlank:false},

    		        {fieldLabel: '经度',name: 'longitude',allowBlank:false},
	        		{fieldLabel: '纬度',name: 'latitude',allowBlank:false},
	        		{fieldLabel: '地址编码',name: 'code',allowBlank:false},
	        		{fieldLabel: '变比',name: 'ratio',allowBlank:false},
        		    {xtype:'checkbox',fieldLabel: '是否有效',inputValue:'1',uncheckedValue:"0",name:'enable',checked:true,allowBlank:false},
           	    	{fieldLabel: '备注信息',name: 'remarks',allowBlank:false}
	        	]
			}],
    	 	buttons:buttons
    	});
    	this.callParent();
    },

    beforeRender:function(){
    	var me = this;
        var id = me.get("id");
        var parentId = me.get("parentId");

        if(!Ext.isEmpty(parentId))me.getForm().setValues({"parentId":parentId});
        if(Ext.isEmpty(id))return;
        me.loadData(id);
    },

    loadData:function(id){
    	var me = this;
        var params =  {id:id};

        EU.RS({url:cfg.appPath+'bussiness/device/getinfo.do',params:params,msg:false,callback:function(result){
       		me.getForm().setValues(result);
//       		me.setReadOnly(true);
        }});
    },


    onFormSubmit:function(callback){
    	var me = this;
    	var params = {};
    	if(!me.getForm().isValid())return;
    	var params = me.getForm().getValues();
    	var oid = me.findField("parentId").getValue();
    	me.set("parentId",oid);
    	Ext.apply(params,me.get());
    	var url = 'bussiness/device/saveorupdate.do';
    	EU.RS({url:url,scope:this,params:params,callback:function(result){
    		  me.set("id",result.id);
    		  me.setReturnValue(result);

    		  EU.toastInfo("设备<font color='red'>【"+params.name+"】</font>保存成功");
    		  me.updateDirty();
    		  if(Ext.isFunction(callback))callback();
    	}});
    },

    onFormCancel:function(){
    	var me = this;
    	me.closeWindowVerify();
    }
});
