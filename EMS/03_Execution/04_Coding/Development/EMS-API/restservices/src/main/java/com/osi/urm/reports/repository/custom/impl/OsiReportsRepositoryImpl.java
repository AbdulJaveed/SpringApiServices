package com.osi.urm.reports.repository.custom.impl;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Clob;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.osi.urm.domain.OsiReports;
import com.osi.urm.domain.OsiReportsGeneration;
import com.osi.urm.domain.OsiRmRequests;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.reports.repository.custom.OsiReportsRepositoryCustom;
import com.osi.urm.service.dto.OsiReportsGenerationDTO;

public class OsiReportsRepositoryImpl implements
		OsiReportsRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiReports> getReportListByRptGrp(Integer businessGroupId, Integer rptGrpId)
			throws DataAccessException {
		List<OsiReports> osiReportsList = null;
		try {
			String query = "SELECT r FROM OsiReports r, OsiRptGrpRpts rgr WHERE r.reportId = rgr.osiReports.reportId AND r.businessGroupId = :businessGroupId AND rgr.osiRptGroups.rptGrpId=:rptGrpId ORDER BY r.userReportName asc";
			osiReportsList = (List<OsiReports>) this.entityManager.createQuery(query)
					.setParameter("rptGrpId", rptGrpId)
					.setParameter("businessGroupId", businessGroupId).getResultList();
		} catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return osiReportsList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiReports> getAllReports(Integer businessGroupId)
			throws DataAccessException {
		List<OsiReports> osiReportsList = null;
		try {
			String query = "SELECT r FROM OsiReports r WHERE r.businessGroupId = :businessGroupId ORDER BY r.userReportName asc";
			osiReportsList = (List<OsiReports>) this.entityManager.createQuery(query)
					.setParameter("businessGroupId", businessGroupId).getResultList();
		} catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return osiReportsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<OsiReportsGeneration> getReportFields(int reportId, Integer businessGroupId) throws DataAccessException{
		List<Object[]> objectList = null;
		List<OsiReportsGeneration> osiReportsGenerationList = null;
		try{
		String query = "select ra.displaySeq, ra.parameterName, ra.paramRequired, ra.multiSelect, ra.listId, l.listDataType, l.validationType, l.listDataMaxSize, ra.screenDisplayName, l.dateFormat  from OsiReports r, OsiReportArgs ra, OsiLists l where r.reportId = ra.reportId and l.listId = ra.listId and ra.reportId =:reportId and r.businessGroupId =:businessGroupId order by ra.displaySeq";
		objectList = (List<Object[]>) this.entityManager.createQuery(query)
				.setParameter("reportId", reportId)
				.setParameter("businessGroupId", businessGroupId)
				.getResultList();
		osiReportsGenerationList = new ArrayList<OsiReportsGeneration>();
			for(Object[] d:objectList){
				OsiReportsGeneration osiReportsGeneration = new OsiReportsGeneration();
				osiReportsGeneration.setDisplaySeq((Integer)d[0]);
				osiReportsGeneration.setParameterName(d[1].toString());
				osiReportsGeneration.setParamRequired(d[2].toString());
				osiReportsGeneration.setMultSelect(d[3].toString());
				osiReportsGeneration.setListId((Integer)d[4]);
				osiReportsGeneration.setListDataType(d[5].toString());
				osiReportsGeneration.setValidationType(d[6].toString());
				osiReportsGeneration.setListDataMaxSize(d[7].toString());
				osiReportsGeneration.setScreenDisplayName(d[8].toString());
				if(d[9]!=null)
					osiReportsGeneration.setDateFormat(d[9].toString());
				osiReportsGenerationList.add(osiReportsGeneration);
			}	
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return osiReportsGenerationList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getSQLStatement(int listId, Map<String, String> selectedValues, Map<String, String> osi_envlist)
			throws DataAccessException {
		List<Object[]> objectList = null;
		String sqlStatement = "";
		try{
			String hql = "select listSql, listSql from OsiListSql where listId =:selectedListId";
			objectList = (List<Object[]>)this.entityManager.createQuery(hql)
			.setParameter("selectedListId", listId)
			.getResultList();
			for(Object[] d:objectList){
				sqlStatement = d[0].toString();
			}
			if(selectedValues!=null) {
				Set<String> keys = selectedValues.keySet();
				for (Iterator iterator = keys.iterator(); iterator
						.hasNext();) {
					String parameter = (String) iterator.next();
					String checkstring = "$OSI$:"+parameter;
					if (!sqlStatement.contains(checkstring) && !sqlStatement.contains("$OSI_ENV$")){
						
					} else if(sqlStatement.contains(checkstring) || sqlStatement.contains("$OSI_ENV$")) {
						if(sqlStatement.contains(checkstring)) {
							sqlStatement = sqlStatement.replace("$OSI$:"+parameter, selectedValues.get(parameter));
						}
						if(sqlStatement.contains("$OSI_ENV$")) {
							if (null != osi_envlist && osi_envlist.size() >= 0) {
								for (Entry<String, String> entry : osi_envlist.entrySet()) {
									String key = entry.getKey();
									String value = entry.getValue();
									if (sqlStatement.contains("$OSI_ENV$:"+key))
										sqlStatement = sqlStatement.replace("$OSI_ENV$:"+key, value);
								}
							}
						}
					} else {
						sqlStatement = null;
					}
				}
			} 
			if (null == selectedValues && sqlStatement.contains("$OSI_ENV$")){
				if (null != osi_envlist && osi_envlist.size() >= 0) {
					for (Entry<String, String> entry : osi_envlist.entrySet()) {
						String key = entry.getKey();
						String value = entry.getValue();
						if (sqlStatement.contains("$OSI_ENV$:"+key))
							sqlStatement = sqlStatement.replace("$OSI_ENV$:"+key, value);
					}
				}
			} else if (null == selectedValues && sqlStatement.contains("$OSI$:")){
				sqlStatement = null;
			}
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return sqlStatement;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getLOVList(int listId, Map<String, String> selectedValues) throws DataAccessException {
		String dropDownString = "";
		List<Object[]> objectList = null;
		try{
			String hql = "select listValue, listValue from OsiListValues where listId = :selectedListId";
			objectList = (List<Object[]>)this.entityManager.createQuery(hql)
					.setParameter("selectedListId", listId)
					.getResultList();
			for(Object[] d:objectList){
				dropDownString = dropDownString +"<option value='"+d[0].toString()+"'>"+d[1].toString()+"</option>";
			}
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return dropDownString;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiReportsGeneration> getChildFields(String parameter, int reportId) throws DataAccessException {
		List<Object[]> objectList = null;
		List<OsiReportsGeneration> osiReportsGenerationList = null;
		try{
			String hql = "select ra.displaySeq, ra.parameterName, ra.paramRequired, ra.multiSelect, ra.listId, l.listDataType, l.validationType, l.listDataMaxSize  from OsiReports r, OsiReportArgs ra, OsiLists l where r.reportId = ra.reportId and l.listId = ra.listId and ra.parameterName =:prmName and r.reportId =:reportId order by ra.displaySeq";
			objectList = (List<Object[]>) this.entityManager.createQuery(hql)
					.setParameter("reportId", reportId)
					.setParameter("prmName", parameter)
					.getResultList();
			osiReportsGenerationList = new ArrayList<OsiReportsGeneration>();
				for(Object[] d:objectList){
					OsiReportsGeneration osiReportsGeneration = new OsiReportsGeneration();
					osiReportsGeneration.setDisplaySeq((Integer)d[0]);
					osiReportsGeneration.setParameterName(d[1].toString());
					osiReportsGeneration.setParamRequired(d[2].toString());
					osiReportsGeneration.setMultSelect(d[3].toString());
					osiReportsGeneration.setListId((Integer)d[4]);
					osiReportsGeneration.setListDataType(d[5].toString());
					osiReportsGeneration.setValidationType(d[6].toString());
					osiReportsGeneration.setListDataMaxSize(d[7].toString());
					osiReportsGenerationList.add(osiReportsGeneration);
				}	
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return osiReportsGenerationList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String executeSQLStatement(String sqlStatement) throws DataAccessException {
		String dropDownString = "";
		List<Object[]> objectList = null;
		try{
			if(sqlStatement!=null && !sqlStatement.contains("$OSI$:")){
				StringBuffer hql = new StringBuffer(sqlStatement);
				objectList = (List<Object[]>)this.entityManager.createNativeQuery(hql.toString())
						.getResultList();
				for(Object[] d:objectList){
					String clobStr =  "";
					if(d[1] instanceof Clob){
						Clob clob = (Clob)d[1];
						clobStr = clob.getSubString(1, (int) clob.length());
					}else
						clobStr = d[1].toString();
					dropDownString = dropDownString +"<option value='"+d[0].toString()+"'>"+clobStr+"</option>";
				}
			}
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return dropDownString;
	}

	@Override
	public String saveReportRequest(int userId, OsiReportsGenerationDTO osiReportsGenerationVO, int reportId,
			Map<String, Object> map) throws DataAccessException {
		String requestedId = "";
		try {
			OsiRmRequests osiRmRequests = new OsiRmRequests();
			if(osiReportsGenerationVO.getOccurrences()!=null && !osiReportsGenerationVO.getOccurrences().equalsIgnoreCase("-1"))
				osiRmRequests.setScheduleFrequencyId(Integer.parseInt(osiReportsGenerationVO.getOccurrences()));
			if(osiReportsGenerationVO.getRequestDate()!=null && !osiReportsGenerationVO.getRequestDate().equals(""))
				osiRmRequests.setRequestDate(getTimestampfromString(osiReportsGenerationVO.getRequestDate()+" "+osiReportsGenerationVO.getRequestTimeHr()+":"+osiReportsGenerationVO.getRequestTimeMins()+":00"));
			if(osiReportsGenerationVO.getRepeatInterval()!=0)
				osiRmRequests.setRepeatInterval(osiReportsGenerationVO.getRepeatInterval());
			if(osiReportsGenerationVO.getScheduleEndDate()!=null && !osiReportsGenerationVO.getScheduleEndDate().equals(""))
				osiRmRequests.setReqestScheEndDate(getTimestampfromString(osiReportsGenerationVO.getScheduleEndDate()+" 23:59:59"));
			if(osiReportsGenerationVO.isRepeat())
				osiRmRequests.setIsRepeat(1);
			else
				osiRmRequests.setIsRepeat(0);
			osiRmRequests.setCreationDate(new Date());
			osiRmRequests.setBusinessGroupId(osiReportsGenerationVO.getBusinessGroupId());
			osiRmRequests.setReportId(reportId);
			osiRmRequests.setRequestProcess("P");
			osiRmRequests.setRequestStatus("N");
			osiRmRequests.setRequestedBy(userId);
			osiRmRequests.setBusinessGroupId(osiReportsGenerationVO.getBusinessGroupId());
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream(bos);
	        oos.writeObject(map);
	        oos.flush();
	        oos.close();
	        byte[] data = bos.toByteArray();
	        bos.close();
			osiRmRequests.setRequestArguments(data);
			osiRmRequests.setRequestArgumentsType("OBJECT");
			osiRmRequests.setOutputType(getReportOutputType(reportId));
			osiRmRequests = this.entityManager.merge(osiRmRequests);
			requestedId = osiRmRequests.getRequestId()+"";
		}catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return requestedId;
	}

	public List<OsiRmRequests> getRequestDetails(Integer userId, String adminRole, OsiRmRequests osiRmRequests) throws DataAccessException {
		List<OsiRmRequests> osiRmRequestss = null;
		try {
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    osiRmRequestss = new ArrayList<OsiRmRequests>();
			StringBuffer sbquery = new StringBuffer("SELECT orr.requestId, orv.userReportName, orr.logFileName, orr.outputFileName, '' as userName, orr.actualExecStartTime, orr.actualExecEndTime, ");
			sbquery.append(" orr.requestDate, orr.requestProcess, orr.requestStatus, orr.parentRequestId, orr.requestedBy, (SELECT CASE WHEN orms.reqestScheEndDate > :currentDate then 'S' END ");
			sbquery.append(" FROM OsiRmRequests orms where orms.requestId = orr.requestId and requestProcess!='C') as scheduleStatus, orr.reqestScheEndDate FROM OsiRmRequests orr, OsiReports orv, OsiRptGrpRpts rg WHERE 1=1 and orr.reportId = orv.reportId and orv.reportId = rg.osiReports.reportId and rg.osiRptGroups.rptGrpId="+osiRmRequests.getRptGroupId()+" ");
/*					+ "SELECT ORR.REQUEST_ID, ORV.USER_REPORT_NAME, ORR.LOG_FILE_NAME, ORR.OUTPUT_FILE_NAME, '' USER_NAME, ORR.ACTUAL_EXEC_START_TIME, ORR.ACTUAL_EXEC_END_TIME");
			sbquery.append(" , TO_CHAR(ORR.REQUEST_DATE, 'DD-MM-YYYY HH24:MI:SS') REQUEST_DATE, ORR.REQUEST_PROCESS, ORR.REQUEST_STATUS, ORR.PARENT_REQUEST_ID, ORR.REQUESTED_BY, (select case when reqest_sche_end_date > current_timestamp then 'S' end ");
			sbquery.append(" from osi_rm_requests orms where  request_id = ORR.REQUEST_ID and  request_process!='C') scheduleStaus FROM OSI_RM_REQUESTS ORR, OSI_REPORTS ORV");
			sbquery.append(" where 1=1 AND ORR.REPORT_ID = ORV.REPORT_ID ");
*/			if(osiRmRequests.getReportId()!=null && osiRmRequests.getReportId()!=-1){
				sbquery.append(" AND orr.reportId = "+osiRmRequests.getReportId());
			}
			if(osiRmRequests.getRequestId()!=null){
				sbquery.append(" AND orr.requestId = "+osiRmRequests.getRequestId());
			}
			if(!adminRole.equalsIgnoreCase(getLoggedInUserRoleName(userId).getUserName()))
				sbquery.append(" AND orr.requestedBy = "+userId);
			if(osiRmRequests.getRequestProcess()!=null && !osiRmRequests.getRequestProcess().equalsIgnoreCase("-1")){
				sbquery.append(" AND orr.requestProcess = '"+osiRmRequests.getRequestProcess()+"'"); 
			}
			if (osiRmRequests.getStartDate()!=null && osiRmRequests.getEndDate()!=null) {
				sbquery.append(" AND orr.requestDate >= '"+osiRmRequests.getStartDate()+"' AND orr.requestDate <= '"+osiRmRequests.getEndDate()+" 23:59:59' ");
			}else if(osiRmRequests.getStartDate()!=null && osiRmRequests.getEndDate()==null){
				sbquery.append(" AND orr.requestDate >= '"+osiRmRequests.getStartDate()+"' ");
			}else if(osiRmRequests.getStartDate()==null && osiRmRequests.getEndDate()!=null){
				sbquery.append(" AND orr.requestDate <= '"+osiRmRequests.getEndDate()+" 23:59:59' ");
			}
			
			sbquery.append(" ORDER BY orr.requestId DESC");
			
			@SuppressWarnings("unchecked")
			List<Object[]> requestObjects = (List<Object[]>)this.entityManager.createQuery(sbquery.toString())
			.setParameter("currentDate", new Date()).getResultList();
			String reportName = "";
			for(int i=0;i< requestObjects.size();i++) {
				osiRmRequests = new OsiRmRequests();
				osiRmRequests.setRequestId(Integer.parseInt(requestObjects.get(i)[0].toString()));
				if(requestObjects.get(i)[1].toString().length()>30)
					reportName = requestObjects.get(i)[1].toString().substring(0,30);
				else
					reportName = requestObjects.get(i)[1].toString();
				osiRmRequests.setReportName(reportName);
				if (requestObjects.get(i)[2] != null)
					osiRmRequests.setLogFileName(requestObjects.get(i)[2].toString());
				if (requestObjects.get(i)[3] != null) {
					osiRmRequests.setOutputFileName(requestObjects.get(i)[3].toString());
				}
				OsiUser osiUser = null;
				if(requestObjects.get(i)[11]!=null){
					osiUser = getLoggedInUserRoleName(Integer.parseInt(requestObjects.get(i)[11].toString()));
					osiRmRequests.setUserName(osiUser.getFirstName()+" "+osiUser.getLastName());
				}
				if (requestObjects.get(i)[5] != null) {
					osiRmRequests.setStartDate(format.format(format.parse(requestObjects.get(i)[5].toString())));
				}
				if (requestObjects.get(i)[6] != null) {
					osiRmRequests.setEndDate(format.format(format.parse(requestObjects.get(i)[6].toString())));
				}
				if (requestObjects.get(i)[5] != null && requestObjects.get(i)[6] != null) {
					long sdatetime = getTimestampfromsdate(requestObjects.get(i)[5].toString()).getTime();
					long edatetime = getTimestampfromsdate(requestObjects.get(i)[6].toString()).getTime();
					long difftime = edatetime-sdatetime;
					int hours = (int) (difftime / 3600000);    
					int mins = (int) (difftime / (60000));
					int secs = (int) (difftime / (1000));
					osiRmRequests.setDuration(hours+":"+mins+":"+secs);
				}
				if (requestObjects.get(i)[7] != null) {
					osiRmRequests.setRequestSubmitionDate(format.format(format.parse(requestObjects.get(i)[7].toString())));
				}
				if (requestObjects.get(i)[8] != null && requestObjects.get(i)[9]!=null) {
					String requestProcesss = requestObjects.get(i)[8].toString();
					String requestStatus = requestObjects.get(i)[9].toString();
					if(requestStatus.equalsIgnoreCase("U"))
						requestProcesss = "Cancelled";
					else if(requestProcesss.equalsIgnoreCase("P"))
						requestProcesss = "Pending";
					else if(requestProcesss.equalsIgnoreCase("R"))
						requestProcesss = "Running";
					else if(requestProcesss.equalsIgnoreCase("C"))
						requestProcesss = "Completed";
				   if(!requestProcesss.equals("C") && requestObjects.get(i)[12]!=null && requestObjects.get(i)[12].toString().equalsIgnoreCase("S")){
						requestStatus = "S";
						osiRmRequests.setScheduleStaus("S");
				   }
				   if(requestStatus.equalsIgnoreCase("E"))
						requestStatus = "Error";
					else if(requestStatus.equalsIgnoreCase("N"))
						requestStatus = "Normal";
					else if(requestStatus.equalsIgnoreCase("W"))
						requestStatus = "Warning";	
					else if(requestStatus.equalsIgnoreCase("S"))
						requestStatus = "Scheduled";
					else if(requestStatus.equalsIgnoreCase("C"))
						requestStatus = "Cancelled";	
					osiRmRequests.setRequestStatus(requestProcesss+"/"+requestStatus);
				}
				if(requestObjects.get(i)[10]!=null)
					osiRmRequests.setParentRequestId(Integer.parseInt(requestObjects.get(i)[10].toString()));
				if(requestObjects.get(i)[13]!=null)
					osiRmRequests.setRequestSchedulEndDate(format.format(format.parse(requestObjects.get(i)[13].toString())));
				osiRmRequestss.add(osiRmRequests);
			}
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return osiRmRequestss;
		
	}
	@Override
	public OsiRmRequests getMoreDetailsForRequest(int requestId) throws DataAccessException {
		OsiRmRequests osiRmRequests = null;
		try{
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			StringBuffer hql = new StringBuffer("SELECT RM.REPORT_ID, RM.IS_REPEAT, RM.REPEAT_INTERVAL, SF.SCHEDULE_FREQUENCY_NAME, RM.HOST_NAME, RM.PROCESS_ID, RM.REQEST_SCHE_END_DATE, RM.OUTPUT_TYPE");
							hql.append(" FROM OSI_RM_REQUESTS RM LEFT JOIN OSI_RM_SCHEDULE_FREQUENCYS SF ON RM.SCHEDULE_FREQUENCY_ID = SF.SCHEDULE_FREQUENCY_ID WHERE RM.REQUEST_ID=:requestId");
			@SuppressWarnings("unchecked")
			List<Object[]> ls = (List<Object[]>)this.entityManager.createNativeQuery(hql.toString())
					.setParameter("requestId", requestId).getResultList();				
			osiRmRequests = new OsiRmRequests();
			if(ls.get(0)[0]!=null)
				osiRmRequests.setReportId(Integer.parseInt(ls.get(0)[0].toString()));
			if(ls.get(0)[1]!=null && ls.get(0)[1].toString().equals("1"))
					osiRmRequests.setIsRepeatFlag("Repeatable");
			else if(ls.get(0)[1]!=null && ls.get(0)[1].toString().equals("0"))
				osiRmRequests.setIsRepeatFlag("Not Repeatable");
			if(ls.get(0)[2]!=null)
				osiRmRequests.setRepeatInterval(Integer.parseInt(ls.get(0)[2].toString()));
			if(ls.get(0)[3]!=null)
				osiRmRequests.setScheduleFrequency(ls.get(0)[3].toString());
			if(ls.get(0)[4]!=null)
				osiRmRequests.setHostName(ls.get(0)[4].toString());
			if(ls.get(0)[5]!=null)
				osiRmRequests.setProcessId(Integer.parseInt(ls.get(0)[5].toString()));
			if(ls.get(0)[6]!=null)
				osiRmRequests.setRequestSchedulEndDate(format.format(format.parse(ls.get(0)[6].toString())));
			if(ls.get(0)[7]!=null)
				osiRmRequests.setOutputType(ls.get(0)[7].toString());
			
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return osiRmRequests;
	}
	@Override
	public boolean waitForRequest(int requestId) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String saveReportRequest(int userId, int reportId, Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OsiRmRequests getOutputLogFileLocation(int requestId) throws DataAccessException {
		OsiRmRequests osiRmRequests = null;
		try{
			String hql = " from OsiRmRequests where requestId=:requestId";
			osiRmRequests = (OsiRmRequests)this.entityManager.createQuery(hql.toString())
					.setParameter("requestId", requestId)
					.getSingleResult();
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return osiRmRequests;
	}

	@Override
	public int getReportId(String reportName) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	public OsiUser getLoggedInUserRoleName(int userId) throws Exception{
		OsiUser osiUser = null;
		try{
			String sqlQuery = "select office_email, employee_number FROM osi_employees where employee_id=:userId and current_timestamp between effective_start_date and effective_end_date";
			Object[] object = (Object[])this.entityManager.createNativeQuery(sqlQuery)
			 .setParameter("userId", userId)
			 .getSingleResult();
			osiUser = new OsiUser();
			osiUser.setUserName(object[0].toString());
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return osiUser;
	}
	@Override
	public void unscheduleRequest(int userId, int requestId) throws DataAccessException {
		try {
			String query = "UPDATE OsiRmRequests SET requestProcess='C', requestStatus='C', requestedBy=:userId, reqestScheEndDate=:currentDate WHERE requestId=:requestId";
			this.entityManager.createQuery(query)
			.setParameter("requestId", requestId)
			.setParameter("currentDate", new Date())
			.setParameter("userId", userId)
			.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		
	}
	public static Timestamp getTodayTimestamp() throws Exception{
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
			e.printStackTrace();
			throw e;
		}
		return timeStamp;
	}
    
    /**
     * @param sdate
     * @return
     */
    public static Timestamp getTimestampfromsdate(String sdate) throws Exception{
    	java.util.Date date = null;
		java.sql.Timestamp timeStamp = null;
    	try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = simpleDateFormat.parse(sdate);
			timeStamp = new java.sql.Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		}
    	return timeStamp;
    }
    
    public static Timestamp getTimestampfromString(String sdate) throws Exception{
    	java.util.Date date = null;
		java.sql.Timestamp timeStamp = null;
    	try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = simpleDateFormat.parse(sdate);
			timeStamp = new java.sql.Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		}
    	return timeStamp;
    }
    @SuppressWarnings("unchecked")
	public String getReportOutputType(int reportId) throws DataAccessException{
		String outputFileType = "";
		List<Object[]> objectList = null;
		try{
			String hql = "select outputType, outputType from OsiReports where reportId=:reportId";
			objectList = (List<Object[]>)this.entityManager.createQuery(hql.toString())
					.setParameter("reportId", reportId)
					.getResultList();
			if(objectList.size()!=0)
				outputFileType = objectList.get(0)[0].toString();
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return outputFileType;
	}
}