package com.osi.ems.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiContacts;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.domain.OsiWfsActivities;
import com.osi.ems.mapper.OsiContactsMapper;
import com.osi.ems.repository.OsiContactsRepository;
import com.osi.ems.repository.OsiWfsActivitiesRepository;
import com.osi.ems.repository.custom.OsiEmployeesRepositoryCustom;
import com.osi.ems.repository.custom.OsiWorkflowsRepositoryCustom;
import com.osi.ems.service.OsiContactsService;
import com.osi.ems.service.dto.OsiContactsDto;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * Class that publish the behavior provided by {@link OsiContactsService}
 * interface.
 * 
 * @author jkolla
 *
 */
@Service
@Transactional
public class OsiContactsServiceImpl implements OsiContactsService {

	private final Logger log = LoggerFactory.getLogger(OsiContactsServiceImpl.class);

	/**
	 * Field for communicating with database.
	 */
	@Autowired
	private OsiContactsRepository contactsRepository;
	
	@Autowired
	private OsiContactsMapper mapper;
	
	@Autowired
	private OsiEmployeesRepositoryCustom employeeRepository;

	@Autowired
	private CommonService commonService;
	
	
	@Autowired
	OsiWfsActivitiesRepository osiWfsActivitiesRepository;
	
	@Autowired
	OsiWorkflowsRepositoryCustom osiWorkflowsRepository;
	
	@Value( "${WORKFLOW.EMPLOYEE_UPDATION}" )
	private String employeeUpdation;
	
	@Override
	public OsiContactsDto saveContact(OsiContactsDto osiContact, Integer userId) throws BusinessException {

		//String createdDate = null;
		//Timestamp timestamp = null;

		/*
		 * log the message
		 */
		log.debug("Request to save osiContact : {}", osiContact);
		
		log.info("saveContact : Begin");
		
		//timestamp = new Timestamp(System.currentTimeMillis());
		//createdDate = new SimpleDateFormat("yyyy/MM/dd").format(timestamp);
		
		osiContact.setCreatedBy(userId);
		//osiContact.setCreationDate(createdDate);
		osiContact.setCreationDate(commonService.getCurrentDateStringinUTC());
		osiContact.setLastUpdatedBy(userId);
		//osiContact.setLastUpdateDate(createdDate);
		osiContact.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
		/*
		 * Save the osicountry object.
		 */
		try {
			
			OsiEmployees emp = employeeRepository.getEmployees(osiContact.getEmployeeId());
			osiContact.setContactName(emp.getFirstName());
			osiContact.setRelation("Self");
			osiContact.setSeq(1);
			//osiContact.setEmployeeId(userId);
			OsiContacts c = mapper.osiContactsDTOToOsiContacts(osiContact);
			OsiContacts existingContacts = contactsRepository.findOneByEmployeeIdAndContactType(emp.getEmployeeId(), "personal");
			if(existingContacts!=null && existingContacts.getContactType()!=null && "personal".equalsIgnoreCase(existingContacts.getContactType())
					&& existingContacts.getContactNumber()!=null && !existingContacts.getContactNumber().equals(osiContact.getContactNumber())){
			OsiWfsActivities wfsActivities = new OsiWfsActivities();
			wfsActivities.setObjectId(emp.getEmployeeId());
			wfsActivities.setObjectName("OSI_EMPLOYEES");
			wfsActivities.setProcessFlag("N");
			wfsActivities.setWfsId(osiWorkflowsRepository.getWorkFlow(employeeUpdation, emp.getOrgId()));
			wfsActivities.setOrgId(emp.getOrgId());
			wfsActivities.setStartDate(commonService.getCurrentDateStringinUTC());
			if(wfsActivities!=null && osiWorkflowsRepository.verifyExistingWorkflow(wfsActivities.getWfsId(), emp.getEmployeeId(), emp.getOrgId())==0)
				osiWfsActivitiesRepository.save(wfsActivities);
			}
			osiContact = mapper.osiContactssToosiContactsDTO(contactsRepository.save(c));
		}catch(DataAccessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while saving the contacts");
		}
		log.info("saveContact : End");
		return osiContact;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.OsiContactService#updateContact(com.osi.ems.domain.
	 * OsiContacts, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public OsiContactsDto updateContact(OsiContactsDto osiContacts, Integer countryId, Integer userId)
			throws BusinessException {
		//String createdDate = null;
		//Timestamp timestamp = null;
		OsiContacts updatedContact = null;

		/*
		 * log the message
		 */
		log.debug("Request to Update osiContact : {}", osiContacts);
		log.info("updateContact : Begin");
		/*
		 * Check if object exists with this id or not.
		 */
		try {
			if ((updatedContact = this.contactsRepository.findOne(countryId)) != null) {
				//timestamp = new Timestamp(System.currentTimeMillis());
				//createdDate = new SimpleDateFormat("yyyy/MM/dd").format(timestamp);
				osiContacts.setContactId(countryId);
				osiContacts.setCreatedBy(updatedContact.getCreatedBy());
				osiContacts.setCreationDate(updatedContact.getCreationDate());
				osiContacts.setLastUpdatedBy(userId);
				//osiContacts.setLastUpdateDate(createdDate);
				osiContacts.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
				OsiContacts c = mapper.osiContactsDTOToOsiContacts(osiContacts);
				
				/*
				 * Save the object
				 */
				osiContacts = mapper.osiContactssToosiContactsDTO(this.contactsRepository.save(c));
			} else {
				osiContacts = this.saveContact(osiContacts, userId);
			}
		}catch(NoResultException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the contacts");
		}
		log.info("updateContact : End");

		return osiContacts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.OsiContactService#getAllContactsList()
	 */
	@Override
	public List<OsiContactsDto> getAllContactsList() throws BusinessException {

		List<OsiContactsDto> osiContactsList = null;
		log.debug("Request to getall osiContacts ");
		log.info("getAllContactsList : Begin");
		try {
			osiContactsList = mapper.osiContactsListToOsiContactsDTOList(this.contactsRepository.findAll());
		
		}catch(NoResultException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the contacts");
		}
		log.info("getAllContactsList : End");
		return osiContactsList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.OsiContactService#getContactByContactId(java.lang.
	 * Integer)
	 */
	@Override
	public OsiContactsDto getContactByContactId(Integer countryId) throws BusinessException {
		OsiContactsDto osiContact = null;
		log.debug("Request to get osiContacts ");
		log.info("getContactByContactId : Begin");
		try {
			osiContact = this.mapper.osiContactssToosiContactsDTO(this.contactsRepository.findOne(countryId));
		}catch(NoResultException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the contacts by id"+countryId);
		}
		log.info("getContactByContactId : End");
		return osiContact;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osi.ems.service.OsiContactService#deleteOsiContacts(java.lang.Integer)
	 */
	@Override
	public void deleteOsiContacts(Integer countryId) throws BusinessException {
		log.debug("Request to delete osiContacts ");
		log.info("deleteOsiContacts : Begin");
		try {
			this.contactsRepository.delete(countryId);
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the contacts by id"+countryId);
		}
		log.info("deleteOsiContacts : End");
		return;
	}


	@Override
	public List<OsiContactsDto> saveContactList(List<OsiContactsDto> osiContacts, Integer id) throws BusinessException {
		/*
		 * log the message
		 */
		log.debug("Request to save osiContact list : {}", osiContacts);
		log.info("saveContactList : Begin");		
		/*
		 * Save the osicountry object.
		 */
		try {
			for( OsiContactsDto c : osiContacts) {
				this.saveContact(c, id);				
			}
			
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while saving the contacts");
		}
		log.info("saveContactList : End");
		
		return osiContacts;
	}

	@Override
	public List<OsiContactsDto> findByEmployeeId(Integer employeeId) throws BusinessException {
		List<OsiContactsDto> osiContactsList = null;
		log.info("findByEmployeeId : Begin");
		log.debug("Request to get osiContacts ");
		try {
			osiContactsList = this.mapper.osiContactsListToOsiContactsDTOList(this.contactsRepository.findOneByEmployeeId(employeeId));
		}catch(NoResultException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the employee");
		}
		log.info("findByEmployeeId : End");
		return osiContactsList;
	}

	
	

}
