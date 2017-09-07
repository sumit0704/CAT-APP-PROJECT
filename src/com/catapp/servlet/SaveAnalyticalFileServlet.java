package com.catapp.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

import com.catapp.action.SaveExceltoDB;
import com.catapp.connection.DBConnection;
import com.catapp.entity.User;

/**
 * Servlet implementation class SaveAnalyticalFileServlet
 */
@WebServlet("/saveAnalyticalFile")
public class SaveAnalyticalFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger logger = Logger.getLogger(SaveAnalyticalFileServlet.class.toString());


       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveAnalyticalFileServlet() {
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
		
		String original_name = "";
		String lFileExtension = "";
		String lFileName  = "";
		String lUploadPath = "";
		String casNumber =  "";
		String sampleNumber =  "";

		Connection lConn  = null;
		User lUser =(User)request.getSession().getAttribute("user");
		File lFiletoDelete =null;
		
		try{
			lConn = new DBConnection().getConnection();
			 
			if(ServletFileUpload.isMultipartContent(request)){		
				List<FileItem> multiparts = new ServletFileUpload( new DiskFileItemFactory()).parseRequest(request);
				for(FileItem item : multiparts){
					logger.info("Inside For Loop");
					if(item.isFormField()){
						if(item.getFieldName().equals("casNumber")){
							casNumber=item.getString();
						}else if(item.getFieldName().equals("sampleNumber")){
							sampleNumber=item.getString();
						}
						
					}
					
					if(!item.isFormField()){
						original_name = new File(item.getName()).getName();		// file name
						if(original_name!=null){
							if(original_name.indexOf(".")!=-1){
								lFileExtension =original_name.split("\\.")[1];			// file extension
							}
						}	
						
						
						String modified_file_name = original_name.replaceAll(" ", "-");
						lUploadPath = "C:\\Users\\sharm\\";	
						lFileName = casNumber + "_" + sampleNumber + "_" +modified_file_name;
						System.out.println("lFileName::"+lFileName);
						item.write( new File(lUploadPath + File.separator + lFileName));
						/*File lFile1 = new File(lUploadPath + File.separator + modified_file_name);
						lFile1.renameTo(new File(lUploadPath + File.separator + lFileName+"."+lFileExtension));
						lFiletoDelete=new File(lUploadPath + File.separator + lFileName);*/
					
					}
				}
			}	
			
				
				//new SaveExceltoDB().saveExcelDataToDb(casNumber,sampleNumber,lFiletoDelete, lConn);
				
			
			
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/uploadAnalytical?success=1");
		    rd.forward(request, response);
			
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

}
