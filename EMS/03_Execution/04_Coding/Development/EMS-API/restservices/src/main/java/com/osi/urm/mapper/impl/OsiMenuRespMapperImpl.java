package com.osi.urm.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.urm.domain.OsiMenuResp;
import com.osi.urm.mapper.OsiMenuRespMapper;
import com.osi.urm.mapper.OsiMenusMapper;
import com.osi.urm.mapper.OsiReponsibilitiesMapper;
import com.osi.urm.service.dto.OsiMenuRespDTO;
@Component
public class OsiMenuRespMapperImpl implements OsiMenuRespMapper {
	
	@Autowired
    private OsiReponsibilitiesMapper osiReponsibilitiesMapper;
	
	 @Autowired
	    private OsiMenusMapper osiMenusMapper;

	@Override
	public OsiMenuRespDTO osiMenuRespToOsiMenuRespDTO(OsiMenuResp osiMenuResp) {
		if ( osiMenuResp == null ) {
            return null;
        }
		OsiMenuRespDTO osiMenuRespDTO = new OsiMenuRespDTO();
		osiMenuRespDTO.setMenuRespId(osiMenuResp.getMenuRespId());
		osiMenuRespDTO.setOsiMenus(osiMenusMapper.OsiMenusToOsiMenusDTO(osiMenuResp.getOsiMenus()));
		osiMenuRespDTO.setOsiResponsibilities(osiReponsibilitiesMapper.osiResponsibilitiesToOsiResponsibilitiesDTO(osiMenuResp.getOsiResponsibilities()));
		osiMenuRespDTO.setBusinessGroupId(osiMenuResp.getBusinessGroupId());
		return osiMenuRespDTO;
		
		
	}

	@Override
	public List<OsiMenuRespDTO> osiMenuRespListToosiMenuRespDTOList(List<OsiMenuResp> OsiMenuResp) {
		if ( OsiMenuResp == null ) {
            return null;
        }
		 List<OsiMenuRespDTO> list = new ArrayList<OsiMenuRespDTO>();
	        for ( OsiMenuResp osiMenuRespUser : OsiMenuResp ) {
	            list.add( osiMenuRespToOsiMenuRespDTO( osiMenuRespUser ) );
	        }

	        return list;
	}

	@Override
	public OsiMenuResp osiMenuRespDTOToOsiMenuResp(OsiMenuRespDTO osiMenuRespDTO) {
		if(osiMenuRespDTO == null){
		return null;
		}
		OsiMenuResp OsiMenuResp = new OsiMenuResp();
		OsiMenuResp.setMenuRespId(osiMenuRespDTO.getMenuRespId());
		OsiMenuResp.setOsiMenus(osiMenusMapper.osiMenusDTOToOsiMenus(osiMenuRespDTO.getOsiMenus()));
		OsiMenuResp.setOsiResponsibilities( osiReponsibilitiesMapper.osiResponsibilitiesDTOToOsiResponsibilities(osiMenuRespDTO.getOsiResponsibilities()));
		OsiMenuResp.setBusinessGroupId(osiMenuRespDTO.getBusinessGroupId());
		return OsiMenuResp;
		
	}

	@Override
	public List<OsiMenuResp> osiMenuRespDTOListToOsiMenuRespList(List<OsiMenuRespDTO> osiMenuRespDTO) {
		 if ( osiMenuRespDTO == null ) {
	            return null;
	        }

	        List<OsiMenuResp> list = new ArrayList<OsiMenuResp>();
	        for ( OsiMenuRespDTO osiRespUserDTO : osiMenuRespDTO ) {
	            list.add( osiMenuRespDTOToOsiMenuResp( osiRespUserDTO ) );
	        }

	        return list;
	}

}
