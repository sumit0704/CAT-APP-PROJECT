package com.catapp.Interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.catapp.entity.User;


/**
 * This interface will be implemented by all the classes that represent a main
 * table in the database.
 */
public interface IBaseEntity extends Comparable<IBaseEntity>
{
	/**
	 * The Unique Id identifying the Entity in the Table. This will be the
	 * Primary Key of the Table.
	 * 
	 * @return - Returns the Long object representing the unique identifier of
	 *         the Record in the Table.
	 */
	Long getEntityId();

	/**
	 * This sets the Unique Identifier of the Record represented by the Object
	 * to the Object.
	 * 
	 * @param pEntityId -
	 *            The Unique number identifying the record in the table.
	 */
	void setEntityId(Long pEntityId);

	/**
	 * The Id of the Data Center where the record was generated.
	 * 
	 * @return - Returns the ID of the Data Center where the record was
	 *         generated.
	 */
	Long getDataCenterId();

	/**
	 * This sets the Data Center Id of the Data Center where the record is
	 * generated.
	 * 
	 * @param pDataCenterId -
	 *            The Id of the Data Center where the record is generated.
	 */
	void setDataCenterId(Long pDataCenterId);

	/**
	 * The Organization Id corresponding to the Entity. It will either be the
	 * Organization Id which owns the record or the Organization to which the
	 * record applies depending on the table.
	 * 
	 * @return - Returns the Long object representing the unique identifier of
	 *         the Organization.
	 */
	Long getOrganizationId();

	/**
	 * This sets Organization Id corresponding to the Entity. It will either be
	 * the Organization Id which owns the record or the Organization to which
	 * the record applies depending on the table.
	 * 
	 * @param pOrganizationId -
	 *            The Organization to which the record belongs.
	 */
	void setOrganizationId(Long pOrganizationId);

	/**
	 * This method returns the Id of the User who created this record.
	 * 
	 * @return - Returns the Id of the User who created this record.
	 */
	Long getLoggedBy();

	/**
	 * This method sets the Id of the User who created this record.
	 * 
	 * @param pLoggedBy -
	 *            The Id of the User who created this record.
	 */
	void setLoggedBy(Long pLoggedBy);

	/**
	 * This method gets the Date and Time at which the record was created.
	 * 
	 * @return - Returns the Date and Time at which the record was created.
	 */
	Timestamp getLoggedDate();

	/**
	 * This method sets the Date and Time at which the record was Created.
	 * 
	 * @param pLoggedDate -
	 *            The Date and Time at which the record was Created.
	 */
	void setLoggedDate(Timestamp pLoggedDate);

	/**
	 * This method records the Id of the user who Last Updated the Record.
	 * 
	 * @return - Returns the Id of the user who Last Updated the Record.
	 */
	Long getLastUpdatedBy();

	/**
	 * This method sets the Id of the User who Last Updated the Record.
	 * 
	 * @param pLastUpdatedBy -
	 *            The Id of the User who Last Updated the Record.
	 */
	void setLastUpdatedBy(Long pLastUpdatedBy);

	/**
	 * This method returns the date and time at which the record was last
	 * updated. This will be null when the record has just been created.
	 * 
	 * @return - The last date and time at which the record was updated.
	 */
	Timestamp getLastUpdatedDate();

	/**
	 * This method sets the date and time at which the record was last updated.
	 * 
	 * @param pLastUpdatedAt -
	 *            The date and time at which the record was last updated.
	 */
	void setLastUpdatedDate(Timestamp pLastUpdatedAt);

	/**
	 * This method gets the Row State of the Record.
	 * 
	 * @return - The Row State of the Record.
	 */
	Long getRowstate();

	/**
	 * This method sets the Row State of the Record.
	 * 
	 * @param pRowstate -
	 *            The Row State of the Record.
	 */
	void setRowstate(Long pRowstate);

	/**
	 * This method gets the name of the table for which this is the class.
	 * 
	 * @return - The name of the table for which this is the class.
	 */
	String getTableName();
	
	/**
	 * This method gets the global entity type id of the table.
	 * 
	 * @return - The global entity type id of the table.
	 */
	Long getEntityTypeId();

	/**
	 * This method will fill the object from the values in the ResultSet. This
	 * method will try to fill in the value of all the columns in the table.
	 * 
	 * @param pResultSet -
	 *            The ResultSet from which the data needs to be filled into the
	 *            object.
	 */
	/**
	 * @param pResultSet -
	 *            The ResultSet from which the data needs to be filled into the
	 *            object.
	 * @param pTableAlias -
	 *            The Alias of the Table as used in the Query
	 * @throws ,
	 *             , SQLException
	 */
	void fill(ResultSet pResultSet, String pTableAlias) throws SQLException;

	/**
	 * Returns the Primary Key value of the Record in a string format.
	 * 
	 * @return - The Primary Key value of the Record.
	 * @throws 
	 */
	String getPrimaryKey() ;

	/**
	 * This method returns the Primary Key Values separated by "-" formatted as
	 * per the formatting preferences of the User. If there are any values of
	 * individual fields which have a "-" in them they are replaced by the
	 * string |h|.
	 * 
	 * @param pUser -
	 *            The User as per whose preferences the Primary Key values are
	 *            to be formatted.
	 * @return Returns a string that represents the Primary Key for the Entity
	 * @throws 
	 */
	String getPrimaryKey(IUser pUser) ;

	/**
	 * Returns there where clause that can be appended to a SQL statement to
	 * filter by the RowState of this record. Typically used in an update
	 * statement where the RowState must match the earlier RowState for update.
	 * 
	 * @return Returns a string that can be appended to a SQL statement to
	 *         filter out records based on the RowState value of this entity.
	 */
	String getRowStateWhereClause();

	/**
	 * Returns the where clause that can be appended to a SQL statement to
	 * filter by the Primary Key value of this Record. This is to be used when
	 * the SQL is being built for use in PreparedStatement.
	 * 
	 * @return Returns a string that can be appended to a SQL statement.
	 */
	String getPrimaryKeyPreparedWhere();

	/**
	 * Returns there where clause that can be appended to a SQL statement to
	 * filter by the RowState of this record. Typically used in an update
	 * statement where the RowState must match the earlier RowState for update.
	 * This is to be used when the SQL is being built for use in
	 * PreparedStatement.
	 * 
	 * @return Returns a string that can be appended to a SQL statement to
	 *         filter out records based on the RowState value of this entity.
	 */
	String getRowStatePreparedWhere();

	/**
	 * This method returns the string that can be used to form the Prepared
	 * Insert Statement. This returns the list of columns to be inserted along
	 * with the place holders for the columns.
	 * 
	 * @return A string that can be used in the INSERT Statement
	 */
	String getInsertColumns();

	/**
	 * This method returns the string that can be used to form the Prepared
	 * Update Statement. This returns the list of columns to be updated along
	 * with the place holders for the columns. It also gives the Primary Key
	 * where clause.
	 * 
	 * @return A string that can be used in the UPDATE Statement
	 */
	String getUpdateColumns();

	/**
	 * This method sets all the values required to be set in an Insert
	 * PreparedStatement.
	 * 
	 * @param pPreparedStatement -
	 *            The Prepared Statement in which the values are to be set.
	 * @throws 
	 */
	void setInsertValues(PreparedStatement pPreparedStatement)  ;

	/**
	 * This method sets all the values required to be set in an Update
	 * PreparedStatement.
	 * 
	 * @param pPreparedStatement -
	 *            The Prepared Statement in which the values are to be set.
	 * @throws 
	 */
	void setUpdateValues(PreparedStatement pPreparedStatement) ;

	/**
	 * This method sets all the values required to be set in a Delete
	 * PreparedStatement or a Select Prepared Statement.
	 * 
	 * @param pPreparedStatement -
	 *            The Prepared Statement in which the values are to be set.
	 * @throws 
	 */
	void setPrimaryKeyValues(PreparedStatement pPreparedStatement)  ;

	/**
	 * This method prepares the Insert Prepared Statement for the Entity.
	 * 
	 * @param pConnection -
	 *            The Connection based on which the PreparedStatement is to be
	 *            created.
	 * @return Returns the PreparedStatement which can be used to Insert the
	 *         Entity into the Database.
	 * @throws 
	 */
	PreparedStatement prepareInsertStatement(Connection pConnection)  ;

	/**
	 * This method prepares the Update Prepared Statement for the Entity.
	 * 
	 * @param pConnection -
	 *            The Connection based on which the PreparedStatement is to be
	 *            created.
	 * @return Returns the PreparedStatement which can be used to Update the
	 *         Entity in the Database.
	 * @throws 
	 */
	PreparedStatement prepareUpdateStatement(Connection pConnection)  ;

	/**
	 * This method prepares the Delete Prepared Statement for the Entity.
	 * 
	 * @param pConnection -
	 *            The Connection based on which the PreparedStatement is to be
	 *            created.
	 * @return Returns the PreparedStatement which can be used to Delete the
	 *         Entity from the Database.
	 * @throws 
	 */
	PreparedStatement prepareDeleteStatement(Connection pConnection)  ;

	/**
	 * This method fill the Primary Key Columns of the Entity with new values if
	 * they are null. This method also fills in the Organization Id value and
	 * the Data Center Id if they are null.
	 * 
	 * @param pConnection -
	 *            The Configuration details of the Database to connect to.
	 * @param pUser -
	 *            The User who is currently logged in.
	 * @param pOrganizationId -
	 *            The Organization for which this action is being done. Either
	 *            this or the User should be specified for interaction with the
	 *            Database.
	 * @throws ,
	 *             , AccessRightsException
	 */
	void fillKeyColumns(Connection pConnection, IUser pUser) ;

	/**
	 * This method sets the values for the CreatedBy and CreatedAt or the
	 * LastUpdatedBy and LastUpdatedAt attributes of the Entity.
	 * 
	 * @param pUser -
	 *            The User for whom this Entity is being saved.
	 * @param pOrganizationId -
	 *            The Organization for which this Entity is being saved.
	 * @throws ,
	 *             
	 */
	void fillAuditColumns(IUser pUser) ;

	/**
	 * This method saves the given Entity to the database.
	 * 
	 * @param pConnection -
	 *            The Configuration details of the Database to connect to.
	 * @param pPreparedStatement -
	 *            The PreparedStatement to be used to Insert/Update the data of
	 *            the Entity to the Database.
	 * @param pUser -
	 *            The User for whom the data is being saved.
	 * @param pOrganizationId -
	 *            The Organization for which the Data is being saved. Either the
	 *            User or the Organization must be specified for a successful
	 *            save.
	 * @throws ,
	 *             AccessRightsException, 
	 */
	void save(Connection pConnection, PreparedStatement pPreparedStatement, User pUser);

	/**
	 * This method is used to specify the fields as a String Array on which
	 * sorting is to be done
	 * 
	 * @return String Array of the sorting fields
	 */
	String[] getCompareFields();

	/**
	 * This method is used to check whether the user has access to update the
	 * record
	 * 
	 * @param pConnection -
	 *            The Configuration details of the Database to connect to.
	 * @param pUser -
	 *            The User for whom the data is being saved.
	 * @param pOrganizationID - -
	 *            The Organization for which the Data is being saved. Either the
	 *            User or the Organization must be specified for a successful
	 *            save.
	 * @throws 
	 * @throws 
	 */
//	void organizationOwnsRecord(Connection pConnection, IUser pUser) ;
}