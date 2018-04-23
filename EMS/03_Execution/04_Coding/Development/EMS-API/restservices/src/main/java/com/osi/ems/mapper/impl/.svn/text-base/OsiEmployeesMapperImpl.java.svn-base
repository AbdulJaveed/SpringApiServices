package com.osi.ems.mapper.impl;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiAttachments;
import com.osi.ems.domain.OsiContacts;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.mapper.OsiEmployeesMapper;
import com.osi.ems.repository.OsiAttachmentsesRepository;
import com.osi.ems.repository.custom.OsiEmployeesRepositoryCustom;
import com.osi.ems.service.OsiEmployeesService;
import com.osi.ems.service.dto.OsiAttachmentsDTO;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Component
@Transactional
public class OsiEmployeesMapperImpl implements OsiEmployeesMapper {

	@Autowired
	private AppConfig appConfig;
	@Autowired
	OsiEmployeesRepositoryCustom osiEmployeesRepository;
	@Autowired
    private OsiEmployeesService osiEmployeesService;

	@Autowired
	private OsiAttachmentsesRepository osiAttachmentsRepository;
	
	@Autowired
	private CommonService commonService;
	
	@Override
	public OsiEmployees mapToBasicInfo(OsiEmployees fromOsiEmployee, OsiEmployees toOsiEmployee)
			throws BusinessException {
		if (fromOsiEmployee != null) {
			toOsiEmployee.setOrgId(fromOsiEmployee.getOrgId());
			toOsiEmployee.setEmployeeNumber(fromOsiEmployee.getEmployeeNumber());
			toOsiEmployee.setTitle(fromOsiEmployee.getTitle());
			toOsiEmployee.setFirstName(fromOsiEmployee.getFirstName());
			toOsiEmployee.setMiddleName(fromOsiEmployee.getMiddleName());
			toOsiEmployee.setLastName(fromOsiEmployee.getLastName());
			toOsiEmployee.setFullName(fromOsiEmployee.getFullName());
			toOsiEmployee.setEffectiveStartDate(fromOsiEmployee.getEffectiveStartDate());
			toOsiEmployee.setTerminationDate(fromOsiEmployee.getTerminationDate());
			toOsiEmployee.setEmployeeType(fromOsiEmployee.getEmployeeType());
			toOsiEmployee.setGender(fromOsiEmployee.getGender());
			toOsiEmployee.setPrefix(fromOsiEmployee.getPrefix());
			toOsiEmployee.setSuffix(fromOsiEmployee.getSuffix());
			toOsiEmployee.setOriginalDateOfHire(fromOsiEmployee.getOriginalDateOfHire());
			toOsiEmployee.setPhotoId(fromOsiEmployee.getPhotoId());
			toOsiEmployee.setNationality(fromOsiEmployee.getNationality());
			toOsiEmployee.setEffectiveEndDate(fromOsiEmployee.getEffectiveEndDate());
			toOsiEmployee.setPhotoId(fromOsiEmployee.getPhotoId());
			toOsiEmployee.setDateOfBirth(fromOsiEmployee.getDateOfBirth());
			toOsiEmployee.setMaritalStatus(fromOsiEmployee.getMaritalStatus());
			toOsiEmployee.setOnMilitaryService(fromOsiEmployee.getOnMilitaryService());
			toOsiEmployee.setBackgroundCheckStatus(fromOsiEmployee.getBackgroundCheckStatus());
			toOsiEmployee.setBackgroundDateCheck(fromOsiEmployee.getBackgroundDateCheck());
			
			toOsiEmployee.setAttribute1(fromOsiEmployee.getAttribute1());
			toOsiEmployee.setAttribute2(fromOsiEmployee.getAttribute2());
			toOsiEmployee.setAttribute3(fromOsiEmployee.getAttribute3());
			toOsiEmployee.setAttribute4(fromOsiEmployee.getAttribute4());
			toOsiEmployee.setAttribute5(fromOsiEmployee.getAttribute5());
			toOsiEmployee.setAttribute6(fromOsiEmployee.getAttribute6());
			toOsiEmployee.setAttribute7(fromOsiEmployee.getAttribute7());
			toOsiEmployee.setAttribute8(fromOsiEmployee.getAttribute8());
			toOsiEmployee.setAttribute9(fromOsiEmployee.getAttribute9());
			toOsiEmployee.setAttribute10(fromOsiEmployee.getAttribute10());
			toOsiEmployee.setAttribute11(fromOsiEmployee.getAttribute11());
			toOsiEmployee.setAttribute12(fromOsiEmployee.getAttribute12());
			toOsiEmployee.setAttribute13(fromOsiEmployee.getAttribute13());
			toOsiEmployee.setAttribute14(fromOsiEmployee.getAttribute14());
			toOsiEmployee.setAttribute15(fromOsiEmployee.getAttribute15());
			toOsiEmployee.setAttribute16(fromOsiEmployee.getAttribute16());
			toOsiEmployee.setAttribute17(fromOsiEmployee.getAttribute17());
			toOsiEmployee.setAttribute18(fromOsiEmployee.getAttribute18());
			toOsiEmployee.setAttribute19(fromOsiEmployee.getAttribute19());
			toOsiEmployee.setAttribute20(fromOsiEmployee.getAttribute20());
			toOsiEmployee.setAttribute21(fromOsiEmployee.getAttribute21());
			toOsiEmployee.setAttribute22(fromOsiEmployee.getAttribute22());
			toOsiEmployee.setAttribute23(fromOsiEmployee.getAttribute23());
			toOsiEmployee.setAttribute24(fromOsiEmployee.getAttribute24());
			toOsiEmployee.setAttribute25(fromOsiEmployee.getAttribute25());
			
			toOsiEmployee.setSystemType(fromOsiEmployee.getSystemType());
			toOsiEmployee.setEmployeeStatus(fromOsiEmployee.getEmployeeStatus());
			
		}
		return toOsiEmployee;
	}

	@Override
	public OsiEmployees mapToPersonalInfo(OsiEmployees fromOsiEmployee, OsiEmployees toOsiEmployee)
			throws BusinessException {
		if (fromOsiEmployee != null) {
			//toOsiEmployee.setDateOfBirth(fromOsiEmployee.getDateOfBirth());
			//toOsiEmployee.setMaritalStatus(fromOsiEmployee.getMaritalStatus());

			toOsiEmployee.setPersonalEmail(fromOsiEmployee.getPersonalEmail());

			toOsiEmployee.setKnownAs(fromOsiEmployee.getKnownAs());
			toOsiEmployee.setPreviousLastName(fromOsiEmployee.getPreviousLastName());
			toOsiEmployee.setMailAddressId(fromOsiEmployee.getMailAddressId());
			toOsiEmployee.setPermanentAddressId(fromOsiEmployee.getPermanentAddressId());
			toOsiEmployee.setBloodType(fromOsiEmployee.getBloodType());
			
			toOsiEmployee.setResumeExists(fromOsiEmployee.getResumeExists());
			toOsiEmployee.setResumeId(fromOsiEmployee.getResumeId());
			toOsiEmployee.setResumeLastUpdated(fromOsiEmployee.getResumeLastUpdated());
			toOsiEmployee.setTotalExp(fromOsiEmployee.getTotalExp());

		}
		return toOsiEmployee;
	}

	@Override
	public OsiAttachments mapToAttachments(OsiAttachmentsDTO osiAttachmentsDto) throws BusinessException {
		OsiAttachments osiAttachments = null;
		if (osiAttachmentsDto != null) {
			osiAttachments = new OsiAttachments();
			if (osiAttachmentsDto.getAttachmentId() != null)
				osiAttachments.setAttachmentId(osiAttachmentsDto.getAttachmentId());
			osiAttachments.setFileType(osiAttachmentsDto.getFileType());
			osiAttachments.setOriginalFileName(osiAttachmentsDto.getOriginalFileName());
			
			osiAttachments.setAttachmentType(osiAttachmentsDto.getAttachmentType());
			osiAttachments.setObjectId(osiAttachmentsDto.getObjectId());
			osiAttachments.setObjectType(osiAttachmentsDto.getObjectType());
			
			String fileType = osiAttachmentsDto.getOriginalFileName();
			fileType = fileType.substring(fileType.indexOf(".") + 1, fileType.length());
			String attachmentName;
			if(osiAttachmentsDto.getAttachmentType().equalsIgnoreCase("CERTIFICATIONS")) {
				attachmentName = osiAttachmentsDto.getEmployeeId()+"-"+ new Timestamp(commonService.getCurrentDateinUTC().getTime()).getTime() + "." + fileType;
			} else if(osiAttachmentsDto.getAttachmentType().equalsIgnoreCase("ADDITIONAL DOCUMENTS")) {
				attachmentName = osiAttachmentsDto.getDuplicateFileName() + "." + fileType;
			}else {	
				attachmentName = osiAttachmentsDto.getEmployeeId() + "." + fileType;
			}
			osiAttachments.setDuplicateFileName(attachmentName);
			String fileContent = osiAttachmentsDto.getFileContent()
					.replace("data:" + osiAttachmentsDto.getFileType() + ";base64,", "");
			File prDir = new File(appConfig.getImagePath() + File.separator + osiAttachments.getAttachmentType());
			if (!prDir.isDirectory()) {
				prDir.mkdir();
			}
			String attachmentNameWithUploadDirectory = appConfig.getImagePath() + File.separator + osiAttachments.getAttachmentType()
					+ File.separator + Paths.get(attachmentName).toString();// Paths.get(attachmentsDirectory+File.separator+File.separator+attachmentName).toString();
			CommonService commonService = new CommonService();
			try {
				commonService.uploadAttachment(fileContent, attachmentNameWithUploadDirectory);
			} catch (Exception e) {
				throw new BusinessException("ERR_1000", "Unable to Save the Image..");
			}
			if (osiAttachmentsDto.getAttachmentId() != null) {
				osiAttachments.setLastUpdatedBy(osiAttachmentsDto.getCreatedBy());
				osiAttachments.setLastUpdateDate(new Timestamp(new Date().getTime()));
			} else {
				osiAttachments.setCreatedBy(osiAttachmentsDto.getCreatedBy());
				osiAttachments.setCreationDate(new Timestamp(new Date().getTime()));
			}
			osiAttachments = osiAttachmentsRepository.save(osiAttachments);
		}
		return osiAttachments;
	}

 
    @Override
    public OsiEmployees osiEmployeesDTOToOsiEmployees(OsiEmployeesDTO osiEmployeesDTO) {
        if ( osiEmployeesDTO == null ) {
            return null;
        }

        OsiEmployees osiEmployees = new OsiEmployees();

        osiEmployees.setBloodType(osiEmployeesDTO.getBloodType());
        osiEmployees.setEmployeeId(osiEmployeesDTO.getEmployeeId());
        osiEmployees.setLastMedicalTestBy(osiEmployeesDTO.getLastMedicalTestBy());
        osiEmployees.setLastMedicalTestDate(osiEmployeesDTO.getLastMedicalTestDate());
        
        
        
        return osiEmployees;
    }

	@Override
	public OsiEmployees osiEmployeesMedicalInfoToOsiEmployeesDTO(OsiEmployeesDTO osiEmployeesDTO) throws BusinessException, DataAccessException {
		
		OsiEmployees osiEmployees = new OsiEmployees();
		
		try{
			if(osiEmployeesDTO.getEmployeeId() ==null){
				Integer employee_id = osiEmployeesRepository.getMaxEmployeeId();
				osiEmployees.setEmployeeId(employee_id+1);
				osiEmployees.setCreatedBy(osiEmployeesDTO.getCreatedBy());
				//osiEmployees.setCreationDate(osiEmployeesRepository.convertTimestampToString(new java.sql.Timestamp(new Date().getTime())));
				osiEmployees.setCreationDate(commonService.getCurrentDateStringinUTC());
				
			}else{
				osiEmployees = osiEmployeesService.findByEmployeeId(osiEmployeesDTO.getEmployeeId());
				//osiEmployees.setLastUpdateDate(osiEmployeesRepository.convertTimestampToString(new java.sql.Timestamp(new Date().getTime())));
				osiEmployees.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
				osiEmployees.setLastUpdatedBy(osiEmployeesDTO.getCreatedBy());
				
				String effecEndDate = osiEmployeesRepository.currentTimeStamp(0);
				String effecStartDate = osiEmployeesRepository.currentTimeStamp(1);
				
				String UTCEffecEndDateString = commonService.convertDateStringToUTC(effecEndDate, osiEmployeesDTO.getCreatedBy());
				String UTCEffecStartDateString = commonService.convertDateStringToUTC(effecStartDate, osiEmployeesDTO.getCreatedBy());
				
				osiEmployees.setVersion(osiEmployees.getVersion().intValue()+1);
				int updateResult = osiEmployeesRepository.updateEmployeeEffectiveEndDate(osiEmployees, UTCEffecEndDateString);
				osiEmployees.setEffectiveStartDate(UTCEffecStartDateString);
			}
			osiEmployees.setBloodType(osiEmployeesDTO.getBloodType());
			osiEmployees.setLastMedicalTestBy(osiEmployeesDTO.getLastMedicalTestBy());
			osiEmployees.setLastMedicalTestDate(osiEmployeesDTO.getLastMedicalTestDate());
			
		}catch(DataAccessException exception){
			throw new BusinessException("ERR_1000",exception.getSystemMessage());
		}
		return osiEmployees;
	}
	
	@Override
	public OsiEmployees osiEmployeesMedicalInfoToOsiEmployeesDTOUpdate(OsiEmployeesDTO osiEmployeesDTO) throws BusinessException, DataAccessException {
		OsiEmployees osiEmployees = new OsiEmployees();
		try{
			if(osiEmployeesDTO.getEmployeeId() != null){
				osiEmployees = osiEmployeesService.findByEmployeeId(osiEmployeesDTO.getEmployeeId());
				if(osiEmployeesDTO.getVersion().intValue() == osiEmployees.getVersion().intValue()) {
					//osiEmployees.setLastUpdateDate(osiEmployeesRepository.convertTimestampToString(new java.sql.Timestamp(new Date().getTime())));
					osiEmployees.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
					osiEmployees.setLastUpdatedBy(osiEmployeesDTO.getCreatedBy());
					//osiEmployees.setVersion(osiEmployees.getVersion().intValue()+1);
					osiEmployees.setBloodType(osiEmployeesDTO.getBloodType());
					osiEmployees.setLastMedicalTestBy(osiEmployeesDTO.getLastMedicalTestBy());
					osiEmployees.setLastMedicalTestDate(osiEmployeesDTO.getLastMedicalTestDate());
				}else {
					throw new BusinessException("ERR_1005", "Version mismatch");
				}
				
			}
			
		}catch(BusinessException exception){
			throw new BusinessException("ERR_1000",exception.getSystemMessage());
		}
		return osiEmployees;
	}
	public static String convertDBDateFormat(String date) throws BusinessException{
		String dbFormatDate = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
			dbFormatDate = simpleDateFormat.format(simpleDateFormat1.parse(date));
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return dbFormatDate;
	}

	@Override
	public OsiEmployees osiEmployeesOfficeInfoToOsiEmployeesDTO(OsiEmployeesDTO osiEmployeesDTO) throws BusinessException, DataAccessException {
		OsiEmployees osiEmployees = new OsiEmployees();
		
		try{
			if(osiEmployeesDTO.getEmployeeId() ==null){
				Integer employee_id = osiEmployeesRepository.getMaxEmployeeId();
				osiEmployees.setEmployeeId(employee_id+1);
				osiEmployees.setCreatedBy(osiEmployeesDTO.getCreatedBy());
				//osiEmployees.setCreationDate(osiEmployeesRepository.currentTimeStamp(0));
				osiEmployees.setCreationDate(commonService.getCurrentDateStringinUTC());
				
			}else{
				if(osiEmployeesService.findByOfficeEmail(osiEmployeesDTO.getOfficeEmail(),osiEmployeesDTO.getEmployeeId()) == false){
					osiEmployees = osiEmployeesService.findByEmployeeId(osiEmployeesDTO.getEmployeeId());
					//osiEmployees.setLastUpdateDate(osiEmployeesRepository.convertTimestampToString(new java.sql.Timestamp(new Date().getTime())));
					osiEmployees.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
					osiEmployees.setLastUpdatedBy(osiEmployeesDTO.getCreatedBy());
					osiEmployees.setVersion(osiEmployees.getVersion().intValue()+1);
					//osiEmployees.setEffectiveStartDate(osiEmployeesService.convertTimestampToString(osiEmployeesService.updateEffectiveEndDate(osiEmployeesDTO.getEmployeeId())));
					String effecEndDate = osiEmployeesRepository.currentTimeStamp(0);
					String effecStartDate = osiEmployeesRepository.currentTimeStamp(1);
					
					String UTCEffecEndDateString = commonService.convertDateStringToUTC(effecEndDate, osiEmployeesDTO.getCreatedBy());
					String UTCEffecStartDateString = commonService.convertDateStringToUTC(effecStartDate, osiEmployeesDTO.getCreatedBy());
					
					int updateResult = osiEmployeesRepository.updateEmployeeEffectiveEndDate(osiEmployees, UTCEffecEndDateString);
					osiEmployees.setEffectiveStartDate(UTCEffecStartDateString);
				}else{
					throw new BusinessException("ERR_1013","Office mail already exist with other employee");
				}
			}
			
			osiEmployees.setMailStop(osiEmployeesDTO.getMailStop());
			osiEmployees.setFteCapacity(osiEmployeesDTO.getFteCapacity());
			osiEmployees.setOfficeEmail(osiEmployeesDTO.getOfficeEmail());
			osiEmployees.setUserName(osiEmployeesDTO.getUserName());
			osiEmployees.setSerialNumber(osiEmployeesDTO.getSerialNumber());
			
		}catch(BusinessException exception){
			throw new BusinessException(exception.getErrorCode(), exception.getSystemMessage());
		}catch(DataAccessException exception){
			throw new BusinessException("ERR_1000", exception.getSystemMessage());
		}
		return osiEmployees;
	}

	@Override
	public OsiContacts osiEmployeesOfficeInfoToOsiEmployeesContactsDTO(OsiEmployeesDTO osiEmployeesDTO,
			Integer employee_id,String employee_name) throws BusinessException {
		
		OsiContacts osiEmployeeContacts = new OsiContacts();
		try{
			
			osiEmployeeContacts.setContactNumber(osiEmployeesDTO.getOsiEmployeeContacts().getContactNumber());
			osiEmployeeContacts.setContactName(employee_name);
			osiEmployeeContacts.setEmployeeId(employee_id);
			osiEmployeeContacts.setContactType("Extension");
			osiEmployeeContacts.setRelation("Self");
			osiEmployeeContacts.setSeq(employee_id);
			if(osiEmployeesDTO.getOsiEmployeeContacts().getContactId() != null){
				osiEmployeeContacts.setCreatedBy(osiEmployeesDTO.getOsiEmployeeContacts().getCreatedBy());
				osiEmployeeContacts.setCreationDate(osiEmployeesDTO.getOsiEmployeeContacts().getCreationDate());
				osiEmployeeContacts.setLastUpdatedBy(osiEmployeesDTO.getCreatedBy());
				//osiEmployeeContacts.setLastUpdateDate(osiEmployeesRepository.convertTimestampToString(new java.sql.Timestamp(new Date().getTime())));
				osiEmployeeContacts.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
				osiEmployeeContacts.setContactId(osiEmployeesDTO.getOsiEmployeeContacts().getContactId());
				
			}else{
				
				osiEmployeeContacts.setCreatedBy(osiEmployeesDTO.getCreatedBy());
				//osiEmployeeContacts.setCreationDate(osiEmployeesRepository.convertTimestampToString(new java.sql.Timestamp(new Date().getTime())));;
				osiEmployeeContacts.setCreationDate(commonService.getCurrentDateStringinUTC());
				osiEmployeeContacts.setLastUpdatedBy(osiEmployeesDTO.getCreatedBy());
				osiEmployeeContacts.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
			}
		}
		catch(Exception e){
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiEmployeeContacts;
		
	}

	@Override
	public OsiEmployees osiEmployeesOfficeInfoToOsiEmployeesDTOUpdate(OsiEmployeesDTO osiEmployeesDTO)
			throws BusinessException, DataAccessException {
		OsiEmployees osiEmployees = new OsiEmployees();
		
		try{
			if(osiEmployeesDTO.getEmployeeId() != null){
				if(osiEmployeesService.findByOfficeEmail(osiEmployeesDTO.getOfficeEmail(),osiEmployeesDTO.getEmployeeId()) == false){
				osiEmployees = osiEmployeesService.findByEmployeeId(osiEmployeesDTO.getEmployeeId());
					if(osiEmployeesDTO.getVersion().intValue() == osiEmployees.getVersion().intValue()) {
						//osiEmployees.setLastUpdateDate(osiEmployeesRepository.convertTimestampToString(new java.sql.Timestamp(new Date().getTime())));
						osiEmployees.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
						osiEmployees.setLastUpdatedBy(osiEmployeesDTO.getCreatedBy());
						osiEmployees.setMailStop(osiEmployeesDTO.getMailStop());
						osiEmployees.setFteCapacity(osiEmployeesDTO.getFteCapacity());
						osiEmployees.setOfficeEmail(osiEmployeesDTO.getOfficeEmail());
						osiEmployees.setUserName(osiEmployeesDTO.getUserName());
						osiEmployees.setSerialNumber(osiEmployeesDTO.getSerialNumber());
					}else {
						throw new BusinessException("ERR_1005", "Version mismatch");
					}
				}else{
					throw new BusinessException("ERR_1005", "Office mail already exist with other employee");
				}
				
			}
			
			
		}catch(BusinessException exception){
			throw new BusinessException("ERR_1000", exception.getSystemMessage());
		}
		return osiEmployees;
	}

}
