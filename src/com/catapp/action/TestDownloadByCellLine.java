package com.catapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.catapp.connection.DBConnection;

public class TestDownloadByCellLine {

	
	public static void downloadFileByCellLine(){
		
		Connection lConn			= null;
		PreparedStatement lPstmt    = null;
		ResultSet lRst              = null;
		String lCellLineName		= null;
		String lPhenoTypeName		= null;
		try{
			String lTimePoint= "30min";
			String lCellLine ="CM";
			String lPhenotype ="PF";
			
		File lFile = new File ("C:/Users/ssingh/serverfiles/templates/Excel By CellLine.xlsx");
		lConn=new DBConnection().getConnection(); 
		FileInputStream fis = new FileInputStream(lFile);
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
		XSSFSheet mySheet = myWorkBook.getSheet("ExcelByCellLine");
		 
		StringBuilder lBuilder = new StringBuilder("select b.timepoint,a.thousandx,a.hunderedx,a.tenx,a.onex ")
								 .append(",d.name as phenotype,c.name as cellline,e.name as chem_name,e.cas_number,e.cat_app_id,")
								 .append(" a.point_of_departure from processed_readings_details a join ")
								 .append(" processed_readings_header b")
								 .append(" on a.header_id=b.entity_id join celllines c on c.tag=b.cellline join phenotypes d on ")
								 .append(" d.tag=b.phenotype join chemical_cas_concawe_mapping e on e.name=a.chemical_id ")
								 .append(" where d.tag=? and c.tag=? and b.timepoint=?");
		
		lPstmt=lConn.prepareStatement(lBuilder.toString());
		lPstmt.setString(1, lPhenotype);
		lPstmt.setString(2, lCellLine);
		lPstmt.setString(3, lTimePoint);
		lRst=lPstmt.executeQuery();
		int i=7;
		while (lRst.next()){
			if(lCellLineName==null){
				lCellLineName=lRst.getString("cellline");
			}if(lPhenoTypeName==null){
				lPhenoTypeName=lRst.getString("phenotype");
			}
			XSSFRow lRow =mySheet.createRow(i);
			XSSFCell lChemName=lRow.createCell(0);
			lChemName.setCellValue(lRst.getString("chem_name"));
			
			XSSFCell lCasNumber=lRow.createCell(1);
			lCasNumber.setCellValue(lRst.getString("cas_number"));
			
			XSSFCell lCatAppId=lRow.createCell(2);
			lCatAppId.setCellValue(lRst.getString("cat_app_id"));
			
			XSSFCell lPODCell=lRow.createCell(3);
			lPODCell.setCellValue(lRst.getString("point_of_departure"));
			
			XSSFCell l1000XCell=lRow.createCell(4);
			l1000XCell.setCellValue(lRst.getString("thousandx"));
			XSSFCell l100XCell=lRow.createCell(5);
			l100XCell.setCellValue(lRst.getString("hunderedx"));
			XSSFCell l10XCell=lRow.createCell(6);
			l10XCell.setCellValue(lRst.getString("tenx"));
			XSSFCell l1XCell=lRow.createCell(7);
			l1XCell.setCellValue(lRst.getString("onex"));
			i++;
		}
		XSSFRow lRow1 =mySheet.getRow(1);
		XSSFCell lCell =lRow1.getCell(1);
		lCell.setCellValue(lCellLineName);
		XSSFRow lRow2 =mySheet.getRow(2);
		XSSFCell lCell1 =lRow2.getCell(1);
		lCell1.setCellValue(lPhenoTypeName);
		XSSFRow lRow3 =mySheet.getRow(3);
		XSSFCell lCell2 =lRow3.getCell(1);
		lCell2.setCellValue(lTimePoint);
		
		FileOutputStream fop = new FileOutputStream("C:/Users/ssingh/serverfiles/templates/Test1.xlsx");  
		myWorkBook.write(fop);
		myWorkBook.close();
		fop.close();
		lConn.close();
		lPstmt.close();
		lRst.close();
			
		}catch (Exception e){
			
		}
		finally 
		{
			try {
				lConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main (String[] args){
		downloadFileByCellLine();
	}



}
