package com.catapp.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

public class ChemData {
	
	public static final Logger logger = Logger.getLogger(ChemData.class.toString());
	public LinkedHashMap<String,String> getNamesofInputs(String pTableName,Connection pConnection){
		
		LinkedHashMap<String,String> lPhenotypeMap =new LinkedHashMap<String,String>();
		PreparedStatement lPstmt = null;
		ResultSet lRst 			 = null;
		try{
			/*if(pTableName!="celllines"){
				
				lPhenotypeMap.put(, "---Select One---");
			}*/
			String lBuilder = "select tag,name,dsc from xxx where rowstate!=-1 ";
			lBuilder =lBuilder.replaceAll("xxx", pTableName);
			lPstmt =pConnection.prepareStatement(lBuilder);
			lRst = lPstmt.executeQuery();
			while(lRst.next()){
				
					lPhenotypeMap.put(lRst.getString(1), lRst.getString(2));
					
				
			}
			
		}catch(Exception e){
			logger.error("Error Occured while getting the cellline names",e);
		}
		
		return lPhenotypeMap;
	}
	
	public HashMap<Long,String> getTagNamesofInputs(String pTableName,Connection pConnection){
		
		HashMap<Long,String> lPhenotypeMap =new HashMap<Long,String>();
		PreparedStatement lPstmt = null;
		ResultSet lRst 			 = null;
		try{
			
			String lBuilder = "select entity_id,tag from xxx where rowstate!=-1 ";
			lBuilder =lBuilder.replaceAll("xxx", pTableName);
			lPstmt =pConnection.prepareStatement(lBuilder);
			lRst = lPstmt.executeQuery();
			while(lRst.next()){
				lPhenotypeMap.put(lRst.getLong(1), lRst.getString(2));
			}
			
		}catch(Exception e){
			logger.error("Error Occured while getting the cellline names",e);
		}
		
		return lPhenotypeMap;
	}
	
	public HashMap<Long,String> getAssayNames(){
		HashMap<Long,String> lAssayMap =new HashMap<Long,String>();
		lAssayMap.put(1l, "Ca2+");
		lAssayMap.put(2l, "Hoechst");
		lAssayMap.put(3l, "MitoTracker");
	
		return lAssayMap;
	}
	
	public HashMap<Long,String> getTimePoints(){
		HashMap<Long,String> lTPMap =new HashMap<Long,String>();
		lTPMap.put(0l, "---Select One---");
		lTPMap.put(1l, "15 min");
		lTPMap.put(2l, "30 min");
		lTPMap.put(3l, "60 min");
		lTPMap.put(4l, "90 min");
		lTPMap.put(5l, "24 hr");
	
		return lTPMap;
	}
	
	public HashMap<Long,String> getCellLines(){
		HashMap<Long,String> lTPMap =new HashMap<Long,String>();
		lTPMap.put(1l, "iCell Hepatocytes 2.0");
		lTPMap.put(2l, "iCell Cardiomyocytes");
		lTPMap.put(3l, "iCell endothelial cells");
		lTPMap.put(4l, "iCell neurons");
		lTPMap.put(5l, "iCell macrophages prototype");
	
		return lTPMap;
	}
	public String getTagNames(Long pCellLine){
		String lReturnTag = null;
		HashMap<Long,String>lTMap = new HashMap<Long,String>();
		lTMap.put(2l, "CM");
		lTMap.put(3l, "HP");
		lReturnTag=lTMap.get(pCellLine);
		
	
		return lReturnTag;
	}
	/*public String getFileExtensions(String pType){
		String lReturnExten = null;
		
		switch(pType) {
		
		case "xls":
			lReturnExten =".xls";
		}
		
	
		return lReturnExten;
	}*/

	public String generateQForparameter(int size){
		
		String lReturnString =null;
		for(int i=0;i<size;i++){
			if(i==0){
				lReturnString="?";
			}
			else{
				lReturnString=lReturnString+",?";
			}
		}
		return lReturnString;
	}
	public HashMap<String,String>getChemicalNames(Connection pConnection){
		HashMap<String,String>lChemicalMap = new HashMap<String,String>();
		PreparedStatement lPstmt 		   = null;
		ResultSet lRst 			           = null;
		try{
			String lBuilder = "select cas_number,name From chemical_cas_concawe_mapping";
			
			lPstmt =pConnection.prepareStatement(lBuilder);
			lRst = lPstmt.executeQuery();
			while(lRst.next()){
				lChemicalMap.put(lRst.getString(1), lRst.getString(2));
				System.out.print(true);
			}
			
		}catch(Exception e){
			logger.error("Error Occured while getting chemical names",e);
		}
		return lChemicalMap;
	}
	public HashMap<String,String>getCasNames(Connection pConnection, Long pFlag){
		HashMap<String,String>lChemicalMap = new HashMap<String,String>();
		PreparedStatement lPstmt 		   = null;
		ResultSet lRst 			           = null;
		try{
			String lBuilder = "select cat_app_id,cas_number,concawe_id From chemical_cas_concawe_mapping";
			
			lPstmt =pConnection.prepareStatement(lBuilder);
			lRst = lPstmt.executeQuery();
			while(lRst.next()){
				if(pFlag==1){
					lChemicalMap.put(lRst.getString(1), lRst.getString(2));	
					
				}else{
					lChemicalMap.put(lRst.getString(1), lRst.getString(3));	
				}
			}
			
		}catch(Exception e){
			logger.error("Error Occured while getting chemical names",e);
		}
		return lChemicalMap;
	}

}
