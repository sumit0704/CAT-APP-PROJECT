package com.catapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catapp.connection.DBConnection;


/**
 * Servlet implementation class LoadDataForHome
 */
@WebServlet("/LoadDataForHome")
public class LoadDataForHome extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(LoadDataForHome.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadDataForHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<Long,String>lListOfSq =getSecurityQuestions();
		request.setAttribute("secqu", lListOfSq);
	  	RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
	    rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public HashMap<Long,String> getSecurityQuestions(){
		
		HashMap<Long,String>lListofSecurity = new HashMap<Long,String>();
		lListofSecurity.put(0l, "-----Select One ------");
		Connection lConn 		  = null;
		PreparedStatement lPstmnt = null;
		ResultSet   lResultSet	  = null;
		try{
			lConn= new DBConnection().getConnection();
			
		StringBuilder lBuilder = new StringBuilder("select entity_id,security_question from security_questions");
								 lBuilder.append( " where rowstate!=-1 and is_active='Y'");
		lPstmnt=lConn.prepareStatement(lBuilder.toString());
		lResultSet=lPstmnt.executeQuery();
		while(lResultSet.next()){
			
			lListofSecurity.put(lResultSet.getLong(1),lResultSet.getString(2));
		}
		
		}catch(Exception e){
			LOGGER.error("Error Occured while fetching security questions in method getSecurityQuestions------------>LoadDataForHome",e);
		}finally{
			try{
				if(lConn!=null){
					lConn.close();
				}if(lPstmnt!=null){
					lPstmnt.close();
				}
			}catch(Exception e){
				LOGGER.error("Error Occured while closing connection",e);
			}
		}
		return 	lListofSecurity;
				
	}

}
