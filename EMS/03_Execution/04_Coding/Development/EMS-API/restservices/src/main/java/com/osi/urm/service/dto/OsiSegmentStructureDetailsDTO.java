package com.osi.urm.service.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OsiSegmentStructureDetailsDTO implements java.io.Serializable {

	private Integer segmentStructureDetailsId;
	private OsiSegmentStructureHdrDTO osiSegmentStructureHdr;
	private Integer businessGroupId;
	private String segmentStructureDetailsDesc;
	private Integer segmentStructureDetailsSeq;
	private Integer depSegmentStructureDetailsId;
	private Integer isSqlReqdForValidation;
	private String lovDataForValidation;
	private String sqlQueryForValidation;
	private Integer osiSegmentStructureHdrId;
	private Set<OsiLookupValuesDTO> osiLookupValuesList = new HashSet<OsiLookupValuesDTO>();
	public OsiSegmentStructureDetailsDTO() {
	}

	public OsiSegmentStructureDetailsDTO(Integer segmentStructureDetailsId,
			OsiSegmentStructureHdrDTO osiSegmentStructureHdr,
			Integer businessGroupId,
			String segmentStructureDetailsDesc, Integer segmentStructureDetailsSeq,
			Integer depSegmentStructureDetailsId) {
		this.segmentStructureDetailsId = segmentStructureDetailsId;
		this.osiSegmentStructureHdr = osiSegmentStructureHdr;
		this.businessGroupId = businessGroupId;
		this.segmentStructureDetailsDesc = segmentStructureDetailsDesc;
		this.segmentStructureDetailsSeq = segmentStructureDetailsSeq;
		this.depSegmentStructureDetailsId = depSegmentStructureDetailsId;
	}

	public OsiSegmentStructureDetailsDTO(Integer segmentStructureDetailsId,
			OsiSegmentStructureHdrDTO osiSegmentStructureHdr,
			Integer businessGroupId,
			String segmentStructureDetailsDesc, Integer segmentStructureDetailsSeq,
			Integer depSegmentStructureDetailsId, Integer isSqlReqdForValidation,
			String lovDataForValidation, String sqlQueryForValidation) {
		this.segmentStructureDetailsId = segmentStructureDetailsId;
		this.osiSegmentStructureHdr = osiSegmentStructureHdr;
		this.businessGroupId = businessGroupId;
		this.segmentStructureDetailsDesc = segmentStructureDetailsDesc;
		this.segmentStructureDetailsSeq = segmentStructureDetailsSeq;
		this.depSegmentStructureDetailsId = depSegmentStructureDetailsId;
		this.isSqlReqdForValidation = isSqlReqdForValidation;
		this.lovDataForValidation = lovDataForValidation;
		this.sqlQueryForValidation = sqlQueryForValidation;
	}

	public Integer getSegmentStructureDetailsId() {
		return this.segmentStructureDetailsId;
	}

	public void setSegmentStructureDetailsId(Integer segmentStructureDetailsId) {
		this.segmentStructureDetailsId = segmentStructureDetailsId;
	}

	public OsiSegmentStructureHdrDTO getOsiSegmentStructureHdr() {
		return this.osiSegmentStructureHdr;
	}

	public void setOsiSegmentStructureHdr(
			OsiSegmentStructureHdrDTO osiSegmentStructureHdr) {
		this.osiSegmentStructureHdr = osiSegmentStructureHdr;
	}

	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}

	public String getSegmentStructureDetailsDesc() {
		return this.segmentStructureDetailsDesc;
	}

	public void setSegmentStructureDetailsDesc(
			String segmentStructureDetailsDesc) {
		this.segmentStructureDetailsDesc = segmentStructureDetailsDesc;
	}

	public Integer getSegmentStructureDetailsSeq() {
		return this.segmentStructureDetailsSeq;
	}

	public void setSegmentStructureDetailsSeq(Integer segmentStructureDetailsSeq) {
		this.segmentStructureDetailsSeq = segmentStructureDetailsSeq;
	}

	public Integer getDepSegmentStructureDetailsId() {
		return this.depSegmentStructureDetailsId;
	}

	public void setDepSegmentStructureDetailsId(Integer depSegmentStructureDetailsId) {
		this.depSegmentStructureDetailsId = depSegmentStructureDetailsId;
	}

	public Integer getIsSqlReqdForValidation() {
		return this.isSqlReqdForValidation;
	}

	public void setIsSqlReqdForValidation(Integer isSqlReqdForValidation) {
		this.isSqlReqdForValidation = isSqlReqdForValidation;
	}

	public String getLovDataForValidation() {
		return this.lovDataForValidation;
	}

	public void setLovDataForValidation(String lovDataForValidation) {
		this.lovDataForValidation = lovDataForValidation;
	}

	public String getSqlQueryForValidation() {
		return this.sqlQueryForValidation;
	}

	public void setSqlQueryForValidation(String sqlQueryForValidation) {
		this.sqlQueryForValidation = sqlQueryForValidation;
	}

	public Set<OsiLookupValuesDTO> getOsiLookupValuesList() {
		return osiLookupValuesList;
	}

	public void setOsiLookupValuesList(Set<OsiLookupValuesDTO> osiLookupValuesList) {
		this.osiLookupValuesList = osiLookupValuesList;
	}

	public Integer getOsiSegmentStructureHdrId() {
		return osiSegmentStructureHdrId;
	}

	public void setOsiSegmentStructureHdrId(Integer osiSegmentStructureHdrId) {
		this.osiSegmentStructureHdrId = osiSegmentStructureHdrId;
	}
	
}
