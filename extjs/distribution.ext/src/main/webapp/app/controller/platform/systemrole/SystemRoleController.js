Ext.define('app.controller.platform.systemrole.SystemRoleController',{
	extend: 'Ext.app.ViewController',
	
	alias: 'controller.systemRole',
	
	init: function(){
		this.gridpanel = this.lookupReference('gridPanel');
	},
	
	onRefresh: function(){
		 this.gridpanel.getStore().reload();
	},
	
	onModuledblClick: function(gridpanel, record, tr, rowIndex, e, eOpts){
		var me = this;
		if(!Ext.isEmpty(record)){
	    	var title = "角色『"+record.data.rolename+"』维护";
			var params = {roleid:record.data.roleid};
			me.openModule(null,title,params)
		}
	},
	
	onUpdate: function(btn){
		var me = this;
		var selected = me.gridpanel.getSelection();
		if(Ext.isEmpty(selected)||selected.length>1){EU.toastWarn("选择一行您要修改的数据！");return;}
		var title = "角色『"+selected[0].data.rolename+"』维护";
		var params = {roleid:selected[0].data.roleid};
		me.openModule(btn,title,params)
	},
	
	onAdd: function(btn){
		var me = this;
		var title = "新增『角色信息』";
		me.openModule(btn,title,null)
	},
	
	onDelete: function(btn){
		var me = this;
		var selected = me.gridpanel.getSelection();
		if(Ext.isEmpty(selected)){EU.toastWarn("至少要选择一行您要删除的数据！");return;}
		var list = new Array();
		var title = '';
		var count = selected.length;
		Ext.each(selected, function(rec) { 
             list.push({roleid:rec.data.roleid});
             title += rec.data.rolename + '  ';  
        });
        EU.showMsg({message:"您确定要删除<font color='red'>『"+title+"』</font>这<font color='red'>"+ count +"</font>条记录吗？",option:1,scope:this,callback:function(btn, text){
        	if(btn == 'yes'){
        		var url = "platform/systemrole/deletesystemrole.do";
        		EU.RS({url:url,scope:this,params:{list: list},callback:function(result){
        			if(result == '1'){
        				EU.toastInfo("删除<font color='red'>『"+title+"』</font>记录成功");
        				 this.gridpanel.getStore().reload();
        			}
		    	}});
        	}
        }}); 	
    },
    
    openModule:function(btn,title,params,callback){
    	PU.openModule({title:title,xtype:"sytemroleEdit",width:500,params:params,scope:this,animateTarget:btn,callback:function(result){
		    if(Ext.isEmpty(result))return;
    		this.gridpanel.getStore().reload();
    	}});
    }
})