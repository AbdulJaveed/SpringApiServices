package com.osi.ems.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.ems.domain.OsiSubPracticeGradesHistory;

public interface OsiSubPracticeGradesHistoryRepository extends JpaRepository<OsiSubPracticeGradesHistory, Integer> {

	public List<OsiSubPracticeGradesHistory> findByOrgIdAndSubPracticeIdAndGradeId(Integer orgId, Integer subPracticeId,
			Integer gradeId);

	public Long countByOrgIdAndSubPracticeIdAndGradeIdAndCostPerHourAndCostPerMonthAndInternalCostOverheadPercentage(
			Integer ogrId, Integer subPracticeId, Integer gradeId, BigDecimal costPerHour, BigDecimal costPerMonth,
			Integer internalCostOverheadPercentage);
}
