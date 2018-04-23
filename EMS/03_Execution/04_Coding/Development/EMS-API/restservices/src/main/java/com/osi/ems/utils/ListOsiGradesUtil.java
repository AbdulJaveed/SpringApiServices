/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.ListOsiGradesVO;

/**
 * @author osi
 *
 */
public class ListOsiGradesUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static ListOsiGradesVO toOsiGradesObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ListOsiGradesVO osigradesVO = mapper.readValue(request, ListOsiGradesVO.class);
		return osigradesVO;
	}
}
