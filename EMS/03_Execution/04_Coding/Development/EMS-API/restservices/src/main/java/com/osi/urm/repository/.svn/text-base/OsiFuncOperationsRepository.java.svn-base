package com.osi.urm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osi.urm.domain.OsiFuncOperations;


@Repository
public interface OsiFuncOperationsRepository extends JpaRepository<OsiFuncOperations,Long>  {
	List<OsiFuncOperations> findByOsiFunctionsId(Integer id);

}
