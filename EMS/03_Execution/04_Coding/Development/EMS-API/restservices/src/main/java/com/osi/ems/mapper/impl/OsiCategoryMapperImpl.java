package com.osi.ems.mapper.impl;




import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiCategories;
import com.osi.ems.domain.OsiCategoryFields;
import com.osi.ems.domain.OsiOrganizations;
import com.osi.ems.mapper.OsiCategoryMapper;
import com.osi.ems.service.dto.OsiCategoryDTO;
import com.osi.ems.service.dto.OsiCategoryFieldsDTO;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.ems.service.dto.OsiTablesDTO;

@Component
public class OsiCategoryMapperImpl implements OsiCategoryMapper {

    @Override
    public OsiCategories osiCategoryDTOToOsiCategory(OsiCategoryDTO osiCategoryDTO) {
    	//OsiCategory osiCategorys = new OsiCategory();
    	 OsiCategories osiCategory = new OsiCategories();
        if ( osiCategoryDTO == null ) {
            return null;
        }
        if ( osiCategoryDTO.getId() != null ) {
        	osiCategory.setId(osiCategoryDTO.getId());;
        }
        osiCategory.setCatName(osiCategoryDTO.getCatName());
        osiCategory.setTblName(osiCategoryDTO.getTblName());
        osiCategory.setInlineOrPopup(osiCategoryDTO.getInlineOrPopup());
       // osiCategory.setOrg(osiCategoryDTO.getOrg());
        osiCategory.setOrgId(osiCategoryDTO.getOrgId());
        osiCategory.setActive(osiCategoryDTO.getActive());
        osiCategory.setCreatedBy(osiCategoryDTO.getCreatedBy());
        osiCategory.setCreatedDate(osiCategoryDTO.getCreatedDate());
        osiCategory.setUpdatedBy(osiCategoryDTO.getUpdatedBy());
        osiCategory.setUpdatedDate(osiCategoryDTO.getUpdatedDate());
    	 Set<OsiCategoryFields> osiCategoryFields = new HashSet<OsiCategoryFields>();
    	
        for( OsiCategoryFieldsDTO osiCategoryFieldsDTO : osiCategoryDTO.getOsiCategoryFields()) {
			  OsiCategoryFields osiCategoryField = new OsiCategoryFields();
			   if ( osiCategoryField.getId() != null ) {
				   osiCategoryField.setId(osiCategoryFieldsDTO.getId());
		        }
			    osiCategoryField.setColName(osiCategoryFieldsDTO.getColName());
			    osiCategoryField.setColValue(osiCategoryFieldsDTO.getColValue());
			    osiCategoryField.setColType(osiCategoryFieldsDTO.getColType());
			    osiCategoryField.setSeq(osiCategoryFieldsDTO.getSeq());
			    osiCategoryField.setColSrcType(osiCategoryFieldsDTO.getColSrcType());
			    osiCategoryField.setColSrc(osiCategoryFieldsDTO.getColSrc());
		    osiCategoryField.setNullable(osiCategoryFieldsDTO.getNullable());
		    osiCategoryField.setSrcpValid(osiCategoryFieldsDTO.getSrcpValid());
			    osiCategoryField.setOsiCategory(osiCategory);
			    osiCategoryFields.add(osiCategoryField);
			   }
        osiCategory.setOsiCategoryFields(osiCategoryFields);
        return osiCategory;
    }
    @Override
    public OsiCategoryDTO osiCategorysToosiCategoryDTO(OsiCategories osiCategory) {
    	   if ( osiCategory == null ) {
               return null;
           }

    	   OsiCategoryDTO osiCategoryDTO = new OsiCategoryDTO();

    	   osiCategoryDTO.setId( osiCategory.getId());
    	   osiCategoryDTO.setCatName(osiCategory.getCatName());
    	   osiCategoryDTO.setTblName(osiCategory.getTblName());
    	   osiCategoryDTO.setOrgId(osiCategory.getOrgId());
    	   osiCategoryDTO.setInlineOrPopup(osiCategory.getInlineOrPopup());
    	   //osiCategoryDTO.setOrg(osiCategory.getOrg());
    	   osiCategoryDTO.setActive( osiCategory.getActive() );
    	   osiCategoryDTO.setCreatedBy( osiCategory.getCreatedBy() );
    	   osiCategoryDTO.setCreatedDate( osiCategory.getCreatedDate() );
    	   osiCategoryDTO.setUpdatedBy( osiCategory.getUpdatedBy() );
    	   osiCategoryDTO.setUpdatedDate( osiCategory.getUpdatedDate() );
     Set<OsiCategoryFieldsDTO> osiCategoryFields = new HashSet<OsiCategoryFieldsDTO>();

           for( OsiCategoryFields osiCategoryFieldsDTO : osiCategory.getOsiCategoryFields()) {
        	   OsiCategoryFieldsDTO osiCategoryFieldDTO = new OsiCategoryFieldsDTO();
       	   osiCategoryFieldDTO.setId(osiCategoryFieldsDTO.getId());
       	   osiCategoryFieldDTO.setColName(osiCategoryFieldsDTO.getColName());
       	   osiCategoryFieldDTO.setColValue(osiCategoryFieldsDTO.getColValue());
       	   osiCategoryFieldDTO.setColType(osiCategoryFieldsDTO.getColType());
        	   osiCategoryFieldDTO.setSeq(osiCategoryFieldsDTO.getSeq());
       	   osiCategoryFieldDTO.setColSrcType(osiCategoryFieldsDTO.getColSrcType());
       	   osiCategoryFieldDTO.setColSrc(osiCategoryFieldsDTO.getColSrc());
       	   osiCategoryFieldDTO.setNullable(osiCategoryFieldsDTO.getNullable());
       	   osiCategoryFieldDTO.setSrcpValid(osiCategoryFieldsDTO.getSrcpValid());
        	   osiCategoryFieldDTO.setCatId(osiCategoryDTO.getId());
   			    osiCategoryFields.add(osiCategoryFieldDTO);
   			   }
           osiCategoryDTO.setOsiCategoryFields(osiCategoryFields);
           return osiCategoryDTO;
    }
   
	@Override
	public List<OsiTablesDTO> tablecolumnListToTableColumnDTOList(List<String> domainList) {
		  if ( domainList == null ) {
	            return null;
	        }
		
	        List<OsiTablesDTO> list = new ArrayList<OsiTablesDTO>();
	        for(String domain:domainList)
	        {
	        	  OsiTablesDTO osiTablesDTO =new OsiTablesDTO();
	        	osiTablesDTO.setColumnName(domain);
	        	list.add(osiTablesDTO);
	        }
	        return list;
	}
	@Override
	public List<OsiCategoryDTO> osiCategoryListToOsiCategoryDTOList(List<OsiCategories> osiCategories)  {
		  if ( osiCategories == null ) {
	            return null;
	        }

	        List<OsiCategoryDTO> list = new ArrayList<OsiCategoryDTO>();
	        for ( OsiCategories osiCategories_ : osiCategories ) {
	            list.add( osiCategorysToosiCategoryDTO( osiCategories_ ) );
	        }

	        return list;
	}
	
	@Override
	public OsiOrganizationsDTO osiOrganizationsToosiOrganizationDTO(OsiOrganizations osiOrganizations) {
		if ( osiOrganizations == null ) {
            return null;
        }

 	   OsiOrganizationsDTO osiOrganizationDTO = new OsiOrganizationsDTO();

 	   osiOrganizationDTO.setOrgId(osiOrganizations.getOrgId());
 	   osiOrganizationDTO.setOrgName(osiOrganizations.getOrgName());
 	  
        return osiOrganizationDTO;
	}
	@Override
	public List<OsiOrganizationsDTO> osiOrganizationsListToOsiOrganizationDTOList(List<OsiOrganizations> osiOrganizations) {
		 if ( osiOrganizations == null ) {
	            return null;
	        }

	        List<OsiOrganizationsDTO> list = new ArrayList<OsiOrganizationsDTO>();
	        for ( OsiOrganizations osiOrganizations_ : osiOrganizations ) {
	            list.add( osiOrganizationsToosiOrganizationDTO( osiOrganizations_ ) );
	        }

	        return list;
	}


}
