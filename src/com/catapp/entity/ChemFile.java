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

public class ChemFile extends BaseEntity{
	
	private static final Logger LOGGER = Logger.getLogger(ChemFile.class);
	public static final String TABLE_NAME = "File_info";
	public static final String f_filename = "file_name";
	public static final String f_filepath = "file_path";
	public static final String f_cellline = "cell_line_id";
	public static final String f_phenotype = "phenotype_id";
	public static final String f_assaytype = "assay_type";
	public static final String f_filetype = "file_type";
	public static final String f_plateId = 	"plate_id";
	public static final String f_Active = "is_active";
	public static final String f_description = "description";
	public static final String f_timePOINT = "timepoint";
	
	private String file_name;
	private String file_path;
	private Long cell_line_id;
	private Long phenotype_id;
	private Long assay_type;
	private String file_type;
	private Long plate_id;
	private int timepoint;
	
	public int getTimepoint() {
		return timepoint;
	}
	public void setTimepoint(int timepoint) {
		this.timepoint = timepoint;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String description;
	
	public Long getPlate_id() {
		return plate_id;
	}
	public void setPlate_id(Long plate_id) {
		this.plate_id = plate_id;
	}
	private String is_active;
	
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public Long getCell_line_id() {
		return cell_line_id;
	}
	public void setCell_line_id(Long cell_line_id) {
		this.cell_line_id = cell_line_id;
	}
	public Long getPhenotype_id() {
		return phenotype_id;
	}
	public void setPhenotype_id(Long phenotype_id) {
		this.phenotype_id = phenotype_id;
	}
	public Long getAssay_type() {
		return assay_type;
	}
	public void setAssay_type(Long assay_type) {
		this.assay_type = assay_type;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public String getInsertColumns()
	{
		return "(" /*+ f_entityId + ","*/  + f_filename + "," + f_filepath + "," + f_cellline + "," + f_phenotype
			+ "," + f_assaytype + "," + f_filetype +"," + f_Active + ","+ f_loggedDate + ","
			 + f_lastUpdatedDate + "," + f_loggedBy + "," + f_lastUpdatedBy + "," + f_rowstate+ "," +f_plateId+ "," +f_description+ ","+f_timePOINT
			+ ") VALUES(?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,?,?,?,?,?) ";
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
			pPreparedStatement.setString(1, getFile_name());
			pPreparedStatement.setString(2, getFile_path());
			pPreparedStatement.setLong(3, getCell_line_id());
			pPreparedStatement.setLong(4, getPhenotype_id());
			pPreparedStatement.setLong(5, getAssay_type());
			pPreparedStatement.setString(6,getFile_type());
			pPreparedStatement.setString(7, "Y");
			pPreparedStatement.setTimestamp(8, getLoggedDate());
			if (getLastUpdatedDate() == null)
			{
				pPreparedStatement.setNull(9, java.sql.Types.TIMESTAMP);
			}
			else
			{
				pPreparedStatement.setTimestamp(9, getLastUpdatedDate());
			}
			pPreparedStatement.setLong(10, getLoggedBy());
			if (getLastUpdatedBy() == null)
			{
				pPreparedStatement.setNull(11, java.sql.Types.BIGINT);
			}
			else
			{
				pPreparedStatement.setLong(11, getLastUpdatedBy());
			}
			pPreparedStatement.setLong(12, getRowstate());
			pPreparedStatement.setLong(13, getPlate_id());
			pPreparedStatement.setString(14, getDescription());
			pPreparedStatement.setInt(15, getTimepoint());
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
		return f_entityId + " = ?" + "," + f_filename + " = ?" + "," + f_filepath + " = ?" + "," + f_cellline + " = ?"
			+ "," + f_phenotype + " = ?" + "," + f_assaytype + " = ?" + "," + f_filetype + " = ?" + "," + f_Active + " = ?" + "," + f_loggedDate
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
	public void save(Connection pConnection, PreparedStatement pPreparedStatement, User pUser) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void fillAuditColumns(IUser pUser) {
		// TODO Auto-generated method stub
		
	}

}
