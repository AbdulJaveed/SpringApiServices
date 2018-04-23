package com.osi.urm.mapper.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.urm.domain.OsiAttachments;
import com.osi.urm.domain.OsiRespUser;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.domain.OsiUserFuncExcl;
import com.osi.urm.domain.OsiUserOperationExcl;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.mapper.OsiAttachmentsMapper;
import com.osi.urm.mapper.OsiFunctionsMapper;
import com.osi.urm.mapper.OsiOperationsMapper;
import com.osi.urm.mapper.OsiReponsibilitiesMapper;
import com.osi.urm.mapper.OsiRespUserMapper;
import com.osi.urm.mapper.OsiUserFuncExclMapper;
import com.osi.urm.mapper.OsiUserMapper;
import com.osi.urm.mapper.OsiUserOperationExclMapper;
import com.osi.urm.service.dto.OsiAttachmentsDTO;
import com.osi.urm.service.dto.OsiRespUserDTO;
import com.osi.urm.service.dto.OsiUserDTO;
import com.osi.urm.service.dto.OsiUserFuncExclDTO;
import com.osi.urm.service.dto.OsiUserOperationExclDTO;
@Component
public class OsiUserMapperImpl implements OsiUserMapper {

	@Autowired
	private OsiAttachmentsMapper osiAttachmentsMapper;
	@Autowired
	private OsiUserFuncExclMapper osiUserFuncExclMapper;
	@Autowired
	private OsiUserOperationExclMapper osiUserOperationExclMapper;
	@Autowired
	private OsiRespUserMapper osiRespUserMapper;
	
	@Autowired
	private OsiReponsibilitiesMapper osiReponsibilitiesMapper;
	
	@Autowired
    private OsiFunctionsMapper osiFunctionsMapper;
	
	@Autowired
    private OsiOperationsMapper osiOperationsMapper;

	
	@Autowired
	private OsiUserMapper osiUserMapper;
	
	@Override
	public OsiUserDTO osiUserToOsiUserDTO(OsiUser osiUser) throws BusinessException {
		if (osiUser == null) {
			return null;
		}

		OsiUserDTO osiUserDTO = new OsiUserDTO();

		osiUserDTO.setId(osiUser.getUserId());
		osiUserDTO.setActive(osiUser.getActive());
		osiUserDTO.setUserName(osiUser.getUserName());
		osiUserDTO.setPassword(osiUser.getPassword());
		osiUserDTO.setFirstName(osiUser.getFirstName());
		osiUserDTO.setLastName(osiUser.getLastName());
		osiUserDTO.setEmailId(osiUser.getEmailId());
		osiUserDTO.setMobileNumber(osiUser.getMobileNumber());
		osiUserDTO.setStartDate(osiUser.getStartDate());
		osiUserDTO.setFullName(osiUser.getFullName());
		osiUserDTO.setEndDate(osiUser.getEndDate());
		osiUserDTO.setCreatedBy(osiUser.getCreatedBy());
		osiUserDTO.setCreatedDate(osiUser.getCreatedDate());
		osiUserDTO.setUpdatedBy(osiUser.getUpdatedBy());
		osiUserDTO.setUpdatedDate(osiUser.getUpdatedDate());
		osiUserDTO.setHasDefaultPwd(osiUser.getHasDefaultPwd());

		Set<OsiUserFuncExclDTO> osiUserFuncExclDTOSet = osiUserFuncExclSetToOsiUserFuncExclDTOSet(osiUser.getOsiUserFuncExcls());
		if (osiUserFuncExclDTOSet != null) {
			osiUserDTO.setOsiUserFuncExcls(osiUserFuncExclDTOSet);
		}
		Set<OsiAttachmentsDTO> osiAttachmentsDTOSet = osiAttachmentsSetToOsiAttachmentsDTOSet(osiUser.getOsiAttachmentses());
		if (osiAttachmentsDTOSet != null) {
			osiUserDTO.setOsiAttachmentses(osiAttachmentsDTOSet);
		}
		Set<OsiRespUserDTO> osiRespUserDTOSet = osiRespUserSetToOsiRespUserDTOSet(osiUser.getOsiRespUsers());
		if (osiRespUserDTOSet != null) {
			osiUserDTO.setOsiRespUsers(osiRespUserDTOSet);
		}
		Set<OsiUserOperationExclDTO> osiUserOperationExclDTOSet = osiUserOperationExclSetToOsiUserOperationExclDTOSet(
				osiUser.getOsiUserOperationExcls());
		if (osiUserOperationExclDTOSet != null) {
			osiUserDTO.setOsiUserOperationExcls(osiUserOperationExclDTOSet);
		}
		
		osiUserDTO.setEmpNumber(osiUser.getEmpNumber());
		osiUserDTO.setBusinessGroupId(osiUser.getBusinessGroupId());
		

		return osiUserDTO;
	}

	@Override
	public List<OsiUserDTO> osiUserListToOsiUserDTOList(List<OsiUser> osiUsers) throws BusinessException {
		if (osiUsers == null) {
			return null;
		}

		List<OsiUserDTO> list = new ArrayList<OsiUserDTO>();
		for (OsiUser osiUser : osiUsers) {
			list.add(osiUserToOsiUserDTO(osiUser));
		}

		return list;
	}
	
	@Override
	public OsiUser osiUserDTOToOsiUser(OsiUserDTO osiUserDTO) {
		if (osiUserDTO == null) {
			return null;
		}

		OsiUser osiUser = new OsiUser();
		
		osiUser.setUserId(osiUserDTO.getId());
		osiUser.setUserName(osiUserDTO.getUserName());
		osiUser.setActive(osiUserDTO.getActive());
		osiUser.setPassword(osiUserDTO.getPassword());
		osiUser.setFirstName(osiUserDTO.getFirstName());
		osiUser.setLastName(osiUserDTO.getLastName());
		osiUser.setEmailId(osiUserDTO.getEmailId());
		osiUser.setEmpNumber(osiUserDTO.getEmpNumber());
		osiUser.setMobileNumber(osiUserDTO.getMobileNumber());
		osiUser.setStartDate(osiUserDTO.getStartDate());
		osiUser.setFullName(osiUserDTO.getFullName());
		osiUser.setEndDate(osiUserDTO.getEndDate());
		osiUser.setCreatedBy(osiUserDTO.getCreatedBy());
		osiUser.setCreatedDate(osiUserDTO.getCreatedDate());
		osiUser.setUpdatedBy(osiUserDTO.getUpdatedBy());
		osiUser.setUpdatedDate(osiUserDTO.getUpdatedDate());
		osiUser.setBusinessGroupId(osiUserDTO.getBusinessGroupId());
		osiUser.setHasDefaultPwd(osiUserDTO.getHasDefaultPwd());
		Set<OsiRespUser> osiRespUserSet = new HashSet<OsiRespUser>(0);
		Set<OsiUserFuncExcl> osiUserFuncExclSet = new HashSet<OsiUserFuncExcl>(0);
		Set<OsiUserOperationExcl> osiUserOperationExclSet = new HashSet<OsiUserOperationExcl>(0);
		for (OsiRespUserDTO osiRespUserDTO : osiUserDTO.getOsiRespUsers()) {
			
			OsiRespUser osiRespUser = new OsiRespUser();
			osiRespUser.setId(osiRespUserDTO.getId() );
			osiRespUser.setEmployeeId(osiRespUser.getEmployeeId());;
			osiRespUser.setOsiResponsibilities(osiReponsibilitiesMapper.osiResponsibilitiesDTOToOsiResponsibilities( osiRespUserDTO.getOsiResponsibilities() ) );
			osiRespUser.setStartDate( osiRespUserDTO.getStartDate() );
			osiRespUser.setEndDate( osiRespUserDTO.getEndDate() );
			osiRespUser.setDefaultResp( osiRespUserDTO.getDefaultResp() );
			osiRespUser.setBusinessGroupId(osiUserDTO.getBusinessGroupId());
			osiRespUser.setCreatedBy( osiUserDTO.getCreatedBy() );
			osiRespUser.setCreatedDate( osiUserDTO.getCreatedDate() );
			osiRespUser.setUpdatedBy( osiUserDTO.getUpdatedBy() );
			osiRespUser.setUpdatedDate( osiUserDTO.getUpdatedDate() );
			osiRespUserSet.add(osiRespUser);
		}
		osiUser.setOsiRespUsers(osiRespUserSet);
		
		for (OsiUserFuncExclDTO osiUserFuncExclDTO : osiUserDTO.getOsiUserFuncExcls()) {
			OsiUserFuncExcl osiUserFuncExcl = new OsiUserFuncExcl();
			osiUserFuncExcl.setId(osiUserFuncExclDTO.getId() );
			osiUserFuncExcl.setEmployeeId(osiUserFuncExclDTO.getEmployeeId());;
			osiUserFuncExcl.setBusinessGroupId(osiUserDTO.getBusinessGroupId());
			osiUserFuncExcl.setCreatedBy( osiUserDTO.getCreatedBy() );
			osiUserFuncExcl.setCreatedDate( osiUserDTO.getCreatedDate() );
			osiUserFuncExcl.setUpdatedBy( osiUserDTO.getUpdatedBy() );
			osiUserFuncExcl.setUpdatedDate( osiUserDTO.getUpdatedDate() );
			osiUserFuncExcl.setOsiFunctions( osiFunctionsMapper.osiFunctionsDTOToOsiFunctions( osiUserFuncExclDTO.getOsiFunctions() ));
			osiUserFuncExclSet.add(osiUserFuncExcl);
		}
		osiUser.setOsiUserFuncExcls(osiUserFuncExclSet);
		
		for (OsiUserOperationExclDTO osiUserOperationExclDTO : osiUserDTO.getOsiUserOperationExcls()) {
			OsiUserOperationExcl osiUserOperationExcl = new OsiUserOperationExcl();
			osiUserOperationExcl.setId(osiUserOperationExclDTO.getId() );
			/*osiUserOperationExcl.setOsiUser(osiUser);*/
			osiUserOperationExcl.setEmployeeId(osiUserOperationExclDTO.getEmployeeId());
			osiUserOperationExcl.setOsiFunctions( osiFunctionsMapper.osiFunctionsDTOToOsiFunctions( osiUserOperationExclDTO.getOsiFunctions() ) );
			osiUserOperationExcl.setOsiOperations( osiOperationsMapper.osiOperationsDTOToOsiOperations( osiUserOperationExclDTO.getOsiOperations()));
			osiUserOperationExcl.setBusinessGroupId(osiUserDTO.getBusinessGroupId());
			osiUserOperationExcl.setCreatedBy( osiUserDTO.getCreatedBy() );
			osiUserOperationExcl.setCreatedDate( osiUserDTO.getCreatedDate() );
			osiUserOperationExcl.setUpdatedBy( osiUserDTO.getUpdatedBy() );
			osiUserOperationExcl.setUpdatedDate( osiUserDTO.getUpdatedDate() );
			osiUserOperationExclSet.add(osiUserOperationExcl);
		}
		osiUser.setOsiUserOperationExcls(osiUserOperationExclSet);
		
		return osiUser;
	}

	@Override
	public List<OsiUser> osiUserDTOListToOsiUserList(List<OsiUserDTO> osiUserDTO) {
		if (osiUserDTO == null) {
			return null;
		}

		List<OsiUser> list = new ArrayList<OsiUser>();
		for (OsiUserDTO osiUserDTO_ : osiUserDTO) {
			list.add(osiUserDTOToOsiUser(osiUserDTO_));
		}

		return list;
	}

	
	/*########################### Domain to DTO ############################################################*/
	
	protected Set<OsiUserFuncExclDTO> osiUserFuncExclSetToOsiUserFuncExclDTOSet(Set<OsiUserFuncExcl> set) {
		if (set == null) {
			return null;
		}

		Set<OsiUserFuncExclDTO> set_ = new HashSet<OsiUserFuncExclDTO>();
		for (OsiUserFuncExcl osiUserFuncExcl : set) {
			set_.add(osiUserFuncExclMapper.osiUserFuncExclToOsiUserFuncExclDTO(osiUserFuncExcl));
		}

		return set_;
	}

	protected Set<OsiAttachmentsDTO> osiAttachmentsSetToOsiAttachmentsDTOSet(Set<OsiAttachments> set) {
		if (set == null) {
			return null;
		}

		Set<OsiAttachmentsDTO> set_ = new HashSet<OsiAttachmentsDTO>();
		for (OsiAttachments osiAttachments : set) {
			set_.add(osiAttachmentsMapper.osiUserToOsiUserDTO(osiAttachments));
		}

		return set_;
	}

	protected Set<OsiRespUserDTO> osiRespUserSetToOsiRespUserDTOSet(Set<OsiRespUser> set) throws BusinessException {
		if (set == null) {
			return null;
		}

		Set<OsiRespUserDTO> set_ = new HashSet<OsiRespUserDTO>();
		for (OsiRespUser osiRespUser : set) {
			set_.add(osiRespUserMapper.osiUserToOsiUserDTO(osiRespUser));
		}

		return set_;
	}

	protected Set<OsiUserOperationExclDTO> osiUserOperationExclSetToOsiUserOperationExclDTOSet(
			Set<OsiUserOperationExcl> set) {
		if (set == null) {
			return null;
		}

		Set<OsiUserOperationExclDTO> set_ = new HashSet<OsiUserOperationExclDTO>();
		for (OsiUserOperationExcl osiUserOperationExcl : set) {
			set_.add(osiUserOperationExclMapper.osiUserToOsiUserDTO(osiUserOperationExcl));
		}

		return set_;
	}
	
	/*############################################ DTO to Domain ##########################################################################*/
	
	// Unused code need to check and remove :: Ravitej Reddy
	
	protected Set<OsiUserFuncExcl> osiUserFuncExclDTOSetToOsiUserFuncExclSet(Set<OsiUserFuncExclDTO> set) throws BusinessException {
		if (set == null) {
			return null;
		}

		Set<OsiUserFuncExcl> set_ = new HashSet<OsiUserFuncExcl>();
		for (OsiUserFuncExclDTO osiUserFuncExclDTO : set) {
			set_.add(osiUserFuncExclMapper.osiUserFuncExclDTOToOsiUserFuncExcl(osiUserFuncExclDTO));
		}

		return set_;
	}

	protected Set<OsiAttachments> osiAttachmentsDTOSetToOsiAttachmentsSet(Set<OsiAttachmentsDTO> set) {
		if (set == null) {
			return null;
		}

		Set<OsiAttachments> set_ = new HashSet<OsiAttachments>();
		for (OsiAttachmentsDTO osiAttachmentsDTO : set) {
			set_.add(osiAttachmentsMapper.osiUserDTOToOsiUser(osiAttachmentsDTO));
		}

		return set_;
	}

	protected Set<OsiRespUser> osiRespUserDTOSetToOsiRespUserSet(Set<OsiRespUserDTO> set) {
		if (set == null) {
			return null;
		}

		Set<OsiRespUser> set_ = new HashSet<OsiRespUser>();
		for (OsiRespUserDTO osiRespUserDTO : set) {
			set_.add(osiRespUserMapper.osiUserDTOToOsiUser(osiRespUserDTO));
		}

		return set_;
	}

	protected Set<OsiUserOperationExcl> osiUserOperationExclDTOSetToOsiUserOperationExclSet(
			Set<OsiUserOperationExclDTO> set) {
		if (set == null) {
			return null;
		}

		Set<OsiUserOperationExcl> set_ = new HashSet<OsiUserOperationExcl>();
		for (OsiUserOperationExclDTO osiUserOperationExclDTO : set) {
			set_.add(osiUserOperationExclMapper.osiUserDTOToOsiUser(osiUserOperationExclDTO));
		}

		return set_;
	}
}
