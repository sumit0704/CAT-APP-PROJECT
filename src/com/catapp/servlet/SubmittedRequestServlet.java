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

import org.apache.log4j.Logger;

import com.catapp.action.Login;
import com.catapp.connection.DBConnection;
import com.catapp.entity.User;  
@WebServlet(value="/SubmittedRequestServlet")
public class SubmittedRequestServlet extends HttpServlet{
	private static final Logger LOGGER = Logger.getLogger(SubmittedRequestServlet	.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
	response.setContentType("text/html");  
    //PrintWriter out=response.getWriter();
    saveUserToDB(request);
    request.getRequestDispatcher("/WEB-INF/requestSubmitted.jsp").include(request, response);
	}
	
	public void saveUserToDB(HttpServletRequest request){
		
		String lFirstName 				= null;
		String lLastName  				= null;
		String lEmail     				= null;
		String lPhone	  				= null;
		String lSuperVisor  			= null;
		String lSuperVisor_ph			= null;
		String lSuperVisor_em			= null;
		String lInstitution				= null;
		String lPassword				= null;
		String lQue1					= null;
		String lAns1					= null;
		String lQue2					= null;
		String lAns2					= null;
		String lQue3					= null;
		String lAns3					= null;
		Connection lConn				= null;
		PreparedStatement lPst			= null;
		try{
			if(request.getParameter("first_name")!=null){
				lFirstName = request.getParameter("first_name");
			}
			if(request.getParameter("last_name")!=null){
				lLastName = request.getParameter("last_name");
			}
			if(request.getParameter("email")!=null){
				lEmail = request.getParameter("email");
			}
			if(request.getParameter("phone_number")!=null){
				lPhone = request.getParameter("phone_number");
			}
			if(request.getParameter("supervisor_name")!=null){
				lSuperVisor = request.getParameter("supervisor_name");
			}
			if(request.getParameter("supervisor_phone")!=null){
				lSuperVisor_ph = request.getParameter("supervisor_phone");
			}
			if(request.getParameter("supervisor_email")!=null){
				lSuperVisor_em = request.getParameter("supervisor_email");
			}
			if(request.getParameter("institution")!=null){
				lInstitution = request.getParameter("institution");
			}
			if(request.getParameter("repassword")!=null){
				lPassword = request.getParameter("repassword");
			}
			if(request.getParameter("sec1")!=null){
				lQue1 = request.getParameter("sec1");
			}
			if(request.getParameter("ans1")!=null){
				lAns1 = request.getParameter("ans1");
			}
			if(request.getParameter("sec2")!=null){
				lQue2 = request.getParameter("sec2");
			}
			if(request.getParameter("ans2")!=null){
				lAns2 = request.getParameter("ans2");
			}
			if(request.getParameter("sec3")!=null){
				lQue3 = request.getParameter("sec3");
			}
			if(request.getParameter("ans3")!=null){
				lAns3 = request.getParameter("ans3");
			}
			lConn= new DBConnection().getConnection();
			LOGGER.error("This is Connection------>  "+lConn);
			User lUser = new User();
			lUser.setPassword(Login.generateHash("PWD"+lPassword));
			lUser.setFirst_name(lFirstName);
			lUser.setLast_name(lLastName);
			lUser.setEmail(lEmail);
			lUser.setPhone_number(lPhone);
			lUser.setSupervisorname(lSuperVisor);
			lUser.setSupervisorphone(lSuperVisor_ph);
			lUser.setSupervisoremail(lSuperVisor_em);
			lUser.setInstitution(lInstitution);
			lUser.setIs_active("Y");
			lUser.save(lConn, lUser);
			if(lUser.getEntityId()!=null){
				for(int i=0;i<3;i++){
					String lQue = null;
					String lAns = null;
					if(i==0){
						lQue = lQue1;
						lAns = lAns1;
					}
					if(i==1){
						lQue = lQue2;
						lAns = lAns2;
					}
					if(i==2){
						lQue = lQue3;
						lAns = lAns3;
					}
					StringBuilder lInsertQuery = new StringBuilder( "insert into security_questions_answers (question_id, answer, user_id, logged_date, logged_by, last_updated_date, last_updated_by, is_active, rowstate)");
					lInsertQuery.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
					
					lPst= lConn.prepareStatement(lInsertQuery.toString());
					lPst.setLong(1, Long.parseLong(lQue));
					lPst.setString(2, lAns);
					lPst.setLong(3, lUser.getEntityId());
					lPst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
					lPst.setLong(5, lUser.getEntityId());
					lPst.setTimestamp(6, null);
					lPst.setString(7, null);
					lPst.setString(8,"Y");
					lPst.setInt(9, 1);
					lPst.execute();
					
				}
			}
			
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
