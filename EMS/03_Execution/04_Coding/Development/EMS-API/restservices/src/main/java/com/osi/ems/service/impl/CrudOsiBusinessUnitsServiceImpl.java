/**
 * 
 */
package com.osi.ems.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.ems.dao.ICrudOsiBusinessUnitsDao;
import com.osi.ems.domain.OsiBUGradesHistoryDTO;
import com.osi.ems.domain.OsiGrades;
import com.osi.ems.mapper.OsiAssignmentsMapper;
import com.osi.ems.repository.custom.ICrudOsiBUGradesHistoryRepositoryCustom;
import com.osi.ems.repository.custom.OsiAssignmentsRepositoryCustom;
import com.osi.ems.service.ICrudOsiBusinessUnitsService;
import com.osi.ems.service.dto.CrudOsiBusinessUnitsVO;
import com.osi.ems.service.dto.OsiBuGradesDTO;
import com.osi.ems.service.dto.OsiGradesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiBusinessUnitsServiceImpl implements ICrudOsiBusinessUnitsService {

	@Autowired
	ICrudOsiBusinessUnitsDao crudOsiBusinessUnitsDao;

	@Autowired
	OsiAssignmentsRepositoryCustom osiAssignmentsRepositoryCustom;

	@Autowired
	OsiAssignmentsMapper osiAssignmentsMapper;

	@Autowired
	ICrudOsiBUGradesHistoryRepositoryCustom buGradesHistoryRepository;

	private Logger LOGGER = Logger.getLogger(CrudOsiBusinessUnitsServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osi.ems.service.IOsiBusinessUnitsService#getOsiBusinessUnits(java.lang.
	 * Integer)
	 */
	@Override
	public CrudOsiBusinessUnitsVO getOsiBusinessUnits(Integer id) throws BusinessException, DataAccessException {

		LOGGER.info("getOsiBusinessUnits : Begin");
		CrudOsiBusinessUnitsVO osiBusinessUnitsVO = new CrudOsiBusinessUnitsVO();
		try {
			osiBusinessUnitsVO = crudOsiBusinessUnitsDao.getOsiBusinessUnits(id);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the data");
		}
		LOGGER.info("getOsiBusinessUnits : End");
		return osiBusinessUnitsVO;
	}

	@Override
	public CrudOsiBusinessUnitsVO createOsiBusinessUnits(CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO, Integer userId)
			throws BusinessException, DataAccessException {
		LOGGER.info("createOsiBusinessUnits : Begin");
		CrudOsiBusinessUnitsVO osiBusinessUnitsVO = new CrudOsiBusinessUnitsVO();
		try {
			osiBusinessUnitsVO = crudOsiBusinessUnitsDao.saveOsiBusinessUnits(crudOsiBusinessUnitsVO, userId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while saving the data");
		}
		LOGGER.info("createOsiBusinessUnits : End");
		return osiBusinessUnitsVO;
	}

	@Override
	public CrudOsiBusinessUnitsVO updateOsiBusinessUnits(CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO, Integer userId)
			throws BusinessException, DataAccessException {
		LOGGER.info("updateOsiBusinessUnits : Begin");
		CrudOsiBusinessUnitsVO osiBusinessUnitsVO = new CrudOsiBusinessUnitsVO();
		try {
			osiBusinessUnitsVO = crudOsiBusinessUnitsDao.updateOsiBusinessUnits(crudOsiBusinessUnitsVO, userId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the data");
		}
		LOGGER.info("updateOsiBusinessUnits : End");
		return osiBusinessUnitsVO;
	}

	@Override
	public void deleteOsiBusinessUnits(Integer id) throws Exception {
		LOGGER.info("deleteOsiBusinessUnits : Begin");
		try {
			crudOsiBusinessUnitsDao.deleteOsiBusinessUnits(id);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the data");
		}
		LOGGER.info("deleteOsiBusinessUnits : End");
	}

	@Override
	public OsiBuGradesDTO createOsiBUGrades(OsiBuGradesDTO osiBuGradesDTO, Integer userId)
			throws BusinessException, DataAccessException {
		LOGGER.info("createOsiBUGrades : Begin");
		OsiBuGradesDTO osiBuGrades = new OsiBuGradesDTO();
		try {
			boolean isNewOrModified = crudOsiBusinessUnitsDao.compareWithExisting(osiBuGradesDTO);
			if (isNewOrModified) {
				// deleting the existing record
				if (osiBuGradesDTO.getBuGradeId() != null)
					crudOsiBusinessUnitsDao.deleteOsiBUGrades(osiBuGradesDTO.getBuGradeId());

				osiBuGrades = crudOsiBusinessUnitsDao.saveOsiBUGrades(osiBuGradesDTO, userId);

				OsiBUGradesHistoryDTO buGradesHistory = new OsiBUGradesHistoryDTO();
				BeanUtils.copyProperties(osiBuGrades, buGradesHistory);
				buGradesHistory.setCreatedBy(userId);
				buGradesHistory.setCreationDate(new CommonService().getCurrentDateinUTC());
				buGradesHistoryRepository.save(buGradesHistory);
			} else
				return osiBuGrades;

		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the BU");
		}
		LOGGER.info("createOsiBUGrades : End");
		return osiBuGrades;
	}

	@Override
	public OsiBuGradesDTO createOsiBUGradesWithHistoryDuplicate(OsiBuGradesDTO osiBuGradesDTO, Integer userId)
			throws BusinessException, DataAccessException {
		LOGGER.info("createOsiBUGrades : Begin");
		OsiBuGradesDTO osiBuGrades = new OsiBuGradesDTO();
		Integer count = 0;
		try {
			// deleting the existing record
			osiBuGrades = crudOsiBusinessUnitsDao.saveOsiBUGrades(osiBuGradesDTO, userId);

			OsiBUGradesHistoryDTO buGradesHistory = new OsiBUGradesHistoryDTO();
			BeanUtils.copyProperties(osiBuGrades, buGradesHistory);
			buGradesHistory.setCreatedBy(userId);
			buGradesHistory.setCreationDate(new CommonService().getCurrentDateinUTC());
			count = buGradesHistoryRepository.getBUGradeHistoryCount(buGradesHistory);
			if (count == 0) {
				buGradesHistoryRepository.save(buGradesHistory);
			}

		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the BU");
		}
		LOGGER.info("createOsiBUGrades : End");
		return osiBuGrades;
	}

	@Override
	public void deleteOsiBUGradesByBUID(Integer id) throws BusinessException, DataAccessException {
		LOGGER.info("deleteOsiBUGradesByBUID : Begin");
		try {
			crudOsiBusinessUnitsDao.deleteOsiBUGradesByBUID(id);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the BU");
		}
		LOGGER.info("deleteOsiBUGradesByBUID : End");
	}

	@Override
	public List<OsiBuGradesDTO> getOsiBUGradesByBUID(Integer buId) throws BusinessException, DataAccessException {
		List<OsiBuGradesDTO> osiBuGradesList = new ArrayList<OsiBuGradesDTO>();
		LOGGER.info("getOsiBUGradesByBUID : Begin");
		try {
			osiBuGradesList = crudOsiBusinessUnitsDao.getOsiBUGradesByBUID(buId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the BU");
		}
		LOGGER.info("getOsiBUGradesByBUID : End");
		return osiBuGradesList;
	}

	@Override
	public List<OsiGradesDTO> getAllGradesByOrganization(Integer orgId) throws BusinessException, DataAccessException {
		List<OsiGradesDTO> dto = new ArrayList<OsiGradesDTO>();
		LOGGER.info("getAllGradesByOrganization : Begin");
		try {
			List<OsiGrades> grades = osiAssignmentsRepositoryCustom.getAllGrades(orgId);
			for (OsiGrades grade : grades) {
				OsiGradesDTO dtoNew = osiAssignmentsMapper.convertOsiGradesToDTO(grade);
				dto.add(dtoNew);
			}
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the BU");
		}
		LOGGER.info("getAllGradesByOrganization : End");
		return dto;
	}

	@Override
	public List<OsiBuGradesDTO> getOsiBUGradesHistory(Integer buId, Integer orgId, Integer gradeId)
			throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("getOsiGrades : Begin");
		List<OsiBuGradesDTO> osiGradesVoList = new ArrayList<>();
		try {
			osiGradesVoList = crudOsiBusinessUnitsDao.getOsiBUGradesHistory(buId, orgId, gradeId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the osi BU Grades");
		}
		LOGGER.info("getOsiGrades : End");
		return osiGradesVoList;
	}
}
