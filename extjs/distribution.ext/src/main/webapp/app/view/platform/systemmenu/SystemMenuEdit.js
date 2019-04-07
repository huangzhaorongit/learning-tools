Ext.define('app.view.platform.systemmenu.SystemMenuEdit', {
    extend: 'ux.form.Panel',
    alternateClassName: 'systemMenuEdit',
    xtype: 'systemMenuEdit',
    
    initComponent:function(){
    	var me = this;
    	Ext.apply(me, {
    	 	items:[
		    	{xtype: 'fieldset',title: '基本信息',defaults: {xtype:"textfield",width:'100%'},
			    	layout: {type: 'table',columns: 2},
			        items: [
			        	{fieldLabel: '菜单名称',name: 'menuname',allowBlank:false,colspan: 2,width: '100%'}, 
		        		{xtype:'treepicker',fieldLabel: '关联模块',name: 'moduleid',colspan: 2,
		        			maxPickerHeight:300,store:{autoLoad:true,url:"platform/systemmenu/getallmodule.do"}
		        		},
		        		{xtype:'combobox',fieldLabel: '菜单类型',name:'menutype',viewname:'v_menutype',allowBlank:false,colspan: 2},
		        		{fieldLabel: '文件图标',name: 'icon',tooltip : '请把图标放入<resources/images/icons/16x16/>目录下',colspan: 2}, 
		        		{fieldLabel: '字体图标',name: 'iconcls',xtype:'iconclsfield',col:15,colspan: 2},
		        		{xtype:'colorfield',fieldLabel: '图标颜色',name: 'iconcolor',blankText:'图标颜色'}, 
	        			{xtype:'checkbox',fieldLabel: '无色',checked:true,inputValue:true,uncheckedValue:false,name:'colornull'},
		        		{xtype:'checkbox',fieldLabel: '是否有效',checked:true,inputValue:'1',uncheckedValue:"0",name:'isdisplay',colspan:2}
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
        var menuid = me.get("menuid");
        if(Ext.isEmpty(menuid))return;
        me.loadData(menuid);
    },
    
    loadData:function(menuid){
    	var me = this;
        var params =  {menuid:menuid};
        var url = "platform/systemmenu/getinfo.do";
        EU.RS({url:url,scope:me,msg:false,params:params,callback:function(result){
        	 result.colornull = Ext.isEmpty(result.iconcolor);
       		 me.getForm().setValues(result);
        }});
    },
    
    onFormSubmit:function(callback){
    	var me = this;
    	if(!me.getForm().isValid())return;
    	var params = me.getValues();
        if(params.colornull)params.iconcolor = null;
        Ext.apply(params,me.get());
    	var url = "platform/systemmenu/saveorupdate.do";
    	EU.RS({url:url,scope:this,params:params,callback:function(result){
    		  me.set("menuid",result.menuid);
    		  me.setReturnValue(me.toTreeData(result));
    		  result.colornull = Ext.isEmpty(result.iconcolor);
    		  me.updateDirty();
    		  EU.toastInfo("菜单<font color='red'>『"+result.menuname+"』</font>保存成功");
    		  if(Ext.isFunction(callback))callback();
    	}});
    },
    
    toTreeData:function(result){
    	result.leaf = true;
	    result.id = result.menuid;
    	if(result.menutype=='00'){
	    	result.leaf = false;
	    	result.loaded = true;
    	}
    	return result;
    },
    
    onFormCancel:function(){
    	var me = this;
    	me.closeWindowVerify();
    }
});