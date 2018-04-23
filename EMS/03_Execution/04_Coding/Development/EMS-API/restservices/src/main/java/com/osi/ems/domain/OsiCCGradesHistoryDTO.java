package com.osi.ems.domain;

import java.math.BigDecimal;
import java.util.Date;

public class OsiCCGradesHistoryDTO implements java.io.Serializable {
	
	/**
	 * 
	 */
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
	
	public Integer getCcGradeId() {
		return ccGradeId;
	}
	public void setCcGradeId(Integer ccGradeId) {
		this.ccGradeId = ccGradeId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getCcId() {
		return ccId;
	}
	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public BigDecimal getCostPerHour() {
		return costPerHour;
	}
	public void setCostPerHour(BigDecimal costPerHour) {
		this.costPerHour = costPerHour;
	}
	public BigDecimal getCostPerMonth() {
		return costPerMonth;
	}
	public void setCostPerMonth(BigDecimal costPerMonth) {
		this.costPerMonth = costPerMonth;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
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
	@Override
	public String toString() {
		return "OsiCCGradesHistoryDTO [ccGradeId=" + ccGradeId + ", orgId=" + orgId + ", ccId=" + ccId + ", gradeId="
				+ gradeId + ", costPerHour=" + costPerHour + ", costPerMonth=" + costPerMonth + ", createdBy="
				+ createdBy + ", creationDate=" + creationDate + ", lastUpdatedBy=" + lastUpdatedBy
				+ ", lastUpdateDate=" + lastUpdateDate + ", internalCostOverheadPct=" + internalCostOverheadPct + "]";
	}
}
