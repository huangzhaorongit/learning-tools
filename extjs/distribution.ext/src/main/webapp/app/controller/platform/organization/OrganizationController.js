Ext.define('app.controller.platform.organization.OrganizationController',{
	extend: 'Ext.app.ViewController',
	
	alias: 'controller.organization',
	
	init:function(){
    	this.treepanel = this.lookupReference('treePanel');
    },
    
    onMenudblClick: function(tree, record, tr, rowIndex, e, eOpts){
	   	var me = this;
   		if(record){
	   		tree.toggleOnDblClick = false;
	    	var selected = me.treepanel.getSelection()[0];
	    	var title = "编辑『"+selected.data.orgname+"』节点";
	    	var params = {orgid: selected.id};
	    	me.openModule(title,params,function(result){
		    	if(!Ext.isEmpty(result))me.treepanel.reloadSelectNode();
		   	});
   	    }
   	},
   	
   	onRowContextmenu: function(tree, record, tr, rowIndex, e, eOpts){
   		e.stopEvent();
		var me = this;
		menu = me.createMenu();
		menu.showAt(e.getXY());
   	},
   	
   	createMenu: function(){
   		var me = this;
   		if (!me.menu) {
        	me.menu = Ext.create('Ext.menu.Menu', {
			    items: [
			        {text: '插入子节点',iconCls:'x-fa fa-cogs',handler:function(btn,e){
		        	    var selected = me.treepanel.getSelection()[0];
		        	    var title = "插入『"+selected.data.orgname+"』子节点";
		        	    var params = {parentid:selected.id, companyname: selected.data.companyname};
		        	    me.openModule(title,params,function(result){
			        	    var node = selected.appendChild(result);
		        	    	me.treepanel.selectNode(node);
		        	    	me.saveMoveOrder(selected.childNodes);
		        	    });
		            }},
		            {text: '插入节点前',iconCls:'x-fa fa-reply',handler:function(btn,e){
		        	    var selected = me.treepanel.getSelection()[0];
		        	    var title = "插入到『"+selected.data.orgname+"』节点前";
		        	    var params = {parentid:selected.data.parentid, companyname: selected.data.companyname};
		        	    me.openModule(title,params,function(result){
		        	    	var node = selected.addNode(result,"before")
		        	    	me.treepanel.selectNode(node);
		        	    	me.saveMoveOrder(selected.parentNode.childNodes);
		        	    });
		            }},
		            {text: '插入节点后',iconCls:'x-fa fa-reply fa-flip-vertical',handler:function(btn,e){
		        	    var selected = me.treepanel.getSelection()[0];
		        	    var title = "插入到『"+selected.data.orgname+"』到节点后";
		        	    var params = {parentid:selected.data.parentid, companyname: selected.data.companyname};
		        	    me.openModule(title,params,function(result){
		        	    	var node = selected.addNode(result,"after");
		        	    	me.treepanel.selectNode(node);
		        	    	me.saveMoveOrder(selected.parentNode.childNodes);
		        	    });
		            }},
		            {text: '编辑节点',iconCls:'x-fa fa-pencil-square-o',handler:function(btn,e){
		        	   me.onUpdate();
		            }},
		            {text: '删除节点',iconCls:'x-fa fa-trash',handler:function(btn,e){
		        	    me.onDelete();
		            }
			    }]
			});
        }
         return me.menu;
   	},
   	
   	saveMoveOrder: function(childNodes){
   		var list = [];
    	Ext.each(childNodes,function(rec){
    		list.push({orgid:rec.id,parentid:rec.data.parentId});
    	});
    	var url = 'platform/organization/saveMoveOrder.do';
    	EU.RS({url:url,scope:this,msg:false,params:{list:list}});
   	},
   	
   	treeDrop: function(node, data, overModel, dropPosition, eOpts){
   		var parentNode = dropPosition=='append'? overModel:overModel.parentNode;
		this.saveMoveOrder(parentNode.childNodes);
   	},
   	
    onExpandAll:function(){
    	this.treepanel.expandAll();
    },
    
    onCollapseAll:function(){
    	this.treepanel.collapseAll();
    },
    
    onRefresh:function(btn) {
         this.treepanel.getStore().reload();
    },
    
    onUpdate:function(){
    	var me = this;
    	var selected = me.treepanel.getSelection()[0];
    	if(selected==null){EU.toastWarn("选择一行您要修改的数据！");return;}
    	var title = "编辑『"+selected.data.orgname+"』节点";
    	var params = {orgid: selected.id};
    	me.openModule(title,params,function(result){
	    	if(!Ext.isEmpty(result))me.treepanel.reloadSelectNode();
	   	});
    },
    
   	openModule: function(title, params, callback){
   		PU.openModule({title:title,xtype:'organizationEdit',width:500,params:params,scope:this,callback:function(result){
		    if(Ext.isEmpty(result))return;
    		if(Ext.isFunction(callback))callback.call(this,result)
    	}});
   	},
   	
   	onDelete: function(){
   		var me = this;
   		var selected = me.treepanel.getSelection()[0];
   		if(selected==null){EU.toastWarn("选择一行您要删除的数据！");return;}
		if(selected.childNodes.length>0){EU.toastWarn("请先删除子节点！");return;}
   		var orgid = selected.id;
   		var orgname = selected.data.orgname;
   		EU.showMsg({message:"您确定删除<font color='red'>『"+orgname+"』</font>吗？",option:1,scope:this,callback:function(btn, text){
        	if(btn == 'yes'){
        		var url = "platform/organization/deleteOrganization.do";
        		EU.RS({url:url,scope:this,params:{orgid:orgid},callback:function(result){
        			 if(result=='-1'){EU.toastWarn("请先删除子节点！");return;};
        			 EU.toastInfo("删除<font color='red'>『"+orgname+"』</font>菜单成功。");
					 selected.remove();
		    	}});
        	}
        }});
   	},
   	
    onChangeDock: function(btn){
   		var dock = btn.ownerCt.dock;
    	dock = dock=='left'?"right":'left';
    	btn.ownerCt.setDock(dock);
    	local.set("system_menu_dock",dock);
   	}
});