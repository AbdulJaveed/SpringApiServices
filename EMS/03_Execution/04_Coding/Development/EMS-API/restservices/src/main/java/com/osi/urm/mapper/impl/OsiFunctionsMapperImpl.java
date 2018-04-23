package com.osi.urm.mapper.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.urm.domain.OsiFunctions;
import com.osi.urm.domain.OsiMenuEntries;
import com.osi.urm.domain.OsiUserFuncExcl;
import com.osi.urm.domain.OsiUserOperationExcl;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.mapper.OsiFunctionsMapper;
import com.osi.urm.mapper.OsiMenuEntriesMapper;
import com.osi.urm.mapper.OsiUserFuncExclMapper;
import com.osi.urm.mapper.OsiUserMapper;
import com.osi.urm.mapper.OsiUserOperationExclMapper;
import com.osi.urm.service.dto.OsiFunctionsDTO;
import com.osi.urm.service.dto.OsiMenuEntriesDTO;
import com.osi.urm.service.dto.OsiUserFuncExclDTO;
import com.osi.urm.service.dto.OsiUserOperationExclDTO;
@Component
public class OsiFunctionsMapperImpl implements OsiFunctionsMapper {
	
	@Autowired
	private OsiUserMapper OsiUserMapper;
    @Autowired
    private OsiUserFuncExclMapper osiUserFuncExclMapper;
    @Autowired
    private OsiMenuEntriesMapper osiMenuEntriesMapper;
    @Autowired
    private OsiUserOperationExclMapper osiUserOperationExclMapper;

    @Override
    public OsiFunctionsDTO osiUserToOsiFunctionsDTO(OsiFunctions osiFunctions) {
        if ( osiFunctions == null ) {
            return null;
        }

        OsiFunctionsDTO osiFunctionsDTO = new OsiFunctionsDTO();

        osiFunctionsDTO.setId( osiFunctions.getId() );
        osiFunctionsDTO.setFuncType( osiFunctions.getFuncType() );
        osiFunctionsDTO.setFuncValue( osiFunctions.getFuncValue() );
        osiFunctionsDTO.setFuncName( osiFunctions.getFuncName() );
        osiFunctionsDTO.setParameters( osiFunctions.getParameters() );
        osiFunctionsDTO.setCreatedBy( osiFunctions.getCreatedBy() );
        osiFunctionsDTO.setCreatedDate( osiFunctions.getCreatedDate() );
        osiFunctionsDTO.setUpdatedBy( osiFunctions.getUpdatedBy() );
        osiFunctionsDTO.setUpdatedDate( osiFunctions.getUpdatedDate() );
        osiFunctionsDTO.setBusinessGroupId(osiFunctions.getBusinessGroupId());
        osiFunctionsDTO.setActive( osiFunctions.getActive() );
        /*Set<OsiUserFuncExclDTO> set = osiUserFuncExclSetToOsiUserFuncExclDTOSet( osiFunctions.getOsiUserFuncExcls() );
        if ( set != null ) {
            osiFunctionsDTO.setOsiUserFuncExcls( set );
        }
        Set<OsiMenuEntriesDTO> set_ = osiMenuEntriesSetToOsiMenuEntriesDTOSet( osiFunctions.getOsiMenuEntrieses() );
        if ( set_ != null ) {
            osiFunctionsDTO.setOsiMenuEntrieses( set_ );
        }
        Set<OsiUserOperationExclDTO> set__ = osiUserOperationExclSetToOsiUserOperationExclDTOSet( osiFunctions.getOsiUserOperationExcls() );
        if ( set__ != null ) {
            osiFunctionsDTO.setOsiUserOperationExcls( set__ );
        }*/

        return osiFunctionsDTO;
    }

    @Override
    public List<OsiFunctionsDTO> osiFunctionsListToOsiFunctionsDTOList(List<OsiFunctions> osiFunctions) {
        if ( osiFunctions == null ) {
            return null;
        }

        List<OsiFunctionsDTO> list = new ArrayList<OsiFunctionsDTO>();
        for ( OsiFunctions osiFunctions_ : osiFunctions ) {
            list.add( osiUserToOsiFunctionsDTO( osiFunctions_ ) );
        }

        return list;
    }

    @Override
    public OsiFunctions osiFunctionsDTOToOsiFunctions(OsiFunctionsDTO osiFunctionsDTO) {
        if ( osiFunctionsDTO == null ) {
            return null;
        }

        OsiFunctions osiFunctions = new OsiFunctions();

        osiFunctions.setId( osiFunctionsDTO.getId() );
        osiFunctions.setFuncName( osiFunctionsDTO.getFuncName() );
        osiFunctions.setFuncType( osiFunctionsDTO.getFuncType() );
        osiFunctions.setFuncValue( osiFunctionsDTO.getFuncValue() );
        osiFunctions.setParameters( osiFunctionsDTO.getParameters() );
        osiFunctions.setCreatedBy( osiFunctionsDTO.getCreatedBy() );
        osiFunctions.setCreatedDate( osiFunctionsDTO.getCreatedDate() );
        osiFunctions.setUpdatedBy( osiFunctionsDTO.getUpdatedBy() );
        osiFunctions.setUpdatedDate( osiFunctionsDTO.getUpdatedDate() );
        osiFunctions.setBusinessGroupId(osiFunctionsDTO.getBusinessGroupId());
        osiFunctions.setActive(osiFunctionsDTO.getActive());
       /* Set<OsiUserFuncExcl> set = osiUserFuncExclDTOSetToOsiUserFuncExclSet( osiFunctionsDTO.getOsiUserFuncExcls() );
        if ( set != null ) {
            osiFunctions.setOsiUserFuncExcls( set );
        }
        Set<OsiMenuEntries> set_ = osiMenuEntriesDTOSetToOsiMenuEntriesSet( osiFunctionsDTO.getOsiMenuEntrieses() );
        if ( set_ != null ) {
            osiFunctions.setOsiMenuEntrieses( set_ );
        }
        Set<OsiUserOperationExcl> set__ = osiUserOperationExclDTOSetToOsiUserOperationExclSet( osiFunctionsDTO.getOsiUserOperationExcls() );
        if ( set__ != null ) {
            osiFunctions.setOsiUserOperationExcls( set__ );
        }*/

        return osiFunctions;
    }

    @Override
    public List<OsiFunctions> osiFunctionsDTOListToOsiFunctionsList(List<OsiFunctionsDTO> osiFunctionsDTO) {
        if ( osiFunctionsDTO == null ) {
            return null;
        }

        List<OsiFunctions> list = new ArrayList<OsiFunctions>();
        for ( OsiFunctionsDTO osiFunctionsDTO_ : osiFunctionsDTO ) {
            list.add( osiFunctionsDTOToOsiFunctions( osiFunctionsDTO_ ) );
        }

        return list;
    }

    protected Set<OsiUserFuncExclDTO> osiUserFuncExclSetToOsiUserFuncExclDTOSet(Set<OsiUserFuncExcl> set) {
        if ( set == null ) {
            return null;
        }

        Set<OsiUserFuncExclDTO> set_ = new HashSet<OsiUserFuncExclDTO>();
        for ( OsiUserFuncExcl osiUserFuncExcl : set ) {
            set_.add( osiUserFuncExclMapper.osiUserFuncExclToOsiUserFuncExclDTO( osiUserFuncExcl ) );
        }

        return set_;
    }

    protected Set<OsiMenuEntriesDTO> osiMenuEntriesSetToOsiMenuEntriesDTOSet(Set<OsiMenuEntries> set) {
        if ( set == null ) {
            return null;
        }

        Set<OsiMenuEntriesDTO> set_ = new HashSet<OsiMenuEntriesDTO>();
        for ( OsiMenuEntries osiMenuEntries : set ) {
            set_.add( osiMenuEntriesMapper.OsiMenuEntriesToOsiMenuEntriesDTO( osiMenuEntries ) );
        }

        return set_;
    }

    protected Set<OsiUserOperationExclDTO> osiUserOperationExclSetToOsiUserOperationExclDTOSet(Set<OsiUserOperationExcl> set) {
        if ( set == null ) {
            return null;
        }

        Set<OsiUserOperationExclDTO> set_ = new HashSet<OsiUserOperationExclDTO>();
        for ( OsiUserOperationExcl osiUserOperationExcl : set ) {
            set_.add( osiUserOperationExclMapper.osiUserToOsiUserDTO( osiUserOperationExcl ) );
        }

        return set_;
    }

    protected Set<OsiUserFuncExcl> osiUserFuncExclDTOSetToOsiUserFuncExclSet(Set<OsiUserFuncExclDTO> set) throws BusinessException {
        if ( set == null ) {
            return null;
        }

        Set<OsiUserFuncExcl> set_ = new HashSet<OsiUserFuncExcl>();
        for ( OsiUserFuncExclDTO osiUserFuncExclDTO : set ) {
            set_.add( osiUserFuncExclMapper.osiUserFuncExclDTOToOsiUserFuncExcl( osiUserFuncExclDTO ) );
        }

        return set_;
    }

    protected Set<OsiMenuEntries> osiMenuEntriesDTOSetToOsiMenuEntriesSet(Set<OsiMenuEntriesDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<OsiMenuEntries> set_ = new HashSet<OsiMenuEntries>();
        for ( OsiMenuEntriesDTO osiMenuEntriesDTO : set ) {
            set_.add( osiMenuEntriesMapper.osiMenuEntriesDTOToOsiMenuEntries( osiMenuEntriesDTO ) );
        }

        return set_;
    }

    protected Set<OsiUserOperationExcl> osiUserOperationExclDTOSetToOsiUserOperationExclSet(Set<OsiUserOperationExclDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<OsiUserOperationExcl> set_ = new HashSet<OsiUserOperationExcl>();
        for ( OsiUserOperationExclDTO osiUserOperationExclDTO : set ) {
            set_.add( osiUserOperationExclMapper.osiUserDTOToOsiUser( osiUserOperationExclDTO ) );
        }

        return set_;
    }
}
