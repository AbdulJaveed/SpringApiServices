package com.osi.urm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.ems.repository.custom.OsiEmployeesRepositoryCustom;
import com.osi.urm.domain.OsiRespUser;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.mapper.OsiRespUserMapper;
import com.osi.urm.repository.OsiOperationsRepository;
import com.osi.urm.repository.OsiRespUserRepository;
import com.osi.urm.service.OsiRespUserService;
import com.osi.urm.service.OsiUserFuncExclService;
import com.osi.urm.service.dto.OsiRespUserDTO;

/**
 * Service Implementation for managing OsiUser.
 */
@Service
@Transactional
public class OsiRespUserServiceImpl implements OsiRespUserService{

    private final Logger log = LoggerFactory.getLogger(OsiRespUserServiceImpl.class);
    
    @Autowired
    private OsiRespUserRepository osiRespUserRepository;
    
    @Autowired
    private OsiRespUserMapper osiRespUserMapper;
    
    @Autowired
    OsiEmployeesRepositoryCustom osiEmployeesRepository;
    
    @Autowired
	private OsiOperationsRepository osiOperationsRepository;
    
    @Autowired
    private OsiUserFuncExclService osiUserFuncExclService;
    
    @Autowired
    private CommonService cs;
    
	@Value( "${DEFAULT_END_DATE}" )
	private String defaultEndDate;
    /**
     * Save a osiUser.
     *
     * @param OsiUser the entity to save
     * @return the persisted entity
     */
    public OsiRespUserDTO save(OsiRespUser osiUser){
        log.debug("Request to save OsiUser : {}", osiUser);
        /*if(osiUser.getId() != null) {
        	osiUser.setId(osiUser.getId());
        	osiUser.setUpdatedBy(osiUser.getUpdatedBy());
        	osiUser.setUpdatedDate(new Date());
        	return osiRespUserRepository.updateUser(osiUser);
        }else{*/
        try{
        	
        	if(osiUser.getId() != null){
        		osiUser.setUpdatedDate(cs.getCurrentDateinUTC());
        	}else{
        		osiUser.setCreatedBy(osiUser.getUpdatedBy());
            	osiUser.setCreatedDate(cs.getCurrentDateinUTC());
            	osiUser.setUpdatedDate(cs.getCurrentDateinUTC());
        	}
        	SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        	osiUser.setStartDate(cs.convertDateStringToUTC(osiUser.getStartDate(),osiUser.getUpdatedBy() ));
        	if(!sDate.parse(osiUser.getEndDate()).equals(sDate.parseObject(defaultEndDate)))
        		osiUser.setEndDate(cs.convertDateStringToUTC(osiUser.getEndDate(),osiUser.getUpdatedBy()));
        	
        	OsiRespUser result1 = osiRespUserRepository.save(osiUser);
        	OsiRespUserDTO result = osiRespUserMapper.osiUserToOsiUserDTO(result1);
        	return result;
        }catch(Exception e){
        	e.printStackTrace();
        }
        /*}*/
		return null;	
    }
    
    
	@Override
	public List<OsiRespUserDTO> getById(Integer employeeId, Integer businessId) throws BusinessException {
		//List<OsiRespUser> list = osiRespUserRepository.findByOsiUser(osiUser);
		List<OsiRespUserDTO> listDto = null;
		try {
			List<OsiRespUser> list  = new ArrayList<OsiRespUser>();
			System.out.println(osiRespUserRepository.findByEmployeeId(employeeId).size());
			list = osiRespUserRepository.findByEmployeeId(employeeId);
			listDto = new ArrayList<OsiRespUserDTO>();
			SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			OsiRespUserDTO osiRespUserDTO = null;
			    for ( OsiRespUser osiRespUser : list ) {
			    	osiRespUserDTO = new OsiRespUserDTO();
			    	osiRespUserDTO = osiRespUserMapper.osiUserToOsiUserDTO( osiRespUser );
			    	osiRespUserDTO.setStartDate(cs.convertUTCDateToLocaleString(osiRespUserDTO.getStartDate(), businessId));
			    	if(!sDate.parse(osiRespUser.getEndDate()).equals(sDate.parseObject(defaultEndDate)))
			    		osiRespUserDTO.setEndDate(cs.convertUTCDateToLocaleString(osiRespUserDTO.getEndDate(), businessId));
			    	else{
			    		osiRespUserDTO.setEndDate(defaultEndDate);
			    	}
			    	listDto.add( osiRespUserDTO);
			    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listDto;
	}


	@Override
	public void deleteRespUser(Integer id)  throws BusinessException {
		// TODO Auto-generated method stub
		osiRespUserRepository.delete(id);
	}
	
	@Override
	public void deleteOperationsExclusionByUserId(Integer userId) throws BusinessException {
		try {
			osiOperationsRepository.deleteByUserId(userId);
		} catch (DataAccessException e) {
			log.error("Exception Occured while deleting the excluded operation for the user : "+e.getSystemMessage());
			throw new BusinessException("ERR_1030", "Exception Occured while deleting the excluded operation for the user"+e.getSystemMessage());
		}
	}
	
	@Override
	public void deleteFuncExclusionByUserId(Integer userId) throws BusinessException {
		try {
			osiOperationsRepository.deleteFuncExclByUserId(userId);
		} catch (DataAccessException e) {
			log.error("Exception Occured while deleting the excluded functions for the user : "+e.getSystemMessage());
			throw new BusinessException("ERR_1030", "Exception Occured while deleting the excluded functions for the user"+e.getSystemMessage());
		}
	}
    
}
