Ext.define('app.controller.platform.systemmodule.SystemModuleController', {
    extend: 'Ext.app.ViewController',
    
    alias:'controller.systemModule',
    
    onAdd:function(btn) {
	     var tree = this.treepanel;
    	 if(btn.type==0){ //0=新增,1=新增根节点
	 	 	var selected = this.getGridSelectData();
	        if(selected==null)return;
	        if(selected.data.moduletype!='00'){EU.toastWarn("请选择目录节点!");return;}
	        var title = "新增『"+selected.data.modulename+"』子节点";
    	 	PU.openModule({title:title,xtype:"systemModuleEdit",width:800,height:650,animateTarget:btn,params:{parentid:selected.id},scope:this,callback:function(result){
    	 		this.treepanel.reloadSelectNode(result,true);
	 	 	}});
    	 }else{
    	 	PU.openModule({title:"新增根节点",xtype:"systemModuleEdit",width:800,height:650,animateTarget:btn,params:{parentid:'00'},scope:this,callback:function(result){
		 	 	this.treepanel.reloadRootNode(result);
		 	}});
    	 }
    },
    
    onUpdate:function(btn) {
         var selected = this.getGridSelectData();
         if(selected==null)return;
	     var title = "模块『"+selected.data.modulename+"』维护";
    	 PU.openModule({title:title,xtype:"systemModuleEdit",animateTarget:btn,width:800,height:650,params:{moduleid:selected.id},scope:this,callback:function(result){
	 	 	  if(!Ext.isEmpty(result))this.treepanel.reloadSelectNode();
	 	 }});
    },
    
    onDelete:function(){
    	var view = this.treepanel;
        var selected = this.getGridSelectData();
        if(selected==null)return;
        if(selected.childNodes.length>0){EU.toastWarn("请先删除子节点！");return;}
        var modulename = selected.data.modulename;
        var moduleid = selected.id;
        EU.showMsg({message:"你去确定删除<font color='red'>『"+modulename+"』</font>吗？",option:1,scope:this,callback:function(btn, text){
        	if(btn == 'yes'){
        		var url = "platform/systemmodule/delete.do";
        		EU.RS({url:url,scope:this,params:{moduleid:moduleid},callback:function(result){
        			 if(result=='-1'){EU.toastWarn("请先删除子节点！");return;};
        			 EU.toastInfo("删除<font color='red'>『"+modulename+"』</font>模块成功。");
        			 view.getStore().remove(selected);
		    	}});
        	}
        }});
    },
    
    onRefresh:function(btn) {
         this.treepanel.getStore().reload();
    },
    
    onExpandAll:function(){
    	this.treepanel.expandAll();
    },
    
    onCollapseAll:function(){
    	this.treepanel.collapseAll();
    },
    
    onSelectionchange:function(tree, selected, eOpts){
    	var me = this;
    	if(selected.length==0)return;
    	var record = selected[0];
    	me.moduleinfo.getForm().loadRecord(record);
    	me.moduleinfo.lookupReference('moduleoperate').getStore().reload({params:{moduleid:record.id}});
    	me.moduleinfo.lookupReference('moduledatastate').getStore().reload({params:{moduleid:record.id}});
    	me.detailPanel.setTitle("『"+record.data.modulename+"』数据明细");
    },
    
    onModuledblClick: function(tree,record, tr, rowIndex, e, eOpts ) {
    	var me = this;
        if (record) {
        	tree.toggleOnDblClick = false;
        	me.detailPanel.setCollapsed(!me.detailPanel.getCollapsed());
        }
    },
    
    getGridSelectData:function(){
    	var view = this.treepanel;
    	var selecteds = view.getSelection();
    	if(selecteds.length==0){EU.toastWarn("请选择一行数据！");return null;}
        return selecteds[0];
    },
    
    init:function(){
    	this.treepanel = this.lookupReference('treePanel');
        this.moduleinfo = this.lookupReference('moduleinfo');
        this.detailPanel = this.lookupReference('detailPanel');
    }
});