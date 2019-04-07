package com.distribution.wamoli.common.context;

import java.io.File;
import java.io.IOException;

import com.distribution.wamoli.config.JHipsterProperties;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.distribution.wamoli.common.bean.MyFile;
import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.utils.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 本地资源目录
 * @author LIUM
 */
@Component
public class LocalSpace {

	@Autowired
	JHipsterProperties properties;

	public String getLocalSpaceRoot() {
		return properties.getWamoli().getLocalSpace();
	}

	public String getRoot(){
		return this.getLocalSpaceRoot() + File.separator;
	}

	/**
	 * 获取本地根目录   <b>localspace/nsc/</b>
	 * @return
	 */
	public File getRoot(String companyid) {
		if(StringUtils.isEmpty(companyid)) throw new RuntimeException(" the localspace-companyid is NULL argument! ");
		return getSpace(this.getLocalSpaceRoot() + File.separator + companyid + File.separator);
	}

	/**
	 * 获取指定公司下用户空间目录  <b>localspace/nsc/0100/users/</b>
	 * @param companyid
	 * @return
	 */
	public File getUserSpace(String companyid) {
		return getSpace(getRoot(companyid), Globals.USER_SPACE + File.separator);
	}

	/**
	 * 获取指定指定公司下用户空间目录 <b>localspace/nsc/0100//users/001/</b>
	 * @param companyid
	 * @param userid
	 * @return
	 */
	public File getUserSpace(String companyid, String userid) {
		return getSpace(getUserSpace(companyid),userid + File.separator);
	}

	/**
	 * 获取指定用户临时目录 <b>localspace/nsc/0100/users/001/temp/</b>
	 * @param companyid
	 * @param userid
	 * @return
	 */
	public File getUserTempSpace(String companyid, String userid) {
		return getSpace(getUserSpace(companyid, userid), Globals.USER_TEMPSPACE+File.separator);
	}

	/**
	 * 获取附件目录 <b>localspace/nsc/0100/fileattach/</b>
	 * @param companyid
	 * @return
	 */
	public File getFileAttachSpace(String companyid) {
		return getSpace(getRoot(companyid), Globals.FILEATTACH_SPACE+File.separator);
	}

	/**
	 * 获取公司文档资料目录 <b>localspace/nsc/0100/document/</b>
	 * @return
	 */
	public File getDocumentSpace(String companyid) {
		return getSpace(getRoot(companyid),Globals.DOCUMENT_SPACE +File.separator);
	}

	/**
	 * 获取公司人员照片目录  <b>localspace/nsc/0100/photo/</b>
	 * @return
	 */
	public File getPeronnelPhotoSpace(String companyid) {
		return getSpace(getRoot(companyid),Globals.PHOTO_SPACE+File.separator);
	}

	/**
	 * 获取系统公共临时目录  <b>localspace/nsc/0100/temp/</b>
	 * @return
	 */
	public File getTempSpace(String companyid) {
		return getSpace(getRoot(companyid),Globals.USER_TEMPSPACE +File.separator);
	}

	/**
	 * 获取系统文件PDF预览目录  <b>localspace/nsc/0100/pdf/</b>
	 * @return
	 */
	public File getPdfSpace(String companyid) {
		return getSpace(getRoot(companyid),Globals.PDF_SPACE +File.separator);
	}

	/**
	 * 获取系统公共资料目录 <b>localspace/nsc/document/</b>
	 * @return
	 */
	public File getDocumentSpace() {
		return getSpace(getRoot() + Globals.DOCUMENT_SPACE + File.separator);
	}



	/**
	 * 获取系统公共资料目录 <b>localspace/nsc/document/</b>
	 * @return
	 */
	public File getZipSpace(String companyid) {
		return getSpace(getRoot() + "zip"
				+ File.separator +companyid + File.separator);
	}




	/**
	 * 获取系统公共资料目录 <b>localspace/nsc/document/</b>
	 * @return
	 */
	public File getExcelSpace(String companyid) {
		return getSpace( getRoot()+ "excel"
				+ File.separator +companyid + File.separator );
	}





	/**
	 * 获取系统公共资料模版目录  <b>localspace/nsc/template/</b>
	 * @return
	 */
	public File getTemplateSpace() {
		return getSpace(getDocumentSpace() , Globals.USER_TEMPLATESPACE + File.separator);
	}

	/**
	 * 清除系统中全部临时文件
	 */
	public void clearUserTempSpace(){
		File root = getSpace(getRoot());
		File[] files = root.listFiles();
		for (int i = 0; i < files.length; i++) {
			File userSpace = new File(files[i],Globals.USER_SPACE);
			File tempSpace = new File(files[i],Globals.USER_TEMPSPACE);
			if(userSpace.exists() && userSpace.isDirectory()){
				File[] users = userSpace.listFiles();
				for (int j = 0; j < users.length; j++) {
					try {
						FileUtils.deleteDirectory(new File(users[j],Globals.USER_TEMPSPACE));
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
			if(tempSpace.exists() && tempSpace.isDirectory()){
				try {
					FileUtils.deleteDirectory(tempSpace);
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}

	private File getSpace(String path) {
		return getSpace(null, path);
	}

	/**
	 * 文件路径不存在且创建
	 * @param parent 基础文件路径
	 * @param name   附加路径
	 * @return
	 */
	private File getSpace(File parent, String name) {
		File file = parent!=null ? new File(parent, name) : new File(name);
		if(!file.isDirectory()) {
			boolean r = file.mkdirs();
		    if(!r) throw new RuntimeException(" create localspace:'"+file.getPath()+"' is wrong! ");
		}
		return file;
	}

	/***********************************************常用文件路径获取方式**************************************************************/
	/**
	 * 获取附件文件路径
	 * @param id
	 * @return
	 */
	public MyFile getFileAttachPath(String id){
		File fileSpace = getFileAttachSpace(Local.getCompanyid());
		MyFile file = new MyFile(fileSpace,id);
		return file;
	}

	/**
	 * 获取预览的pdf文件路径
	 * @param id
	 * @return
	 */
	public MyFile getPdfPath(String id){
		File pdfSpace = getPdfSpace(Local.getCompanyid());
		MyFile pdf = new MyFile(pdfSpace,id+".pdf");
		return pdf;
	}

	/**
	 * 临时文件存储路径
	 * @param filename
	 * @param filesuffix
	 * @return
	 */
	public MyFile getTempFileSpace(String filename,String filesuffix){
		File tempSpace = getTempSpace(Local.getCompanyid());
		MyFile tempfile = new MyFile(tempSpace,filename+"."+filesuffix);
		return tempfile;
	}
}
