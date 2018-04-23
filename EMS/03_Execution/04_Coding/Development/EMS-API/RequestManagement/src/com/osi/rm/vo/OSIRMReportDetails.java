package com.osi.rm.vo;


public class OSIRMReportDetails {
	
	private int reportId;
	private String reportName;
	private String jrxmlName;
	private String reportType;
	private String outputType;
	private int lockOnReport;
	
	public OSIRMReportDetails(){}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getJrxmlName() {
		return jrxmlName;
	}

	public void setJrxmlName(String jrxmlName) {
		this.jrxmlName = jrxmlName;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public int getLockOnReport() {
		return lockOnReport;
	}

	public void setLockOnReport(int lockOnReport) {
		this.lockOnReport = lockOnReport;
	}

}
