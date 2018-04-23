package com.osi.ems.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "osi_organizations")
public class OsiOrganizations implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer orgId;
	private String orgName;
	private String orgShortName;
	private String website;
	private String faxNumber;
	private String countryCode;
	private String faxCode;
	private String phoneNumber;
	private Integer parentOrgId;
	private String emailId;
	private Integer contactPersonId;
	private OsiCurrencies baseCurrencyId;
	private OsiCurrencies reportingCurrencyId;
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
	
	public OsiOrganizations() {
		
	}
	
	public OsiOrganizations(String orgName, String orgShortName, String website, String faxNumber,String faxCode, String countryCode,
			String phoneNumber, Integer parentOrgId, String emailId, Integer contactPersonId, OsiCurrencies baseCurrencyId,
			OsiCurrencies reportingCurrencyId, Integer active, Integer overheadPct,Integer totalHrsPerYear, String costCalc,Integer interEmpOverheadPct,Integer createdBy, Date creationDate, Integer lastUpdatedBy,
			Date lastUpdateDate, String startDayOfWeek) {
		super();
		this.orgName = orgName;
		this.orgShortName = orgShortName;
		this.website = website;
		this.faxNumber = faxNumber;
		this.faxCode= faxCode;
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

	@Id
	@Column(name = "org_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "org_name")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "org_short_name")
	public String getOrgShortName() {
		return orgShortName;
	}

	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}

	@Column(name = "website")
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "fax_number")
	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	@Column(name = "country_code")
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "parent_org_id")
	public Integer getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Integer parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	@Column(name = "EMAIL_ADDRRESS")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "contact_person_id")
	public Integer getContactPersonId() {
		return contactPersonId;
	}

	public void setContactPersonId(Integer contactPersonId) {
		this.contactPersonId = contactPersonId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "base_currency_id")
	public OsiCurrencies getBaseCurrencyId() {
		return baseCurrencyId;
	}

	public void setBaseCurrencyId(OsiCurrencies baseCurrencyId) {
		this.baseCurrencyId = baseCurrencyId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reporting_currency_id")
	public OsiCurrencies getReportingCurrencyId() {
		return reportingCurrencyId;
	}

	public void setReportingCurrencyId(OsiCurrencies reportingCurrencyId) {
		this.reportingCurrencyId = reportingCurrencyId;
	}

	@Column(name = "active")
	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", length = 0)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "last_updated_by")
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update_date", length = 0)
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	@Column(name = "fax_code")
	public String getFaxCode() {
		return faxCode;
	}

	public void setFaxCode(String faxCode) {
		this.faxCode = faxCode;
	}
	
	@Column(name = "overhead_pct")
	public Integer getOverheadPct() {
		return overheadPct;
	}
	
	public void setOverheadPct(Integer overheadPct) {
		this.overheadPct = overheadPct;
	}
	
	@Column(name = "total_hrs_per_year")
	public Integer getTotalHrsPerYear() {
		return totalHrsPerYear;
	}

	public void setTotalHrsPerYear(Integer totalHrsPerYear) {
		this.totalHrsPerYear = totalHrsPerYear;
	}
	
	@Column(name = "cost_calc")
	public String getCostCalc() {
		return costCalc;
	}

	public void setCostCalc(String costCalc) {
		this.costCalc = costCalc;
	}

	@Column(name = "inter_org_emp_cost_overhead_pct")
	public Integer getInterEmpOverheadPct() {
		return interEmpOverheadPct;
	}

	public void setInterEmpOverheadPct(Integer interEmpOverheadPct) {
		this.interEmpOverheadPct = interEmpOverheadPct;
	}

	@Column(name = "start_day_of_week")
	public String getStartDayOfWeek() {
		return startDayOfWeek;
	}

	public void setStartDayOfWeek(String startDayOfWeek) {
		this.startDayOfWeek = startDayOfWeek;
	}
}
