package com.osi.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.ems.domain.OsiSkillGroups;

public interface OsiSkillGroupsRepository extends JpaRepository<OsiSkillGroups, Integer> {

	public List<OsiSkillGroups> findByGroupNameContaining(String groupName);

	public List<OsiSkillGroups> findByIsActive(Integer isActive);

}
