package com.catapp.action;


import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.catapp.connection.DBConnection;

public class FormatExcel {
	
	
	public static void main (String[] args){
		
		try{
			//File lFile = new File("C:/Users/ssingh/Desktop/processeddata/serverfiles");
			
			 File lDir = new File("C:/Users/ssingh/Desktop/processeddata/output/testfinal/");
			 File[] directoryListing = lDir.listFiles();
			 
			 for (File child : directoryListing) {
				 
				// child.renameTo(new File("C:/Users/ssingh/Desktop/processeddata/output/testfinal/"+child.getName()+".xlsx"));
				
				 
				  Connection lConn = new DBConnection().getConnection();
				  
				  File lFile2= new File("C:/Users/ssingh/Desktop/processeddata/output/testfinal/"+child.getName());   
				  FileInputStream fis1 = new FileInputStream(lFile2);
					 XSSFWorkbook myWorkBook1 = new XSSFWorkbook (fis1);
					 XSSFSheet mySheet1 = myWorkBook1.getSheet("Vehicle");
					 for(int i=1;i<113;i++){
						 XSSFRow lSourceRow = mySheet1.getRow(i);
						 XSSFCell lCell1 = lSourceRow.getCell(0);
						// String value=lCell1.getStringCellValue();
						 XSSFCell lCell2 = lSourceRow.getCell(1);
						// Double value1=lCell2.getNumericCellValue();
						 
							
							String lDetailQuery= "INSERT INTO test_movement (tag,value,"
									+ "cell,assay,pheno,tp) "
						            + " VALUES(?,?,?,?,?,?)";
							PreparedStatement lPstmtForDetail=lConn.prepareStatement(lDetailQuery);
							
							lPstmtForDetail.setString(1, lCell1.getStringCellValue());
							lPstmtForDetail.setDouble(2, lCell2.getNumericCellValue());
							lPstmtForDetail.setString(3, child.getName().split("_")[0]);
							lPstmtForDetail.setString(4, child.getName().split("_")[1]);
							lPstmtForDetail.setString(5, child.getName().split("_")[3]);
							lPstmtForDetail.setString(6, child.getName().split("_")[2]);
							lPstmtForDetail.execute();
							myWorkBook1.close();
					 }

				//child.renameTo(new File("C:/Users/ssingh/Desktop/processeddata/output/testfinal/"+lFinalname));
				 /*FileInputStream fis = new FileInputStream(lFile1);
				 XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
				 XSSFSheet mySheet = myWorkBook.getSheet("Vehicle");
				 
				 
				 File lFile2= new File("C:/Users/ssingh/Desktop/processeddata/output/tests/test1/"+child.getName());
				 FileInputStream fis1 = new FileInputStream(lFile2);
				 XSSFWorkbook myWorkBook1 = new XSSFWorkbook (fis1);
				 XSSFSheet mySheet1 = myWorkBook.getSheet("Vehicle");
				 
				 for(int i=1;i<113;i++){
					 XSSFRow lSourceRow = mySheet.getRow(i);
					 XSSFCell lCell1 = lSourceRow.getCell(0);
					 String value=lCell1.getStringCellValue();
					 XSSFCell lCell2 = lSourceRow.getCell(1);
					 Double value1=lCell2.getNumericCellValue();
					 XSSFRow lDestRow = mySheet1.createRow(i);
					 
					 XSSFCell lCell3 = lDestRow.createCell(0);
					 lCell3.setCellValue(value);
					 XSSFCell lCell4 = lDestRow.createCell(1);
					 lCell4.setCellValue(value1);
					 
					 
				 }
				 FileOutputStream lOutputFile = new FileOutputStream("C:/Users/ssingh/Desktop/processeddata/output/tests/test3/"+child.getName());
				 myWorkBook1.write(lOutputFile);
				 lOutputFile.close();
				 myWorkBook1.close();
				 myWorkBook.close();*/
			    }
			
			
			/*FileInputStream fis = new FileInputStream(lFile);
			
			XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
			for(int i=0;i<5;i++){
				XSSFWorkbook myOutputWorkbook = new XSSFWorkbook();
				XSSFSheet mySheet = myWorkBook.getSheetAt(i);
				
				FileOutputStream lOutputFile = new FileOutputStream("C:/Users/ssingh/Desktop/processeddata/output/HUV/"+myWorkBook.getSheetAt(i).getSheetName()+".xlsx");
				XSSFSheet outsheet1 = myOutputWorkbook.createSheet("Data");
				XSSFSheet outsheet2 = myOutputWorkbook.createSheet("Control");
				XSSFSheet outsheet3 = myOutputWorkbook.createSheet("Vehicle");
				XSSFRow lHeaderRow = outsheet1.createRow(1);
				
				 XSSFCell lHeaderCell0 = lHeaderRow.createCell(0);
				 lHeaderCell0.setCellValue("Chemical Name");
			     XSSFCell lHeaderCell1 = lHeaderRow.createCell(1);
			     lHeaderCell1.setCellValue("1000x");
			     XSSFCell lHeaderCell2 = lHeaderRow.createCell(2);
			     lHeaderCell2.setCellValue("100x");
			     XSSFCell lHeaderCell3 = lHeaderRow.createCell(3);
			     lHeaderCell3.setCellValue("10x");
			     XSSFCell lHeaderCell4 = lHeaderRow.createCell(4);
			     lHeaderCell4.setCellValue("1x");
				
				for(int j=2;j<163;j++) {   
				
				     XSSFRow lRow = mySheet.getRow(j);
				     XSSFCell lCellName = lRow.getCell(55);
				     XSSFCell lCell1 = lRow.getCell(58);
				     System.out.println(lCell1.getNumericCellValue());
				     XSSFCell lCell2 = lRow.getCell(59);
				     System.out.println(lCell2.getNumericCellValue());
				     XSSFCell lCell3 = lRow.getCell(60);
				     System.out.println(lCell3.getNumericCellValue());
				     XSSFCell lCell4 = lRow.getCell(61);
				     System.out.println(lCell4.getNumericCellValue());
				    
				     XSSFRow lOutRow = outsheet1.createRow(j);
				     System.out.println(j);
				     XSSFCell lOutCell0 = lOutRow.createCell(0);
				     lOutCell0.setCellValue(lCellName.getStringCellValue());
				     XSSFCell lOutCell1 = lOutRow.createCell(1);
				     lOutCell1.setCellValue(lCell1.getNumericCellValue());
				     XSSFCell lOutCell2 = lOutRow.createCell(2);
				     lOutCell2.setCellValue(lCell2.getNumericCellValue());
				     XSSFCell lOutCell3 = lOutRow.createCell(3);
				     lOutCell3.setCellValue(lCell3.getNumericCellValue());
				     XSSFCell lOutCell4 = lOutRow.createCell(4);
				     lOutCell4.setCellValue(lCell4.getNumericCellValue());
				     
				 
				     
					
				}
				for(int k=1;k<29;k++){
					System.out.println(k);
					 XSSFRow lControlRow = mySheet.getRow(128+k);
				     XSSFCell lControlCell1 = lControlRow.getCell(27);
				     XSSFCell lControlCell2 = lControlRow.getCell(28);
				     XSSFCell lControlCell3 = lControlRow.getCell(29);
				     XSSFCell lControlCell4 = lControlRow.getCell(30);
				     //XSSFCell lControlCell5 = lControlRow.getCell(31);
				     //XSSFCell lControlCell6 = lControlRow.getCell(32);
				     
				     
				     
				     XSSFRow lControlOutRow = outsheet2.createRow(k);
				     XSSFCell lControlOutCell1 = lControlOutRow.createCell(1);
				     lControlOutCell1.setCellValue(lControlCell1.getNumericCellValue());
				     XSSFCell lControlOutCell2 = lControlOutRow.createCell(2);
				     lControlOutCell2.setCellValue(lControlCell2.getNumericCellValue());
				     XSSFCell lControlOutCell3 = lControlOutRow.createCell(3);
				     lControlOutCell3.setCellValue(lControlCell3.getNumericCellValue());
				     XSSFCell lControlOutCell4 = lControlOutRow.createCell(4);
				     lControlOutCell4.setCellValue(lControlCell4.getNumericCellValue());
				     XSSFCell lControlOutCell5 = lControlOutRow.createCell(5);
				     lControlOutCell5.setCellValue(lControlCell5.getNumericCellValue());
				     XSSFCell lControlOutCell6 = lControlOutRow.createCell(6);
				     lControlOutCell6.setCellValue(lControlCell6.getNumericCellValue());
				}
				int lRowCount=1;
				for(int l=164;l<192;l++){
					System.out.println(l);
					XSSFRow lVehicleRow = mySheet.getRow(l);
					XSSFCell lVehicleCell = lVehicleRow.getCell(12);
					System.out.println(lVehicleRow.getCell(12).getNumericCellValue());
					 XSSFRow lVehicleOutRow = outsheet3.createRow(lRowCount);
				     XSSFCell lVehicleOutCell1 = lVehicleOutRow.createCell(0);
				     lVehicleOutCell1.setCellValue("DMSO"+ lRowCount);
				     XSSFCell lVehicleOutCell2 = lVehicleOutRow.createCell(1);
				     lVehicleOutCell2.setCellValue(lVehicleCell.getNumericCellValue());
					
					lRowCount++;
				}
				int lRowCount1=lRowCount;
				for(int l=164;l<192;l++){
					System.out.println(l);
					XSSFRow lVehicleRow = mySheet.getRow(l);
					XSSFCell lVehicleCell = lVehicleRow.getCell(13);
					
					 XSSFRow lVehicleOutRow = outsheet3.createRow(lRowCount1);
				     XSSFCell lVehicleOutCell1 = lVehicleOutRow.createCell(0);
				     lVehicleOutCell1.setCellValue("DMSO"+ lRowCount1);
				     XSSFCell lVehicleOutCell2 = lVehicleOutRow.createCell(1);
				     lVehicleOutCell2.setCellValue(lVehicleCell.getNumericCellValue());
					
				     lRowCount1++;
				}
				int lRowCount2=lRowCount1;
				for(int l=164;l<192;l++){
					XSSFRow lVehicleRow = mySheet.getRow(l);
					XSSFCell lVehicleCell = lVehicleRow.getCell(14);
					
					 XSSFRow lVehicleOutRow = outsheet3.createRow(lRowCount2);
				     XSSFCell lVehicleOutCell1 = lVehicleOutRow.createCell(0);
				     lVehicleOutCell1.setCellValue("DMSO"+ lRowCount2);
				     XSSFCell lVehicleOutCell2 = lVehicleOutRow.createCell(1);
				     lVehicleOutCell2.setCellValue(lVehicleCell.getNumericCellValue());
					
				     lRowCount2++;
				}
				int lRowCount3=lRowCount2;
				for(int l=164;l<192;l++){
					XSSFRow lVehicleRow = mySheet.getRow(l);
					XSSFCell lVehicleCell = lVehicleRow.getCell(15);
					
					 XSSFRow lVehicleOutRow = outsheet3.createRow(lRowCount3);
				     XSSFCell lVehicleOutCell1 = lVehicleOutRow.createCell(0);
				     lVehicleOutCell1.setCellValue("DMSO"+ lRowCount3);
				     XSSFCell lVehicleOutCell2 = lVehicleOutRow.createCell(1);
				     lVehicleOutCell2.setCellValue(lVehicleCell.getNumericCellValue());
					
				     lRowCount3++;
				}
			    myOutputWorkbook.write(lOutputFile);
				myOutputWorkbook.close();
				
			}
			
			myWorkBook.close();*/
		}catch(Exception e){
			System.out.println("Error"+e);
		}
		
		
	}
	
	

}
