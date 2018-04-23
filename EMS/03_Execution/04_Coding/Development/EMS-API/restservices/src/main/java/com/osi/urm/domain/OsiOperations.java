package com.osi.urm.domain;

// Generated Dec 1, 2016 5:20:37 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
 * OsiOperations generated by hbm2java
 */
@Entity
@Table(name = "osi_operations")
public class OsiOperations implements java.io.Serializable {

	private Integer id;
	private String name;
	private String description;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private Set<OsiFuncOperations> osiFuncOperationses = new HashSet<OsiFuncOperations>(
			0);
	private Set<OsiUserOperationExcl> osiUserOperationExcls = new HashSet<OsiUserOperationExcl>(
			0);
	private Integer businessGroupId;
	private Integer active;

	public OsiOperations() {
	}

	public OsiOperations(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public OsiOperations(Integer id, String name, String description,
			Integer createdBy, Date createdDate, Integer updatedBy,
			Date updatedDate, Set<OsiFuncOperations> osiFuncOperationses,
			Set<OsiUserOperationExcl> osiUserOperationExcls) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.osiFuncOperationses = osiFuncOperationses;
		this.osiUserOperationExcls = osiUserOperationExcls;
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

	@Column(name = "name", nullable = false, length = 1000)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false, length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "osiOperations")
	public Set<OsiFuncOperations> getOsiFuncOperationses() {
		return this.osiFuncOperationses;
	}

	public void setOsiFuncOperationses(
			Set<OsiFuncOperations> osiFuncOperationses) {
		this.osiFuncOperationses = osiFuncOperationses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "osiOperations")
	public Set<OsiUserOperationExcl> getOsiUserOperationExcls() {
		return this.osiUserOperationExcls;
	}

	public void setOsiUserOperationExcls(
			Set<OsiUserOperationExcl> osiUserOperationExcls) {
		this.osiUserOperationExcls = osiUserOperationExcls;
	}
	@Column(name = "business_group_id")
	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}
	@Column(name = "active")
	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

}
