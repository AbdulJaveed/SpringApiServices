package com.osi.urm.mapper.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.urm.domain.OsiMenuResp;
import com.osi.urm.domain.OsiRespUser;
import com.osi.urm.domain.OsiResponsibilities;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.mapper.OsiMenuRespMapper;
import com.osi.urm.mapper.OsiMenusMapper;
import com.osi.urm.mapper.OsiReponsibilitiesMapper;
import com.osi.urm.mapper.OsiRespUserMapper;
import com.osi.urm.service.dto.OsiMenuRespDTO;
import com.osi.urm.service.dto.OsiRespUserDTO;
import com.osi.urm.service.dto.OsiResponsibilitiesDTO;

@Component
public class OsiReponsibilitiesMapperImpl implements OsiReponsibilitiesMapper {

    @Autowired
    private OsiMenusMapper osiMenusMapper;
    
    @Autowired
    private OsiRespUserMapper osiRespUserMapper;
    
    @Autowired
    private OsiMenuRespMapper osiMenuRespMapper;

    @Override
    public OsiResponsibilitiesDTO osiResponsibilitiesToOsiResponsibilitiesDTO(OsiResponsibilities osiResponsibilities) {
        if ( osiResponsibilities == null ) {
            return null;
        }

        OsiResponsibilitiesDTO osiResponsibilitiesDTO = new OsiResponsibilitiesDTO();

        osiResponsibilitiesDTO.setId( osiResponsibilities.getId() );
        osiResponsibilitiesDTO.setRespName( osiResponsibilities.getRespName() );
        osiResponsibilitiesDTO.setDescription( osiResponsibilities.getDescription() );
        osiResponsibilitiesDTO.setCreatedBy( osiResponsibilities.getCreatedBy() );
        osiResponsibilitiesDTO.setCreatedDate( osiResponsibilities.getCreatedDate() );
        osiResponsibilitiesDTO.setUpdatedBy( osiResponsibilities.getUpdatedBy() );
        osiResponsibilitiesDTO.setUpdatedDate( osiResponsibilities.getUpdatedDate() );
        osiResponsibilitiesDTO.setBusinessGroupId(osiResponsibilities.getBusinessGroupId());
        osiResponsibilitiesDTO.setActive(osiResponsibilities.getActive());
        Set<OsiMenuRespDTO> osiMenuRespDTOs = new HashSet<OsiMenuRespDTO>(0);
        for (OsiMenuResp osiMenuResp : osiResponsibilities.getOsiMenuResp()) {
        	OsiMenuRespDTO resp = new OsiMenuRespDTO();
        	resp.setBusinessGroupId(osiResponsibilities.getBusinessGroupId());
        	resp.setOsiMenus(osiMenusMapper.OsiMenusToOsiMenusDTO(osiMenuResp.getOsiMenus()));
        	//resp.setOsiResponsibilities(new OsiResponsibilitiesDTO());
        	osiMenuRespDTOs.add(resp);
//			osiRespUserSet.add(osiRespUser);
//        	osiRespUser.setOsiResponsibilities(osiReponsibilitiesMapper.osiResponsibilitiesDTOToOsiResponsibilities( osiRespUserDTO.getOsiResponsibilities() ) );/
		}
        osiResponsibilitiesDTO.setOsiMenuResp(osiMenuRespDTOs);
        
//        Set<OsiMenuRespDTO>  set = osiMenuRespToOsiMenuRespDTOSet (osiResponsibilities.getOsiMenuResp());
//        if(set != null){
//        	osiResponsibilitiesDTO.setOsiMenuResp(set);
//        }
        //osiResponsibilitiesDTO.setReportGrpId(osiResponsibilities.getReportGrpId());
        /*Set<OsiRespUserDTO> set = osiRespUserSetToOsiRespUserDTOSet( osiResponsibilities.getOsiRespUsers() );
        if ( set != null ) {
            osiResponsibilitiesDTO.setOsiRespUsers( set );
        }*/
        osiResponsibilitiesDTO.setStartDate( osiResponsibilities.getStartDate() );
        osiResponsibilitiesDTO.setEndDate( osiResponsibilities.getEndDate() );

        return osiResponsibilitiesDTO;
    }

    @Override
    public List<OsiResponsibilitiesDTO> osiResponsibilitiesListToOsiResponsibilitiesDTOList(List<OsiResponsibilities> osiResponsibilities) {
        if ( osiResponsibilities == null ) {
            return null;
        }

        List<OsiResponsibilitiesDTO> list = new ArrayList<OsiResponsibilitiesDTO>();
        for ( OsiResponsibilities osiResponsibilities_ : osiResponsibilities ) {
            list.add( osiResponsibilitiesToOsiResponsibilitiesDTO( osiResponsibilities_ ) );
        }

        return list;
    }

    @Override
    public OsiResponsibilities osiResponsibilitiesDTOToOsiResponsibilities(OsiResponsibilitiesDTO osiResponsibilitiesDTO) {
        if ( osiResponsibilitiesDTO == null ) {
            return null;
        }

        OsiResponsibilities osiResponsibilities = new OsiResponsibilities();

        osiResponsibilities.setId( osiResponsibilitiesDTO.getId() );
        osiResponsibilities.setRespName( osiResponsibilitiesDTO.getRespName() );
        osiResponsibilities.setActive(osiResponsibilitiesDTO.getActive());
        osiResponsibilities.setDescription( osiResponsibilitiesDTO.getDescription() );
        osiResponsibilities.setCreatedBy( osiResponsibilitiesDTO.getCreatedBy() );
        osiResponsibilities.setCreatedDate( osiResponsibilitiesDTO.getCreatedDate() );
        osiResponsibilities.setUpdatedBy( osiResponsibilitiesDTO.getUpdatedBy() );
        osiResponsibilities.setUpdatedDate( osiResponsibilitiesDTO.getUpdatedDate() );
        osiResponsibilities.setBusinessGroupId(osiResponsibilitiesDTO.getBusinessGroupId());
        Set<OsiMenuResp> osiMenuResp = new HashSet<OsiMenuResp>(0);
        for (OsiMenuRespDTO osiMenuRespDTO : osiResponsibilitiesDTO.getOsiMenuResp()) {
        	OsiMenuResp resp = new OsiMenuResp();
        	resp.setBusinessGroupId(osiResponsibilities.getBusinessGroupId());
        	resp.setOsiMenus(osiMenusMapper.osiMenusDTOToOsiMenus(osiMenuRespDTO.getOsiMenus()));
        	resp.setOsiResponsibilities(osiResponsibilities);
        	osiMenuResp.add(resp);
        	//osiRespUserSet.add(osiRespUser);
//        	osiRespUser.setOsiResponsibilities(osiReponsibilitiesMapper.osiResponsibilitiesDTOToOsiResponsibilities( osiRespUserDTO.getOsiResponsibilities() ) );/
		}
        osiResponsibilities.setOsiMenuResp(osiMenuResp);
//        Set<OsiMenuResp>  set = osiMenuRespDTOSetToOsiMenuRespSet (osiResponsibilitiesDTO.getOsiMenuResp());
//        if(set != null){
//        	osiResponsibilities.setOsiMenuResp(set);
//        }
        //osiResponsibilities.setReportGrpId(osiResponsibilitiesDTO.getReportGrpId());
        /*Set<OsiRespUser> set = osiRespUserDTOSetToOsiRespUserSet( osiResponsibilitiesDTO.getOsiRespUsers() );
        if ( set != null ) {
            osiResponsibilities.setOsiRespUsers( set );
        }*/
        osiResponsibilities.setStartDate( osiResponsibilitiesDTO.getStartDate() );
        osiResponsibilities.setEndDate( osiResponsibilitiesDTO.getEndDate() );

        return osiResponsibilities;
    }

    @Override
    public List<OsiResponsibilities> OsiResponsibilitiesDTOListToOsiResponsibilitiesList(List<OsiResponsibilitiesDTO> osiResponsibilitiesDTO) {
        if ( osiResponsibilitiesDTO == null ) {
            return null;
        }

        List<OsiResponsibilities> list = new ArrayList<OsiResponsibilities>();
        for ( OsiResponsibilitiesDTO osiResponsibilitiesDTO_ : osiResponsibilitiesDTO ) {
            list.add( osiResponsibilitiesDTOToOsiResponsibilities( osiResponsibilitiesDTO_ ) );
        }

        return list;
    }

    protected Set<OsiRespUserDTO> osiRespUserSetToOsiRespUserDTOSet(Set<OsiRespUser> set) throws BusinessException {
        if ( set == null ) {
            return null;
        }

        Set<OsiRespUserDTO> set_ = new HashSet<OsiRespUserDTO>();
        for ( OsiRespUser osiRespUser : set ) {
            set_.add( osiRespUserMapper.osiUserToOsiUserDTO( osiRespUser ) );
        }

        return set_;
    }

    protected Set<OsiRespUser> osiRespUserDTOSetToOsiRespUserSet(Set<OsiRespUserDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<OsiRespUser> set_ = new HashSet<OsiRespUser>();
        for ( OsiRespUserDTO osiRespUserDTO : set ) {
            set_.add( osiRespUserMapper.osiUserDTOToOsiUser( osiRespUserDTO ) );
        }

        return set_;
    }
    protected Set<OsiMenuRespDTO> osiMenuRespToOsiMenuRespDTOSet(Set<OsiMenuResp> set) {
        if ( set == null ) {
            return null;
        }

        Set<OsiMenuRespDTO> set_ = new HashSet<OsiMenuRespDTO>();
        for ( OsiMenuResp osiMenuResp : set ) {
            set_.add( osiMenuRespMapper.osiMenuRespToOsiMenuRespDTO( osiMenuResp ) );
        }

        return set_;
    }
    
    protected Set<OsiMenuResp> osiMenuRespDTOSetToOsiMenuRespSet(Set<OsiMenuRespDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<OsiMenuResp> set_ = new HashSet<OsiMenuResp>();
        for ( OsiMenuRespDTO osiMenuRespDTO : set ) {
            set_.add( osiMenuRespMapper.osiMenuRespDTOToOsiMenuResp( osiMenuRespDTO ) );
        }

        return set_;
    }
}
