Ext.define('app.view.platform.systemlog.SystemOperateEdit',{
	extend: 'ux.form.Panel',
    alternateClassName: 'systemOperateEdit',
    xtype: 'systemOperateEdit',
    
    initComponent:function(){
    	var me = this;
    	Ext.apply(me, {
    	 	items:[
		    	{xtype: 'fieldset',title: '基本信息',defaults: {xtype:"textfield",anchor: '100%'},
			        items: [
		        		{fieldLabel: '操作信息',name: 'description',allowBlank:false,readOnly: true},
		        		{fieldLabel: '操作人',name: 'creater',allowBlank:false,readOnly: true},
		        		{fieldLabel: '请求ip',name: 'ip',allowBlank:false,readOnly: true},
		        		{fieldLabel: '操作时间',name: 'createdate',allowBlank:false,readOnly: true},
		        		{xtype: 'textareafield',fieldLabel: '操作详情',name: 'method',readOnly: true}
			        ]
			    }	
		    ],
    	 	buttons:[
		    	{text:'关闭',scope:this,handler:me.onFormCancel}
		    ]
    	});
    	this.callParent();
    },
    
    beforeRender:function(){
    	var me = this;
    	this.callParent();
        var id = me.get("cid");
        if(Ext.isEmpty(id))return;
        me.loadData(id);
    },
    
    loadData: function(id){
    	var me = this;
    	var params =  {id:id};
    	var url = 'platform/systemlog/getsystemlog.do';
    	EU.RS({url:url,scope:me,msg:false,params:params,callback:function(result){
       		 me.getForm().setValues(result);
        }});
    },
    
    onFormCancel:function(){
    	var me = this;
    	me.closeWindowVerify();
    }
});