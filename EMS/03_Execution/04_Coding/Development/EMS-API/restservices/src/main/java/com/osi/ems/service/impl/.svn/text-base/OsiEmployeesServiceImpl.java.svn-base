package com.osi.ems.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.ems.common.Util;
import com.osi.ems.domain.OsiAssignments;
import com.osi.ems.domain.OsiAttachments;
import com.osi.ems.domain.OsiContacts;
import com.osi.ems.domain.OsiCountries;
import com.osi.ems.domain.OsiCountryVisas;
import com.osi.ems.domain.OsiEmpAdditionalDocs;
import com.osi.ems.domain.OsiEmpVisaDetails;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.domain.OsiWfsActivities;
import com.osi.ems.mapper.OsiAttachmentsesMapper;
import com.osi.ems.mapper.OsiEmpAdditionalDocsMapper;
import com.osi.ems.mapper.OsiEmployeesMapper;
import com.osi.ems.repository.OsiAttachmentsesRepository;
import com.osi.ems.repository.OsiContactsRepository;
import com.osi.ems.repository.OsiEmpAdditionalDocsRepository;
import com.osi.ems.repository.OsiEmpVisaDetailsRepository;
import com.osi.ems.repository.OsiWfsActivitiesRepository;
import com.osi.ems.repository.custom.OsiAssignmentsRepositoryCustom;
import com.osi.ems.repository.custom.OsiEmpVisaDetailsRepositoryCustom;
import com.osi.ems.repository.custom.OsiEmployeesRepositoryCustom;
import com.osi.ems.repository.custom.OsiWorkflowsRepositoryCustom;
import com.osi.ems.service.OsiAddressService;
import com.osi.ems.service.OsiContactsService;
import com.osi.ems.service.OsiEmployeesService;
import com.osi.ems.service.dto.OsiAttachmentsDTO;
import com.osi.ems.service.dto.OsiContactsDto;
import com.osi.ems.service.dto.OsiCountriesDTO;
import com.osi.ems.service.dto.OsiCountryVisasDTO;
import com.osi.ems.service.dto.OsiEmpAdditionalDocsDTO;
import com.osi.ems.service.dto.OsiEmpVisaDetailsDTO;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.ems.service.dto.OsiOrgAddressesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Service
@Transactional
public class OsiEmployeesServiceImpl implements OsiEmployeesService {
	
	private final Logger log = LoggerFactory.getLogger(OsiEmployeesServiceImpl.class);
	
	@Autowired
	OsiEmployeesRepositoryCustom osiEmployeesRepository;
	
	@Autowired
	private OsiEmployeesMapper osiEmployeesMapper;
	
	@Autowired
	private OsiAttachmentsesRepository osiAttachmentsesRepository;
	
	@Autowired
	private OsiAttachmentsesMapper osiAttachmentsMapper;
	
	@Autowired
	private OsiContactsRepository osiEmployeeContactsRepository;
	
	@Autowired
	private OsiEmpVisaDetailsRepositoryCustom osiEmpVisaDetailsRepositoryCustom;
	
	@Autowired
	private OsiEmpVisaDetailsRepository osiEmpVisaDetailsRepository;
	
	@Autowired
	private OsiAddressService addressesService;
	
	@Autowired
	private OsiContactsService contactsService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private OsiEmpAdditionalDocsRepository osiEmpAdditionalDocsRepository;
	
	@Autowired
	private OsiEmpAdditionalDocsMapper osiEmpAdditionalDocsMapper;
	
	@Value( "${DEFAULT_END_DATE}" )
	private String defaultEndDate;
	
	@Autowired
	OsiWfsActivitiesRepository osiWfsActivitiesRepository;
	
	@Autowired
	OsiWorkflowsRepositoryCustom osiWorkflowsRepository;
	
	@Value( "${WORKFLOW.EMPLOYEE_TERMINATION}" )
	private String employeeTermination;
	
	@Value( "${WORKFLOW.EMPLOYEE_TERMINATION_REMAINDER}" )
	private String employeeTerminationRemainder;
	

	@Autowired
	OsiAssignmentsRepositoryCustom osiAssignmentsRepositoryCustom;
	
	@Override
	public List<OsiEmployees> findAll() throws BusinessException {
		return null;
	}

	@Override
	public OsiEmployees findByEmployeeId(Integer employeeId) throws BusinessException {
		OsiEmployees osiEmployees = null;
		log.info("findByEmployeeId : Begin");
		List<OsiAttachments> osiAttachmentsList = new ArrayList<OsiAttachments>();
		try {
			osiEmployees = osiEmployeesRepository.getEmployees(employeeId);
			if(osiEmployees.getPhotoId() != null) {
				OsiAttachments osiAttachments = osiAttachmentsesRepository.findOne(osiEmployees.getPhotoId());
				osiAttachmentsList.add(osiAttachments);
			}
			List<OsiAttachmentsDTO> osiAttachmentsDtoList = osiAttachmentsMapper.osiAttachmentsToAttachmentsDTOList(osiAttachmentsList);
			osiEmployees.setOsiEmpAttachments(osiAttachmentsDtoList);
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the employee details");
		}
		log.info("findByEmployeeId : End");
		return osiEmployees;
	}

	@Override
	public boolean findByOfficeEmail(String mailId,Integer employeeId) throws BusinessException {
		boolean result = false;
		log.info("findByOfficeEmail : Begin");
		try {
			result = osiEmployeesRepository.findByOfficeEmail(mailId,employeeId);
			
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the employee details email "+mailId);
		}
		log.info("findByOfficeEmail : End");
		return result;
	}
	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public OsiEmployees saveEmployee(OsiEmployees osiEmployees, Integer loggedInEmployeeId) throws BusinessException {
			Integer result = null;
			Integer maxEmployeeId = null;
			log.info("saveEmployee : Begin");
			boolean isExistEmployee = false;
			List<OsiAssignments> existingAssignmentList =  null;
			OsiAssignments existingAssignments = null;
			try {
				SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				if( osiEmployees.getEmployeeId() == null) {
					 if(osiEmployees.getEmployeeNumber() != null && osiEmployees.getOrgId() != null)
						 isExistEmployee = osiEmployeesRepository.isExistEmployee(osiEmployees.getEmployeeNumber(), osiEmployees.getOrgId(), 0);
					 if(isExistEmployee)
						 throw new BusinessException("ERR_1006", "Employee Number is already exist with this organization");
					 else {
						 maxEmployeeId = osiEmployeesRepository.getMaxEmployeeId();
					 }
				} else {
					isExistEmployee = osiEmployeesRepository.isExistEmployee(osiEmployees.getEmployeeNumber(), osiEmployees.getOrgId(), osiEmployees.getEmployeeId());
					if(isExistEmployee)
						 throw new BusinessException("ERR_1006", "Employee Number is already exist with this organization");
				}
				List<OsiAttachmentsDTO> attachementsDto = osiEmployees.getOsiEmpAttachments();
				OsiAttachments osiAttachment = null;
				if(!attachementsDto.isEmpty()) {
					for(OsiAttachmentsDTO attachment : attachementsDto) {
						if(attachment.getFileContent() != null && !attachment.getFileContent().startsWith("http")) {
							if( osiEmployees.getEmployeeId() != null) {
								attachment.setEmployeeId(osiEmployees.getEmployeeId());
							} else {
								attachment.setEmployeeId(maxEmployeeId+1);
							}
							attachment.setAttachmentType("EMPLOYEE"); // TODO: Floder name
							attachment.setObjectType("osi_employees");// TODO: Table name
							attachment.setObjectId(osiEmployees.getEmployeeId());
							osiAttachment = osiEmployeesMapper.mapToAttachments(attachment);
						}
					}
				}
				if(osiAttachment != null) 
					osiEmployees.setPhotoId(osiAttachment.getAttachmentId());
				if( osiEmployees.getEmployeeId() != null) {
					OsiEmployees existingEmployee = this.findByEmployeeId(osiEmployees.getEmployeeId());
					String existTerminationDateString = existingEmployee.getTerminationDate();
					existingAssignmentList =  osiAssignmentsRepositoryCustom.findAssignmentByEmployeeId(osiEmployees.getEmployeeId());
					if(existingAssignmentList!=null && !existingAssignmentList.isEmpty() && existingAssignmentList.get(0)!=null)
						existingAssignments = existingAssignmentList.get(0);
					String existingEmpType = existingEmployee.getEmployeeType();
					Date existTerminationDate = null;
					if(null != existTerminationDateString)
						existTerminationDate = sDate.parse(existTerminationDateString);
					OsiEmployees finalEmployee = null;
					if(osiEmployees.getVersion().intValue() == existingEmployee.getVersion().intValue()) {
						if(!sDate.parse(existingEmployee.getTerminationDate()).equals(sDate.parse(defaultEndDate)) && sDate.parse(osiEmployees.getTerminationDate()).equals(sDate.parse(defaultEndDate))){
							if(new Date().before(existTerminationDate)
								|| new Date().equals(existTerminationDate)){
								Date effStartDateExisting= sDate.parse(existingEmployee.getEffectiveStartDate());
								effStartDateExisting.setSeconds(effStartDateExisting.getSeconds()+1);
								int updateResult = this.updateEmployeeEffectiveEndDate(existingEmployee, sDate.format(effStartDateExisting));
								effStartDateExisting.setSeconds(effStartDateExisting.getSeconds()+1);
								finalEmployee = osiEmployeesMapper.mapToBasicInfo(osiEmployees, existingEmployee);
								finalEmployee.setEffectiveStartDate(sDate.format(effStartDateExisting));
								existingAssignments.setEffectiveStartDate(effStartDateExisting);
								// Delete wfs activities
								List<Integer> wfsIds = new ArrayList<Integer>();
								wfsIds.add(osiWorkflowsRepository.getWorkFlow(employeeTerminationRemainder, osiEmployees.getOrgId()));
								wfsIds.add(osiWorkflowsRepository.getWorkFlow(employeeTermination, osiEmployees.getOrgId()));
								osiWorkflowsRepository.deleteExistingTerminationRecords(wfsIds, osiEmployees.getEmployeeId(), osiEmployees.getOrgId());
							}else{
								finalEmployee = osiEmployeesMapper.mapToBasicInfo(osiEmployees, existingEmployee);
								finalEmployee.setEffectiveStartDate(commonService.getCurrentDateStringinUTC());
								existingAssignments.setEffectiveStartDate(sDate.parse(finalEmployee.getEffectiveStartDate()));
								finalEmployee.setStartDate(commonService.getCurrentDateStringinUTC());
							}
							finalEmployee.setEffectiveEndDate(defaultEndDate);
							finalEmployee.setTerminationDate(defaultEndDate);
							existingAssignments.setEffectiveEndDate(defaultEndDate);
							result = osiEmployeesRepository.saveEmployeeInfo(finalEmployee);
							osiAssignmentsRepositoryCustom.updateAssignments(existingAssignments, osiEmployees.getTerminationDate());
							osiEmployees = finalEmployee;
						}else{
							finalEmployee = osiEmployeesMapper.mapToBasicInfo(osiEmployees, existingEmployee);
							String terminateString = finalEmployee.getTerminationDate();
							String fianlTerminationSting = terminateString.split(" ")[0].toString().concat(" ").concat(existTerminationDateString.split(" ")[1].toString());
							finalEmployee.setTerminationDate(fianlTerminationSting);
							finalEmployee.setVersion(existingEmployee.getVersion().intValue()+1);
							String effecEndDate = osiEmployeesRepository.currentTimeStamp(0);
							String effecStartDate = osiEmployeesRepository.currentTimeStamp(1);
							String UtcEffecEndDate = commonService.convertDateStringToUTC(effecEndDate, loggedInEmployeeId);
							String UtcEffecStartDate = commonService.convertDateStringToUTC(effecStartDate, loggedInEmployeeId);
							
							//finalEmployee.setEffectiveStartDate(effecStartDate);
							finalEmployee.setEffectiveStartDate(UtcEffecStartDate);
							if(existingEmployee.getEmployeeType().equalsIgnoreCase("EX EMPLOYEE")) {
								DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
								LocalDateTime now = LocalDateTime.now();
								String time  = dtf.format(now);
								String terminateDateString = existingEmployee.getTerminationDate().split(" ")[0];
								finalEmployee.setEffectiveEndDate(terminateDateString+" "+time);
								finalEmployee.setEffectiveEndDate(commonService.convertDateStringToUTC(finalEmployee.getEffectiveEndDate(), loggedInEmployeeId));
							}
							else
								finalEmployee.setEffectiveEndDate(existingEmployee.getTerminationDate());
							finalEmployee.setLastUpdatedBy(loggedInEmployeeId);
							//finalEmployee.setLastUpdateDate(osiEmployeesRepository.currentTimeStamp(0));
							finalEmployee.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
							
							if(existingEmpType != null && existTerminationDate != null 
									&& existingEmpType.equalsIgnoreCase("EX EMPLOYEE") &&
										existTerminationDate.before(new Date())) {
								result = osiEmployeesRepository.saveEmployeeInfo(finalEmployee);
									
							}else {
								int updateResult = this.updateEmployeeEffectiveEndDate(existingEmployee, UtcEffecEndDate);
								if(updateResult > 0){
									result = osiEmployeesRepository.saveEmployeeInfo(finalEmployee);
								}
								osiEmployees = finalEmployee;
							}
						}
					}else {
						throw new BusinessException("ERR_1005", "Version mismatch");
					}
				} else {
					int existCount = osiEmployeesRepository.isExistEmployee(osiEmployees.getOfficeEmail(), osiEmployees.getEmployeeNumber());
					if(existCount == 0) {
						osiEmployees.setVersion(0);
						osiEmployees.setEmployeeId(maxEmployeeId+1);
						osiEmployees.setEffectiveStartDate(commonService.getCurrentDateStringinUTC());
						osiEmployees.setEffectiveEndDate(osiEmployees.getTerminationDate());
						osiEmployees.setStartDate(osiEmployees.getEffectiveStartDate());
						osiEmployees.setOriginalDateOfHire(osiEmployeesRepository.convertToTimestamp(osiEmployees.getOriginalDateOfHire()));
						osiEmployees.setCreatedBy(loggedInEmployeeId);
						//osiEmployees.setCreationDate(osiEmployeesRepository.currentTimeStamp(0));
						osiEmployees.setCreationDate(commonService.getCurrentDateStringinUTC());
						osiEmployees.setLastUpdatedBy(loggedInEmployeeId);
						osiEmployees.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
						result = osiEmployeesRepository.saveEmployeeInfo(osiEmployees);
					}else {
						throw new BusinessException("ERR_1004", "Employee already exist with given Employee Number");
					}
				}
				if(!sDate.parse(osiEmployees.getTerminationDate()).equals(sDate.parse(defaultEndDate))){
					if(existingAssignments!=null)
						osiAssignmentsRepositoryCustom.updateAssignments(existingAssignments, osiEmployees.getTerminationDate());

					List<OsiWfsActivities> wfsActivitesList = new ArrayList<OsiWfsActivities>();
					OsiWfsActivities wfsActivities = new OsiWfsActivities();
					wfsActivities.setObjectId(osiEmployees.getEmployeeId());
					wfsActivities.setObjectName("OSI_EMPLOYEES");
					wfsActivities.setProcessFlag("N");
					Integer wfsId = osiWorkflowsRepository.getWorkFlow(employeeTermination, osiEmployees.getOrgId());
					if(wfsActivities!=null && osiWorkflowsRepository.verifyExistingWorkflow(wfsId, osiEmployees.getEmployeeId(), osiEmployees.getOrgId())==0){
						wfsActivities.setWfsId(wfsId);
						wfsActivities.setStartDate(commonService.convertDateStringToUTC(osiEmployees.getTerminationDate(), loggedInEmployeeId));
						wfsActivities.setOrgId(osiEmployees.getOrgId());
						wfsActivitesList.add(wfsActivities);
						
						Date terminationDate= sDate.parse(osiEmployees.getTerminationDate());
						wfsActivities = new OsiWfsActivities();
						wfsActivities.setObjectId(osiEmployees.getEmployeeId());
						wfsActivities.setObjectName("OSI_EMPLOYEES");
						wfsActivities.setProcessFlag("N");
						terminationDate.setDate(terminationDate.getDate()-1);
						wfsActivities.setStartDate(commonService.convertDateStringToUTC(sDate.format(terminationDate), loggedInEmployeeId));
						wfsId = osiWorkflowsRepository.getWorkFlow(employeeTerminationRemainder, osiEmployees.getOrgId());
						wfsActivities.setWfsId(wfsId);
						wfsActivities.setOrgId(osiEmployees.getOrgId());
						wfsActivitesList.add(wfsActivities);
						wfsActivities = new OsiWfsActivities();
						wfsActivities.setObjectId(osiEmployees.getEmployeeId());
						wfsActivities.setObjectName("OSI_EMPLOYEES");
						wfsActivities.setProcessFlag("N");
						terminationDate.setDate(terminationDate.getDate()-1);
						wfsActivities.setStartDate(commonService.convertDateStringToUTC(sDate.format(terminationDate), loggedInEmployeeId));
						wfsId = osiWorkflowsRepository.getWorkFlow(employeeTerminationRemainder, osiEmployees.getOrgId());
						wfsActivities.setWfsId(wfsId);
						wfsActivities.setOrgId(osiEmployees.getOrgId());
						wfsActivitesList.add(wfsActivities);
					
						osiWfsActivitiesRepository.save(wfsActivitesList);
					}
				}
			}catch(BusinessException e){
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
			} catch(DataAccessException e){
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
			}catch(Exception e){
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException("ERR_1000", "Error occured while saving the employee ");
			}
			log.info("saveEmployee : End");
			return osiEmployees;
	}
	
	
	@SuppressWarnings("unused")
	@Override
	public OsiEmployees saveEmployeePersonalInfo(OsiEmployees osiEmployees, Integer loggedInEmployeeId) throws BusinessException {
			Integer result = null;
			log.info("saveEmployeePersonalInfo : Begin");
			try {
				int maxEmployeeId = osiEmployeesRepository.getMaxEmployeeId();					
				
				if( osiEmployees.getEmployeeId() != null) {
					
					OsiEmployees existingEmployee = this.findByEmployeeId(osiEmployees.getEmployeeId());
					
					OsiEmployees finalEmployee = osiEmployeesMapper.mapToPersonalInfo(osiEmployees, existingEmployee);
					
					String effecEndDate = osiEmployeesRepository.currentTimeStamp(0);
					String effecStartDate = osiEmployeesRepository.currentTimeStamp(1);
					String UtcEffecEndDate = commonService.convertDateStringToUTC(effecEndDate, loggedInEmployeeId);
					String UtcEffecStartDate = commonService.convertDateStringToUTC(effecStartDate, loggedInEmployeeId);
					
					int updateResult = this.updateEmployeeEffectiveEndDate(existingEmployee, UtcEffecEndDate);
					finalEmployee.setVersion(existingEmployee.getVersion().intValue()+1);
					//finalEmployee.setEffectiveStartDate(effecStartDate);
					finalEmployee.setEffectiveStartDate(UtcEffecStartDate);
					finalEmployee.setLastUpdatedBy(loggedInEmployeeId);
					//finalEmployee.setLastUpdateDate(osiEmployeesRepository.currentTimeStamp(0));
					finalEmployee.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
					if(updateResult > 0){
						result = osiEmployeesRepository.saveEmployeeInfo(finalEmployee);
						osiEmployees = finalEmployee;
					}
				} else {					
					osiEmployees.setEmployeeId(maxEmployeeId+1);
					osiEmployees.setVersion(0);
					osiEmployees.setEffectiveStartDate(osiEmployees.getEffectiveStartDate());
					osiEmployees.setEffectiveEndDate(osiEmployees.getEffectiveEndDate());
					osiEmployees.setOriginalDateOfHire(osiEmployeesRepository.convertToTimestamp(osiEmployees.getOriginalDateOfHire()));
					osiEmployees.setCreatedBy(loggedInEmployeeId);
					//osiEmployees.setCreationDate(osiEmployeesRepository.currentTimeStamp(0));
					osiEmployees.setCreationDate(commonService.getCurrentDateStringinUTC());
					osiEmployees.setLastUpdatedBy(loggedInEmployeeId);
					osiEmployees.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
					result = osiEmployeesRepository.saveEmployeeInfo(osiEmployees);
					
				}
			}catch(DataAccessException e){
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
			}catch(Exception e){
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException("ERR_1000", "Error occured while saving the employee ");
			}
			log.info("saveEmployeePersonalInfo : End");
			return osiEmployees;
	}	

	@Override
	public Integer updateEmployeeEffectiveEndDate(OsiEmployees osiEmployees, String effectiveEndDate) throws BusinessException {
		Integer result;
		log.info("updateEmployeeEffectiveEndDate : Begin");
		try {
			result = osiEmployeesRepository.updateEmployeeEffectiveEndDate(osiEmployees, effectiveEndDate);
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the employee ");
		}
		log.info("updateEmployeeEffectiveEndDate : End");
		return result;
	}
	
	@Override
	public OsiContacts saveContacts(OsiEmployeesDTO osiEmployeesDTO, Integer employeeId,String employee_name)
			throws BusinessException {
		log.info("saveContacts : Begin");	
		
		OsiContacts osiEmployeeContacts = null;
		
		try{
			osiEmployeeContacts = osiEmployeeContactsRepository.save(osiEmployeesMapper.osiEmployeesOfficeInfoToOsiEmployeesContactsDTO(osiEmployeesDTO,employeeId,employee_name));
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while saving the employee contacts");
		}
		log.info("saveContacts : End");
		return osiEmployeeContacts;
		
	}

	@Override
	public List<OsiEmployees> searchEmployees(String searchData) throws BusinessException {
		List<OsiEmployees> employeeList = null;
		log.info("searchEmployees : Begin");
		try {
			employeeList = osiEmployeesRepository.searchEmployees(searchData);
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the employee ");
		}
		log.info("searchEmployees : End");
		return employeeList;
	}

	@Override
	public OsiContacts findOsiContacts(int employee_id) throws BusinessException {
		OsiContacts osiEmployeeContacts = null;
		log.info("findOsiContacts : Begin");
		try{
			String contactType = "Extension";
			osiEmployeeContacts = osiEmployeeContactsRepository.findOneByEmployeeIdAndContactType(employee_id,contactType);
		}catch(NoResultException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002","No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the employee ");
		}
		log.info("searchEmployees : End");
		return osiEmployeeContacts;
		
	}
	
	@Override
	public List<OsiCountriesDTO> getAllCountries() throws BusinessException {
		List<OsiCountriesDTO> countryList = new ArrayList<OsiCountriesDTO>();
		log.info("getAllCountries : Begin");
		try {
			List<OsiCountries> countries = osiEmployeesRepository.getAllCountries();
			for(OsiCountries osiCountries : countries) {
				OsiCountriesDTO country = new OsiCountriesDTO();
				country.setCountryId(osiCountries.getCountryId());
				country.setCountryName(osiCountries.getCountryName());
				country.setCreatedBy(osiCountries.getCreatedBy());
				country.setCreationDate(osiCountries.getCreationDate());
				country.setLastUpdatedBy(osiCountries.getLastUpdatedBy());
				country.setLastUpdateDate(osiCountries.getLastUpdateDate());
				countryList.add(country);
			}
			
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting contries");
		}
		log.info("getAllCountries : End");
		return countryList;
	}
	
	@Override
	public List<OsiCountryVisasDTO> getAllCountryVisas(Integer countryId) throws BusinessException {
		List<OsiCountryVisasDTO> countryVisaList = new ArrayList<OsiCountryVisasDTO>();
		log.info("getAllCountryVisas : Begin");
		try {
			List<OsiCountryVisas> countryVisas = osiEmployeesRepository.getAllCountryVisas(countryId);
			for(OsiCountryVisas osiCountryVisas : countryVisas) {
				OsiCountryVisasDTO countryVisa = new OsiCountryVisasDTO();
				countryVisa.setCountryVisaId(osiCountryVisas.getCountryVisaId());
				countryVisa.setCountryId(osiCountryVisas.getCountryId());
				countryVisa.setVisaType(osiCountryVisas.getVisaType());
				countryVisa.setCreatedBy(osiCountryVisas.getCreatedBy());
				countryVisa.setCreationDate(osiCountryVisas.getCreationDate());
				countryVisa.setLastUpdatedBy(osiCountryVisas.getLastUpdatedBy());
				countryVisa.setLastUpdateDate(osiCountryVisas.getLastUpdateDate());
				countryVisaList.add(countryVisa);
			}
			
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting all country visas");
		}
		log.info("getAllCountryVisas : End");
		return countryVisaList;
	}
	
	@Override
	public OsiEmployees getPassportInfoByEmployeeId(Integer employeeId) throws BusinessException {
		OsiEmployees employee = null;
		List<OsiAttachments> osiAttachmentsList = new ArrayList<OsiAttachments>();
		List<OsiEmpVisaDetailsDTO> empVisaDetailsList = new ArrayList<OsiEmpVisaDetailsDTO>();
		log.info("getPassportInfoByEmployeeId : Begin");
		try {
			employee = osiEmployeesRepository.getEmployees(employeeId);
			
			if(employee.getPhotoId() != null) {
				OsiAttachments osiAttachments = osiAttachmentsesRepository.findOne(employee.getPhotoId());
				osiAttachmentsList.add(osiAttachments);
			}
			List<OsiAttachmentsDTO> osiAttachmentsDtoList = osiAttachmentsMapper.osiAttachmentsToAttachmentsDTOList(osiAttachmentsList);
			employee.setOsiEmpAttachments(osiAttachmentsDtoList);
			
			List<OsiEmpVisaDetails> visaDetailsList = osiEmpVisaDetailsRepositoryCustom.getVisaInfoByEmployeeId(employeeId);
			if(visaDetailsList.size() > 0) {
				for(OsiEmpVisaDetails empVisaDetails : visaDetailsList) {
					OsiEmpVisaDetailsDTO visaDetails = new OsiEmpVisaDetailsDTO();
					visaDetails.setVisaId(empVisaDetails.getVisaId());
					visaDetails.setEmployeeId(empVisaDetails.getEmployeeId());
					visaDetails.setVisaNumber(empVisaDetails.getVisaNumber());
					visaDetails.setDateOfIssue(empVisaDetails.getDateOfIssue());
					visaDetails.setDateOfExpiry(empVisaDetails.getDateOfExpiry());
					visaDetails.setIssuanceAuthority(empVisaDetails.getIssuanceAuthority());
					visaDetails.setPlaceOfIssue(empVisaDetails.getPlaceOfIssue());
					visaDetails.setVisaType(empVisaDetails.getVisaType());
					visaDetails.setCountryOfVisa(empVisaDetails.getCountryOfVisa());
					visaDetails.setSingleMultiple(empVisaDetails.getSingleMultiple());
					visaDetails.setCreatedBy(empVisaDetails.getCreatedBy());
					visaDetails.setCreatedDate(empVisaDetails.getCreatedDate());
					visaDetails.setUpdatedBy(empVisaDetails.getUpdatedBy());
					visaDetails.setLastUpdateDate(empVisaDetails.getLastUpdateDate());
					empVisaDetailsList.add(visaDetails);
				}
			}
			employee.setOsiEmpVisaDetails(empVisaDetailsList);
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting passport information");
		}
		log.info("getPassportInfoByEmployeeId : End");
		return employee;
	}
	
	@Override
	public OsiEmployees getPassportInfoByEmployeeIdAndSearchDate(String inputObject) throws BusinessException {
		OsiEmployees employee = null;
		List<OsiAttachments> osiAttachmentsList = new ArrayList<OsiAttachments>();
		List<OsiEmpVisaDetailsDTO> empVisaDetailsList = new ArrayList<OsiEmpVisaDetailsDTO>();
		log.info("getPassportInfoByEmployeeIdAndSearchDate : Begin");
		try {
			if(null != inputObject) {
				JSONObject inputJson = new JSONObject(inputObject);			
				employee = osiEmployeesRepository.findByEmployeeIdAndSerachDate(inputJson.getInt("employeeId"), inputJson.getString("searchDate"));
				if(employee.getPhotoId() != null) {
					OsiAttachments osiAttachments = osiAttachmentsesRepository.findOne(employee.getPhotoId());
					osiAttachmentsList.add(osiAttachments);
				}
				List<OsiAttachmentsDTO> osiAttachmentsDtoList = osiAttachmentsMapper.osiAttachmentsToAttachmentsDTOList(osiAttachmentsList);
				employee.setOsiEmpAttachments(osiAttachmentsDtoList);
				
				List<OsiEmpVisaDetails> visaDetailsList = osiEmpVisaDetailsRepositoryCustom.getVisaInfoByEmployeeId(inputJson.getInt("employeeId"));
				if(visaDetailsList.size() > 0) {
					for(OsiEmpVisaDetails empVisaDetails : visaDetailsList) {
						OsiEmpVisaDetailsDTO visaDetails = new OsiEmpVisaDetailsDTO();
						visaDetails.setVisaId(empVisaDetails.getVisaId());
						visaDetails.setEmployeeId(empVisaDetails.getEmployeeId());
						visaDetails.setVisaNumber(empVisaDetails.getVisaNumber());
						visaDetails.setDateOfIssue(empVisaDetails.getDateOfIssue());
						visaDetails.setDateOfExpiry(empVisaDetails.getDateOfExpiry());
						visaDetails.setIssuanceAuthority(empVisaDetails.getIssuanceAuthority());
						visaDetails.setPlaceOfIssue(empVisaDetails.getPlaceOfIssue());
						visaDetails.setVisaType(empVisaDetails.getVisaType());
						visaDetails.setCountryOfVisa(empVisaDetails.getCountryOfVisa());
						visaDetails.setSingleMultiple(empVisaDetails.getSingleMultiple());
						visaDetails.setCreatedBy(empVisaDetails.getCreatedBy());
						visaDetails.setCreatedDate(empVisaDetails.getCreatedDate());
						visaDetails.setUpdatedBy(empVisaDetails.getUpdatedBy());
						visaDetails.setLastUpdateDate(empVisaDetails.getLastUpdateDate());
						empVisaDetailsList.add(visaDetails);
					}
				}
				employee.setOsiEmpVisaDetails(empVisaDetailsList);
			} else {
				throw new BusinessException("ERR_1000", "Invalid employeeId or searchDate");
			}
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting passport information");
		}
		log.info("getPassportInfoByEmployeeIdAndSearchDate : End");
		return employee;
	}
	
	@SuppressWarnings("unused")
	@Override
	public Integer savePassportInfo(OsiEmployees osiEmployees, Integer loggedInEmployeeId) throws BusinessException {
			Integer result = null;
			log.info("savePassportInfo : Begin");
			try {
				int maxEmployeeId = osiEmployeesRepository.getMaxEmployeeId();					
			/*List<OsiAttachmentsDTO> attachmentsDto = osiEmployees.getOsiEmpAttachments();
			OsiAttachments osiAttachment = null;
			if(!attachmentsDto.isEmpty()) {
				for(OsiAttachmentsDTO attachment : attachmentsDto) {
					if( osiEmployees.getEmployeeId() != null) {
						attachment.setEmployeeId(osiEmployees.getEmployeeId());
					} else {
						attachment.setEmployeeId(maxEmployeeId+1);
					}
					attachment.setAttachmentType("EMPLOYEE"); // TODO: Floder name
					attachment.setObjectType("osi_employees");// TODO: Table name
					attachment.setObjectId(osiEmployees.getEmployeeId());
					osiAttachment = osiEmployeesMapper.mapToAttachments(attachment);
				}
			}
			if(osiAttachment != null) 
				osiEmployees.setPhotoId(osiAttachment.getAttachmentId());*/
				
				List<OsiEmpVisaDetails> visaDetailsList = osiEmpVisaDetailsRepositoryCustom.getVisaInfoByEmployeeId(osiEmployees.getEmployeeId());
				List<Integer> newVisaIdList = new ArrayList<Integer>();
				List<OsiEmpVisaDetailsDTO> visaDetailsDtoList = osiEmployees.getOsiEmpVisaDetails();
				if(!visaDetailsDtoList.isEmpty()) {
					for(OsiEmpVisaDetailsDTO visaDetailsDto : visaDetailsDtoList) {
						if(visaDetailsDto.getIssuanceAuthority()!=null && visaDetailsDto.getVisaType()!=null && visaDetailsDto.getDateOfExpiry()!=null && visaDetailsDto.getSingleMultiple()!=null){
						if(visaDetailsDto.getVisaId() != 0) {
							newVisaIdList.add(visaDetailsDto.getVisaId());
						}
						
						OsiEmpVisaDetails empVisaDetails = new OsiEmpVisaDetails();
						empVisaDetails.setVisaId(visaDetailsDto.getVisaId());
						empVisaDetails.setEmployeeId(osiEmployees.getEmployeeId());
						empVisaDetails.setVisaNumber(visaDetailsDto.getVisaNumber());
						empVisaDetails.setDateOfIssue(visaDetailsDto.getDateOfIssue());
						empVisaDetails.setDateOfExpiry(visaDetailsDto.getDateOfExpiry());
						empVisaDetails.setIssuanceAuthority(visaDetailsDto.getIssuanceAuthority());
						empVisaDetails.setPlaceOfIssue(visaDetailsDto.getPlaceOfIssue());
						empVisaDetails.setVisaType(visaDetailsDto.getVisaType());
						empVisaDetails.setCountryOfVisa(visaDetailsDto.getCountryOfVisa());
						empVisaDetails.setSingleMultiple(visaDetailsDto.getSingleMultiple());
						empVisaDetails.setCreatedBy(loggedInEmployeeId);
						//empVisaDetails.setCreatedDate(new Timestamp(System.currentTimeMillis()));
						empVisaDetails.setCreatedDate(commonService.getCurrentDateinUTC());
						empVisaDetails.setUpdatedBy(loggedInEmployeeId);
						empVisaDetails.setLastUpdateDate(new Timestamp(System.currentTimeMillis()));
						empVisaDetails.setLastUpdateDate(commonService.getCurrentDateinUTC());
						osiEmpVisaDetailsRepository.save(empVisaDetails);
					}
					}
				}
				
				List<Integer> visaIdList = new ArrayList<Integer>();
				if(visaDetailsList.size() > 0) {
					for(OsiEmpVisaDetails empVisaDetails : visaDetailsList) {
						if(!newVisaIdList.contains(empVisaDetails.getVisaId())) {
							visaIdList.add(empVisaDetails.getVisaId());
						}
					}
					
					if(visaIdList.size() > 0) {
						osiEmpVisaDetailsRepositoryCustom.removeVisaInfoByVisaId(visaIdList);
					}
				}
				
				if(osiEmployees.getEmployeeId() != null) {
					OsiEmployees existingEmployee = this.findByEmployeeId(osiEmployees.getEmployeeId());
//					if(osiEmployees.getVersion().intValue() == existingEmployee.getVersion().intValue()) {
						OsiEmployees finalEmployee = osiEmployeesMapper.mapToBasicInfo(osiEmployees, existingEmployee);
						finalEmployee.setVersion(existingEmployee.getVersion().intValue()+1);
						String effecEndDate = osiEmployeesRepository.currentTimeStamp(0);
						String effecStartDate = osiEmployeesRepository.currentTimeStamp(1);
						
						String UtcEffecEndDate = commonService.convertDateStringToUTC(effecEndDate, loggedInEmployeeId);
						String UtcEffecStartDate = commonService.convertDateStringToUTC(effecStartDate, loggedInEmployeeId);
						
						int updateResult = this.updateEmployeeEffectiveEndDate(existingEmployee, UtcEffecEndDate);
						finalEmployee.setEmployeeId(osiEmployees.getEmployeeId());
						finalEmployee.setPassportNumber(osiEmployees.getPassportNumber());
						//finalEmployee.setNationality(osiEmployees.getNationality());
						finalEmployee.setPassportDateOfIssue(osiEmployees.getPassportDateOfIssue());
						finalEmployee.setPassportDateOfExpiry(osiEmployees.getPassportDateOfExpiry());
						finalEmployee.setPassportIssuanceAuthority(osiEmployees.getPassportIssuanceAuthority());
						finalEmployee.setPassportPlaceOfIssue(osiEmployees.getPassportPlaceOfIssue());
						finalEmployee.setSecondPassportExists(osiEmployees.getSecondPassportExists());
						finalEmployee.setEffectiveStartDate(UtcEffecStartDate);
						finalEmployee.setLastUpdatedBy(loggedInEmployeeId);
						//finalEmployee.setLastUpdateDate(osiEmployeesRepository.currentTimeStamp(0));
						finalEmployee.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
						if(updateResult > 0)
							result = osiEmployeesRepository.saveEmployeeInfo(finalEmployee);
//					}else {
//						throw new BusinessException("ERR_1005", "Version mismatch");
//					}
				}
			}catch(DataAccessException e){
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
			}catch(Exception e){
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException("ERR_1000", "Error occured while saving passport information");
			}
			log.info("savePassportInfo : End");
			return result;
	}
	
	public String getPersonalInfoByEmployeeId(Integer employeeId) throws BusinessException {
		String result = null; 
		log.info("getPersonalInfoByEmployeeId : Begin");
		Map<String, Object> personalInfo = new HashMap<String, Object>();
		try {
			OsiEmployees employees = this.findByEmployeeId(employeeId);
			OsiOrgAddressesDTO mailAddressDto = null;
			OsiOrgAddressesDTO permanantAddressDto = null;
			if(employees.getMailAddressId() != null) {
				mailAddressDto = addressesService.getAddressByAddressId(employees.getMailAddressId());
			}
			if(employees.getPermanentAddressId() != null) {
				permanantAddressDto = addressesService.getAddressByAddressId(employees.getPermanentAddressId());
			}
			List<OsiContactsDto> contactsDto = contactsService.findByEmployeeId(employeeId);
			personalInfo.put("employee", employees);
			personalInfo.put("mailingAddress", mailAddressDto);
			personalInfo.put("permanantAddress", permanantAddressDto);
			personalInfo.put("contacts", contactsDto);
			if(! personalInfo.isEmpty())
				result = Util.toJsonString(personalInfo);
			} catch (BusinessException e) {
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
			}catch(Exception e){
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException("ERR_1000", "Error occured while getting passport information");
			}
		log.info("getPersonalInfoByEmployeeId : End");
		return result;
	}
	
	@Override
	public OsiEmployees findByEmployeeIdAndSerachDate(String inputObject) throws BusinessException {
		OsiEmployees osiEmployees = null;
		List<OsiAttachments> osiAttachmentsList = new ArrayList<OsiAttachments>();
		log.info("findByEmployeeIdAndSerachDate : Begin");
		try {
			if(null != inputObject) {
				JSONObject inputJson = new JSONObject(inputObject);			
				osiEmployees = osiEmployeesRepository.findByEmployeeIdAndSerachDate(inputJson.getInt("employeeId"), inputJson.getString("searchDate"));
				if(osiEmployees.getPhotoId() != null) {
					OsiAttachments osiAttachments = osiAttachmentsesRepository.findOne(osiEmployees.getPhotoId());
					osiAttachmentsList.add(osiAttachments);
				}
				List<OsiAttachmentsDTO> osiAttachmentsDtoList = osiAttachmentsMapper.osiAttachmentsToAttachmentsDTOList(osiAttachmentsList);
				osiEmployees.setOsiEmpAttachments(osiAttachmentsDtoList);
			} else {
				throw new BusinessException("ERR_", "invalid employeeId or searchDate");
			}
		} catch (BusinessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting employee information");
		}
	log.info("findByEmployeeIdAndSerachDate : End");
		return osiEmployees;
	}

	@Override
	public String getPersonalInfoByEmployeeIdAndDate(String inputObject) throws BusinessException {
		String result = null; 
		Map<String, Object> personalInfo = new HashMap<String, Object>();
		log.info("getPersonalInfoByEmployeeIdAndDate : Begin");
		try {
			if(null != inputObject) {
				JSONObject jsonInput = new JSONObject(inputObject);
				int employeeId = jsonInput.getInt("employeeId");
			
				OsiEmployees employees = this.findByEmployeeIdAndSerachDate(inputObject);
				if(null != employees && null != employees.getResumeExists()) {
					if(1 == employees.getResumeExists()) {
						if(employees.getResumeId() != null) {
							OsiAttachments osiAttachments = osiAttachmentsesRepository.findOne(employees.getResumeId());
							employees.getOsiEmpAttachments().add(osiAttachmentsMapper.osiAttachmentsToAttachmentsDTO(osiAttachments));
						}
					}
				}
				OsiOrgAddressesDTO mailAddressDto = null;
				OsiOrgAddressesDTO permanantAddressDto = null;
				if(employees.getMailAddressId() != null) {
					mailAddressDto = addressesService.getAddressByAddressId(employees.getMailAddressId());
				}
				if(employees.getPermanentAddressId() != null) {
					permanantAddressDto = addressesService.getAddressByAddressId(employees.getPermanentAddressId());
				}
				List<OsiContactsDto> contactsDto = contactsService.findByEmployeeId(employeeId);
				personalInfo.put("employee", employees);
				personalInfo.put("mailingAddress", mailAddressDto);
				personalInfo.put("permanantAddress", permanantAddressDto);
				personalInfo.put("contacts", contactsDto);
				if(! personalInfo.isEmpty())
					result = Util.toJsonString(personalInfo);
			}
		} catch (BusinessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting passport information");
		}
		log.info("getPersonalInfoByEmployeeIdAndDate : End");
		return result;
	}

	/**
	 * Update Employee Basic Info
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public OsiEmployees updateEmployee(OsiEmployees osiEmployees, Integer loggedInEmployeeId) throws BusinessException {
		Integer result = null;
		log.info("updateEmployee : Begin");
		OsiEmployees finalEmployee = null;
		OsiAssignments existingAssignments = null;
		List<OsiAssignments> existingAssignmentList =  null;
		boolean dontUpdateCurrentRecord = true;
		try {
			SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date existTerminationDate = null;
			if(null != osiEmployees.getEmployeeId() ) {
				boolean isExistEmployee = false;
				isExistEmployee = osiEmployeesRepository.isExistEmployee(osiEmployees.getEmployeeNumber(), osiEmployees.getOrgId(), osiEmployees.getEmployeeId());
				if(isExistEmployee)
					throw new BusinessException("ERR_1006", "Employee Number is already exist with this organization");
			}
			List<OsiAttachmentsDTO> attachementsDto = osiEmployees.getOsiEmpAttachments();
			OsiAttachments osiAttachment = null;
			if(!attachementsDto.isEmpty()) {
				for(OsiAttachmentsDTO attachment : attachementsDto) {
					if(attachment.getFileContent() != null && !attachment.getFileContent().startsWith("http")) {
						if( osiEmployees.getEmployeeId() != null) 
							attachment.setEmployeeId(osiEmployees.getEmployeeId());
						attachment.setAttachmentType("EMPLOYEE"); // TODO: Floder name
						attachment.setObjectType("osi_employees");// TODO: Table name
						attachment.setObjectId(osiEmployees.getEmployeeId());
						osiAttachment = osiEmployeesMapper.mapToAttachments(attachment);
					}
				}
			}
			if(osiAttachment != null) 
				osiEmployees.setPhotoId(osiAttachment.getAttachmentId());
			if( osiEmployees.getEmployeeId() != null) {
				OsiEmployees existingEmployee = this.findByEmployeeId(osiEmployees.getEmployeeId());
				String existTerminationDateString = existingEmployee.getTerminationDate();
				if(osiEmployees.getVersion().intValue() == existingEmployee.getVersion().intValue()) {
				
					if(null != existTerminationDateString)
						existTerminationDate = sDate.parse(existTerminationDateString);
						existingAssignmentList =  osiAssignmentsRepositoryCustom.findAssignmentByEmployeeId(osiEmployees.getEmployeeId());
						if(existingAssignmentList!=null && !existingAssignmentList.isEmpty() && existingAssignmentList.get(0)!=null)
							existingAssignments = existingAssignmentList.get(0);
						if(!sDate.parse(existingEmployee.getTerminationDate()).equals(sDate.parse(defaultEndDate)) && sDate.parse(osiEmployees.getTerminationDate()).equals(sDate.parse(defaultEndDate))){
					if(new Date().before(existTerminationDate)
						|| new Date().equals(existTerminationDate)){
						// Delete wfs activities
						List<Integer> wfsIds = new ArrayList<Integer>();
						wfsIds.add(osiWorkflowsRepository.getWorkFlow(employeeTerminationRemainder, osiEmployees.getOrgId()));
						wfsIds.add(osiWorkflowsRepository.getWorkFlow(employeeTermination, osiEmployees.getOrgId()));
						osiWorkflowsRepository.deleteExistingTerminationRecords(wfsIds, osiEmployees.getEmployeeId(), osiEmployees.getOrgId());
					}else if(new Date().after(existTerminationDate)){
						finalEmployee = osiEmployeesMapper.mapToBasicInfo(osiEmployees, existingEmployee);
						finalEmployee.setEffectiveStartDate(commonService.getCurrentDateStringinUTC());
						existingAssignments.setEffectiveStartDate(sDate.parse(finalEmployee.getEffectiveStartDate()));
						finalEmployee.setStartDate(commonService.getCurrentDateStringinUTC());
						finalEmployee.setEffectiveEndDate(defaultEndDate);
						finalEmployee.setTerminationDate(defaultEndDate);
						existingAssignments.setEffectiveEndDate(defaultEndDate);
						finalEmployee.setLastUpdatedBy(loggedInEmployeeId);
						result = osiEmployeesRepository.saveEmployeeInfo(finalEmployee);
						finalEmployee.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
						//osiAssignmentsRepositoryCustom.saveAssignments(existingAssignments, osiEmployees.getTerminationDate());
						osiEmployees = finalEmployee;
						dontUpdateCurrentRecord = false;
					}
					}
					if(dontUpdateCurrentRecord){
						finalEmployee = osiEmployeesMapper.mapToBasicInfo(osiEmployees, existingEmployee);					
						String terminateString = finalEmployee.getTerminationDate();
						String fianlTerminationSting = terminateString.split(" ")[0].toString().concat(" ").concat(existTerminationDateString.split(" ")[1].toString());
						finalEmployee.setEffectiveEndDate(fianlTerminationSting);
						finalEmployee.setTerminationDate(fianlTerminationSting);
					//	finalEmployee.setVersion(existingEmployee.getVersion().intValue()+1);
						finalEmployee.setLastUpdatedBy(loggedInEmployeeId);
						//finalEmployee.setLastUpdateDate(osiEmployeesRepository.currentTimeStamp(0));
						finalEmployee.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
						result = osiEmployeesRepository.updateEmployee(finalEmployee);
						if(existingAssignments!=null)
							osiAssignmentsRepositoryCustom.updateAssignments(existingAssignments, osiEmployees.getTerminationDate());
						osiEmployees = finalEmployee;
					}
					
					}else {
					throw new BusinessException("ERR_1005", "Version mismatch");
				}
			}
			if(!sDate.parse(osiEmployees.getTerminationDate()).equals(sDate.parse(defaultEndDate))){
				if(existingAssignments!=null)
					osiAssignmentsRepositoryCustom.updateAssignments(existingAssignments, osiEmployees.getTerminationDate());

				List<OsiWfsActivities> wfsActivitesList = new ArrayList<OsiWfsActivities>();
				OsiWfsActivities wfsActivities = new OsiWfsActivities();
				wfsActivities.setObjectId(osiEmployees.getEmployeeId());
				wfsActivities.setObjectName("OSI_EMPLOYEES");
				wfsActivities.setProcessFlag("N");
				Integer wfsId = osiWorkflowsRepository.getWorkFlow(employeeTermination, osiEmployees.getOrgId());
				if(wfsActivities!=null && osiWorkflowsRepository.verifyExistingWorkflow(wfsId, osiEmployees.getEmployeeId(), osiEmployees.getOrgId())==0){
					wfsActivities.setWfsId(wfsId);
					wfsActivities.setStartDate(commonService.convertDateStringToUTC(osiEmployees.getTerminationDate(), loggedInEmployeeId));
					wfsActivities.setOrgId(osiEmployees.getOrgId());
					wfsActivitesList.add(wfsActivities);
					
					Date terminationDate= sDate.parse(osiEmployees.getTerminationDate());
					wfsActivities = new OsiWfsActivities();
					wfsActivities.setObjectId(osiEmployees.getEmployeeId());
					wfsActivities.setObjectName("OSI_EMPLOYEES");
					wfsActivities.setProcessFlag("N");
					terminationDate.setDate(terminationDate.getDate()-1);
					wfsActivities.setStartDate(commonService.convertDateStringToUTC(sDate.format(terminationDate), loggedInEmployeeId));
					wfsId = osiWorkflowsRepository.getWorkFlow(employeeTerminationRemainder, osiEmployees.getOrgId());
					wfsActivities.setWfsId(wfsId);
					wfsActivities.setOrgId(osiEmployees.getOrgId());
					wfsActivitesList.add(wfsActivities);
					wfsActivities = new OsiWfsActivities();
					wfsActivities.setObjectId(osiEmployees.getEmployeeId());
					wfsActivities.setObjectName("OSI_EMPLOYEES");
					wfsActivities.setProcessFlag("N");
					terminationDate.setDate(terminationDate.getDate()-1);
					wfsActivities.setStartDate(commonService.convertDateStringToUTC(sDate.format(terminationDate), loggedInEmployeeId));
					wfsId = osiWorkflowsRepository.getWorkFlow(employeeTerminationRemainder, osiEmployees.getOrgId());
					wfsActivities.setWfsId(wfsId);
					wfsActivities.setOrgId(osiEmployees.getOrgId());
					wfsActivitesList.add(wfsActivities);
				
					osiWfsActivitiesRepository.save(wfsActivitesList);
				}
			}
		} catch (BusinessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the employee");
		}
		log.info("updateEmployee : End");
		return osiEmployees;
	}
	
	/**
	 * Update Personal Info..
	 * for correction mode
	 */
	@SuppressWarnings("unused")
	@Override
	public OsiEmployees updateEmployeePersonalInfo(OsiEmployees osiEmployees, Integer loggedInEmployeeId) throws BusinessException {
		log.info("updateEmployeePersonalInfo : Begin");	
		Integer result = null;
			try {
				
				if( osiEmployees.getEmployeeId() != null) {
					
					OsiEmployees existingEmployee = this.findByEmployeeId(osiEmployees.getEmployeeId());
			//		if(osiEmployees.getVersion().intValue() == existingEmployee.getVersion().intValue()) {
						OsiEmployees finalEmployee = osiEmployeesMapper.mapToPersonalInfo(osiEmployees, existingEmployee);
						finalEmployee.setVersion(existingEmployee.getVersion().intValue()+1);
						finalEmployee.setLastUpdatedBy(loggedInEmployeeId);
						//finalEmployee.setLastUpdateDate(osiEmployeesRepository.currentTimeStamp(0));
						finalEmployee.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
						result = osiEmployeesRepository.updateEmployee(finalEmployee);
						osiEmployees = finalEmployee;
						
					/*}else {
						throw new BusinessException("ERR_1005", "Version mismatch");
					}*/
				}
			}catch (BusinessException e) {
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
			} catch (DataAccessException e) {
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
			}catch(Exception e){
				log.error("Error Occured : "+e.getMessage());
				throw new BusinessException("ERR_1000", "Error occured while updating the personal information");
			}
			log.info("updateEmployeePersonalInfo : End");
			return osiEmployees;
	}

	@Override
	public boolean isManager(Integer employeeId) throws BusinessException {
		log.info("isManager : Begin");
		try {
			log.info("isManager : End");
			return osiEmployeesRepository.isManager(employeeId);
		} catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while executing");
		}
		
	}

	@Override
	public List<OsiEmployees> searchEmployeesSelf(String searchData) throws BusinessException {
		List<OsiEmployees> employeeList = null;
		log.info("searchEmployeesSelf : Begin");
		try {
			employeeList = osiEmployeesRepository.searchEmployeesSelf(searchData);
		} catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while searching the employee");
		}
		log.info("searchEmployeesSelf : End");
		return employeeList;
	}
	
	@Override
	public List<OsiEmpAdditionalDocsDTO> findAdditionalDocumentsByEmployeeId(Integer employeeId) throws BusinessException {
		List<OsiEmpAdditionalDocsDTO> osiEmpAdditionalDocsDTOList = null;
		log.info("findAdditionalDocumentsByEmployeeId : Begin");
		try {
			List<OsiEmpAdditionalDocs> osiEmpAdditionalDocsList = osiEmpAdditionalDocsRepository.findByEmployeeId(employeeId);
			osiEmpAdditionalDocsDTOList = new ArrayList<OsiEmpAdditionalDocsDTO>();
			for(OsiEmpAdditionalDocs empAdditionalDocs: osiEmpAdditionalDocsList) {
				OsiEmpAdditionalDocsDTO osiEmpAdditionalDocsDTO = osiEmpAdditionalDocsMapper.toOsiEmpAdditionalDocsDTO(empAdditionalDocs);
				osiEmpAdditionalDocsDTOList.add(osiEmpAdditionalDocsDTO);
			}
		}catch(BusinessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the Additional Documents details");
		}
		log.info("findAdditionalDocumentsByEmployeeId : End");
		return osiEmpAdditionalDocsDTOList;
	}
	
	
	@Override
	public List<OsiEmpAdditionalDocsDTO> saveAdditionalDocs(OsiEmpAdditionalDocsDTO empDocs) throws BusinessException {
		List<OsiEmpAdditionalDocsDTO> osiEmpAdditionalDocsDTOList = null;
		log.info("findAdditionalDocumentsByEmployeeId : Begin");
		try {
			OsiEmpAdditionalDocs finalDocs= osiEmpAdditionalDocsMapper.toOsiEmpAdditionalDocs(empDocs);
			OsiEmpAdditionalDocs savedDocs = osiEmpAdditionalDocsRepository.save(finalDocs);
			List<OsiEmpAdditionalDocs> osiEmpAdditionalDocsList = osiEmpAdditionalDocsRepository.findByEmployeeId(savedDocs.getEmployeeId());
			osiEmpAdditionalDocsDTOList = new ArrayList<OsiEmpAdditionalDocsDTO>();
			for(OsiEmpAdditionalDocs empAdditionalDocs: osiEmpAdditionalDocsList) {
				OsiEmpAdditionalDocsDTO osiEmpAdditionalDocsDTO = osiEmpAdditionalDocsMapper.toOsiEmpAdditionalDocsDTO(empAdditionalDocs);
				osiEmpAdditionalDocsDTOList.add(osiEmpAdditionalDocsDTO);
			}
		}catch(BusinessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the Additional Documents details");
		}
		log.info("findAdditionalDocumentsByEmployeeId : End");
		return osiEmpAdditionalDocsDTOList;
	}
	
	@Override
	public List<OsiEmployees> overallSearch(String searchData) throws BusinessException {
		List<OsiEmployees> employeeList = null;
		log.info("overallSearch : Begin");
		try {
			employeeList = osiEmployeesRepository.overallEmployeesSearch(searchData);
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the employee ");
		}
		log.info("overallSearch : End");
		return employeeList;
	}
	
	@Override
	public List<OsiEmployees> getAllEmployees(String inputData) throws BusinessException {
		List<OsiEmployees> employeeList = null;
		log.info("getAllEmployees With in the Date range : Begin");
		try {
			employeeList = osiEmployeesRepository.getAllEmployees(inputData);
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the employees with in the date range ");
		}
		log.info("getAllEmployees With in the Date range : End");
		return employeeList;
	}
	
	@Override
	public List<OsiEmployees> updateEmployees(List<OsiEmployees> empList) throws BusinessException {
		List<OsiEmployees> employeeList = null;
		log.info("getAllEmployees With in the Date range : Begin");
		try {
			for(OsiEmployees emp : empList)
				osiEmployeesRepository.updateEmployee(emp);
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the employees with in the date range ");
		}
		log.info("getAllEmployees With in the Date range : End");
		return empList;
	}
}
