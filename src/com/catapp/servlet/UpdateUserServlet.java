package com.catapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.catapp.action.Login;
import com.catapp.connection.DBConnection;
import com.catapp.entity.User;
import org.apache.log4j.Logger;

@WebServlet(value="/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(SubmittedRequestServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
		response.setContentType("text/html");  
		HttpSession session=request.getSession(false);
		PrintWriter out = response.getWriter();
		
		System.out.println("Inside update user");
		try{
			if(session!=null){
				long id= ((User)request.getSession().getAttribute("user")).getEntityId();
				updateUserToDB(request,id);
				request.setAttribute("success","Your information is saved");
				request.getServletContext().getRequestDispatcher("/WEB-INF/welcomeUserHome.jsp").forward(request, response);
			}
		
		else{
			request.setAttribute("error","Current session is lost. Please log in");
			request.getRequestDispatcher("/LoadDataForHome").include(request, response);  
			}
		}	
		finally{
			out.close();
		}
	      }
	
	public void updateUserToDB(HttpServletRequest request,long id){
		
		String lFirstName 				= null;
		String lLastName  				= null;
		
		String lPhone	  				= null;
		
		String lInstitution				= null;
		
		Connection lConn				= null;
		PreparedStatement lPst			= null;
		try{
			
			
			if(request.getParameter("firstName")!=null){
				lFirstName = request.getParameter("first_name");
			}
			else{
				lFirstName = ((User)request.getSession().getAttribute("user")).getFirst_name().toString();
			}
			if(request.getParameter("lastName")!=null){
				lLastName = request.getParameter("last_name");
			}
			else{
				lLastName = ((User)request.getSession().getAttribute("user")).getLast_name().toString();
			}
			
			if(request.getParameter("phNo")!=null){
				lPhone = request.getParameter("phone_number");
			}
			else{
				lPhone = ((User)request.getSession().getAttribute("user")).getPhone_number().toString();
			}
			
			if(request.getParameter("instName")!=null){
				lInstitution = request.getParameter("institution");
			}
			else{
				lInstitution = ((User)request.getSession().getAttribute("user")).getInstitution().toString();
			}
			
			lConn= new DBConnection().getConnection();
			LOGGER.error("This is Connection------>  "+lConn);
			User lUser = new User();
			
			lUser.setFirst_name(lFirstName);
			lUser.setLast_name(lLastName);
			//lUser.setEmail(lEmail);
			lUser.setPhone_number(lPhone);
			lUser.setInstitution(lInstitution);
			
			//lUser.setIs_active("Y");
			//lUser.save(lConn, lUser);
			
					StringBuilder lInsertQuery = new StringBuilder( "update users set first_name=?, last_name=?, institution=?, phone_number=? where entity_id=?)");
					lInsertQuery.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
					
					lPst= lConn.prepareStatement(lInsertQuery.toString());
					lPst.setString(1, lUser.getFirst_name());
					lPst.setString(2, lUser.getLast_name());
					lPst.setString(3, lUser.getInstitution());
					lPst.setString(4, lUser.getPhone_number());
					lPst.setLong(5, id);
					
					lPst.execute();
					
				
			
	
			
		}catch(Exception e){
			LOGGER.error("Error Occured while saving user details.",e);
		}
		finally{
			try{
				if(lConn!=null){
					lConn.close();
				}
				if(lPst!=null){
					lPst.close();
				}
			}catch(Exception e1){
				LOGGER.error("Error Occured while closing connection.",e1);
			}
		}
		
	}
		
}