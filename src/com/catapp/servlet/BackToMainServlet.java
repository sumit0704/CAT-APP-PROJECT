package com.catapp.servlet;
import java.io.IOException;  
import java.io.PrintWriter;  
	  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
@WebServlet(value="/BackToMainServlet")
public class BackToMainServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
		//String pathInfo = request.getPathInfo();
	      request.getRequestDispatcher("/LoadDataForHome").forward(request, response);
	      HttpSession session=request.getSession();  
          session.invalidate();
          }
}
