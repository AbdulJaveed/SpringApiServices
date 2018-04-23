package com.osi.urm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.urm.domain.OsiUserOperationExcl;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.mapper.OsiUserOperationExclMapper;
import com.osi.urm.repository.OsiOperationsRepository;
import com.osi.urm.repository.OsiUserOperationExclRepository;
import com.osi.urm.service.OsiUserOperationExclService;
import com.osi.urm.service.dto.OsiUserOperationExclDTO;

@Service
@Transactional
public class OsiUserOperationExclServiceImpl implements OsiUserOperationExclService {
	
	@Autowired
    private OsiUserOperationExclRepository osiUserOperationExclRepository;
	
	@Autowired
	private OsiUserOperationExclMapper osiUserOperationExclMapper;
	
	@Autowired
	private OsiOperationsRepository osiOperationsRepository;
		
	@Override
	public OsiUserOperationExclDTO save(OsiUserOperationExclDTO osiUserOperationExclDTO, Integer businessGroupId) {		
		osiUserOperationExclDTO.setBusinessGroupId(businessGroupId);
		OsiUserOperationExcl osiUserOperationExcl = osiUserOperationExclMapper.osiUserDTOToOsiUser(osiUserOperationExclDTO);
		osiUserOperationExcl = osiUserOperationExclRepository.save(osiUserOperationExcl);
		OsiUserOperationExclDTO result = osiUserOperationExclMapper.osiUserToOsiUserDTO(osiUserOperationExcl);
		return result;
	}
	@Override
	public List<OsiUserOperationExclDTO> findOne(Integer funcId, Integer userId) {
		List<OsiUserOperationExcl> osiUserOperationExclusions = osiUserOperationExclRepository.findByOsiFunctionsIdAndEmployeeId(funcId, userId);
		List<OsiUserOperationExclDTO> osiUserOperationExclDTOs = osiUserOperationExclMapper.osiUserListToOsiUserDTOList(osiUserOperationExclusions);
		/*List<OsiUserOperationExclDTO> osiUserOperationExclDTOs = new ArrayList<OsiUserOperationExclDTO>();
		for (Iterator iterator = osiUserOperationExclusions.iterator(); iterator.hasNext();) {
			OsiUserOperationExcl osiUserOperationExcl = (OsiUserOperationExcl) iterator.next();
			OsiUserOperationExclDTO osiUserOperationExclDTO =  new OsiUserOperationExclDTO();
			osiUserOperationExclDTO.setId(osiUserOperationExcl.getId());
			OsiOperationsDTO osiOperationsDTO = new OsiOperationsDTO();
			osiOperationsDTO.setId(osiUserOperationExcl.getOsiOperations().getId());
			osiUserOperationExclDTO.setOsiOperations(osiOperationsDTO);
			
			osiUserOperationExclDTOs.add(osiUserOperationExclDTO);
		}*/
		return osiUserOperationExclDTOs;
	}
	@Override
	public void delete(Integer id) {
		osiUserOperationExclRepository.delete(id);
	}
	@Override
	public void deleteByUserId(Integer userId, Integer functionId, Integer businessGroupId) {
		try {
			osiOperationsRepository.deleteByUserId(userId, functionId, businessGroupId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<OsiUserOperationExclDTO> findByEmployeeId(Integer userId) {
		 List<OsiUserOperationExclDTO> osiUserOperationExclDTOs =null;
		try {
			List<OsiUserOperationExcl>  userOper= osiUserOperationExclRepository.findByEmployeeId(userId);
			osiUserOperationExclDTOs = osiUserOperationExclMapper.osiUserListToOsiUserDTOList(userOper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return osiUserOperationExclDTOs;
	}
	@Override
	public void deleteByEmployeeId(int employeeId) {
		try {
			osiUserOperationExclRepository.removeByEmployeeId(employeeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void deleteAll() {
		try {
			osiUserOperationExclRepository.deleteAllInBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}