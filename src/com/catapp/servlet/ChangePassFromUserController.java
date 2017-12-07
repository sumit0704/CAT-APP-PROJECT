package com.catapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.catapp.action.Login;
import com.catapp.connection.DBConnection;
import com.catapp.entity.User;

@WebServlet(value="/ChangePassFromUserController")
public class ChangePassFromUserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
		response.setContentType("text/html");  
		HttpSession session=request.getSession(false);
		PrintWriter out = response.getWriter();
		//session.getAttribute("user");
        //PrintWriter out=response.getWriter();
		
		System.out.println("Session is"+session);
		try{
			if(session!=null){
				String password = request.getParameter("password");
				String lHashedPwd=Login.generateHash("PWD"+password);
				Connection lConn = new DBConnection().getConnection();
				//User lUser =new User();
				String email = ((User)request.getSession().getAttribute("user")).getEmail().toString();
				
		    	System.out.print("email is :"+email);
		        updateUserPassword(lHashedPwd, lConn, email);
				//request.getServletContext().getRequestDispatcher("/WEB-INF/userProfile.jsp").forward(request, response);
			}
		
		else{
			request.setAttribute("error","Current session is lost. Please log in");
			//request.getRequestDispatcher("/LoadDataForHome").include(request, response);  
			}
		}	
		finally{
			out.close();
		}
	      }
	
	public void updateUserPassword(String pwd,Connection pConnection, String email){
		
		PreparedStatement lPstmnt = null;
		ResultSet lRst			  = null;
		User lUser 				  = new User();
		
		try{
			StringBuilder lBuilder = new StringBuilder("update users set password=? where email=?");
			
			lPstmnt = pConnection.prepareStatement(lBuilder.toString());
			lPstmnt.setString(1, pwd);
			lPstmnt.setString(2, email);

		      // execute the java preparedstatement
			lPstmnt.executeUpdate();
			
			
		}catch(Exception e){
			System.out.println(e);
			//LOGGER.error("Error Occured while fetching user details", e);
		}
		finally 
		{
			try {
				pConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}