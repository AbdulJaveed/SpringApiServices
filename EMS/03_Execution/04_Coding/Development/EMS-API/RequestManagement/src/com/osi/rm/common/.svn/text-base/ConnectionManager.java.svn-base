package com.osi.rm.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;


public class ConnectionManager {
	private static  Logger logger = Logger.getLogger(ConnectionManager.class);
	
	public static Connection getRawConnection() throws Exception{
		logger.info("ConnectionManager::getRawConnection::start");
		Connection connection = null;
		Properties properties=null;
		try{
			properties=readProperties();
			Class.forName(properties.getProperty("DRIVER_NAME"));
			DriverManager.setLoginTimeout(15);
		    connection = DriverManager.getConnection(properties.getProperty("DB_URL"),properties.getProperty("DB_USER_NAME"),properties.getProperty("DB_PASSWORD"));
		    connection.setAutoCommit(false);
		} catch(Exception e){
			logger.error("OSIRMManager::main:ERROR   ", e);
		}
		logger.info("ConnectionManager::getRawConnection::end");
		return connection;
	}
	
	public static Properties readProperties() throws Exception{
		logger.info("ConnectionManager::readProperties::start");
		Properties properties=new Properties();
		//properties.load(new FileInputStream("connection.properties"));
		
        try {  
            String path = System.getProperty("user.dir");
            
            logger.info("path:" + path);  //info
    /* load the properties file onto Properties object. The properties file should be in the 
     * same location where OSIRMDriver.jar is placed   
     */
            properties.load(new FileInputStream(path+"/OSI_RM_connection.properties"));  

//            properties.load(ConnectionManager.class.getClassLoader().getResourceAsStream(path+"\\OSI_RM_connection.properties"));            
            // access ur properties  
//            String url = (String) properties.get("DB_URL");
//            System.out.println("url:" + url);
//            String username = (String) properties.get("DB_USER_NAME");
//            System.out.println("username:" + username);
//            String dbDriver = (String) properties.get("DRIVER_NAME");
//            System.out.println("dbDriver:" + dbDriver);
//            String dbPassword = (String) properties.get("DB_Password");
//            System.out.println("dbPassword:" + dbPassword);
        } catch (IOException e) {  
			logger.error("OSIRMManager::readProperties:ERROR   ", e);
        }  		
        logger.info("ConnectionManager::readProperties::end");
		return properties;
	}
}
