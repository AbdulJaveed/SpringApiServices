package com.osi.urm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.osi.urm.domain.EmrContactDetais;
import com.osi.urm.repository.custom.ContactsRepositryCustom;
import com.osi.urm.service.ContactService;

public class ContactServiceImpl implements ContactService{
	
	@Autowired
	private ContactsRepositryCustom contactsRepositryCustom;

	@Override
	public int saveContacts(EmrContactDetais[] contactDetais) {
		// TODO Auto-generated method stub
		return contactsRepositryCustom.saveContacts(contactDetais);
	}

	@Override
	public EmrContactDetais[] getContactDetails(String emplId) {
		// TODO Auto-generated method stub
		 return contactsRepositryCustom.getContactDetails(emplId);
	}


}
