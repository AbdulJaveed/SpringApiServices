/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.ListOsiTitlesVO;

/**
 * @author osi
 *
 */
public class ListOsiTitlesUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static ListOsiTitlesVO toOsiTitlesObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ListOsiTitlesVO osititlesVO = mapper.readValue(request, ListOsiTitlesVO.class);
		return osititlesVO;
	}
}
