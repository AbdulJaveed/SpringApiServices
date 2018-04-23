package com.osi.urm.repository.custom.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osi.urm.domain.OsiFunctions;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiFunctionRepositoryCustom;

public class OsiFunctionRepositoryImpl implements OsiFunctionRepositoryCustom {
	
	Logger log = LoggerFactory.getLogger(getClass());
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<OsiFunctions> getFunctionsName(Integer bussinessGroupId,
			List<Integer> userRespIds) throws DataAccessException {
		List<OsiFunctions> osiFunctionsList = new ArrayList<OsiFunctions>();
		List<OsiFunctions> osiFunctionsUniqueList = new ArrayList<OsiFunctions>();
		//OsiFunctions osiFunctions = new OsiFunctions();
		HashSet<String> mySet = new HashSet<>();
		try {
			String queryString = "select id,funcName from OsiFunctions where id in (select distinct osiFunctions.id from OsiMenuEntries where osiMenusByMenuId.id in (select osiMenus.id from OsiMenuResp where osiResponsibilities.id IN (:userRespIds)) "
					+ " and osiFunctions.id is not null) ";
			
			String queryString1 = "select id,funcName from OsiFunctions where id in (select distinct osiFunctions.id from OsiMenuEntries where osiMenusByMenuId.id in (select osiMenusBySubMenuId.id from OsiMenuEntries where osiMenusByMenuId.id in (select osiMenus.id from OsiMenuResp where osiResponsibilities.id IN (:userRespIds))) "
					+ "and osiFunctions.id is not null)";



			@SuppressWarnings("unchecked")
			List<Object[]> queryList = (List<Object[]>) this.entityManager
					.createQuery(queryString)
					.setParameter("userRespIds", userRespIds)
					//.setParameter("currentDate", new Date(), TemporalType.DATE)
					.getResultList();
			
			@SuppressWarnings("unchecked")
			List<Object[]> queryList1 = (List<Object[]>) this.entityManager
					.createQuery(queryString1)
					.setParameter("userRespIds", userRespIds)
					//.setParameter("currentDate", new Date(), TemporalType.DATE)
					.getResultList();
			
			osiFunctionsList = new ArrayList<OsiFunctions>();
			for (Object[] objects : queryList) {
				OsiFunctions osiFunctions = new OsiFunctions();
				if (objects[0] != null)
					osiFunctions.setId(Integer.parseInt(objects[0].toString()));
				if (objects[1] != null)
					osiFunctions.setFuncName(objects[1].toString());
				osiFunctionsList.add(osiFunctions);

			}
			
			//osiFunctionsList = new ArrayList<OsiFunctions>();
			for (Object[] objects : queryList1) {
				OsiFunctions osiFunctions = new OsiFunctions();
				if (objects[0] != null)
					osiFunctions.setId(Integer.parseInt(objects[0].toString()));
				if (objects[1] != null)
					osiFunctions.setFuncName(objects[1].toString());
				osiFunctionsList.add(osiFunctions);
				
			}
			
			
			for(OsiFunctions funName : osiFunctionsList){
			 if (!mySet.contains(funName.getFuncName())) {
				 osiFunctionsUniqueList.add(funName);
	            	mySet.add(funName.getFuncName());
	            }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepton ===========:" + e.getMessage());
		}
		return osiFunctionsUniqueList;
	}

	@Override
	public List<OsiFunctions> getUserFunctions(Integer userId) throws DataAccessException {
		List<OsiFunctions> osiFunctionsList = new ArrayList<OsiFunctions>();
		log.info(" ## getUserFunctions : Begin");
		try {
			String queryString = "select f.FUNC_NAME, f.FUNC_VALUE, f.id from osi_functions f where f.ID IN "
					+ " (select distinct me.FUNC_ID from osi_menu_entries me where me.MENU_ID IN "
					+ " ( select mr.MENU_ID from osi_menu_resp mr where mr.RESP_ID IN "
					+ " ( select ru.RESP_ID from osi_resp_user ru where ru.USER_ID = :userId)))"
					+ "  and f.ID NOT in (select ufx.FUNC_ID from osi_user_func_excl ufx where ufx.USER_ID = :userId)";


			@SuppressWarnings("unchecked")
			List<Object[]> queryList = (List<Object[]>) this.entityManager
					.createNativeQuery(queryString)
					.setParameter("userId", userId)
					.getResultList();
			
			for (Object[] objects : queryList) {
				OsiFunctions osiFunctions = new OsiFunctions();
				if (objects[0] != null)
					osiFunctions.setFuncName(objects[0].toString());
				if (objects[1] != null)
					osiFunctions.setFuncValue(objects[1].toString());
				if (objects[1] != null)
					osiFunctions.setId(Integer.parseInt(objects[2].toString()));
				osiFunctionsList.add(osiFunctions);

			}
			
		} catch (Exception e) {
			//e.printStackTrace();
			log.error(" Error Occurred while getting the user specific functions: "+ e.getMessage());
		}
		log.info(" ## getUserFunctions : End");
		return osiFunctionsList;
	}
}
