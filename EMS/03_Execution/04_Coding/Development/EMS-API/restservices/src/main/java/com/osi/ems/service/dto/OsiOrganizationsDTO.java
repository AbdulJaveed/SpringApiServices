package com.osi.ems.service.dto;

import java.io.Serializable;
import java.util.Date;

public class OsiOrganizationsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer orgId;
	private String orgName;
	private String orgShortName;
	private String website;
	private String faxNumber;
	private String faxCode;
	private String countryCode;
	private String phoneNumber;
	private String locations;
	private String countryName;
	private Integer parentOrgId;
	private String contactPersonName;
	private String emailId;
	private Integer contactPersonId;
	private OsiCurrenciesDTO baseCurrencyId;
	private OsiCurrenciesDTO reportingCurrencyId;
	private Integer active;
	private Integer overheadPct;
	private Integer totalHrsPerYear;
	private String costCalc;
	private Integer interEmpOverheadPct;
	private Integer createdBy;
	private Date creationDate;
	private Integer lastUpdatedBy;
	private Date lastUpdateDate;
	
	private String startDayOfWeek;
	
	public OsiOrganizationsDTO() {
		
	}
	
	public OsiOrganizationsDTO(String orgName, String orgShortName, String website, String faxNumber,String faxCode, String countryCode,
			String phoneNumber, Integer parentOrgId, String emailId, Integer contactPersonId, OsiCurrenciesDTO baseCurrencyId,
			OsiCurrenciesDTO reportingCurrencyId, Integer active, Integer overheadPct,Integer totalHrsPerYear, String costCalc,Integer interEmpOverheadPct, Integer createdBy, Date creationDate, Integer lastUpdatedBy,
			Date lastUpdateDate, String startDayOfWeek) {
		super();
		this.orgName = orgName;
		this.orgShortName = orgShortName;
		this.website = website;
		this.faxNumber = faxNumber;
		this.setFaxCode(faxCode);
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.parentOrgId = parentOrgId;
		this.emailId = emailId;
		this.contactPersonId = contactPersonId;
		this.baseCurrencyId = baseCurrencyId;
		this.reportingCurrencyId = reportingCurrencyId;
		this.active = active;
		this.overheadPct = overheadPct;
		this.totalHrsPerYear = totalHrsPerYear;
		this.costCalc = costCalc;
		this.interEmpOverheadPct = interEmpOverheadPct;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.startDayOfWeek = startDayOfWeek;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgShortName() {
		return orgShortName;
	}

	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Integer parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getContactPersonId() {
		return contactPersonId;
	}

	public void setContactPersonId(Integer contactPersonId) {
		this.contactPersonId = contactPersonId;
	}

	public OsiCurrenciesDTO getBaseCurrencyId() {
		return baseCurrencyId;
	}

	public void setBaseCurrencyId(OsiCurrenciesDTO baseCurrencyId) {
		this.baseCurrencyId = baseCurrencyId;
	}

	public OsiCurrenciesDTO getReportingCurrencyId() {
		return reportingCurrencyId;
	}

	public void setReportingCurrencyId(OsiCurrenciesDTO reportingCurrencyId) {
		this.reportingCurrencyId = reportingCurrencyId;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getFaxCode() {
		return faxCode;
	}

	public void setFaxCode(String faxCode) {
		this.faxCode = faxCode;
	}

	public Integer getOverheadPct() {
		return overheadPct;
	}

	public void setOverheadPct(Integer overheadPct) {
		this.overheadPct = overheadPct;
	}

	public Integer getTotalHrsPerYear() {
		return totalHrsPerYear;
	}

	public void setTotalHrsPerYear(Integer totalHrsPerYear) {
		this.totalHrsPerYear = totalHrsPerYear;
	}

	public String getCostCalc() {
		return costCalc;
	}

	public void setCostCalc(String costCalc) {
		this.costCalc = costCalc;
	}

	public Integer getInterEmpOverheadPct() {
		return interEmpOverheadPct;
	}

	public void setInterEmpOverheadPct(Integer interEmpOverheadPct) {
		this.interEmpOverheadPct = interEmpOverheadPct;
	}

	public String getStartDayOfWeek() {
		return startDayOfWeek;
	}

	public void setStartDayOfWeek(String startDayOfWeek) {
		this.startDayOfWeek = startDayOfWeek;
	}


}
