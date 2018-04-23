package com.osi.urm.repository.custom;

import java.util.List;

import com.osi.urm.domain.OsiLookupTypes;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.service.dto.OsiUserDTO;

public interface OsiLookupRepositoryCustom {
	
	public OsiLookupTypes updateLookup(OsiLookupTypes osiLookupTypes) throws DataAccessException;

	public int deleteLookup(List<Long> ids,OsiUserDTO user) throws DataAccessException;
	
	public List<OsiLookupTypes> searchLookup(String queryParam,Integer businessGroupId) throws DataAccessException; 
	
	public Long isLookupUsedinCategory(Long lookupId,Integer businessGroupId) throws DataAccessException;
	
	public Long isLookupUsedinCOa(Long lookupId,Integer businessGroupId) throws DataAccessException;
	
	public Long isLookupUsedinInvOrg(Long lookupId,Integer businessGroupId) throws DataAccessException;
	
	public Long isLookupUsedinUserCategory(Long lookupId,Integer businessGroupId) throws DataAccessException;
	
	public Long isLookupUsedinPrHeader(Long lookupId,Integer businessGroupId) throws DataAccessException;
	
	public Long isLookupUsedinPoHeader(Long lookupId,Integer businessGroupId) throws DataAccessException;
	
	public Long isLookupUsedinPrReqLines(Long lookupId,Integer businessGroupId) throws DataAccessException;
	
	public Long isLookupUsedinPoReqLines(Long lookupId,Integer businessGroupId) throws DataAccessException;
	
}
