package com.osi.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.ems.domain.OsiCertificationGroups;

public interface OsiCertificationGroupsRepository extends JpaRepository<OsiCertificationGroups, Integer> {

	public List<OsiCertificationGroups> findByGroupNameContaining(String groupName);

	public List<OsiCertificationGroups> findByIsActive(Integer isActive);

}
