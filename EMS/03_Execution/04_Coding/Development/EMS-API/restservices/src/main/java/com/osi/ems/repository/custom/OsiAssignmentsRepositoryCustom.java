package com.osi.ems.repository.custom;

import java.sql.Timestamp;
import java.util.List;

import org.json.JSONArray;

import com.osi.ems.domain.OsiAssignments;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.domain.OsiGrades;
import com.osi.ems.domain.OsiJobCodes;
import com.osi.ems.domain.OsiLocations;
import com.osi.urm.domain.OsiLookupTypes;
import com.osi.urm.exception.DataAccessException;

public interface OsiAssignmentsRepositoryCustom {

	Integer saveAssignment(OsiAssignments newAssignment) throws DataAccessException;
	
	Integer updateAssignment(OsiAssignments newAssignment) throws DataAccessException;

	List<OsiJobCodes> getAllJobs(Integer orgId) throws DataAccessException;

	List<OsiGrades> getAllGrades(Integer orgId) throws DataAccessException;

	OsiGrades getGradeByID(Integer graderId) throws DataAccessException;

	OsiJobCodes getJobCodeById(Integer jobCodeId) throws DataAccessException;
	
	OsiLocations getOsiLocationById(Integer locationId) throws DataAccessException;

	List<OsiLookupTypes> getAllDepartment() throws DataAccessException;

	List<OsiEmployees> searchEmployeeByNumber(String empNumber) throws DataAccessException;

	OsiEmployees searchSupervisorByNumber(String empNumber, String convertedDate) throws DataAccessException;

	Integer updateAssignments(OsiAssignments osiEmployees, String effectiveEndDate) throws DataAccessException;

	String currentTimeStamp(int addSeconds) throws DataAccessException;

	String findEmployeeById(Integer employeeId) throws DataAccessException;

	List<OsiEmployees> searchEmployeeById(Integer id) throws DataAccessException;

	String convertTimestampToString(Timestamp timestamp) throws DataAccessException;

	String convertToTimestamp(String date) throws DataAccessException;

	Integer getMaxAssignmentId() throws DataAccessException;

	List<OsiAssignments> findAssignmentByEmployeeId(Integer empId) throws DataAccessException;

	List<OsiLocations> getAllLocations(Integer orgId) throws DataAccessException;

	List<OsiEmployees> getEmployees(Integer empId) throws DataAccessException;
	
	OsiAssignments findEmployeeByIdAndDate(Integer empId, String searchDate) throws DataAccessException;

	String findEmployeeByIdAndSearchDate(Integer employeeId, String searchDate) throws DataAccessException;

	JSONArray executeQuery(String query, JSONArray dependentsArray) throws DataAccessException;

	Integer findSuperviosrId(Integer empId) throws DataAccessException;
	


}
