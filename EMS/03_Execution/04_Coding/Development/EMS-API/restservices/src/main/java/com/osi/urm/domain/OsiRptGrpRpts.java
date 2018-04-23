package com.osi.urm.domain;

// Generated Mar 3, 2017 8:07:44 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * OsiRptGrpRpts generated by hbm2java
 */
@Entity
@Table(name = "osi_rpt_grp_rpts")
public class OsiRptGrpRpts implements java.io.Serializable {

	private Integer rptGrpRptsId;
	private OsiReports osiReports;
	private OsiRptGroups osiRptGroups;
	private Integer businessGroupId;
	public OsiRptGrpRpts() {
	}

	public OsiRptGrpRpts(Integer rptGrpRptsId) {
		this.rptGrpRptsId = rptGrpRptsId;
	}

	public OsiRptGrpRpts(Integer rptGrpRptsId, OsiReports osiReports, OsiRptGroups osiRptGroups) {
		super();
		this.rptGrpRptsId = rptGrpRptsId;
		this.osiReports = osiReports;
		this.osiRptGroups = osiRptGroups;
	}

	@Id
	@SequenceGenerator(name="seq_osi_rpt_grp_rpts", sequenceName="seq_osi_rpt_grp_rpts",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_osi_rpt_grp_rpts")
	@Column(name = "RPT_GRP_RPTS_ID", unique = true, nullable = false)
	public Integer getRptGrpRptsId() {
		return this.rptGrpRptsId;
	}

	public void setRptGrpRptsId(Integer rptGrpRptsId) {
		this.rptGrpRptsId = rptGrpRptsId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REPORT_ID", nullable = false)
	public OsiReports getOsiReports() {
		return osiReports;
	}

	public void setOsiReports(OsiReports osiReports) {
		this.osiReports = osiReports;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RPT_GRP_ID", nullable = false)
	public OsiRptGroups getOsiRptGroups() {
		return osiRptGroups;
	}

	public void setOsiRptGroups(OsiRptGroups osiRptGroups) {
		this.osiRptGroups = osiRptGroups;
	}
	
	@JoinColumn(name = "BUSINESS_GROUP_ID", nullable = false)
	public Integer getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}
}