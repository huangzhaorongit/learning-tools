
/**
* WebSocket
*/
Ext.define('app.utils.WebSocket', {
	alternateClassName: 'WS',
	statics: {
		online:false,
		messagesCallback:[],
		closeCallback:[],
		
		/**
		 * 启动WebSocket
		 */
		start:function(callback,scope){
			var me = this,scope = scope || me;
    		window.WebSocket = window.WebSocket || window.MozWebSocket;
    		if (!window.WebSocket){alert("WebSocket not supported by this browser");return;}
			var host = window.location.host;
    		url = 'ws://' + window.location.host + cfg.sub.basepath+'/WebSocketServer';
			var ws = this.ws = new WebSocket(url);
			ws.onopen = function() {
				var msg = {type:1,fid:cfg.sub.userid,fname:cfg.sub.username};
				me.ws.send(CU.toString(msg));
				me.online = true;
				me.messagesCallback = [];
				me.closeCallback = [];
				Ext.callback(callback,scope,[true]);
			};
			ws.onclose = function() {
				me.online = false;
				Ext.each(me.addCloselistener,function(){
					var scope = rec.scope || me;
					Ext.callback(rec.callback,scope)
				})
				Ext.callback(callback,scope,[false]);
			};
			ws.onmessage = function(message) {
				Ext.each(me.messagesCallback,function(rec){
					var scope = rec.scope || me;
					Ext.callback(rec.callback,scope,[message.data,message])
				})
			};
			ws.onerror = function(){
				CU.log(11111);
    			Ext.toast("<font color='red'>"+data.fname+"退出系统"+"</font>");
		    };
		},
		
		addMessageListener:function(callback,scope){
			this.messagesCallback.push({callback:callback,scope:scope});
		},
		
		addCloseListener:function(callback,scope){
			this.closeCallback.push({callback:callback,scope:scope});
		},
		
		end:function(){
			this.sendMessage({type:2});
		},
		
		/**
		 * 发送消息
		 * @param {} result
		 */
		sendMessage:function(result){
			result.type = result.type || 3;
			result.ctype = result.ctype || 0;
			result.fid = result.fid || cfg.sub.userid;
			result.fname = result.fname || cfg.sub.username;
			this.ws.send(CU.toString(result));
		}
	}
});