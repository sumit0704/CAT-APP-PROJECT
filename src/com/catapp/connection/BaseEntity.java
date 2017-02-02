
package com.catapp.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.catapp.Interfaces.IBaseEntity;
import com.catapp.Interfaces.IUser;
import com.catapp.entity.User;


public abstract class BaseEntity implements IBaseEntity
{
	public static final Logger logger = Logger.getLogger(BaseEntity.class.toString());
	protected String TABLE_NAME = "";
	protected Long ENTITY_TYPE_ID=null;
	public final static String f_entityId = "entity_id";
	public final static String f_parentEntityId = "parent_entity_id";
	public final static String f_dataCenterId = "dcnid";
	public final static String f_organizationId = "orgid";
	public final static String f_lastUpdatedBy = "last_updated_by";
	public final static String f_lastUpdatedDate = "last_updated_date";
	public final static String f_loggedDate = "logged_date";
	public final static String f_loggedBy = "logged_by";
	public final static String f_rowstate = "rowstate";
	private Long entityId;
	private Long parentEntityId;
	private Long dataCenterId;
	private Long organizationId;
	private Long loggedBy;
	private Timestamp loggedDate;
	private Long lastUpdatedBy;
	private Timestamp lastUpdatedDate;
	private Long rowstate;
	private String insertSQL;
	private String updateSQL;
	private String selectBeforeUpdateSQL;
	private String deleteSQL;
	private String logicalDeleteSQL;
	private boolean rowStateFound = false;

	public Long getEntityTypeId(){
		return ENTITY_TYPE_ID;
	}
	public String getPrimaryKey(IUser pUser)
	{
		logger.info(rowStateFound + "," + pUser);
		return null;
	}

	public String getRowStateWhereClause()
	{
		return f_rowstate + "=" + rowstate;
	}
/*
	*//**
	 * This method saves the details of the Entity to the corresponding Database
	 * Table. This is to be used for both insert and update. The value of
	 * rowstate determines if this is an update or a insert.
	 * 
	 * @param pConnection -
	 *            The Configuration details of the Database to connect to.
	 * @param pUser -
	 *            This is the logged in User.
	 * @
	 * @,
	 *             AccessRightsException, ExecutionException
	 *//*
	public void save(Connection pConnection, IUser pUser) throws SQLException
	{
		save(pConnection, pUser);
	}
*/

	
	/**
	 * This method saves the details of the Entity to the corresponding Database
	 * Table. This is to be used for both insert and update. The value of
	 * rowstate determines if this is an update or a insert.
	 * 
	 * @param pConnection -
	 *            The Configuration details of the Database to connect to.
	 * @param pUser -
	 *            This is the logged in User.
	 * @param pOrganizationId -
	 *            This is the Organization that owns the Record. This parameter
	 *            is to be passed in scenarios where no User is logged in but
	 *            the Organization is known.
	 * @
	 * @,
	 *             AccessRightsException, ExecutionException
	 */
	public void save(Connection pConnection, IUser pUser) 
	{
		PreparedStatement lPreparedStatement = null;
		try
		{
			if (rowstate == null)
			{
				rowStateFound = false;
				lPreparedStatement = prepareInsertStatement(pConnection);
			}
			else
			{
				rowStateFound = true;
				lPreparedStatement = prepareUpdateStatement(pConnection);
			}
			save(pConnection, lPreparedStatement, pUser);
		}
		finally
		{
			// CloseResources.close(lPreparedStatement, this);
			try
			{
				if (lPreparedStatement != null)
					lPreparedStatement.close();
			}
			catch (Exception pException1)
			{
				logger.getLogger("save : Disconnect Failed", pException1.toString());
			}
		}
	}

	
	public void save(Connection pConnection, IUser pUser,int pAutoIncrement) 
	{
		PreparedStatement lPreparedStatement = null;
		ResultSet lResultSet=null;
			if(pAutoIncrement==1)
			{
				try
				{
				rowStateFound = false;
				lPreparedStatement = prepareInsertStatement(pConnection);
				setParentEntityId(0L);
				setDataCenterId(getDcnId());
				
				fillAuditColumns(pUser);
				this.setRowstate(new Long(1));
				this.setInsertValues(lPreparedStatement);
				
				lPreparedStatement.execute();
				lResultSet=lPreparedStatement.getGeneratedKeys();
				if (lResultSet.next())
				{
					Long lReturnId = lResultSet.getLong(1);
					setEntityId(lReturnId);
					setDataCenterId(getDcnId());
				}
				
			}
			catch (SQLException pException1)
			{
					Logger.getLogger("Error in Method --> save for AutoIncrement Entity Id", pException1.toString());
					
			}
			
			finally
			{
				try
				{
					if (lPreparedStatement != null)
						lPreparedStatement.close();
					if (lResultSet != null)
						lResultSet.close();
				}
				catch (Exception pException1)
				{
					Logger.getLogger("error in finally of : save for AutoIncrement Entity Id", pException1.toString());
				}
			}
		}	
	}
	/* This method returns the Prepared Statement of the Entity to the corresponding Database
    * Table. This is to be used for both insert and update. The values to be updated/inserted are already set in the statement
    *  The value of rowstate determines if this is an update or a insert.
    *  
    * @param pConnection
    * @param pUser
    * @param pOrganizationId
    * @return
    * @
    */
   public PreparedStatement getSavePreparedStatement( PreparedStatement pPreparedStatement, Connection pConnection, IUser pUser) 
   {
      
       try
       {
    	   if( pPreparedStatement==null){
    		   
    		   if (rowstate == null)
               {
                   rowStateFound = false;
                   pPreparedStatement = prepareInsertStatement(pConnection);
               }
               else
               {
                   rowStateFound = true;
                   pPreparedStatement = prepareUpdateStatement(pConnection);
               }
    	   }
          
          
       }
      
           catch (Exception pException1)
           {
               logger.getLogger("save : Disconnect Failed", pException1.toString());
           }
           return getSavePreparedStatement(pConnection, pPreparedStatement, pUser);
   }


	
	 /** This method returns the prepared statement to save the given Entity to the database.
     * 
     * @param pConnection
     * @param pPreparedStatement
     * @param pUser
     * @param pOrganizationId
     * @return
     * @
     */
    public PreparedStatement getSavePreparedStatement(Connection pConnection, PreparedStatement pPreparedStatement,IUser pUser)
    
    {
    try
    {
        if (rowstate == null)
        {
            fillKeyColumns(pConnection, pUser);
            fillAuditColumns(pUser);
            this.setRowstate(new Long(1));
            this.setInsertValues(pPreparedStatement);
        }
        else
        {
            rowStateFound = true;
            fillAuditColumns(pUser);
            this.setRowstate(new Long(this.getRowstate().longValue() + 1));
            this.setInsertValues(pPreparedStatement);
            this.setUpdateValues(pPreparedStatement);
        }
       
    }catch(Exception e){
    	
    }
    
    return pPreparedStatement;
    }

	// TODO - Change the concept of datacenter column
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
	 * @throws ExecutionException,
	 *             DatabaseException, AccessRightsException
	 */
	public void fillKeyColumns(Connection pConnection, IUser pUser)
	{
		Long lReturnId = new Long(0);
		
		try
		{
			//lReturnId = EDUIDFactory.getFactory().getID(pConnection, this.getTableName(), pOrganizationId,this.getEntityTypeId(),pUser.getId(),lAppId);
		}
		catch (Exception pException1)
		{
			Logger.getLogger("Error in Method --> setRowstate", pException1.toString());
			// TODO Auto-generated catch block
		}
		
		setEntityId(lReturnId);
		setParentEntityId(0L);
		setDataCenterId(getDcnId());

	}

	/**
	 * This method sets the values for the CreatedBy and CreatedAt or the
	 * LastUpdatedBy and LastUpdatedAt attributes of the Entity.
	 * 
	 * @param pUser -
	 *            The User for whom this Entity is being saved/
	 * @param pOrganizationId -
	 *            The Organization for which this Entity is being saved.
	 * @,
	 *             ExecutionException
	 */
	public void fillAuditColumns(IUser pUser)
	{
		if (getRowstate() == null)
		{
			setLoggedDate(new Timestamp(System.currentTimeMillis()));
			if (pUser != null)
			{
				//this.setLoggedBy(pUser.getId());
			}
			else
			{
				//this.setLoggedBy(pOrganizationId);
			}
		}
		else
		{
			setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
			if (pUser != null)
			{
				//this.setLastUpdatedBy(pUser.getId());
			}
			else
			{
				//this.setLastUpdatedBy(pOrganizationId);
			}
		}
	}

	/**
	 * This method prepares the Insert Prepared Statement for the Entity.
	 * 
	 * @param pConnection -
	 *            The Connection based on which the PreparedStatement is to be
	 *            created.
	 * @return Returns the PreparedStatement which can be used to Insert the
	 *         Entity into the Database.
	 * @
	 */
	public PreparedStatement prepareInsertStatement(Connection pConnection) 
	{
		if (insertSQL == null)
		{
			insertSQL = "INSERT INTO " + this.getTableName() + " " + this.getInsertColumns();
		}
		return createPreparedStatement(pConnection, insertSQL);
	}
	public PreparedStatement prepareInsertStatement(Connection pConnection,String lHostelRequested) 
	{
		if (insertSQL == null)
		{
			insertSQL = "INSERT INTO " + this.getTableName() + " " + this.getInsertColumns();
		}
		
		return createPreparedStatement(pConnection, insertSQL,lHostelRequested);
	}
	public PreparedStatement prepareInsertStatement(Connection pConnection,int pAutoIncrementFlag) 
	{
		if (insertSQL == null)
		{
			insertSQL = "INSERT INTO " + this.getTableName() + " " + this.getInsertColumns();
		}
		return createPreparedStatement(pConnection, insertSQL,pAutoIncrementFlag);
	}
	/**
	 * This method prepares the Update Prepared Statement for the Entity.
	 * 
	 * @param pConnection -
	 *            The Connection based on which the PreparedStatement is to be
	 *            created.
	 * @return Returns the PreparedStatement which can be used to Update the
	 *         Entity in the Database.
	 * @
	 */
	public PreparedStatement prepareUpdateStatement(Connection pConnection) 
	{
		if (updateSQL == null)
		{
			updateSQL = "UPDATE " + this.getTableName() + " SET " + this.getUpdateColumns();
		}
		return createPreparedStatement(pConnection, updateSQL);
	}

	/**
	 * This method prepares the Delete Prepared Statement for the Entity.
	 * 
	 * @param pConnection -
	 *            The Connection based on which the PreparedStatement is to be
	 *            created.
	 * @return Returns the PreparedStatement which can be used to Delete the
	 *         Entity from the Database.
	 * @
	 */
	public PreparedStatement prepareDeleteStatement(Connection pConnection) 
	{
		if (deleteSQL == null)
		{
			deleteSQL = "DELETE FROM " + this.getTableName() + " WHERE " + this.getPrimaryKeyPreparedWhere();
		}
		return createPreparedStatement(pConnection, deleteSQL);
	}

	/**
	 * This method creates a Prepared Statement
	 * 
	 * @param pConnection -
	 *            The Object of the Connection of Type java.sql.Connection
	 * @param pSQLStatement -
	 *            The SQL Query for which Prepared statement is to be created.
	 * @return lPreparedStatement
	 * @
	 */
	private PreparedStatement createPreparedStatement(Connection pConnection, String pSQLStatement) 
	{
		PreparedStatement lPreparedStatement = null; // PreparedStatement is
		// returned from here
		// and will be closed in
		// calling method
		try
		{
			lPreparedStatement = pConnection.prepareStatement(pSQLStatement);
		}
		catch (SQLException pException1)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException1);
			
		}
		catch (Exception pException2)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException2);
		}
		return lPreparedStatement;
	}

	private PreparedStatement createPreparedStatement(Connection pConnection, String pSQLStatement,String lHostelRequested) 
	{
		PreparedStatement lPreparedStatement = null; // PreparedStatement is
		// returned from here
		// and will be closed in
		// calling method
		try
		{
			logger.info(lHostelRequested);
			lPreparedStatement = pConnection.prepareStatement(pSQLStatement, Statement.RETURN_GENERATED_KEYS);
		}
	
		catch (SQLException pException1)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException1);
			
		}
		catch (Exception pException2)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException2);
		}
		return lPreparedStatement;
	}
	
	private PreparedStatement createPreparedStatement(Connection pConnection, String pSQLStatement,int pIncrementFlag) 
	{
		PreparedStatement lPreparedStatement = null; // PreparedStatement is
		// returned from here
		// and will be closed in
		// calling method
		logger.info(pIncrementFlag+"");
		try
		{
			lPreparedStatement = pConnection.prepareStatement(pSQLStatement,Statement.RETURN_GENERATED_KEYS);
		}
		catch (SQLException pException1)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException1);
			
		}
		catch (Exception pException2)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException2);
		}
		return lPreparedStatement;
	}
	/**
	 * This method deletes the record corresponding to the entity from the
	 * database.
	 * 
	 * @param pConnection -
	 *            The Object of the Connection of Type java.sql.Connection
	 * @param pUser -
	 *            This is the logged in User.
	 * @,
	 *             AccessRightsException, ExecutionException
	 */
	public void delete(Connection pConnection, IUser pUser) 
	{
		delete(pConnection, pUser);
	}



	/**
	 * This method is used to logically delete the record. The rowstate of the
	 * record is changed to -1.
	 * 
	 * @param pConnection -
	 *            The Object of the Connection of Type java.sql.Connection
	 * @param pUser -
	 *            This is the logged in User.
	 * @,
	 *             AccessRightsException, ExecutionException
	 */
	public void logicalDelete(Connection pConnection, IUser pUser) 
	{
		logicalDelete(pConnection, pUser);
	}

	/**
	 * This method is used to logically delete the record. The rowstate of the
	 * record is changed to -1.
	 * 
	 * @param pConnection -
	 *            The Object of the Connection of Type java.sql.Connection
	 * @param pUser -
	 *            This is the logged in User.
	 * @param pOrganizationId -
	 *            This is the Organization that owns the Record. This parameter
	 *            is to be passed in scenarios where no User is logged in but
	 *            the Organization is known.
	 * @,
	 *             AccessRightsException, ExecutionException
	 */
	public void logicalDelete(Connection pConnection, IUser pUser, Long pOrganizationId) 
	{
		PreparedStatement lPreparedStatement = null;
		try
		{
			if (logicalDeleteSQL == null)
			{
				logicalDeleteSQL = "UPDATE " + this.getTableName() + " SET " + f_lastUpdatedDate + " = '"
					+ new Timestamp(System.currentTimeMillis()) + "', " + f_lastUpdatedBy + " = " + pUser.getId() + ", " + f_rowstate
					+ " = -1 WHERE " + this.getPrimaryKeyPreparedWhere();
			}
			lPreparedStatement = pConnection.prepareStatement(logicalDeleteSQL);
			setDeleteKeyValues(lPreparedStatement);
			lPreparedStatement.execute();
		}
		catch (SQLException pException1)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException1);
			
		}
		catch (Exception pException3)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException3);
		}
		finally
		{
			// CloseResources.close(lPreparedStatement, this);
			try
			{
				if (lPreparedStatement != null)
					lPreparedStatement.close();
			}
			catch (Exception pException4)
			{
				logger.log(Level.INFO,"logicalDelete : Disconnect Failed", pException4);
			}
		}
	}

	/**
	 * This method sets the values to a prepared statement as will be required
	 * for deletion of the record.
	 * 
	 * @param pPreparedStatement -
	 *            The PreparedStatement in which the values need to be set.
	 * @
	 */
	public void setDeleteKeyValues(PreparedStatement pPreparedStatement) 
	{
		this.setPrimaryKeyValues(pPreparedStatement);
	}

	public void find(Connection pConnection, User pUser) 
	{
		String lSQLStatement = "";
		String lWhereClause;
		ArrayList<IBaseEntity> lEntities;
		String[] pTableAliases = { "A" };
		PreparedStatement lPreparedStatement = null;
		QueryEngine lQueryEngine = new QueryEngine();
		ResultSet lResultSet = null;
		lEntities = new ArrayList<IBaseEntity>();
		try
		{
			lEntities.add(this.getClass().newInstance());
			lWhereClause = this.getPrimaryKeyPreparedWhere(); 
			lSQLStatement = lQueryEngine.buildFindQuery(pConnection, lEntities, pTableAliases, lWhereClause, pUser);
			lPreparedStatement = pConnection.prepareStatement(lSQLStatement);
			setPrimaryKeyValues(lPreparedStatement);
			lResultSet = lPreparedStatement.executeQuery();
			if (lResultSet.next())
			{
				this.fill(lResultSet, pTableAliases[0]);
			}
			else
			{
				
			}
			if (lResultSet.next())
			{
				
			}
		}
		catch (SQLException pException1)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException1);
			
		} 
		catch (Exception pException4)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException4);
		}
		finally
		{
			try
			{
				if (lResultSet != null)
				{
					lResultSet.close();
				}
			}
			catch (Exception pException5)
			{
				logger.log(Level.INFO,"Error in Method --> setRowstate", pException5);
			}
			try
			{
				if (lPreparedStatement != null)
				{
					lPreparedStatement.close();
				}
			}
			catch (SQLException pException6)
			{
				logger.log(Level.INFO,"Error in Method --> setRowstate", pException6);
				
			}
			catch (Exception pException7)
			{
				logger.log(Level.INFO,"Error in Method --> setRowstate", pException7);
			}
		}
	}

	

	
	public String[] getCompareFields()
	{
		return null;
	}

	public int compareTo(IBaseEntity pEntity)
	{
		int lReturnCompareValue = 0;
		String lJavaMethodName = "";
		String lDataType = "";
		String[] lCompareColumns = getCompareFields();
		Method lMethod = null;
		try
		{
			if (lCompareColumns != null)
			{
				for (int i = 0; i < lCompareColumns.length; i++)
				{
					lJavaMethodName = "get" + lCompareColumns[i].substring(0, 1).toUpperCase();
					lJavaMethodName = lJavaMethodName + lCompareColumns[i].substring(1, lCompareColumns[i].length());
					lMethod = this.getClass().getMethod(lJavaMethodName, (Class[]) null);
					lDataType = lMethod.getReturnType().getSimpleName();
					if (lDataType.equalsIgnoreCase("long"))
					{
						Long lLongValue = 0L;
						Long lLongCompareValue = 0L;
						lLongValue = (Long) lMethod.invoke(this, (Object[]) null);
						lLongCompareValue = (Long) lMethod.invoke(pEntity, (Object[]) null);
						if (lLongValue == null && lLongCompareValue == null)
						{
							lReturnCompareValue = 0;
						}
						else if (lLongValue == null && lLongCompareValue != null)
						{
							lReturnCompareValue = -1;
						}
						else if (lLongValue != null && lLongCompareValue == null)
						{
							lReturnCompareValue = 1;
						}
						else
						{
							if (lLongValue != null && lLongCompareValue != null)
								lReturnCompareValue = lLongValue.intValue() - lLongCompareValue.intValue();
						}
					}
					else if (lDataType.equalsIgnoreCase("integer") || lDataType.equalsIgnoreCase("int"))
					{
						Integer lIntegerValue = 0;
						Integer lIntegerCompareValue = 0;
						lIntegerValue = (Integer) lMethod.invoke(this, (Object[]) null);
						lIntegerCompareValue = (Integer) lMethod.invoke(pEntity, (Object[]) null);
						if (lIntegerValue == null && lIntegerCompareValue == null)
						{
							lReturnCompareValue = 0;
						}
						else if (lIntegerValue == null && lIntegerCompareValue != null)
						{
							lReturnCompareValue = -1;
						}
						else if (lIntegerValue != null && lIntegerCompareValue == null)
						{
							lReturnCompareValue = 1;
						}
						else
						{
							if (lIntegerValue != null && lIntegerCompareValue != null)
								lReturnCompareValue = lIntegerValue.intValue() - lIntegerCompareValue.intValue();
						}
					}
					else if (lDataType.equalsIgnoreCase("string"))
					{
						String lStrValue = (String) lMethod.invoke(this, (Object[]) null);
						String lStrCompareValue = (String) lMethod.invoke(pEntity, (Object[]) null);
						if (lStrValue == null && lStrCompareValue == null)
						{
							lReturnCompareValue = 0;
						}
						else if (lStrValue == null && lStrCompareValue != null)
						{
							lReturnCompareValue = -1;
						}
						else if (lStrValue != null && lStrCompareValue == null)
						{
							lReturnCompareValue = 1;
						}
						else
						{
							if (lStrValue != null)
								lReturnCompareValue = lStrValue.compareTo(lStrCompareValue);
						}
					}
					else if (lDataType.equalsIgnoreCase("timestamp"))
					{
						Timestamp lTimeValue = (Timestamp) lMethod.invoke(this, (Object[]) null);
						Timestamp lTimeCompareValue = (Timestamp) lMethod.invoke(pEntity, (Object[]) null);
						if (lTimeValue == null && lTimeCompareValue == null)
						{
							lReturnCompareValue = 0;
						}
						else if (lTimeValue == null && lTimeCompareValue != null)
						{
							lReturnCompareValue = -1;
						}
						else if (lTimeValue != null && lTimeCompareValue == null)
						{
							lReturnCompareValue = 1;
						}
						else
						{
							if (lTimeValue != null)
								lReturnCompareValue = lTimeValue.compareTo(lTimeCompareValue);
						}
					}
					else if (lDataType.equalsIgnoreCase("Date"))
					{
						Date lDateValue = (Date) lMethod.invoke(this, (Object[]) null);
						Date lDateCompareValue = (Date) lMethod.invoke(pEntity, (Object[]) null);
						if (lDateValue == null && lDateCompareValue == null)
						{
							lReturnCompareValue = 0;
						}
						else if (lDateValue == null && lDateCompareValue != null)
						{
							lReturnCompareValue = -1;
						}
						else if (lDateValue != null && lDateCompareValue == null)
						{
							lReturnCompareValue = 1;
						}
						else
						{
							if (lDateValue != null)
								lReturnCompareValue = lDateValue.compareTo(lDateCompareValue);
						}
					}
					if (lReturnCompareValue != 0)
					{
						break;
					}
				}
			}
			else
			{
				Long lLongValue = this.getEntityId();
				Long lLongCompareValue = pEntity.getEntityId();
				if (lLongValue == null && lLongCompareValue != null)
				{
					lReturnCompareValue = -1;
				}
				else if (lLongValue != null && lLongCompareValue == null)
				{
					lReturnCompareValue = 1;
				}
				else if (lLongValue == null && lLongCompareValue == null)
				{
					lReturnCompareValue = 0;
				}
				else if (lLongValue != null && lLongCompareValue != null)
				{
					lReturnCompareValue = lLongValue.intValue() - lLongCompareValue.intValue();
				}
			}
		}
		catch (SecurityException pException1)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException1);
		}
		catch (NoSuchMethodException pException2)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException2);
		}
		catch (Exception pException3)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException3);
		}
		return lReturnCompareValue;
	}

	public String getRowStatePreparedWhere()
	{
		return f_rowstate + " = ?";
	}

	public String getPrimaryKeyPreparedWhere()
	{
		return f_entityId + " = ?";
	}

	public String getPrimaryKey()
	{
		return getEntityId() + "-" + getDataCenterId();
	}

	public void setPrimaryKeyValues(PreparedStatement pPreparedStatement) 
	{
		try
		{
			pPreparedStatement.setLong(1, getEntityId());
		}
		catch (SQLException pException1)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException1);
			
		}
		catch (Exception pException2)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException2);
		}
	}

	public void commitTransactions(Connection pConnection) 
	{
		try
		{
			if (!pConnection.getAutoCommit())
			{
				pConnection.commit();
				pConnection.setAutoCommit(false);
			}
		}
		catch (SQLException pException1)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException1);
			
		}
		catch (Exception pException2)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException2);
		}
	}

	public void organizationOwnsRecord(Connection pConnection, IUser pUser, Long pOrganizationID) 
	{
		logger.info(pUser + "," + pOrganizationID);
		PreparedStatement lPreparedStatement = null;
		ResultSet lResultSet = null;
		long lRowState;
		// First check that the organization in the database corresponding to
		// this record
		if (selectBeforeUpdateSQL == null)
		{
			selectBeforeUpdateSQL = "SELECT rowstate FROM " + this.getTableName() + " WHERE " + this.getPrimaryKeyPreparedWhere();
		}
		try
		{
			lPreparedStatement = pConnection.prepareStatement(selectBeforeUpdateSQL);
			this.setPrimaryKeyValues(lPreparedStatement);
			lResultSet = lPreparedStatement.executeQuery();
			if (lResultSet.next())
			{
				lRowState = lResultSet.getLong(1);
				if (this.getRowstate().longValue() != lRowState)
				{
					//throw new DatabaseException(Applications.getInstance().getMessage(Applications.MSG_DATA_CHANGED_ERROR));
				}
			}
			else
			{
				//throw new DatabaseException(Applications.getInstance().getMessage(Applications.MSG_DATA_NOT_FOUND_ERROR));
			}
		}
		catch (SQLException pException1)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException1);
			
		}
		
		catch (Exception pException3)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException3);
		}
		finally
		{
			// CloseResources.close(lResultSet, this);
			// CloseResources.close(lPreparedStatement, this);
			try
			{
				if (lResultSet != null)
					lResultSet.close();
				if (lPreparedStatement != null)
					lPreparedStatement.close();
			}
			catch (Exception pException4)
			{
				logger.log(Level.INFO,"organizationOwnsRecord : Disconnect Failed", pException4);
			}
		}
	}

	public Long getEntityId()
	{
		return entityId;
	}

	public void setEntityId(Long entityId)
	{
		this.entityId = entityId;
	}

	public Long getParentEntityId()
	{
		return parentEntityId;
	}

	public void setParentEntityId(Long parentEntityId)
	{
		this.parentEntityId = parentEntityId;
	}

	public Long getDataCenterId()
	{
		return dataCenterId;
	}

	public Long getDcnId() throws NumberFormatException
	{
		Properties lProperties = new Properties();
		FileInputStream oFileInputStream=null;
		String lDcnId = null;
		String lServerHome = System.getProperty("jboss.home.dir");
		if (lServerHome == null)
		{
			// If not found jBoss server then search for Tomcat server
			lServerHome = System.getProperty("catalina.home");
		}
		
		String lPropertiesFile = lServerHome + "/bin/BootStrapParameters.properties";
		
		try
		{
		 oFileInputStream=new FileInputStream(lPropertiesFile);	
			
			lProperties.load(oFileInputStream);
			lDcnId = lProperties.getProperty("DATACENTERID");
			
		}
		catch (IOException pException1)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException1);
		}
		catch (Exception pException2)
		{
			logger.log(Level.INFO,"Error in Method --> setRowstate", pException2);
		}
		finally
		{
			try{
				if(oFileInputStream!=null)
				oFileInputStream.close();
			}
			catch(IOException pException4){
				logger.log(Level.INFO,"Error in Method --> ", pException4);
			}
		}
		return Long.parseLong(lDcnId);
	}

	public void setDataCenterId(Long dataCenterId)
	{
		this.dataCenterId = dataCenterId;
	}

	public Long getOrganizationId()
	{
		return organizationId;
	}

	public void setOrganizationId(Long organizationId)
	{
		this.organizationId = organizationId;
	}

	public Long getLoggedBy()
	{
		return loggedBy;
	}

	public void setLoggedBy(Long loggedBy)
	{
		this.loggedBy = loggedBy;
	}

	public Timestamp getLoggedDate()
	{
		return loggedDate;
	}

	public void setLoggedDate(Timestamp loggedDate)
	{
		this.loggedDate = loggedDate;
	}

	public Long getLastUpdatedBy()
	{
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy)
	{
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getLastUpdatedDate()
	{
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate)
	{
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Long getRowstate()
	{
		return rowstate;
	}

	public void setRowstate(Long rowstate)
	{
		this.rowstate = rowstate;
	}
}