package com.osi.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.ems.domain.OsiAssignments;
import com.osi.urm.exception.DataAccessException;

public interface OsiAssignmentsRepository extends JpaRepository<OsiAssignments, Integer>{

	@Query("FROM OsiAssignments oa WHERE oa.assignmentId = :id")
	public OsiAssignments findByAssignmentId(@Param("id") Integer id);

	@Query("FROM OsiAssignments")
	public List<OsiAssignments> getAllAssignments() throws DataAccessException;

}
