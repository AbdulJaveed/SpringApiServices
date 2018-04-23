/**
 * ============================================================================
 * All rights reserved © 2011, OSI Consulting, Inc. This software is 
 * confidential and proprietary information. 
 * Package           : com.sm.bps.common.util
 * Filename          : JasperReportsUtil.java
 * Author            : OSI Consulting Pvt. Ltd.
 * Version           : 1.0
 * Purpose           : This class will be used as to generate the reports
 * Revision History
 * =============================================================================
 *  Date              Author               Changes
 * 03/03/2014	 Theresa	Created this class
 * =============================================================================
 */

package com.osi.rm.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.olap.xmla.JRXmlaQueryExecuterFactory;

import org.apache.log4j.Logger;

public class JasperReportsUtil {

	JasperReport jasperReport;
	private final static String encoding = "ISO-8859-1"; //
	public JasperReportsUtil() {
	}
	public static String generateOSIRMReport(Map<String, Object> map, Logger logger) throws Exception {
		logger.info("JasperReportsUtil:: generateOSIRMReport() : Start");
		Connection conn = null;
		StringBuffer file = new StringBuffer();
		PropertyInitialize initialize= new PropertyInitialize();
		initialize.initializeJasperProperties();
		try {
			//map.put("STATUS", "All");
			StringBuffer fileLocation = new StringBuffer( map.get("FILE_LOCATION").toString());
			logger.info("JasperReportsUtil::generateOSIRMReport()  :: Output file location is : "+fileLocation);
			String fileName = map.get("REQUEST_ID").toString();
			String outputFormat=(String) map.get("OUTPUT_TYPE");
			String templateLoc = map.get("TEMPLATE_LOC").toString();
			long startTime = System.currentTimeMillis();
			
			conn = ConnectionManager.getRawConnection();
			long endTime = System.currentTimeMillis();
			long difftime = endTime-startTime;
			int hours = (int) (difftime / 3600000);    
			int mins = (int) (difftime / (60000));
			int secs = (int) (difftime / (1000));
			logger.info("JasperReportsUtil::generateOSIRMReport()  :: time taken for db connection :: "+ hours+":"+mins+":"+secs);
			outputFormat = outputFormat.replace("'", "");
			startTime = System.currentTimeMillis();
			FileInputStream fis= new FileInputStream(templateLoc + (String) map.get("jrxmlName"));
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fis);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
			jasperPrint.setProperty(JRXmlaQueryExecuterFactory.QUERY_EXECUTER_FACTORY_PREFIX, "plsql");
			if(outputFormat!=null){
				if(outputFormat.equalsIgnoreCase("pdf")) {
					JasperExportManager.exportReportToPdfFile(jasperPrint, fileLocation.append(fileName).append(".").append(outputFormat).toString());
				} else if (outputFormat.equalsIgnoreCase("html")) {
					JasperExportManager.exportReportToHtmlFile(jasperPrint, fileLocation.append(fileName).append(".").append(outputFormat).toString());// ".html"
				}else if(outputFormat.equalsIgnoreCase("xls") || outputFormat.equalsIgnoreCase("excel")) {
					outputFormat = "xls";
					JRXlsExporter exporter = new JRXlsExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
					exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
					if(map.get("jrxmlName").toString().indexOf("usdmts_trading")!=-1||map.get("jrxmlName").toString().indexOf("us_dollor")!=-1||map.get("jrxmlName").toString().indexOf("third_currencies")!=-1){
						exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
					}
					exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileLocation.append(fileName).append(".").append(outputFormat).toString());
					exporter.exportReport();
					exporter=null;
				} else if (outputFormat.equalsIgnoreCase("csv")) {
					int i =0;
					JRCsvExporter exporter1 = new JRCsvExporter();
					exporter1.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, Integer.valueOf(3000));
					exporter1.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, Integer.valueOf(50));
					exporter1.setParameter(JRTextExporterParameter.CHARACTER_ENCODING, encoding);
					exporter1.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
					exporter1.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,fileLocation.append(fileName).append(".").append(outputFormat).toString());	//+ ".csv"
					if(map.containsKey("DELIMITER")){
						exporter1.setParameter(JRCsvExporterParameter.FIELD_DELIMITER,map.get("DELIMITER"));
					} else {
						exporter1.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, "|");
					}
					if(map.containsKey("REC_DELIMITER")){
						String recDelimiter = map.get("REC_DELIMITER").toString()+"\r\n";
						exporter1.setParameter(JRCsvExporterParameter.RECORD_DELIMITER, recDelimiter);
					} else {
						 exporter1.setParameter(JRCsvExporterParameter.RECORD_DELIMITER,"\r\n");
					}
					exporter1.exportReport();
				} else {
					throw new Exception("Invalid Output Format");
				}
			}
			
			
			file.append(fileName).append(".").append(outputFormat);
			fis.close();
		
			
			logger.info("JasperReportsUtil::generateOSIRMReport()  :: Output file is : "+file);
		} catch (JRException e) {
			logger.error("JasperReportsUtil::generateOSIRMReport() :: ERROR " , e);
			throw e;
		} catch (Exception e) {
			logger.error("JasperReportsUtil::generateOSIRMReport() :: ERROR ", e);
			throw e;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("JasperReportsUtil::generateOSIRMReport() :: ERROR ",e);
					throw e;
				}
			}
		}
		logger.info("JasperReportsUtil::generateOSIRMReport() : End");
		return file.toString();
	}

}
