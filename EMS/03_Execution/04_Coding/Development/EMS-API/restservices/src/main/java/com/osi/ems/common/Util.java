package com.osi.ems.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.urm.exception.BusinessException;

public class Util {
	public static String toJsonString(Object object) throws BusinessException {
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			response = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			//e.printStackTrace();
			throw new BusinessException("ERR_1000", "unable to parse the result");
		}
		return response;
	}
}
