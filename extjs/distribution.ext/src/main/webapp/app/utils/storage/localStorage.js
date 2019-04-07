/**
*项目永久存储信息
*/
Ext.define('app.utils.storage.localStorage', {
	 alternateClassName: 'local',
	 statics: {
	 	 /**
	 	  * 设置系统缓存数据
	 	  * @param {} key
	 	  * @param {} obj
	 	  */
	 	 set :function(key,obj){
	 	 	 if(Ext.isEmpty(key))return;
	 	 	 localStorage.setItem(key,CU.toString(obj));
	 	 },
	 	 
	 	 /**
	 	  * 获取系统缓存数据
	 	  * @param {} key
	 	  * @return {}
	 	  */
	 	 get:function(key){
	 	 	if(Ext.isEmpty(key))return null;
	 	 	return CU.toObject(localStorage.getItem(key));
	 	 },
	 	 
	 	 remove:function(key){
	 	 	localStorage.removeItem(key);
	 	 },
	 	 
	 	 clean:function(){
	 	 	localStorage.clear();
	 	 }
	 }
});