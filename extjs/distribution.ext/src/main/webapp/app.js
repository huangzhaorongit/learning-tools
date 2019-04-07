/*
 * This file is generated and updated by Sencha Cmd. You can edit this file as
 * needed for your application, but these edits will have to be merged by
 * Sencha Cmd when upgrading.
 */
Ext.application({
    name: 'app',

    extend: 'app.Application',

    requires: [
        'app.utils.Loader',
    	'app.utils.Config', 
        'app.utils.CommonUtils', 
        'app.utils.storage.localStorage',  //永久数据存储
        'app.utils.storage.sessionStorage',//会话数据存储
        
        'app.view.Main'
    ],

    mainView: 'app.view.Main'
});
