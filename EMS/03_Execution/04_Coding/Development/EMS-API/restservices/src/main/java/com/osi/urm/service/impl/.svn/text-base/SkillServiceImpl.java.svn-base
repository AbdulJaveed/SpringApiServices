package com.osi.urm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiSkils;
import com.osi.ems.mapper.impl.CrudOsiSkilsAssembler;
import com.osi.ems.repository.OsiSkillRepository;
import com.osi.urm.domain.SkillDetails;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiSkillRepositryCustom;
import com.osi.urm.service.SkillsService;

public class SkillServiceImpl implements SkillsService {

	@Autowired
	private OsiSkillRepositryCustom OsiSkillRepositryCustom;

	@Autowired
	private OsiSkillRepository osiSkillRepository;

	@Autowired
	private CrudOsiSkilsAssembler crudOsiSkilsAssembler;

	@Autowired
	private CommonService commonService;

	@Override
	public List<SkillDetails> getSkillDetails() {
		List<SkillDetails> skillDetails = null;
		List<OsiSkils> osiSkillList = null;
		try {

			// skillDetails = OsiSkillRepositryCustom.getSkillDetails();
			osiSkillList = this.osiSkillRepository.findByActive(1);
			
			/*
			 * Remove the dupllicates.
			 */
			skillDetails = this.crudOsiSkilsAssembler.toOsiSkillDetailsList(osiSkillList);

		} catch (Exception expected) {
			expected.printStackTrace();
		}

		return skillDetails;
	}

	@Override
	public List<SkillDetails> searchSkillDetails(SkillDetails skillDetails) {

		return OsiSkillRepositryCustom.searchSkillDetails(skillDetails);
	}

	@Override
	public int saveSkills(SkillDetails skillDetails, Integer orgId) throws BusinessException {
		// TODO Auto-generated method stub
		boolean result;
		try {
			skillDetails.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
			/*
			 * if(skillDetails.getEmpSkillId() != null &&
			 * !"".equalsIgnoreCase(skillDetails.getEmpSkillId())){ result =false; }else{
			 * result
			 * =OsiSkillRepositryCustom.findBySkillIdAndEmployeeId(skillDetails.getSkillId()
			 * , skillDetails.getEmployeeId()); }
			 * 
			 * if( result == false)
			 */
			return OsiSkillRepositryCustom.saveSkills(skillDetails, orgId);
			/*
			 * else throw new BusinessException("ERR_1000",
			 * "Entered skill is alreay exist");
			 */
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (DuplicateKeyException e) {
			throw new BusinessException("ERR_1000", "Entered skill is alreay exist");
		}
	}

	@Override
	public List<SkillDetails> getSkillDetailsById(int skillId) {
		// TODO Auto-generated method stub
		return OsiSkillRepositryCustom.getSkillDetailsById(skillId);
	}

	@Override
	public void updateVerifiedSkills(List<SkillDetails> skillDetailsList, int updatedBy) throws BusinessException {

		try {
			for (SkillDetails skill : skillDetailsList) {
				skill.setUpdatedBy("" + updatedBy);
				skill.setLastUpdateDate(commonService.getCurrentDateStringinUTC());
				OsiSkillRepositryCustom.updateVerifiedSkills(skill);
			}
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}

	}

}
