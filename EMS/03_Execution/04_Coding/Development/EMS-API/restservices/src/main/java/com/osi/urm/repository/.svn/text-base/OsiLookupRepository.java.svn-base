package com.osi.urm.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.osi.urm.domain.OsiLookupTypes;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiLookupRepositoryCustom;

/**
 * Spring Data JPA repository for the OsiLookupTypes entity.
 */
@Repository
public interface OsiLookupRepository extends JpaRepository<OsiLookupTypes,Long>, OsiLookupRepositoryCustom {
	
		/*@Query("SELECT t FROM OsiLookupTypes t where t.businessGroupId = :businessGroupId  ORDER BY t.updatedDate desc")
		public List<OsiLookupTypes> findAllLookups(@Param("businessGroupId") Integer businessGroupId);*/
		
		List<OsiLookupTypes> findLookupsOrderByLookupName(Pageable pageObject) throws DataAccessException;

	    @Query("SELECT t FROM OsiLookupTypes t where 1=1 AND t.active=1")
	    public List<OsiLookupTypes> findAllActiveLookups();
	   
	    @Query("SELECT t FROM OsiLookupTypes t where  1=1 AND t.id=:id")
	    public OsiLookupTypes findSingleActiveLookup(@Param("id") Long id);
	    
	    @Query("SELECT t.id FROM OsiLookupTypes t where 1=1 AND UPPER(t.lookupCode)=:lookupCode")
	    public List<Long> validateUniqueLookupCode(@Param("lookupCode") String lookupCode);
	    
	    @Query("SELECT t.id FROM OsiLookupTypes t where 1=1 AND UPPER(t.lookupName)=:lookupName")
	    public List<Long> validateUniqueLookupName(@Param("lookupName") String lookupName);
	    
	    public OsiLookupTypes findOsiLookupValuesesByLookupName(String lookupName);
}
