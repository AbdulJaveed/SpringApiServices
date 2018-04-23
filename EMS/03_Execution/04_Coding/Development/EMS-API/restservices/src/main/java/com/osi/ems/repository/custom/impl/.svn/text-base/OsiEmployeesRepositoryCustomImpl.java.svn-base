package com.osi.ems.repository.custom.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiCountries;
import com.osi.ems.domain.OsiCountryVisas;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.repository.custom.OsiEmployeesRepositoryCustom;
import com.osi.ems.service.dto.OsiOrgAddressesDTO;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.urm.exception.DataAccessException;

@Component
public class OsiEmployeesRepositoryCustomImpl implements OsiEmployeesRepositoryCustom {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CommonService commonService;
	
	@Value("${GLOBAL_ORG}")
	private String globalOrg;
	
	@Value("${IND_ORGS}")
	private String indOrgs;
	
	@Value("${US_ORGS}")
	private String usOrgs;
	
	public List<OsiEmployees> getAllEmployees() {
		return null;
	}

	@Override
	public Integer saveEmployeeInfo(OsiEmployees osiEmployees) throws DataAccessException {
		try {
			LOGGER.info("saveEmployeeInfo : Begin");
			Query query = entityManager.createNativeQuery("insert into osi_employees(employee_id,employee_number,first_name, last_name, middle_name,full_name,title, suffix,prefix"
					+ ",employee_type,applicant_number,date_of_birth, start_date,effective_start_date,effective_end_date"
					+ ",org_id,blood_type,background_check_status,background_date_check,correspondence_language,office_email,personal_email"
					+ ",expense_check_send_to_address_id,fte_capacity, hold_applicant_date_until,mail_stop,known_as"
					+ ",last_medical_test_date, last_medical_test_by, nationality, marital_status,national_identifier,on_military_service,previous_last_name"
					+ ",rehire_reason, rehire_recommendation,resume_exists,resume_last_updated,resume_id,second_passport_exists,gender, work_schedule_id"
					+ ",receipt_of_death_cert_date,uses_tobacco_flag,photo_id,date_of_death,original_date_of_hire,passport_number,passport_date_of_issue," 
					+ "passport_date_of_expiry,passport_issuance_authority,passport_place_of_issue,mail_address_id,permanent_address_id, version"
					+ ",Attribute1,Attribute2, Attribute3, Attribute4, Attribute5, Attribute6, Attribute7, Attribute8, Attribute9, Attribute10,Attribute11,Attribute12"
					+",Attribute13,Attribute14,Attribute15,Attribute16,Attribute17, Attribute18, Attribute19, Attribute20, Attribute21,Attribute22, Attribute23,Attribute24, Attribute25"
					+ ",created_by, creation_date, last_updated_by,last_update_date,attachment_id, termination_date, total_exp, user_name, system_type, serial_number, employee_status ) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
					+ ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				  
				   query.setParameter(1, osiEmployees.getEmployeeId());
				   query.setParameter(2, osiEmployees.getEmployeeNumber());
				   query.setParameter(3, osiEmployees.getFirstName());
				   query.setParameter(4, osiEmployees.getLastName());
				   query.setParameter(5, osiEmployees.getMiddleName());
				   
				   query.setParameter(6, osiEmployees.getFullName());
				   query.setParameter(7, osiEmployees.getTitle());
				   query.setParameter(8, osiEmployees.getSuffix());
				   query.setParameter(9, osiEmployees.getPrefix());
				   query.setParameter(10, osiEmployees.getEmployeeType());
				  
				   query.setParameter(11, osiEmployees.getApplicantNumber());
				   query.setParameter(12, osiEmployees.getDateOfBirth());
				   query.setParameter(13, osiEmployees.getStartDate());
				   query.setParameter(14, osiEmployees.getEffectiveStartDate());
				   query.setParameter(15, osiEmployees.getEffectiveEndDate());
				   
				   query.setParameter(16, osiEmployees.getOrgId());
				   query.setParameter(17, osiEmployees.getBloodType());
				   query.setParameter(18, osiEmployees.getBackgroundCheckStatus());
				   query.setParameter(19, osiEmployees.getBackgroundDateCheck());
				   query.setParameter(20, osiEmployees.getCorrespondenceLanguage());
				   query.setParameter(21, osiEmployees.getOfficeEmail());
				   query.setParameter(22, osiEmployees.getPersonalEmail());
				   query.setParameter(23, osiEmployees.getExpenseCheckSendToAddressId());
				   query.setParameter(24, osiEmployees.getFteCapacity());
				   query.setParameter(25, osiEmployees.getHoldApplicantDateUntil());
				   query.setParameter(26, osiEmployees.getMailStop());
				   query.setParameter(27, osiEmployees.getKnownAs());
				   query.setParameter(28, osiEmployees.getLastMedicalTestDate());
				   query.setParameter(29, osiEmployees.getLastMedicalTestBy());
				   query.setParameter(30, osiEmployees.getNationality());
				   query.setParameter(31, osiEmployees.getMaritalStatus());
				   query.setParameter(32, osiEmployees.getNationalIdentifier());
				   query.setParameter(33, osiEmployees.getOnMilitaryService());
				   query.setParameter(34, osiEmployees.getPreviousLastName());
				   query.setParameter(35, osiEmployees.getRehireReason());
				   query.setParameter(36, osiEmployees.getRehireRecommendation());
				   query.setParameter(37, osiEmployees.getResumeExists());
				   query.setParameter(38, osiEmployees.getResumeLastUpdated());
				   query.setParameter(39, osiEmployees.getResumeId());
				   
				   query.setParameter(40, osiEmployees.getSecondPassportExists());
				   query.setParameter(41, osiEmployees.getGender());
				   query.setParameter(42, osiEmployees.getWorkScheduleId());
				   query.setParameter(43, osiEmployees.getReceiptOfDeathCertDate());
				   query.setParameter(44, osiEmployees.getUsesTobaccoFlag());
				   query.setParameter(45, osiEmployees.getPhotoId());
				   query.setParameter(46, osiEmployees.getDateOfDeath());
				   query.setParameter(47, osiEmployees.getOriginalDateOfHire());				   
				   query.setParameter(48, osiEmployees.getPassportNumber());
				   query.setParameter(49, osiEmployees.getPassportDateOfIssue());
				   query.setParameter(50, osiEmployees.getPassportDateOfExpiry());
				   query.setParameter(51, osiEmployees.getPassportIssuanceAuthority());
				   query.setParameter(52, osiEmployees.getPassportPlaceOfIssue());
				   query.setParameter(53, osiEmployees.getMailAddressId());
				   query.setParameter(54, osiEmployees.getPermanentAddressId());
				   query.setParameter(55, osiEmployees.getVersion());
				   query.setParameter(56, osiEmployees.getAttribute1());
				   query.setParameter(57, osiEmployees.getAttribute2());
				   query.setParameter(58, osiEmployees.getAttribute3());
				   query.setParameter(59, osiEmployees.getAttribute4());
				   query.setParameter(60, osiEmployees.getAttribute5());
				   query.setParameter(61, osiEmployees.getAttribute6());
				   query.setParameter(62, osiEmployees.getAttribute7());
				   query.setParameter(63, osiEmployees.getAttribute8());
				   query.setParameter(64, osiEmployees.getAttribute9());
				   query.setParameter(65, osiEmployees.getAttribute10());
				   query.setParameter(66, osiEmployees.getAttribute11());
				   query.setParameter(67, osiEmployees.getAttribute12());
				   query.setParameter(68, osiEmployees.getAttribute13());
				   query.setParameter(69, osiEmployees.getAttribute14());
				   query.setParameter(70, osiEmployees.getAttribute15());
				   query.setParameter(71, osiEmployees.getAttribute16());
				   query.setParameter(72, osiEmployees.getAttribute17());
				   query.setParameter(73, osiEmployees.getAttribute18());
				   query.setParameter(74, osiEmployees.getAttribute19());
				   query.setParameter(75, osiEmployees.getAttribute20());
				   query.setParameter(76, osiEmployees.getAttribute21());
				   query.setParameter(77, osiEmployees.getAttribute22());
				   query.setParameter(78, osiEmployees.getAttribute23());
				   query.setParameter(79, osiEmployees.getAttribute24());
				   query.setParameter(80, osiEmployees.getAttribute25());
				   query.setParameter(81, osiEmployees.getCreatedBy());
				   query.setParameter(82, osiEmployees.getCreationDate());
				   query.setParameter(83, osiEmployees.getLastUpdatedBy());
				   query.setParameter(84, osiEmployees.getLastUpdateDate());
				   query.setParameter(85, osiEmployees.getAttachmentId());
				   query.setParameter(86, osiEmployees.getTerminationDate());
				   query.setParameter(87, osiEmployees.getTotalExp());
				   query.setParameter(88, osiEmployees.getUserName());
				   
				   query.setParameter(89, osiEmployees.getSystemType());
				   query.setParameter(90, osiEmployees.getSerialNumber());
				   query.setParameter(91, osiEmployees.getEmployeeStatus());
				   
				   query.executeUpdate();
				   
				   LOGGER.info("saveEmployeeInfo : End");
		} catch (Exception e) {
			LOGGER.error("Exception: "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		return osiEmployees.getEmployeeId();
	}
	
	@Override
	public OsiEmployees getEmployees(int employeeId) throws DataAccessException {
		OsiEmployees osiEmployees = null;
		try {
			LOGGER.info("getEmployees : Begin");
			String getEmployeeQuery = "select employee_id,employee_number,first_name, last_name, middle_name,full_name,title, suffix,prefix "
					+ ",employee_type,applicant_number,date_of_birth, start_date,effective_start_date,effective_end_date"
					+ ",org_id,blood_type,background_check_status,background_date_check,correspondence_language,office_email,personal_email"
					+ ",expense_check_send_to_address_id,fte_capacity, hold_applicant_date_until,mail_stop,known_as"
					+ ",last_medical_test_date, last_medical_test_by, nationality, marital_status,national_identifier,on_military_service,previous_last_name"
					+ ",rehire_reason, rehire_recommendation,resume_exists,resume_last_updated,resume_id,second_passport_exists,gender, work_schedule_id"
					+ ",receipt_of_death_cert_date,uses_tobacco_flag,photo_id,date_of_death,original_date_of_hire,passport_number,passport_date_of_issue," 
					+ "passport_date_of_expiry,passport_issuance_authority,passport_place_of_issue,mail_address_id,permanent_address_id, version"
					+ ",Attribute1,Attribute2, Attribute3, Attribute4, Attribute5, Attribute6, Attribute7, Attribute8, Attribute9, Attribute10,Attribute11,Attribute12"
					+",Attribute13,Attribute14,Attribute15,Attribute16,Attribute17, Attribute18, Attribute19, Attribute20, Attribute21,Attribute22, Attribute23,Attribute24, Attribute25"
					+ ",created_by, creation_date, last_updated_by,last_update_date,attachment_id, termination_date, user_name, system_type, serial_number, total_exp, employee_status "
					+ " from osi_employees e " 
					+ " where "
					+ " e.employee_id = ? order by effective_end_date desc limit 1 " ;
				//	+ " and sysdate() between e.effective_start_date "
				//	+ " and e.effective_end_date";
			
			Object[] object = (Object[]) this.entityManager.createNativeQuery(getEmployeeQuery)
			.setParameter(1, employeeId).getSingleResult();
			
			osiEmployees = new OsiEmployees();
			osiEmployees.setEmployeeId(object[0]!= null ? Integer.parseInt(object[0].toString()) : null);
			osiEmployees.setEmployeeNumber(object[1]!= null ? object[1].toString() : null);
			osiEmployees.setFirstName(object[2]!= null ? object[2].toString() : null);
			osiEmployees.setLastName(object[3]!= null ? object[3].toString() : null);
			osiEmployees.setMiddleName(object[4]!= null ? object[4].toString(): null);
			osiEmployees.setFullName(object[5]!= null ? object[5].toString() : null);
			osiEmployees.setTitle(object[6]!= null ? object[6].toString() : null);
			osiEmployees.setSuffix(object[7]!=null ? object[7].toString() : null);
			osiEmployees.setPrefix(object[8]!= null ? object[8].toString() : null);
			osiEmployees.setEmployeeType(object[9]!= null ? object[9].toString() : null);
			osiEmployees.setApplicantNumber(object[10]!= null ? object[10].toString() : null);
			osiEmployees.setDateOfBirth(object[11]!= null ? object[11].toString() : null);
			osiEmployees.setStartDate(object[12]!= null ? object[12].toString():null);
			osiEmployees.setEffectiveStartDate(object[13]!= null ? convertTimestampToString((Timestamp)object[13]) : null);
			osiEmployees.setEffectiveEndDate(object[14]!= null ? convertTimestampToString((Timestamp)object[14]): null);
			osiEmployees.setOrgId(object[15]!= null ? Integer.parseInt(object[15].toString()) : null);
			osiEmployees.setBloodType(object[16]!= null ? object[16].toString(): null);
			osiEmployees.setBackgroundCheckStatus(object[17]!= null ? Integer.parseInt(object[17].toString()): null);
			osiEmployees.setBackgroundDateCheck(object[18]!= null ? object[18].toString(): null);
			osiEmployees.setCorrespondenceLanguage(object[19]!= null ? object[19].toString(): null);
			osiEmployees.setOfficeEmail(object[20]!= null ? object[20].toString(): null);
			osiEmployees.setPersonalEmail(object[21]!= null ? object[21].toString(): null);
			osiEmployees.setExpenseCheckSendToAddressId(object[22]!= null ? Integer.parseInt(object[22].toString()): null);
			osiEmployees.setFteCapacity(object[23]!= null ? Integer.parseInt(object[23].toString()): null);
			osiEmployees.setHoldApplicantDateUntil(object[24]!= null ? object[24].toString(): null);
			osiEmployees.setMailStop(object[25]!= null ? object[25].toString(): null);
			osiEmployees.setKnownAs(object[26]!= null ? object[26].toString(): null);
			
			osiEmployees.setLastMedicalTestDate(object[27]!= null ? object[27].toString(): null);
			
			osiEmployees.setLastMedicalTestBy(object[28]!= null ? object[28].toString(): null);
			osiEmployees.setNationality(object[29]!= null ? object[29].toString(): null);
			osiEmployees.setMaritalStatus(object[30]!= null ? object[30].toString(): null);
			osiEmployees.setNationalIdentifier(object[31]!= null ? object[31].toString(): null);
			osiEmployees.setOnMilitaryService(object[32]!= null ? Integer.parseInt(object[32].toString()): null);
			osiEmployees.setPreviousLastName(object[33]!= null ? object[33].toString(): null);
			osiEmployees.setRehireReason(object[34]!= null ? object[34].toString(): null);
			osiEmployees.setRehireRecommendation(object[35]!= null ? object[35].toString(): null);
			osiEmployees.setResumeExists(object[36]!= null ? Integer.parseInt(object[36].toString()): null);
			osiEmployees.setResumeLastUpdated(object[37]!= null ? object[37].toString(): null);
			osiEmployees.setResumeId(object[38]!= null ? Integer.parseInt(object[38].toString()): null);
			osiEmployees.setSecondPassportExists(object[39]!= null ? Integer.parseInt(object[39].toString()): null);
			osiEmployees.setGender(object[40]!= null ? object[40].toString(): null);
			osiEmployees.setWorkScheduleId(object[41]!= null ? Integer.parseInt(object[41].toString()): null);
			osiEmployees.setReceiptOfDeathCertDate(object[42]!= null ? object[42].toString(): null);
			osiEmployees.setUsesTobaccoFlag(object[43]!= null ? Integer.parseInt(object[43].toString()): null);
			osiEmployees.setPhotoId(object[44]!= null ? Integer.parseInt(object[44].toString()): null);
			osiEmployees.setDateOfDeath(object[45]!= null ? object[45].toString(): null);
			osiEmployees.setOriginalDateOfHire(object[46]!= null ? object[46].toString(): null);
			osiEmployees.setPassportNumber(object[47]!= null ? object[47].toString(): null);
			osiEmployees.setPassportDateOfIssue(object[48]!= null ? object[48].toString(): null);
			osiEmployees.setPassportDateOfExpiry(object[49]!= null ? object[49].toString(): null);
			osiEmployees.setPassportIssuanceAuthority(object[50]!= null ? object[50].toString(): null);
			osiEmployees.setPassportPlaceOfIssue(object[51]!= null ? object[51].toString(): null);
			osiEmployees.setMailAddressId(object[52]!= null ? Integer.parseInt(object[52].toString()): null);
			osiEmployees.setPermanentAddressId(object[53]!= null ? Integer.parseInt(object[53].toString()): null);
			osiEmployees.setVersion(object[54]!= null ? Integer.parseInt(object[54].toString()): null);
			osiEmployees.setAttribute1(object[55]!= null ? object[55].toString(): null);
			osiEmployees.setAttribute2(object[56]!= null ? object[56].toString(): null);
			osiEmployees.setAttribute3(object[57]!= null ? object[57].toString(): null);
			osiEmployees.setAttribute4(object[58]!= null ? object[58].toString(): null);
			osiEmployees.setAttribute5(object[59]!= null ? object[59].toString(): null);
			osiEmployees.setAttribute6(object[60]!= null ? object[60].toString(): null);
			osiEmployees.setAttribute7(object[61]!= null ? object[61].toString(): null);
			osiEmployees.setAttribute8(object[62]!= null ? object[62].toString(): null);
			osiEmployees.setAttribute9(object[63]!= null ? object[63].toString(): null);
			osiEmployees.setAttribute10(object[64]!= null ? object[64].toString(): null);
			osiEmployees.setAttribute11(object[65]!= null ? object[65].toString(): null);
			osiEmployees.setAttribute12(object[66]!= null ? object[66].toString(): null);
			osiEmployees.setAttribute13(object[67]!= null ? object[67].toString(): null);
			osiEmployees.setAttribute14(object[68]!= null ? object[68].toString(): null);
			osiEmployees.setAttribute15(object[69]!= null ? object[69].toString(): null);
			osiEmployees.setAttribute16(object[70]!= null ? object[70].toString(): null);
			osiEmployees.setAttribute17(object[71]!= null ? object[71].toString(): null);
			osiEmployees.setAttribute18(object[72]!= null ? object[72].toString(): null);
			osiEmployees.setAttribute19(object[73]!= null ? object[73].toString(): null);
			osiEmployees.setAttribute20(object[74]!= null ? object[74].toString(): null);
			osiEmployees.setAttribute21(object[75]!= null ? object[75].toString(): null);
			osiEmployees.setAttribute22(object[76]!= null ? object[76].toString(): null);
			osiEmployees.setAttribute23(object[77]!= null ? object[77].toString(): null);
			osiEmployees.setAttribute24(object[78]!= null ? object[78].toString(): null);
			osiEmployees.setAttribute25(object[79]!= null ? object[79].toString(): null);
			osiEmployees.setCreatedBy(object[80]!= null ? Integer.parseInt(object[80].toString()) : null);
			osiEmployees.setCreationDate(object[81]!= null ? object[81].toString(): null);
			osiEmployees.setLastUpdatedBy(object[82]!= null ? Integer.parseInt(object[82].toString()): null);
			osiEmployees.setLastUpdateDate(object[83]!= null ? object[83].toString(): null);
			osiEmployees.setAttachmentId(object[84]!= null ? Integer.parseInt(object[84].toString()): null);
			osiEmployees.setTerminationDate(object[85]!= null ? convertTimestampToString((Timestamp)object[85]) : null);
			
			osiEmployees.setUserName(object[86]!= null ? object[86].toString(): null);
			osiEmployees.setSystemType(object[87]!= null ? object[87].toString(): null);
			osiEmployees.setSerialNumber(object[88]!= null ? object[88].toString(): null);
			osiEmployees.setTotalExp(object[89]!= null ? Double.parseDouble(object[89].toString()): null);
			osiEmployees.setEmployeeStatus(object[90]!= null ? Integer.parseInt(object[90].toString()) : null);
			
			LOGGER.info("getEmployees : End");
		} catch(NoResultException ne) {
			
			String empCount = "select count(*) from osi_employees e where e.employee_id = ? and e.employee_type = 'EX Employee';";
			BigInteger count = (BigInteger) this.entityManager.createNativeQuery(empCount)
					.setParameter(1, employeeId).getSingleResult();
			if(count.intValue() >= 1) {
				try {
					String exEmpRetrieve = "select employee_id,employee_number,first_name, last_name, middle_name,full_name,title, suffix,prefix "
							+ ",employee_type,applicant_number,date_of_birth, start_date,effective_start_date,effective_end_date"
							+ ",org_id,blood_type,background_check_status,background_date_check,correspondence_language,office_email,personal_email"
							+ ",expense_check_send_to_address_id,fte_capacity, hold_applicant_date_until,mail_stop,known_as"
							+ ",last_medical_test_date, last_medical_test_by, nationality, marital_status,national_identifier,on_military_service,previous_last_name"
							+ ",rehire_reason, rehire_recommendation,resume_exists,resume_last_updated,resume_id,second_passport_exists,gender, work_schedule_id"
							+ ",receipt_of_death_cert_date,uses_tobacco_flag,photo_id,date_of_death,original_date_of_hire,passport_number,passport_date_of_issue," 
							+ "passport_date_of_expiry,passport_issuance_authority,passport_place_of_issue,mail_address_id,permanent_address_id, version"
							+ ",Attribute1,Attribute2, Attribute3, Attribute4, Attribute5, Attribute6, Attribute7, Attribute8, Attribute9, Attribute10,Attribute11,Attribute12"
							+",Attribute13,Attribute14,Attribute15,Attribute16,Attribute17, Attribute18, Attribute19, Attribute20, Attribute21,Attribute22, Attribute23,Attribute24, Attribute25"
							+ ",created_by, creation_date, last_updated_by,last_update_date,attachment_id, termination_date, user_name, system_type, serial_number, total_exp, employee_status "
							+ " from osi_employees e " 
							+ " where "
							+ " e.employee_id = ? "  
							+ " and e.employee_type = 'EX Employee' "
							+ " and e.effective_end_date = (select max(e1.effective_end_date) from osi_employees e1 where e1.employee_id = ? and e1.employee_type = 'EX EMPLOYEE')";
					
					Object[] object = (Object[]) this.entityManager.createNativeQuery(exEmpRetrieve)
					.setParameter(1, employeeId)
					.setParameter(2, employeeId)
					.getSingleResult();
					
					osiEmployees = new OsiEmployees();
					osiEmployees.setEmployeeId(object[0]!= null ? Integer.parseInt(object[0].toString()) : null);
					osiEmployees.setEmployeeNumber(object[1]!= null ? object[1].toString() : null);
					osiEmployees.setFirstName(object[2]!= null ? object[2].toString() : null);
					osiEmployees.setLastName(object[3]!= null ? object[3].toString() : null);
					osiEmployees.setMiddleName(object[4]!= null ? object[4].toString(): null);
					osiEmployees.setFullName(object[5]!= null ? object[5].toString() : null);
					osiEmployees.setTitle(object[6]!= null ? object[6].toString() : null);
					osiEmployees.setSuffix(object[7]!=null ? object[7].toString() : null);
					osiEmployees.setPrefix(object[8]!= null ? object[8].toString() : null);
					osiEmployees.setEmployeeType(object[9]!= null ? object[9].toString() : null);
					osiEmployees.setApplicantNumber(object[10]!= null ? object[10].toString() : null);
					osiEmployees.setDateOfBirth(object[11]!= null ? object[11].toString() : null);
					osiEmployees.setStartDate(object[12]!= null ? object[12].toString():null);
					osiEmployees.setEffectiveStartDate(object[13]!= null ? convertTimestampToString((Timestamp)object[13]) : null);
					osiEmployees.setEffectiveEndDate(object[14]!= null ? convertTimestampToString((Timestamp)object[14]): null);
					osiEmployees.setOrgId(object[15]!= null ? Integer.parseInt(object[15].toString()) : null);
					osiEmployees.setBloodType(object[16]!= null ? object[16].toString(): null);
					osiEmployees.setBackgroundCheckStatus(object[17]!= null ? Integer.parseInt(object[17].toString()): null);
					osiEmployees.setBackgroundDateCheck(object[18]!= null ? object[18].toString(): null);
					osiEmployees.setCorrespondenceLanguage(object[19]!= null ? object[19].toString(): null);
					osiEmployees.setOfficeEmail(object[20]!= null ? object[20].toString(): null);
					osiEmployees.setPersonalEmail(object[21]!= null ? object[21].toString(): null);
					osiEmployees.setExpenseCheckSendToAddressId(object[22]!= null ? Integer.parseInt(object[22].toString()): null);
					osiEmployees.setFteCapacity(object[23]!= null ? Integer.parseInt(object[23].toString()): null);
					osiEmployees.setHoldApplicantDateUntil(object[24]!= null ? object[24].toString(): null);
					osiEmployees.setMailStop(object[25]!= null ? object[25].toString(): null);
					osiEmployees.setKnownAs(object[26]!= null ? object[26].toString(): null);
					
					osiEmployees.setLastMedicalTestDate(object[27]!= null ? object[27].toString(): null);
					
					osiEmployees.setLastMedicalTestBy(object[28]!= null ? object[28].toString(): null);
					osiEmployees.setNationality(object[29]!= null ? object[29].toString(): null);
					osiEmployees.setMaritalStatus(object[30]!= null ? object[30].toString(): null);
					osiEmployees.setNationalIdentifier(object[31]!= null ? object[31].toString(): null);
					osiEmployees.setOnMilitaryService(object[32]!= null ? Integer.parseInt(object[32].toString()): null);
					osiEmployees.setPreviousLastName(object[33]!= null ? object[33].toString(): null);
					osiEmployees.setRehireReason(object[34]!= null ? object[34].toString(): null);
					osiEmployees.setRehireRecommendation(object[35]!= null ? object[35].toString(): null);
					osiEmployees.setResumeExists(object[36]!= null ? Integer.parseInt(object[36].toString()): null);
					osiEmployees.setResumeLastUpdated(object[37]!= null ? object[37].toString(): null);
					osiEmployees.setResumeId(object[38]!= null ? Integer.parseInt(object[38].toString()): null);
					osiEmployees.setSecondPassportExists(object[39]!= null ? Integer.parseInt(object[39].toString()): null);
					osiEmployees.setGender(object[40]!= null ? object[40].toString(): null);
					osiEmployees.setWorkScheduleId(object[41]!= null ? Integer.parseInt(object[41].toString()): null);
					osiEmployees.setReceiptOfDeathCertDate(object[42]!= null ? object[42].toString(): null);
					osiEmployees.setUsesTobaccoFlag(object[43]!= null ? Integer.parseInt(object[43].toString()): null);
					osiEmployees.setPhotoId(object[44]!= null ? Integer.parseInt(object[44].toString()): null);
					osiEmployees.setDateOfDeath(object[45]!= null ? object[45].toString(): null);
					osiEmployees.setOriginalDateOfHire(object[46]!= null ? object[46].toString(): null);
					osiEmployees.setPassportNumber(object[47]!= null ? object[47].toString(): null);
					osiEmployees.setPassportDateOfIssue(object[48]!= null ? object[48].toString(): null);
					osiEmployees.setPassportDateOfExpiry(object[49]!= null ? object[49].toString(): null);
					osiEmployees.setPassportIssuanceAuthority(object[50]!= null ? object[50].toString(): null);
					osiEmployees.setPassportPlaceOfIssue(object[51]!= null ? object[51].toString(): null);
					osiEmployees.setMailAddressId(object[52]!= null ? Integer.parseInt(object[52].toString()): null);
					osiEmployees.setPermanentAddressId(object[53]!= null ? Integer.parseInt(object[53].toString()): null);
					osiEmployees.setVersion(object[54]!= null ? Integer.parseInt(object[54].toString()): null);
					osiEmployees.setAttribute1(object[55]!= null ? object[55].toString(): null);
					osiEmployees.setAttribute2(object[56]!= null ? object[56].toString(): null);
					osiEmployees.setAttribute3(object[57]!= null ? object[57].toString(): null);
					osiEmployees.setAttribute4(object[58]!= null ? object[58].toString(): null);
					osiEmployees.setAttribute5(object[59]!= null ? object[59].toString(): null);
					osiEmployees.setAttribute6(object[60]!= null ? object[60].toString(): null);
					osiEmployees.setAttribute7(object[61]!= null ? object[61].toString(): null);
					osiEmployees.setAttribute8(object[62]!= null ? object[62].toString(): null);
					osiEmployees.setAttribute9(object[63]!= null ? object[63].toString(): null);
					osiEmployees.setAttribute10(object[64]!= null ? object[64].toString(): null);
					osiEmployees.setAttribute11(object[65]!= null ? object[65].toString(): null);
					osiEmployees.setAttribute12(object[66]!= null ? object[66].toString(): null);
					osiEmployees.setAttribute13(object[67]!= null ? object[67].toString(): null);
					osiEmployees.setAttribute14(object[68]!= null ? object[68].toString(): null);
					osiEmployees.setAttribute15(object[69]!= null ? object[69].toString(): null);
					osiEmployees.setAttribute16(object[70]!= null ? object[70].toString(): null);
					osiEmployees.setAttribute17(object[71]!= null ? object[71].toString(): null);
					osiEmployees.setAttribute18(object[72]!= null ? object[72].toString(): null);
					osiEmployees.setAttribute19(object[73]!= null ? object[73].toString(): null);
					osiEmployees.setAttribute20(object[74]!= null ? object[74].toString(): null);
					osiEmployees.setAttribute21(object[75]!= null ? object[75].toString(): null);
					osiEmployees.setAttribute22(object[76]!= null ? object[76].toString(): null);
					osiEmployees.setAttribute23(object[77]!= null ? object[77].toString(): null);
					osiEmployees.setAttribute24(object[78]!= null ? object[78].toString(): null);
					osiEmployees.setAttribute25(object[79]!= null ? object[79].toString(): null);
					osiEmployees.setCreatedBy(object[80]!= null ? Integer.parseInt(object[80].toString()) : null);
					osiEmployees.setCreationDate(object[81]!= null ? object[81].toString(): null);
					osiEmployees.setLastUpdatedBy(object[82]!= null ? Integer.parseInt(object[82].toString()): null);
					osiEmployees.setLastUpdateDate(object[83]!= null ? object[83].toString(): null);
					osiEmployees.setAttachmentId(object[84]!= null ? Integer.parseInt(object[84].toString()): null);
					osiEmployees.setTerminationDate(object[85]!= null ? convertTimestampToString((Timestamp)object[85]) : null);
										
					osiEmployees.setUserName(object[86]!= null ? object[86].toString(): null);
					osiEmployees.setSystemType(object[87]!= null ? object[87].toString(): null);
					osiEmployees.setSerialNumber(object[88]!= null ? object[88].toString(): null);
					osiEmployees.setTotalExp(object[89]!= null ? Double.parseDouble(object[89].toString()): null);
					osiEmployees.setEmployeeStatus(object[90]!= null ? Integer.parseInt(object[90].toString()) : null);
					
					LOGGER.info("getEmployees : End");
				} catch(NoResultException e) {
					LOGGER.error("Exception : "+e.getMessage());
					throw new DataAccessException("ERR_1002", "No Records Found");
				} catch (Exception e) {
					LOGGER.error("Exception : "+e.getMessage());
					throw new DataAccessException("ERR_1000", "Exception occured while executing");
				}
			} else {
				LOGGER.info("No Ex-Employee found");
				throw new DataAccessException("ERR_1002", "No Records found with Ex-Employee");
			}
		}catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		return osiEmployees;
	}

	@Override
	public Integer updateEmployeeEffectiveEndDate(OsiEmployees osiEmployees, String effectiveEndDate) throws DataAccessException {
		Integer result;
		try {
			LOGGER.info("updateEmployeeEffectiveEndDate : Begin");
			String updateQuery = "update osi_employees e set "
					+ " e.effective_end_date = ?, e.last_update_date = ? where "
					+ " e.employee_id = ? and "
					+ " ? between e.effective_start_date and e.effective_end_date";
			result = this.entityManager.createNativeQuery(updateQuery)
					.setParameter(1, effectiveEndDate)
					.setParameter(2, commonService.getCurrentDateStringinUTC())
					.setParameter(3, osiEmployees.getEmployeeId())
					.setParameter(4, new java.sql.Timestamp(new Date().getTime()))
					.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("updateEmployeeEffectiveEndDate : End");
		return result;
	}
	
	@Override
	public Integer getMaxEmployeeId() throws DataAccessException {
		Integer employeeId = null;
		try {
			LOGGER.info("getMaxEmployeeId : Begin");
			String query = "select max(employee_id) from osi_employees";
			employeeId = (Integer) this.entityManager.createNativeQuery(query).getSingleResult();
			
		} catch (NoResultException ne) {
			LOGGER.error("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "Exception occured in getting the Max employee id ");
		} catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getMaxEmployeeId : End");
		return employeeId;
	}
	
	@Override
	public String currentTimeStamp(int addSeconds) throws DataAccessException {
		Timestamp finalTimestamp = null;
		LOGGER.info("currentTimeStamp : Begin");
		try {
			long currentDate = System.currentTimeMillis();
	              
	        Timestamp currentTimestamp = new Timestamp(currentDate);
	        Calendar cal = Calendar.getInstance();
	        cal.setTimeInMillis(currentTimestamp.getTime());
	        cal.add(Calendar.SECOND, addSeconds);
	        finalTimestamp = new Timestamp(cal.getTime().getTime());
		} catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("currentTimeStamp : End");
        return convertTimestampToString(finalTimestamp);
	}

	@Override
	public String convertToTimestamp(String date) throws DataAccessException {
		Timestamp timestamp = null;
		SimpleDateFormat dateFormat = null;
		String dateString = null;
		LOGGER.info("convertToTimestamp : Begin");
		try {
			if(date != null && date!="") {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(date);
				timestamp = new java.sql.Timestamp(parsedDate.getTime());
				dateString = timestamp.toString();
			} else {
				return null;
			}
		} catch(Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("convertToTimestamp : End");
		return dateString;
	}
	
	@Override
	public String convertTimestampToString(Timestamp timestamp) throws DataAccessException {
		String timestampString = null;
		SimpleDateFormat dateFormat = null;
		LOGGER.info("convertTimestampToString : Begin");
		try {
			if(timestamp != null) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				timestampString = dateFormat.format(timestamp);
			}
		} catch(Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("convertTimestampToString : End");
		return timestampString;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiEmployees> searchEmployees(String searchData) throws DataAccessException {
		List<OsiEmployees> employeesList = null;
		LOGGER.info("searchEmployees : Begin");
		String orgCode = "";
		try {
			String searchQuery = "select e.employee_id,e.employee_number,e.first_name,e.last_name,e.full_name,e.office_email,e.mail_stop,e.org_id, o.org_name "
					+ " from osi_employees e, osi_organizations o where o.org_id = e.org_id  and ";
			StringBuilder sb = new StringBuilder(searchQuery);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String time  = dtf.format(now);   
			JSONObject searchObject = new JSONObject(searchData);
			if(searchObject.has("orgCode")) {
				if(indOrgs.indexOf(searchObject.getString("orgCode"))>-1){
					String[] indOrg = indOrgs.split(",");
					for (String string : indOrg) {
						orgCode = orgCode+"'"+string.trim()+"',";
					}
					orgCode = orgCode.substring(0, orgCode.length()-1);
				}else if(usOrgs.indexOf(searchObject.getString("orgCode"))>-1){
					String[] indOrg = usOrgs.split(",");
					for (String string : indOrg) {
						orgCode = orgCode+"'"+string.trim()+"',";
					}
					orgCode = orgCode.substring(0, orgCode.length()-1);
				}else{
					orgCode = "'"+searchObject.getString("orgCode")+"'";
				}
				sb.append("(case when ?=? then 1=1 else o.org_short_name in ("+orgCode+") end) and ");
			}
			/*String searchQuery = "select e.employee_id,e.employee_number,e.first_name,e.last_name,e.full_name,e.office_email,e.mail_stop,e.org_id, o.org_name "
					+ " from osi_employees e, osi_organizations o "
					+ " where o.org_id = e.org_id  and (case when ?=? then 1=1 else o.org_short_name in ("+orgCode+") end) and ";*/
			
			if(searchObject.has("searchDate"))
				sb.append("'"+searchObject.getString("searchDate")+" "+time +"' between e.effective_start_date and e.effective_end_date ");
			if(searchObject.has("employeeNumber"))
				sb.append("and e.employee_number like '%"+searchObject.getString("employeeNumber")+"%' ");
			if(searchObject.has("firstName"))
				sb.append(" and e.first_name like '%"+searchObject.getString("firstName")+"%' ");
			if(searchObject.has("lastName"))
				sb.append(" and e.last_name like '%"+searchObject.getString("lastName")+"%' ");
			if(searchObject.has("officeEmail"))
				sb.append(" and e.office_email like '%"+searchObject.getString("officeEmail")+"%' ");
			if(searchObject.has("employeeId"))
				sb.append(" and e.employee_id !="+Integer.parseInt(searchObject.getString("employeeId")));
			Query searchEmpQuery = this.entityManager.createNativeQuery(sb.toString());
			//List<Object[]> employeesListObj = this.entityManager.createNativeQuery(sb.toString());
					if(searchObject.has("orgCode")) {
						searchEmpQuery.setParameter(1, globalOrg);
						searchEmpQuery.setParameter(2, searchObject.getString("orgCode"));
					}
				//	.setParameter(3, orgCode)
			List<Object[]> employeesListObj = searchEmpQuery.getResultList();
			if(employeesListObj.size() != 0)
				employeesList = new ArrayList<OsiEmployees>();
			for(Object[] employeeObj : employeesListObj) {
				OsiEmployees employee = new OsiEmployees();
				employee.setEmployeeId((Integer) employeeObj[0]);
				employee.setEmployeeNumber((String) employeeObj[1]);
				employee.setFirstName((String) employeeObj[2]);
				employee.setLastName((String) employeeObj[3]);
				employee.setFullName((String) employeeObj[4]);
				employee.setOfficeEmail((String) employeeObj[5]);
				employee.setMailStop((String) employeeObj[6]);
				employee.setOrgId((Integer) employeeObj[7]);
				if(employeeObj[7] != null) {
					OsiOrganizationsDTO osiOrganizations = new OsiOrganizationsDTO();
					osiOrganizations.setOrgId((Integer) employeeObj[7]);
					osiOrganizations.setOrgName((String) employeeObj[8]);
					employee.setOsiOrganizationsDTO(osiOrganizations);
				}
				employeesList.add(employee);
			}
		} catch (NoResultException ne) {
			LOGGER.error("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No records found for search employee");
		} catch (Exception e) {
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("searchEmployees : End");
		return employeesList;
	}
	
	@Override
	public List<OsiCountries> getAllCountries() throws DataAccessException {
		List<OsiCountries> countries = new ArrayList<OsiCountries>();
		try {
			LOGGER.info("getAllCountries : Begin");
			String queryStr = "select c from OsiCountries c order by c.countryName";
			TypedQuery<OsiCountries> query = entityManager.createQuery(queryStr, OsiCountries.class);
			countries = query.getResultList();
		} catch (NoResultException ne) {
			LOGGER.error("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No records found for countries");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllCountries : End");
		return countries;		
	}
	
	@Override
	public List<OsiCountryVisas> getAllCountryVisas(Integer countryId) throws DataAccessException {
		List<OsiCountryVisas> countryVisas = new ArrayList<OsiCountryVisas>();
		try {
			LOGGER.info("getAllCountryVisas : Begin");
			String queryStr = "select cv from OsiCountryVisas cv where cv.countryId = ? order by cv.visaType";
			TypedQuery<OsiCountryVisas> query = entityManager.createQuery(queryStr, OsiCountryVisas.class);
			countryVisas = query.setParameter(1, countryId).getResultList();
		} catch (NoResultException ne) {
			LOGGER.error("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No records found for country visas");
		} catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		LOGGER.info("getAllCountryVisas : End");
		return countryVisas;		
	}

	@Override
	public int isExistEmployee(String officeEmail, String employeeNumber) throws DataAccessException {
		int count = 0;
		try {
			LOGGER.info("isExistEmployee : Begin");
			String isExistEmpQuery = "select count(e.employee_id) from osi_employees e "
					+ " where "
					+ " ? between e.effective_start_date and e.effective_end_date "
					+ " and (e.office_email = ? or e.employee_number = ?)";
			
			BigInteger existCount = (BigInteger) this.entityManager.createNativeQuery(isExistEmpQuery)
													.setParameter(1, new java.sql.Timestamp(new Date().getTime()))
													.setParameter(2, officeEmail)
													.setParameter(3, employeeNumber)
													.getSingleResult();
			count = existCount.intValue();
		} catch (NoResultException ne) {
			LOGGER.error("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No records found for existing employee");
		} catch (Exception e) {
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("isExistEmployee : End");
		return count;
	}
	
	public boolean isManager(Integer employeeId) throws DataAccessException {
		boolean isManagerFlag = false;
		try {
			LOGGER.info("isManager : Begin");
			String isManagerQuery = "select a.is_manager from osi_assignments a "
					+ " where "
					+ "  ? between a.effective_start_date and a.effective_end_date "
					+ " and a.employee_id = ?";
			if(null != employeeId) {
				Integer obj = (Integer) this.entityManager.createNativeQuery(isManagerQuery)
						.setParameter(1, new java.sql.Timestamp(new Date().getTime()))
						.setParameter(2, employeeId)
						.getSingleResult();
				if(obj != null && obj.intValue() == 1)
					isManagerFlag = true;
			} else {
				LOGGER.info("Exception : Invalid employee number is passed");
				throw new DataAccessException("ERR_1021", "Invalid Employee Number");
			}
		} catch (NoResultException ne) {
			LOGGER.error("Exception : "+ne.getMessage());
			return false;
		} catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("isManager : End");
		return isManagerFlag;
	}
	
	@Override
	public OsiEmployees findByEmployeeIdAndSerachDate(int employeeId, String searchDate) throws DataAccessException {
		OsiEmployees osiEmployees = null;
		try {
			LOGGER.info("findByEmployeeIdAndSerachDate : Begin");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String time  = dtf.format(now);   
			String getEmployeeQuery = "select employee_id,employee_number,first_name, last_name, middle_name,full_name,title, suffix,prefix "
					+ ",employee_type,applicant_number,date_of_birth, start_date,effective_start_date,effective_end_date"
					+ ",org_id,blood_type,background_check_status,background_date_check,correspondence_language,office_email,personal_email"
					+ ",expense_check_send_to_address_id,fte_capacity, hold_applicant_date_until,mail_stop,known_as"
					+ ",last_medical_test_date, last_medical_test_by, nationality, marital_status,national_identifier,on_military_service,previous_last_name"
					+ ",rehire_reason, rehire_recommendation,resume_exists,resume_last_updated,resume_id,second_passport_exists,gender, work_schedule_id"
					+ ",receipt_of_death_cert_date,uses_tobacco_flag,photo_id,date_of_death,original_date_of_hire,passport_number,passport_date_of_issue," 
					+ "passport_date_of_expiry,passport_issuance_authority,passport_place_of_issue,mail_address_id,permanent_address_id, version"
					+ ",Attribute1,Attribute2, Attribute3, Attribute4, Attribute5, Attribute6, Attribute7, Attribute8, Attribute9, Attribute10,Attribute11,Attribute12"
					+",Attribute13,Attribute14,Attribute15,Attribute16,Attribute17, Attribute18, Attribute19, Attribute20, Attribute21,Attribute22, Attribute23,Attribute24, Attribute25"
					+ ",created_by, creation_date, last_updated_by,last_update_date,attachment_id, termination_date, total_exp, user_name, system_type, serial_number, employee_status "
					+ " from osi_employees e " 
					+ " where "
					+ " e.employee_id = ? " 
					+ " and ? between e.effective_start_date "
					+ " and e.effective_end_date";
			Object[] object = (Object[]) this.entityManager.createNativeQuery(getEmployeeQuery)
			.setParameter(1, employeeId)
			.setParameter(2, searchDate+" "+time).getSingleResult();
			
			osiEmployees = new OsiEmployees();
			osiEmployees.setEmployeeId(object[0]!= null ? Integer.parseInt(object[0].toString()) : null);
			osiEmployees.setEmployeeNumber(object[1]!= null ? object[1].toString() : null);
			osiEmployees.setFirstName(object[2]!= null ? object[2].toString() : null);
			osiEmployees.setLastName(object[3]!= null ? object[3].toString() : null);
			osiEmployees.setMiddleName(object[4]!= null ? object[4].toString(): null);
			osiEmployees.setFullName(object[5]!= null ? object[5].toString() : null);
			osiEmployees.setTitle(object[6]!= null ? object[6].toString() : null);
			osiEmployees.setSuffix(object[7]!=null ? object[7].toString() : null);
			osiEmployees.setPrefix(object[8]!= null ? object[8].toString() : null);
			osiEmployees.setEmployeeType(object[9]!= null ? object[9].toString() : null);
			osiEmployees.setApplicantNumber(object[10]!= null ? object[10].toString() : null);
			osiEmployees.setDateOfBirth(object[11]!= null ? object[11].toString() : null);
			osiEmployees.setStartDate(object[12]!= null ? object[12].toString():null);
			osiEmployees.setEffectiveStartDate(object[13]!= null ? convertTimestampToString((Timestamp)object[13]) : null);
			osiEmployees.setEffectiveEndDate(object[14]!= null ? convertTimestampToString((Timestamp)object[14]): null);
			osiEmployees.setOrgId(object[15]!= null ? Integer.parseInt(object[15].toString()) : null);
			osiEmployees.setBloodType(object[16]!= null ? object[16].toString(): null);
			osiEmployees.setBackgroundCheckStatus(object[17]!= null ? Integer.parseInt(object[17].toString()): null);
			osiEmployees.setBackgroundDateCheck(object[18]!= null ? object[18].toString(): null);
			osiEmployees.setCorrespondenceLanguage(object[19]!= null ? object[19].toString(): null);
			osiEmployees.setOfficeEmail(object[20]!= null ? object[20].toString(): null);
			osiEmployees.setPersonalEmail(object[21]!= null ? object[21].toString(): null);
			osiEmployees.setExpenseCheckSendToAddressId(object[22]!= null ? Integer.parseInt(object[22].toString()): null);
			osiEmployees.setFteCapacity(object[23]!= null ? Integer.parseInt(object[23].toString()): null);
			osiEmployees.setHoldApplicantDateUntil(object[24]!= null ? object[24].toString(): null);
			osiEmployees.setMailStop(object[25]!= null ? object[25].toString(): null);
			osiEmployees.setKnownAs(object[26]!= null ? object[26].toString(): null);
			
			osiEmployees.setLastMedicalTestDate(object[27]!= null ? object[27].toString(): null);
			
			osiEmployees.setLastMedicalTestBy(object[28]!= null ? object[28].toString(): null);
			osiEmployees.setNationality(object[29]!= null ? object[29].toString(): null);
			osiEmployees.setMaritalStatus(object[30]!= null ? object[30].toString(): null);
			osiEmployees.setNationalIdentifier(object[31]!= null ? object[31].toString(): null);
			osiEmployees.setOnMilitaryService(object[32]!= null ? Integer.parseInt(object[32].toString()): null);
			osiEmployees.setPreviousLastName(object[33]!= null ? object[33].toString(): null);
			osiEmployees.setRehireReason(object[34]!= null ? object[34].toString(): null);
			osiEmployees.setRehireRecommendation(object[35]!= null ? object[35].toString(): null);
			osiEmployees.setResumeExists(object[36]!= null ? Integer.parseInt(object[36].toString()): null);
			osiEmployees.setResumeLastUpdated(object[37]!= null ? object[37].toString(): null);
			osiEmployees.setResumeId(object[38]!= null ? Integer.parseInt(object[38].toString()): null);
			osiEmployees.setSecondPassportExists(object[39]!= null ? Integer.parseInt(object[39].toString()): null);
			osiEmployees.setGender(object[40]!= null ? object[40].toString(): null);
			osiEmployees.setWorkScheduleId(object[41]!= null ? Integer.parseInt(object[41].toString()): null);
			osiEmployees.setReceiptOfDeathCertDate(object[42]!= null ? object[42].toString(): null);
			osiEmployees.setUsesTobaccoFlag(object[43]!= null ? Integer.parseInt(object[43].toString()): null);
			osiEmployees.setPhotoId(object[44]!= null ? Integer.parseInt(object[44].toString()): null);
			osiEmployees.setDateOfDeath(object[45]!= null ? object[45].toString(): null);
			osiEmployees.setOriginalDateOfHire(object[46]!= null ? object[46].toString(): null);
			osiEmployees.setPassportNumber(object[47]!= null ? object[47].toString(): null);
			osiEmployees.setPassportDateOfIssue(object[48]!= null ? object[48].toString(): null);
			osiEmployees.setPassportDateOfExpiry(object[49]!= null ? object[49].toString(): null);
			osiEmployees.setPassportIssuanceAuthority(object[50]!= null ? object[50].toString(): null);
			osiEmployees.setPassportPlaceOfIssue(object[51]!= null ? object[51].toString(): null);
			osiEmployees.setMailAddressId(object[52]!= null ? Integer.parseInt(object[52].toString()): null);
			osiEmployees.setPermanentAddressId(object[53]!= null ? Integer.parseInt(object[53].toString()): null);
			osiEmployees.setVersion(object[54]!= null ? Integer.parseInt(object[54].toString()): null);
			osiEmployees.setAttribute1(object[55]!= null ? object[55].toString(): null);
			osiEmployees.setAttribute2(object[56]!= null ? object[56].toString(): null);
			osiEmployees.setAttribute3(object[57]!= null ? object[57].toString(): null);
			osiEmployees.setAttribute4(object[58]!= null ? object[58].toString(): null);
			osiEmployees.setAttribute5(object[59]!= null ? object[59].toString(): null);
			osiEmployees.setAttribute6(object[60]!= null ? object[60].toString(): null);
			osiEmployees.setAttribute7(object[61]!= null ? object[61].toString(): null);
			osiEmployees.setAttribute8(object[62]!= null ? object[62].toString(): null);
			osiEmployees.setAttribute9(object[63]!= null ? object[63].toString(): null);
			osiEmployees.setAttribute10(object[64]!= null ? object[64].toString(): null);
			osiEmployees.setAttribute11(object[65]!= null ? object[65].toString(): null);
			osiEmployees.setAttribute12(object[66]!= null ? object[66].toString(): null);
			osiEmployees.setAttribute13(object[67]!= null ? object[67].toString(): null);
			osiEmployees.setAttribute14(object[68]!= null ? object[68].toString(): null);
			osiEmployees.setAttribute15(object[69]!= null ? object[69].toString(): null);
			osiEmployees.setAttribute16(object[70]!= null ? object[70].toString(): null);
			osiEmployees.setAttribute17(object[71]!= null ? object[71].toString(): null);
			osiEmployees.setAttribute18(object[72]!= null ? object[72].toString(): null);
			osiEmployees.setAttribute19(object[73]!= null ? object[73].toString(): null);
			osiEmployees.setAttribute20(object[74]!= null ? object[74].toString(): null);
			osiEmployees.setAttribute21(object[75]!= null ? object[75].toString(): null);
			osiEmployees.setAttribute22(object[76]!= null ? object[76].toString(): null);
			osiEmployees.setAttribute23(object[77]!= null ? object[77].toString(): null);
			osiEmployees.setAttribute24(object[78]!= null ? object[78].toString(): null);
			osiEmployees.setAttribute25(object[79]!= null ? object[79].toString(): null);
			osiEmployees.setCreatedBy(object[80]!= null ? Integer.parseInt(object[80].toString()) : null);
			osiEmployees.setCreationDate(object[81]!= null ? object[81].toString(): null);
			osiEmployees.setLastUpdatedBy(object[82]!= null ? Integer.parseInt(object[82].toString()): null);
			osiEmployees.setLastUpdateDate(object[83]!= null ? object[83].toString(): null);
			osiEmployees.setAttachmentId(object[84]!= null ? Integer.parseInt(object[84].toString()): null);
			osiEmployees.setTerminationDate(object[85]!= null ? convertTimestampToString((Timestamp)object[85]) : null);
			osiEmployees.setTotalExp(object[86]!= null ? Double.parseDouble(object[86].toString()) : null);
			osiEmployees.setUserName(object[87]!= null ? object[87].toString() : null);
			
			osiEmployees.setSystemType(object[88]!= null ? object[88].toString(): null);
			osiEmployees.setSerialNumber(object[89]!= null ? object[89].toString(): null);
			osiEmployees.setEmployeeStatus(object[90]!= null ? Integer.parseInt(object[90].toString()): null);
			
		} catch(NoResultException ne) {
			LOGGER.error("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No Records found with employee id and search date");
		} catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("findByEmployeeIdAndSerachDate : End");
		return osiEmployees;
	}
	
	@Override
	public Integer updateEmployee(OsiEmployees osiEmployees) throws DataAccessException{
		try {
			LOGGER.info("updateEmployee : Begin");
			String updateEmployeeQuery = "update osi_employees set employee_id = ?, employee_number = ?, first_name = ?, last_name = ?, middle_name = ?, full_name = ?, title = ?, suffix = ?, prefix = ?, employee_type = ?, applicant_number = ?, date_of_birth = ?, start_date = ?, effective_start_date = ?, effective_end_date = ?, org_id = ?, blood_type = ?, background_check_status = ?, background_date_check = ?, correspondence_language = ?, office_email = ?, personal_email = ?, " + 
					" expense_check_send_to_address_id = ?,fte_capacity = ?, hold_applicant_date_until = ?,mail_stop = ?,known_as = ?,last_medical_test_date = ?, last_medical_test_by = ?, nationality = ?, marital_status = ?, national_identifier = ?, on_military_service = ?, previous_last_name = ?, rehire_reason = ?, rehire_recommendation = ?, resume_exists = ?, resume_last_updated = ?, resume_id = ?, second_passport_exists = ?, gender = ?, " + 
					" work_schedule_id = ?, receipt_of_death_cert_date = ?, uses_tobacco_flag = ?, photo_id = ?, date_of_death = ?, original_date_of_hire = ?, passport_number = ?, passport_date_of_issue = ?, passport_date_of_expiry = ?, passport_issuance_authority = ?, passport_place_of_issue = ?, mail_address_id = ?, permanent_address_id = ?, version = ?, " + 
					" Attribute1 = ?, Attribute2 = ?, Attribute3 = ?, Attribute4 = ?, Attribute5 = ?, Attribute6 = ?, Attribute7 = ?, Attribute8 = ?, Attribute9 = ?, Attribute10 = ?, Attribute11 = ?, Attribute12 = ?, Attribute13 = ?, Attribute14 = ?, Attribute15 = ?, Attribute16 = ?, Attribute17 = ?, Attribute18 = ?, Attribute19 = ?, Attribute20 = ?, Attribute21 = ?, Attribute22 = ?, Attribute23 = ?, Attribute24 = ?, Attribute25 = ?, " + 
					" created_by = ?, creation_date = ?, last_updated_by = ?, last_update_date = ?, attachment_id = ?, termination_date= ?, total_exp= ?, user_name = ?, system_type = ? , serial_number = ?, employee_status = ? " + 
					" where " + 
					" employee_id = ? " + 
					" and ? between effective_start_date and effective_end_date";
			Query query = this.entityManager.createNativeQuery(updateEmployeeQuery);
			query.setParameter(1, osiEmployees.getEmployeeId());
			query.setParameter(2, osiEmployees.getEmployeeNumber());
			query.setParameter(3, osiEmployees.getFirstName());
			query.setParameter(4, osiEmployees.getLastName());
			query.setParameter(5, osiEmployees.getMiddleName());
			   
			query.setParameter(6, osiEmployees.getFullName());
			query.setParameter(7, osiEmployees.getTitle());
			query.setParameter(8, osiEmployees.getSuffix());
			query.setParameter(9, osiEmployees.getPrefix());
			query.setParameter(10, osiEmployees.getEmployeeType());
			
			query.setParameter(11, osiEmployees.getApplicantNumber());
			query.setParameter(12, osiEmployees.getDateOfBirth());
			query.setParameter(13, osiEmployees.getStartDate());
			query.setParameter(14, osiEmployees.getEffectiveStartDate());
			query.setParameter(15, osiEmployees.getEffectiveEndDate());
			
			query.setParameter(16, osiEmployees.getOrgId());
			query.setParameter(17, osiEmployees.getBloodType());
			query.setParameter(18, osiEmployees.getBackgroundCheckStatus());
			query.setParameter(19, osiEmployees.getBackgroundDateCheck());
			query.setParameter(20, osiEmployees.getCorrespondenceLanguage());
			query.setParameter(21, osiEmployees.getOfficeEmail());
			query.setParameter(22, osiEmployees.getPersonalEmail());
			query.setParameter(23, osiEmployees.getExpenseCheckSendToAddressId());
			query.setParameter(24, osiEmployees.getFteCapacity());
			query.setParameter(25, osiEmployees.getHoldApplicantDateUntil());
			query.setParameter(26, osiEmployees.getMailStop());
			query.setParameter(27, osiEmployees.getKnownAs());
			query.setParameter(28, osiEmployees.getLastMedicalTestDate());
			query.setParameter(29, osiEmployees.getLastMedicalTestBy());
			query.setParameter(30, osiEmployees.getNationality());
			query.setParameter(31, osiEmployees.getMaritalStatus());
			query.setParameter(32, osiEmployees.getNationalIdentifier());
			query.setParameter(33, osiEmployees.getOnMilitaryService());
			query.setParameter(34, osiEmployees.getPreviousLastName());
			query.setParameter(35, osiEmployees.getRehireReason());
			query.setParameter(36, osiEmployees.getRehireRecommendation());
			query.setParameter(37, osiEmployees.getResumeExists());
			query.setParameter(38, osiEmployees.getResumeLastUpdated());
			query.setParameter(39, osiEmployees.getResumeId());
			   
			   query.setParameter(40, osiEmployees.getSecondPassportExists());
			   query.setParameter(41, osiEmployees.getGender());
			   query.setParameter(42, osiEmployees.getWorkScheduleId());
			   query.setParameter(43, osiEmployees.getReceiptOfDeathCertDate());
			   query.setParameter(44, osiEmployees.getUsesTobaccoFlag());
			   query.setParameter(45, osiEmployees.getPhotoId());
			   query.setParameter(46, osiEmployees.getDateOfDeath());
			   query.setParameter(47, osiEmployees.getOriginalDateOfHire());				   
			   query.setParameter(48, osiEmployees.getPassportNumber());
			   query.setParameter(49, osiEmployees.getPassportDateOfIssue());
			   query.setParameter(50, osiEmployees.getPassportDateOfExpiry());
			   query.setParameter(51, osiEmployees.getPassportIssuanceAuthority());
			   query.setParameter(52, osiEmployees.getPassportPlaceOfIssue());
			   query.setParameter(53, osiEmployees.getMailAddressId());
			   query.setParameter(54, osiEmployees.getPermanentAddressId());
			   query.setParameter(55, osiEmployees.getVersion());
			   query.setParameter(56, osiEmployees.getAttribute1());
			   query.setParameter(57, osiEmployees.getAttribute2());
			   query.setParameter(58, osiEmployees.getAttribute3());
			   query.setParameter(59, osiEmployees.getAttribute4());
			   query.setParameter(60, osiEmployees.getAttribute5());
			   query.setParameter(61, osiEmployees.getAttribute6());
			   query.setParameter(62, osiEmployees.getAttribute7());
			   query.setParameter(63, osiEmployees.getAttribute8());
			   query.setParameter(64, osiEmployees.getAttribute9());
			   query.setParameter(65, osiEmployees.getAttribute10());
			   query.setParameter(66, osiEmployees.getAttribute11());
			   query.setParameter(67, osiEmployees.getAttribute12());
			   query.setParameter(68, osiEmployees.getAttribute13());
			   query.setParameter(69, osiEmployees.getAttribute14());
			   query.setParameter(70, osiEmployees.getAttribute15());
			   query.setParameter(71, osiEmployees.getAttribute16());
			   query.setParameter(72, osiEmployees.getAttribute17());
			   query.setParameter(73, osiEmployees.getAttribute18());
			   query.setParameter(74, osiEmployees.getAttribute19());
			   query.setParameter(75, osiEmployees.getAttribute20());
			   query.setParameter(76, osiEmployees.getAttribute21());
			   query.setParameter(77, osiEmployees.getAttribute22());
			   query.setParameter(78, osiEmployees.getAttribute23());
			   query.setParameter(79, osiEmployees.getAttribute24());
			   query.setParameter(80, osiEmployees.getAttribute25());
			   query.setParameter(81, osiEmployees.getCreatedBy());
			   query.setParameter(82, osiEmployees.getCreationDate());
			   query.setParameter(83, osiEmployees.getLastUpdatedBy());
			   query.setParameter(84, osiEmployees.getLastUpdateDate());
			   query.setParameter(85, osiEmployees.getAttachmentId());
			   query.setParameter(86, osiEmployees.getTerminationDate());
			   query.setParameter(87, osiEmployees.getTotalExp());
			   query.setParameter(88, osiEmployees.getUserName());
			   
			   query.setParameter(89, osiEmployees.getSystemType());
			   query.setParameter(90, osiEmployees.getSerialNumber());
			   query.setParameter(91, osiEmployees.getEmployeeStatus());
			   
			   query.setParameter(92, osiEmployees.getEmployeeId());
			   query.setParameter(93,new java.sql.Timestamp(new Date().getTime()));
			   
			  query.executeUpdate();
			
		} catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("updateEmployee : End");
		return osiEmployees.getEmployeeId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiEmployees> searchEmployeesSelf(String searchData) throws DataAccessException {
		List<OsiEmployees> employeesList = null;
		try {
			LOGGER.info("searchEmployeesSelf : Begin");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String time  = dtf.format(now);  
			JSONObject searchObject = new JSONObject(searchData);
			String searchQuery = "select e.employee_id,e.employee_number,e.first_name,e.last_name,e.full_name,e.office_email,e.mail_stop,e.org_id, o.org_name "
					+ " from osi_employees e, osi_organizations o "
					+ " where o.org_id = e.org_id and "
					+ " e.employee_id in "
					+ " (select s.employee_id from osi_employees e1 "
					+ " inner join osi_assignments s on s.supervisor_id = e1.employee_id where e1.employee_id = ?"
					+ " union "
					+ " select ee.employee_id from osi_employees ee where ee.employee_id = ? ) and ";
			StringBuilder sb = new StringBuilder(searchQuery);
			if(searchObject.has("searchDate"))
				sb.append("'"+searchObject.getString("searchDate")+" "+time+"' between e.effective_start_date and e.effective_end_date ");
			if(searchObject.has("employeeNumber"))
				sb.append("and e.employee_number like '%"+searchObject.getString("employeeNumber")+"%' ");
			if(searchObject.has("firstName"))
				sb.append(" and e.first_name like '%"+searchObject.getString("firstName")+"%' ");
			if(searchObject.has("lastName"))
				sb.append(" and e.last_name like '%"+searchObject.getString("lastName")+"%' ");
			if(searchObject.has("officeEmail"))
				sb.append(" and e.office_email like '%"+searchObject.getString("officeEmail")+"%' ");
			Query query = this.entityManager.createNativeQuery(sb.toString());
			query.setParameter(1, searchObject.getInt("employeeId"));
			query.setParameter(2, searchObject.getInt("employeeId"));
			List<Object[]> employeesListObj = query.getResultList();
			//List<Object[]> employeesListObj = this.entityManager.createNativeQuery(sb.toString()).getResultList();
			if(employeesListObj.size() != 0)
				employeesList = new ArrayList<OsiEmployees>();
			for(Object[] employeeObj : employeesListObj) {
				OsiEmployees employee = new OsiEmployees();
				employee.setEmployeeId((Integer) employeeObj[0]);
				employee.setEmployeeNumber((String) employeeObj[1]);
				employee.setFirstName((String) employeeObj[2]);
				employee.setLastName((String) employeeObj[3]);
				employee.setFullName((String) employeeObj[4]);
				employee.setOfficeEmail((String) employeeObj[5]);
				employee.setMailStop((String) employeeObj[6]);
				employee.setOrgId((Integer) employeeObj[7]);
				if(employeeObj[7] != null) {
					OsiOrganizationsDTO osiOrganizations = new OsiOrganizationsDTO();
					osiOrganizations.setOrgId((Integer) employeeObj[7]);
					osiOrganizations.setOrgName((String) employeeObj[8]);
					employee.setOsiOrganizationsDTO(osiOrganizations);
				}
				employeesList.add(employee);
			}
		} catch(NoResultException ne) {
			LOGGER.error("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No Result Found for searchEmployeesSelf");
		} catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("searchEmployeesSelf : End");
		return employeesList;
	}
	
	@Override
	public boolean isExistEmployee(String employeeNumber, Integer orgId, Integer employeeId) throws DataAccessException {
		boolean isExistEmp = false;
		try {
			LOGGER.info("isExistEmployee : Begin");
			String isExistEmployee = "select count(*) from osi_employees e where e.employee_number = ? and e.org_id = ? and employee_id != ?";
			BigInteger obj = (BigInteger) this.entityManager.createNativeQuery(isExistEmployee)
						.setParameter(1, employeeNumber)
						.setParameter(2, orgId)
						.setParameter(3, employeeId)
						.getSingleResult();
			if(obj != null && obj.intValue() >= 1)
				isExistEmp = true;
			
		} catch (NoResultException ne) {
			LOGGER.info("isExistEmployee : No Employee found..");
			isExistEmp = false;
		} catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception while executing the isExistEmployee..");
		}
		LOGGER.info("isExistEmployee : End");
		return isExistEmp;
	}
	@Override
	public boolean findByOfficeEmail(String mailId,Integer employeeId) throws DataAccessException {
		 boolean isEmployeeExist = false;
		try {
			LOGGER.info("findByOfficeEmail : Begin");
			String osiemployee = "select count(*) from osi_employees a "
					+ " where "
					+ "  ? between a.effective_start_date and a.effective_end_date "
					+ " and a.office_email = ? and employee_id != ?";
			if(null != mailId) {
				BigInteger obj = (BigInteger) this.entityManager.createNativeQuery(osiemployee)
						.setParameter(1, new java.sql.Timestamp(new Date().getTime()))
						.setParameter(2, mailId)
						.setParameter(3, employeeId)
						.getSingleResult();
						
				Integer result = ((BigInteger) obj).intValue();
				
 				if(obj != null && result == 1)
					isEmployeeExist = true;
			} else {
				LOGGER.info("Invalid Email Id..");
				throw new DataAccessException("ERR_1020", "Invalid Employee Mail Id");
			}
			LOGGER.info("findByOfficeEmail : End");
		} catch (NoResultException ne) {
			LOGGER.error("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No Record found in osi_employees");
		} catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception while getting the employee by office mail");
		}
		LOGGER.info("findByOfficeEmail : End");
		return isEmployeeExist;
	}

	
	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiEmployees> overallEmployeesSearch(String searchData) throws DataAccessException {
		List<OsiEmployees> employeesList = null;
		LOGGER.info("overallEmployeesSearch : Begin");
		try {
			/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String time  = dtf.format(now);*/ 
			JSONObject searchObject = new JSONObject(searchData);
			//System.out.println(searchObject);
			String searchSkills = null;
			String searchCertificates = null;
			String searchProjects = null;
			if(searchObject.has("skills"))
				searchSkills = searchObject.getString("skills");
			if(searchObject.has("certifications"))
				searchCertificates = searchObject.getString("certifications");
			if(searchObject.has("projects"))
				searchProjects = searchObject.getString("projects");
			
			List<Object[]> employeesListObj = this.entityManager.createNativeQuery("CALL searchResources(:skills, :certifications, :projects)")
				.setParameter("skills", searchSkills)
				.setParameter("certifications", searchCertificates)
				.setParameter("projects", searchProjects)
				//.setParameter("orgId", 18)
				.getResultList();
			if(employeesListObj.size() != 0)
				employeesList = new ArrayList<OsiEmployees>();
			for(Object[] employeeObj : employeesListObj) {
				OsiEmployees employee = new OsiEmployees();
				//employee.setEmployeeId((Integer) employeeObj[0]);
				employee.setEmployeeNumber((String) employeeObj[0]);
				employee.setFirstName((String) employeeObj[1]);
				employee.setLastName((String) employeeObj[2]);
				//employee.setFullName((String) employeeObj[4]);
				employee.setOfficeEmail((String) employeeObj[3]);
				//employee.setMailStop((String) employeeObj[4]);
				employee.setLocationName((employeeObj[4] != null) ? (String) employeeObj[4]: null);
				if(employeeObj[5] != null) {
					OsiOrganizationsDTO osiOrganizations = new OsiOrganizationsDTO();
					//osiOrganizations.setOrgId((Integer) employeeObj[7]);
					osiOrganizations.setOrgName((employeeObj[5] != null) ? (String) employeeObj[5]: null);
					employee.setOsiOrganizationsDTO(osiOrganizations);
				}
				employee.setProjects((employeeObj[6] != null) ? (String) employeeObj[6]: null);
				employeesList.add(employee);
			}
		} catch (NoResultException ne) {
			LOGGER.error("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No records found for search employee");
		} catch (Exception e) {
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("overallEmployeesSearch : End");
		return employeesList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiEmployees> getAllEmployees(String searchData) throws DataAccessException {
		List<OsiEmployees> employeesList = null;
		LOGGER.info("getAllEmployees : Begin");
		try {
			String getEmployeeQuery = "select e.employee_id,e.employee_number,e.first_name, e.last_name, e.middle_name,e.full_name,e.title, e.suffix,e.prefix "
					+ " ,e.employee_type,e.applicant_number,e.date_of_birth, e.start_date,e.effective_start_date,e.effective_end_date "
					+ " ,e.org_id,e.blood_type,e.background_check_status,e.background_date_check,e.correspondence_language,e.office_email,e.personal_email "
					+ " ,e.expense_check_send_to_address_id,e.fte_capacity, e.hold_applicant_date_until,e.mail_stop,e.known_as "
					+ " ,e.last_medical_test_date, e.last_medical_test_by, e.nationality, e.marital_status,e.national_identifier,e.on_military_service,e.previous_last_name "
					+ " ,e.rehire_reason, e.rehire_recommendation,e.resume_exists,e.resume_last_updated,e.resume_id,e.second_passport_exists,e.gender, e.work_schedule_id "
					+ " ,e.receipt_of_death_cert_date,e.uses_tobacco_flag,e.photo_id,e.date_of_death,e.original_date_of_hire,e.passport_number,e.passport_date_of_issue "
					+ " ,e.passport_date_of_expiry,e.passport_issuance_authority,e.passport_place_of_issue,e.mail_address_id,e.permanent_address_id, e.version "
					+ " ,e.Attribute1,e.Attribute2, e.Attribute3, e.Attribute4, e.Attribute5, e.Attribute6, e.Attribute7, e.Attribute8, e.Attribute9, e.Attribute10,e.Attribute11,e.Attribute12 "
					+ " ,e.Attribute13,e.Attribute14,e.Attribute15,e.Attribute16,e.Attribute17, e.Attribute18, e.Attribute19, e.Attribute20, e.Attribute21,e.Attribute22, e.Attribute23,e.Attribute24, e.Attribute25 "
					+ " ,e.created_by, e.creation_date, e.last_updated_by,e.last_update_date,e.attachment_id, e.termination_date, e.user_name, e.system_type, e.serial_number, e.total_exp, e.employee_status, "
					+ " mail.address_line1 mail_add_line1,mail.address_line2 mail_add_line2,mail.city mail_city,mail.state_id mail_st_id,s.state_name mail_st_name,mail.country_id mail_country_id,c.country_name mail_country_name,mail.zipcode mail_zipcode, "
					+ " permanent.address_line1 perm_add_line1,permanent.address_line2 perm_add_line2,permanent.city perm_city,permanent.state_id perm_st_id,s1.state_name perm_st_name,permanent.country_id perm_country_id,c1.country_name perm_country_name,permanent.zipcode perm_zipcode,o.ORG_NAME " 
					+ " from osi_employees e "
					+ " left join osi_addresses mail on mail.address_id = e.mail_address_id "
					+ " left join osi_addresses permanent on permanent.address_id = e.permanent_address_id "
					+ " left join osi_states s on s.state_id = mail.state_id "
					+ " left join osi_states s1 on s1.state_id = permanent.state_id "
					+ " left join osi_countries c on c.country_id = mail.country_id "
					+ " left join osi_countries c1 on c1.country_id = permanent.country_id"
					+ " left join osi_organizations o on o.ORG_ID = e.org_id " 
					+ " where "
					+ " e.effective_start_date  between ? and ? " 
					+ " and CURRENT_TIMESTAMP() between e.effective_start_date and e.effective_end_date" ;
			StringBuilder sb = new StringBuilder(getEmployeeQuery);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String time  = dtf.format(now);   
			JSONObject searchObject = new JSONObject(searchData);
			String fromDate = null;
			String toDate = null;
			if(searchObject.has("fromDate")) 
				fromDate = searchObject.getString("fromDate")+" "+time;
			if(searchObject.has("toDate"))
				toDate = searchObject.getString("toDate")+" "+time;
			if(fromDate == null || toDate == null)
				throw new DataAccessException("", "Invalid from/to date");
			List<Object[]> employeesListObj = (List<Object[]>) this.entityManager.createNativeQuery(sb.toString()).setParameter(1, fromDate).setParameter(2, toDate).getResultList();
			
			if(employeesListObj.size() != 0)
				employeesList = new ArrayList<OsiEmployees>();
			for(Object[] object : employeesListObj) {
				
				
				OsiEmployees osiEmployees = new OsiEmployees();
				osiEmployees.setEmployeeId(object[0]!= null ? Integer.parseInt(object[0].toString()) : null);
				osiEmployees.setEmployeeNumber(object[1]!= null ? object[1].toString() : null);
				osiEmployees.setFirstName(object[2]!= null ? object[2].toString() : null);
				osiEmployees.setLastName(object[3]!= null ? object[3].toString() : null);
				osiEmployees.setMiddleName(object[4]!= null ? object[4].toString(): null);
				osiEmployees.setFullName(object[5]!= null ? object[5].toString() : null);
				osiEmployees.setTitle(object[6]!= null ? object[6].toString() : null);
				osiEmployees.setSuffix(object[7]!=null ? object[7].toString() : null);
				osiEmployees.setPrefix(object[8]!= null ? object[8].toString() : null);
				osiEmployees.setEmployeeType(object[9]!= null ? object[9].toString() : null);
				osiEmployees.setApplicantNumber(object[10]!= null ? object[10].toString() : null);
				osiEmployees.setDateOfBirth(object[11]!= null ? object[11].toString() : null);
				osiEmployees.setStartDate(object[12]!= null ? object[12].toString():null);
				osiEmployees.setEffectiveStartDate(object[13]!= null ? convertTimestampToString((Timestamp)object[13]) : null);
				osiEmployees.setEffectiveEndDate(object[14]!= null ? convertTimestampToString((Timestamp)object[14]): null);
				osiEmployees.setOrgId(object[15]!= null ? Integer.parseInt(object[15].toString()) : null);
				osiEmployees.setBloodType(object[16]!= null ? object[16].toString(): null);
				osiEmployees.setBackgroundCheckStatus(object[17]!= null ? Integer.parseInt(object[17].toString()): null);
				osiEmployees.setBackgroundDateCheck(object[18]!= null ? object[18].toString(): null);
				osiEmployees.setCorrespondenceLanguage(object[19]!= null ? object[19].toString(): null);
				osiEmployees.setOfficeEmail(object[20]!= null ? object[20].toString(): null);
				osiEmployees.setPersonalEmail(object[21]!= null ? object[21].toString(): null);
				osiEmployees.setExpenseCheckSendToAddressId(object[22]!= null ? Integer.parseInt(object[22].toString()): null);
				osiEmployees.setFteCapacity(object[23]!= null ? Integer.parseInt(object[23].toString()): null);
				osiEmployees.setHoldApplicantDateUntil(object[24]!= null ? object[24].toString(): null);
				osiEmployees.setMailStop(object[25]!= null ? object[25].toString(): null);
				osiEmployees.setKnownAs(object[26]!= null ? object[26].toString(): null);
				
				osiEmployees.setLastMedicalTestDate(object[27]!= null ? object[27].toString(): null);
				
				osiEmployees.setLastMedicalTestBy(object[28]!= null ? object[28].toString(): null);
				osiEmployees.setNationality(object[29]!= null ? object[29].toString(): null);
				osiEmployees.setMaritalStatus(object[30]!= null ? object[30].toString(): null);
				osiEmployees.setNationalIdentifier(object[31]!= null ? object[31].toString(): null);
				osiEmployees.setOnMilitaryService(object[32]!= null ? Integer.parseInt(object[32].toString()): null);
				osiEmployees.setPreviousLastName(object[33]!= null ? object[33].toString(): null);
				osiEmployees.setRehireReason(object[34]!= null ? object[34].toString(): null);
				osiEmployees.setRehireRecommendation(object[35]!= null ? object[35].toString(): null);
				osiEmployees.setResumeExists(object[36]!= null ? Integer.parseInt(object[36].toString()): null);
				osiEmployees.setResumeLastUpdated(object[37]!= null ? object[37].toString(): null);
				osiEmployees.setResumeId(object[38]!= null ? Integer.parseInt(object[38].toString()): null);
				osiEmployees.setSecondPassportExists(object[39]!= null ? Integer.parseInt(object[39].toString()): null);
				osiEmployees.setGender(object[40]!= null ? object[40].toString(): null);
				osiEmployees.setWorkScheduleId(object[41]!= null ? Integer.parseInt(object[41].toString()): null);
				osiEmployees.setReceiptOfDeathCertDate(object[42]!= null ? object[42].toString(): null);
				osiEmployees.setUsesTobaccoFlag(object[43]!= null ? Integer.parseInt(object[43].toString()): null);
				osiEmployees.setPhotoId(object[44]!= null ? Integer.parseInt(object[44].toString()): null);
				osiEmployees.setDateOfDeath(object[45]!= null ? object[45].toString(): null);
				osiEmployees.setOriginalDateOfHire(object[46]!= null ? object[46].toString(): null);
				osiEmployees.setPassportNumber(object[47]!= null ? object[47].toString(): null);
				osiEmployees.setPassportDateOfIssue(object[48]!= null ? object[48].toString(): null);
				osiEmployees.setPassportDateOfExpiry(object[49]!= null ? object[49].toString(): null);
				osiEmployees.setPassportIssuanceAuthority(object[50]!= null ? object[50].toString(): null);
				osiEmployees.setPassportPlaceOfIssue(object[51]!= null ? object[51].toString(): null);
				osiEmployees.setMailAddressId(object[52]!= null ? Integer.parseInt(object[52].toString()): null);
				osiEmployees.setPermanentAddressId(object[53]!= null ? Integer.parseInt(object[53].toString()): null);
				//if(object[52] != null) {
					OsiOrgAddressesDTO mailAddress = new OsiOrgAddressesDTO();
					mailAddress.setAddressId(object[52]!= null ? Integer.parseInt(object[52].toString()): null);
					mailAddress.setAddressLine1(object[91]!= null ? object[91].toString(): null);
					mailAddress.setAddressLine2(object[92]!= null ? object[92].toString(): null);
					mailAddress.setCity(object[93]!= null ? object[93].toString(): null);
					mailAddress.setStateId(object[94]!= null ? Integer.parseInt(object[94].toString()): null);
					mailAddress.setState(object[95]!= null ? object[95].toString(): null);					
					mailAddress.setCountryId(object[96]!= null ? Integer.parseInt(object[96].toString()): null);
					mailAddress.setCountry(object[97]!= null ? object[97].toString(): null);
					mailAddress.setZipcode(object[98]!= null ? object[98].toString(): null);
					osiEmployees.setMailAddress(mailAddress);
				//}
				//if(object[53] != null) {
					OsiOrgAddressesDTO permAddress = new OsiOrgAddressesDTO();
					permAddress.setAddressId(object[53]!= null ? Integer.parseInt(object[53].toString()): null);
					permAddress.setAddressLine1(object[99]!= null ? object[99].toString(): null);
					permAddress.setAddressLine2(object[100]!= null ? object[100].toString(): null);
					permAddress.setCity(object[101]!= null ? object[101].toString(): null);
					permAddress.setStateId(object[102]!= null ? Integer.parseInt(object[102].toString()): null);
					permAddress.setState(object[103]!= null ? object[103].toString(): null);					
					permAddress.setCountryId(object[104]!= null ? Integer.parseInt(object[104].toString()): null);
					permAddress.setCountry(object[105]!= null ? object[105].toString(): null);
					permAddress.setZipcode(object[106]!= null ? object[106].toString(): null);
					osiEmployees.setPermanentAddress(permAddress);
				//}
				osiEmployees.setVersion(object[54]!= null ? Integer.parseInt(object[54].toString()): null);
				osiEmployees.setAttribute1(object[55]!= null ? object[55].toString(): null);
				osiEmployees.setAttribute2(object[56]!= null ? object[56].toString(): null);
				osiEmployees.setAttribute3(object[57]!= null ? object[57].toString(): null);
				osiEmployees.setAttribute4(object[58]!= null ? object[58].toString(): null);
				osiEmployees.setAttribute5(object[59]!= null ? object[59].toString(): null);
				osiEmployees.setAttribute6(object[60]!= null ? object[60].toString(): null);
				osiEmployees.setAttribute7(object[61]!= null ? object[61].toString(): null);
				osiEmployees.setAttribute8(object[62]!= null ? object[62].toString(): null);
				osiEmployees.setAttribute9(object[63]!= null ? object[63].toString(): null);
				osiEmployees.setAttribute10(object[64]!= null ? object[64].toString(): null);
				osiEmployees.setAttribute11(object[65]!= null ? object[65].toString(): null);
				osiEmployees.setAttribute12(object[66]!= null ? object[66].toString(): null);
				osiEmployees.setAttribute13(object[67]!= null ? object[67].toString(): null);
				osiEmployees.setAttribute14(object[68]!= null ? object[68].toString(): null);
				osiEmployees.setAttribute15(object[69]!= null ? object[69].toString(): null);
				osiEmployees.setAttribute16(object[70]!= null ? object[70].toString(): null);
				osiEmployees.setAttribute17(object[71]!= null ? object[71].toString(): null);
				osiEmployees.setAttribute18(object[72]!= null ? object[72].toString(): null);
				osiEmployees.setAttribute19(object[73]!= null ? object[73].toString(): null);
				osiEmployees.setAttribute20(object[74]!= null ? object[74].toString(): null);
				osiEmployees.setAttribute21(object[75]!= null ? object[75].toString(): null);
				osiEmployees.setAttribute22(object[76]!= null ? object[76].toString(): null);
				osiEmployees.setAttribute23(object[77]!= null ? object[77].toString(): null);
				osiEmployees.setAttribute24(object[78]!= null ? object[78].toString(): null);
				osiEmployees.setAttribute25(object[79]!= null ? object[79].toString(): null);
				osiEmployees.setCreatedBy(object[80]!= null ? Integer.parseInt(object[80].toString()) : null);
				osiEmployees.setCreationDate(object[81]!= null ? object[81].toString(): null);
				osiEmployees.setLastUpdatedBy(object[82]!= null ? Integer.parseInt(object[82].toString()): null);
				osiEmployees.setLastUpdateDate(object[83]!= null ? object[83].toString(): null);
				osiEmployees.setAttachmentId(object[84]!= null ? Integer.parseInt(object[84].toString()): null);
				osiEmployees.setTerminationDate(object[85]!= null ? convertTimestampToString((Timestamp)object[85]) : null);
				
				osiEmployees.setUserName(object[86]!= null ? object[86].toString(): null);
				osiEmployees.setSystemType(object[87]!= null ? object[87].toString(): null);
				osiEmployees.setSerialNumber(object[88]!= null ? object[88].toString(): null);
				osiEmployees.setTotalExp(object[89]!= null ? Double.parseDouble(object[89].toString()): null);
				osiEmployees.setEmployeeStatus(object[90]!= null ? Integer.parseInt(object[90].toString()) : null);
				
				OsiOrganizationsDTO osiOrganizations = new OsiOrganizationsDTO();
				osiOrganizations.setOrgId(object[15]!= null ? Integer.parseInt(object[15].toString()) : null);
				osiOrganizations.setOrgName((String) object[107]);
				osiEmployees.setOsiOrganizationsDTO(osiOrganizations);
				
				employeesList.add(osiEmployees);
			}
		} catch (NoResultException ne) {
			LOGGER.error("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No records found for search employee");
		} catch (Exception e) {
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllEmployees : End");
		return employeesList;
	}
}
