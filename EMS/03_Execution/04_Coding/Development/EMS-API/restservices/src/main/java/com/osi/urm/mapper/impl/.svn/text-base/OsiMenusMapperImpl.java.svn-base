package com.osi.urm.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.urm.domain.OsiMenus;
import com.osi.urm.mapper.OsiMenusMapper;
import com.osi.urm.service.dto.OsiMenusDTO;
@Component
public class OsiMenusMapperImpl implements OsiMenusMapper {

    @Override
    public OsiMenusDTO OsiMenusToOsiMenusDTO(OsiMenus osiMenus) {
        if ( osiMenus == null ) {
            return null;
        }

        OsiMenusDTO osiMenusDTO = new OsiMenusDTO();

        osiMenusDTO.setId( osiMenus.getId() );
        osiMenusDTO.setMenuName( osiMenus.getMenuName() );
        osiMenusDTO.setDescription( osiMenus.getDescription() );
        osiMenusDTO.setCreatedBy( osiMenus.getCreatedBy() );
        osiMenusDTO.setCreatedDate( osiMenus.getCreatedDate() );
        osiMenusDTO.setUpdatedBy( osiMenus.getUpdatedBy() );
        osiMenusDTO.setUpdatedDate( osiMenus.getUpdatedDate() );
        osiMenusDTO.setBusinessGroupId(osiMenus.getBusinessGroupId());
        osiMenusDTO.setActive(osiMenus.getActive());
        osiMenusDTO.setReportGrpId(osiMenus.getReportGrpId());
        
        return osiMenusDTO;
    }

    @Override
    public List<OsiMenusDTO> osiMenusListToOsiMenusDTOList(List<OsiMenus> osiMenus) {
        if ( osiMenus == null ) {
            return null;
        }

        List<OsiMenusDTO> list = new ArrayList<OsiMenusDTO>();
        for ( OsiMenus osiMenus_ : osiMenus ) {
            list.add( OsiMenusToOsiMenusDTO( osiMenus_ ) );
        }

        return list;
    }

    @Override
    public OsiMenus osiMenusDTOToOsiMenus(OsiMenusDTO osiMenusDTO) {
        if ( osiMenusDTO == null ) {
            return null;
        }

        OsiMenus osiMenus = new OsiMenus();

        osiMenus.setId( osiMenusDTO.getId() );
        osiMenus.setMenuName( osiMenusDTO.getMenuName() );
        osiMenus.setDescription( osiMenusDTO.getDescription() );
        osiMenus.setCreatedBy( osiMenusDTO.getCreatedBy() );
        osiMenus.setCreatedDate( osiMenusDTO.getCreatedDate() );
        osiMenus.setUpdatedBy( osiMenusDTO.getUpdatedBy() );
        osiMenus.setUpdatedDate( osiMenusDTO.getUpdatedDate() );
        osiMenus.setBusinessGroupId(osiMenusDTO.getBusinessGroupId());
        osiMenus.setActive(osiMenusDTO.getActive());
        osiMenus.setReportGrpId(osiMenusDTO.getReportGrpId());
        
        return osiMenus;
    }

    @Override
    public List<OsiMenus> osiMenusDTOListToOsiMenusList(List<OsiMenusDTO> osiMenusDTO) {
        if ( osiMenusDTO == null ) {
            return null;
        }

        List<OsiMenus> list = new ArrayList<OsiMenus>();
        for ( OsiMenusDTO osiMenusDTO_ : osiMenusDTO ) {
            list.add( osiMenusDTOToOsiMenus( osiMenusDTO_ ) );
        }

        return list;
    }
}
