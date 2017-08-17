package com.catapp.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.catapp.action.ChemData;
import com.catapp.connection.DBConnection;


/**
 * Servlet implementation class DownloadFileServlet
 */
@WebServlet("/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
	public static final Logger logger = Logger.getLogger(DownloadFileServlet.class.toString());
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String [] lFiles					= request.getParameterValues("optradio");
		
		String lButtonClick					= null;
		PreparedStatement lPstmt 			= null;
		ResultSet lRst					    = null;
		Connection lConn 					= null;
		File lLocalFile 					= new File("C:\\Users\\ssingh\\serverfiles\\");
		String lFolderLoc 					= null;
		lConn=new DBConnection().getConnection();
		if(request.getParameter("download")!=null){
			lButtonClick="Download";
		}else if(request.getParameter("json")!=null){
			lButtonClick="json";
		}
		/*if(lButtonClick!=null && lButtonClick=="raw"){
			
		}else{
			String lFrontEndParameters = request.getParameter("cellline");
			Long lCellLine = Long.valueOf(lFrontEndParameters.split("-")[0]);
			Long lAssayType= Long.valueOf(lFrontEndParameters.split("-")[1]);
			Integer lTimePoint =4;
			Long lPhenotype= Long.valueOf(lFrontEndParameters.split("-")[2]);
			startCustomCode(response,lCellLine,lAssayType,lTimePoint,lPhenotype,lConn);
			return;
		}*/
		if(lLocalFile.exists()){
			lFolderLoc ="C:\\Users\\ssingh\\serverfiles\\";
		}else{
			// Write code as per the server //
		}
		try{
			if(lFiles.length>0){
				
				ArrayList<Long>lParameter = new ArrayList<Long>() ;
				for(int i=0;i<lFiles.length;i++){
						lParameter.add(Long.parseLong(lFiles[i]));
					
				}
				
				
				StringBuilder lBuild = new StringBuilder("select * From file_info where entity_id in (");
									   lBuild.append(new ChemData().generateQForparameter(lParameter.size()));
									   lBuild.append(")");
									   
				lPstmt = lConn.prepareStatement(lBuild.toString());
				
				for(int i=0;i<lParameter.size();i++){
					
					lPstmt.setLong(i+1, lParameter.get(i));
					
				}
				
				lRst=lPstmt.executeQuery();
				ArrayList<String>lFileFromServer = new ArrayList<String>();
				String lFileName ="";
				String lFileType ="";
				while(lRst.next()){
					 lFileName = lRst.getString("file_name");
					//lFileName ="test";
					lFileType = lRst.getString("file_type");
					String lCellLine = lRst.getString("cell_line_id");
					//String lPlate =lRst.getString("plate_id");
					lFileFromServer.add(lFolderLoc+lCellLine+"\\"+lFileName);
				}
				
				if(lFileFromServer.size()>0 && lFileFromServer.size()==1){
					
					if(lButtonClick!=null && lButtonClick=="Download"){
						response.setHeader("Content-disposition","attachment; filename="+ lFileName);
						response.setContentType(lFileType);
						
						OutputStream out = response.getOutputStream();
						File lMyFile = new File(lFileFromServer.get(0));
						// FileReader lReader = new FileReader(lMyFile);
						FileInputStream in = new FileInputStream(lMyFile);
						byte[] buffer = new byte[4096];
						int length;
						while ((length = in.read(buffer)) > 0){
							out.write(buffer, 0, length);
						}
						in.close();
						out.flush();
						
					}else{
						if(lFileType.equals("xls") ||  lFileType.equals("xlsx")){
							FileInputStream inp = new FileInputStream( lFileFromServer.get(0) );
							Workbook workbook =WorkbookFactory.create(inp);

							// Get the first Sheet.
							Sheet sheet = workbook.getSheetAt( 0 );

							JSONObject json = new JSONObject();

						    // Iterate through the rows.
						    JSONArray rows = new JSONArray();
						    for ( Iterator<Row> rowsIT = sheet.rowIterator(); rowsIT.hasNext(); )
						    {
						        Row row = rowsIT.next();
						        JSONObject jRow = new JSONObject();

						        // Iterate through the cells.
						        JSONArray cells = new JSONArray();
						        for ( Iterator<Cell> cellsIT = row.cellIterator(); cellsIT.hasNext(); )
						        {
						            Cell cell = cellsIT.next();
						            cells.add( cell.getStringCellValue() );
						        }
						        jRow.put( "cell", cells );
						        rows.add( jRow );
						    }

						    // Create the JSON.
						    json.put( "rows", rows );
							    workbook.close();

							// Get the JSON text.
							    
							    response.setHeader("Content-disposition","attachment; filename="+ lFileName+".json");
								response.setContentType(lFileType);
								File lJsonFile =new File ("C:/Users/CATAPP/serverfiles/NewJson.txt");
								lJsonFile.createNewFile();
								FileWriter lWriter = new FileWriter("C:/Users/CATAPP/serverfiles/NewJson.txt");
								lWriter.write(json.toJSONString());
								OutputStream out = response.getOutputStream();
								File lMyFile = new File("C:/Users/CATAPP/serverfiles/NewJson.txt");
								// FileReader lReader = new FileReader(lMyFile);
								FileInputStream in = new FileInputStream(lMyFile);
								byte[] buffer = new byte[4096];
								int length;
								while ((length = in.read(buffer)) > 0){
									out.write(buffer, 0, length);
								}
								in.close();
								out.flush();
								lWriter.close();
								File lToDelete = new File("C:/Users/CATAPP/serverfiles/NewJson.txt");
								lToDelete.delete();
								
						}
					}
					 
					
				}else{
					 ArrayList<File>files = new ArrayList<File>();
					for(int i=0;i<lFileFromServer.size();i++){
						File lTemp = new File(lFileFromServer.get(i));
						
						files.add(lTemp);
					}
					response.setContentType("Content-type: text/zip");
					response.setHeader("Content-Disposition",
							"attachment; filename=mytest.zip");

					

					OutputStream out = response.getOutputStream();
					ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out));

					for (File file : files) {
						FileInputStream fis = null;
						try {
							fis = new FileInputStream(file);

						} catch (FileNotFoundException fnfe) {
							zos.write(("ERRORld not find file " + file.getName())
									.getBytes());
							zos.closeEntry();
							System.out.println("Couldfind file "
									+ file.getAbsolutePath());
							continue;
						}

						BufferedInputStream fif = new BufferedInputStream(fis);
						zos.putNextEntry(new ZipEntry(file.getName()));

						int data = 0;
						while ((data = fif.read()) != -1) {
							zos.write(data);
						}
						fif.close();

						zos.closeEntry();
						System.out.println("Finishedng file " + file.getName());
					}
					zos.close();
				}
		
			}
					
		}
		catch(Exception e){	
			logger.error("Error Occured While downloading existing file------>downloadfileforservlet.",e);
			
		}finally{
			if(lConn!=null){
				try{
					lConn.close();
				}catch(Exception e){
					
				}
			}
			if(lPstmt!=null){
				try{
					lPstmt.close();
				}catch(Exception e){
					
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	protected void startCustomCode(HttpServletResponse response,Long pCellLine,Long pAssay,Integer pTimePoint, Long pPhenotype, Connection pConn){
		
		PreparedStatement lPstmt         = null;
		ResultSet         lRst		     = null;
		//HashMap<Integer, HashMap<Integer,Double>>pValueAddressMap = new HashMap<Integer, HashMap<Integer,Double>>();
		
		try{
			StringBuilder lQuery = new StringBuilder("select * from plate_chemical_denormalized_value_mapping where ")
								   .append("cellline_id=? and assay=? and timepoint=? and phenotype=? and rowstate!=-1");
			lPstmt= pConn.prepareStatement(lQuery.toString());
			lPstmt.setLong(1, pCellLine);
			lPstmt.setLong(2, pAssay);
			lPstmt.setInt(3, pTimePoint);
			lPstmt.setLong(4, pPhenotype);
			
			lRst = lPstmt.executeQuery();
			HashMap<Integer,HashMap<Integer, HashMap<Integer,Double>>> lConcMap = new HashMap<Integer,HashMap<Integer, HashMap<Integer,Double>>>();
			while(lRst.next()){
				
				if(lConcMap.containsKey(lRst.getInt("plateconc"))){
					HashMap<Integer, HashMap<Integer,Double>>pValueAddressMap= lConcMap.get(lRst.getInt("plateconc"));
					HashMap<Integer,Double>lEndValueMap = new HashMap<Integer,Double>();
					if(pValueAddressMap.containsKey(lRst.getInt("rowno"))){
						lEndValueMap = pValueAddressMap.get(lRst.getInt("rowno"));
						lEndValueMap.put(lRst.getInt("relative_pos_in_row"), lRst.getDouble("value"));
					}else{
						lEndValueMap.put(lRst.getInt("relative_pos_in_row"), lRst.getDouble("value"));
						pValueAddressMap.put(lRst.getInt("rowno"), lEndValueMap);
						
					}
				}else{
					HashMap<Integer,Double> lNewEndValMap = new HashMap<Integer,Double>();
					lNewEndValMap.put(lRst.getInt("relative_pos_in_row"), lRst.getDouble("value"));
					HashMap<Integer, HashMap<Integer,Double>>lRowValueMap = new HashMap<Integer, HashMap<Integer,Double>>();
					lRowValueMap.put(lRst.getInt("rowno"),lNewEndValMap);
					lConcMap.put(lRst.getInt("plateconc"), lRowValueMap);
				}
				
				/*
				if (pValueAddressMap.containsKey(lRst.getInt("rowno"))){
					HashMap<Integer,Double> lExistingMap=pValueAddressMap.get(lRst.getInt("rowno"));
					lExistingMap.put(lRst.getInt("relative_pos_in_row"), lRst.getDouble("value"));
				}else{
					HashMap<Integer,Double>lRelativeMap = new HashMap<Integer,Double>();
					lRelativeMap.put(lRst.getInt("relative_pos_in_row"), lRst.getDouble("value"));
					pValueAddressMap.put(lRst.getInt("rowno"), lRelativeMap);
				}
				if(lConcMap.containsKey(lRst.getInt("plateconc"))){
					HashMap<Integer, HashMap<Integer,Double>> lExt =lConcMap.get(lRst.getInt("plateconc"));
					HashMap<Integer,Double> lValueMap = new HashMap<Integer,Double>();
					lValueMap.put(lRst.getInt("relative_pos_in_row"), lRst.getDouble("value"));
					lExt.put(lRst.getInt("rowno"), lValueMap);
					
					
				}else{
					lConcMap.put(lRst.getInt("plateconc"), pValueAddressMap);
				}
			*/}
			
			//////////////////////// Template Read Start //////////////////////////////////
			
			XSSFWorkbook lWorkBook = new XSSFWorkbook(new FileInputStream("C:\\Users\\ssingh\\serverfiles\\templates\\Cardiomyocytes.xlsx"));       
			XSSFFormulaEvaluator.evaluateAllFormulaCells(lWorkBook);
			lWorkBook.setForceFormulaRecalculation(true);
	       
	        XSSFSheet lWorkingSheet = lWorkBook.getSheetAt(0);
	        
	        ////////////////////////Template Read End ////////////////////////////////////
	        
	        //////////////////////////////// Iteration of HashMap Starts ///////////////////////////////
	        
	        for (Integer lPlateConc : lConcMap.keySet()){
	        	HashMap<Integer, HashMap<Integer,Double>>lInnerMap=lConcMap.get(lPlateConc);
	        	Integer lStartRow =0;
	        	Integer lStartColumn =2;
	        	for(Integer row : lInnerMap.keySet()){
	        		if(lPlateConc==4){
	        			lStartRow =23;
	        		}else if (lPlateConc==3){
	        			lStartRow =49;
	        		}else if (lPlateConc==2){
	        			lStartRow =75;
	        		}else if (lPlateConc==1){
	        			lStartRow =101;
	        		}
	        		
	        		HashMap <Integer, Double>lFinalValue = lInnerMap.get(row) ;
	        		 for(Integer pos: lFinalValue.keySet()){
	        			 if(row<15){
	        				 Row iRow = lWorkingSheet.getRow(lStartRow+row);
	        				 if(pos<23){
	        					 Cell iCell = iRow.createCell(lStartColumn+pos);
	        					 iCell.setCellValue(lFinalValue.get(pos));
	        				 }
	        			}
	        		 }
	        	}
	        	
	        	
	        }
	        
	         /////////////////////////////// Iteration of HashMap Ends /////////////////////////////////
	        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
	        lWorkBook.write(outByteStream);
	        byte [] outArray = outByteStream.toByteArray();
	        response.setContentType("application/ms-excel");
	        response.setContentLength(outArray.length);
	        response.setHeader("Expires:", "0"); // eliminates browser caching
	        response.setHeader("Content-Disposition", "attachment; filename=Cardio.xls");
	        OutputStream outStream = response.getOutputStream();
	        outStream.write(outArray);
	        outStream.flush();
	        outByteStream.close();
	        lWorkBook.close();
	       
		}catch (Exception e){
			logger.error("Error Occured While downloading custom file------>startCustomCode.",e);
		}
		finally{
			try{
				if(pConn!=null){
					pConn.close();
				}
				
			}catch(Exception e){
				logger.error("Error Occured While closing connection------>startCustomCode.",e);
			}
		}
	 }
}
