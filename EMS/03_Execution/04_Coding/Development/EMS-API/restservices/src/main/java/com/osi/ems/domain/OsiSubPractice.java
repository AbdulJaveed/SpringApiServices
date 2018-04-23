package com.osi.ems.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "osi_sub_practice")
public class OsiSubPractice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3225451186068304403L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "SP_ID")
	private Integer subPracticeId;

	@Column(name = "SP_SHORT_NAME")
	private String subPracticeShortName;
	
	@Column(name = "SP_LONG_NAME")
	private String subPractceLongName;

	@Column(name = "ACTIVE")
	private Integer active;

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	@Column(name = "CREATION_DATE")
	private Date createdDate;

	@Column(name = "LAST_UPDATED_BY")
	private Integer updatedBy;
	
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	public Integer getSubPracticeId() {
		return subPracticeId;
	}

	public String getSubPracticeShortName() {
		return subPracticeShortName;
	}

	public String getSubPractceLongName() {
		return subPractceLongName;
	}

	public Integer getActive() {
		return active;
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

	public void setSubPracticeId(Integer subPracticeId) {
		this.subPracticeId = subPracticeId;
	}

	public void setSubPracticeShortName(String subPracticeShortName) {
		this.subPracticeShortName = subPracticeShortName;
	}

	public void setSubPractceLongName(String subPractceLongName) {
		this.subPractceLongName = subPractceLongName;
	}

	public void setActive(Integer active) {
		this.active = active;
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

}
