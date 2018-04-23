package com.osi.ems.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "osi_emp_visa_details")
public class OsiEmpVisaDetails implements java.io.Serializable {

	private Integer visaId;
	private Integer employeeId;
	private String visaNumber;
	private String dateOfIssue;
	private String dateOfExpiry;
	private String issuanceAuthority;
	private String placeOfIssue;
	private String visaType;
	private String countryOfVisa;
	private String singleMultiple;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date lastUpdateDate;

	public OsiEmpVisaDetails() {
	}

	public OsiEmpVisaDetails(Integer visaId, String visaNumber, String dateOfIssue, String dateOfExpiry, 
							 String issuanceAuthority, String placeOfIssue, String visaType, String countryOfVisa, 
							 String singleMultiple, Integer createdBy, Date createdDate, Integer updatedBy, 
							 Date lastUpdateDate, Integer employeeId) {
		this.visaId = visaId;
		this.employeeId = employeeId;
		this.visaNumber = visaNumber;
		this.dateOfIssue = dateOfIssue;
		this.dateOfExpiry = dateOfExpiry;
		this.issuanceAuthority = issuanceAuthority;
		this.placeOfIssue = placeOfIssue;
		this.visaType = visaType;
		this.countryOfVisa = countryOfVisa;
		this.singleMultiple = singleMultiple;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.lastUpdateDate = lastUpdateDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "visa_id", unique = true, nullable = false)
	public Integer getVisaId() {
		return this.visaId;
	}
	
	public void setVisaId(Integer visaId) {
		this.visaId = visaId;
	}

	@Column(name = "employee_id")
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "visa_number", length = 100)
	public String getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_issue", length = 19)
	public String getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_expiry", length = 19)
	public String getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	@Column(name = "issuance_authority", length = 100)
	public String getIssuanceAuthority() {
		return issuanceAuthority;
	}

	public void setIssuanceAuthority(String issuanceAuthority) {
		this.issuanceAuthority = issuanceAuthority;
	}

	@Column(name = "place_of_issue", length = 100)
	public String getPlaceOfIssue() {
		return placeOfIssue;
	}

	public void setPlaceOfIssue(String placeOfIssue) {
		this.placeOfIssue = placeOfIssue;
	}

	@Column(name = "visa_type", length = 20)
	public String getVisaType() {
		return visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	@Column(name = "country_of_visa", length = 50)
	public String getCountryOfVisa() {
		return countryOfVisa;
	}

	public void setCountryOfVisa(String countryOfVisa) {
		this.countryOfVisa = countryOfVisa;
	}

	@Column(name = "single_multiple", length = 20)
	public String getSingleMultiple() {
		return singleMultiple;
	}

	public void setSingleMultiple(String singleMultiple) {
		this.singleMultiple = singleMultiple;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_by")
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update_date", length = 19)
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}
