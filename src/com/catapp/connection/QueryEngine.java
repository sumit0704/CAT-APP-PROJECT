package com.catapp.connection;

import java.sql.Connection;
import java.util.ArrayList;

import com.catapp.Interfaces.IBaseEntity;
import com.catapp.entity.User;

public class QueryEngine {

public String buildFindQuery(Connection pConnection, ArrayList<IBaseEntity> pEntities, String[] pTableAliases, 
			 String lWhereClause,User pUser){

		String lReturnQuery=null;
		String lTableName="";
		try{
			if(pConnection!=null){
				
				for(int i=0;i<pEntities.size();i++){
					lTableName= pEntities.get(i).getTableName()+" as "+ pTableAliases[i];
				}
				lReturnQuery = "select A.* from "+ lTableName +" where " + lWhereClause;
				
		   }
		}catch (Exception e){
			
		}
		return lReturnQuery;
		
		}
}
