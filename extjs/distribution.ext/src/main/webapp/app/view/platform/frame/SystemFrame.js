Ext.define('app.view.platform.frame.SystemFrame', {
    extend: 'Ext.panel.Panel',
    alternateClassName: 'systemFrame',
    xtype: 'systemFrame',
    requires: [
        'app.controller.platform.frame.SystemFrameController',
        'app.view.platform.frame.desktop.Main'
    ],
    controller:'systemFrame',
    layout: 'fit',
    referenceHolder: true,
    items:[]
});