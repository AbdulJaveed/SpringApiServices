/**
 * 
 */
package com.osi.rm.reports.processor;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author srkummitha
 *
 */
public class PurgeOSIRMRequestHistory implements JavaWrapperInterface {

	/* (non-Javadoc)
	 * @see com.osi.rm.reports.processor.JavaWrapperInterface#executeJavaWrapper(java.sql.Connection, org.apache.log4j.Logger, java.util.Map)
	 */
	public String executeJavaWrapper(Connection connection, Logger logger,
			Map<String, Object> parameters) throws Exception {
		logger.info("executeJavaWrapper(-,-,-) :: Start");
		String result = "Failed";
		String noOfDays = null;
		PreparedStatement preparedStatement=null, preparedStatement1=null;
		ResultSet resultSet=null;
		String outputFilePath = parameters.get("FILE_LOCATION")+""+parameters.get("REQUEST_ID")+".TXT";
		try {
			noOfDays = (String) parameters.get("NO_OF_DAYS");
			
			String query = "SELECT LOG_FILE_NAME, OUTPUT_FILE_NAME FROM OSI_RM_REQUESTS WHERE REQUEST_DATE < CURRENT_TIMESTAMP - ?";
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(noOfDays.trim()));
			preparedStatement.execute();
			resultSet=preparedStatement.executeQuery();
			while (resultSet.next()){
				if (null != resultSet.getString("LOG_FILE_NAME")) {
					File logFile = new File(resultSet.getString("LOG_FILE_NAME"));
					if(logFile.exists())
						logFile.delete();
				}
				if(null != resultSet.getString("OUTPUT_FILE_NAME")) {
					File outFile = new File(resultSet.getString("OUTPUT_FILE_NAME"));
					if(outFile.exists())
						outFile.delete();
				}
				
			}
			
			query = "DELETE FROM OSI_RM_REQUESTS WHERE REQUEST_DATE < CURRENT_TIMESTAMP - ?";
			preparedStatement1=connection.prepareStatement(query);
			preparedStatement1.setInt(1, Integer.parseInt(noOfDays.trim()));
			preparedStatement1.executeUpdate();
			connection.commit();
			result = "Success";
		} catch (Exception e) {
			logger.error("executeJavaWrapper(-,-,-) : Exception", e);
			throw e;
		}
		logger.info("executeJavaWrapper(-,-,-) :: End");
		try {
			String content = "";
			if (result.equalsIgnoreCase("Success")) {
				content = "Purge Job Executed Successfully";
			} else {
				content = "Failed to execute Purge Job";
			}
			FileOutputStream fop = null;
			File file = new File(outputFilePath);
			fop = new FileOutputStream(file);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} catch (Exception e){
			logger.error("executeJavaWrapper(-,-,-) Writing file : Exception", e);
		}
		
		return parameters.get("REQUEST_ID")+".TXT";
	}

}
