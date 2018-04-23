package com.osi.ems.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiSkillTags;
import com.osi.ems.mapper.OsiSkillTagsMapper;
import com.osi.ems.repository.OsiSkillTagsRepository;
import com.osi.ems.service.OsiSkillTagsService;
import com.osi.ems.service.dto.OsiSkillTagsDto;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Service
@Transactional(rollbackOn = Exception.class)
public class OsiSkillTagsServiceImpl implements OsiSkillTagsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OsiSkillTagsServiceImpl.class);

	@Autowired
	private OsiSkillTagsRepository osiSkillTagsRepository;

	@Autowired
	private OsiSkillTagsMapper osiSkillTagsMapper;

	@Override
	public OsiSkillTagsDto saveOsiSkillTags(OsiSkillTagsDto osiSkillTagsDto, Integer userId)
			throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiSkillTags osiSkillTags = null;
		LOGGER.info("OsiSkillTagsServiceImpl :: saveOsiSkillTags :: Satrt");
		try {
			osiSkillTagsDto.setCreatedBy(userId);
			if (osiSkillTagsDto.getIsActive() == null) {
				osiSkillTagsDto.setIsActive(1);
			}
			osiSkillTagsDto.setCreationDate(cs.getCurrentDateinUTC());
			osiSkillTags = this.osiSkillTagsMapper.osiSkillTagsDtoToOsiSkillTags(osiSkillTagsDto);
			osiSkillTags = this.osiSkillTagsRepository.save(osiSkillTags);
			osiSkillTagsDto = this.osiSkillTagsMapper.OsiSkillTagToOsiSkillTagsDto(osiSkillTags);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			if (e.getMessage() != null) {
				if (e.getMessage().contains("tag_name_UNIQUE")) {
					throw new BusinessException("ERR_1098", "Error occured while saving the Skill Tags");
				}
			}
			throw new BusinessException("ERR_1000", "Error occured while saving the osiskill tags");
		}
		LOGGER.info("OsiSkillTagsServiceImpl :: saveOsiSkillTags :: End");

		return osiSkillTagsDto;
	}

	@Override
	public OsiSkillTagsDto updateOsiSkillTags(OsiSkillTagsDto osiSkillTagsDto, Integer userId)
			throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiSkillTags osiSkillTags = null;
		OsiSkillTags existingRecord = null;
		LOGGER.info("OsiSkillTagsServiceImpl :: updateOsiSkillTags :: Satrt");
		try {
			existingRecord = this.osiSkillTagsRepository.findOne(osiSkillTagsDto.getTagId());
			if (existingRecord != null) {
				existingRecord.setLastUpdatedBy(userId);
				existingRecord.setLastUpdateDate(cs.getCurrentDateinUTC());
				existingRecord.setTagName(osiSkillTagsDto.getTagName());
				existingRecord.setDescription(osiSkillTagsDto.getDescription());
				existingRecord.setIsActive(osiSkillTagsDto.getIsActive());
				osiSkillTags = this.osiSkillTagsRepository.save(existingRecord);
				osiSkillTagsDto = this.osiSkillTagsMapper.OsiSkillTagToOsiSkillTagsDto(osiSkillTags);
			} else {
				throw new BusinessException("ERR_100", "No Records Found with the given Id");
			}

		} catch (BusinessException expected) {
			LOGGER.error("Error Occured : " + expected.getMessage());
			throw expected;
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the osiSkillgroup");
		}
		LOGGER.info("OsiSkillTagsServiceImpl :: updateOsiSkillTags :: End");

		return osiSkillTagsDto;
	}

	@Override
	public OsiSkillTagsDto getOsiSkillTagsById(Integer osiSkillTagId) throws BusinessException, DataAccessException {
		LOGGER.info("OsiSkillGroupsServiceImpl :: getOsiSkillGroupsById :: start");
		OsiSkillTags osiSkillTags = null;
		OsiSkillTagsDto osiSkillTagsDto = null;
		try {
			osiSkillTags = this.osiSkillTagsRepository.getOne(osiSkillTagId);
			osiSkillTagsDto = this.osiSkillTagsMapper.OsiSkillTagToOsiSkillTagsDto(osiSkillTags);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi skill groups");
		}
		LOGGER.info("OsiSkillGroupsServiceImpl :: getOsiSkillGroupsById :: End");
		return osiSkillTagsDto;

	}

	@Override
	public List<OsiSkillTagsDto> getAllOsiSkillTags(OsiSkillTagsDto dto) throws BusinessException, DataAccessException {
		LOGGER.info("OsiSkillGroupsServiceImpl :: getOsiSkillGroupsById :: start");
		List<OsiSkillTags> osiSkillTagsList = null;
		List<OsiSkillTagsDto> osiSkillTagsDtoList = null;
		try {
			if (dto != null && dto.getTagName() != null && !dto.getTagName().trim().isEmpty()) {
				osiSkillTagsList = this.osiSkillTagsRepository.findByTagNameContaining(dto.getTagName().trim());
			} else {
				osiSkillTagsList = this.osiSkillTagsRepository.findAll();
			}
			osiSkillTagsDtoList = this.osiSkillTagsMapper.osiSkillTagsListToOsiSkillTagsDtoList(osiSkillTagsList);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi skill groups");
		}
		LOGGER.info("OsiSkillGroupsServiceImpl :: getOsiSkillGroupsById :: End");
		return osiSkillTagsDtoList;
	}

	@Override
	public List<OsiSkillTagsDto> getAllActiveOsiSkillTags() throws BusinessException, DataAccessException {
		LOGGER.info("OsiSkillGroupsServiceImpl :: getAllActiveOsiSkillTags :: start");
		List<OsiSkillTags> osiSkillTagsList = null;
		List<OsiSkillTagsDto> osiSkillTagsDtoList = null;
		try {
			osiSkillTagsList = this.osiSkillTagsRepository.findByIsActive(1);
			osiSkillTagsDtoList = this.osiSkillTagsMapper.osiSkillTagsListToOsiSkillTagsDtoList(osiSkillTagsList);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi skill groups");
		}
		LOGGER.info("OsiSkillGroupsServiceImpl :: getAllActiveOsiSkillTags :: End");
		return osiSkillTagsDtoList;
	}

	@Override
	public OsiSkillTagsDto deleteOsiSkillTags(Integer skillTagId, Integer userId)
			throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiSkillTags osiSkillTags = null;
		OsiSkillTags existingRecord = null;
		OsiSkillTagsDto osiSkillTagsDto = null;
		LOGGER.info("OsiSkillTagsServiceImpl :: deleteOsiSkillTags :: Satrt");
		try {
			existingRecord = this.osiSkillTagsRepository.findOne(skillTagId);
			if (existingRecord != null) {
				existingRecord.setLastUpdatedBy(userId);
				existingRecord.setLastUpdateDate(cs.getCurrentDateinUTC());
				existingRecord.setIsActive(0);
				osiSkillTags = this.osiSkillTagsRepository.save(existingRecord);
				osiSkillTagsDto = this.osiSkillTagsMapper.OsiSkillTagToOsiSkillTagsDto(osiSkillTags);
			} else {
				throw new BusinessException("ERR_100", "No Records Found with the Given Id");
			}

		} catch (BusinessException expected) {
			LOGGER.error("Error Occured : " + expected.getMessage());
			throw expected;
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the osiSkillgroup");
		}
		LOGGER.info("OsiSkillTagsServiceImpl :: deleteOsiSkillTags :: End");

		return osiSkillTagsDto;
	}

}
