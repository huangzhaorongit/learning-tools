Ext.define('app.controller.bussiness.device.DeviceController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.device',

	afterRender : function() {

		var e = echarts.init(document.getElementById("echarts-line-chart")), a = {
			title : {
				text : "未来一周气温变化"
			},
			tooltip : {
				trigger : "axis"
			},
			legend : {
				data : ["最高气温", "最低气温"]
			},
			grid : {
				x : 40,
				x2 : 40,
				y2 : 24
			},
			calculable : !0,
			xAxis : [{
						type : "category",
						boundaryGap : !1,
						data : ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
					}],
			yAxis : [{
						type : "value",
						axisLabel : {
							formatter : "{value} °C"
						}
					}],
			series : [{
						name : "最高气温",
						type : "line",
						data : [11, 11, 15, 13, 12, 13, 10],
						markPoint : {
							data : [{
										type : "max",
										name : "最大值"
									}, {
										type : "min",
										name : "最小值"
									}]
						},
						markLine : {
							data : [{
										type : "average",
										name : "平均值"
									}]
						}
					}, {
						name : "最低气温",
						type : "line",
						data : [1, -2, 2, 5, 3, 2, 0],
						markPoint : {
							data : [{
										name : "周最低",
										value : -2,
										xAxis : 1,
										yAxis : -1.5
									}]
						},
						markLine : {
							data : [{
										type : "average",
										name : "平均值"
									}]
						}
					}]
		};
		e.setOption(a), $(window).resize(e.resize);
	},

	treeSelectionchange : function(tree, records, item) {
		var me = this;
		if (records.length == 0)
			return;
		var data = records[0].data;
		var id = data.id;

		 me.gridpanel.load({parentId:id});
		// me.gridpanel.load({id:id});
		// psLineChar =
		// echarts.init(document.getElementById('echarts-line-chart'));
		// psLineChar.clear();
		// psLineChar.showLoading({text: '正在努力的读取数据中...'});
		//
		// psLineChar.hideLoading();
		// // psLineChar.setOption(null);
		// window.onresize = psLineChar.resize;

		var e = echarts.init(document.getElementById("echarts-line-chart")), a = {
			title : {
				text : "未来一周气温变化"
			},
			tooltip : {
				trigger : "axis"
			},
			legend : {
				data : ["最高气温", "最低气温"]
			},
			grid : {
				x : 40,
				x2 : 40,
				y2 : 24
			},
			calculable : !0,
			xAxis : [{
						type : "category",
						boundaryGap : !1,
						data : ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
					}],
			yAxis : [{
						type : "value",
						axisLabel : {
							formatter : "{value} °C"
						}
					}],
			series : [{
						name : "最高气温",
						type : "line",
						data : [11, 11, 15, 13, 12, 13, 10],
						markPoint : {
							data : [{
										type : "max",
										name : "最大值"
									}, {
										type : "min",
										name : "最小值"
									}]
						},
						markLine : {
							data : [{
										type : "average",
										name : "平均值"
									}]
						}
					}, {
						name : "最低气温",
						type : "line",
						data : [1, -2, 2, 5, 3, 2, 0],
						markPoint : {
							data : [{
										name : "周最低",
										value : -2,
										xAxis : 1,
										yAxis : -1.5
									}]
						},
						markLine : {
							data : [{
										type : "average",
										name : "平均值"
									}]
						}
					}]
		};
		e.setOption(a), $(window).resize(e.resize);
	},

	tbarclick : function(btn) {

	},

	refresh : function(event, toolEl, panelHeader) {
		this.treePanel.getStore().reload();
		this.gridpanel.load({
					id : ""
				});
	},

	rowdblclick : function(grid, rowIndex, e) {

	},




	    tbarclick:function(btn){
    	var me = this;
    	var gridSel = me.gridpanel.getSelection();
    	var treeSel = me.treePanel.getSelection();
    	if(btn.type==1){//添加
    		if(treeSel.length==0){EU.toastWarn("<font color='red'>【请选择一个设备】</font>");return;}
    		var title = "添加设备到『"+treeSel[0].data.text+"』";
    		var params = {parentId:treeSel[0].data.id};
    		me.openModule(title,params,function(result){
    			if(Ext.isEmpty(result))return;
				me.gridpanel.getStore().reload({params:{parentId:result.parentId}});
				var tnode = me.treePanel.getStore().getNodeById(result.parentId);
				me.treePanel.getSelectionModel().select(tnode);
			});
    	}else if(btn.type==2){
    		if(gridSel.length>1){ EU.toastWarn("<font color='red'>【一次只能修改一条数据】</font>");return;}
	    	if(gridSel.length==0){EU.toastWarn("<font color='red'>【请选择一条数据】</font>");return;}
    		var title = "设备『"+gridSel[0].data.name+"』信息维护";
    		var params = {id:gridSel[0].data.id};
    		me.openModule(title,params,function(result){
    			if(Ext.isEmpty(result))return;
    			var params = treeSel.length==0?null:{parentId:result.parentId};
    			me.gridpanel.getStore().reload({params:params});
		 	});
    	}else if(btn.type==3){
    		var ids=[],name='';
			if(gridSel.length==0){EU.toastWarn("<font color='red'> 【请选择至少一条数据】</font>");return;}
			for(var i=0;i<gridSel.length;i++){
				var data = gridSel[i].data;
				ids.push({id:data.id});
				name+=data.name+"、";
	    	}
	    	if(name.length>0)name=name.substring(0,name.length-1);
			EU.showMsg({title:'提示信息',message:"确定要删除设备【<font color='red'>"+name+"</font>】吗？",option:1,callback:function(rt){
		    	if(rt=='yes'){
					var url = 'bussiness/device/delete.do';
		    		EU.RS({url:url,scope:this,msg:false,params:{ids:ids},callback:function(result){
			       		 EU.toastInfo("设备【<font color='red'>"+name+"</font>】删除成功");
			       		 me.gridpanel.getStore().remove(gridSel);
			        }});
		    	}
	    	}});
    	}
    },

	openModule : function(title, params, callback) {
		PU.openModule({
					title : title,
					xtype : "deviceEdit",
					width : 600,
					params : params,
					scope : this,
					callback : callback
				});
	},

	init : function() {
		this.gridpanel = this.lookupReference('gridpanel');
		this.treePanel = this.lookupReference('treePanel');

	}
});
