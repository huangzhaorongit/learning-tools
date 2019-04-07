Ext.define('app.view.platform.frame.default.SkinSelect', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.skinSelect',
	margin : '0 10 0 0',
	border : false,
	height : 24,
	width : 36,
	layout : {
		type : 'table',
		columns : 3
	},
	initComponent : function() {
		this.defaults = {
			type : 0,
			xtype : 'themeselectpanel'
		};
		this.items = [ {
			type : 1,
			border : true,
			style:'position:relative;',
			html:'<span style="top:-5px;left:0px;position:absolute;color:#000000;cursor:default;">×</span>',
			bodyStyle : 'background:none;'
		},{
			color : '#35BAF6',
			bodyStyle : 'background:#35BAF6;'
		},{
			color : '#9B6614',
			bodyStyle : 'background:#9B6614;'
		},{
			border : true,
			style:'position:relative;',
			type : 2,
			html:'<span style="top:-5px;left:2px;font-size:8px;position:absolute;color:#000000;cursor:default;">?</span>',
			bodyStyle : 'background:none;'
		},{
			color : '#458B00',
			bodyStyle : 'background:#458B00;'
		}, {
			color : '#5E5E5E',
			bodyStyle : 'background:#5E5E5E;'
		}];
		this.callParent(arguments);
	}
});

Ext.define('app.view.platform.frame.default.ThemeSelectPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.themeselectpanel',
	border : false,
	margin : '1 1 1 1',
	width : 10,
	height : 10,
	theme : null,
	themetext : null,
	listeners : {
		afterrender : function(scope) {
			var html = scope.type==0?('界面色系:' + this.color):scope.type==1?"系统默认色系":"自定义颜色";
			Ext.create('Ext.tip.ToolTip', {target : this.id,bodyStyle : 'background:'+ this.color,html : html});
			Ext.get(this.id).on('click', function() {
				switch(scope.type){
					case 0 :{
						localStorage.setItem("color",scope.color.replace("#",""));
						PU.onAppUpdate({msg:"系统颜色设置成功。<br/>是否立即更新当前颜色("+scope.color+")？"});
						break;
					}
					case 1 :{
						localStorage.removeItem("color");
						PU.onAppUpdate({msg:"恢复系统默认颜色。<br/>是否立即更新？"});
						break;
					}
					case 2 :{
						scope.createColorWindows(localStorage.getItem("color"),function(value){
							localStorage.setItem("color",value.replace("#",""));
							PU.onAppUpdate({msg:"系统颜色设置成功。<br/>是否立即更新当前颜色("+value+")？"});
						});
						break;
					}
				}
			});
		}
	},
	
	createColorWindows:function(value,callback){
        var me = this;
	    if(me.colorWin==null){
	    	var colorField = Ext.create("Ext.ux.colorpick.Selector",{flex: 1});
	    	var buttons = [{text:'提交',scope:this,handler:function(){
				if(Ext.isFunction(me.fun))me.fun('#'+colorField.getValue());
				me.colorWin.hide();
			}},
			{text:'关闭',scope:this,handler:function(){me.colorWin.hide();}}];
	    	me.colorWin = Ext.create("Ext.window.Window",{closeAction :'hide',modal:true,resizable:false,header:false,items:[colorField],buttons:buttons});
	    	me.colorWin.show = function(value,callback){
			    me.fun = callback;
			    try{colorField.setValue(value);}catch(e){colorField.setValue('#FF0000');}
	    		if(me.colorWin.isVisible())return;
	    		me.colorWin.superclass.show.call(this);
	    	}
	    }
		me.colorWin.show(value,callback);
    }
});