package com.catapp.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catapp.connection.DBConnection;

/**
 * Servlet implementation class ChemProperties
 */
@WebServlet("/ChemProperties")
public class ChemProperties extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChemProperties() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//HashMap<String, String> lChemicalMap = new HashMap<String, String>();
			PreparedStatement lPstmt 			= null;
			ResultSet lRst 						= null;
			Connection lConn 					= null;
			String lSelectedCasNumber			= null;
			try {
				lConn=new DBConnection().getConnection();
				lSelectedCasNumber=request.getParameter("cas");
				String lBuilder = "select cat_app_id,cas_number,concawe_id,name,category,ec_number,source_description"
						+ " From chemical_cas_concawe_mapping where cas_number=?";

				lPstmt = lConn.prepareStatement(lBuilder);
				lPstmt.setString(1, lSelectedCasNumber);
				lRst = lPstmt.executeQuery();
				//String lPropertyString="";
				StringBuilder lXMLBuilder = new StringBuilder();
				lXMLBuilder.append("<chemproperty>");
				while (lRst.next()) {
					lXMLBuilder.append("<chem>");
					lXMLBuilder.append("<cat_app_id>" + lRst.getString(1)+"</cat_app_id>");
					lXMLBuilder.append("<cas_number>" + lRst.getString(2) +"</cas_number>");
					lXMLBuilder.append("<concawe_id>" + lRst.getString(3) +"</concawe_id>");
					lXMLBuilder.append("<name>" + lRst.getString(4) +"</name>");
					lXMLBuilder.append("<category>" + lRst.getString(5) +"</category>");
					lXMLBuilder.append("<ec_number>" + lRst.getString(6) +"</ec_number>");
					lXMLBuilder.append("<desc>" + lRst.getString(7) +"</desc>");
					lXMLBuilder.append("</chem>");

				}
				lXMLBuilder.append("</chemproperty>");
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				
				File lFile = new File("C:\\Users\\CATAPP\\Logic\\Figs\\generated_graph1.png");
				if(lFile.exists()){
					lFile.delete();
				}
				
				response.getWriter().write(lXMLBuilder.toString());
				
				

			} catch (Exception e) {
				
			}
			
		
	}

}
