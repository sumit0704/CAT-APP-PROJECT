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
 * Servlet implementation class TimePoint
 */
@WebServlet("/TimePoint")
public class TimePoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger logger = Logger.getLogger(TimePoint.class.toString());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimePoint() {
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
		String lAssay 		      = 			request.getParameter("lAssay");
		String lPhenotype  		  = 			request.getParameter("lPhenotype");
		Connection lConn	   	  = 			null;
		PreparedStatement lPstmt  = 	        null;
		ResultSet lRst            = 			null;
		
		try{
			lConn=new DBConnection().getConnection();
			StringBuilder lQuery = new StringBuilder("select DISTINCT b.timepoint,a.timepoint From timepoints a join timepoint_metadata b on b.entity_id=a.timepoint where a.celltag=? ");
			
			if(lPhenotype!=null && lAssay!=null){
				lQuery.append(" and a.phenotypetag=? and a.assaytag=?");
			}
			lPstmt=lConn.prepareStatement(lQuery.toString());
			lPstmt.setString(1, lCellLine);

			if(lPhenotype!=null && lAssay!=null){
				lPstmt.setString(2, lPhenotype);
				lPstmt.setString(3, lAssay);
			}
			lRst=lPstmt.executeQuery();
			StringBuilder lXMLBuilder = new StringBuilder();
			lXMLBuilder.append("<timepoint>");
				
			while(lRst.next()){
				lXMLBuilder.append("<tp>");
				lXMLBuilder.append("<entity_id>" + lRst.getString(1)+"</entity_id>");
				lXMLBuilder.append("<name>" + lRst.getString(2) +"</name>");
				lXMLBuilder.append("</tp>");
				
			}
 			lXMLBuilder.append("</timepoint>");
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
