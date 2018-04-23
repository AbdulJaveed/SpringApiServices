/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.CrudOsiTitlesVO;

/**
 * @author osi
 *
 */
public class CrudOsiTitlesUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static CrudOsiTitlesVO toCrudOsiTitlesObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		CrudOsiTitlesVO crudOsiTitlesVO = mapper.readValue(request, CrudOsiTitlesVO.class);
		return crudOsiTitlesVO;
	}
}
