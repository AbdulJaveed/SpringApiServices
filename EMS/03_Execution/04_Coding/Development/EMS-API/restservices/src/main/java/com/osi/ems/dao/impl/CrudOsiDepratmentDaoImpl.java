/**
 * 
 */
package com.osi.ems.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.osi.ems.dao.ICrudOsiDepratmentDao;
import com.osi.ems.domain.OsiDepartment;
import com.osi.ems.domain.OsiDeptGrades;
import com.osi.ems.mapper.impl.CrudOsiCostCenterAssembler;
import com.osi.ems.mapper.impl.CrudOsiDepratmentAssembler;
import com.osi.ems.repository.ICrudOsiDepratmentRepository;
import com.osi.ems.repository.ICrudOsiDeptGradesRepository;
import com.osi.ems.repository.custom.ICrudOsiDeptGradesHistoryRepositoryCustom;
import com.osi.ems.service.OsiRollUpsService;
import com.osi.ems.service.dto.CrudOsiDepartmentVO;
import com.osi.ems.service.dto.OsiDeptGradesDTO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiDepratmentDaoImpl implements ICrudOsiDepratmentDao {
	
	@Autowired
	ICrudOsiDepratmentRepository crudOsiDepratmentRepository;
	
	@Autowired
	CrudOsiDepratmentAssembler crudOsiDepratmentAssembler;
	
	@Autowired
	OsiRollUpsService osiRollUpsService;
	
	@Autowired
	ICrudOsiDeptGradesRepository crudOsiDeptGradesRepository;
	
	@Autowired
	CrudOsiCostCenterAssembler crudOsiCostCenterAssembler;
	
	@Autowired
	ICrudOsiDeptGradesHistoryRepositoryCustom deptGradesHistoryRepositoryCustom;
	private final Logger LOGGER = Logger.getLogger(CrudOsiDepratmentDaoImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiDepratmentDao#getOsiDepratment(java.lang.Integer)
	 */
	@Override
	public CrudOsiDepartmentVO getOsiDepratment(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		CrudOsiDepartmentVO crudOsiDepratmentVO = null;
		OsiDepartment osidepratmentEntity = null;
		LOGGER.info("getOsiDepratment :: Begin");
		try {
			osidepratmentEntity = crudOsiDepratmentRepository.findOne(id);
			crudOsiDepratmentVO = crudOsiDepratmentAssembler.toCrudOsiDepratmentVO(osidepratmentEntity);
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		finally {
			osidepratmentEntity = null;
		}
		LOGGER.info("getOsiDepratment :: End");
		return crudOsiDepratmentVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiDepratmentDao#saveOsiDepratment()
	 */
	@Override
	public CrudOsiDepartmentVO saveOsiDepratment(CrudOsiDepartmentVO crudOsiDepratmentVO,Integer userId) throws DataAccessException {
		LOGGER.info("saveOsiDepratment :: Begin");
		try{
			crudOsiDepratmentVO.setCreatedBy(userId);
			crudOsiDepratmentVO.setCreationDate(new Date());
			crudOsiDepratmentVO.setLastUpdateDate(new Date());
			crudOsiDepratmentVO.setLastUpdatedBy(userId);
			crudOsiDepratmentVO = crudOsiDepratmentAssembler.toCrudOsiDepratmentVO(crudOsiDepratmentRepository.save(crudOsiDepratmentAssembler.toOsiDepratmentEntity(crudOsiDepratmentVO)));
		} catch (DataIntegrityViolationException e) { 
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Department Already Exists");
		} catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("saveOsiDepratment :: End");
		return crudOsiDepratmentVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiDepratmentDao#updateOsiDepratment()
	 */
	@Override
	public CrudOsiDepartmentVO updateOsiDepratment(CrudOsiDepartmentVO crudOsiDepratmentVO,Integer userId) throws DataAccessException {
		LOGGER.info("updateOsiDepratment :: Begin");
		OsiDepartment osidepratmentEntity = null;
		OsiDepartment crudOsiDepratmentEntity = null;
		String flag="0";
		Boolean continueUpdate=true;
		long version = 0;
		try{
			if(crudOsiDepratmentVO.getActive()==0){
				flag=osiRollUpsService.checkActiveStatus(crudOsiDepratmentVO.getDeptShortName());
				if(flag.equals("1")){
					continueUpdate=false;
				}
			}
			
			if(continueUpdate){
//				version = crudOsiDepratmentVO.getVersion();
				crudOsiDepratmentEntity = crudOsiDepratmentAssembler.toOsiDepratmentEntity(crudOsiDepratmentVO);
				osidepratmentEntity = crudOsiDepratmentRepository.findOne(crudOsiDepratmentVO.getDeptId());
				/*if(crudOsiDepratmentEntity!=null && osidepratmentEntity!=null && crudOsiDepratmentEntity.getVersion()!=osidepratmentEntity.getVersion()){
					throw new DataAccessException(4015, "A version change has been found on the data. It seems that there was an update on the data.", "Data that you are trying to save seems to be old", null);
				}*/
				crudOsiDepratmentEntity.setLastUpdateDate(new Date());
				crudOsiDepratmentEntity.setLastUpdatedBy(userId);
				crudOsiDepratmentEntity = crudOsiDepratmentRepository.save(crudOsiDepratmentEntity);
				
			}else{
				throw new DataAccessException("ERR_1015", "This Department is already assigned for an employee, we cannot deactivate.");
			}
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (DataAccessException e) {
			LOGGER.error("Exception occured "+e.getSystemMessage());
			throw new DataAccessException(e.getErrorCode(),e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}	
		LOGGER.info("updateOsiDepratment :: End");
		return crudOsiDepratmentVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiDepratmentDao#deleteOsiDepratment()
	 */
	@Override
	public void deleteOsiDepratment(Integer id) throws DataAccessException {
		LOGGER.info("deleteOsiDepratment :: Begin");
		try {
			crudOsiDepratmentRepository.delete(id);
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while deleting the department");
		}	
		LOGGER.info("deleteOsiDepratment :: End");
	}
	

	
	
	@Override
	public List<OsiDeptGradesDTO> getOsiDeptGradesByDeptID(Integer deptId) throws DataAccessException {
		LOGGER.info("getOsiDeptGradesByDeptID :: Begin");
		List<OsiDeptGradesDTO> crudOsiDeptGradesDTOList = null;
		List<OsiDeptGrades> osiDeptGradesEntityList = null;
		try {
			osiDeptGradesEntityList = crudOsiDeptGradesRepository.findByDeptId(deptId);
			crudOsiDeptGradesDTOList = crudOsiCostCenterAssembler.toCrudOsiDeptGradesDTOList(osiDeptGradesEntityList);
		} catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving the department");
		} finally {
			osiDeptGradesEntityList = null;
		}
		LOGGER.info("getOsiDeptGradesByDeptID :: End");
		return crudOsiDeptGradesDTOList;
	}

	@Override
	public void deleteOsiDeptGradesByDeptID(Integer deptId) throws DataAccessException {
		LOGGER.info("deleteOsiDeptGradesByDeptID :: Begin");
		try {
			crudOsiDeptGradesRepository.deleteByDeptId(deptId);
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while deleting the department");
		}	
		LOGGER.info("deleteOsiDeptGradesByDeptID :: End");
	}
	
	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCostCenterDao#deleteOsiCostCenter()
	 */
	@Override
	public void deleteOsiDeptGrades(Integer deptGraeId) throws DataAccessException {
		LOGGER.info("deleteOsiDeptGrades :: Begin ");
		try {
			crudOsiDeptGradesRepository.delete(deptGraeId);
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while deleting the Dept Grades");
		}	
		LOGGER.info("deleteOsiDeptGrades :: End ");
	}
	@Override
	public OsiDeptGradesDTO saveOsiDeptGrades(OsiDeptGradesDTO osiDeptGradesDTO, Integer userId) throws DataAccessException {
		LOGGER.info("saveOsiDeptGrades :: Begin");
		try{
			osiDeptGradesDTO.setDeptGradeId(null);
			osiDeptGradesDTO.setCreationDate(new Date());
			osiDeptGradesDTO.setCreatedBy(userId);
			osiDeptGradesDTO.setLastUpdateDate(new Date());
			osiDeptGradesDTO.setLastUpdatedBy(userId);
			osiDeptGradesDTO = crudOsiCostCenterAssembler.toCrudOsiDeptGradesDTO(crudOsiDeptGradesRepository.save(crudOsiCostCenterAssembler.toOsiDeptGradesEntity(osiDeptGradesDTO)));
		} catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving the department");
		}	
		LOGGER.info("saveOsiDeptGrades :: End");
		return osiDeptGradesDTO;
	}
	
	@Override
	public List<OsiDeptGradesDTO> getOsiDeptGradesHistory(Integer deptId, Integer orgId, Integer gradeId) throws DataAccessException {
		// TODO Auto-generated method stub
		List<OsiDeptGradesDTO> ccGradesDTOList = null;
		LOGGER.info("getOsiGradesHistory :: Begin");
		try {
			ccGradesDTOList=crudOsiDepratmentAssembler.toCrudOsiGradesHistDTOList(deptGradesHistoryRepositoryCustom.getDeptGradeHistory(deptId, orgId, gradeId));
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving BU Grades");
		}
		LOGGER.info("getOsiGradesHistory :: End");
		return ccGradesDTOList;
	}
	
	@Override
	public boolean compareWithExisting(OsiDeptGradesDTO deptGrade) {
		boolean isNewOrModified = false;
		try {
			if(deptGrade.getDeptGradeId() != null) {
				OsiDeptGrades existDeptGradesEntity = crudOsiDeptGradesRepository.findOne(deptGrade.getDeptGradeId());
				if(existDeptGradesEntity != null 
						&& existDeptGradesEntity.getCostPerHour().floatValue() == deptGrade.getCostPerHour().floatValue()
						&& existDeptGradesEntity.getCostPerMonth().floatValue() == deptGrade.getCostPerMonth().floatValue()
						&& existDeptGradesEntity.getInternalCostOverheadPct() == deptGrade.getInternalCostOverheadPct()
						&& existDeptGradesEntity.getOrgId() == deptGrade.getOrgId()
						&& existDeptGradesEntity.getGradeId() == deptGrade.getGradeId()) {
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
