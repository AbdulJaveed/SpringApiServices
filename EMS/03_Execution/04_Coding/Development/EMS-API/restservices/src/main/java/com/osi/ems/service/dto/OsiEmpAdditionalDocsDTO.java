package com.osi.ems.service.dto;

public class OsiEmpAdditionalDocsDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer docId;
	private Integer employeeId;
	private Integer attachmentId;
	private String description;
	private OsiAttachmentsDTO attachments;

	public OsiEmpAdditionalDocsDTO() {
	}

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "OsiEmpAdditionalDocsDTO [docId=" + docId + ", employeeId=" + employeeId + ", attachmentId="
				+ attachmentId + ", description=" + description + "]";
	}

	public OsiAttachmentsDTO getAttachments() {
		return attachments;
	}

	public void setAttachments(OsiAttachmentsDTO attachments) {
		this.attachments = attachments;
	}
	
}