/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.ListOsiSkilsVO;

/**
 * @author osi
 *
 */
public class ListOsiSkilsUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static ListOsiSkilsVO toOsiSkilsObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ListOsiSkilsVO osiskilsVO = mapper.readValue(request, ListOsiSkilsVO.class);
		return osiskilsVO;
	}
}
