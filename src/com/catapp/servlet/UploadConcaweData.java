package com.catapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.catapp.action.ChemData;
import com.catapp.connection.DBConnection;

/**
 * Servlet implementation class UploadConcaweData
 */
@WebServlet("/UploadConcaweData")
public class UploadConcaweData extends HttpServlet {
	public static final Logger logger = Logger.getLogger(UploadConcaweData.class.toString());
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public UploadConcaweData() {
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection lConn = null;
		
		try{
			lConn= new DBConnection().getConnection();
			HashMap<String,String>lCASNumberMap =  new ChemData().getCasNames(lConn,1l);
			
			//HashMap<String,String>lConcaweMap =  new ChemData().getCasNames(lConn,2l);
			request.setAttribute("casnumber", lCASNumberMap);
			//request.setAttribute("concawe", lConcaweMap);
			HttpSession session=request.getSession(false);
			if(session!=null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/UploadConcaweData.jsp");
			rd.forward(request, response);
			}
			else{
				request.setAttribute("error","Current session is lost. Please log in");
				request.getRequestDispatcher("/LoadDataForHome").include(request, response);  
				}
			
		}catch(Exception e){
			logger.error("Error Occured while dispatching the request",e);
		}finally{
			if(lConn!=null){
				try{
					lConn.close();
				}catch(Exception e){
					logger.error("Error Occured while closing the connection",e);
				}
			}
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
