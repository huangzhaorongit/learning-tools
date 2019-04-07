Ext.define('app.controller.platform.basecodetype.BaseCodeTypeController',{
	extend: 'Ext.app.ViewController',
	alias: 'controller.basecodetype',
	init:function(){
    	this.gridpanel = this.lookupReference('gridPanel');
        this.detailPanel = this.lookupReference('detailPanel');
        this.basecodetype = this.lookupReference('basecodetypeEditInto');
    },
    
    onModuledblClick: function(gridpanel, record, tr, rowIndex, e, eOpts){
    	var me = this;
    	if(record){
    		me.detailPanel.setCollapsed(!me.detailPanel.getCollapsed());
    	}
    },
    
    onSelectionchange:function(gridpanel, selected, eOpts){
    	var me = this;
    	if(selected.length==0)return;
    	var record = selected[0];
    	me.basecodetype.getForm().loadRecord(record);
    	me.basecodetype.lookupReference('moduleoperate').getStore().reload({params:{codetype:record.data.codetype}});
    	me.detailPanel.setTitle("『"+record.data.coderemark+"』数据明细");
    },
    
    onRefresh:function(btn) {
         this.gridpanel.getStore().reload();
    },
    
    onUpdate: function(btn){
    	var me = this;
    	var selected = me.gridpanel.getSelection()[0];
    	if(selected==null){EU.toastWarn("选择一行您要修改的数据！");return;}
    	var title = "模块『"+selected.data.coderemark+"』维护";
    	var params = {codetype:selected.data.codetype};
    	PU.openModule({title:title,xtype:"basecodetypeedit",animateTarget:btn,width:800,height:650,params:params,scope:this,callback:function(result){
	 		if(!Ext.isEmpty(result)){
	 			this.gridpanel.getStore().reload();
    			var params =  {codetype:selected.data.codetype};
        		var url = "platform/basecodetype/getbasecodetypeinfo.do";
        		EU.RS({url:url,scope:me,msg:false,params:params,callback:function(result){
       		 		me.basecodetype.getForm().setValues(result);
        		}});
    			me.basecodetype.lookupReference('moduleoperate').getStore().reload({params:{codetype:selected.data.codetype}});
	 		}
	 	}});
    },
    
    onAdd: function(btn){
    	var title = "新增『基本数据』";
    	PU.openModule({title:title,xtype:"basecodetypeedit",animateTarget:btn,width:900,height:600,scope:this,callback:function(result){
	 		if(!Ext.isEmpty(result)){
	 			 this.gridpanel.getStore().reload();
	 		}
	 	}});
    },
    
    onDelete: function(btn){
    	var me = this;
    	var selected = me.gridpanel.getSelection()[0];
    	if(selected==null){EU.toastWarn("选择一行您要删除的数据！");return;}
    	var coderemark = selected.data.coderemark;
    	var codetype = selected.data.codetype;
    	EU.showMsg({message:"您确定要删除<font color='red'>『"+coderemark+"』</font>吗？",option:1,scope:this,callback:function(btn, text){
        	if(btn == 'yes'){
        		var url = "platform/basecodetype/deletefbasecodetype.do";
        		EU.RS({url:url,scope:this,params:{codetype:codetype},callback:function(result){
        			if(result == '1'){
        				EU.toastInfo("删除<font color='red'>『"+coderemark+"』</font>模块成功。");
        				 this.gridpanel.getStore().reload();
        			}
		    	}});
        	}
        }});
    }
});