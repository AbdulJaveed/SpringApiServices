package com.osi.urm.service;

import com.osi.urm.domain.EmrContactDetais;

public interface ContactService {
	

	public int saveContacts( EmrContactDetais[] contactDetais );
	
	public EmrContactDetais[] getContactDetails(String emplId) ;
	
	
}
