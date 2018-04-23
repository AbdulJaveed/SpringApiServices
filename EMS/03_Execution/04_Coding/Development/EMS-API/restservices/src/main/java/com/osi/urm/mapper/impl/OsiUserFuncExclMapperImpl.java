package com.osi.urm.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.urm.domain.OsiUserFuncExcl;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.mapper.OsiFunctionsMapper;
import com.osi.urm.mapper.OsiUserFuncExclMapper;
import com.osi.urm.mapper.OsiUserMapper;
import com.osi.urm.service.dto.OsiUserFuncExclDTO;
@Component
public class OsiUserFuncExclMapperImpl implements OsiUserFuncExclMapper {

    @Autowired
    private OsiFunctionsMapper osiFunctionsMapper;
    @Autowired
    private OsiUserMapper osiUserMapper;
    @Autowired
    private CommonService cs;
    

    @Override
    public OsiUserFuncExclDTO osiUserFuncExclToOsiUserFuncExclDTO(OsiUserFuncExcl osiUser) {
        if ( osiUser == null ) {
            return null;
        }

        OsiUserFuncExclDTO osiUserFuncExclDTO = new OsiUserFuncExclDTO();

        osiUserFuncExclDTO.setId( osiUser.getId() );
        osiUserFuncExclDTO.setOsiFunctions( osiFunctionsMapper.osiUserToOsiFunctionsDTO( osiUser.getOsiFunctions() ) );
     //   osiUserFuncExclDTO.setOsiUser( osiUserMapper.osiUserToOsiUserDTO( osiUser.getOsiUser() ) );
        osiUserFuncExclDTO.setBusinessGroupId( osiUser.getBusinessGroupId() ); 
        osiUserFuncExclDTO.setCreatedBy( osiUser.getCreatedBy() );
        osiUserFuncExclDTO.setCreatedDate( osiUser.getCreatedDate() );
        osiUserFuncExclDTO.setUpdatedBy( osiUser.getUpdatedBy() );
        osiUserFuncExclDTO.setUpdatedDate( osiUser.getUpdatedDate() );

        return osiUserFuncExclDTO;
    }

    @Override
    public List<OsiUserFuncExclDTO> osiUserFuncExclListToOsiUserFuncExclDTOList(List<OsiUserFuncExcl> osiUsers) {
        if ( osiUsers == null ) {
            return null;
        }

        List<OsiUserFuncExclDTO> list = new ArrayList<OsiUserFuncExclDTO>();
        for ( OsiUserFuncExcl osiUserFuncExcl : osiUsers ) {
            list.add( osiUserFuncExclToOsiUserFuncExclDTO( osiUserFuncExcl ) );
        }

        return list;
    }

    @Override
    public OsiUserFuncExcl osiUserFuncExclDTOToOsiUserFuncExcl(OsiUserFuncExclDTO osiUserDTO) throws BusinessException {
        if ( osiUserDTO == null ) {
            return null;
        }

        OsiUserFuncExcl osiUserFuncExcl = new OsiUserFuncExcl();

        if ( osiUserDTO.getId() != null ) {
        	
        	 osiUserFuncExcl.setUpdatedBy( osiUserDTO.getUpdatedBy() );
             osiUserFuncExcl.setUpdatedDate( cs.getCurrentDateinUTC() );
             osiUserFuncExcl.setId(osiUserDTO.getId());
            
        }else{
        	 osiUserFuncExcl.setCreatedBy( osiUserDTO.getUpdatedBy());
             osiUserFuncExcl.setCreatedDate( cs.getCurrentDateinUTC());
             osiUserFuncExcl.setUpdatedBy( osiUserDTO.getUpdatedBy() );
             osiUserFuncExcl.setUpdatedDate(cs.getCurrentDateinUTC());
        }
        osiUserFuncExcl.setOsiFunctions( osiFunctionsMapper.osiFunctionsDTOToOsiFunctions( osiUserDTO.getOsiFunctions() ) );
        osiUserFuncExcl.setEmployeeId(osiUserDTO.getEmployeeId());
        osiUserFuncExcl.setBusinessGroupId( osiUserDTO.getBusinessGroupId() );
        
        /*osiUserFuncExcl.setCreatedBy( osiUserDTO.getCreatedBy() );
        osiUserFuncExcl.setCreatedDate( osiUserDTO.getCreatedDate() );
        osiUserFuncExcl.setUpdatedBy( osiUserDTO.getUpdatedBy() );
        osiUserFuncExcl.setUpdatedDate( osiUserDTO.getUpdatedDate() );*/

        return osiUserFuncExcl;
    }

    @Override
    public List<OsiUserFuncExcl> osiUserFuncExclDTOListToOsiUserFuncExclList(List<OsiUserFuncExclDTO> osiUserDTO) throws BusinessException {
        if ( osiUserDTO == null ) {
            return null;
        }

        List<OsiUserFuncExcl> list = new ArrayList<OsiUserFuncExcl>();
        for ( OsiUserFuncExclDTO osiUserFuncExclDTO : osiUserDTO ) {
            list.add( osiUserFuncExclDTOToOsiUserFuncExcl( osiUserFuncExclDTO ) );
        }

        return list;
    }

	
	
}
