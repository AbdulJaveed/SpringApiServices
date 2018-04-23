package com.osi.ems.service.dto;
// Generated Nov 30, 2017 4:50:13 PM by Hibernate Tools 5.2.3.Final

import java.util.Date;

/**
 * OsiRegions generated by hbm2java
 */
public class OsiRegionsDTO implements java.io.Serializable {

	private String regionShortName;
	private int regionId;
	private String regionName;
	private int createdBy;
	private Date creationDate;
	private int lastUpdatedBy;
	private Date lastUpdateDate;

	public OsiRegionsDTO() {
	}

	public OsiRegionsDTO(int regionId, String regionName, int createdBy, Date creationDate, int lastUpdatedBy,
			Date lastUpdateDate) {
		this.regionId = regionId;
		this.regionName = regionName;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getRegionShortName() {
		return this.regionShortName;
	}

	public void setRegionShortName(String regionShortName) {
		this.regionShortName = regionShortName;
	}

	public int getRegionId() {
		return this.regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(int lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}