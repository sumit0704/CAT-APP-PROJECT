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
	public static final String f_supervisor = "supervisor_id";
	public static final String f_admin = "is_admin";
	public static final String f_last_login_time = "last_login_time";
	public static final String f_Active = "is_active";
	
	private String user_name;
	private String password;
	private String first_name;
	private String last_name;
	private String institution;
	private String phone_number;
	private String email;
	private Long supervisor_id;
	private String is_admin;
	private Timestamp last_login_time;
	private String is_active;
	
	public String getIs_active() {
		return is_active;
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
	public Long getSupervisor_id() {
		return supervisor_id;
	}
	public void setSupervisor_id(Long supervisor_id) {
		this.supervisor_id = supervisor_id;
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
		return "(" + f_entityId + ","  + f_username + "," + f_password + "," + f_firstname + "," + f_last_name
			+ "," + f_institution + "," + f_phone + "," + f_email + "," + f_supervisor + "," + f_admin + "," + f_last_login_time + f_Active + ","+ f_loggedDate + ","
			+ "," + f_lastUpdatedDate + "," + f_loggedBy + "," + f_lastUpdatedBy + "," + f_rowstate
			+ ") VALUES(?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,? ) ";
	}
	
	public void setInsertValues(PreparedStatement pPreparedStatement)
	{
		try
		{
			if (getEntityId() == null)
			{
				pPreparedStatement.setNull(1, java.sql.Types.BIGINT);
			}
			else
			{
				pPreparedStatement.setLong(1, getEntityId());
			}
			pPreparedStatement.setString(2, getUser_name());
			pPreparedStatement.setString(3, getPassword());
			pPreparedStatement.setString(4, getFirst_name());
			pPreparedStatement.setString(5, getLast_name());
			pPreparedStatement.setString(6, getInstitution());
			pPreparedStatement.setString(7, getPhone_number());
			pPreparedStatement.setString(8, getEmail());
			pPreparedStatement.setLong(9, 	getSupervisor_id());
			pPreparedStatement.setString(10, 	getIs_admin());
			pPreparedStatement.setTimestamp(11,null);
			pPreparedStatement.setString(12, getIs_active());
			pPreparedStatement.setTimestamp(13, getLoggedDate());
			if (getLastUpdatedDate() == null)
			{
				pPreparedStatement.setNull(14, java.sql.Types.TIMESTAMP);
			}
			else
			{
				pPreparedStatement.setTimestamp(14, getLastUpdatedDate());
			}
			pPreparedStatement.setLong(15, getLoggedBy());
			if (getLastUpdatedBy() == null)
			{
				pPreparedStatement.setNull(16, java.sql.Types.BIGINT);
			}
			else
			{
				pPreparedStatement.setLong(16, getLastUpdatedBy());
			}
			pPreparedStatement.setLong(17, getRowstate());
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
		return f_entityId + " = ?" + "," + f_username + " = ?" + "," + f_password + " = ?" + "," + f_firstname + " = ?"
			+ "," + f_last_name + " = ?" + "," + f_institution + " = ?" + "," + f_phone + " = ?" + "," + f_email + " = ?" + "," + f_supervisor
			+ " = ?" + "," + f_admin + " = ?" + "," + f_last_login_time + " = ?" + "," + f_Active + " = ?" + "," + f_loggedDate
			+ " = ?" + f_lastUpdatedDate + " = ?" + "," + f_loggedBy + " = ?" + "," + f_lastUpdatedBy + " = ?" + "," + f_rowstate + " = ?" + " WHERE " + f_entityId
			+ " = ?";
	}

	public void setUpdateValues(PreparedStatement pPreparedStatement)
	{
		try
		{
			pPreparedStatement.setLong(18, getEntityId());
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
	{}
	
	public void fill(HttpServletRequest pRequest, String pRecordCount, String pPrefix)
	{}
	@Override
	public void save(Connection pConnection, PreparedStatement pPreparedStatement, IUser pUser) {
		// TODO Auto-generated method stub
		
	}
	
}
