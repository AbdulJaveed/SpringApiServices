package com.osi.urm.mapper.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.osi.urm.domain.OsiAttachments;
import com.osi.urm.mapper.OsiAttachmentsMapper;
import com.osi.urm.mapper.OsiUserMapper;
import com.osi.urm.service.dto.OsiAttachmentsDTO;

@Component
public class OsiAttachmentsMapperImpl implements OsiAttachmentsMapper {

    @Autowired
    private OsiUserMapper osiUserMapper;

  /*  @Override
    public OsiAttachmentsDTO osiUserToOsiUserDTO(OsiAttachments osiUser) {
        if ( osiUser == null ) {
            return null;
        }

        OsiAttachmentsDTO osiAttachmentsDTO = new OsiAttachmentsDTO();

        osiAttachmentsDTO.setId(osiUser.getId() );
      //  osiAttachmentsDTO.setOsiUser( osiUserMapper.osiUserToOsiUserDTO( osiUser.getOsiUser() ) );
        osiAttachmentsDTO.setAttachment(osiUser.getAttachment());
        if ( osiUser.getAttachment() != null ) {
            byte[] attachment = osiUser.getAttachment();
            osiAttachmentsDTO.setAttachment( Arrays.copyOf( attachment, attachment.length ) );
        }
//        osiAttachmentsDTO.setAttachmentType( osiUser.getAttachmentType() );
        osiAttachmentsDTO.setAttachment(getBase64ForImage(osiUser.getAttachment()));
        osiAttachmentsDTO.setCreatedBy( osiUser.getCreatedBy() );
        osiAttachmentsDTO.setCreatedDate( osiUser.getCreatedDate() );
        osiAttachmentsDTO.setUpdatedBy( osiUser.getUpdatedBy() );
        osiAttachmentsDTO.setUpdatedDate( osiUser.getUpdatedDate() );

        return osiAttachmentsDTO;
    }*/

    @Override
    public List<OsiAttachmentsDTO> osiUserListToOsiUserDTOList(List<OsiAttachments> osiUsers) {
        if ( osiUsers == null ) {
            return null;
        }

        List<OsiAttachmentsDTO> list = new ArrayList<OsiAttachmentsDTO>();
        for ( OsiAttachments osiAttachments : osiUsers ) {
            list.add( osiUserToOsiUserDTO( osiAttachments ) );
        }

        return list;
    }

  /*  @Override
    public OsiAttachments osiUserDTOToOsiUser(OsiAttachmentsDTO osiUserDTO) {
        if ( osiUserDTO == null ) {
            return null;
        }

        OsiAttachments osiAttachments = new OsiAttachments();

        if ( osiUserDTO.getId() != null ) {
            osiAttachments.setId( osiUserDTO.getId());
        }
        osiAttachments.setOsiUser( osiUserMapper.osiUserDTOToOsiUser( osiUserDTO.getOsiUser() ) );
        osiAttachments.setAttachment(osiUserDTO.getAttachment());
        if ( osiUserDTO.getAttachment() != null ) {
            byte[] attachment = osiUserDTO.getAttachment();
            osiAttachments.setAttachment( Arrays.copyOf( attachment, attachment.length ) );
        }
//        osiAttachments.setAttachmentType( osiUserDTO.getAttachmentType() );
        osiAttachments.setCreatedBy( osiUserDTO.getCreatedBy() );
        osiAttachments.setCreatedDate( osiUserDTO.getCreatedDate() );
        osiAttachments.setUpdatedBy( osiUserDTO.getUpdatedBy() );
        osiAttachments.setUpdatedDate( osiUserDTO.getUpdatedDate() );

        return osiAttachments;
    }
*/
    @Override
    public List<OsiAttachments> osiUserDTOListToOsiUserList(List<OsiAttachmentsDTO> osiUserDTO) {
        if ( osiUserDTO == null ) {
            return null;
        }

        List<OsiAttachments> list = new ArrayList<OsiAttachments>();
        for ( OsiAttachmentsDTO osiAttachmentsDTO : osiUserDTO ) {
            list.add( osiUserDTOToOsiUser( osiAttachmentsDTO ) );
        }

        return list;
    }
    
	private  String getBase64ForImage(String imagePath) {
		String springImageDataString = "";
		try {
			File file = new File(imagePath);
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			springImageDataString = Base64Utils.encodeToString(imageData);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return springImageDataString;
	}

	@Override
	public OsiAttachmentsDTO osiUserToOsiUserDTO(OsiAttachments osiUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OsiAttachments osiUserDTOToOsiUser(OsiAttachmentsDTO osiUserDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}
