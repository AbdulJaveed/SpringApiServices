/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.ListOsiDepartmentVO;

/**
 * @author osi
 *
 */
public class ListOsiDepratmentUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static ListOsiDepartmentVO toOsiDepratmentObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ListOsiDepartmentVO osidepratmentVO = mapper.readValue(request, ListOsiDepartmentVO.class);
		return osidepratmentVO;
	}
}
