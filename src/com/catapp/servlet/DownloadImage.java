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

/**
 * Servlet implementation class DownloadImage
 */
@WebServlet("/DownloadImage")
public class DownloadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DownloadImage() {
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
		
		// TODO Auto-generated method stub
		try{
			File lLocalFile = new File("C:\\Users\\CATAPP\\Logic\\Figs\\generated_graph1.png");
			
		if(lLocalFile.exists()){
			response.setHeader("Content-disposition","attachment; filename="+ "Generated_Graph");
			response.setContentType("png");
			
			OutputStream out = response.getOutputStream();
			File lMyFile = new File("C:\\Users\\CATAPP\\Logic\\Figs\\generated_graph1.png");
			FileInputStream in = new FileInputStream(lMyFile);
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0){
				out.write(buffer, 0, length);
			}
			in.close();
			out.flush();
		}
			
		}catch(Exception e){
		}
	}

}
