package com.osi.urm.service.dto;

// Generated Jan 6, 2017 8:49:50 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OsiSegmentStructureHdrDTO implements java.io.Serializable {

	private Integer segmentStructureHdrId;
	private Integer businessGroupId;
	private String segmentStructureHdrDesc;
	private Integer active;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private List<Integer> segmentStructureHdrIds;
	private Integer editable;
	private String  segHdrName;
	
	private Set<OsiSegmentStructureDetailsDTO> osiSegmentStructureDetailses = new HashSet<OsiSegmentStructureDetailsDTO>(
			0);

	public OsiSegmentStructureHdrDTO() {
	}
	public OsiSegmentStructureHdrDTO(Integer segmentStructureHdrId) {
		this.segmentStructureHdrId = segmentStructureHdrId;
	}
	public OsiSegmentStructureHdrDTO(Integer segmentStructureHdrId,
			Integer businessGroupId, String segmentStructureHdrDesc,
			Integer active) {
		this.segmentStructureHdrId = segmentStructureHdrId;
		this.businessGroupId = businessGroupId;
		this.segmentStructureHdrDesc = segmentStructureHdrDesc;
		this.active = active;
	}

	public OsiSegmentStructureHdrDTO(Integer segmentStructureHdrId,
			Integer businessGroupId, String segmentStructureHdrDesc,
			Integer active, Integer createdBy, Date createdDate, Integer updatedBy,
			Date updatedDate,
			Set<OsiSegmentStructureDetailsDTO> osiSegmentStructureDetailses) {
		this.segmentStructureHdrId = segmentStructureHdrId;
		this.businessGroupId = businessGroupId;
		this.segmentStructureHdrDesc = segmentStructureHdrDesc;
		this.active = active;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.osiSegmentStructureDetailses = osiSegmentStructureDetailses;
	}

	public Integer getSegmentStructureHdrId() {
		return this.segmentStructureHdrId;
	}

	public void setSegmentStructureHdrId(Integer segmentStructureHdrId) {
		this.segmentStructureHdrId = segmentStructureHdrId;
	}


	public String getSegmentStructureHdrDesc() {
		return this.segmentStructureHdrDesc;
	}

	public void setSegmentStructureHdrDesc(String segmentStructureHdrDesc) {
		this.segmentStructureHdrDesc = segmentStructureHdrDesc;
	}

	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
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

	public Set<OsiSegmentStructureDetailsDTO> getOsiSegmentStructureDetailses() {
		return this.osiSegmentStructureDetailses;
	}

	public void setOsiSegmentStructureDetailses(
			Set<OsiSegmentStructureDetailsDTO> osiSegmentStructureDetailses) {
		this.osiSegmentStructureDetailses = osiSegmentStructureDetailses;
	}

	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}
	public List<Integer> getSegmentStructureHdrIds() {
		return segmentStructureHdrIds;
	}
	public void setSegmentStructureHdrIds(List<Integer> segmentStructureHdrIds) {
		this.segmentStructureHdrIds = segmentStructureHdrIds;
	}
	public Integer getEditable() {
		return editable;
	}
	public void setEditable(Integer editable) {
		this.editable = editable;
	}
	public String getSegHdrName() {
		return segHdrName;
	}
	public void setSegHdrName(String segHdrName) {
		this.segHdrName = segHdrName;
	}
}
