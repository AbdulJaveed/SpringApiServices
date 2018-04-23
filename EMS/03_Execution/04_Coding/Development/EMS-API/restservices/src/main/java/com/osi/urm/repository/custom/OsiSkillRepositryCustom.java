package com.osi.urm.repository.custom;

import java.util.List;

import com.osi.urm.domain.SkillDetails;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiSkillRepositryCustom {
	

	public List<SkillDetails> getSkillDetails();
	
	public List<SkillDetails> searchSkillDetails( SkillDetails osiSkillDetails );
	
	public int saveSkills( SkillDetails osiSkillDetails, Integer orgId  ) throws DataAccessException;
	
	public List<SkillDetails> getSkillDetailsById(int skillId);
	
	public boolean findBySkillIdAndEmployeeId(String skillId,String employeeId) throws BusinessException;

	int updateVerifiedSkills(SkillDetails skillDetails) throws DataAccessException;
	

}
