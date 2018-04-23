package com.osi.ems.service.dto;
// Generated Nov 30, 2017 4:50:13 PM by Hibernate Tools 5.2.3.Final

import java.util.Date;

/**
 * OsiTimezones generated by hbm2java
 */
public class OsiTimezonesDTO implements java.io.Serializable {

	private String tzShortName;
	private int tzId;
	private String tzName;
	private int hrsOffsetUtc;
	private int minsOffsetUtc;
	private int createdBy;
	private Date creationDate;
	private int lastUpdatedBy;
	private Date lastUpdateDate;

	public OsiTimezonesDTO() {
	}

	public OsiTimezonesDTO(int tzId, String tzName, int hrsOffsetUtc, int minsOffsetUtc, int createdBy, Date creationDate,
			int lastUpdatedBy, Date lastUpdateDate) {
		this.tzId = tzId;
		this.tzName = tzName;
		this.hrsOffsetUtc = hrsOffsetUtc;
		this.minsOffsetUtc = minsOffsetUtc;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getTzShortName() {
		return this.tzShortName;
	}

	public void setTzShortName(String tzShortName) {
		this.tzShortName = tzShortName;
	}

	public int getTzId() {
		return this.tzId;
	}

	public void setTzId(int tzId) {
		this.tzId = tzId;
	}

	public String getTzName() {
		return this.tzName;
	}

	public void setTzName(String tzName) {
		this.tzName = tzName;
	}

	public int getHrsOffsetUtc() {
		return this.hrsOffsetUtc;
	}

	public void setHrsOffsetUtc(int hrsOffsetUtc) {
		this.hrsOffsetUtc = hrsOffsetUtc;
	}

	public int getMinsOffsetUtc() {
		return this.minsOffsetUtc;
	}

	public void setMinsOffsetUtc(int minsOffsetUtc) {
		this.minsOffsetUtc = minsOffsetUtc;
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