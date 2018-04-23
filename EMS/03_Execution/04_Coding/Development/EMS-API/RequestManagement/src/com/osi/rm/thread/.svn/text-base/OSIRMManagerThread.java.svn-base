/**
 * 
 */
package com.osi.rm.thread;

import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.osi.rm.OSIRMManager;
import com.osi.rm.OSIRMManagerDAOImpl;
import com.osi.rm.OSIRMSynchDAOImpl;
import com.osi.rm.reports.processor.ExecuteOSIRMJRXMLFile;
import com.osi.rm.vo.OSIRMConfig;
import com.osi.rm.vo.OSIRMReportDetails;
import com.osi.rm.vo.OSIRMRequest;
import com.osi.rm.vo.OSIRMSchedulerFrequencies;

/**
 * @author jkorada
 *
 */
public class OSIRMManagerThread extends Thread {
	private static  Logger logger = Logger.getLogger(OSIRMManagerThread.class);
	private String hostName;
	private int pId;
	private Timestamp currentTimestamp;
	private OSIRMConfig rmconfig;
	
	public OSIRMManagerThread(){
		
	}
	
	public OSIRMManagerThread(String hostName, int pId, Timestamp currentTimestamp, OSIRMConfig rmconfig){
		this.hostName=hostName;
		this.pId=pId;
		this.currentTimestamp=currentTimestamp;
		this.rmconfig=rmconfig;
	}
	public void run() {
		logger.info("OSIRMManagerThread::run::start");
		// TODO Auto-generated method stub
		//OSIRMConfig rmconfig = null;
		OSIRMReportDetails osirmReportDetails = null;
		try {
			OSIRMManagerDAOImpl osirmManagerDAOImpl = new OSIRMManagerDAOImpl(this.hostName, this.pId);
			//rmconfig = osirmManagerDAOImpl.getRMConfig();
			OSIRMRequest osirmRequest = null;
			OSIRMSynchDAOImpl oSIRMSynchDAOImpl =OSIRMSynchDAOImpl.getInstance(); 
			System.out.println("Object Hash "+oSIRMSynchDAOImpl.hashCode()+" :Thread actual Start:: "+Thread.currentThread() +"::"+Thread.currentThread().getPriority());
			while(true){
				try {
					//synchronized(oSIRMSynchDAOImpl){
						osirmRequest = oSIRMSynchDAOImpl.getNextRequest(this.rmconfig,this.hostName, this.pId);
						if(osirmRequest!=null && osirmRequest.getRequestId()!= 0) {
							osirmManagerDAOImpl.UpdateCurrentRunningThreads(hostName, 1);
							//OSIRMManagerThread.sleep(5000); // Added for testing threads
							osirmReportDetails = osirmManagerDAOImpl.getReportDetails(osirmRequest.getReportId());
							//System.out.println("Thread name :: "+OSIRMManagerThread.currentThread());
							ExecuteOSIRMJRXMLFile executeOSIRMJRXMLFile = new ExecuteOSIRMJRXMLFile(osirmRequest, osirmReportDetails);
							String outputFileName = executeOSIRMJRXMLFile.executeJRXML();
							osirmManagerDAOImpl.updateOSIRMEndRequest(osirmRequest, outputFileName);
							osirmManagerDAOImpl.UpdateCurrentRunningThreads(hostName, -1);
						} else {
							OSIRMManagerThread.sleep(this.rmconfig.getSleepInterval()*1000);
						}
				//	}
					//osirmRequest = osirmManagerDAOImpl.getNextRequest(this.rmconfig);
					/*if(osirmRequest!=null && osirmRequest.getRequestId()!= 0) {
						osirmManagerDAOImpl.UpdateCurrentRunningThreads(hostName, 1);
						//OSIRMManagerThread.sleep(5000); // Added for testing threads
						osirmReportDetails = osirmManagerDAOImpl.getReportDetails(osirmRequest.getReportId());
						//System.out.println("Thread name :: "+OSIRMManagerThread.currentThread());
						ExecuteOSIRMJRXMLFile executeOSIRMJRXMLFile = new ExecuteOSIRMJRXMLFile(osirmRequest, osirmReportDetails);
						String outputFileName = executeOSIRMJRXMLFile.executeJRXML();
						osirmManagerDAOImpl.updateOSIRMEndRequest(osirmRequest, outputFileName);
						osirmManagerDAOImpl.UpdateCurrentRunningThreads(hostName, -1);
					} else {
						OSIRMManagerThread.sleep(this.rmconfig.getSleepInterval()*1000);
					}*/
				} catch (Exception e) {
					osirmManagerDAOImpl.updateOSIRMErrorEndRequest(osirmRequest);
					logger.error("OSIRMManagerThread::run::ERROR ="+ e.getMessage());
				}
				
				if (null != osirmRequest && osirmRequest.getIsrepeat() == 1 && osirmRequest.getRequestScheEndDate().after(this.currentTimestamp)
						&& (osirmManagerDAOImpl.checkParentId(osirmRequest.getRequestId())==0)) {
					OSIRMSchedulerFrequencies osirmSchedulerFrequencies = osirmManagerDAOImpl.getScheduleFrequencies(osirmRequest);
					Timestamp requestDate = osirmRequest.getRequestDate();
					Calendar calendar = Calendar.getInstance();
			        calendar.setTimeInMillis(requestDate.getTime());
					if (osirmSchedulerFrequencies.getScheduleFrequencyName().equalsIgnoreCase("Minute")) {
						calendar.add(Calendar.MINUTE, osirmRequest.getRepeatInterval());
					}
					if (osirmSchedulerFrequencies.getScheduleFrequencyName().equalsIgnoreCase("Hourly")) {
						calendar.add(Calendar.HOUR, osirmRequest.getRepeatInterval());
					}
					if (osirmSchedulerFrequencies.getScheduleFrequencyName().equalsIgnoreCase("Daily")) {
						calendar.add(Calendar.DATE, osirmRequest.getRepeatInterval());
					}
					if (osirmSchedulerFrequencies.getScheduleFrequencyName().equalsIgnoreCase("Weekly")) {
						calendar.add(Calendar.WEEK_OF_MONTH, osirmRequest.getRepeatInterval());
					}					
					if (osirmSchedulerFrequencies.getScheduleFrequencyName().equalsIgnoreCase("Monthly")) {
						calendar.add(Calendar.MONTH, osirmRequest.getRepeatInterval());
					}
					if (osirmSchedulerFrequencies.getScheduleFrequencyName().equalsIgnoreCase("Yearly")) {
						calendar.add(Calendar.YEAR, osirmRequest.getRepeatInterval());
					}
					osirmRequest.setRequestDate(new Timestamp(calendar.getTimeInMillis()));
					osirmManagerDAOImpl.insertOSIRMRequest(osirmRequest);
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error("OSIRMManagerThread::run::ERROR ="+ e1.getMessage());
		}
		logger.info("OSIRMManagerThread::run::start");
	}
	
}

