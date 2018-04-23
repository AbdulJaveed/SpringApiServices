package com.osi.ems.service.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OsiFlexiDataDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	private String columnName;
	
	@JsonProperty
	private String columnValue;
	
	@JsonProperty
	private String columnType;
	
	@JsonProperty
	private int columnSeq;
	
	@JsonProperty
	private String columnSourceType;
	
	@JsonProperty
	private String columnSource;
	
	@JsonProperty
	private Integer isMandatory;
	
	@JsonProperty
	private String javascriptValidation;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public int getColumnSeq() {
		return columnSeq;
	}

	public void setColumnSeq(int columnSeq) {
		this.columnSeq = columnSeq;
	}

	public String getColumnSourceType() {
		return columnSourceType;
	}

	public void setColumnSourceType(String columnSourceType) {
		this.columnSourceType = columnSourceType;
	}

	public String getColumnSource() {
		return columnSource;
	}

	public void setColumnSource(String columnSource) {
		this.columnSource = columnSource;
	}

	public Integer getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(Integer isMandatory) {
		this.isMandatory = isMandatory;
	}

	public String getJavascriptValidation() {
		return javascriptValidation;
	}

	public void setJavascriptValidation(String javascriptValidation) {
		this.javascriptValidation = javascriptValidation;
	}

}
