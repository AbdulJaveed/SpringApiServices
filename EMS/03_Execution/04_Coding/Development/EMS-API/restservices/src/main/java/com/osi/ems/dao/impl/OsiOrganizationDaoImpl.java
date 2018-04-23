
package com.osi.ems.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.ems.dao.OsiOrganizationDao;
import com.osi.ems.domain.OsiCurrencies;
import com.osi.ems.domain.OsiOrganizations;
import com.osi.ems.mapper.OsiOrganizationsMapper;
import com.osi.ems.repository.OsiOrganizationsRepository;
import com.osi.ems.repository.custom.OsiOrganizationsRepositoryCustom;
import com.osi.ems.service.OsiRollUpsService;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


@Component
public class OsiOrganizationDaoImpl implements OsiOrganizationDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	OsiOrganizationsRepository organizationsRepo;
	
	@Autowired
	OsiOrganizationsRepositoryCustom organizationsRepository;
	
	@Autowired
	OsiOrganizationsMapper organizationsMapper;
	
	@Autowired
	OsiRollUpsService osiRollUpsService;
	
	@Autowired
	CommonService cs;


	@Override
	public List<OsiOrganizations> getOsiOrganizations() throws BusinessException, DataAccessException {
		LOGGER.info("getOsiOrganizations : Begin");
		List<OsiOrganizations> osiOrganizationsList = new ArrayList<OsiOrganizations>();
		try {
			osiOrganizationsList.addAll(organizationsRepository.getOsiOrganizations());
		} catch(DataAccessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		} 
		LOGGER.info("getOsiOrganizations : End");
		return osiOrganizationsList;
	}



	@Override
	public List<OsiOrganizationsDTO> orgSeacrh(String orgName,String country,String location) throws BusinessException, DataAccessException {
		LOGGER.info("orgSeacrh : Begin");
		List<OsiOrganizationsDTO> orgList = null;			
		try 
		{
			orgList=new ArrayList<OsiOrganizationsDTO>(0);
			orgList=organizationsRepository.orgSeacrh( orgName, country, location);
			
		} catch(DataAccessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("orgSeacrh : End");
		return orgList;		
	}



	@Override
	public List<OsiCurrencies> getOsiCurrencyInfo() throws BusinessException, DataAccessException {
		LOGGER.info("getOsiCurrencyInfo : Begin");
		List<OsiCurrencies> currenciesList = null;
		try {
			currenciesList = organizationsRepository.getOsiCurrencyInfo();					
		} catch(DataAccessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getOsiCurrencyInfo : End");
		return currenciesList;
	}



	@Override
	public OsiOrganizations updateOrganization(OsiOrganizations organizations, Integer userId)
			throws BusinessException, DataAccessException {
		LOGGER.info("updateOrganization : Begin");
		String flag="0";
		Boolean continueUpdate=true;
		try{
			if(organizations.getActive()==0){
				flag=osiRollUpsService.checkActiveStatus(organizations.getOrgShortName());
				if(flag.equals("1")){
					continueUpdate=false;
				}
			}
			if(continueUpdate){
				organizations.setLastUpdatedBy(userId);
				organizations.setLastUpdateDate(cs.getCurrentDateinUTC());	
				organizations = organizationsRepo.save(organizations);
			}else{
				throw new DataAccessException("ERR_1000", "Cannot deactivate this organization.");
			}
			
		} catch(BusinessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch(DataAccessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("updateOrganization : End");
		return organizations;
	}



	@Override
	public OsiOrganizations createOrganization(OsiOrganizations organizations, Integer userId)
			throws BusinessException, DataAccessException {
		LOGGER.info("createOrganization : Begin");
		try{	
			
			organizations.setCreatedBy(userId);
			organizations.setCreationDate(cs.getCurrentDateinUTC());
			organizations.setLastUpdatedBy(userId);
			organizations.setLastUpdateDate(cs.getCurrentDateinUTC());	
			organizations = organizationsRepo.save(organizations);		
		} catch(BusinessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("createOrganization : End");
		return organizations;		
	}



	@Override
	public OsiOrganizationsDTO getOrganizationsByOrgId(Integer orgId) throws BusinessException, DataAccessException {
		LOGGER.info("getOrganizationsByOrgId : Begin");
		OsiOrganizationsDTO osiOrganizations = new OsiOrganizationsDTO();
		try {
			osiOrganizations=organizationsRepository.getOrganizationsByOrgId(orgId);
		} catch(DataAccessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getOrganizationsByOrgId : End");
		return osiOrganizations;
	}



	@Override
	public List<OsiEmployeesDTO> getOsiEmployeeInfo(String empName,Integer orgId) throws BusinessException, DataAccessException {
		LOGGER.info("getOsiEmployeeInfo : Begin");
		List<OsiEmployeesDTO> employeesList = null;
		try {
			employeesList = organizationsRepository.getOsiEmployeeInfo(empName,orgId);					
		} catch(DataAccessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getOsiEmployeeInfo : End");
		return employeesList;
	}
	
}
