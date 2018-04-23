/**
 * 
 */
package com.osi.rm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.osi.rm.common.ConnectionManager;
import com.osi.rm.thread.OSIRMManagerThread;
import com.osi.rm.vo.OSIRMConfig;


/**
 * @author OSI
 *
 */
public class OSIRMManager {

	/**
	 * @param args
	 */
	private static  Logger logger = Logger.getLogger(OSIRMManager.class);
	public static void main(String[] args) {
		logger.info("OSIRMManager::main::start");
		// TODO Auto-generated method stub
		try {
			
			/*Connection connection2=ConnectionManager.getRawConnection();
			System.out.println(connection2);*/
			String capacity=System.getProperty("capacity");
			String pId=System.getProperty("pid");
			String hostName=System.getProperty("hostname");
/* The below line should be commented when deploying this jar to server*/
			
			/*capacity="10";pId="2465";hostName="localhost";*/
			if(capacity==null || pId==null || hostName==null){
				
				logger.info("Standard usage is  exec java -Dpid=$$ -DhostName='' -Dcapacity='' -jar /OSIRMManager.jar");
				System.exit(1);
			}
			OSIRMManager obj=new OSIRMManager();
			Connection connection=ConnectionManager.getRawConnection();
			logger.info("TestMain.main() : Connection Object :"+connection);

/* The below if loop should be uncommented when deploying this jar to server*/			
			
			if(obj.checkAnyOtherActive(hostName)) {
				System.exit(1);
			}
			
			obj.deleteAndInsertHostEntry(hostName, Integer.parseInt(pId), Integer.parseInt(capacity));
			OSIRMManagerThread managerThread=null;
			OSIRMConfig osiRMConfig = getRMConfig();
			for(int i=0;i<Integer.parseInt(capacity);i++){
				//Thread.sleep(15000);
				managerThread=new OSIRMManagerThread(hostName, Integer.parseInt(pId), getCurrentTimestamp(), osiRMConfig);
				managerThread.start();
			}
			
		} catch (Exception e) {
			logger.error("OSIRMManager::main:ERROR  ", e);
		}
		logger.info("OSIRMManager::main::end");
	}
	
	public static OSIRMConfig getRMConfig() throws Exception{
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
	}
	
	public static Timestamp getCurrentTimestamp() {
		logger.info("OSIRMManager::getCurrentTimestamp::start");
		java.util.Date date = null;
		java.sql.Timestamp timeStamp = null;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			java.sql.Date dt = new java.sql.Date(calendar.getTimeInMillis());
			java.sql.Time sqlTime = new java.sql.Time(calendar.getTime()
					.getTime());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = simpleDateFormat.parse(dt.toString() + " "+ sqlTime.toString());
			timeStamp = new java.sql.Timestamp(date.getTime());
		} catch (Exception e) {
			logger.error("OSIRMManager::getCurrentTimestamp:	ERROR   ", e);
		}
		logger.info("OSIRMManager::getCurrentTimestamp::end");
		return timeStamp;
	}
	
	public boolean isActive(int pId) throws Exception{
		  try {
			  logger.info("OSIRMManager::isActive::start");
			  logger.info("Inside isActive Method");
		      String line;
		      Process p = Runtime.getRuntime().exec("ps -ef");
		      BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		      int loopCount=0;
		      while ((line = input.readLine()) != null) {
		          //System.out.println(line); //<-- Parse data here.
		          if ( line.contains("-Dpid="+pId) ){
		          	//System.out.println("Came inside the if");
		          	loopCount++;
		           	break;
		          }
		      }
		      input.close();
		      if(loopCount>0){
		      	return true;
		      }
		    } catch (Exception err) {
		        logger.error("OSIRMManager::isActive:	ERROR   ", err);
		        return true;
		    }
		  logger.info("OSIRMManager::isActive::end");
		  return false;
	}
	
	public boolean checkAnyOtherActive(String hostName)throws Exception {
		logger.info("OSIRMManager::checkAnyOtherActive::start");
		Connection connection=null;
		//Statement statement=null;
		PreparedStatement preparedStatement=null,preparedStatement1=null;
		ResultSet resultSet=null;
		int pId=0;
		boolean flag=false;
		Properties properties=null;
		try{
			properties=ConnectionManager.readProperties();
			String query=properties.getProperty("GET_PROCESS_ID");
			connection = ConnectionManager.getRawConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, hostName);
			preparedStatement.execute();
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				pId=resultSet.getInt("PROCESS_Id");
			}
			flag=isActive(pId);
			if (flag) {
				String updateFailedInfo=properties.getProperty("UPDATE_FAILED_REQUEST_INFO");
				preparedStatement1=connection.prepareStatement(updateFailedInfo);
				preparedStatement1.setInt(1, pId);
				preparedStatement1.executeUpdate();
				connection.commit();
			}
		}catch (Exception e) {
		        logger.error("OSIRMManager::checkAnyOtherActive:	ERROR   ", e);
			flag=true;
		}finally{
			try {
				if(resultSet!=null) resultSet.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(preparedStatement1!=null) preparedStatement1.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
			        logger.error("OSIRMManager::checkAnyOtherActive:	ERROR   ", e2);
			}
		}
		logger.info("OSIRMManager::checkAnyOtherActive::end");
		return flag;
	}

	public void deleteAndInsertHostEntry(String hostName, int pId, int capacity) throws Exception{
		logger.info("OSIRMManager::deleteAndInsertHostEntry::start");
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		PreparedStatement preparedStatement1=null;
		Properties properties=null;
		try{
			properties=ConnectionManager.readProperties();
			String query=properties.getProperty("DELETE_NODE_INFO");
			connection = ConnectionManager.getRawConnection();
			preparedStatement1=connection.prepareStatement(query);
			preparedStatement1.setString(1, hostName);
			preparedStatement1.execute();
			if(preparedStatement1!=null) 
				preparedStatement1.close();
			query=properties.getProperty("INSERT_HOST_ENTRY");
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, hostName);
			preparedStatement.setInt(2, pId);
			preparedStatement.setInt(3, capacity);
			preparedStatement.execute();
			connection.commit();
		}catch (Exception e) {
			logger.error("OSIRMManager::deleteAndInsertHostEntry:	ERROR   ", e);
			throw e;
		}finally{
			try {
				if(preparedStatement!=null) 
					preparedStatement.close();
				if(preparedStatement1!=null) 
					preparedStatement1.close();
				if(connection!=null) 
					connection.close();
			} catch (Exception e2) {
				logger.error("OSIRMManager::deleteAndInsertHostEntry:	ERROR   ", e2);
			}
		}
		logger.info("OSIRMManager::deleteAndInsertHostEntry::end");
	}
	
}
