package com.catapp.servlet;

import java.io.IOException;
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

import com.catapp.action.Login;
import com.catapp.connection.DBConnection;
import com.catapp.entity.User;  
@WebServlet(value="/LoginServlet")
public class LoginServlet extends HttpServlet {  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	//private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response)  
	                    throws ServletException, IOException {    
			String email=request.getParameter("email").toString();  
	        String password=request.getParameter("password");  
	        Connection lConn = new DBConnection().getConnection();
	        
	        try{
	        	User lUser =new User();
        
	        	lUser =fetchUserDetails(email, lConn);
	        	boolean lFlag =validateUsers(password, lUser.getPassword());
	        	
	        	if(lFlag){
	        		if(lUser.getIs_admin()!=null && lUser.getIs_admin().equals("Y")){
	        			HttpSession session=request.getSession();  
	        			session.setAttribute("email",email);
	        			session.setAttribute("user", lUser);
	        			request.getRequestDispatcher("/WEB-INF/adminUser.jsp").include(request, response);
	        			
	        		}else{
	        			if(lUser.getApproved()!=null && lUser.getApproved().equals("Y")){
	        				
	        				HttpSession session=request.getSession();  
	        				session.setAttribute("email",email);
	        				session.setAttribute("user", lUser);
	        				/*User lUserToSave = new User();
	        				lUserToSave.setEntityId(lUser.getEntityId());
	        				lUserToSave.find(lConn, lUserToSave);
	        				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        				lUserToSave.setLast_login_time(timestamp);
	        				lUserToSave.save(lConn, lUserToSave);*/
	        				request.getRequestDispatcher("/WEB-INF/welcomeUserHome.jsp").include(request, response);
	        				
	        			}else{
	        				
	        				//request.setAttribute("error","Your request is not approved yet.");
	    		        	request.getRequestDispatcher("/WEB-INF/index.jsp").include(request, response);  
	        			}
	        			
	        		}
	        	}else{
	        		//request.setAttribute("error","Invalid Username or Password");
		        	request.getRequestDispatcher("/WEB-INF/index.jsp?failure=2").include(request, response);  
	        	}
	        	
	        }catch(Exception e){
	        	LOGGER.error("Error Occured while validating user",e);
	        }finally{
	        	try{
	        		if(lConn!=null){
	        			lConn.close();
	        		}
	        	}catch(Exception e1){
	        		LOGGER.error("Error Occured while closing connection",e1);
	        	}
	        }
	        
	      }  
		public Boolean validateUsers(String lTypedPassword, String lStoredPassword ){
			
			boolean lFlag=false;
			String lHashedPwd=Login.generateHash("PWD"+lTypedPassword);
			if(lStoredPassword.equals(lHashedPwd)){
				lFlag=true;
			}else{
				lFlag=false;
			}
			return lFlag;
		}
		public User fetchUserDetails(String pEmail,Connection pConnection){
			
			PreparedStatement lPstmnt = null;
			ResultSet lRst			  = null;
			User lUser 				  = new User();
			
			try{
				StringBuilder lBuilder = new StringBuilder("select a.entity_id,a.email,a.first_name,a.last_name,a.password,a.approved,a.is_admin,a.supervisor_name,a.institution,a.last_login_time from users a where a.email=? ");
										 lBuilder.append(" and a.is_active='Y' and a.rowstate!=-1");
				
				lPstmnt = pConnection.prepareStatement(lBuilder.toString());
				lPstmnt.setString(1, pEmail);
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
					if(lRst.getTimestamp(10)!=null){
						lUser.setLast_login_time(lRst.getTimestamp(10));
					}
					
				}
				
				
			}catch(Exception e){
				LOGGER.error("Error Occured while fetching user details", e);
			}
			return lUser;
		}
	}  

