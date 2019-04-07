package com.distribution.wamoli.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import com.distribution.wamoli.common.bean.DownloadFile;
import com.distribution.wamoli.common.bean.MyFile;
import com.distribution.wamoli.common.bean.MyFileBean;
import com.distribution.wamoli.common.exception.DownloadException;
import com.distribution.wamoli.common.exception.ZipException;

public class ZipUtils { 

	
	/**
	 * 文件打包下载
	 * @param list       打包文件信息
	 * @param filepath   打包文件公共目录
	 * @param tempath	   压缩后存放的临时目录
	 * @param fileMaxSize 单个文件的最大大小(M) -1 = 没有限制 
	 * @param fileSumSize 总文件的最大大小(M) -1 = 没有限制
	 * @return
	 */
	public static DownloadFile batchDownload(List<MyFileBean> list,File filepath,File tempath,int fileMaxSize,int fileSumSize){
		List<MyFile> flist = new ArrayList<MyFile>();
		for (int i = 0; i < list.size(); i++) {
			MyFileBean bean = list.get(i);
			flist.add(new MyFile(filepath,bean.getFilename(),bean.getFileviewname()));
		}
		return batchDownload(flist,tempath,"文档资料",fileMaxSize,fileSumSize);
	} 
	
	/**
	 * 文件打包下载
	 * @param list       打包文件信息
	 * @param filepath   打包文件公共目录
	 * @param tempath	   压缩后存放的临时目录
	 * @param downloadFileName 下载时候提示给客户显示的文件名称 
	 * @param fileMaxSize 单个文件的最大大小(M) -1 = 没有限制 
	 * @param fileSumSize 总文件的最大大小(M) -1 = 没有限制
	 * @return
	 */
	public static DownloadFile batchDownload(List<MyFileBean> list,File filepath,File tempath,String downloadFileName,int fileMaxSize,int fileSumSize){
		List<MyFile> flist = new ArrayList<MyFile>();
		for (int i = 0; i < list.size(); i++) {
			MyFileBean bean = list.get(i);
			flist.add(new MyFile(filepath,bean.getFilename(),bean.getFileviewname()));
		}
		return batchDownload(flist,tempath,downloadFileName,fileMaxSize,fileSumSize);
	} 
	
	/** 
	 * 文件打包下载
	 * @param flist        打包文件信息
	 * @param tempath      压缩后存放的临时目录
	 * @param downloadFileName 下载时候提示给客户显示的文件名称 
	 * @param fileMaxSize  单个文件的最大大小(M) -1 = 没有限制 
	 * @param fileSumSize  总文件的最大大小(M) -1 = 没有限制
	 * @return
	 */
	public static DownloadFile batchDownload(List<MyFile> flist,File tempath,String downloadFileName,int fileMaxSize,int fileSumSize){
		for (int i = 0; i < flist.size(); i++) {
			if(CommonUtils.isEmpty(flist.get(i).getFilename())){
				throw new DownloadException("附件ID:<font color='red'>"+flist.get(i).getName()+"</font> 文件不存在服务器!",true);
			}
		}
		if(flist.size()==1){
			 MyFile file = flist.get(0);  
			 if(!file.exists())throw new DownloadException(file.getFilename()+" 文件不存在服务器!",true);
			 return new DownloadFile(file,file.getFilename(),true);
		}else{
		   String tempid = "";
		   for (int i = 0; i < flist.size(); i++) {
			   MyFile file = flist.get(i);
			   tempid+=file.getFilename()+"&"+file.length()+"/";
			   if(!file.exists())throw new DownloadException(file.getFilename()+" 文件不存在服务器!",true);
		   } 
		   File tempFile = new File(tempath,MD5.MD5Encode(tempid)+".zip");//增加缓存机制,判断上次是否已经压缩过,如果压缩过,获取上次压缩文件的路径
		   try {
			   if(!tempFile.exists())ZipUtils.getPackAgeDownLoad(flist,new FileOutputStream(tempFile),fileMaxSize,fileSumSize);
		   }catch (Exception e){
			   if(tempFile.exists())tempFile.delete();
			   throw new DownloadException(e.getMessage(),true);
		   }
		   downloadFileName = CommonUtils.isEmpty(downloadFileName)?"download":downloadFileName;
		   return new DownloadFile(tempFile,downloadFileName+".zip",true);
		}
	}
	
	
	/**
	 * 解压文件
	 * @param zipfile: 被解压的zip文件
	 */
	public static void decompress(File zipfile) {
		decompress(zipfile, null);
	}
	
	/**
	 * 解压文件
	 * @param zipfile: 被解压的zip文件
	 * @param directory: 解压之后的文件所存放的目录 , 为null表示为zipfile所在目录
	 */
	public static void decompress(File zipfile, File directory) {
		try {
			if(directory == null) {
				directory = zipfile.getParentFile();
			}else {
				if(!directory.isDirectory()) throw new ZipException(" the file:'"+directory.getCanonicalPath()+"' is not directory! ");
			}
			ZipFile zip = new ZipFile(zipfile);
			try {
				Enumeration<? extends java.util.zip.ZipEntry> es = zip.entries();
				while(es.hasMoreElements()) {
					java.util.zip.ZipEntry entry = es.nextElement();
					if(entry.isDirectory()) continue ;
					File item = new File(directory, entry.getName());
					File itemdir = item.getParentFile();
					if(!itemdir.isDirectory()) itemdir.mkdirs();
					FileUtils.copy(zip.getInputStream(entry), new FileOutputStream(item));
				}
			}finally {
				if(zip != null) zip.close();
			}
		}catch(Exception e) {
			throw (e instanceof ZipException) ? (ZipException)e : new ZipException(e);
		}
	}
	
	//----------------------------------------------------文件打包------------------------------------------------------------------
	
	/**
	 * 将文件打包到指定输出流 
	 * @param files 被打包的文件数组
	 * @param out 被指定输出流  
	 * @throws IOException 
	 */
	public static void getPackAgeDownLoad(List<MyFile> files, OutputStream out)throws IOException {
		getPackAgeDownLoad(files, out, false,-1,-1);
	}
	
	/**
	 * 将文件打包到指定输出流 
	 * @param files 被打包的文件
	 * @param out 被指定输出流 
	 * @param fileMaxSize 单个文件的最大大小(M) -1 = 没有限制 
	 * @param fileSumSize 总文件的最大大小(M) -1 = 没有限制
	 * @throws IOException 
	 */
	public static void getPackAgeDownLoad(List<MyFile> files, OutputStream out,int fileMaxSize,int fileSumSize)throws IOException {
		getPackAgeDownLoad(files, out, false,fileMaxSize, fileSumSize);
	}

	/**
	 * 将文件打包到指定输出流 
	 * @param files 被打包的文件
	 * @param out 被指定输出流
	 * @param isDelete  是否删除打包文件 true=删除
	 * @param fileMaxSize 单个文件的最大大小 -1 = 没有限制 
	 * @param fileSumSize 总文件的最大大小 -1 = 没有限制
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	public static void getPackAgeDownLoad(List<MyFile> files, OutputStream out,boolean isDelete,int fileMaxSize,int fileSumSize)throws IOException { 
		try{
			long maxsize = fileMaxSize*1024*1024;  //M
			long sunsize = fileSumSize*1024*1024;  //M
			ZipOutputStream zipout = new ZipOutputStream(out); 
			zipout.setLevel(1);  
			long sum = 0;
			for (int i = 0; i < files.size(); i++) {
				MyFile file = files.get(i);
				if(fileMaxSize!=-1&&file.length()>maxsize){
						throw new ZipException(file.getFilename()+" 文件大小超过:"+fileMaxSize+"M,建议单独下载!");
				}
				sum+=file.length();
			}
			if(fileSumSize!=-1&&sum>sunsize)throw new ZipException("文件总大小超过:"+fileSumSize+"M,建议大文件单独下载!");
			Map<String,Long> fileMap = new HashMap<String,Long>();
			for (int i = 0; i < files.size(); i++) {
				MyFile file = files.get(i);
				if(file.canRead()) {
					String filename = new String(file.getFilename().getBytes());
					Long len = fileMap.get(filename);
					if(len!=null){
						if(len == file.length())continue;
						filename ="1_"+filename;
					}
					fileMap.put(filename,file.length());
					zipout.putNextEntry(new ZipEntry(filename));
					BufferedInputStream fr = new BufferedInputStream(new FileInputStream(file));
					int b;
					while ((b = fr.read()) != -1)zipout.write(b);
					fr.close();
					zipout.closeEntry();
				}
				if (isDelete&&file.exists()) {
					file.delete();
				}
			} 
			zipout.finish();
			zipout.flush();
			out.flush(); 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(out!=null)out.close();
		}
		
	}
	
	
	public static void main(String[] args) {
		File zipfile = new File("C:\\Users\\JIE\\Desktop\\src.zip");
		File directory = new File("C:\\Users\\JIE\\Desktop\\aaa");
		
		
		decompress(zipfile, directory);
	}
	
}
