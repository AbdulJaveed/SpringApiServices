package com.osi.urm.service.dto;

// Generated Dec 1, 2016 5:20:37 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OsiLookupTypesDTO implements java.io.Serializable {

	private Long id;
	private String lookupName;
	private String lookupCode;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private List<Long> lookupTypeIds;
	private Set<OsiLookupValuesDTO> osiLookupValueses = new HashSet<OsiLookupValuesDTO>(
			0);
	private Integer businessGroupId;
	private Integer active;
	private Integer inactivable;
	
	public OsiLookupTypesDTO() {
	}

	public OsiLookupTypesDTO(Long id, String lookupName, String lookupCode) {
		this.id = id;
		this.lookupName = lookupName;
		this.lookupCode = lookupCode;
	}

	public OsiLookupTypesDTO(Long id, String lookupName, String lookupCode,
			Integer createdBy, Date createdDate, Integer updatedBy,
			Date updatedDate, Set<OsiLookupValuesDTO> osiLookupValueses) {
		this.id = id;
		this.lookupName = lookupName;
		this.lookupCode = lookupCode;
		this.setCreatedBy(createdBy);
		this.createdDate = createdDate;
		this.setUpdatedBy(updatedBy);
		this.updatedDate = updatedDate;
		this.osiLookupValueses = osiLookupValueses;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLookupName() {
		return this.lookupName;
	}

	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}

	public String getLookupCode() {
		return this.lookupCode;
	}

	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}

	

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set<OsiLookupValuesDTO> getOsiLookupValueses() {
		return this.osiLookupValueses;
	}

	public void setOsiLookupValueses(Set<OsiLookupValuesDTO> osiLookupValueses) {
		this.osiLookupValueses = osiLookupValueses;
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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<Long> getLookupTypeIds() {
		return lookupTypeIds;
	}

	public void setLookupTypeIds(List<Long> lookupTypeIds) {
		this.lookupTypeIds = lookupTypeIds;
	}

	public Integer getInactivable() {
		return inactivable;
	}

	public void setInactivable(Integer inactivable) {
		this.inactivable = inactivable;
	}	

}
