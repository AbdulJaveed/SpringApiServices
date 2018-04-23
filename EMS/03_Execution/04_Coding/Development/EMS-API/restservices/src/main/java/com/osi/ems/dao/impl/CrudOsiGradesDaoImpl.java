/**
 * 
 */
package com.osi.ems.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.ems.dao.ICrudOsiGradesDao;
import com.osi.ems.domain.OsiGrades;
import com.osi.ems.domain.OsiGradesHistory;
import com.osi.ems.domain.OsiTitleGrades;
import com.osi.ems.mapper.impl.CrudOsiGradesAssembler;
import com.osi.ems.mapper.impl.CrudOsiTitleGradesAssembler;
import com.osi.ems.repository.ICrudOsiGradesRepository;
import com.osi.ems.repository.ICrudOsiTitleGradesRepository;
import com.osi.ems.repository.custom.ICrudOsiGradesHistoryRepository;
import com.osi.ems.service.dto.CrudOsiGradesVO;
import com.osi.ems.service.dto.OsiTitleGradesDTO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiGradesDaoImpl implements ICrudOsiGradesDao {
	
	@PersistenceContext
    EntityManager entityManager;

	@Autowired
	ICrudOsiGradesRepository crudOsiGradesRepository;
	
	@Autowired
	ICrudOsiTitleGradesRepository crudOsiTitleGradesRepository;
	
	@Autowired
	ICrudOsiGradesHistoryRepository crudOsiTitleGradesHistoryRepository;
	
	@Autowired
	CrudOsiGradesAssembler crudOsiGradesAssembler;

	@Autowired
	CrudOsiTitleGradesAssembler crudOsiTitleGradesAssembler;
	private final Logger LOGGER = Logger.getLogger(CrudOsiGradesDaoImpl.class);
	@Override
	public CrudOsiGradesVO getOsiGrades(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		CrudOsiGradesVO crudOsiGradesVO = null;
		LOGGER.info("getOsiGrades :: Begin");
		OsiTitleGradesDTO OsiGrades = null;
		try {
			crudOsiGradesVO = getGrades(id);
			//crudOsiGradesVO = crudOsiGradesAssembler.toCrudOsiGradesVO(OsiGrades);
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("getOsiGrades :: End");
		return crudOsiGradesVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiGradesDao#saveOsiGrades()
	 */
	@Override
	public CrudOsiGradesVO saveOsiGrades(CrudOsiGradesVO crudOsiGradesVO,Integer userId) throws DataAccessException {
		// TODO Auto-generated method stub
		OsiTitleGrades osiTitleGrades = new OsiTitleGrades();
		OsiTitleGradesDTO osiTitleGradesDTO = new OsiTitleGradesDTO();
		OsiGrades crudOsiGrades = null;
		OsiGradesHistory crudOsiGradesHistory = new OsiGradesHistory();
		LOGGER.info("saveOsiGrades :: Begin");
		try{
			CommonService cs = new CommonService();
			crudOsiGrades = crudOsiGradesAssembler.toOsiGrades(crudOsiGradesVO);
			osiTitleGrades.setGrades(crudOsiGrades);
			osiTitleGrades.setTitleId(crudOsiGradesVO.getTitleId());
			osiTitleGrades.setCreatedBy(userId);
			osiTitleGrades.setCreationDate(cs.getCurrentDateinUTC());
			osiTitleGrades.setLastUpdateDate(cs.getCurrentDateinUTC());
			osiTitleGrades.setLastUpdatedBy(userId);
			crudOsiGrades.setCreatedDate(cs.getCurrentDateinUTC());
			crudOsiGrades.setCreatedBy(userId);
			crudOsiGrades.setLastUpdateDate(cs.getCurrentDateinUTC());
			crudOsiGrades.setUpdatedBy(userId);
			osiTitleGradesDTO = crudOsiTitleGradesAssembler.toOsiTitleGradesDTO(crudOsiTitleGradesRepository.save(osiTitleGrades));
			BeanUtils.copyProperties(crudOsiGrades, crudOsiGradesHistory);
			crudOsiTitleGradesHistoryRepository.save(crudOsiGradesHistory);
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("saveOsiGrades :: End");
		return crudOsiGradesVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiGradesDao#updateOsiGrades()
	 */
	@Override
	public CrudOsiGradesVO updateOsiGrades(CrudOsiGradesVO crudOsiGradesVO,Integer userId) throws DataAccessException {
		// TODO Auto-generated method stub
		OsiGrades existingOsiGrade = null;
		OsiGrades crudOsiGrades = null;
		OsiTitleGrades osiTitleGrades = new OsiTitleGrades();
		OsiTitleGradesDTO osiTitleGradesDTO = new OsiTitleGradesDTO();
		OsiGradesHistory crudOsiGradesHistory = new OsiGradesHistory();
		LOGGER.info("saveOsiGrades :: Begin");
		long version = 0;
		try{
		//	version = crudOsiGradesVO.getVersion();
			crudOsiGrades = crudOsiGradesAssembler.toOsiGrades(crudOsiGradesVO);
			osiTitleGrades.setTitleGradeId(crudOsiGradesVO.getTitleGradeId());
			osiTitleGrades.setGrades(crudOsiGrades);
			existingOsiGrade = crudOsiGradesRepository.findOne(crudOsiGradesVO.getGradeId());
			
			CommonService cs = new CommonService();
			osiTitleGrades.setTitleId(crudOsiGradesVO.getTitleId());
			osiTitleGrades.setLastUpdateDate(cs.getCurrentDateinUTC());
			osiTitleGrades.setLastUpdatedBy(userId);
			crudOsiGrades.setLastUpdateDate(cs.getCurrentDateinUTC());
			crudOsiGrades.setUpdatedBy(userId);
			osiTitleGrades.setGrades(crudOsiGrades);
			boolean isModified = false;
			// Check with existing object, if any field is modified insert to history
			if(existingOsiGrade.getCostPerHour().equals(crudOsiGradesVO.getCostPerHour())
					&& existingOsiGrade.getCostPerMonth().equals(crudOsiGradesVO.getCostPerMonth())
					&& existingOsiGrade.getRevPerHour().equals(crudOsiGradesVO.getRevPerHour())
					&& existingOsiGrade.getRevPerMonth().equals(crudOsiGradesVO.getRevPerMonth()) ){
				isModified = false;
			} else {
				isModified = true;
				BeanUtils.copyProperties(crudOsiGrades, crudOsiGradesHistory);
				crudOsiGradesHistory.setCreatedBy(userId);
				crudOsiGradesHistory.setCreatedDate(cs.getCurrentDateinUTC());
				crudOsiTitleGradesHistoryRepository.save(crudOsiGradesHistory);
			}
			//crudOsiGradesVO = crudOsiGradesAssembler.toCrudOsiGradesVO(crudOsiGradesRepository.save(crudOsiGrades));
			osiTitleGradesDTO = crudOsiTitleGradesAssembler.toOsiTitleGradesDTO(crudOsiTitleGradesRepository.save(osiTitleGrades));
			
		}
		catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("saveOsiGrades :: End");
		return crudOsiGradesVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiGradesDao#deleteOsiGrades()
	 */
	@Override
	public void deleteOsiGrades(Integer id) throws DataAccessException {
		LOGGER.info("deleteOsiGrades :: Begin");
		try {
			crudOsiGradesRepository.delete(id);
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while deleting");
		}
		LOGGER.info("deleteOsiGrades :: End");
		
	}
	

	public CrudOsiGradesVO getGrades(Integer gradeId) throws DataAccessException {
		
		CrudOsiGradesVO gradesVo=new CrudOsiGradesVO();
		OsiTitleGrades results;
		LOGGER.info("getGrades :: Begin");
		try {
			String sql = "select osiTitleGrades from OsiTitleGrades osiTitleGrades where osiTitleGrades.grades.gradeId=?";
			Query query = entityManager.createQuery(sql);
			query.setParameter(1, gradeId);
			results = (OsiTitleGrades)query.getSingleResult();
			
			
			OsiTitleGradesDTO osigrades=new OsiTitleGradesDTO();
			
			gradesVo=crudOsiGradesAssembler.toCrudOsiGradesVO(results.getGrades());
			gradesVo.setTitleGradeId(results.getTitleGradeId());
			gradesVo.setTitleId(results.getTitleId());
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("getGrades :: End");
		
		return gradesVo;
	}

	@Override
	public List<CrudOsiGradesVO> getOsiGradesHistory(Integer id, Integer orgId) throws DataAccessException {
		// TODO Auto-generated method stub
		List<CrudOsiGradesVO> crudOsiGradesVOList = null;
		LOGGER.info("getOsiGrades :: Begin");
		try {
			crudOsiGradesVOList=crudOsiGradesAssembler.toCrudOsiGradesHistVOList(crudOsiTitleGradesHistoryRepository.getGradeHistory(id, orgId));
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("getOsiGrades :: End");
		return crudOsiGradesVOList;
	}

}
