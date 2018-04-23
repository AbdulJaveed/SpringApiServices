package com.osi.ems.service.dto;

import java.sql.Timestamp;
import java.util.List;

public class OrgHierarchyDTO {
	private String employeeNumber;
	private Integer id;
	private String name;
	private String department;
	private String position;	
	private Integer supervisorId;
	private String supervisorName;
	private Boolean isSupervisor;
	private String imageLocation;
	private Timestamp dateHired;
	private String deptStructure;
	private List<OrgHierarchy> children;
	private String sex;
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(Integer supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public Boolean getIsSupervisor() {
		return isSupervisor;
	}
	public void setIsSupervisor(Boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}
	public Timestamp getDateHired() {
		return dateHired;
	}
	public void setDateHired(Timestamp dateHired) {
		this.dateHired = dateHired;
	}
	public List<OrgHierarchy> getChildren() {
		return children;
	}
	public void setChildren(List<OrgHierarchy> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeDTO [id=");
		builder.append(id);
		builder.append(", employeeNumber=");
		builder.append(employeeNumber);
		builder.append(", name=");
		builder.append(name);
		builder.append(", department=");
		builder.append(department);
		builder.append(", position=");
		builder.append(position);
		builder.append(", supervisorId=");
		builder.append(supervisorId);
		builder.append(", supervisorName=");
		builder.append(supervisorName);
		builder.append(", isSupervisor=");
		builder.append(isSupervisor);
		builder.append(", dateHired=");
		builder.append(dateHired);
		builder.append(", children=");
		builder.append(children);
		builder.append("]");
		return builder.toString();
	}
	public String getImageLocation() {
		return imageLocation;
	}
	public void setImageLocation(String imageLocation) {
		if( imageLocation == null || imageLocation.isEmpty()){
			this.imageLocation="maleAvatar.png";
		}
		else
			this.imageLocation = imageLocation;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getDeptStructure() {
		return deptStructure;
	}
	public void setDeptStructure(String deptStructure) {
		this.deptStructure = deptStructure;
	}
}
