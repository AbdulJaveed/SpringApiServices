package com.osi.ems.service.dto;

import java.math.BigDecimal;

public class ListOsiGradesVO {

	private Integer gradeId;
	private String gradeName;
	private String gradeDescription;
	private BigDecimal costPerHour;
	private BigDecimal costPerMonth;
	private BigDecimal revPerHour;
	private BigDecimal revPerMonth;
	private Integer orgId;
	private Integer seq;	
	
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getGradeDescription() {
		return gradeDescription;
	}
	public void setGradeDescription(String gradeDescription) {
		this.gradeDescription = gradeDescription;
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
	public BigDecimal getRevPerHour() {
		return revPerHour;
	}
	public void setRevPerHour(BigDecimal revPerHour) {
		this.revPerHour = revPerHour;
	}
	public BigDecimal getRevPerMonth() {
		return revPerMonth;
	}
	public void setRevPerMonth(BigDecimal revPerMonth) {
		this.revPerMonth = revPerMonth;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}