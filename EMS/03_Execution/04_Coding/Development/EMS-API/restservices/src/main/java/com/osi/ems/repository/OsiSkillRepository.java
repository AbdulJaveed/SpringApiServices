package com.osi.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.ems.domain.OsiSkils;

public interface OsiSkillRepository extends JpaRepository<OsiSkils, Integer> {

	public List<OsiSkils> findByActive(Integer active);
}
