package com.catapp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;

/**
 * Servlet implementation class DownloadConcaweAsIs
 */
@WebServlet("/DownloadConcaweAsIs")
public class DownloadConcaweAsIs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadConcaweAsIs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String [] lFiles					= request.getParameterValues("cas");
		String lFilePath					="C:\\Users\\ssingh\\serverfiles\\ConcaweFiles";
		try{
			if(lFiles.length>0){
				for(int i=0;i<lFiles.length;i++){
					File lFile = new File(lFilePath+"\\"+lFiles[i]+".xlsx");
					
					response.setHeader("Content-disposition","attachment; filename="+ lFile.getName());
					response.setContentType("xlsx");
					OutputStream out = response.getOutputStream();
			
					FileInputStream in = new FileInputStream(lFile);
					byte[] buffer = new byte[4096];
					int length;
					while ((length = in.read(buffer)) > 0){
						out.write(buffer, 0, length);
					}
					in.close();
					out.flush();
				}
				
			}
		}catch(Exception e){
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
