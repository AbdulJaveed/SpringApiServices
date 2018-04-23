package com.osi.urm.repository.custom.impl;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiWfsActivities;
import com.osi.ems.repository.OsiWfsActivitiesRepository;
import com.osi.ems.repository.custom.OsiWorkflowsRepositoryCustom;
import com.osi.urm.domain.OsiCertificationDetails;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiCerticationRepositryCustom;

public class OsiCerticationRepositryCustomImpl implements OsiCerticationRepositryCustom{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@PersistenceContext
    private EntityManager entityManager;
	
	@Value( "${WORKFLOW.EMPLOYEE_CERTIFICATION_UPDATION}" )
	private String employeeCertificationUpdation;

	@Autowired
	OsiWfsActivitiesRepository osiWfsActivitiesRepository;
	
	@Autowired
	OsiWorkflowsRepositoryCustom osiWorkflowsRepository;
	
	@Autowired
	private CommonService commonService;

	@Override
	public List<OsiCertificationDetails> getCertificationDetails() {
		// TODO Auto-generated method stub
		 
		List<OsiCertificationDetails> list = null;
		String sql = "select certification_id,certification_name,certification_code,certification_add_info,issued_by,active,created_by,created_date,updated_by,last_update_date from  osi_certifications where active = 1";
		
		try{
			
			list = jdbcTemplate.query(sql, new RowMapper<OsiCertificationDetails>() {

				@Override
				public OsiCertificationDetails mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					OsiCertificationDetails details = new OsiCertificationDetails();

					details.setActive((rs.getInt("active") == 1) ? true : false);
					details.setCertificationId((rs.getString("certification_id") != null) ? rs.getString("certification_id") :"");
					details.setCertificationName((rs.getString("certification_name") != null) ? rs.getString("certification_name") :"");
					details.setIssuedBy((rs.getString("issued_by") != null) ? rs.getString("issued_by") :"");
				
					
					return details;
				}
			});
			
		}catch(Exception ex){
			
		}
		
		return list;
		
	}
	

	@Override
	public List<OsiCertificationDetails> searchCertificationDetails( final OsiCertificationDetails osiCertificationDetails ){
		// TODO Auto-generated method stub
		 
		List<OsiCertificationDetails> list = null;
		StringBuffer sb = new StringBuffer();
		
		sb.append( "select  osic.certification_id,osic.certification_name,osic.issued_by,DATE_FORMAT(empc.year_of_passing, '%Y-%m-%d') as year_of_passing,DATE_FORMAT(empc.expiry_date, '%Y-%m-%d') as expiry_date,empc.gpa_percentage,empc.verified_by,empc.emp_certification_id, "
				+ "empc.is_verified, empc.attachment_id "
					+" from  osi_emp_certifications empc inner join osi_certifications osic on empc.certification_id = osic.certification_id where empc.employee_id = ?");
		
		if(osiCertificationDetails.getCertificationId() != null && !"".equalsIgnoreCase(osiCertificationDetails.getCertificationId())){
			
			sb.append(" and osic.certification_id ='"+osiCertificationDetails.getCertificationId()+"' " );
			
		}
		
		if(osiCertificationDetails.getExpiryDate() != null && !"".equals(osiCertificationDetails.getExpiryDate())){		
			sb.append(" and empc.expiry_date = DATE_FORMAT('"+osiCertificationDetails.getExpiryDate()+"', '%Y-%m-%d') " );			
		}
		
		
		if(osiCertificationDetails.getYearOfPass() != null && !"".equals(osiCertificationDetails.getYearOfPass())){			
			sb.append(" and  year_of_passing = DATE_FORMAT('"+osiCertificationDetails.getYearOfPass()+"', '%Y-%m-%d')  " );				
		}
		
			
		
			
		
		
		try{
			
			list = jdbcTemplate.query(sb.toString(), new Object[]{osiCertificationDetails.getEmployeeId()},new RowMapper<OsiCertificationDetails>() {

				@Override
				public OsiCertificationDetails mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					OsiCertificationDetails details = new OsiCertificationDetails();
					
					details.setCertificationId((rs.getString("certification_id") != null) ? rs.getString("certification_id") :"");
					details.setCertificationName((rs.getString("certification_name") != null) ? rs.getString("certification_name") :"");
					details.setIssuedBy((rs.getString("issued_by") != null) ? rs.getString("issued_by") :"");
					details.setYearOfPass((rs.getString("year_of_passing") != null) ? rs.getString("year_of_passing") :"");
					details.setExpiryDate((rs.getString("expiry_date") != null) ? rs.getString("expiry_date") :"");
					details.setPercentage((rs.getString("gpa_percentage") != null) ? rs.getString("gpa_percentage") :"");
					details.setVerifiedBy((rs.getString("verified_by") != null) ? rs.getString("verified_by") :"");
					details.setEmployeeId(osiCertificationDetails.getEmployeeId());
					details.setEmpCertificationId((rs.getString("emp_certification_id") != null) ? rs.getString("emp_certification_id") :"");
					details.setVerified((rs.getString("is_verified") != null) ? (rs.getString("is_verified").equalsIgnoreCase("1")) ? "true" : "false" :"");
					details.setAttachmentId((rs.getString("attachment_id") != null) ? rs.getInt("attachment_id") :null);
					
					return details;
				}
			});
			
		}catch(Exception ex){
			
		}
		
		return list;
		
	}


	@Override
	public int saveCertifications(OsiCertificationDetails osiCertificationDetails, Integer orgId)  throws DataAccessException{
		int result = 0 ;
		boolean initiateWorkflow = false;
		try {
			if(osiCertificationDetails.getEmpCertificationId() != null && !"".equalsIgnoreCase(osiCertificationDetails.getEmpCertificationId())){
				OsiCertificationDetails osiCertificationDetails1 = getEmployeeCertificatesById(osiCertificationDetails.getEmpCertificationId());
				if(!osiCertificationDetails1.getCertificationId().equalsIgnoreCase(osiCertificationDetails.getCertificationId())
					|| 	(osiCertificationDetails1.getExpiryDate()!=null && !osiCertificationDetails1.getExpiryDate().equalsIgnoreCase(osiCertificationDetails.getExpiryDate()))
					||  !osiCertificationDetails1.getPercentage().equalsIgnoreCase(osiCertificationDetails.getPercentage())
					||  !osiCertificationDetails1.getYearOfPass().equalsIgnoreCase(osiCertificationDetails.getYearOfPass())
					||  osiCertificationDetails1.getAttachmentId()!=osiCertificationDetails.getAttachmentId()
				){
					osiCertificationDetails.setVerified("false");	
				String sql = "update osi_emp_certifications set certification_id = ?, year_of_passing= ?, gpa_percentage=?,expiry_date=?,attachment_id=?,is_verified=?,verified_by=?,updated_by=?,last_update_date=? where emp_certification_id= ?";
				 result = jdbcTemplate.update(sql,new Object[]{ osiCertificationDetails.getCertificationId(), osiCertificationDetails.getYearOfPass(),osiCertificationDetails.getPercentage(),
							osiCertificationDetails.getExpiryDate().equals("")?null : osiCertificationDetails.getExpiryDate(),osiCertificationDetails.getAttachmentId(),(osiCertificationDetails.getVerified().equalsIgnoreCase("true")) ? 1: 0 ,osiCertificationDetails.getUpdatedBy(),
							osiCertificationDetails.getUpdatedBy(),osiCertificationDetails.getLastUpdatedDate(),osiCertificationDetails.getEmpCertificationId()});
				 initiateWorkflow = true;
				}
			}else{
				String sql = "insert into osi_emp_certifications(certification_id,year_of_passing,gpa_percentage,expiry_date,employee_id,attachment_id,is_verified,verified_by,created_by,created_date,updated_by,last_update_date) "
						+ "						values (?,?,?,STR_TO_DATE(?, '%Y-%c-%e %r'),?,?,?,?,?,?,?,?)";
				 result = jdbcTemplate.update(sql,new Object[]{osiCertificationDetails.getCertificationId(), osiCertificationDetails.getYearOfPass(),osiCertificationDetails.getPercentage(),
						osiCertificationDetails.getExpiryDate(),osiCertificationDetails.getEmployeeId(),osiCertificationDetails.getAttachmentId(),(osiCertificationDetails.getVerified().equalsIgnoreCase("true")) ? 1: 0 ,osiCertificationDetails.getVerifiedBy(),
						osiCertificationDetails.getCreatedBy(),osiCertificationDetails.getCreatedDate(),osiCertificationDetails.getUpdatedBy(),osiCertificationDetails.getLastUpdatedDate()
						});
				 initiateWorkflow = true;
			}
			
			if(initiateWorkflow){
				OsiWfsActivities wfsActivities = new OsiWfsActivities();
				Integer employeeId =osiCertificationDetails.getEmployeeId();
				wfsActivities.setObjectId(employeeId);
				wfsActivities.setObjectName("OSI_EMP_CERTIFICATIONS");
				wfsActivities.setProcessFlag("N");
				wfsActivities.setWfsId(osiWorkflowsRepository.getWorkFlow(employeeCertificationUpdation, orgId));
				wfsActivities.setOrgId(orgId);
				wfsActivities.setStartDate(commonService.getCurrentDateStringinUTC());
				if(wfsActivities!=null && osiWorkflowsRepository.verifyExistingWorkflow(wfsActivities.getWfsId(),employeeId ,orgId)==0)
					osiWfsActivitiesRepository.save(wfsActivities);
			}
		} catch (DataAccessException e) {
			throw new DataAccessException("ERR_1000", "Unable to save/update requested certificate");
		}
		
		return result;
	}


	@Override
	public List<OsiCertificationDetails> getCertificationDetailsById(int certificateId) {
		List<OsiCertificationDetails> list = null;
		String sql = "select certification_id,certification_name,certification_code,certification_add_info,issued_by,active,created_by,created_date,updated_by,last_update_date from  osi_certifications where certification_id = ?";
		
		try{
			
			list = jdbcTemplate.query(sql, new Object[]{certificateId},new RowMapper<OsiCertificationDetails>() {

				@Override
				public OsiCertificationDetails mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					OsiCertificationDetails details = new OsiCertificationDetails();

					details.setActive((rs.getInt("active") == 1) ? true : false);
					details.setCertificationId((rs.getString("certification_id") != null) ? rs.getString("certification_id") :"");
					details.setCertificationName((rs.getString("certification_name") != null) ? rs.getString("certification_name") :"");
					details.setCertificationCode((rs.getString("certification_code") != null) ? rs.getString("certification_code") :"");
					details.setIssuedBy((rs.getString("issued_by") != null) ? rs.getString("issued_by") :"");
				
					
					return details;
				}
			});
			
		}catch(Exception ex){
			
		}
		return list;
	}
	public OsiCertificationDetails getEmployeeCertificatesById(String empCertificateId) throws DataAccessException {
		OsiCertificationDetails osiCertificationDetails = null;
		try {
			Query query = entityManager.createNativeQuery("select certification_id,year_of_passing,gpa_percentage,expiry_date,attachment_id from osi_emp_certifications where emp_certification_id = ?").setParameter(1, empCertificateId);
			
			Object[] object = (Object[]) query.getSingleResult();
			osiCertificationDetails= new OsiCertificationDetails();
			osiCertificationDetails.setCertificationId(object[0].toString());
			osiCertificationDetails.setYearOfPass(object[1].toString());
			osiCertificationDetails.setPercentage(object[2].toString());
			if(object[3]!=null)
				osiCertificationDetails.setExpiryDate(object[3].toString());
			if(object[4]!=null)
				osiCertificationDetails.setAttachmentId(Integer.parseInt(object[4].toString()));
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		return osiCertificationDetails;
	}

	@Override
	public boolean findByCetificationIdAndEmployeeId(String certificationId, Integer employeeId) {
		boolean result = false;
		String sql = "select count(*) from osi_emp_certifications e where e.certification_id = ? and e.employee_id = ? ";
		try{
			
			BigInteger resultValue = (BigInteger)entityManager.createNativeQuery(sql).setParameter(1, certificationId).setParameter(2, employeeId).getSingleResult();
			Integer resultInt = ((BigInteger) resultValue).intValue();
			if(resultValue !=null && resultInt == 1)
				result = true;
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return result;
	}


	@Override
	public int updateVerifiedSkills(OsiCertificationDetails certificate) throws DataAccessException {
		int result = 0;
		try {
			if(certificate.getEmpCertificationId() != null && !"".equalsIgnoreCase(certificate.getEmpCertificationId())){
				String sql = "update  osi_emp_certifications set is_verified = ?, updated_by = ? ,last_update_date = ? where emp_certification_id = ?";
				result = jdbcTemplate.update(sql,new Object[]{(certificate.getVerified().equalsIgnoreCase("true")) ? 1: 0 ,
						certificate.getUpdatedBy(),certificate.getLastUpdatedDate(),certificate.getEmpCertificationId()});
			}
		}catch(Exception ex){
			throw new DataAccessException("ERR_1000", "Exception while updating the Skills Verification");
		}
		return result;
	}

}
