package com.osi.urm.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.urm.domain.OsiLookupTypes;
import com.osi.urm.domain.OsiLookupValues;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.mapper.OsiLookupMapper;
import com.osi.urm.service.dto.OsiLookupTypesDTO;
import com.osi.urm.service.dto.OsiLookupValuesDTO;
@Component
public class OsiLookupMapperImpl implements OsiLookupMapper {
	
	@Autowired
	private CommonService commonService;
	
	@Override
	public OsiLookupTypesDTO osiLookupToOsiLookupDTO(
			OsiLookupTypes osiLookupTypes) {
		// TODO Auto-generated method stub
		
		OsiLookupTypesDTO osiLookupTypesDTO = new OsiLookupTypesDTO();
		osiLookupTypesDTO.setId(osiLookupTypes.getId());
		osiLookupTypesDTO.setLookupName(osiLookupTypes.getLookupName());
		osiLookupTypesDTO.setLookupCode(osiLookupTypes.getLookupCode());
	//	osiLookupTypesDTO.setBusinessGroupId(osiLookupTypes.getBusinessGroupId());
		osiLookupTypesDTO.setCreatedBy(osiLookupTypes.getCreatedBy());
		osiLookupTypesDTO.setCreatedDate(osiLookupTypes.getCreatedDate());
		osiLookupTypesDTO.setUpdatedBy(osiLookupTypes.getUpdatedBy());
		osiLookupTypesDTO.setUpdatedDate(osiLookupTypes.getUpdatedDate());
	//	osiLookupTypesDTO.setBusinessGroupId(osiLookupTypes.getBusinessGroupId());
		osiLookupTypesDTO.setActive(osiLookupTypes.getActive());
		Set<OsiLookupValuesDTO> values=osiLookupValuesSetToOsiLookupValuesDTOSet(osiLookupTypes.getOsiLookupValueses());
		osiLookupTypesDTO.setOsiLookupValueses(values);
		return osiLookupTypesDTO;
	}

	@Override
	public OsiLookupTypes osiLookupDTOToOsiLookup(
			OsiLookupTypesDTO osiLookupTypesDTO) {
		// TODO Auto-generated method stub
		OsiLookupTypes osiLookupTypes = new OsiLookupTypes();
		osiLookupTypes.setId(osiLookupTypesDTO.getId());
		osiLookupTypes.setLookupName(osiLookupTypesDTO.getLookupName());
		osiLookupTypes.setLookupCode(osiLookupTypesDTO.getLookupCode());
	//	osiLookupTypes.setBusinessGroupId(osiLookupTypesDTO.getBusinessGroupId());
		osiLookupTypes.setCreatedBy(osiLookupTypesDTO.getCreatedBy());
		osiLookupTypes.setCreatedDate(osiLookupTypesDTO.getCreatedDate());
		osiLookupTypes.setUpdatedBy(osiLookupTypesDTO.getUpdatedBy());
		osiLookupTypes.setUpdatedDate(osiLookupTypesDTO.getUpdatedDate());
	//	osiLookupTypes.setBusinessGroupId(osiLookupTypesDTO.getBusinessGroupId());
		osiLookupTypes.setActive(osiLookupTypesDTO.getActive());
		Set<OsiLookupValues> values=osiLookupValuesDTOSetToOsiLookupValuesSet(osiLookupTypesDTO.getOsiLookupValueses(),osiLookupTypes);
		osiLookupTypes.setOsiLookupValueses(values);
		return osiLookupTypes;
	}

	//@Override
	private OsiLookupValuesDTO osiLookupValueToOsiLookupValueDTO(
			OsiLookupValues osiLookupValues) {
		// TODO Auto-generated method stub
		
		OsiLookupValuesDTO osiLookupValuesDTO = new OsiLookupValuesDTO();
		osiLookupValuesDTO.setId(osiLookupValues.getId());
		osiLookupValuesDTO.setLookupDesc(osiLookupValues.getLookupDesc());
		osiLookupValuesDTO.setLookupValue(osiLookupValues.getLookupValue());
		//OsiLookupValuesDTO osiLookupValuesDTO = modelMapper.map(osiLookupValues, OsiLookupValuesDTO.class);
		//osiLookupValuesDTO.setBusinessGroupId(businessGroupId);
		osiLookupValuesDTO.setOsiLookupTypes(null);
		osiLookupValuesDTO.setLookupSeqNum(osiLookupValues.getLookupSeqNum());
		return osiLookupValuesDTO;
	}

	//@Override
	private OsiLookupValues osiLookupValueDTOToOsiLookupValue(
			OsiLookupValuesDTO osiLookupValuesDTO) {
		// TODO Auto-generated method stub
		//OsiLookupValues osiLookupValues = modelMapper.map(osiLookupValuesDTO, OsiLookupValues.class);
		OsiLookupValues osiLookupValues = new OsiLookupValues();
		osiLookupValues.setId(osiLookupValuesDTO.getId());
		osiLookupValues.setLookupDesc(osiLookupValuesDTO.getLookupDesc());
		osiLookupValues.setLookupValue(osiLookupValuesDTO.getLookupValue());
		//osiLookupValues.setId(null);
		//osiLookupValues.setBusinessGroupId(businessGroupId);
		osiLookupValues.setCreatedBy(osiLookupValuesDTO.getCreatedBy());
		osiLookupValues.setUpdatedBy(osiLookupValuesDTO.getUpdatedBy());
		osiLookupValues.setLookupSeqNum(osiLookupValuesDTO.getLookupSeqNum());
		return osiLookupValues;
	}

	//@Override
	private Set<OsiLookupValuesDTO> osiLookupValuesSetToOsiLookupValuesDTOSet(
			Set<OsiLookupValues> osiLookupValuesSet) {
		Set<OsiLookupValuesDTO> osiLookupValuesDTOSet = new HashSet<OsiLookupValuesDTO>();
		for(OsiLookupValues osiLookupValues:osiLookupValuesSet){
			OsiLookupValuesDTO osiLookupValuesDTO=osiLookupValueToOsiLookupValueDTO(osiLookupValues);
			osiLookupValuesDTOSet.add(osiLookupValuesDTO);
		}
		// TODO Auto-generated method stub
		return osiLookupValuesDTOSet;
	}

	//@Override
	private Set<OsiLookupValues> osiLookupValuesDTOSetToOsiLookupValuesSet(
			Set<OsiLookupValuesDTO> osiLookupValuesDTOSet,OsiLookupTypes osiLookupTypes) {
		// TODO Auto-generated method stub
		Set<OsiLookupValues> osiLookupValuesSet = new HashSet<OsiLookupValues>();
		for(OsiLookupValuesDTO osiLookupValuesDTO:osiLookupValuesDTOSet){
			OsiLookupValues osiLookupValues=osiLookupValueDTOToOsiLookupValue(osiLookupValuesDTO);
			osiLookupValues.setOsiLookupTypes(osiLookupTypes);
			osiLookupValues.setCreatedBy(osiLookupTypes.getCreatedBy());
			osiLookupValues.setUpdatedBy(osiLookupTypes.getUpdatedBy());			
			try {
				if(null != osiLookupValuesDTO.getId()) {
					osiLookupValues.setUpdatedDate(commonService.getCurrentDateinUTC());
				} else {
					osiLookupValues.setCreatedDate(commonService.getCurrentDateinUTC());
					osiLookupValues.setUpdatedDate(commonService.getCurrentDateinUTC());
				}
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			osiLookupValuesSet.add(osiLookupValues);
		}
		// TODO Auto-generated method stub
		return osiLookupValuesSet;
	}

	



	
	

	

    /*@Override
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
    }*/
}
