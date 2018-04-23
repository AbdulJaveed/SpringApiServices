package com.osi.ems.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiEmpAdditionalDocs;

@Repository
public interface OsiEmpAdditionalDocsRepository extends JpaRepository<OsiEmpAdditionalDocs, Serializable>{

	List<OsiEmpAdditionalDocs> findByEmployeeId(Integer employeeId);
}