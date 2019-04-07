Ext.define('app.controller.platform.systemuserroles.SystemUserRolesController',{
	extend: 'Ext.app.ViewController',
	alias: 'controller.systemUserRoles',
	
	init:function(){
    	this.userRolePanel = this.lookupReference('userRolePanel');
    	this.userRolePanelYes = this.lookupReference('userRolePanelYes');
    	this.userRolePanelNo = this.lookupReference('userRolePanelNo');
    	
    },
    
	onSelectionchange: function(gridpanel, selected, eOpts){
		var me = this;
		if(selected.length==0)return;
    	var record = selected[0];
    	me.userRolePanelYes.setTitle("『"+record.data.username+"』已关联的角色");
    	me.userRolePanelNo.setTitle("『"+record.data.username+"』无关联的角色");
    	me.userRolePanelYes.load({userid : record.data.id});
    	me.userRolePanelNo.load({userid : record.data.id});
	},
	
	gridDropNo: function(node, data, overModel, dropPosition, eOpts){
		var me = this;
		var selected = me.userRolePanel.getSelection()[0];
		if(Ext.isEmpty(selected)){
			EU.toastWarn("请选择一条用户记录!");
		    me.userRolePanelYes.getStore().reload();
		    me.userRolePanelNo.getStore().reload();
		    return ;
		}
		var userid = selected.data.id;
		var roleid = data.records[0].data.id;
		var params = {roleid:roleid,userid:userid}
		var url = 'platform/systemuserroles/deletesystemuserroles.do';
		EU.RS({url:url,scope:this,msg:false,params:params,callback:function(result){
			if(result == '1')return null;
			this.beforeRefresh();
        }});
	},
	
	gridDropYes: function(node, data, overModel, dropPosition, eOpts){
		var me = this;
		var selected = me.userRolePanel.getSelection()[0];
		if(Ext.isEmpty(selected)){
			EU.toastWarn("请选择一条用户记录!");
		    me.userRolePanelYes.getStore().load();
		    me.userRolePanelNo.getStore().load();
		    return ;
		}
		var userid = selected.data.id;
		var roleid = data.records[0].data.id;
		var params = {roleid:roleid,userid:userid}
		var url = 'platform/systemuserroles/savasystemuserroles.do';
		EU.RS({url:url,scope:this,msg:false,params:params,callback:function(result){
			if(!Ext.isEmpty(result))return ;
			this.beforeRefresh();
        }});
	},
	
	beforeRefresh: function(){
		var me = this;
		var selected = me.userRolePanel.getSelection();
		if(!Ext.isEmpty(selected)){
			var userid = selected[0].data.id;
			me.userRolePanelYes.setTitle("『"+selected[0].data.username+"』已关联的角色");
			me.userRolePanelNo.setTitle("『"+selected[0].data.username+"』无 关联的角色");
		};
		me.userRolePanelYes.getStore().load({params:{userid : userid}});
		me.userRolePanelNo.getStore().load({params:{userid : userid}});
	}
})