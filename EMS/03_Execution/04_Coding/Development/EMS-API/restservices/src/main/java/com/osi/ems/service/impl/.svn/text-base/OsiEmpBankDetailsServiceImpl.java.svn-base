package com.osi.ems.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiEmpBankDetails;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.repository.custom.OsiEmpBankDetailsRepositoryCustom;
import com.osi.ems.repository.custom.OsiEmployeesRepositoryCustom;
import com.osi.ems.service.OsiEmpBankDetailsService;
import com.osi.ems.service.dto.OsiEmpBankDetailsDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * Service Implementation for managing OsiFunction.
 */
@Service
@Transactional
public class OsiEmpBankDetailsServiceImpl implements OsiEmpBankDetailsService {

	private final Logger log = LoggerFactory.getLogger(OsiEmpBankDetailsServiceImpl.class);

	
	@Autowired
	private OsiEmpBankDetailsRepositoryCustom osiEmpBankDetailsRepositoryCustom;
	
	@Autowired
	OsiEmpBankDetailsService osiEmpBankDetailsService;
	
	@Autowired
	OsiEmployeesRepositoryCustom osiEmployeesRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	@Autowired
	private CommonService commonService;
	
	public List<OsiEmpBankDetailsDTO> getallBankDetails() throws DataAccessException, BusinessException {
		List<OsiEmpBankDetailsDTO> osiEmpBankDetailsDTOList = new ArrayList<OsiEmpBankDetailsDTO>();
		log.info("getallBankDetails : Begin");
		try{
			List<OsiEmpBankDetails> OsiEmpBankDetailsList = osiEmpBankDetailsRepositoryCustom.getAllEmpBankInfoDetails();
		
			for(OsiEmpBankDetails osiEmpBankDetails : OsiEmpBankDetailsList){
				OsiEmpBankDetailsDTO dto = new OsiEmpBankDetailsDTO(osiEmpBankDetails);
				osiEmpBankDetailsDTOList.add(dto);
			}
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the bank details");
		}
		log.debug("Request to get all Employee Bank Details: " , osiEmpBankDetailsDTOList);
		log.info("getallBankDetails : End");
		return osiEmpBankDetailsDTOList;
	}
	
	public List<OsiEmpBankDetailsDTO> getAllBankDetailsByEmpIdAndSearchDate(String inputObject) throws DataAccessException, BusinessException {
		List<OsiEmpBankDetailsDTO> osiEmpBankDetailsDTOList = new ArrayList<OsiEmpBankDetailsDTO>();
		log.info("getAllBankDetailsByEmpIdAndSearchDate : Begin");
		try{
			if(null != inputObject) {
				JSONObject inputJson = new JSONObject(inputObject);			
				OsiEmployees employee = osiEmployeesRepository.findByEmployeeIdAndSerachDate(inputJson.getInt("employeeId"), inputJson.getString("searchDate"));
				
				if(null != employee) {
					List<OsiEmpBankDetails> OsiEmpBankDetailsList = osiEmpBankDetailsRepositoryCustom.getAllEmpBankInfoDetailsByEmployeeId(inputJson.getInt("employeeId"));
					for(OsiEmpBankDetails osiEmpBankDetails : OsiEmpBankDetailsList){
						OsiEmpBankDetailsDTO dto = new OsiEmpBankDetailsDTO(osiEmpBankDetails);
						osiEmpBankDetailsDTOList.add(dto);
					}
				}
			}
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the bank details");
		}
		
		log.info("getAllBankDetailsByEmpIdAndSearchDate : End");
		log.debug("Request to get all Employee Bank Details: " , osiEmpBankDetailsDTOList);
		return osiEmpBankDetailsDTOList;
	}



	@Transactional
	public OsiEmpBankDetailsDTO saveEmpBankDetails(OsiEmpBankDetailsDTO osiEmpBankDetailsDTO, Integer id) throws BusinessException {
		OsiEmpBankDetails osiEmpBankDetails = new OsiEmpBankDetails();
		
		log.debug("Request to save Employee Bank Details: " , osiEmpBankDetailsDTO);
		log.info("saveEmpBankDetails : Begin");
		try {

			osiEmpBankDetails.setAccountHolderName(osiEmpBankDetailsDTO.getAccountHolderName());
			osiEmpBankDetails.setAccountNumber(osiEmpBankDetailsDTO.getAccountNumber());
			if(osiEmpBankDetailsDTO.getActive() == null){
				osiEmpBankDetails.setActive(0);
			}else{
				osiEmpBankDetails.setActive(osiEmpBankDetailsDTO.getActive());
			}
			
			osiEmpBankDetails.setBankName(osiEmpBankDetailsDTO.getBankName());
			osiEmpBankDetails.setBranchName(osiEmpBankDetailsDTO.getBranchName());
			osiEmpBankDetails.setCreatedBy(id);
			osiEmpBankDetails.setEmployeeId(osiEmpBankDetailsDTO.getEmployeeId());
			
			
			osiEmpBankDetails.setIfscCode(osiEmpBankDetailsDTO.getIfscCode());
			osiEmpBankDetails.setUpdatedBy(id);
			//osiEmpBankDetails.setLastUpdateDate(new Date());
			osiEmpBankDetails.setLastUpdateDate(commonService.getCurrentDateinUTC());
			
			if(osiEmpBankDetailsDTO.getId() == null){
				//osiEmpBankDetails.setCreatedDate(new Date());
				osiEmpBankDetails.setCreatedDate(commonService.getCurrentDateinUTC());
				osiEmpBankDetails.setId(0);
				osiEmpBankDetailsRepositoryCustom.saveEmpBankDetails(osiEmpBankDetails);
				
				if(osiEmpBankDetails.getActive() == 1) {
					osiEmpBankDetailsRepositoryCustom.updateEmpBankDetailsStatus(osiEmpBankDetails.getEmployeeId(), 
																				 osiEmpBankDetails.getAccountNumber(), 
																				 osiEmpBankDetails.getIfscCode());
				}
			}else{
				osiEmpBankDetails.setId(osiEmpBankDetailsDTO.getId());
				osiEmpBankDetailsRepositoryCustom.updateEmpBankDetails(osiEmpBankDetails);
				
				if(osiEmpBankDetails.getActive() == 1) {
					osiEmpBankDetailsRepositoryCustom.updateEmpBankDetailsStatus(osiEmpBankDetails.getEmployeeId(), 
																				 osiEmpBankDetails.getAccountNumber(), 
																				 osiEmpBankDetails.getIfscCode());
				}
			}
			
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while saving the bank details");
		}
		
		log.info("saveEmpBankDetails : End");
		
		return osiEmpBankDetailsDTO;
	}

	@Transactional(readOnly = true) 
    public OsiEmpBankDetailsDTO getEmpBankDetailById(Integer id) throws DataAccessException, BusinessException{
		log.info("getEmpBankDetailById : Begin");   
		log.debug("Request to get all Employee Bank Details by Id");
		 OsiEmpBankDetailsDTO osiEmpBankDetailsDTO = new OsiEmpBankDetailsDTO();
		try{
	        OsiEmpBankDetails osiEmpBankDetails = osiEmpBankDetailsRepositoryCustom.getEmpBankDetailById(id);
	        
	         osiEmpBankDetailsDTO = new OsiEmpBankDetailsDTO(osiEmpBankDetails);
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the bank details");
		}
		
		log.info("getEmpBankDetailById : End");   
        return osiEmpBankDetailsDTO;
    }
	
}
