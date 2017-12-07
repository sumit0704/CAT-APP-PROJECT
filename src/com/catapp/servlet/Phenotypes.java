package com.catapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catapp.connection.DBConnection;

/**
 * Servlet implementation class Phenotypes
 */
@WebServlet("/Phenotypes")
public class Phenotypes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Phenotypes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String lCellLine 		  = 			request.getParameter("lCM");
		String lAssay 			  =             request.getParameter("lAssay");
		Connection lConn	   	  = 			null;
		PreparedStatement lPstmt  = 	        null;
		ResultSet lRst            = 			null;
		
		try{
			lConn=new DBConnection().getConnection();
			StringBuilder lQuery = new StringBuilder(" select d.name,d.tag from celllines a join assaynames b on ")
								   .append(" a.entity_id=b.cellline_id join assayphenomapping ")
			 					   .append(" c on c.assay_id=b.entity_id join phenotypes d on d.entity_id=c.pheno_id where a.tag=? ");
			if(lAssay!=null && lAssay.trim().length()>0){
				lQuery.append("  and b.tag=? ");
			}
			lPstmt=lConn.prepareStatement(lQuery.toString());
			lPstmt.setString(1, lCellLine);
			if(lAssay!=null && lAssay.trim().length()>0){
				lPstmt.setString(2, lAssay);
			}
			lRst=lPstmt.executeQuery();
			StringBuilder lXMLBuilder = new StringBuilder();
			if(lAssay!=null && lAssay.trim().length()>0){
				lXMLBuilder.append("<phenolist>");
				
			}else{
				
				lXMLBuilder.append("<phenolist>");
				lXMLBuilder.append("<pheno>");
				lXMLBuilder.append("<name>" + "--Select All--"+"</name>");
				lXMLBuilder.append("<tag>" + "SAA" +"</tag>");
				lXMLBuilder.append("</pheno>");
				
			}
			while(lRst.next()){
				lXMLBuilder.append("<pheno>");
				lXMLBuilder.append("<name>" + lRst.getString(1)+"</name>");
				lXMLBuilder.append("<tag>" + lRst.getString(2) +"</tag>");
				lXMLBuilder.append("</pheno>");
				
			}
 			lXMLBuilder.append("</phenolist>");
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(lXMLBuilder.toString());
		}catch(SQLException e){
			
		}
		finally 
		{
			try {
				lConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

}
