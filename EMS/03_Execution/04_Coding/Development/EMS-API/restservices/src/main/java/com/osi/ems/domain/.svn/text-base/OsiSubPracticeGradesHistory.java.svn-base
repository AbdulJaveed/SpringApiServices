package com.osi.ems.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "osi_sub_practice_grades_histpry")
public class OsiSubPracticeGradesHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6661106722789122582L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sp_grade_id")
	private Integer subPracticeGradeId;

	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "sp_id")
	private Integer subPracticeId;

	private Integer gradeId;

	@Column(name = "cost_per_hour")
	private BigDecimal costPerHour;

	@Column(name = "cost_per_month")
	private BigDecimal costPerMonth;

	@Column(name = "internal_cost_overhead_pct")
	private Integer internalCostOverheadPercentage;

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	@Column(name = "CREATION_DATE")
	private Date createdDate;

	@Column(name = "LAST_UPDATED_BY")
	private Integer updatedBy;

	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	public Integer getSubPracticeGradeId() {
		return subPracticeGradeId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public Integer getInternalCostOverheadPercentage() {
		return internalCostOverheadPercentage;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setSubPracticeGradeId(Integer subPracticeGradeId) {
		this.subPracticeGradeId = subPracticeGradeId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public void setInternalCostOverheadPercentage(Integer internalCostOverheadPercentage) {
		this.internalCostOverheadPercentage = internalCostOverheadPercentage;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getSubPracticeId() {
		return subPracticeId;
	}

	public void setSubPracticeId(Integer subPracticeId) {
		this.subPracticeId = subPracticeId;
	}

	public BigDecimal getCostPerHour() {
		return costPerHour;
	}

	public BigDecimal getCostPerMonth() {
		return costPerMonth;
	}

	public void setCostPerHour(BigDecimal costPerHour) {
		this.costPerHour = costPerHour;
	}

	public void setCostPerMonth(BigDecimal costPerMonth) {
		this.costPerMonth = costPerMonth;
	}

}
