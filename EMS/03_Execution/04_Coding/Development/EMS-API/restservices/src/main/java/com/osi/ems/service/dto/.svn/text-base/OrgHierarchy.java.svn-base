package com.osi.ems.service.dto;

import java.sql.Timestamp;
import java.util.List;

public class OrgHierarchy {
	private Integer id;
	private String employeeNumber;
	private String name;
	private String department;
	private String departmentId;
	private String position;
	private Integer supervisorId;
	private String supervisorName;
	private Boolean isSupervisor;
	private String imageLocation;
	private Timestamp dateHired;
	private String sex;
	private String deptStructure;
	
	private List<OrgHierarchy> subordinate;
	private int level;
	
	public int getLevel() {
		return level;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setLevel(int level) {
		this.level = level;
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
	public Integer isSupervisor(){
		if(getIsSupervisor()){
			return 1;
		}
		return 0;
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
	
	public String getImageLocation() {
		return imageLocation;
	}
	public void setImageLocation(String imageLocation) {
		if( imageLocation == null || imageLocation.isEmpty()){
			if(this.sex !=null && (this.sex.equalsIgnoreCase("M") || this.sex.equalsIgnoreCase("MALE")))
				this.imageLocation="maleAvatar.png";
			else
				this.imageLocation="femaleAvatar.png";	
		}
		else
			this.imageLocation = imageLocation;

	}

	public List<OrgHierarchy> getSubordinate() {
		return subordinate;
	}
	public void setSubordinate(List<OrgHierarchy> subordinate) {
		this.subordinate = subordinate;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", employeeNumber=");
		builder.append(employeeNumber);
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
		builder.append(", imageLocation=");
		builder.append(imageLocation);
		builder.append(", subordinate=");
		builder.append(subordinate);
		builder.append("]");
		return builder.toString();
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
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
