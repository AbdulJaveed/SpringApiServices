/**
 * 
 */
package com.osi.urm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author suyeturi
 *
 */
@Entity
@Table(name = "OSI_MENU_RESP")
public class OsiMenuResp {
	
	private Integer menuRespId;
	private OsiResponsibilities osiResponsibilities;
	private OsiMenus osiMenus;
	private Integer businessGroupId;

	public OsiMenuResp(Integer menuRespId, OsiResponsibilities osiResponsibilities, OsiMenus osiMenus, Integer businessGroupId) {
		this.menuRespId = menuRespId;
		this.osiResponsibilities =osiResponsibilities ;
		this.osiMenus = osiMenus;
		this.businessGroupId = businessGroupId;
	}
	
	public OsiMenuResp() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "MENU_RESP_ID", unique = true, nullable = false)
	public Integer getMenuRespId() {
		return menuRespId;
	}

	public void setMenuRespId(Integer menuRespId) {
		this.menuRespId = menuRespId;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESP_ID", nullable = false)
	public OsiResponsibilities getOsiResponsibilities() {
		return osiResponsibilities;
	}

	public void setOsiResponsibilities(OsiResponsibilities osiResponsibilities) {
		this.osiResponsibilities = osiResponsibilities;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MENU_ID", nullable = false)
	public OsiMenus getOsiMenus() {
		return osiMenus;
	}

	public void setOsiMenus(OsiMenus osiMenus) {
		this.osiMenus = osiMenus;
	}
	
	@Column(name = "BUSINESS_GROUP_ID")
	public Integer getBusinessGroupId() { 
		return businessGroupId;
	}

	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}

}
