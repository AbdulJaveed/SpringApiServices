package com.osi.ems.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiOrgAddresses;
import com.osi.ems.mapper.OsiOrgAddressMapper;
import com.osi.ems.repository.OsiAddressRepository;
import com.osi.ems.service.OsiAddressService;
import com.osi.ems.service.dto.OsiOrgAddressesDTO;
import com.osi.urm.exception.BusinessException;

/**
 * Class that publish the behavior provided by {@link OsiOrgAddressesDTOService}
 * interface.
 * 
 * @author jkolla
 *
 */
@Service
@Transactional
public class OsiAddressesServiceImpl implements OsiAddressService {

	private final Logger log = LoggerFactory.getLogger(OsiAddressesServiceImpl.class);

	/**
	 * Field for communicating with database.
	 */
	@Autowired
	private OsiAddressRepository addressRepository;

	@Autowired
	private OsiOrgAddressMapper osiOrgAddressMapper;
	
	@Autowired
	private CommonService commonService;

	@Override
	public OsiOrgAddressesDTO saveAddress(OsiOrgAddressesDTO osiAddress, Integer userId) throws BusinessException {

		/*
		 * log the message
		 */
		log.debug("Request to save osiAddress : {}", osiAddress);
		log.info("saveAddress : Begin");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			osiAddress.setCreatedBy(userId);
			osiAddress.setObjectId(userId);
			//osiAddress.setCreationDate(df.parse(df.format(timestamp)));
			osiAddress.setCreationDate(df.parse(commonService.getCurrentDateStringinUTC()));
			osiAddress.setLastUpdatedBy(userId);
			//osiAddress.setLastUpdateDate(df.parse(df.format(timestamp)));
			osiAddress.setLastUpdateDate(df.parse(commonService.getCurrentDateStringinUTC()));
			osiAddress.setObjectId(userId);
			/*
			 * Save the osiAddress object.
			 */

			// convert the mapper to pojo
			OsiOrgAddresses address = osiOrgAddressMapper.osiOrgAddressDTOToOsiOrgAddress(osiAddress);

			address = addressRepository.save(address);
			osiAddress = osiOrgAddressMapper.osiOrgAddressToOsiOrgAddressDTO(address);

		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while saving address");
		}
		log.info("saveAddress : End");
		return osiAddress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.osiAddressService#updateCountry(com.osi.ems.domain.
	 * OsiOrgAddressesDTO, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public OsiOrgAddressesDTO updateAddress(OsiOrgAddressesDTO osiAddresses, Integer addressId, Integer userId)
			throws BusinessException {
		OsiOrgAddresses updatedAddresses = null;

		/*
		 * log the message
		 */
		log.debug("Request to Update osiAddress : {}", osiAddresses);

		log.info("updateAddress : Begin");
		/*
		 * Check if object exists with this id or not.
		 */
		try {
			// validate address object and throw business exception.
			validateAddressObject(osiAddresses);

			if ((updatedAddresses = this.addressRepository.findOne(addressId)) != null) {
				osiAddresses.setAddressId(addressId);
				//timestamp = new Timestamp(System.currentTimeMillis());
				//DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				//createdDate = df.format(timestamp);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				osiAddresses.setCreatedBy(updatedAddresses.getCreatedBy());
				osiAddresses.setCreationDate(updatedAddresses.getCreationDate());
				osiAddresses.setLastUpdatedBy(userId);
				//osiAddresses.setLastUpdateDate(df.parse(createdDate));
				osiAddresses.setLastUpdateDate(df.parse(commonService.getCurrentDateStringinUTC()));
				OsiOrgAddresses address = osiOrgAddressMapper.osiOrgAddressDTOToOsiOrgAddress(osiAddresses);
				/*
				 * Save the object
				 */
				this.addressRepository.save(address);
			} else {
				osiAddresses = this.saveAddress(osiAddresses, userId);
			}
		} catch(BusinessException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating address");
		}
		log.info("updateAddress : End");
		return osiAddresses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.osiAddressService#getAllAddressesList()
	 */
	@Override
	public List<OsiOrgAddressesDTO> getAllAddressesList() throws BusinessException {
		log.info("getAllAddressesList : Begin");
		List<OsiOrgAddressesDTO> osiAddressesDtoList = null;
		List<OsiOrgAddresses> osiAddressList = null;
		log.debug("Request to getall osiAddresses ");
		try {
			
			osiAddressList = this.addressRepository.findAll();
			osiAddressesDtoList = osiOrgAddressMapper.osiOrgAddressListToOsiOrgAddressDTOList(osiAddressList);
			log.info("getAllAddressesList : End");
			return osiAddressesDtoList;
		}catch (NoResultException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002","No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the address");
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.osiAddressService#getCountryByCountryId(java.lang.
	 * Integer)
	 */
	@Override
	public OsiOrgAddressesDTO getAddressByAddressId(Integer countryId) throws BusinessException {
		OsiOrgAddressesDTO osiAddress = null;
		log.info("getAddressByAddressId : Begin");
		log.debug("Request to get osiAddresses ");
		try {
			
			osiAddress = osiOrgAddressMapper.osiOrgAddressToOsiOrgAddressDTO(this.addressRepository.findOne(countryId));
			
		}catch (NoResultException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002","No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the address by id"+countryId);
		}
		log.info("getAddressByAddressId : End");
		return osiAddress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osi.ems.service.osiAddressService#deleteOsiOrgAddressesDTO(java.lang.
	 * Integer)
	 */
	@Override
	public void deleteOsiOrgAddressesDTO(Integer countryId) throws BusinessException {
		log.debug("Request to delete osiAddresses ");
		log.info("deleteOsiOrgAddressesDTO : Begin");
		try {
			this.addressRepository.delete(countryId);
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", "Error Occured while deleting org address");
		}
		log.info("deleteOsiOrgAddressesDTO : End");
		return;
	}

	/*
	 * Validating the address field.
	 */
	private void validateAddressObject(OsiOrgAddressesDTO address) throws BusinessException {
		// check all mandatory fields
		if (address.getAddressLine1() == null) {
			throw new BusinessException("ERR_1111", "Address line1 is mandatory");
		}
		if (address.getCity() == null) {
			throw new BusinessException("ERR_1111", "City is mandatory");
		}
		if (address.getStateId() == null) {
			throw new BusinessException("ERR_1111", "State is mandatory");
		}
		if (address.getCountryId() == null) {
			throw new BusinessException("ERR_1111", "Country is mandatory");
		}
		if (address.getZipcode() == null) {
			throw new BusinessException("ERR_1111", "Zip code is mandatory");

		}
	}

}
