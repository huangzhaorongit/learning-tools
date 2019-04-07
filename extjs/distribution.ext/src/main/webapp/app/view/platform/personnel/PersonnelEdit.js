Ext.define('app.view.platform.personnel.PersonnelEdit', {
    extend: 'ux.form.Panel',
    alternateClassName: 'personnelEdit',
    xtype: 'personnelEdit',
    
    initComponent:function(){
    	var me = this;
    	var buttons = [{text:'提交',scope:this,handler:this.onFormSubmit},{text:'关闭',scope:this,handler:this.onFormCancel}];
    	me.photo =  Ext.create('Ext.Img',{width:'50%',rowspan:5,src: '',alt:'人员照片',style:'cursor:pointer;',listeners: {el: {scope:this,click: me.onPhotoUpload}}});
    	Ext.apply(me, {
    	 	items:[{
	 			xtype: 'fieldset',title: '基本信息',defaults: {xtype:"textfield",width: '100%'},
	 			layout: {type: 'table',columns: 2, tdAttrs :{style : {width:'50%',textAlign:'center'}}},
		        items: [
	        		{fieldLabel: '员工姓名',name: 'personnelname',allowBlank:false},
	        		me.photo,
	                {xtype:'treepicker',fieldLabel: '所属部门',name: 'departmentid',allowBlank:true,minPickerHeight:300,
    			    	store:{autoLoad:true,url:"platform/personnel/gettreelist.do"}
    		        },
    		        {xtype:'combobox',fieldLabel: '性别',name:'sex',viewname:'v_sex',allowBlank:false},
	        		{xtype:'combobox',fieldLabel: '婚姻情况',name:'maritalstatus',viewname:'v_maritalstatus',allowBlank:false},  
	        		{xtype:'datefield',fieldLabel: '出生年月',format : 'Y-m-d',name: 'birthdate'},
	        		{fieldLabel: '电话',name: 'mobile'}, 
	        		{fieldLabel: '邮箱',name: 'email'}, 
	        		{fieldLabel: '职位',name: 'position'}, 
        		    {xtype:'checkbox',fieldLabel: '是否有效',inputValue:'1',uncheckedValue:"0",name:'isvalid',checked:true,allowBlank:false},
           	    	{xtype:'uploadfield',name:"attachsnum",cfg:{tablename:"f_personnel",fieldname:"personnelid"}}
	        	]
			}],
    	 	buttons:buttons
    	});
    	this.callParent();
    },
    
    beforeRender:function(){
    	var me = this;
        var personnelid = me.get("personnelid");
        var departmentid = me.get("departmentid");
    	var imgSrc = "platform/personnel/getphoto.do?_dc="+new Date().getTime()+"&photoid="+personnelid;
		me.photo.setSrc(imgSrc);
        if(!Ext.isEmpty(departmentid))me.getForm().setValues({"departmentid":departmentid});
        if(Ext.isEmpty(personnelid))return;
        me.loadData(personnelid);
    },
    
    loadData:function(personnelid){
    	var me = this;
        var params =  {personnelid:personnelid};
    	me.findField("attachsnum").setFieldValue(personnelid);
//    	EU.Personnel.getPersonnelInfo({params:params,msg:false,callback:function(result){
//       		me.getForm().setValues(result);
//        }});
//        EU.RS({service:"Personnel",method:"getPersonnelInfo",params:params,msg:false,callback:function(result){
//       		me.getForm().setValues(result);
//        }});
        EU.RS({url:'platform/personnel/getinfo.do',params:params,msg:false,callback:function(result){
       		me.getForm().setValues(result);
//       		me.setReadOnly(true);
        }});
    },
    
    onPhotoUpload:function(){
    	var me = this;
    	if(me.findField("attachsnum").cReadOnly)return;
    	var personnelid = me.get("personnelid");
    	if(Ext.isEmpty(personnelid)){EU.toastWarn("保存单据信息后在上传头像。");return;}
    	if(!me.winPhoto){
    		var p_form = Ext.create("Ext.form.Panel",{layout: 'fit',items:{xtype:'filefield',labelWidth:70,emptyText: '请选择照片',fieldLabel: '照片',
    					name: 'photofile',triggers:{clear:false},allowBlank:false,buttonText:'',buttonConfig: {iconCls: 'x-fa fa-file-image-o'}}});
    		var form  = p_form.getForm();
			me.winPhoto = Ext.create('Ext.window.Window', {title: '上传照片',height: 150,width: 500,
			    layout: 'fit',bodyPadding: 5,modal:true,closeAction:'hide',
			    items: p_form,
			    buttons: [{
		            text: '清除',
		            handler: function(){
		            	var url = "platform/personnel/cleanphoto.do";
		            	EU.RS({url:url,params:{personnelid:personnelid},callback: function(options, success, response) {
		                	 EU.toastInfo("清除成功!");
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
				                	EU.toastInfo("上传成功!");
    								var imgSrc = "platform/personnel/getphoto.do?_dc="+new Date().getTime()+"&photoid="+personnelid;
				                	me.photo.setSrc(imgSrc);
				                	me.winPhoto.hide();
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
    	var params = me.getForm().getValues();
    	var oid = me.findField("departmentid").getValue();
    	me.set("departmentid",oid);
    	Ext.apply(params,me.get());
    	var url = "platform/personnel/saveorupdate.do";
    	EU.RS({url:url,scope:this,params:params,callback:function(result){
    		  me.set("personnelid",result.personnelid);
    		  me.setReturnValue(result);
    		  me.findField("attachsnum").setFieldValue(result.personnelid);
    		  EU.toastInfo("员工<font color='red'>【"+params.personnelname+"】</font>保存成功");
    		  me.updateDirty();
    		  if(Ext.isFunction(callback))callback();
    	}});
    },
    
    onFormCancel:function(){
    	var me = this;
    	me.closeWindowVerify();
    }
});