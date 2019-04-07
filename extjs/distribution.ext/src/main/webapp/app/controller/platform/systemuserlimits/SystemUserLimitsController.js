Ext.define('app.controller.platform.systemuserlimits.SystemUserLimitsController',{
	extend: 'Ext.app.ViewController',
	alias: 'controller.systemUserLimits',
	init:function(){
    	this.gridpanel = this.lookupReference('gridPanel');
        this.treePanel = this.lookupReference('treePanel');
    },
    
    gridclick: function(gridpanel, record, tr, rowIndex, e, eOpts){
    	var me = this;
    	if(record){
    		me.treePanel.setTitle("系统模块『"+record.data.username+"』");
    		me.treePanel.userid = record.data.userid;
    		me.treePanel.getStore().reload({params:{userid:record.data.userid}});
    	}
    },
    
    onSearch:function(){
        var me=this;
        var refs = me.getReferences();
        var text = refs.textpanel.rawValue;
        var url = "platform/systemuserlimits/getsystemuserlist.do";
        me.gridpanel.getStore().reload({params:{userCode:encodeURIComponent(text)}});
        me.treePanel.setCollapsed(true);
    },
    
    onDelete:function(){
    	var me = this;
    	var textfield = me.getReferences().textpanel;
    	textfield.setValue("");
    },
    
    saveLimit:function(btn, e){
    	var me = this;
    	var gridSel = me.gridpanel.getSelection();
    	var userid =  me.treePanel.userid;
    	if(Ext.isEmpty(userid))return;
    	EU.showMsg({title:'提示信息',message:"确定保存当前配置权限吗？",option:1,callback:function(rt){
    		if(rt!='yes')return;
    		var list = [];
	    	var treeNodes = me.treePanel.getChecked();
	    	Ext.each(treeNodes,function(rec){
	    		if(rec.data.disabled)return;
	    		var data = rec.data;
	    		list.push({userid:userid,menuid:data.param1,operatecode:data.param2});
	    	});
	    	var url = "platform/systemuserlimits/savelimit.do";
	    	EU.RS({url:url,scope:this,msg:false,params:{list:list,userid:userid},callback:function(result){
	         	EU.toastInfo("【保存成功】");
    			me.lookupReference('saveBtn').hide();
	        }});	
    	}});
    },
    
    checkchange:function(node,check){
    	var me = this;
    	this.lookupReference('saveBtn').show();
    	if(node.data.param3 =='01'){ //如果按钮选择，菜单必须选择
    		me.eachParentNode(node,true);
    	}else{
    		node.eachChildNode(function(child){
    			if(!child.data.disabled){
		    		child.set("checked",check);
		    	}
    		});
    	}
    },
    
    /**
     * 当前节点的父级节点
     * @param {} node
     * @param {} check
     */
    eachParentNode:function(node,check){
    	var me = this;
    	var pnode = node.parentNode;
    	if(pnode.data.checked != null){
    		pnode.set("checked",check);
    	}else{
    		me.eachParentNode(pnode,check);
    	}
    }
});