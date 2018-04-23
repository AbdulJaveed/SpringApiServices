package com.osi.urm.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "osi_user")
public class OsiUser implements java.io.Serializable {

	private Integer userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String emailId;
	private String empNumber;
	private String mobileNumber;
	private Date startDate;
	private String fullName;
	private Integer orgId;
	private String orgCode;
	private String photoPath;
	private Date endDate;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private Integer active;
	private Integer hasDefaultPwd;
	private Set<OsiUserFuncExcl> osiUserFuncExcls = new HashSet<OsiUserFuncExcl>(0);
	private Set<OsiAttachments> osiAttachmentses = new HashSet<OsiAttachments>(0);
	private Set<OsiRespUser> osiRespUsers = new HashSet<OsiRespUser>(0);
	private Set<OsiUserOperationExcl> osiUserOperationExcls = new HashSet<OsiUserOperationExcl>(0);
	private Integer businessGroupId;

	public OsiUser() {
	}

	public OsiUser(Integer id, String userName, String password, String emailId,
			String empNumber, String mobileNumber, Date startDate, Date endDate) {
		this.userId = id;
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
		this.empNumber = empNumber;
		this.mobileNumber = mobileNumber;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public OsiUser(Integer id, String userName, String password, String firstName,
			String lastName, String emailId, String empNumber,Integer active,
			String mobileNumber, Date startDate, String fullName, Date endDate,
			Integer createdBy, Date createdDate, Integer updatedBy,
			Date updatedDate, 
			Set<OsiUserFuncExcl> osiUserFuncExcls,
			Set<OsiUserOperationExcl> osiUserOperationExcls,
			Set<OsiAttachments> osiAttachmentses,
			Set<OsiRespUser> osiRespUsers,
			Integer hasDefaultPwd) {
		this.userId = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.active=active;
		this.empNumber = empNumber;
		this.mobileNumber = mobileNumber;
		this.startDate = startDate;
		this.fullName = fullName;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.osiUserFuncExcls = osiUserFuncExcls;
		this.osiAttachmentses = osiAttachmentses;
		this.osiRespUsers = osiRespUsers;
		this.osiUserOperationExcls = osiUserOperationExcls;
		this.hasDefaultPwd=hasDefaultPwd;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer id) {
		this.userId = id;
	}

	@Column(name = "user_name", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "ACTIVE")
	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}
	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "first_name", length = 100)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 100)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email_id", nullable = false, length = 50)
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Column(name = "emp_number", nullable = false, length = 45)
	public String getEmpNumber() {
		return this.empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}
	
	@Column(name = "mobile_number", nullable = false, length = 11)
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false, length = 0)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "full_name", length = 100)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", nullable = false, length = 0)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 0)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_by")
	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", length = 0)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<OsiUserFuncExcl> getOsiUserFuncExcls() {
		return this.osiUserFuncExcls;
	}

	public void setOsiUserFuncExcls(Set<OsiUserFuncExcl> osiUserFuncExcls) {
		this.osiUserFuncExcls = osiUserFuncExcls;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "osiUser", cascade=CascadeType.ALL)
	public Set<OsiAttachments> getOsiAttachmentses() {
		return this.osiAttachmentses;
	}

	public void setOsiAttachmentses(Set<OsiAttachments> osiAttachmentses) {
		this.osiAttachmentses = osiAttachmentses;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<OsiRespUser> getOsiRespUsers() {
		return this.osiRespUsers;
	}

	public void setOsiRespUsers(Set<OsiRespUser> osiRespUsers) {
		this.osiRespUsers = osiRespUsers;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<OsiUserOperationExcl> getOsiUserOperationExcls() {
		return this.osiUserOperationExcls;
	}

	public void setOsiUserOperationExcls(Set<OsiUserOperationExcl> osiUserOperationExcls) {
		this.osiUserOperationExcls = osiUserOperationExcls;
	}
	
	@Column(name = "business_group_id")
	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}

	
	@Column(name = "HAS_DEFAULT_PWD")
	public Integer getHasDefaultPwd() {
		return hasDefaultPwd;
	}

	public void setHasDefaultPwd(Integer hasDefaultPwd) {
		this.hasDefaultPwd = hasDefaultPwd;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
}
