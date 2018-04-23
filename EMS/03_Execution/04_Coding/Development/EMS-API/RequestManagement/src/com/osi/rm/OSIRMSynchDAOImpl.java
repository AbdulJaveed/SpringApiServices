package com.osi.rm;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.osi.rm.common.ConnectionManager;
import com.osi.rm.thread.OSIRMManagerThread;
import com.osi.rm.vo.OSIRMConfig;
import com.osi.rm.vo.OSIRMRequest;

public class OSIRMSynchDAOImpl {
	private static  Logger logger = Logger.getLogger(OSIRMSynchDAOImpl.class);
	public static OSIRMSynchDAOImpl oSIRMSynchDAOImpl;
	private OSIRMSynchDAOImpl(){}
		
	public static OSIRMSynchDAOImpl getInstance(){
		synchronized (OSIRMSynchDAOImpl.class){
			if(oSIRMSynchDAOImpl==null){
				oSIRMSynchDAOImpl = new OSIRMSynchDAOImpl();
			}
		}
		return oSIRMSynchDAOImpl;
	}
	
	public synchronized OSIRMRequest getNextRequest(OSIRMConfig configVO,String hostName, int pId) throws Exception{
		logger.info("OSIRMManagerDAOImpl::getNextRequest::start");
		Properties properties=null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		OSIRMRequest osirmRequest = null;
		try{
				properties=ConnectionManager.readProperties();
				connection=ConnectionManager.getRawConnection();
				String query=properties.getProperty("GET_NEXT_REQUEST");
				
				preparedStatement=connection.prepareStatement(query);
				resultSet=preparedStatement.executeQuery();
				if(resultSet.next()){
					// if(osirmRequest==null){  
						//synchronized{
						//   if(osirmRequest==null){  
							   System.out.println("Request Id"+resultSet.getInt("REQUEST_ID")+" :Thread name Start:: "+Thread.currentThread() +"::"+Thread.currentThread().getPriority());
							   osirmRequest = new OSIRMRequest();
							   // osirmRequest = new OSIRMRequest();
								osirmRequest.setRequestId(resultSet.getInt("REQUEST_ID"));
								osirmRequest.setReportId(resultSet.getInt("REPORT_ID"));
								osirmRequest.setReportArguments(resultSet.getBytes("REQUEST_ARGUMENTS"));
								osirmRequest.setReportArgumentsType(resultSet.getString("REQUEST_ARGUMENTS_TYPE"));
								osirmRequest.setOutputType(resultSet.getString("OUTPUT_TYPE"));
								osirmRequest.setRequestedBy(resultSet.getInt("REQUESTED_BY"));
								osirmRequest.setRequestDate(resultSet.getTimestamp("REQUEST_DATE"));
								osirmRequest.setIsrepeat(resultSet.getInt("IS_REPEAT"));
								osirmRequest.setRepeatInterval(resultSet.getInt("REPEAT_INTERVAL"));
								osirmRequest.setScheduleFrequencyId(resultSet.getInt("SCHEDULE_FREQUENCY_ID"));
								osirmRequest.setRequestScheEndDate(resultSet.getTimestamp("REQEST_SCHE_END_DATE"));
								//OSIRMConfig configVO = getRMConfig();
								osirmRequest.setLogFileDir(configVO.getLogFileDir());
								osirmRequest.setOutputFileDir(configVO.getOutputFileDir());
								osirmRequest.setDaysToKeep(configVO.getDaysToKeep());
								osirmRequest.setSleepInterval(configVO.getSleepInterval());
								osirmRequest.setJrxmlFileLocation(configVO.getJrxmlFileLocation());
								System.out.println("Request Id"+resultSet.getInt("REQUEST_ID")+" :Thread name End:: "+Thread.currentThread() +"::"+Thread.currentThread().getPriority());
								boolean updateFlag = this.updateOSIRMStartRequest(connection, osirmRequest, hostName, pId);
								if(!updateFlag) {
									//OSIRMRequest.destroyOsiRMReqeust();
									osirmRequest = null;
							}
						  //  }
						// }
					// }
				}
		}catch (Exception e) {
			logger.error("OSIRMManagerDAOImpl::getNextRequest:ERROR   ", e);
			throw e;
		}finally{
			try {
				if(resultSet!=null) resultSet.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				logger.error("OSIRMManagerDAOImpl::getNextRequest:ERROR   ", e2);
			}
		}
		logger.info("OSIRMManagerDAOImpl::getNextRequest::end");
		return osirmRequest;
	}
	
	public  boolean updateOSIRMStartRequest(Connection connection, OSIRMRequest osirmRequest, String hostName, int pId) throws Exception {
		logger.info("OSIRMManagerDAOImpl::updateOSIRMStartRequest::start");
		Statement statement=null;
		PreparedStatement preparedStatement=null;
		CallableStatement callableStatement = null;
		int rCount;
		Properties properties=null;
		boolean updateFlag = false;
		try{
			properties=ConnectionManager.readProperties();
			String dbType = properties.getProperty("DB_TYPE").trim();
			if (dbType.equalsIgnoreCase("ORACLEDB")) {
				String query=properties.getProperty("UPDATE_REQUEST_START_INFO");
				preparedStatement=connection.prepareStatement(query);
				preparedStatement.setString(1, osirmRequest.getLogFileDir()+osirmRequest.getRequestId()+".log");
				preparedStatement.setString(2, hostName);
				preparedStatement.setInt(3, pId);
				preparedStatement.setInt(4, osirmRequest.getRequestId());
				preparedStatement.execute();
				connection.commit();
				updateFlag = true;
			} else if (dbType.equalsIgnoreCase("MSSQLDB")) {
				logger.info("updateOSIRMStartRequest Start by calling stored procedure .. ");
				String query=properties.getProperty("UPDATE_REQUEST_START_INFO");
				preparedStatement=connection.prepareStatement(query);
				preparedStatement.setString(1, osirmRequest.getLogFileDir()+osirmRequest.getRequestId()+".log");
				preparedStatement.setString(2, hostName);
				preparedStatement.setInt(3, pId);
				preparedStatement.setInt(4, osirmRequest.getRequestId());
				preparedStatement.execute();
				connection.commit();
				logger.info("updateOSIRMStartRequest End by executing stored procedure .. ");
					updateFlag = true;
			} else if (dbType.equalsIgnoreCase("MYSQL")) {
				logger.info("updateOSIRMStartRequest Start by calling stored procedure .. ");
				String query=properties.getProperty("UPDATE_REQUEST_START_INFO");
				preparedStatement=connection.prepareStatement(query);
				preparedStatement.setString(1, osirmRequest.getLogFileDir()+osirmRequest.getRequestId()+".log");
				preparedStatement.setString(2, hostName);
				preparedStatement.setInt(3, pId);
				preparedStatement.setInt(4, osirmRequest.getRequestId());
				preparedStatement.execute();
				connection.commit();
				logger.info("updateOSIRMStartRequest End by executing stored procedure .. ");
					updateFlag = true;
			}
		} catch(SQLException se ) {
			updateFlag = false;
			logger.error("OSIRMManagerDAOImpl::updateOSIRMStartRequest:ERROR   ", se);
		} catch (Exception e) {
			updateFlag = false;
			logger.error("OSIRMManagerDAOImpl::updateOSIRMStartRequest:ERROR   ", e);
			throw e;
		}finally{
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(statement!=null) statement.close();
				if(callableStatement!=null) callableStatement.close();
			} catch (Exception e2) {
				logger.error("OSIRMManagerDAOImpl::updateOSIRMStartRequest:ERROR   ", e2);
			}
		}
		logger.info("OSIRMManagerDAOImpl::updateOSIRMStartRequest::end");
		return updateFlag;
	}
	
}
