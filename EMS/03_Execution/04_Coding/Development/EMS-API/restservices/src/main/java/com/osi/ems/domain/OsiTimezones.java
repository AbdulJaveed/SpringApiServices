package com.osi.ems.domain;
// Generated Nov 30, 2017 4:45:33 PM by Hibernate Tools 5.2.3.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OsiTimezones generated by hbm2java
 */
@Entity
@Table(name = "osi_timezones")
public class OsiTimezones implements java.io.Serializable {

	private String tzShortName;
	private int tzId;
	private String tzName;
	private int hrsOffsetUtc;
	private int minsOffsetUtc;
	private int createdBy;
	private Date creationDate;
	private int lastUpdatedBy;
	private Date lastUpdateDate;
	private Set<OsiLocations> osiLocationsForTimezoneId = new HashSet<OsiLocations>(0);

	public OsiTimezones() {
	}

	public OsiTimezones(int tzId, String tzName, int hrsOffsetUtc, int minsOffsetUtc, int createdBy, Date creationDate,
			int lastUpdatedBy, Date lastUpdateDate, Set<OsiLocations> osiLocationsForTimezoneId) {
		this.tzId = tzId;
		this.tzName = tzName;
		this.hrsOffsetUtc = hrsOffsetUtc;
		this.minsOffsetUtc = minsOffsetUtc;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.osiLocationsForTimezoneId=osiLocationsForTimezoneId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	
	@Column(name = "tz_id", unique = true, nullable = false)
	public int getTzId() {
		return this.tzId;
	}

	public void setTzId(int tzId) {
		this.tzId = tzId;
	}

	@Column(name = "tz_short_name", unique = true, nullable = false, length = 20)
	public String getTzShortName() {
		return this.tzShortName;
	}

	public void setTzShortName(String tzShortName) {
		this.tzShortName = tzShortName;
	}


	@Column(name = "tz_name", nullable = false, length = 45)
	public String getTzName() {
		return this.tzName;
	}

	public void setTzName(String tzName) {
		this.tzName = tzName;
	}

	@Column(name = "hrs_offset_utc", nullable = false)
	public int getHrsOffsetUtc() {
		return this.hrsOffsetUtc;
	}

	public void setHrsOffsetUtc(int hrsOffsetUtc) {
		this.hrsOffsetUtc = hrsOffsetUtc;
	}

	@Column(name = "mins_offset_utc", nullable = false)
	public int getMinsOffsetUtc() {
		return this.minsOffsetUtc;
	}

	public void setMinsOffsetUtc(int minsOffsetUtc) {
		this.minsOffsetUtc = minsOffsetUtc;
	}

	@Column(name = "CREATED_BY", nullable = false)
	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATION_DATE", nullable = false, length = 19)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "LAST_UPDATED_BY", nullable = false)
	public int getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(int lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE_DATE", nullable = false, length = 19)
	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "osiTimezonesId")
	public Set<OsiLocations> getOsiLocationsForTimezoneId() {
		return osiLocationsForTimezoneId;
	}

	public void setOsiLocationsForTimezoneId(Set<OsiLocations> osiLocationsForTimezoneId) {
		this.osiLocationsForTimezoneId = osiLocationsForTimezoneId;
	}

}
