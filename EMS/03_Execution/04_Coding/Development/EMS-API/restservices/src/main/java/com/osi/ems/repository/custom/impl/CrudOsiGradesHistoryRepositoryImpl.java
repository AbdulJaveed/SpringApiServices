package com.osi.ems.repository.custom.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiGradesHistory;
import com.osi.ems.repository.custom.ICrudOsiGradesHistoryRepository;
import com.osi.urm.exception.DataAccessException;

@Repository
@Transactional
public class CrudOsiGradesHistoryRepositoryImpl implements ICrudOsiGradesHistoryRepository {
	
	private final Logger LOGGER = Logger.getLogger(CrudOsiGradesHistoryRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager entityManager;
	
	@Override
	public void save(OsiGradesHistory osiGradesHistory) throws DataAccessException {
		String saveGradesHistory = "insert into osi_grades_history(grade_id, grade_name, grade_description, org_id, active, seq, cost_per_hour, "
				+ "cost_per_month, rev_per_hour, rev_per_month, created_by, created_date, last_updated_by, last_update_date) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		LOGGER.info("## CrudOsiGradesHistoryRepositoryImpl:save -- Begin ");
		try {
			Query query = this.entityManager.createNativeQuery(saveGradesHistory);
			query.setParameter(1, osiGradesHistory.getGradeId());
			query.setParameter(2, osiGradesHistory.getGradeName());
			query.setParameter(3, osiGradesHistory.getGradeDescription());
			query.setParameter(4, osiGradesHistory.getOrgId());
			query.setParameter(5, osiGradesHistory.getActive());
			query.setParameter(6, osiGradesHistory.getSeq());
			query.setParameter(7, osiGradesHistory.getCostPerHour());
			query.setParameter(8, osiGradesHistory.getCostPerMonth());
			query.setParameter(9, osiGradesHistory.getRevPerHour());
			query.setParameter(10, osiGradesHistory.getRevPerMonth());
			query.setParameter(11, osiGradesHistory.getCreatedBy());
			query.setParameter(12, osiGradesHistory.getCreatedDate());
			query.setParameter(13, osiGradesHistory.getUpdatedBy());
			query.setParameter(14, osiGradesHistory.getLastUpdateDate());
			
			query.executeUpdate();
			
		} catch(Exception e) {
			LOGGER.error("Error Occured while saving record to grades history table");
			throw new DataAccessException("ERR_2001", "Error Occured while saving record to grades history table");
		}
		LOGGER.info("## CrudOsiGradesHistoryRepositoryImpl:save -- End ");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiGradesHistory> getGradeHistory(Integer gradeId, Integer orgId) throws DataAccessException {
		List<OsiGradesHistory> gradeHistList = new ArrayList<>();
		String getGradeHistory = "select grade_id, grade_name, grade_description, org_id, active, seq, cost_per_hour, cost_per_month, rev_per_hour, rev_per_month, created_date from osi_grades_history where grade_id = ? and org_id = ?";
		LOGGER.info("## CrudOsiGradesHistoryRepositoryImpl:getGradeHistory -- Begin ");
		try {
			List<Object []> newObject = this.entityManager.createNativeQuery(getGradeHistory).setParameter(1, gradeId).setParameter(2, orgId).getResultList();
			for(Object[]  gh : newObject){
				OsiGradesHistory gradeHist = new OsiGradesHistory();
				gradeHist.setGradeId(gh[0]!= null ? Integer.parseInt(gh[0].toString()) : null);
				gradeHist.setGradeName((String) gh[1]);
				gradeHist.setGradeDescription((String) gh[2]);
				gradeHist.setOrgId(gh[3]!= null ? Integer.parseInt(gh[3].toString()) : null);
				gradeHist.setActive(gh[4]!= null ? Integer.parseInt(gh[4].toString()) : null);
				gradeHist.setSeq(gh[5]!= null ? Integer.parseInt(gh[5].toString()) : null);
				gradeHist.setCostPerHour(gh[6] != null ? new BigDecimal(gh[6].toString()): null);
				gradeHist.setCostPerMonth(gh[7] != null ? new BigDecimal(gh[7].toString()): null);
				gradeHist.setRevPerHour(gh[8] != null ? new BigDecimal(gh[8].toString()): null);
				gradeHist.setRevPerMonth(gh[9] != null ? new BigDecimal(gh[9].toString()): null);
				gradeHist.setCreatedDate(gh[10] != null ? (Date)(gh[10]): null);
				
				gradeHistList.add(gradeHist);
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		} catch(Exception e) {
			LOGGER.error("Error Occured while getting records from grades history table");
			throw new DataAccessException("ERR_2001", "Error Occured while getting records from grades history table");
		}
		LOGGER.info("## CrudOsiGradesHistoryRepositoryImpl:getGradeHistory -- End ");
		return gradeHistList;
	}

}
