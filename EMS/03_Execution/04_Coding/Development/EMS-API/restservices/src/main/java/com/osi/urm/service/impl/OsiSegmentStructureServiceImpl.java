package com.osi.urm.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.urm.domain.OsiKeyFlex;
import com.osi.urm.domain.OsiSegmentStructureDetails;
import com.osi.urm.domain.OsiSegmentStructureHdr;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.OsiSegmentStructureHdrRepository;
import com.osi.urm.service.OsiSegmentStructureService;
import com.osi.urm.service.dto.OsiKeyFlexDTO;
import com.osi.urm.service.dto.OsiLookupValuesDTO;
import com.osi.urm.service.dto.OsiSegmentStructureDetailsDTO;
import com.osi.urm.service.dto.OsiSegmentStructureHdrDTO;
import com.osi.urm.service.dto.OsiUserDTO;

/**
 * Service Implementation for managing OsiSegmentStructureHdr.
 */
@Service
@Transactional
public class OsiSegmentStructureServiceImpl implements OsiSegmentStructureService {

	private final Logger log = LoggerFactory.getLogger(OsiSegmentStructureServiceImpl.class);

	@Autowired
	private OsiSegmentStructureHdrRepository osiSegmentStructureHdrRepository;
	
	@Autowired
	private CommonService commonService;

	/*
	 * @Autowired private OsiSegmentStructureMapper
	 * osiSegmentStructureHdrMapper;
	 */

	/**
	 * Save a osiSegmentStructureHdr.
	 *
	 * @param osiSegmentStructureHdrDTO
	 *            the entity to save
	 * @return the persisted entity
	 */

	@Override
	public OsiSegmentStructureHdrDTO save(OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO, Integer userId)
			throws BusinessException {

		String errorCode = "";
		boolean isValidationSuccess = false;

		try {
			if (osiSegmentStructureHdrDTO.getSegmentStructureHdrDesc() != null&& !osiSegmentStructureHdrDTO.getSegmentStructureHdrDesc().equals("")) {
				Integer existingSegmentStructureHdrIds = osiSegmentStructureHdrRepository.validateUniqueSegmentStructureHdrDesc(osiSegmentStructureHdrDTO.getSegmentStructureHdrDesc().toUpperCase());
				if (osiSegmentStructureHdrDTO.getSegmentStructureHdrId() != null) {
					if (existingSegmentStructureHdrIds != null) {
						if (existingSegmentStructureHdrIds.equals(osiSegmentStructureHdrDTO.getSegmentStructureHdrId())) {
							isValidationSuccess = true;
						}
					} else {
						isValidationSuccess = true;
					}
				} else {
					if (existingSegmentStructureHdrIds != null) {
						isValidationSuccess = false;
					} else {
						isValidationSuccess = true;
					}
				}
			}
			OsiSegmentStructureHdr osiSegmentStructureHdr = new OsiSegmentStructureHdr();

			if (isValidationSuccess && osiSegmentStructureHdrDTO.getSegmentStructureHdrDesc() != null
					&& !osiSegmentStructureHdrDTO.getSegmentStructureHdrDesc().equals("")) {

				osiSegmentStructureHdr.setActive(1);
				osiSegmentStructureHdr.setSegmentStructureHdrId(osiSegmentStructureHdrDTO.getSegmentStructureHdrId());
				osiSegmentStructureHdr
						.setSegmentStructureHdrDesc(osiSegmentStructureHdrDTO.getSegmentStructureHdrDesc());
			//	osiSegmentStructureHdr.setBusinessGroupId(osiSegmentStructureHdrDTO.getBusinessGroupId());

				Set<OsiSegmentStructureDetails> osiSegmentStructureDetailsSet = new HashSet<OsiSegmentStructureDetails>();
				for (OsiSegmentStructureDetailsDTO osiSegmentStructureDetailsDTO : osiSegmentStructureHdrDTO
						.getOsiSegmentStructureDetailses()) {
					OsiSegmentStructureDetails osiSegmentStructureDetails = new OsiSegmentStructureDetails();
					//osiSegmentStructureDetails.setBusinessGroupId(osiSegmentStructureHdrDTO.getBusinessGroupId());
					osiSegmentStructureDetails.setOsiSegmentStructureHdr(osiSegmentStructureHdr);
					osiSegmentStructureDetails.setLovDataForValidation(osiSegmentStructureDetailsDTO.getLovDataForValidation());
					osiSegmentStructureDetails.setIsSqlReqdForValidation(osiSegmentStructureDetailsDTO.getIsSqlReqdForValidation());
					osiSegmentStructureDetails.setSqlQueryForValidation(osiSegmentStructureDetailsDTO.getSqlQueryForValidation());
					osiSegmentStructureDetails.setSegmentStructureDetailsSeq(osiSegmentStructureDetailsDTO.getSegmentStructureDetailsSeq());
					osiSegmentStructureDetails.setSegmentStructureDetailsDesc(osiSegmentStructureDetailsDTO.getSegmentStructureDetailsDesc());
					osiSegmentStructureDetails.setCreatedBy(userId);
					osiSegmentStructureDetails.setUpdatedBy(userId);
					osiSegmentStructureDetailsSet.add(osiSegmentStructureDetails);
				}
				osiSegmentStructureHdr.setOsiSegmentStructureDetailses(osiSegmentStructureDetailsSet);

				if (osiSegmentStructureHdrDTO.getSegmentStructureHdrId() != null) {
					errorCode = "ERR_1016";
					osiSegmentStructureHdr
							.setSegmentStructureHdrId(osiSegmentStructureHdrDTO.getSegmentStructureHdrId());

					osiSegmentStructureHdr.setUpdatedBy(userId);
					//osiSegmentStructureHdr.setUpdatedDate(new Date());
					osiSegmentStructureHdr.setUpdatedDate(commonService.getCurrentDateinUTC());

					osiSegmentStructureHdr = osiSegmentStructureHdrRepository
							.updateOsiSegmentStructureHdr(osiSegmentStructureHdr);

				} else {

					errorCode = "ERR_1017";
					osiSegmentStructureHdr.setCreatedBy(userId);
					//osiSegmentStructureHdr.setCreatedDate(new Date());
					osiSegmentStructureHdr.setCreatedDate(commonService.getCurrentDateinUTC());
					osiSegmentStructureHdr.setUpdatedBy(userId);
					//osiSegmentStructureHdr.setUpdatedDate(new Date());
					osiSegmentStructureHdr.setUpdatedDate(commonService.getCurrentDateinUTC());
					osiSegmentStructureHdr = osiSegmentStructureHdrRepository.save(osiSegmentStructureHdr);
				}
				osiSegmentStructureHdrDTO.setSegmentStructureHdrId(osiSegmentStructureHdr.getSegmentStructureHdrId());
				osiSegmentStructureHdrDTO
						.setSegmentStructureHdrDesc(osiSegmentStructureHdr.getSegmentStructureHdrDesc());

			} else {
				errorCode = "ERR_1021";
				if (!isValidationSuccess)
					errorCode = "ERR_1053";
				throw new Exception();
			}

		} catch (DataAccessException e) {
			throw new BusinessException(errorCode, e.getSystemMessage());
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException("ERR_1004", e.getMessage());
		} catch (Exception e) {
			throw new BusinessException(errorCode, e.getMessage());
		}

		return osiSegmentStructureHdrDTO;
	}

	/**
	 * Get all the osiSegmentStructureHdrs.
	 * 
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<OsiSegmentStructureHdrDTO> findAll(Integer businessGroupId) throws BusinessException {

		String errorCode = "";
		List<OsiSegmentStructureHdrDTO> osiSegmentStructureHdrDTOList = new ArrayList<OsiSegmentStructureHdrDTO>();

		try {

			List<OsiSegmentStructureHdr> result = osiSegmentStructureHdrRepository
					.findAllOsiSegmentStructureHdr(businessGroupId);

			for (OsiSegmentStructureHdr osiSegmentStructureHdr : result) {
				OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO = new OsiSegmentStructureHdrDTO();

				osiSegmentStructureHdrDTO.setActive(1);
				//osiSegmentStructureHdrDTO.setBusinessGroupId(osiSegmentStructureHdr.getBusinessGroupId());
				osiSegmentStructureHdrDTO.setSegmentStructureHdrId(osiSegmentStructureHdr.getSegmentStructureHdrId());
				osiSegmentStructureHdrDTO
						.setSegmentStructureHdrDesc(osiSegmentStructureHdr.getSegmentStructureHdrDesc());
				osiSegmentStructureHdrDTO
						.setSegmentStructureHdrDesc(osiSegmentStructureHdr.getSegmentStructureHdrDesc());

				osiSegmentStructureHdrDTOList.add(osiSegmentStructureHdrDTO);

				Set<OsiSegmentStructureDetailsDTO> osiSegmentStructureDetailsDTOSet = new HashSet<OsiSegmentStructureDetailsDTO>();

				for (OsiSegmentStructureDetails osiSegmentStructureDetails : osiSegmentStructureHdr
						.getOsiSegmentStructureDetailses()) {
					OsiSegmentStructureDetailsDTO osiSegmentStructureDetailsDTO = new OsiSegmentStructureDetailsDTO();

				//	osiSegmentStructureDetailsDTO.setBusinessGroupId(osiSegmentStructureDetails.getBusinessGroupId());
					// osiSegmentStructureDetailsDTO.setOsiSegmentStructureHdr(osiSegmentStructureHdrDTO);
					osiSegmentStructureDetailsDTO
							.setLovDataForValidation(osiSegmentStructureDetails.getLovDataForValidation());
					osiSegmentStructureDetailsDTO
							.setSegmentStructureDetailsSeq(osiSegmentStructureDetails.getSegmentStructureDetailsSeq());
					osiSegmentStructureDetailsDTO.setSegmentStructureDetailsDesc(
							osiSegmentStructureDetails.getSegmentStructureDetailsDesc());
					osiSegmentStructureDetailsDTOSet.add(osiSegmentStructureDetailsDTO);
				}
				osiSegmentStructureHdrDTO.setOsiSegmentStructureDetailses(osiSegmentStructureDetailsDTOSet);

			}

		} catch (DataIntegrityViolationException e) {
			throw new BusinessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			throw new BusinessException(errorCode, e.getMessage());
		}

		/*
		 * log.debug("Request to get all OsiSegmentStructureHdrs");
		 * Page<OsiSegmentStructureHdr> result =
		 * osiSegmentStructureHdrRepository.findAll(pageable); return
		 * result.map(osiSegmentStructureHdr -> osiSegmentStructureHdrMapper.
		 * osiSegmentStructureHdrToOsiSegmentStructureHdrDTO(
		 * osiSegmentStructureHdr));
		 */
		return osiSegmentStructureHdrDTOList;
	}

	/**
	 * Get one osiSegmentStructureHdr by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public OsiSegmentStructureHdrDTO findOne(Integer segmentStructureHdrId, Integer businessGroupId)
			throws BusinessException {

		OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO = new OsiSegmentStructureHdrDTO();
		OsiSegmentStructureHdr result = osiSegmentStructureHdrRepository
				.findOneSegmentStructureHdr(segmentStructureHdrId);

		try {
			if (result != null) {
				osiSegmentStructureHdrDTO.setActive(result.getActive());
				//osiSegmentStructureHdrDTO.setBusinessGroupId(result.getBusinessGroupId());
				osiSegmentStructureHdrDTO.setSegmentStructureHdrId(result.getSegmentStructureHdrId());
				osiSegmentStructureHdrDTO.setSegmentStructureHdrDesc(result.getSegmentStructureHdrDesc());
				osiSegmentStructureHdrDTO.setSegmentStructureHdrDesc(result.getSegmentStructureHdrDesc());
				Set<OsiSegmentStructureDetailsDTO> osiSegmentStructureDetailsDTOSet = new HashSet<OsiSegmentStructureDetailsDTO>();
				for (OsiSegmentStructureDetails osiSegmentStructureDetails : result.getOsiSegmentStructureDetailses()) {
					OsiSegmentStructureDetailsDTO osiSegmentStructureDetailsDTO = new OsiSegmentStructureDetailsDTO();
					osiSegmentStructureDetailsDTO.setIsSqlReqdForValidation(osiSegmentStructureDetails.getIsSqlReqdForValidation());
					osiSegmentStructureDetailsDTO.setSegmentStructureDetailsId(osiSegmentStructureDetails.getSegmentStructureDetailsId());
					
					osiSegmentStructureDetailsDTO.setSqlQueryForValidation(osiSegmentStructureDetails.getSqlQueryForValidation());
					//osiSegmentStructureDetailsDTO.setBusinessGroupId(osiSegmentStructureDetails.getBusinessGroupId());
					// osiSegmentStructureDetailsDTO.setOsiSegmentStructureHdr(osiSegmentStructureHdrDTO);
					osiSegmentStructureDetailsDTO
							.setLovDataForValidation(osiSegmentStructureDetails.getLovDataForValidation());
					osiSegmentStructureDetailsDTO
							.setSegmentStructureDetailsSeq(osiSegmentStructureDetails.getSegmentStructureDetailsSeq());
					osiSegmentStructureDetailsDTO.setSegmentStructureDetailsDesc(
							osiSegmentStructureDetails.getSegmentStructureDetailsDesc());
					osiSegmentStructureDetailsDTOSet.add(osiSegmentStructureDetailsDTO);
				}
				osiSegmentStructureHdrDTO.setOsiSegmentStructureDetailses(osiSegmentStructureDetailsDTOSet);

			}

		} catch (DataIntegrityViolationException e) {
			throw new BusinessException("ERR_1091", e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1089", e.getMessage());
		}

		/*
		 * log.debug("Request to get OsiSegmentStructureHdr : {}", id);
		 * OsiSegmentStructureHdr osiSegmentStructureHdr =
		 * osiSegmentStructureHdrRepository.findOne(id);
		 * OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO =
		 * osiSegmentStructureHdrMapper.
		 * osiSegmentStructureHdrToOsiSegmentStructureHdrDTO(
		 * osiSegmentStructureHdr); return osiSegmentStructureHdrDTO;
		 */
		return osiSegmentStructureHdrDTO;
	}

	@Override
	public int deleteAll(List<Integer> segmentStructureHdrIds, OsiUserDTO user) throws BusinessException {

		int count = 0;
		String errorCode = "";

		// OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO = new
		// OsiSegmentStructureHdrDTO();

		/*
		 * try { count =
		 * osiSegmentStructureHdrRepository.deleteSegmentStructureHdr(
		 * segmentStructureHdrIds, user); } catch (DataAccessException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
		try {

			if (segmentStructureHdrIds != null && !segmentStructureHdrIds.isEmpty()) {

				count = osiSegmentStructureHdrRepository.deleteSegmentStructureHdr(segmentStructureHdrIds, user);
			}
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException("ERR_1021", e.getMessage());
		} catch (Exception e) {
			throw new BusinessException(errorCode, e.getMessage());
		}

		return count;
	}

	@Override
	public List<OsiSegmentStructureDetailsDTO> getOsiSegmentStructureValues(String structureName,
			Integer businessGroupId) throws BusinessException {
		List<OsiSegmentStructureDetailsDTO> osiSegmentStructureDetailsDTOList = null;
		List<OsiSegmentStructureDetails> osiSegmentStructureDetailsList = null;
		Set<OsiLookupValuesDTO> osiLookupValuesDTOList = null;
		try {
			osiSegmentStructureDetailsList = osiSegmentStructureHdrRepository.getSegmentStructureValues(structureName,
					businessGroupId);
			osiSegmentStructureDetailsDTOList = new ArrayList<OsiSegmentStructureDetailsDTO>();
			String priviousCode = null;
			OsiLookupValuesDTO osiLookupValuesDTO = null;
			OsiSegmentStructureDetailsDTO osiSegmentStructureDetailsDTO = null;
			for (Iterator iterator = osiSegmentStructureDetailsList.iterator(); iterator.hasNext();) {
				Object[] obj = (Object[]) iterator.next();
				if (priviousCode == null
						|| (priviousCode != null && obj[3] != null && !priviousCode.equals(obj[3].toString()))) {
					osiSegmentStructureDetailsDTO = new OsiSegmentStructureDetailsDTO();
					osiSegmentStructureDetailsDTO.setOsiSegmentStructureHdrId(Integer.parseInt(obj[0].toString()));
					osiSegmentStructureDetailsDTO.setLovDataForValidation(obj[3].toString());
					osiSegmentStructureDetailsDTO.setSegmentStructureDetailsSeq(Integer.parseInt(obj[1].toString()));
					osiSegmentStructureDetailsDTO.setSegmentStructureDetailsDesc(obj[2].toString());
					if(obj[6]!=null)
						osiSegmentStructureDetailsDTO.setIsSqlReqdForValidation(Integer.parseInt(obj[6].toString()));
					if(obj[7]!=null)
						osiSegmentStructureDetailsDTO.setSqlQueryForValidation(obj[7].toString());
					osiLookupValuesDTOList = new HashSet<OsiLookupValuesDTO>();
					osiLookupValuesDTO = new OsiLookupValuesDTO();
					osiLookupValuesDTO.setLookupValue(obj[4].toString());
					if(obj[5]!=null)
						osiLookupValuesDTO.setLookupDesc(obj[5].toString());
					else
						osiLookupValuesDTO.setLookupDesc(null);
					osiLookupValuesDTOList.add(osiLookupValuesDTO);
					osiSegmentStructureDetailsDTO.setOsiLookupValuesList(osiLookupValuesDTOList);
					osiSegmentStructureDetailsDTOList.add(osiSegmentStructureDetailsDTO);
				} else {
					osiLookupValuesDTO = new OsiLookupValuesDTO();
					osiLookupValuesDTO.setLookupValue(obj[4].toString());
					if(obj[5]!=null)
						osiLookupValuesDTO.setLookupDesc(obj[5].toString());
					else
						osiLookupValuesDTO.setLookupDesc(null);
					osiLookupValuesDTOList.add(osiLookupValuesDTO);
				}
				priviousCode = obj[3].toString();
			}

		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return osiSegmentStructureDetailsDTOList;
	}

	@Override
	public Integer findOne(String segmentStructureHdrDesc) throws BusinessException {
		// TODO Auto-generated method stub
		
		Integer osiSegmentStructureHdr = osiSegmentStructureHdrRepository
				.findOneSegmentStructureHdrId(segmentStructureHdrDesc);
		return osiSegmentStructureHdr;
	}

	@Override
	public List<OsiKeyFlexDTO> getOsiKeyFlexFields(Integer segmentStructureHdrId, Integer businessGroupId)
			throws BusinessException {
		List<OsiKeyFlexDTO> osiKeyFlexDTOList = null;
		List<OsiKeyFlex> osiKeyFlexList = null;
		try {
			osiKeyFlexList = osiSegmentStructureHdrRepository.getRemainingKeyFlexFields(segmentStructureHdrId);

			if (osiKeyFlexList == null || (osiKeyFlexList != null && osiKeyFlexList.size() == 0)) {
				throw new DataAccessException("ERR_1002", null);
			}
			osiKeyFlexDTOList = new ArrayList<OsiKeyFlexDTO>();
			for (OsiKeyFlex osiKeyFlex : osiKeyFlexList) {
				OsiKeyFlexDTO osiKeyFlexDTO = new OsiKeyFlexDTO();
				osiKeyFlexDTO.setName(osiKeyFlex.getName());
				osiKeyFlexDTO.setValue(osiKeyFlex.getValue());
				osiKeyFlexDTOList.add(osiKeyFlexDTO);
			}
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1089", e.getMessage());
		}
		return osiKeyFlexDTOList;
	}

	@Override
	public List<OsiSegmentStructureHdrDTO> searchSegment(OsiSegmentStructureHdrDTO segmentSearchInput,
			Integer businessGroupId) throws BusinessException {
		List<OsiSegmentStructureHdrDTO> osiSegmentStructureHdrDTOList = new ArrayList<OsiSegmentStructureHdrDTO>();

		try {
			StringBuffer queryParam = new StringBuffer("");
			if (segmentSearchInput.getSegmentStructureHdrDesc() != null
					&& !segmentSearchInput.getSegmentStructureHdrDesc().equals("")) {
				queryParam.append(" AND UPPER(kf.name) LIKE '%"
						+ segmentSearchInput.getSegmentStructureHdrDesc().toUpperCase() + "%' ");
			}

			List<OsiSegmentStructureHdr> result = osiSegmentStructureHdrRepository
					.searchOsiSegmentStructureHdr(queryParam.toString(), businessGroupId);

			for (OsiSegmentStructureHdr osiSegmentStructureHdr : result) {
				OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO = new OsiSegmentStructureHdrDTO();

				osiSegmentStructureHdrDTO.setActive(1);
				//osiSegmentStructureHdrDTO.setBusinessGroupId(osiSegmentStructureHdr.getBusinessGroupId());
				osiSegmentStructureHdrDTO.setSegmentStructureHdrId(osiSegmentStructureHdr.getSegmentStructureHdrId());
				osiSegmentStructureHdrDTO
						.setSegmentStructureHdrDesc(osiSegmentStructureHdr.getSegmentStructureHdrDesc());
				osiSegmentStructureHdrDTO
						.setSegmentStructureHdrDesc(osiSegmentStructureHdr.getSegmentStructureHdrDesc());

				osiSegmentStructureHdrDTOList.add(osiSegmentStructureHdrDTO);

				Set<OsiSegmentStructureDetailsDTO> osiSegmentStructureDetailsDTOSet = new HashSet<OsiSegmentStructureDetailsDTO>();

				for (OsiSegmentStructureDetails osiSegmentStructureDetails : osiSegmentStructureHdr
						.getOsiSegmentStructureDetailses()) {
					OsiSegmentStructureDetailsDTO osiSegmentStructureDetailsDTO = new OsiSegmentStructureDetailsDTO();

					//osiSegmentStructureDetailsDTO.setBusinessGroupId(osiSegmentStructureDetails.getBusinessGroupId());
					// osiSegmentStructureDetailsDTO.setOsiSegmentStructureHdr(osiSegmentStructureHdrDTO);
					osiSegmentStructureDetailsDTO
							.setLovDataForValidation(osiSegmentStructureDetails.getLovDataForValidation());
					osiSegmentStructureDetailsDTO
							.setSegmentStructureDetailsSeq(osiSegmentStructureDetails.getSegmentStructureDetailsSeq());
					osiSegmentStructureDetailsDTO.setSegmentStructureDetailsDesc(
							osiSegmentStructureDetails.getSegmentStructureDetailsDesc());
					osiSegmentStructureDetailsDTOSet.add(osiSegmentStructureDetailsDTO);
				}
				osiSegmentStructureHdrDTO.setOsiSegmentStructureDetailses(osiSegmentStructureDetailsDTOSet);

			}
			
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1092", e.getMessage());
		}
		return osiSegmentStructureHdrDTOList;
	}
	public OsiSegmentStructureHdrDTO isSegmentUsedinCategoryOrCoa(Integer segmentHdrId,Integer businessGroupId,String segmentHdrName) throws BusinessException{
		OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO=new OsiSegmentStructureHdrDTO();		
		try{
			if(segmentHdrName.equalsIgnoreCase("Category")){
			 Long catCount=osiSegmentStructureHdrRepository.isCategoryCreatedWithThisStucture(segmentHdrId, businessGroupId);
			 osiSegmentStructureHdrDTO.setSegHdrName("category");
			 if(catCount>0){
					osiSegmentStructureHdrDTO.setEditable(0);
				}else{
					osiSegmentStructureHdrDTO.setEditable(1);
				}
			}
			else{
			Long coaCount=osiSegmentStructureHdrRepository.isCoaCreatedWithThisStucture(segmentHdrId, businessGroupId);
			 osiSegmentStructureHdrDTO.setSegHdrName("coa");
				if(coaCount>0){
					osiSegmentStructureHdrDTO.setEditable(0);
				}else{
					osiSegmentStructureHdrDTO.setEditable(1);
				}
			}
		}catch (Exception e) {
			throw new BusinessException("ERR_1066", e.getMessage());
		}
		return osiSegmentStructureHdrDTO;
		
	}

	@Override
	public OsiSegmentStructureHdrDTO getOsiSegmentStructureValues(Integer businessGroupId) throws BusinessException {
		OsiSegmentStructureHdrDTO segmentStructureDto = null;
		OsiKeyFlex keyFlex = osiSegmentStructureHdrRepository.getKeyFlexInfo(businessGroupId);
		if(null != keyFlex) {
			String segmentStructureHdrDesc = keyFlex.getValue();
			Integer segmentStructureHdrId = osiSegmentStructureHdrRepository.findOneActiveSegmentStructureHdrId(segmentStructureHdrDesc);
			if(null != segmentStructureHdrId) {
				segmentStructureDto = this.findOne(segmentStructureHdrId, businessGroupId);
			} else {
				throw new BusinessException("ERR_1009", "No Active Rule is available for this organization");
			}
		} else {
			throw new BusinessException("ERR_1008", "No Rule Mapped for this organization");
		}
		return segmentStructureDto;
	}
}
