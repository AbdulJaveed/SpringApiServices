package com.osi.urm.service.dto;

// Generated Dec 1, 2016 5:20:37 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.List;

public class OsiRespUserDTO implements java.io.Serializable {

	private Integer id;
/*	private OsiUserDTO osiUser;*/
	private Integer employeeId;
	private OsiResponsibilitiesDTO osiResponsibilities;
	private String startDate;
	private String endDate;
	private Boolean defaultResp;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private Integer businessGroupId;
	private List<Integer> userRespIds;

	public OsiRespUserDTO() {
	}

	public OsiRespUserDTO(Integer id, String startDate, String endDate,
			List<Integer> userRespIds) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userRespIds = userRespIds;
	}

	public OsiRespUserDTO(Integer id, Integer employeeId,
			OsiResponsibilitiesDTO osiResponsibilities, String startDate,
			String endDate, Boolean defaultResp, Integer createdBy,
			Date createdDate, Integer updatedBy, Date updatedDate,
			List<Integer> userRespIds) {
		this.id = id;
		this.employeeId = employeeId;
		this.osiResponsibilities = osiResponsibilities;
		this.startDate = startDate;
		this.endDate = endDate;
		this.defaultResp = defaultResp;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.userRespIds = userRespIds;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public OsiResponsibilitiesDTO getOsiResponsibilities() {
		return this.osiResponsibilities;
	}

	public void setOsiResponsibilities(
			OsiResponsibilitiesDTO osiResponsibilities) {
		this.osiResponsibilities = osiResponsibilities;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Boolean getDefaultResp() {
		return this.defaultResp;
	}

	public void setDefaultResp(Boolean defaultResp) {
		this.defaultResp = defaultResp;
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

	public List<Integer> getUserRespIds() {
		return userRespIds;
	}

	public void setUserRespIds(List<Integer> userRespIds) {
		this.userRespIds = userRespIds;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

}
