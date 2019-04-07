Ext.define('app.view.platform.login.Login', {
    extend: 'Ext.panel.Panel',
    alternateClassName: 'login',
    xtype: 'login',
    requires: [
        'app.controller.platform.login.LoginController',
        'app.view.platform.frame.SystemFrame'
    ],
    controller:'login',
    layout: { type: 'fit'},
    referenceHolder: true,
    items:[
    	{
    		xtype:'panel',
		    title: '<h2><font color="#ffffff"><span style="letter-spacing:2mm;text-shadow: 3px 3px 3px rgba(42, 42, 42, 0.75);">'+cfg.systemname+'</span></font></h2>',
		    titleAlign: 'center',
		    bodyStyle:"background-image:url('resources/images/bg.gif');",
		    heigth: 900,
		    layout: {
		        type: 'vbox',
		        align: 'center',
		        pack: 'center'
		    },
    		items:[
		    	{
		            xtype: 'form',
		            reference:'p_form',
		            width: 415,
		            height:300,
		            bodyPadding: '20 20',
		            layout: {
		                type: 'vbox'
		            },
		            defaults : {
		                margin : '5 0',
		                width :'100%'
		            },
		            items:[
		                {
		                    xtype: 'label',
		                    text: '登录到您的帐户'
		                },
		                {
		                    xtype: 'textfield',
		                    name: 'j_username',
		                    height: 55,
		                    hideLabel: true,
		                    allowBlank:false,
                    		cls: 'auth-textbox',
		                    emptyText: '用户名称',
		                    triggers: {
						        user: {
						            cls: 'x-fa fa-user login-textfield-triggers'
						        }
						    },
		                    listeners: {
			                    specialkey: 'onUserNameEnterKey'
			                }
		                },
		                {
		                    xtype: 'textfield',
		                    reference:'t_password',
		                    name: 'j_password',
		                    height: 55,
		                    hideLabel: true,
		                    emptyText: '用户密码',
		                    inputType: 'password',
		                    allowBlank:false,
		                    triggers: {
						        user: {
						            cls: 'x-fa fa-lock login-textfield-triggers'
						        }
						    },
		                    listeners: {
			                    specialkey: 'onPasswordEnterKey'
			                }
		                },
		                {
		                    xtype: 'container',
		                    layout: 'hbox',
		                    items: [
		                        {
		                            xtype: 'checkboxfield',
		                   			name: 'keepusername',
		                            flex : 1,
		                            cls: 'form-panel-font-color rememberMeCheckbox',
		                            height: 30,
		                            checked:true,
		                            inputValue:'1',
		                            uncheckedValue:"0",
		                            boxLabel: '记住用户名'
		                        },
		                        {
		                            xtype: 'checkboxfield',
		                   			name: 'keeppassword',
		                            flex : 1,
		                            cls: 'form-panel-font-color rememberMeCheckbox',
		                            height: 30,
		                            inputValue:'1',
		                            uncheckedValue:"0",
		                            boxLabel: '记住密码'
		                        }
		                    ]
		                },
		                {
		                    xtype: 'button',
		                    name:'loginButton',
		                    scale: 'large',
		                    height: 50,
		                    iconAlign: 'right',
		                    iconCls: 'x-fa fa-angle-right',
		                    text: '登录',
				            action:'login',
				            listeners: {
		                        click: 'onLoginButton'
		                    }
		                }
		            ]
		        }
    		],
		    buttons: [
			    {
				    width:'100%',
				    html: '©湖南大云新能电力技术有限公司  版本:<font color="#ffffff">(Ver:6.0.2016.10.10)</font> '
			    }
			]
    	}
    ]
});
