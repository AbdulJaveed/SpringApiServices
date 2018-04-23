package com.osi.urm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer businessGroupId;
	private Integer menuId;
	private Integer subMenuId;
	private String title;
	private Integer functionId;
	private String url;
	private Boolean isFolder;
	private Boolean expand;
	private Integer rptGrpId;
	private List<MenuDetails> children = new ArrayList<MenuDetails>();

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getSubMenuId() {
		return subMenuId;
	}

	public void setSubMenuId(Integer subMenuId) {
		this.subMenuId = subMenuId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuDetails> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDetails> children) {
		this.children = children;
	}

	public Boolean getIsFolder() {
		return isFolder;
	}

	public void setIsFolder(Boolean isFolder) {
		this.isFolder = isFolder;
	}

	public Boolean getExpand() {
		return expand;
	}

	public void setExpand(Boolean expand) {
		this.expand = expand;
	}
	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}

	public Integer getRptGrpId() {
		return rptGrpId;
	}

	public void setRptGrpId(Integer rptGrpId) {
		this.rptGrpId = rptGrpId;
	}
	
	

}