package com.catapp.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

import com.catapp.action.ChemData;
import com.catapp.connection.DBConnection;

@WebServlet(value="/AnalysisPageAction")
public class AnalysisPageAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
		/*response.setContentType("text/html");  
		
		PrintWriter out = response.getWriter();
		//session.getAttribute("user");
        //PrintWriter out=response.getWriter();
		System.out.println("Session is"+session);*/
		
		HttpSession session=request.getSession(false);
		Connection lConn   = null;
		try{
			if(session!=null){
				lConn=new DBConnection().getConnection();
				HashMap<String,String>lChemicalMap=new ChemData().getCasNames(lConn, 3L);
				//HashMap<String,String>lChemicalPropertyMap=new ChemData().getChemicalProperties(lConn);
				request.setAttribute("chemicals", lChemicalMap);
				LinkedHashMap<String,String>lPhenoMap =  new ChemData().getNamesofInputs("phenotypes",lConn);
				LinkedHashMap<String,String>lAssayMap =  new ChemData().getNamesofInputs("assaynames",lConn);
				LinkedHashMap<String,String>lCellMap  =  new ChemData().getNamesofInputs("celllines",lConn);
				HashMap<Long,String>lTimMap   =  new ChemData().getTimePoints();
				lTimMap.remove(0l);
				request.setAttribute("time", lTimMap);

				request.setAttribute("pheno", lPhenoMap);
				//request.setAttribute("cp", lChemicalPropertyMap);
				request.setAttribute("assay", lAssayMap);
				request.setAttribute("cell", lCellMap);
				
				
			/*	File lFile1 = new File("C:\\Users\\ssingh\\Desktop\\Cardio_Total_Cell_24h\\Figs\\generated_graph.png");
				if(lFile1.exists()){
					lFile1.delete();
				}
				File lFile = new File("C:\\Users\\ssingh\\Desktop\\Cardio_Total_Cell_24h\\no_data.png");
				File lFile2 = new File("C:\\Users\\ssingh\\Desktop\\Cardio_Total_Cell_24h\\Figs\\generated_graph.png");
				FileUtils.copyFile(lFile, lFile2);*/
				request.getServletContext().getRequestDispatcher("/WEB-INF/analysisPage.jsp").forward(request, response);
				
			
			
			}
		
		else{
			request.setAttribute("error","Current session is lost. Please log in");
			request.getRequestDispatcher("/LoadDataForHome").include(request, response);  
			}
		}catch(Exception e){
			
		}
		finally{
			try{
				if(lConn!=null){
					lConn.close();
				}
			}catch(Exception e){
				
			}
		}
	      }
		
}