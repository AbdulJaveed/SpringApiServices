/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.ListOsiBusinessUnitsVO;

/**
 * @author osi
 *
 */
public class ListOsiBusinessUnitsUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static ListOsiBusinessUnitsVO toOsiBusinessUnitsObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ListOsiBusinessUnitsVO osibusinessunitsVO = mapper.readValue(request, ListOsiBusinessUnitsVO.class);
		return osibusinessunitsVO;
	}
}
