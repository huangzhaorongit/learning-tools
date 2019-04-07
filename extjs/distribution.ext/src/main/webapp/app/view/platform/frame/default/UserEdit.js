Ext.define('app.view.platform.frame.default.UserEdit', {
    extend: 'ux.form.Panel',
    alternateClassName: 'userEdit',
    xtype: 'userEdit',
    initComponent:function(){
    	var me = this;
    	var buttons = [{text:'提交',scope:this,handler:this.onFormSubmit},{text:'关闭',scope:this,handler:this.onFormCancel}];
    	me.photo =  Ext.create('Ext.Img',{width:'74%',rowspan:5,src: '',alt:'人员照片',listeners: {el: {scope:this,click: me.onPhotoUpload}}});
    	Ext.apply(me, {
    	 	items:[{
	 			xtype: 'fieldset',title: '人员信息',defaults: {xtype:"textfield",width: '100%'},
	 			layout: {type: 'table',columns: 2, tdAttrs :{style : {width: '50%',textAlign:'center'}
		        }},
		        items: [
	        		{fieldLabel: '员工姓名',name: 'personnelname',allowBlank:false},
	        		me.photo,
	        		{xtype:'combobox',fieldLabel: '性别',name:'sex',viewname:'v_sex',allowBlank:false,editor:{xtype:'combobox',viewname:'v_sex'}},
	        		{xtype:'combobox',fieldLabel: '婚姻情况',name:'maritalstatus',viewname:'v_maritalstatus',allowBlank:false,editor:{xtype:'combobox',viewname:'v_maritalstatus'}},  
	        		{xtype:'datefield',format : 'Y-m-d',fieldLabel: '出生年月',name: 'birthdate',allowBlank:false}, 
	        		{fieldLabel: '电话',name: 'mobile',allowBlank:false}, 
	        		{fieldLabel: '邮箱',name: 'email',allowBlank:false}, 
	        		{fieldLabel: '职位',name: 'position',allowBlank:true,readOnly:true}
		        ]
			},{
	 			xtype: 'fieldset',title: '用户信息',defaults: {xtype:"textfield",width: '100%'},
	 			layout: {type: 'table',columns: 2, tdAttrs :{style : {width: '50%',textAlign:'center'}
		        }},
		        items: [
	        		{fieldLabel: '用户代码',name: 'usercode',allowBlank:false,readOnly:true},
	        		{fieldLabel: '用户姓名',name: 'username',allowBlank:false},
	        		{fieldLabel: '原密码',name: 'opassword',allowBlank:true,inputType: 'password'},
	        		{fieldLabel: '新密码',name: 'npassword',allowBlank:true,inputType: 'password'},
	        		{xtype:'panel'},
	        		{fieldLabel: '确认密码',name: 'qpassword',allowBlank:true,inputType: 'password'}
		        ]
			}],
    	 	buttons:buttons
    	});
    	this.callParent();
    },
    
    beforeRender:function(){
    	var me = this;
        var personnelid = me.get("personnelid");
        var userid = me.get("userid");
    	var imgSrc = "platform/personnel/getphoto.do?_dc="+new Date().getTime()+"&photoid="+personnelid;
		me.photo.setSrc(imgSrc);
        if(Ext.isEmpty(personnelid)||Ext.isEmpty(userid))return;
        me.loadData(personnelid,userid);
    },
    
    loadData:function(personnelid,userid){
    	var me = this;
        var params =  {personnelid:personnelid,userid:userid};
        var url = "platform/systemframe/getUserInfo.do";
        EU.RS({url:url,scope:me,msg:false,params:params,callback:function(result){
        	if(!Ext.isEmpty(result)){
        		me.getForm().setValues(result);
        	}
        }});
    },
    
    onPhotoUpload:function(){
    	var me = this;
    	var personnelid = me.get("personnelid");
    	if(Ext.isEmpty(personnelid)){EU.toastWarn("保存单据信息后在上传头像。");return;}
    	if(!me.winPhoto){
    		var p_form = Ext.create("Ext.form.Panel",{layout: 'fit',items:{xtype:'filefield',labelWidth:70,emptyText: '请选择照片',fieldLabel: '照片',
    					name: 'photofile',triggers:{clear:false},allowBlank:false,buttonText:'',buttonConfig: {iconCls: 'x-fa fa-file-image-o'}}});
    		var form  = p_form.getForm();
			me.winPhoto = Ext.create('Ext.window.Window', {title: '上传照片',height: 150,width: 500,
			    layout: 'fit',bodyPadding: 5,modal:true,
			    items: p_form,
			    buttons: [{
		            text: '清除',
		            handler: function(){
		            	var url = "platform/personnel/cleanphoto.do";
		            	EU.RS({url:url,params:{personnelid:personnelid},callback: function(options, success, response) {
		                	 EU.toastWarn("清除成功!");
		                }});
		            }
		        },{
		            text: '上传',
		            handler: function(){
		            	if (form.isValid()) {
				            p_form.submit({
				                url: 'platform/personnel/uploadphoto.do',
				                params:{personnelid:personnelid},
				                waitMsg: '照片上传中..',
				                timeout:60,
				                callback: function(options, success, response) {
				                	EU.toastWarn("上传成功!");
    								var imgSrc = "platform/personnel/getphoto.do?_dc="+new Date().getTime()+"&photoid="+personnelid;
				                	me.photo.setSrc(imgSrc);
				                }
				            });
				        }
		            }
		        }, {
		            text: '关闭',
		            handler: function(){form.reset();me.winPhoto.hide();}
		        }]
			})
    	}
    	me.winPhoto.show();
    },
    
    onFormSubmit:function(callback){
    	var me = this;
    	var params = {};
    	if(!me.getForm().isValid())return;
    	//密码验证
    	var url = "platform/systemframe/updateUser.do";
    	var opassword = me.getForm().findField("opassword").getValue();
    	var npassword = me.getForm().findField("npassword").getValue();
    	var qpassword = me.getForm().findField("qpassword").getValue();
    	var params = me.getForm().getValues();
    	if(Ext.isEmpty(opassword) && (!Ext.isEmpty(npassword) || !Ext.isEmpty(qpassword))){
    		EU.toastWarn("请输入原密码后才能修改密码!");
    		return;
    	}
    	if(!Ext.isEmpty(opassword)){
    		if(Ext.isEmpty(npassword)){EU.toastWarn("新密码不能为空!");return;}
    		if(npassword != qpassword){EU.toastWarn("修改密码输入不一致!");return;}
    	}
    	Ext.apply(params,me.get());
    	EU.RS({url:url,scope:this,params:params,callback:function(result){
    		EU.toastInfo(result.message);
    		if(result.success&&result.stackTrace=="password"){
    			me.closeWindow();
  			    EU.redirectTo("login");
    		}else if(result.success&&result.stackTrace!="password"){
    			me.getForm().setValues(result.data);
    		}else{
    			me.findField("opassword").setValue("");
    			me.findField("npassword").setValue("");
    			me.findField("qpassword").setValue("");
    		}
    		if(Ext.isFunction(callback))callback();
    	}});
    },
    
    onFormCancel:function(){
    	 var me = this;
	     me.closeWindowVerify();
    }
});