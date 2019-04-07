Ext.define('app.view.platform.systemattach.FileBatchUpload', {
	extend: 'Ext.panel.Panel',
    alternateClassName: 'FileBatchUpload',
	xtype: 'filebatchupload',
	plugins:[{ptype:'PRQ'}],
	requires: [
        'app.view.platform.systemattach.UploadGrid'
    ],
    referenceHolder: true,
	layout:'border',
	initComponent:function(){
		var me = this, params = this.params;
		var queryParams = {sourcetablename:params.tablename,sourcefieldname:params.fieldname,sourcerecordkey:params.fieldvalue,nfield:params.nfield};
		var P_grid = Ext.create("Ext.grid.Panel",{reference:'P_grid',region:"center",paging:true,selType: 'checkboxmodel',
    		tbar:[
    			{xtype:'searchfield',emptyText: '文件名',paramName:'title',hasSearch:true,width:300,store: "gridStore"},
           	    {xtype:'btngridquery',text: '高级查询',iconCls:'x-fa fa-search'},
           	    {text: '批量下载',iconCls:'x-fa fa-download',scope:this,handler:function(){
           	    	var sel = P_grid.getSelection();
        			this.download(sel,P_grid);
           	    }}
    		],
    		store:{storeId:"gridStore",autoLoad:true,url:"platform/fileattach/getfilelist.do",params:queryParams},
    		columns:[
    			{xtype:'actioncolumn',text:"文件名", flex:1, align:"left", sortable:true, dataIndex:"originalname",qcfg:{type:'string'},items: [{
                    tooltip: '点击下载',style:"text-decoration:underline;",scope:this,handler:me.onDownloadhandler,renderer:function(v){return v;}
                }]},
				{xtype:'actioncolumn',text:'预览',dataIndex:"filesuffix",menuDisabled:true,sortable:false,width:60,align:'center',items: [{
                    iconCls: 'x-fa fa-file-image-o',tooltip: '查看文件',handler:me.onPreviewhandler,
                    getClass: me.getClasshandler
                }]},
				{header:"文件类型", width:80, align:"center",sortable:true, dataIndex:"filesuffix",qcfg:{type:'string'}},
				{header:"文件大小", width:100, style:'text-align:center;',align:"right",sortable:true,qcfg:{type:'number'}, dataIndex:"filesize",renderer:function(value,metadate,record,row,col) {
					return Ext.util.Format.fileSize(value);
				}},
				{header:"上传时间", width:150, align:"center", sortable:true, dataIndex:"uploadtime",qcfg:{type:'datetime'}},
				{xtype:'actioncolumn',text:'操作',menuDisabled:true,sortable:false,width:50,align:'center',items: [{
                    icon: 'resources/images/icons/16x16/cross.gif',tooltip: '删除记录',scope:this,handler:me.onRemovehandler
                }]}
    		]
		});
		var P_uploadgrid = Ext.create("uploadgrid",{region:"east",width:300,frame: false,border:false,split: true,upcfg:me.params,uploadComplete:function(){
			P_grid.load(queryParams);
		}});
    	this.items = [P_grid,P_uploadgrid];
		this.callParent(arguments);
	},
	
	onRemovehandler:function(grid, rowIndex, colIndex){
        var rec = grid.getStore().getAt(rowIndex);
		var url = "platform/fileattach/removefile.do";
    	EU.RS({url:url,scope:this,params:{id:rec.data.id,nfield:this.params.nfield},callback:function(result){
    		EU.toastWarn("文件『"+rec.data.originalname+"』删除成功!");
    		grid.getStore().removeAt(rowIndex);
    	}});
    },
    
    onDownloadhandler:function(grid, rowIndex, colIndex){
        var rec = grid.getStore().getAt(rowIndex);
        this.download([rec],grid);
    },
    
    getClasshandler:function(v){
        var formats = cfg.sub.pdfformat;
        if(!Ext.isEmpty(formats) && formats.indexOf(v)!=-1){
        	return CU.getFileTypeIconCls(v);
        }
    	return "";
    },
    
    onPreviewhandler:function(grid, rowIndex, colIndex){
        var rec = grid.getStore().getAt(rowIndex);
        var formats = cfg.sub.pdfformat;
        if(!Ext.isEmpty(formats) && formats.indexOf(rec.data.filesuffix)!=-1){
	        var file = Loader.baseUrl()+"platform/fileattach/preview.do?id="+rec.id;
	        var url = "resources/html/pdfpreview/viewer.html?file="+escape(file);
	        PU.openModule({title:"在线预览",url:url,width:1000,height:700,scope:this});
        }else{
        	EU.toastWarn("该文件类型不可预览!");
        }
    },
    
    download:function(datas,grid){
    	var ids = [];
    	Ext.each(datas,function(rec){
    		ids.push(rec.id);
    	})
    	PU.download({params:{ids:ids}});
    }
});