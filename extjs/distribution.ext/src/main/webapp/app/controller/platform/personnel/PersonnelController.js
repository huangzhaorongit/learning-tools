Ext.define('app.controller.platform.personnel.PersonnelController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.personnel',
    
    treeSelectionchange:function(tree, records, item) {
    	var me = this;
    	if(records.length==0)return;
     	var data = records[0].data;
     	var id = data.id;
     	me.gridpanel.load({departmentid:id});
    },
    
    tbarclick:function(btn){
    	var me = this;
    	var gridSel = me.gridpanel.getSelection();
    	var treeSel = me.treePanel.getSelection();
    	if(btn.type==1){//添加
    		if(treeSel.length==0){EU.toastWarn("<font color='red'>【请选择一个部门】</font>");return;}
    		var title = "添加员工到『"+treeSel[0].data.text+"』部门";
    		var params = {departmentid:treeSel[0].data.id};
    		me.openModule(title,params,function(result){
    			if(Ext.isEmpty(result))return;
				me.gridpanel.getStore().reload({params:{departmentid:result.departmentid}});
				var tnode = me.treePanel.getStore().getNodeById(result.departmentid);
				me.treePanel.getSelectionModel().select(tnode);
			});
    	}else if(btn.type==2){
    		if(gridSel.length>1){ EU.toastWarn("<font color='red'>【一次只能修改一条数据】</font>");return;}
	    	if(gridSel.length==0){EU.toastWarn("<font color='red'>【请选择一条数据】</font>");return;}
    		var title = "员工『"+gridSel[0].data.personnelname+"』信息维护";
    		var params = {personnelid:gridSel[0].data.personnelid};
    		me.openModule(title,params,function(result){
    			if(Ext.isEmpty(result))return;
    			var params = treeSel.length==0?null:{departmentid:result.departmentid};
    			me.gridpanel.getStore().reload({params:params});
		 	});
    	}else if(btn.type==3){
    		var ids=[],name='';
			if(gridSel.length==0){EU.toastWarn("<font color='red'> 【请选择至少一条数据】</font>");return;}
			for(var i=0;i<gridSel.length;i++){
				var data = gridSel[i].data;
				ids.push({personnelid:data.personnelid});
				name+=data.personnelname+"、";
	    	}
	    	if(name.length>0)name=name.substring(0,name.length-1);
			EU.showMsg({title:'提示信息',message:"确定要删除员工【<font color='red'>"+name+"</font>】吗？",option:1,callback:function(rt){
		    	if(rt=='yes'){
					var url = "platform/personnel/delete.do";
		    		EU.RS({url:url,scope:this,msg:false,params:{ids:ids},callback:function(result){
			       		 EU.toastInfo("员工【<font color='red'>"+name+"</font>】删除成功");
			       		 me.gridpanel.getStore().remove(gridSel);
			        }});	
		    	}	
	    	}});
    	}
    },
    
    beforedrop: function(node, data, overModel, dropPosition, dropHandlers) {//注：此处事件是gridviewdragdrop 的放置监听事件  
    	var me = this;
        dropHandlers.wait = true; 
        var ids=[],name="";
        var departmentid = overModel.id;
        Ext.each(data.records,function(rec){
        	var data = rec.data;
        	ids.push({personnelid:data.personnelid,departmentid:departmentid});
        	name+=data.personnelname+"、";
        });
	    if(name.length>0)name=name.substring(0,name.length-1);
        EU.showMsg({title:'提示信息',message:"确定要将员工【<font color='red'>"+name+"</font>】移动到【<font color='red'>"+node.textContent+"</font>】吗？",option:1,callback:function(rt){
	    	if(rt=='yes'){
		        var url="platform/personnel/removetot.do";
		    	EU.RS({url:url,scope:this,msg:false,params:{ids:ids},callback:function(result){
		      		 EU.toastInfo("员工【<font color='red'>"+name+"</font>】成功移动到【<font color='red'>"+node.textContent+"</font>】");
					 me.gridpanel.getStore().remove(data.records);
		        }});	
			}else{
			    dropHandlers.cancelDrop(); 
			}	
    	}});
    }, 
    
    refresh:function(event, toolEl, panelHeader){
     	this.treePanel.getStore().reload();
     	this.gridpanel.load({departmentid:""});
    },
    
    rowdblclick:function(grid, rowIndex, e){
    	var me = this;
    	var title = "员工『"+rowIndex.data.personnelname+"』信息维护";
		var params = {personnelid:rowIndex.data.personnelid};
		me.openModule(title,params,function(result){
			if(Ext.isEmpty(result))return;
			var params = {departmentid:rowIndex.data.departmentid};
			me.gridpanel.getStore().reload({params:params});
	 	});
    },
    
    openModule:function(title,params,callback){
    	PU.openModule({title:title,xtype:"personnelEdit",width:600,params:params,scope:this,callback:callback});
    },
    
    init:function(){
    	this.gridpanel = this.lookupReference('gridpanel');
    	this.treePanel = this.lookupReference('treePanel');
    }
});