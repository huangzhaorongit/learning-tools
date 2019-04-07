package com.distribution.wamoli.common.context;

import java.io.File;

import com.distribution.wamoli.common.critical.Local;
import org.springframework.stereotype.Component;

/**
 * 项目资源目录
 * @author LIUM
 */
@Component
public class ProjectSpace {

	/**Web项目目录*/
	private String workSpaceRoot;

	public String getWorkSpaceRoot() {
		return workSpaceRoot;
	}

	public void setWorkSpaceRoot(String workSpaceRoot) {
		this.workSpaceRoot = workSpaceRoot;
	}

	/**
	 * 获取项目根目录
	 * @return
	 */
	public String getRoot() {
		return Local.getCriticalObject().getRoot();
	}

	/**
	 * 获取WEB-INF配置目录
	 * @return
	 */
	public String getWebInfo() {
		return getRoot() + "WEB-INF" + File.separatorChar;
	}

	/**
	 * 获取Class根目录
	 * @return
	 */
	public String getClassRoot() {
		return getWebInfo() + "classes" + File.separator;
	}


	/**
	 * 系统公共资料模版空间
	 * @return
	 */
	public File getTemplateSpace() {
		return getSpace(getWebInfo()+ "template" + File.separator);
	}

	/**
	 * 获取系统资源库根目录
	 * @return
	 */
	public String getLibraryRoot() {
		return getWebInfo() + "lib" + File.separator;
	}

	private File getSpace(String path) {
		return getSpace(null, path);
	}

	public String getReportDir(){
		return getFramework() +"plugins"+File.separator+"runqian"+File.separator+"report";
	}

	/**
	 * 获取framework目录
	 * @return
	 */
	public String getFramework() {
		return getRoot() + "framework" + File.separator;
	}


	private File getSpace(File parent, String name) {
		File file = parent!=null ? new File(parent, name) : new File(name);
		if(!file.isDirectory()) {
			boolean r = file.mkdirs();
		 //	if(!r) throw new ProjectException(" create localspace:'"+file.getPath()+"' is wrong! ");
		}
		return file;
	}
}
