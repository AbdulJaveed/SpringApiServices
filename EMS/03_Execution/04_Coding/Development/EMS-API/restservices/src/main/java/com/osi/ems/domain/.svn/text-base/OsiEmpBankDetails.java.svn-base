package com.osi.ems.domain;

// Generated Dec 1, 2016 5:20:37 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "osi_emp_bank_details")
public class OsiEmpBankDetails implements java.io.Serializable {

	private Integer id;
	private String accountHolderName;
	private String branchName;
	private String ifscCode;
	private String accountNumber;
	private String bankName;
	private Integer active;
	private Integer employeeId;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date lastUpdateDate;

	public OsiEmpBankDetails() {
	}

	public OsiEmpBankDetails(Integer id) {
		this.id = id;
	}

	public OsiEmpBankDetails(Integer id, String accountHolderName, String branchName,
			Integer createdBy, Date createdDate, Integer updatedBy, Integer active,
			Date lastUpdateDate, String ifscCode, String accountNumber, String bankName, Integer employeeId) {
		this.id = id;
		this.accountHolderName = accountHolderName;
		this.branchName = branchName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.active = active;
		this.ifscCode = ifscCode;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.employeeId = employeeId;
	}

	@Id
	@Column(name = "bank_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "account_holder_name", length = 1000)
	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	@Column(name = "branch_name", length = 1000)
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Column(name = "ifsc_code", length = 1000)
	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	@Column(name = "account_number", length = 1000)
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "bank_name", length = 1000)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name="active")
	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	@Column(name="employee_id") 
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 0)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="updated_by")
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name="last_update_date") 
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}


}
