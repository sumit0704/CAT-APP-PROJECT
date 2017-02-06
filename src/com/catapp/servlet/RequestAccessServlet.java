package com.catapp.servlets;

import java.io.IOException;  
import java.io.PrintWriter;  
	  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
@WebServlet(value="/RequestAccessServlet")
public class RequestAccessServlet extends HttpServlet {  
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
	        response.setContentType("text/html");  
	        PrintWriter out=response.getWriter();  
	        //request.getRequestDispatcher("link.html").include(request, response);  
	        //int totalAttempts = 3;  
	        String action = request.getParameter("action");
	        
	            
	         //else if ("SecondServlet".equals(action)) {
	            // Invoke SecondServlet's job here.
	        //}
	        
	        String forgotEmail=request.getParameter("forgotEmail").toString();  
	        //String password=request.getParameter("password");  
	        
	        
	        
	        HttpSession session=request.getSession();  
	        session.setAttribute("forgotEmail",forgotEmail);
	        //System.out.println("Session "+request.getSession().getAttribute(email));
	        request.getRequestDispatcher("/WEB-INF/ForPassPage.jsp").include(request, response);
	       
	        
	        out.close();  
	    }  
	}  


