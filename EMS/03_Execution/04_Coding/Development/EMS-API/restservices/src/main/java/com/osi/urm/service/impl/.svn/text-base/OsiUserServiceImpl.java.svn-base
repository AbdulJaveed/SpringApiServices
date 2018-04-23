package com.osi.urm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.urm.domain.OsiAttachments;
import com.osi.urm.domain.OsiRespUser;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.domain.OsiUserFuncExcl;
import com.osi.urm.domain.OsiUserOperationExcl;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.mapper.OsiAttachmentsMapper;
import com.osi.urm.mapper.OsiRespUserMapper;
import com.osi.urm.mapper.OsiUserFuncExclMapper;
import com.osi.urm.mapper.OsiUserMapper;
import com.osi.urm.mapper.OsiUserOperationExclMapper;
import com.osi.urm.repository.OsiUserRepository;
import com.osi.urm.service.OsiUserService;
import com.osi.urm.service.dto.OsiAttachmentsDTO;
import com.osi.urm.service.dto.OsiRespUserDTO;
import com.osi.urm.service.dto.OsiUserDTO;
import com.osi.urm.service.dto.OsiUserFuncExclDTO;
import com.osi.urm.service.dto.OsiUserOperationExclDTO;
import com.osi.urm.service.dto.OsiUserProjection;

/**
 * Service Implementation for managing OsiUser.
 */
@Service
@Transactional
public class OsiUserServiceImpl implements OsiUserService {

	private final Logger log = LoggerFactory.getLogger(OsiUserServiceImpl.class);

	@Autowired
	private OsiUserRepository osiUserRepository;
	
	@Autowired
	private OsiUserMapper osiUserMapper;
	
	@Autowired
	OsiRespUserMapper osiRespUserMapper;
	
	@Autowired
	OsiUserFuncExclMapper osiUserFuncExclMapper;
	
	@Autowired
	OsiUserOperationExclMapper osiUserOperationExclMapper;
	
	@Autowired
	private OsiAttachmentsMapper osiAttachmentsMapper;
	
	/**
	 * Save a osiUser.
	 *
	 * @param osiUserDTO
	 *            the entity to save
	 * @return the persisted entity
	 * @throws DataAccessException 
	 */
	public OsiUser save(OsiUserDTO osiUserDTO, Integer userId, Integer businessGroupId) throws BusinessException, DataAccessException {
		log.debug("Request to save OsiUser : {}", osiUserDTO);
		OsiUser osiUser=null;
		osiUserDTO.setActive(1);
		osiUserDTO.setBusinessGroupId(businessGroupId);
		osiUserDTO.setFullName(osiUserDTO.getFirstName()+" "+osiUserDTO.getLastName());
			if (osiUserDTO.getId() != null) {
				osiUserDTO.setUpdatedBy(userId);
				osiUserDTO.setUpdatedDate(new Date());
				osiUser = osiUserMapper.osiUserDTOToOsiUser(osiUserDTO);
			    osiUserRepository.updateUser(osiUser);
			} else {
				osiUserDTO.setHasDefaultPwd(0);
				osiUserDTO.setCreatedDate(new Date());
				osiUserDTO.setCreatedBy(userId);
				osiUserDTO.setUpdatedDate(new Date());
				osiUserDTO.setUpdatedBy(userId);
				osiUser = osiUserMapper.osiUserDTOToOsiUser(osiUserDTO);
				osiUser=osiUserRepository.save(osiUser);
			}
		
		return osiUser;
	}

	/**
	 * Get all the osiUsers.
	 * 
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	
	@Transactional(readOnly = true)
	public List<OsiUserDTO> findUserInitially(Integer businessGroupId) throws BusinessException {
		log.debug("Request to get all OsiUsers");
		PageRequest pgrObj=new PageRequest(0, 8);
		List<OsiUserDTO> OsiUserDTOList = new ArrayList<>();
		try {
			List<OsiUserProjection> OsiUserList = osiUserRepository.findByBusinessGroupIdAndActiveOrderByUpdatedDateDesc(businessGroupId,1,OsiUserProjection.class, pgrObj);
			if (OsiUserList == null || (OsiUserList != null && OsiUserList.size() == 0)) {
				throw new DataAccessException("ERR_1002", null);
			}
			for (OsiUserProjection osiUserProjection : OsiUserList) {
				OsiUserDTO osiUserDTO=new OsiUserDTO();
				osiUserDTO.setId(osiUserProjection.getUserId());
				osiUserDTO.setUserName(osiUserProjection.getUserName());
				osiUserDTO.setEmailId(osiUserProjection.getEmailId());
				osiUserDTO.setEmpNumber(osiUserProjection.getEmpNumber());
				osiUserDTO.setFirstName(osiUserProjection.getFirstName());
				osiUserDTO.setLastName(osiUserProjection.getLastName());
				osiUserDTO.setActive(osiUserProjection.getActive());
				//osiUserDTO.setFullName(osiUserProjection.getFullName());
				OsiUserDTOList.add(osiUserDTO);
			}
			//OsiUserDTOList = osiUserMapper.osiUserListToOsiUserDTOList(OsiUserList);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return OsiUserDTOList;
	}
	
	@Transactional(readOnly = true)
	public List<OsiUserDTO> findAll(Integer businessGroupId) throws BusinessException {
		log.debug("Request to get all OsiUsers");
		List<OsiUserDTO> OsiUserDTOList = new ArrayList<>();
		try {
			List<OsiUserProjection> OsiUserList = osiUserRepository.findByBusinessGroupIdAndActiveOrderByUpdatedDateDesc(businessGroupId,1,OsiUserProjection.class);
			if (OsiUserList == null || (OsiUserList != null && OsiUserList.size() == 0)) {
				throw new DataAccessException("ERR_1002", null);
			}
			for (OsiUserProjection osiUserProjection : OsiUserList) {
				OsiUserDTO osiUserDTO=new OsiUserDTO();
				osiUserDTO.setId(osiUserProjection.getUserId());
				osiUserDTO.setUserName(osiUserProjection.getUserName());
				osiUserDTO.setEmailId(osiUserProjection.getEmailId());
				osiUserDTO.setEmpNumber(osiUserProjection.getEmpNumber());
				osiUserDTO.setFirstName(osiUserProjection.getFirstName());
				osiUserDTO.setLastName(osiUserProjection.getLastName());
				osiUserDTO.setActive(osiUserProjection.getActive());
				//osiUserDTO.setFullName(osiUserProjection.getFullName());
				OsiUserDTOList.add(osiUserDTO);
			}
			//OsiUserDTOList = osiUserMapper.osiUserListToOsiUserDTOList(OsiUserList);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return OsiUserDTOList;
	}

	@Transactional(readOnly = true)
	public List<OsiUserDTO> findAllActiveUsers(Integer businessGroupId) throws BusinessException {
		log.debug("Request to get all active OsiUsers");
		List<OsiUserDTO> OsiUserDTOList = new ArrayList<>();
		try {
			//List<OsiUserProjection> OsiUserList = osiUserRepository.findByBusinessGroupIdAndActiveAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByUpdatedDateDesc(businessGroupId,1,new Date(),new Date(),OsiUserProjection.class);
			List<OsiUser> OsiUserList=osiUserRepository.findByBusinessGroupIdAndActiveOrderByFirstName(businessGroupId,1);
			if (OsiUserList == null || (OsiUserList != null && OsiUserList.size() == 0)) {
				throw new DataAccessException("ERR_1002", null);
			}
			for (OsiUser osiUserProjection : OsiUserList) {
				OsiUserDTO osiUserDTO=new OsiUserDTO();
				osiUserDTO.setId(osiUserProjection.getUserId());
				osiUserDTO.setUserName(osiUserProjection.getUserName());
				osiUserDTO.setEmailId(osiUserProjection.getEmailId());
				osiUserDTO.setEmpNumber(osiUserProjection.getEmpNumber());
				osiUserDTO.setFirstName(osiUserProjection.getFirstName());
				osiUserDTO.setLastName(osiUserProjection.getLastName());
				osiUserDTO.setActive(osiUserProjection.getActive());
				//osiUserDTO.setFullName(osiUserProjection.getFullName());
				OsiUserDTOList.add(osiUserDTO);
			}
			//OsiUserDTOList = osiUserMapper.osiUserListToOsiUserDTOList(OsiUserList);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return OsiUserDTOList;
	}
	
	/**
	 * Get one osiUser by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 * @throws BusinessException 
	 */
	@Transactional(readOnly = true)
	public OsiUserDTO findOne(Integer id, Integer businessGroupId) throws BusinessException {
		log.debug("Request to get OsiUser : {}", id);
		OsiUser osiUser = osiUserRepository.findUserByUserIdAndBusinessGroupIdAndActiveOrderByUpdatedDateDesc(id, businessGroupId, 1);
		OsiUserDTO osiUserDTO = osiUserMapper.osiUserToOsiUserDTO(osiUser);
		return osiUserDTO;
	}

	/**
	 * Delete the osiUser by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	public Integer delete(List<Integer> id, Integer businessGroupId,Integer userId) throws BusinessException{
		Integer count = 0;
		try {
			count = osiUserRepository.deleteUser(id, businessGroupId, userId);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return count;
	}

	@Override
	public OsiUserDTO findUserById(Integer userId, Integer businessGroupId) throws BusinessException {
		
		OsiUserDTO osiUserDTO =  new OsiUserDTO();
		log.debug("Request to get findUserById : {}", userId);
		OsiUser osiUser;
		try {
			osiUser = osiUserRepository.findUserById(userId, businessGroupId);
				osiUserDTO  = osiUserMapper.osiUserToOsiUserDTO(osiUser);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		}
		return osiUserDTO;
	}

	@Override
	public List<OsiRespUserDTO> findUserResponsibilities(Integer userId, Integer businessGroupId)
			throws BusinessException {
		
		List<OsiRespUser> list = new ArrayList<>();
		List<OsiRespUserDTO> listDTO = new ArrayList<>();
		try{
			list = osiUserRepository.findUserResponsibilities(userId, businessGroupId);
			listDTO = osiRespUserMapper.osiUserListToOsiUserDTOList(list);
		}catch(DataAccessException e){
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		}
		return listDTO;
	}
	
	@Override
	public List<OsiUserFuncExclDTO> getUserFunctionExclusions(Integer userId, Integer businessGroupId)
			throws BusinessException {
		
		List<OsiUserFuncExcl> list = new ArrayList<>();
		List<OsiUserFuncExclDTO> listDTO = new ArrayList<>();
		try{
			list = osiUserRepository.getUserFunctionExclusions(userId, businessGroupId);
			listDTO = osiUserFuncExclMapper.osiUserFuncExclListToOsiUserFuncExclDTOList(list);
		}catch(DataAccessException e){
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		}
		return listDTO;
	}

	@Override
	public List<OsiUserOperationExclDTO> getUserOperationExclusions(Integer userId, Integer businessGroupId)
			throws BusinessException {
		List<OsiUserOperationExcl> list = new ArrayList<>();
		List<OsiUserOperationExclDTO> listDTO = new ArrayList<>();
		try{
			list = osiUserRepository.getUserOperationExclusions(userId, businessGroupId);
			listDTO = osiUserOperationExclMapper.osiUserListToOsiUserDTOList(list);
		}catch(DataAccessException e){
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		}
		return listDTO;
	}

	@Override
	public List<OsiAttachmentsDTO> getUserAttachments(Integer userId, Integer businessGroupId)
			throws BusinessException {

		List<OsiAttachments> list = new ArrayList<>();
		List<OsiAttachmentsDTO> listDTO = new ArrayList<>();
		try{
			list = osiUserRepository.getUserAttachments(userId, businessGroupId);
			for(OsiAttachments attachment:list){
				OsiAttachmentsDTO attach = new OsiAttachmentsDTO();
				attach= osiAttachmentsMapper.osiUserToOsiUserDTO(attachment);
				listDTO.add(attach);
			}
		}catch(DataAccessException e){
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		}
		return listDTO;
	}
	
	@Override
	public OsiUser updateUserProfile(OsiUserDTO osiUserDTO,Integer businessGroupId)
			throws BusinessException {
		
		log.debug("Request to update User Profile : {}", osiUserDTO);
		OsiUser osiUser=null;
		try {
			  osiUser = new OsiUser();
			  osiUser.setUserId(osiUserDTO.getId());
			  osiUser.setUpdatedBy(osiUserDTO.getId());
			  osiUser.setUpdatedDate(new Date());
			  osiUser.setFullName(osiUserDTO.getFirstName()+" "+osiUserDTO.getLastName());
			  osiUser.setPassword(osiUserDTO.getPassword());
			  osiUser.setMobileNumber(osiUserDTO.getMobileNumber());
			  osiUser.setBusinessGroupId(osiUserDTO.getBusinessGroupId());
			  Set<OsiAttachments> osiAttachmentsSet = new HashSet<OsiAttachments>(0);
			  if(osiUserDTO.getOsiAttachmentses()!=null && !osiUserDTO.getOsiAttachmentses().isEmpty()){
				  
				  for (OsiAttachmentsDTO osiAttachmentsDTO : osiUserDTO.getOsiAttachmentses()) {
						OsiAttachments osiAttachments = new OsiAttachments();
						osiAttachments.setId(osiAttachmentsDTO.getId() );
						osiAttachments.setOsiUser(osiUser);
						osiAttachments.setAttachment(osiAttachmentsDTO.getAttachment());
						osiAttachments.setBusinessGroupId(osiUserDTO.getBusinessGroupId());
						osiAttachments.setCreatedBy( osiUserDTO.getCreatedBy() );
						osiAttachments.setCreatedDate( osiUserDTO.getCreatedDate() );
						osiAttachments.setUpdatedBy( osiUserDTO.getUpdatedBy() );
						osiAttachments.setUpdatedDate( osiUserDTO.getUpdatedDate() );
					    osiAttachmentsSet.add(osiAttachments);
					}
					osiUser.setOsiAttachmentses(osiAttachmentsSet);
			  }
			  
			  
				osiUser=osiUserRepository.updateUserProfile(osiUser);
		
		
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiUser;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<OsiUserDTO> findUser(OsiUserDTO osiUserDTO,Integer businessGroupId) throws BusinessException {
		List<OsiUserDTO> osiUserDTOList = null;
		List<OsiUser> userList = null;
		String userName=null;
		String empNumber =null;
		String emailId =null;
		String firstName = null;
		String lastName = null;
		
		if(osiUserDTO.getUserName()!=null && osiUserDTO.getUserName()!="")
		{
			userName=osiUserDTO.getUserName();
		}
		if(osiUserDTO.getEmpNumber()!=null)
		{
		  empNumber=osiUserDTO.getEmpNumber();
		}
		if(osiUserDTO.getEmailId()!=null)
		{
			 emailId=osiUserDTO.getEmailId();
		}
		if(osiUserDTO.getEmpNumber()!=null)
		{
			empNumber=osiUserDTO. getEmpNumber();
		}
		if(osiUserDTO.getFirstName()!=null)
		{
			firstName=osiUserDTO.getFirstName();
		}
		if(osiUserDTO.getLastName()!=null)
		{
			lastName=osiUserDTO.getLastName();
		}
		
		
		try 
		{
			 userList=new ArrayList<>();
			 userList=osiUserRepository.searchUser(businessGroupId, userName, empNumber, emailId, firstName, lastName);
			 osiUserDTOList=osiUserMapper.osiUserListToOsiUserDTOList(userList);
		} 
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		 return osiUserDTOList;
	}

	@Override
	public Integer updatePassword(Integer userId, Integer businessGroupId,String password)	throws BusinessException {
		Integer count = null;
		OsiUser osiUser=new OsiUser();
		osiUser.setUpdatedBy(userId);
		osiUser.setBusinessGroupId(businessGroupId);
		osiUser.setUserId(userId);
		osiUser.setPassword(password);
		osiUser.setHasDefaultPwd(1);
		try {
			count=osiUserRepository.updatePassword(osiUser);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public OsiUser updateUserPassword(Integer businessGroupId, Integer logedInUserId, Integer id,
			String encodedPassword) throws BusinessException {

		OsiUser osiUser = new OsiUser();
		try {
			osiUser.setUserId(id);
			osiUser.setBusinessGroupId(businessGroupId);
			osiUser.setUpdatedBy(logedInUserId);
			osiUser.setPassword(encodedPassword);
			osiUser.setHasDefaultPwd(0);
			osiUser = osiUserRepository.updateResetUserPassword(osiUser);

		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiUser;
	}
	
}
