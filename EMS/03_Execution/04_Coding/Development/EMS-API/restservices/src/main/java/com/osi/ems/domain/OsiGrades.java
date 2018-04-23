package com.osi.ems.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
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

@Entity
@Table(name = "osi_grades")
public class OsiGrades implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer gradeId;
	private String gradeName;
	private String gradeDescription;
	private Integer orgId;
	private Integer active;
	private Integer seq;
	private BigDecimal costPerHour;
	private BigDecimal costPerMonth;
	private BigDecimal revPerHour;
	private BigDecimal revPerMonth;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date lastUpdateDate;
	private Set<OsiTitleGrades> osiTitleGradeses = new HashSet<OsiTitleGrades>(0);
	
	public OsiGrades(Integer gradeId, String gradeName, String gradeDescription, Integer orgId, Integer active,
			Integer seq, BigDecimal costPerHour, BigDecimal costPerMonth, BigDecimal revPerHour, BigDecimal revPerMonth,
			Integer createdBy, Date createdDate, Integer updatedBy, Date lastUpdateDate,
			Set<OsiTitleGrades> osiTitleGradeses) {

		this.gradeId = gradeId;
		this.gradeName = gradeName;
		this.gradeDescription = gradeDescription;
		this.orgId = orgId;
		this.active = active;
		this.seq = seq;
		this.costPerHour = costPerHour;
		this.costPerMonth = costPerMonth;
		this.revPerHour = revPerHour;
		this.revPerMonth = revPerMonth;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.setOsiTitleGradeses(osiTitleGradeses);
	}

	public OsiGrades(){
		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "grade_id", unique = true, nullable = false)
	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	@Column(name = "grade_name")
	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	@Column(name = "grade_description")
	public String getGradeDescription() {
		return gradeDescription;
	}

	public void setGradeDescription(String gradeDescription) {
		this.gradeDescription = gradeDescription;
	}

	@Column(name = "org_id")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "active")
	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	@Column(name = "seq")
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Column(name = "created_by",updatable = false)
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date",updatable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "last_updated_by")
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update_date")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
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

	@Column(name = "rev_per_hour")
	public BigDecimal getRevPerHour() {
		return revPerHour;
	}

	public void setRevPerHour(BigDecimal revPerHour) {
		this.revPerHour = revPerHour;
	}

	@Column(name = "rev_per_month")
	public BigDecimal getRevPerMonth() {
		return revPerMonth;
	}

	public void setRevPerMonth(BigDecimal revPerMonth) {
		this.revPerMonth = revPerMonth;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grades")
	public Set<OsiTitleGrades> getOsiTitleGradeses() {
		return osiTitleGradeses;
	}

	public void setOsiTitleGradeses(Set<OsiTitleGrades> osiTitleGradeses) {
		this.osiTitleGradeses = osiTitleGradeses;
	}

	
	
}
