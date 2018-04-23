/**
 * 
 */
package com.osi.ems.dao.impl;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.ems.dao.ICrudOsiSkilsDao;
import com.osi.ems.domain.OsiSkils;
import com.osi.ems.mapper.impl.CrudOsiSkilsAssembler;
import com.osi.ems.repository.ICrudOsiSkilsRepository;
import com.osi.ems.service.dto.CrudOsiSkilsVO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiSkilsDaoImpl implements ICrudOsiSkilsDao {
	
	@Autowired
	ICrudOsiSkilsRepository crudOsiSkilsRepository;
	
	@Autowired
	CrudOsiSkilsAssembler crudOsiSkilsAssembler;

	private final Logger LOGGER = Logger.getLogger(CrudOsiSkilsDaoImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiSkilsDao#getOsiSkils(java.lang.Integer)
	 */
	@Override
	public CrudOsiSkilsVO getOsiSkils(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		CrudOsiSkilsVO crudOsiSkilsVO = null;
		OsiSkils osiskilsEntity = null;
		LOGGER.info("getOsiSkils :: Begin"); 
		try {
			osiskilsEntity = crudOsiSkilsRepository.findOne(id);
			crudOsiSkilsVO = crudOsiSkilsAssembler.toCrudOsiSkilsVO(osiskilsEntity);
		} catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}finally {
			osiskilsEntity = null;
		}
		LOGGER.info("getOsiSkils :: End"); 
		return crudOsiSkilsVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiSkilsDao#saveOsiSkils()
	 */
	@Override
	public CrudOsiSkilsVO saveOsiSkils(CrudOsiSkilsVO crudOsiSkilsVO, Integer userId) throws DataAccessException {
		LOGGER.info("saveOsiSkils :: Begin"); 
		try{
			CommonService cs = new CommonService();
			crudOsiSkilsVO.setCreatedBy(userId);
			crudOsiSkilsVO.setCreatedDate(cs.getCurrentDateinUTC());
			crudOsiSkilsVO.setLastUpdateDate(cs.getCurrentDateinUTC());
			crudOsiSkilsVO = crudOsiSkilsAssembler.toCrudOsiSkilsVO(crudOsiSkilsRepository.save(crudOsiSkilsAssembler.toOsiSkilsEntity(crudOsiSkilsVO)));
		} catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("saveOsiSkils :: End"); 
		return crudOsiSkilsVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiSkilsDao#updateOsiSkils()
	 */
	@Override
	public CrudOsiSkilsVO updateOsiSkils(CrudOsiSkilsVO crudOsiSkilsVO, Integer userId) throws DataAccessException {
		// TODO Auto-generated method stub
		OsiSkils osiskilsEntity = null;
		OsiSkils crudOsiSkilsEntity = null;
		long version = 0;
		LOGGER.info("updateOsiSkils :: Begin"); 
		try{
		//	version = crudOsiSkilsVO.getVersion();
			crudOsiSkilsEntity = crudOsiSkilsAssembler.toOsiSkilsEntity(crudOsiSkilsVO);
			osiskilsEntity = crudOsiSkilsRepository.findOne(crudOsiSkilsVO.getSkillId());
			/*if(crudOsiSkilsEntity!=null && osiskilsEntity!=null && crudOsiSkilsEntity.getVersion()!=osiskilsEntity.getVersion()){
				throw new DataAccessException(4015, "A version change has been found on the data. It seems that there was an update on the data.", "Data that you are trying to save seems to be old", null);
			}*/
			CommonService cs = new CommonService();
			crudOsiSkilsEntity.setUpdatedBy(userId);
			crudOsiSkilsEntity.setLastUpdateDate(cs.getCurrentDateinUTC());
			crudOsiSkilsVO = crudOsiSkilsAssembler.toCrudOsiSkilsVO(crudOsiSkilsRepository.save(crudOsiSkilsEntity));
		
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("updateOsiSkils :: End"); 
		return crudOsiSkilsVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiSkilsDao#deleteOsiSkils()
	 */
	@Override
	public void deleteOsiSkils(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("deleteOsiSkils :: Begin"); 
		try {
			crudOsiSkilsRepository.delete(id);
		} catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while deleting");
		}
		LOGGER.info("deleteOsiSkils :: End"); 
		
	}

}
