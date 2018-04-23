package com.osi.ems.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "osi_contacts")
public class OsiContacts implements Serializable {

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
	public String toString(){
		return "OsiEmployeeContacts[contactId="+contactId+" , contactName = "+contactName+" , contactNumber="+contactNumber+","
				+"countryCode = "+countryCode+" , contactType = "+contactType+" ,relation =  "+relation+" , seq = "+seq+","
				+"employeeId = "+employeeId+" , createdBy = "+createdBy+" , creationDate = "+creationDate+" ,lastUpdatedBy = "+lastUpdatedBy+","
				+"lastUpdateDate ="+lastUpdateDate;
	}
	public OsiContacts(){
		
	}
	public OsiContacts(Integer contactId,String contactName,String contactNumber,String countryCode,
	 String contactType,String relation,Integer seq,Integer employeeId,Integer createdBy,
	 String creationDate,Integer lastUpdatedBy,String lastUpdateDate){
		
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
	
	@Id
	@Column(name = "contact_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	
	@Column(name = "contact_name")
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	@Column(name = "contact_number")
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@Column(name = "country_code")
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@Column(name = "contact_type")
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	
	@Column(name = "relation")
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	@Column(name = "seq")
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	@Column(name = "employee_id")
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "creation_date")
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	
	@Column(name = "last_updated_by")
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
