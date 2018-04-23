/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.CrudOsiDepartmentVO;

/**
 * @author osi
 *
 */
public class CrudOsiDepratmentUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static CrudOsiDepartmentVO toCrudOsiDepratmentObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		CrudOsiDepartmentVO crudOsiDepratmentVO = mapper.readValue(request, CrudOsiDepartmentVO.class);
		return crudOsiDepratmentVO;
	}
}
