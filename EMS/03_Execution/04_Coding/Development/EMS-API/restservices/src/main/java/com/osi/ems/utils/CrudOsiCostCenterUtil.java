/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.CrudOsiCostCenterVO;

/**
 * @author osi
 *
 */
public class CrudOsiCostCenterUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static CrudOsiCostCenterVO toCrudOsiCostCenterObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		CrudOsiCostCenterVO crudOsiCostCenterVO = mapper.readValue(request, CrudOsiCostCenterVO.class);
		return crudOsiCostCenterVO;
	}
}
