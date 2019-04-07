package com.distribution.wamoli.common.bean;

import java.io.File;

/**
 * 附件下载时候使用,因为file路径的文件名字不一定是我们需要的
 * @author lium 
 */
public class MyFile extends File{ 
	private static final long serialVersionUID = 1L; 
	
	private String filename;
	
	public MyFile(File pathname,String filename){
		super(pathname,filename);
		filename = this.getName();
	} 

	public MyFile(String pathname,String filename){
		super(pathname);  
		this.filename = filename;
	} 
	
	public MyFile(File parent, String child,String filename){
		super(parent,child);  
		this.filename = filename;
	} 
	   
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
