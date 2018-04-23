package com.osi.ems.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="osi_attachments")
public class OsiAttachments implements Serializable {

	/**
	 * The serial version uid is for identifying this class while serializing this
	 * object.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The file attachment id is for identifying the each object uniquely.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer attachmentId;

	/**
	 * The original file name is for taking the original file name.
	 */
	@Column(name="original_file_name")
	private String originalFileName;

	/**
	 * The duplicate file name is for taking the duplicate file name.
	 */
	@Column(name="duplicate_file_name")
	private String duplicateFileName;

	/**
	 * The field file type is for storing the file type information.
	 */
	@Column(name="file_type")
	private String fileType;
	
	@Transient
	@Column(name="file_content")
	private String fileContent;
	@Column(name="attachment_type")
	private String attachmentType;
	@Column(name="object_type")
	private String objectType;
	@Column(name="object_id")
	private Integer objectId;
	/**
	 * The field created by is for tracking the created person information.
	 */
	@Column(name="created_by")
	private Integer createdBy;

	/**
	 * The field is for storing the created date information.
	 */
	@Column(name="creation_date")
	private Date creationDate;

	/**
	 * This is for storing the updated user information.
	 */
	@Column(name="last_updated_by")
	private Integer lastUpdatedBy;

	/**
	 * The field is for storing the updated date.
	 */
	@Column(name="last_update_date")
	private Date lastUpdateDate;	

	@Transient
	private Integer employeeId;
	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the attachmentId
	 */
	public Integer getAttachmentId() {
		return attachmentId;
	}

	/**
	 * @param attachmentId
	 *            the attachmentId to set
	 */
	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	/**
	 * @return the originalFileName
	 */
	public String getOriginalFileName() {
		return originalFileName;
	}

	/**
	 * @param originalFileName
	 *            the originalFileName to set
	 */
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 *            the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the fileContent
	 */
	public String getFileContent() {
		return fileContent;
	}

	/**
	 * @param fileContent
	 *            the fileContent to set
	 */
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getDuplicateFileName() {
		return duplicateFileName;
	}

	public void setDuplicateFileName(String duplicateFileName) {
		this.duplicateFileName = duplicateFileName;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
}
