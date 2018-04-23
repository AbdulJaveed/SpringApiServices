package com.osi.ems.service.dto;

import java.util.Date;

public class CrudOsiCostCenterVO {

	private Integer ccId;
	private String ccShortName;
	private String ccLongName;
	private Integer active;
	private Integer createdBy;
	private Date creationDate;
	private Integer lastUpdatedBy;
	private Date lastUpdateDate;

	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public String getCcShortName(){
		 return ccShortName;
	}

	public void setCcShortName(String ccShortName){
		 this.ccShortName = ccShortName;
	}

	public String getCcLongName(){
		 return ccLongName;
	}

	public void setCcLongName(String ccLongName){
		 this.ccLongName = ccLongName;
	}

	public Integer getActive(){
		 return active;
	}

	public void setActive(Integer active){
		 this.active = active;
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


}