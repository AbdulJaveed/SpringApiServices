package com.osi.urm.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.urm.domain.OsiUserLogins;
import com.osi.urm.mapper.OsiUserLoginsMapper;
import com.osi.urm.mapper.OsiUserMapper;
import com.osi.urm.service.dto.OsiUserLoginsDTO;
@Component
public class OsiUserLoginsMapperImpl implements OsiUserLoginsMapper {

    @Autowired
    private OsiUserMapper osiUserMapper;

    @Override
    public OsiUserLoginsDTO OsiUserLoginsToOsiUserLoginsDTO(OsiUserLogins osiUser) {
        if ( osiUser == null ) {
            return null;
        }

        OsiUserLoginsDTO osiUserLoginsDTO = new OsiUserLoginsDTO();

        osiUserLoginsDTO.setId( osiUser.getId() );
       /* osiUserLoginsDTO.setOsiUser( osiUserMapper.osiUserToOsiUserDTO( osiUser.getOsiUser() ) );*/
        osiUserLoginsDTO.setStartTime( osiUser.getStartTime() );
        osiUserLoginsDTO.setEndTime( osiUser.getEndTime() );
        osiUserLoginsDTO.setPid( osiUser.getPid() );
        osiUserLoginsDTO.setLoginType( osiUser.getLoginType() );
        osiUserLoginsDTO.setToken( osiUser.getToken() );
        osiUserLoginsDTO.setTokenExpTime( osiUser.getTokenExpTime() );

        return osiUserLoginsDTO;
    }

    @Override
    public List<OsiUserLoginsDTO> osiUserLoginsListToOsiUserLoginsDTOList(List<OsiUserLogins> osiUsers) {
        if ( osiUsers == null ) {
            return null;
        }

        List<OsiUserLoginsDTO> list = new ArrayList<OsiUserLoginsDTO>();
        for ( OsiUserLogins osiUserLogins : osiUsers ) {
            list.add( OsiUserLoginsToOsiUserLoginsDTO( osiUserLogins ) );
        }

        return list;
    }

    @Override
    public OsiUserLogins osiUserLoginsDTOToOsiUserLogins(OsiUserLoginsDTO osiUserDTO) {
        if ( osiUserDTO == null ) {
            return null;
        }

        OsiUserLogins osiUserLogins = new OsiUserLogins();

        if ( osiUserDTO.getId() != null ) {
            osiUserLogins.setId( osiUserDTO.getId() );
        }
        /*osiUserLogins.setOsiUser( osiUserMapper.osiUserDTOToOsiUser( osiUserDTO.getOsiUser() ) );*/
        osiUserLogins.setStartTime( osiUserDTO.getStartTime() );
        osiUserLogins.setEndTime( osiUserDTO.getEndTime() );
        osiUserLogins.setPid( osiUserDTO.getPid() );
        osiUserLogins.setLoginType( osiUserDTO.getLoginType() );
        osiUserLogins.setToken( osiUserDTO.getToken() );
        osiUserLogins.setTokenExpTime( osiUserDTO.getTokenExpTime() );

        return osiUserLogins;
    }

    @Override
    public List<OsiUserLogins> osiUserLoginsDTOListToOsiUserLoginsList(List<OsiUserLoginsDTO> osiUserDTO) {
        if ( osiUserDTO == null ) {
            return null;
        }

        List<OsiUserLogins> list = new ArrayList<OsiUserLogins>();
        for ( OsiUserLoginsDTO osiUserLoginsDTO : osiUserDTO ) {
            list.add( osiUserLoginsDTOToOsiUserLogins( osiUserLoginsDTO ) );
        }

        return list;
    }
}
