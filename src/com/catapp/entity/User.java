package com.catapp.entity;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.catapp.Interfaces.IUser;
import com.catapp.connection.BaseEntity;

public class User extends BaseEntity{
	private static final Logger LOGGER = Logger.getLogger(User.class);
	public static final String TABLE_NAME = "users";
	public static final String f_username = "user_name";
	public static final String f_password = "password";
	public static final String f_firstname = "first_name";
	public static final String f_last_name = "last_name";
	public static final String f_institution = "institution";
	public static final String f_phone = "phone_number";
	public static final String f_email = "email";
	public static final String f_supervisor_name = "supervisor_name";
	public static final String f_supervisor_phone = "supervisor_phone";
	public static final String f_supervisor_email = "supervisor_email";
	public static final String f_approved = "approved";
	public static final String f_admin = "is_admin";
	public static final String f_last_login_time = "last_login_time";
	public static final String f_Active = "is_active";
	

	private String password;
	private String first_name;
	private String last_name;
	private String institution;
	private String phone_number;
	private String email;
	private String supervisorname;
	private String supervisorphone;
	private String supervisoremail;
	private String approved;
	private String is_admin;
	private Timestamp last_login_time;
	private String is_active;
	private String user_name;
	
	public String getIs_active() {
		return is_active;
	}
	
	public String getSupervisorname() {
		return supervisorname;
	}
	public void setSupervisorname(String supervisorname) {
		this.supervisorname = supervisorname;
	}
	public String getSupervisorphone() {
		return supervisorphone;
	}
	
	public void setSupervisorphone(String supervisorphone) {
		this.supervisorphone = supervisorphone;
	}
	public String getSupervisoremail() {
		return supervisoremail;
	}
	public void setSupervisoremail(String supervisoremail) {
		this.supervisoremail = supervisoremail;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}
	public Timestamp getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Timestamp last_login_time) {
		this.last_login_time = last_login_time;
	}
	
	public String getInsertColumns()
	{
		return "(" /*+ f_entityId + ","*/  + f_username + "," + f_password + "," + f_firstname + "," + f_last_name
			+ "," + f_institution + "," + f_phone + "," + f_email + "," + f_supervisor_name + "," +f_supervisor_email + "," +f_supervisor_phone + "," +f_approved + ","  + f_admin + "," + f_last_login_time + ","+ f_Active + ","+ f_loggedDate + ","
			 + f_lastUpdatedDate + "," + f_loggedBy + "," + f_lastUpdatedBy + "," + f_rowstate
			+ ") VALUES(?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,?,?,?,?) ";
	}
	
	public void setInsertValues(PreparedStatement pPreparedStatement)
	{
		try
		{
			/*if (getEntityId() == null)
			{
				pPreparedStatement.setNull(1, java.sql.Types.BIGINT);
			}
			else
			{
				pPreparedStatement.setLong(1, getEntityId());
			}*/
			pPreparedStatement.setString(1, getUser_name());
			pPreparedStatement.setString(2, getPassword());
			pPreparedStatement.setString(3, getFirst_name());
			pPreparedStatement.setString(4, getLast_name());
			pPreparedStatement.setString(5, getInstitution());
			pPreparedStatement.setString(6, getPhone_number());
			pPreparedStatement.setString(7, getEmail());
			pPreparedStatement.setString(8, getSupervisorname());
			pPreparedStatement.setString(9, getSupervisoremail());
			pPreparedStatement.setString(10, getSupervisorphone());
			pPreparedStatement.setString(11, getApproved());
			pPreparedStatement.setString(12, 	getIs_admin());
			pPreparedStatement.setTimestamp(13,null);
			pPreparedStatement.setString(14, getIs_active());
			pPreparedStatement.setTimestamp(15, getLoggedDate());
			if (getLastUpdatedDate() == null)
			{
				pPreparedStatement.setNull(16, java.sql.Types.TIMESTAMP);
			}
			else
			{
				pPreparedStatement.setTimestamp(16, getLastUpdatedDate());
			}
			pPreparedStatement.setLong(17, getLoggedBy());
			if (getLastUpdatedBy() == null)
			{
				pPreparedStatement.setNull(18, java.sql.Types.BIGINT);
			}
			else
			{
				pPreparedStatement.setLong(18, getLastUpdatedBy());
			}
			pPreparedStatement.setLong(19, getRowstate());
		}
		catch (SQLException pException1)
		{
			LOGGER.error("Error in Method --> getTableName", pException1);
			
		}
		catch (Exception pException2)
		{
			LOGGER.error("Error in Method --> getTableName", pException2);
		}
	}

	public String getUpdateColumns()
	{
		return  f_username + " = ?" + "," + f_password + " = ?" + "," + f_firstname + " = ?"
			+ "," + f_last_name + " = ?" + "," + f_institution + " = ?" + "," + f_phone + " = ?" + "," + f_email
			+ " = ?" + "," + f_admin + " = ?" + "," + f_last_login_time + " = ?" + "," + f_Active + " = ?" + "," + f_loggedDate
			+ " = ?" + "," + f_approved + " = ?" + "," + f_supervisor_email + " = ?" + "," + f_supervisor_name + " = ?" + "," + f_supervisor_phone
			+ " = ?" + ","+ f_lastUpdatedDate + " = ?" + "," + f_loggedBy + " = ?" + "," + f_lastUpdatedBy + " = ?" + "," + f_rowstate + " = ?" + " WHERE " + f_entityId
			+ " = ?";
	}

	public void setUpdateValues(PreparedStatement pPreparedStatement)
	{
		try
		{
			pPreparedStatement.setLong(20, getEntityId());
		}
		catch (SQLException pException1)
		{
			LOGGER.error("Error in Method --> getTableName", pException1);
		}
		catch (Exception pException2)
		{
			LOGGER.error("Error in Method --> getTableName", pException2);
		}
	}
	public String getTableName()
	{
		return TABLE_NAME;
	}
	
	public void fill(ResultSet pResultSet, String pTableAlias)
		{
			try
			{
				setEntityId(pResultSet.getLong(f_entityId));
				if (pResultSet.wasNull())
				{
					setEntityId(null);
				}
			}
			catch (Exception pException1)
			{
				//LOGGER.error("Error in Method --> getTableName", pException1);
			}
			try
			{
				setParentEntityId(pResultSet.getLong(f_parentEntityId));
				if (pResultSet.wasNull())
				{
					setParentEntityId(null);
				}
			}
			catch (Exception pException2)
			{
				//LOGGER.error("Error in Method --> getTableName", pException2);
			}
			try
			{
				setUser_name(pResultSet.getString(f_username));
				if (pResultSet.wasNull())
				{
					setUser_name(null);
				}
			}
			catch (Exception pException2)
			{
				//LOGGER.error("Error in Method --> getTableName", pException2);
			}
			try
			{
				setPassword(pResultSet.getString(f_password));
				if (pResultSet.wasNull())
				{
					setPassword(null);
				}
			}
			catch (Exception pException2)
			{
				//LOGGER.error("Error in Method --> getTableName", pException2);
			}
			try
			{
				setFirst_name(pResultSet.getString(f_firstname));
				if (pResultSet.wasNull())
				{
					setFirst_name(null);
				}
			}
			catch (Exception pException2)
			{
				//LOGGER.error("Error in Method --> getTableName", pException2);
			}
			try
			{
				setPhone_number(pResultSet.getString(f_phone));
				if (pResultSet.wasNull())
				{
					setPhone_number(null);
				}
			}
			catch (Exception pException2)
			{
				//LOGGER.error("Error in Method --> getTableName", pException2);
			}
			
			try
			{
				setLast_name(pResultSet.getString(f_last_name));
				if (pResultSet.wasNull())
				{
					setLast_name(null);
				}
			}
			catch (Exception pException2)
			{
				//LOGGER.error("Error in Method --> getTableName", pException2);
			}
			try
			{
				setEmail(pResultSet.getString(f_email));
				if (pResultSet.wasNull())
				{
					setEmail(null);
				}
			}
			catch (Exception pException2)
			{
				//LOGGER.error("Error in Method --> getTableName", pException2);
			}
			try
			{
				setSupervisorname(pResultSet.getString(f_supervisor_name));
				if (pResultSet.wasNull())
				{
					setSupervisorname(null);
				}
			}
			catch (Exception pException2)
			{
				//LOGGER.error("Error in Method --> getTableName", pException2);
			}
			try
			{
				setSupervisoremail(pResultSet.getString(f_supervisor_email));
				if (pResultSet.wasNull())
				{
					setSupervisoremail(null);
				}
			}
			catch (Exception pException2)
			{
				//LOGGER.error("Error in Method --> getTableName", pException2);
			}
			try
			{
				setApproved(pResultSet.getString(f_approved));
				if (pResultSet.wasNull())
				{
					setApproved(null);
				}
			}
			catch (Exception pException2)
			{
				//LOGGER.error("Error in Method --> getTableName", pException2);
			}
			try
			{
				setIs_admin(pResultSet.getString(f_admin));
				if (pResultSet.wasNull())
				{
					setIs_admin(null);
				}
			}
			catch (Exception pException2)
			{
				//LOGGER.error("Error in Method --> getTableName", pException2);
			}
			try
			{
				setSupervisorphone(pResultSet.getString(f_supervisor_phone));
				if (pResultSet.wasNull())
				{
					setSupervisorphone(null);
				}
			}
			catch (Exception pException2)
			{
				//LOGGER.error("Error in Method --> getTableName", pException2);
			}
			try
			{
				setOrganizationId(pResultSet.getLong(f_organizationId));
				if (pResultSet.wasNull())
				{
					setOrganizationId(null);
				}
			}
			catch (Exception pException3)
			{
				//LOGGER.error("Error in Method --> getTableName", pException3);
			}
			try
			{
				setDataCenterId(pResultSet.getLong(f_dataCenterId));
				if (pResultSet.wasNull())
				{
					setDataCenterId(null);
				}
			}
			catch (Exception pException4)
			{
				//LOGGER.error("Error in Method --> getTableName", pException4);
			}
			
			
			try
			{
				setLoggedBy(pResultSet.getLong(f_loggedBy));
				if (pResultSet.wasNull())
				{
					setLoggedBy(null);
				}
			}
			catch (Exception pException12)
			{
				//LOGGER.error("Error in Method --> getTableName", pException12);
			}
			try
			{
				setLoggedDate(pResultSet.getTimestamp(f_loggedDate));
			}
			catch (Exception pException13)
			{
				//LOGGER.error("Error in Method --> getTableName", pException13);
			}
			try
			{
				setLastUpdatedBy(pResultSet.getLong(f_lastUpdatedBy));
				if (pResultSet.wasNull())
				{
					setLastUpdatedBy(null);
				}
			}
			catch (Exception pException14)
			{
				//LOGGER.error("Error in Method --> getTableName", pException14);
			}
			try
			{
				setLastUpdatedDate(pResultSet.getTimestamp(f_lastUpdatedDate));
			}
			catch (Exception pException15)
			{
				//LOGGER.error("Error in Method --> getTableName", pException15);
			}
			try
			{
				setRowstate(pResultSet.getLong(f_rowstate));
				if (pResultSet.wasNull())
				{
					setRowstate(null);
				}
			}
			catch (Exception pException16)
			{
				//LOGGER.error("Error in Method --> getTableName", pException16);
			}
		}
		
	
	
	public void fill(HttpServletRequest pRequest, String pRecordCount, String pPrefix)
	{}
	@Override
	public void save(Connection pConnection, PreparedStatement pPreparedStatement, User pUser) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void fillAuditColumns(IUser pUser) {
		// TODO Auto-generated method stub
		
	}
	
}
