/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.CrudOsiBusinessUnitsVO;

/**
 * @author osi
 *
 */
public class CrudOsiBusinessUnitsUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static CrudOsiBusinessUnitsVO toCrudOsiBusinessUnitsObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO = mapper.readValue(request, CrudOsiBusinessUnitsVO.class);
		return crudOsiBusinessUnitsVO;
	}
}
