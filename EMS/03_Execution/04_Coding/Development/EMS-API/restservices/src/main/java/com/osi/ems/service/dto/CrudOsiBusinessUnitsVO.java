package com.osi.ems.service.dto;

import java.util.Date;

public class CrudOsiBusinessUnitsVO {

	private Integer buId;
	private String buShortName;
	private String buLongName;
	private Integer active;
	private Integer createdBy;
	private Date creationDate;
	private Integer lastUpdatedBy;
	private Date lastUpdateDate;

	public Integer getBuId() {
		return buId;
	}

	public void setBuId(Integer buId) {
		this.buId = buId;
	}

	public String getBuShortName(){
		 return buShortName;
	}

	public void setBuShortName(String buShortName){
		 this.buShortName = buShortName;
	}

	public String getBuLongName(){
		 return buLongName;
	}

	public void setBuLongName(String buLongName){
		 this.buLongName = buLongName;
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