Ext.define('app.controller.platform.systemuser.SystemUserController', {
    extend: 'Ext.app.ViewController',
    
    alias:'controller.systemUser',
    
    rowdblclick: function(gridpanel, record, tr, rowIndex, e, eOpts){
    	var me = this;
    	if(record){
    		var title = "编辑『"+record.data.username+"』信息";
  	    	var params = {userid:record.data.userid};
    		me.openModule(title,params);
    	}
    },
    
    onAdd:function(btn){
    	this.openModule("新增人员",null,btn);
    },
    
    onUpdate:function(btn) {
    	var me = this;
    	var selecteds = this.gridpanel.getSelection();
    	if(selecteds.length==0 ||　selecteds.length>1){EU.toastWarn("请选择一行数据!");return null;}
    	var selected = selecteds[0];
  	    var title = "编辑『"+selected.data.username+"』信息";
  	    var params = {userid:selected.data.userid};
  	    me.openModule(title,params,btn);
   },
    
    onDelete:function(btn){
	   var me = this;
       var selecteds = this.gridpanel.getSelection();
       if(selecteds.length==0){EU.toastWarn("最少选择一行数据!");return null;}
       var usernames = [];
       var userids = [];
       Ext.each(selecteds,function(rec){
       		usernames.push(rec.data.username);
       		userids.push({userid:rec.data.userid});
       });
       EU.showMsg({message:"你确定删除<font color='red'>『"+usernames.join(",")+"』</font>吗？",option:1,scope:this,callback:function(btn, text){
       		if(btn == 'yes'){
	       		var url = "platform/systemuser/delete.do";
	       		EU.RS({url:url,scope:this,params:{users:userids},callback:function(result){
	       			EU.toastInfo("删除<font color='red'>『"+usernames.join(",")+"』</font>用户成功。");
	       			me.gridpanel.getStore().remove(selecteds);
			    }});
       		}
       }});
    },
    
    onRefresh:function(btn){
    	this.gridpanel.getStore().reload();
    },
    
    openModule:function(title,params,btn){
    	PU.openModule({title:title,xtype:"systemUserEdit",width:500,params:params,animateTarget:btn,scope:this,callback:function(result){
		    if(Ext.isEmpty(result))return;
    		this.gridpanel.getStore().reload();
    	}});
    },
    
    init:function(){
    	this.gridpanel = this.lookupReference('gridPanel');
    }
});