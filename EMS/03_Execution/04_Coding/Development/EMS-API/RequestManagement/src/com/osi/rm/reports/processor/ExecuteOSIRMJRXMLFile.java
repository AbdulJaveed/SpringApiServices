package com.osi.rm.reports.processor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.osi.rm.common.ConnectionManager;
import com.osi.rm.common.CreateLogFile;
import com.osi.rm.common.JasperReportsUtil;
import com.osi.rm.vo.OSIRMReportDetails;
import com.osi.rm.vo.OSIRMRequest;

public class ExecuteOSIRMJRXMLFile {
	
	private OSIRMRequest osirmRequest;
	private OSIRMReportDetails osirmReportDetails;
	public ExecuteOSIRMJRXMLFile(){
		
	}
	
	public ExecuteOSIRMJRXMLFile(OSIRMRequest osirmRequest, OSIRMReportDetails osirmReportDetails){
		this.osirmRequest=osirmRequest;
		this.osirmReportDetails=osirmReportDetails;
	}
	public String executeJRXML() throws Exception {
		String file="";
		Properties properties=ConnectionManager.readProperties();
		String dbType = properties.getProperty("DB_TYPE").trim();
		String logfilename = osirmRequest.getLogFileDir()+osirmRequest.getRequestId()+".log";
		Logger logger = CreateLogFile.getLogger(logfilename, ExecuteOSIRMJRXMLFile.class);
		long startTime = System.currentTimeMillis();
		logger.info("executeJRXML() : Start - "+DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date()));
		
		try {
			logger.info("Report Name is : ***"+osirmReportDetails.getReportName()+"***");
 			logger.info("ReportType is : "+osirmReportDetails.getReportType()+" And ReportArgumentsType is :: "+osirmRequest.getReportArgumentsType());
			if(osirmReportDetails.getReportType().equalsIgnoreCase("JASPER")) {
				
				Map<String, Object> parameters = new HashMap<String, Object>(0);
				if(osirmRequest.getReportArgumentsType().equalsIgnoreCase("OBJECT")) {
			         try {
			        	 if (null != osirmRequest.getReportArguments()) {
				        	 if (dbType.equalsIgnoreCase("ORACLEDB")) {
				        		 InputStream is = new ByteArrayInputStream(osirmRequest.getReportArguments()); 
							     ObjectInputStream oip = new ObjectInputStream(is);
							     parameters =(Map<String, Object>)oip.readObject();
							     oip.close();
				        	 } else if (dbType.equalsIgnoreCase("MSSQLDB")) {
						         ByteArrayInputStream bais = new ByteArrayInputStream(osirmRequest.getReportArguments());
						         ObjectInputStream ins = new ObjectInputStream(bais);
						         parameters =(Map<String, Object>)ins.readObject();
						         ins.close();
				        	 } else if (dbType.equalsIgnoreCase("MYSQL")) {
				        		 InputStream is = new ByteArrayInputStream(osirmRequest.getReportArguments()); 
							     ObjectInputStream oip = new ObjectInputStream(is);
							     parameters =(Map<String, Object>)oip.readObject();
							     oip.close();
				        	 }
			        	 }
			            
			         } catch (Exception e) {
			        	 logger.error("Getting serializable object ",e);
			        	 throw e;
			         }

				} else if (osirmRequest.getReportArgumentsType().equalsIgnoreCase("STRING")){
					String reportArgs = null;
			        try {
			        	 if (dbType.equalsIgnoreCase("ORACLEDB")) {
			        		 InputStream is = new ByteArrayInputStream(osirmRequest.getReportArguments()); 
						     ObjectInputStream oip = new ObjectInputStream(is);
						     reportArgs =(String)oip.readObject();
					         logger.info("Parsing text object "+reportArgs);
						     oip.close();
			        	 } else if (dbType.equalsIgnoreCase("MSSQLDB")) {
					         ByteArrayInputStream bais = new ByteArrayInputStream(osirmRequest.getReportArguments());
					         ObjectInputStream ins = new ObjectInputStream(bais);
					         reportArgs =(String)ins.readObject();
					         logger.info("Parsing text object "+reportArgs);
					         ins.close();
			        	 }
			        } catch (Exception e) {
			        	logger.error("Getting serializable object ",e);
			        	throw e;
			        }
					
					String reportArg[]=null;
					if(reportArgs!=null){
						reportArg=reportArgs.split(";");
						for (String argument : reportArg) {
							String[] keyValue=argument.split("=");
							parameters.put(keyValue[0], keyValue[1]);
						}
					}
				}
				
				parameters.put("NO_DATA_MESSAGE","No Data Found For The Selected Criteria");
				parameters.put("jrxmlName", osirmReportDetails.getJrxmlName());
				parameters.put("REQUEST_ID", osirmRequest.getRequestId());
				parameters.put("FILE_NAME", osirmReportDetails.getReportName());
				parameters.put("TEMPLATE_LOC", osirmRequest.getJrxmlFileLocation());
				parameters.put("FILE_LOCATION", osirmRequest.getOutputFileDir());
				if(parameters.get("OUTPUT_TYPE")==null||parameters.get("OUTPUT_TYPE").equals(""))
					parameters.put("OUTPUT_TYPE", osirmRequest.getOutputType());
				parameters.put("REQUESTED_BY",osirmRequest.getRequestedBy());
				parameters.put("SUBREPORT_DIR",osirmRequest.getJrxmlFileLocation());
				parameters.put("ORACLE_REF_CURSOR","");
				/*parameters.put("jrxmlName", "RPTB.jasper");
				parameters.put("REQUEST_ID", "123");
				parameters.put("FILE_NAME", "RPTB");
				parameters.put("TEMPLATE_LOC", "D:\\");
				parameters.put("FILE_LOCATION", "D:\\");
				parameters.put("hostname", "ss");*/
			
				// Printing request parameter based on map object -- start
				//Map<String, Object> mapObj = new HashMap<String, Object>(0);

				logger.info("Input Arguments are ");
				for (Entry<String, Object> entry : parameters.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					logger.info(" "+key+" = "+value);
					//mapObj.put(key, value);
				}
             /* String toDate=parameters.get("TO_DATE").toString();
              String fromDate=parameters.get("FROM_DATE").toString();
              toDate=toDate.substring(1, toDate.length()-1);
              fromDate=fromDate.substring(1,fromDate.length()-1);
              parameters.put("TO_DATE", toDate);
              parameters.put("FROM_DATE", fromDate);*/
            		  // Printing request parameter based on map object -- end
				file=JasperReportsUtil.generateOSIRMReport(parameters,logger);
				parameters.clear();
			} else if (osirmReportDetails.getReportType().equalsIgnoreCase("JAVA")) {
				String objectParams = "";
				Map<String, Object> parameters = new HashMap<String, Object>();
				if(osirmRequest.getReportArgumentsType().equalsIgnoreCase("OBJECT")) {
			         try {
			        	 if (null != osirmRequest.getReportArguments()) {
				        	 if (dbType.equalsIgnoreCase("ORACLEDB")) {
				        		 InputStream is = new ByteArrayInputStream(osirmRequest.getReportArguments()); 
							     ObjectInputStream oip = new ObjectInputStream(is);
							     parameters =(Map<String, Object>)oip.readObject();
							     oip.close();
				        	 } else if (dbType.equalsIgnoreCase("MSSQLDB")) {
						         ByteArrayInputStream bais = new ByteArrayInputStream(osirmRequest.getReportArguments());
						         ObjectInputStream ins = new ObjectInputStream(bais);
						         parameters =(Map<String, Object>)ins.readObject();
						         ins.close();
				        	 }
			        	 }
			            
			         } catch (Exception e) {
			        	 logger.error("Getting serializable object ",e);
			        	 throw e;
			         }

				} 
				parameters.put("REQUEST_ID", osirmRequest.getRequestId());
				parameters.put("FILE_LOCATION", osirmRequest.getOutputFileDir());
				parameters.put("jrxmlName", osirmReportDetails.getJrxmlName());				
				parameters.put("REQUEST_ID", osirmRequest.getRequestId());				
				parameters.put("FILE_NAME", osirmReportDetails.getReportName());				
				parameters.put("TEMPLATE_LOC", osirmRequest.getJrxmlFileLocation());				
				if(parameters.get("OUTPUT_TYPE")==null||parameters.get("OUTPUT_TYPE").equals(""))				
					parameters.put("OUTPUT_TYPE", osirmRequest.getOutputType());				
				parameters.put("REQUESTED_BY",osirmRequest.getRequestedBy());
				parameters.put("FILE_NAME", osirmReportDetails.getReportName());
				parameters.put("NO_DATA_MESSAGE","No Data Found For The Selected Criteria");
				// Printing request parameter based on map object -- start
				logger.info("Input Arguments are ");
				for (Entry<String, Object> entry : parameters.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					logger.info("\n "+key+" = "+value);
				}
				// Printing request parameter based on map object -- end
				
				JavaWrapperInterface javaWrapperInterface = (JavaWrapperInterface)Class.forName(osirmReportDetails.getJrxmlName()).newInstance();
				file = javaWrapperInterface.executeJavaWrapper(ConnectionManager.getRawConnection(), logger, parameters);
				parameters.clear();
				
			} else {
				logger.info("Unsupported Report Format");
			}
		} catch (Exception e) {
			logger.error("Final Exception block of executeJRXML() ",e);
			throw e;
		}
		long endTime = System.currentTimeMillis();
		long difftime = endTime-startTime;
		int hours = (int) (difftime / 3600000);    
		int mins = (int) (difftime / (60000));
		int secs = (int) (difftime / (1000));
		logger.info("executeJRXML() : End - "+ DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date()));
		logger.info("executeJRXML()  : Time taken for report execution (hours:mins:secs) - "+ hours+":"+mins+":"+secs);
		logger.removeAllAppenders();
		return file;
	}
	
	/*public static void main(String[] args) {
		ExecuteOSIRMJRXMLFile executeOSIRMJRXMLFile=new ExecuteOSIRMJRXMLFile();
		try {
			executeOSIRMJRXMLFile.executeJRXML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
