/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.ListOsiCostCenterVO;

/**
 * @author osi
 *
 */
public class ListOsiCostCenterUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static ListOsiCostCenterVO toOsiCostCenterObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ListOsiCostCenterVO osicostcenterVO = mapper.readValue(request, ListOsiCostCenterVO.class);
		return osicostcenterVO;
	}
}
