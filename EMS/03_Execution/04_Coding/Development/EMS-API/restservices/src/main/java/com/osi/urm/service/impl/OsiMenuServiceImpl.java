package com.osi.urm.service.impl;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.urm.domain.OsiMenus;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.mapper.OsiMenusMapper;
import com.osi.urm.repository.OsiMenuRepository;
import com.osi.urm.service.OsiMenuService;
import com.osi.urm.service.dto.OsiMenusDTO;

/**
 * Service Implementation for managing OsiMenu.
 */
@Service
@Transactional
public class OsiMenuServiceImpl implements OsiMenuService {

	private final Logger log = LoggerFactory.getLogger(OsiMenuServiceImpl.class);

	@Autowired
	private OsiMenuRepository osiMenuRepository;

	@Autowired
	private OsiMenusMapper osiMenusMapper;
	
	@Autowired
	private CommonService commonService;

	/**
	 * Save a osiMenu.
	 *
	 * @param osiMenusDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	public Integer save(OsiMenusDTO osiMenusDTO, Integer userId, Integer businessGroupId) throws BusinessException {
		log.debug("Request to save OsiMenu : {}", osiMenusDTO);
		OsiMenus osiMenus = null;
		Integer count = 0;

		try {
			osiMenusDTO.setBusinessGroupId(businessGroupId);
			if (osiMenusDTO.getId() != null) {
				osiMenusDTO.setUpdatedBy(userId);
				//osiMenusDTO.setUpdatedDate(new Date());
				osiMenusDTO.setUpdatedDate(commonService.getCurrentDateinUTC());
				osiMenus = osiMenusMapper.osiMenusDTOToOsiMenus(osiMenusDTO);
				if (osiMenuRepository.findMenusByNameId(osiMenusDTO.getId(), osiMenusDTO.getMenuName(),
						businessGroupId) > 0) {
					throw new BusinessException("ERR_1032", null);
				} else {
					count = osiMenuRepository.updateMenu(osiMenus);
				}
			} else {
				osiMenusDTO.setCreatedBy(userId);
				//osiMenusDTO.setCreatedDate(new Date());
				osiMenusDTO.setCreatedDate(commonService.getCurrentDateinUTC());
				//osiMenusDTO.setUpdatedDate(new Date());
				osiMenusDTO.setUpdatedDate(commonService.getCurrentDateinUTC());
				osiMenus = osiMenusMapper.osiMenusDTOToOsiMenus(osiMenusDTO);
				if (osiMenusDTO.getMenuName() != null) {
					if (osiMenuRepository.findMenusByName(osiMenusDTO.getMenuName(), businessGroupId) > 0) {
						throw new BusinessException("ERR_1032", null);

					} else {
						osiMenus = osiMenuRepository.save(osiMenus);
					}
				} else {
					osiMenus = osiMenuRepository.save(osiMenus);
				}
				if (osiMenus != null && osiMenus.getId() != 0) {
					count = 1;
				}
			}
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return count;
	}
	
	/**
	 * Get 10 Menus.
	 * 
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	public List<OsiMenusDTO> findMenusInitially(Integer businessGroupId) throws BusinessException {
		PageRequest pgrObj=new PageRequest(0, 8);
		List<OsiMenusDTO> osiMenusDTOList = null;
		try {
			List<OsiMenus> osiMenusList = osiMenuRepository.findOsiMenusByBusinessGroupId(businessGroupId, pgrObj);
			if (osiMenusList == null || (osiMenusList != null && osiMenusList.size() == 0)) {
				throw new DataAccessException("ERR_1002", null);
			}
			osiMenusDTOList = osiMenusMapper.osiMenusListToOsiMenusDTOList(osiMenusList);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiMenusDTOList;
	}

	/**
	 * Get all the osiMenus.
	 * 
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	public List<OsiMenusDTO> findAll(Integer businessGroupId) throws BusinessException {
		List<OsiMenusDTO> osiMenusDTOList = null;
		try {
			List<OsiMenus> osiMenusList = osiMenuRepository.findOsiMenusByBusinessGroupId(businessGroupId);
			if (osiMenusList == null || (osiMenusList != null && osiMenusList.size() == 0)) {
				throw new DataAccessException("ERR_1002", null);
			}
			osiMenusDTOList = osiMenusMapper.osiMenusListToOsiMenusDTOList(osiMenusList);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiMenusDTOList;
	}

	/**
	 * Get one osiMenu by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public OsiMenusDTO findOne(Integer id, Integer businessGroupId) throws BusinessException {
		OsiMenusDTO osiMenusDTO = null;
		try {
			OsiMenus osiMenus = osiMenuRepository.findOsiMenusByBusinessGroupIdAndId(id, businessGroupId);
			if (osiMenus == null) {
				throw new DataAccessException("ERR_1002", null);
			}
			osiMenusDTO = osiMenusMapper.OsiMenusToOsiMenusDTO(osiMenus);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiMenusDTO;
	}

	/**
	 * Delete the osiMenu by id.
	 *
	 * @param id
	 *            the id of the entity
	 */

	public Integer deleteMenu(Integer OsiMenuId, Integer businessGroupId, Integer userId) throws BusinessException {
		Integer count = 0;
		try {
			count = osiMenuRepository.deleteMenu(OsiMenuId, businessGroupId, userId);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return count;
	}

	@Override
	public Integer deleteMenus(List<Integer> menuIds, Integer businessGroupId, Integer userId) throws BusinessException {
		Integer count = 0;
    	try {
    		count = osiMenuRepository.deleteMenus(menuIds, businessGroupId, userId);
    				    				
    				
		}  catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
    	return count;
	}


	@Override
	public List<OsiMenusDTO> findOsiMenusByMenuNameOrDescription(String menuName, String menuDescription)
			throws BusinessException {
		// TODO Auto-generated method stub
		
		List<OsiMenusDTO> osiMenusDTOList = null;
		
		try {
			if(StringUtils.isNotBlank(menuName) && StringUtils.isNotBlank(menuDescription)){
				osiMenusDTOList =osiMenusMapper.osiMenusListToOsiMenusDTOList(osiMenuRepository.findOsiMenusByMenuNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByUpdatedDateDesc(menuName,menuDescription));
						
			} else if(StringUtils.isNotBlank(menuName) && StringUtils.isBlank(menuDescription)){
				osiMenusDTOList = osiMenusMapper.osiMenusListToOsiMenusDTOList(osiMenuRepository.findOsiMenusByMenuNameContainingIgnoreCaseOrderByUpdatedDateDesc(menuName));
			} else if(StringUtils.isBlank(menuName) &&StringUtils.isNotBlank(menuDescription)){
				osiMenusDTOList = osiMenusMapper.osiMenusListToOsiMenusDTOList(osiMenuRepository.findOsiMenusByDescriptionContainingIgnoreCaseOrderByUpdatedDateDesc(menuDescription));
			} 
			
		/*	if (osiMenusDTOList == null || (osiMenusDTOList != null && osiMenusDTOList.size() == 0)) {
				throw new DataAccessException("ERR_1002", null);
			}*/
			if(ObjectUtils.equals(osiMenusDTOList, null)){
				throw new BusinessException("ERR_1002", null);
			}
//		} catch (DataAccessException e) {
//			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		 return osiMenusDTOList;
		
	}

	@Override
	public List<OsiMenusDTO> findAllForList(Integer businessGroupId) throws BusinessException {
		List<OsiMenusDTO> osiMenusDTOList = null;
		try {
			
			List<OsiMenus> osiMenusList = osiMenuRepository.findAllOsiMenusByBusinessGroupIdOrderByUpdatedDate(businessGroupId);
			if (osiMenusList == null || (osiMenusList != null && osiMenusList.size() == 0)) {
				throw new DataAccessException("ERR_1002", null);
			}
			osiMenusDTOList = osiMenusMapper.osiMenusListToOsiMenusDTOList(osiMenusList);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiMenusDTOList;
	}

	
}
