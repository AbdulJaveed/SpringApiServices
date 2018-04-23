package com.osi.urm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osi.urm.domain.OsiUserLogins;

/**
 * Spring Data JPA repository for the OsiUserLogins entity.
 */
@Repository
public interface OsiUserLoginsRepository extends JpaRepository<OsiUserLogins,Integer> {

}
