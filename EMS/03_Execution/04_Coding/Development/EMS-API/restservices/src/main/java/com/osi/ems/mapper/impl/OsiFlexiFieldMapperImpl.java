package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiCategories;
import com.osi.ems.domain.OsiCategoryFields;
import com.osi.ems.mapper.OsiFlexiFieldMapper;
import com.osi.ems.service.dto.OsiFlexiDataDTO;
import com.osi.ems.service.dto.OsiFlexiFieldDTO;

@Component
public class OsiFlexiFieldMapperImpl implements OsiFlexiFieldMapper {

	public List<OsiFlexiFieldDTO> toFlexiFieldsVOList(List<OsiCategories> categoryList) {
		List<OsiFlexiFieldDTO> flexiFieldList = new ArrayList<OsiFlexiFieldDTO>();
		System.out.println("toFlexiFieldsVOList Convert Entity to DTO......");
		for(OsiCategories cat : categoryList) {
			OsiFlexiFieldDTO flexiField = new OsiFlexiFieldDTO();
			
			flexiField.setTableName(cat.getTblName());
			
			List<OsiFlexiDataDTO> flexiDataList = new ArrayList<OsiFlexiDataDTO>();
			if(null != cat.getOsiCategoryFields()) {
				for(OsiCategoryFields cf : cat.getOsiCategoryFields()) {
					OsiFlexiDataDTO flexiData = new OsiFlexiDataDTO();
					flexiData.setColumnName(cf.getColName());
					flexiData.setColumnSeq(cf.getSeq());
					flexiData.setColumnSource(cf.getColSrc());
					flexiData.setColumnSourceType(cf.getColSrcType());
					flexiData.setColumnType(cf.getColType());
					flexiData.setColumnValue(cf.getColValue());
					flexiData.setJavascriptValidation(cf.getSrcpValid());
					flexiData.setIsMandatory(cf.getNullable());
					flexiDataList.add(flexiData);
				}
			}
			
			flexiField.setFlexiDataList(flexiDataList);
			flexiFieldList.add(flexiField);
		}
		return flexiFieldList;
	}
}
