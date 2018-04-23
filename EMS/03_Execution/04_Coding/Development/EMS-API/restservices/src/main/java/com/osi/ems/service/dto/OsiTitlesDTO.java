package com.osi.ems.service.dto;
// Generated Dec 4, 2017 5:08:19 PM by Hibernate Tools 5.2.3.Final

import java.util.Date;

/**
 * OsiTitles generated by hbm2java
 */
public class OsiTitlesDTO implements java.io.Serializable {

	private String titleShortName;
	private int titleId;
	private String titleLongName;
	private String titleCode;
	private int createdBy;
	private Date creationDate;
	private int lastUpdatedBy;
	private Date lastUpdateDate;
	private Integer orgId;
	
	private int gradeId;

	public OsiTitlesDTO() {
	}


	public String getTitleShortName() {
		return this.titleShortName;
	}

	public void setTitleShortName(String titleShortName) {
		this.titleShortName = titleShortName;
	}

	public int getTitleId() {
		return this.titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	public String getTitleLongName() {
		return this.titleLongName;
	}

	public void setTitleLongName(String titleLongName) {
		this.titleLongName = titleLongName;
	}

	public String getTitleCode() {
		return this.titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(int lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public int getGradeId() {
		return gradeId;
	}


	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

}
