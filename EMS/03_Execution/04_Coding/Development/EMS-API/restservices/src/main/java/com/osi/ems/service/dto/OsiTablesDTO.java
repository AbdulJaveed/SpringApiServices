package com.osi.ems.service.dto;

import java.util.List;

public class OsiTablesDTO implements java.io.Serializable{

	private List<String> tblName;
	private String tableName;
	private String columnName;

	public List<String> getTblName() {
		return tblName;
	}

	public void setTblName(List<String> tblName) {
		this.tblName = tblName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
}

