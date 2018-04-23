package com.osi.urm.service.dto;

// Generated Mar 3, 2017 8:07:44 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * OsiRmRequests generated by hbm2java
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OsiRmRequestsDTO implements java.io.Serializable {

	private Integer requestId;
	private Integer reportId;
	private String requestProcess;
	private String requestStatus;
	private Date requestDate;
	private Date actualExecStartTime;
	private Date actualExecEndTime;
	private Integer requestedBy;
	private byte[] requestArguments;
	private String requestArgumentsType;
	private Date creationDate;
	private String logFileName;
	private String outputFileName;
	private Integer isRepeat;
	private Integer repeatInterval;
	private Integer scheduleFrequencyId;
	private String hostName;
	private Integer processId;
	private Date reqestScheEndDate;
	private String outputType;
	private Integer parentRequestId;
	private Integer businessGroupId;
	private String startDate;
	private String endDate;
	private String scheduleStaus;
	private String duration;
	private String userName;
	private String reportName;
	private String requestSubmitionDate;
	private String requestSchedulEndDate;

	private String isRepeatFlag;
	private String scheduleFrequency;
	
	private Integer rptGroupId;
	
	public String getIsRepeatFlag() {
		return isRepeatFlag;
	}

	public void setIsRepeatFlag(String isRepeatFlag) {
		this.isRepeatFlag = isRepeatFlag;
	}

	public String getScheduleFrequency() {
		return scheduleFrequency;
	}

	public void setScheduleFrequency(String scheduleFrequency) {
		this.scheduleFrequency = scheduleFrequency;
	}

	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}

	public String getScheduleStaus() {
		return scheduleStaus;
	}

	public void setScheduleStaus(String scheduleStaus) {
		this.scheduleStaus = scheduleStaus;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public OsiRmRequestsDTO() {
	}

	public OsiRmRequestsDTO(Integer requestId) {
		this.requestId = requestId;
	}

	public OsiRmRequestsDTO(Integer requestId, Integer reportId,
			String requestProcess, String requestStatus, Date requestDate,
			Date actualExecStartTime, Date actualExecEndTime,
			Integer requestedBy, byte[] requestArguments,
			String requestArgumentsType, Date creationDate, String logFileName,
			String outputFileName, Integer isRepeat, Integer repeatInterval,
			Integer scheduleFrequencyId, String hostName, Integer processId,
			Date reqestScheEndDate, String outputType, Integer parentRequestId) {
		this.requestId = requestId;
		this.reportId = reportId;
		this.requestProcess = requestProcess;
		this.requestStatus = requestStatus;
		this.requestDate = requestDate;
		this.actualExecStartTime = actualExecStartTime;
		this.actualExecEndTime = actualExecEndTime;
		this.requestedBy = requestedBy;
		this.requestArguments = requestArguments;
		this.requestArgumentsType = requestArgumentsType;
		this.creationDate = creationDate;
		this.logFileName = logFileName;
		this.outputFileName = outputFileName;
		this.isRepeat = isRepeat;
		this.repeatInterval = repeatInterval;
		this.scheduleFrequencyId = scheduleFrequencyId;
		this.hostName = hostName;
		this.processId = processId;
		this.reqestScheEndDate = reqestScheEndDate;
		this.outputType = outputType;
		this.parentRequestId = parentRequestId;
	}

	public Integer getRequestId() {
		return this.requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getReportId() {
		return this.reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getRequestProcess() {
		return this.requestProcess;
	}

	public void setRequestProcess(String requestProcess) {
		this.requestProcess = requestProcess;
	}

	public String getRequestStatus() {
		return this.requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getActualExecStartTime() {
		return this.actualExecStartTime;
	}

	public void setActualExecStartTime(Date actualExecStartTime) {
		this.actualExecStartTime = actualExecStartTime;
	}

	public Date getActualExecEndTime() {
		return this.actualExecEndTime;
	}

	public void setActualExecEndTime(Date actualExecEndTime) {
		this.actualExecEndTime = actualExecEndTime;
	}

	public Integer getRequestedBy() {
		return this.requestedBy;
	}

	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}

	public byte[] getRequestArguments() {
		return this.requestArguments;
	}

	public void setRequestArguments(byte[] requestArguments) {
		this.requestArguments = requestArguments;
	}

	public String getRequestArgumentsType() {
		return this.requestArgumentsType;
	}

	public void setRequestArgumentsType(String requestArgumentsType) {
		this.requestArgumentsType = requestArgumentsType;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLogFileName() {
		return this.logFileName;
	}

	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

	public String getOutputFileName() {
		return this.outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public Integer getIsRepeat() {
		return this.isRepeat;
	}

	public void setIsRepeat(Integer isRepeat) {
		this.isRepeat = isRepeat;
	}

	public Integer getRepeatInterval() {
		return this.repeatInterval;
	}

	public void setRepeatInterval(Integer repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public Integer getScheduleFrequencyId() {
		return this.scheduleFrequencyId;
	}

	public void setScheduleFrequencyId(Integer scheduleFrequencyId) {
		this.scheduleFrequencyId = scheduleFrequencyId;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Integer getProcessId() {
		return this.processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public Date getReqestScheEndDate() {
		return this.reqestScheEndDate;
	}

	public void setReqestScheEndDate(Date reqestScheEndDate) {
		this.reqestScheEndDate = reqestScheEndDate;
	}

	public String getOutputType() {
		return this.outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public Integer getParentRequestId() {
		return this.parentRequestId;
	}

	public void setParentRequestId(Integer parentRequestId) {
		this.parentRequestId = parentRequestId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRequestSubmitionDate() {
		return requestSubmitionDate;
	}

	public void setRequestSubmitionDate(String requestSubmitionDate) {
		this.requestSubmitionDate = requestSubmitionDate;
	}

	public String getRequestSchedulEndDate() {
		return requestSchedulEndDate;
	}

	public void setRequestSchedulEndDate(String requestSchedulEndDate) {
		this.requestSchedulEndDate = requestSchedulEndDate;
	}

	public Integer getRptGroupId() {
		return rptGroupId;
	}

	public void setRptGroupId(Integer rptGroupId) {
		this.rptGroupId = rptGroupId;
	}
}