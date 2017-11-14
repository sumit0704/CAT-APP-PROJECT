package com.catapp.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.catapp.connection.DBConnection;
import com.catapp.entity.FileInfo;

public class ChemData {

	public static final Logger logger = Logger.getLogger(ChemData.class.toString());

	public LinkedHashMap<String, String> getNamesofInputs(String pTableName, Connection pConnection) {

		System.out.println("start of  getNamesofInputs table name ::" + pTableName);
		LinkedHashMap<String, String> lPhenotypeMap = new LinkedHashMap<String, String>();
		PreparedStatement lPstmt = null;
		ResultSet lRst = null;
		try {
			/*
			 * if(pTableName!="celllines"){
			 * 
			 * lPhenotypeMap.put(, "---Select One---"); }
			 */
			String lBuilder = "select tag,name,dsc from xxx where rowstate!=-1 ";
			lBuilder = lBuilder.replaceAll("xxx", pTableName);
			lPstmt = pConnection.prepareStatement(lBuilder);
			lRst = lPstmt.executeQuery();
			while (lRst.next()) {

				lPhenotypeMap.put(lRst.getString(1), lRst.getString(2));

			}

		} catch (Exception e) {
			logger.error("Error Occured while getting the cellline names", e);
		}
		System.out.println("End of  getNamesofInputs table name ::" + pTableName);
		return lPhenotypeMap;
	}

	public HashMap<Long, String> getTagNamesofInputs(String pTableName, Connection pConnection) {

		HashMap<Long, String> lPhenotypeMap = new HashMap<Long, String>();
		PreparedStatement lPstmt = null;
		ResultSet lRst = null;
		try {

			String lBuilder = "select entity_id,tag from xxx where rowstate!=-1 ";
			lBuilder = lBuilder.replaceAll("xxx", pTableName);
			lPstmt = pConnection.prepareStatement(lBuilder);
			lRst = lPstmt.executeQuery();
			while (lRst.next()) {
				lPhenotypeMap.put(lRst.getLong(1), lRst.getString(2));
			}

		} catch (Exception e) {
			logger.error("Error Occured while getting the cellline names", e);
		}

		return lPhenotypeMap;
	}

	public HashMap<Long, String> getAssayNames() {
		HashMap<Long, String> lAssayMap = new HashMap<Long, String>();
		lAssayMap.put(1l, "Ca2+");
		lAssayMap.put(2l, "Hoechst");
		lAssayMap.put(3l, "MitoTracker");

		return lAssayMap;
	}

	public HashMap<Long, String> getTimePoints() {
		System.out.println("start of  getTimePoints");
		HashMap<Long, String> lTPMap = new HashMap<Long, String>();

		lTPMap.put(0l, "---Select One---");
		lTPMap.put(1l, "15min");
		lTPMap.put(2l, "30min");
		lTPMap.put(3l, "45min");
		lTPMap.put(4l, "60min");
		lTPMap.put(5l, "90min");
		lTPMap.put(6l, "18hr");
		lTPMap.put(7l, "24hr");
		lTPMap.put(8l, "36hr");
		lTPMap.put(9l, "72hr");
		lTPMap.put(10l, "48hr");
		lTPMap.put(1000l, "All");

		System.out.println("end of  getTimePoints");
		return lTPMap;
	}

	public HashMap<Long, String> getCellLines() {
		HashMap<Long, String> lTPMap = new HashMap<Long, String>();
		lTPMap.put(1l, "iCell Hepatocytes 2.0");
		lTPMap.put(2l, "iCell Cardiomyocytes");
		lTPMap.put(3l, "iCell endothelial cells");
		lTPMap.put(4l, "iCell neurons");
		lTPMap.put(5l, "iCell macrophages prototype");

		return lTPMap;
	}

	public String getTagNames(Long pCellLine) {
		String lReturnTag = null;
		HashMap<Long, String> lTMap = new HashMap<Long, String>();
		lTMap.put(2l, "CM");
		lTMap.put(3l, "HP");
		lReturnTag = lTMap.get(pCellLine);

		return lReturnTag;
	}
	/*
	 * public String getFileExtensions(String pType){ String lReturnExten =
	 * null;
	 * 
	 * switch(pType) {
	 * 
	 * case "xls": lReturnExten =".xls"; }
	 * 
	 * 
	 * return lReturnExten; }
	 */

	public String generateQForparameter(int size) {

		String lReturnString = null;
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				lReturnString = "?";
			} else {
				lReturnString = lReturnString + ",?";
			}
		}
		return lReturnString;
	}

	public HashMap<String, String> getChemicalNames(Connection pConnection) {

		System.out.println("start of  getChemicalNames");
		HashMap<String, String> lChemicalMap = new HashMap<String, String>();
		PreparedStatement lPstmt = null;
		ResultSet lRst = null;
		try {
			String lBuilder = "select cas_number,name From chemical_cas_concawe_mapping";

			lPstmt = pConnection.prepareStatement(lBuilder);
			lRst = lPstmt.executeQuery();
			while (lRst.next()) {
				lChemicalMap.put(lRst.getString(1), lRst.getString(2));

			}

		} catch (Exception e) {
			logger.error("Error Occured while getting chemical names", e);
		}
		System.out.println("End of getChemicalNames");
		return lChemicalMap;
	}

	public HashMap<String, String> getCasNames(Connection pConnection, Long pFlag) {
		HashMap<String, String> lChemicalMap = new HashMap<String, String>();
		PreparedStatement lPstmt = null;
		ResultSet lRst = null;
		try {
			String lBuilder = "select cat_app_id,cas_number,concawe_id,name,category From chemical_cas_concawe_mapping";

			lPstmt = pConnection.prepareStatement(lBuilder);
			lRst = lPstmt.executeQuery();
			while (lRst.next()) {
				if (pFlag == 1) {
					lChemicalMap.put(lRst.getString(1), lRst.getString(2));

				}

				else if (pFlag == 3) {
					String lValue = lRst.getString(2) + "    ||    " + lRst.getString(4) + "    ||    "
							+ lRst.getString(5);
					lChemicalMap.put(lRst.getString(2), lValue);
				}

				else {
					lChemicalMap.put(lRst.getString(1), lRst.getString(3));
				}
			}

		} catch (Exception e) {
			logger.error("Error Occured while getting chemical names", e);
		}
		return lChemicalMap;
	}

	public HashMap<String, String> getMolecularClasses(Connection pConnection) {
		HashMap<String, String> lMoleculeMap = new HashMap<String, String>();
		PreparedStatement lPstmt = null;
		ResultSet lRst = null;
		try {
			String lBuilder = "select attribute from concawe_attribute_list order  by 1";

			lPstmt = pConnection.prepareStatement(lBuilder);
			lRst = lPstmt.executeQuery();
			while (lRst.next()) {

				lMoleculeMap.put(lRst.getString(1), lRst.getString(1));

			}

		} catch (Exception e) {
			logger.error("Error Occured while getting attribute names", e);
		}
		return lMoleculeMap;
	}

	public List<HashMap<String, String>> getMolecularData(String molecularClass, String casNumbers) {
		Connection lConn = new DBConnection().getConnection();
		System.out.println("molecularClass::" + molecularClass + "casNumbers::" + casNumbers);

		List<HashMap<String, String>> moleculeList = new ArrayList<HashMap<String, String>>();
		PreparedStatement lPstmt = null;
		ResultSet lRst = null;
		StringBuilder lBuilder = new StringBuilder();
		try {
			lBuilder.append(" select ca.attribute,cd.rowno,cd.value,ch.sample from concawe_values_details cd,");
			lBuilder.append(" concawe_attribute_list ca, concawe_values_header ch ");
			lBuilder.append(" where cd.attributeid=ca.entity_id and (ca.attribute ='C-nr' OR ca.attribute =? )  and ");
			lBuilder.append(" cd.headerid=ch.entity_id and ch.cas_number = ? ");

			String query = lBuilder.toString();
			lPstmt = lConn.prepareStatement(query);
			lPstmt.setString(1, molecularClass);
			lPstmt.setString(2, casNumbers);

			lRst = lPstmt.executeQuery();
			HashMap<String, String> moleculeMap = null;
			boolean flag = true;
			while (lRst.next()) {
				moleculeMap = new HashMap<String, String>();
				moleculeMap.put("attribute", lRst.getString(1));
				moleculeMap.put("row", lRst.getString(2));
				moleculeMap.put("value", lRst.getString(3));
				if (flag) {
					moleculeMap.put("sample", lRst.getString(4));
					flag = false;
				}
				moleculeList.add(moleculeMap);
			}

		} catch (

		Exception e) {
			logger.error("Error Occured while getting chemical names", e);
		}
		System.out.println("moleculeList::" + moleculeList.size());
		return moleculeList;

	}

	public HashMap<String, String> getCarbonData(String carbonClass, String casNumbers) {
		Connection lConn = new DBConnection().getConnection();
		HashMap<String, String> lcarbonMap = new HashMap<String, String>();
		PreparedStatement lPstmt = null;
		ResultSet lRst = null;
		StringBuilder lBuilder = new StringBuilder();
		System.out.println("carbonClass ::" + carbonClass + "casNumbers::" + casNumbers);
		try {
			/*
			 * lBuilder.
			 * append(" select ca.attribute, cd.value, ch.sample, cd.columnno from concawe_values_details cd, "
			 * ); lBuilder.
			 * append(" concawe_attribute_list ca, concawe_values_header ch, chemical_cas_concawe_mapping cm "
			 * );
			 * lBuilder.append(" where (cd.attributeid=ca.entity_id  ) and ");
			 * lBuilder.
			 * append(" cd.headerid=ch.entity_id and ch.concaweid=cm.concawe_id and cm.cas_number = ? "
			 * ); lBuilder.
			 * append(" and cd.rowno= ( select rowno from concawe_values_details where attributeid=1 and value=? ) "
			 * ); lBuilder.append(" union "); lBuilder.
			 * append(" select attributeid, value, headerid,columnno from concawe_values_details cd, concawe_values_header ch, chemical_cas_concawe_mapping cm "
			 * ); lBuilder.
			 * append(" where cd.headerid=ch.entity_id and ch.concaweid=cm.concawe_id and cm.cas_number = ? "
			 * );
			 * lBuilder.append(" and attributeid=0 and valuetype=2 and rowno= "
			 * ); lBuilder.
			 * append(" (select rowno from concawe_values_details where attributeid=1 and value=? ) "
			 * );
			 */

			lBuilder.append(" select   distinct(ca.attribute), cd1.value, ch.sample, cd1.columnno from ");
			lBuilder.append(" concawe_values_details cd join concawe_values_details cd1 on ");
			lBuilder.append(" cd.rowno=cd1.rowno and cd.attributeid=1 and cd.value='" + carbonClass + "'");
			lBuilder.append(" join concawe_attribute_list ca on cd1.attributeid=ca.entity_id ");
			lBuilder.append(" join concawe_values_header ch on cd1.headerid=ch.entity_id and ch.cas_number='"
					+ casNumbers + "'");

			lBuilder.append(" union ");

			lBuilder.append("  select   distinct('RowSum'), cd1.value, ch.sample, cd1.columnno from ");
			lBuilder.append("  concawe_values_details cd join concawe_values_details cd1 on ");
			lBuilder.append("  cd.rowno=cd1.rowno and cd.attributeid=1 and cd.value='" + carbonClass
					+ "' and cd1.valuetype=2 ");
			lBuilder.append("  join concawe_values_header ch on cd1.headerid=ch.entity_id and ch.cas_number='"
					+ casNumbers + "'");
			lBuilder.append("  order by columnno ");

			// lBuilder.append(" select * from concawe_values_details ");
			String query = lBuilder.toString();
			lPstmt = lConn.prepareStatement(query);
			System.out.println("**********************************************");

			/*
			 * lPstmt.setString(1, "6.0"); lPstmt.setString(2, "64741-77-1");
			 * lPstmt.setString(3, "6.0"); lPstmt.setString(4, "64741-77-1");
			 */
			boolean flag = true;

			lRst = lPstmt.executeQuery();
			lcarbonMap = new HashMap<String, String>();
			while (lRst.next()) {
				System.out.println("lRst:: has value");

				lcarbonMap.put(lRst.getString(1), lRst.getString(2));
				// lcarbonMap.put("column", lRst.getString(4));
				if (flag) {
					lcarbonMap.put("sample", lRst.getString(3));
					flag = false;
				}
			}

		} catch (Exception e) {
			logger.error("Error Occured while getting carbon names", e);
		}
		System.out.println("lcarbonMap::" + lcarbonMap);
		return lcarbonMap;
	}

	public ArrayList<FileInfo> getFileInfo(ArrayList<String> cellList, ArrayList<String> assayList) {

		Connection lConn = new DBConnection().getConnection();
		System.out.println("Start of method getFileInfo cellList::" + cellList + "assayList::" + assayList);

		PreparedStatement lPstmt = null;
		ResultSet lRst = null;
		StringBuilder lBuilder = new StringBuilder();
		ArrayList<FileInfo> fileInfoList = new ArrayList<FileInfo>();

		try {
			lBuilder.append(
					" SELECT entity_id, file_name, cell_line_id, assay_type, Dilution, timepoint FROM file_info WHERE ");
			lBuilder.append(" cell_line_id IN (" + new ChemData().generateQForparameter(cellList.size())
					+ ") AND assay_type " + " IN (" + new ChemData().generateQForparameter(assayList.size()) + ")");

			String query = lBuilder.toString();
			lPstmt = lConn.prepareStatement(query);

			for (int i = 1; i <= cellList.size(); i++) {
				lPstmt.setString(i, cellList.get(i - 1));
			}
			for (int i = cellList.size() + 1; i <= assayList.size() + cellList.size(); i++) {
				lPstmt.setString(i, assayList.get(i - cellList.size() - 1));
			}

			lRst = lPstmt.executeQuery();
			FileInfo fileInfo = null;
			System.out.println("lRst::" + lRst);

			while (lRst.next()) {
				fileInfo = new FileInfo();
				fileInfo.setEntityId(lRst.getString(1));
				fileInfo.setFileName(lRst.getString(2));
				fileInfo.setCellLine(lRst.getString(3));
				fileInfo.setAssayType(lRst.getString(4));
				fileInfo.setDilution(lRst.getString(5));
				fileInfo.setTimePoint(lRst.getString(6));

				fileInfoList.add(fileInfo);
			}

		} catch (

		Exception e) {
			logger.error("Error Occured while getting chemical names", e);
		}
		System.out.println("End of method fileInfoList::" + fileInfoList.size());
		return fileInfoList;

	}

	public HashMap<String, ArrayList<String>> getAssayPhenoList(ArrayList<String> cellList, 
			ArrayList<String> assayList) {
		Connection lConn = new DBConnection().getConnection();
		System.out.println("Start of method getAssayPhenoList assayList::" + assayList+"cellList::"+cellList);

		PreparedStatement lPstmt = null;
		ResultSet lRst = null;
		StringBuilder lBuilder = new StringBuilder();
		ArrayList<String> assayResultList = null;
		HashMap<String, ArrayList<String>> mapList = new HashMap<String, ArrayList<String>>();

		try {
			lBuilder.append("select distinct(d.name),d.tag,a.tag,b.tag from celllines a join assaynames b on ");
			lBuilder.append(" a.entity_id=b.cellline_id join assayphenomapping ");
			lBuilder.append(" c on c.assay_id=b.entity_id join phenotypes d on d.entity_id=c.pheno_id "
					+ "where a.tag IN (" + new ChemData().generateQForparameter(cellList.size())
					+ ") ");
	
			lBuilder.append(" and b.tag IN (" + new ChemData().generateQForparameter(assayList.size())
					+ ") "); 
			
			String query = lBuilder.toString();
			lPstmt = lConn.prepareStatement(query);

			for (int i = 1; i <= cellList.size(); i++) {
				lPstmt.setString(i, cellList.get(i - 1));
			}
			for (int i = cellList.size() + 1; i <= assayList.size() + cellList.size(); i++) {
				lPstmt.setString(i, assayList.get(i - cellList.size() - 1));
			}

			lRst = lPstmt.executeQuery();
			System.out.println("lRst::" + lRst);

			while (lRst.next()) {
				if (!mapList.containsKey(lRst.getString(4))) {
					assayResultList = new ArrayList<String>();
					assayResultList.add(lRst.getString(2));
					mapList.put(lRst.getString(4), assayResultList);

				} else {
					assayResultList = mapList.get(lRst.getString(4));
					assayResultList.add(lRst.getString(2));
				}

			}

		} catch (

		Exception e) {
			logger.error("Error Occured while getting pheno names", e);
		}
		System.out.println("End of method mapList::" + mapList.size());
		return mapList;

	}

	public HashMap<String, ArrayList<String>> getCellAssayList(ArrayList<String> selected_cell_lines) 
	{
		Connection lConn = new DBConnection().getConnection();
		System.out.println("Start of method getCellAssayList assayList::" + selected_cell_lines);

		PreparedStatement lPstmt = null;
		ResultSet lRst = null;
		StringBuilder lBuilder = new StringBuilder();
		ArrayList<String> assayResultList = null;
		HashMap<String, ArrayList<String>> mapList = new HashMap<String, ArrayList<String>>();

		try {
			lBuilder.append(" select  a.name,a.tag,b.tag,b.name From assaynames a join celllines b on ")
					.append("  a.cellline_id=b.entity_id where b.tag in  (" + 
			new ChemData().generateQForparameter(selected_cell_lines.size()) + ")");
			
			String query = lBuilder.toString();
			lPstmt = lConn.prepareStatement(query);

			for (int i = 1; i <= selected_cell_lines.size(); i++) {
				lPstmt.setString(i, selected_cell_lines.get(i - 1));
			}

			lRst = lPstmt.executeQuery();
			System.out.println("lRst::" + lRst);

			while (lRst.next()) {
				if (!mapList.containsKey(lRst.getString(4))) {
					assayResultList = new ArrayList<String>();
					assayResultList.add(lRst.getString(2));
					mapList.put(lRst.getString(4), assayResultList);

				} else {
					assayResultList = mapList.get(lRst.getString(4));
					assayResultList.add(lRst.getString(2));
				}

			}

		} catch (

		Exception e) {
			logger.error("Error Occured while getCellAssayList ", e);
		}
		System.out.println("End of method getCellAssayList::" + mapList.size());
		return mapList;

	}

	public HashMap<String, ArrayList<String>> getTimePoint(List<String> pCellLines, List<String> pAssays)
	{
		System.out.println(" Start of method getTimePoint ");
		Connection lConn = new DBConnection().getConnection();
		PreparedStatement lPstmt = null;
		ResultSet lRst = null;
		StringBuilder lBuilder = new StringBuilder();
		ArrayList<String> timeResultList = null;
		HashMap<String, ArrayList<String>> mapList = new HashMap<String, ArrayList<String>>();

		try {
			lBuilder.append(" select distinct b.timepoint,b.entity_id,a.assaytag from timepoints a join timepoint_metadata b on a.timepoint=b.entity_id ");
			lBuilder.append("  where a.celltag in (" + new ChemData().generateQForparameter(pCellLines.size()) + ")"
							+ " and a.assaytag in (" + new ChemData().generateQForparameter(pAssays.size()) + ")" );
			
			String query = lBuilder.toString();
			lPstmt = lConn.prepareStatement(query);

			for (int i = 1; i <= pCellLines.size(); i++) {
				lPstmt.setString(i, pCellLines.get(i - 1));
			}
			for (int i = pCellLines.size() + 1; i <= pAssays.size() + pCellLines.size(); i++) {
				lPstmt.setString(i, pAssays.get(i - pCellLines.size() - 1));
			}


			lRst = lPstmt.executeQuery();
			System.out.println("lRst::" + lRst);

			while (lRst.next())
			{
					System.out.println("hii");
				if (!mapList.containsKey(lRst.getString(3))) {
					timeResultList = new ArrayList<String>();
					timeResultList.add(lRst.getString(1));
					mapList.put(lRst.getString(3), timeResultList);

				} else {
					timeResultList = mapList.get(lRst.getString(3));
					timeResultList.add(lRst.getString(1));
				}

			}

		} catch (

		Exception e) {
			logger.error("Error Occured while getTimePoint ", e);
		}
		System.out.println("End of method getTimePoint::" + mapList.size());
		return mapList;

	}

}
