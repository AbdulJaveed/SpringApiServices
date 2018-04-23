package com.osi.urm.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.urm.domain.OsiMenuEntries;
import com.osi.urm.mapper.OsiFunctionsMapper;
import com.osi.urm.mapper.OsiMenuEntriesMapper;
import com.osi.urm.mapper.OsiMenusMapper;
import com.osi.urm.service.dto.OsiMenuEntriesDTO;
@Component
public class OsiMenuEntriesMapperImpl implements OsiMenuEntriesMapper {

    @Autowired
    private OsiFunctionsMapper osiFunctionsMapper;
    @Autowired
    private OsiMenusMapper osiMenusMapper;

    @Override
    public OsiMenuEntriesDTO OsiMenuEntriesToOsiMenuEntriesDTO(OsiMenuEntries osiMenuEntries) {
        if ( osiMenuEntries == null ) {
            return null;
        }

        OsiMenuEntriesDTO osiMenuEntriesDTO = new OsiMenuEntriesDTO();

        osiMenuEntriesDTO.setId( osiMenuEntries.getId() );
        osiMenuEntriesDTO.setOsiFunctions( osiFunctionsMapper.osiUserToOsiFunctionsDTO( osiMenuEntries.getOsiFunctions() ) );
        osiMenuEntriesDTO.setOsiMenusBySubMenuId( osiMenusMapper.OsiMenusToOsiMenusDTO( osiMenuEntries.getOsiMenusBySubMenuId() ) );
        osiMenuEntriesDTO.setOsiMenusByMenuId( osiMenusMapper.OsiMenusToOsiMenusDTO( osiMenuEntries.getOsiMenusByMenuId() ) );
        osiMenuEntriesDTO.setSeq( osiMenuEntries.getSeq() );
        osiMenuEntriesDTO.setPrompt( osiMenuEntries.getPrompt() );
        osiMenuEntriesDTO.setCreatedBy( osiMenuEntries.getCreatedBy() );
        osiMenuEntriesDTO.setCreatedDate( osiMenuEntries.getCreatedDate() );
        osiMenuEntriesDTO.setUpdatedBy( osiMenuEntries.getUpdatedBy() );
        osiMenuEntriesDTO.setUpdatedDate( osiMenuEntries.getUpdatedDate() );
        osiMenuEntriesDTO.setStartDate( osiMenuEntries.getStartDate() );
        osiMenuEntriesDTO.setBusinessGroupId( osiMenuEntries.getBusinessGroupId() );
        osiMenuEntriesDTO.setActive(osiMenuEntries.getActive());
        return osiMenuEntriesDTO;
    }

    @Override
    public List<OsiMenuEntriesDTO> osiMenuEntriesListToOsiMenuEntriesDTOList(List<OsiMenuEntries> osiMenuEntries) {
        if ( osiMenuEntries == null ) {
            return null;
        }

        List<OsiMenuEntriesDTO> list = new ArrayList<OsiMenuEntriesDTO>();
        for ( OsiMenuEntries osiMenuEntries_ : osiMenuEntries ) {
            list.add( OsiMenuEntriesToOsiMenuEntriesDTO( osiMenuEntries_ ) );
        }

        return list;
    }

    @Override
    public OsiMenuEntries osiMenuEntriesDTOToOsiMenuEntries(OsiMenuEntriesDTO osiMenuEntriesDTO) {
        if ( osiMenuEntriesDTO == null ) {
            return null;
        }

        OsiMenuEntries osiMenuEntries = new OsiMenuEntries();

        if ( osiMenuEntriesDTO.getId() != null ) {
            osiMenuEntries.setId( osiMenuEntriesDTO.getId() );
        }
        osiMenuEntries.setOsiFunctions( osiFunctionsMapper.osiFunctionsDTOToOsiFunctions( osiMenuEntriesDTO.getOsiFunctions() ) );
        osiMenuEntries.setOsiMenusBySubMenuId( osiMenusMapper.osiMenusDTOToOsiMenus( osiMenuEntriesDTO.getOsiMenusBySubMenuId() ) );
        osiMenuEntries.setOsiMenusByMenuId( osiMenusMapper.osiMenusDTOToOsiMenus( osiMenuEntriesDTO.getOsiMenusByMenuId() ) );
        if ( osiMenuEntriesDTO.getSeq() != null ) {
            osiMenuEntries.setSeq( osiMenuEntriesDTO.getSeq() );
        }
        osiMenuEntries.setPrompt( osiMenuEntriesDTO.getPrompt() );
        osiMenuEntries.setCreatedBy( osiMenuEntriesDTO.getCreatedBy() );
        osiMenuEntries.setCreatedDate( osiMenuEntriesDTO.getCreatedDate() );
        osiMenuEntries.setUpdatedBy( osiMenuEntriesDTO.getUpdatedBy() );
        osiMenuEntries.setUpdatedDate( osiMenuEntriesDTO.getUpdatedDate() );
        osiMenuEntries.setStartDate( osiMenuEntriesDTO.getStartDate() );
        osiMenuEntries.setEndDate(osiMenuEntriesDTO.getEndDate());
        osiMenuEntries.setBusinessGroupId(osiMenuEntriesDTO.getBusinessGroupId());
        osiMenuEntries.setActive(osiMenuEntriesDTO.getActive());
        return osiMenuEntries;
    }

    @Override
    public List<OsiMenuEntries> osiMenuEntriesDTOListToOsMenuEntriesList(List<OsiMenuEntriesDTO> osiMenuEntriesDTO) {
        if ( osiMenuEntriesDTO == null ) {
            return null;
        }

        List<OsiMenuEntries> list = new ArrayList<OsiMenuEntries>();
        for ( OsiMenuEntriesDTO osiMenuEntriesDTO_ : osiMenuEntriesDTO ) {
            list.add( osiMenuEntriesDTOToOsiMenuEntries( osiMenuEntriesDTO_ ) );
        }

        return list;
    }
}
