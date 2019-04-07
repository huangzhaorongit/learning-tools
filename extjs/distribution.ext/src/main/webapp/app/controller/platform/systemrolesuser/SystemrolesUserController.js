Ext.define('app.controller.platform.systemrolesuser.SystemrolesUserController',{
	extend: 'Ext.app.ViewController',
	alias: 'controller.systemRolesUser',
	init:function(){
    	this.rolePanel = this.lookupReference('rolePanel');
    	this.roleyespanel = this.lookupReference('roleyespanel');
    	this.rolenopanel = this.lookupReference('rolenopanel');
    	
    },
    
	onSelectionchange: function(gridpanel, selected, eOpts){
		var me = this;
		if(selected.length==0)return;
    	var record = selected[0];
    	me.roleyespanel.setTitle("『"+record.data.rolename+"』已关联的角色");
    	me.rolenopanel.setTitle("『"+record.data.rolename+"』无关联的角色");
    	me.roleyespanel.load({roleid : record.data.id});
    	me.rolenopanel.load({roleid : record.data.id});
	},
	
	gridDropNo: function(node, data, overModel, dropPosition, eOpts){
		var me = this;
		var selected = me.rolePanel.getSelection()[0];
		if(Ext.isEmpty(selected)){
			EU.toastWarn("请选择一条用户记录!");
		    me.roleyespanel.getStore().reload();
		    me.rolenopanel.getStore().reload();
		    return ;
		}
		var roleid = selected.data.id;
		var userid = data.records[0].data.id;
		var params = {roleid:roleid,userid:userid}
		var url = 'platform/systemuserroles/deletesystemuserroles.do';
		EU.RS({url:url,scope:this,msg:false,params:params,callback:function(result){
			if(result == '1')return null;
			this.beforeRefresh();
        }});
	},
	
	gridDropYes: function(node, data, overModel, dropPosition, eOpts){
		var me = this;
		var selected = me.rolePanel.getSelection()[0];
		if(Ext.isEmpty(selected)){
			EU.toastWarn("请选择一条用户记录!");
		    me.roleyespanel.getStore().load();
		    me.rolenopanel.getStore().load();
		    return ;
		}
		var roleid = selected.data.id;
		var userid = data.records[0].data.id;
		var params = {roleid:roleid,userid:userid}
		var url = 'platform/systemuserroles/savasystemuserroles.do';
		EU.RS({url:url,scope:this,msg:false,params:params,callback:function(result){
			if(!Ext.isEmpty(result))return ;
			this.beforeRefresh();
        }});
	},
	
	beforeRefresh: function(){
		var me = this;
		var selected = me.rolePanel.getSelection();
		if(!Ext.isEmpty(selected)){
			var roleid = selected[0].data.id;
			me.roleyespanel.setTitle("『"+selected[0].data.rolename+"』已关联的角色");
			me.rolenopanel.setTitle("『"+selected[0].data.rolename+"』无 关联的角色");
		};
		me.roleyespanel.getStore().load({params:{roleid : userid}});
		me.rolenopanel.getStore().load({params:{roleid : userid}});
	}
});