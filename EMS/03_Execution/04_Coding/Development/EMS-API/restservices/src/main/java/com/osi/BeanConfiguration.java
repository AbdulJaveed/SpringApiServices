package com.osi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.osi.urm.repository.custom.ContactsRepositryCustom;
import com.osi.urm.repository.custom.OsiCerticationRepositryCustom;
import com.osi.urm.repository.custom.OsiSkillRepositryCustom;
import com.osi.urm.repository.custom.impl.ContactsRepositryCustomImpl;
import com.osi.urm.repository.custom.impl.OsiCerticationRepositryCustomImpl;
import com.osi.urm.repository.custom.impl.OsiSkillRepositryCustomImpl;
import com.osi.urm.service.ContactService;
import com.osi.urm.service.OsiCertifactionService;
import com.osi.urm.service.SkillsService;
import com.osi.urm.service.impl.ContactServiceImpl;
import com.osi.urm.service.impl.OsiCertifactionServiceImpl;
import com.osi.urm.service.impl.SkillServiceImpl;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public OsiCertifactionService osiCertifactionService(){
		return new OsiCertifactionServiceImpl();
	}
	
	@Bean
	public OsiCerticationRepositryCustom osiCerticationRepositryCustom(){
		return new OsiCerticationRepositryCustomImpl();
	}
	
	@Bean
	public SkillsService skillsService(){
		return new SkillServiceImpl();
	}
	
	@Bean
	public OsiSkillRepositryCustom OsiSkillRepositryCustom(){
		return new OsiSkillRepositryCustomImpl();
	}
	
	@Bean
	public ContactService contactEmergencyService(){
		return new ContactServiceImpl();
	}
	
	@Bean
	public ContactsRepositryCustom contactsRepositryCustom(){
		return new ContactsRepositryCustomImpl();
	}

}
