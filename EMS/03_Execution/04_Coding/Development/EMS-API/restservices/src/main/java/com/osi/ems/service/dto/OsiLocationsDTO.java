package com.osi.ems.service.dto;
// Generated Oct 10, 2017 12:00:12 PM by Hibernate Tools 5.2.3.Final

import java.util.Date;

/**
 * OsiLocations generated by hbm2java
 */
public class OsiLocationsDTO implements java.io.Serializable {

	private Integer locationId;
	private OsiOrgAddressesDTO osiAddresses;
	private String locationName;
	private Integer isPrimary;
	private OsiOrganizationsDTO osiOrganizations;
	private Integer active;
	private Integer createdBy;
	private Date creationDate;
	private Integer lastUpdatedBy;
	private Date lastUpdateDate;
	private OsiRegionsDTO osiRegionsId;
	private OsiTimezonesDTO osiTimezonesId;

	public OsiLocationsDTO() {
	}

	public OsiLocationsDTO(OsiOrgAddressesDTO osiAddresses, String locationName, Integer isPrimary, OsiOrganizationsDTO osiOrganizations,
			Integer active, Integer createdBy, Date creationDate, Integer lastUpdatedBy, Date lastUpdateDate, OsiRegionsDTO osiRegionsId, OsiTimezonesDTO osiTimezonesId) {
		this.osiAddresses = osiAddresses;
		this.locationName = locationName;
		this.isPrimary = isPrimary;
		this.setOsiOrganizations(osiOrganizations);
		this.active = active;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.osiRegionsId=osiRegionsId;
		this.osiTimezonesId=osiTimezonesId;
	}

	public Integer getLocationId() {
		return this.locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public OsiOrgAddressesDTO getOsiAddresses() {
		return this.osiAddresses;
	}

	public void setOsiAddresses(OsiOrgAddressesDTO osiAddresses) {
		this.osiAddresses = osiAddresses;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Integer getIsPrimary() {
		return this.isPrimary;
	}

	public void setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
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

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public OsiOrganizationsDTO getOsiOrganizations() {
		return osiOrganizations;
	}

	public void setOsiOrganizations(OsiOrganizationsDTO osiOrganizations) {
		this.osiOrganizations = osiOrganizations;
	}

	public OsiRegionsDTO getOsiRegionsId() {
		return osiRegionsId;
	}

	public void setOsiRegionsId(OsiRegionsDTO osiRegionsId) {
		this.osiRegionsId = osiRegionsId;
	}

	public OsiTimezonesDTO getOsiTimezonesId() {
		return osiTimezonesId;
	}

	public void setOsiTimezonesId(OsiTimezonesDTO osiTimezonesId) {
		this.osiTimezonesId = osiTimezonesId;
	}

}
