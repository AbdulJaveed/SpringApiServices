package com.osi.ems.service;

import java.util.List;

import com.osi.ems.domain.OsiOrganizations;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

	/**
	 * Service Interface for managing OsiOrganizations.
	 */
public interface OsiOrganizationsService {

		/**
		 * Save a OsiOrganizations.
		 *
		 * @param OsiOrganizationsDTO
		 *            the entity to save
		 * @return the persisted entity
		 */
	OsiOrganizationsDTO save(OsiOrganizationsDTO osiOrganizationsDTO)throws BusinessException;

		/**
		 * Get all the OsiOrganizations.
		 * 
		 * @param pageable
		 *            the pagination information
		 * @return the list of entities
		 */
		List<OsiOrganizationsDTO> findAll() throws BusinessException;
		


		/**
		 * Get the "id" OsiOrganizations.
		 *
		 * @param id
		 *            the id of the entity
		 * @return the entity
		 */
		OsiOrganizationsDTO findOne(Integer id);

		/**
		 * Delete the "id" OsiOrganizations.
		 *
		 * @param id
		 *            the id of the entity
		 */
		void delete(Integer id);
		
		public List<OsiOrganizationsDTO> getOsiOrganizations() throws  BusinessException, DataAccessException;
		public List<OsiOrganizationsDTO> findOrg(String orgName,String city,String location) throws BusinessException, DataAccessException;
		public Object getOsiCurrencyInfo() throws BusinessException, DataAccessException;
		public OsiOrganizations createOrganization(OsiOrganizationsDTO orgVO, Integer userId) throws BusinessException,DataAccessException ;
		public OsiOrganizationsDTO getOrganizationsByOrgId(Integer orgId) throws BusinessException, DataAccessException ;

		public List<OsiEmployeesDTO> contactPersonSearch(String empName,Integer orgId) throws BusinessException, DataAccessException ;

}
