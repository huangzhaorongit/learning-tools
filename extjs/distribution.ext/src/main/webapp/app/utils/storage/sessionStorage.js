/**
* 项目临时存储信息,浏览器关闭自动清除
*/
Ext.define('app.utils.storage.sessionStorage', {
	 alternateClassName: 'session',
	 statics: {
	 	 /**
	 	  * 设置系统缓存数据
	 	  * @param {} key
	 	  * @param {} obj
	 	  */
	 	 set :function(key,obj){
	 	 	 if(Ext.isEmpty(key))return;
	 	 	 sessionStorage.setItem(key,CU.toString(obj));
	 	 },
	 	 
	 	 /**
	 	  * 获取系统缓存数据
	 	  * @param {} key
	 	  * @return {}
	 	  */
	 	 get:function(key){
	 	 	if(Ext.isEmpty(key))return null;
	 	 	return CU.toObject(sessionStorage.getItem(key));
	 	 },
	 	 
	 	 remove:function(key){
	 	 	sessionStorage.removeItem(key);
	 	 },
	 	 
	 	 clean:function(){
	 	 	sessionStorage.clear();
	 	 }
	 }
});