package com.osi.ems.repository.custom.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.ems.repository.custom.OrgHeirarchyRepository;
import com.osi.ems.service.dto.OrgHierarchy;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Component
public class MySQL implements OrgHeirarchyRepository {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
		
	@Autowired
	private AppConfig appConfig;
	
	@Autowired
	private Environment env;
	
	public Connection getConnection() throws DataAccessException {
		Connection con = null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		} catch(ClassNotFoundException e) {
			LOGGER.error("Exception : "+ e.getMessage());
			throw new DataAccessException("ERR_1023","Exception occured while getting the connection");
		} catch(SQLException e) {
			LOGGER.error("Exception : "+ e.getMessage());
			throw new DataAccessException("ERR_1023","Exception occured while getting the connection");
		}
		return con; 
	}
	
	// Methods of OrgHierarchy
	/* START of Org Chart */
	public OrgHierarchy getEmployee(Integer employeeId, String fullEmployeeName) throws DataAccessException {
		OrgHierarchy Employee = new OrgHierarchy();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		LOGGER.info("getEmployee : Begin");
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(" select Employee.*,emp_image.file_type, emp_image.duplicate_file_name from ( ");
			sb.append("	select ");
			sb.append("	a.employee_id, ");
			sb.append("	a.employee_number, ");
			sb.append("	a.gender, ");
			sb.append("	a.full_name,   ");
			sb.append("	a.first_name,  ");
			sb.append("	a.original_date_of_hire,  ");
			sb.append("	a.last_name,   ");
			sb.append("	b.employee_id supervisor,");
			sb.append("	b.full_name supervisor_name,");
			sb.append("	d.job_code_name,    ");
			sb.append("	e.city,   ");
			sb.append("	g.org_name,    ");
			sb.append("	a.photo_id    ");
			//sb.append("	c.department_id department_id  ");
			sb.append(", concat(r.SEGMENT1,' - ',r.SEGMENT2,' - ',r.SEGMENT3,' - ',r.SEGMENT4,' - ',r.SEGMENT5) deptStructure ");
			sb.append("	from  ");
			sb.append("	OSI_EMPLOYEES a,");
			sb.append("	OSI_EMPLOYEES b,");
			sb.append("	OSI_JOB_CODES d,    ");
			sb.append("	OSI_ADDRESSES e, ");
			sb.append("	OSI_LOCATIONS f,    ");
			sb.append("	OSI_ORGANIZATIONS g,  ");
			sb.append("	OSI_ASSIGNMENTS c  ");
			sb.append(" LEFT JOIN OSI_ROLLUPS r ON r.rollup_id = c.dept_id ");
			sb.append("	where 1 = 1   ");
			sb.append("	and	a.employee_id = c.employee_id ");
			sb.append("	and	c.supervisor_id = b.employee_id    ");
			sb.append("	and c.job_id = d.job_code_id ");
			sb.append("	and c.location_id = f.location_id ");
			sb.append("	and f.address_id = e.address_id   ");
			sb.append("	and a.org_id = g.org_id  ");
			sb.append("and c.assignment_id = (select max(assignment_id) from osi_assignments ass where ass.employee_id = a.employee_id and sysdate() between ass.effective_start_date and ass.effective_end_date) ");
			if (null != employeeId) {
				sb.append("	and a.employee_id = " + employeeId + "  and sysdate() between a.effective_start_date and a.effective_end_date) Employee  ");
			} else {
				sb.append("	and a.full_name like '%" + fullEmployeeName + "%' and sysdate() between a.effective_start_date and a.effective_end_date) Employee  ");
			}
			sb.append("	 LEFT OUTER JOIN OSI_ATTACHMENTS emp_image  ");
			sb.append("	 ON Employee.photo_id = emp_image.attachment_id ");
			
			connection = this.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(sb.toString());

			if (rs.next()) {
				Employee.setId(rs.getInt("employee_id"));
				Employee.setEmployeeNumber(rs.getString("employee_number"));
				Employee.setSupervisorId(rs.getInt("supervisor"));
				Employee.setName(rs.getString("full_name"));
				Employee.setPosition(rs.getString("job_code_name"));
				//Employee.setDepartmentId(rs.getString("department_id"));
				Employee.setDepartment(rs.getString("org_name"));
				Employee.setSupervisorName(rs.getString("supervisor_name"));
				Employee.setSex(rs.getString("gender"));
				Employee.setDateHired(rs.getTimestamp("original_date_of_hire"));
				Employee.setDeptStructure(rs.getString("deptStructure"));
				int photoId = rs.getInt("photo_id");
				if(photoId != 0) {
					String duplicateFileName = rs.getString("duplicate_file_name");
					String fileType = rs.getString("file_type");
					String filePath =appConfig.getImagePath()+File.separator+"EMPLOYEE"+File.separator+duplicateFileName;
					//try {
						//Employee.setImageLocation("data:"+fileType+";base64,"+new CommonService().encodeFile(filePath));
						//Employee.setImageLocation("data:"+fileType+";base64,"+this.convertToThumbnailImage(filePath, duplicateFileName, duplicateFileName.split("\\.")[1]));
						Employee.setImageLocation(env.getProperty("image.rendering.path")+"EMPLOYEE/"+duplicateFileName);
					/*} catch (BusinessException e) {
						throw new DataAccessException(e.getErrorCode(), e.getSystemMessage());
					}*/
				} else {
					String fileType = rs.getString("file_type");
					if ((null == fileType) || (fileType != null && fileType.equals("null"))) {
						Employee.setImageLocation(null);
					}
				}
			}

		} catch (SQLException e) {
			LOGGER.error("Exception : "+ e.getMessage());
			throw new DataAccessException("ERR_1002", "No record found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			try {
				if(rs != null) rs.close();
				if(statement != null) statement.close();
				if(connection != null) 	connection.close();
			} catch (SQLException e) {
				LOGGER.error("Exception : "+ e.getMessage());
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
			}
			
		}
		LOGGER.info("getEmployee : End");
		return Employee;
	}
	public OrgHierarchy getEmployeeChildrenBySupervisorId(OrgHierarchy parentEmployee, Integer level) throws DataAccessException {
		List<OrgHierarchy> children = new ArrayList<OrgHierarchy>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		LOGGER.info("getEmployeeChildrenBySupervisorId : Begin");
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(" select Employee.*, emp_image.file_type, emp_image.duplicate_file_name from ( ");
			sb.append("	select ");
			sb.append("	a.employee_id, ");
			sb.append("	a.employee_number, ");
			sb.append("	a.gender, ");
			sb.append("	a.full_name,   ");
			sb.append("	a.first_name,  ");
			sb.append("	a.original_date_of_hire,  ");
			sb.append("	a.last_name,   ");
			sb.append("	b.employee_id supervisor,");
			sb.append("	b.full_name supervisor_name,");
			sb.append("	d.job_code_name,    ");
			sb.append("	e.city,   ");
			sb.append("	g.org_name,    ");
			sb.append("	a.photo_id    ");
			//sb.append("	c.department_id department_id  ");
			sb.append(", concat(r.SEGMENT1,' - ',r.SEGMENT2,' - ',r.SEGMENT3,' - ',r.SEGMENT4,' - ',r.SEGMENT5) deptStructure ");
			sb.append("	from  ");
			sb.append("	OSI_EMPLOYEES a,");
			sb.append("	OSI_EMPLOYEES b,");
			sb.append("	OSI_JOB_CODES d,    ");
			sb.append("	OSI_ADDRESSES e, ");
			sb.append("	OSI_LOCATIONS f,    ");
			sb.append("	OSI_ORGANIZATIONS g, ");
			sb.append("	OSI_ASSIGNMENTS c  ");
			sb.append(" LEFT JOIN OSI_ROLLUPS r ON r.rollup_id = c.dept_id ");
			sb.append("	where 1 = 1   ");
			sb.append(" and c.assignment_id  = (select max(assignment_id) from osi_assignments c1 where c1.employee_id = a.employee_id and sysdate() between c1.effective_start_date and c1.effective_end_date) ");
			//sb.append("	and	a.employee_id = c.employee_id ");
			sb.append("	and	c.supervisor_id = b.employee_id    ");
			sb.append("	and c.job_id = d.job_code_id ");
			sb.append("	and c.location_id = f.location_id ");
			sb.append("	and f.address_id = e.address_id   ");
			sb.append("	and a.org_id = g.org_id  ");
			sb.append("	and b.employee_id = " + parentEmployee.getId() + " and sysdate() between a.effective_start_date and a.effective_end_date group by a.employee_id) Employee  ");
			sb.append("	 LEFT OUTER JOIN OSI_ATTACHMENTS emp_image  ");
			sb.append("	 ON Employee.photo_id = emp_image.attachment_id ");
			
			connection = this.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(sb.toString());
			while (rs.next()) {
				OrgHierarchy Employee = new OrgHierarchy();
				Employee.setId(rs.getInt("employee_id"));
				Employee.setEmployeeNumber(rs.getString("employee_number"));
				Employee.setSupervisorId(rs.getInt("supervisor"));
				Employee.setName(rs.getString("full_name"));
				Employee.setPosition(rs.getString("job_code_name"));
				//Employee.setDepartmentId(rs.getString("department_id"));
				Employee.setDepartment(rs.getString("org_name"));
				Employee.setSupervisorName(rs.getString("supervisor_name"));
				Employee.setSex(rs.getString("gender"));
				Employee.setDateHired(rs.getTimestamp("original_date_of_hire"));
				Employee.setDeptStructure(rs.getString("deptStructure"));
				Employee.setLevel(level);
				
				int photoId = rs.getInt("photo_id");
				if(photoId != 0) {
					String duplicateFileName = rs.getString("duplicate_file_name");
					String fileType = rs.getString("file_type");
					String filePath =appConfig.getImagePath()+File.separator+"EMPLOYEE"+File.separator+duplicateFileName;
					//try {
						//Employee.setImageLocation("data:"+fileType+";base64,"+new CommonService().encodeFile(filePath));
						//Employee.setImageLocation("data:"+fileType+";base64,"+this.convertToThumbnailImage(filePath, duplicateFileName, duplicateFileName.split("\\.")[1]));
					Employee.setImageLocation(env.getProperty("image.rendering.path")+"EMPLOYEE/"+duplicateFileName);
					/*} catch (BusinessException e) {
						throw new DataAccessException(e.getErrorCode(), e.getSystemMessage());
					}*/
				} else {
					String fileType = rs.getString("file_type");
					if ((null == fileType) || (fileType != null && fileType.equals("null"))) {
						Employee.setImageLocation(null);
					}
				}
				children.add(Employee);
			}

			parentEmployee.setSubordinate(children);

		} catch (SQLException e) {
			LOGGER.error("Exception : "+ e.getMessage());
			throw new DataAccessException("ERR_1002", "No record found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			try {
				if(rs != null) rs.close();
				if(statement != null) statement.close();
				if(connection != null) 	connection.close();
			} catch (SQLException e) {
				LOGGER.error("Exception : "+ e.getMessage());
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
			}
			
		}
		LOGGER.info("getEmployeeChildrenBySupervisorId : End");
		return parentEmployee;
	}

	public List<OrgHierarchy> getAllEmployees() throws DataAccessException {
		List<OrgHierarchy> children = new ArrayList<OrgHierarchy>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		LOGGER.info("getAllEmployees : Begin");
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(" select Employee.*, emp_image.file_type, emp_image.duplicate_file_name from ( ");
			sb.append("	select ");
			sb.append("	a.employee_id, ");
			sb.append("	a.employee_number, ");
			sb.append("	a.gender, ");
			sb.append("	a.full_name,   ");
			sb.append("	a.first_name,  ");
			sb.append("	a.original_date_of_hire,  ");
			sb.append("	a.last_name,   ");
			sb.append("	b.employee_id supervisor,");
			sb.append("	b.full_name supervisor_name,");
			sb.append("	d.job_code_name,    ");
			sb.append("	e.city,   ");
			sb.append("	g.org_name,    ");
			sb.append("	a.photo_id    ");
			sb.append(",concat(r.SEGMENT1,' - ',r.SEGMENT2,' - ',r.SEGMENT3,' - ',r.SEGMENT4,' - ',r.SEGMENT5) as deptStructure ");
			sb.append("	from  ");
			sb.append("	OSI_EMPLOYEES a,");
			sb.append("	OSI_EMPLOYEES b,");
			sb.append("	OSI_JOB_CODES d,    ");
			sb.append("	OSI_ADDRESSES e, ");
			sb.append("	OSI_LOCATIONS f,    ");
			sb.append("	OSI_ORGANIZATIONS g,  ");
			sb.append("	OSI_ASSIGNMENTS c  ");
			sb.append(" LEFT JOIN OSI_ROLLUPS r ON r.rollup_id = c.dept_id ");
			sb.append("	where 1 = 1   ");
			sb.append("	and	a.employee_id = c.employee_id ");
			sb.append("	and	c.supervisor_id = b.employee_id    ");
			sb.append("	and c.job_id = d.job_code_id ");
			sb.append("	and c.location_id = f.location_id ");
			sb.append("	and f.address_id = e.address_id   ");
			sb.append("	and a.org_id = g.org_id and sysdate() between a.effective_start_date and a.effective_end_date) Employee  ");
			sb.append("	 LEFT OUTER JOIN OSI_ATTACHMENTS emp_image  ");
			sb.append("	 ON Employee.photo_id = emp_image.attachment_id ");
			
			connection = this.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(sb.toString());

			if (rs.next()) {
				OrgHierarchy Employee = new OrgHierarchy();
				Employee.setId(rs.getInt("employee_id"));
				Employee.setEmployeeNumber(rs.getString("employee_number"));
				Employee.setSupervisorId(rs.getInt("supervisor"));
				Employee.setName(rs.getString("full_name"));
				Employee.setPosition(rs.getString("job_code_name"));
				Employee.setDepartment(rs.getString("org_name"));
				Employee.setSupervisorName(rs.getString("supervisor_name"));
				Employee.setSex(rs.getString("gender"));
				Employee.setDateHired(rs.getTimestamp("original_date_of_hire"));
				Employee.setDeptStructure(rs.getString("deptStructure"));
				
				int photoId = rs.getInt("photo_id");
				if(photoId != 0) {
					String duplicateFileName = rs.getString("duplicate_file_name");
					String fileType = rs.getString("file_type");
					String filePath =appConfig.getImagePath()+File.separator+"EMPLOYEE"+File.separator+duplicateFileName;
//					try {
						//Employee.setImageLocation("data:"+fileType+";base64,"+new CommonService().encodeFile(filePath));
						//Employee.setImageLocation("data:"+fileType+";base64,"+this.convertToThumbnailImage(filePath, duplicateFileName, duplicateFileName.split("\\.")[1]));
					Employee.setImageLocation(env.getProperty("image.rendering.path")+"EMPLOYEE/"+duplicateFileName);
					/*} catch (BusinessException e) {
						throw new DataAccessException(e.getErrorCode(), e.getSystemMessage());
					}*/
				} else {
					String fileType = rs.getString("file_type");
					if ((null == fileType) || (fileType != null && fileType.equals("null"))) {
						Employee.setImageLocation(null);
					}
				}
				children.add(Employee);
			}

		} catch (SQLException e) {
			LOGGER.error("Exception : "+ e.getMessage());
			throw new DataAccessException("ERR_1002", "No record found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			try {
				if(rs != null) rs.close();
				if(statement != null) statement.close();
				if(connection != null) 	connection.close();
			} catch (SQLException e) {
				LOGGER.error("Exception : "+ e.getMessage());
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
			}			
		}
		LOGGER.info("getAllEmployees : End");
		return children;
	}

	/* END of Org Chart */

	// From here code is ignored please dont mind this to be removed

	public List<OrgHierarchy> getSubordinateEmployee(OrgHierarchy supervisingEmployee, int level) throws DataAccessException {
		
		List<OrgHierarchy> listOfEmployees = new ArrayList<OrgHierarchy>();
		level++;
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		LOGGER.info("getSubordinateEmployee : Begin");
		try {
			connection = this.getConnection();
			statement = connection.createStatement();
			ResultSet count = statement.executeQuery("SELECT count(*) as numberOfSubordinates FROM Employee WHERE supervisorId="
							+ supervisingEmployee.getId());
			count.next();
			int numberOfSubordinates = count.getInt("numberOfSubordinates");
			rs = statement.executeQuery("SELECT * FROM Employee WHERE supervisorId=" + supervisingEmployee.getId());
			// boolean isStillSupervisor = rs.first();
			if (numberOfSubordinates > 0) {

				while (rs.next()) {
					Integer id = rs.getInt("id");
					Integer supervisorId = rs.getInt("supervisorId");
					String name = rs.getString("name");
					Boolean isSupervisor = false;
					String position = rs.getString("position");
					String department = rs.getString("department");
					String dateHired = rs.getString("datehired");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date hiredDate = sdf.parse(dateHired);

					OrgHierarchy Employee = new OrgHierarchy();
					Employee.setId(id);
					Employee.setSupervisorId(supervisorId);
					Employee.setName(name);
					Employee.setPosition(position);
					Employee.setDepartment(department);
					Employee.setSupervisorName(supervisingEmployee.getName());
					Employee.setDateHired(new Timestamp(hiredDate.getTime()));

					Employee.setLevel(level);
					if (rs.getShort("isSupervisor") == 1) {
						isSupervisor = true;
						List<OrgHierarchy> listOfSubordinates = new ArrayList<OrgHierarchy>();
						// TODO: get all children spells here
						listOfSubordinates = getSubordinateEmployee(Employee, level);
						Employee.setSubordinate(listOfSubordinates);
					}

					Employee.setIsSupervisor(isSupervisor);
					listOfEmployees.add(Employee);
				}
			} else {
				demoteEmployee(supervisingEmployee.getId());
			}
		} catch (SQLException e) {
			LOGGER.error("Exception : "+ e.getMessage());
			throw new DataAccessException("ERR_1002", "No record found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			try {
				if(rs != null) rs.close();
				if(statement != null) statement.close();
				if(connection != null) 	connection.close();
			} catch (SQLException e) {
				LOGGER.error("Exception : "+ e.getMessage());
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
			}			
		}
		LOGGER.info("getSubordinateEmployee : End");
		return listOfEmployees;
	}

	public List<OrgHierarchy> getSubordinateEmployeeNoLimit(OrgHierarchy supervisingEmployee) throws DataAccessException {
		List<OrgHierarchy> listOfEmployees = new ArrayList<OrgHierarchy>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		LOGGER.info("getSubordinateEmployeeNoLimit : Begin");
		try {
			connection = this.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Employee WHERE supervisorId=" + supervisingEmployee.getId());
			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer supervisorId = rs.getInt("supervisorId");
				String name = rs.getString("name");
				Boolean isSupervisor = false;
				String position = rs.getString("position");
				String department = rs.getString("department");
				String dateHired = rs.getString("datehired");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date hiredDate = sdf.parse(dateHired);

				OrgHierarchy Employee = new OrgHierarchy();
				Employee.setId(id);
				Employee.setSupervisorId(supervisorId);
				Employee.setName(name);
				Employee.setPosition(position);
				Employee.setDepartment(department);
				Employee.setSupervisorName(supervisingEmployee.getName());
				Employee.setDateHired(new Timestamp(hiredDate.getTime()));

				if (rs.getShort("isSupervisor") == 1) {
					isSupervisor = true;
					List<OrgHierarchy> listOfSubordinates = new ArrayList<OrgHierarchy>();
					// TODO: get all children spells here
					listOfSubordinates = getSubordinateEmployeeNoLimit(Employee);
					Employee.setSubordinate(listOfSubordinates);
				}

				Employee.setIsSupervisor(isSupervisor);
				listOfEmployees.add(Employee);
			}
		} catch (SQLException e) {
			LOGGER.error("Exception : "+ e.getMessage());
			throw new DataAccessException("ERR_1002", "No record found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			try {
				if(rs != null) rs.close();
				if(statement != null) statement.close();
				if(connection != null) 	connection.close();
			} catch (SQLException e) {
				LOGGER.error("Exception : "+ e.getMessage());
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
			}			
		}
		LOGGER.info("getSubordinateEmployeeNoLimit : End");
		return listOfEmployees;
	}

	public List<OrgHierarchy> getAllEmployeesByName(String employeeName) throws DataAccessException {
		List<OrgHierarchy> listOfEmployees = new ArrayList<OrgHierarchy>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		LOGGER.info("getAllEmployeesByName : Begin");
		try {
			connection = this.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Employee WHERE name LIKE '%" + employeeName + "%'");

			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer supervisorId = rs.getInt("supervisorId");
				String name = rs.getString("name");
				Boolean isSupervisor = false;
				String position = rs.getString("position");
				String department = rs.getString("department");
				String dateHired = rs.getString("datehired");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date hiredDate = sdf.parse(dateHired);

				if (rs.getShort("isSupervisor") == 1) {
					isSupervisor = true;
				}

				OrgHierarchy Employee = new OrgHierarchy();
				Employee.setId(id);
				Employee.setSupervisorId(supervisorId);
				Employee.setName(name);
				Employee.setPosition(position);
				Employee.setDepartment(department);
				Employee.setDateHired(new Timestamp(hiredDate.getTime()));

				int level = 1;
				Employee.setLevel(level);

				if (rs.getShort("isSupervisor") == 1) {
					isSupervisor = true;
					List<OrgHierarchy> listOfSubordinates = new ArrayList<OrgHierarchy>();
					// TODO: get all children spells here
					listOfSubordinates = getSubordinateEmployee(Employee, level);
					Employee.setSubordinate(listOfSubordinates);
				}

				Employee.setIsSupervisor(isSupervisor);
				listOfEmployees.add(Employee);
			}

		} catch (SQLException e) {
			LOGGER.error("Exception : "+ e.getMessage());
			throw new DataAccessException("ERR_1002", "No record found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			try {
				if(rs != null) rs.close();
				if(statement != null) statement.close();
				if(connection != null) 	connection.close();
			} catch (SQLException e) {
				LOGGER.error("Exception : "+ e.getMessage());
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
			}			
		}
		LOGGER.info("getAllEmployeesByName : End");
		return listOfEmployees;
	}

	public List<OrgHierarchy> getAllEmployeesById(Integer employeeId) throws DataAccessException {
		List<OrgHierarchy> listOfEmployees = new ArrayList<OrgHierarchy>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		LOGGER.info("getAllEmployeesById : Begin");
		try {
			connection = this.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Employee WHERE id = " + employeeId);

			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer supervisorId = rs.getInt("supervisorId");
				String name = rs.getString("name");
				Boolean isSupervisor = false;
				String position = rs.getString("position");
				String department = rs.getString("department");
				String dateHired = rs.getString("datehired");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date hiredDate = sdf.parse(dateHired);

				if (rs.getShort("isSupervisor") == 1) {
					isSupervisor = true;
				}

				OrgHierarchy Employee = new OrgHierarchy();
				Employee.setId(id);
				Employee.setSupervisorId(supervisorId);
				Employee.setName(name);
				Employee.setPosition(position);
				Employee.setDepartment(department);
				Employee.setDateHired(new Timestamp(hiredDate.getTime()));
				int level = 1;
				Employee.setLevel(level);

				if (rs.getShort("isSupervisor") == 1) {
					isSupervisor = true;
					List<OrgHierarchy> listOfSubordinates = new ArrayList<OrgHierarchy>();
					// TODO: get all children spells here
					listOfSubordinates = getSubordinateEmployee(Employee, level);
					Employee.setSubordinate(listOfSubordinates);
				}

				Employee.setIsSupervisor(isSupervisor);
				listOfEmployees.add(Employee);
			}

		} catch (SQLException e) {
			LOGGER.error("Exception : "+ e.getMessage());
			throw new DataAccessException("ERR_1002", "No record found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			try {
				if(rs != null) rs.close();
				if(statement != null) statement.close();
				if(connection != null) 	connection.close();
			} catch (SQLException e) {
				LOGGER.error("Exception : "+ e.getMessage());
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
			}			
		}
		LOGGER.info("getAllEmployeesById : End");
		return listOfEmployees;
	}

	public OrgHierarchy getRootChildrenBySupervisorId(OrgHierarchy parentEmployee, Integer level) throws DataAccessException {
		List<OrgHierarchy> children = new ArrayList<OrgHierarchy>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		LOGGER.info("getRootChildrenBySupervisorId : Begin");
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("	select distinct ");
			sb.append("	b.supervisor_id supervisor,");
			sb.append("	(select osi_employees.full_name FROM osi_employees WHERE osi_employees.employee_id = "
					+ parentEmployee.getId() + ") as supervisor_name,");
			sb.append("	a.employee_id,");
			sb.append("	a.employee_number,");
			sb.append("	a.full_name,");
			sb.append("	a.gender,");
			sb.append("	d.job_code_name,");
			sb.append("	e.duplicate_file_name, e.file_type, ");
			sb.append("	a.original_date_of_hire,");
			sb.append("	c.org_name,");
			sb.append("	a.original_date_of_hire");
			sb.append(" ,a.photo_id");
			sb.append(" ,concat(r.SEGMENT1,' - ',r.SEGMENT2,' - ',r.SEGMENT3,' - ',r.SEGMENT4,' - ',r.SEGMENT5) deptStructure");
			sb.append("	from  ");
			sb.append("	OSI_EMPLOYEES a");
			sb.append("	INNER JOIN osi_assignments as b");
			sb.append("	ON a.employee_id = b.employee_id");
			sb.append("	INNER JOIN osi_organizations as c");
			sb.append("	ON c.org_id = a.org_id");
			sb.append("	INNER JOIN osi_job_codes as d");
			sb.append("	ON d.job_code_id = b.job_id");
			sb.append("	LEFT OUTER JOIN osi_attachments as e ON a.photo_id = e.attachment_id");
			sb.append(" LEFT JOIN osi_rollups r ON r.ROLLUP_ID = b.dept_id ");
			sb.append("	WHERE b.supervisor_id=" + parentEmployee.getId() + "");
			sb.append(" AND a.employee_id <> " + parentEmployee.getId() + "");
			sb.append(" and sysdate() between a.effective_start_date and a.effective_end_date "); 
			sb.append("	and sysdate() between b.effective_start_date and b.effective_end_date");
			
			connection = this.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(sb.toString());

			while (rs.next()) {
				OrgHierarchy Employee = new OrgHierarchy();
				Employee.setId(rs.getInt("employee_id"));
				Employee.setEmployeeNumber(rs.getString("employee_number"));
				Employee.setSupervisorId(rs.getInt("supervisor"));
				Employee.setName(rs.getString("full_name"));
				Employee.setPosition(rs.getString("job_code_name"));
				Employee.setDepartment(rs.getString("org_name"));
				Employee.setSupervisorName(rs.getString("supervisor_name"));
				Employee.setSex(rs.getString("gender"));
				Employee.setDeptStructure(rs.getString("deptStructure"));
				
				int photoId = rs.getInt("photo_id");
				if(photoId != 0) {
					String duplicateFileName = rs.getString("duplicate_file_name");
					String fileType = rs.getString("file_type");
					String filePath =appConfig.getImagePath()+File.separator+"EMPLOYEE"+File.separator+duplicateFileName;
//					try {
						//Employee.setImageLocation("data:"+fileType+";base64,"+new CommonService().encodeFile(filePath));
						//Employee.setImageLocation("data:"+fileType+";base64,"+this.convertToThumbnailImage(filePath, duplicateFileName, duplicateFileName.split("\\.")[1]));
						Employee.setImageLocation(env.getProperty("image.rendering.path")+"EMPLOYEE/"+duplicateFileName);
					/*} catch (BusinessException e) {
						throw new DataAccessException(e.getErrorCode(), e.getSystemMessage());
					}*/
				} else {
					String fileType = rs.getString("file_type");
					if ((null == fileType) || (fileType != null && fileType.equals("null"))) {
						Employee.setImageLocation(null);
					}
				}
				
				Employee.setDateHired(rs.getTimestamp("original_date_of_hire"));
				Employee.setLevel(level);

				children.add(Employee);
			}

			parentEmployee.setSubordinate(children);
		} catch (SQLException e) {
			LOGGER.error("Exception : "+ e.getMessage());
			throw new DataAccessException("ERR_1002", "No record found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			try {
				if(rs != null) rs.close();
				if(statement != null) statement.close();
				if(connection != null) 	connection.close();
			} catch (SQLException e) {
				LOGGER.error("Exception : "+ e.getMessage());
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
			}			
		}
		LOGGER.info("getRootChildrenBySupervisorId : End");
		return parentEmployee;
	}
	// End of OrgHierarchy
	
	private void demoteEmployee(Integer employeeId) throws DataAccessException {
		
		Connection connection = null;		
		Statement statement = null;	
		LOGGER.info("demoteEmployee : Begin");
		try {
			connection = this.getConnection();
			if (isEmployeeSupervisor(employeeId)) {
					statement = connection.createStatement();
					String updateQuery = "UPDATE Employee " + "SET isSupervisor=0 WHERE id=" + employeeId;
					statement.execute(updateQuery);
			}
		} catch (SQLException e) {
			LOGGER.error("Exception : "+ e.getMessage());
			throw new DataAccessException("ERR_1002", "Unable to demote the employee");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			try {
				if(statement != null) statement.close();
				if(connection != null) 	connection.close();
			} catch (SQLException e) {
				LOGGER.error("Exception : "+ e.getMessage());
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
			}			
		}
		LOGGER.info("demoteEmployee : End");
	}
	
	private Boolean isEmployeeSupervisor(Integer employeeId) throws DataAccessException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		boolean flag = false;
		LOGGER.info("isEmployeeSupervisor : Begin");
		try {
			connection = this.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM Employee WHERE id=" + employeeId);
			if(rs != null)
				flag = rs.first();
		} catch (SQLException e) {
			LOGGER.error("Exception : "+ e.getMessage());
			throw new DataAccessException("ERR_1002", "exception occured while checking isEmployee supervisor");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} finally {
			try {
				if(statement != null) statement.close();
				if(connection != null) 	connection.close();
			} catch (SQLException e) {
				LOGGER.error("Exception : "+ e.getMessage());
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
			}			
		}
		LOGGER.info("isEmployeeSupervisor : End");
		return flag;
	}
	
	
		/*
		 * For Employee List - Grid view
		 */
		@Override
		public String getEmployeeOrgList(int employeeId) throws DataAccessException {
			
			String empOrgList="SELECT parent, level, rn, hi.id employee_id,	parent supervisor_id, hi.employee_number,	hi.full_name,  " + 
					"hi.supervisor_name, hi.job_code_name,	hi.office_email, hi.city, hi.organization, hi.deptStructure " +
					" ,hi.SEGMENT1 ,hi.SEGMENT2 ,hi.SEGMENT3 ,hi.SEGMENT4 ,hi.SEGMENT5,hi.SEGMENT6,hi.SEGMENT7,hi.SEGMENT8,hi.SEGMENT9,hi.SEGMENT10 " +
					"FROM	 " + 
					"(SELECT HIERARCHY_CONNECT_BY_PARENT_EQ_PRIOR_ID(id) AS id,	@level AS level,  " + 
					"(@row_number:=@row_number + 1) AS rn  " + 
					"FROM  " + 
					"(SELECT @start_with:=?, @id:=@start_with, @level:=0) vars,  " + 
					"(SELECT aa.employee_id id, aa.full_name, bb.supervisor_id parent  " + 
					"FROM osi_employees aa  " + 
					"JOIN osi_assignments bb ON aa.employee_id = bb.employee_id  " + 
					"AND SYSDATE() BETWEEN IFNULL(aa.effective_start_date, SYSDATE() - 1)  " + 
					"AND IFNULL(aa.effective_end_date, SYSDATE() + 1)  " + 
					"AND SYSDATE() BETWEEN IFNULL(bb.effective_start_date, SYSDATE() - 1)  " + 
					"AND IFNULL(bb.effective_end_date, SYSDATE() + 1)) t_hierarchy,  " + 
					"(SELECT @row_number:=0) AS t WHERE @id IS NOT NULL) ho  " + 
					"JOIN  " + 
					"(SELECT aa.employee_id id, aa.*, bb.supervisor_id parent, bb.job_id, bb.location_id,   " + 
					"a9.full_name supervisor_name, a1.job_code_name, a4.org_name organization, a3.city  "
					+ ",concat(r.SEGMENT1,' | ',r.SEGMENT2,' | ',r.SEGMENT3,' | ',r.SEGMENT4,' | ',r.SEGMENT5) deptStructure " 
					+ " ,r.SEGMENT1, r.SEGMENT2, r.SEGMENT3, r.SEGMENT4, r.SEGMENT5, r.SEGMENT6, r.SEGMENT7, r.SEGMENT8, r.SEGMENT9, r.SEGMENT10 " +
					"FROM osi_employees aa  " + 
					"JOIN osi_assignments bb ON aa.employee_id = bb.employee_id  " + 
					"AND SYSDATE() BETWEEN IFNULL(aa.effective_start_date, SYSDATE() - 1)  " + 
					"AND IFNULL(aa.effective_end_date, SYSDATE() + 1)  " + 
					"AND SYSDATE() BETWEEN IFNULL(bb.effective_start_date, SYSDATE() - 1)  " + 
					"AND IFNULL(bb.effective_end_date, SYSDATE() + 1)  " + 
					"LEFT JOIN OSI_JOB_CODES a1 ON bb.job_id = a1.job_code_id	 " + 
					"LEFT JOIN OSI_ORGANIZATIONS a4 ON aa.org_id = a4.org_id  " + 
					"LEFT JOIN OSI_LOCATIONS a2 ON bb.location_id = a2.location_id	 " + 
					"LEFT JOIN OSI_ADDRESSES a3 ON a2.address_id = a3.address_id 	     " + 
					"LEFT JOIN OSI_EMPLOYEES a9 ON bb.supervisor_id = a9.employee_id "
					+ "LEFT JOIN OSI_ROLLUPS r ON r.ROLLUP_ID = bb.dept_id) hi ON hi.id = ho.id " + 
					"union	 " + 
					"SELECT a2.supervisor_id parent, 0 level, 0 rn, a1.employee_id,	a2.supervisor_id, a1.employee_number,  " + 
					"a1.full_name, a3.full_name supervisor_name, a4.job_code_name, a1.office_email, a11.city, a6.org_name organization "
					+ ",concat(r1.SEGMENT1,' | ',r1.SEGMENT2,' | ',r1.SEGMENT3,' | ',r1.SEGMENT4,' | ',r1.SEGMENT5) deptStructure " 
					+ " ,r1.SEGMENT1, r1.SEGMENT2, r1.SEGMENT3, r1.SEGMENT4, r1.SEGMENT5, r1.SEGMENT6, r1.SEGMENT7, r1.SEGMENT8, r1.SEGMENT9, r1.SEGMENT10 " +
					"FROM osi_employees a1, osi_employees a3, osi_assignments a2	                 " + 
					"LEFT JOIN OSI_JOB_CODES a4 ON a2.job_id = a4.job_code_id  " + 
					"LEFT JOIN OSI_LOCATIONS a5 ON a2.location_id = a5.location_id  " + 
					"LEFT JOIN OSI_ORGANIZATIONS a6 ON a6.org_id = a4.org_id  " + 
					"LEFT JOIN OSI_ADDRESSES a11 ON a11.address_id = a5.address_id  "
					+ "LEFT JOIN OSI_ROLLUPS r1 ON r1.ROLLUP_ID = a2.dept_id " + 
					"where   a1.employee_id = ?	and                   " + 
					"SYSDATE() BETWEEN IFNULL(a1.effective_start_date, SYSDATE() - 1)  " + 
					"AND IFNULL(a1.effective_end_date, SYSDATE() + 1)	 " + 
					"and a1.employee_id = a2.employee_id	 " + 
					"and SYSDATE() BETWEEN IFNULL(a2.effective_start_date, SYSDATE() - 1)  " + 
					"AND IFNULL(a2.effective_end_date, SYSDATE() + 1)	 " + 
					"and  a2.supervisor_id = a3.employee_id	 " + 
					"and SYSDATE() BETWEEN IFNULL(a3.effective_start_date, SYSDATE() - 1)  " + 
					"AND IFNULL(a3.effective_end_date, SYSDATE() + 1) " + 
					"ORDER BY rn";
	      
			Connection connection = null;
			PreparedStatement statement = null;
			String result = null;
			ResultSet rs = null;
			
			LOGGER.info("getEmployeeOrgList : Begin");
			try{
				
				connection = this.getConnection();
				statement = connection.prepareStatement(empOrgList);
				statement.setInt(1, employeeId);
				statement.setInt(2, employeeId);
				rs = statement.executeQuery();
				JSONArray rmArray = this.convertResultSetIntoJSON(rs);
				result = rmArray.toString();
				
			} catch (SQLException e) {
				LOGGER.error("Exception : "+ e.getMessage());
				throw new DataAccessException("ERR_1002", "Unable to get the employee list");
			} catch (Exception e) {
				LOGGER.error("Exception : " + e.getMessage());			
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
			} finally {
				try {
					if(rs != null) rs.close();
					if(statement != null) statement.close();
					if(connection != null) 	connection.close();
				} catch (SQLException e) {
					LOGGER.error("Exception : "+ e.getMessage());
					throw new DataAccessException("ERR_1000", "Exception occured while executing");
				}			
			}
			LOGGER.info("getEmployeeOrgList : End");
			return result;
		}

		@Override
		public String getLocationList() throws DataAccessException {
			String empOrgList = "select distinct city from osi_addresses";
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			String result = null;
			LOGGER.info("getLocationList : Begin");
			try{
				connection = this.getConnection();
				statement = connection.prepareStatement(empOrgList);
				
				//ps.setInt(2, employeeId);
				rs = statement.executeQuery();
				JSONArray rmArray = this.convertResultSetIntoJSON(rs);
				result = rmArray.toString();
			} catch (SQLException e) {
				LOGGER.error("Exception : "+ e.getMessage());
				throw new DataAccessException("ERR_1002", "No locations found");
			} catch (Exception e) {
				LOGGER.error("Exception : " + e.getMessage());			
				throw new DataAccessException("ERR_1000", "Exception occured while executing");
			} finally {
				try {
					if(rs != null) rs.close();
					if(statement != null) statement.close();
					if(connection != null) 	connection.close();
				} catch (SQLException e) {
					LOGGER.error("Exception : "+ e.getMessage());
					throw new DataAccessException("ERR_1000", "Exception occured while executing");
				}			
			}
			LOGGER.info("getLocationList : End");
			return result;
	}
		
		
		
		private JSONArray convertResultSetIntoJSON(ResultSet resultSet) throws Exception {
	        JSONArray jsonArray = new JSONArray();
	        while (resultSet.next()) {
	            int total_rows = resultSet.getMetaData().getColumnCount();
	            JSONObject obj = new JSONObject();
	            for (int i = 0; i < total_rows; i++) {
	                String columnName = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
	                Object columnValue = resultSet.getObject(i + 1);
	                // if value in DB is null, then we set it to default value
	                if (columnValue == null){
	                    columnValue = "";
	                }
	                /*
	                Next if block is a hack. In case when in db we have values like price and price1 there's a bug in jdbc - 
	                both this names are getting stored as price in ResulSet. Therefore when we store second column value,
	                we overwrite original value of price. To avoid that, i simply add 1 to be consistent with DB.
	                 */
	                if (obj.has(columnName)){
	                    columnName += "1";
	                }
	                obj.put(columnName, columnValue);
	            }
	            jsonArray.put(obj);
	        }
	        return jsonArray;
	    }
		
	public String convertToThumbnailImage(String filePath, String fileName, String fileType) throws BusinessException {
		String encodedString = "";
		try {
			// create a buffered image
			BufferedImage image = null;
			byte[] imageByte;
			String base64String = new CommonService().encodeFile(filePath);
			imageByte = Base64.decodeBase64(base64String);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();
	
			// write the image to a file
			File outputfile = new File(fileName);
			ImageIO.write(image, fileType, outputfile);
			
			BufferedImage thumbImg = Scalr.resize(image, Method.QUALITY,Mode.AUTOMATIC, 50, 50, Scalr.OP_ANTIALIAS);
		   //convert bufferedImage to outpurstream 
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(thumbImg,fileType,os);
			
			byte[] encoded = Base64.encodeBase64(os.toByteArray());
			encodedString = new String(encoded, "UTF-8");
			os.close();
			return encodedString;
		} catch(IOException io) {
			throw new BusinessException("ERR_1022", "Unable to convert to Thumbnail Image");
		}
	}		
}