/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.CrudOsiSkilsVO;

/**
 * @author osi
 *
 */
public class CrudOsiSkilsUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static CrudOsiSkilsVO toCrudOsiSkilsObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		CrudOsiSkilsVO crudOsiSkilsVO = mapper.readValue(request, CrudOsiSkilsVO.class);
		return crudOsiSkilsVO;
	}
}
