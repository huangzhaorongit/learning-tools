Ext.define('app.view.bussiness.dashbord.Dashbord', {
	extend : 'Ext.panel.Panel',
	alternateClassName : 'Dashbord',
	xtype : 'dashbord',
	requires : ['app.controller.bussiness.dashbord.DashbordController'],
	controller : 'dashbord',
	referenceHolder : true,
	// bodyPadding : 0,
	border : true,

	layout : 'border',
	items : [{
		xtype : 'panel',
		layout : {
			type : 'vbox',
			align : 'stretch',
			pack : 'start'
		},
		region : 'center',
		defaults : {
			flex : 1,
			frame : true,
			border : true,
			margin : '0 1 0 0'
		},
		items : [{

			xtype : 'panel',
			height:'500',
			width : '100%',
			align : 'center',
			layout : 'fit',
			html : '<div id="echarts-line-chart" style="width:100%; height:100%;"/>',
			listeners : {
				afterRender : 'afterRenderCharts'
			}

		}, {
			xtype : 'panel',
			height:'500',
			width : '100%',
			align : 'center',
			layout : 'fit',
			html : '<div id="echarts-gauge-chart" style="width:100%; height:100%;"/>',

			listeners : {
				afterRender : 'afterRenderCharts'
			}

		}, {
			xtype : 'panel',
			width : '100%',
			height:'500',
			align : 'center',
			layout : 'fit',
			html : '<div id="echarts-funnel-chart" style="width:100%; height:100%;"/>',
			listeners : {
				afterRender : 'afterRenderCharts'
			}

		}]

	}]

});