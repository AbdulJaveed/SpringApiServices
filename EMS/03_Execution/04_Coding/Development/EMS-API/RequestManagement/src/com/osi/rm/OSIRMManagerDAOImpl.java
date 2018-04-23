package com.osi.rm;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.osi.rm.common.ConnectionManager;
import com.osi.rm.thread.OSIRMManagerThread;
import com.osi.rm.vo.OSIRMConfig;
import com.osi.rm.vo.OSIRMReportDetails;
import com.osi.rm.vo.OSIRMRequest;
import com.osi.rm.vo.OSIRMSchedulerFrequencies;

public class OSIRMManagerDAOImpl {
	private static  Logger logger = Logger.getLogger(OSIRMManagerDAOImpl.class);
	private String hostName=null;
	private int pId=0;

	public OSIRMManagerDAOImpl(){}
	public OSIRMManagerDAOImpl(String hostName, int pId) {
		this.hostName=hostName;
		this.pId = pId;
	}
	
	public void insertOSIRMRequest(OSIRMRequest osirmRequest) throws Exception {
		logger.info("OSIRMManagerDAOImpl::insertOSIRMRequest::start");
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		Properties properties=null;
		try {
			properties=ConnectionManager.readProperties();
			String query=properties.getProperty("INSERT_OSIRM_REQUEST");
			connection = ConnectionManager.getRawConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, osirmRequest.getReportId());
			preparedStatement.setBytes(2, osirmRequest.getReportArguments());
			preparedStatement.setString(3, osirmRequest.getReportArgumentsType());
			preparedStatement.setInt(4, osirmRequest.getRequestedBy());
			preparedStatement.setString(5, osirmRequest.getOutputType());
			preparedStatement.setTimestamp(6, osirmRequest.getRequestDate());
			preparedStatement.setInt(7, osirmRequest.getIsrepeat());
			preparedStatement.setInt(8, osirmRequest.getRepeatInterval());
			preparedStatement.setInt(9, osirmRequest.getScheduleFrequencyId());
			preparedStatement.setTimestamp(10, osirmRequest.getRequestScheEndDate());
			preparedStatement.setInt(11, osirmRequest.getRequestId());
			preparedStatement.setInt(12, osirmRequest.getBusinessGroupId());
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			logger.error("OSIRMManagerDAOImpl::insertOSIRMRequest:ERROR   ", e);
		} finally{
			try {
				if(preparedStatement!=null) 
					preparedStatement.close();
				if(connection!=null) 
					connection.close();
			} catch (Exception e2) {
				logger.error("OSIRMManagerDAOImpl::insertOSIRMRequest:ERROR   ", e2);
			}
		}
		logger.info("OSIRMManagerDAOImpl::insertOSIRMRequest::end");
	}
	public OSIRMSchedulerFrequencies getScheduleFrequencies(OSIRMRequest osirmRequest) throws Exception {
		logger.info("OSIRMManagerDAOImpl::getScheduleFrequencies::start");
		OSIRMSchedulerFrequencies osirmSchedulerFrequencies = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Properties properties=null;
		
		try {
			properties=ConnectionManager.readProperties();
			String query=properties.getProperty("GET_SCHEDULER_FREQUENCY_NAME");
			connection = ConnectionManager.getRawConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, osirmRequest.getScheduleFrequencyId());
			preparedStatement.execute();
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				osirmSchedulerFrequencies = new OSIRMSchedulerFrequencies();
				osirmSchedulerFrequencies.setScheduleFrequencyId(osirmRequest.getScheduleFrequencyId());
				osirmSchedulerFrequencies.setScheduleFrequencyName(resultSet.getString("SCHEDULE_FREQUENCY_NAME"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("OSIRMManagerDAOImpl::getScheduleFrequencies:ERROR   ", e);
		} finally{
			try {
				if(resultSet!=null) resultSet.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				logger.error("OSIRMManagerDAOImpl::getScheduleFrequencies:ERROR   ", e2);
			}
		}
		logger.info("OSIRMManagerDAOImpl::getScheduleFrequencies::end");
		return osirmSchedulerFrequencies;
	}
	
	public synchronized OSIRMRequest getNextRequest(OSIRMConfig configVO) throws Exception{
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
							   System.out.println("Thread name Start:: "+OSIRMManagerThread.currentThread() +"::"+OSIRMManagerThread.currentThread().getPriority());
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
								System.out.println("Thread name End:: "+OSIRMManagerThread.currentThread() +"::"+OSIRMManagerThread.currentThread().getPriority());
								boolean updateFlag = this.updateOSIRMStartRequest(connection, osirmRequest, this.hostName, this.pId);
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
	
	/*public OSIRMConfig getRMConfig() throws Exception{
		logger.info("OSIRMManagerDAOImpl::getRMConfig::start");
		OSIRMConfig configVO=null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Properties properties=null;
		try {
			properties=ConnectionManager.readProperties();
			String query=properties.getProperty("GET_OSI_RM_CONFIG");
			connection=ConnectionManager.getRawConnection();
			preparedStatement=connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				configVO=new OSIRMConfig();
				configVO.setLogFileDir(resultSet.getString("LOG_FILE_DIR"));
				configVO.setOutputFileDir(resultSet.getString("OUTPUT_FILE_DIR"));
				configVO.setDaysToKeep(resultSet.getInt("DAYS_TO_KEEP"));
				configVO.setSleepInterval(resultSet.getInt("SLEEP_INTERVAL"));
				configVO.setJrxmlFileLocation(resultSet.getString("JRXML_FILE_LOCATION"));	
			}
		} catch (Exception e) {
			logger.error("OSIRMManagerDAOImpl::getRMConfig:ERROR   ", e);
			throw e;
		}finally{
			try {
				if(resultSet!=null) resultSet.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				logger.error("OSIRMManagerDAOImpl::getRMConfig:ERROR   ", e2);
			}
		}
		logger.info("OSIRMManagerDAOImpl::getRMConfig::end");
		return configVO;
	}*/
	
	public OSIRMReportDetails getReportDetails(int reportId) throws Exception {
		logger.info("OSIRMManagerDAOImpl::getReportDetails::start");
		OSIRMReportDetails osirmReportDetails = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Properties properties=null;
		try {
			properties=ConnectionManager.readProperties();
			String query=properties.getProperty("GET_OSI_RM_REPORT_DETAILS");
			connection=ConnectionManager.getRawConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, reportId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				osirmReportDetails = new OSIRMReportDetails();
				osirmReportDetails.setReportId(resultSet.getInt("REPORT_ID"));
				osirmReportDetails.setReportName(resultSet.getString("USER_REPORT_NAME"));
				osirmReportDetails.setJrxmlName(resultSet.getString("SRC_FILE_NAME"));
				osirmReportDetails.setReportType(resultSet.getString("REPORT_TYPE"));
				osirmReportDetails.setOutputType(resultSet.getString("OUTPUT_TYPE"));
			}
		} catch (Exception e) {
			logger.error("OSIRMManagerDAOImpl::getReportDetails:ERROR   ", e);
			throw e;
		}finally{
			try {
				if(resultSet!=null) resultSet.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				logger.error("OSIRMManagerDAOImpl::getReportDetails:ERROR   ", e2);
			}
		}
		logger.info("OSIRMManagerDAOImpl::getReportDetails::end");
		return osirmReportDetails;
	}
	
	public boolean updateOSIRMStartRequest(Connection connection, OSIRMRequest osirmRequest, String hostName, int pId) throws Exception {
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
			}else if (dbType.equalsIgnoreCase("MYSQL")) {
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
	
	/*public boolean updateOSIRMStartRequest(Connection connection, OSIRMRequest osirmRequest, String hostName, int pId) throws Exception {
		CallableStatement callableStatement = null;
		int rCount;
		boolean updateFlag = false;
		try{
			System.out.println("updateOSIRMStartRequest Start by calling stored procedure .. ");
			String updateOSIRMRequests = "{call upd_osi_rm_requests(?,?,?,?,?)}";
			callableStatement = connection.prepareCall(updateOSIRMRequests);
			callableStatement.setInt(1, osirmRequest.getRequestId());
			callableStatement.setString(2, osirmRequest.getLogFileDir()+osirmRequest.getRequestId()+".log");
			callableStatement.setString(3, hostName);
			callableStatement.setInt(4, pId);
			callableStatement.registerOutParameter(5, java.sql.Types.INTEGER);
			callableStatement.executeUpdate();
			connection.commit();
			rCount = callableStatement.getInt(5);
			System.out.println("updateOSIRMStartRequest End by executing stored procedure .. ");
			if (rCount>0)
				updateFlag = true;
		}catch (Exception e) {
			updateFlag = false;
			throw e;
		}finally{
			try {
				if(callableStatement!=null) callableStatement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return updateFlag;
	}*/
	
	public void updateOSIRMEndRequest(OSIRMRequest osirmRequest, String outputFileName) throws Exception {
		logger.info("OSIRMManagerDAOImpl::updateOSIRMEndRequest::start");
		Connection connection=null;
		Statement statement=null;
		PreparedStatement preparedStatement=null;
		Properties properties=null;
		try{
			properties=ConnectionManager.readProperties();
			String query=properties.getProperty("UPDATE_REQUEST_END_INFO");
			connection = ConnectionManager.getRawConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, osirmRequest.getOutputFileDir()+outputFileName);
			preparedStatement.setInt(2, osirmRequest.getRequestId());
			preparedStatement.execute();
			connection.commit();
		}catch (Exception e) {
			logger.error("OSIRMManagerDAOImpl::updateOSIRMEndRequest:ERROR   ", e);
			throw e;
		}finally{
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				logger.error("OSIRMManagerDAOImpl::updateOSIRMEndRequest:ERROR   ", e2);
			}
		}
		logger.info("OSIRMManagerDAOImpl::updateOSIRMEndRequest::end");
	}
	
	public void updateOSIRMErrorEndRequest(OSIRMRequest osirmRequest) throws Exception {
		logger.info("OSIRMManagerDAOImpl::updateOSIRMErrorEndRequest::start");
		Connection connection=null;
		Statement statement=null;
		PreparedStatement preparedStatement=null;
		Properties properties=null;
		try{
			properties=ConnectionManager.readProperties();
			String query=properties.getProperty("UPDATE_REQUEST_ERROR_END_INFO");
			connection = ConnectionManager.getRawConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, osirmRequest.getRequestId());
			preparedStatement.execute();
			connection.commit();
		}catch (Exception e) {
			logger.error("OSIRMManagerDAOImpl::updateOSIRMErrorEndRequest:ERROR   ", e);
		}finally{
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				logger.error("OSIRMManagerDAOImpl::updateOSIRMErrorEndRequest:ERROR   ", e2);
			}
		}
		logger.info("OSIRMManagerDAOImpl::updateOSIRMErrorEndRequest::end");
	}
	
	public void UpdateCurrentRunningThreads(String hostName, int incCount) throws Exception {
		logger.info("OSIRMManagerDAOImpl::UpdateCurrentRunningThreads::start");
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		Properties properties=null;
		try{
			properties=ConnectionManager.readProperties();
			String query=properties.getProperty("UPDATE_NODE_THREAD_INFO");
			connection = ConnectionManager.getRawConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, incCount);
			preparedStatement.setString(2, hostName);
			preparedStatement.execute();
			connection.commit();
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("OSIRMManagerDAOImpl::UpdateCurrentRunningThreads:ERROR   ", e);
			throw e;
		}finally{
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				logger.error("OSIRMManagerDAOImpl::UpdateCurrentRunningThreads:ERROR   ", e2);
			}
		}
		logger.info("OSIRMManagerDAOImpl::UpdateCurrentRunningThreads::end");
	}
	
	public Integer checkParentId(Integer parentId) throws Exception{
		logger.info("OSIRMManagerDAOImpl::checkParentId::start");
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs = null;
		Integer count = 0;
		try{
			if(parentId!= null){
			String query="SELECT COUNT(PARENT_REQUEST_ID) FROM OSI_RM_REQUESTS WHERE PARENT_REQUEST_ID = ?";
			connection = ConnectionManager.getRawConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, parentId);
			rs = preparedStatement.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			}else{
				count = 1;
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("OSIRMManagerDAOImpl::checkParentId   ", e);
			throw e;
		}finally{
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				logger.error("OSIRMManagerDAOImpl::checkParentId   ", e2);
			}
		}
		logger.info("OSIRMManagerDAOImpl::checkParentId::end");
		return count;
	}
	
}
