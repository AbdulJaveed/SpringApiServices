/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.ListOsiJobCodesVO;

/**
 * @author osi
 *
 */
public class ListOsiJobCodesUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static ListOsiJobCodesVO toOsiJobCodesObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ListOsiJobCodesVO osijobcodesVO = mapper.readValue(request, ListOsiJobCodesVO.class);
		return osijobcodesVO;
	}
}
