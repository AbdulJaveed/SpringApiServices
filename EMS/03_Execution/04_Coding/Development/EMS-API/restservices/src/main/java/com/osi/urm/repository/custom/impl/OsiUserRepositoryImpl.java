package com.osi.urm.repository.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.osi.urm.domain.OsiAttachments;
import com.osi.urm.domain.OsiRespUser;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.domain.OsiUserFuncExcl;
import com.osi.urm.domain.OsiUserOperationExcl;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiUserRepositoryCustom;

public class OsiUserRepositoryImpl implements OsiUserRepositoryCustom{
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public Integer deleteUser(List<Integer> id, Integer businessGroupId, Integer userId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "update OsiUser set updatedBy = :updatedBy, updatedDate = :updatedDate, active=0 where businessGroupId = :businessGroupId and userId IN (:userId)";
			count = this.entityManager.createQuery(query)
							  .setParameter("updatedBy", userId)
							  .setParameter("updatedDate", new Date())
							  .setParameter("businessGroupId", businessGroupId)
							  .setParameter("userId", id)
					          .executeUpdate();
			if(count==0){
				throw new DataAccessException("ERR_1012", null);
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode(), e.getSystemMessage()); 
		}catch (Exception e) {
			throw new DataAccessException("ERR_1035", e.getMessage()); 
		}
		return count;
	}
	
	
	@Override
	public void updateUser(OsiUser osiUser) throws DataAccessException {
		try {
			Integer count = 0;
			String userUpdateQuery = "update OsiUser set userId=:userId, userName=:userName, password=:password, firstName=:firstName, lastName=:lastName, emailId=:emailId, empNumber=:empNumber, mobileNumber=:mobileNumber, startDate=:startDate, endDate=:endDate, active=:active, updatedBy = :updatedBy, updatedDate = :updatedDate where businessGroupId = :businessGroupId and userId =:userId";
			String userOsiUserFuncExclDeleteQuery = "delete from OsiUserFuncExcl where osiUser.userId=:userId";
			String userOsiRespUserDeleteQuery = "delete from OsiRespUser where osiUser.userId=:userId";
			String userOsiUserOperationExclDeleteQuery = "delete from OsiUserOperationExcl where osiUser.userId=:userId";
			String userOsiUserCategoriesDeleteQuery = "delete from OsiUserCategories where osiUser.userId=:userId";
			String userOsiInvOrgUserDeleteQuery = "delete from OsiInvOrgUser where osiUser.userId=:userId";
			String userOsiApproversDeleteQuery = "delete from OsiApprovers where osiUser.userId=:userId";
			String userOsiUserRolesDeleteQuery = "delete from OsiUserRoles where osiUser.userId=:userId";
			String userOsiUserDepartmentDeleteQuery = "delete from OsiUserDepartment where osiUser.userId=:userId";
			this.entityManager.createQuery(userOsiUserFuncExclDeleteQuery).setParameter("userId", osiUser.getUserId()).executeUpdate();
			this.entityManager.createQuery(userOsiRespUserDeleteQuery).setParameter("userId", osiUser.getUserId()).executeUpdate();
			this.entityManager.createQuery(userOsiUserOperationExclDeleteQuery).setParameter("userId", osiUser.getUserId()).executeUpdate();
			this.entityManager.createQuery(userOsiUserCategoriesDeleteQuery).setParameter("userId", osiUser.getUserId()).executeUpdate();
			this.entityManager.createQuery(userOsiInvOrgUserDeleteQuery).setParameter("userId", osiUser.getUserId()).executeUpdate();
			this.entityManager.createQuery(userOsiApproversDeleteQuery).setParameter("userId", osiUser.getUserId()).executeUpdate();
			this.entityManager.createQuery(userOsiUserRolesDeleteQuery).setParameter("userId", osiUser.getUserId()).executeUpdate();
			this.entityManager.createQuery(userOsiUserDepartmentDeleteQuery).setParameter("userId", osiUser.getUserId()).executeUpdate();
			count=this.entityManager.createQuery(userUpdateQuery)
							  .setParameter("userId", osiUser.getUserId())	
							  .setParameter("userName", osiUser.getUserName())	
							  .setParameter("password", osiUser.getPassword())	
							  .setParameter("firstName", osiUser.getFirstName())	
							  .setParameter("lastName", osiUser.getLastName())	
							  .setParameter("emailId", osiUser.getEmailId())	
							  .setParameter("empNumber", osiUser.getEmpNumber())	
							  .setParameter("mobileNumber", osiUser.getMobileNumber())	
							  .setParameter("startDate", osiUser.getStartDate())	
							  .setParameter("endDate", osiUser.getEndDate())
							  .setParameter("active", osiUser.getActive())	
							  .setParameter("updatedBy", osiUser.getUpdatedBy())	
							  .setParameter("updatedDate", osiUser.getUpdatedDate())	
							  .setParameter("businessGroupId", osiUser.getBusinessGroupId())	
							  .executeUpdate();
							  
			if(count==0){
				throw new DataAccessException("ERR_1043",  null); 
			}
			for (OsiUserFuncExcl osiUserFuncExcl : osiUser.getOsiUserFuncExcls()) {
				this.entityManager.merge(osiUserFuncExcl);
			}
			for (OsiRespUser osiRespUser : osiUser.getOsiRespUsers()) {
				this.entityManager.merge(osiRespUser);
			}
			for (OsiUserOperationExcl osiUserOperationExcl : osiUser.getOsiUserOperationExcls()) {
				this.entityManager.merge(osiUserOperationExcl);
			}
		}catch (Exception e) {
			throw new DataAccessException("ERR_1043", e.getMessage()); 
		}
	}


	@Override
	public OsiUser findUserById(Integer userId, Integer businessGroupId) throws DataAccessException {

		List<Object[]> objectList = null;
		List<OsiUser> result = new ArrayList<OsiUser>();
		OsiUser user = new OsiUser();
		try {
			
			String query="select usr from OsiUser usr where usr.businessGroupId= :businessGroupId and usr.userId= :userId ORDER BY updatedDate desc";
			result = (List<OsiUser>) this.entityManager.createQuery(query)
					.setParameter("businessGroupId", businessGroupId)
					.setParameter("userId",userId)
					.getResultList();
			if(result!=null || !result.isEmpty()){
				for(OsiUser data : result){
					user.setUserId(data.getUserId());
					user.setActive(data.getActive());
					user.setUserName(data.getUserName());
					user.setFirstName(data.getFirstName());
					user.setLastName(data.getLastName());
					user.setEmpNumber(data.getEmpNumber());
					user.setEmailId(data.getEmailId());
					user.setFullName(data.getFullName());
					user.setPassword(data.getPassword());
					user.setMobileNumber(data.getMobileNumber());
					user.setBusinessGroupId(data.getBusinessGroupId());
					user.setCreatedBy(data.getCreatedBy());
					user.setCreatedDate(data.getCreatedDate());
					user.setUpdatedBy(data.getUpdatedBy());
					user.setUpdatedDate(data.getUpdatedDate());
					user.setStartDate(data.getCreatedDate());
					user.setEndDate(data.getEndDate());
				}
			}
		}catch (Exception e) {
			throw new DataAccessException("ERR_1035", e.getMessage()); 
		}
		
		return user;
	}


	@Override
	public List<OsiRespUser> findUserResponsibilities(Integer userId, Integer businessGroupId)
			throws DataAccessException {
		List<OsiRespUser> result = new ArrayList<OsiRespUser>();
		List<OsiRespUser> list = new ArrayList<OsiRespUser>();
		String query="select respUsr from OsiRespUser respUsr where respUsr.businessGroupId= :businessGroupId and respUsr.employeeId= :userId";
		result = (List<OsiRespUser>) this.entityManager.createQuery(query)
				.setParameter("businessGroupId", businessGroupId)
				.setParameter("userId",userId)
				.getResultList();
		if(result!=null || !result.isEmpty()){
			for(OsiRespUser respUser:result){
				
				OsiRespUser resp = new OsiRespUser();
				resp = (OsiRespUser)respUser;
				list.add(resp);
			}
		}
		return list;
	}
	
	@Override
	public List<OsiUserFuncExcl> getUserFunctionExclusions(Integer userId, Integer businessGroupId)
			throws DataAccessException {
		List<OsiUserFuncExcl> result = new ArrayList<OsiUserFuncExcl>();
		List<OsiUserFuncExcl> list = new ArrayList<OsiUserFuncExcl>();
		String query="select usrFuncExcl from OsiUserFuncExcl usrFuncExcl where usrFuncExcl.businessGroupId= :businessGroupId and usrFuncExcl.employeeId= :userId";
		result = (List<OsiUserFuncExcl>) this.entityManager.createQuery(query)
				.setParameter("businessGroupId", businessGroupId)
				.setParameter("userId",userId)
				.getResultList();
		if(result!=null || !result.isEmpty()){
			for(OsiUserFuncExcl userFuncExcl:result){
				
				OsiUserFuncExcl funcExcl = new OsiUserFuncExcl();
				funcExcl = (OsiUserFuncExcl)userFuncExcl;
				list.add(funcExcl);
			}
		}
		return list;
	}
	
	@Override
	public List<OsiUserOperationExcl> getUserOperationExclusions(Integer userId, Integer businessGroupId)
			throws DataAccessException {
		List<OsiUserOperationExcl> result = new ArrayList<OsiUserOperationExcl>();
		List<OsiUserOperationExcl> list = new ArrayList<OsiUserOperationExcl>();
		String query="select usrOpExcl from OsiUserOperationExcl usrOpExcl where usrOpExcl.businessGroupId= :businessGroupId and usrOpExcl.employeeId= :userId";
		result = (List<OsiUserOperationExcl>) this.entityManager.createQuery(query)
				.setParameter("businessGroupId", businessGroupId)
				.setParameter("userId",userId)
				.getResultList();
		if(result!=null || !result.isEmpty()){
			for(OsiUserOperationExcl userOpExcl:result){
				
				OsiUserOperationExcl operationExcl = new OsiUserOperationExcl();
				operationExcl = (OsiUserOperationExcl)userOpExcl;
				list.add(operationExcl);
			}
		}
		return list;
	}


	@Override
	public List<OsiAttachments> getUserAttachments(Integer userId, Integer businessGroupId)
			throws DataAccessException {
		
		List<OsiAttachments> result = new ArrayList<OsiAttachments>();
		List<OsiAttachments> list = new ArrayList<OsiAttachments>();
		String query="select attachment from OsiAttachments attachment where attachment.businessGroupId= :businessGroupId and attachment.osiUser.userId= :userId";
		result = (List<OsiAttachments>) this.entityManager.createQuery(query)
				.setParameter("businessGroupId", businessGroupId)
				.setParameter("userId",userId)
				.getResultList();
		if(result!=null || !result.isEmpty()){
			for(OsiAttachments attachment:result){
				
				OsiAttachments attach = new OsiAttachments();
				attach = (OsiAttachments)attachment;
				list.add(attach);
			}
		}
		return list;
	}


	@Override
	public OsiUser updateUserProfile(OsiUser osiUser) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "update OsiUser set updatedBy = :updatedBy, updatedDate = :updatedDate, mobileNumber = :mobileNumber, password = :password where businessGroupId = :businessGroupId and userId = :userId";
			count = this.entityManager.createQuery(query)
							  .setParameter("updatedBy", osiUser.getUpdatedBy())
							  .setParameter("updatedDate", new Date())
							  .setParameter("businessGroupId", osiUser.getBusinessGroupId())
							  .setParameter("password", osiUser.getPassword())
							  .setParameter("mobileNumber",osiUser.getMobileNumber())
							  .setParameter("userId", osiUser.getUserId())
					          .executeUpdate();
			if(count==0){
				throw new DataAccessException("ERR_1012", null);
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode(), e.getSystemMessage()); 
		}catch (Exception e) {
			throw new DataAccessException("ERR_1035", e.getMessage()); 
		}
		return osiUser;
	}
	
	@Override
	public List<OsiUser> searchUser(Integer businessGroupId,String userName, String empNumber, String emailId, String firstName, String lastName) throws DataAccessException 
	{
		List<OsiUser> osiSearchUserList=new ArrayList<>();
		try 
		{
			if(businessGroupId > 0 && businessGroupId !=null)
			{
				StringBuilder queryString= new StringBuilder("SELECT ou.firstName, ou.lastName, ou.userName, ou.empNumber, ou.emailId, ou.active,ou.userId FROM OsiUser ou WHERE businessGroupId= :businessGroupId AND");
				
				String whereClause="";

				if( userName!=null && userName !="" ){
						whereClause+=" UPPER(ou.userName) LIKE UPPER(CONCAT('%',:userName,'%'))";
				}

				if( empNumber!=null ){
					if(whereClause.length()>0){
						whereClause+=" AND ou.empNumber LIKE CONCAT('%',:empNumber,'%')";
					}
					else{
						whereClause+=" ou.empNumber LIKE CONCAT('%',:empNumber,'%')";
					}
				}

				if( emailId!=null ){
					if(whereClause.length()>0){
						whereClause+=" AND ou.emailId LIKE CONCAT('%',:emailId,'%')";
					}
					else{
						whereClause+=" ou.emailId LIKE CONCAT('%',:emailId,'%')";
					}
				}
				
				if( firstName!=null ){
					if(whereClause.length()>0){
						whereClause+=" AND ou.firstName LIKE CONCAT('%',:firstName,'%')";
					}
					else{
						whereClause+=" ou.firstName LIKE CONCAT('%',:firstName,'%')";
					}
				}
				
				if( lastName!=null ){
					if(whereClause.length()>0){
						whereClause+=" AND ou.lastName LIKE CONCAT('%',:lastName,'%')";
					}
					else{
						whereClause+=" ou.lastName LIKE CONCAT('%',:lastName,'%')";
					}
				}

				queryString.append(whereClause+" ORDER BY updatedDate desc");
				System.out.println("QUERY=============================:"+queryString.toString());
				Query query=this.entityManager.createQuery(queryString.toString());
				query.setParameter("businessGroupId", businessGroupId);
				if( userName!=null){
					query.setParameter("userName", userName);
				}
				if( empNumber!=null){
					query.setParameter("empNumber", empNumber);
				}

				if( emailId!=null ){
					query.setParameter("emailId", emailId);
				}
				
				if( firstName!=null ){
					query.setParameter("firstName", firstName);
				}
				
				if( lastName!=null ){
					query.setParameter("lastName", lastName);
				}
				
				List<Object[]> objList =query.getResultList();
				
				for (Object[] objects : objList) {
					OsiUser osiUser=new OsiUser();
					if(objects[0]!=null)
					{
					   osiUser.setFirstName(objects[0].toString());
					}
					if(objects[1]!=null)
					{
					   osiUser.setLastName(objects[1].toString());
					}
					osiUser.setUserName(objects[2].toString());
					if(objects[3]!=null)
					{
						osiUser.setEmpNumber(objects[3].toString());
					}
					osiUser.setEmailId(objects[4].toString());
					osiUser.setActive(Integer.parseInt(objects[5].toString()));
					if(objects[6]!=null)
						osiUser.setUserId(Integer.parseInt(objects[6].toString()));
					osiSearchUserList.add(osiUser);
				}
				
//				osiSearchUserList=query.getResultList();
			}

		}
		catch (Exception e) {
			System.out.println("Excepton ===========:"+e.getMessage());
			// TODO: handle exception
		} 
		
		return osiSearchUserList;
	}

	@Override
	public Integer updatePassword(OsiUser osiUser) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "update OsiUser set updatedBy = :updatedBy, updatedDate = :updatedDate, password = :password, hasDefaultPwd = :hasDefaultPwd where businessGroupId = :businessGroupId and userId = :userId";
			count = this.entityManager.createQuery(query)
							  .setParameter("updatedBy", osiUser.getUpdatedBy())
							  .setParameter("updatedDate", new Date())
							  .setParameter("businessGroupId", osiUser.getBusinessGroupId())
							  .setParameter("password", osiUser.getPassword())
							  .setParameter("userId", osiUser.getUserId())
							  .setParameter("hasDefaultPwd", osiUser.getHasDefaultPwd())
					          .executeUpdate();
			if(count==0){
				throw new DataAccessException("ERR_1012", null);
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode(), e.getSystemMessage()); 
		}catch (Exception e) {
			throw new DataAccessException("ERR_1035", e.getMessage()); 
		}
		return count;
	}

	@Override
	public OsiUser updateResetUserPassword(OsiUser osiUser) throws DataAccessException {

		Integer count = 0;
		try {
			String query = "update OsiUser set updatedBy = :updatedBy, updatedDate = :updatedDate, password = :password, hasDefaultPwd = :hasDefaultPwd "
					+ "where businessGroupId = :businessGroupId and userId = :userId";
			count = this.entityManager.createQuery(query).setParameter("updatedBy", osiUser.getUpdatedBy())
					.setParameter("updatedDate", new Date()).setParameter("password", osiUser.getPassword())
					.setParameter("hasDefaultPwd", osiUser.getHasDefaultPwd())
					.setParameter("businessGroupId", osiUser.getBusinessGroupId())
					.setParameter("userId", osiUser.getUserId()).executeUpdate();
			if (count == 0) {
				throw new DataAccessException("ERR_1012", null);
			}
		} catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			throw new DataAccessException("ERR_1035", e.getMessage());
		}
		return osiUser;
	}
}