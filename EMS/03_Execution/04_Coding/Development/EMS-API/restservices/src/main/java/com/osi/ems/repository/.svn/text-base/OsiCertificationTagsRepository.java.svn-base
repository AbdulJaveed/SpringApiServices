package com.osi.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.ems.domain.OsiCertificationTags;

public interface OsiCertificationTagsRepository extends JpaRepository<OsiCertificationTags, Integer> {
	public List<OsiCertificationTags> findByTagNameContaining(String tagName);

	public List<OsiCertificationTags> findByIsActive(Integer isActive);
}
