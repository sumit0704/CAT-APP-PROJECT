package com.catapp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.catapp.action.ChemData;
import com.catapp.connection.DBConnection;
import com.catapp.entity.User;

/**
 * Servlet implementation class SaveAndValidateConcaweData
 */
@WebServlet("/SaveAndValidateConcaweData")
public class SaveAndValidateConcaweData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "C:/Users/ssingh/serverfiles";
	public static final Logger logger = Logger.getLogger(SaveAndValidateConcaweData.class.toString());
	HashMap<String,Long> lAttributes	 		  = new HashMap<String,Long>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveAndValidateConcaweData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection lConn    	= null;
		
		String lCatAppId  	    = null;
		String lSample  	    = null;
		
		String lUploadPath		= null;
		String name				= null;
		User lUser 				= (User)request.getSession().getAttribute("user");
		try{
		lConn = new DBConnection().getConnection();
		if(ServletFileUpload.isMultipartContent(request)){
			
			List<FileItem> multiparts = new ServletFileUpload( new DiskFileItemFactory()).parseRequest(request);
			for(FileItem item : multiparts){
				
				if(item.isFormField()){
					 if(item.getFieldName().equals("cas")){
						lCatAppId=item.getString();
					}else if(item.getFieldName().equals("sample")){
						lSample=item.getString();
					}
					
				}
				if(!item.isFormField()){
					
					lUploadPath = UPLOAD_DIRECTORY+File.separator+"ConcaweFiles"+File.separator;
					/*File lFile = new File(lUploadPath);
					if(!lFile.exists()){
						lFile.mkdirs();
					}*/
				//	name = new File(item.getName()).getName();
					item.write( new File(lUploadPath + File.separator + lCatAppId));
				}
			}
			
			
		}
		File lFile =new File(lUploadPath + File.separator + lCatAppId);
		
		FileInputStream fis = new FileInputStream(lFile);
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
		
			HashMap<Integer,ArrayList<String>>lValidationOutputMap=validateUploadedFiles(lConn, myWorkBook,lCatAppId,lSample);
			HashMap<String,String>lCASNumberMap =  new ChemData().getCasNames(lConn,1l);
			
			HashMap<String,String>lConcaweMap =  new ChemData().getCasNames(lConn,2l);
			request.setAttribute("casnumber", lCASNumberMap);
			/*request.setAttribute("concawe", lConcaweMap);*/
			request.setAttribute("errormap", lValidationOutputMap);
			if(lValidationOutputMap.isEmpty()){

				String lReturnResponse=saveConcaweData(lConn, myWorkBook, lCatAppId, lSample);
				if(lReturnResponse=="Success"){
					
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/uploadAnalytical.jsp?success=1");
					rd.forward(request, response);
				}else{
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/uploadAnalytical.jsp?failure=2");
					rd.forward(request, response);
				}
				
			}else{
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/uploadAnalytical.jsp?failure=2");
				rd.forward(request, response);
			}
			
		
		}catch(Exception e){
			
		}
		
		
	}
	
	public HashMap<Integer,ArrayList<String>>validateUploadedFiles(Connection pConnection,XSSFWorkbook pWorkbook,String pCatAppId,String pSampleValue){
		
		HashMap<Integer,ArrayList<String>> lReturnMap = new HashMap<Integer,ArrayList<String>>();
		PreparedStatement lPstmt 					  = null;
		ResultSet lRst								  = null;
		Integer lErrorFlag							  = 0;						
		
		try{
			if(pWorkbook!=null){
				
				String lQuery="select entity_id,attribute From concawe_attribute_list where rowstate!=-1";
				lPstmt=pConnection.prepareStatement(lQuery);
				lRst=lPstmt.executeQuery();
				while(lRst.next()){
					lAttributes.put(lRst.getString(2),lRst.getLong(1));
				}
				XSSFSheet mySheet = pWorkbook.getSheetAt(0);
				Row row =mySheet.getRow(2);
				ArrayList<String>lErrorList =new ArrayList<String>();
				
				
				/**************************** Validation for Header Start ***********************************/
				
				for(int i=0;i<row.getLastCellNum()-1;i++){
					
					if(lAttributes.get(row.getCell(i).getStringCellValue())!=null && lAttributes.get(row.getCell(i).getStringCellValue())>0l){
						
					}else{
						lErrorFlag=1;
						lErrorList.add("Value at Column "+CellReference.convertNumToColString(i)+" in attribute header row  does not match with attribute list.");
						
					}
					
				}
				if(lErrorFlag==1){
					lReturnMap.put(lErrorFlag, lErrorList);
					
				}
				
				/**************************** Validation for Header End ***********************************/
				
				
				
				/**************************** Validation for Sample Start ***********************************/
				
				Row lSampleRow =mySheet.getRow(1);
				if(lSampleRow.getCell(0)!=null && lSampleRow.getCell(0).getStringCellValue()!=null
						&& !lSampleRow.getCell(0).getStringCellValue().equals(pSampleValue)){
					if(lReturnMap!=null && lReturnMap.get(1)!=null){
						
						ArrayList<String>lErrorList1=lReturnMap.get(1);
						lErrorList.add("Sample value does not match with the selection criteria.");
						lReturnMap.put(1, lErrorList1);
					}else{
						
						ArrayList<String>lErrorList1= new ArrayList<String>();
						lErrorList1.add("Sample value does not match with the selection criteria.");
						lReturnMap.put(1, lErrorList1);
					}
				}
				
				/**************************** Validation for Sample End ***********************************/
				
				
				
				/**************************** Validation for Cas Start ***********************************/
				
				if(lSampleRow.getCell(2)!=null && lSampleRow.getCell(2).getStringCellValue()!=null
						&& !lSampleRow.getCell(2).getStringCellValue().equals("CAS"+" "+pCatAppId)){
					if(lReturnMap!=null && lReturnMap.get(1)!=null){
						
						ArrayList<String>lErrorList1=lReturnMap.get(1);
						lErrorList.add("Cas Number does not match with the selection criteria.");
						lReturnMap.put(1, lErrorList1);
					}else{
						
						ArrayList<String>lErrorList1= new ArrayList<String>();
						lErrorList1.add("Cas Number does not match with the selection criteria.");
						lReturnMap.put(1, lErrorList1);
					}
				}
				
				/**************************** Validation for Cas End ***********************************/
				
				
				
				/**************************** Validation for Values Start ***********************************/
				
				    Double lFinalRowValue=0.0;
					for (int i=3;i<mySheet.getLastRowNum()-1;i++){
						
						Row datarow =mySheet.getRow(i);
						for(int j=0;j<datarow.getLastCellNum()-1;j++){
							
								if(j==0){
									String lCarbonNumber = null;
									CellType type = datarow.getCell(j).getCellTypeEnum();
									if(type==CellType.NUMERIC){
										
											if(datarow.getCell(j).getNumericCellValue()>100){
												if(lReturnMap!=null && lReturnMap.get(1)!=null){
													ArrayList<String>lErrorList1=lReturnMap.get(1);
													lErrorList.add("C-nr value can't be greater than 100 at row no.  "+i);
													lReturnMap.put(1, lErrorList1);
												}else{
													ArrayList<String>lErrorList1= new ArrayList<String>();
													lErrorList1.add("C-nr value can't be greater than 100 at row no.  "+i);
													lReturnMap.put(1, lErrorList1);
												}
											}
										}
									else if(type==CellType.STRING){
										if(datarow.getCell(j).getStringCellValue().indexOf("<")!=-1 ||
												datarow.getCell(j).getStringCellValue().indexOf(">")!=-1) {
											lCarbonNumber= datarow.getCell(j).getStringCellValue();
											lCarbonNumber=lCarbonNumber.replaceAll(">", "");
											lCarbonNumber=lCarbonNumber.replaceAll("<", "");
											if(Integer.getInteger(lCarbonNumber)>100){
												if(lReturnMap!=null && lReturnMap.get(1)!=null){
													ArrayList<String>lErrorList1=lReturnMap.get(1);
													lErrorList.add("C-nr value can't be greater than 100 at row no.  "+i);
													lReturnMap.put(1, lErrorList1);
												}else{
													ArrayList<String>lErrorList1= new ArrayList<String>();
													lErrorList1.add("C-nr value can't be greater than 100 at row no.  "+i);
													lReturnMap.put(1, lErrorList1);
												}
											}
										}
										
									}
								}
								else{
									if(datarow.getCell(j).getNumericCellValue()>0){
										lFinalRowValue=lFinalRowValue+datarow.getCell(j).getNumericCellValue();
										
									}
									
								}
							}
					}
					
					if(lFinalRowValue>100){
						if(lReturnMap!=null && lReturnMap.get(1)!=null){
							ArrayList<String>lErrorList1=lReturnMap.get(1);
							lErrorList.add("Summation of all values can't be greater than 100.");
							lReturnMap.put(1, lErrorList1);
						}else{
							ArrayList<String>lErrorList1= new ArrayList<String>();
							lErrorList1.add("Summation of all values can't be greater than 100.");
							lReturnMap.put(1, lErrorList1);
						}
					}
					/**************************** Validation for Values Ends ***********************************/
			
			}
			
		}catch(Exception e){
			logger.log(Level.INFO,"Error Occured while validating the excel files.",e);
		}
		finally{
			
			try{
				if(lErrorFlag==1){
					pWorkbook.close();
					File lFile = new File(UPLOAD_DIRECTORY+File.separator+"ConcaweFiles"+File.separator+pSampleValue);
					if(lFile.exists()){
						lFile.delete();
					}
				}
			}catch(Exception e){
				logger.log(Level.INFO,"Error Occured while deleting the files",e);
			}
		}
		return lReturnMap;
		
		
		
	}
	
	public String saveConcaweData(Connection pConnection,XSSFWorkbook pWorkbook,String pCatAppId,String pSampleValue){
		PreparedStatement lPstmt 					= null;
		String lQuery						        = null;
		ResultSet lRst			 				    = null;
		Long lHeaderId			                    = null;
		String lReturnResponse						= null;
		
		try{
			HashMap<String,String>lColumnAttributeMapping = new HashMap<String,String>();
			XSSFSheet lSheet = pWorkbook.getSheetAt(0);
			Row headerrow=lSheet.getRow(2);
			for(int j=0;j<headerrow.getLastCellNum();j++){
				lColumnAttributeMapping.put(CellReference.convertNumToColString(j), headerrow.getCell(j).getStringCellValue());
			}
			//////////////////////////Start of Save for Concawe Header //////////////////////////////
			
			StringBuilder  lHeaderQuery = new StringBuilder ("insert into concawe_values_header (sample,cas_number,logged_Date,logged_by,")
										  .append("last_updated_date,last_updated_by,is_Active,rowstate)")
										  .append(" values (?,?,?,?,?,?,?,?) ");
			
			lPstmt = pConnection.prepareStatement(lHeaderQuery.toString(),Statement.RETURN_GENERATED_KEYS);
			
			lPstmt.setString(1, pSampleValue);
			lPstmt.setString(2, pCatAppId);
			lPstmt.setLong(4, 1l);
			lPstmt.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
			lPstmt.setNull(5, java.sql.Types.TIMESTAMP);
			lPstmt.setNull(6, java.sql.Types.BIGINT);
			lPstmt.setString(7, "Y");
			lPstmt.setInt(8, 1);
			
			
			lPstmt.execute();
			lRst = lPstmt.getGeneratedKeys();
			if(lRst.next()){
				lHeaderId=lRst.getLong(1);
			}
			
			lPstmt.clearParameters();
			//////////////////////////End of Save for Concawe Header //////////////////////////////
			
			//////////////////////////  Start of Save for Concawe Details //////////////////////////////
			lQuery= "INSERT INTO concawe_values_details (attributeid,headerid,"
					+ " rowno,columNno,value,valuetype,logged_date,logged_by,last_updated_date,"
					+ " last_updated_by,is_active,rowstate) "
		            + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			lPstmt=pConnection.prepareStatement(lQuery);
		
		
			for(int i=3;i<=lSheet.getLastRowNum()-1;i++){
				Row lDataRow =lSheet.getRow(i);
				 for(int j=0;j<=lDataRow.getLastCellNum()-1;j++){
					 
					 if(lAttributes.get(lColumnAttributeMapping.get(CellReference.convertNumToColString(j)))!=null){
							 lPstmt.setLong(1, Long.valueOf(lAttributes.get(lColumnAttributeMapping.get(CellReference.convertNumToColString(j)))));
							  
						 }
						 lPstmt.setLong(2, lHeaderId);
						 lPstmt.setInt(3, i);
						 lPstmt.setString(4, CellReference.convertNumToColString(j));
						 CellType type = lDataRow.getCell(j).getCellTypeEnum();
						 if(type==CellType.STRING){
							 lPstmt.setString(5, lDataRow.getCell(j).getStringCellValue()+"");
						 }else if (type==CellType.NUMERIC){
							 lPstmt.setString(5, lDataRow.getCell(j).getNumericCellValue()+"");
						 }
						 if(j==lDataRow.getLastCellNum()-1){
							 lPstmt.setLong(1, 0l);
							 lPstmt.setInt(6, 2);
						 }else if(i==lSheet.getLastRowNum()-1){
							 lPstmt.setLong(1, 0l);
							 lPstmt.setInt(6, 3);
						 }else{
							
							 lPstmt.setInt(6, 1);
						 }
						 lPstmt.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
						 lPstmt.setLong(8, 1l);
						 lPstmt.setNull(9, java.sql.Types.TIMESTAMP);
						 lPstmt.setNull(10, java.sql.Types.BIGINT);
						 lPstmt.setString(11, "Y"); 
						 lPstmt.setInt(12, 1);
						 lPstmt.addBatch();
					 
					
				 }
				
			}
			int [] k=lPstmt.executeBatch();
			if(k.length>0){
				lReturnResponse="Success";
			}
			
		 //////////////////////////End of Save for Concawe Details //////////////////////////////
			
		}catch(Exception e){
			logger.log(Level.INFO,"Error Occured while saving the files",e);
			lReturnResponse="failure";
		}
		
		return lReturnResponse;
	}
	
}
