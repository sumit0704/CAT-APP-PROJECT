package com.catapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catapp.action.CatAppConstants;
import com.catapp.action.ChemData;

/**
 * Servlet implementation class RedirectionServlet
 */
@WebServlet("/redirect")
public class RedirectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger logger = Logger.getLogger(RedirectionServlet.class.toString());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Start of method doGet|| destination param::"+request.getParameter("destination"));
		String destination=request.getParameter("destination");
		
		if(CatAppConstants.HOME.equals(destination))
		{
			request.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			
		}
		else if(CatAppConstants.ABOUT.equals(destination))
		{
			request.getServletContext().getRequestDispatcher("/WEB-INF/about.jsp").forward(request, response);
	
		}
		else if(CatAppConstants.CONTACT.equals(destination))
		{
			request.getServletContext().getRequestDispatcher("/WEB-INF/contact.jsp").forward(request, response);

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
