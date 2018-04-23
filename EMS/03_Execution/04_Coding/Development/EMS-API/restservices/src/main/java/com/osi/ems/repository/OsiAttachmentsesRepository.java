package com.osi.ems.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiAttachments;

@Repository
public interface OsiAttachmentsesRepository extends JpaRepository<OsiAttachments, Serializable>{

}
