package com.osi.ems.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "osi_wf_activities")
public class OsiWorkFlows implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer activityId;
	private Integer wfsId;
	private Integer objectId;
	private String objectName;
	private String error;
	private String startDate;
	private String actualStartDate;
	private String processFlag;
	private Integer orgId;
	
	public OsiWorkFlows(){
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "activity_id", unique = true, nullable = false)
	public Integer getActivityId() {
		return activityId;
	}



	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}


	@Column(name = "wfs_id", length = 20)
	public Integer getWfsId() {
		return wfsId;
	}



	public void setWfsId(Integer wfsId) {
		this.wfsId = wfsId;
	}


	@Column(name = "object_id", length = 20)
	public Integer getObjectId() {
		return objectId;
	}



	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}


	@Column(name = "object_name", length = 20)
	public String getObjectName() {
		return objectName;
	}



	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}


	@Column(name = "error", length = 20)
	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}


	@Column(name = "start_date", length = 20)
	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	@Column(name = "actual_start_date", length = 20)
	public String getActualStartDate() {
		return actualStartDate;
	}



	public void setActualStartDate(String actualStartDate) {
		this.actualStartDate = actualStartDate;
	}


	@Column(name = "process_flag", length = 20)
	public String getProcessFlag() {
		return processFlag;
	}



	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}


	@Column(name = "org_id", length = 20)
	public Integer getOrgId() {
		return orgId;
	}



	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
}
