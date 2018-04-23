package com.osi.urm.repository.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import com.osi.ActiveDirectory;
import com.osi.urm.domain.MenuDetails;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiLoginRepositoryCustom;

public class OsiLoginRepositoryImpl implements OsiLoginRepositoryCustom{
	@PersistenceContext
    private EntityManager entityManager;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value( "${image.rendering.path}" )
	private String photographPath;
	
	@Override
	public OsiUser validateLogin(String userName, String password, String currentDate) throws DataAccessException {
		String username1 = null;
		/*try {
			username1 = new ActiveDirectory().authenticate(userName, password);
		} catch (NamingException e1) {
			e1.printStackTrace();
			throw new DataAccessException("MSG_1005", e1.getMessage()); 
		}catch (Exception e1) {
			e1.printStackTrace();
			throw new DataAccessException("MSG_1005", e1.getMessage()); 
		}*/
		OsiUser osiUser = null;
		username1 = userName;
		if(username1!=null){
		//String query = "select id, userName, fullName, firstName, lastName, empNumber, businessGroupId, emailId, hasDefaultPwd  from OsiUser where startDate <= :currentDate and endDate >= :currentDate and userName=:userName and password=:password and Active=1";
		String query = "select employee_id, first_name, last_name, middle_name, employee_number, emp.org_id, org.org_short_name, (select duplicate_file_name from osi_attachments where attachment_id = emp.photo_id) photo from osi_employees emp, osi_organizations org where user_name=? and emp.org_id = org.org_id "
				+ " and ? between effective_start_date and effective_end_date";
		try {
			Object[] object = (Object[])this.entityManager.createNativeQuery(query)
							  .setParameter(1, userName)
							  .setParameter(2, new java.sql.Timestamp(new Date().getTime()))
					          .getSingleResult();
			osiUser = new OsiUser();
			if(object[0]!=null)
				osiUser.setUserId(Integer.parseInt(object[0].toString()));
				osiUser.setUserName(userName);
			if(object[1]!=null)
				osiUser.setFullName(object[1].toString()+" "+object[2].toString());
			if(object[1]!=null)
				osiUser.setFirstName(object[1].toString());
			if(object[2]!=null)
				osiUser.setLastName(object[2].toString());
			if(object[4]!=null)
				osiUser.setEmpNumber(object[4].toString());
			if(object[5]!=null)
				osiUser.setOrgId(Integer.parseInt(object[5].toString()));
			if(object[6]!=null)
				osiUser.setOrgCode(object[6].toString());
			if(object[7]!=null)
				osiUser.setPhotoPath(photographPath+"EMPLOYEE/"+object[7].toString());
			/*if(object[6]!=null)
				osiUser.setBusinessGroupId(Integer.parseInt(object[6].toString()));
			if(object[7]!=null)
				osiUser.setEmailId(object[7].toString());
			if(object[8]!=null)
				osiUser.setHasDefaultPwd(Integer.parseInt(object[8].toString()));*/
		} catch (NoResultException e) {
			throw new DataAccessException("MSG_1005", e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		}else{
			throw new DataAccessException("MSG_1005", "Login Failed!!");
		}
		return osiUser;
	}
	@Override
	public List<MenuDetails> getAllMenusAndSubMenus(Integer userId)  throws DataAccessException{
		List<MenuDetails> menuDetailsList = new ArrayList<MenuDetails>();
		try {
			// mysql
			// List<Map<String, Object>> rows = jdbcTemplate.queryForList("CALL menuTreeData(?)", new Object[]{userId});
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("{call menuTreeData(?)}", new Object[]{userId});
			/*StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("menuTreeData");
			query.registerStoredProcedureParameter("usrId", Long.class, ParameterMode.IN);
			query.setParameter("usrId", userId);
			List<Map<String, Object>> rows = (List<Map<String, Object>>)query.getResultList();
			*/
			if(!rows.isEmpty()){
			//Long noOfLevels = (Long) rows.get(0).get("levels");
			Integer noOfLevels = (Integer) rows.get(0).get("levels"); //Change from Long to Integer to be able to login.
			if(noOfLevels!=null){
			Set<String> combination = new HashSet<String>();
			boolean setDefaultRestp = true;
			for (Map<String, Object> row : rows) {
				for(int i=1; i<=noOfLevels.intValue(); i++) {
					String menuStr = (Integer)row.get("lev" + i + "_menu_id") != null ? ((Integer)row.get("lev" + i + "_menu_id")).toString() : "N";
					String subMenuStr = (Integer)row.get("lev" + i + "_sub") != null ? ((Integer)row.get("lev" + i + "_sub")).toString() : "N";
					String funcStr = (Integer)row.get("lev" + i + "_func_id") != null ? ((Integer)row.get("lev" + i + "_func_id")).toString(): "N";
					String tempComb= (menuStr+"-"+subMenuStr+"-"+funcStr);				
					if((Integer) row.get("lev" + i  + "_sub") != null || ((Integer) row.get("lev" + i  + "_func_id") != null && row.get("lev" + i  + "_func_value") != null) ) {					
						if(!combination.contains(tempComb)) {					
						MenuDetails menuDetails = new MenuDetails();
						Integer menuId = (Integer) row.get("lev" + i + "_menu_id");
						Integer subMenuId = (Integer) row.get("lev" + i + "_sub");
						String menuName = (String) row.get("lev" + i + "_prompt");
						Integer functionId = (Integer) row.get("lev" + i + "_func_id");
						Integer deftResp = 0;
						if(row.get("default_resp")!=null)
							deftResp = (Integer) row.get("default_resp");
						Boolean defaultResp = false;
						if(deftResp==1){
							defaultResp = true;
						}
						Integer rptGrpId = 0;
						if(row.get("rpt_grp_id")!=null)
							rptGrpId = (Integer) row.get("rpt_grp_id");
						String functionUrl = (String) row.get("lev" + i + "_func_value");
						menuDetails.setMenuId(menuId);
						if(setDefaultRestp && defaultResp){
							menuDetails.setExpand(defaultResp);
							setDefaultRestp = false;
						}
						menuDetails.setSubMenuId(subMenuId);
						menuDetails.setTitle(menuName);
						menuDetails.setFunctionId(functionId);
						menuDetails.setUrl(functionUrl);
						menuDetails.setRptGrpId(rptGrpId);
						if(functionId==null)
							menuDetails.setIsFolder(true);
						else
							menuDetails.setIsFolder(false);
						menuDetailsList.add(menuDetails);
						String str = (menuId != null ? menuId.toString() : "N")+"-"+(subMenuId != null ? subMenuId.toString() : "N")+"-"+(functionId !=null ? functionId.toString() : "N");
						combination.add(str);
						}						
					}
				}
			}
			}
			}
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1003", e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return menuDetailsList;
	}
	@Override
	public void logout(Integer userId, String token) throws DataAccessException {
		String query = "update OsiUserLogins set endTime=:endTime where userId=:userId";
		try {
			this.entityManager.createQuery(query)
							  .setParameter("endTime", new Date(), TemporalType.DATE)
							  .setParameter("userId", userId)
							  //.setParameter("token", token)
					          .executeUpdate();
		}catch(Exception e){
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
	}
	
	public OsiUser forgotPAsswordUserDetails(String userName, String currentDate)throws DataAccessException{
		OsiUser osiUser = null;
		String query = "select  userName, fullName  from OsiUser where startDate <= :currentDate and endDate >= :currentDate and userName=:userName and Active=1";
		try {
			Object[] object = (Object[])this.entityManager.createQuery(query)
							  .setParameter("currentDate", new Date(), TemporalType.DATE)
							  .setParameter("userName", userName)
					          .getSingleResult();
			if(object!=null){
			osiUser = new OsiUser();
			if(object[0]!=null)
				osiUser.setUserName(object[0].toString());
			if(object[1]!=null)
				osiUser.setFullName(object[1].toString());
			}
		} catch (NoResultException e) {
			throw new DataAccessException("ERR_1001", e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return osiUser;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAdminEmailIds(String currentDate)throws DataAccessException{
		List<String> emailList =null;
		String query = "select  ou.emailId  from OsiUser ou, OsiRoles roles, OsiUserRoles ous where ou.userId=ous.osiUser.userId and ous.roleId.roleId=roles.roleId "
				+ " and  ou.startDate <= :currentDate and ou.endDate >= :currentDate and upper(roles.roleName) =:roleName";
		try {
			emailList = (List<String>)this.entityManager.createQuery(query)
							  .setParameter("currentDate", new Date(), TemporalType.DATE)
							  .setParameter("roleName", "Admin".toUpperCase())
					          .getResultList();			
		} catch (NoResultException e) {
			throw new DataAccessException("ERR_1001", e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return emailList;
	}

}
