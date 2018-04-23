package com.osi.urm.service.dto;

// Generated Dec 1, 2016 5:20:37 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class OsiOperationsDTO implements java.io.Serializable {

	private Integer id;
	private String name;
	private String description;
	private String url;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private Set<OsiFuncOperationsDTO> osiFuncOperationses = new HashSet<OsiFuncOperationsDTO>(
			0);
	private Set<OsiUserOperationExclDTO> osiUserOperationExcls = new HashSet<OsiUserOperationExclDTO>(
			0);
	private Integer businessGroupId;
	private Integer active;

	public OsiOperationsDTO() {
	}

	public OsiOperationsDTO(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public OsiOperationsDTO(Integer id, String name, String description,
			Integer createdBy, Date createdDate, Integer updatedBy,
			Date updatedDate, Set<OsiFuncOperationsDTO> osiFuncOperationses,
			Set<OsiUserOperationExclDTO> osiUserOperationExcls) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.osiFuncOperationses = osiFuncOperationses;
		this.osiUserOperationExcls = osiUserOperationExcls;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<OsiFuncOperationsDTO> getOsiFuncOperationses() {
		return this.osiFuncOperationses;
	}

	public void setOsiFuncOperationses(
			Set<OsiFuncOperationsDTO> osiFuncOperationses) {
		this.osiFuncOperationses = osiFuncOperationses;
	}

	public Set<OsiUserOperationExclDTO> getOsiUserOperationExcls() {
		return this.osiUserOperationExcls;
	}

	public void setOsiUserOperationExcls(
			Set<OsiUserOperationExclDTO> osiUserOperationExcls) {
		this.osiUserOperationExcls = osiUserOperationExcls;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	
}
