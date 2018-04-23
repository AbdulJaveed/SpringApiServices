package com.osi.urm.repository.custom.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiSkillGroups;
import com.osi.ems.domain.OsiSkillTags;
import com.osi.ems.domain.OsiWfsActivities;
import com.osi.ems.repository.OsiWfsActivitiesRepository;
import com.osi.ems.repository.custom.OsiWorkflowsRepositoryCustom;
import com.osi.urm.domain.SkillDetails;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiSkillRepositryCustom;

public class OsiSkillRepositryCustomImpl implements OsiSkillRepositryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${WORKFLOW.EMPLOYEE_SKILL_UPDATION}")
	private String employeeSkillUpdation;

	@Autowired
	OsiWfsActivitiesRepository osiWfsActivitiesRepository;

	@Autowired
	OsiWorkflowsRepositoryCustom osiWorkflowsRepository;

	@Autowired
	private CommonService commonService;

	@Override
	public List<SkillDetails> getSkillDetails() {
		List<SkillDetails> list = null;
		String sql = "select s.skill_id, s.skill_name, s.skill_display_name, s.active, s.created_by, s.created_date, "
				+ "s.updated_by, s.last_update_date, s.skill_version, s.osi_skill_group_id , "
				+ "sk.group_name, sk.group_desc,st.tag_name,st.description,st.tag_id "
				+ "from  osi_skils s left join osi_skill_groups sk on s.osi_skill_group_id = sk.group_id "
				+ "left join osi_skill_tag_mapping tm on s.skill_id = tm.skill_id "
				+ "left join osi_skill_tags st on tm.tag_id = st.tag_id " + " where active = 1 ";

		try {

			list = jdbcTemplate.query(sql, new RowMapper<SkillDetails>() {

				@Override
				public SkillDetails mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					SkillDetails details = new SkillDetails();

					details.setActive((rs.getInt("active") == 1) ? true : false);
					details.setSkillId((rs.getString("skill_id") != null) ? rs.getString("skill_id") : "");
					details.setSkillName((rs.getString("skill_name") != null) ? rs.getString("skill_name") : "");
					details.setSkillDisplayName(
							(rs.getString("skill_display_name") != null) ? rs.getString("skill_display_name") : "");
					if (((rs.getBigDecimal("skill_version") != null))) {
						details.setSkillVersion(" - " + ((BigDecimal) rs.getBigDecimal("skill_version")).toString());
					} else {
						details.setSkillVersion("");
					}

					if (rs.getInt("osi_skill_group_id") != 0) {
						details.setGroupId(rs.getInt("osi_skill_group_id"));
						OsiSkillGroups skillGroupDto = new OsiSkillGroups();
						skillGroupDto.setGroupId(rs.getInt("osi_skill_group_id"));
						skillGroupDto.setGroupName(rs.getString("group_name"));
						skillGroupDto.setGroupDescription(rs.getString("group_desc"));
						details.setOsiSkillGroups(skillGroupDto);
					}
					if (rs.getInt("tag_id") != 0) {
						OsiSkillTags skillTagsDto = new OsiSkillTags();
						skillTagsDto.setTagId(rs.getInt("tag_id"));
						skillTagsDto.setTagName(rs.getString("tag_name"));
						skillTagsDto.setDescription(rs.getString("description"));
						Set<OsiSkillTags> tagsList = new TreeSet<>();
						tagsList.add(skillTagsDto);
						details.setOsiSkillTagsList(tagsList);
					}

					return details;
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;

	}

	@Override
	public List<SkillDetails> searchSkillDetails(SkillDetails skillDetails) {

		List<SkillDetails> list = null;
		StringBuffer sb = new StringBuffer();

		sb.append(
				"select  distinct (oskill.skill_id) as skill_id,oskill.skill_version,oskill.skill_name,oskill.skill_display_name,DATE_FORMAT(emps.last_used_date, '%Y-%m-%d') as last_used_date,emps.proficiency,is_verified, "
						+ "emps.emp_skill_id as  emp_skill_id,emps.years_of_exp "
						+ " ,months_of_exp, oskill.skill_description from  osi_emp_skills emps inner join osi_skils  oskill  on  emps.skill_id = oskill.skill_id  where emps.employee_id = ?");

		if (skillDetails.getSkillId() != null && !"".equals(skillDetails.getSkillId())) {
			sb.append(" and oskill.skill_Id='" + skillDetails.getSkillId() + "' ");
		}

		if (skillDetails.getLastUpdatedDate() != null && !"".equals(skillDetails.getLastUpdatedDate())) {

			sb.append(
					" and emps.last_used_date = DATE_FORMAT('" + skillDetails.getLastUpdatedDate() + "', '%Y-%m-%d') ");

		}

		if (skillDetails.getYearOfExperience() > 0) {
			sb.append(" and  emps.years_of_exp = " + skillDetails.getYearOfExperience() + " ");
		}
		if (skillDetails.getMonthsOfExp() > 0) {
			sb.append(" and  emps.months_of_exp =  " + skillDetails.getMonthsOfExp() + "  ");
		}

		if (skillDetails.getProficiency() != null && !"".equals(skillDetails.getProficiency())) {
			sb.append(" and  emps.proficiency =  '" + skillDetails.getProficiency() + "'  ");
		}

		System.out.println("Final Search query ::::::::::::::::::::::: " + sb.toString());

		try {

			list = jdbcTemplate.query(sb.toString(), new Object[] { skillDetails.getEmployeeId() },
					new RowMapper<SkillDetails>() {

						@Override
						public SkillDetails mapRow(ResultSet rs, int arg1) throws SQLException {
							// TODO Auto-generated method stub
							SkillDetails details = new SkillDetails();
							details.setSkillId((rs.getString("skill_id") != null) ? rs.getString("skill_id") : "");
							details.setSkillName(
									(rs.getString("skill_name") != null) ? rs.getString("skill_name") : "");
							details.setSkillDisplayName(
									(rs.getString("skill_display_name") != null) ? rs.getString("skill_display_name")
											: "");
							details.setYearOfExperience(rs.getInt("years_of_exp"));
							details.setMonthsOfExp(rs.getInt("months_of_exp"));
							details.setLastUpdatedDate(
									(rs.getString("last_used_date") != null) ? rs.getString("last_used_date") : "");
							details.setProficiency(
									(rs.getString("proficiency") != null) ? rs.getString("proficiency") : "");
							details.setEmpSkillId(
									(rs.getString("emp_skill_id") != null) ? rs.getString("emp_skill_id") : "");
							details.setVerified((rs.getString("is_verified") != null)
									? (rs.getString("is_verified").equalsIgnoreCase("1")) ? "true" : "false"
									: "");
							if (((rs.getBigDecimal("skill_version") != null))) {
								details.setSkillVersion(
										" - " + ((BigDecimal) rs.getBigDecimal("skill_version")).toString());
							} else {
								details.setSkillVersion("");
							}
							details.setSkillDescription(
									(rs.getString("skill_description") != null) ? rs.getString("skill_description")
											: "");

							return details;
						}
					});

		} catch (Exception ex) {

		}

		return list;
	}

	@Override
	public int saveSkills(SkillDetails skillDetails, Integer orgId) throws DataAccessException {
		int result = 0;
		boolean initiateWorkflow = false;
		try {
			if (skillDetails.getEmpSkillId() != null && !"".equalsIgnoreCase(skillDetails.getEmpSkillId())) {
				SkillDetails skillDetails1 = getEmployeeSkillsById(skillDetails.getEmpSkillId());
				if (!skillDetails1.getLastUpdatedDate().equalsIgnoreCase(skillDetails.getLastUpdatedDate())
						|| !skillDetails1.getProficiency().equalsIgnoreCase(skillDetails.getProficiency())
						|| skillDetails1.getMonthsOfExp() != skillDetails.getMonthsOfExp()
						|| skillDetails1.getYearOfExperience() != skillDetails.getYearOfExperience()
						|| !skillDetails1.getSkillId().equalsIgnoreCase(skillDetails.getSkillId())) {
					skillDetails.setVerified("false");
					String sql = "update  osi_emp_skills set skill_id=?, proficiency= ?,last_used_date =? ,years_of_exp = ?, months_of_exp = ? ,is_verified = ? ,updated_by = ? ,last_update_date = ? where emp_skill_id = ?";
					result = jdbcTemplate.update(sql, new Object[] { skillDetails.getSkillId(),
							skillDetails.getProficiency(), skillDetails.getLastUpdatedDate(),
							skillDetails.getYearOfExperience(), skillDetails.getMonthsOfExp(),
							(skillDetails.getVerified().equalsIgnoreCase("true")) ? 1 : 0, skillDetails.getUpdatedBy(),
							skillDetails.getLastUpdateDate(), skillDetails.getEmpSkillId() });
					initiateWorkflow = true;
				}

			} else {
				String sql = "insert into osi_emp_skills(skill_id,proficiency,employee_id,last_used_date,years_of_exp,months_of_exp,is_verified,verified_by,created_by,created_date,updated_by,last_update_date) "
						+ " values (?,?,?,?,?,?,?,?,?,?,?,?)";
				result = jdbcTemplate.update(sql, new Object[] { skillDetails.getSkillId(),
						skillDetails.getProficiency(), skillDetails.getEmployeeId(), skillDetails.getLastUpdatedDate(),
						skillDetails.getYearOfExperience(), skillDetails.getMonthsOfExp(),
						(skillDetails.getVerified().equalsIgnoreCase("true")) ? 1 : 0, skillDetails.getVerifiedBy(),
						skillDetails.getCreatedBy(), skillDetails.getLastUpdateDate(), skillDetails.getUpdatedBy(),
						skillDetails.getLastUpdateDate() });
				initiateWorkflow = true;
			}
			if (initiateWorkflow) {
				OsiWfsActivities wfsActivities = new OsiWfsActivities();
				Integer employeeId = Integer.parseInt(skillDetails.getEmployeeId());
				wfsActivities.setObjectId(employeeId);
				wfsActivities.setObjectName("OSI_EMP_SKILLS");
				wfsActivities.setProcessFlag("N");
				wfsActivities.setWfsId(osiWorkflowsRepository.getWorkFlow(employeeSkillUpdation, orgId));
				wfsActivities.setOrgId(orgId);
				wfsActivities.setStartDate(commonService.getCurrentDateStringinUTC());
				if (wfsActivities != null && osiWorkflowsRepository.verifyExistingWorkflow(wfsActivities.getWfsId(),
						employeeId, orgId) == 0)
					osiWfsActivitiesRepository.save(wfsActivities);
			}
		} catch (DataAccessException e) {
			throw new DataAccessException("ERR_1000", "Unable to save/update requested skill");
		} catch (DuplicateKeyException e) {
			throw new DataAccessException("ERR_1000", "Entered skill is alreay exist");
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", "Unable to save/update requested skill");
		}
		return result;
	}

	public SkillDetails getEmployeeSkillsById(String employeeSkillId) throws DataAccessException {
		SkillDetails skillDetails = null;
		try {
			Query query = entityManager.createNativeQuery(
					"select skill_id,proficiency,last_used_date,years_of_exp,months_of_exp from osi_emp_skills where emp_skill_id = ?")
					.setParameter(1, employeeSkillId);

			Object[] object = (Object[]) query.getSingleResult();
			skillDetails = new SkillDetails();
			skillDetails.setSkillId(object[0].toString());
			skillDetails.setProficiency(object[1].toString());
			skillDetails.setLastUpdatedDate(object[2].toString());
			skillDetails.setYearOfExperience(Integer.parseInt(object[3].toString()));
			skillDetails.setMonthsOfExp(Integer.parseInt(object[4].toString()));
		} catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", " No Records Found");
		} catch (Exception e) {
			throw new DataAccessException("ERR_1000", "Exception occured while updating");
		}
		return skillDetails;
	}

	@Override
	public List<SkillDetails> getSkillDetailsById(int skillId) {
		List<SkillDetails> list = null;
		String sql = "select skill_id,skill_name,skill_display_name,active,created_by,created_date,updated_by,last_update_date from  osi_skils where skill_id = ?";

		try {

			list = jdbcTemplate.query(sql, new Object[] { skillId }, new RowMapper<SkillDetails>() {

				@Override
				public SkillDetails mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					SkillDetails details = new SkillDetails();

					details.setActive((rs.getInt("active") == 1) ? true : false);
					details.setSkillId((rs.getString("skill_id") != null) ? rs.getString("skill_id") : "");
					details.setSkillName((rs.getString("skill_name") != null) ? rs.getString("skill_name") : "");
					details.setSkillDisplayName(
							(rs.getString("skill_display_name") != null) ? rs.getString("skill_display_name") : "");
					return details;
				}
			});

		} catch (Exception ex) {

		}

		return list;
	}

	@Override
	public boolean findBySkillIdAndEmployeeId(String skillId, String employeeId) throws BusinessException {

		boolean result = false;
		String sql = "select count(*) from osi_emp_skills e where e.skill_id = ? and e.employee_id = ?";

		try {

			BigInteger resultValue = (BigInteger) entityManager.createNativeQuery(sql).setParameter(1, skillId)
					.setParameter(2, employeeId).getSingleResult();
			Integer resultInt = ((BigInteger) resultValue).intValue();
			if (resultValue != null && resultInt == 1)
				result = true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	@Override
	public int updateVerifiedSkills(SkillDetails skillDetails) throws DataAccessException {
		int result = 0;
		try {
			if (skillDetails.getEmpSkillId() != null && !"".equalsIgnoreCase(skillDetails.getEmpSkillId())) {
				String sql = "update  osi_emp_skills set is_verified = ?, updated_by = ? ,last_update_date = ? where emp_skill_id = ?";
				result = jdbcTemplate.update(sql,
						new Object[] { (skillDetails.getVerified().equalsIgnoreCase("true")) ? 1 : 0,
								skillDetails.getUpdatedBy(), skillDetails.getLastUpdateDate(),
								skillDetails.getEmpSkillId() });
			}
		} catch (Exception ex) {
			throw new DataAccessException("ERR_1000", "Exception while updating the Skills Verification");
		}
		return result;
	}

}
