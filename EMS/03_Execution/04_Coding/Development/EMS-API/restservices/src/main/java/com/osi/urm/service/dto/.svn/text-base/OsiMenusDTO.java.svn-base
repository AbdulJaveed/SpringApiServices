package com.osi.urm.service.dto;

// Generated Dec 1, 2016 5:20:37 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class OsiMenusDTO implements java.io.Serializable {

	private Integer id;
	private String menuName;
	private String description;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private List<Integer> menus;
	public List<Integer> getMenus() {
		return menus;
	}

	public void setMenus(List<Integer> menus) {
		this.menus = menus;
	}

	private Set<OsiMenuEntriesDTO> osiMenuEntriesesForSubMenuId = new HashSet<OsiMenuEntriesDTO>(
			0);
	private Set<OsiResponsibilitiesDTO> osiResponsibilitieses = new HashSet<OsiResponsibilitiesDTO>(
			0);
	private Set<OsiMenuEntriesDTO> osiMenuEntriesesForMenuId = new HashSet<OsiMenuEntriesDTO>(
			0);
	private Integer businessGroupId;
	private Integer active;
	private Integer reportGrpId;
	

	public OsiMenusDTO() {
	}

	public OsiMenusDTO(Integer id, String menuName) {
		this.id = id;
		this.menuName = menuName;
	}

	public OsiMenusDTO(Integer id, String menuName, String description,
			Integer createdBy, Date createdDate, Integer updatedBy,
			Date updatedDate, Integer businessGroupId, Integer active, Set<OsiMenuEntriesDTO> osiMenuEntriesesForSubMenuId,
			Set<OsiResponsibilitiesDTO> osiResponsibilitieses,
			Set<OsiMenuEntriesDTO> osiMenuEntriesesForMenuId) {
		this.id = id;
		this.menuName = menuName;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.businessGroupId = businessGroupId;
		this.active = active;
		this.osiMenuEntriesesForSubMenuId = osiMenuEntriesesForSubMenuId;
		this.osiResponsibilitieses = osiResponsibilitieses;
		this.osiMenuEntriesesForMenuId = osiMenuEntriesesForMenuId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}


	public Set<OsiMenuEntriesDTO> getOsiMenuEntriesesForSubMenuId() {
		return this.osiMenuEntriesesForSubMenuId;
	}

	public void setOsiMenuEntriesesForSubMenuId(
			Set<OsiMenuEntriesDTO> osiMenuEntriesesForSubMenuId) {
		this.osiMenuEntriesesForSubMenuId = osiMenuEntriesesForSubMenuId;
	}

	public Set<OsiResponsibilitiesDTO> getOsiResponsibilitieses() {
		return this.osiResponsibilitieses;
	}

	public void setOsiResponsibilitieses(
			Set<OsiResponsibilitiesDTO> osiResponsibilitieses) {
		this.osiResponsibilitieses = osiResponsibilitieses;
	}

	public Set<OsiMenuEntriesDTO> getOsiMenuEntriesesForMenuId() {
		return this.osiMenuEntriesesForMenuId;
	}

	public void setOsiMenuEntriesesForMenuId(
			Set<OsiMenuEntriesDTO> osiMenuEntriesesForMenuId) {
		this.osiMenuEntriesesForMenuId = osiMenuEntriesesForMenuId;
	}

	public Integer getReportGrpId() {
		return reportGrpId;
	}

	public void setReportGrpId(Integer reportGrpId) {
		this.reportGrpId = reportGrpId;
	}

}
