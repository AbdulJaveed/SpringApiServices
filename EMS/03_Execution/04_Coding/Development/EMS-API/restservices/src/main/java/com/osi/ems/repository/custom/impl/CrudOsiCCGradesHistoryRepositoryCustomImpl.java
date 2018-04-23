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

import com.osi.ems.domain.OsiCCGradesHistoryDTO;
import com.osi.ems.repository.custom.ICrudOsiCCGradesHistoryRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

@Repository
@Transactional
public class CrudOsiCCGradesHistoryRepositoryCustomImpl implements ICrudOsiCCGradesHistoryRepositoryCustom {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void save(OsiCCGradesHistoryDTO osiCCGradesHistoryDTO) throws DataAccessException {
		String saveGradesHistory = "insert into osi_cc_grades_history"
				+ " (cc_grade_id, org_id, cc_id, grade_id, cost_per_hour, cost_per_month, created_by, "
				+ " creation_date, last_updated_by, last_update_date, internal_cost_overhead_pct )"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		LOGGER.info("## CrudOsiCCGradesRepositoryCustomImpl : Save -- Begin ");
		try {
			Query query = this.entityManager.createNativeQuery(saveGradesHistory);
			query.setParameter(1, osiCCGradesHistoryDTO.getCcGradeId());
			query.setParameter(2, osiCCGradesHistoryDTO.getOrgId());
			query.setParameter(3, osiCCGradesHistoryDTO.getCcId());
			query.setParameter(4, osiCCGradesHistoryDTO.getGradeId());
			query.setParameter(5, osiCCGradesHistoryDTO.getCostPerHour());
			query.setParameter(6, osiCCGradesHistoryDTO.getCostPerMonth());
			query.setParameter(7, osiCCGradesHistoryDTO.getCreatedBy());
			query.setParameter(8, osiCCGradesHistoryDTO.getCreationDate());
			query.setParameter(9, osiCCGradesHistoryDTO.getLastUpdatedBy());
			query.setParameter(10, osiCCGradesHistoryDTO.getLastUpdateDate());
			query.setParameter(11, osiCCGradesHistoryDTO.getInternalCostOverheadPct());

			query.executeUpdate();

		} catch (Exception e) {
			LOGGER.error("Error Occured while saving record to grades history table");
			throw new DataAccessException("ERR_2001", "Error Occured while saving record to grades history table");
		}
		LOGGER.info("## CrudOsiCCGradesRepositoryCustomImpl : save -- End ");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiCCGradesHistoryDTO> getCCGradeHistory(Integer ccGradeId, Integer orgId, Integer gradeId)
			throws DataAccessException {
		List<OsiCCGradesHistoryDTO> ccGradeHistList = new ArrayList<>();
		String getGradeHistory = "select org_id, cc_id, grade_id, cost_per_hour, cost_per_month, internal_cost_overhead_pct, creation_date from osi_cc_grades_history where cc_id = ? and org_id = ? and grade_id = ?";
		LOGGER.info("## CrudOsiCCGradesRepositoryCustomImpl: getCCGradeHistory -- Begin ");
		try {
			List<Object[]> newObject = this.entityManager.createNativeQuery(getGradeHistory).setParameter(1, ccGradeId)
					.setParameter(2, orgId).setParameter(3, gradeId).getResultList();
			for (Object[] gh : newObject) {
				OsiCCGradesHistoryDTO ccGradeHist = new OsiCCGradesHistoryDTO();

				ccGradeHist.setOrgId(gh[0] != null ? Integer.parseInt(gh[0].toString()) : null);
				ccGradeHist.setCcId(gh[1] != null ? Integer.parseInt(gh[1].toString()) : null);
				ccGradeHist.setGradeId(gh[2] != null ? Integer.parseInt(gh[2].toString()) : null);
				ccGradeHist.setCostPerHour(gh[3] != null ? new BigDecimal(gh[3].toString()) : null);
				ccGradeHist.setCostPerMonth(gh[4] != null ? new BigDecimal(gh[4].toString()) : null);
				ccGradeHist.setInternalCostOverheadPct(gh[5] != null ? Integer.parseInt(gh[5].toString()) : null);
				ccGradeHist.setCreationDate(gh[6] != null ? (Date) (gh[6]) : null);

				ccGradeHistList.add(ccGradeHist);
			}
		} catch (NoResultException e) {
			LOGGER.error("Exception occured " + e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		} catch (Exception e) {
			LOGGER.error("Error Occured while getting records from grades history table");
			throw new DataAccessException("ERR_2001",
					"Error Occured while getting records from ccGrades history table");
		}
		LOGGER.info("## CrudOsiCCGradesRepositoryCustomImpl: getCCGradeHistory -- End ");
		return ccGradeHistList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getCCGradeHistoryCount(OsiCCGradesHistoryDTO costHistory) throws DataAccessException {
		Integer count = 0;
		String getGradeHistory = "select count(*) from osi_cc_grades_history h where h.org_id = ? and h.cc_id = ? and h.grade_id = ? and h.cost_per_hour = ? "
				+ "and h.cost_per_month = ? and h.internal_cost_overhead_pct = ?";

		LOGGER.info("## CrudOsiCCGradesRepositoryCustomImpl: getCCGradeHistoryCount -- Begin ");
		try {
			count = (int) this.entityManager.createNativeQuery(getGradeHistory).setParameter(1, costHistory.getOrgId())
					.setParameter(2, costHistory.getCcId()).setParameter(3, costHistory.getGradeId())
					.setParameter(4, costHistory.getCostPerHour()).setParameter(5, costHistory.getCostPerMonth())
					.setParameter(6, costHistory.getInternalCostOverheadPct()).getFirstResult();

		} catch (NoResultException e) {
			LOGGER.error("Exception occured " + e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		} catch (Exception e) {
			LOGGER.error("Error Occured while getting records from grades history table");
			throw new DataAccessException("ERR_2001",
					"Error Occured while getting records from ccGrades history table");
		}
		LOGGER.info("## CrudOsiCCGradesRepositoryCustomImpl: getCCGradeHistoryCount -- End ");
		
		return count;
	}

}
