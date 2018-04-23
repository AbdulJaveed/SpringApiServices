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

import com.osi.ems.dao.ICrudOsiBusinessUnitsDao;
import com.osi.ems.domain.OsiBuGrades;
import com.osi.ems.domain.OsiBusinessUnits;
import com.osi.ems.mapper.impl.CrudOsiBusinessUnitsAssembler;
import com.osi.ems.mapper.impl.CrudOsiCostCenterAssembler;
import com.osi.ems.repository.ICrudOsiBUGradesRepository;
import com.osi.ems.repository.ICrudOsiBusinessUnitsRepository;
import com.osi.ems.repository.custom.ICrudOsiBUGradesHistoryRepositoryCustom;
import com.osi.ems.service.OsiRollUpsService;
import com.osi.ems.service.dto.CrudOsiBusinessUnitsVO;
import com.osi.ems.service.dto.OsiBuGradesDTO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiBusinessUnitsDaoImpl implements ICrudOsiBusinessUnitsDao {
	
	@Autowired
	ICrudOsiBusinessUnitsRepository crudOsiBusinessUnitsRepository;
	
	private final Logger LOGGER = Logger.getLogger(CrudOsiBusinessUnitsDaoImpl.class);
	
	@Autowired
	CrudOsiBusinessUnitsAssembler crudOsiBusinessUnitsAssembler;
	
	@Autowired
	OsiRollUpsService osiRollUpsService;
	
	@Autowired
	ICrudOsiBUGradesRepository crudOsiBUGradesRepository;
	
	@Autowired
	CrudOsiCostCenterAssembler crudOsiCostCenterAssembler;
	
	@Autowired
	ICrudOsiBUGradesHistoryRepositoryCustom buGradesHistoryRepository;

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiBusinessUnitsDao#getOsiBusinessUnits(java.lang.Integer)
	 */
	@Override
	public CrudOsiBusinessUnitsVO getOsiBusinessUnits(Integer id) throws DataAccessException{
		// TODO Auto-generated method stub
		CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO = null;
		OsiBusinessUnits osibusinessunitsEntity = null;
		LOGGER.info("getOsiBusinessUnits :: Begin ");
		try {
			osibusinessunitsEntity = crudOsiBusinessUnitsRepository.findOne(id);
			crudOsiBusinessUnitsVO = crudOsiBusinessUnitsAssembler.toCrudOsiBusinessUnitsVO(osibusinessunitsEntity);
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		finally {
			osibusinessunitsEntity = null;
		}
		LOGGER.info("getOsiBusinessUnits :: End ");
		return crudOsiBusinessUnitsVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiBusinessUnitsDao#saveOsiBusinessUnits()
	 */
	@Override
	public CrudOsiBusinessUnitsVO saveOsiBusinessUnits(CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO,Integer userId) throws DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("saveOsiBusinessUnits :: Begin ");
		try{
			crudOsiBusinessUnitsVO.setCreatedBy(userId);
			crudOsiBusinessUnitsVO.setCreationDate(new Date());
			crudOsiBusinessUnitsVO.setLastUpdateDate(new Date());
			crudOsiBusinessUnitsVO.setLastUpdatedBy(userId);
			crudOsiBusinessUnitsVO = crudOsiBusinessUnitsAssembler.toCrudOsiBusinessUnitsVO(crudOsiBusinessUnitsRepository.save(crudOsiBusinessUnitsAssembler.toOsiBusinessUnitsEntity(crudOsiBusinessUnitsVO)));
		} catch (DataIntegrityViolationException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Business Unit Already Exists");
		} catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Error Occured while saving business units data");
		}
		LOGGER.info("saveOsiBusinessUnits :: End ");
		return crudOsiBusinessUnitsVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiBusinessUnitsDao#updateOsiBusinessUnits()
	 */
	@Override
	public CrudOsiBusinessUnitsVO updateOsiBusinessUnits(CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO,Integer userId) throws DataAccessException {
		// TODO Auto-generated method stub
		OsiBusinessUnits osibusinessunitsEntity = null;
		OsiBusinessUnits crudOsiBusinessUnitsEntity = null;
		String flag="0";
		Boolean continueUpdate=true;
		LOGGER.info("updateOsiBusinessUnits :: Begin ");
		long version = 0;
		try{
			if(crudOsiBusinessUnitsVO.getActive()==0){
				flag=osiRollUpsService.checkActiveStatus(crudOsiBusinessUnitsVO.getBuShortName());
				if(flag.equals("1")){
					continueUpdate=false;
				}
			}
			if(continueUpdate){
				//version = crudOsiBusinessUnitsVO.getVersion();
				crudOsiBusinessUnitsEntity = crudOsiBusinessUnitsAssembler.toOsiBusinessUnitsEntity(crudOsiBusinessUnitsVO);
				osibusinessunitsEntity = crudOsiBusinessUnitsRepository.findOne(crudOsiBusinessUnitsVO.getBuId());
				/*if(crudOsiBusinessUnitsEntity!=null && osibusinessunitsEntity!=null && crudOsiBusinessUnitsEntity.getVersion()!=osibusinessunitsEntity.getVersion()){
					throw new DataAccessException(4015, "A version change has been found on the data. It seems that there was an update on the data.", "Data that you are trying to save seems to be old", null);
				}*/
				crudOsiBusinessUnitsEntity.setLastUpdateDate(new Date());
				crudOsiBusinessUnitsEntity.setLastUpdatedBy(userId);
				crudOsiBusinessUnitsVO = crudOsiBusinessUnitsAssembler.toCrudOsiBusinessUnitsVO(crudOsiBusinessUnitsRepository.save(crudOsiBusinessUnitsEntity));
			
			}else{
				throw new DataAccessException("ERR_1012", "This Business Unit is already assigned for an employee, we cannot deactivate.");
			}

		} catch (DataAccessException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1012", e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Error Occured while executing");
		}
		LOGGER.info("updateOsiBusinessUnits :: End ");
		return crudOsiBusinessUnitsVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiBusinessUnitsDao#deleteOsiBusinessUnits()
	 */
	@Override
	public void deleteOsiBusinessUnits(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("deleteOsiBusinessUnits :: Begin ");
		try {
			crudOsiBusinessUnitsRepository.delete(id);
		} catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Error Occured while deleting the business unit");
		}
		LOGGER.info("deleteOsiBusinessUnits :: End ");
	}
	

	
	@Override
	public OsiBuGradesDTO saveOsiBUGrades(OsiBuGradesDTO osiBuGradesDTO,Integer userId) throws DataAccessException{
		// TODO Auto-generated method stub
		LOGGER.info("saveOsiBUGrades :: Begin ");
		try{
			osiBuGradesDTO.setBuGradeId(null);
			osiBuGradesDTO.setCreationDate(new Date());
			osiBuGradesDTO.setCreatedBy(userId);
			osiBuGradesDTO.setLastUpdateDate(new Date());
			osiBuGradesDTO.setLastUpdatedBy(userId);
			osiBuGradesDTO = crudOsiCostCenterAssembler.toCrudOsiBuGradesDTO(crudOsiBUGradesRepository.save(crudOsiCostCenterAssembler.toOsiBuGradesEntity(osiBuGradesDTO)));
		} catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Error Occured while executing");
		} 
		LOGGER.info("saveOsiBUGrades :: End ");
		return osiBuGradesDTO;
	}
	
	@Override
	public void deleteOsiBUGradesByBUID(Integer buId) throws DataAccessException {
		LOGGER.info("deleteOsiBUGradesByBUID :: Begin ");
		try {
			crudOsiBUGradesRepository.deleteByBuId(buId);
		} catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Error occured while deleting the business unit");
		} 
		LOGGER.info("deleteOsiBUGradesByBUID :: End ");
		
	}
	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCostCenterDao#deleteOsiCostCenter()
	 */
	@Override
	public void deleteOsiBUGrades(Integer id) throws DataAccessException {
		LOGGER.info("deleteOsiBUGrades :: Begin ");
		try {
			crudOsiBUGradesRepository.delete(id);
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while deleting the Business Units Grades");
		}	
		LOGGER.info("deleteOsiBUGrades :: End ");
	}
	@Override
	public List<OsiBuGradesDTO> getOsiBUGradesByBUID(Integer ccId) throws DataAccessException {
		// TODO Auto-generated method stub
		List<OsiBuGradesDTO> crudOsiBuGradesDTOList = null;
		List<OsiBuGrades> osiBuGradesEntityList = null;
		LOGGER.info("getOsiBUGradesByBUID :: Begin");
		try {
			osiBuGradesEntityList = crudOsiBUGradesRepository.findByBuId(ccId);
			crudOsiBuGradesDTOList = crudOsiCostCenterAssembler.toCrudOsiBuGradesDTOList(osiBuGradesEntityList);
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Error occured while retreiving");
		} finally {
			osiBuGradesEntityList = null;
		}
		LOGGER.info("getOsiBUGradesByBUID :: End ");
		
		return crudOsiBuGradesDTOList;
	}
	
	@Override
	public List<OsiBuGradesDTO> getOsiBUGradesHistory(Integer buId, Integer orgId, Integer gradeId) throws DataAccessException {
		// TODO Auto-generated method stub
		List<OsiBuGradesDTO> ccGradesDTOList = null;
		LOGGER.info("getOsiGradesHistory :: Begin");
		try {
			ccGradesDTOList=crudOsiBusinessUnitsAssembler.toCrudOsiGradesHistDTOList(buGradesHistoryRepository.getBUGradeHistory(buId, orgId, gradeId));
			
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
	public boolean compareWithExisting(OsiBuGradesDTO buGrade) {
		boolean isNewOrModified = false;
		try {
			if(buGrade.getBuGradeId() != null) {
				OsiBuGrades existBuGradesEntity = crudOsiBUGradesRepository.findOne(buGrade.getBuGradeId());
				if(existBuGradesEntity != null 
						&& existBuGradesEntity.getCostPerHour().floatValue() == buGrade.getCostPerHour().floatValue()
						&& existBuGradesEntity.getCostPerMonth().floatValue() == buGrade.getCostPerMonth().floatValue()
						&& existBuGradesEntity.getInternalCostOverheadPct() == buGrade.getInternalCostOverheadPct()
						&& existBuGradesEntity.getOrgId() == buGrade.getOrgId()
						&& existBuGradesEntity.getGradeId() == buGrade.getGradeId()) {
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
