package com.osi.urm.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.urm.domain.OsiUserOperationExcl;
import com.osi.urm.mapper.OsiFunctionsMapper;
import com.osi.urm.mapper.OsiOperationsMapper;
import com.osi.urm.mapper.OsiUserMapper;
import com.osi.urm.mapper.OsiUserOperationExclMapper;
import com.osi.urm.service.dto.OsiUserOperationExclDTO;
@Component
public class OsiUserOperationExclMapperImpl implements OsiUserOperationExclMapper {

    @Autowired
    private OsiFunctionsMapper osiFunctionsMapper;
    @Autowired
    private OsiUserMapper osiUserMapper;
    @Autowired
    private OsiOperationsMapper osiOperationsMapper;

    @Override
    public OsiUserOperationExclDTO osiUserToOsiUserDTO(OsiUserOperationExcl osiUser) {
        if ( osiUser == null ) {
            return null;
        }

        OsiUserOperationExclDTO osiUserOperationExclDTO = new OsiUserOperationExclDTO();

        osiUserOperationExclDTO.setId( osiUser.getId() );
        osiUserOperationExclDTO.setOsiFunctions( osiFunctionsMapper.osiUserToOsiFunctionsDTO( osiUser.getOsiFunctions() ) );
     //   osiUserOperationExclDTO.setOsiUser( osiUserMapper.osiUserToOsiUserDTO( osiUser.getOsiUser() ) );
        osiUserOperationExclDTO.setOsiOperations( osiOperationsMapper.osiOperationsToOsiOperationsDTO( osiUser.getOsiOperations()));
        /*osiUserOperationExclDTO.setStartDate( osiUser.getStartDate() );
        osiUserOperationExclDTO.setEndDate( osiUser.getEndDate() );
        osiUserOperationExclDTO.setOperation( osiUser.getOperation() );*/
        osiUserOperationExclDTO.setBusinessGroupId( osiUserOperationExclDTO.getBusinessGroupId() );
        osiUserOperationExclDTO.setCreatedBy( osiUser.getCreatedBy() );
        osiUserOperationExclDTO.setCreatedDate( osiUser.getCreatedDate() );
        osiUserOperationExclDTO.setUpdatedBy( osiUser.getUpdatedBy() );
        osiUserOperationExclDTO.setUpdatedDate( osiUser.getUpdatedDate() );

        return osiUserOperationExclDTO;
    }

    @Override
    public List<OsiUserOperationExclDTO> osiUserListToOsiUserDTOList(List<OsiUserOperationExcl> osiUsers) {
        if ( osiUsers == null ) {
            return null;
        }

        List<OsiUserOperationExclDTO> list = new ArrayList<OsiUserOperationExclDTO>();
        for ( OsiUserOperationExcl osiUserOperationExcl : osiUsers ) {
            list.add( osiUserToOsiUserDTO( osiUserOperationExcl ) );
        }

        return list;
    }

    @Override
    public OsiUserOperationExcl osiUserDTOToOsiUser(OsiUserOperationExclDTO osiUserDTO) {
        if ( osiUserDTO == null ) {
            return null;
        }

        OsiUserOperationExcl osiUserOperationExcl = new OsiUserOperationExcl();

        if ( osiUserDTO.getId() != null ) {
            osiUserOperationExcl.setId( osiUserDTO.getId() );
        }
        osiUserOperationExcl.setOsiFunctions( osiFunctionsMapper.osiFunctionsDTOToOsiFunctions( osiUserDTO.getOsiFunctions() ) );
        /*osiUserOperationExcl.setOsiUser( osiUserMapper.osiUserDTOToOsiUser( osiUserDTO.getOsiUser() ) );
        */
        osiUserOperationExcl.setEmployeeId(osiUserDTO.getEmployeeId());
        osiUserOperationExcl.setOsiOperations( osiOperationsMapper.osiOperationsDTOToOsiOperations( osiUserDTO.getOsiOperations() ));
       /* osiUserOperationExcl.setStartDate( osiUserDTO.getStartDate() );
        osiUserOperationExcl.setEndDate( osiUserDTO.getEndDate() );
        osiUserOperationExcl.setOperation( osiUserDTO.getOperation() );*/
        osiUserOperationExcl.setBusinessGroupId( osiUserDTO.getBusinessGroupId() );
        osiUserOperationExcl.setCreatedBy( osiUserDTO.getCreatedBy() );
        osiUserOperationExcl.setCreatedDate( osiUserDTO.getCreatedDate() );
        osiUserOperationExcl.setUpdatedBy( osiUserDTO.getUpdatedBy() );
        osiUserOperationExcl.setUpdatedDate( osiUserDTO.getUpdatedDate() );

        return osiUserOperationExcl;
    }

    @Override
    public List<OsiUserOperationExcl> osiUserDTOListToOsiUserList(List<OsiUserOperationExclDTO> osiUserDTO) {
        if ( osiUserDTO == null ) {
            return null;
        }

        List<OsiUserOperationExcl> list = new ArrayList<OsiUserOperationExcl>();
        for ( OsiUserOperationExclDTO osiUserOperationExclDTO : osiUserDTO ) {
            list.add( osiUserDTOToOsiUser( osiUserOperationExclDTO ) );
        }

        return list;
    }
}
