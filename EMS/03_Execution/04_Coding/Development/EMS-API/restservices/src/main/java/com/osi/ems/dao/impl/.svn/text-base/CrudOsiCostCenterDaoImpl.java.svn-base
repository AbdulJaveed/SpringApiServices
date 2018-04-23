/**
 * 
 */
package com.osi.ems.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.ems.dao.ICrudOsiCostCenterDao;
import com.osi.ems.domain.OsiCCGradesHistoryDTO;
import com.osi.ems.domain.OsiCcGrades;
import com.osi.ems.domain.OsiCostCenter;
import com.osi.ems.mapper.impl.CrudOsiCostCenterAssembler;
import com.osi.ems.repository.ICrudOsiCCGradesRepository;
import com.osi.ems.repository.ICrudOsiCostCenterRepository;
import com.osi.ems.repository.custom.ICrudOsiCCGradesHistoryRepositoryCustom;
import com.osi.ems.service.OsiRollUpsService;
import com.osi.ems.service.dto.CrudOsiCostCenterVO;
import com.osi.ems.service.dto.OsiCcGradesDTO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiCostCenterDaoImpl implements ICrudOsiCostCenterDao {
	
	@Autowired
	ICrudOsiCostCenterRepository crudOsiCostCenterRepository;
	
	@Autowired
	ICrudOsiCCGradesRepository crudOsiCCGradesRepository;
	
	@Autowired
	CrudOsiCostCenterAssembler crudOsiCostCenterAssembler;
	
	@Autowired
	ICrudOsiCCGradesHistoryRepositoryCustom ccGradesHistoryRepository;
	
	private final Logger LOGGER = Logger.getLogger(CrudOsiCostCenterDaoImpl.class);
	
	@Autowired
	OsiRollUpsService osiRollUpsService;

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCostCenterDao#getOsiCostCenter(java.lang.Integer)
	 */
	@Override
	public CrudOsiCostCenterVO getOsiCostCenter(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		CrudOsiCostCenterVO crudOsiCostCenterVO = null;
		OsiCostCenter osicostcenterEntity = null;
		LOGGER.info("getOsiCostCenter :: Begin ");
		try {
			osicostcenterEntity = crudOsiCostCenterRepository.findOne(id);
			crudOsiCostCenterVO = crudOsiCostCenterAssembler.toCrudOsiCostCenterVO(osicostcenterEntity);
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		} finally {
			osicostcenterEntity = null;
		}
		LOGGER.info("getOsiCostCenter :: End ");
		return crudOsiCostCenterVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCostCenterDao#saveOsiCostCenter()
	 */
	@Override
	public CrudOsiCostCenterVO saveOsiCostCenter(CrudOsiCostCenterVO crudOsiCostCenterVO,Integer userId) throws DataAccessException {
		LOGGER.info("saveOsiCostCenter :: Begin ");
		try{
			crudOsiCostCenterVO.setCreationDate(new Date());
			crudOsiCostCenterVO.setCreatedBy(userId);
			crudOsiCostCenterVO.setLastUpdateDate(new Date());
			crudOsiCostCenterVO.setLastUpdatedBy(userId);
			crudOsiCostCenterVO = crudOsiCostCenterAssembler.toCrudOsiCostCenterVO(crudOsiCostCenterRepository.save(crudOsiCostCenterAssembler.toOsiCostCenterEntity(crudOsiCostCenterVO)));
			
		} catch(DataIntegrityViolationException e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Practice Already Exists");
		} catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		} 
		LOGGER.info("saveOsiCostCenter :: End ");
		return crudOsiCostCenterVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCostCenterDao#updateOsiCostCenter()
	 */
	@Override
	public CrudOsiCostCenterVO updateOsiCostCenter(CrudOsiCostCenterVO crudOsiCostCenterVO,Integer userId) throws DataAccessException {
		// TODO Auto-generated method stub
		OsiCostCenter osicostcenterEntity = null;
		OsiCostCenter crudOsiCostCenterEntity = null;
		String flag="0";
		Boolean continueUpdate=true;
		long version = 0;
		LOGGER.info("updateOsiCostCenter :: Begin ");
		try{
			if(crudOsiCostCenterVO.getActive()==0){
				flag=osiRollUpsService.checkActiveStatus(crudOsiCostCenterVO.getCcShortName());
				if(flag.equals("1")){
					continueUpdate=false;
				}
			}
			if(continueUpdate){
				//version = crudOsiCostCenterVO.getVersion();
				crudOsiCostCenterEntity = crudOsiCostCenterAssembler.toOsiCostCenterEntity(crudOsiCostCenterVO);
				osicostcenterEntity = crudOsiCostCenterRepository.findOne(crudOsiCostCenterVO.getCcId());
			
				crudOsiCostCenterEntity.setLastUpdateDate(new Date());
				crudOsiCostCenterEntity.setLastUpdatedBy(userId);
				crudOsiCostCenterVO = crudOsiCostCenterAssembler.toCrudOsiCostCenterVO(crudOsiCostCenterRepository.save(crudOsiCostCenterEntity));
				
			}else{
				throw new DataAccessException("ERR_1014", "This Practice is already assigned for an employee, we cannot deactivate.");
			}
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (DataAccessException e) {
			LOGGER.error("Exception occured "+e.getSystemMessage());
			throw new DataAccessException(e.getErrorCode(),e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("updateOsiCostCenter :: End ");
		return crudOsiCostCenterVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCostCenterDao#deleteOsiCostCenter()
	 */
	@Override
	public void deleteOsiCostCenter(Integer id) throws DataAccessException {
		LOGGER.info("deleteOsiCostCenter :: Begin ");
		try {
			crudOsiCostCenterRepository.delete(id);
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while deleting the cost center");
		}
		LOGGER.info("deleteOsiCostCenter :: End ");
	}

	@Override
	public OsiCcGradesDTO getOsiCCGrades(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		OsiCcGradesDTO crudOsiCCGradesDTO = null;
		OsiCcGrades osiccGradesEntity = null;
		LOGGER.info("getOsiCCGrades :: Begin ");
		try {
			osiccGradesEntity = crudOsiCCGradesRepository.findOne(id);
			crudOsiCCGradesDTO = crudOsiCostCenterAssembler.toCrudOsiCCGradesDTO(osiccGradesEntity);
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}
		catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retrieving the cc grades details");
		}
		finally {
			osiccGradesEntity = null;
		}
		LOGGER.info("getOsiCCGrades :: End ");
		return crudOsiCCGradesDTO;
	}
	
	@Override
	public OsiCcGradesDTO saveOsiCCGrades(OsiCcGradesDTO crudOsiCCGradesDTO,Integer userId) throws DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("saveOsiCCGrades :: Begin ");
		OsiCCGradesHistoryDTO ccGradesHistory = new OsiCCGradesHistoryDTO();
		try{
				crudOsiCCGradesDTO.setCcGradeId(null);
				crudOsiCCGradesDTO.setCreationDate(new Date());
				crudOsiCCGradesDTO.setCreatedBy(userId);
				crudOsiCCGradesDTO.setLastUpdateDate(new Date());
				crudOsiCCGradesDTO.setLastUpdatedBy(userId);
			crudOsiCCGradesDTO = crudOsiCostCenterAssembler.toCrudOsiCCGradesDTO(crudOsiCCGradesRepository.save(crudOsiCostCenterAssembler.toOsiCCGradesEntity(crudOsiCCGradesDTO)));
			
			/*BeanUtils.copyProperties(crudOsiCCGradesDTO, ccGradesHistory);
			ccGradesHistoryRepository.save(ccGradesHistory);*/
		} catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Error Occured while saving the CC grade");
		}
		LOGGER.info("saveOsiCCGrades :: End ");
		return crudOsiCCGradesDTO;
	}
	
	@Override
	public OsiCcGradesDTO updateOsiCostCenter(OsiCcGradesDTO osiCcGradesDTO,Integer userId) throws DataAccessException {
		LOGGER.info("updateOsiCostCenter :: Begin ");
		OsiCcGrades crudOsiCcGradesEntity = null;
		OsiCcGrades existingCcGradesEntity = null;
		OsiCCGradesHistoryDTO ccGradesHistory = new OsiCCGradesHistoryDTO();
		try{
			crudOsiCcGradesEntity = crudOsiCostCenterAssembler.toOsiCCGradesEntity(osiCcGradesDTO);
			crudOsiCcGradesEntity.setLastUpdateDate(new Date());
			crudOsiCcGradesEntity.setLastUpdatedBy(userId);
			
			existingCcGradesEntity = crudOsiCCGradesRepository.findOne(osiCcGradesDTO.getCcGradeId());
			
			// Check with existing object, if any field is modified insert to history
			if(existingCcGradesEntity.getCostPerHour().equals(osiCcGradesDTO.getCostPerHour())
					&& existingCcGradesEntity.getCostPerMonth().equals(osiCcGradesDTO.getCostPerMonth())
					&& existingCcGradesEntity.getInternalCostOverheadPct().equals(osiCcGradesDTO.getInternalCostOverheadPct())
					&& existingCcGradesEntity.getOrgId().equals(osiCcGradesDTO.getOrgId())
					&& existingCcGradesEntity.getGradeId().equals(osiCcGradesDTO.getGradeId())){
			} else {
				BeanUtils.copyProperties(crudOsiCcGradesEntity, ccGradesHistory);
				ccGradesHistory.setCreatedBy(userId);
				ccGradesHistory.setCreationDate(new CommonService().getCurrentDateinUTC());
				ccGradesHistoryRepository.save(ccGradesHistory);
			}
						
			osiCcGradesDTO = crudOsiCostCenterAssembler.toCrudOsiCCGradesDTO(crudOsiCCGradesRepository.save(crudOsiCcGradesEntity));
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating the cost center details");
		}
		LOGGER.info("updateOsiCostCenter :: End ");
		return osiCcGradesDTO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCostCenterDao#deleteOsiCostCenter()
	 */
	@Override
	public void deleteOsiCCGrades(Integer id) throws DataAccessException {
		LOGGER.info("deleteOsiCCGrades :: Begin ");
		try {
			crudOsiCCGradesRepository.delete(id);
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while deleting the cost center details");
		}	
		LOGGER.info("deleteOsiCCGrades :: End ");
	}
	
	@Override
	public List<OsiCcGradesDTO> getOsiCCGradesByCCID(Integer ccId) throws DataAccessException {
		LOGGER.info("getOsiCCGradesByCCID :: Begin ");
		List<OsiCcGradesDTO> crudOsiCCGradesDTOList = null;
		List<OsiCcGrades> osiccGradesEntityList = null;
		try {
			osiccGradesEntityList = crudOsiCCGradesRepository.findByCcId(ccId);
			crudOsiCCGradesDTOList = crudOsiCostCenterAssembler.toCrudOsiCCGradesDTOList(osiccGradesEntityList);
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving the cost center details");
		}
		finally {
			osiccGradesEntityList = null;
		}
		LOGGER.info("getOsiCCGradesByCCID :: End ");
		return crudOsiCCGradesDTOList;
	}
	
	@Override
	public void deleteOsiCCGradesByCCID(Integer id) throws DataAccessException {
		LOGGER.info("deleteOsiCCGradesByCCID :: Begin ");
		try {
			crudOsiCCGradesRepository.deleteByCcId(id);
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while deleting the cost center details");
		} 
		LOGGER.info("deleteOsiCCGradesByCCID :: End ");
		
	}
	
	@Override
	public List<OsiCcGradesDTO> getOsiCCGradesHistory(Integer ccGradeId, Integer orgId, Integer gradeId) throws DataAccessException {
		// TODO Auto-generated method stub
		List<OsiCcGradesDTO> ccGradesDTOList = null;
		LOGGER.info("getOsiGrades :: Begin");
		try {
			ccGradesDTOList=crudOsiCostCenterAssembler.toCrudOsiGradesHistDTOList(ccGradesHistoryRepository.getCCGradeHistory(ccGradeId, orgId, gradeId));
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving CC Grades");
		}
		LOGGER.info("getOsiGrades :: End");
		return ccGradesDTOList;
	}
	
	@Override
	public boolean compareWithExisting(OsiCcGradesDTO ccGrade) {
		boolean isNewOrModified = false;
		try {
			if(ccGrade.getCcGradeId() != null) {
				OsiCcGrades existCcGradesEntity = crudOsiCCGradesRepository.findOne(ccGrade.getCcGradeId());
				if(existCcGradesEntity != null 
						&& existCcGradesEntity.getCostPerHour().floatValue() == ccGrade.getCostPerHour().floatValue()
						&& existCcGradesEntity.getCostPerMonth().floatValue() == ccGrade.getCostPerMonth().floatValue()
						&& existCcGradesEntity.getInternalCostOverheadPct() == ccGrade.getInternalCostOverheadPct()
						&& existCcGradesEntity.getOrgId() == ccGrade.getOrgId()
						&& existCcGradesEntity.getGradeId() == ccGrade.getGradeId()) {
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
