package com.osi.urm.service.dto;

// Generated Dec 1, 2016 5:47:35 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.osi.urm.domain.OsiRespUser;

public class OsiResponsibilitiesDTO implements java.io.Serializable {

	private Integer id;
	private OsiMenusDTO osiMenus;
	private String respName;
	private String description;
	private Date startDate;
	private Date endDate;
	private Integer createdBy;
	private Integer active;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private Set<OsiRespUser> osiRespUsers = new HashSet<OsiRespUser>(0);
	private Set<OsiMenuRespDTO> osiMenuResp = new HashSet<OsiMenuRespDTO>(0);

	private Integer businessGroupId;
	//private Integer reportGrpId;

	public OsiResponsibilitiesDTO() {
	}

	public OsiResponsibilitiesDTO(Integer id, OsiMenusDTO osiMenus, String respName) {
		this.id = id;
		this.osiMenus = osiMenus;
		this.respName = respName;
	}

	public OsiResponsibilitiesDTO(Integer id, OsiMenusDTO osiMenus, String respName,
			String description, Date startDate, Date endDate,
			Integer createdBy, Date createdDate, Integer updatedBy,
			Date updatedDate, Set<OsiRespUser> osiRespUsers, Set<OsiMenuRespDTO> osiMenuResp) {
		this.id = id;
		this.osiMenus = osiMenus;
		this.respName = respName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.osiRespUsers = osiRespUsers;
		this.osiMenuResp = osiMenuResp;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OsiMenusDTO getOsiMenus() {
		return this.osiMenus;
	}
	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}
	public void setOsiMenus(OsiMenusDTO osiMenus) {
		this.osiMenus = osiMenus;
	}

	public String getRespName() {
		return this.respName;
	}

	public void setRespName(String respName) {
		this.respName = respName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public Set<OsiRespUser> getOsiRespUsers() {
		return this.osiRespUsers;
	}

	public void setOsiRespUsers(Set<OsiRespUser> osiRespUsers) {
		this.osiRespUsers = osiRespUsers;
	}
	
	public Set<OsiMenuRespDTO> getOsiMenuResp() {
		return osiMenuResp;
	}

	public void setOsiMenuResp(Set<OsiMenuRespDTO> osiMenuResp) {
		this.osiMenuResp = osiMenuResp;
	}

	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}

	/*public Integer getReportGrpId() {
		return reportGrpId;
	}

	public void setReportGrpId(Integer reportGrpId) {
		this.reportGrpId = reportGrpId;
	}*/
}
