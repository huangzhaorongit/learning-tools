Ext.define('app.controller.platform.frame.dashboard.MainController', {
    extend: 'app.controller.platform.frame.default.MainController',
    alias:'controller.dashboardThemeController',
    onToggleNavigationSize:function(){
        var me = this,
            refs = me.getReferences(),
            navigationList = refs.navigationTreeList,
            wrapContainer = refs.mainContainerWrap,
            collapsing = !navigationList.getMicro(),
            new_width = collapsing ? 66 : 250;

        if (Ext.isIE9m || !Ext.os.is.Desktop) {
            Ext.suspendLayouts();
            refs.senchaLogo.setWidth(new_width);
            navigationList.setWidth(new_width);
            navigationList.setMicro(collapsing);
            Ext.resumeLayouts();
            wrapContainer.layout.animatePolicy = wrapContainer.layout.animate = null;
            wrapContainer.updateLayout();
        }
        else {
            if (!collapsing){navigationList.setMicro(false);}
            refs.senchaLogo.animate({dynamic: true, to: {width: new_width}});
            navigationList.width = new_width;
            wrapContainer.updateLayout({isRoot: true});
            if (collapsing) {
                navigationList.on({
                    afterlayoutanimation: function () {
                        navigationList.setMicro(true);
                    },
                    single: true
                });
            }
        }
    },
    
    onNavigationTreeSelectionChange:function(tree, node){
    	PU.openTabModule(node.data);
    }
});