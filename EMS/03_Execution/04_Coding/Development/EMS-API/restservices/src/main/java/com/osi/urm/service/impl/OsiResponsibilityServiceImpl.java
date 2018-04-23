package com.osi.urm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.urm.domain.OsiResponsibilities;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.mapper.OsiReponsibilitiesMapper;
import com.osi.urm.repository.OsiResponsibilityRepository;
import com.osi.urm.service.OsiResponsibilityService;
import com.osi.urm.service.dto.OsiResponsibilitiesDTO;
/**
 * Service Implementation for managing OsiResponsibility.
 */
@Service
@Transactional
public class OsiResponsibilityServiceImpl implements OsiResponsibilityService {

	private final Logger log = LoggerFactory.getLogger(OsiResponsibilityServiceImpl.class);

	@Autowired
	private OsiReponsibilitiesMapper osiResponsibilitiesMapper;

	@Autowired
	private OsiResponsibilityRepository osiResponsibilityRepository;

	@Autowired
	private OsiReponsibilitiesMapper OsiReponsibilitiesMapper;
	
	@PersistenceContext
    private EntityManager entityManager;


	/**
	 * Save a osiResponsibility.
	 *
	 * @param osiResponsibilitiesDTO
	 *            the entity to save
	 * @return the persisted entity
	 * @throws DataAccessException 
	 */
	public OsiResponsibilitiesDTO save(OsiResponsibilitiesDTO osiResponsibilitiesDTO, Integer businessGroupId)
			throws BusinessException, DataAccessException {
		OsiResponsibilitiesDTO result = new OsiResponsibilitiesDTO();
		/*osiResponsibilitiesDTO.setBusinessGroupId(businessGroupId);
		osiResponsibilitiesDTO.setActive(1);*/
		try {
			osiResponsibilitiesDTO.setBusinessGroupId(businessGroupId);
			/*osiResponsibilitiesDTO.setActive(1);*/

			if (osiResponsibilitiesDTO.getId() != null) {
				
				OsiResponsibilities osiResponsibility = OsiReponsibilitiesMapper
									.osiResponsibilitiesDTOToOsiResponsibilities(osiResponsibilitiesDTO);
				Date startDate = osiResponsibility.getStartDate();
				Date endDate = osiResponsibility.getEndDate();
				Date startDateFormat = formatStartDate(startDate);
				Date endDateFormat = formatEndDate(endDate);
				
		        osiResponsibility.setStartDate(startDateFormat);
		        osiResponsibility.setEndDate(endDateFormat);
				osiResponsibility = osiResponsibilityRepository.updateResponsbility(osiResponsibility);
				 osiResponsibilitiesDTO = OsiReponsibilitiesMapper
						.osiResponsibilitiesToOsiResponsibilitiesDTO(osiResponsibility);
			} else {
				OsiResponsibilities osiResponsibility = OsiReponsibilitiesMapper
									.osiResponsibilitiesDTOToOsiResponsibilities(osiResponsibilitiesDTO);
				/*if ((findResponsibilityByNameAndActive(osiResponsibilitiesDTO.getRespName(), businessGroupId,1)) > 0) {
					throw new BusinessException("ERR_1037", null);
				}*/
				if (osiResponsibilitiesDTO.getRespName() != null) {
					Date startDate = osiResponsibility.getStartDate();
					Date endDate = osiResponsibility.getEndDate();
					Date startDateFormat = formatStartDate(startDate);
					Date endDateFormat = formatEndDate(endDate);
					
			        osiResponsibility.setStartDate(startDateFormat);
			        osiResponsibility.setEndDate(endDateFormat);
					
							osiResponsibility = osiResponsibilityRepository.save(osiResponsibility);
							 osiResponsibilitiesDTO = OsiReponsibilitiesMapper
									.osiResponsibilitiesToOsiResponsibilitiesDTO(osiResponsibility);
					}
				}
				
		//} catch (BusinessException e) {
			//throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiResponsibilitiesDTO;
	
	}

	private Date formatStartDate(Date date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatedDate = format1.format(cal.getTime());
		Date startDateFormat = format1.parse(formatedDate);
		return startDateFormat;
	}
	private Date formatEndDate(Date date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatedDate = format1.format(cal.getTime());
		Date startDateFormat = format1.parse(formatedDate);
		return startDateFormat;
	}

	/**
	 * Get all the osiResponsibilities.
	 * 
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	public List<OsiResponsibilitiesDTO> findInitialResponsibilities(Integer businessGroupId) throws BusinessException {
		log.debug("Request to get all OsiMenus");
		PageRequest pgrObj=new PageRequest(0, 8);
		List<OsiResponsibilities> osiResponsibilities = new ArrayList<>(0);
		Date currentDate=new Date();
		//osiResponsibilities = osiResponsibilityRepository.findAll();
		try {
			osiResponsibilities = osiResponsibilityRepository.findOsiResponsibilitiesBybusinessGroupId(businessGroupId, pgrObj);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<OsiResponsibilitiesDTO> osiResponsibilitiesDTOs = osiResponsibilitiesMapper
				.osiResponsibilitiesListToOsiResponsibilitiesDTOList(osiResponsibilities);
		for( OsiResponsibilitiesDTO responsibility : osiResponsibilitiesDTOs){
			if(responsibility.getEndDate().getTime()< new Date().getTime()){
				responsibility.setActive(0);
			}
		}
		return osiResponsibilitiesDTOs;
	}
	
	@Override
	public List<OsiResponsibilitiesDTO> findAll(Integer businessGroupId) throws BusinessException {
		log.debug("Request to get all OsiMenus");
		List<OsiResponsibilities> osiResponsibilities = new ArrayList<>(0);
		Date currentDate=new Date();
		//osiResponsibilities = osiResponsibilityRepository.findAll();
		try {
			//PageRequest pgrObj=new PageRequest(0, 10);
			osiResponsibilities = osiResponsibilityRepository.findOsiResponsibilitiesBybusinessGroupIdOrderByUpdatedDateDesc(businessGroupId);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<OsiResponsibilitiesDTO> osiResponsibilitiesDTOs = osiResponsibilitiesMapper
				.osiResponsibilitiesListToOsiResponsibilitiesDTOList(osiResponsibilities);
		for( OsiResponsibilitiesDTO responsibility : osiResponsibilitiesDTOs){
			if(responsibility.getEndDate().getTime()< new Date().getTime()){
				responsibility.setActive(0);
			}
		}
		return osiResponsibilitiesDTOs;
	}
	
	@Override
	public List<OsiResponsibilitiesDTO> findAllOsiResponsibilitiesList(Integer businessGroupId) throws BusinessException {
		log.debug("Request to get all OsiMenus");
		List<OsiResponsibilities> osiResponsibilities = new ArrayList<>(0);
		Date currentDate=new Date();
		//osiResponsibilities = osiResponsibilityRepository.findAll();
		try {
			osiResponsibilities = osiResponsibilityRepository.findOsiResponsibilitiesBybusinessGroupIdList(businessGroupId, currentDate);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<OsiResponsibilitiesDTO> osiResponsibilitiesDTOs = osiResponsibilitiesMapper
				.osiResponsibilitiesListToOsiResponsibilitiesDTOList(osiResponsibilities);

		return osiResponsibilitiesDTOs;
	}

	/**
	 * Get one osiResponsibility by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public OsiResponsibilitiesDTO findOne(Integer id,Integer businessGroupId) throws BusinessException {
		log.debug("Request to get OsiResponsibility : {}", id);
		OsiResponsibilities osiResponsibility = osiResponsibilityRepository.findOne(id);
		OsiResponsibilitiesDTO osiResponsibilitiesDTO = OsiReponsibilitiesMapper
				.osiResponsibilitiesToOsiResponsibilitiesDTO(osiResponsibility);
		if(osiResponsibilitiesDTO.getEndDate().getTime()< new Date().getTime()){
			osiResponsibilitiesDTO.setActive(0);
		}
		return osiResponsibilitiesDTO;
	}

	/**
	 * Delete the osiResponsibility by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	public OsiResponsibilitiesDTO delete(Integer id,Integer userId) throws BusinessException {
		log.debug("Request to delete OsiResponsibility : {}", id);
		OsiResponsibilitiesDTO osiResponsibilitiesDTO=new OsiResponsibilitiesDTO();
		OsiResponsibilities osiResponsibility=osiResponsibilityRepository.findOne(id);
		osiResponsibility.setActive(0);
		osiResponsibility.setUpdatedBy(userId);
		osiResponsibility.setUpdatedDate(new Date());
		osiResponsibility=osiResponsibilityRepository.save(osiResponsibility);
		 osiResponsibilitiesDTO = OsiReponsibilitiesMapper
				.osiResponsibilitiesToOsiResponsibilitiesDTO(osiResponsibility);
		return osiResponsibilitiesDTO;
	}

	@Override
	public List<OsiResponsibilitiesDTO> findOsiResponsibilitiesByNameOrDescription(String respName,
			String respDescription) throws BusinessException {
		// TODO Auto-generated method stub
			List<OsiResponsibilitiesDTO> osiResponsibilitiesDTOList = null;
		
		try {
			
			if(StringUtils.isNotBlank(respName) && StringUtils.isNotBlank(respDescription)){
				osiResponsibilitiesDTOList =OsiReponsibilitiesMapper.osiResponsibilitiesListToOsiResponsibilitiesDTOList(osiResponsibilityRepository.findOsiResponsibilitiesByRespNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndActiveOrderByUpdatedDateDesc(respName,respDescription,1));
						
			} else if(StringUtils.isNotBlank(respName) && StringUtils.isBlank(respDescription)){
				osiResponsibilitiesDTOList = OsiReponsibilitiesMapper.osiResponsibilitiesListToOsiResponsibilitiesDTOList(osiResponsibilityRepository.findOsiResponsibilitiesByRespNameContainingIgnoreCaseAndActiveOrderByUpdatedDateDesc(respName,1));
			} else if(StringUtils.isBlank(respName) &&StringUtils.isNotBlank(respDescription)){
				osiResponsibilitiesDTOList = OsiReponsibilitiesMapper.osiResponsibilitiesListToOsiResponsibilitiesDTOList(osiResponsibilityRepository.findOsiResponsibilitiesByDescriptionContainingIgnoreCaseAndActiveOrderByUpdatedDateDesc(respDescription,1));
			} 
			if (ObjectUtils.equals(osiResponsibilitiesDTOList, null)) {
				throw new DataAccessException("ERR_1002", null);
			}
			
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		for( OsiResponsibilitiesDTO responsibility : osiResponsibilitiesDTOList){
			if(responsibility.getEndDate().getTime()< new Date().getTime()){
				responsibility.setActive(0);
			}
		}
		 return osiResponsibilitiesDTOList;
		
	}
	@Override
	public Integer findResponsibilityByNameAndActive(String respName, Integer businessGroupId, Integer active) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiResponsibilities WHERE businessGroupId = :businessGroupId and upper(respName) =:respName";
			List list = this.entityManager.createQuery(query)
							  .setParameter("respName", respName.toUpperCase())
							  .setParameter("businessGroupId", businessGroupId)
							//  .setParameter(active, active)
							  .getResultList();
			if(list!=null && list.get(0)!=null && Integer.parseInt(list.get(0).toString())>0)
				count = 1;
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}
	@Override
	public Integer findResponsibilityByNameIdAndActive(Integer id, String respName, Integer businessGroupId, Integer active)
			throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiResponsibilities WHERE businessGroupId = :businessGroupId and upper(respName) =:respName and id !=:id";
			List list = this.entityManager.createQuery(query)
							  .setParameter("id", id)
							  .setParameter("respName", respName.toUpperCase())
							  .setParameter("businessGroupId", businessGroupId)
						//	  .setParameter("active", active)
							  .getResultList();
			if(list!=null && list.get(0)!=null && Integer.parseInt(list.get(0).toString())>0)
				count = 1;
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}
}
