package com.osi.ems.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiAssignments;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.domain.OsiGrades;
import com.osi.ems.domain.OsiJobCodes;
import com.osi.ems.domain.OsiLocations;
import com.osi.ems.domain.OsiRollUps;
import com.osi.ems.domain.OsiWfsActivities;
import com.osi.ems.mapper.OsiAssignmentsMapper;
import com.osi.ems.mapper.OsiRollupsMapper;
import com.osi.ems.repository.OsiAssignmentsRepository;
import com.osi.ems.repository.OsiRollupsRepository;
import com.osi.ems.repository.OsiWfsActivitiesRepository;
import com.osi.ems.repository.custom.OsiAssignmentsRepositoryCustom;
import com.osi.ems.repository.custom.OsiEmployeesRepositoryCustom;
import com.osi.ems.repository.custom.OsiWorkflowsRepositoryCustom;
import com.osi.ems.service.OsiAssignmentsService;
import com.osi.ems.service.dto.OsiAssignmentsDTO;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.ems.service.dto.OsiGradesDTO;
import com.osi.ems.service.dto.OsiJobCodesDTO;
import com.osi.ems.service.dto.OsiLocationsDTO;
import com.osi.ems.service.dto.OsiRollUpsDTO;
import com.osi.urm.domain.OsiLookupTypes;
import com.osi.urm.domain.OsiSegmentStructureDetails;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.mapper.OsiLookupMapper;
import com.osi.urm.repository.OsiSegmentStructureDetailsRepository;
import com.osi.urm.service.OsiLookupTypesService;
import com.osi.urm.service.dto.OsiLookupTypesDTO;
import com.osi.urm.service.dto.OsiLookupValuesDTO;

@Service
public class OsiAssignmentsServiceImpl implements OsiAssignmentsService {

	@Autowired
	private OsiAssignmentsMapper osiAssignmentsMapper;

	@Autowired
	OsiAssignmentsRepository osiAssignmentsRepository;

	@Autowired
	OsiAssignmentsRepositoryCustom osiAssignmentsRepositoryCustom;

	@Autowired
	OsiEmployeesRepositoryCustom osiEmployeesRepositoryCustom;

	@Autowired
	private OsiLookupMapper osiLookupMapper;
	
	@Autowired
	private OsiRollupsMapper osiRollupsMapper;
	
	@Autowired
	private OsiRollupsRepository osiRollupsRepository;
	
	@Autowired
    private OsiLookupTypesService osiLookupTypesService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	OsiWfsActivitiesRepository osiWfsActivitiesRepository;
	
	@Autowired
	OsiWorkflowsRepositoryCustom osiWorkflowsRepository;
	
	@Value( "${WORKFLOW.EMPLOYEE_CREATION}" )
	private String employeeCreation;
	
	@Value( "${WORKFLOW.EMPLOYEE_UPDATION}" )
	private String employeeUpdation;
	
	private final Logger LOGGER = LoggerFactory.getLogger(OsiAssignmentsServiceImpl.class);
	
	@Autowired
	private OsiSegmentStructureDetailsRepository osiSegmentStructureDetailsRepository;
	@Transactional
	public List<OsiAssignmentsDTO> getInitialAssignmentsList() throws BusinessException {
		List<OsiAssignmentsDTO> dto = new ArrayList<OsiAssignmentsDTO>();
		LOGGER.info("getInitialAssignmentsList : Begin");
		try {

			List<OsiAssignments> assignmentsList = osiAssignmentsRepository.getAllAssignments();
			if (assignmentsList.size() != 0) {
				for (OsiAssignments assignment : assignmentsList) {
					dto.add(osiAssignmentsMapper.convertOsiAssignmentToDTO(assignment));
				}
			}
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}

		return dto;
	}

	@Transactional
	public OsiAssignmentsDTO findByAssignmentId(Integer empId) throws BusinessException, DataAccessException {
		OsiAssignmentsDTO dto = new OsiAssignmentsDTO();

		try {
			if(empId != null){
				// to get the value of employeeType
				String employeeType = osiAssignmentsRepositoryCustom.findEmployeeById(empId);
				dto.setEmployeeType(employeeType);
							
							
				List<OsiAssignments> assignmentList = osiAssignmentsRepositoryCustom.findAssignmentByEmployeeId(empId);
				if (assignmentList.size() != 0) {
					dto = osiAssignmentsMapper.convertOsiAssignmentToDTO(assignmentList.get(0));
					// to get the value of employeeType
					employeeType = osiAssignmentsRepositoryCustom.findEmployeeById(empId);
					dto.setEmployeeType(employeeType);
				}
				
				// to get supervisor name
				if (dto.getSupervisorId() != null) {
					List<OsiEmployees> searchSupervisor = osiAssignmentsRepositoryCustom
							.searchEmployeeById(dto.getSupervisorId());
					dto.setSupervisorName(searchSupervisor.get(0).getFullName());
				}
				// to get referral name
				if (dto.getReferredById() != null) {
					List<OsiEmployees> searchReferredBy = osiAssignmentsRepositoryCustom
							.searchEmployeeById(dto.getReferredById());
					dto.setReferralName(searchReferredBy.get(0).getFullName());
				}
			}
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the Assignments");
		}
		LOGGER.info("getInitialAssignmentsList : End");
		return dto;
	}

	@Transactional
	public OsiAssignmentsDTO saveAssignments(OsiAssignmentsDTO osiAssignmentsDTO) throws BusinessException {
		OsiAssignmentsDTO dto = new OsiAssignmentsDTO();
		Integer result = 0;
		OsiAssignments newAssignment = new OsiAssignments();
		OsiWfsActivities wfsActivities = null;
		LOGGER.info("saveAssignments : Begin");
		try {
			DateFormat convertDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Integer maxId = osiAssignmentsRepositoryCustom.getMaxAssignmentId();
			if(osiAssignmentsDTO!=null && osiAssignmentsDTO.getOnProbation()==null){
				osiAssignmentsDTO.setOnProbation(0);
			}
			// existing emp assignments
			if (osiAssignmentsDTO.getAssignmentId() != null) {
				OsiAssignments existingAssignments = osiAssignmentsRepository
						.findByAssignmentId(osiAssignmentsDTO.getAssignmentId());
				Date existingEffectiveEndDate = convertDate.parse(existingAssignments.getEffectiveEndDate());
				if(new Date().before(existingEffectiveEndDate) || new Date().equals(existingEffectiveEndDate)){
				if (osiAssignmentsDTO.getVersion().intValue() == existingAssignments.getVersion().intValue()) {
					OsiAssignments finalAssignments = osiAssignmentsMapper.convertDTOToOsiAssignment(osiAssignmentsDTO);
					
					String effecEndDate = osiAssignmentsRepositoryCustom.currentTimeStamp(0);
					String effecStartDate = osiAssignmentsRepositoryCustom.currentTimeStamp(1);
					String UtcEffecEndDate = commonService.convertDateStringToUTC(effecEndDate, osiAssignmentsDTO.getUpdatedBy());
					String UtcEffecStartDate = commonService.convertDateStringToUTC(effecStartDate, osiAssignmentsDTO.getUpdatedBy());
					
					osiAssignmentsRepositoryCustom.updateAssignments(existingAssignments, UtcEffecEndDate);

					finalAssignments.setVersion(existingAssignments.getVersion().intValue() + 1);
					finalAssignments.setEffectiveStartDate(convertDate.parse(UtcEffecStartDate));
					finalAssignments.setEffectiveEndDate(existingAssignments.getEffectiveEndDate());
					//finalAssignments.setEffectiveEndDate(osiAssignmentsDTO.getEffectiveEndDate());
					finalAssignments.setUpdatedBy(osiAssignmentsDTO.getUpdatedBy());
					finalAssignments.setLastUpdateDate(commonService.getCurrentDateinUTC());

					if (maxId != null) {
						finalAssignments.setAssignmentId(maxId + 1);
					} else {
						finalAssignments.setAssignmentId(0);
					}
					// call save employee
					// OsiEmployees toSaveEmployee =
					// osiEmployeesRepositoryCustom.getEmployees(osiAssignmentsDTO.getEmployeeId());
					// osiEmployeesRepositoryCustom.saveEmployeeInfo(toSaveEmployee);

					result = osiAssignmentsRepositoryCustom.saveAssignment(finalAssignments);
					

					// to get the value of employeeType
					//String employeeType = osiAssignmentsRepositoryCustom.findEmployeeById(osiAssignmentsDTO.getEmployeeId());
					//dto.setEmployeeType(employeeType);
					
					dto = osiAssignmentsMapper.convertOsiAssignmentToDTO(finalAssignments);
					dto.setEmployeeType(finalAssignments.getAssignmentType());
					
					
					if (dto.getSupervisorId() != null) {
						List<OsiEmployees> searchSupervisor = osiAssignmentsRepositoryCustom
								.searchEmployeeById(dto.getSupervisorId());
						dto.setSupervisorName(searchSupervisor.get(0).getFullName());
					}
					// to get referral name
					if (dto.getReferredById() != null) {
						List<OsiEmployees> searchReferredBy = osiAssignmentsRepositoryCustom
								.searchEmployeeById(dto.getReferredById());
						dto.setReferralName(searchReferredBy.get(0).getFullName());
					}
				}
				if(!existingAssignments.getLocationId().equals(osiAssignmentsDTO.getLocationId()) || !existingAssignments.getDeptId().equals(osiAssignmentsDTO.getDeptId())
					|| !existingAssignments.getGradeId().equals(osiAssignmentsDTO.getGradeId()) || !existingAssignments.getJobId().equals(osiAssignmentsDTO.getJobId())
					|| !existingAssignments.getSupervisorId().equals(osiAssignmentsDTO.getSupervisorId())){
						wfsActivities = new OsiWfsActivities();
						wfsActivities.setObjectId(dto.getEmployeeId());
						wfsActivities.setObjectName("OSI_EMPLOYEES");
						wfsActivities.setProcessFlag("N");
						wfsActivities.setWfsId(osiWorkflowsRepository.getWorkFlow(employeeUpdation, osiAssignmentsDTO.getOrgId()));
						wfsActivities.setOrgId(osiAssignmentsDTO.getOrgId());
						wfsActivities.setStartDate(commonService.getCurrentDateStringinUTC());
				}
			}else{
				wfsActivities = new OsiWfsActivities();
				wfsActivities.setObjectId(dto.getEmployeeId());
				wfsActivities.setObjectName("OSI_EMPLOYEES");
				wfsActivities.setProcessFlag("N");
				wfsActivities.setWfsId(osiWorkflowsRepository.getWorkFlow(employeeCreation, osiAssignmentsDTO.getOrgId()));
				wfsActivities.setOrgId(osiAssignmentsDTO.getOrgId());
				wfsActivities.setStartDate(commonService.getCurrentDateStringinUTC());
			}
			} else {

				newAssignment = osiAssignmentsMapper.convertDTOToOsiAssignment(osiAssignmentsDTO);
				newAssignment.setVersion(0);
				if (maxId != null) {
					newAssignment.setAssignmentId(maxId + 1);
				} else {
					newAssignment.setAssignmentId(0);
				}

				// call save employee
				// OsiEmployees toSaveEmployee =
				// osiEmployeesRepositoryCustom.getEmployees(osiAssignmentsDTO.getEmployeeId());
				// osiEmployeesRepositoryCustom.saveEmployeeInfo(toSaveEmployee);
				//newAssignment.setEffectiveStartDate(convertDate.parse(osiAssignmentsRepositoryCustom.currentTimeStamp(0)));
				newAssignment.setEffectiveStartDate(commonService.getCurrentDateinUTC());
				newAssignment.setEffectiveEndDate(osiAssignmentsDTO.getEffectiveEndDate());
				result = osiAssignmentsRepositoryCustom.saveAssignment(newAssignment);
				
				// to get the value of employeeType
				String employeeType = osiAssignmentsRepositoryCustom.findEmployeeById(newAssignment.getEmployeeId());
			
				
				dto = osiAssignmentsMapper.convertOsiAssignmentToDTO(newAssignment);
				dto.setEmployeeType(employeeType);
				if (dto.getSupervisorId() != null) {
					List<OsiEmployees> searchSupervisor = osiAssignmentsRepositoryCustom
							.searchEmployeeById(dto.getSupervisorId());
					dto.setSupervisorName(searchSupervisor.get(0).getFullName());
				}
				// to get referral name
				if (dto.getReferredById() != null) {
					List<OsiEmployees> searchReferredBy = osiAssignmentsRepositoryCustom
							.searchEmployeeById(dto.getReferredById());
					dto.setReferralName(searchReferredBy.get(0).getFullName());
				}
				wfsActivities = new OsiWfsActivities();
				wfsActivities.setObjectId(dto.getEmployeeId());
				wfsActivities.setObjectName("OSI_EMPLOYEES");
				wfsActivities.setProcessFlag("N");
				wfsActivities.setWfsId(osiWorkflowsRepository.getWorkFlow(employeeCreation, osiAssignmentsDTO.getOrgId()));
				wfsActivities.setOrgId(osiAssignmentsDTO.getOrgId());
				wfsActivities.setStartDate(commonService.getCurrentDateStringinUTC());
			}
			if(wfsActivities!=null && osiWorkflowsRepository.verifyExistingWorkflow(wfsActivities.getWfsId(), dto.getEmployeeId(), osiAssignmentsDTO.getOrgId())==0)
				osiWfsActivitiesRepository.save(wfsActivities);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while saving the Assignments");
		}
		LOGGER.info("saveAssignments : End");

		return dto;
	}
	
	@Transactional
	public OsiAssignmentsDTO updateAssignments(OsiAssignmentsDTO osiAssignmentsDTO) throws BusinessException {
		OsiAssignmentsDTO dto = new OsiAssignmentsDTO();
		Integer result = 0;
		OsiAssignments existingAssignments = null;
		LOGGER.info("updateAssignments : Begin");
		try {

			// existing emp assignments
			if (osiAssignmentsDTO.getAssignmentId() != null) {
				existingAssignments = osiAssignmentsRepository
						.findByAssignmentId(osiAssignmentsDTO.getAssignmentId());
				if (osiAssignmentsDTO.getVersion().intValue() == existingAssignments.getVersion().intValue()) {
					OsiAssignments finalAssignments = osiAssignmentsMapper.convertDTOToOsiAssignment(osiAssignmentsDTO);
					
					//finalAssignments.setEffectiveStartDate(new Date());
					finalAssignments.setEffectiveStartDate(commonService.getCurrentDateinUTC());
					finalAssignments.setEffectiveEndDate(existingAssignments.getEffectiveEndDate());
					finalAssignments.setCreatedBy(existingAssignments.getCreatedBy());
					finalAssignments.setCreatedDate(existingAssignments.getCreatedDate());
					finalAssignments.setLastUpdateDate(commonService.getCurrentDateinUTC());

					result = osiAssignmentsRepositoryCustom.updateAssignment(finalAssignments);
					

					// to get the value of employeeType
					//String employeeType = osiAssignmentsRepositoryCustom.findEmployeeById(osiAssignmentsDTO.getEmployeeId());
					//dto.setEmployeeType(employeeType);
					dto.setEmployeeType(finalAssignments.getAssignmentType());
					
					dto = osiAssignmentsMapper.convertOsiAssignmentToDTO(finalAssignments);
				}
			}
			if(!existingAssignments.getLocationId().equals(osiAssignmentsDTO.getLocationId()) || !existingAssignments.getDeptId().equals(osiAssignmentsDTO.getDeptId())
					|| !existingAssignments.getGradeId().equals(osiAssignmentsDTO.getGradeId()) || !existingAssignments.getJobId().equals(osiAssignmentsDTO.getJobId())
					|| !existingAssignments.getSupervisorId().equals(osiAssignmentsDTO.getSupervisorId())){
				OsiWfsActivities wfsActivities = new OsiWfsActivities();
				wfsActivities.setObjectId(dto.getEmployeeId());
				wfsActivities.setObjectName("OSI_EMPLOYEES");
				wfsActivities.setProcessFlag("N");
				Integer wfsId = osiWorkflowsRepository.getWorkFlow(employeeUpdation, osiAssignmentsDTO.getOrgId());
				wfsActivities.setWfsId(wfsId);
				wfsActivities.setOrgId(osiAssignmentsDTO.getOrgId());
				wfsActivities.setStartDate(commonService.getCurrentDateStringinUTC());
				if(osiWorkflowsRepository.verifyExistingWorkflow(wfsId, dto.getEmployeeId(), osiAssignmentsDTO.getOrgId())==0)
					osiWfsActivitiesRepository.save(wfsActivities);
			}
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the Assignments");
		}
		LOGGER.info("updateAssignments : End");

		return dto;
	}
	

	@Transactional
	public List<OsiJobCodesDTO> getAllJobCodes(Integer empId) throws BusinessException {
		List<OsiJobCodesDTO> dto = new ArrayList<OsiJobCodesDTO>();
		LOGGER.info("getAllJobCodes : Begin");
		try {
			//List<OsiEmployees> getEmployee = osiAssignmentsRepositoryCustom.getEmployees(empId);
			List<OsiJobCodes> jobs = osiAssignmentsRepositoryCustom.getAllJobs(empId);
			for (OsiJobCodes job : jobs) {
				OsiJobCodesDTO dtoNew = osiAssignmentsMapper.convertOsiJobCodesToDTO(job);
				dto.add(dtoNew);
			}

		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the job codes");
		}
		LOGGER.info("getAllJobCodes : End");
		return dto;
	}

	@Transactional
	public List<OsiGradesDTO> getAllGrades(Integer empId) throws BusinessException {
		List<OsiGradesDTO> dto = new ArrayList<OsiGradesDTO>();
		LOGGER.info("getAllGrades : Begin");
		try {
		//	List<OsiEmployees> getEmployee = osiAssignmentsRepositoryCustom.getEmployees(empId);
			List<OsiGrades> grades = osiAssignmentsRepositoryCustom.getAllGrades(empId);
			for (OsiGrades grade : grades) {
				OsiGradesDTO dtoNew = osiAssignmentsMapper.convertOsiGradesToDTO(grade);

				dto.add(dtoNew);
			}

		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the grades");
		}
		LOGGER.info("getAllGrades : End");
		return dto;
	}

	@Override
	public List<OsiLocationsDTO> getAllLocations(Integer empId) throws BusinessException {
		List<OsiLocationsDTO> dto = new ArrayList<OsiLocationsDTO>();
		LOGGER.info("getAllLocations : Begin");
		try {
		//	List<OsiEmployees> getEmployee = osiAssignmentsRepositoryCustom.getEmployees(empId);
		//	if(getEmployee!=null && !getEmployee.isEmpty()){
			List<OsiLocations> locations = osiAssignmentsRepositoryCustom.getAllLocations(empId);
			for (OsiLocations location : locations) {
				OsiLocationsDTO dtoNew = osiAssignmentsMapper.convertOsiLocationsToDTO(location);

				dto.add(dtoNew);
		//	}
			}

		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the locations");
		}
		LOGGER.info("getAllLocations : End");
		return dto;
	}

	@Override
	public List<OsiLookupTypesDTO> getAllDepartment() throws BusinessException {
		List<OsiLookupTypesDTO> dto = new ArrayList<OsiLookupTypesDTO>();
		LOGGER.info("getAllDepartment : Begin");
		try {
			List<OsiLookupTypes> lookupTypes = osiAssignmentsRepositoryCustom.getAllDepartment();
			for (OsiLookupTypes lookupType : lookupTypes) {
				OsiLookupTypesDTO dtoNew = osiLookupMapper.osiLookupToOsiLookupDTO(lookupType);
				dto.add(dtoNew);
			}

		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the departments");
		}
		LOGGER.info("getAllDepartment : End");
		return dto;
	}

	@Override
	public List<OsiEmployeesDTO> searchEmployeeByNumber(String empName) throws BusinessException {
		List<OsiEmployeesDTO> dto = new ArrayList<OsiEmployeesDTO>();
		LOGGER.info("searchEmployeeByNumber : Begin");
		try {
			List<OsiEmployees> employees = osiAssignmentsRepositoryCustom.searchEmployeeByNumber(empName);
			if (employees.size() > 0) {
				for(OsiEmployees newEmp : employees){
					dto.add(osiAssignmentsMapper.convertOsiEmployeeToDTO(newEmp));
				}
			}

		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the employee");
		}
		LOGGER.info("searchEmployeeByNumber : End");

		return dto;
	}

	@Override
	public OsiEmployeesDTO searchSupervisorByNumber(String empNumber) throws BusinessException {
		OsiEmployeesDTO dto = new OsiEmployeesDTO();
		LOGGER.info("searchSupervisorByNumber : Begin");
		try {
			SimpleDateFormat convert = new SimpleDateFormat("yyyy-MM-dd");
			Date currentDate = new Date();
			String convertedDate = convert.format(currentDate);
			OsiEmployees employee = osiAssignmentsRepositoryCustom.searchSupervisorByNumber(empNumber, convertedDate);
			if (null != employee) {
				dto = osiAssignmentsMapper.convertOsiEmployeeToDTO(employee);
			}

		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the supervisor");
		}
		LOGGER.info("searchSupervisorByNumber : End");

		return dto;
	}

	@Override
	public OsiEmployeesDTO searchEmployeeById(Integer id) throws BusinessException {
		OsiEmployeesDTO dto = new OsiEmployeesDTO();
		List<OsiEmployeesDTO> list = new ArrayList<OsiEmployeesDTO>();
		LOGGER.info("searchEmployeeById : Begin");
		try {
			List<OsiEmployees> employees = osiAssignmentsRepositoryCustom.searchEmployeeById(id);
			if (employees.size() != 0) {
				for (OsiEmployees employee : employees) {
					list.add(osiAssignmentsMapper.convertOsiEmployeeToDTO(employee));
				}
			}

		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the employee by id "+id);
		}
		LOGGER.info("searchEmployeeById : End");

		return dto;
	}

	@Override
	public OsiJobCodesDTO getJobCodesById(Integer id) throws BusinessException {
		OsiJobCodesDTO dto = new OsiJobCodesDTO();
		LOGGER.info("getJobCodesById : Begin");
		try {
			OsiJobCodes osiJobCodes = osiAssignmentsRepositoryCustom.getJobCodeById(id);
			
				dto = osiAssignmentsMapper.convertOsiJobCodesToDTO(osiJobCodes);

		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the jobs by id "+id);
		}
		LOGGER.info("getJobCodesById : End");

		return dto;
	}

	@Override
	public OsiGradesDTO getGradesById(Integer id) throws BusinessException {
		OsiGradesDTO dto = new OsiGradesDTO();
		LOGGER.info("getGradesById : Begin");
		try {
			OsiGrades osiGrades = osiAssignmentsRepositoryCustom.getGradeByID(id);
			
				dto =  osiAssignmentsMapper.convertOsiGradesToDTO(osiGrades);
			

		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the grades by id "+id);
		}
		LOGGER.info("getGradesById : End");

		return dto;
	}

	@Override
	public OsiLocationsDTO getOsiLocatonById(Integer id) throws BusinessException {
		OsiLocationsDTO dto = new OsiLocationsDTO();
		LOGGER.info("getOsiLocatonById : Begin");
		try {
			OsiLocations osiLocations = osiAssignmentsRepositoryCustom.getOsiLocationById(id);
			dto= osiAssignmentsMapper.convertOsiLocationsToDTO(osiLocations);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the locations by id "+id);
		}
		LOGGER.info("getOsiLocatonById : End");

		return dto;
	}

	@Override
	public OsiAssignmentsDTO findByAssignmentIdAndSerachDate(String inputObject)
			throws BusinessException, DataAccessException {
		OsiAssignmentsDTO dto = new OsiAssignmentsDTO();
		JSONObject inputJson = null;
		LOGGER.info("findByAssignmentIdAndSerachDate : Begin");
		try {
			if(null != inputObject) {
				inputJson = new JSONObject(inputObject);
			}
			if(null != inputJson) {
				// to get the value of employeeType
				//String employeeType = osiAssignmentsRepositoryCustom.findEmployeeById(inputJson.getInt("employeeId"));
				String employeeType = osiAssignmentsRepositoryCustom.findEmployeeByIdAndSearchDate(inputJson.getInt("employeeId"), inputJson.getString("searchDate"));
				dto.setEmployeeType(employeeType);
							
				try {
					OsiAssignments assignmentList = osiAssignmentsRepositoryCustom.findEmployeeByIdAndDate(inputJson.getInt("employeeId"), inputJson.getString("searchDate"));
					
					if (null != assignmentList) {
						dto = osiAssignmentsMapper.convertOsiAssignmentToDTO(assignmentList);
						// to get the value of employeeType
						//employeeType = osiAssignmentsRepositoryCustom.findEmployeeById(inputJson.getInt("employeeId"));
						dto.setEmployeeType(employeeType);
					}
				} catch (DataAccessException e) {
					LOGGER.error("Error Occured : "+e.getSystemMessage());
				//	throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
				}catch (Exception e) {
					LOGGER.error("Error Occured : "+e.getMessage());
				}
				
				// to get supervisor name
				if (dto.getSupervisorId() != null) {
					List<OsiEmployees> searchSupervisor = osiAssignmentsRepositoryCustom
							.searchEmployeeById(dto.getSupervisorId());
					dto.setSupervisorName(searchSupervisor.get(0).getFullName());
				}
				// to get referral name
				if (dto.getReferredById() != null) {
					List<OsiEmployees> searchReferredBy = osiAssignmentsRepositoryCustom
							.searchEmployeeById(dto.getReferredById());
					dto.setReferralName(searchReferredBy.get(0).getFullName());
				}
			}
		}/* catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}*/catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the employee");
		}
		LOGGER.info("findByAssignmentIdAndSerachDate : End");

		return dto;
	}
	
	@Override
	public OsiRollUpsDTO saveEmployeeRollups(OsiRollUpsDTO employeeRollupDto) throws BusinessException {
		OsiRollUpsDTO saveddRollupsDto = null;
		OsiRollUps saveddRollups = null;
		LOGGER.info("saveEmployeeRollups : Begin");
		try {
			if(null != employeeRollupDto) {
				OsiRollUps employeeRollUps = osiRollupsMapper.osiRollupsDtoToOsiRollups(employeeRollupDto);
				OsiRollUps existRollups = osiRollupsRepository.findBySegment1AndSegment2AndSegment3AndSegment4AndSegment5AndSegment6AndSegment7AndSegment8AndSegment9AndSegment10AndOrgId
				(employeeRollUps.getSegment1(), employeeRollUps.getSegment2(), employeeRollUps.getSegment3(), employeeRollUps.getSegment4(), 
						employeeRollUps.getSegment5(), employeeRollUps.getSegment6(), employeeRollUps.getSegment7(), employeeRollUps.getSegment8(), 
						employeeRollUps.getSegment9(), employeeRollUps.getSegment10(), employeeRollUps.getOrgId());
				if(null == existRollups) {
					employeeRollUps.setRollupId(null);
					saveddRollups = osiRollupsRepository.save(employeeRollUps);
				} else {
					saveddRollups = osiRollupsRepository.findOne(existRollups.getRollupId());
				}
				saveddRollupsDto = osiRollupsMapper.osiRollupsToOsiRollupsDTO(saveddRollups);
			}
		}catch (NoResultException e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while saving the employee");
		}
		LOGGER.info("saveEmployeeRollups : End");
		return saveddRollupsDto;
	}
	
	@Override
	public JSONArray loadRollupsPopup(String inputObject) throws BusinessException, DataAccessException {
		JSONArray resultArray = null;
		LOGGER.info("loadRollupsPopup : Begin");
		try {
			JSONObject inputJson = new JSONObject(inputObject);
			Integer segmentStructureDetId = (Integer) inputJson.get("id");
			JSONArray dependentsArray = inputJson.getJSONArray("dependents");
			if(null != segmentStructureDetId) {
				OsiSegmentStructureDetails osiSegmentStructureDetails = osiSegmentStructureDetailsRepository.findOneSegmentStructureDetId(segmentStructureDetId);
				boolean isSql = (osiSegmentStructureDetails.getIsSqlReqdForValidation() == 1 ? true : false);
				if(isSql) {
					String sqlQuery = osiSegmentStructureDetails.getSqlQueryForValidation();
					System.out.println(sqlQuery);
					resultArray =osiAssignmentsRepositoryCustom.executeQuery(sqlQuery, dependentsArray);
					System.out.println(resultArray);
				} else {
					resultArray = new JSONArray();
					OsiLookupTypesDTO result = osiLookupTypesService.findOsiLookupValuesesByLookupName(osiSegmentStructureDetails.getLovDataForValidation());
					for(OsiLookupValuesDTO ldto : result.getOsiLookupValueses()) {
						JSONObject json = new JSONObject();
						json.put("id", ldto.getLookupValue());
						json.put("name", ldto.getLookupDesc());
						
						resultArray.put(json);
					}
				}
			}
		} catch(BusinessException e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getLocalizedMessage());
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getLocalizedMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while loading the look ups for popup");
		}
		LOGGER.info("loadRollupsPopup : End");
		return resultArray;
	}
	@Override
	public OsiRollUpsDTO findRollUpsById(Integer rollUpId) throws BusinessException {
		OsiRollUpsDTO osiRollUpDTO = null;
		LOGGER.info("findRollUpsById : Begin");
		try{
			OsiRollUps existRollups = osiRollupsRepository.findOne(rollUpId);
			osiRollUpDTO = osiRollupsMapper.osiRollupsToOsiRollupsDTO(existRollups);
		}catch (NoResultException e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the look ups");
		}
		LOGGER.info("findRollUpsById : End");
		return osiRollUpDTO;
	}
	
	@Transactional
	public Integer findSuperviosrId(Integer empId) throws BusinessException {
		Integer superviosrId = null;
		LOGGER.info("findSuperviosrId : Begin");
		try {
			if(empId != null){
				// to get the value of employeeType
				superviosrId = osiAssignmentsRepositoryCustom.findSuperviosrId(empId);
			}
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the superviosr");
		}
		LOGGER.info("findSuperviosrId : End");
		return superviosrId;
	}

}
