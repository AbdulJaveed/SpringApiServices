/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.CrudOsiJobCodesVO;

/**
 * @author osi
 *
 */
public class CrudOsiJobCodesUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static CrudOsiJobCodesVO toCrudOsiJobCodesObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		CrudOsiJobCodesVO crudOsiJobCodesVO = mapper.readValue(request, CrudOsiJobCodesVO.class);
		return crudOsiJobCodesVO;
	}
}
