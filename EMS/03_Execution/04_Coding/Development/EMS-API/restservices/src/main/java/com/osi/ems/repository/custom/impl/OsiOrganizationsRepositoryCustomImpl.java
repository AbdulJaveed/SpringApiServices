/**
 * 
 */
package com.osi.ems.repository.custom.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.domain.OsiCurrencies;
import com.osi.ems.domain.OsiOrganizations;
import com.osi.ems.repository.custom.OsiOrganizationsRepositoryCustom;
import com.osi.ems.service.dto.OsiCurrenciesDTO;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */

@Repository
@Transactional(readOnly = true)
public class OsiOrganizationsRepositoryCustomImpl implements OsiOrganizationsRepositoryCustom {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	EntityManager entityManager;

	public OsiOrganizationsRepositoryCustomImpl() {
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public List<OsiOrganizations> getOsiOrganizations() throws DataAccessException {
		LOGGER.info("getOsiOrganizations : Begin");
		List<OsiOrganizations> osiOrganizationsList = null;
		OsiOrganizations osiOrganizations = null;
		List<Objects[]> result = new ArrayList<Objects[]>();
		try {
			Query query = entityManager.createQuery("select org from OsiOrganizations org where org.active = ?").setParameter(1, 1);
			
			result = (List<Objects[]>) query.getResultList();
			osiOrganizationsList = new ArrayList<OsiOrganizations>();
			if (result != null || !result.isEmpty()) {
				for (Object objects : result) {
					osiOrganizations = new OsiOrganizations();
					osiOrganizations = (OsiOrganizations) objects;
					osiOrganizationsList.add(osiOrganizations);
				}
			}
		} catch (NoResultException ne) {
			LOGGER.info("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No records found in organizations");
		} catch (Exception e) {
			LOGGER.info("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getOsiOrganizations : End");
		return osiOrganizationsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiOrganizationsDTO> orgSeacrh(String orgName, String country, String location)
			throws DataAccessException {
		LOGGER.info("orgSeacrh : Begin");

		List<OsiOrganizationsDTO> osiSearchOrgList = new ArrayList<OsiOrganizationsDTO>();
		String name="";
		String cntry="";
		String loc="";
		try

		{
			StringBuilder queryString = new StringBuilder("select org.org_id,org.org_name,loc.location_name,cnt.country_name, concat(org.country_code, ' ', org.phone_number) phone_number, org.EMAIL_ADDRRESS,org.website ");
			queryString.append("from osi_organizations org , ");
			queryString.append(" osi_locations loc left join osi_addresses adr on loc.address_id=adr.address_id  ");
			queryString.append("left join osi_countries cnt on cnt.country_id=adr.country_id  ");
			queryString.append("WHERE loc.org_id=org.org_id  ");
			String whereClause = "";

			if (orgName != null && orgName != "") {
				whereClause += " AND UPPER(org.org_name) LIKE UPPER(CONCAT('%',:orgName,'%'))";
				name=orgName;
			}
			
			if(location!=null && location !="" ){
			  whereClause+=" AND UPPER(loc.location_name) LIKE UPPER(CONCAT('%',:location,'%'))"; 
			  cntry=country;
			  } 
			  if( country!=null && country !="" ) 
			  { 
			whereClause +=" AND UPPER(cnt.country_name) LIKE UPPER(CONCAT('%',:country,'%')) "; 
			loc=location;
			} 
			  whereClause +="   ORDER BY org.org_id;"; 
			queryString.append(whereClause);
			Query query =this.entityManager.createNativeQuery(queryString.toString());
			  if( orgName!=null)
			  { query.setParameter("orgName", orgName); } 
			
			 if( country!=null )
			 { query.setParameter("country", country); }
			  if( location!=null )
			  { query.setParameter("location", location); }
		/*	List<Object[]> queryList = (List<Object[]>) this.entityManager.createQuery(queryString.toString())
					.setParameter("orgName", name)	
					 .setParameter("country", cntry) 
					  .setParameter("location", loc)
					 
					.getResultList();*/
			
			List<Object[]> queryList = (List<Object[]>) 	query.getResultList();
			
			int prvOrgId=0;
			OsiOrganizationsDTO osiOrg = null;
			boolean flag=false;
			for (Object[] objects : queryList) {
				String locations="";
				
				if( prvOrgId!=Integer.parseInt(objects[0].toString())){
					
					
					 osiOrg = new OsiOrganizationsDTO();
						if (objects[0] != null) {
							osiOrg.setOrgId(Integer.parseInt(objects[0].toString()));
						}
						if (objects[1] != null) {
							osiOrg.setOrgName(objects[1].toString());
						}
						if (objects[2] != null) {
							
							osiOrg.setLocations(objects[2].toString()+",");
							
						}
						if (objects[3] != null) {
							osiOrg.setCountryName(objects[3].toString());
							
						}
						if (objects[4] != null) {
							osiOrg.setPhoneNumber(objects[4].toString());
							
						}
						if (objects[5] != null) {
							osiOrg.setEmailId(objects[5].toString());
							
						}
						if (objects[6] != null) {
							osiOrg.setWebsite(objects[6].toString());
						}
						osiSearchOrgList.add(osiOrg);
				}else if (osiOrg!=null && objects[2] != null) {					
						locations=osiOrg.getLocations()+objects[2].toString();		
						locations+=",";
						osiOrg.setLocations(locations);				
				}				
				prvOrgId = Integer.parseInt(objects[0].toString());	

			}
			} catch (NoResultException ne) {
				LOGGER.info("Exception : "+ne.getMessage());
				throw new DataAccessException("ERR_1002", "No records found in organizations with the org name, country and location");
			} catch (Exception e) {
				LOGGER.info("Exception : "+e.getMessage());
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("orgSeacrh : End");
		return osiSearchOrgList;
	}

	@Override
	public List<OsiCurrencies> getOsiCurrencyInfo() throws DataAccessException {
		LOGGER.info("getOsiCurrencyInfo : Begin");
		List<OsiCurrencies> osiCurrenciesList = null;
		OsiCurrencies osiCurrencies = null;

		try {
			String queryString = "select currencyId,currencyName,currencyCode from OsiCurrencies) ";
			List<Object[]> queryList = (List<Object[]>) this.entityManager.createQuery(queryString).getResultList();
			osiCurrenciesList = new ArrayList<OsiCurrencies>(0);
			for (Object[] objects : queryList) {
				osiCurrencies = new OsiCurrencies();
				if (objects[0] != null)
					osiCurrencies.setCurrencyId(Integer.parseInt(objects[0].toString()));
				if (objects[1] != null)
					osiCurrencies.setCurrencyName(objects[1].toString());
				if (objects[2] != null)
					osiCurrencies.setCurrencyCode(objects[2].toString());
				osiCurrenciesList.add(osiCurrencies);
			}
		} catch (NoResultException ne) {
			LOGGER.info("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No records found in the currencies");
		} catch (Exception e) {
			LOGGER.info("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getOsiCurrencyInfo : End");
		return osiCurrenciesList;
	}

	@Override
	public OsiOrganizationsDTO getOrganizationsByOrgId(Integer orgId) throws DataAccessException {
		LOGGER.info("getOrganizationsByOrgId : Begin");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		OsiOrganizationsDTO osiOrganizations = null;
		try {
			
			
			StringBuilder queryString = new StringBuilder("select org.org_id,org.org_name,org.org_short_name,org.website,org.fax_number,org.phone_number,org.created_by,");
			queryString.append(	"org.creation_date,org.parent_org_id,org.EMAIL_ADDRRESS,org.contact_person_id,org.active,org.country_code, org.fax_code, ");
			queryString.append(	"bcur.currency_id as bcurrencyId,bcur.currency_name as bcurrencyName,bcur.currency_code as bcurrencyCode,");			
			queryString.append(	" rcur.currency_id,rcur.currency_name,rcur.currency_code, ") ;
			queryString.append( " (CASE WHEN org.contact_person_id IS NOT NULL THEN emp.full_name ELSE null END )AS empName,overhead_pct,total_hrs_per_year,cost_calc,inter_org_emp_cost_overhead_pct, start_day_of_week ");
			queryString.append( " from osi_organizations org  LEFT JOIN osi_employees emp  ON  emp.employee_id=org.contact_person_id");
			queryString.append( " and ? between emp.effective_start_date and emp.effective_end_date,osi_currencies bcur,osi_currencies rcur ");
			queryString.append( " where  bcur.currency_id=org.base_currency_id and rcur.currency_id=org.reporting_currency_id and org.org_id = ?");
			
			Object[] object = (Object[]) this.entityManager.createNativeQuery(queryString.toString())
			.setParameter(1, new java.sql.Timestamp(new Date().getTime()))
			.setParameter(2, orgId).getSingleResult();
			if (object != null) {
				//osiOrganizations = (OsiOrganizations) result;
				osiOrganizations = new OsiOrganizationsDTO();
				
				osiOrganizations.setOrgId(object[0]!= null ? Integer.parseInt(object[0].toString()) : null);
				osiOrganizations.setOrgName(object[1]!= null ? object[1].toString() : null);
				osiOrganizations.setOrgShortName(object[2]!= null ? object[2].toString() : null);
				osiOrganizations.setWebsite(object[3]!= null ? object[3].toString() : null);
				osiOrganizations.setFaxNumber(object[4]!= null ? object[4].toString(): null);
				osiOrganizations.setPhoneNumber(object[5]!= null ? object[5].toString() : null);
				osiOrganizations.setCreatedBy(object[6]!= null ? Integer.parseInt(object[6].toString()) : null);
				osiOrganizations.setCreationDate(object[7]!= null ? formatter.parse(object[7].toString()) : null);
				osiOrganizations.setParentOrgId(object[8]!= null ? Integer.parseInt(object[8].toString() ): null);
				osiOrganizations.setEmailId(object[9]!=null ? object[9].toString() : null);
				osiOrganizations.setContactPersonId(object[10]!= null ?  Integer.parseInt(object[10].toString()) : null);
				osiOrganizations.setActive(object[11]!= null ? Integer.parseInt(object[11].toString()) : null);
				osiOrganizations.setCountryCode(object[12]!= null ?  object[12].toString():null);
				osiOrganizations.setFaxCode(object[13]!= null ?  object[13].toString():null);
				OsiCurrenciesDTO bcurrency= new OsiCurrenciesDTO();
				bcurrency.setCurrencyId(object[14]!= null ?  Integer.parseInt(object[14].toString()) : null);
				bcurrency.setCurrencyName(object[15]!=null ? object[15].toString() : null);
				bcurrency.setCurrencyCode(object[16]!=null ? object[16].toString() : null);
				osiOrganizations.setBaseCurrencyId(bcurrency);
				OsiCurrenciesDTO rcurrency= new OsiCurrenciesDTO();
				rcurrency.setCurrencyId(object[17]!= null ?  Integer.parseInt(object[17].toString()) : null);
				rcurrency.setCurrencyName(object[18]!=null ? object[18].toString() : null);
				rcurrency.setCurrencyCode(object[19]!=null ? object[19].toString() : null);
				osiOrganizations.setReportingCurrencyId(rcurrency);		
				osiOrganizations.setContactPersonName(object[20]!= null ?  object[20].toString():null);
				osiOrganizations.setOverheadPct(object[21]!= null ?  Integer.parseInt(object[21].toString()) : null);
				osiOrganizations.setTotalHrsPerYear(object[22]!= null ? Integer.parseInt(object[22].toString()) : null);
				osiOrganizations.setCostCalc(object[23]!= null ?  object[23].toString():null);
				osiOrganizations.setInterEmpOverheadPct(object[24]!= null ? Integer.parseInt(object[24].toString()) : null);
				osiOrganizations.setStartDayOfWeek(object[25]!= null ?  object[25].toString():null);
				
			}
		} catch (NoResultException ne) {
			LOGGER.info("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No records found in organizations with the organizationId");
		} catch (Exception e) {
			LOGGER.info("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getOrganizationsByOrgId : End");
		return osiOrganizations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiEmployeesDTO> getOsiEmployeeInfo(String empName,Integer orgId) throws DataAccessException {
		LOGGER.info("getOsiEmployeeInfo : Begin");
		List<OsiEmployeesDTO> osiEmployeesList = null;
		OsiEmployeesDTO osiEmployees = null;

		try {
			
			StringBuilder queryString = new StringBuilder("select distinct(emp.employee_id),employee_number,emp.full_name from osi_employees emp ");
		
			queryString.append(	" where  UPPER(emp.full_name) like UPPER('%"+empName+"%') ");
			queryString.append(	"  and emp.org_id = ?  ");
			
		
			queryString.append( " and ? between emp.effective_start_date ");
			queryString.append(" and emp.effective_end_date");
	
			
			List<Object[]> queryList = (List<Object[]>) this.entityManager.createNativeQuery(queryString.toString())
					.setParameter(1, orgId)
					.setParameter(2, new java.sql.Timestamp(new Date().getTime()))
					.getResultList();
			osiEmployeesList = new ArrayList<OsiEmployeesDTO>(0);
			for (Object[] objects : queryList) {
				osiEmployees = new OsiEmployeesDTO();
				if (objects[0] != null)
					osiEmployees.setEmployeeId(Integer.parseInt(objects[0].toString()));
				if (objects[1] != null)
					osiEmployees.setEmployeeNumber(objects[1].toString());
				if (objects[2] != null)
					osiEmployees.setFullName(objects[2].toString());
				osiEmployeesList.add(osiEmployees);
			}
		} catch (NoResultException ne) {
			LOGGER.info("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No records found from employees with the empName and organizationId");
		} catch (Exception e) {
			LOGGER.info("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getOsiEmployeeInfo : End");
		return osiEmployeesList;
	}

}
