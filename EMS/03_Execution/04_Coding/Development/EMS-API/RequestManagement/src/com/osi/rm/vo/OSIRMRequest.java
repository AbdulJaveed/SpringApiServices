/**
 * 
 */
package com.osi.rm.vo;

import java.sql.Timestamp;


/**
 * @author jkorada
 *
 */
public class OSIRMRequest extends OSIRMConfig {
	
	private int requestId;
	private int reportId;
	private byte[] reportArguments;
	private String reportArgumentsType;
	private String outputType;
	private int requestedBy;
	private Timestamp requestDate;
	private int isrepeat;
	private int repeatInterval;
	private int scheduleFrequencyId;
	private Timestamp requestScheEndDate;
	private int parentRequestId;
	private int businessGroupId;
	private OSIRMRequest oSIRMRequest = null;
	
	/*public static OSIRMRequest getOsiRMReqeust(){
		if(oSIRMRequest==null){
			oSIRMRequest = new OSIRMRequest();
		}
		return oSIRMRequest;
	}*/
	
	public  void destroyOsiRMReqeust(){
			oSIRMRequest = null;
	}
	
	public OSIRMRequest(){}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public byte[] getReportArguments() {
		return reportArguments;
	}

	public void setReportArguments(byte[] reportArguments) {
		this.reportArguments = reportArguments;
	}

	public String getReportArgumentsType() {
		return reportArgumentsType;
	}

	public void setReportArgumentsType(String reportArgumentsType) {
		this.reportArgumentsType = reportArgumentsType;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public int getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(int requestedBy) {
		this.requestedBy = requestedBy;
	}

	public Timestamp getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	public int getIsrepeat() {
		return isrepeat;
	}

	public void setIsrepeat(int isrepeat) {
		this.isrepeat = isrepeat;
	}

	public int getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(int repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public int getScheduleFrequencyId() {
		return scheduleFrequencyId;
	}

	public void setScheduleFrequencyId(int scheduleFrequencyId) {
		this.scheduleFrequencyId = scheduleFrequencyId;
	}

	public Timestamp getRequestScheEndDate() {
		return requestScheEndDate;
	}

	public void setRequestScheEndDate(Timestamp requestScheEndDate) {
		this.requestScheEndDate = requestScheEndDate;
	}

	public int getParentRequestId() {
		return parentRequestId;
	}

	public void setParentRequestId(int parentRequestId) {
		this.parentRequestId = parentRequestId;
	}

	public int getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(int businessGroupId) {
		this.businessGroupId = businessGroupId;
	}
	}
