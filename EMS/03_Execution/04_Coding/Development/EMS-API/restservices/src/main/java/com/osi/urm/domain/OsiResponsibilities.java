package com.osi.urm.domain;

// Generated Dec 1, 2016 5:47:35 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OsiResponsibilities generated by hbm2java
 */
@Entity
@Table(name = "osi_responsibilities")
public class OsiResponsibilities implements java.io.Serializable {

	private Integer id;
	private String respName;
	private String description;
	private Date startDate;
	private Date endDate;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private Integer active;
	private Set<OsiRespUser> osiRespUsers = new HashSet<OsiRespUser>(0);
	private Set<OsiMenuResp> osiMenuResp = new HashSet<OsiMenuResp>(0);
	
	private Integer businessGroupId;
//	private Integer reportGrpId;

	public OsiResponsibilities() {
	}

	public OsiResponsibilities(Integer id, String respName) {
		this.id = id;
		this.respName = respName;
	}

	public OsiResponsibilities(Integer id, String respName,
			String description, Date startDate, Date endDate,Integer active,
			Integer createdBy, Date createdDate, Integer updatedBy,
			Date updatedDate, Set<OsiRespUser> osiRespUsers, Set<OsiMenuResp> osiMenuResp) {
		this.id = id;
		this.respName = respName;
		this.description = description;
		this.startDate = startDate;
		this.active=active;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.osiRespUsers = osiRespUsers;
		this.osiMenuResp = osiMenuResp;
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


	@Column(name = "resp_name", nullable = false, length = 1000)
	public String getRespName() {
		return this.respName;
	}

	public void setRespName(String respName) {
		this.respName = respName;
	}
	
	@Column(name = "ACTIVE")
	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}
	@Column(name = "description", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", length = 0)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date", length = 0)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "osiResponsibilities",cascade=CascadeType.ALL)
	public Set<OsiRespUser> getOsiRespUsers() {
		return this.osiRespUsers;
	}

	public void setOsiRespUsers(Set<OsiRespUser> osiRespUsers) {
		this.osiRespUsers = osiRespUsers;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "osiResponsibilities",cascade=CascadeType.ALL)
	public Set<OsiMenuResp> getOsiMenuResp() {
		return osiMenuResp;
	}

	public void setOsiMenuResp(Set<OsiMenuResp> osiMenuResp) {
		this.osiMenuResp = osiMenuResp;
	}
	
	@Column(name = "business_group_id")
	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}
	
//	@Column(name = "RPT_GRP_ID")
//	public Integer getReportGrpId() {
//		return reportGrpId;
//	}
//
//	public void setReportGrpId(Integer reportGrpId) {
//		this.reportGrpId = reportGrpId;
//	}

	
}
