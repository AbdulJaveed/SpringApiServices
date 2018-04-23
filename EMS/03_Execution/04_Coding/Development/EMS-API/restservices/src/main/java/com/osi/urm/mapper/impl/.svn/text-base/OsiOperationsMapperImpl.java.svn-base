package com.osi.urm.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.urm.domain.OsiOperations;
import com.osi.urm.mapper.OsiFunctionsMapper;
import com.osi.urm.mapper.OsiOperationsMapper;
import com.osi.urm.service.dto.OsiOperationsDTO;
@Component
public class OsiOperationsMapperImpl implements OsiOperationsMapper {

    @Autowired
    private OsiFunctionsMapper osiFunctionsMapper;

    @Override
    public OsiOperationsDTO osiOperationsToOsiOperationsDTO(OsiOperations osiOperations) {
        if ( osiOperations == null ) {
            return null;
        }

        OsiOperationsDTO osiOperationsDTO = new OsiOperationsDTO();

        osiOperationsDTO.setId( osiOperations.getId() );
       /* osiOperationsDTO.setOsiFunctions( osiFunctionsMapper.osiUserToOsiFunctionsDTO( osiOperations.getOsiFunctions() ) );
        osiOperationsDTO.setOpType( osiOperations.getOpType() );
        osiOperationsDTO.setOpValue( osiOperations.getOpValue() );
        osiOperationsDTO.setParameters( osiOperations.getParameters() );*/
        osiOperationsDTO.setName( osiOperations.getName() );
        osiOperationsDTO.setBusinessGroupId( osiOperations.getBusinessGroupId() );
        osiOperationsDTO.setActive( osiOperations.getActive() );
        osiOperationsDTO.setCreatedBy( osiOperations.getCreatedBy() );
        osiOperationsDTO.setCreatedDate( osiOperations.getCreatedDate() );
        osiOperationsDTO.setUpdatedBy( osiOperations.getUpdatedBy() );
        osiOperationsDTO.setUpdatedDate( osiOperations.getUpdatedDate() );

        return osiOperationsDTO;
    }

    @Override
    public List<OsiOperationsDTO> osiOperationsListToOsiOperationsDTOList(List<OsiOperations> osiOperations) {
        if ( osiOperations == null ) {
            return null;
        }

        List<OsiOperationsDTO> list = new ArrayList<OsiOperationsDTO>();
        for ( OsiOperations osiOperations_ : osiOperations ) {
            list.add( osiOperationsToOsiOperationsDTO( osiOperations_ ) );
        }

        return list;
    }

    @Override
    public OsiOperations osiOperationsDTOToOsiOperations(OsiOperationsDTO osiOperationsDTO) {
        if ( osiOperationsDTO == null ) {
            return null;
        }

        OsiOperations osiOperations = new OsiOperations();

        osiOperations.setId( osiOperationsDTO.getId() );
       /* osiOperations.setOpType( osiOperationsDTO.getOpType() );
        osiOperations.setOpValue( osiOperationsDTO.getOpValue() );
        osiOperations.setParameters( osiOperationsDTO.getParameters() );*/
        osiOperations.setCreatedBy( osiOperationsDTO.getCreatedBy() );
        osiOperations.setCreatedDate( osiOperationsDTO.getCreatedDate() );
        osiOperations.setUpdatedBy( osiOperationsDTO.getUpdatedBy() );
        osiOperations.setUpdatedDate( osiOperationsDTO.getUpdatedDate() );

        return osiOperations;
    }

    @Override
    public List<OsiOperations> osiOperationsDTOListToOsiOperationsList(List<OsiOperationsDTO> osiOperationsDTO) {
        if ( osiOperationsDTO == null ) {
            return null;
        }

        List<OsiOperations> list = new ArrayList<OsiOperations>();
        for ( OsiOperationsDTO osiOperationsDTO_ : osiOperationsDTO ) {
            list.add( osiOperationsDTOToOsiOperations( osiOperationsDTO_ ) );
        }

        return list;
    }
}
