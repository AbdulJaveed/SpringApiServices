package com.osi.ems.service.dto;

import java.util.Date;

public class CrudOsiJobCodesVO {

	private Integer jobCodeId;
	private Integer version;
	private String jobCodeName;
	private String jobCodeDescription;
	private Integer active;
	private Integer orgId;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date lastUpdateDate;

	public Integer getJobCodeId(){
		 return jobCodeId;
	}

	public void setJobCodeId(Integer jobCodeId){
		 this.jobCodeId = jobCodeId;
	}

	public Integer getVersion(){
		 return version;
	}

	public void setVersion(Integer version){
		 this.version = version;
	}

	public String getJobCodeName(){
		 return jobCodeName;
	}

	public void setJobCodeName(String jobCodeName){
		 this.jobCodeName = jobCodeName;
	}

	public String getJobCodeDescription(){
		 return jobCodeDescription;
	}

	public void setJobCodeDescription(String jobCodeDescription){
		 this.jobCodeDescription = jobCodeDescription;
	}

	public Integer getActive(){
		 return active;
	}

	public void setActive(Integer active){
		 this.active = active;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}


}