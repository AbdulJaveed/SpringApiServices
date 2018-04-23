package com.osi.ems.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "osi_job_codes")
public class OsiJobCodes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer jobCodeId;
	private String jobCodeName;
	private String jobCodeDescription;
	private Integer orgId;
	private Integer active;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date lastUpdateDate;
	
	public OsiJobCodes(){
		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "job_code_id", unique = true, nullable = false)
	public Integer getJobCodeId() {
		return jobCodeId;
	}

	public void setJobCodeId(Integer jobCodeId) {
		this.jobCodeId = jobCodeId;
	}

	@Column(name = "job_code_name")
	public String getJobCodeName() {
		return jobCodeName;
	}

	public void setJobCodeName(String jobCodeName) {
		this.jobCodeName = jobCodeName;
	}

	@Column(name = "job_code_description")
	public String getJobCodeDescription() {
		return jobCodeDescription;
	}

	public void setJobCodeDescription(String jobCodeDescription) {
		this.jobCodeDescription = jobCodeDescription;
	}

	@Column(name = "active")
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	
	@Column(name = "org_id")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	

	@Column(name = "created_by",updatable = false)
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date",updatable = false)
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

}
