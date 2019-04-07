Ext.define('app.controller.platform.workflow.WorkFlowDeploymentController',{
	extend: 'Ext.app.ViewController',
	alias: 'controller.workFlowDeployment',
	
	init: function(){
		this.gridpanel = this.lookupReference('gridPanel');
	},
	
	onRefresh: function(){
		CU.log(this.gridpanel.getFieldDataAt('name').join());
		CU.log(this.gridpanel.getFieldDataAt(function(rec){
			return rec.get("name");
		}));
//		this.gridpanel.getStore().reload();
	},
	
	//上传流程压缩包
	onUpload:function(filefield, value){
		var me = this;
		var form = filefield.up('form').getForm();
		var suffix = CU.getFileSuffix(value).toLowerCase();
        if (suffix == 'zip' || suffix == 'rar' || suffix == 'bpmn' || suffix == 'xml') {
            form.submit({
                url: 'platform/workflow/deployment/importbyfile.do',
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
        	EU.toastWarn('只支持上传后缀名为zip、rar、bpmn、xml的文件。');
        }
	},
	
	//预览流程图
	onGridShowPicByModelId:function(view, rowIndex, colIndex, item, e, record, row){
		var url = "platform/workflow/deployment/showpicbydeploymentid.do?deploymentid="+record.get("deploymentid");
		var tools = [{iconCls:'x-fa fa-floppy-o',tooltip: '保存文件',
		    handler: function(event, toolEl, panelHeader) {
		    	PU.download({url:url,params:{type:"2"}});
		    }
		}];
		PU.openModule({title:"流程图『"+record.get("name")+"』",url:url,width:1000,height:800,scope:this,tools:tools});
	},
	
	//预览流程XML
	onGridshowXMLByModelId:function(view, rowIndex, colIndex, item, e, record, row){
		var url = "platform/workflow/deployment/showxmlbydeploymentid.do?deploymentid="+record.get("deploymentid");
		var tools = [{iconCls:'x-fa fa-floppy-o',tooltip: '保存文件',
		    handler: function(event, toolEl, panelHeader) {
		    	PU.download({url:url,params:{type:"2"}});
		    }
		}];
		PU.openModule({title:"流程XML『"+record.get("name")+"』",url:url,width:1000,height:800,scope:this,tools:tools});
	},
	
	//删除流程定义对象
	onGridRemovehandler:function(view, rowIndex, colIndex, item, e, record, row){
		EU.showMsg({title:'提示信息',message:"确定要删除流程定义【<font color='red'>"+record.get("name")+"</font>】吗？",option:1,callback:function(rt){
	    	if(rt=='yes'){
				var url = "platform/workflow/deployment/deleteprocessdefinition.do";
				EU.RS({url:url,scope:this,params:{deploymentid:record.get("deploymentid"),cascade:true},callback:function(result){
					if(!result.success){EU.toastErrorInfo('删除失败!');return;}
		    		EU.toastInfo("删除成功!");
					view.getStore().reload();
		    	}});
	    	}	
    	}});
	},
	
	//流程启动
	onGridWorkFlowStarthandler:function(key){
		EU.showMsg({title:'提示信息',message:"确定启动流程定义【<font color='red'>"+key+"</font>】吗？",option:1,callback:function(rt){
	    	if(rt=='yes'){
				var url = "platform/workflow/runtime/startprocessinstance.do";
				EU.RS({url:url,scope:this,params:{key:key,cascade:true},callback:function(result){
		    		EU.toastInfo("启动成功!");
		    	}});
	    	}	
    	}});
	},
	
	//流程转为模型
	onGridWorkFlowToModelhandler:function(view, rowIndex, colIndex, item, e, record, row){
		EU.showMsg({title:'提示信息',message:"确定将流程【<font color='red'>"+record.get("name")+"</font>】转换为模型吗？",option:1,callback:function(rt){
	    	if(rt=='yes'){
				var url = "platform/workflow/deployment/converttomodel.do";
				EU.RS({url:url,scope:this,params:{processdefinitionid:record.get("id"),cascade:true},callback:function(result){
					if(!result.success){EU.toastErrorInfo('转换失败!');return;}
		    		EU.toastInfo("转换成功!");
		    	}});
	    	}	
    	}});
	}
})