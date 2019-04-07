Ext.define('app.controller.platform.workflow.WorkFlowTodoController',{
	extend: 'Ext.app.ViewController',
	alias: 'controller.workFlowTodo',
	
	init: function(){
		this.gridpanel = this.lookupReference('gridPanel');
	},
	
	onRefresh: function(){
		this.gridpanel.getStore().reload();
	},
	
	//预览待办
	onGridWorkFlowTodoHandler:function(view, rowIndex, colIndex, item, e, record, row){
		var title = "流程图『"+record.get("name")+"』";
		var params = {report:record.get("formkey"),taskid:record.get("id")};
		PU.openModule({title:title,xtype:"WFReportWin",params:params,width:1000,height:800});
	},
	
	//预览流程图
	onGridShowPicByProcessInstanceIdHandler:function(view, rowIndex, colIndex, item, e, record, row){
		var url = "platform/history/traceprocesspic.do?processinstanceid="+record.get("processinstanceid");
		var tools = [{iconCls:'x-fa fa-floppy-o',tooltip: '保存文件',
		    handler: function(event, toolEl, panelHeader) {
		    	PU.download({url:url,params:{type:"2"}});
		    }
		}];
		PU.openModule({title:"流程图『"+record.get("name")+"』",url:url,width:1000,height:800,scope:this,tools:tools});
	}
})