package com.osi.urm.repository.custom;

import java.util.List;

import com.osi.urm.exception.DataAccessException;

public interface OsiOperationsRepositoryCustom {
	public List getUserExlOperations(Integer userId, Integer functionId, Integer businessGroupId) throws DataAccessException;
	public void deleteByUserId(Integer userId, Integer functionId, Integer businessGroupId) throws DataAccessException;
	Integer deleteByUserId(Integer userId) throws DataAccessException;
	Integer deleteFuncExclByUserId(Integer userId) throws DataAccessException;
}
