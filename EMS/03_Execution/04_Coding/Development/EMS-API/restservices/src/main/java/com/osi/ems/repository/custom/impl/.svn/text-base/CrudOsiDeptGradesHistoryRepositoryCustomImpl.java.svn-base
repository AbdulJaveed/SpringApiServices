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

import com.osi.ems.domain.OsiDeptGradesHistoryDTO;
import com.osi.ems.repository.custom.ICrudOsiDeptGradesHistoryRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

@Repository
@Transactional
public class CrudOsiDeptGradesHistoryRepositoryCustomImpl implements ICrudOsiDeptGradesHistoryRepositoryCustom {
	
private final Logger LOGGER = Logger.getLogger(getClass());
	
	@PersistenceContext
    EntityManager entityManager;
	
	@Override
	public void save(OsiDeptGradesHistoryDTO osiDeptGradesHistoryDTO) throws DataAccessException {
		String saveGradesHistory = "insert into osi_dept_grades_history"
				+ " (dept_grade_id, org_id, dept_id, grade_id, cost_per_hour, cost_per_month, created_by, "
				+ " creation_date, last_updated_by, last_update_date, internal_cost_overhead_pct )"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		LOGGER.info("## CrudOsiDeptGradesHistoryRepositoryCustomImpl : Save -- Begin ");
		try {
			Query query = this.entityManager.createNativeQuery(saveGradesHistory);
			query.setParameter(1, osiDeptGradesHistoryDTO.getDeptGradeId());
			query.setParameter(2, osiDeptGradesHistoryDTO.getOrgId());
			query.setParameter(3, osiDeptGradesHistoryDTO.getDeptId());
			query.setParameter(4, osiDeptGradesHistoryDTO.getGradeId());
			query.setParameter(5, osiDeptGradesHistoryDTO.getCostPerHour());
			query.setParameter(6, osiDeptGradesHistoryDTO.getCostPerMonth());
			query.setParameter(7, osiDeptGradesHistoryDTO.getCreatedBy());
			query.setParameter(8, osiDeptGradesHistoryDTO.getCreationDate());
			query.setParameter(9, osiDeptGradesHistoryDTO.getLastUpdatedBy());
			query.setParameter(10, osiDeptGradesHistoryDTO.getLastUpdateDate());
			query.setParameter(11, osiDeptGradesHistoryDTO.getInternalCostOverheadPct());
			
			query.executeUpdate();
			
		} catch(Exception e) {
			LOGGER.error("Error Occured while saving record to grades history table");
			throw new DataAccessException("ERR_2001", "Error Occured while saving record to BU grades history table");
		}
		LOGGER.info("## CrudOsiDeptGradesHistoryRepositoryCustomImpl : save -- End ");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiDeptGradesHistoryDTO> getDeptGradeHistory(Integer deptId, Integer orgId, Integer gradeId) throws DataAccessException {
		List<OsiDeptGradesHistoryDTO> deptGradeHistList = new ArrayList<>();
		String getGradeHistory = "select org_id, dept_id, grade_id, cost_per_hour, cost_per_month, internal_cost_overhead_pct, creation_date from osi_dept_grades_history where dept_id = ? and org_id = ? and grade_id = ?";
		LOGGER.info("## CrudOsiDeptGradesHistoryRepositoryCustomImpl: getDeptGradeHistory -- Begin ");
		try {
			List<Object []> newObject = this.entityManager.createNativeQuery(getGradeHistory)
						.setParameter(1, deptId)
						.setParameter(2, orgId)
						.setParameter(3, gradeId)
						.getResultList();
			for(Object[]  gh : newObject){
				OsiDeptGradesHistoryDTO deptGradeHist = new OsiDeptGradesHistoryDTO();
				
				deptGradeHist.setOrgId(gh[0]!= null ? Integer.parseInt(gh[0].toString()) : null);
				deptGradeHist.setDeptId(gh[1]!= null ? Integer.parseInt(gh[1].toString()) : null);
				deptGradeHist.setGradeId(gh[2]!= null ? Integer.parseInt(gh[2].toString()) : null);				
				deptGradeHist.setCostPerHour(gh[3] != null ? new BigDecimal(gh[3].toString()): null);
				deptGradeHist.setCostPerMonth(gh[4] != null ? new BigDecimal(gh[4].toString()): null);
				deptGradeHist.setInternalCostOverheadPct(gh[5] != null ? Integer.parseInt(gh[5].toString()): null);				
				deptGradeHist.setCreationDate(gh[6] != null ? (Date)(gh[6]): null);
				
				deptGradeHistList.add(deptGradeHist);
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		} catch(Exception e) {
			LOGGER.error("Error Occured while getting records from grades history table");
			throw new DataAccessException("ERR_2001", "Error Occured while getting records from DeptGrades history table");
		}
		LOGGER.info("## CrudOsiDeptGradesHistoryRepositoryCustomImpl: getDeptGradeHistory -- End ");
		return deptGradeHistList;
	}
	
	@Override
	public Integer getCountOfDepartmentGradeHistory(OsiDeptGradesHistoryDTO osiDeptGradesHistoryDTO) throws DataAccessException {
		String saveGradesHistory = "select count(*) from osi_dept_grades_history h where h.org_id = ? and h.dept_id = ? and h.grade_id = ? and h.cost_per_hour = ? " + 
				"and h.cost_per_month = ? and h.internal_cost_overhead_pct = ? )";
		Integer count = 0;
		LOGGER.info("## CrudOsiDeptGradesHistoryRepositoryCustomImpl : Save -- Begin ");
		try {
			Query query = this.entityManager.createNativeQuery(saveGradesHistory);
			query.setParameter(1, osiDeptGradesHistoryDTO.getOrgId());
			query.setParameter(2, osiDeptGradesHistoryDTO.getDeptId());
			query.setParameter(3, osiDeptGradesHistoryDTO.getGradeId());
			query.setParameter(4, osiDeptGradesHistoryDTO.getCostPerHour());
			query.setParameter(5, osiDeptGradesHistoryDTO.getCostPerMonth());
			query.setParameter(6, osiDeptGradesHistoryDTO.getInternalCostOverheadPct());
			
			count = query.getFirstResult();			
		} catch(Exception e) {
			LOGGER.error("Error Occured while saving record to grades history table");
			throw new DataAccessException("ERR_2001", "Error Occured while saving record to BU grades history table");
		}
		LOGGER.info("## CrudOsiDeptGradesHistoryRepositoryCustomImpl : save -- End ");
		return count;
	}
}
