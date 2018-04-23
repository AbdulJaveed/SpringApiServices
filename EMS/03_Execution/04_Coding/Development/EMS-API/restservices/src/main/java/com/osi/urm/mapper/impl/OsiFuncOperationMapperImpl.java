package com.osi.urm.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.urm.domain.OsiFuncOperations;
import com.osi.urm.mapper.OsiFuncOperationMapper;
import com.osi.urm.mapper.OsiFunctionsMapper;
import com.osi.urm.mapper.OsiOperationsMapper;
import com.osi.urm.service.dto.OsiFuncOperationsDTO;
@Component
public class OsiFuncOperationMapperImpl implements OsiFuncOperationMapper {

    @Autowired
    private OsiFunctionsMapper osiFunctionsMapper;
    @Autowired
    private OsiOperationsMapper osiOperationsMapper;

    @Override
    public OsiFuncOperationsDTO osiFuncOperationToOsiFuncOperationDTO(OsiFuncOperations osiFuncOperations) {
        if ( osiFuncOperations == null ) {
            return null;
        }

        OsiFuncOperationsDTO osiFuncOperationsDTO = new OsiFuncOperationsDTO();

        if ( osiFuncOperations.getId() != null ) {
            osiFuncOperationsDTO.setId( osiFuncOperations.getId().intValue() );
        }
        osiFuncOperationsDTO.setOsiFunctions( osiFunctionsMapper.osiUserToOsiFunctionsDTO( osiFuncOperations.getOsiFunctions() ) );
        osiFuncOperationsDTO.setOsiOperations( osiOperationsMapper.osiOperationsToOsiOperationsDTO( osiFuncOperations.getOsiOperations() ) );
        osiFuncOperationsDTO.setOpUrl( osiFuncOperations.getOpUrl() );
        osiFuncOperationsDTO.setStartDate( osiFuncOperations.getStartDate() );
        osiFuncOperationsDTO.setEndDate( osiFuncOperations.getEndDate() );
        osiFuncOperationsDTO.setCreatedBy( osiFuncOperations.getCreatedBy() );
        osiFuncOperationsDTO.setCreatedDate( osiFuncOperations.getCreatedDate() );
        osiFuncOperationsDTO.setUpdatedBy( osiFuncOperations.getUpdatedBy() );
        osiFuncOperationsDTO.setUpdatedDate( osiFuncOperations.getUpdatedDate() );
        osiFuncOperationsDTO.setBusinessGroupId(osiFuncOperations.getBusinessGroupId());

        return osiFuncOperationsDTO;
    }

    @Override
    public List<OsiFuncOperationsDTO> osiFuncOperationListToOsiFuncOperationDTOList(List<OsiFuncOperations> osiFuncOperations) {
        if ( osiFuncOperations == null ) {
            return null;
        }

        List<OsiFuncOperationsDTO> list = new ArrayList<OsiFuncOperationsDTO>();
        for ( OsiFuncOperations osiFuncOperations_ : osiFuncOperations ) {
            list.add( osiFuncOperationToOsiFuncOperationDTO( osiFuncOperations_ ) );
        }

        return list;
    }

    @Override
    public OsiFuncOperations osiFuncOperationDTOToOsiFuncOperation(OsiFuncOperationsDTO osiFuncOperationsDTO) {
        if ( osiFuncOperationsDTO == null ) {
            return null;
        }

        OsiFuncOperations osiFuncOperations = new OsiFuncOperations();

        osiFuncOperations.setId( osiFuncOperationsDTO.getId() );
        osiFuncOperations.setOsiFunctions( osiFunctionsMapper.osiFunctionsDTOToOsiFunctions( osiFuncOperationsDTO.getOsiFunctions() ) );
        osiFuncOperations.setOsiOperations( osiOperationsMapper.osiOperationsDTOToOsiOperations( osiFuncOperationsDTO.getOsiOperations() ) );
        osiFuncOperations.setOpUrl( osiFuncOperationsDTO.getOpUrl() );
        osiFuncOperations.setStartDate( osiFuncOperationsDTO.getStartDate() );
        osiFuncOperations.setEndDate( osiFuncOperationsDTO.getEndDate() );
        osiFuncOperations.setCreatedBy( osiFuncOperationsDTO.getCreatedBy() );
        osiFuncOperations.setCreatedDate( osiFuncOperationsDTO.getCreatedDate() );
        osiFuncOperations.setUpdatedBy( osiFuncOperationsDTO.getUpdatedBy() );
        osiFuncOperations.setUpdatedDate( osiFuncOperationsDTO.getUpdatedDate() );
        osiFuncOperations.setBusinessGroupId(osiFuncOperationsDTO.getBusinessGroupId());
        return osiFuncOperations;
    }

    @Override
    public List<OsiFuncOperations> osiFuncOperationDTOListToOsiFuncOperationList(List<OsiFuncOperationsDTO> osiFuncOperationsDTO) {
        if ( osiFuncOperationsDTO == null ) {
            return null;
        }

        List<OsiFuncOperations> list = new ArrayList<OsiFuncOperations>();
        for ( OsiFuncOperationsDTO osiFuncOperationsDTO_ : osiFuncOperationsDTO ) {
            list.add( osiFuncOperationDTOToOsiFuncOperation( osiFuncOperationsDTO_ ) );
        }

        return list;
    }
}
