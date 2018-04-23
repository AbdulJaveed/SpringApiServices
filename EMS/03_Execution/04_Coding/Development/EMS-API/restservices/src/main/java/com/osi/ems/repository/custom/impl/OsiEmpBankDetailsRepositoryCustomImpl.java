package com.osi.ems.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiEmpBankDetails;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.repository.custom.OsiEmpBankDetailsRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

@Component
public class OsiEmpBankDetailsRepositoryCustomImpl implements OsiEmpBankDetailsRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final Logger LOGGER = Logger.getLogger(OsiEmpBankDetailsRepositoryCustomImpl.class);
	
	public List<OsiEmployees> getAllEmployees() {
		return null;
	}

	@Override
	public List<OsiEmpBankDetails> getAllEmpBankInfoDetails() throws DataAccessException {
		List<OsiEmpBankDetails> bankDetailsListQuery = new ArrayList<OsiEmpBankDetails>();
		LOGGER.info("getAllEmpBankInfoDetails :: Begin ");
		try {
			bankDetailsListQuery = entityManager.createQuery("FROM OsiEmpBankDetails b ORDER BY b.createdDate DESC", OsiEmpBankDetails.class)
					.getResultList();

		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found for osi employee bank details");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllEmpBankInfoDetails :: End ");
		
		return bankDetailsListQuery;
	}
	
	@Override
	public List<OsiEmpBankDetails> getAllEmpBankInfoDetailsByEmployeeId(Integer employeeId) throws DataAccessException {
		List<OsiEmpBankDetails> bankDetailsListQuery = new ArrayList<OsiEmpBankDetails>();
		LOGGER.info("getAllEmpBankInfoDetailsByEmployeeId :: Begin ");
		try {
			bankDetailsListQuery = entityManager.createQuery("FROM OsiEmpBankDetails b where b.employeeId = :employeeId ORDER BY b.createdDate DESC", 
					OsiEmpBankDetails.class).setParameter("employeeId", employeeId).getResultList();

		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllEmpBankInfoDetailsByEmployeeId :: End ");
	
		return bankDetailsListQuery;
	}
	
	@Override
	public Integer saveEmpBankDetails(OsiEmpBankDetails osiEmpBankDetails) throws DataAccessException {
		Integer result = null;
		LOGGER.info("saveEmpBankDetails :: Begin ");
		try {
			Query query = entityManager.createNativeQuery("INSERT INTO osi_emp_bank_details(bank_id, account_holder_name, branch_name, ifsc_code"
								+ " , account_number, bank_name, active, employee_id, created_by"
								+ ", `created_date`, `updated_by`, `last_update_date`) "
								+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ");
				  
				   query.setParameter(1, osiEmpBankDetails.getId());
				   query.setParameter(2, osiEmpBankDetails.getAccountHolderName());
				   query.setParameter(3, osiEmpBankDetails.getBranchName());
				   query.setParameter(4, osiEmpBankDetails.getIfscCode());
				   query.setParameter(5, osiEmpBankDetails.getAccountNumber());
				   query.setParameter(6, osiEmpBankDetails.getBankName());
				   query.setParameter(7, osiEmpBankDetails.getActive());
				   query.setParameter(8, osiEmpBankDetails.getEmployeeId());
				   query.setParameter(9, osiEmpBankDetails.getCreatedBy());
				   query.setParameter(10, osiEmpBankDetails.getCreatedDate());
				   query.setParameter(11, osiEmpBankDetails.getUpdatedBy());
				   query.setParameter(12, osiEmpBankDetails.getLastUpdateDate());
				  
				   
				   result = query.executeUpdate();
				   
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while saving");
		}
		LOGGER.info("saveEmpBankDetails :: End ");
		return result;
	}
	
	@Override
	public Integer updateEmpBankDetails(OsiEmpBankDetails osiEmpBankDetails) throws DataAccessException {
		Integer result = null;
		LOGGER.info("updateEmpBankDetails :: Begin ");
		try {
			Query query = entityManager.createNativeQuery("UPDATE osi_emp_bank_details SET account_holder_name = :account_holder_name, "
					+ "branch_name = :branch_name, ifsc_code = :ifsc_code"
								+ " , account_number = :account_number, bank_name = :bank_name, active = :active, employee_id = :employee_id, created_by = :created_by"
								+ ", updated_by = :updated_by, last_update_date = :last_update_date "
								+ "  WHERE bank_id = :id");
			
			
				   query.setParameter("id", osiEmpBankDetails.getId());
				   query.setParameter("account_holder_name", osiEmpBankDetails.getAccountHolderName());
				   query.setParameter("branch_name", osiEmpBankDetails.getBranchName());
				   query.setParameter("ifsc_code", osiEmpBankDetails.getIfscCode());
				   query.setParameter("account_number", osiEmpBankDetails.getAccountNumber());
				   
				   query.setParameter("bank_name", osiEmpBankDetails.getBankName());
				   query.setParameter("active", osiEmpBankDetails.getActive());
				   query.setParameter("employee_id", osiEmpBankDetails.getEmployeeId());
				   query.setParameter("created_by", osiEmpBankDetails.getCreatedBy());
				   
				   query.setParameter("updated_by", osiEmpBankDetails.getUpdatedBy());
				   query.setParameter("last_update_date", osiEmpBankDetails.getLastUpdateDate());
				  
				   
				   result = query.executeUpdate();
				   
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("updateEmpBankDetails :: End ");
		return result;
	}
	
	@Override
	public Integer updateEmpBankDetailsStatus(Integer employeeId, String acctNo, String ifscCode) throws DataAccessException {
		Integer result = null;
		LOGGER.info("updateEmpBankDetailsStatus :: Begin ");
		try {
			Query query = entityManager.createNativeQuery("UPDATE osi_emp_bank_details " + 
														  "SET active = 0 " + 
														  "WHERE employee_id = :employeeId " + 
														  "AND account_number != :acctNo " + 
														  "AND ifsc_code != :ifscCode ");
			
		   query.setParameter("employeeId", employeeId);
		   query.setParameter("acctNo", acctNo);
		   query.setParameter("ifscCode", ifscCode);
		   result = query.executeUpdate();
				   
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("updateEmpBankDetailsStatus :: End ");
		return result;
	}
	
	public OsiEmpBankDetails getEmpBankDetailById(Integer id) throws DataAccessException {
		LOGGER.info("getEmpBankDetailById :: Begin ");
		OsiEmpBankDetails osiEmpBankDetails = new OsiEmpBankDetails();
		try {
			osiEmpBankDetails = entityManager.createQuery("FROM OsiEmpBankDetails o WHERE o.id = :id ", OsiEmpBankDetails.class)
					.setParameter("id",id)
					.getSingleResult();

		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		LOGGER.info("getEmpBankDetailById :: End ");
		return osiEmpBankDetails;
	}
}
