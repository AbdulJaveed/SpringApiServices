package com.osi.activiti.delegate;

import java.util.List;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.osi.activiti.exception.BusinessException;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.domain.OsiNotifications;
import com.osi.ems.repository.IOsiNotificationsRepository;

@Service("chkProbDate")
public class CheckProbationEndDateForEmployee {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IOsiNotificationsRepository notificationsRepo;
	
	public String onExecute(ActivityExecution execution) throws BpmnError {
		String probCheckResult = "NOTREACHED";
		try {
			OsiEmployees employee = (OsiEmployees) execution.getVariable("employee");
			if(employee.equals(null)) {
				LOGGER.info("Employee object is null");
				throw new BusinessException("ERR_1003", "Employee Details Not Found in execution");
			} else {
				List<OsiNotifications> existNotifications = notificationsRepo.findByNotificationEmpIdAndNotificationStatusAndNotificationObjectEndingWithIgnoreCase(employee.getEmployeeId(), "process", " For Probation End");
				if(existNotifications.isEmpty()) {
					LocalDate probEndDate = LocalDate.parse(employee.getProbEndDate());
					LocalDate today = LocalDate.now();
					int gapPeriodInDays = Days.daysBetween(today, probEndDate).getDays();
					LOGGER.info("Probtion Period Gap(In Days): "+gapPeriodInDays);
					int gapPeriod = Integer.parseInt(env.getProperty("ems.probation.remainder.gap.period"));
					if(gapPeriodInDays == 0) {
						probCheckResult = "REACHED";
					} else if(gapPeriodInDays == gapPeriod){
						probCheckResult = "REMAINDER";
					} 
				} else {
					LOGGER.info("**********************    Already Processing");
				}
			}
		} catch(NumberFormatException e) {
			LOGGER.info("Invalid Gap Period in Resource.properties");
			execution.setVariable("processDefId", execution.getProcessDefinitionId());
			execution.setVariable("processInsId", execution.getCurrentActivityName());
			execution.setVariable("error", e.getMessage() + " -- "+e.getLocalizedMessage());
			throw new BpmnError("ERR_1023", "Invalid Gap Period in Resource.properties");
		} catch(BusinessException e) {
			LOGGER.info("Error Occured while executing the Get Config mails from DB");
			execution.setVariable("processDefId", execution.getProcessDefinitionId());
			execution.setVariable("processInsId", execution.getCurrentActivityName());
			execution.setVariable("error", e.getMessage() + " -- "+e.getSystemMessage());
			throw new BpmnError(e.getErrorCode(), e.getSystemMessage());
		} catch(Exception e) {
			LOGGER.info("Error Occured while executing the Get Config mails from DB");
			execution.setVariable("processDefId", execution.getProcessDefinitionId());
			execution.setVariable("processInsId", execution.getCurrentActivityName());
			execution.setVariable("error", e.getMessage() + " -- "+e.getLocalizedMessage());
			throw new BpmnError("ERR_1019", "Error Occured while executing the Get Config mails from DB");
		}
		return probCheckResult;
	}
}
