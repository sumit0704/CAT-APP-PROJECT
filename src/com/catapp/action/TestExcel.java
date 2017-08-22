package com.catapp.action;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestExcel {
	
	public static void main (String[] args){
		getExcel();
	}
	public static void getExcel(){
		try{
			HashMap<Double,String>lConfigMap =new HashMap<Double,String>();
			File myConfigFile = new File("C://Users/ssingh/Desktop/Config Ids_System_information_report.xlsx");
			File myReportFile = new File("C://Users/ssingh/Desktop/report.system_information.csv");
			BufferedReader br = new BufferedReader(new FileReader(myReportFile));
			FileInputStream fis1 = new FileInputStream(myConfigFile);
			XSSFWorkbook myConfigWorkBook = new XSSFWorkbook (fis1);
			XSSFSheet mySheet = myConfigWorkBook.getSheetAt(0);
			for(int i=1;i<=mySheet.getLastRowNum();i++){
				XSSFRow iRow = mySheet.getRow(i);
				if(iRow.getCell(0)!=null){
					lConfigMap.put(iRow.getCell(0).getNumericCellValue(), iRow.getCell(1).getStringCellValue());
				}
			}
			String line;
			String line1=null;
			String line2=null;
			int i=0;
		    while ((line = br.readLine()) != null) {
		    	if(i<2){
		    		if(i==0){
		    			line1=line;
		    		}
		    		else if (i==1){
		    			line2=line;
		    		}
		    		i++;
		    	}
		      }
		    int lCommaCount=0;
		    for(int j=0;j<line1.length();j++){
		    	if(line1.charAt(j)==','){
		    		lCommaCount++;
		    	}
		    }
		    for(int k=1;k<lCommaCount;k++){
		    	String lSecondValue=line1.split(",")[k].replace("\"", "");
		    	Double lSecondKey=0.0;
		    	if(line2.split(",")[k].trim().length()>0){
		    		String lTempvar =line2.split(",")[k].replace("\"", "");
		    		if(lTempvar.trim().length()>0){
		    			lSecondKey=Double.parseDouble(lTempvar);
		    		}
		    	}
		    	if(lConfigMap.get(lSecondKey)!=null && lConfigMap.get(lSecondKey).trim().length()>0
		    			 && lSecondValue.trim().length()>0 
		    			&& lConfigMap.get(lSecondKey).equals(lSecondValue) ){
		    		System.out.println(" Value Matched "+ lSecondValue+"-"+lSecondKey);
		    	}
		    }
			
		}catch(IOException e){
			//////
		}
	}
}
