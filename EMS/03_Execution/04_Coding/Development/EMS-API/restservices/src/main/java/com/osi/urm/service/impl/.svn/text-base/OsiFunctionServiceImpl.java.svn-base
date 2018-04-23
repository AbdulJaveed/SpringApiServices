package com.osi.urm.service.impl;

import java.util.ArrayList;
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

import com.osi.ems.common.CommonService;
import com.osi.urm.domain.OsiFunctions;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.mapper.OsiFunctionsMapper;
import com.osi.urm.repository.OsiFunctionRepository;
import com.osi.urm.repository.custom.OsiFunctionRepositoryCustom;
import com.osi.urm.service.OsiFunctionService;
import com.osi.urm.service.dto.OsiFunctionsDTO;

/**
 * Service Implementation for managing OsiFunction.
 */
@Service
@Transactional
public class OsiFunctionServiceImpl implements OsiFunctionService {

	private final Logger log = LoggerFactory.getLogger(OsiFunctionServiceImpl.class);
	
	@Autowired
	private OsiFunctionRepositoryCustom osiFunctionRepoCustom;
	
	@Autowired
	private OsiFunctionRepository osiFunctionRepository;
	
	@Autowired
	private OsiFunctionsMapper osiFunctionsMapper;
	
	@Autowired
	OsiFunctionService osiFunctionService;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private CommonService commonService;

	/**
	 * Save a osiFunction.
	 *
	 * @param osiFunctionDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	public OsiFunctionsDTO save(OsiFunctionsDTO osiFunctionDTO, Integer userId, Integer bussinessGroupId) throws BusinessException {
		OsiFunctions osiFunctions =null;
		/*OsiFunctions osiFunctions= osiFunctionsMapper.osiFunctionsDTOToOsiFunctions(osiFunctionDTO);
		osiFunctions.setBusinessGroupId(bussinessGroupId);
		osiFunctions.setActive(1);
		osiFunctions = osiFunctionRepository.save(osiFunctions);
		OsiFunctionsDTO osiFunctionsDTO= osiFunctionsMapper.osiUserToOsiFunctionsDTO(osiFunctions);
		return osiFunctionsDTO;*/
		OsiFunctionsDTO osiFunctionsDTO=null;
		Integer count = 0;
		try {
			osiFunctionDTO.setBusinessGroupId(bussinessGroupId);
			if (osiFunctionDTO.getId() != null) {
				osiFunctionDTO.setUpdatedBy(userId);
				//osiFunctionDTO.setUpdatedDate(new Date());
				osiFunctionDTO.setUpdatedDate(commonService.getCurrentDateinUTC());
				osiFunctions = osiFunctionsMapper.osiFunctionsDTOToOsiFunctions(osiFunctionDTO);
				if ((findFunctionsByNameId(osiFunctionDTO.getId(), osiFunctionDTO.getFuncName(),
						bussinessGroupId)) > 0) {
					throw new BusinessException("ERR_1036", "");
				} else {
					osiFunctions = osiFunctionRepository.save(osiFunctions);
					 osiFunctionsDTO= osiFunctionsMapper.osiUserToOsiFunctionsDTO(osiFunctions);
				}
			} else {
				osiFunctionDTO.setCreatedBy(userId);
				//osiFunctionDTO.setCreatedDate(new Date());
				osiFunctionDTO.setCreatedDate(commonService.getCurrentDateinUTC());
				osiFunctionDTO.setUpdatedBy(userId);
				//osiFunctionDTO.setUpdatedDate(new Date());
				osiFunctionDTO.setUpdatedDate(commonService.getCurrentDateinUTC());
				osiFunctions = osiFunctionsMapper.osiFunctionsDTOToOsiFunctions(osiFunctionDTO);
				if (osiFunctionDTO.getFuncName() != null) {
					if ((findFunctionsByName(osiFunctionDTO.getFuncName(), bussinessGroupId)) > 0) {
						throw new BusinessException("ERR_1036", "");

					} else {
						osiFunctions = osiFunctionRepository.save(osiFunctions);
						 osiFunctionsDTO= osiFunctionsMapper.osiUserToOsiFunctionsDTO(osiFunctions);
					}
				} /*else {
					osiFunctions = osiFunctionRepository.save(osiFunctions);
					 osiFunctionsDTO= osiFunctionsMapper.osiUserToOsiFunctionsDTO(osiFunctions);
				}*/
				
			}
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiFunctionsDTO;
	}
	
	public List<OsiFunctionsDTO> getall() {
		List<OsiFunctions> functionsList = osiFunctionRepository.findAll();
		List<OsiFunctionsDTO> osiFunctionsDTOList = osiFunctionsMapper.osiFunctionsListToOsiFunctionsDTOList(functionsList);
		return osiFunctionsDTOList;
	}

	/**
	 * Get all the osiFunctions.
	 * 
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	/*
	 * @Transactional(readOnly = true) public List<OsiFunctionsDTO> findAll() {
	 * log.debug("Request to get all OsiFunctions"); List<OsiFunctions>
	 * osiFunctions = osiFunctionRepository.findAll(); //return
	 * result.map(osiFunction ->
	 * osiFunctionMapper.osiFunctionToOsiFunctionsDTO(osiFunction));
	 * List<OsiFunctionsDTO> osiFunctionsDTOs = new
	 * ArrayList<OsiFunctionsDTO>(); //List<OsiFunctionsDTO> osiFunctionsDTOs1 =
	 * new ArrayList<OsiFunctionsDTO>(); for (Iterator iterator =
	 * osiFunctions.iterator(); iterator.hasNext();) { OsiFunctions
	 * osiFunctions2 = (OsiFunctions) iterator.next(); OsiFunctionsDTO
	 * osiFunctionsDTO = new OsiFunctionsDTO();
	 * osiFunctionsDTO.setId(osiFunctions2.getId());
	 * osiFunctionsDTO.setFuncName(osiFunctions2.getFuncName());
	 * osiFunctionsDTO.setFuncType(osiFunctions2.getFuncType());
	 * osiFunctionsDTO.setFuncValue(osiFunctions2.getFuncValue());
	 * 
	 * osiFunctionsDTOs.add(osiFunctionsDTO); } return osiFunctionsDTOs;
	 * 
	 * }
	 */

	/*@Transactional(readOnly = true)
	public List<OsiFunctionsDTO> findAll() {
		log.debug("Request to get all OsiFunctions");
		List<OsiFunctions> osiFunctions = osiFunctionRepository.findAll();
		// return result.map(osiFunction ->
		// osiFunctionMapper.osiFunctionToOsiFunctionsDTO(osiFunction));
		List<OsiFunctionsDTO> osiFunctionsDTOs = new ArrayList<OsiFunctionsDTO>();
	
		osiFunctionsDTOs=osiFunctionsMapper.osiFunctionsListToOsiFunctionsDTOList(osiFunctions);
		//List<OsiFunctionsDTO> osiFunctionsDTOs = osiFunctionsMapper.osiFunctionsListToOsiFunctionsDTOList(osiFunctions);
		return osiFunctionsDTOs;

	}*/

	/**
	 * Get one osiFunction by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public OsiFunctionsDTO findOne(Integer id) {
		log.debug("Request to get OsiFunction : {}", id);
		OsiFunctions osiFunctions = osiFunctionRepository.findOne(id);
		// OsiFunctionsDTO osiFunctionDTO =
		// osiFunctionMapper.osiFunctionToOsiFunctionsDTO(osiFunction);
		// return osiFunctionDTO;
		return null;
	}

	/**
	 * Delete the osiFunction by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	public void delete(Integer id) {
		log.debug("Request to delete OsiFunction : {}", id);
		osiFunctionRepository.delete(id);
	}
	
	
	@Override
	public Integer findFunctionsByNameId(Integer id, String funcName, Integer businessGroupId)
			throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiFunctions WHERE businessGroupId = :businessGroupId and upper(funcName) =:funcName and id !=:id";
			List list = this.entityManager.createQuery(query)
							  .setParameter("id", id)
							  .setParameter("funcName", funcName.toUpperCase())
							  .setParameter("businessGroupId", businessGroupId)
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
	public Integer findFunctionsByName(String funcName, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiFunctions WHERE businessGroupId = :businessGroupId and upper(funcName) =:funcName";
			List list = this.entityManager.createQuery(query)
							  .setParameter("funcName", funcName.toUpperCase())
							  .setParameter("businessGroupId", businessGroupId)
							  .getResultList();
			if(list!=null && list.get(0)!=null && Integer.parseInt(list.get(0).toString())>0)
				count = 1;
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}
	
	public Integer deleteFunction(Integer functionId, Integer businessGroupId, Integer userId) throws BusinessException {
		Integer count = 0;
		try {
			String query = "update OsiFunctions set active = 0, updatedBy = :updatedBy, updatedDate = :updatedDate where businessGroupId = :businessGroupId and id IN (:id)";
			count = this.entityManager.createQuery(query)
							  .setParameter("updatedBy", userId)
							  .setParameter("updatedDate", new Date())
							  .setParameter("businessGroupId", businessGroupId)
							  .setParameter("id", functionId)
					          .executeUpdate();
			if(count==0){
				throw new DataAccessException("ERR_1002", null);
			}
		}catch (DataAccessException e) {
//			e.printStackTrace();
			}
			return count;
			
	}

	@Override
	@Transactional(readOnly = true)
	public List<OsiFunctionsDTO> findAll(Integer businessGroupId) {
		log.debug("Request to get all OsiFunctions");
		List<OsiFunctions> osiFunctions = new ArrayList<OsiFunctions>();
		try {
			
			osiFunctions = osiFunctionRepository.findByBusinessGroupIdAndActiveOrderByUpdatedDateDesc(businessGroupId,1);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		List<OsiFunctionsDTO> osiFunctionsDTOs = new ArrayList<OsiFunctionsDTO>();
	
		osiFunctionsDTOs = osiFunctionsMapper.osiFunctionsListToOsiFunctionsDTOList(osiFunctions);
		return osiFunctionsDTOs;
	}

	@Override
	public List<OsiFunctionsDTO> search(String funcName, String funcValue) throws BusinessException, DataAccessException {
		List<OsiFunctions> osiFunctions= new ArrayList<OsiFunctions>();
			try{
				if(StringUtils.isNotBlank(funcName) && StringUtils.isNotBlank(funcValue)){
					 osiFunctions= osiFunctionRepository.findOsiFunctionsByFuncNameContainingIgnoreCaseAndFuncValueContainingIgnoreCaseOrderByUpdatedDateDesc(funcName,funcValue);
				}else{
					if(StringUtils.isNotBlank(funcName) && StringUtils.isBlank(funcValue)){
					osiFunctions= osiFunctionRepository.findOsiFUnctionByFuncNameContainingIgnoreCaseOrderByUpdatedDateDesc(funcName);
					}else{
						osiFunctions= osiFunctionRepository.findOsiFUnctionByFuncValueContainingIgnoreCaseOrderByUpdatedDateDesc(funcValue);
					}
				}
				if(ObjectUtils.equals(osiFunctions, null)){
					throw new DataAccessException("ERR_1002", null);
				}
				
			}catch(DataAccessException e){
				throw new DataAccessException("", e.getMessage()); 
			}
		
			List<OsiFunctionsDTO> osiFunctionsDTOs = osiFunctionsMapper.osiFunctionsListToOsiFunctionsDTOList(osiFunctions);
		return osiFunctionsDTOs;
	}

	@Override
	public List<OsiFunctionsDTO> findAllByRespIds(Integer bussinessGroupId,List<Integer> userRespIds) {
		List<OsiFunctions> osiFunctionList  = new ArrayList<OsiFunctions>();
		List<OsiFunctionsDTO> osiFunctionsDTOList = new ArrayList<OsiFunctionsDTO>();
		try {
			
			osiFunctionList = osiFunctionRepository.findTotalFuncName(userRespIds);
		
		for (OsiFunctions osiFunctionProjection : osiFunctionList) {
			OsiFunctionsDTO osiFunctionsDTO=new OsiFunctionsDTO();
			osiFunctionsDTO.setId(osiFunctionProjection.getId());
			osiFunctionsDTO.setFuncName(osiFunctionProjection.getFuncName());
			osiFunctionsDTOList.add(osiFunctionsDTO);
		}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return osiFunctionsDTOList;
	}
	
	@Override
	public List<OsiFunctionsDTO> findFunctionsInitially(Integer businessGroupId) throws BusinessException {
		log.debug("Request to get all OsiFunctions");
		List<OsiFunctions> osiFunctions = new ArrayList<OsiFunctions>();
		try {
			PageRequest pgrObj=new PageRequest(0, 8);
			osiFunctions = osiFunctionRepository.findByBusinessGroupIdOrderByUpdatedDateDesc(businessGroupId,pgrObj);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		List<OsiFunctionsDTO> osiFunctionsDTOs = new ArrayList<OsiFunctionsDTO>();
	
		osiFunctionsDTOs = osiFunctionsMapper.osiFunctionsListToOsiFunctionsDTOList(osiFunctions);
		return osiFunctionsDTOs;
	}

	@Override
	public List<OsiFunctionsDTO> findAllList(Integer businessGroupId) throws BusinessException {
		log.debug("Request to get all OsiFunctions");
		List<OsiFunctions> osiFunctions = new ArrayList<OsiFunctions>();
		try {
			
			osiFunctions = osiFunctionRepository.findByBusinessGroupIdOrderByUpdatedDateDesc(businessGroupId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		List<OsiFunctionsDTO> osiFunctionsDTOs = new ArrayList<OsiFunctionsDTO>();
	
		osiFunctionsDTOs = osiFunctionsMapper.osiFunctionsListToOsiFunctionsDTOList(osiFunctions);
		return osiFunctionsDTOs;
	}
	
	@Override
	public List<OsiFunctionsDTO> findUserFunctionsList(Integer userId) throws BusinessException {
		log.info("Request to get all user OsiFunctions");
		List<OsiFunctions> osiFunctions = new ArrayList<OsiFunctions>();
		try {
			
			osiFunctions = osiFunctionRepoCustom.getUserFunctions(userId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		List<OsiFunctionsDTO> osiFunctionsDTOs = new ArrayList<OsiFunctionsDTO>();
	
		osiFunctionsDTOs = osiFunctionsMapper.osiFunctionsListToOsiFunctionsDTOList(osiFunctions);
		return osiFunctionsDTOs;
	}
}
