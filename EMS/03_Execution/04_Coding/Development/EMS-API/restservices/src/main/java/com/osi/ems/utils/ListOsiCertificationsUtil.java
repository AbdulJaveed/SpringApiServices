/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.ListOsiCertificationsVO;

/**
 * @author osi
 *
 */
public class ListOsiCertificationsUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static ListOsiCertificationsVO toOsiCertificationsObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ListOsiCertificationsVO osicertificationsVO = mapper.readValue(request, ListOsiCertificationsVO.class);
		return osicertificationsVO;
	}
}
