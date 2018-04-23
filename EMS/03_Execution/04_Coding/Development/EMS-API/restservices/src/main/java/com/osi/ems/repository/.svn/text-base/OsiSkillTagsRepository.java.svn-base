package com.osi.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.ems.domain.OsiSkillTags;

public interface OsiSkillTagsRepository extends JpaRepository<OsiSkillTags, Integer> {
	public List<OsiSkillTags> findByTagNameContaining(String tagName);
	
	public List<OsiSkillTags> findByIsActive(Integer isActive);
}
