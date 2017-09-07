package com.catapp.servlet;

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
				HashMap<String,String>lChemicalMap=new ChemData().getChemicalNames(lConn);
				request.setAttribute("chemicals", lChemicalMap);
				LinkedHashMap<String,String>lPhenoMap =  new ChemData().getNamesofInputs("phenotypes",lConn);
				LinkedHashMap<String,String>lAssayMap =  new ChemData().getNamesofInputs("assaynames",lConn);
				LinkedHashMap<String,String>lCellMap  =  new ChemData().getNamesofInputs("celllines",lConn);
				request.setAttribute("pheno", lPhenoMap);
				request.setAttribute("assay", lAssayMap);
				request.setAttribute("cell", lCellMap);
				
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