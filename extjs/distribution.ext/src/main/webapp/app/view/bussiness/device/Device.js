

Ext.define('app.view.bussiness.device.Device', {
	extend : 'Ext.panel.Panel',
	alternateClassName : 'Device',
	xtype : 'device',
	requires : ['app.controller.bussiness.device.DeviceController',
			'app.view.bussiness.device.DeviceEdit'],
	controller : 'device',
	referenceHolder : true,
	layout : 'border',
	items : [{
				xtype : 'treepanel',
				title : '',
				region : 'west',
				layout : 'fit',
				reference : 'treePanel',
				split : true,
				collapsible : true,
				width : 250,
				useArrows : true,
				store : {
					autoLoad : true,
					url : 'bussiness/device/gettreelist.do'
				},
				tools : [{
							type : 'expand',
							handler : function(event, toolEl, panelHeader) {
								this.up('treepanel').collapseAll();
							}
						}, {
							type : 'collapse',
							handler : function() {
								this.up('treepanel').expandAll();
							}
						}, {
							type : 'refresh',
							handler : 'refresh'
						}],
				viewConfig : {
					plugins : {
						ptype : 'treeviewdragdrop',
						ddGroup : 'DragDropGroup',
						enableDrag : false
						// 配置tree不允许拖动
					},
					listeners : {
						beforedrop : 'beforedrop'
					}
				},
				listeners : {
					selectionchange : 'treeSelectionchange'
				}
			}, {
				xtype : 'panel',
				layout : {
					type : 'vbox',
					align : 'stretch',
					pack : 'start'
				},
				region : 'center',
				reference : 'contentPanel',
				items : [ {
							flex : 2,
							xtype : 'grid',
							paging : true,
							reference : 'gridpanel',
							selType : 'checkboxmodel',
							store : {
								storeId : "gridStore",
								url : 'bussiness/device/getlist.do',
								autoLoad : true,
								remoteSort : true
							},
							tbar : [{
										xtype : 'button',
										text : '添加11',
										reference : 'add',
										iconCls : 'x-fa fa-plus',
										handler : 'tbarclick',
										type : 1
									}, {
										xtype : 'button',
										text : '修改',
										reference : 'update',
										iconCls : 'x-fa fa-pencil-square-o',
										handler : 'tbarclick',
										type : 2
									}, {
										xtype : 'button',
										text : '删除',
										reference : 'delete',
										iconCls : 'x-fa fa-trash',
										handler : 'tbarclick',
										type : 3
									}, {
										xtype : 'searchfield',
										emptyText : '名称/编号',
										paramName : 'name',
										hasSearch : true,
										width : 200,
										store : "gridStore"
									}],

							viewConfig : {
								plugins : {
									ptype : 'gridviewdragdrop',
									ddGroup : 'DragDropGroup'// 此处代表拖动的组
									// 拖动组件与放置组件要同属一组才能实现相互拖放
								}
							},
							columns : [{
										text : '编号',
										sortable : false,
										width : 60,
										xtype : 'rownumberer',
										dataIndex : 'deviceNo',
										align : 'center'
									}, {
										text : '设备名称',
										width : 100,
										dataIndex : 'name',
										type : 'key',
										qcfg : {
											type : 'string'
										}
									}, {
										text : '地址',
										width : 100,
										dataIndex : 'address',
										align : 'center',
										qcfg : {
											type : 'string'
										}
									}, {
										text : '经度',
										width : 150,
										dataIndex : 'longitude',
										align : 'left',
										qcfg : {
											type : 'string'
										}
									}, {
										text : '纬度',
										width : 60,
										dataIndex : 'latitude',

										align : 'center',
										qcfg : {
											type : 'string'
										}
									}, {
										text : '纬度',
										width : 60,
										dataIndex : 'code',

										align : 'center',
										qcfg : {
											type : 'string'
										}
									}, {
										text : '备注信息',
										width : 100,
										dataIndex : 'remarks',
										align : 'center'
									}, {
										text : '是否有效',
										width : 80,
										dataIndex : 'enable',
										sortable : true

									}],
							listeners : {
								rowdblclick : 'rowdblclick'
							}

						},{
							html : '<div id="echarts-line-chart" style="width:100%;height:100%;">报表视图</div>',
							flex : 1
						}]

			}]
});
