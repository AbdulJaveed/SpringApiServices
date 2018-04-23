/**
 * 
 */
package com.osi.ems.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.dao.OsiSubPracticeDao;
import com.osi.ems.domain.OsiSubPracticeGrades;
import com.osi.ems.mapper.OsiSubPracticeGradesHistoryMapper;
import com.osi.ems.mapper.OsiSubPracticeGradesMapper;
import com.osi.ems.repository.OsiSubPracticeGradesHistoryRepository;
import com.osi.ems.repository.OsiSubPracticeGradesRepository;
import com.osi.ems.repository.OsiSubPracticeRepository;
import com.osi.ems.service.OsiRollUpsService;
import com.osi.ems.service.dto.OsiSubPracticeGradesDto;
import com.osi.ems.service.dto.OsiSubPracticeGradesHistoryDto;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class OsiSubPracticesDaoImpl implements OsiSubPracticeDao {

	@Autowired
	OsiSubPracticeRepository osiSubPracticeRepository;

	private final Logger LOGGER = Logger.getLogger(OsiSubPracticesDaoImpl.class);

	@Autowired
	OsiRollUpsService osiRollUpsService;

	@Autowired
	OsiSubPracticeGradesRepository osiSubPracticesGradesRepository;

	@Autowired
	private OsiSubPracticeGradesMapper osiSubPracticeGradeMapper;

	@Autowired
	private OsiSubPracticeGradesHistoryMapper gradeHistoryMapper;

	@Autowired
	OsiSubPracticeGradesHistoryRepository osiSubPracticeGradesHistoryRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osi.ems.dao.IOsiSubPracticesinessUnitsDao#getOsiSubPracticesinessUnits(
	 * java.lang.Integer)
	 */

	@Override
	public OsiSubPracticeGradesDto saveOsiSubPracticeGrades(OsiSubPracticeGradesDto osiSubPracticeDto, Integer userId)
			throws DataAccessException {
		LOGGER.info("OsiSubPracticesDaoImpl :: saveOsiSubPracticeGrades :: Begin ");
		try {
			osiSubPracticeDto.setSubPracticeGradeId(null);
			osiSubPracticeDto.setCreatedDate(new Date());
			osiSubPracticeDto.setCreatedBy(userId);
			osiSubPracticeDto.setLastUpdateDate(new Date());
			osiSubPracticeDto.setUpdatedBy(userId);
			osiSubPracticeDto = osiSubPracticeGradeMapper
					.getOsiSubPracticeGradesDto(this.osiSubPracticesGradesRepository
							.save(osiSubPracticeGradeMapper.getOsiSubPracticeGrades(osiSubPracticeDto)));
		} catch (Exception e) {
			LOGGER.error("Exception occured " + e.getMessage());
			throw new DataAccessException("ERR_1000", "Error Occured while executing");
		}
		LOGGER.info("OsiSubPracticesDaoImpl :: saveOsiSubPracticeGrades :: End ");
		return osiSubPracticeDto;
	}

	@Override
	public void deleteOsiSubPracticeGradesBysubPracticeId(Integer subPracticeId) throws DataAccessException {
		LOGGER.info("OsiSubPracticesDaoImpl :: deleteOsiSubPracticeGradesBysubPracticeId :: Begin ");
		try {
			this.osiSubPracticesGradesRepository.deleteBySubPracticeId(subPracticeId);
		} catch (Exception e) {
			LOGGER.error("Exception occured " + e.getMessage());
			throw new DataAccessException("ERR_1000", "Error occured while deleting the SubPractice Grade");
		}
		LOGGER.info("OsiSubPracticesDaoImpl :: deleteOsiSubPracticeGradesBysubPracticeId :: End ");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.dao.IOsiCostCenterDao#deleteOsiCostCenter()
	 */
	@Override
	public void deleteOsiSubPracticeGrades(Integer id) throws DataAccessException {
		LOGGER.info("OsiSubPracticesDaoImpl :: deleteOsiSubPracticeGrades :: Begin ");
		try {
			this.osiSubPracticesGradesRepository.delete(id);
		} catch (Exception e) {
			LOGGER.error("Exception occured " + e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while deleting the Business Units Grades");
		}
		LOGGER.info("OsiSubPracticesDaoImpl :: deleteOsiSubPracticeGrades :: End ");
	}

	@Override
	public List<OsiSubPracticeGradesDto> getOsiSubPracticeGradesBysubPracticeId(Integer subPracticeId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		List<OsiSubPracticeGradesDto> crudOsiSubPracticeGradesDtoList = null;
		List<OsiSubPracticeGrades> osiSubPracticeEntityList = null;
		LOGGER.info("OsiSubPracticesDaoImpl :: getOsiSubPracticeGradesBysubPracticeId :: Begin");
		try {
			osiSubPracticeEntityList = osiSubPracticesGradesRepository.findBySubPracticeId(subPracticeId);
			crudOsiSubPracticeGradesDtoList = this.osiSubPracticeGradeMapper
					.getOsiSubPracticeGradesDtoList(osiSubPracticeEntityList);
		} catch (NoResultException e) {
			LOGGER.error("Exception occured " + e.getMessage());
			LOGGER.error("Exception occured " + e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception occured " + e.getMessage());
			throw new DataAccessException("ERR_1000", "Error occured while retreiving");
		} finally {
			osiSubPracticeEntityList = null;
		}
		LOGGER.info("OsiSubPracticesDaoImpl :: getOsiSubPracticeGradesBysubPracticeId :: End ");

		return crudOsiSubPracticeGradesDtoList;
	}

	@Override
	public List<OsiSubPracticeGradesHistoryDto> getOsiSubPracticeGradesHistory(Integer subPracticeId, Integer orgId,
			Integer gradeId) throws DataAccessException {
		// TODO Auto-generated method stub
		List<OsiSubPracticeGradesHistoryDto> subPracticeGradeHistoryDtoList = null;
		LOGGER.info("OsiSubPracticesDaoImpl :: getOsiSubPracticeGradesHistory :: Begin");
		try {
			subPracticeGradeHistoryDtoList = this.gradeHistoryMapper
					.getOsiSubPracticeGradesHistoryDtoList(osiSubPracticeGradesHistoryRepository
							.findByOrgIdAndSubPracticeIdAndGradeId(orgId, subPracticeId, gradeId));

		} catch (NoResultException e) {
			LOGGER.error("Exception occured " + e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception occured " + e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving BU Grades");
		}
		LOGGER.info("OsiSubPracticesDaoImpl :: getOsiSubPracticeGradesHistory :: End");
		return subPracticeGradeHistoryDtoList;
	}

	@Override
	public boolean compareWithExisting(OsiSubPracticeGradesDto osiSubPracticeGradeDto) {
		boolean isNewOrModified = false;
		try {
			if (osiSubPracticeGradeDto.getGradeId() != null) {
				OsiSubPracticeGrades existBuGradesEntity = this.osiSubPracticesGradesRepository
						.findOne(osiSubPracticeGradeDto.getGradeId());
				if (existBuGradesEntity != null
						&& existBuGradesEntity.getCostPerHour().floatValue() == osiSubPracticeGradeDto.getCostPerHour()
								.floatValue()
						&& existBuGradesEntity.getCostPerMonth().floatValue() == osiSubPracticeGradeDto
								.getCostPerMonth().floatValue()
						&& existBuGradesEntity.getInternalCostOverheadPercentage() == osiSubPracticeGradeDto
								.getInternalCostOverheadPct()
						&& existBuGradesEntity.getOrgId() == osiSubPracticeGradeDto.getOrgId()
						&& existBuGradesEntity.getGradeId() == osiSubPracticeGradeDto.getGradeId()) {
					isNewOrModified = false;
				} else
					isNewOrModified = true;
			} else
				isNewOrModified = true;
		} catch (Exception e) {

		}
		return isNewOrModified;
	}

}
