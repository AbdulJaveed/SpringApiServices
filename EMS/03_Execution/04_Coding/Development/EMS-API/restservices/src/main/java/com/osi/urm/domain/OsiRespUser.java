package com.osi.urm.domain;

// Generated Dec 1, 2016 5:20:37 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OsiRespUser generated by hbm2java
 */
@Entity
@Table(name = "osi_resp_user")
public class OsiRespUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	/*private OsiUser osiUser;
	*/
	private Integer employeeId;
	private OsiResponsibilities osiResponsibilities;
	private String startDate;
	private String endDate;
	private Boolean defaultResp;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private Integer businessGroupId;

	public OsiRespUser() {
	}

	public OsiRespUser(Integer id, String startDate, String endDate) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public OsiRespUser(Integer id, Integer employeeId,
			OsiResponsibilities osiResponsibilities, String startDate,
			String endDate, Boolean defaultResp, Integer createdBy,
			Date createdDate, Integer updatedBy, Date updatedDate) {
		this.id = id;
		this.employeeId = employeeId;
		this.osiResponsibilities = osiResponsibilities;
		this.startDate = startDate;
		this.endDate = endDate;
		this.defaultResp = defaultResp;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
/*	@JoinColumn(name = "user_id", nullable = false)
	public OsiUser getOsiUser() {
		return this.osiUser;
	}

	public void setOsiUser(OsiUser osiUser) {
		this.osiUser = osiUser;
	}
*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resp_id")
	public OsiResponsibilities getOsiResponsibilities() {
		return this.osiResponsibilities;
	}

	public void setOsiResponsibilities(OsiResponsibilities osiResponsibilities) {
		this.osiResponsibilities = osiResponsibilities;
	}

	@Column(name = "start_date", nullable = false, length = 0)
	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date", nullable = false, length = 0)
	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "default_resp")
	public Boolean getDefaultResp() {
		return this.defaultResp;
	}

	public void setDefaultResp(Boolean defaultResp) {
		this.defaultResp = defaultResp;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 0)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_by")
	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", length = 0)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Column(name = "business_group_id")
	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}
	@Column(name = "user_id")
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

}
