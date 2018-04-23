package com.osi.ems.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.dao.OsiLocationDao;
import com.osi.ems.dao.OsiOrganizationDao;
import com.osi.ems.domain.OsiCurrencies;
import com.osi.ems.domain.OsiLocations;
import com.osi.ems.domain.OsiOrganizations;
import com.osi.ems.mapper.OsiOrganizationsMapper;
import com.osi.ems.repository.OsiOrganizationsRepository;
import com.osi.ems.service.OsiOrganizationsService;
import com.osi.ems.service.dto.OsiCurrenciesDTO;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Service
@Transactional
public class OsiOrganizationsServiceImpl implements OsiOrganizationsService {

	@Autowired
	private OsiOrganizationsRepository osiOrganizationsRepository;
	
	@Autowired
	private OsiOrganizationDao osiOrganizationsDao;
	
	@Autowired
	private OsiLocationDao locationDao;
	
	@Autowired
	private OsiOrganizationsMapper osiOrganizationsMapper;
	
	private final Logger log = LoggerFactory.getLogger(OsiOrganizationsServiceImpl.class);
	
	@Override
	public OsiOrganizationsDTO save(OsiOrganizationsDTO osiOrganizationsDTO) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OsiOrganizationsDTO> findAll() throws BusinessException {
		List<OsiOrganizationsDTO> osiOrganizationsDTO = new ArrayList<OsiOrganizationsDTO>();
		log.info("findAll : Begin");
		try{
			List<OsiOrganizations> organizationsList = osiOrganizationsRepository.findAll();
			osiOrganizationsDTO = osiOrganizationsMapper.osiOrganizationsListToOsiOrganizationsDTOList(organizationsList);
		}catch (NoResultException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting organization");
		}
		log.info("findAll : End");
		return osiOrganizationsDTO;
		
	}

	@Override
	public OsiOrganizationsDTO findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OsiOrganizationsDTO> getOsiOrganizations() throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		log.info("getOsiOrganizations : Begin");
		List<OsiOrganizationsDTO> osiOrganizationsVOList=new ArrayList<OsiOrganizationsDTO>();
		List<OsiOrganizationsDTO> osiOrganizationsVOWithLocationList=new ArrayList<OsiOrganizationsDTO>();
		List<OsiOrganizations> osiOrganizations = new ArrayList<OsiOrganizations>();
		List<OsiLocations> osiLocationsList = new ArrayList<OsiLocations>();
		try{	
			osiOrganizations=osiOrganizationsDao.getOsiOrganizations();
		    osiOrganizationsVOList=osiOrganizationsMapper.osiOrganizationsListToOsiOrganizationsDTOList(osiOrganizations);
		    for(OsiOrganizationsDTO osiOrganizationsDTO:osiOrganizationsVOList){
		    	String orgLocations="";
		    	osiLocationsList=locationDao.locationbyOrgId(osiOrganizationsDTO.getOrgId());
		    	for(OsiLocations osiLocations:osiLocationsList){
		    		orgLocations+=osiLocations.getLocationName();
		    		orgLocations+=",";
		    	}
		    	orgLocations=orgLocations.replaceAll(",$", "");
		    	osiOrganizationsDTO.setLocations(orgLocations);
		    	osiOrganizationsVOWithLocationList.add(osiOrganizationsDTO);
		    }
		}catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting organization");
		}
		log.info("getOsiOrganizations : End");
		return osiOrganizationsVOWithLocationList;
	}

	@Override
	public List<OsiOrganizationsDTO> findOrg(String orgName,String country,String location)
			throws BusinessException, DataAccessException {
		List<OsiOrganizationsDTO> osiOrganizationsVOWithLocationList = new ArrayList<OsiOrganizationsDTO>();
		// TODO Auto-generated method stub
		log.info("findOrg : Begin");
		try{
			List<OsiOrganizationsDTO> osiOrganizationsVOList=osiOrganizationsDao.orgSeacrh(orgName,country,location);
			osiOrganizationsVOWithLocationList=new ArrayList<OsiOrganizationsDTO>();
		
			for(OsiOrganizationsDTO osiOrganizationsDTO:osiOrganizationsVOList){
		    	
		    	String locations="";
		    	locations=osiOrganizationsDTO.getLocations().replaceAll(",$", "");
		    	osiOrganizationsDTO.setLocations(locations);
		    	osiOrganizationsVOWithLocationList.add(osiOrganizationsDTO);
		    }
		}catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting organization");
		}
		log.info("findOrg : End");
		return osiOrganizationsVOWithLocationList;
	}

	@Override
	public Object getOsiCurrencyInfo() throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		List<OsiCurrenciesDTO> osiCurrenciesVOList = null;
		OsiCurrenciesDTO osiCurrenciesVO = null;		
		log.info("getOsiCurrencyInfo : Begin");
    	try {
			List<OsiCurrencies> osiCurrenciesList  = osiOrganizationsDao.getOsiCurrencyInfo();
			if(osiCurrenciesList==null || (osiCurrenciesList!=null && osiCurrenciesList.size()==0)){
			throw new DataAccessException("ERR_1002", null);
			}
			osiCurrenciesVOList = new ArrayList<OsiCurrenciesDTO>(0);
			for (OsiCurrencies OsiCurrencies : osiCurrenciesList) {
				osiCurrenciesVO = new OsiCurrenciesDTO();			
				osiCurrenciesVO.setCurrencyId(OsiCurrencies.getCurrencyId());				
				osiCurrenciesVO.setCurrencyCode(OsiCurrencies.getCurrencyCode());
				osiCurrenciesVO.setCurrencyName(OsiCurrencies.getCurrencyName());				
				osiCurrenciesVOList.add(osiCurrenciesVO);
			}
    	} catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting osi currency information");
		}
		log.info("getOsiCurrencyInfo : End");
    	return osiCurrenciesVOList;
		}

	@Override
	public OsiOrganizations createOrganization(OsiOrganizationsDTO orgVO, Integer userId)
				throws BusinessException, DataAccessException {
			// TODO Auto-generated method stub
			log.info("createOrganization : Begin");
			OsiOrganizations org=new OsiOrganizations();	
			try{
				if(orgVO.getOrgId()!=null){
					org = osiOrganizationsDao.updateOrganization(osiOrganizationsMapper.osiOrganizationsDTOToOsiOrganizations(orgVO), userId);
				}
				else{
					org = osiOrganizationsDao.createOrganization(osiOrganizationsMapper.osiOrganizationsDTOToOsiOrganizations(orgVO), userId);
				}
			}  catch (DataAccessException e) {
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
			} catch (BusinessException e) {
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
			}catch(Exception e){
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException("ERR_1000", "Error occured while creating organizations");
			}
			log.info("createOrganization : End");
			return org;
		}

	@Override
	public OsiOrganizationsDTO getOrganizationsByOrgId(Integer orgId) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		log.info("getOrganizationsByOrgId : Begin");
		OsiOrganizationsDTO osiOrganizationsDTO=new OsiOrganizationsDTO();
	//	OsiOrganizations osiOrganizations = new OsiOrganizations();
		try {
			osiOrganizationsDTO=osiOrganizationsDao.getOrganizationsByOrgId(orgId);
			//osiOrganizationsDTO=osiOrganizationsMapper.osiOrganizationsToOsiOrganizationsDTO(osiOrganizations);
		} catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting organizations");
		}
		log.info("getOrganizationsByOrgId : End");
		return osiOrganizationsDTO;
	}

	@Override
	public List<OsiEmployeesDTO> contactPersonSearch(String empName,Integer orgId) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		List<OsiEmployeesDTO> osiEmployeesVOList = null;
		OsiEmployeesDTO osiEmployeesVO = null;		
		log.info("contactPersonSearch : Begin");
    	try {
			List<OsiEmployeesDTO> osiEmployeesList  = osiOrganizationsDao.getOsiEmployeeInfo(empName,orgId);
			if(osiEmployeesList==null || (osiEmployeesList!=null && osiEmployeesList.size()==0)){
			throw new DataAccessException("ERR_1002", null);
			}
			osiEmployeesVOList = new ArrayList<OsiEmployeesDTO>(0);
			for (OsiEmployeesDTO OsiEmployees : osiEmployeesList) {
				osiEmployeesVO = new OsiEmployeesDTO();			
				osiEmployeesVO.setEmployeeId(OsiEmployees.getEmployeeId());				
				osiEmployeesVO.setEmployeeNumber(OsiEmployees.getEmployeeNumber());
				osiEmployeesVO.setFullName(OsiEmployees.getFullName());				
				osiEmployeesVOList.add(osiEmployeesVO);
			}
    	} catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting contact person");
		}
		log.info("contactPersonSearch : End");
    	return osiEmployeesVOList;
	}
	}
