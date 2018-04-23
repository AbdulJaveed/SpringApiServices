package com.osi.urm.service.dto;

public class OsiKeyFlexDTO implements java.io.Serializable {
	private Integer businessGroupId;
	private String name;
	private String value;
	public OsiKeyFlexDTO() {
	}

	public Integer getBusinessGroupId() {
		return businessGroupId;
	}
	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}