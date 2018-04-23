package com.osi.ems.domain;
//Generated Dec 8, 2017 2:18:30 PM by Hibernate Tools 5.2.3.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
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
@Table(name = "osi_cc_grades")
public class OsiCcGrades implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer ccGradeId;
	private Integer orgId;
	private Integer ccId;
	private Integer gradeId;
	private BigDecimal costPerHour;
	private BigDecimal costPerMonth;
	private Integer createdBy;
	private Date creationDate;
	private Integer lastUpdatedBy;
	private Date lastUpdateDate;
	private Integer internalCostOverheadPct;

	public OsiCcGrades() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cc_grade_id", unique = true, nullable = false)
	public Integer getCcGradeId() {
		return ccGradeId;
	}

	public void setCcGradeId(Integer ccGradeId) {
		this.ccGradeId = ccGradeId;
	}
	
	@Column(name = "cc_id")
	public Integer getCcId() {
		return this.ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}
	
	@Column(name = "org_id")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "grade_id")
	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	@Column(name = "cost_per_hour")
	public BigDecimal getCostPerHour() {
		return costPerHour;
	}

	public void setCostPerHour(BigDecimal costPerHour) {
		this.costPerHour = costPerHour;
	}

	@Column(name = "cost_per_month")
	public BigDecimal getCostPerMonth() {
		return costPerMonth;
	}

	public void setCostPerMonth(BigDecimal costPerMonth) {
		this.costPerMonth = costPerMonth;
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

	public Integer getInternalCostOverheadPct() {
		return internalCostOverheadPct;
	}

	public void setInternalCostOverheadPct(Integer internalCostOverheadPct) {
		this.internalCostOverheadPct = internalCostOverheadPct;
	}
}
