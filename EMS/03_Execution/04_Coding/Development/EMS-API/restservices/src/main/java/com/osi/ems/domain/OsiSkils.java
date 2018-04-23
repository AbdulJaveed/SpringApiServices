package com.osi.ems.domain;
// Generated Dec 8, 2017 2:23:37 PM by Hibernate Tools 5.2.3.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OsiSkils generated by hbm2java
 */
@Entity
@Table(name = "osi_skils")
public class OsiSkils implements java.io.Serializable {

	private Integer skillId;
	private String skillName;
	private String skillDisplayName;
	private String skillDescription;
	private Integer osiSkillGroupId;
	private Set<OsiSkillTags> osiSkillTags;
	private Integer active;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date lastUpdateDate;
	private BigDecimal skillVersion;

	private OsiSkillGroups osiSkillGroup;

	public OsiSkils() {
	}

	public OsiSkils(String skillName, String skillDisplayName, String skillDescription, Integer active,
			Integer createdBy, Date createdDate, Integer updatedBy, Date lastUpdateDate, BigDecimal skillVersion) {
		this.skillName = skillName;
		this.skillDisplayName = skillDisplayName;
		this.skillDescription = skillDescription;
		this.active = active;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.skillVersion = skillVersion;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "skill_id", unique = true, nullable = false)
	public Integer getSkillId() {
		return this.skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	@Column(name = "skill_name", length = 100)
	public String getSkillName() {
		return this.skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	@Column(name = "skill_display_name", length = 100)
	public String getSkillDisplayName() {
		return this.skillDisplayName;
	}

	public void setSkillDisplayName(String skillDisplayName) {
		this.skillDisplayName = skillDisplayName;
	}

	@Column(name = "skill_description", length = 500)
	public String getSkillDescription() {
		return this.skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	@Column(name = "active")
	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	@Column(name = "created_by", updatable = false)
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19, updatable = false)
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
	@Column(name = "last_update_date", length = 19)
	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "skill_version", precision = 18)
	public BigDecimal getSkillVersion() {
		return this.skillVersion;
	}

	public void setSkillVersion(BigDecimal skillVersion) {
		this.skillVersion = skillVersion;
	}

	public Integer getOsiSkillGroupId() {
		return osiSkillGroupId;
	}

	public void setOsiSkillGroupId(Integer osiSkillGroupId) {
		this.osiSkillGroupId = osiSkillGroupId;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "osi_skill_tag_mapping", joinColumns = @JoinColumn(name = "skill_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	public Set<OsiSkillTags> getOsiSkillTags() {
		return osiSkillTags;
	}

	public void setOsiSkillTags(Set<OsiSkillTags> osiSkillTags) {
		this.osiSkillTags = osiSkillTags;
	}
	
	@OneToOne
	@JoinColumn(name = "osiSkillGroupId", insertable = false, updatable = false)
	public OsiSkillGroups getOsiSkillGroup() {
		return osiSkillGroup;
	}

	public void setOsiSkillGroup(OsiSkillGroups osiSkillGroup) {
		this.osiSkillGroup = osiSkillGroup;
	}

}