package com.catapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.catapp.connection.DBConnection;
import com.catapp.entity.User;



/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet("/UserManagementServlet")
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// PrintWriter out = response.getWriter();
		// out.println("DogName_Servlet.java: Hello Java!");
		
		String received_value = request.getParameter("what");	// receiving the post value
		request.setAttribute("what", received_value);			// submit vlue to following page:
		
		String received_Email = request.getParameter("Email");	// receiving the post value
		request.setAttribute("Email", received_Email);			// submit vlue to following page:
		
		String email= received_Email; 
	    Connection lConn = new DBConnection().getConnection();
		
		
		User lUser =new User();
      	lUser =fetchUserDetails(email, lConn);
		
      	HttpSession session=request.getSession();  
			session.setAttribute("email",email);
			session.setAttribute("user", lUser);

		
		// request.getRequestDispatcher("/WEB-INF/UserManagement.jsp").include(request, response);

		getServletContext().getRequestDispatcher("/WEB-INF/UserManagement.jsp").forward(request, response);
	}

	


	public User fetchUserDetails(String pEmail, Connection pConnection){
		
		PreparedStatement lPstmnt = null;
		ResultSet lRst			  = null;
		User lUser 				  = new User();
		
		try{
			
			String query_string = "select entity_id, email, first_name, last_name, password, approved, "
			 	+ "is_admin, supervisor_name, Institution, last_login_time, Phone_Number, supervisor_phone, "
			 	+ "Supervisor_Email"
				+ "	 from users where email='" + pEmail + "'";
			// System.out.println("Hello World: " + query_string);	
			
			// String query_string = "select * from users where email='" + pEmail + "'";			 
									 
			lPstmnt = pConnection.prepareStatement(query_string);
			// lPstmnt.setString(1, pEmail);
			lRst=lPstmnt.executeQuery();
			
			while(lRst.next()){
				lUser.setEntityId(lRst.getLong(1));
				lUser.setEmail(lRst.getString(2));
				lUser.setFirst_name(lRst.getString(3));
				lUser.setLast_name(lRst.getString(4));
				lUser.setPassword(lRst.getString(5));
				lUser.setApproved(lRst.getString(6));
				lUser.setIs_admin(lRst.getString(7));
				lUser.setSupervisorname(lRst.getString(8));
				lUser.setInstitution(lRst.getString(9));
				lUser.setLoggedBy(lRst.getLong(10));
				lUser.setPhone_number(lRst.getString(11));
				lUser.setSupervisorphone(lRst.getString(12));
				lUser.setSupervisoremail(lRst.getString(13));
				}	
			
		}catch(Exception e){
			LOGGER.error("Error Occured while fetching user details", e);
		}
		return lUser;
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
		doGet(request, response);
	}



}
