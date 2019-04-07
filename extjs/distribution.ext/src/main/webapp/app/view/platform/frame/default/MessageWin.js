Ext.define('app.view.platform.frame.default.MessageWin', {
    extend: 'Ext.window.Window',
    alternateClassName: 'systemMessageWin',
    xtype:'systemMessageWin',
    layout:'accordion',
    border: false,
    resizable:true,
	closeAction :'hide',
    width:250,
    height:600,
    title:"消息管理",
    userWins:{}, //已经打开的聊天窗口
    onMessageChange:null, //消息数量变更执行
    listeners:{
    	show:function(){
    		this.initData();
    	}
    },
    initComponent : function() {
    	var me = this;
    	var imageTpl = new Ext.XTemplate(
		    '<tpl for=".">',
		        '<div style="margin-bottom: 10px;" class="thumb-wrap">',
			    '    <table border="0" width="100%">',
			    '        <tr>',
			    '            <td width="36" height="40" align="center" valign="middle" rowspan="2">',
			    '               <img src="platform/personnel/getphoto.do?imgsize=36&photoid={icon}" style="border-radius:10%;" align="middle" width="36" height="36" border="0" vspace="0">',
			    '            </td>',
			    '            <td>{text}</td>',
			    '			 <td width="36" align="center" valign="middle" rowspan="2">',
			    '				<tpl if="param4"><div class="circle" style="width:20px;height:20px;line-height:20px;"><font color="white"><b>{param4}</b></font></div></tpl>',
		    	'			 </td>',
			    '        </tr>',
			    '        <tr><td>{attributes.departmentname}</td></tr>',
			    '    </table>',
		        '</div>',
		    '</tpl>'
		);
		me.onlineUsers = Ext.create("Ext.view.View",{
			selectedCls:'x-grid-item-selected',loadingText:'刷新在线用户...',loadMask:false,
			listeners: {itemdblclick:function(thiz, record, item, index, e, eOpts){
				var win = me.userWins[record.id];
				if(win==null){
					me.chatWindow(thiz, record, item, index, e, eOpts);
				}else{
					win.restore();
            		win.toFront();
				}
			}},
			store:{proxy: {type: 'ajax', url: 'platform/systemframe/getonlineusers.do',reader: {type: 'json'}},
				listeners:{
					datachanged:function(thiz, eOpts){
						var tipsnum = 0;
						Ext.each(thiz.data.items,function(item){
							if(Ext.isNumber(item.data.param4))tipsnum+=item.data.param4;
						});
						me.onMessageChange(tipsnum);
						me.onlineUsers.up("panel").setTitle("在线用户(<font color='red'><b>"+thiz.data.items.length+"</b></font>)")
					}
	    		}
    		},
			tpl:imageTpl,itemSelector:'div.thumb-wrap'
		});
    	me.offlineUsers = Ext.create("Ext.view.View",{
			selectedCls:'x-grid-item-selected',loadingText:'刷新离线用户...',
			listeners: {itemdblclick:function(thiz, record, item, index, e, eOpts){
				var win = me.userWins[record.id];
				if(win==null){
					me.chatWindow(thiz, record, item, index, e, eOpts);
				}else{
					win.restore();
            		win.toFront();
				}
			}},
			store:{proxy: {type: 'ajax', url: 'platform/systemframe/getofflineusers.do',reader: {type: 'json'}},
				listeners:{
					datachanged:function(thiz, eOpts){
						var tipsnum = 0;
						Ext.each(thiz.data.items,function(item){
							if(Ext.isNumber(item.data.param4))tipsnum+=item.data.param4;
						});
						me.onMessageChange(tipsnum);
					}
	    		}
    		},
			tpl:imageTpl,itemSelector:'div.thumb-wrap'
		});
    	me.items = [{title:'在线用户',autoScroll:true,items:[me.onlineUsers]},
    				{title:'离线用户',autoScroll:true,items:[me.offlineUsers]}];
    	me.tools = [{type : 'refresh',tooltip:'刷新用户',handler : function(event, toolEl, panelHeader) {
			if(WS.online){
				me.onlineUsers.getStore().reload();
				me.offlineUsers.getStore().reload();
			}
		}}],
		this.initStart();
    	this.callParent();
    },
    
    initStart:function(){
    	var me = this;
		WS.start(function(){
	    	WS.addMessageListener(this.onMessage,me);
	    	WS.addCloseListener(this.onClose,me);
	    	me.initData();
		},this);
    },
    
    initData :function(){
    	var me = this;
    	if(WS.online){
    		me.title = "消息管理[在线]";
    		me.onlineUsers.getStore().reload();
    		me.offlineUsers.getStore().reload();
    	}else{
    		me.title = "消息管理[离线]";
    	}
    },
    
    sysclose:function(){
    	var me = this;
    	for(var key in me.userWins){
			me.userWins[key].close();
		}
		me.close();
		WS.end();
    },
    
    onMessage:function(text,message){
    	var me = this;
    	var data = CU.toObject(text);
    	switch(data.type){
    		case 1:{
    			var node = {id:data.fid,text:data.fname,leaf:true,iconCls:'tree-panel-icon',icon:data.result.photoid,attributes:data.result};
    			me.onlineUsers.getStore().add(node);
    			var offlinestore = me.offlineUsers.getStore();
    			var index = offlinestore.indexOfId(data.fid);
    			if(index!=-1)offlinestore.removeAt(index);
    			Ext.toast("<font color='red'>"+data.fname+"登录系统"+"</font>");
    			break;
    		}
    		case 2:{
    			var onlineStore = me.onlineUsers.getStore();
    			var offlinestore = me.offlineUsers.getStore();
    			var index = onlineStore.indexOfId(data.fid);
    			if(index!=-1){
    				offlinestore.add(onlineStore.getAt(index));
    				onlineStore.removeAt(index);
    			}
    			Ext.toast("<font color='red'>"+data.fname+"退出系统"+"</font>");
    			break;
    		}
    		case 3:{
    			var chatwin = me.userWins[data.fid];
    			if(chatwin == null){
    				var store = me.onlineUsers.getStore();
    				var index = store.indexOfId(data.fid);
    				var rec = store.getAt(index);
    				var param4 = parseInt(rec.get("param4")||'0');
    				rec.set("param4",param4+1);
    				me.onMessageChange(1);
    			}else{
    				me.applyMessages(chatwin,data);
    			}
    		}
    	}
    },
    
    onClose:function(){
    	me.title = "消息管理[离线]";
    	me.onlineUsers.getStore().removeAll();
    },
    
    chatWindow:function(thiz, record, item, index, e, eOpts){
    	var me = this,
    		data = record.data,
    		userbean = record.data.attributes,
    		title = "用户："+userbean.username +" / 部门: "+userbean.departmentname,
    		contentPanel = Ext.create("Ext.form.field.HtmlEditor",{width:'100%',region:'center',
    			enableAlignments:false,enableColors:false,
    			enableFont:false,enableFontSize:false,
    			enableFormat:false,enableLinks:false,
    			enableLists:false,enableSourceEdit:false,
    			listeners:{change:function(thiz, newValue, oldValue, eOpts){
    				var body = thiz.getDoc().body;
    				body.scrollTop = body.scrollHeight;
    				var htmlNode = thiz.getDoc().childNodes[1];
    				htmlNode.scrollTop = htmlNode.scrollTopMax;
    			}}
    		}),
    		newsPanel = Ext.create("Ext.form.field.HtmlEditor",{width:'100%',region:'south',minHeight:100,collapsible: true,split:true,
    					height:150,enableSourceEdit:false,enableFont:false}),
	    	chatwin = Ext.create('Ext.window.Window',{
	    		title:title,width:650,height:600,layout:"border",
	    		items:[contentPanel,newsPanel],
	    	 	buttons:[
			    	{text:'关闭',scope:this,handler:function(){chatwin.close();}},
			    	{text:'发送',scope:this,handler:function(){
			    		var result = newsPanel.getValue();
			    		if(result.lastIndexOf("<br>")>0){
			    			result = result.substring(0,result.length-4);
			    		}
			    		var msg = {jid:userbean.userid,jname:userbean.username,result:result};
			    		me.sendMessage(msg,function(){
				    		newsPanel.reset();
			    		});
			    	}}
			    ],
			    listeners:{
			    	show:function(thiz, eOpts){
			    		var url = "platform/systemmessage/chatrecordlist.do";
			    		var params = {fid:userbean.userid,jid:cfg.sub.userid};
			    		EU.RS({url:url,params:params,msg:false,callback:function(result){
			    			me.applyMessages(chatwin,result);
			    		}});
			    		me.userWins[userbean.userid] = thiz;
			    	},
			    	close:function(thiz,eOpts){
			    		delete me.userWins[userbean.userid]
			    	}
			    }
	    	});
    	chatwin.show();
    	var tipsnum = record.get("param4");
    	if(Ext.isNumber(tipsnum)){
    		me.onMessageChange(tipsnum*-1);
    	}
    	record.set("param4","");
    },
    
    /**
     * 拼接消息
     * @param {} chatwin
     * @param {} datas  对象或数组
     */
    applyMessages:function(chatwin,datas){
    	var contentPanel  = chatwin.items.items[0];
    	var msg = "";
    	if(Ext.isObject(datas)){
    		var color = (datas.fid==cfg.sub.userid?"#0021FF":"#008063");
			msg ="<p><font color='"+color+"'>"+datas.fname+"&nbsp;&nbsp;&nbsp;"+datas.time+"</font></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+datas.result+"</p>";
    	}else if(Ext.isArray(datas)){
    		Ext.each(datas,function(data){
    			var color = (data.fid==cfg.sub.userid?"#0021FF":"#008063");
				msg +="<p><font color='"+color+"'>"+data.fname+"&nbsp;&nbsp;&nbsp;"+data.time+"</font></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+data.result+"</p>";
    		});
    	}
		contentPanel.setValue(contentPanel.getValue()+msg);
    },
    
    /**
     * 发送消息
     * @param {} jid 接收者用户id
     * @param {} msg	发送内容
     */
    sendMessage:function(msg,callback){
    	WS.sendMessage(msg);
    	Ext.callback(callback,this)
    }
}); 