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

import org.apache.log4j.Logger;

import com.catapp.connection.DBConnection;

/**
 * Servlet implementation class AssayNames
 */
@WebServlet("/AssayNames")
public class AssayNames extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger logger = Logger.getLogger(AssayNames.class.toString());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssayNames() {
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
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		String lCellLine 		  = 			request.getParameter("lCM");
		Connection lConn	   	  = 			null;
		PreparedStatement lPstmt  = 	        null;
		ResultSet lRst            = 			null;
		
		try{
			lConn=new DBConnection().getConnection();
			StringBuilder lQuery = new StringBuilder(" select a.name,a.tag From assaynames a join celllines b on ")
								   .append("  a.cellline_id=b.entity_id where b.tag=?  ");
			 					  
			
			lPstmt=lConn.prepareStatement(lQuery.toString());
			lPstmt.setString(1, lCellLine);
			
			lRst=lPstmt.executeQuery();
			StringBuilder lXMLBuilder = new StringBuilder();
			lXMLBuilder.append("<assaylist>");
				
			while(lRst.next()){
				lXMLBuilder.append("<assay>");
				lXMLBuilder.append("<name>" + lRst.getString(1)+"</name>");
				lXMLBuilder.append("<tag>" + lRst.getString(2) +"</tag>");
				lXMLBuilder.append("</assay>");
				
			}
 			lXMLBuilder.append("</assaylist>");
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(lXMLBuilder.toString());
		}catch(SQLException e){
			logger.error("Error Occured while getting assay names",e);
		}
		finally{
			try{
				if(lConn!=null){
					lConn.close();
				}
			}catch(Exception e1){
				logger.error("Error Occured while closing connection",e1);
			}
		}
	
	}

}
