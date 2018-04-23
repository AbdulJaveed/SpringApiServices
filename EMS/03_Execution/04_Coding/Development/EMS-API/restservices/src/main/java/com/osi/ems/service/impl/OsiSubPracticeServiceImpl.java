package com.osi.ems.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osi.ems.common.CommonService;
import com.osi.ems.dao.OsiSubPracticeDao;
import com.osi.ems.domain.OsiSubPractice;
import com.osi.ems.mapper.OsiSubPracticeGradesHistoryMapper;
import com.osi.ems.mapper.OsiSubPracticeMapper;
import com.osi.ems.repository.OsiSubPracticeGradesHistoryRepository;
import com.osi.ems.repository.OsiSubPracticeRepository;
import com.osi.ems.service.OsiSubPracticeService;
import com.osi.ems.service.dto.OsiSubPracticeDto;
import com.osi.ems.service.dto.OsiSubPracticeGradesDto;
import com.osi.ems.service.dto.OsiSubPracticeGradesHistoryDto;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Service
@Transactional(rollbackOn = Exception.class)
public class OsiSubPracticeServiceImpl implements OsiSubPracticeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OsiSubPracticeServiceImpl.class);

	@Autowired
	private OsiSubPracticeRepository osiSubPracticeRepository;

	@Autowired
	private OsiSubPracticeMapper osiSubPracticeMapper;

	@Autowired
	private OsiSubPracticeDao osiSubPracticedao;

	@Autowired
	private OsiSubPracticeGradesHistoryMapper historyMapper;

	@Autowired
	private OsiSubPracticeGradesHistoryRepository osiSubPracticeHistoryRepository;

	@Override
	public OsiSubPracticeDto saveOsiSubPractice(OsiSubPracticeDto osiSubPracticeDto, Integer userId)
			throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiSubPractice osiSubPractice = null;
		LOGGER.info("OsiSubPracticeServiceImpl :: saveOsiSubPractice :: Satrt");
		try {
			osiSubPracticeDto.setCreatedBy(userId);
			if (osiSubPracticeDto.getActive() == null) {
				osiSubPracticeDto.setActive(1);
			}
			osiSubPracticeDto.setCreatedDate(cs.getCurrentDateinUTC());
			osiSubPracticeDto.setUpdatedBy(userId);
			osiSubPracticeDto.setLastUpdateDate(cs.getCurrentDateinUTC());
			osiSubPractice = this.osiSubPracticeMapper.getOsiSubPractice(osiSubPracticeDto);
			osiSubPractice = this.osiSubPracticeRepository.save(osiSubPractice);
			osiSubPracticeDto = this.osiSubPracticeMapper.getOsiSubPracticeDto(osiSubPractice);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			if (e.getMessage() != null) {
				if (e.getMessage().contains("tag_name_UNIQUE")) {
					throw new BusinessException("ERR_1098", "Error occured while saving the OsiSub Practice");
				}
			}
			throw new BusinessException("ERR_1000", "Error occured while saving the Osi SubPractice");
		}
		LOGGER.info("OsiSubPracticeServiceImpl :: saveOsiSubPractice :: End");

		return osiSubPracticeDto;
	}

	@Override
	public OsiSubPracticeDto updateOsiSubPractice(OsiSubPracticeDto osiSubPracticeDto, Integer userId)
			throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiSubPractice osiSubPractice = null;
		OsiSubPractice existingRecord = null;
		LOGGER.info("OsiSubPracticeServiceImpl :: updateOsiSubPractice :: Satrt");
		try {
			existingRecord = this.osiSubPracticeRepository.findOne(osiSubPracticeDto.getSubPracticeId());
			if (existingRecord != null) {
				existingRecord.setUpdatedBy(userId);
				existingRecord.setLastUpdateDate(cs.getCurrentDateinUTC());
				existingRecord.setSubPractceLongName(osiSubPracticeDto.getSubPractceLongName());
				existingRecord.setSubPracticeShortName(osiSubPracticeDto.getSubPracticeShortName());
				int active = osiSubPracticeDto.getActive() != null ? osiSubPracticeDto.getActive() : 1;
				existingRecord.setActive(active);
				osiSubPractice = this.osiSubPracticeRepository.save(existingRecord);
				osiSubPracticeDto = this.osiSubPracticeMapper.getOsiSubPracticeDto(osiSubPractice);
			} else {
				throw new BusinessException("ERR_100", "No Records Found with the given Id");
			}

		} catch (BusinessException expected) {
			LOGGER.error("Error Occured : " + expected.getMessage());
			throw expected;
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the osiSubPractice");
		}
		LOGGER.info("OsiSubPracticeServiceImpl :: updateOsiSubPractice :: End");

		return osiSubPracticeDto;
	}

	@Override
	public OsiSubPracticeDto getOsiSubPracticeById(Integer osiSkillTagId)
			throws BusinessException, DataAccessException {
		LOGGER.info("OsiSubPracticeServiceImpl :: getOsiSubPracticeById :: start");
		OsiSubPractice osiSubPractice = null;
		OsiSubPracticeDto osiSubPracticeDto = null;
		try {
			osiSubPractice = this.osiSubPracticeRepository.getOne(osiSkillTagId);
			osiSubPracticeDto = this.osiSubPracticeMapper.getOsiSubPracticeDto(osiSubPractice);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osiSubPractice");
		}
		LOGGER.info("OsiSubPracticeServiceImpl :: getOsiSubPracticeById :: End");
		return osiSubPracticeDto;

	}

	@Override
	public List<OsiSubPracticeDto> getAllOsiSubPractice(OsiSubPracticeDto osiSubPracticeDto)
			throws BusinessException, DataAccessException {
		LOGGER.info("OsiSubPracticeServiceImpl :: getAllOsiSubPractice :: start");
		List<OsiSubPractice> osiSubPracticeList = null;
		List<OsiSubPracticeDto> osiSubPracticeDtoList = null;
		try {
			String shortName = osiSubPracticeDto.getSubPracticeShortName();
			String longName = osiSubPracticeDto.getSubPractceLongName();
			if (StringUtils.isNotEmpty(shortName) && StringUtils.isNotEmpty(longName)) {
				shortName = shortName.trim();
				longName = longName.trim();
				osiSubPracticeList = this.osiSubPracticeRepository
						.findBySubPracticeShortNameContainingIgnoreCaseAndSubPractceLongNameContainingIgnoreCase(
								shortName, longName);
			} else if (StringUtils.isNotEmpty(shortName)) {
				shortName = shortName.trim();
				osiSubPracticeList = this.osiSubPracticeRepository
						.findBySubPracticeShortNameContainingIgnoreCase(shortName);
			} else if (StringUtils.isNotEmpty(longName)) {
				longName = longName.trim();
				osiSubPracticeList = this.osiSubPracticeRepository
						.findBySubPractceLongNameContainingIgnoreCase(longName);
			} else {
				osiSubPracticeList = this.osiSubPracticeRepository.findAll();
			}
			osiSubPracticeDtoList = this.osiSubPracticeMapper.getOsiSubPracticeDtoList(osiSubPracticeList);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi Osi SubPractice");
		}
		LOGGER.info("OsiSubPracticeServiceImpl :: getAllOsiSubPractice :: End");
		return osiSubPracticeDtoList;
	}

	@Override
	public List<OsiSubPracticeDto> getAllActiveOsiSubPractice() throws BusinessException, DataAccessException {
		LOGGER.info("OsiSkillGroupsServiceImpl :: getAllActiveOsiSubPractice :: start");
		List<OsiSubPractice> osiSubPracticeList = null;
		List<OsiSubPracticeDto> osiSubPracticeDtoList = null;
		try {
			osiSubPracticeList = this.osiSubPracticeRepository.findByActive(1);
			osiSubPracticeDtoList = this.osiSubPracticeMapper.getOsiSubPracticeDtoList(osiSubPracticeList);
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the Osi SubPractice");
		}
		LOGGER.info("OsiSkillGroupsServiceImpl :: getAllActiveOsiSubPractice :: End");
		return osiSubPracticeDtoList;
	}

	@Override
	public OsiSubPracticeDto deleteOsiSubPractice(Integer osiSubPracticeId, Integer userId)
			throws BusinessException, DataAccessException {
		CommonService cs = new CommonService();
		OsiSubPractice osiSubPractice = null;
		OsiSubPractice existingRecord = null;
		OsiSubPracticeDto osiSubPracticeDto = null;
		LOGGER.info("OsiSubPracticeServiceImpl :: deleteOsiSubPractice :: Satrt");
		try {
			existingRecord = this.osiSubPracticeRepository.findOne(osiSubPracticeId);
			if (existingRecord != null) {
				existingRecord.setUpdatedBy(userId);
				existingRecord.setLastUpdateDate(cs.getCurrentDateinUTC());
				existingRecord.setActive(0);
				osiSubPractice = this.osiSubPracticeRepository.save(existingRecord);
				osiSubPracticeDto = this.osiSubPracticeMapper.getOsiSubPracticeDto(osiSubPractice);
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
		LOGGER.info("OsiSubPracticeServiceImpl :: deleteOsiSubPractice :: End");

		return osiSubPracticeDto;
	}

	@Override
	public OsiSubPracticeGradesDto createOsiSubPracticesGrades(OsiSubPracticeGradesDto osiSubPracticeGradesDto,
			Integer userId) throws BusinessException, DataAccessException {
		LOGGER.info("\"OsiSubPracticeServiceImpl :: createOsiSubPracticesGrades :: Begin");
		OsiSubPracticeGradesDto osiSubPracticeGrades = new OsiSubPracticeGradesDto();
		try {
			boolean isNewOrModified = this.osiSubPracticedao.compareWithExisting(osiSubPracticeGradesDto);
			if (isNewOrModified) {
				// deleting the existing record
				if (osiSubPracticeGradesDto.getSubPracticeGradeId() != null)
					this.osiSubPracticedao.deleteOsiSubPracticeGrades(osiSubPracticeGradesDto.getSubPracticeGradeId());

				osiSubPracticeGrades = this.osiSubPracticedao.saveOsiSubPracticeGrades(osiSubPracticeGradesDto, userId);

				OsiSubPracticeGradesHistoryDto buGradesHistory = new OsiSubPracticeGradesHistoryDto();
				BeanUtils.copyProperties(osiSubPracticeGrades, buGradesHistory);
				buGradesHistory.setCreatedBy(userId);
				buGradesHistory.setCreatedDate(new CommonService().getCurrentDateinUTC());
				osiSubPracticeHistoryRepository
						.save(this.historyMapper.getOsiSubPracticeGradesHistory(buGradesHistory));
			} else
				return osiSubPracticeGrades;

		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the BU");
		}
		LOGGER.info("OsiSubPracticeServiceImpl :: createOsiSubPracticesGrades :: End");
		return osiSubPracticeGrades;
	}

	@Override
	public OsiSubPracticeGradesDto createOsiSubPracticesGradesWithHistoryDuplicate(
			OsiSubPracticeGradesDto osiSubPracticeGradesDto, Integer userId)
			throws BusinessException, DataAccessException {
		LOGGER.info("\"OsiSubPracticeServiceImpl :: createOsiSubPracticesGrades :: Begin");
		OsiSubPracticeGradesDto osiSubPracticeGrades = new OsiSubPracticeGradesDto();
		int count = 0;
		try {
			// boolean isNewOrModified =
			// this.osiSubPracticedao.compareWithExisting(osiSubPracticeGradesDto);
			// if (isNewOrModified) {
			// deleting the existing record

			osiSubPracticeGrades = this.osiSubPracticedao.saveOsiSubPracticeGrades(osiSubPracticeGradesDto, userId);

			OsiSubPracticeGradesHistoryDto buGradesHistory = new OsiSubPracticeGradesHistoryDto();
			BeanUtils.copyProperties(osiSubPracticeGrades, buGradesHistory);
			buGradesHistory.setCreatedBy(userId);
			buGradesHistory.setCreatedDate(new CommonService().getCurrentDateinUTC());

			Integer ogrId = buGradesHistory.getOrgId();
			Integer subPracticeId = buGradesHistory.getSubPracticeId();
			Integer gradeId = buGradesHistory.getGradeId();
			BigDecimal costPerHour = buGradesHistory.getCostPerHour();
			BigDecimal costPerMonth = buGradesHistory.getCostPerMonth();
			Integer internalCostOverheadPercentage = buGradesHistory.getInternalCostOverheadPct();
			count = this.osiSubPracticeHistoryRepository
					.countByOrgIdAndSubPracticeIdAndGradeIdAndCostPerHourAndCostPerMonthAndInternalCostOverheadPercentage	(
							ogrId, subPracticeId, gradeId, costPerHour, costPerMonth, internalCostOverheadPercentage)
					.intValue();
			if (count == 0) {
				osiSubPracticeHistoryRepository
						.save(this.historyMapper.getOsiSubPracticeGradesHistory(buGradesHistory));
			}

		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the BU");
		}
		LOGGER.info("OsiSubPracticeServiceImpl :: createOsiSubPracticesGrades :: End");
		return osiSubPracticeGrades;
	}

	@Override
	public void deleteOsiSubPracticeGradesBysubPracticeId(Integer id) throws BusinessException, DataAccessException {
		LOGGER.info("OsiSubPracticeServiceImpl :: deleteOsiSubPracticeGradesBysubPracticeId :: Begin");
		try {
			osiSubPracticedao.deleteOsiSubPracticeGradesBysubPracticeId(id);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the BU");
		}
		LOGGER.info("OsiSubPracticeServiceImpl :: deleteOsiSubPracticeGradesBysubPracticeId :: End");
	}

	@Override
	public List<OsiSubPracticeGradesDto> getOsiSubPracticeGradesBysubPracticeId(Integer subPracticeId)
			throws BusinessException, DataAccessException {
		List<OsiSubPracticeGradesDto> osiSubPracticeGradesList = new ArrayList<OsiSubPracticeGradesDto>();
		LOGGER.info("OsiSubPracticeServiceImpl :: getOsiSubPracticeGradesBysubPracticeId :: Begin");
		try {
			osiSubPracticeGradesList = osiSubPracticedao.getOsiSubPracticeGradesBysubPracticeId(subPracticeId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the BU");
		}
		LOGGER.info("OsiSubPracticeServiceImpl :: getOsiSubPracticeGradesBysubPracticeId :: End");
		return osiSubPracticeGradesList;
	}

	@Override
	public List<OsiSubPracticeGradesHistoryDto> getOsiSubPracticeGradesHistory(Integer subPracticeId, Integer orgId,
			Integer gradeId) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("OsiSubPracticeServiceImpl :: getOsiSubPracticeGradesHistory :: Begin");
		List<OsiSubPracticeGradesHistoryDto> osiGradesVoList = new ArrayList<>();
		try {
			osiGradesVoList = osiSubPracticedao.getOsiSubPracticeGradesHistory(subPracticeId, orgId, gradeId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the osi BU Grades");
		}
		LOGGER.info("OsiSubPracticeServiceImpl :: getOsiSubPracticeGradesHistory :: End");
		return osiGradesVoList;
	}

}
