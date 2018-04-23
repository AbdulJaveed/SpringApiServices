package com.osi.urm.mapper.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.urm.domain.OsiRespUser;
import com.osi.urm.mapper.OsiReponsibilitiesMapper;
import com.osi.urm.mapper.OsiRespUserMapper;
import com.osi.urm.mapper.OsiUserMapper;
import com.osi.urm.service.dto.OsiRespUserDTO;
@Component
public class OsiRespUserMapperImpl implements OsiRespUserMapper {

    @Autowired
    private OsiReponsibilitiesMapper osiReponsibilitiesMapper;
    @Autowired
    private OsiUserMapper osiUserMapper;

    @Override
    public OsiRespUserDTO osiUserToOsiUserDTO(OsiRespUser osiUser) {
        if ( osiUser == null ) {
            return null;
        }

        OsiRespUserDTO osiRespUserDTO = new OsiRespUserDTO();

        osiRespUserDTO.setId( osiUser.getId() );
        osiRespUserDTO.setEmployeeId(osiUser.getEmployeeId());
        osiRespUserDTO.setOsiResponsibilities( osiReponsibilitiesMapper.osiResponsibilitiesToOsiResponsibilitiesDTO( osiUser.getOsiResponsibilities() ) );
        osiRespUserDTO.setStartDate( convertDateFormat(osiUser.getStartDate()) );
        osiRespUserDTO.setEndDate(convertDateFormat(osiUser.getEndDate()) );
        osiRespUserDTO.setDefaultResp( osiUser.getDefaultResp() );
        osiRespUserDTO.setCreatedBy( osiUser.getCreatedBy() );
        osiRespUserDTO.setCreatedDate( osiUser.getCreatedDate() );
        osiRespUserDTO.setUpdatedBy( osiUser.getUpdatedBy() );
        osiRespUserDTO.setUpdatedDate( osiUser.getUpdatedDate() );
        osiRespUserDTO.setBusinessGroupId(osiUser.getBusinessGroupId());

        return osiRespUserDTO;
    }

    public static String convertDateFormat(String date){
		String dbFormatDate = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dbFormatDate = simpleDateFormat1.format(simpleDateFormat.parse(date));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			dbFormatDate = date;
		}
		return dbFormatDate;
	}
    
    @Override
    public List<OsiRespUserDTO> osiUserListToOsiUserDTOList(List<OsiRespUser> osiUsers) {
        if ( osiUsers == null ) {
            return null;
        }

        List<OsiRespUserDTO> list = new ArrayList<OsiRespUserDTO>();
        for ( OsiRespUser osiRespUser : osiUsers ) {
            list.add( osiUserToOsiUserDTO( osiRespUser ) );
        }

        return list;
    }

    @Override
    public OsiRespUser osiUserDTOToOsiUser(OsiRespUserDTO osiUserDTO) {
        if ( osiUserDTO == null ) {
            return null;
        }

        OsiRespUser osiRespUser = new OsiRespUser();

        osiRespUser.setId( osiUserDTO.getId() );
        osiRespUser.setEmployeeId(osiUserDTO.getEmployeeId());
        osiRespUser.setOsiResponsibilities( osiReponsibilitiesMapper.osiResponsibilitiesDTOToOsiResponsibilities( osiUserDTO.getOsiResponsibilities() ) );
        osiRespUser.setStartDate( osiUserDTO.getStartDate() );
        osiRespUser.setEndDate( osiUserDTO.getEndDate() );
        osiRespUser.setDefaultResp( osiUserDTO.getDefaultResp() );
        osiRespUser.setCreatedBy( osiUserDTO.getCreatedBy() );
        osiRespUser.setCreatedDate( osiUserDTO.getCreatedDate() );
        osiRespUser.setUpdatedBy( osiUserDTO.getUpdatedBy() );
        osiRespUser.setUpdatedDate( osiUserDTO.getUpdatedDate() );
        osiRespUser.setBusinessGroupId(osiUserDTO.getBusinessGroupId());

        return osiRespUser;
    }

    @Override
    public List<OsiRespUser> osiUserDTOListToOsiUserList(List<OsiRespUserDTO> osiUserDTO) {
        if ( osiUserDTO == null ) {
            return null;
        }

        List<OsiRespUser> list = new ArrayList<OsiRespUser>();
        for ( OsiRespUserDTO osiRespUserDTO : osiUserDTO ) {
            list.add( osiUserDTOToOsiUser( osiRespUserDTO ) );
        }

        return list;
    }
}
