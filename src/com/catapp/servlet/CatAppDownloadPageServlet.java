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

@WebServlet(value="/CatAppDownloadPageServlet")
public class CatAppDownloadPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
		
		Connection lConn= null; 
		try{
			
			lConn= new DBConnection().getConnection();
			HashMap <String,String>lChemicalMap=new ChemData().getChemicalNames(lConn);
			LinkedHashMap <String,String>lPhenoMap =  new ChemData().getNamesofInputs("phenotypes",lConn);
			LinkedHashMap <String,String>lAssayMap =  new ChemData().getNamesofInputs("assaynames",lConn);
			LinkedHashMap <String,String>lCellMap  =  new ChemData().getNamesofInputs("celllines",lConn);
			HashMap<Long,String>lTimMap   =  new ChemData().getTimePoints();
			System.out.println("lPhenoMap"+lPhenoMap.size()+"lAssayMap::"+lAssayMap.size()+
					"lCellMap::"+lCellMap.size()+"lTimMap::"+lTimMap.size());
			request.setAttribute("chemicals", lChemicalMap);
			request.setAttribute("pheno", lPhenoMap);
			request.setAttribute("assay", lAssayMap);
			request.setAttribute("cell", lCellMap);
			request.setAttribute("time", lTimMap);
			HttpSession session=request.getSession(false);
			if(session!=null){
			request.getRequestDispatcher("/WEB-INF/catAppDownloadPage.jsp").include(request, response);
				}
			else{
				request.setAttribute("error","Current session is lost. Please log in");
				request.getRequestDispatcher("/WEB-INF/index.jsp").include(request, response);  
				}
			} catch(Exception e){
			
		}finally{
			try{
				lConn.close();
			}catch(Exception e1){
				
			}
		}
		
	      }
		
}
