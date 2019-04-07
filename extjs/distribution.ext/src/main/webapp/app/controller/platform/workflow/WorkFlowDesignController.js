Ext.define('app.controller.platform.workflow.WorkFlowDesignController',{
	extend: 'Ext.app.ViewController',
	alias: 'controller.workFlowDesign',
	
	init: function(){
		this.gridpanel = this.lookupReference('gridPanel');
	},
	
	onRefresh: function(){
		this.gridpanel.getStore().reload();
	},
	
	//创建流程模型
	onAdd:function(btn){
		if(!btn.winModel){
			btn.winModel = this.createModel();
		}
		btn.winModel.show();
	},
	
	//上传模型
	onUpload:function(filefield, value){
		var me = this;
		var form = filefield.up('form').getForm();
		var suffix = CU.getFileSuffix(value).toLowerCase();
        if (suffix == 'bpmn' || suffix == 'xml') {
            form.submit({
                url: 'platform/workflow/model/importmodel.do',
                waitMsg: '文件上传中..',
                timeout:60,
                callback: function(options, success, response) {
                	var result = CU.toObject(response.responseText);
                	if(!result.success){EU.toastErrorInfo('文件上传失败!');return;}
                	EU.toastInfo('文件上传成功!');
                	me.gridpanel.getStore().reload();
                }
            });
        }else{
        	EU.toastWarn('只支持上传后缀名为bpmn、xml的文件。');
        }
	},
	
	//预览流程图
	onGridShowPicByModelId:function(view, rowIndex, colIndex, item, e, record, row){
		var url = "platform/workflow/model/showpicbymodelid.do?id="+record.get("id");
		var tools = [{iconCls:'x-fa fa-floppy-o',tooltip: '保存文件',
		    handler: function(event, toolEl, panelHeader) {
		    	PU.download({url:url,params:{type:"2"}});
		    }
		}];
		PU.openModule({title:"流程图『"+record.get("name")+"』",url:url,width:1000,height:800,scope:this,tools:tools});
	},
	
	//预览流程XML
	onGridshowXMLByModelId:function(view, rowIndex, colIndex, item, e, record, row){
		var url = "platform/workflow/model/showxmlbymodelid.do?id="+record.get("id");
		var tools = [{iconCls:'x-fa fa-floppy-o',tooltip: '保存文件',
		    handler: function(event, toolEl, panelHeader) {
		    	PU.download({url:url,params:{type:"2"}});
		    }
		}];
		PU.openModule({title:"流程XML『"+record.get("name")+"』",url:url,width:1000,height:800,scope:this,tools:tools});
	},
	
	//修改流程模型
	onGridUpdateWorkFlowhandler:function(view, rowIndex, colIndex, item, e, record, row){
		var url = "resources/html/workflow/modeler.html?modelId="+record.get("id");
		window.open(url);
	},
	
	//删除流程模型
	onGridRemovehandler:function(view, rowIndex, colIndex, item, e, record, row){
		EU.showMsg({title:'提示信息',message:"确定要删除流程模型【<font color='red'>"+record.get("name")+"</font>】吗？",option:1,callback:function(rt){
	    	if(rt=='yes'){
				var url = "platform/workflow/model/deletemodel.do";
				EU.RS({url:url,scope:this,params:{id:record.get("id")},callback:function(result){
					if(!result.success){EU.toastErrorInfo('删除失败!');return;}
		    		EU.toastInfo("删除成功!");
					view.getStore().removeAt(rowIndex);
		    	}});
	    	}	
    	}});
	},
	
	onGridDeployWorkFlowhandler:function(view, rowIndex, colIndex, item, e, record, row){
		EU.showMsg({title:'提示信息',message:"确定要发布流程模型【<font color='red'>"+record.get("name")+"</font>】吗？",option:1,callback:function(rt){
	    	if(rt=='yes'){
				var url = "platform/workflow/model/deploybymodelid.do";
				EU.RS({url:url,scope:this,params:{id:record.get("id")},callback:function(result){
					if(!result.success){EU.toastErrorInfo('发布失败!');return;}
		    		EU.toastInfo("发布成功!");
		    	}});
	    	}	
    	}});
	},
	
	//创建流程模型窗口
	createModel:function(){
		 var p_form = Ext.create("Ext.form.Panel",{defaults: {width: '100%',labelAlign:'left',labelWidth:60},
		 	items:[
		 	 	{fieldLabel: '名称',xtype: 'textfield',name: 'name',allowBlank:false}, 
        		{fieldLabel: 'key',xtype: 'textfield',name: 'key',allowBlank:false},
        		{fieldLabel: '描述',xtype: 'textareafield',name: 'description'}
		 	]});
		 var form  = p_form.getForm();
		 var winModel = Ext.create('Ext.window.Window', {title: '添加工作流模型',height:350,width:500,
			    layout: 'fit',modal:true,closeAction:'hide',items: p_form,bodyPadding:15,
			    buttons: [{
		            text: '提交',
		            handler: function(){
		            	var url = "platform/workflow/model/createmodel.do";
		            	var params = form.getValues();
				    	EU.RS({url:url,scope:this,params:params,callback:function(result){
				    		 if(result.success){
				    		 	var url = "resources/html/workflow/modeler.html?modelId="+result.data;
								window.open(url);
								winModel.hide();
				    		 	return;
				    		 }
				    		 EU.toastErrorInfo("提交失败!");
				    	}});
		            }
		        }, {
		            text: '关闭',
		            handler: function(){winModel.hide();}
		        }]
		 });
		 winModel.on("show",function(){
		 	form.reset();
		 })
		 return winModel;
	}
})