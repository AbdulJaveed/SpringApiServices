package com.osi.ems.service.dto;
// Generated Oct 10, 2017 12:00:12 PM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * OsiOrgAddresses generated by hbm2java
 */
public class OsiOrgAddressesDTO implements java.io.Serializable {

	private Integer addressId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private Integer stateId;
	private Integer countryId;
	private String zipcode;
	private String objectType;
	private Integer objectId;
	private Integer createdBy;
	private Date creationDate;
	private Integer lastUpdatedBy;
	private Date lastUpdateDate;
	private Set<OsiLocationsDTO> osiLocationses = new HashSet<OsiLocationsDTO>(0);
	
	private String state;
	private String country;

	public OsiOrgAddressesDTO() {
	}

	public OsiOrgAddressesDTO(String addressLine1, String addressLine2, String city, Integer stateId, Integer countryId,
			String zipcode, String objectType, Integer objectId, Integer createdBy, Date creationDate,
			Integer lastUpdatedBy, Date lastUpdateDate, Set<OsiLocationsDTO> osiLocationses) {
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.stateId = stateId;
		this.countryId = countryId;
		this.zipcode = zipcode;
		this.objectType = objectType;
		this.objectId = objectId;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.osiLocationses = osiLocationses;
	}

	public Integer getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getStateId() {
		return this.stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Integer getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
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

	public Set<OsiLocationsDTO> getOsiLocationses() {
		return this.osiLocationses;
	}

	public void setOsiLocationses(Set<OsiLocationsDTO> osiLocationses) {
		this.osiLocationses = osiLocationses;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
