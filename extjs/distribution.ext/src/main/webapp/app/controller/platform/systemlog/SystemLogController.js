Ext.define('app.controller.platform.systemlog.SystemLogController',{
	extend: 'Ext.app.ViewController',
	alias: 'controller.systemLog',
	
	init:function(){
		this.systemOperate = this.lookupReference('systemOperate');
		this.systemException = this.lookupReference('systemException');
		this.startDate = this.lookupReference('startDate');
		this.endDate = this.lookupReference('endDate');
	},
	
	onRefresh: function(btn){
		btn.up("grid").getStore().reload();
	},
	
	querylog:function(){
		var startDateValue = this.startDate.rawValue;
    	var endDateValue = this.endDate.rawValue;
		this.systemOperate.load({startDateValue:startDateValue,endDateValue:endDateValue})
	},
	
	onModuledblClick: function(gridpanel, record, tr, rowIndex, e, eOpts){
		var me = this;
		if(!Ext.isEmpty(record)){
	    	var title = "查询『"+record.data.description+"』详情";
			var params = {cid:record.data.id};
			if(record.data.type == '0'){
				me.openModuleOperate(title,params)
			}else{
				me.openModuleException(title,params)
			}
		}	
	},
	
     openModuleOperate:function(title,params,callback){
    	PU.openModule({title:title,xtype:"systemOperateEdit",width:500,params:params,scope:this,callback:function(result){
		    if(Ext.isEmpty(result))return;
    		if(Ext.isFunction(callback))callback.call(this,result)
    	}});
    },
    
     openModuleException:function(title,params,callback){
    	PU.openModule({title:title,xtype:"systemExceptionEdit",width:600,params:params,scope:this,callback:function(result){
		    if(Ext.isEmpty(result))return;
    		if(Ext.isFunction(callback))callback.call(this,result)
    	}});
    }
    
})