package com.osi.urm.service.dto;

// Generated Dec 1, 2016 5:20:37 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OsiLookupValuesDTO implements java.io.Serializable {

	private Long id;
	private OsiLookupTypesDTO osiLookupTypes;
	private String lookupValue;
	private String lookupDesc;
	private Integer segmentSeq;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private Integer businessGroupId;
	private Integer lookupSeqNum;

	public OsiLookupValuesDTO() {
	}

	public OsiLookupValuesDTO(Long id, OsiLookupTypesDTO osiLookupTypes,
			String lookupValue, String lookupDesc) {
		this.id = id;
		this.osiLookupTypes = osiLookupTypes;
		this.lookupValue = lookupValue;
		this.lookupDesc = lookupDesc;
	}

	public OsiLookupValuesDTO(Long id, OsiLookupTypesDTO osiLookupTypes,
			String lookupValue, String lookupDesc, Integer createdBy,
			Date createdDate, Integer updatedBy, Date updatedDate,Integer lookupSeqNum) {
		this.id = id;
		this.osiLookupTypes = osiLookupTypes;
		this.lookupValue = lookupValue;
		this.lookupDesc = lookupDesc;
		this.setCreatedBy(createdBy);
		this.createdDate = createdDate;
		this.setUpdatedBy(updatedBy);
		this.updatedDate = updatedDate;
		this.lookupSeqNum=lookupSeqNum;
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OsiLookupTypesDTO getOsiLookupTypes() {
		return this.osiLookupTypes;
	}

	public void setOsiLookupTypes(OsiLookupTypesDTO osiLookupTypes) {
		this.osiLookupTypes = osiLookupTypes;
	}

	public String getLookupValue() {
		return this.lookupValue;
	}

	public void setLookupValue(String lookupValue) {
		this.lookupValue = lookupValue;
	}

	public String getLookupDesc() {
		return this.lookupDesc;
	}

	public void setLookupDesc(String lookupDesc) {
		this.lookupDesc = lookupDesc;
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

	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
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

	public Integer getSegmentSeq() {
		return segmentSeq;
	}

	public void setSegmentSeq(Integer segmentSeq) {
		this.segmentSeq = segmentSeq;
	}

	public Integer getLookupSeqNum() {
		return lookupSeqNum;
	}

	public void setLookupSeqNum(Integer lookupSeqNum) {
		this.lookupSeqNum = lookupSeqNum;
	}
	
}
