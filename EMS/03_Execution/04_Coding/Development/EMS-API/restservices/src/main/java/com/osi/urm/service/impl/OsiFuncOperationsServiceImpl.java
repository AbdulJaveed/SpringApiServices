package com.osi.urm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.urm.domain.OsiFuncOperations;
import com.osi.urm.mapper.OsiFuncOperationMapper;
import com.osi.urm.repository.OsiFuncOperationsRepository;
import com.osi.urm.service.OsiFuncOperationsService;
import com.osi.urm.service.dto.OsiFuncOperationsDTO;


@Service
@Transactional
public class OsiFuncOperationsServiceImpl implements OsiFuncOperationsService  {
	
	@Autowired
	private OsiFuncOperationsRepository osiFuncOperationsRepository;

	@Autowired
	private OsiFuncOperationMapper osiFuncOperationMapper;
	
	@Autowired
	private CommonService commonService;

	public OsiFuncOperationsDTO save(OsiFuncOperationsDTO osiFuncOperationsDTO,Integer businessGroupId) {
		osiFuncOperationsDTO.setBusinessGroupId(businessGroupId);
		OsiFuncOperations osiFuncOperations=osiFuncOperationMapper.osiFuncOperationDTOToOsiFuncOperation(osiFuncOperationsDTO);
		osiFuncOperations=osiFuncOperationsRepository.save(osiFuncOperations);
		 osiFuncOperationsDTO= osiFuncOperationMapper.osiFuncOperationToOsiFuncOperationDTO(osiFuncOperations);
		 return osiFuncOperationsDTO;
		 }

	@Override
	public List<OsiFuncOperationsDTO> findAll() {
		List<OsiFuncOperations> osiFuncOperations=osiFuncOperationsRepository.findAll();
		List<OsiFuncOperationsDTO> osiFuncOperationsDTOs=osiFuncOperationMapper.osiFuncOperationListToOsiFuncOperationDTOList(osiFuncOperations);
		return osiFuncOperationsDTOs;
	}

	@Override
	public List<OsiFuncOperationsDTO> findByFunctionId(Integer id) {
		List<OsiFuncOperations> osiFuncOperations=osiFuncOperationsRepository.findByOsiFunctionsId(id);
		List<OsiFuncOperationsDTO> osiFuncOperationsDTOs=osiFuncOperationMapper.osiFuncOperationListToOsiFuncOperationDTOList(osiFuncOperations);
		return osiFuncOperationsDTOs;
	}
	
}
