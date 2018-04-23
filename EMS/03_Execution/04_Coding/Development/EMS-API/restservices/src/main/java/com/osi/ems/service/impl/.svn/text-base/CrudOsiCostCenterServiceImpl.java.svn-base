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
import com.osi.ems.dao.ICrudOsiCostCenterDao;
import com.osi.ems.domain.OsiCCGradesHistoryDTO;
import com.osi.ems.domain.OsiGrades;
import com.osi.ems.mapper.OsiAssignmentsMapper;
import com.osi.ems.repository.custom.ICrudOsiCCGradesHistoryRepositoryCustom;
import com.osi.ems.repository.custom.OsiAssignmentsRepositoryCustom;
import com.osi.ems.service.ICrudOsiCostCenterService;
import com.osi.ems.service.dto.CrudOsiCostCenterVO;
import com.osi.ems.service.dto.OsiCcGradesDTO;
import com.osi.ems.service.dto.OsiGradesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiCostCenterServiceImpl implements ICrudOsiCostCenterService {
	@Autowired
	ICrudOsiCostCenterDao crudOsiCostCenterDao;

	@Autowired
	OsiAssignmentsRepositoryCustom osiAssignmentsRepositoryCustom;

	@Autowired
	OsiAssignmentsMapper osiAssignmentsMapper;

	@Autowired
	ICrudOsiCCGradesHistoryRepositoryCustom ccGradesHistoryRepository;

	private final Logger LOGGER = Logger.getLogger(CrudOsiCostCenterServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osi.ems.service.IOsiCostCenterService#getOsiCostCenter(java.lang.Integer)
	 */
	@Override
	public CrudOsiCostCenterVO getOsiCostCenter(Integer id) throws BusinessException, DataAccessException {
		LOGGER.info("getOsiCostCenter : Begin");
		CrudOsiCostCenterVO osiCostCenterVO = new CrudOsiCostCenterVO();
		try {
			osiCostCenterVO = crudOsiCostCenterDao.getOsiCostCenter(id);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the data");
		}
		LOGGER.info("getOsiCostCenter : End");
		return osiCostCenterVO;
	}

	@Override
	public CrudOsiCostCenterVO createOsiCostCenter(CrudOsiCostCenterVO crudOsiCostCenterVO, Integer userId)
			throws BusinessException, DataAccessException {
		LOGGER.info("createOsiCostCenter : Begin");
		CrudOsiCostCenterVO osiCostCenterVO = new CrudOsiCostCenterVO();
		try {
			osiCostCenterVO = crudOsiCostCenterDao.saveOsiCostCenter(crudOsiCostCenterVO, userId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the data");
		}
		LOGGER.info("createOsiCostCenter : End");
		return osiCostCenterVO;
	}

	@Override
	public CrudOsiCostCenterVO updateOsiCostCenter(CrudOsiCostCenterVO crudOsiCostCenterVO, Integer userId)
			throws BusinessException, DataAccessException {
		LOGGER.info("updateOsiCostCenter : Begin");
		CrudOsiCostCenterVO osiCostCenterVO = new CrudOsiCostCenterVO();
		try {
			osiCostCenterVO = crudOsiCostCenterDao.updateOsiCostCenter(crudOsiCostCenterVO, userId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the data");
		}
		LOGGER.info("updateOsiCostCenter : End");
		return osiCostCenterVO;
	}

	@Override
	public void deleteOsiCostCenter(Integer id) throws BusinessException {
		LOGGER.info("deleteOsiCostCenter : Begin");
		try {
			crudOsiCostCenterDao.deleteOsiCostCenter(id);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the data");
		}
		LOGGER.info("deleteOsiCostCenter : End");
	}

	@Override
	public OsiCcGradesDTO getOsiCCGrades(Integer id) throws BusinessException, DataAccessException {
		LOGGER.info("getOsiCCGrades : Begin");
		OsiCcGradesDTO ccGradesDTO = new OsiCcGradesDTO();
		try {
			ccGradesDTO = crudOsiCostCenterDao.getOsiCCGrades(id);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the data");
		}
		LOGGER.info("deleteOsiCostCenter : End");
		return ccGradesDTO;
	}

	@Override
	public OsiCcGradesDTO createOsiCCGrades(OsiCcGradesDTO osiCCGradesDTO, Integer userId) throws BusinessException {

		LOGGER.info("createOsiCCGrades : Begin");
		OsiCcGradesDTO ccGradesDTO = new OsiCcGradesDTO();
		try {
			boolean isNewOrModified = crudOsiCostCenterDao.compareWithExisting(osiCCGradesDTO);
			if (isNewOrModified) {
				// deleting the existing record
				if (osiCCGradesDTO.getCcGradeId() != null)
					crudOsiCostCenterDao.deleteOsiCCGrades(osiCCGradesDTO.getCcGradeId());

				ccGradesDTO = crudOsiCostCenterDao.saveOsiCCGrades(osiCCGradesDTO, userId);

				OsiCCGradesHistoryDTO ccGradesHistory = new OsiCCGradesHistoryDTO();
				BeanUtils.copyProperties(ccGradesDTO, ccGradesHistory);
				ccGradesHistory.setCreatedBy(userId);
				ccGradesHistory.setCreationDate(new CommonService().getCurrentDateinUTC());
				ccGradesHistoryRepository.save(ccGradesHistory);
			} else
				return osiCCGradesDTO;

		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the data");
		}
		LOGGER.info("createOsiCCGrades : End");
		return ccGradesDTO;
	}

	@Override
	public OsiCcGradesDTO createOsiCCGradesHistoryDuplicate(OsiCcGradesDTO osiCCGradesDTO, Integer userId)
			throws BusinessException {

		LOGGER.info("createOsiCCGrades : Begin");
		OsiCcGradesDTO ccGradesDTO = new OsiCcGradesDTO();
		Integer count = 0;
		try {
			// deleting the existing record

			ccGradesDTO = crudOsiCostCenterDao.saveOsiCCGrades(osiCCGradesDTO, userId);

			OsiCCGradesHistoryDTO ccGradesHistory = new OsiCCGradesHistoryDTO();
			BeanUtils.copyProperties(ccGradesDTO, ccGradesHistory);
			ccGradesHistory.setCreatedBy(userId);
			ccGradesHistory.setCreationDate(new CommonService().getCurrentDateinUTC());
			count = ccGradesHistoryRepository.getCCGradeHistoryCount(ccGradesHistory);
			if (count == 0) {
				ccGradesHistoryRepository.save(ccGradesHistory);
			}

		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the data");
		}
		LOGGER.info("createOsiCCGrades : End");
		return ccGradesDTO;
	}

	@Override
	public OsiCcGradesDTO updateOsiCCGrades(OsiCcGradesDTO osiCCGradesDTO, Integer userId) throws BusinessException {
		LOGGER.info("updateOsiCCGrades : Begin");
		OsiCcGradesDTO ccGradesDTO = new OsiCcGradesDTO();
		try {
			ccGradesDTO = crudOsiCostCenterDao.updateOsiCostCenter(osiCCGradesDTO, userId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the data");
		}
		LOGGER.info("updateOsiCCGrades : End");
		return ccGradesDTO;
	}

	@Override
	public void deleteOsiCCGrades(Integer id) throws BusinessException {
		LOGGER.info("deleteOsiCCGrades : Begin ");
		try {
			crudOsiCostCenterDao.deleteOsiCCGrades(id);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the data");
		}
		LOGGER.info("deleteOsiCCGrades : End");
	}

	@Override
	public void deleteOsiCCGradesByCCID(Integer id) throws BusinessException {
		LOGGER.info("deleteOsiCCGradesByCCID : Begin ");
		try {
			crudOsiCostCenterDao.deleteOsiCCGradesByCCID(id);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the data");
		}
		LOGGER.info("deleteOsiCCGradesByCCID : End");
	}

	@Override
	public List<OsiCcGradesDTO> getOsiCCGradesByCCID(Integer ccId) throws BusinessException {
		LOGGER.info("getOsiCCGradesByCCID : Begin ");
		List<OsiCcGradesDTO> osiCcGradesList = new ArrayList<OsiCcGradesDTO>();
		try {
			osiCcGradesList = crudOsiCostCenterDao.getOsiCCGradesByCCID(ccId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the data");
		}
		LOGGER.info("getOsiCCGradesByCCID : End");
		return osiCcGradesList;
	}

	@Override
	public List<OsiGradesDTO> getAllGradesByOrganization(Integer orgId) throws BusinessException {
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
			throw new BusinessException("ERR_1000", "Error occured while retreiving the data");
		}
		LOGGER.info("getOsiCCGradesByCCID : End");
		return dto;
	}

	@Override
	public List<OsiCcGradesDTO> getOsiCCGradesHistory(Integer garedId, Integer orgId, Integer gradeId)
			throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("getOsiGrades : Begin");
		List<OsiCcGradesDTO> osiGradesVoList = new ArrayList<>();
		try {
			osiGradesVoList = crudOsiCostCenterDao.getOsiCCGradesHistory(garedId, orgId, gradeId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the osi Cc Grades");
		}
		LOGGER.info("getOsiGrades : End");
		return osiGradesVoList;
	}
}
