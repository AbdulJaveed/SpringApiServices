package com.osi.rm;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.osi.rm.common.ConnectionManager;

public class TestDBConnection {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection connection2=ConnectionManager.getRawConnection();
		System.out.println(connection2);
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("USER_ID", new Integer(1));
		map.put("USER_IDS", new Integer(1));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(map);
        oos.flush();
        oos.close();
        byte[] data = bos.toByteArray();
        System.out.println(data);
        String query = "UPDATE OSI_RM_REQUESTS SET REQUEST_ARGUMENTS=?";
        PreparedStatement statement = connection2.prepareStatement(query);
        statement.setBytes(1,data); 
        int rs=statement.executeUpdate();
        statement = null;
        String sqlQuery = "SELECT TOP 1 REQUEST_ID, REPORT_ID, REQUEST_ARGUMENTS, REQUEST_ARGUMENTS_TYPE, REQUESTED_BY, OUTPUT_TYPE, REQUEST_DATE, IS_REPEAT, REPEAT_INTERVAL, SCHEDULE_FREQUENCY_ID, REQEST_SCHE_END_DATE FROM OSI_RM_REQUESTS WHERE HOST_NAME IS NULL AND REQUEST_PROCESS='P' AND REQUEST_STATUS='N' AND REQUEST_DATE<=CURRENT_TIMESTAMP";
        statement = connection2.prepareStatement(sqlQuery);
        ResultSet rs1=statement.executeQuery();
        connection2.commit();
        while(rs1.next()){
        	System.out.println(rs1.getInt("REQUEST_ID"));
        	System.out.println(rs1.getInt("REPORT_ID"));
        	System.out.println(rs1.getBytes("REQUEST_ARGUMENTS"));
        	System.out.println(rs1.getString("REQUEST_ARGUMENTS_TYPE"));
        	System.out.println(rs1.getInt("REQUESTED_BY"));
        	System.out.println(rs1.getString("OUTPUT_TYPE"));
        }
        if(connection2!=null){
        	connection2.close();
        }
	}

}
