package com.osi.urm.service.dto;


public class OsiMenuRespDTO {
	
	private Integer menuRespId;
	private OsiResponsibilitiesDTO OsiResponsibilities;
	private OsiMenusDTO osiMenus;
	private Integer businessGroupId;
	
	public OsiMenuRespDTO(Integer menuRespId, OsiResponsibilitiesDTO osiResponsibilities, OsiMenusDTO osiMenus,
			Integer businessGroupId) {
		this.menuRespId = menuRespId;
		OsiResponsibilities = osiResponsibilities;
		this.osiMenus = osiMenus;
		this.businessGroupId = businessGroupId;
	}
	
	public OsiMenuRespDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getMenuRespId() {
		return menuRespId;
	}

	public void setMenuRespId(Integer menuRespId) {
		this.menuRespId = menuRespId;
	}

	public OsiResponsibilitiesDTO getOsiResponsibilities() {
		return OsiResponsibilities;
	}

	public void setOsiResponsibilities(OsiResponsibilitiesDTO osiResponsibilities) {
		OsiResponsibilities = osiResponsibilities;
	}

	public OsiMenusDTO getOsiMenus() {
		return osiMenus;
	}

	public void setOsiMenus(OsiMenusDTO osiMenus) {
		this.osiMenus = osiMenus;
	}

	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}
	
	

}
