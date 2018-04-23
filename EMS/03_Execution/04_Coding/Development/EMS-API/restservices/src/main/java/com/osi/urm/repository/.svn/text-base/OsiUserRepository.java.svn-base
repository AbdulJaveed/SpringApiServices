package com.osi.urm.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.osi.urm.domain.OsiUser;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiUserRepositoryCustom;
import com.osi.urm.service.dto.OsiUserProjection;

/**
 * Spring Data JPA repository for the OsiUser entity.
 */
@Repository
public interface OsiUserRepository extends JpaRepository<OsiUser,Integer> ,OsiUserRepositoryCustom{
	
	OsiUser findUserByUserIdAndBusinessGroupIdAndActiveOrderByUpdatedDateDesc(Integer id,Integer businessGroupId,Integer active);

	List<OsiUserProjection> findByBusinessGroupIdAndActiveAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByUpdatedDateDesc(Integer businessGroupId,Integer active,Date currentDate1,Date currentDate2, Class<OsiUserProjection> class1);
	List<OsiUserProjection> findByBusinessGroupIdAndActiveOrderByUpdatedDateDesc(Integer businessGroupId,Integer active,Class<OsiUserProjection> class1, Pageable pageObject);
	
	List<OsiUserProjection> findByBusinessGroupIdAndActiveOrderByUpdatedDateDesc(Integer businessGroupId,Integer active,Class<OsiUserProjection> class1);
	List<OsiUser> findByBusinessGroupIdAndActiveOrderByFirstName(Integer businessGroupId,Integer active);
	/*@Query(" FROM OsiItem WHERE businessGroupId = :businessGroupId ORDER BY createdDate asc")
	List<OsiUser> findByBusinessGroupIdAndActiveOrderByFirstName(@Param("businessGroupId")Integer businessGroupId,@Param("active")Integer active,@Param("businessGroupId")Date currentDate1,@Param("active")Date currentDate2);*/
	
	@Query(value = "select distinct a.inv_org_id,b.department_id,c.role_id from osi_inv_org_user a , osi_user_department b,osi_user_roles c where a.user_id= b.user_id and a.user_id=c.user_id and b.user_id = c.user_id  group by  a.inv_org_id ,b.department_id ,c.role_id", nativeQuery=true)
	public List<Object[]> getAllDepartmentRoleInventoryCombination() throws DataAccessException;

	OsiUser updateResetUserPassword(OsiUser osiUser);

}
