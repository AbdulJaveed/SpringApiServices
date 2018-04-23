package com.osi.urm.repository.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.osi.ems.common.CommonService;
import com.osi.urm.domain.OsiLookupTypes;
import com.osi.urm.domain.OsiLookupValues;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.OsiLookupRepository;
import com.osi.urm.repository.custom.OsiLookupRepositoryCustom;
import com.osi.urm.service.dto.OsiUserDTO;

public class OsiLookupRepositoryImpl implements OsiLookupRepositoryCustom {
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private OsiLookupRepository osiLookupTypesRepository;
	
	@Autowired
	private CommonService commonService;
	
	@Override
	public OsiLookupTypes updateLookup(OsiLookupTypes osiLookupTypes)
			throws DataAccessException {
		// TODO Auto-generated method stub
		List<Long> idsforUpdate = null;
		try {
			if(osiLookupTypes.getId()!=null){
				OsiLookupTypes osiLookupTypesExit = this.entityManager.find(OsiLookupTypes.class, osiLookupTypes.getId());
				osiLookupTypes.setCreatedBy(osiLookupTypesExit.getCreatedBy());
				osiLookupTypes.setCreatedDate(osiLookupTypesExit.getCreatedDate());
			}
			
			/*String deleteLookupChildsQuery = "delete from OsiLookupValues v where v.osiLookupTypes=:lookUpParent and v.businessGroupId = :businessGroupId";
			this.entityManager.createQuery(deleteLookupChildsQuery)
					  .setParameter("lookUpParent", osiLookupTypes)	
					  .setParameter("businessGroupId", osiLookupTypes.getBusinessGroupId())
			          .executeUpdate();
			
			String updateLookUpType = "update OsiLookupTypes t set t.lookupName = :lookupName, t.lookupCode = :lookupCode,t.updatedBy = :updatedBy, t.updatedDate = :updatedDate , t.active=:active where t.id = :id and t.businessGroupId = :businessGroupId";
			int count = this.entityManager.createQuery(updateLookUpType)
					  .setParameter("lookupName", osiLookupTypes.getLookupName())
					  .setParameter("lookupCode", osiLookupTypes.getLookupCode())
					  .setParameter("businessGroupId", osiLookupTypes.getBusinessGroupId())
					  .setParameter("updatedBy", osiLookupTypes.getUpdatedBy())
					  .setParameter("updatedDate", osiLookupTypes.getUpdatedDate())
					  .setParameter("id", osiLookupTypes.getId())
					  .setParameter("active", osiLookupTypes.getActive())
			          .executeUpdate();*/
			
			idsforUpdate = new ArrayList<Long>();
			for (OsiLookupValues osiLookupValues : osiLookupTypes.getOsiLookupValueses()) {
				osiLookupValues.setCreatedBy(osiLookupTypes.getCreatedBy());
				osiLookupValues.setCreatedDate(osiLookupTypes.getCreatedDate());
				//osiLookupValues.setUpdatedDate(new Date());
				osiLookupValues.setUpdatedDate(commonService.getCurrentDateinUTC());
				osiLookupValues.setUpdatedBy(osiLookupTypes.getUpdatedBy());
				if(osiLookupValues.getId()!= null){
					idsforUpdate.add(osiLookupValues.getId());
				}
			}
			if(idsforUpdate!=null && idsforUpdate.size()>0 && osiLookupTypes.getId()!=null){
				String updateQuery = "delete from OsiLookupValues v where v.osiLookupTypes.id=:lookUpTypeId and v.id not in (:idsforUpdate)";
				this.entityManager.createQuery(updateQuery)
				.setParameter("idsforUpdate", idsforUpdate)
				.setParameter("lookUpTypeId", osiLookupTypes.getId())
				.executeUpdate();
			}
			osiLookupTypes = this.entityManager.merge(osiLookupTypes);
			
			/*if(count>0){
				for(OsiLookupValues lookupValue: osiLookupTypes.getOsiLookupValueses() ){
					this.entityManager.persist(lookupValue);
				}
			}*/
		
		}catch (Exception e) {
			throw new DataAccessException("ERR_1009", e.getMessage()); 
			//e.printStackTrace();
		}

		return osiLookupTypes;
		
		
	}
	
	@Override
	public int deleteLookup(List<Long> ids, OsiUserDTO user)
			throws DataAccessException {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			String query = "update OsiLookupTypes t set t.updatedBy = :updatedBy, t.updatedDate = :updatedDate, t.active = 0 where t.id IN ( :ids ) and t.active = 1";
			count = this.entityManager.createQuery(query)
					.setParameter("updatedBy", user.getId().intValue())
					.setParameter("updatedDate", new Date())
					//.setParameter("businessGroupId", user.getBusinessGroupId())
					.setParameter("ids", ids)
					.executeUpdate();
			if (count == 0) {
				throw new DataAccessException("ERR_1002", null);
			}
		} catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode(), e.getMessage());
			// e.printStackTrace();
		} catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage());
			// e.printStackTrace();
		}
		return count;
		
		
	}

	@Override
	public List<OsiLookupTypes> searchLookup(String queryParam,
			Integer businessGroupId) throws DataAccessException {
		List<OsiLookupTypes> list = new ArrayList<OsiLookupTypes>();
		try{
			if(queryParam!=null){
				String query = "Select l from OsiLookupTypes l where 1=1 "+queryParam+" ORDER BY l.updatedDate desc ";
				//System.out.println("query "+query);
				@SuppressWarnings("unchecked")
				List<OsiLookupTypes> lists = (List<OsiLookupTypes>) this.entityManager.createQuery(query)
						//  .setParameter("businessGroupId", businessGroupId)
						  .getResultList();
				
				for(OsiLookupTypes o : lists ){
					if (o != null)
						list.add((OsiLookupTypes) o);
				}
			}else{
				throw new DataAccessException("ERR_1026",  null); 
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode() , e.getMessage()); 
			//e.printStackTrace();
		}
		return list;
	}
	
	public List<String> getLookupValuesByLookupId(Long lookupId,Integer businessGroupId) throws DataAccessException{
		List<String> lists= new ArrayList<String>();
		try{
			String query = "Select lv.lookupValue from OsiLookupValues lv where 1=1 and lv.osiLookupTypes.id=:lookupId ORDER BY lv.lookupValue asc ";				
			@SuppressWarnings("unchecked")
			List<String>  list = (List<String>) this.entityManager.createQuery(query)
						//.setParameter("businessGroupId", businessGroupId)
						.setParameter("lookupId", lookupId)
					    .getResultList();
			for(String val:list){
				lists.add(val);	
			}			
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1065" , e.getMessage()); 
		}
		return lists;
	}
	
	public Long isLookupUsedinCategory(Long lookupId,Integer businessGroupId) throws DataAccessException{
		Long countVal=0L;
		try{
			List<String> listString=getLookupValuesByLookupId(lookupId,businessGroupId);
			if(listString!=null && listString.size()>0){
			 StringBuilder queryString=new StringBuilder();
			 for(String lookupVal:listString){
				queryString.append("oc.categoryCode like ").append("'%"+lookupVal+"%' " +"OR ");								
			}
		    String queryParam1=queryString.toString().trim();
		    String queryParam=queryParam1.substring(0,queryParam1.length()-2);
			String query = "Select count(*) from  OsiCategories oc where 1=1 and "+ queryParam;			
			@SuppressWarnings("unchecked")
			Long count = (Long) this.entityManager.createQuery(query)
						.setParameter("businessGroupId", businessGroupId)
					    .getSingleResult();	
			countVal=count;
		 }
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1065" , e.getMessage()); 
		}		
		return countVal;
	}
	public Long isLookupUsedinCOa(Long lookupId,Integer businessGroupId) throws DataAccessException{
		Long countVal=0L;
		try{
			List<String> listString=getLookupValuesByLookupId(lookupId,businessGroupId);
			if(listString!=null && listString.size()>0){
			StringBuilder queryString=new StringBuilder();
			for(String lookupVal:listString){
				queryString.append("oc.coaCode like ").append("'%"+lookupVal+"%' " +"OR ");								
			}
			String queryParam1=queryString.toString().trim();
			String queryParam=queryParam1.substring(0,queryParam1.length()-2);
			String query = "Select count(*) from  OsiCoa oc where 1=1 and "+ queryParam;			
			@SuppressWarnings("unchecked")
			Long count = (Long) this.entityManager.createQuery(query)
						.setParameter("businessGroupId", businessGroupId)
					    .getSingleResult();	
			countVal=count;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1065" , e.getMessage()); 
		}		
		return countVal;
	}
	
	public Long isLookupUsedinInvOrg(Long lookupId,Integer businessGroupId) throws DataAccessException{
		Long countVal=0L;
		try{
			String query = "Select lv.lookupValue from OsiLookupValues lv where 1=1 and lv.osiLookupTypes.id=:lookupId and lv.osiLookupTypes.lookupCode in ('INV_ORG_TYPE','RECEIPT_ROUTING','RMA_RECEIPT_ROUTING') ORDER BY lv.lookupValue asc ";				
			@SuppressWarnings("unchecked")
			List<String>  list = (List<String>) this.entityManager.createQuery(query)
					//	.setParameter("businessGroupId", businessGroupId)
						.setParameter("lookupId", lookupId)
					    .getResultList();
			if(list!=null && list.size()>0){
			  StringBuilder queryString=new StringBuilder("(");
			  for(String lookupVal:list){
				queryString.append("'"+lookupVal+"',");								
			 }
			  String queryParam1=queryString.toString().trim();
			  String queryParam=queryParam1.substring(0,queryParam1.length()-1);
			  queryParam=queryParam.concat(")");
			 String query1 = "Select count(*) from  OsiInventoryOrg oiv where 1=1 and oiv.invOrgType in " +queryParam+" or oiv.receiptRouting in " +queryParam+" or oiv.rmaReceiptRouting in " +queryParam;	
			 @SuppressWarnings("unchecked")
			 Long count = (Long) this.entityManager.createQuery(query1)
					//	.setParameter("businessGroupId", businessGroupId)
					    .getSingleResult();	
			 countVal=count;
		  }
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1065" , e.getMessage()); 
		}		
		return countVal;
	}
	
	
	public Long isLookupUsedinUserCategory(Long lookupId,Integer businessGroupId) throws DataAccessException{
		Long countVal=0L;
		try{
			String query = "Select lv.lookupValue from OsiLookupValues lv where 1=1 and lv.osiLookupTypes.id=:lookupId and lv.osiLookupTypes.lookupCode in ('approver_level') ORDER BY lv.lookupValue asc ";				
			@SuppressWarnings("unchecked")
			List<String>  list = (List<String>) this.entityManager.createQuery(query)
						//.setParameter("businessGroupId", businessGroupId)
						.setParameter("lookupId", lookupId)
					    .getResultList();
			if(list!=null && list.size()>0){
				StringBuilder queryString=new StringBuilder("(");
				for(String lookupVal:list){
					queryString.append("'"+lookupVal+"',");								
				}
				String queryParam1=queryString.toString().trim();
			    String queryParam=queryParam1.substring(0,queryParam1.length()-1);
			    queryParam=queryParam.concat(")");
			
				String query1 = "Select count(*) from  OsiUserCategories uc where uc.businessGroupId = :businessGroupId and uc.apLevel in "+ queryParam;	
				@SuppressWarnings("unchecked")
				Long count = (Long) this.entityManager.createQuery(query1)
						.setParameter("businessGroupId", businessGroupId)
					    .getSingleResult();	
				countVal=count;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1065" , e.getMessage()); 
		}		
		return countVal;
	}
	
	public Long isLookupUsedinPrHeader(Long lookupId,Integer businessGroupId) throws DataAccessException{
		Long countVal=0L;
		try{
			String query = "Select lv.lookupValue from OsiLookupValues lv where lv.businessGroupId = :businessGroupId and lv.osiLookupTypes.id=:lookupId and lv.osiLookupTypes.lookupCode in ('HEADER_TYPE') ORDER BY lv.lookupValue asc";
			@SuppressWarnings("unchecked")
			List<String>  list = (List<String>) this.entityManager.createQuery(query)
						.setParameter("businessGroupId", businessGroupId)
						.setParameter("lookupId", lookupId)
					    .getResultList();
			
			if(list!=null && list.size()>0){
				StringBuilder queryString=new StringBuilder("(");
			    for(String lookupVal:list){
				queryString.append("'"+lookupVal+"',");								
			    }
			    String queryParam1=queryString.toString().trim();
			    String queryParam=queryParam1.substring(0,queryParam1.length()-1);
			    queryParam=queryParam.concat(")");
			    
			    String query1 = "Select count(*) from  OsiPrReqHeader prh where prh.businessGroupId = :businessGroupId and prh.requestType in "+ queryParam;	
			    @SuppressWarnings("unchecked")
			    Long count = (Long) this.entityManager.createQuery(query1)
						.setParameter("businessGroupId", businessGroupId)
					    .getSingleResult();	
			    countVal=count;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1065" , e.getMessage()); 
		}		
		return countVal;
	}
	
	public Long isLookupUsedinPrReqLines(Long lookupId,Integer businessGroupId) throws DataAccessException{
		Long countVal=0L;
		try{
			//String query = "Select lv.lookupValue from OsiPrReqHeader prh,OsiPrReqLines prl where lv.businessGroupId = :businessGroupId and lv.osiLookupTypes.id=:lookupId and lv.osiLookupTypes.lookupCode in ('approver_level') ORDER BY lv.lookupValue asc ";				
			String query = "Select lv.lookupValue from OsiLookupValues lv where lv.businessGroupId = :businessGroupId and lv.osiLookupTypes.id=:lookupId and lv.osiLookupTypes.lookupCode in ('LINE_TYPE') ORDER BY lv.lookupValue asc";
			@SuppressWarnings("unchecked")
			List<String>  list = (List<String>) this.entityManager.createQuery(query)
						.setParameter("businessGroupId", businessGroupId)
						.setParameter("lookupId", lookupId)
					    .getResultList();
			if(list!=null && list.size()>0){
				StringBuilder queryString=new StringBuilder("(");
				for(String lookupVal:list){
				queryString.append("'"+lookupVal+"',");								
				}
				String queryParam1=queryString.toString().trim();
			    String queryParam=queryParam1.substring(0,queryParam1.length()-1);
			    queryParam=queryParam.concat(")");
				String query1 = "Select count(*) from  OsiPrReqLines prl where prl.businessGroupId = :businessGroupId and prl.reqLineType in "+ queryParam;	
				@SuppressWarnings("unchecked")
				Long count = (Long) this.entityManager.createQuery(query1)
						.setParameter("businessGroupId", businessGroupId)
					    .getSingleResult();	
				countVal=count;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1065" , e.getMessage()); 
		}		
		return countVal;
	}
	
	public Long isLookupUsedinPoHeader(Long lookupId,Integer businessGroupId) throws DataAccessException{
		Long countVal=0L;
		try{
			//String query = "Select lv.lookupValue from OsiPrReqHeader prh,OsiPrReqLines prl where lv.businessGroupId = :businessGroupId and lv.osiLookupTypes.id=:lookupId and lv.osiLookupTypes.lookupCode in ('approver_level') ORDER BY lv.lookupValue asc ";				
			String query = "Select lv.lookupValue from OsiLookupValues lv where lv.businessGroupId = :businessGroupId and lv.osiLookupTypes.id=:lookupId and lv.osiLookupTypes.lookupCode in ('HEADER_TYPE') ORDER BY lv.lookupValue asc";
			@SuppressWarnings("unchecked")
			List<String>  list = (List<String>) this.entityManager.createQuery(query)
						.setParameter("businessGroupId", businessGroupId)
						.setParameter("lookupId", lookupId)
					    .getResultList();
		if(list!=null && list.size()>0){
			StringBuilder queryString=new StringBuilder("(");
			for(String lookupVal:list){
				queryString.append("'"+lookupVal+"',");								
			}
			String queryParam1=queryString.toString().trim();
		    String queryParam=queryParam1.substring(0,queryParam1.length()-1);
		    queryParam=queryParam.concat(")");
			String query1 = "Select count(*) from  OsiPoReqHeader poh where poh.businessGroupId = :businessGroupId and poh.poType in "+ queryParam;	
			@SuppressWarnings("unchecked")
			Long count = (Long) this.entityManager.createQuery(query1)
						.setParameter("businessGroupId", businessGroupId)
					    .getSingleResult();	
			countVal=count;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1065" , e.getMessage()); 
		}		
		return countVal;
	}
	
	public Long isLookupUsedinPoReqLines(Long lookupId,Integer businessGroupId) throws DataAccessException{
		Long countVal=0L;
		try{
			String query = "Select lv.lookupValue from OsiLookupValues lv where lv.businessGroupId = :businessGroupId and lv.osiLookupTypes.id=:lookupId and lv.osiLookupTypes.lookupCode in ('LINE_TYPE') ORDER BY lv.lookupValue asc";
			@SuppressWarnings("unchecked")
			List<String>  list = (List<String>) this.entityManager.createQuery(query)
						.setParameter("businessGroupId", businessGroupId)
						.setParameter("lookupId", lookupId)
					    .getResultList();
			StringBuilder queryString=new StringBuilder("(");
			if(list!=null && list.size()>0){
			for(String lookupVal:list){
				queryString.append("'"+lookupVal+"',");								
			}
			String queryParam1=queryString.toString().trim();
		    String queryParam=queryParam1.substring(0,queryParam1.length()-1);
		    queryParam=queryParam.concat(")");
			String query1 = "Select count(*) from  OsiPoReqLines pol where pol.businessGroupId = :businessGroupId and pol.poLineType in "+ queryParam;	
			@SuppressWarnings("unchecked")
			Long count = (Long) this.entityManager.createQuery(query1)
						.setParameter("businessGroupId", businessGroupId)
					    .getSingleResult();	
			countVal=count;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1065" , e.getMessage()); 
		}		
		return countVal;
	}	

}
