/**
 * 
 */
package com.osi.ems.dao.impl;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.ems.dao.ICrudOsiTitlesDao;
import com.osi.ems.domain.OsiTitles;
import com.osi.ems.mapper.impl.CrudOsiTitlesAssembler;
import com.osi.ems.repository.ICrudOsiTitlesRepository;
import com.osi.ems.service.dto.CrudOsiTitlesVO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiTitlesDaoImpl implements ICrudOsiTitlesDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ICrudOsiTitlesRepository crudOsiTitlesRepository;
	
	@Autowired
	CrudOsiTitlesAssembler crudOsiTitlesAssembler;

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiTitlesDao#getOsiTitles(java.lang.Integer)
	 */
	@Override
	public CrudOsiTitlesVO getOsiTitles(Integer id) throws DataAccessException {
		LOGGER.info("getOsiTitles : Begin");
		CrudOsiTitlesVO crudOsiTitlesVO = null;
		OsiTitles osititlesEntity = null;
		try {
			osititlesEntity = crudOsiTitlesRepository.findOne(id);
			crudOsiTitlesVO = crudOsiTitlesAssembler.toCrudOsiTitlesVO(osititlesEntity);
		} catch (NoResultException ne) {
			LOGGER.error("Exception : " + ne.getMessage());			
			throw new DataAccessException("ERR_1002", "No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}finally {
			osititlesEntity = null;
		}
		LOGGER.info("getOsiTitles : End");
		return crudOsiTitlesVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiTitlesDao#saveOsiTitles()
	 */
	@Override
	public CrudOsiTitlesVO saveOsiTitles(CrudOsiTitlesVO crudOsiTitlesVO,Integer userId) throws DataAccessException {
		LOGGER.info("saveOsiTitles : Begin");
		OsiTitles crudOsiTitlesEntity = null;
		try{
			crudOsiTitlesEntity = crudOsiTitlesAssembler.toOsiTitlesEntity(crudOsiTitlesVO);
			CommonService cs = new CommonService();
			crudOsiTitlesEntity.setCreationDate(cs.getCurrentDateinUTC());
			crudOsiTitlesEntity.setLastUpdateDate(cs.getCurrentDateinUTC());
			crudOsiTitlesEntity.setCreatedBy(userId);
			crudOsiTitlesVO = crudOsiTitlesAssembler.toCrudOsiTitlesVO(crudOsiTitlesRepository.save(crudOsiTitlesEntity));
		} catch (DataIntegrityViolationException e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1024", "Duplicate Title is found");
		}catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			
		}
		LOGGER.info("saveOsiTitles : End");
		return crudOsiTitlesVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiTitlesDao#updateOsiTitles()
	 */
	@Override
	public CrudOsiTitlesVO updateOsiTitles(CrudOsiTitlesVO crudOsiTitlesVO,Integer userId) throws DataAccessException {
		LOGGER.info("updateOsiTitles : Begin");
		OsiTitles osititlesEntity = null;
		OsiTitles crudOsiTitlesEntity = null;
		long version = 0;
		try{
			//version = crudOsiTitlesVO.getVersion();
			crudOsiTitlesEntity = crudOsiTitlesAssembler.toOsiTitlesEntity(crudOsiTitlesVO);
			osititlesEntity = crudOsiTitlesRepository.findOne(crudOsiTitlesVO.getTitleId());
			/*if(crudOsiTitlesEntity!=null && osititlesEntity!=null && crudOsiTitlesEntity.getVersion()!=osititlesEntity.getVersion()){
				throw new DataAccessException(4015, "A version change has been found on the data. It seems that there was an update on the data.", "Data that you are trying to save seems to be old", null);
			}*/
			CommonService cs = new CommonService();
			crudOsiTitlesEntity.setLastUpdateDate(cs.getCurrentDateinUTC());
			crudOsiTitlesEntity.setLastUpdatedBy(userId);
			crudOsiTitlesVO = crudOsiTitlesAssembler.toCrudOsiTitlesVO(crudOsiTitlesRepository.save(crudOsiTitlesEntity));
		//	System.out.println("CrudOsiTitlesDaoImpl.updateOsiTitles() crudOsiTitlesVO.getVersion() " + crudOsiTitlesVO.getVersion());
		//	System.out.println("CrudOsiTitlesDaoImpl.updateOsiTitles() version " + version);
		/*	if(crudOsiTitlesVO!=null && crudOsiTitlesVO.getVersion()==version){
		//		throw new DataAccessException(4015, "A version change has been found on the data. It seems that there was an update on the data.", "Data that you are trying to save seems to be old", null);
			}*/
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			
		}
		LOGGER.info("updateOsiTitles : End");
		return crudOsiTitlesVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiTitlesDao#deleteOsiTitles()
	 */
	@Override
	public void deleteOsiTitles(Integer id) throws DataAccessException {
		LOGGER.info("deleteOsiTitles : Begin");
		try {
			crudOsiTitlesRepository.delete(id);
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			
		}
		LOGGER.info("deleteOsiTitles : End");
		
	}

}
