package com.osi.urm.repository.custom.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.osi.ems.common.CommonService;
import com.osi.urm.domain.EmrContactDetais;
import com.osi.urm.repository.custom.ContactsRepositryCustom;

public class ContactsRepositryCustomImpl implements ContactsRepositryCustom {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CommonService commonService;
	
	@Override
	public int saveContacts(EmrContactDetais[] contactDetais) {
		int result = 0;
		String sql = "insert into osi_contacts (contact_name,contact_number,relation,country_code,employee_id,created_by,creation_date,last_updated_by,last_update_date,seq,altr_contact_number,altr_country_code,contact_type) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		for (EmrContactDetais details : contactDetais) {
			if(details.getEmergencyContactId() != null && !"".equals(details.getEmergencyContactId())){
				editEmergencyContacts(details);
			}else{
			result = jdbcTemplate.update(
					sql,
					new Object[] { details.getName(),
							details.getMobileNumber(), details.getRelation(),
							details.getCountryCode(), details.getEmployeeId(),
							details.getCreatedBy(), commonService.getCurrentDateStringinUTC(), 
							details.getUpdatedBy(), commonService.getCurrentDateStringinUTC(), 
							(details.getSeq() != null && "1".equalsIgnoreCase(details.getSeq())) ? details.getSeq() : "0",
							details.getAltrMobileNumber(),details.getAltrCountryCode(),"emergency"});
			}
		}
		return result;
	}
	

	public int editEmergencyContacts(EmrContactDetais details) {
		int result = 0;
		String sql = "update osi_contacts set contact_name=?,contact_number=?,relation=?,country_code=?,last_updated_by=?,last_update_date = ? ,seq = ?,altr_contact_number=?,altr_country_code=?  "
				+ "  where contact_id = ?";
		
		
			result = jdbcTemplate.update(
					sql,
					new Object[] { details.getName(),
							details.getMobileNumber(), details.getRelation(),
							details.getCountryCode(),  details.getUpdatedBy(), commonService.getCurrentDateStringinUTC(), (details.getSeq() != null && "1".equalsIgnoreCase(details.getSeq())) ? details.getSeq() : "0",details.getAltrMobileNumber(),details.getAltrCountryCode(),details.getEmergencyContactId() });
	
		return result;
	}
	
	@Override
	public EmrContactDetais[] getContactDetails(String emplId) {

		List<EmrContactDetais> list = new ArrayList();
		String sql = "select contact_id,contact_name,contact_number,relation,contact_type,seq,contact_id,country_code,altr_contact_number,altr_country_code from osi_contacts where employee_id="+emplId+" and contact_type='emergency' limit 2";

		try {
			list = jdbcTemplate.query(sql,
					new RowMapper<EmrContactDetais>() {

						@Override
						public EmrContactDetais mapRow(ResultSet rs,
								int arg1) throws SQLException {
							// TODO Auto-generated method stub
							EmrContactDetais details = new EmrContactDetais();

							details.setName((rs
									.getString("contact_name") != null) ? rs
									.getString("contact_name") : "");
							details.setMobileNumber((rs
									.getString("contact_number") != null) ? rs
									.getString("contact_number") : "");
							details.setRelation((rs.getString("relation") != null) ? rs
									.getString("relation") : "");
							details.setSeq((rs.getString("seq") != null) ? rs
									.getString("seq") : "");
							details.setEmergencyContactId((rs.getString("contact_id") != null) ? rs
									.getString("contact_id") : "");
							details.setCountryCode((rs.getString("country_code") != null) ? rs
									.getString("country_code") : "");
							details.setAltrMobileNumber((rs.getString("altr_contact_number") != null) ? rs
									.getString("altr_contact_number") : "");
							details.setAltrCountryCode((rs.getString("altr_country_code") != null) ? rs
									.getString("altr_country_code") : "");
							

							return details;
						}
					});
		} catch (Exception ex) {

		}

		return list.toArray(new EmrContactDetais[list.size()]);

	}

}
