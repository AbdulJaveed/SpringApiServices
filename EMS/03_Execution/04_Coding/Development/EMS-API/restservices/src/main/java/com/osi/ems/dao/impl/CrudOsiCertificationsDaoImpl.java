/**
 * 
 */
package com.osi.ems.dao.impl;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.ems.dao.ICrudOsiCertificationsDao;
import com.osi.ems.domain.OsiCertifications;
import com.osi.ems.mapper.impl.CrudOsiCertificationsAssembler;
import com.osi.ems.repository.ICrudOsiCertificationsRepository;
import com.osi.ems.service.dto.CrudOsiCertificationsVO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiCertificationsDaoImpl implements ICrudOsiCertificationsDao {
	
	@Autowired
	ICrudOsiCertificationsRepository crudOsiCertificationsRepository;
	
	@Autowired
	CrudOsiCertificationsAssembler crudOsiCertificationsAssembler;
	

	private final Logger LOGGER = Logger.getLogger(CrudOsiCertificationsDaoImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCertificationsDao#getOsiCertifications(java.lang.Integer)
	 */
	@Override
	public CrudOsiCertificationsVO getOsiCertifications(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		CrudOsiCertificationsVO crudOsiCertificationsVO = null;
		OsiCertifications osicertificationsEntity = null;
		LOGGER.info("getOsiCertifications :: Begin ");
		try {
			osicertificationsEntity = crudOsiCertificationsRepository.findOne(id);
			crudOsiCertificationsVO = crudOsiCertificationsAssembler.toCrudOsiCertificationsVO(osicertificationsEntity);
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		finally {
			osicertificationsEntity = null;
		}
		LOGGER.info("getOsiCertifications :: End ");
		return crudOsiCertificationsVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCertificationsDao#saveOsiCertifications()
	 */
	@Override
	public CrudOsiCertificationsVO saveOsiCertifications(CrudOsiCertificationsVO crudOsiCertificationsVO,Integer userId) throws DataAccessException {
		LOGGER.info("saveOsiCertifications :: End ");
		try{
			CommonService cs = new CommonService();
			crudOsiCertificationsVO.setCreatedBy(userId);
			crudOsiCertificationsVO.setCreatedDate(cs.getCurrentDateinUTC());
			crudOsiCertificationsVO.setLastUpdateDate(cs.getCurrentDateinUTC());
			crudOsiCertificationsVO = crudOsiCertificationsAssembler.toCrudOsiCertificationsVO(crudOsiCertificationsRepository.save(crudOsiCertificationsAssembler.toOsiCertificationsEntity(crudOsiCertificationsVO)));
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("saveOsiCertifications :: End ");
		return crudOsiCertificationsVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCertificationsDao#updateOsiCertifications()
	 */
	@Override
	public CrudOsiCertificationsVO updateOsiCertifications(CrudOsiCertificationsVO crudOsiCertificationsVO,Integer userId) throws DataAccessException {
		// TODO Auto-generated method stub
		OsiCertifications osicertificationsEntity = null;
		OsiCertifications crudOsiCertificationsEntity = null;
		long version = 0;
		LOGGER.info("updateOsiCertifications :: Begin ");
		try{
			//version = crudOsiCertificationsVO.getVersion();
			crudOsiCertificationsEntity = crudOsiCertificationsAssembler.toOsiCertificationsEntity(crudOsiCertificationsVO);
			osicertificationsEntity = crudOsiCertificationsRepository.findOne(crudOsiCertificationsVO.getCertificationId());
			/*if(crudOsiCertificationsEntity!=null && osicertificationsEntity!=null && crudOsiCertificationsEntity.getVersion()!=osicertificationsEntity.getVersion()){
				throw new DataAccessException(4015, "A version change has been found on the data. It seems that there was an update on the data.", "Data that you are trying to save seems to be old", null);
			}*/
			CommonService cs = new CommonService();
			crudOsiCertificationsEntity.setUpdatedBy(userId);
			crudOsiCertificationsEntity.setLastUpdateDate(cs.getCurrentDateinUTC());
			
			crudOsiCertificationsVO = crudOsiCertificationsAssembler.toCrudOsiCertificationsVO(crudOsiCertificationsRepository.save(crudOsiCertificationsEntity));
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("updateOsiCertifications :: End ");
		return crudOsiCertificationsVO;
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCertificationsDao#deleteOsiCertifications()
	 */
	@Override
	public void deleteOsiCertifications(Integer id) throws DataAccessException {
		LOGGER.info("deleteOsiCertifications :: Begin ");
		try {
			crudOsiCertificationsRepository.delete(id);
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while deleting the certification");
		}
		LOGGER.info("deleteOsiCertifications :: End ");
		
	}

}
