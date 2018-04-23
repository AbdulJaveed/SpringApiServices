/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.CrudOsiGradesVO;

/**
 * @author osi
 *
 */
public class CrudOsiGradesUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static CrudOsiGradesVO toCrudOsiGradesObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		CrudOsiGradesVO crudOsiGradesVO = mapper.readValue(request, CrudOsiGradesVO.class);
		return crudOsiGradesVO;
	}
}
