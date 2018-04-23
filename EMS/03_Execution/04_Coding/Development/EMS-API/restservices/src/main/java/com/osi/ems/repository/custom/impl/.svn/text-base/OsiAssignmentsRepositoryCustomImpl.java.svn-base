package com.osi.ems.repository.custom.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiAssignments;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.domain.OsiGrades;
import com.osi.ems.domain.OsiJobCodes;
import com.osi.ems.domain.OsiLocations;
import com.osi.ems.domain.OsiRegions;
import com.osi.ems.repository.custom.OsiAssignmentsRepositoryCustom;
import com.osi.ems.repository.custom.OsiEmployeesRepositoryCustom;
import com.osi.ems.service.OsiAssignmentsService;
import com.osi.urm.domain.OsiLookupTypes;
import com.osi.urm.exception.DataAccessException;

@Component
public class OsiAssignmentsRepositoryCustomImpl implements OsiAssignmentsRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	OsiAssignmentsService osiAssignmentsService;
	
	@Autowired
	OsiEmployeesRepositoryCustom osiEmpRepo;
	
	@Autowired
	private CommonService commonService;
	
	private final Logger LOGGER = Logger.getLogger(OsiAssignmentsRepositoryCustomImpl.class);
	
	@Transactional
	public Integer saveAssignment(OsiAssignments newAssignment) throws DataAccessException {
		
		LOGGER.info("saveAssignment :: Begin ");
		
		Integer result = null;
		try {
			
			Query query = entityManager.createNativeQuery("insert into osi_assignments(version, assignment_id, assignment_type, effective_start_date, effective_end_date"
					+ ",contract_start_date, contract_end_date, is_manager, supervisor_id, employee_id, grade_id, referred_by_id"
					+ ",change_reason, dept_id, job_id, location_id, on_probation, probation_unit, probation_unit_value, pay_grade_id"
					+ ",created_by, creation_date, last_updated_by, last_update_date, probation_end_date )"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			   
			   query.setParameter(1, newAssignment.getVersion());
			   query.setParameter(2, newAssignment.getAssignmentId());
			   query.setParameter(3, newAssignment.getAssignmentType());
			   query.setParameter(4, newAssignment.getEffectiveStartDate());
			   query.setParameter(5, newAssignment.getEffectiveEndDate());
			   
			   query.setParameter(6, newAssignment.getContractStartDate());
			   query.setParameter(7, newAssignment.getContractEndDate());
			   query.setParameter(8, newAssignment.getIsManager());
			   query.setParameter(9, newAssignment.getSupervisorId());
			   query.setParameter(10, newAssignment.getEmployeeId());
			  
			   query.setParameter(11, newAssignment.getGradeId());
			   query.setParameter(12, newAssignment.getReferredById());
			   query.setParameter(13, newAssignment.getChangeReason());
			   query.setParameter(14, newAssignment.getDeptId());
			   query.setParameter(15, newAssignment.getJobId());
			   
			   query.setParameter(16, newAssignment.getLocationId());
			   query.setParameter(17, newAssignment.getOnProbation());
			   query.setParameter(18, newAssignment.getProbationUnit());
			   query.setParameter(19, newAssignment.getProbationUnitValue());
			   query.setParameter(20, newAssignment.getPayGradeId());
			   
			   query.setParameter(21, newAssignment.getCreatedBy());
			   query.setParameter(22, newAssignment.getCreatedDate());
			   query.setParameter(23, newAssignment.getUpdatedBy());
			   query.setParameter(24, newAssignment.getLastUpdateDate());
			   if(newAssignment.getOnProbation()==1){
				   String originalDatOfHire = osiEmpRepo.getEmployees(newAssignment.getEmployeeId()).getOriginalDateOfHire();
				   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				   Date originalDOH = sdf.parse(originalDatOfHire);
				   Calendar cal = Calendar.getInstance();
				   //cal.setTime(newAssignment.getProbationEndDate());
				   cal.setTime(originalDOH);
				   if(newAssignment.getProbationUnit()!=null && "years".equalsIgnoreCase(newAssignment.getProbationUnit())){
					   cal.set(Calendar.YEAR, (cal.get(Calendar.YEAR)+newAssignment.getProbationUnitValue()));
				   }else if(newAssignment.getProbationUnit()!=null && "months".equalsIgnoreCase(newAssignment.getProbationUnit())){
					   cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+newAssignment.getProbationUnitValue()));
				   }else if(newAssignment.getProbationUnit()!=null && "days".equalsIgnoreCase(newAssignment.getProbationUnit())){
					   cal.set(Calendar.DAY_OF_MONTH, (cal.get(Calendar.DAY_OF_MONTH)+newAssignment.getProbationUnitValue()));
				   }
				   newAssignment.setProbationEndDate(cal.getTime());
			   }
			   query.setParameter(25, newAssignment.getProbationEndDate());
			   
			   if(newAssignment.getAssignmentType()!=null && (newAssignment.getAssignmentType().equals("CONTRACTOR") || 
					   newAssignment.getAssignmentType().equals("INTERN"))){
				   query.setParameter(17, 0);
				   query.setParameter(18, null);
				   query.setParameter(19, null);
				   query.setParameter(25, null);
			   }
			   result = query.executeUpdate();
			   
		}catch(Exception e){
			LOGGER.error("Error Occured :: "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("saveAssignment :: End ");
		return result;
	}
	
	@Transactional
	public Integer updateAssignment(OsiAssignments newAssignment) throws DataAccessException {
		Integer result = null;
		LOGGER.info("updateAssignment :: Begin ");
		try {
			
			Query query = entityManager.createNativeQuery("update osi_assignments set version = ?, assignment_type = ?, effective_start_date = ?, "
					+ "effective_end_date = ?, contract_start_date = ?, contract_end_date = ?, is_manager = ?, supervisor_id = ?, employee_id = ?, location_id = ?, "
					+ "grade_id = ?, referred_by_id = ? ,change_reason = ?, dept_id = ?, job_id = ?, on_probation = ?, probation_unit = ?, probation_unit_value = ?, "
					+ "pay_grade_id = ? ,created_by = ?, creation_date = ?, last_updated_by = ?, last_update_date = ?, probation_end_date = ? "
					+ "where assignment_id = ?");
			   
			   query.setParameter(1, newAssignment.getVersion());
			   query.setParameter(2, newAssignment.getAssignmentType());
			   query.setParameter(3, newAssignment.getEffectiveStartDate());
			   query.setParameter(4, newAssignment.getEffectiveEndDate());
			   
			   query.setParameter(5, newAssignment.getContractStartDate());
			   query.setParameter(6, newAssignment.getContractEndDate());
			   query.setParameter(7, newAssignment.getIsManager());
			   query.setParameter(8, newAssignment.getSupervisorId());
			   query.setParameter(9, newAssignment.getEmployeeId());
			   query.setParameter(10, newAssignment.getLocationId());
			  
			   query.setParameter(11, newAssignment.getGradeId());
			   query.setParameter(12, newAssignment.getReferredById());
			   query.setParameter(13, newAssignment.getChangeReason());
			   query.setParameter(14, newAssignment.getDeptId());
			   query.setParameter(15, newAssignment.getJobId());
			  
			   query.setParameter(16, newAssignment.getOnProbation());
			   query.setParameter(17, newAssignment.getProbationUnit());
			   query.setParameter(18, newAssignment.getProbationUnitValue());
			   query.setParameter(19, newAssignment.getPayGradeId());
			   
			   query.setParameter(20, newAssignment.getCreatedBy());
			   query.setParameter(21, newAssignment.getCreatedDate());
			   query.setParameter(22, newAssignment.getUpdatedBy());
			   query.setParameter(23, newAssignment.getLastUpdateDate());
			   if(newAssignment.getOnProbation()!=null && newAssignment.getOnProbation()==1){
				   String originalDatOfHire = osiEmpRepo.getEmployees(newAssignment.getEmployeeId()).getOriginalDateOfHire();
				   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				   Date originalDOH = sdf.parse(originalDatOfHire);
				   Calendar cal = Calendar.getInstance();
				   //cal.setTime(newAssignment.getProbationEndDate());
				   cal.setTime(originalDOH);
				   if(newAssignment.getProbationUnit()!=null && "years".equalsIgnoreCase(newAssignment.getProbationUnit())){
					   cal.set(Calendar.YEAR, (cal.get(Calendar.YEAR)+newAssignment.getProbationUnitValue()));
				   }else if(newAssignment.getProbationUnit()!=null && "months".equalsIgnoreCase(newAssignment.getProbationUnit())){
					   cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+newAssignment.getProbationUnitValue()));
				   }else if(newAssignment.getProbationUnit()!=null && "days".equalsIgnoreCase(newAssignment.getProbationUnit())){
					   cal.set(Calendar.DAY_OF_MONTH, (cal.get(Calendar.DAY_OF_MONTH)+newAssignment.getProbationUnitValue()));
				   }
				   newAssignment.setProbationEndDate(cal.getTime());
			   }
			   query.setParameter(24, newAssignment.getProbationEndDate());
			   query.setParameter(25, newAssignment.getAssignmentId());
			   if(newAssignment.getAssignmentType()!=null && (newAssignment.getAssignmentType().equals("CONTRACTOR") || 
					   newAssignment.getAssignmentType().equals("INTERN"))){
				   query.setParameter(17, 0);
				   query.setParameter(18, null);
				   query.setParameter(19, null);
				   query.setParameter(25, null);
			   }
			   result = query.executeUpdate();
			   
		} catch (Exception e) {
			LOGGER.error(" Exception ::"+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("updateAssignment :: End");
		return result;
	}	
	

	@Override
	public List<OsiJobCodes> getAllJobs(Integer orgId) throws DataAccessException {
		LOGGER.info("getAllJobs :: Begin ");
		List<OsiJobCodes> jobs = new ArrayList<OsiJobCodes>();
		try {
			String query = "select job_code_id, job_code_name, org_id, active from osi_job_codes WHERE active = ? and org_id = :orgId";
			List<Object []> newObject = this.entityManager.createNativeQuery(query)
					.setParameter(1, 1)
					.setParameter("orgId", orgId)
					.getResultList();
			for(Object[]  job : newObject){
				OsiJobCodes newJobs = new OsiJobCodes();
				newJobs.setJobCodeId((Integer)job[0]);
				newJobs.setJobCodeName((String)job[1]);
				newJobs.setOrgId((Integer)job[2]);
				newJobs.setActive((Integer)job[3]);
				jobs.add(newJobs);
				
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("getAllJobs :: End ");
		return jobs;
	}

	@Override
	public List<OsiGrades> getAllGrades(Integer orgId) throws DataAccessException {
		List<OsiGrades> grades = new ArrayList<OsiGrades>();
		LOGGER.info("getAllGrades :: Begin ");
		try {
			String query = "select grade_id, grade_name, org_id, active, cost_per_hour, cost_per_month from osi_grades where active = ? and org_id = :orgId order by seq";
			List<Object []> newObject = this.entityManager.createNativeQuery(query)
					.setParameter(1, 1)
					.setParameter("orgId", orgId)
					.getResultList();
			for(Object[]  grads : newObject){
				OsiGrades newGrades = new OsiGrades();
				newGrades.setGradeId((Integer)grads[0]);
				newGrades.setGradeName((String)grads[1]);
				newGrades.setOrgId((Integer)grads[2]);
				newGrades.setActive((Integer)grads[3]);
				newGrades.setCostPerHour((BigDecimal) grads[4]);
				newGrades.setCostPerMonth((BigDecimal) grads[5]);
				grades.add(newGrades);
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("getAllGrades :: End ");
		return grades;
	}

	@Override
	public List<OsiLocations> getAllLocations(Integer orgId) throws DataAccessException {
		List<OsiLocations> locations = new ArrayList<OsiLocations>();
		LOGGER.info("getAllLocations :: Begin ");
		try {
			String query = "select location_id, location_name, active, org_id, r.region_id, r.region_short_name from osi_locations l, osi_regions r"
					+ " where org_id = :orgId and active = :active and l.region_id = r.region_id ";
			List<Object []> newObject = this.entityManager.createNativeQuery(query)
					.setParameter("orgId", orgId)
					.setParameter("active", 1)
					.getResultList();
			for(Object[]  locs : newObject){
				OsiLocations newLocations = new OsiLocations();
				newLocations.setLocationId((Integer)locs[0]);
				newLocations.setLocationName((String)locs[1]);
				newLocations.setActive((Integer)locs[2]);
				OsiRegions osiRegions = new OsiRegions();
				if(locs[3]!=null){
					osiRegions.setRegionId((Integer)locs[4]);
				}
				if(locs[4]!=null){
					osiRegions.setRegionShortName((String)locs[5]);
				}
				newLocations.setOsiRegionsId(osiRegions);
				
				locations.add(newLocations);
			}		
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("getAllLocations :: End ");
		return locations;
	}

	@Override
	public List<OsiLookupTypes> getAllDepartment() throws DataAccessException {
		List<OsiLookupTypes> lookupTypes = new ArrayList<OsiLookupTypes>();
		LOGGER.info("getAllDepartment :: Begin ");
		try {
			String query = "from OsiLookupTypes ol where lookupName = Upper('Department') or lookupCode = Upper('Department')";
			lookupTypes = this.entityManager.createQuery(query, OsiLookupTypes.class).getResultList();
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("getAllDepartment :: End ");
		return lookupTypes;
	}

	@Override
	public List<OsiEmployees> searchEmployeeByNumber(String employeeName) throws DataAccessException {
		List<OsiEmployees> employees = new ArrayList<OsiEmployees>();
		LOGGER.info("searchEmployeeByNumber :: Begin ");
		Date currentDate = new Date();
		try {
			String query = "select distinct employee_id, full_name from osi_employees "
					+ "where Upper(full_name) LIKE Upper(:employeeName) "
					+ "and :currentDate between effective_start_date "
					+ "and effective_end_date ";
			
			List<Object []> newObject = this.entityManager.createNativeQuery(query)
					.setParameter("employeeName", "%"+employeeName+"%")
					.setParameter("currentDate", currentDate)
					.getResultList();
			
			for(Object[] list : newObject){
				OsiEmployees newEmp = new OsiEmployees();
				newEmp.setEmployeeId((Integer) list[0]);
				newEmp.setFullName((String) list[1]);
				employees.add(newEmp);
			}	
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("searchEmployeeByNumber :: End ");
		return employees;
	}

	@Override
	public OsiEmployees searchSupervisorByNumber(String employeeNumber, String currentDate) throws DataAccessException {
		OsiEmployees employee = new OsiEmployees();
		LOGGER.info("searchSupervisorByNumber :: Begin ");
		try {
			String query = "select distinct oe.employee_id, oe.employee_number, oe.full_name "
					+ "from osi_assignments oa inner join osi_employees oe on oa.employee_id = oe.employee_id "
					+ "where oa.is_manager = 1 "
					+ "and Upper(oe.employee_number) = Upper(:employeeNumber) "
					+ "and oe.effective_start_date <= :currentDate and oe.effective_end_date >= :currentDate "
					+ "and oa.effective_start_date <= :currentDate and oa.effective_end_date >= :currentDate ";
			
			Object [] newObject = (Object[]) this.entityManager.createNativeQuery(query)
					.setParameter("employeeNumber", employeeNumber)
					.setParameter("currentDate", currentDate)
					.getSingleResult();
			
			employee.setEmployeeId((Integer) newObject[0]);
			employee.setEmployeeNumber((String) newObject[1]);
			employee.setFullName((String) newObject[2]);

			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("searchSupervisorByNumber :: End ");
		return employee;
	}
	
	@Override
	public Integer updateAssignments(OsiAssignments osiAssignments, String effectiveEndDate) throws DataAccessException {
		Integer result;
		LOGGER.info("updateAssignments :: Begin ");
		try {
			String updateQuery = "update osi_assignments e set "
					+ " e.effective_end_date = ?, e.LAST_UPDATE_DATE = ? where "
					+ " e.employee_id = ? and "
					+ " ? between e.effective_start_date and e.effective_end_date";
			result = this.entityManager.createNativeQuery(updateQuery)
					.setParameter(1, effectiveEndDate)
					.setParameter(2, commonService.getCurrentDateStringinUTC())
					.setParameter(3, osiAssignments.getEmployeeId())
					.setParameter(4, new java.sql.Timestamp(new Date().getTime()))
					.executeUpdate();
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("updateAssignments :: End ");
		return result;
	}

	@Override
	public String currentTimeStamp(int addSeconds) throws DataAccessException {
		  Timestamp finalTimestamp;
		  LOGGER.info("currentTimeStamp :: Begin ");
		try{
			long currentDate = System.currentTimeMillis();      
	        Timestamp currentTimestamp = new Timestamp(currentDate);
	        Calendar cal = Calendar.getInstance();
	        cal.setTimeInMillis(currentTimestamp.getTime());
	        cal.add(Calendar.SECOND, addSeconds);
	        finalTimestamp = new Timestamp(cal.getTime().getTime());
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured getting the current time stamp");
		}
		  LOGGER.info("currentTimeStamp :: end ");
        return convertTimestampToString(finalTimestamp);
	}
	
	@Override
	public String convertTimestampToString(Timestamp timestamp) throws DataAccessException {
		String timestampString = null;
		LOGGER.info("convertTimestampToString :: Begin ");
		try{
			
			SimpleDateFormat dateFormat = null;
			if(timestamp != null) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				timestampString = dateFormat.format(timestamp);
			}
		}
		catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured getting the current time stamp");
		}
		LOGGER.info("convertTimestampToString :: End ");
		return timestampString;
	}
	
	@Override
	public String convertToTimestamp(String date) throws DataAccessException {
		Timestamp timestamp = null;
		SimpleDateFormat dateFormat = null;
		String dateString = null;
		LOGGER.info("convertToTimestamp :: Begin ");
		try {
			if(date != null && date!="") {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(date);
				timestamp = new java.sql.Timestamp(parsedDate.getTime());
				dateString = timestamp.toString();
			}
		} catch(Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while parsing");
		}
		LOGGER.info("convertToTimestamp :: End ");
		return dateString;
	}
	

	@Override
	public String findEmployeeById(Integer employeeId) throws DataAccessException {
		String employeeType = null;
		LOGGER.info("findEmployeeById :: Begin ");
		try {
			String query = "select employee_type "
					+ "from osi_employees "
					+ "where employee_id = :employeeId  and "
					+ " current_timestamp between effective_start_date and effective_end_date";
			employeeType = (String) this.entityManager.createNativeQuery(query)
					.setParameter("employeeId", employeeId)
					.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("findEmployeeById :: End ");
		
		return employeeType;
	}
	
	@Override
	public String findEmployeeByIdAndSearchDate(Integer employeeId, String searchDate) throws DataAccessException {
		String employeeType = null;
		LOGGER.info("findEmployeeByIdAndSearchDate :: Begin ");
		try {
			String dates[] =searchDate.split(" ");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String time  = dtf.format(now);  
			String query = "select employee_type "
					+ "from osi_employees "
					+ "where employee_id = :employeeId "
					+ " and '"+dates[0]+" "+time+"' between effective_start_date and effective_end_date";
			employeeType = (String) this.entityManager.createNativeQuery(query)
					.setParameter("employeeId", employeeId)
					.getSingleResult();
			
			
		}  catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("findEmployeeByIdAndSearchDate :: End ");
		return employeeType;
	}



	@Override
	public List<OsiEmployees> searchEmployeeById(Integer employeeId) throws DataAccessException {
		List<OsiEmployees> employeeList = new ArrayList<OsiEmployees>();
		LOGGER.info("searchEmployeeById :: Begin ");
		try {
			String query = "select employee_id, full_name, employee_number, employee_type "
					+ "from osi_employees "
					+ "where employee_id = :employeeId "
					+ "order by last_update_date desc ";
			List<Object []> newObject = this.entityManager.createNativeQuery(query)
					.setParameter("employeeId", employeeId)
					.getResultList();
			
			for(Object [] list : newObject){
				OsiEmployees employee = new OsiEmployees();
				
				employee.setEmployeeId((Integer)list[0]);
				employee.setFullName((String)list[1]);
				employee.setEmployeeNumber((String)list[2]);
				employee.setEmployeeType((String)list[3]);
				employeeList.add(employee);
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("searchEmployeeById :: End ");
		return employeeList;
	}



	@Override
	public List<OsiAssignments> findAssignmentByEmployeeId(Integer empId) throws DataAccessException {
		List<OsiAssignments> osiAssignments = new ArrayList<OsiAssignments>();
		LOGGER.info("findAssignmentByEmployeeId :: Begin ");
		try {
			String query = "select version, assignment_id, assignment_type, effective_start_date, effective_end_date"
					+ ",contract_start_date, contract_end_date, is_manager, supervisor_id, employee_id, grade_id, referred_by_id"
					+ ",change_reason, dept_id, job_id, location_id, on_probation, probation_unit, probation_unit_value, pay_grade_id"
					+ ",created_by, creation_date, last_updated_by, last_update_date, probation_end_date "
					+ "from osi_assignments "
					+ "where employee_id = :empId "
					+ "order by last_update_date desc limit 1";
			
			List<Object []> newObject = this.entityManager.createNativeQuery(query)
					.setParameter("empId", empId)
					.getResultList();
			
			for(Object[]  assignments : newObject){
				OsiAssignments assignment = new OsiAssignments();
				assignment.setVersion((Integer)assignments[0]);
				assignment.setAssignmentId((Integer)assignments[1]);
				assignment.setAssignmentType((String)assignments[2]);
				assignment.setEffectiveStartDate((Date)assignments[3]);
				assignment.setEffectiveEndDate(convertTimestampToString((Timestamp)assignments[4]));
				           
				assignment.setContractStartDate((Timestamp)assignments[5]);
				assignment.setContractEndDate((Timestamp)assignments[6]);
				assignment.setIsManager((Integer)assignments[7]);
				assignment.setSupervisorId((Integer)assignments[8]);
				assignment.setEmployeeId((Integer)assignments[9]);
				           
				assignment.setGradeId((Integer)assignments[10]);
				assignment.setReferredById((Integer)assignments[11]);
				assignment.setChangeReason((String)assignments[12]);
				assignment.setDeptId((Integer)assignments[13]);
				assignment.setJobId((Integer)assignments[14]);
				           
				assignment.setLocationId((Integer)assignments[15]);
				assignment.setOnProbation((Integer)assignments[16]);
				assignment.setProbationUnit((String)assignments[17]);
				assignment.setProbationUnitValue((Integer)assignments[18]);
				assignment.setPayGradeId((Integer)assignments[19]);
				           
				assignment.setCreatedBy((Integer)assignments[20]);
				assignment.setCreatedDate((Timestamp)assignments[21]);
				assignment.setUpdatedBy((Integer)assignments[22]);
				assignment.setLastUpdateDate((Timestamp)assignments[23]);
				assignment.setProbationEndDate((Date)assignments[24]);
				osiAssignments.add(assignment);
			}
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("findAssignmentByEmployeeId :: End ");
		return osiAssignments;
	}

	@Override
	public Integer getMaxAssignmentId() throws DataAccessException {
		Integer assignmentId = null;
		LOGGER.info("getMaxAssignmentId :: Begin ");
		try {
			String query = "select max(assignment_id) from osi_assignments";
			assignmentId = (Integer) this.entityManager.createNativeQuery(query).getSingleResult();
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("getMaxAssignmentId :: End ");
		return assignmentId;
	}



	@Override
	public List<OsiEmployees> getEmployees(Integer empId) throws DataAccessException {
		List<OsiEmployees> employeeList = new ArrayList<OsiEmployees>();
		Timestamp currentDate = new java.sql.Timestamp(new Date().getTime());
		LOGGER.info("getEmployees :: Begin ");
		try {
			String query = "select employee_id, org_id "
					+ "from osi_employees "
					+ "where employee_id = :empId "
					+ "and :currentDate between effective_start_date "
					+ "and effective_end_date ";
			List<Object []> newObject = this.entityManager.createNativeQuery(query)
					.setParameter("empId", empId)
					.setParameter("currentDate", currentDate)
					.getResultList();
			
			for(Object [] list : newObject){
				OsiEmployees employee = new OsiEmployees();
				
				employee.setEmployeeId((Integer)list[0]);
				employee.setOrgId((Integer)list[1]);
				employeeList.add(employee);
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("getEmployees :: End ");
		return employeeList;
	}



	@Override
	public OsiGrades getGradeByID(Integer graderId) throws DataAccessException {
		OsiGrades osiGrades = null;
		List<Objects[]> result = new ArrayList<Objects[]>();
		LOGGER.info("getGradeByID :: Begin ");
		try {
			Query query = entityManager.createQuery("select grades from OsiGrades grades where grades.gradeId = ?").setParameter(1, graderId);
			
			result = (List<Objects[]>) query.getResultList();
			osiGrades = new OsiGrades();
			if (result != null || !result.isEmpty()) {
				for (Object objects : result) {
					osiGrades = (OsiGrades) objects;
				}
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("getGradeByID :: End ");
		return osiGrades;
	}



	@Override
	public OsiJobCodes getJobCodeById(Integer jobCodeId) throws DataAccessException {
		OsiJobCodes osiJobCodes = null;
		List<Objects[]> result = new ArrayList<Objects[]>();
		LOGGER.info("getJobCodeById :: Begin ");
		try {
			Query query = entityManager.createQuery("select jobCodes from OsiJobCodes jobCodes where jobCodes.jobCodeId = ?").setParameter(1, jobCodeId);
			
			result = (List<Objects[]>) query.getResultList();
			osiJobCodes = new OsiJobCodes();
			if (result != null || !result.isEmpty()) {
				for (Object objects : result) {
					osiJobCodes = (OsiJobCodes) objects;
				}
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("getJobCodeById :: End ");
		return osiJobCodes;
	}



	@Override
	public OsiLocations getOsiLocationById(Integer locationId) throws DataAccessException {
		OsiLocations osiLocations = null;
		List<Objects[]> result = new ArrayList<Objects[]>();
		LOGGER.info("getOsiLocationById :: Begin ");
		try {
			Query query = entityManager.createQuery("select location from OsiLocations location where location.locationId = ?").setParameter(1, locationId);
			
			result = (List<Objects[]>) query.getResultList();
			osiLocations = new OsiLocations();
			if (result != null || !result.isEmpty()) {
				for (Object objects : result) {
					osiLocations = (OsiLocations) objects;
				}
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("getOsiLocationById :: End ");
		return osiLocations;
	}

	@Override
	public OsiAssignments findEmployeeByIdAndDate(Integer empId, String searchDate) throws DataAccessException {
		OsiAssignments assignment = new OsiAssignments();
		LOGGER.info("findEmployeeByIdAndDate :: Begin ");
		
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String time  = dtf.format(now);   
			String query = "select version, assignment_id, assignment_type, effective_start_date, effective_end_date"
					+ ",contract_start_date, contract_end_date, is_manager, supervisor_id, employee_id, grade_id, referred_by_id"
					+ ",change_reason, dept_id, job_id, location_id, on_probation, probation_unit, probation_unit_value, pay_grade_id"
					+ ",created_by, creation_date, last_updated_by, last_update_date, probation_end_date "
					+ "from osi_assignments "
					+ "where employee_id = :empId "
					+ " and '"+searchDate+" "+time+"' between effective_start_date and effective_end_date "
					+ "order by last_update_date desc ";
			
			Object [] assignments = (Object[]) this.entityManager.createNativeQuery(query)
					.setParameter("empId", empId)
//					.setParameter(1, searchDate)
					.getSingleResult();
			
			//for(Object[]  assignments : newObject){
				assignment.setVersion((Integer)assignments[0]);
				assignment.setAssignmentId((Integer)assignments[1]);
				assignment.setAssignmentType((String)assignments[2]);
				assignment.setEffectiveStartDate((Date)assignments[3]);
				assignment.setEffectiveEndDate(convertTimestampToString((Timestamp)assignments[4]));
				           
				assignment.setContractStartDate((Timestamp)assignments[5]);
				assignment.setContractEndDate((Timestamp)assignments[6]);
				assignment.setIsManager((Integer)assignments[7]);
				assignment.setSupervisorId((Integer)assignments[8]);
				assignment.setEmployeeId((Integer)assignments[9]);
				           
				assignment.setGradeId((Integer)assignments[10]);
				assignment.setReferredById((Integer)assignments[11]);
				assignment.setChangeReason((String)assignments[12]);
				assignment.setDeptId((Integer)assignments[13]);
				assignment.setJobId((Integer)assignments[14]);
				           
				assignment.setLocationId((Integer)assignments[15]);
				assignment.setOnProbation((Integer)assignments[16]);
				assignment.setProbationUnit((String)assignments[17]);
				assignment.setProbationUnitValue((Integer)assignments[18]);
				assignment.setPayGradeId((Integer)assignments[19]);
				           
				assignment.setCreatedBy((Integer)assignments[20]);
				assignment.setCreatedDate((Timestamp)assignments[21]);
				assignment.setUpdatedBy((Integer)assignments[22]);
				assignment.setLastUpdateDate((Timestamp)assignments[23]);
				assignment.setProbationEndDate((Date)assignments[24]);
			//}
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("findEmployeeByIdAndDate :: End ");
		return assignment;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONArray executeQuery(String query, JSONArray dependentsArray) throws DataAccessException {
		JSONArray resultArray = null;
		LOGGER.info("executeQuery :: Begin ");
		try {
			for(int i=0; i< dependentsArray.length(); i++) {
				JSONObject paramJson = dependentsArray.getJSONObject(i);
				Set<String> keys = paramJson.keySet();
				for(String key : keys){
						try{
						  int num = Integer.parseInt(paramJson.get(key).toString());
						  query = query.replaceAll(":"+key,paramJson.get(key).toString());
						} catch (NumberFormatException e) {
							query = query.replaceAll(":"+key,"'"+paramJson.get(key).toString()+"'");
						}
					
				}
			}
			Query sqlQuery = this.entityManager.createNativeQuery(query);
			
			List<Object> object = (List<Object>) this.entityManager.createNativeQuery(query)
					.getResultList();
			System.out.println(object.getClass());
			resultArray = new JSONArray();
			for(Object obj : object) {
				System.out.println(obj);
				JSONObject json = new JSONObject();
				json.put("id", obj.toString());
				json.put("name", obj.toString());
				
				resultArray.put(json);
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("executeQuery :: End ");
		return resultArray;
		
	}
	
	@Override
	public Integer findSuperviosrId(Integer empId) throws DataAccessException {
		LOGGER.info("findSuperviosrId :: Begin ");
		Integer supervisorId = null;
		try {
			String query = "select a.supervisor_id from osi_assignments a where a.employee_id = :empId and sysdate() between a.effective_start_date and a.effective_end_date";
			
			supervisorId = (Integer) this.entityManager.createNativeQuery(query)
					.setParameter("empId", empId)
					.getSingleResult();
			
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while getting supperviosr");
		}
		LOGGER.info("findSuperviosrId :: End ");
		return supervisorId;
	}
}


