package com.osi.urm.service;

import java.util.List;

import com.osi.urm.domain.SkillDetails;
import com.osi.urm.exception.BusinessException;

public interface SkillsService {
	
	public List<SkillDetails> getSkillDetails();
	public List<SkillDetails> searchSkillDetails( SkillDetails skillDetails );

	public int saveSkills( SkillDetails skillDetails, Integer orgId ) throws BusinessException;
	
	public List<SkillDetails> getSkillDetailsById(int skillId);
	
	public void updateVerifiedSkills(List<SkillDetails> skillDetailsList, int updatedBy) throws BusinessException;

}
