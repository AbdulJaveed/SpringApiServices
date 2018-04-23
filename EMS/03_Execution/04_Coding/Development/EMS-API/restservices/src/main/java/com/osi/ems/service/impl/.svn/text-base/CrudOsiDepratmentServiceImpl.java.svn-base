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
import com.osi.ems.dao.ICrudOsiDepratmentDao;
import com.osi.ems.domain.OsiDeptGradesHistoryDTO;
import com.osi.ems.domain.OsiGrades;
import com.osi.ems.mapper.OsiAssignmentsMapper;
import com.osi.ems.repository.custom.ICrudOsiDeptGradesHistoryRepositoryCustom;
import com.osi.ems.repository.custom.OsiAssignmentsRepositoryCustom;
import com.osi.ems.service.ICrudOsiDepratmentService;
import com.osi.ems.service.dto.CrudOsiDepartmentVO;
import com.osi.ems.service.dto.OsiDeptGradesDTO;
import com.osi.ems.service.dto.OsiGradesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiDepratmentServiceImpl implements ICrudOsiDepratmentService {

	@Autowired
	ICrudOsiDepratmentDao crudOsiDepratmentDao;

	@Autowired
	OsiAssignmentsRepositoryCustom osiAssignmentsRepositoryCustom;

	@Autowired
	OsiAssignmentsMapper osiAssignmentsMapper;

	@Autowired
	ICrudOsiDeptGradesHistoryRepositoryCustom deptGradesHistoryRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osi.ems.service.IOsiDepratmentService#getOsiDepratment(java.lang.Integer)
	 */
	private final Logger LOGGER = Logger.getLogger(CrudOsiDepratmentServiceImpl.class);

	@Override
	public CrudOsiDepartmentVO getOsiDepratment(Integer id) throws BusinessException, DataAccessException {
		LOGGER.info("getOsiDepratment : Begin");
		CrudOsiDepartmentVO osiDepartmentVO = new CrudOsiDepartmentVO();
		try {
			osiDepartmentVO = crudOsiDepratmentDao.getOsiDepratment(id);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the data");
		}
		LOGGER.info("getOsiDepratment : End");
		return osiDepartmentVO;
	}

	@Override
	public CrudOsiDepartmentVO createOsiDepratment(CrudOsiDepartmentVO crudOsiDepratmentVO, Integer userId)
			throws BusinessException, DataAccessException {
		LOGGER.info("createOsiDepratment : Begin");
		CrudOsiDepartmentVO osiDepartmentVO = new CrudOsiDepartmentVO();
		try {
			osiDepartmentVO = crudOsiDepratmentDao.saveOsiDepratment(crudOsiDepratmentVO, userId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the department");
		}
		LOGGER.info("createOsiDepratment : End");
		return osiDepartmentVO;
	}

	@Override
	public CrudOsiDepartmentVO updateOsiDepratment(CrudOsiDepartmentVO crudOsiDepratmentVO, Integer userId)
			throws BusinessException, DataAccessException {
		LOGGER.info("updateOsiDepratment : Begin");
		CrudOsiDepartmentVO osiDepartmentVO = new CrudOsiDepartmentVO();
		try {
			osiDepartmentVO = crudOsiDepratmentDao.updateOsiDepratment(crudOsiDepratmentVO, userId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the department");
		}
		LOGGER.info("updateOsiDepratment : End");
		return osiDepartmentVO;
	}

	@Override
	public void deleteOsiDepratment(Integer id) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("deleteOsiDepratment : Begin");
		try {
			crudOsiDepratmentDao.deleteOsiDepratment(id);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the department");
		}
		LOGGER.info("deleteOsiDepratment : End");
	}

	@Override
	public OsiDeptGradesDTO createOsiDeptGrades(OsiDeptGradesDTO osiDeptGradesDTO, Integer userId)
			throws BusinessException {
		LOGGER.info("createOsiDeptGrades : Begin");
		OsiDeptGradesDTO osiDeptGrades = new OsiDeptGradesDTO();
		try {
			boolean isNewOrModified = crudOsiDepratmentDao.compareWithExisting(osiDeptGradesDTO);
			if (isNewOrModified) {
				// deleting the existing record
				if (osiDeptGradesDTO.getDeptGradeId() != null)
					crudOsiDepratmentDao.deleteOsiDeptGrades(osiDeptGradesDTO.getDeptGradeId());

				osiDeptGrades = crudOsiDepratmentDao.saveOsiDeptGrades(osiDeptGradesDTO, userId);

				OsiDeptGradesHistoryDTO deptGradesHistory = new OsiDeptGradesHistoryDTO();
				BeanUtils.copyProperties(osiDeptGrades, deptGradesHistory);
				deptGradesHistory.setCreatedBy(userId);
				deptGradesHistory.setCreationDate(new CommonService().getCurrentDateinUTC());
				deptGradesHistoryRepository.save(deptGradesHistory);
			} else
				return osiDeptGrades;
			// osiDeptGrades = crudOsiDepratmentDao.saveOsiDeptGrades(osiDeptGradesDTO,
			// userId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the department grades");
		}
		LOGGER.info("createOsiDeptGrades : End");
		return osiDeptGrades;
	}

	@Override
	public OsiDeptGradesDTO createOsiDeptGradesHistoryDuplicate(OsiDeptGradesDTO osiDeptGradesDTO, Integer userId)
			throws BusinessException {
		LOGGER.info("createOsiDeptGrades : Begin");
		Integer count = 0;
		OsiDeptGradesDTO osiDeptGrades = new OsiDeptGradesDTO();
		try {

			osiDeptGrades = crudOsiDepratmentDao.saveOsiDeptGrades(osiDeptGradesDTO, userId);

			OsiDeptGradesHistoryDTO deptGradesHistory = new OsiDeptGradesHistoryDTO();
			BeanUtils.copyProperties(osiDeptGrades, deptGradesHistory);
			deptGradesHistory.setCreatedBy(userId);
			deptGradesHistory.setCreationDate(new CommonService().getCurrentDateinUTC());
			count = deptGradesHistoryRepository.getCountOfDepartmentGradeHistory(deptGradesHistory);
			if (count == 0) {
				deptGradesHistoryRepository.save(deptGradesHistory);
			}

		} catch (

		DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the department grades");
		}
		LOGGER.info("createOsiDeptGrades : End");
		return osiDeptGrades;
	}

	@Override
	public void deleteOsiBUGradesByDeptID(Integer deptId) throws BusinessException {
		LOGGER.info("deleteOsiBUGradesByDeptID : Begin");
		try {
			crudOsiDepratmentDao.deleteOsiDeptGradesByDeptID(deptId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000",
					"Error occured while deleting the department grades by deptId : " + deptId);
		}
		LOGGER.info("deleteOsiBUGradesByDeptID : End");
	}

	@Override
	public List<OsiDeptGradesDTO> getOsiDeptGradesByDeptID(Integer deptId) throws BusinessException {

		LOGGER.info("getOsiDeptGradesByDeptID : Begin");
		List<OsiDeptGradesDTO> osiDeptDtoList = new ArrayList<OsiDeptGradesDTO>();
		try {
			osiDeptDtoList = crudOsiDepratmentDao.getOsiDeptGradesByDeptID(deptId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000",
					"Error occured while retrieving the department grades by dept id" + deptId);
		}
		LOGGER.info("createOsiDeptGrades : End");
		return osiDeptDtoList;
	}

	@Override
	public List<OsiGradesDTO> getAllGradesByOrganization(Integer orgId) throws BusinessException {
		LOGGER.info("getAllGradesByOrganization : Begin");
		List<OsiGradesDTO> dto = new ArrayList<OsiGradesDTO>();
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
			throw new BusinessException("ERR_1000",
					"Error occured while retrieving the all grades by organization" + orgId);
		}
		LOGGER.info("getAllGradesByOrganization : End");
		return dto;
	}

	@Override
	public List<OsiDeptGradesDTO> getOsiDeptGradesHistory(Integer deptId, Integer orgId, Integer gradeId)
			throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("getOsiGrades : Begin");
		List<OsiDeptGradesDTO> osiGradesVoList = new ArrayList<>();
		try {
			osiGradesVoList = crudOsiDepratmentDao.getOsiDeptGradesHistory(deptId, orgId, gradeId);
		} catch (DataAccessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the osi Dept Grades");
		}
		LOGGER.info("getOsiGrades : End");
		return osiGradesVoList;
	}
}
