package com.catapp.servlet;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.catapp.action.ChemData;
import com.catapp.action.SaveExceltoDB;
import com.catapp.connection.DBConnection;
import com.catapp.entity.User;


/**
 * Servlet implementation class SaveFileFormServlet
 */
@WebServlet("/SaveFileFormServlet")
public class SaveFileFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private final String UPLOAD_DIRECTORY = "C:/Users/ssingh/serverfiles";

	public static final Logger logger = Logger.getLogger(SaveFileFormServlet.class.toString());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveFileFormServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		

		String lCellLine  = request.getParameter("cellline");		
		String lAssay = request.getParameter("CM_assay_select");
		String lTimePoint = request.getParameter("timepoint");
		String lDilution  = request.getParameter("dilution");
		// request.getParameter("raw")
		String lDescription = "";
		String original_name = "";
		String lFileExtension = "";
		String lFileName  = "";
		String lUploadPath = "";
		String lFileSaveParamter =  "";
		String lPhenotype =null;
		Connection lConn  = null;
		User lUser =(User)request.getSession().getAttribute("user");
		File lFiletoDelete =null;
		int file_category=0;
		boolean lExistsflag=false;
		//String lDilutionInfo = request.getParameter("form-Plate1");
		try{
			lConn = new DBConnection().getConnection();
			 
			if(ServletFileUpload.isMultipartContent(request)){		
				List<FileItem> multiparts = new ServletFileUpload( new DiskFileItemFactory()).parseRequest(request);
				for(FileItem item : multiparts){
					
					logger.info("Inside For Loop");
					if(item.isFormField()){
						if(item.getFieldName().equals("cellline")){
							lCellLine=item.getString();
						}else if(item.getFieldName().equals("assay")){
							lAssay=item.getString();
						}else if(item.getFieldName().equals("timepoints_4_select")){
							lTimePoint=item.getString();
						}else if(item.getFieldName().equals("dilution")){
							lDilution=item.getString();
							//lDilution=null;
						}else if(item.getFieldName().equals("desc")){
							lDescription=item.getString();
						}
						else if(item.getFieldName().equals("fileRadio")){
							lFileSaveParamter=item.getString();
							
						}
						else if(item.getFieldName().equals("phenotype")){
							lPhenotype=item.getString();
							
						}
						/*else if(item.getFieldName().equals("processed")){
							lFileSaveParamter=item.getString();
						}*/
					}
					if(lFileSaveParamter!=null && lFileSaveParamter.equals("processed") ){
						file_category=3;
					}else if(lFileSaveParamter!=null && lFileSaveParamter.equals("pod") ){
						file_category=2;
					}else{
						file_category=1;
					}
					
					
					if(!item.isFormField()){
						original_name = new File(item.getName()).getName();	
						Pattern p = Pattern.compile("[.!?]");
					    Matcher matcher = p.matcher(original_name);
					    int count = 0;
					    while(matcher.find()) {
					        count++;
					    }
					    
					    System.out.println("Count : " + count);
						if(original_name!=null){
							if(original_name.indexOf(".")!=-1){
								lFileExtension =original_name.split("\\.")[count];			// file extension
							}
						}	
						if(lDilution==null){
							lDilution="00";
						}
						
						String modified_file_name = original_name.replaceAll(" ", "-");
						lUploadPath = "C:\\Users\\ssingh\\serverfiles\\" + lCellLine;	
						if(file_category==2){
							lUploadPath="C:\\Users\\ssingh\\serverfiles\\podfiles";
							lFileName = lCellLine + "_" + lAssay + "_" + new ChemData().getTimePoints().get(Long.parseLong(lTimePoint)) + "_" + lPhenotype;
							
						}else if(file_category==3){
							lUploadPath="C:\\Users\\ssingh\\serverfiles\\processedfiles";
							lFileName=lCellLine+"_"+"processed";
							
						}else{
							lUploadPath="C:\\Users\\ssingh\\serverfiles\\" + lCellLine;
							lFileName = lCellLine + "_" + lAssay + "_" + lTimePoint + "_" + 
									lDilution + "_" + modified_file_name;
						}
						
						item.write( new File(lUploadPath + File.separator + lFileName));
						File lFile1 = new File(lUploadPath + File.separator + modified_file_name);
						lFile1.renameTo(new File(lUploadPath + File.separator + lFileName+"."+lFileExtension));
						lFiletoDelete=new File(lUploadPath + File.separator + lFileName);
					
					}
				
				}
			}	
			
			 lExistsflag=cmsCheckFileInDB(lCellLine, lAssay, lPhenotype, lTimePoint, lDilution, file_category, lConn);
			 
			if(!lExistsflag){
				
			
			String Path_for_SQL="";
			if(file_category==2){
				Path_for_SQL="C:/Users/ssingh/serverfiles/podfiles";
				new SaveExceltoDB().saveExcelDataToDb(lCellLine,lAssay,lTimePoint,lPhenotype,lFiletoDelete, lConn);
				
			}else if(file_category==3){
				lAssay="All";
				lPhenotype="All";
				lDilution="All";
				lTimePoint="1000";
				Path_for_SQL="C:/Users/ssingh/serverfiles/processedfiles";
			}else{
				
				Path_for_SQL="C:/Users/ssingh/serverfiles/" + lCellLine;
			}
			String insert_record_str = "INSERT INTO file_info (cell_line_id, assay_type, phenotype_id, " +
					"timepoint, Dilution, description, Original_name, file_name, file_type, file_path,file_category) " + 
					"VALUES ('" + lCellLine + "', '" + lAssay + "','" + lPhenotype + "', '" + new ChemData().getTimePoints().get(Long.parseLong(lTimePoint))  + "', '" + 
					lDilution + "', '" + lDescription  + "', '" + original_name + "', '" + lFileName +"."+lFileExtension+ "', '" +
					lFileExtension + "', '" + Path_for_SQL + "','" + file_category + "')";
			Save_file_info2DB(insert_record_str, lConn);
			
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Upload?success=1");
		    rd.forward(request, response);
			}else{
				File lFiletoDelete1=new File(lUploadPath + File.separator + lFileName);
				lFiletoDelete1.delete();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Upload?exists=1");
			    rd.forward(request, response);
			}
			///// *************************** Data save started ************************************/////
		   
		}catch(Exception e){
			System.out.println("SaveFileFormServlet Zz" + " \n");
			  PrintWriter out = response.getWriter();
			  out.println("Sorry, errors occured while uploading the file.");	// print directly to the web page.
			logger.log(Level.INFO,"Error Occured while uploading the file.",e);
			
		}
		
		finally{
			try{
				System.out.println("SaveFileFormServlet before close" + " \n");
				lConn.close();
			}catch (Exception e1){
				System.out.println("SaveFileFormServlet after close" + " \n");
				logger.log(Level.INFO,"Error Occured while closing the connection.",e1);
			}
		}
		
	}

	public boolean cmsCheckFileInDB(String pCell,String pAssay,String pPheno,String pTime,String pDil,int pFilecat,Connection pConnection){
		boolean lExistsFlag      = false;
		PreparedStatement lPstmt = null;
		ResultSet lRst 			 = null;
		try{
			StringBuilder lQueryBuilder= new StringBuilder( "select * From file_info" );
			if(pFilecat==1){
				lQueryBuilder.append(" where cell_line_id=? and assay_type=? and timepoint=? and dilution=? and file_category=1 ");
				lPstmt=pConnection.prepareStatement(lQueryBuilder.toString());
				lPstmt.setString(1, pCell);
				lPstmt.setString(2, pAssay);
				lPstmt.setString(3, new ChemData().getTimePoints().get(Long.parseLong(pTime)));
				lPstmt.setString(4, pDil);
		
			}else if(pFilecat==2){
				lQueryBuilder.append(" where cell_line_id=? and assay_type=? and timepoint=? and phenotype_id=? and file_category=2 ");
				lPstmt=pConnection.prepareStatement(lQueryBuilder.toString());
				lPstmt.setString(1, pCell);
				lPstmt.setString(2, pAssay);
				lPstmt.setString(3, pTime);
				lPstmt.setString(4, pPheno);
			}
			else if(pFilecat==3){
				lQueryBuilder.append(" where cell_line_id=? and file_category=3 ");
				lPstmt=pConnection.prepareStatement(lQueryBuilder.toString());
				lPstmt.setString(1, pCell);
				
			}
			
			lRst= lPstmt.executeQuery();
			while(lRst.next()){
				lExistsFlag=true;
			}
			
		}catch(Exception e){
			logger.log(Level.INFO, "validation error", e);
		}
		
		return lExistsFlag;
	}
	
	public boolean Save_file_info2DB(String insert_record_str, Connection pConnection){
		boolean lExistsFlag      = false;
		ResultSet lRst 			 = null;
		
		System.out.println("SaveFileFormServlet before D");
		try{
						
			java.sql.Statement statement = pConnection.createStatement();
			// System.out.println("SaveFileFormServlet before D2 in save, insert string: ");
			// System.out.println(insert_record_str);
			statement.executeUpdate(insert_record_str);
	
		}catch(Exception e){
			logger.log(Level.INFO, "validation error", e);
		}
		
		return lExistsFlag;
	}
}