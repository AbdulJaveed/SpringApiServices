package com.osi.rm.common;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.osi.rm.OSIRMManager;



public class PropertyInitialize {
	private static  Logger logger = Logger.getLogger(PropertyInitialize.class);
	//private static final ResourceBundle jasperProperties =  ResourceBundle.getBundle("com/osi/rm/common/config/jasper.properties");
	public static String digesterJar = null;
	public static String tempFolder = null;

	public PropertyInitialize(){
		
		Properties properties=null;
		try {
			properties = ConnectionManager.readProperties();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("PropertyInitialize::ERROR" ,e);
		}
		String os =properties.getProperty("OS");
		if (os.equalsIgnoreCase("windows")) {
			digesterJar = properties.getProperty("WIN_JASPER_CLASSPATH");
			tempFolder = properties.getProperty("WIN_TEMP_FOLDER");
		} else {
			digesterJar = properties.getProperty("LINUX_JASPER_CLASSPATH");
			tempFolder = properties.getProperty("LINUX_TEMP_FOLDER");
		}
	}
	public void initializeJasperProperties(){
		logger.info("PropertyInitialize::initializeJasperProperties::start");
		System.setProperty("jasper.reports.compile.class.path", digesterJar);
		System.setProperty("jasper.reports.compile.temp", tempFolder);
		System.setProperty("jasper.reports.compile.keep.java.file", "true");
		logger.info("PropertyInitialize::initializeJasperProperties::end");
	}
}
