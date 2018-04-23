package com.osi.ems.service.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OsiFlexiFieldDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	private String tableName;

	@JsonProperty
	private List<OsiFlexiDataDTO> flexiDataList;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<OsiFlexiDataDTO> getFlexiDataList() {
		return flexiDataList;
	}

	public void setFlexiDataList(List<OsiFlexiDataDTO> flexiDataList) {
		this.flexiDataList = flexiDataList;
	}
	
	

}
