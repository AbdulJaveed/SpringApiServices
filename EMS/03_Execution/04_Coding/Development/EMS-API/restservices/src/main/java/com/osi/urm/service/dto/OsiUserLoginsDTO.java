package com.osi.urm.service.dto;

// Generated Dec 1, 2016 5:20:37 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

public class OsiUserLoginsDTO implements java.io.Serializable {

	private Integer id;
	private OsiUserDTO osiUser;
	private Date startTime;
	private Date endTime;
	private Integer pid;
	private String loginType;
	private String token;
	private Integer tokenExpTime;
	private Integer businessGroupId;

	public OsiUserLoginsDTO() {
	}

	public OsiUserLoginsDTO(Integer id, OsiUserDTO osiUser, Date startTime) {
		this.id = id;
		this.osiUser = osiUser;
		this.startTime = startTime;
	}

	public OsiUserLoginsDTO(Integer id, OsiUserDTO osiUser, Date startTime, Date endTime,
			Integer pid, String loginType, String token, Integer tokenExpTime) {
		this.id = id;
		this.osiUser = osiUser;
		this.startTime = startTime;
		this.endTime = endTime;
		this.pid = pid;
		this.loginType = loginType;
		this.token = token;
		this.tokenExpTime = tokenExpTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OsiUserDTO getOsiUser() {
		return this.osiUser;
	}

	public void setOsiUser(OsiUserDTO osiUser) {
		this.osiUser = osiUser;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getLoginType() {
		return this.loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getTokenExpTime() {
		return this.tokenExpTime;
	}

	public void setTokenExpTime(Integer tokenExpTime) {
		this.tokenExpTime = tokenExpTime;
	}

	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}

}
