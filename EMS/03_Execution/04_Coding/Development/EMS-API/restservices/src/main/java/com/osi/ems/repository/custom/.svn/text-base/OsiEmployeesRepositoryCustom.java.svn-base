package com.osi.ems.repository.custom;

import java.sql.Timestamp;
import java.util.List;

import com.osi.ems.domain.OsiCountries;
import com.osi.ems.domain.OsiCountryVisas;
import com.osi.ems.domain.OsiEmployees;
import com.osi.urm.exception.DataAccessException;

public interface OsiEmployeesRepositoryCustom {

	/**
	 * Method for convert save OsiEmployees.
	 * @param OsiEmployees
	 * @return Returns the Integer.
	 */
	Integer saveEmployeeInfo(OsiEmployees osiEmployees) throws DataAccessException;
	
	/**
	 * Method for get the single OsiEmployees. 
	 * @param employeeId
	 * @return Returns the OsiEmployees.
	 */
	OsiEmployees getEmployees(int employeeId) throws DataAccessException;
	
	/**
	 * Method for get the current Timestamp by adding the given seconds.
	 * @param int 
	 * @return Returns the String.
	 * @throws DataAccessException 
	 */
	String currentTimeStamp(int addSeconds) throws DataAccessException;
	
	/**
	 * Method for update the OsiEmployees.
	 * @param OsiEmployees 
	 * @param effectiveEndDate as String
	 * @return Returns the Integer.
	 */
	Integer updateEmployeeEffectiveEndDate(OsiEmployees osiEmployees, String effectiveEndDate) throws DataAccessException;
	
	/**
	 * Method for convert Timestamp value to String value. 
	 * @param Timestamp
	 * @return Returns the String object.
	 * @throws DataAccessException 
	 */
	String convertTimestampToString(Timestamp timestamp) throws DataAccessException;
	

	/**
	 * Method for convert String date value to Timestamp value.
	 * @param date
	 * @return Returns the String object.
	 * @throws DataAccessException 
	 */
	String convertToTimestamp(String date) throws DataAccessException;
	
	/**
	 * Method for get the max employeeId.
	 * @return Returns the Integer.
	 */
	Integer getMaxEmployeeId() throws DataAccessException;
	
	/**
	 * Method for search for the employee based on 
	 * String searchData (employeeNumber, firstName, lastName and officeMail).
	 * @return Returns the List<OsiEmployees>.
	 */
	List<OsiEmployees> searchEmployees(String searchData) throws DataAccessException;
	
	int isExistEmployee(String officeEmail, String employeeNumber) throws DataAccessException;
	
	public List<OsiCountries> getAllCountries() throws DataAccessException;
	
	public List<OsiCountryVisas> getAllCountryVisas(Integer countryId) throws DataAccessException;

	OsiEmployees findByEmployeeIdAndSerachDate(int employeeId, String searchDate) throws DataAccessException;

	Integer updateEmployee(OsiEmployees osiEmployees) throws DataAccessException;
	
	public boolean isManager(Integer employeeId) throws DataAccessException;
	
	/**
	 * For manager/employee search employees list
	 * Method for search for the employees based on 
	 * String searchData (employeeNumber, firstName, lastName and officeMail).
	 * @return Returns the List<OsiEmployees>.
	 */
	List<OsiEmployees> searchEmployeesSelf(String searchData) throws DataAccessException;
	boolean findByOfficeEmail(String mailId, Integer employeeId)throws DataAccessException;
	public boolean isExistEmployee(String employeeNumber, Integer orgId, Integer employeeId) throws DataAccessException;
	
	List<OsiEmployees> overallEmployeesSearch(String searchData) throws DataAccessException;

	List<OsiEmployees> getAllEmployees(String searchData) throws DataAccessException;
	
}
