package com.catapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.catapp.connection.DBConnection;

public class TestDownloadByCompound {
	
	public static void downloadFileByCompound(){
		
		Connection lConn			= null;
		PreparedStatement lPstmt    = null;
		ResultSet lRst              = null;
		String lCellLineName		= null;
		String lPhenoTypeName			= null;
		String lCatAppId			= null;
		String lCASNumber			= null;
	
		
		try{
			String lCompound= "016/kerosine";
			String lCellLine ="CM";
			String lPhenotype ="PF";
			
		File lFile = new File ("C:/Users/ssingh/serverfiles/templates/Excel By Compound.xlsx");
		lConn=new DBConnection().getConnection(); 
		FileInputStream fis = new FileInputStream(lFile);
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
		XSSFSheet mySheet = myWorkBook.getSheet("ExcelByCompound");
		 
		StringBuilder lBuilder = new StringBuilder("select b.timepoint,a.thousandx,a.hunderedx,a.tenx,a.onex ")
								 .append(",d.name as phenotype,c.name as cellline,e.cas_number,e.cat_app_id,")
								 .append(" a.point_of_departure from processed_readings_details a join ")
								 .append(" processed_readings_header b")
								 .append(" on a.header_id=b.entity_id join celllines c on c.tag=b.cellline join phenotypes d on ")
								 .append(" d.tag=b.phenotype join chemical_cas_concawe_mapping e on e.name=a.chemical_id ")
								 .append(" where d.tag=? and c.tag=? and e.name=? ");
		
		lPstmt=lConn.prepareStatement(lBuilder.toString());
		lPstmt.setString(1, lPhenotype);
		lPstmt.setString(2, lCellLine);
		lPstmt.setString(3, lCompound);
		lRst=lPstmt.executeQuery();
		int i=9;
		while (lRst.next()){
			if(lCellLineName==null){
				lCellLineName=lRst.getString("cellline");
			}if(lPhenoTypeName==null){
				lPhenoTypeName=lRst.getString("phenotype");
			}if (lCASNumber==null){
				lCASNumber=lRst.getString("cas_number");
			}if (lCatAppId==null){
				lCatAppId =lRst.getString("cat_app_id");
			}
			XSSFRow lRow =mySheet.createRow(i);
			XSSFCell lTimePointCell=lRow.createCell(0);
			lTimePointCell.setCellValue(lRst.getString("timepoint"));
			XSSFCell l1000XCell=lRow.createCell(1);
			l1000XCell.setCellValue(lRst.getString("thousandx"));
			XSSFCell l100XCell=lRow.createCell(2);
			l100XCell.setCellValue(lRst.getString("hunderedx"));
			XSSFCell l10XCell=lRow.createCell(3);
			l10XCell.setCellValue(lRst.getString("tenx"));
			XSSFCell l1XCell=lRow.createCell(4);
			l1XCell.setCellValue(lRst.getString("onex"));
			XSSFCell lPODCell=lRow.createCell(5);
			lPODCell.setCellValue(lRst.getString("point_of_departure"));
			i++;
		}
		XSSFRow lRow1 =mySheet.getRow(1);
		XSSFCell lCell =lRow1.getCell(1);
		lCell.setCellValue(lCompound);
		XSSFRow lRow2 =mySheet.getRow(2);
		XSSFCell lCell1 =lRow2.getCell(1);
		lCell1.setCellValue(lCASNumber);
		XSSFRow lRow3 =mySheet.getRow(3);
		XSSFCell lCell2 =lRow3.getCell(1);
		lCell2.setCellValue(lCatAppId);
		XSSFRow lRow4 =mySheet.getRow(4);
		XSSFCell lCell3 =lRow4.getCell(1);
		lCell3.setCellValue(lCellLineName);
		XSSFRow lRow5 =mySheet.getRow(5);
		XSSFCell lCell4 =lRow5.getCell(1);
		lCell4.setCellValue(lPhenoTypeName);
		FileOutputStream fop = new FileOutputStream("C:/Users/ssingh/serverfiles/templates/Test.xlsx");  
		myWorkBook.write(fop);
		myWorkBook.close();
		fop.close();
		lConn.close();
		lPstmt.close();
		lRst.close();
			
		}catch (Exception e){
			
		}
		
	}
	
	public static void main (String[] args){
		downloadFileByCompound();
	}

}
