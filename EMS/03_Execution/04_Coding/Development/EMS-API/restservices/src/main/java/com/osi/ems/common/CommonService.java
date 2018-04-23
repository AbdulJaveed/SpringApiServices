package com.osi.ems.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.EntityManager;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;

/**
 * @author smanchala
 *
 */
@Component
public class CommonService {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private AppConfig appConfig;
	public void uploadImages(MultipartFile file, String fileName){

	            try {
	       		 if (!file.isEmpty()) {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream = 
	                        new BufferedOutputStream(new FileOutputStream(new File(appConfig.getImagePath()+"\\"+fileName)));
	                stream.write(bytes);
	                stream.close();
	       		 }
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	}
	
	public void uploadAttachment(String fileContent, String fileNameWithUploadDirectory) throws BusinessException {
		BufferedOutputStream stream;
		try {
			byte[] data = Base64.decodeBase64(fileContent.getBytes(StandardCharsets.UTF_8));
			//OutputStream stream = new FileOutputStream(fileNameWithUploadDirectory);
			File file = new File(fileNameWithUploadDirectory);
			if(file.exists()){
				file.delete();
				file.createNewFile();
			}
			stream = new BufferedOutputStream(new FileOutputStream(file));			
			stream.write(data);
			stream.close();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new BusinessException("ERR_1000", e.getMessage());
		} /*catch(Throwable t){
			t.printStackTrace();
		}	*/	
		
	}
	
	//Deletes Files from File System for PR, PO & BPA
	public void deleteAttachment(String fileNameWithUploadDirectory) throws BusinessException {
		try {
			File file = new File(fileNameWithUploadDirectory);
			file.delete();
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
	}
	
	//Encode file to Base64
	@SuppressWarnings("resource")
	public String encodeFile(String filePath) throws BusinessException{
		String encodedString = "";
		try{
			File file = new File(filePath);
			InputStream is = new FileInputStream(file);

		    long length = file.length();
		    if (length > Integer.MAX_VALUE) {
		        // File is too large
		    }
		    byte[] bytes = new byte[(int)length];
		    
		    int offset = 0;
		    int numRead = 0;
		    while (offset < bytes.length
		           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
		        offset += numRead;
		    }

		    if (offset < bytes.length) {
		        throw new BusinessException("ERR_1000","");
		    }

		    is.close();
		    byte[] encoded = Base64.encodeBase64(bytes);
		    encodedString = new String(encoded, "UTF-8");
			
		}catch(Exception e){
			//e.printStackTrace();
			throw new BusinessException("ERR_1060", "");
		}
		
		return encodedString;
	}
	
	
	/**
	 * @param dateString
	 * @param employeeId
	 * converts the given @dateString to UTC format based on the employee location
	 * If dateString is NULL, returns the current UTC date in String
	 * @return @String of UTC Date format
	 * @throws BusinessException
	 */
	public String convertDateStringToUTC(String dateString, int employeeId) throws BusinessException {
	    
		String UTCString = null;
		String queryString = "select tz.hrs_offset_utc,tz.mins_offset_utc from osi_timezones tz where tz.tz_id = " 
				+ " (select l.tz_id from osi_locations l where l.location_id = "
				+ " (select a.location_id from osi_assignments a where a.employee_id = ? and " 
				+ " sysdate() between a.effective_start_date and a.effective_end_date))";
		if(null != dateString) {
			Object [] object = (Object[]) em.createNativeQuery(queryString)
									.setParameter(1, employeeId)
									.getSingleResult();
			if(null != object) {
				int hours = (int) object[0];
				int minutes = (int) object[1];
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date date = dateFormat.parse(dateString);
			        Calendar calendar = Calendar.getInstance();
			        calendar.setTimeInMillis(date.getTime());
			        calendar.add(Calendar.HOUR, -hours);
			        calendar.add(Calendar.MINUTE, -minutes);
			        calendar.add(Calendar.SECOND, date.getSeconds()+1);
			        Timestamp finalTimestamp = new Timestamp(calendar.getTime().getTime());
			        UTCString = dateFormat.format(finalTimestamp);
				} catch (ParseException e) {
					throw new BusinessException("ERR_1010", "Date Conversion not possible");
				}
				
			}
		} else {
			//throw new BusinessException("ERR_1010", "Input date is not valid");
			UTCString = this.getCurrentDateStringinUTC();
		}
		return UTCString;
	}
	
	/**
	 * @param dateString
	 * @param employeeId
	 * converts the given @dateString to UTC format based on the employee location
	 * If dateString is NULL, returns the current UTC date in String
	 * @return @String of UTC Date format
	 * @throws BusinessException
	 */
	public String convertUTCDateToLocaleString(String dateString, int employeeId) throws BusinessException {
	    
		String UTCString = null;
		String queryString = "select tz.hrs_offset_utc,tz.mins_offset_utc from osi_timezones tz where tz.tz_id = " 
				+ " (select l.tz_id from osi_locations l where l.location_id = "
				+ " (select a.location_id from osi_assignments a where a.employee_id = ? and " 
				+ " sysdate() between a.effective_start_date and a.effective_end_date))";
		if(null != dateString) {
			Object [] object = (Object[]) em.createNativeQuery(queryString)
									.setParameter(1, employeeId)
									.getSingleResult();
			if(null != object) {
				int hours = (int) object[0];
				int minutes = (int) object[1];
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date date = dateFormat.parse(dateString);
			        Calendar calendar = Calendar.getInstance();
			        calendar.setTimeInMillis(date.getTime());
			        calendar.add(Calendar.HOUR, hours);
			        calendar.add(Calendar.MINUTE, minutes);
			        Timestamp finalTimestamp = new Timestamp(calendar.getTime().getTime());
			        UTCString = dateFormat.format(finalTimestamp);
				} catch (ParseException e) {
					throw new BusinessException("ERR_1010", "Date Conversion not possible");
				}
				
			}
		} else {
			//throw new BusinessException("ERR_1010", "Input date is not valid");
			UTCString = this.getCurrentDateStringinUTC();
		}
		return UTCString;
	}
	
	/*
	 * get current TimeZone in UTC format
	 */
	public String getCurrentDateStringinUTC() {
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));	   	    
	    return dateFormatGmt.format(new Date());
	}
	
	public Date getCurrentDateinUTC() throws BusinessException {
		Date UTCdate = null;
		 try {
			 SimpleDateFormat dateFormatIst = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 dateFormatIst.setTimeZone(TimeZone.getTimeZone("UTC"));	   	    
			 SimpleDateFormat dateFormatUtc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 UTCdate = dateFormatUtc.parse(dateFormatIst.format(new Date()));
		} catch (ParseException e) {
			throw new BusinessException("ERR_1010", "Unparsable Date");
		}
		 return UTCdate;
	}
	

}
