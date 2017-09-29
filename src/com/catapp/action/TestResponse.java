package com.catapp.action;

import java.io.FileWriter;

public class TestResponse {
	
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "CellLine,Assay,Time Point,Phenotype";
	
	public static void main (String[] args){
		
		String lFileName="C:\\Users\\ssingh\\Desktop\\Cardio_Total_Cell_24h\\Query Parameters.csv";
		try{
			
			FileWriter fileWriter = null;
			fileWriter = new FileWriter(lFileName);
			 fileWriter.append(FILE_HEADER.toString());
			 fileWriter.append(NEW_LINE_SEPARATOR);
			 fileWriter.append("CM");
			 fileWriter.append(COMMA_DELIMITER);
			 fileWriter.append("Ca2");
			 fileWriter.append(COMMA_DELIMITER);
			 fileWriter.append("90min");
			 fileWriter.append(COMMA_DELIMITER);
			 fileWriter.append("PF");
			 fileWriter.flush();
			 fileWriter.close();
			String R_command = "cmd.exe /c C:\\\"Program Files\"\\R\\R-2.15.1\\bin\\Rscript C:\\Users\\ssingh\\Desktop\\";
			R_command +=  "Cardio_Total_Cell_24h" + "\\Script_Data.R";
			shellCommands obj = new shellCommands();
			String output = obj.executeCommand(R_command);
			System.out.println(output);
		}catch(Exception e){
			
		}
	}

}
