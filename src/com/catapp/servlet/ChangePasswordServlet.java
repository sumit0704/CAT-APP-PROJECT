package com.catapp.servlet;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
@WebServlet(value="/ChangePassword")
public class ChangePasswordServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
		response.setContentType("text/html");  
        //PrintWriter out=response.getWriter();
		String lUserId=request.getQueryString().split("user_id=")[1];
		request.setAttribute("user_id",  URLEncoder.encode(lUserId,"UTF-8") );
		

		System.out.println(request.getParameter("user_id"));
		
	      request.getServletContext().getRequestDispatcher("/WEB-INF/changePassword.jsp").forward(request, response);}
		
}