package com.osi.ems.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiCertificationTags;
import com.osi.ems.mapper.OsiCertificationTagsMapper;
import com.osi.ems.repository.OsiCertificationTagsRepository;
import com.osi.ems.service.OsiCertificationTagsService;
import com.osi.ems.service.dto.OsiCertificationTagsDto;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Service
@Transactional(rollbackOn = Exception.class)
public class OsiCertificationTagsServiceImpl implements OsiCertificationTagsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OsiCertificationTagsServiceImpl.class);

	@Autowired
	private OsiCertificationTagsRepository osiCertificationTagsRepository;

	@Autowired
	private OsiCertificationTagsMapper osiCertificationTagsMapper;

	@Override
	public OsiCertificationTagsDto saveOsiCertificationTags(OsiCertificationTagsDto osiCertificationTagsDto,
			Integer userId) throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiCertificationTags osiCertificationTags = null;
		LOGGER.info("OsiCertificationTagsServiceImpl :: saveOsiCertificationTags :: Satrt");
		try {
			osiCertificationTagsDto.setCreatedBy(userId);
			if (osiCertificationTagsDto.getIsActive() == null) {
				osiCertificationTagsDto.setIsActive(1);
			}
			osiCertificationTagsDto.setCreationDate(cs.getCurrentDateinUTC());
			osiCertificationTags = this.osiCertificationTagsMapper
					.osiCertificationTagsDtoToOsiCertificationTags(osiCertificationTagsDto);
			osiCertificationTags = this.osiCertificationTagsRepository.save(osiCertificationTags);
			osiCertificationTagsDto = this.osiCertificationTagsMapper
					.OsiCertificationTagToOsiCertificationTagsDto(osiCertificationTags);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			if (e.getMessage() != null) {
				if (e.getMessage().contains("tag_name_UNIQUE")) {
					throw new BusinessException("ERR_1097", "Error occured while saving the Certification Tags");
				}
			}
			throw new BusinessException("ERR_1000", "Error occured while saving the osiCertification tags");
		}
		LOGGER.info("OsiCertificationTagsServiceImpl :: saveOsiCertificationTags :: End");

		return osiCertificationTagsDto;
	}

	@Override
	public OsiCertificationTagsDto updateOsiCertificationTags(OsiCertificationTagsDto osiCertificationTagsDto,
			Integer userId) throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiCertificationTags osiCertificationTags = null;
		OsiCertificationTags existingRecord = null;
		LOGGER.info("OsiCertificationTagsServiceImpl :: updateOsiCertificationTags :: Satrt");
		try {
			existingRecord = this.osiCertificationTagsRepository.findOne(osiCertificationTagsDto.getTagId());
			if (existingRecord != null) {
				existingRecord.setLastUpdatedBy(userId);
				existingRecord.setLastUpdateDate(cs.getCurrentDateinUTC());
				existingRecord.setTagName(osiCertificationTagsDto.getTagName());
				existingRecord.setDescription(osiCertificationTagsDto.getDescription());
				existingRecord.setIsActive(osiCertificationTagsDto.getIsActive());
				osiCertificationTags = this.osiCertificationTagsRepository.save(existingRecord);
				osiCertificationTagsDto = this.osiCertificationTagsMapper
						.OsiCertificationTagToOsiCertificationTagsDto(osiCertificationTags);
			} else {
				throw new BusinessException("ERR_1000", "No Records Found with the given Id");
			}

		} catch (BusinessException expected) {
			LOGGER.error("Error Occured : " + expected.getMessage());
			throw expected;
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the osiCertificationgroup");
		}
		LOGGER.info("OsiCertificationTagsServiceImpl :: updateOsiCertificationTags :: End");

		return osiCertificationTagsDto;
	}

	@Override
	public OsiCertificationTagsDto getOsiCertificationTagsById(Integer osiCertificationTagId)
			throws BusinessException, DataAccessException {
		LOGGER.info("OsiCertificationGroupsServiceImpl :: getOsiCertificationGroupsById :: start");
		OsiCertificationTags osiCertificationTags = null;
		OsiCertificationTagsDto osiCertificationTagsDto = null;
		try {
			osiCertificationTags = this.osiCertificationTagsRepository.getOne(osiCertificationTagId);
			osiCertificationTagsDto = this.osiCertificationTagsMapper
					.OsiCertificationTagToOsiCertificationTagsDto(osiCertificationTags);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi Certification groups");
		}
		LOGGER.info("OsiCertificationGroupsServiceImpl :: getOsiCertificationGroupsById :: End");
		return osiCertificationTagsDto;

	}

	@Override
	public List<OsiCertificationTagsDto> getAllOsiCertificationTags(OsiCertificationTagsDto dto)
			throws BusinessException, DataAccessException {
		LOGGER.info("OsiCertificationGroupsServiceImpl :: getOsiCertificationGroupsById :: start");
		List<OsiCertificationTags> osiCertificationTagsList = null;
		List<OsiCertificationTagsDto> osiCertificationTagsDtoList = null;
		try {
			if (dto != null && dto.getTagName() != null && !dto.getTagName().trim().isEmpty()) {
				osiCertificationTagsList = this.osiCertificationTagsRepository
						.findByTagNameContaining(dto.getTagName().trim());
			} else {
				osiCertificationTagsList = this.osiCertificationTagsRepository.findAll();
			}
			osiCertificationTagsDtoList = this.osiCertificationTagsMapper
					.osiCertificationTagsListToOsiCertificationTagsDtoList(osiCertificationTagsList);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi Certification groups");
		}
		LOGGER.info("OsiCertificationGroupsServiceImpl :: getOsiCertificationGroupsById :: End");
		return osiCertificationTagsDtoList;
	}

	@Override
	public List<OsiCertificationTagsDto> getAllActiveOsiCertificationTags()
			throws BusinessException, DataAccessException {
		LOGGER.info("OsiCertificationGroupsServiceImpl :: getAllActiveOsiCertificationTags :: start");
		List<OsiCertificationTags> osiCertificationTagsList = null;
		List<OsiCertificationTagsDto> osiCertificationTagsDtoList = null;
		try {

			osiCertificationTagsList = this.osiCertificationTagsRepository.findByIsActive(1);
			osiCertificationTagsDtoList = this.osiCertificationTagsMapper
					.osiCertificationTagsListToOsiCertificationTagsDtoList(osiCertificationTagsList);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi Certification groups");
		}
		LOGGER.info("OsiCertificationGroupsServiceImpl :: getAllActiveOsiCertificationTags :: End");
		return osiCertificationTagsDtoList;
	}

	@Override
	public OsiCertificationTagsDto deleteOsiCertificationTags(Integer CertificationTagId, Integer userId)
			throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiCertificationTags osiCertificationTags = null;
		OsiCertificationTags existingRecord = null;
		OsiCertificationTagsDto osiCertificationTagsDto = null;
		LOGGER.info("OsiCertificationTagsServiceImpl :: deleteOsiCertificationTags :: Satrt");
		try {
			existingRecord = this.osiCertificationTagsRepository.findOne(CertificationTagId);
			if (existingRecord != null) {
				existingRecord.setLastUpdatedBy(userId);
				existingRecord.setLastUpdateDate(cs.getCurrentDateinUTC());
				existingRecord.setIsActive(0);
				osiCertificationTags = this.osiCertificationTagsRepository.save(existingRecord);
				osiCertificationTagsDto = this.osiCertificationTagsMapper
						.OsiCertificationTagToOsiCertificationTagsDto(osiCertificationTags);
			} else {
				throw new BusinessException("ERR_1000", "No Records Found with the Given Id");
			}

		} catch (BusinessException expected) {
			LOGGER.error("Error Occured : " + expected.getMessage());
			throw expected;
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the osiCertificationgroup");
		}
		
		LOGGER.info("OsiCertificationTagsServiceImpl :: deleteOsiCertificationTags :: End");

		return osiCertificationTagsDto;
	}

}
