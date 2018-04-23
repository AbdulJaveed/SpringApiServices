package com.osi.urm.repository.custom;

import java.util.List;

import com.osi.urm.domain.OsiAttachments;
import com.osi.urm.domain.OsiRespUser;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.domain.OsiUserFuncExcl;
import com.osi.urm.domain.OsiUserOperationExcl;
import com.osi.urm.exception.DataAccessException;

public interface OsiUserRepositoryCustom {
	public Integer deleteUser(List<Integer> id, Integer businessGroupId, Integer userId) throws DataAccessException;
	public void updateUser(OsiUser osiUser) throws DataAccessException;
	public OsiUser findUserById(Integer userId,Integer businessGroupId) throws DataAccessException;
	public List<OsiRespUser> findUserResponsibilities(Integer userId,Integer businessGroupId) throws DataAccessException;
	public List<OsiUserFuncExcl> getUserFunctionExclusions(Integer userId, Integer businessGroupId) throws DataAccessException;
	public List<OsiUserOperationExcl> getUserOperationExclusions(Integer userId, Integer businessGroupId) throws DataAccessException;
	public List<OsiAttachments> getUserAttachments(Integer userId, Integer businessGroupId) throws DataAccessException;
	public OsiUser updateUserProfile(OsiUser osiUser) throws DataAccessException;
	public Integer updatePassword(OsiUser osiUser) throws DataAccessException;
	public List<OsiUser> searchUser(Integer businessGroupId,String userName,String empNumber,String emailId, String firstName, String lastName)throws DataAccessException;
	OsiUser updateResetUserPassword(OsiUser osiUser) throws DataAccessException;
}  