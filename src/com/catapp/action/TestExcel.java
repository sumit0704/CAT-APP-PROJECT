package com.catapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestExcel {
	
	
	public static void main (String[] args){
		getExcel();
	}
	public static void getExcel(){
		try{
			
			File myFile = new File("C://Users/ssingh/Desktop/Average Peak Amplitude.xlsx");
			FileInputStream fis = new FileInputStream(myFile);
			XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			Iterator<Row> rowIterator = mySheet.iterator();
			while (rowIterator.hasNext()){
				Row row = rowIterator.next();
				 Iterator<Cell> cellIterator = row.cellIterator();
	                while (cellIterator.hasNext()) {
	                	Cell cell = cellIterator.next();
	                }
			}
			
		}catch(Exception e){
			
		}
	}
}
