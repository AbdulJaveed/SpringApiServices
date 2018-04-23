package com.osi.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.ems.domain.OsiContacts;

public interface OsiContactsRepository extends JpaRepository<OsiContacts,Integer>{
	
	
public List<OsiContacts> findOneByEmployeeId(Integer employeeId);
public OsiContacts findOneByEmployeeIdAndContactType(Integer employeeId,String contactType);


}
