package com.distribution.wamoli.common.bean; 
public class MyFileBean{ 
	
	private String filename;
	
	private String fileviewname;
	
	private long filesize;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileviewname() {
		return fileviewname;
	}

	public void setFileviewname(String fileviewname) {
		this.fileviewname = fileviewname;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
}
