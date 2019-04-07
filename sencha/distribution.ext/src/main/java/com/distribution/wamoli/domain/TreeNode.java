package com.distribution.wamoli.domain;

import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.distribution.wamoli.common.bean.BasePojo;



//@Table(name = "tree_node")
public class TreeNode extends BasePojo<TreeNode> implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


	private String id;

	private String text;

	private String parentid;

	private String url;

	private String type;

	private boolean leaf;

	private boolean expanded;
	
	private boolean disabled;

	private String iconCls;

	private String iconColor;

	private int level;
	
	private String cls;
	
	private String icon;
	
	private Boolean checked = null;
	
	private boolean allowDrop = true;
	
	private boolean allowDrag = true;
	
	private boolean loaded = false;
	
	private boolean loading = false;
	
	private String href;
	
	private String hrefTarget;
	
	private boolean visible = true;

	private String param1;

	private String param2;

	private String param3;

	private double param4;

	private double param5;
	
	private boolean linkIcon = true;
	@Transient
	private List<TreeNode> children;
	@Transient
	private Object attributes;

	public TreeNode() {
	}

	public TreeNode(String id, String parentId, String text) {
		this.id = id;
		this.parentid = parentId;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getParentId() {
		return parentid;
	}

	public void setParentId(String parentId) {
		this.parentid = parentId;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getIcon() {
		return (!StringUtils.isEmpty(icon) && linkIcon)?("classic/resources/images/icons/16x16/"+icon):icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isAllowDrop() {
		return allowDrop;
	}

	public void setAllowDrop(boolean allowDrop) {
		this.allowDrop = allowDrop;
	}

	public boolean isAllowDrag() {
		return allowDrag;
	}

	public void setAllowDrag(boolean allowDrag) {
		this.allowDrag = allowDrag;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public boolean isLoading() {
		return loading;
	}

	public void setLoading(boolean loading) {
		this.loading = loading;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public boolean isLinkIcon() {
		return linkIcon;
	}

	public void setLinkIcon(boolean linkIcon) {
		this.linkIcon = linkIcon;
	}

	public String getIconColor() {
		return iconColor;
	}

	public void setIconColor(String iconColor) {
		this.iconColor = iconColor;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public double getParam4() {
		return param4;
	}

	public void setParam4(double param4) {
		this.param4 = param4;
	}

	public double getParam5() {
		return param5;
	}

	public void setParam5(double param5) {
		this.param5 = param5;
	}
}
