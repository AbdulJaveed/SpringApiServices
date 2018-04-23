package com.osi.ems.service.dto;

public class ListOsiJobCodesVO {

	private Integer jobCodeId;
	private String jobCodeName;
	private String jobCodeDescription;
	private Integer active;
	private Integer orgId;
	
	public Integer getJobCodeId() {
		return jobCodeId;
	}

	public void setJobCodeId(Integer jobCodeId) {
		this.jobCodeId = jobCodeId;
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

}