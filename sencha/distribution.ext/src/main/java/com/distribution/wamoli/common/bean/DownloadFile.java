package com.distribution.wamoli.common.bean;

import java.io.File;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.distribution.wamoli.common.utils.FileUtils;





/**
 * 文件下载
 * @author LIUM 2013年7月23日 10:00:05 
 */
public class DownloadFile implements HttpResult {
	
	
	private int type=0;		//0=一般下载	1=exportExcel
	
	private HSSFWorkbook hwb;
	private String fileName;
	private InputStream is;
	
	private Object tag;
	private boolean dialog;
	private String contentType;
	
	
	public DownloadFile(File file) {
		this(FileUtils.getFileInputStream(file), file.getName(), true,null);
	}
	
	
	public DownloadFile(File file, String fileName) {
		this(FileUtils.getFileInputStream(file), fileName, true,null);
	}
	
	public DownloadFile(File file, String fileName,String contentType) {
		this(FileUtils.getFileInputStream(file), fileName, true,contentType);
	}
	
	public DownloadFile(HSSFWorkbook hwb, String fileName) {
		this(hwb, fileName, true);
	}
	
	public DownloadFile(HSSFWorkbook hwb, String fileName, boolean dialog) {
		this.type = 1;
		this.hwb = hwb;
		this.fileName = fileName;
		this.dialog = dialog;
	}
	
	public DownloadFile(InputStream is, String fileName) {
		this(is, fileName, true,null);
	}
	
	public DownloadFile(File file, boolean dialog) {
		this(FileUtils.getFileInputStream(file), file.getName(), dialog,null);
	}
	
	public DownloadFile(File file, String fileName, boolean dialog) {
		this(FileUtils.getFileInputStream(file), fileName, dialog,null);
	}
	
	
	public DownloadFile(File file, String fileName, boolean dialog,String contentType) {
		this(FileUtils.getFileInputStream(file), fileName, dialog,null);
	}
	
	
	public DownloadFile(InputStream is, String fileName, boolean dialog,String contentType) {
		this.is = is;
		this.fileName = fileName;
		this.dialog = dialog;
		this.contentType = contentType;
	}
	
	
	
	/**
	 * 获取文件输入流
	 * @return
	 */
	public InputStream getInputStream() {
		return this.is;
	}
	
	
	
	/**
	 * 获取文件名称
	 * @return
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * 设置文件名称
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	/**
	 * 备用参数
	 * @return
	 */
	public Object getTag() {
		return tag;
	}

	
	
	/**
	 * 备用参数
	 * @return
	 */	
	public void setTag(Object tag) {
		this.tag = tag;
	}
	
	
	/**
	 * 获取文件后缀
	 * @return
	 */
	public String getSuffix() {
		return this.fileName.indexOf(".")>0 ? this.fileName.substring(this.fileName.lastIndexOf(".")+1) : "";
	}

	
	/**
	 * 是否需要对话框
	 * @return
	 */
	public boolean isDialog() {
		return dialog;
	}
	
	/**
	 * 设置是否需要对话框
	 * @return
	 */
	public void setDialog(boolean dialog) {
		this.dialog = dialog;
	}
	
	
	/**
	 * 获取下载类型
	 * @return
	 */
	public int getType() {
		return this.type;
	}
	
	
	/**
	 * 获取导出Excel对象
	 * @return
	 */
	public HSSFWorkbook getExcel() {
		return this.hwb;
	}


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
	
	
}


