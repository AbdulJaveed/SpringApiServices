package com.osi.ems.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiCertificationGroups;
import com.osi.ems.mapper.OsiCertificationGroupMapper;
import com.osi.ems.repository.OsiCertificationGroupsRepository;
import com.osi.ems.service.OsiCertificationGroupsService;
import com.osi.ems.service.dto.OsiCertificationGroupsDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Service
@Transactional(rollbackOn = Exception.class)
public class OsiCertificationGroupsServiceImpl implements OsiCertificationGroupsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OsiCertificationGroupsServiceImpl.class);

	@Autowired
	private OsiCertificationGroupsRepository osiCertificationGroupsRepository;

	@Autowired
	private OsiCertificationGroupMapper osiCertificationGroupMapper;

	@Override
	public OsiCertificationGroupsDTO saveOsiCertificationGroups(OsiCertificationGroupsDTO osiCertificationGroupsDto,
			Integer userId) throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiCertificationGroups osiCertificationGroups = null;
		LOGGER.info("OsiCertificationGroupsServiceImpl :: saveOsiCertificationGroups :: Satrt");
		try {
			osiCertificationGroupsDto.setCreatedBy(userId);
			if (osiCertificationGroupsDto.getIsActive() == null) {
				osiCertificationGroupsDto.setIsActive(1);
			}
			osiCertificationGroupsDto.setCreationDate(cs.getCurrentDateinUTC());
			osiCertificationGroups = this.osiCertificationGroupMapper
					.osiCertificationGroupsDtoToOsiCertificationGroups(osiCertificationGroupsDto);
			osiCertificationGroups = this.osiCertificationGroupsRepository.save(osiCertificationGroups);
			osiCertificationGroupsDto = this.osiCertificationGroupMapper
					.osiCertificationGroupsToOsiCertificationGroupsDTO(osiCertificationGroups);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			if (e.getMessage() != null) {
				if (e.getMessage().contains("group_name_UNIQUE")) {
					throw new BusinessException("ERR_1096", "Error occured while saving the osiCertificationgroup");
				}
			}

			throw new BusinessException("ERR_1000", "Error occured while saving the osiCertificationgroup");
		}
		LOGGER.info("OsiCertificationGroupsServiceImpl :: saveOsiCertificationGroups :: End");

		return osiCertificationGroupsDto;
	}

	@Override
	public OsiCertificationGroupsDTO updateOsiCertificationGroups(OsiCertificationGroupsDTO osiCertificationGroupsDto,
			Integer userId) throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiCertificationGroups osiCertificationGroups = null;
		OsiCertificationGroups existingRecord = null;
		String message = "Error occured while updating the osiCertificationgroup";
		LOGGER.info("OsiCertificationGroupsServiceImpl :: updateOsiCertificationGroups :: Satrt");
		try {
			existingRecord = this.osiCertificationGroupsRepository.findOne(osiCertificationGroupsDto.getGroupId());
			if (existingRecord != null) {
				existingRecord.setLastUpdatedBy(userId);
				existingRecord.setLastUpdateDate(cs.getCurrentDateinUTC());
				existingRecord.setGroupName(osiCertificationGroupsDto.getGroupName());
				existingRecord.setGroupDescription(osiCertificationGroupsDto.getGroupDescription());
				existingRecord.setIsActive(osiCertificationGroupsDto.getIsActive());
				osiCertificationGroups = this.osiCertificationGroupsRepository.save(existingRecord);
				osiCertificationGroupsDto = this.osiCertificationGroupMapper
						.osiCertificationGroupsToOsiCertificationGroupsDTO(osiCertificationGroups);
			} else {
				throw new BusinessException("ERR_1000", "No Record Found with this id");
			}

		} catch (BusinessException expected) {
			LOGGER.error("Error Occured : " + expected.getMessage());
			throw expected;
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", message);
		}
		LOGGER.info("OsiCertificationGroupsServiceImpl :: updateOsiCertificationGroups :: End");

		return osiCertificationGroupsDto;
	}

	@Override
	public OsiCertificationGroupsDTO getOsiCertificationGroupsById(Integer osiCertificationGroupId)
			throws BusinessException, DataAccessException {
		LOGGER.info("OsiCertificationGroupsServiceImpl :: getOsiCertificationGroupsById :: start");
		OsiCertificationGroups osiCertificationGroup = null;
		OsiCertificationGroupsDTO osiCertificationGroupDto = null;
		try {
			osiCertificationGroup = this.osiCertificationGroupsRepository.getOne(osiCertificationGroupId);
			osiCertificationGroupDto = this.osiCertificationGroupMapper
					.osiCertificationGroupsToOsiCertificationGroupsDTO(osiCertificationGroup);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi Certification groups");
		}
		LOGGER.info("OsiCertificationGroupsServiceImpl :: getOsiCertificationGroupsById :: End");
		return osiCertificationGroupDto;
	}

	@Override
	public List<OsiCertificationGroupsDTO> getAllOsiCertificationGroups(OsiCertificationGroupsDTO dto)
			throws BusinessException, DataAccessException {
		LOGGER.info("OsiCertificationGroupsServiceImpl :: getAllOsiCertificationGroups :: start");
		List<OsiCertificationGroups> osiCertificationGroupList = null;
		List<OsiCertificationGroupsDTO> osiCertificationGroupDtoList = null;
		try {
			if (dto != null && dto.getGroupName() != null && !dto.getGroupName().trim().isEmpty()) {
				osiCertificationGroupList = this.osiCertificationGroupsRepository
						.findByGroupNameContaining(dto.getGroupName().trim());
			} else {
				osiCertificationGroupList = this.osiCertificationGroupsRepository.findAll();
			}
			osiCertificationGroupDtoList = this.osiCertificationGroupMapper
					.osiCertificationGroupsListToOsiCertificationGroupsDTO(osiCertificationGroupList);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi Certification groups");
		}
		LOGGER.info("OsiCertificationGroupsServiceImpl :: getAllOsiCertificationGroups :: End");
		return osiCertificationGroupDtoList;
	}

	@Override
	public List<OsiCertificationGroupsDTO> getAllActiveOsiCertificationGroups()
			throws BusinessException, DataAccessException {
		LOGGER.info("OsiCertificationGroupsServiceImpl :: getAllActiveOsiCertificationGroups :: start");
		List<OsiCertificationGroups> osiCertificationGroupList = null;
		List<OsiCertificationGroupsDTO> osiCertificationGroupDtoList = null;
		try {

			osiCertificationGroupList = this.osiCertificationGroupsRepository.findByIsActive(1);
			osiCertificationGroupDtoList = this.osiCertificationGroupMapper
					.osiCertificationGroupsListToOsiCertificationGroupsDTO(osiCertificationGroupList);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi Certification groups");
		}
		LOGGER.info("OsiCertificationGroupsServiceImpl :: getAllActiveOsiCertificationGroups :: End");
		return osiCertificationGroupDtoList;
	}

	@Override
	public OsiCertificationGroupsDTO deleteOsiCertificationGroups(Integer osiCertificationGroupId, Integer userId)
			throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiCertificationGroups osiCertificationGroups = null;
		OsiCertificationGroups existingRecord = null;
		OsiCertificationGroupsDTO osiCertificationGroupsDto = null;

		LOGGER.info("OsiCertificationGroupsServiceImpl :: deleteOsiCertificationGroups :: Satrt");
		try {
			existingRecord = this.osiCertificationGroupsRepository.findOne(osiCertificationGroupId);
			if (existingRecord != null) {
				existingRecord.setLastUpdatedBy(userId);
				existingRecord.setLastUpdateDate(cs.getCurrentDateinUTC());
				existingRecord.setIsActive(0);
				osiCertificationGroups = this.osiCertificationGroupsRepository.save(existingRecord);
				osiCertificationGroupsDto = this.osiCertificationGroupMapper
						.osiCertificationGroupsToOsiCertificationGroupsDTO(osiCertificationGroups);
			} else {
				throw new BusinessException("ERR_1022", "No Record Found with this id");
			}

		} catch (BusinessException expected) {
			LOGGER.error("Error Occured : " + expected.getMessage());
			throw expected;
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the osiCertificationgroup");
		}
		LOGGER.info("OsiCertificationGroupsServiceImpl :: deleteOsiCertificationGroups :: End");

		return osiCertificationGroupsDto;
	}

}
