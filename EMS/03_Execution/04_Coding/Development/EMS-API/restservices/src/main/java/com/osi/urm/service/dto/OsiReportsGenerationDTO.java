package com.osi.urm.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OsiReportsGenerationDTO {
	private int reportId;
	private String multSelect;
	private String paramRequired;
	private String validationType;
	private String listDataType;
	private String parameterName;
	private String screenDisplayName;
	private int listId;
	private String scheduleEndDate;
	private String occurrences;
	private int repeatInterval;
	private boolean repeat;
	private String finalParameters;
	private String requestTimeHr;
	private Integer businessGroupId;
	private List<String> hrsList;
	private List<String> minsList;
	private String screenFields;
	private String downloadFile;
	private String downloadFileName;
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	private String requestTimeMins;
	private String dateFormat;
	public String getRequestTimeHr() {
		return requestTimeHr;
	}
	public void setRequestTimeHr(String requestTimeHr) {
		this.requestTimeHr = requestTimeHr;
	}
	public String getRequestTimeMins() {
		return requestTimeMins;
	}
	public void setRequestTimeMins(String requestTimeMins) {
		this.requestTimeMins = requestTimeMins;
	}
	public String getRequestTimeSecs() {
		return requestTimeSecs;
	}
	public void setRequestTimeSecs(String requestTimeSecs) {
		this.requestTimeSecs = requestTimeSecs;
	}
	private String requestTimeSecs;
	public String getFinalParameters() {
		return finalParameters;
	}
	public void setFinalParameters(String finalParameters) {
		this.finalParameters = finalParameters;
	}
	public String getScheduleEndDate() {
		return scheduleEndDate;
	}
	public void setScheduleEndDate(String scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}
	public String getOccurrences() {
		return occurrences;
	}
	public void setOccurrences(String occurrences) {
		this.occurrences = occurrences;
	}
	public int getRepeatInterval() {
		return repeatInterval;
	}
	public void setRepeatInterval(int repeatInterval) {
		this.repeatInterval = repeatInterval;
	}
	public boolean isRepeat() {
		return repeat;
	}
	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	private String requestDate;
	public int getReportId() {
		return reportId;
	}
	public int getListId() {
		return listId;
	}
	public void setListId(int listId) {
		this.listId = listId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public int getDisplaySeq() {
		return displaySeq;
	}
	public void setDisplaySeq(int displaySeq) {
		this.displaySeq = displaySeq;
	}
	private String listDataMaxSize;
	private int displaySeq;
	public String getMultSelect() {
		return multSelect;
	}
	public void setMultSelect(String multSelect) {
		this.multSelect = multSelect;
	}
	public String getParamRequired() {
		return paramRequired;
	}
	public void setParamRequired(String paramRequired) {
		this.paramRequired = paramRequired;
	}
	public String getValidationType() {
		return validationType;
	}
	public void setValidationType(String validationType) {
		this.validationType = validationType;
	}
	public String getListDataType() {
		return listDataType;
	}
	public void setListDataType(String listDataType) {
		this.listDataType = listDataType;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getScreenDisplayName() {
		return screenDisplayName;
	}
	public void setScreenDisplayName(String screenDisplayName) {
		this.screenDisplayName = screenDisplayName;
	}
	public String getListDataMaxSize() {
		return listDataMaxSize;
	}
	public void setListDataMaxSize(String listDataMaxSize) {
		this.listDataMaxSize = listDataMaxSize;
	}
	public Integer getBusinessGroupId() {
		return businessGroupId;
	}
	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}
	public List<String> getHrsList() {
		return hrsList;
	}
	public void setHrsList(List<String> hrsList) {
		this.hrsList = hrsList;
	}
	public List<String> getMinsList() {
		return minsList;
	}
	public void setMinsList(List<String> minsList) {
		this.minsList = minsList;
	}
	public String getScreenFields() {
		return screenFields;
	}
	public void setScreenFields(String screenFields) {
		this.screenFields = screenFields;
	}
	public String getDownloadFile() {
		return downloadFile;
	}
	public void setDownloadFile(String downloadFile) {
		this.downloadFile = downloadFile;
	}
	public String getDownloadFileName() {
		return downloadFileName;
	}
	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	
}