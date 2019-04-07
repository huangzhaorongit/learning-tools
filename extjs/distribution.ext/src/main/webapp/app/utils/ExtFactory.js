
/**
* 辅组方法
*/
Ext.define('app.utils.ExtFactory', {
	 alternateClassName: 'EF',
	 statics: {
	 	 /**
		 * 参考示例 [{field:TF_cdt0,labelWidth:0.2,columnWidth:0.5},{field:TF_cdt2,labelWidth:0.2,columnWidth:0.5}]
		 * 根据组件集合创建一行(column)
		 * @param [] arr 组件集合 {field:TF_cdt0}
		 * @param {} trconfig 行样式
		 * @return {}
		 */
		getTrPanel: function (arr,cfg){
		 	var trconfig = {};
		 	Ext.apply(trconfig,cfg);
			var columnWidth = 1/arr.length;
			var labelWidth = trconfig.labelWidth;
			var items = [];
			for (var i = 0; i < arr.length; i++) {
			 	var item  = arr[i];
				item.columnWidth = item.columnWidth || columnWidth;
				item.labelWidth =  item.labelWidth || labelWidth || 90;
				item.xtype = item.xtype || "textfield";
				items.push(item);
			}
			trconfig.layout = "column";
			trconfig.columnWidth = 1.0;
			trconfig.bodyPadding = 5;
			trconfig.items = items;
			trconfig.xtype = "panel";
			trconfig.anchor= '100%';
			return trconfig;
		},
		 
		getTabelPanel: function(arr,cfg){
		 	var p_items = [];
			for (var i = 0; i < arr.length; i++) {
		 		var items = arr[i];
		 		p_items.push(EF.getTrPanel(items,cfg));
		 	}
		 	return p_items;
		}
	}
});