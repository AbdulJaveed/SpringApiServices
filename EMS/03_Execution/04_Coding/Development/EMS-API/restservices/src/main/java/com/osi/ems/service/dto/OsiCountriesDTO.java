package com.osi.ems.service.dto;

import java.io.Serializable;

public class OsiCountriesDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer countryId;
	private String countryName;
	private Integer createdBy;
	private String creationDate;
	private String countryCode;
	private Integer lastUpdatedBy;
	private String lastUpdateDate;
	private String countryCallingCode;

	public OsiCountriesDTO() {
	}

	public OsiCountriesDTO(String countryName, Integer createdBy, String creationDate, 
						   Integer lastUpdatedBy, String lastUpdateDate,String countryCallingCode) {
		super();
		this.countryName = countryName;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.countryCallingCode = countryCallingCode;
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(String string) {
		this.creationDate = string;
	}

	public Integer getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(String string) {
		this.lastUpdateDate = string;
	}

	public String getCountryCallingCode() {
		return countryCallingCode;
	}

	public void setCountryCallingCode(String countryCallingCode) {
		this.countryCallingCode = countryCallingCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	

}
