Ext.define('app.view.platform.systemuser.SystemUserEdit', {
    extend: 'ux.form.Panel',
    alternateClassName: 'systemUserEdit',
    xtype: 'systemUserEdit',
    initComponent:function(){
    	var me = this;
    	me.params = me.params || {};
		var buttons = [{text:'提交',scope:this,handler:this.onFormSubmit},{text:'关闭',scope:this,handler:this.onFormCancel}];
		me.fieldset = new Ext.form.FieldSet({xtype: 'fieldset',title: '用户信息',defaults: {xtype:"textfield",anchor: '100%'},
		        items: [
		        	{fieldLabel: '用户代码',name: 'usercode',allowBlank:false,readOnly:!Ext.isEmpty(me.params.userid)}, 
	        		{fieldLabel: '用户姓名',name: 'username',allowBlank:false},
	        		{fieldLabel: '用户密码',name: 'password',inputType: 'password'},
	        		{fieldLabel: '密码确认',name: 'password1',inputType: 'password'},
	        		{xtype:'selectfield',fieldLabel: '关联人员',hiddenName:'personnelid',name:'personnelname',
	        		   openconfig:{
	        		   	   modal:true,height:700,width:800,singleSelect:true,
		        		   columns:[
			    	            {text: '序  号',sortable:false,width:60,xtype: 'rownumberer',align: 'center'},
			    		    	{text: '员工姓名',flex: 1,dataIndex: 'personnelname',type:'key'},
			    		    	{text: '员工编号',flex: 1,dataIndex: 'personnelcode',align:'right'},
			    		    	{text: '所属部门',flex: 1,dataIndex: 'orgname',align:'right'}
			        	   ],
		        		   store:{autoLoad:true,url:"platform/personnel/getlist.do"}
	        		   }
	        		},
	        		{fieldLabel: '所属公司',name: 'companyname',allowBlank:false,readOnly:true,value:cfg.sub.companyname},
	        		{fieldLabel: '是否有效',xtype:'checkbox',checked:true,inputValue:'1',uncheckedValue:"0",name:'isvalid'},
	        		{fieldLabel: '是否锁定',xtype:'checkbox',checked:false,inputValue:'0',uncheckedValue:"0",name:'islocked'}
		         ]
			});
		Ext.apply(this, {
		 	items:[me.fieldset],
		 	buttons:buttons
		});
		this.callParent();
	},
    
    beforeRender:function(){
		var me = this;
		var userid = me.get("userid");
        if(Ext.isEmpty(userid))return;
		me.loadData(userid);
	},

	loadData:function(userid){
	    var me = this;
	    var url = "platform/systemuser/getinfo.do";
        EU.RS({url:url,scope:me,msg:false,params:{userid:userid},callback:function(result){
    	    me.getForm().setValues(result);
        }});
	},	

	onFormCancel:function(){
    	var me = this;
    	me.closeWindowVerify();
    }, 
    
    onFormSubmit:function(callback){
    	var me = this;
    	if(!me.getForm().isValid())return;
    	var params = me.getForm().getValues();
		var userid = me.get("userid");
    	if(Ext.isEmpty(userid) && Ext.isEmpty(params.password)){EU.toastWarn("请输入密码!");return;}
    	if(params.password!=params.password1){EU.toastWarn("两次密码输入不一致!");return;}
        Ext.apply(params,me.get());
    	var url = "platform/systemuser/saveorupdate.do";
    	EU.RS({url:url,scope:this,params:params,callback:function(result){
    		  me.set("userid",result.userid);
    		  me.setReturnValue(result.userid);
    		  me.updateDirty();
    		  EU.toastInfo("用户<font color='red'>『"+params.username+"』</font>保存成功");
    		  if(Ext.isFunction(callback))callback();
    	}});
    }
 
});