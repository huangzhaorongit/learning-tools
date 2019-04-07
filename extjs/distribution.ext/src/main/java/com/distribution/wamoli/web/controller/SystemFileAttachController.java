package com.distribution.wamoli.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.distribution.wamoli.common.bean.DownloadFile;
import com.distribution.wamoli.common.bean.FileAttrBean;
import com.distribution.wamoli.common.bean.FileUploadBean;
import com.distribution.wamoli.common.bean.MyFile;
import com.distribution.wamoli.common.bean.ResultBean;
import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.exception.CommonException;
import com.distribution.wamoli.common.exception.FileStreamException;
import com.distribution.wamoli.common.interceptor.bind.annotation.FormModel;
import com.distribution.wamoli.common.utils.DateUtils;
import com.distribution.wamoli.common.utils.FileUtils;
import com.distribution.wamoli.common.utils.IDUtils;
import com.distribution.wamoli.common.utils.ProjectUtils;
import com.distribution.wamoli.common.utils.ZipUtils;
import com.distribution.wamoli.service.SystemFileAttachService;
import com.distribution.wamoli.domain.FSystemfileattach;



@Controller
@RequestMapping("/platform/fileattach")
public class SystemFileAttachController {

	@Autowired
	private SystemFileAttachService service;


	@RequestMapping(value="/getfilelist")
	@ResponseBody
	public List<FSystemfileattach> getSystemFileAttachList(FSystemfileattach bean){
		bean.setCompanyid(Local.getCompanyid());
		return service.getSystemFileAttachList(bean);
	}

	@RequestMapping(value="/getfileinfo")
	@ResponseBody
	public FSystemfileattach getSystemFileAttachInfo(FSystemfileattach bean){
		return service.getSystemFileAttachInfo(bean);
	}

	@RequestMapping(value="/uploadfile")
	@ResponseBody
	public ResultBean uploadFile(HttpServletRequest request,FileUploadBean bean){
		MultipartHttpServletRequest re=(MultipartHttpServletRequest)request;
		CommonsMultipartFile mfile = (CommonsMultipartFile)re.getFile("formFile");
		long filesize = mfile.getSize();
		if(mfile==null || (filesize==0L))
			try {
				throw new CommonException(" is NULL upload attach file! ");
			} catch (CommonException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		FSystemfileattach info =getFSystemfileattach(bean, mfile);
		File path = Local.getLocalSpace().getFileAttachSpace(Local.getCompanyid());
		File realfile = new File(path,info.getId());
		try {
			service.addFSystemfileattach(info,bean.getNfield());
			mfile.getFileItem().write(realfile);
			FileUtils.file2Pdf(realfile,info.getId(),info.getFilesuffix(),true,null);
		} catch (Exception e) {
			try {
				throw new FileStreamException("文件上传失败!");
			} catch (FileStreamException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(!StringUtils.isEmpty(bean.getIsindex())&&(bean.getIsindex().equals("1") || bean.getIsindex().equals("true"))){  //添加到索引
		//	LuceneUtils.updateCoverDocument(info.getId(),info.getTitle(), info.getFilesuffix(), realfile);
		}
		return new ResultBean(info.getId());
	}

	@RequestMapping(value="/removefile")
	@ResponseBody
	public String removefile(FSystemfileattach bean,String nfield){
		nfield = StringUtils.isEmpty(nfield)?"attachs":nfield;
		service.removeFile(bean,nfield);
		return "true";
	}

	@RequestMapping(value="/downloadfile")
	public void downloadFile(@FormModel("list") List<String> ids,HttpServletResponse response,HttpServletRequest request){
        try {
        	List<MyFile> flist = new ArrayList<MyFile>();
        	for (int i = 0; i < ids.size(); i++) {
        		FSystemfileattach bean = service.updateDownloadNum(ids.get(i));
        		service.updateDownloadNum(bean.getId());
        		MyFile myfile = Local.getLocalSpace().getFileAttachPath(ids.get(i));
//        		myfile.setFilename(bean.getOriginalname());
        		flist.add(myfile);
			}
        	File tempath = Local.getLocalSpace().getTempSpace(Local.getCompanyid());
        	DownloadFile df = ZipUtils.batchDownload(flist, tempath,"", -1, -1);
			ProjectUtils.returnDownFile(df);
		} catch (Exception e) {
			ProjectUtils.returnDownError(e);
		}
	}

//	@RequestMapping(value="/preview")
//	public void preview(FSystemfileattach bean,HttpServletResponse response,HttpServletRequest request) throws IOException{
//		bean = getSystemFileAttachInfo(bean);
//		if(bean==null){ProjectUtils.writeHtml("错误数据!");return;}
//		service.updateViewNum(bean.getId());
//		synchronized(PdfUtils.getOfficeManager()){
//			File pdf = Local.getLocalSpace().getPdfPath(bean.getId());
//			if(!pdf.isFile()){
//				File path = Local.getLocalSpace().getFileAttachSpace(Local.getCompanyid());
//				File source = new File(path,bean.getId());
//				boolean sueess = FileUtils.file2Pdf(source,bean.getId(),bean.getFilesuffix(),false,null);
//				if(!sueess){ProjectUtils.writeHtml("文件转换失败!");return;}
//			}
//			DownloadFile df = new DownloadFile(pdf,bean.getTitle(),"application/pdf");
//		ProjectUtils.returnDownFile(df);
//		}
//	}


	private FSystemfileattach getFSystemfileattach(FileUploadBean bean,CommonsMultipartFile mfile){
		FileAttrBean fb = new FileAttrBean(mfile);
		FSystemfileattach info = new FSystemfileattach();
		info.setId(IDUtils.getUuid());
		info.setCompanyid(Local.getCompanyid());
		info.setTitle(fb.getFilename());
		info.setKeyword(fb.getFilename());
		info.setFiletype(fb.getDoctype());
		info.setOriginalname(fb.getOriginalname());
		info.setFilesuffix(fb.getFilesuffix());
		info.setFilesize(fb.getFilesize());
		info.setUploadtime(DateUtils.getCurrentTimestamp());
		info.setViewnum(0L);
		info.setDownloadnum(0L);
		info.setSourcetablename(bean.getTablename());
		info.setSourcefieldname(bean.getFieldname());
		info.setSourcerecordkey(bean.getFieldvalue());
		return info;
	}

}
