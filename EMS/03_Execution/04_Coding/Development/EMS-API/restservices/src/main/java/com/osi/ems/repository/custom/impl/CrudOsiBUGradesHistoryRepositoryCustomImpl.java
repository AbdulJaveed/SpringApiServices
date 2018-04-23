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

import com.osi.ems.domain.OsiBUGradesHistoryDTO;
import com.osi.ems.repository.custom.ICrudOsiBUGradesHistoryRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

@Repository
@Transactional
public class CrudOsiBUGradesHistoryRepositoryCustomImpl implements ICrudOsiBUGradesHistoryRepositoryCustom {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void save(OsiBUGradesHistoryDTO osiBUGradesHistoryDTO) throws DataAccessException {
		String saveGradesHistory = "insert into osi_bu_grades_history"
				+ " (bu_grade_id, org_id, bu_id, grade_id, cost_per_hour, cost_per_month, created_by, "
				+ " creation_date, last_updated_by, last_update_date, internal_cost_overhead_pct )"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		LOGGER.info("## CrudOsiBUGradesHistoryRepositoryCustomImpl : Save -- Begin ");
		try {
			Query query = this.entityManager.createNativeQuery(saveGradesHistory);
			query.setParameter(1, osiBUGradesHistoryDTO.getBuGradeId());
			query.setParameter(2, osiBUGradesHistoryDTO.getOrgId());
			query.setParameter(3, osiBUGradesHistoryDTO.getBuId());
			query.setParameter(4, osiBUGradesHistoryDTO.getGradeId());
			query.setParameter(5, osiBUGradesHistoryDTO.getCostPerHour());
			query.setParameter(6, osiBUGradesHistoryDTO.getCostPerMonth());
			query.setParameter(7, osiBUGradesHistoryDTO.getCreatedBy());
			query.setParameter(8, osiBUGradesHistoryDTO.getCreationDate());
			query.setParameter(9, osiBUGradesHistoryDTO.getLastUpdatedBy());
			query.setParameter(10, osiBUGradesHistoryDTO.getLastUpdateDate());
			query.setParameter(11, osiBUGradesHistoryDTO.getInternalCostOverheadPct());

			query.executeUpdate();

		} catch (Exception e) {
			LOGGER.error("Error Occured while saving record to grades history table");
			throw new DataAccessException("ERR_2001", "Error Occured while saving record to BU grades history table");
		}
		LOGGER.info("## CrudOsiBUGradesHistoryRepositoryCustomImpl : save -- End ");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiBUGradesHistoryDTO> getBUGradeHistory(Integer buId, Integer orgId, Integer gradeId)
			throws DataAccessException {
		List<OsiBUGradesHistoryDTO> buGradeHistList = new ArrayList<>();
		String getGradeHistory = "select org_id, bu_id, grade_id, cost_per_hour, cost_per_month, internal_cost_overhead_pct, creation_date from osi_bu_grades_history where bu_id = ? and org_id = ? and grade_id = ?";
		LOGGER.info("## CrudOsiBUGradesHistoryRepositoryCustomImpl: getBUGradeHistory -- Begin ");
		try {
			List<Object[]> newObject = this.entityManager.createNativeQuery(getGradeHistory).setParameter(1, buId)
					.setParameter(2, orgId).setParameter(3, gradeId).getResultList();
			for (Object[] gh : newObject) {
				OsiBUGradesHistoryDTO buGradeHist = new OsiBUGradesHistoryDTO();

				buGradeHist.setOrgId(gh[0] != null ? Integer.parseInt(gh[0].toString()) : null);
				buGradeHist.setBuId(gh[1] != null ? Integer.parseInt(gh[1].toString()) : null);
				buGradeHist.setGradeId(gh[2] != null ? Integer.parseInt(gh[2].toString()) : null);
				buGradeHist.setCostPerHour(gh[3] != null ? new BigDecimal(gh[3].toString()) : null);
				buGradeHist.setCostPerMonth(gh[4] != null ? new BigDecimal(gh[4].toString()) : null);
				buGradeHist.setInternalCostOverheadPct(gh[5] != null ? Integer.parseInt(gh[5].toString()) : null);
				buGradeHist.setCreationDate(gh[6] != null ? (Date) (gh[6]) : null);

				buGradeHistList.add(buGradeHist);
			}
		} catch (NoResultException e) {
			LOGGER.error("Exception occured " + e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		} catch (Exception e) {
			LOGGER.error("Error Occured while getting records from grades history table");
			throw new DataAccessException("ERR_2001",
					"Error Occured while getting records from BUGrades history table");
		}
		LOGGER.info("## CrudOsiBUGradesHistoryRepositoryCustomImpl: getBUGradeHistory -- End ");
		return buGradeHistList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getBUGradeHistoryCount(OsiBUGradesHistoryDTO history) throws DataAccessException {
		Integer count = 0;
		String getGradeHistory = "select count(*) from osi_bu_grades_history h where h.org_id=? and h.bu_id=? and h.grade_id = ? and h.cost_per_hour=? and h.cost_per_month=? "
				+ "and h.internal_cost_overhead_pct=?";
		LOGGER.info("## CrudOsiBUGradesHistoryRepositoryCustomImpl: getBUGradeHistoryCount -- Begin ");
		try {
			count =  (int)this.entityManager.createNativeQuery(getGradeHistory).setParameter(1, history.getOrgId())
					.setParameter(2, history.getBuId()).setParameter(3, history.getGradeId())
					.setParameter(4, history.getCostPerHour()).setParameter(5, history.getCostPerMonth())
					.setParameter(6, history.getInternalCostOverheadPct()).getFirstResult();

		} catch (NoResultException e) {
			LOGGER.error("Exception occured " + e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		} catch (Exception e) {
			LOGGER.error("Error Occured while getting count from grades history table");
			throw new DataAccessException("ERR_2001",
					"Error Occured while getting count from BUGrades history table");
		}
		LOGGER.info("## CrudOsiBUGradesHistoryRepositoryCustomImpl: getBUGradeHistoryCount -- End ");
		return count;
	}
}
