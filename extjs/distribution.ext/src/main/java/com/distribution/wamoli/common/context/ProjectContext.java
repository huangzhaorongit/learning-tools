package com.distribution.wamoli.common.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ProjectContext {
	/**
	 * 获取项目空间
	 * @return
	 */
	@Autowired
	private ProjectSpace projectSpace;

	/**
	 * 获取本地空间
	 * @return
	 */
	@Autowired
	private LocalSpace localSpace;

	public ProjectSpace getProjectSpace() {
		if(projectSpace == null){
			projectSpace = new ProjectSpace();
		}
		return projectSpace;
	}

	public void setProjectSpace(ProjectSpace projectSpace) {
		this.projectSpace = projectSpace;
	}

	public LocalSpace getLocalSpace() {
		return localSpace;
	}

	public void setLocalSpace(LocalSpace localSpace) {
		this.localSpace = localSpace;
	}

}
