package com.catapp.servlet;
import java.io.IOException;  
import java.io.PrintWriter;  
	  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession; 
@WebServlet(value="/BackToHomeServlet")
public class BackToHomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
		response.setContentType("text/html");  
		HttpSession session=request.getSession(false);
		PrintWriter out = response.getWriter();
		//session.getAttribute("user");
        //PrintWriter out=response.getWriter();
		System.out.println("Session is"+session);
		try{
			if(session!=null){
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
		
}