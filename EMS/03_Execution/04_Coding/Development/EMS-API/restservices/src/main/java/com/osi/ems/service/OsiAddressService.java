package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.OsiOrgAddressesDTO;
import com.osi.urm.exception.BusinessException;

/**
 * The interface for publishing the method to deal with address details.
 * @author jkolla
 *
 */
public interface OsiAddressService {
	
	
	/**
	 * Method for saving the Addresses.
	 * @param osiAddress
	 * @return Returns the Same {@link OsiOrgAddressesDTO} object.
	 * @throws BusinessException 
	 */
	public OsiOrgAddressesDTO saveAddress(OsiOrgAddressesDTO osiAddress,Integer userId) throws BusinessException;
	
	/**
	 * Method for updating the {@link OsiOrgAddressesDTO} object.
	 * @param OsiOrgAddressesDTO
	 * @param addressId
	 * @return Returns the updated {@link OsiOrgAddressesDTO} object.
	 * @throws BusinessException 
	 */
	public OsiOrgAddressesDTO updateAddress(OsiOrgAddressesDTO osiAddresses,Integer addressId, Integer userId) throws BusinessException;
	
	/**
	 * Method for getting all the countries list.
	 * @return Returns the {@link List} of {@link OsiOrgAddressesDTO}.
	 * @throws BusinessException 
	 */
	public List<OsiOrgAddressesDTO> getAllAddressesList() throws BusinessException;
	
	/**
	 * Method for getting the address by id.
	 * @param addressId
	 * @return Returns the {@link OsiOrgAddressesDTO} object.
	 * @throws BusinessException 
	 */
	public OsiOrgAddressesDTO getAddressByAddressId(Integer addressId) throws BusinessException;
	
	/**
	 * Method for deleting the address id.
	 * @param addressId Returns void.
	 * @throws BusinessException 
	 */
	public void deleteOsiOrgAddressesDTO(Integer addressId) throws BusinessException;



	

}
