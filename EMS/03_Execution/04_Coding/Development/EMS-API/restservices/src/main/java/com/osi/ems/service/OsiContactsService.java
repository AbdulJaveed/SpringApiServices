package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.OsiContactsDto;
import com.osi.urm.exception.BusinessException;

/**
 * The interface for publishing the method to deal with contact details.
 * @author jkolla
 *
 */
public interface OsiContactsService {
	
	
	/**
	 * Method for saving the countries.
	 * @param osiContact
	 * @return Returns the Same {@link OsiContactsDto} object.
	 * @throws BusinessException 
	 */
	public OsiContactsDto saveContact(OsiContactsDto osiContact,Integer userId) throws BusinessException;
	
	/**
	 * Method for updating the {@link OsiContactsDto} object.
	 * @param osiContacts
	 * @param contactId
	 * @return Returns the updated {@link OsiContactsDto} object.
	 * @throws BusinessException 
	 */
	public OsiContactsDto updateContact(OsiContactsDto osiContacts,Integer contactId, Integer userId) throws BusinessException;
	
	/**
	 * Method for getting all the countries list.
	 * @return Returns the {@link List} of {@link OsiContactsDto}.
	 * @throws BusinessException 
	 */
	public List<OsiContactsDto> getAllContactsList() throws BusinessException;
	
	/**
	 * Method for getting the contact by id.
	 * @param contactId
	 * @return Returns the {@link OsiContactsDto} object.
	 * @throws BusinessException 
	 */
	public OsiContactsDto getContactByContactId(Integer contactId) throws BusinessException;
	
	/**
	 * Method for deleting the contact id.
	 * @param contactId Returns void.
	 * @throws BusinessException 
	 */

	public List<OsiContactsDto> saveContactList(List<OsiContactsDto> osiContacts, Integer id) throws BusinessException;

	void deleteOsiContacts(Integer countryId) throws BusinessException;
	
	public List<OsiContactsDto> findByEmployeeId(Integer employeeId) throws BusinessException;	

}
