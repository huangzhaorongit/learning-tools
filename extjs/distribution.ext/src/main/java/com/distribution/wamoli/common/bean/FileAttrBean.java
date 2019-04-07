package com.distribution.wamoli.common.bean;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.distribution.wamoli.common.utils.FileUtils;


public class FileAttrBean {
	private CommonsMultipartFile cmf;
	private String filename; // 文件名称
	private String originalname; // 原文件名称
	private String doctype; // 文档类型
	private String filesuffix; // 文档后缀
	private long filesize; // 文档大小
	
	public FileAttrBean(CommonsMultipartFile cmf){
		 this.cmf = cmf;
		 this.originalname = cmf.getOriginalFilename(); 
		 this.filename = originalname.indexOf(".")>0 ? originalname.substring(0, originalname.lastIndexOf(".")) : originalname;
		 this.filesuffix = originalname.indexOf(".")>0 ? originalname.substring(originalname.lastIndexOf(".")+1).toUpperCase() : "";
		 this.filesize = cmf.getSize();
		 this.doctype = FileUtils.getDocType(filesuffix);
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getOriginalname() {
		return originalname;
	}

	public void setOriginalname(String originalname) {
		this.originalname = originalname;
	} 

	public String getFilesuffix() {
		return filesuffix;
	}

	public void setFilesuffix(String filesuffix) {
		this.filesuffix = filesuffix;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	public CommonsMultipartFile getCmf() {
		return cmf;
	}

	public void setCmf(CommonsMultipartFile cmf) {
		this.cmf = cmf;
	}
 
}
