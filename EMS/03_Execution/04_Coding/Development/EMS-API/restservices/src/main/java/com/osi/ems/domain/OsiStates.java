package com.osi.ems.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The class is for managing osi stes information.
 * 
 * @author jkolla
 *
 */
@Entity
@JsonInclude(value = Include.NON_NULL)
public class OsiStates implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = -693561764661699943L;

	/**
	 * STate id is for identifying uniquely.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer stateId;

	/**
	 * The state name is for managing the osi states information.
	 */
	private String stateName;

	/**
	 * The country is for managing the country informtion.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "countryId")
	private OsiCountries country;

	/**
	 * The field is for tracking the created use id.
	 */
	private Integer createdBy;

	/**
	 * The field is for tracking the last upated user id.
	 */
	private Integer lastUpdatedBy;

	/**
	 * The field is for tracking the creation date.
	 */
	private String creationDate;

	/**
	 * The field is for tracking last updated date.
	 */
	private String lastUpdateDate;

	/**
	 * @return the stateId
	 */
	public Integer getStateId() {
		return stateId;
	}

	/**
	 * @param stateId
	 *            the stateId to set
	 */
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName
	 *            the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the country
	 */
	public OsiCountries getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(OsiCountries country) {
		this.country = country;
	}

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
	 * @return the lastUpdatedBy
	 */
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	/**
	 * @param lastUpdatedBy
	 *            the lastUpdatedBy to set
	 */
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the lastUpdateDate
	 */
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @param lastUpdateDate
	 *            the lastUpdateDate to set
	 */
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}
