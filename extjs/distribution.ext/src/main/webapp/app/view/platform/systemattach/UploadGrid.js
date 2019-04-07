Ext.define('app.view.platform.systemattach.UploadGrid', {
    extend: 'Ext.grid.Panel',
    alternateClassName:'uploadgrid',
    xtype: 'uploadgrid',
    store:{},
    initComponent:function(){
    	var me = this;
    	var C_index = Ext.create('Ext.form.field.Checkbox',{boxLabel:'添加到索引',name:'isindex',checked: true});
    	var B_addfile = Ext.create('Ext.Button',{iconCls:'x-fa fa-plus-square',tooltip:"添加文件",border:"0px",scope:this});
    	var B_startupload = Ext.create('Ext.Button',{iconCls:'x-fa fa-upload',tooltip:"开始上传",border:"0px",scope:this,handler:me.start});
    	var B_clearfile = Ext.create('Ext.Button',{iconCls:'x-fa fa-trash',tooltip:"清空列表",border:"0px",scope:this,handler:me.clear});
    	me.bts = [B_addfile,B_startupload,B_clearfile,C_index];
    	Ext.apply(me,{
    		tbar: [C_index,'->',B_addfile,B_startupload,B_clearfile],
    		columns:[
				{header:'文件名',flex:1, dataIndex:'fileName',sortable:false,fixed:true,renderer:function(_v, cellmeta, record){
					var error = record.get("fileState") == SWFUpload.FILE_STATUS.ERROR || record.get("fileState") == SWFUpload.FILE_STATUS.CANCELLED;
					return  error ? ('<div class="ux-cell-color-gray">' + _v + '</div>') :_v;
				}},
		        {header:'进度',xtype:'widgetcolumn',width:98,dataIndex: 'progress',widget: {xtype: 'progressbarwidget',textTpl: ['{percent:number("0")}%']}},
		        {header:'&nbsp;', width:40,dataIndex:'fileState',sortable:false,fixed:true,align:'center',renderer:function(_v, cellmeta, record) {
					var returnValue = '';
					switch(_v){
						case SWFUpload.FILE_STATUS.QUEUED:
							returnValue = '<span id="' + record.id + '"><div id="fileId_' + record.data.fileId + '" style="background:url(resources/images/icons/16x16/delete.gif) no-repeat;cursor: pointer;height:16px;"/></span>';
							break;
						case SWFUpload.FILE_STATUS.ERROR:
							returnValue = '<span id="' + record.id + '"><div id="fileId_' + record.data.fileId + '" style="color:gray;font-style:italic;"/>失败</span>';
							break;
						case SWFUpload.FILE_STATUS.COMPLETE:
							returnValue = '<span id="' + record.id + '"><div id="fileId_' + record.data.fileId + '" style="background:url(resources/images/icons/16x16/checked.gif) no-repeat;cursor: pointer;height:16px;"/></span>';
							break;
						case SWFUpload.FILE_STATUS.CANCELLED:
							returnValue = '<span id="' + record.id + '"><div id="fileId_' + record.data.fileId + '" style="color:gray;font-style:italic;"/>取消</span>';
							break;
						default : break;
					}
					return returnValue;
				}}
			]
    	});
		this.callParent(arguments);
    },
    
	afterRender:function(){
		this.callParent(arguments);
		var me = this,bts = me.bts;
		var upcfg = this.upcfg || {};
    	upcfg.filetypes = upcfg.filetypes || "*.*";
    	upcfg.filesize = Ext.isEmpty(upcfg.filesize)?10:parseInt(upcfg.filesize,10);
    	upcfg.nfield = upcfg.nfield || 'attachs';
    	upcfg.limit = upcfg.limit || 100;
    	upcfg.isindex = Ext.isEmpty(upcfg.isindex)?true:upcfg.isindex;
    	upcfg.JSESSIONID = cfg.sub.sessionid;
    	var B_addfile = bts[0];
    	var em = B_addfile.el.child('span');
	    var placeHolderId = Ext.id();
	    em.createChild({tag:'div',id: placeHolderId});
    	var settings = {
			upload_url: upcfg.uploadurl ||　"platform/fileattach/uploadfile.do",
			post_params: upcfg,
			flash_url : 'expand/plugin/swfupload/swf/swfupload.swf', 
			flash9_url : 'expand/plugin/swfupload/swf/swfupload_fp9.swf', 
			file_post_name: "formFile",
			file_size_limit: upcfg.filesize+" MB",
			file_types: upcfg.filetypes,
			file_upload_limit: upcfg.limit,
			use_query_string:true,
			button_width : B_addfile.getWidth(),
			button_height : B_addfile.getHeight(),
			style:'position:absolute;left:0px;top:0px;',
			button_placeholder_id: placeHolderId,
			button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
			button_cursor : SWFUpload.CURSOR.HAND,
			file_queued_handler : onFileQueued,
			file_queue_error_handler : onFileQueueError,
			file_dialog_complete_handler : null,
			upload_start_handler : null,
			upload_progress_handler : onUploadProgress,
			upload_error_handler : null,
			upload_success_handler : onUploadSuccess,
			upload_complete_handler : onUploadComplete
		};
		Ext.apply(settings,this.settings);
		var swfupload = me.swfupload = new SWFUpload(settings);
		swfupload.uploadStopped = false;
    	
    	this.on('cellclick',function(grid, td, cellIndex, record, tr, rowIndex, e, eOpts) {
			if(cellIndex!=2 ) return ;
			var fileId = record.data.id;
			var file = swfupload.getFile(fileId);
			if(file){
				if(file.filestatus == SWFUpload.FILE_STATUS.QUEUED){
					grid.getStore().remove(record);
				}else if(file.filestatus == SWFUpload.FILE_STATUS.IN_PROGRESS){
					swfupload.cancelUpload(fileId);
					record.set('fileState',SWFUpload.FILE_STATUS.CANCELLED);
					file.filestatus = SWFUpload.FILE_STATUS.QUEUED; 
				}
			}
		});
		
		function onFileQueued(file) {
			me.getStore().add({id:file.id,fileName:file.name,fileSize:file.size,fileType:file.type,fileState:file.filestatus});
		};
		
		function onFileQueueError(file) {
			if(Ext.isEmpty(file)) return ;
			EU.toastWarn("文件：\""+file.name+"\"\r\n 超过了指定大小："+upcfg.filesize+"MB!不能上传!");
		};
		
		/**
		 * 过程记录
		 */
		function onUploadProgress(file, completeBytes, bytesTotal) {
			var percent = completeBytes / bytesTotal;
			var rec = me.getRecord(file.id);
			if(rec==null)return;
			rec.set("progress",percent);
		}
				
		/**
		 * 单个文件上传完成
		 */
		function onUploadSuccess(file, serverData) {
			var result = eval("("+serverData+")");
			if(result.success) {
				var rec = me.getRecord(file.id);
				if(rec==null)return;
				rec.set('fileState',SWFUpload.FILE_STATUS.COMPLETE);
				if(Ext.isFunction(me.successHandler)){
					Ext.callback(me.successHandler, me, [file, serverData]);
				}
			}
		}
		
		/**
		 * 完整的上传完成
		 */
		function onUploadComplete(file) {
			if(swfupload.getStats().files_queued > 0 && swfupload.uploadStopped==false){
				swfupload.startUpload();
				return;
			}
			me.setBtnsDisabled(false);
			var store = me.getStore(),
				count = store.getCount();
			for(var i=count-1; i>=0; i--) {
				var record = store.getAt(i),
					fileid = record.get("id");
				if(record.get("fileState") == SWFUpload.FILE_STATUS.COMPLETE) {
					store.remove(record);
				}
			}
			if(Ext.isFunction(me.uploadComplete)){
				Ext.callback(me.uploadComplete, me, [file]);
			}
		}
	},
	
	setBtnsDisabled :function(disabled) {
		var me = this,bts = me.bts;
		Ext.each(bts,function(rec){
			rec.setDisabled(disabled);
		});
	},
	
	/**
	 * 开始上传
	 */
	start : function () {
		var me=this,filecount = 0;
		Ext.each(me.getStore().data.items,function(record){
			if(record.get("fileState") == SWFUpload.FILE_STATUS.QUEUED) {
				filecount = 1;
				return;
			}
		});
		if(filecount == 0) {EU.toastWarn("请添加上传文件!");return ;}
		if (me.swfupload) {
			if(this.swfupload.getStats().files_queued == 0){
				EU.toastWarn("请添加上传文件!");return ;
			}
			me.upcfg.isindex = me.bts[3].getValue() ;
			me.swfupload.setPostParams(me.upcfg); 
			this.swfupload.uploadStopped = false;
			this.swfupload.startUpload();
			this.setBtnsDisabled(true);
		}
	},
	
	/**
	 * 清空
	 */
	clear : function () {
		this.getStore().removeAll();
	}
});