/**
 * 
 */
package com.osi.ems.dao.impl;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.ems.dao.ICrudOsiJobCodesDao;
import com.osi.ems.domain.OsiJobCodes;
import com.osi.ems.mapper.impl.CrudOsiJobCodesAssembler;
import com.osi.ems.repository.ICrudOsiJobCodesRepository;
import com.osi.ems.service.dto.CrudOsiJobCodesVO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiJobCodesDaoImpl implements ICrudOsiJobCodesDao {
	
	@Autowired
	ICrudOsiJobCodesRepository crudOsiJobCodesRepository;
	
	@Autowired
	CrudOsiJobCodesAssembler crudOsiJobCodesAssembler;

	private final Logger LOGGER = Logger.getLogger(CrudOsiJobCodesDaoImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiJobCodesDao#getOsiJobCodes(java.lang.Integer)
	 */
	@Override
	public CrudOsiJobCodesVO getOsiJobCodes(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		CrudOsiJobCodesVO crudOsiJobCodesVO = null;
		OsiJobCodes OsiJobCodes = null;
		LOGGER.info("getOsiJobCodes :: Begin"); 
		try {
			OsiJobCodes = crudOsiJobCodesRepository.findOne(id);
			crudOsiJobCodesVO = crudOsiJobCodesAssembler.toCrudOsiJobCodesVO(OsiJobCodes);
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		finally {
			OsiJobCodes = null;
		}
		LOGGER.info("getOsiJobCodes :: End"); 
		return crudOsiJobCodesVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiJobCodesDao#saveOsiJobCodes()
	 */
	@Override
	public CrudOsiJobCodesVO saveOsiJobCodes(CrudOsiJobCodesVO crudOsiJobCodesVO,Integer userId) throws DataAccessException {
		LOGGER.info("saveOsiJobCodes :: Begin"); 
		try{
			CommonService cs = new CommonService();
			crudOsiJobCodesVO.setCreatedBy(userId);
			crudOsiJobCodesVO.setCreatedDate(cs.getCurrentDateinUTC());
			crudOsiJobCodesVO.setUpdatedBy(userId);
			crudOsiJobCodesVO.setLastUpdateDate(cs.getCurrentDateinUTC());
			crudOsiJobCodesVO = crudOsiJobCodesAssembler.toCrudOsiJobCodesVO(crudOsiJobCodesRepository.save(crudOsiJobCodesAssembler.toOsiJobCodes(crudOsiJobCodesVO)));
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("saveOsiJobCodes :: End"); 
		return crudOsiJobCodesVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiJobCodesDao#updateOsiJobCodes()
	 */
	@Override
	public CrudOsiJobCodesVO updateOsiJobCodes(CrudOsiJobCodesVO crudOsiJobCodesVO,Integer userId) throws DataAccessException {
		// TODO Auto-generated method stub
		OsiJobCodes OsiJobCodes = null;
		LOGGER.info("updateOsiJobCodes :: Begin"); 
		OsiJobCodes crudOsiJobCodes = null;
		long version = 0;
		try{
			version = crudOsiJobCodesVO.getVersion();
			crudOsiJobCodes = crudOsiJobCodesAssembler.toOsiJobCodes(crudOsiJobCodesVO);
			OsiJobCodes = crudOsiJobCodesRepository.findOne(crudOsiJobCodesVO.getJobCodeId());
			/*if(crudOsiJobCodes!=null && OsiJobCodes!=null && crudOsiJobCodes.getVersion()!=OsiJobCodes.getVersion()){
				//throw new DataAccessException(4015, "A version change has been found on the data. It seems that there was an update on the data.", "Data that you are trying to save seems to be old", null);
			}*/
			CommonService cs = new CommonService();
			crudOsiJobCodes.setUpdatedBy(userId);
			crudOsiJobCodes.setLastUpdateDate(cs.getCurrentDateinUTC());
			crudOsiJobCodesVO = crudOsiJobCodesAssembler.toCrudOsiJobCodesVO(crudOsiJobCodesRepository.save(crudOsiJobCodes));
			//if(crudOsiJobCodesVO!=null && crudOsiJobCodesVO.getVersion()==version){
				//throw new DataAccessException(4015, "A version change has been found on the data. It seems that there was an update on the data.", "Data that you are trying to save seems to be old", null);
			//}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("updateOsiJobCodes :: End"); 
		return crudOsiJobCodesVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiJobCodesDao#deleteOsiJobCodes()
	 */
	@Override
	public void deleteOsiJobCodes(Integer id) throws DataAccessException {
		LOGGER.info("deleteOsiJobCodes :: Begin"); 
		try {
			crudOsiJobCodesRepository.delete(id);
		} catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while deleting");
		}
		LOGGER.info("deleteOsiJobCodes :: End"); 
	}

}
