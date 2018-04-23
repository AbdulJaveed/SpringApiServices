package com.osi.ems.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiSkillGroups;
import com.osi.ems.mapper.OsiSkillGroupMapper;
import com.osi.ems.repository.OsiSkillGroupsRepository;
import com.osi.ems.service.OsiSkillGroupsService;
import com.osi.ems.service.dto.OsiSkillGroupsDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Service
@Transactional(rollbackOn = Exception.class)
public class OsiSkillGroupsServiceImpl implements OsiSkillGroupsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OsiSkillGroupsServiceImpl.class);

	@Autowired
	private OsiSkillGroupsRepository osiSkillGroupsRepository;

	@Autowired
	private OsiSkillGroupMapper osiSkillGroupMapper;

	@Override
	public OsiSkillGroupsDTO saveOsiSkillGroups(OsiSkillGroupsDTO osiSkillGroupsDto, Integer userId)
			throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiSkillGroups osiSkillGroups = null;
		LOGGER.info("OsiSkillGroupsServiceImpl :: saveOsiSkillGroups :: Satrt");
		try {
			osiSkillGroupsDto.setCreatedBy(userId);
			if (osiSkillGroupsDto.getIsActive() == null) {
				osiSkillGroupsDto.setIsActive(1);
			}
			osiSkillGroupsDto.setCreationDate(cs.getCurrentDateinUTC());
			osiSkillGroups = this.osiSkillGroupMapper.osiSkillGroupsDtoToOsiSkillGroups(osiSkillGroupsDto);
			osiSkillGroups = this.osiSkillGroupsRepository.save(osiSkillGroups);
			osiSkillGroupsDto = this.osiSkillGroupMapper.osiSkillGroupsToOsiSkillGroupsDTO(osiSkillGroups);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			if (e.getMessage() != null) {
				if (e.getMessage().contains("group_name_UNIQUE")) {
					throw new BusinessException("ERR_1099", "Error occured while saving the osiSkillgroup");
				}
			}

			throw new BusinessException("ERR_1000", "Error occured while saving the osiSkillgroup");
		}
		LOGGER.info("OsiSkillGroupsServiceImpl :: saveOsiSkillGroups :: End");

		return osiSkillGroupsDto;
	}

	@Override
	public OsiSkillGroupsDTO updateOsiSkillGroups(OsiSkillGroupsDTO osiSkillGroupsDto, Integer userId)
			throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiSkillGroups osiSkillGroups = null;
		OsiSkillGroups existingRecord = null;
		String message = "Error occured while updating the osiSkillgroup";
		LOGGER.info("OsiSkillGroupsServiceImpl :: updateOsiSkillGroups :: Satrt");
		try {
			existingRecord = this.osiSkillGroupsRepository.findOne(osiSkillGroupsDto.getGroupId());
			if (existingRecord != null) {
				existingRecord.setLastUpdatedBy(userId);
				existingRecord.setLastUpdateDate(cs.getCurrentDateinUTC());
				existingRecord.setGroupName(osiSkillGroupsDto.getGroupName());
				existingRecord.setGroupDescription(osiSkillGroupsDto.getGroupDescription());
				existingRecord.setIsActive(osiSkillGroupsDto.getIsActive());
				osiSkillGroups = this.osiSkillGroupsRepository.save(existingRecord);
				osiSkillGroupsDto = this.osiSkillGroupMapper.osiSkillGroupsToOsiSkillGroupsDTO(osiSkillGroups);
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
		LOGGER.info("OsiSkillGroupsServiceImpl :: updateOsiSkillGroups :: End");

		return osiSkillGroupsDto;
	}

	@Override
	public OsiSkillGroupsDTO getOsiSkillGroupsById(Integer osiSkillGroupId)
			throws BusinessException, DataAccessException {
		LOGGER.info("OsiSkillGroupsServiceImpl :: getOsiSkillGroupsById :: start");
		OsiSkillGroups osiSkillGroup = null;
		OsiSkillGroupsDTO osiSkillGroupDto = null;
		try {
			osiSkillGroup = this.osiSkillGroupsRepository.getOne(osiSkillGroupId);
			osiSkillGroupDto = this.osiSkillGroupMapper.osiSkillGroupsToOsiSkillGroupsDTO(osiSkillGroup);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi skill groups");
		}
		LOGGER.info("OsiSkillGroupsServiceImpl :: getOsiSkillGroupsById :: End");
		return osiSkillGroupDto;
	}

	@Override
	public List<OsiSkillGroupsDTO> getAllOsiSkillGroups(OsiSkillGroupsDTO dto)
			throws BusinessException, DataAccessException {
		LOGGER.info("OsiSkillGroupsServiceImpl :: getAllOsiSkillGroups :: start");
		List<OsiSkillGroups> osiSkillGroupList = null;
		List<OsiSkillGroupsDTO> osiSkillGroupDtoList = null;
		try {
			if (dto != null && dto.getGroupName() != null && !dto.getGroupName().trim().isEmpty()) {
				osiSkillGroupList = this.osiSkillGroupsRepository.findByGroupNameContaining(dto.getGroupName().trim());
			} else {
				osiSkillGroupList = this.osiSkillGroupsRepository.findAll();
			}
			osiSkillGroupDtoList = this.osiSkillGroupMapper.osiSkillGroupsListToOsiSkillGroupsDTO(osiSkillGroupList);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi skill groups");
		}
		LOGGER.info("OsiSkillGroupsServiceImpl :: getAllOsiSkillGroups :: End");
		return osiSkillGroupDtoList;
	}

	@Override
	public List<OsiSkillGroupsDTO> getAllActiveOsiSkillGroups() throws BusinessException, DataAccessException {
		LOGGER.info("OsiSkillGroupsServiceImpl :: getAllActiveOsiSkillGroups :: start");
		List<OsiSkillGroups> osiSkillGroupList = null;
		List<OsiSkillGroupsDTO> osiSkillGroupDtoList = null;
		try {

			osiSkillGroupList = this.osiSkillGroupsRepository.findByIsActive(1);
			osiSkillGroupDtoList = this.osiSkillGroupMapper.osiSkillGroupsListToOsiSkillGroupsDTO(osiSkillGroupList);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi skill groups");
		}
		LOGGER.info("OsiSkillGroupsServiceImpl :: getAllActiveOsiSkillGroups :: End");
		return osiSkillGroupDtoList;
	}

	@Override
	public OsiSkillGroupsDTO deleteOsiSkillGroups(Integer osiSkillGroupId, Integer userId)
			throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiSkillGroups osiSkillGroups = null;
		OsiSkillGroups existingRecord = null;
		OsiSkillGroupsDTO osiSkillGroupsDto = null;

		LOGGER.info("OsiSkillGroupsServiceImpl :: deleteOsiSkillGroups :: Satrt");
		try {
			existingRecord = this.osiSkillGroupsRepository.findOne(osiSkillGroupId);
			if (existingRecord != null) {
				existingRecord.setLastUpdatedBy(userId);
				existingRecord.setLastUpdateDate(cs.getCurrentDateinUTC());
				existingRecord.setIsActive(0);
				osiSkillGroups = this.osiSkillGroupsRepository.save(existingRecord);
				osiSkillGroupsDto = this.osiSkillGroupMapper.osiSkillGroupsToOsiSkillGroupsDTO(osiSkillGroups);
			} else {
				throw new BusinessException("ERR_1022", "No Record Found with this id");
			}

		} catch (BusinessException expected) {
			LOGGER.error("Error Occured : " + expected.getMessage());
			throw expected;
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the osiSkillgroup");
		}
		LOGGER.info("OsiSkillGroupsServiceImpl :: deleteOsiSkillGroups :: End");

		return osiSkillGroupsDto;
	}

}
