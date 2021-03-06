package com.osi.ems.domain;
// Generated Dec 8, 2017 2:18:30 PM by Hibernate Tools 5.2.3.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OsiCostCenter generated by hbm2java
 */
@Entity
@Table(name = "osi_cost_center")
public class OsiCostCenter implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer ccId;
	private String ccShortName;
	private String ccLongName;
	private Integer active;
	private Integer createdBy;
	private Date creationDate;
	private Integer lastUpdatedBy;
	private Date lastUpdateDate;

	public OsiCostCenter() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CC_ID", unique = true, nullable = false)
	public Integer getCcId() {
		return this.ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}
	
	@Column(name = "CC_SHORT_NAME", unique = true, nullable = false, length = 45)
	public String getCcShortName() {
		return this.ccShortName;
	}

	public void setCcShortName(String ccShortName) {
		this.ccShortName = ccShortName;
	}
	@Column(name = "CC_LONG_NAME", length = 512)
	public String getCcLongName() {
		return this.ccLongName;
	}

	public void setCcLongName(String ccLongName) {
		this.ccLongName = ccLongName;
	}

	@Column(name = "ACTIVE", nullable = false)
	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	@Column(name = "CREATED_BY", nullable = false,updatable = false)
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATION_DATE", nullable = false, length = 19,updatable = false)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "LAST_UPDATED_BY", nullable = false)
	public Integer getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
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

}
