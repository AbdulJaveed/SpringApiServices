/**
 * 
 */
package com.osi.ems.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.service.dto.CrudOsiCertificationsVO;

/**
 * @author osi
 *
 */
public class CrudOsiCertificationsUtil {
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	
	public static CrudOsiCertificationsVO toCrudOsiCertificationsObject(String request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		CrudOsiCertificationsVO crudOsiCertificationsVO = mapper.readValue(request, CrudOsiCertificationsVO.class);
		return crudOsiCertificationsVO;
	}
}
