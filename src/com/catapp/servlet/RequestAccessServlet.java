package com.catapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.catapp.connection.DBConnection;
import com.catapp.entity.User;  
@WebServlet(value="/RequestAccessServlet")
public class RequestAccessServlet extends HttpServlet {  
	private static final Logger LOGGER = Logger.getLogger(RequestAccessServlet.class);
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response)  
	                    throws ServletException, IOException {  
			Connection lConn=null;
			
	        try{
	        	String forgotEmail=request.getParameter("forgotEmail").toString();   
	        	HttpSession session=request.getSession();  
	        	session.setAttribute("forgotEmail",forgotEmail);
	        	 lConn = new DBConnection().getConnection();
	        	 String lQuest1=null;
	        	 String lQuest2=null;
	        	 String lQuest3=null;
	        	boolean lVailidityflag=ForgotPasswordServlet.pAuthenticateEmail(forgotEmail, lConn);
	        	if(lVailidityflag){
	        		User lUser =new LoginServlet().fetchUserDetails(forgotEmail, lConn);
	        		HashMap<Long,String>pSQMap = new LoadDataForHome().getSecurityQuestionsForUsers(lUser.getEntityId());
	        		request.setAttribute("secqu", pSQMap);
	        		request.getSession().setAttribute("user", lUser);
	        		if(pSQMap.isEmpty()){
	        			request.getRequestDispatcher("/WEB-INF/ForPassPage.jsp?page=3").include(request, response);

	        		}else{
	        			int i=0;
	        			for(Long lKey:pSQMap.keySet()){
	        				if(i==0){
	        					lQuest1=pSQMap.get(lKey);
	        				}else if(i==1){
	        					lQuest2=pSQMap.get(lKey);
	        				}else if(i==2){
	        					lQuest3=pSQMap.get(lKey);
	        				}
	        				i++;
	        			}
	        			request.setAttribute("qu1", lQuest1);
	        			request.setAttribute("qu2", lQuest2);
	        			request.setAttribute("qu3", lQuest3);
	        			request.getRequestDispatcher("/WEB-INF/ForPassPage.jsp?page=1").include(request, response);
	        		}
	        		
	        	}else{
	        		request.getRequestDispatcher("/WEB-INF/ForPassPage.jsp?page=4").include(request, response);
	        	}
	        	
	        }catch(Exception e){
	        	LOGGER.error("Error Occured while redirecting the request",e);
	        }finally{
	        	try{
	        		if(lConn!=null){
	        			lConn.close();
	        		}
	        	}catch(Exception e1){
	        		LOGGER.error("Error Occured while closing the connection",e1);
	        	}
	        }
	       
	        
	    }  
	}  


