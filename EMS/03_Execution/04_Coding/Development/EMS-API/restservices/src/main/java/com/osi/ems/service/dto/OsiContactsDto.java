package com.osi.ems.service.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class OsiContactsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer contactId;
	private String contactName;
	private String contactNumber;
	private String countryCode;
	private String contactType;
	private String relation;
	private Integer seq;
	private Integer employeeId;
	private Integer createdBy;
	private String creationDate;
	private Integer lastUpdatedBy;
	private String lastUpdateDate;

	@Override
	public String toString() {
		return "OsiEmployeeContacts[contactId=" + contactId + " , contactName = " + contactName + " , contactNumber="
				+ contactNumber + "," + "countryCode = " + countryCode + " , contactType = " + contactType
				+ " ,relation =  " + relation + " , seq = " + seq + "," + "employeeId = " + employeeId
				+ " , createdBy = " + createdBy + " , creationDate = " + creationDate + " ,lastUpdatedBy = "
				+ lastUpdatedBy + "," + "lastUpdateDate =" + lastUpdateDate;
	}

	public OsiContactsDto() {
	}

	public OsiContactsDto(Integer contactId, String contactName, String contactNumber, String countryCode,
			String contactType, String relation, Integer seq, Integer employeeId, Integer createdBy,
			String creationDate, Integer lastUpdatedBy, String lastUpdateDate) {

		super();
		this.contactId = contactId;
		this.contactName = contactName;
		this.contactNumber = contactNumber;
		this.contactType = contactType;
		this.countryCode = countryCode;
		this.relation = relation;
		this.seq = seq;
		this.employeeId = employeeId;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "last_update_date")
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
}
