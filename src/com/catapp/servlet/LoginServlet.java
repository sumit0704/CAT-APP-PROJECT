package com.catapp.servlets;

import java.io.IOException;  
import java.io.PrintWriter;  
	  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
@WebServlet(value="/LoginServlet")
public class LoginServlet extends HttpServlet {  
	    /**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response)  
	                    throws ServletException, IOException {  
	        response.setContentType("text/html");  
	        PrintWriter out=response.getWriter();  
	        //request.getRequestDispatcher("link.html").include(request, response);  
	        //int totalAttempts = 3;  
	        String action = request.getParameter("action");

	        
	            
	        
	        String email=request.getParameter("email").toString();  
	        String password=request.getParameter("password");  
	        
	        
	        if(password.equals("admin123")){  
	        //out.print("Welcome, "+name);  
	        HttpSession session=request.getSession();  
	        session.setAttribute("email",email);
	        //System.out.println("Session "+request.getSession().getAttribute(email));
	        request.getRequestDispatcher("/WEB-INF/adminUser.jsp").include(request, response);
	        }  
	        else if(password.equals("user123")){
	        	HttpSession session=request.getSession();  
		        session.setAttribute("email",email);
		        //System.out.println("Session "+request.getSession().getAttribute(email));
		        request.getRequestDispatcher("/WEB-INF/welcomeUserHome.jsp").include(request, response);
		        	
	        }
	        else{  
	            //out.print("Sorry, username or password error!");  
	        	request.setAttribute("error","Invalid Username or Password");
	        	request.getRequestDispatcher("/WEB-INF/credentialMismatch.jsp").include(request, response);  
	        }  
	        
	        
	        out.close();  
	    }  
	}  

