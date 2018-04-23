package com.osi.activiti.scheduler;

import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osi.activiti.exception.BusinessException;

@Service("runtimeServiceForExplorer")
public class EMSRuntimeServiceForExplorer {
	
	private final Logger LOGGER = LoggerFactory.getLogger(EMSRuntimeServiceForExplorer.class);
	
//	private ResourceBundle bundle = ResourceBundle.getBundle("resources");
	
	@Autowired
	private RuntimeService runtimeService;
	

	/**
	 * startProcess
	 * @throws BusinessException
	 */
	public void startEMSActivitiProcess(String processDefinition) throws BusinessException {
		LOGGER.info("###### Start :EMSRuntimeServiceForExplorer");
		LOGGER.info("************ "+processDefinition);
		//runtimeService.startProcessInstanceByKey(ActivitiUtil.PROCESS_COMMON_REMAINDER_TO_EMPLOYEES);
		runtimeService.startProcessInstanceByKey(processDefinition);
		
		LOGGER.info("###### End : EMSRuntimeServiceForExplorer");
	}
}